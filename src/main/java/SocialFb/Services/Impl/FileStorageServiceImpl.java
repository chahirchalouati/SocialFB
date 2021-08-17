package SocialFb.Services.Impl;

import SocialFb.Exceptions.InvalidAttachmentTypeException;
import SocialFb.Exceptions.ResourceNotFoundException;
import SocialFb.Helpers.*;
import SocialFb.Models.Attachment;
import SocialFb.Models.FileDetails;
import SocialFb.Models.Size;
import SocialFb.Providers.FileProperties;
import SocialFb.Services.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {

    private final AttachmentsHelper attachmentsHelper;
    private final ImageResizeHelper imageResizeHelper;
    private final FileDetailsHepler fileDetailsHepler;
    private final PathHelper pathHelper;

    private final String FILE_STORE_LOCATION;
    private final List<Size> sizes = List.of(
            new Size(250, 180),
            new Size(320, 240),
            new Size(560, 420),
            new Size(780, 640),
            new Size(1024, 860),
            new Size(860, 1024)
    );

    public FileStorageServiceImpl (AttachmentsHelper attachmentsHelper,
                                   ImageResizeHelper imageResizeHelper,
                                   FileDetailsHepler fileDetailsHepler, @Autowired FileProperties fileProperties,
                                   PathHelper pathHelper) {
        this.attachmentsHelper = attachmentsHelper;
        this.imageResizeHelper = imageResizeHelper;
        this.fileDetailsHepler = fileDetailsHepler;
        this.FILE_STORE_LOCATION = fileProperties.getFileStoreLocation().trim().toLowerCase(Locale.ROOT);
        this.pathHelper = pathHelper;
        this.createLocalStore();
    }

    @Override
    public void createStore (String storeLocation) throws IOException {
        FileStorageService.super.createStore(storeLocation);
    }

    @Override
    public List<FileDetails> storeFile (MultipartFile multipartFile) {
        switch (AttachmentTypeHelper.resolve(multipartFile)) {
            case IMAGE:
                return this.storeImage(multipartFile);
            case VIDEO:
                return this.storeVideo(multipartFile);
            case FILE:
            case PDF:
                return this.storeOtherFile(multipartFile);
            default:
                throw new InvalidAttachmentTypeException("");
        }
    }

    public Set<Attachment> storeFiles (List<MultipartFile> files) {
        return files.stream()
                .map(this::storeFile)
                .map(this.attachmentsHelper::buildAttachments)
                .collect(Collectors.toSet());

    }


    private void copyFile (MultipartFile multipartFile, Path filePath) {
        try {
            this.createLocalStore();
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            log.info("FileStorageServiceImpl success filename : {}, path : {}", multipartFile.getOriginalFilename(), filePath);
        } catch (IOException e) {
            log.error("FileStorageServiceImpl.copyFile error when storing file %s error : { }", e);
            e.printStackTrace();
        }
    }

    private void createLocalStore () {
        if ( !Files.exists(Paths.get(this.FILE_STORE_LOCATION).toAbsolutePath().normalize()) ) {
            try {
                this.createStore(this.FILE_STORE_LOCATION);
                log.info("createLocalStore success");
            } catch (IOException e) {
                log.error("createLocalStore failed : { }", e);
                e.printStackTrace();
            }
        }
    }


    @NotNull
    private String getFileName (MultipartFile multipartFile) {

        return MessageFormat.format("{0}{1}{2}", UUID.randomUUID(), ".", Objects.requireNonNull(multipartFile.getOriginalFilename()).split("\\.")[1]);
    }

    @Override
    public Resource getResource (FileDetails fileDetails) {
        Path path = Paths.get(fileDetails.getPath()).normalize().toAbsolutePath();
        try {
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException(fileDetails.getName().concat(" does not exist ."));
        }

    }

    @Override
    public List<FileDetails> storeResisedImages (MultipartFile multipartFile) {
        List<FileDetails> fileDetails = new ArrayList<>();
        try {
            sizes.forEach(size -> {
                String filename = this.getFileName(multipartFile);
                final Path filePath = this.pathHelper.getFilePath(filename);
                this.copyFile(multipartFile, filePath);
                this.imageResizeHelper.imageResizer(multipartFile, size, filePath);
                fileDetails.add(this.fileDetailsBuilder(multipartFile, filename, filePath, size));
                log.info("created file with size : {}", size);
            });

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);

        }
        return fileDetails;
    }

    @Override
    public List<FileDetails> storeImage (MultipartFile multipartFile) {
        String filename = this.getFileName(multipartFile);
        log.info("storeFile multipartFile filename {}", multipartFile.getOriginalFilename());
        final Path filePath = this.pathHelper.getFilePath(filename);
        this.copyFile(multipartFile, filePath);
        final String ENDPOINT_PREFIX = "files";

        List<FileDetails> fileDetailsList = this.storeResisedImages(multipartFile);

        fileDetailsList.add(FileDetails.builder()
                .length(multipartFile.getSize())
                .name(filename)
                .type(multipartFile.getContentType())
                .url(ENDPOINT_PREFIX.concat("/").concat(filename))
                .path(filePath.toAbsolutePath().normalize().toString())
                .isOriginal(true)
                .sizeLabel(FileDetails.SizeLabel.ORIGINAL)
                .size(null)
                .build());
        return fileDetailsList;
    }

    @Override
    public List<FileDetails> storeVideo (MultipartFile multipartFile) {
        return this.getFileDetails(multipartFile);
    }

    @NotNull
    private List<FileDetails> getFileDetails (MultipartFile multipartFile) {
        String filename = this.getFileName(multipartFile);
        log.info("storeFile multipartFile filename {}", multipartFile.getOriginalFilename());
        final Path filePath = this.pathHelper.getFilePath(filename);
        this.copyFile(multipartFile, filePath);
        final String ENDPOINT_PREFIX = "files";

        return List.of(FileDetails.builder()
                .length(multipartFile.getSize())
                .name(filename)
                .type(multipartFile.getContentType())
                .url(ENDPOINT_PREFIX.concat("/").concat(filename))
                .path(filePath.toAbsolutePath().normalize().toString())
                .isOriginal(true)
                .sizeLabel(FileDetails.SizeLabel.ORIGINAL)
                .size(null)
                .build());
    }

    @Override
    public List<FileDetails> storeOtherFile (MultipartFile multipartFile) {
        return this.getFileDetails(multipartFile);
    }


    private FileDetails fileDetailsBuilder (MultipartFile multipartFile, String filename, Path filePath, Size size) {
        return FileDetails.builder()
                .length(multipartFile.getSize())
                .name(filename)
                .type(multipartFile.getContentType())
                .url("files".concat("/").concat(filename))
                .path(filePath.toAbsolutePath().normalize().toString())
                .size(size)
                .sizeLabel(this.fileDetailsHepler.sizeLabelHandler(size))
                .isOriginal(false)
                .build();
    }


}

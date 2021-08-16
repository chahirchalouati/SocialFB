package SocialFb.Services.Impl;

import SocialFb.Exceptions.ResourceNotFoundException;
import SocialFb.Models.FileDetails;
import SocialFb.Models.ResizeDetails;
import SocialFb.Providers.FileProperties;
import SocialFb.Services.FileStorageService;
import lombok.Getter;
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
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {
    @Getter
    private final String FILE_STORE_LOCATION;


    public FileStorageServiceImpl (@Autowired FileProperties fileProperties) {
        this.FILE_STORE_LOCATION = fileProperties.getFileStoreLocation().trim().toLowerCase(Locale.ROOT);
        this.createLocalStore();

    }

    @Override
    public void createStore (String storeLocation) throws IOException {
        FileStorageService.super.createStore(storeLocation);
    }

    @Override
    public FileDetails storeFile (MultipartFile multipartFile) {
        String filename = this.getFileName(multipartFile);
        final Path filePath = this.getFilePath(filename);
        this.copyFile(multipartFile, filePath);
        final String ENDPOINT_PREFIX = "files";
        return FileDetails.builder()
                .length(multipartFile.getSize())
                .name(filename)
                .type(multipartFile.getContentType())
                .url(ENDPOINT_PREFIX.concat("/").concat(filename))
                .path(filePath.toAbsolutePath().normalize().toString())
                .build();

    }

    public void storeResizedImage (List<ResizeDetails> resizeDetails) {
      resizeDetails.stream().forEach(System.out::println);
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

    public Path getFilePath (String filename) {

        return Paths
                .get(this.getRelativePath(filename))
                .normalize()
                .toAbsolutePath();
    }


    @NotNull
    private String getRelativePath (String filename) {
        return this.FILE_STORE_LOCATION
                .concat("/")
                .concat(filename);
    }

    @NotNull
    private String getFileName (MultipartFile multipartFile) {
        return UUID.randomUUID() + "." + Objects.requireNonNull(multipartFile.getOriginalFilename()).split("\\.")[1];
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


}

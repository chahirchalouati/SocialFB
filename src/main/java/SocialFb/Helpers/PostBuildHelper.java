package SocialFb.Helpers;

import SocialFb.Enums.AttachmentType;
import SocialFb.Exceptions.FileOperationException;
import SocialFb.Models.Attachment;
import SocialFb.Models.FileDetails;
import SocialFb.Models.ResizeDetails;
import SocialFb.Models.Size;
import SocialFb.Requests.PostCreateRequest;
import SocialFb.Services.Impl.FileStorageServiceImpl;
import SocialFb.Utils.Src;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostBuildHelper {
    private final FileStorageServiceImpl fileStorageService;
    /**
     *
     */
    private final List<Size> sizes = List.of(
            new Size(250, 180),
            new Size(320, 240),
            new Size(560, 420),
            new Size(780, 640),
            new Size(1024, 860)
    );

    public PostBuildHelper (FileStorageServiceImpl fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    /**
     *
     * @param details
     * @return
     * @throws FileOperationException
     */
    private List<ResizeDetails> resize (List<ResizeDetails> details) throws FileOperationException {
        return details
                .stream()
                .map(this::resizeImage)
                .collect(Collectors.toList());

    }

    /**
     *
     * @param resizeDetails
     * @return
     */
    private ResizeDetails resizeImage (ResizeDetails resizeDetails) {
        if ( Objects.isNull(resizeDetails.getInputStream()) ) {
            throw new FileOperationException("Resizing failed due to missing dataStream");
        }
        BufferedImage image;
        try {
            image = ImageIO.read(resizeDetails.getInputStream());
            var newImage = Scalr.resize(image, resizeDetails.getWidth());
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
             ImageIO.write(newImage, resizeDetails.getExtension(), bas);
             resizeDetails.setInputStream(new ByteArrayInputStream(bas.toByteArray()));
            return resizeDetails;
        } catch ( Exception e) {
            log.error(e.getLocalizedMessage(), e);
            throw new FileOperationException("Resizing failed",e);
        }
    }

    /**
     *
     * @param fileDetails
     * @return
     */
    public Set<Attachment> getAttachmentSet (List<FileDetails> fileDetails) {
        return fileDetails.stream().map(this::buildAttachment).collect(Collectors.toSet());
    }

    /**
     *
     * @param fileDetail
     * @return
     */
    private Attachment buildAttachment (FileDetails fileDetail) {
        return Attachment.builder()
                .url(fileDetail.getUrl())
                .attachmentType(AttachmentType.IMAGE)
                .src(Src.builder().original(fileDetail.getUrl()).build())
                .build();
    }

    /**
     *
     * @param postCreateRequest
     * @return
     */
    public List<FileDetails> getFileDetails (PostCreateRequest postCreateRequest) {
        this.getResizedImages(postCreateRequest);
        return postCreateRequest
                .getAttachments()
                .stream()
                .map(this.fileStorageService::storeFile)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param postCreateRequest
     */
    private void getResizedImages (PostCreateRequest postCreateRequest) {
        postCreateRequest.getAttachments()
                .stream()
                .filter(this::isFileImage)
                .map(this::createResizedImage)
                .map(this::resize)
                .forEach(this.fileStorageService::storeResizedImage);
    }

    /**
     *
     * @param multipartFile
     * @return
     */
    private boolean isFileImage (MultipartFile multipartFile) {
        return Objects.requireNonNull(multipartFile.getContentType()).startsWith("image");
    }

    /**
     *
     * @param multipartFile
     * @return
     */
    @SneakyThrows({IOException.class})
    private List<ResizeDetails> createResizedImage (MultipartFile multipartFile) {
        final String extension = Objects.requireNonNull(multipartFile.getOriginalFilename()).split("\\.")[1];
        final InputStream inputStream = multipartFile.getInputStream();
        return this.sizes.stream().map(size -> getBuildResizeDetails(extension, inputStream, size)).collect(Collectors.toList());

    }

    /**
     *
     * @param extension
     * @param inputStream
     * @param size
     * @return
     */
    private ResizeDetails getBuildResizeDetails (String extension, InputStream inputStream, Size size) {
        return ResizeDetails.builder().extension(extension)
                .width(size.width())
                .height(size.height())
                .filePath(this.fileStorageService.getFilePath(UUID.randomUUID().toString().concat(".").concat(extension)).toString())
                .inputStream(inputStream)
                .build();
    }


}

package SocialFb.Helpers;

import SocialFb.Enums.AttachmentType;
import SocialFb.Exceptions.FileOperationException;
import SocialFb.Models.Attachment;
import SocialFb.Models.FileDetails;
import SocialFb.Requests.PostCreateRequest;
import SocialFb.Services.Impl.FileStorageServiceImpl;
import SocialFb.Utils.Src;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostBuildHelper {

    private final FileStorageServiceImpl fileStorageService;

    public PostBuildHelper (FileStorageServiceImpl fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    private void resize (File file, int width, int height) throws FileOperationException {
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            image = Scalr.resize(image,
                    Scalr.Method.ULTRA_QUALITY,
                    Scalr.Mode.FIT_EXACT,
                    width,
                    height);

            image.flush();
        } catch (IOException | IllegalArgumentException e) {
            log.error(e.getMessage(), e);
            throw new FileOperationException("Resizing failed");
        }
    }

    public Set<Attachment> getAttachmentSet (List<FileDetails> fileDetails) {
        return fileDetails.stream().map(fileDetail ->
                        Attachment.builder()
                                .url(fileDetail.getUrl())
                                .attachmentType(AttachmentType.IMAGE)
                                .src(Src.builder().original(fileDetail.getUrl()).build())
                                .build())
                .collect(Collectors.toSet());
    }

    public List<FileDetails> getFileDetails (PostCreateRequest postCreateRequest) {
        return postCreateRequest.getAttachments()
                .stream()
                .map(this.fileStorageService::storeFile).collect(Collectors.toList());
    }
}

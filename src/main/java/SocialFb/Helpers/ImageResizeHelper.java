package SocialFb.Helpers;

import SocialFb.Exceptions.FileOperationException;
import SocialFb.Models.Size;
import SocialFb.Validation.ImageValidation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
@Component
@Slf4j
@AllArgsConstructor
public class ImageResizeHelper {
    @SneakyThrows(IOException.class)
    public void imageResizer (MultipartFile multipartFile, Size size, Path filePath) {
        String s = Objects.requireNonNull(multipartFile.getOriginalFilename()).split("\\.")[1];
        if ( Objects.isNull(s) ) {
            throw new FileOperationException(String.format("please provide file extension for filename : %s size : %s", multipartFile.getOriginalFilename(), size));
        }
        ImageValidation.validateDimensions(size.width(), size.height());
        Thumbnails.of(multipartFile.getInputStream()).outputFormat(s).size(size.height(), size.width()).toFile(filePath.toString());
    }
}

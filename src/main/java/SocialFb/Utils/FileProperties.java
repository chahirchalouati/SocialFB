package SocialFb.Utils;

import SocialFb.Exceptions.BadRequestException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

@Configuration
@PropertySource(value = "classpath:files.properties", ignoreResourceNotFound = true)
@Data
public class FileProperties {
    private static String[] allowedExtensions = {"3g2", "3gp", "3gp2", "3gpp", "avi", "csv", "doc", "docx", "flv", "jpeg", "jpg", "m4a", "m4v", "mov", "mp3", "mp4", "mpeg", "mpg", "ods", "pdf", "png", "ppt", "txt", "wmv", "xls", "xlsx", "zip"};
    private String fileStoreLocation;

    public FileProperties (@Value(value = "${file.store.location : localFileStorage}") String fileStoreLocation) {this.fileStoreLocation = fileStoreLocation;}

    public static void validateFileFormat (String fileName) {
        if ( fileName == null && fileName.trim().toLowerCase().contains("..") )
            throw new BadRequestException(String.format("[INVALID FILE NAME ]  please remove unnecessary dots from filename : %s", fileName));
        final boolean match = Arrays.stream(allowedExtensions).anyMatch(extension -> extension.equals(fileName.split("\\.")[1]));
        if ( !match )
        throw new BadRequestException(String.format("[INVALID FILE FORMAT]  please make sure that your file format is one of these allowed formats  : %s \n error in file :%s", Arrays.toString(allowedExtensions), fileName));
    }


}

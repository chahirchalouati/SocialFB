package SocialFb.Services;

import SocialFb.Models.FileDetails;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public interface FileStorageService {


    default void createStore (String storeLocation) throws IOException {
        Files.createDirectories(Paths.get(storeLocation).toAbsolutePath().normalize());
    }

    FileDetails storeFile (MultipartFile multipartFile);

    Resource getResource (FileDetails fileDetails);
}

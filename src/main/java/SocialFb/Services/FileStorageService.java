package SocialFb.Services;

import SocialFb.Models.FileDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public interface FileStorageService {


    default void createStore (String storeLocation) throws IOException {
        Files.createDirectories(Paths.get(storeLocation).toAbsolutePath().normalize());
    }

    List<FileDetails> storeFile (MultipartFile multipartFile);



    List<FileDetails> storeResisedImages (MultipartFile multipartFile);

    List<FileDetails>  storeImage (MultipartFile multipartFile);

    List<FileDetails>  storeVideo (MultipartFile multipartFile);

    List<FileDetails>  storeOtherFile (MultipartFile multipartFile);
}

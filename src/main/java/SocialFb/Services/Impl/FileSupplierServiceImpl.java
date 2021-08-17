package SocialFb.Services.Impl;

import SocialFb.Exceptions.ResourceNotFoundException;
import SocialFb.Helpers.PathHelper;
import SocialFb.Models.FileDetails;
import SocialFb.Services.FileSupplierService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
@Service
@AllArgsConstructor
@Slf4j
public class FileSupplierServiceImpl implements FileSupplierService {
   private final  PathHelper pathHelper;
    @Override
    public Resource getResource (String filename) {
        Path path = Paths.get(pathHelper.getFilePath(filename).toUri()).normalize().toAbsolutePath();
        try {
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException(String.format("file %sdoes not exist", filename));
        }

    }

}

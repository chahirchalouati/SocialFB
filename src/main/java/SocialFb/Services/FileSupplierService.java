package SocialFb.Services;

import SocialFb.Models.FileDetails;
import org.springframework.core.io.Resource;

import java.nio.file.Path;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
public interface FileSupplierService {
    Resource getResource (String url);
}

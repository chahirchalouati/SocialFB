package SocialFb.Helpers;

import SocialFb.Providers.FileProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
@Component
@Slf4j
public class PathHelper {
    @Getter
    private final String FILE_STORE_LOCATION;

    public PathHelper (@Autowired FileProperties fileProperties) {
        this.FILE_STORE_LOCATION = fileProperties.getFileStoreLocation().trim().toLowerCase(Locale.ROOT);;
    }

    public Path getFilePath (String filename) {

        return Paths
                .get(this.getRelativePath(filename))
                .toAbsolutePath()
                .normalize();
    }

    @NotNull
    public String getRelativePath (String filename) {
        return this.FILE_STORE_LOCATION
                .concat("/")
                .concat(filename);
    }
}

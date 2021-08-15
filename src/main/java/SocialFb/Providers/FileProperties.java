package SocialFb.Providers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath*:files.properties", ignoreResourceNotFound = true)
@Data
public class FileProperties {

    private String fileStoreLocation;

    public FileProperties (@Value(value = "${file.store.location : localFileStorage}") String fileStoreLocation) {
        this.fileStoreLocation = fileStoreLocation;
    }


}

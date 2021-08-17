package SocialFb.Models;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;
import java.nio.file.Path;

@Builder
@Data
public
class ResizeDetails {
    private InputStream inputStream;
    private int width;
    private int height;
    private Path filePath;
    private String extension;
}
package SocialFb.Models;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Builder
@Data
public
class ResizeDetails {
    private InputStream inputStream;
    private int width;
    private int height;
    private String filePath;
    private String extension;
}
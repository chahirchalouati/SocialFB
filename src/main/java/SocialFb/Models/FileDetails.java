package SocialFb.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class FileDetails {

    private String url;
    private String path;
    private String name;
    private String type;
    private Size size;
    private boolean isOriginal;
    private long length;
    private SizeLabel sizeLabel;
    public enum SizeLabel {
        LARGE, LARGE_2X, LANDSCAPE, PORTRAIT, SMALL, TINY, ORIGINAL;
    }
}

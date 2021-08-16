package SocialFb.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;


@Builder
@Data
@AllArgsConstructor
@Accessors(fluent = true)
public class Size {
    private int width;
    private int height;
}
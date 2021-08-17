package SocialFb.Helpers;

import SocialFb.Models.FileDetails;
import SocialFb.Models.Src;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
@Component
@Slf4j
public class SrcHelper {
    public Src SrcBuilder (FileDetails fileDetail, Src src) {
        switch (fileDetail.getSizeLabel()) {
            case LARGE:
                src.setLarge(fileDetail.getUrl());
                break;
            case LARGE_2X:
                src.setLarge2x(fileDetail.getUrl());
                break;
            case LANDSCAPE:
                src.setLandscape(fileDetail.getUrl());
                break;
            case PORTRAIT:
                src.setPortrait(fileDetail.getUrl());
                break;
            case SMALL:
                src.setSmall(fileDetail.getUrl());
                break;
            case TINY:
                src.setTiny(fileDetail.getUrl());
                break;
            default:
                src.setOriginal(fileDetail.getUrl());
        }
        return src;
    }
}

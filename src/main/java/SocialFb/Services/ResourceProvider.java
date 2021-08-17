package SocialFb.Services;

import org.springframework.core.io.Resource;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
public interface ResourceProvider {
    Resource getResource (String url);
}

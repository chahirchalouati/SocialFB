package SocialFb;

import SocialFb.Providers.MediaProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@SpringBootApplication
@EnableCaching
@AllArgsConstructor
public class SocialFBApplication {

    private final MediaProvider mediaProvider;


    public static void main (String[] args) {
        SpringApplication.run(SocialFBApplication.class, args);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer () {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings (CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE") ;
            }
        };
    }


//    @Bean
//    public CommandLineRunner runner () {
//        return args -> {
//            this.mediaProvider.getPostWithCustomHeaders("person");
//            this.mediaProvider.getPostWithCustomHeaders("portrait");
//            this.mediaProvider.getPostWithCustomHeaders("nature");
//        };
//    }
}

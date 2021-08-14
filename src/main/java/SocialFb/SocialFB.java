package SocialFb;

import SocialFb.Providers.MediaProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@SpringBootApplication
@EnableCaching
public class SocialFB {

    private final MediaProvider mediaProvider;

    public SocialFB (MediaProvider mediaProvider) {
        this.mediaProvider = mediaProvider;
    }

    public static void main (String[] args) {
        SpringApplication.run(SocialFB.class, args);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer () {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings (CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }


    @Bean
    public CommandLineRunner runner () {
        return args -> {
            this.mediaProvider.getPostWithCustomHeaders("person");
            this.mediaProvider.getPostWithCustomHeaders("portrait");
            this.mediaProvider.getPostWithCustomHeaders("nature");
        };
    }
}

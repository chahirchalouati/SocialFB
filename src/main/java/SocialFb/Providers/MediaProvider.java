//package SocialFb.Providers;
//
//import SocialFb.Repositories.PexeLObjectRepository;
//import SocialFb.Utils.PexelObject;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//import com.zaxxer.hikari.util.FastList;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.time.Duration;
//import java.util.List;
//
//@Service
//@Slf4j(topic = "MediaProvider")
//@PropertySource("classpath:keys.properties")
//public class MediaProvider {
//
//    private final HttpClient httpClient;
//    private final PexeLObjectRepository pexeLObjectRepository;
//
//    @Value(value = "${key.pexel}")
//    String PEXEL_KEY ;
//
//    public MediaProvider (PexeLObjectRepository pexeLObjectRepository) {
//        this.pexeLObjectRepository = pexeLObjectRepository;
//        this.httpClient = HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_1_1)
//                .connectTimeout(Duration.ofSeconds(10))
//                .build();
//    }
//
//    public void getPostWithCustomHeaders (String param) throws IOException, InterruptedException {
//
//        String responseBody = this.buildMedia(param, httpClient, 0).body();
//        List<PexelObject> success = pexeLObjectRepository.saveAll(this.extractPexelObjects(responseBody));
//        log.info("LearnMongoApplication Save PEXELS success {} ", success);
//
//    }
//
//    private List<PexelObject> extractPexelObjects (String responseBody) {
//        final List<PexelObject> pexelObjects = new FastList<>(PexelObject.class);
//        new JsonParser()
//                .parse(responseBody)
//                .getAsJsonObject()
//                .getAsJsonArray("photos")
//                .spliterator()
//                .forEachRemaining(jsonElement -> {
//                    try {
//                        pexelObjects.add(getPexelObject(jsonElement));
//
//                    } catch (JsonProcessingException e) {
//                        e.printStackTrace();
//                    }
//                });
//        return pexelObjects;
//    }
//
//    private PexelObject getPexelObject (JsonElement jsonElement) throws JsonProcessingException {
//        return new ObjectMapper().readValue(jsonElement.getAsJsonObject().toString(), PexelObject.class);
//
//    }
//
//    public HttpResponse<String> buildMedia (String param, HttpClient httpClient, Integer page_Number) throws IOException, InterruptedException {
//
//        if ( page_Number != null && page_Number >= 0 ) {
//            log.info("START SENDING REQUEST {}");
//            return httpClient.send(HttpRequestBuilder(param, page_Number, PEXEL_KEY), HttpResponse.BodyHandlers.ofString());
//        }
//        throw new RuntimeException("you must provide a page number");
//    }
//
//    private HttpRequest HttpRequestBuilder (String param, int page_num, String PEXEL_KEY) {
//        return HttpRequest.newBuilder()
//                .GET()
//                .uri(URI.create("https://api.pexels.com/v1/search?query=" + param + "&per_page=80&page=" + page_num))
//                .setHeader("Authorization", PEXEL_KEY) // add request header
//                .build();
//
//    }
//}

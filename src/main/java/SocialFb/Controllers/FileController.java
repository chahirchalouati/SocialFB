package SocialFb.Controllers;

import SocialFb.Services.FileSupplierService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
@RequestMapping("files")
@RestController
@AllArgsConstructor
public class FileController {

    private final FileSupplierService fileSupplierService;

    @GetMapping("/{filename}")
    public ResponseEntity response (@PathVariable(value = "filename") String filename, HttpServletRequest request) throws IOException {
        final Resource resource = this.fileSupplierService.getResource(filename);
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        if ( Objects.isNull(contentType) ) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(contentType))
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePrivate().proxyRevalidate())
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", filename))
                .body(resource);

    }
}

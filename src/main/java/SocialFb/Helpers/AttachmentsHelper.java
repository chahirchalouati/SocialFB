package SocialFb.Helpers;

import SocialFb.Exceptions.InvalidAttachmentTypeException;
import SocialFb.Models.Attachment;
import SocialFb.Models.FileDetails;
import SocialFb.Models.Src;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
@Component
@Slf4j
@AllArgsConstructor
public class AttachmentsHelper {
    private final SrcHelper srcHelper;
    ;

    public Attachment buildAttachments (List<FileDetails> fileDetails) {

        AtomicReference<Src> src = new AtomicReference<>(new Src());

        for ( FileDetails fileDetails1 : fileDetails ) {
            src.set(this.srcHelper.SrcBuilder(fileDetails1, src.get()));
        }

        final FileDetails details = fileDetails.stream().findFirst()
                .orElseThrow(() -> new InvalidAttachmentTypeException("unable to define attachmentType for file :" + src.get().getOriginal()));
        log.info("buildAttachment Success  {}", fileDetails);

        return Attachment.builder()
                .url(src.get().getOriginal())
                .attachmentType(AttachmentTypeHelper.resolve(details.getType()))
                .src(src.get())
                .build();
    }
}

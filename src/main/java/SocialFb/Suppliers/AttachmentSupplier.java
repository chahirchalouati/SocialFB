package SocialFb.Suppliers;

import SocialFb.Enums.AttachmentType;
import SocialFb.Models.Attachment;
import SocialFb.Repositories.AttachmentsRepository;
import SocialFb.Repositories.PexeLObjectRepository;
import SocialFb.Suppliers.abstraction.AbstractSupplier;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class AttachmentSupplier extends AbstractSupplier<Attachment> {

    private final AttachmentsRepository attachmentsRepository;

    private final PexeLObjectRepository pexeLObjectRepository;

    @Override
    public Set<Attachment> supplyMany (int count) {
        Set<Attachment> attachments = new HashSet<>();
        if ( count > 0 ) {
            for ( int i = 0; i < count; i++ ) {
                attachments.add(this.supplyOne());
            }

        }
        return attachments;
    }

    @Override
    public Attachment supplyOne () {
        var objects = pexeLObjectRepository.findAll();
        var x = objects.get(RandomUtils.nextInt(0, objects.size()));

        return Attachment.builder()
                .attachmentType(AttachmentType.IMAGE)
                .url(x.getUrl())
                .src(x.getSrc())
                .build();
    }


}

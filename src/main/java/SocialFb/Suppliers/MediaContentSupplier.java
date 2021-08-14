package SocialFb.Suppliers;


import SocialFb.Enums.ContentMediaType;
import SocialFb.Models.MediaContent;
import SocialFb.Repositories.PexeLObjectRepository;
import SocialFb.Suppliers.abstraction.AbstractSupplier;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class MediaContentSupplier extends AbstractSupplier<MediaContent> {
    private final PexeLObjectRepository pexeLObjectRepository;


    @Override
    public Set<MediaContent> supplyMany (int count) {
        Set<MediaContent> mediaContents = new HashSet<>();
        for ( int i = 0; i < count; i++ ) {
            mediaContents.add(this.supplyOne());
        }
        return mediaContents;
    }

    @Override
    public MediaContent supplyOne () {
       var count =  pexeLObjectRepository.count();
       var object = pexeLObjectRepository.getById(RandomUtils.nextLong(0L, count));

        return MediaContent.builder()
                .contentMediaType(ContentMediaType.IMAGE)
                .url(object.getUrl())
                .src(object.getSrc())
                .build();
    }


}

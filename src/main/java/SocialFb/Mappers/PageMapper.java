package SocialFb.Mappers;

import SocialFb.DTOs.PageDTO;
import org.springframework.data.domain.Page;

public abstract class PageMapper<T,Z> {

    protected abstract PageDTO<Z> pageToPageDTO (Page<T> page);

}

package SocialFb.Services;

import SocialFb.DTOs.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CrudBaseOperations<A, B, C,D> {
    A create (B b);

    Optional<Long> update (C c);

    Optional<Long> delete (Long id);

    List<A> findAll ();

    PageDTO<A> findAll (Pageable pageable);

    Optional<A> findOne (Long id);


    default  PageDTO<A> getPageDTO (Page<D> all, List<A> content) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setContent(content);
        pageDTO.setHasContent(all.hasContent());
        pageDTO.setHasNext(all.hasNext());
        pageDTO.setHasPrevious(all.hasPrevious());
        pageDTO.setNumber(all.getNumber());
        pageDTO.setNumberOfElements(all.getNumberOfElements());
        pageDTO.setLast(all.isLast());
        pageDTO.setSize(all.getSize());
        pageDTO.setSort(all.getSort());
        pageDTO.setFirst(all.isFirst());
        pageDTO.setTotalElements(all.getTotalElements());
        pageDTO.setTotalPages(all.getTotalPages());
        return pageDTO;
    }
}

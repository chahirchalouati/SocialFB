package SocialFb.DTOs;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Data
@Setter(AccessLevel.NONE)
public class PageDTO <T>{
    private List<T> content;
    private int number;
    private int size;
    private int numberOfElements;
    private boolean hasContent;
    private Sort sort;
    private boolean isFirst;
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;
    private int totalPages;
    private long totalElements;

    public PageDTO<T> setContent (List<T> content) {
        this.content = content;
        return this;
    }

    public PageDTO<T> setNumber (int number) {
        this.number = number;
        return this;
    }

    public PageDTO<T> setSize (int size) {
        this.size = size;
        return this;
    }

    public PageDTO<T> setNumberOfElements (int numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    public PageDTO<T> setHasContent (boolean hasContent) {
        this.hasContent = hasContent;
        return this;
    }

    public PageDTO<T> setSort (Sort sort) {
        this.sort = sort;
        return this;
    }

    public PageDTO<T> setFirst (boolean first) {
        isFirst = first;
        return this;
    }

    public PageDTO<T> setLast (boolean last) {
        isLast = last;
        return this;
    }

    public PageDTO<T> setHasNext (boolean hasNext) {
        this.hasNext = hasNext;
        return this;
    }

    public PageDTO<T> setHasPrevious (boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
        return this;
    }

    public PageDTO<T> setTotalPages (int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public PageDTO<T> setTotalElements (long totalElements) {
        this.totalElements = totalElements;
        return this;
    }
}

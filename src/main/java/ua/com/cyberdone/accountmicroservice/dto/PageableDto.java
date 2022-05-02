package ua.com.cyberdone.accountmicroservice.dto;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
public class PageableDto<T> {
    public Integer page;
    public Integer elementsOnThePage;
    public Integer totallyPages;
    public Long totallyElements;
    public List<@Valid T> content;
}

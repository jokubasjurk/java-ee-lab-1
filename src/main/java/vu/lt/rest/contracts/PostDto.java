package vu.lt.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Author;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class PostDto {
    private String Title;

    private String Text;

    private LocalDateTime CreatedOn;

    private List<Author> authors;
}

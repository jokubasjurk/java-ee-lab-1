package vu.lt.rest.contracts;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostDto {
    private String Title;

    private String Text;

    private LocalDateTime CreatedOn;
}

package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({@NamedQuery(
        name = "Comment.findAll",
        query = "select c from Comment as c"
)})
@Table(
        name = "COMMENT"
)
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Column(
            name = "NAME"
    )
    private String name;
    @Column(
            name = "TEXT"
    )
    private String text;
    @ManyToOne
    @JoinColumn(
            name = "POST_ID"
    )
    private Post post;
    @Version
    @Column(
            name = "OPT_LOCK_VERSION"
    )
    private Integer version;

    public Comment() {
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Comment comment = (Comment) o;
            return Objects.equals(this.name, comment.name);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.name);
    }
}

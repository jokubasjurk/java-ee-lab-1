package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({@NamedQuery(
        name = "Post.findAll",
        query = "select p from Post as p"
)})
@Table(
        name = "POST"
)
@Getter @Setter
public class Post implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Size(
            max = 50
    )
    @Column(
            name = "TITLE"
    )
    private String title;
    @Size(
            max = 1000
    )
    @Column(
            name = "TEXT"
    )
    private String text;
    @Column(
            name = "CREATED_ON"
    )
    private LocalDateTime createdOn;
    @ManyToMany
    @JoinColumn(
            name = "AUTHOR_ID"
    )
    private List<Author> authors = new ArrayList();
    @OneToMany(
            mappedBy = "post"
    )
    private List<Comment> comments;
    @Version
    @Column(
            name = "OPT_LOCK_VERSION"
    )
    private Integer version;

    public Post() {
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Post post = (Post) o;
            return Objects.equals(id, post.id) && Objects.equals(this.title, post.title);
        } else {
            return false;
        }
    }

    public String toString() {
        return this.id.toString();
    }

    public int hashCode() {
        return Objects.hash(id, title);
    }
}

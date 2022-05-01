package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({@NamedQuery(
        name = "Author.findAll",
        query = "select c from Author as c"
)})
@Table(
        name = "AUTHOR"
)
@Getter @Setter
public class Author implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Size(
            max = 50
    )
    @Column(
            name = "NAME"
    )
    private String name;
    @ManyToMany(
            mappedBy = "authors"
    )
    private List<Post> posts = new ArrayList();
    @OneToOne(
            mappedBy = "author"
    )
    private ContactInfo contactInfo;
    @Version
    @Column(
            name = "OPT_LOCK_VERSION"
    )
    private Integer version;

    public Author() {
    }

    public void addPost(Post post) {
        if (post != null && !this.posts.contains(post)) {
            this.posts.add(post);
        }

    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Author Author = (Author) o;
            return Objects.equals(this.id, Author.id) && Objects.equals(this.name, Author.name);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    public String toString() {
        return this.id.toString();
    }
}

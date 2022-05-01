package vu.lt.persistence;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import vu.lt.entities.Post;

@ApplicationScoped
public class PostsDAO {
    @Inject
    private EntityManager em;

    public PostsDAO() {
    }

    public void persist(Post post) {
        this.em.persist(post);
    }

    public List<Post> loadAll() {
        return this.em.createNamedQuery("Post.findAll", Post.class).getResultList();
    }

    public Post findOne(Integer id) {
        return (Post)this.em.find(Post.class, id);
    }

    public Post update(Post post) {
        return (Post)this.em.merge(post);
    }
}

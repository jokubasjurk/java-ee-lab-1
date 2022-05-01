package vu.lt.persistence;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import vu.lt.entities.Comment;

@ApplicationScoped
public class CommentsDAO {
    @Inject
    private EntityManager em;

    public CommentsDAO() {
    }

    public List<Comment> loadAll() {
        return this.em.createNamedQuery("Comment.findAll", Comment.class).getResultList();
    }

    public void persist(Comment comment) {
        this.em.persist(comment);
    }

    public Comment findOne(Integer id) {
        return (Comment)this.em.find(Comment.class, id);
    }
}

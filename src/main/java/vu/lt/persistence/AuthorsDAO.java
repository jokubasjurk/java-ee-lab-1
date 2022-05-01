package vu.lt.persistence;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import vu.lt.entities.Author;

@ApplicationScoped
public class AuthorsDAO {
    @Inject
    private EntityManager em;

    public AuthorsDAO() {
    }

    public List<Author> loadAll() {
        return this.em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    public void persist(Author author) {
        this.em.persist(author);
    }

    public Author update(Author author) {
        return (Author)this.em.merge(author);
    }

    public Author findOne(Integer id) {
        return (Author)this.em.find(Author.class, id);
    }
}

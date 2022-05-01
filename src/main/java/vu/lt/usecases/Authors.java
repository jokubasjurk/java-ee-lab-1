package vu.lt.usecases;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Author;
import vu.lt.persistence.AuthorsDAO;

@Model
public class Authors {

    @Inject
    private AuthorsDAO authorsDAO;

    @Getter @Setter
    private Author authorToCreate = new Author();

    @Getter
    private List<Author> allAuthors;

    @PostConstruct
    public void init() {
        this.loadAllAuthors();
    }

    @Transactional
    public String createAuthor() {
        authorsDAO.persist(this.authorToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllAuthors() {
        this.allAuthors = this.authorsDAO.loadAll();
    }
}

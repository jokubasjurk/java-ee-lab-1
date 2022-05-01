package vu.lt.usecases;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Author;
import vu.lt.entities.Post;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.AuthorsDAO;
import vu.lt.persistence.PostsDAO;

@Model
public class PostsForAuthor implements Serializable {
    @Inject
    private AuthorsDAO authorsDAO;
    @Inject
    private PostsDAO postsDAO;
    @Getter @Setter
    private Author author;
    @Getter @Setter
    private Post postToCreate = new Post();

    public PostsForAuthor() {
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer authorId = Integer.parseInt((String)requestParameters.get("authorId"));
        author = authorsDAO.findOne(authorId);
    }

    @Transactional
    @LoggedInvocation
    public String createPost() {
        postToCreate.getAuthors().add(author);
        postsDAO.persist(postToCreate);
        return "authors?faces-redirect=true&authorId=" + author.getId();
    }
}

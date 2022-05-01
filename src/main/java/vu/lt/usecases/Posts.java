package vu.lt.usecases;

import java.util.List;
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
import vu.lt.persistence.AuthorsDAO;
import vu.lt.persistence.PostsDAO;

@Model
public class Posts {
    @Inject
    private PostsDAO postsDAO;
    @Inject
    private AuthorsDAO authorsDAO;
    @Getter @Setter
    private Post postToCreate = new Post();
    @Getter @Setter
    private String postIdToSet;
    @Getter
    private List<Post> allPosts;
    @Getter @Setter
    private Author author;

    public Posts() {
    }

    @PostConstruct
    public void init() {
        loadAllPosts();
        setCurrentAuthor();
    }

    @Transactional
    public String createPost() {
        postsDAO.persist(postToCreate);
        return "index?faces-redirect=true";
    }

    @Transactional
    public String addPostToAuthor() {
        Post post = postsDAO.findOne(Integer.parseInt(postIdToSet));
        post.getAuthors().add(author);
        postsDAO.update(post);
        return "authors.xhtml?authorId=" + author.getId() + "&faces-redirect=true";
    }

    private void setCurrentAuthor() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer authorId = Integer.parseInt(requestParameters.get("authorId"));
        this.author = authorsDAO.findOne(authorId);
    }

    private void loadAllPosts() {
        allPosts = postsDAO.loadAll();
    }
}

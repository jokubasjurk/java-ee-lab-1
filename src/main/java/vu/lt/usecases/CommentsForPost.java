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
import vu.lt.entities.Comment;
import vu.lt.entities.Post;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.CommentsDAO;
import vu.lt.persistence.PostsDAO;

@Model
public class CommentsForPost implements Serializable {
    @Inject
    private PostsDAO postsDAO;
    @Inject
    private CommentsDAO commentsDAO;
    @Getter @Setter
    private Post post;
    @Getter @Setter
    private Comment commentsToCreate = new Comment();

    public CommentsForPost() {
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer postId = Integer.parseInt((String)requestParameters.get("postId"));
        post = postsDAO.findOne(postId);
    }

    @Transactional
    @LoggedInvocation
    public String createComment() {
        commentsToCreate.setPost(post);
        commentsDAO.persist(commentsToCreate);
        return "postDetails?faces-redirect=true&postId=" + post.getId();
    }
}

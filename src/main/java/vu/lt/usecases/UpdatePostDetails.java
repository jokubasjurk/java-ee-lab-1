package vu.lt.usecases;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Post;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.PostsDAO;

@ViewScoped
@Named
@Getter @Setter
public class UpdatePostDetails implements Serializable {

    private Post post;

    @Inject
    private PostsDAO postsDAO;

    public UpdatePostDetails() {
    }

    @PostConstruct
    private void init() {
        System.out.println("updatePostDetails INIT CALLED");
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer postId = Integer.parseInt(requestParameters.get("postId"));
        post = postsDAO.findOne(postId);
    }

    @Transactional
    @LoggedInvocation
    public String updatePost() {
        try {
            postsDAO.update(post);
            return "index.xhtml?faces-redirect=true";
        } catch (OptimisticLockException var2) {
            return "/postDetails.xhtml?faces-redirect=true&postId=" + post.getId() + "&error=optimistic-lock-exception";
        }
    }
}

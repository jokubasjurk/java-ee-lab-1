package vu.lt.usecases;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.PostMapper;
import vu.lt.mybatis.model.Post;

@Model
public class PostMyBatis {
    @Inject
    private PostMapper postMapper;

    @Getter
    private List<Post> allPosts;
    @Getter @Setter
    private Post postToCreate = new Post();

    @PostConstruct
    public void init() {
        loadAllPosts();
    }

    private void loadAllPosts() {
        allPosts = postMapper.selectAll();
    }

    @Transactional
    public String createPost() {
        postMapper.insert(postToCreate);
        return "/myBatis/posts?faces-redirect=true";
    }
}

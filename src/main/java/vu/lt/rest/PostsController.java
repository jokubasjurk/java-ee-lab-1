package vu.lt.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Post;
import vu.lt.persistence.PostsDAO;
import vu.lt.rest.contracts.PostDto;

@ApplicationScoped
@Path("/posts")
public class PostsController {

    @Inject
    @Setter @Getter
    private PostsDAO postsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        Post post = postsDAO.findOne(id);
        if (post == null) {
            return Response.status(Status.NOT_FOUND).build();
        } else {
            PostDto postDto = new PostDto();
            postDto.setText(post.getText());
            postDto.setTitle(post.getTitle());
            postDto.setCreatedOn(post.getCreatedOn());
            return Response.ok(postDto).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Integer postId, PostDto postData) {
        try {
            Post currentPost = postsDAO.findOne(postId);
            if (currentPost == null) {
                return Response.status(Status.NOT_FOUND).build();
            } else {
                currentPost.setTitle(postData.getTitle());
                currentPost.setText(postData.getText());
                postsDAO.update(currentPost);
                return Response.ok().build();
            }
        } catch (OptimisticLockException var4) {
            return Response.status(Status.CONFLICT).build();
        }
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(PostDto postData) {
        Post newPost = new Post();
        newPost.setText(postData.getText());
        newPost.setText(postData.getTitle());
        newPost.setAuthors(postData.getAuthors());
        postsDAO.persist(newPost);
        return Response.ok().build();
    }
}

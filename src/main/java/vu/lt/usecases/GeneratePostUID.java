package vu.lt.usecases;

import vu.lt.interceptors.LoggedInvocation;
import vu.lt.services.generator.PostSerialNumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GeneratePostUID implements Serializable {
    @Inject
    PostSerialNumberGenerator serialNumberGenerator;

    private CompletableFuture<String> postUIDGenerationTask = null;

    @LoggedInvocation
    public String generatePostUID() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        postUIDGenerationTask = CompletableFuture.supplyAsync(() -> serialNumberGenerator.generatePostSerialNumber());

        return  "/postDetails.xhtml?faces-redirect=true&postId=" + requestParameters.get("postId");
    }

    public boolean isPostUIDGenerationRunning() {
        return postUIDGenerationTask != null && !postUIDGenerationTask.isDone();
    }

    public String getPostUIDGenerationStatus() throws ExecutionException, InterruptedException {
        if (postUIDGenerationTask == null) {
            return null;
        } else if (isPostUIDGenerationRunning()) {
            return "Post UID generation in progress";
        }
        return "Suggested Post UID number: " + postUIDGenerationTask.get();
    }
}

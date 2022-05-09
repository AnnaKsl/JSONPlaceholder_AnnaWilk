package tests;

import models.PostDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.PostSteps;

import java.util.Arrays;

public class PostIT extends BaseIT {
    int ID = 1;
    int USER_ID = 25;
    String TITLE = "testTitle";
    String BODY = "testBody";

    @Test
    @DisplayName("Verify one post")
    public void getPostTest() {
        var response = postEndpoint.getPost(ID);
        verifyResponseStatusCodeIsEqualToOK(response);
        var post = gson.fromJson(response.jsonPath().prettify(), PostDto.class);
        postSteps.verifyPost(post, ID);
    }

    @Test
    @DisplayName("Verify the number of posts")
    public void getAllPosts() {
        var response = postEndpoint.getAllPosts();
        var allPosts = Arrays.asList(gson.fromJson(response.jsonPath().prettify(), PostDto[].class));
        Assertions.assertEquals(allPosts.size(), 100);
        var idOfThePost = PostSteps.getSpecificPostId(allPosts, "qui est esse");
        System.out.print("Post with the title 'qui est esse' is the one with id " + idOfThePost);
    }

    @Test
    @DisplayName("Verify adding post")
    public void addPostTest() {
        var response = postEndpoint.addPost(USER_ID, TITLE, BODY);
        verifyResponseStatusCodeIsEqualToCreated(response);
        var post = gson.fromJson(response.jsonPath().prettify(), PostDto.class);
        postSteps.verifyPost(post, 101);
    }

    @Test
    @DisplayName("Verify deleting post")
    public void deletePostTest() {
        var response = postEndpoint.deletePost(ID);
        verifyResponseStatusCodeIsEqualToOK(response);
    }

    @Test
    @DisplayName("Verify modifying post")
    public void changePostTest() {
        var userId = 3;
        var newTitle = "Modified title";
        var body = "new Body";
        var response = postEndpoint.modifyPost(ID, userId, newTitle, body);
        verifyResponseStatusCodeIsEqualToOK(response);
        var modifiedComment = gson.fromJson(response.jsonPath().prettify(), PostDto.class);
        postSteps.verifyPost(modifiedComment, userId, newTitle, body);
    }
}

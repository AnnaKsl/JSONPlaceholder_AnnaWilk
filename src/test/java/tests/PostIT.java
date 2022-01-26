package tests;

import models.Post;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

//nazwe zmiennych żeby miało odpowiednie cases

public class PostIT extends BaseIT {
    int ID = 1;
    int USER_ID = 25;
    String TITLE = "testTitle";
    String BODY = "testBody";

    @Test
    @DisplayName("Verify one post")
    public void getPostTest() {
        var response = postEndpoint.getPost(ID);
        var post = gson.fromJson(response.jsonPath().prettify(), Post.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        //osobna klasa na to
        postSteps.verifyPost(post, ID);
    }

    @Test
    @DisplayName("Verify the number of posts")
    public void getAllPosts() {
        var response = postEndpoint.getAllPosts();
        var allPosts = Arrays.asList(gson.fromJson(response.jsonPath().prettify(), Post[].class));
        Assertions.assertEquals(allPosts.size(), 100);
        int idOfThePost = getSpecificPostId(allPosts,"qui est esse");
        System.out.print("Post with the title 'qui est esse' is the one with id " + idOfThePost);
    }
    public static int getSpecificPostId(List<Post> allPosts, String title){
        Stream<Post> streamOfPosts = allPosts.stream();
        int id = streamOfPosts.filter(post -> post.getTitle().equals(title)).map(Post::getId).findFirst().orElse(null);
        return id;

    }

    @Test
    @DisplayName("Verify adding post")
    public void addPostTest() {
        var response = postEndpoint.addPost(USER_ID, TITLE, BODY);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        var post = gson.fromJson(response.jsonPath().prettify(), Post.class);
        postSteps.verifyPost(post, 101);
    }

    @Test
    @DisplayName("Verify deleting post")
    public void deletePostTest() {
        var response = postEndpoint.deletePost(ID);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Verify modifying post")
    public void changePostTest() {
        var userId = 3;
        var newTitle = "Modified title";
        var body = "new Body";
        var response = postEndpoint.modifyPost(ID, userId, newTitle, body);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        var modifiedComment = gson.fromJson(response.jsonPath().prettify(), Post.class);
        postSteps.verifyPost(modifiedComment, userId, newTitle, body);

    }

}

package tests;

import endpoints.CommentEndpoint;
import utils.JsonFormatter;
import models.Comment;
import steps.CommentSteps;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import java.util.Arrays;

public class CommentIT extends BaseIT {
    int POST_ID = 1;
    int ID = 2;
    String NAME = "testName";
    String EMAIL = "testemail@test.com";
    String BODY = "ora et labora";
    CommentEndpoint commentEndpoint = new CommentEndpoint();
    CommentSteps commentSteps = new CommentSteps();
    Gson gson = new Gson();

    @Test
    @DisplayName("Verify all comments")
    public void verifyAllComments() {
        var response = commentEndpoint.getAllComments();
        var allComments = Arrays.asList(JsonFormatter.convertFromJson(response, Comment[].class));
        verifyResponseStatusCode(response);
        Assert.assertEquals(allComments.size(), 500);

        //changes to create problems
    }

    @Test
    @DisplayName("Verify single comment")
    public void verifyGetComment() {
        var response = commentEndpoint.getComment(POST_ID);
        var comment = JsonFormatter.convertFromJson(response, Comment.class);
        verifyResponseStatusCode(response);
        commentSteps.verifyComment(comment, POST_ID);
    }

    @Test
    @DisplayName("Verify deleting comment")
    public void verifyDeletingCommentWasSuccessfull() {
        var response = commentEndpoint.deleteComment(POST_ID);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Verify adding comment")
    public void verifyAddingComment() {
        var response = commentEndpoint.addComment(ID, NAME, EMAIL, BODY);
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
        var actualComment = gson.fromJson(response.jsonPath().prettify(), Comment.class);
        commentSteps.verifyComment(actualComment, 501);
    }

    @Test
    @DisplayName("Verify updating comment")
    public void verifyUpdatingPost() {
        int postID = 5;
        String newName = "NewName";
        String newEmail = "NewEmail@test.com";
        String newBody = "updatedBody";
        commentEndpoint = new CommentEndpoint();
        var response = commentEndpoint.updateComment(postID, ID, newName, newEmail, newBody);
        var updatedComment = gson.fromJson(response.jsonPath().prettify(), Comment.class);
        commentSteps.verifyComment(updatedComment, postID, newName, newEmail, newBody);
    }


}

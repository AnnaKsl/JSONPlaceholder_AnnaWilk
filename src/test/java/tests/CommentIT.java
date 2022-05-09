package tests;

import com.google.gson.Gson;
import endpoints.CommentEndpoint;
import models.CommentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.CommentSteps;
import utils.CommentService;
import utils.JsonFormatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CommentIT extends BaseIT {
    int POST_ID = 1;
    int ID = 2;
    private String NAME = "testName";
    private String EMAIL = "testemail@test.com";
    private String BODY = "ora et labora";
    Path path = Paths.get("src/main/java/Comments.txt");
    private CommentEndpoint commentEndpoint = new CommentEndpoint();
    private CommentSteps commentSteps = new CommentSteps();
    private CommentService commentService = new CommentService();
    Gson gson = new Gson();

    @BeforeEach
    public void initEach() {
        commentEndpoint = new CommentEndpoint();
        commentService = new CommentService();
        try {
            Files.deleteIfExists(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    @DisplayName("Verify all comments")
    public void verifyAllComments() {
        var response = commentEndpoint.getComments();
        var allComments = Arrays.asList(JsonFormatter.convertFromJson(response, CommentDto[].class));
        verifyResponseStatusCodeIsEqualToOK(response);
        CommentSteps.filterCommentsWithEvenIds(allComments);
        Assertions.assertEquals(allComments.size(), 500);
    }

    @Test
    @DisplayName("Verify single comment")
    public void verifyGetComment() {
        var response = commentEndpoint.getComment(POST_ID);
        var comment = JsonFormatter.convertFromJson(response, CommentDto.class);
        verifyResponseStatusCodeIsEqualToOK(response);
        commentSteps.verifyComment(comment, POST_ID);
    }

    @Test
    @DisplayName("Verify deleting comment")
    public void verifyDeletingCommentWasSuccessfull() {
        var response = commentEndpoint.deleteComment(POST_ID);
        verifyResponseStatusCodeIsEqualToOK(response);
    }

    @Test
    @DisplayName("Verify adding comment")
    public void verifyAddingComment() {
        var response = commentEndpoint.addComment(ID, NAME, EMAIL, BODY);
        verifyResponseStatusCodeIsEqualToCreated(response);
        var actualComment = gson.fromJson(response.jsonPath().prettify(), CommentDto.class);
        commentSteps.verifyComment(actualComment, 501);
    }

    @Test
    @DisplayName("Verify updating comment")
    public void verifyUpdatingPost() {
        int postID = 5;
        var newName = "NewName";
        var newEmail = "NewEmail@test.com";
        var newBody = "updatedBody";
        var response = commentEndpoint.updateComment(postID, ID, newName, newEmail, newBody);
        var updatedComment = gson.fromJson(response.jsonPath().prettify(), CommentDto.class);
        commentSteps.verifyComment(updatedComment, postID, newName, newEmail, newBody);
    }

    @Test
    @DisplayName("Collect 100 comments")
    public void collectHungredComments() {
        var listOfHungredCommentDto = commentEndpoint.getComments(100);
        var listOfComments = CommentService.convertListOfCommentDtoToListOfComments(listOfHungredCommentDto);
        new CommentService().putCommentsToTextFile(listOfComments);
        Assertions.assertEquals(listOfComments.size(), 100);
    }

    @Test
    @DisplayName("Send comments from text file")
    public void postThreeComments() {
        Path path = Paths.get("src/main/java/CommentsToBeSend.json");
        var comments = commentService.extractCommentsFromFile(path);
        var commentDTO = CommentService.convertListOfCommentsToCommentDTO(List.of(comments));
        var response = commentEndpoint.addComments(commentDTO);
        verifyResponseStatusCodeIsEqualToCreated(response);
    }
}



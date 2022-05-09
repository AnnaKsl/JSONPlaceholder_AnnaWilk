package endpoints;

import builders.CommentBuilder;
import io.restassured.response.Response;
import models.CommentDto;
import utils.JsonFormatter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class CommentEndpoint extends BaseEndpoint {
    final static String COMMENT_URI = "comments/";

    public Response getComment(int id) {
        return given().get(COMMENT_URI + id).then().log().all().extract().response();
    }

    public Response getComments() {
        return given().when().get(COMMENT_URI).then().log().all().extract().response();
    }

    public List<CommentDto> getComments(int size) {
        var response = this.getComments();
        var allComments = Arrays.asList(JsonFormatter.convertFromJson(response,CommentDto[].class));
        return allComments.stream().limit(size).collect(Collectors.toList());
    }

    public Response addComment(int postID, String name, String testEmail, String body) {
        var commentBuilder = new CommentBuilder();
        commentBuilder.setEmail(testEmail);
        commentBuilder.setBody(body);
        var comment = commentBuilder.build();
        return given().body(comment).post(COMMENT_URI).
                then().log().all().extract().response();
    }

    public Response addComments(List<CommentDto> commentDtos){
        return given().body(commentDtos).post(COMMENT_URI).
                then().log().all().extract().response();
    }

    public Response deleteComment(int id) {
        return given().delete(COMMENT_URI + id).
                then().log().all().extract().response();
    }

    public Response updateComment(int postId, int id, String name, String email, String body) {
        var updatedComment = new CommentDto(id,postId, name, email, body);
        var a = given().body(updatedComment).put(COMMENT_URI + id)
                .then().log().all().extract().response();
        return a;
    }
}

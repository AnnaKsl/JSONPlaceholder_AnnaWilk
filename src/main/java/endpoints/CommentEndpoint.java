package endpoints;

import models.Comment;
import io.restassured.response.Response;


public class CommentEndpoint extends BaseEndpoint {
    final static String COMMENT_URI = "comments/";

    public Response getComment(int id) {
        return given().get(COMMENT_URI + id).then().log().all().extract().response();
    }

    public Response getAllComments() {
        return given().when().get(COMMENT_URI).then().log().all().extract().response();
    }

    public Response addComment(int postID, String name, String testEmail, String body) {
        var comment = new Comment(101,postID, name, testEmail, body);
        return given().body(comment).post(COMMENT_URI).then().log().all().extract().response();
    }

    public Response deleteComment(int id) {
        return given().delete(COMMENT_URI + id).then().log().all().extract().response();
    }

    public Response updateComment(int postId, int id, String name, String email, String body) {
        var updatedComment = new Comment(id,postId, name, email, body);
        var a = given().body(updatedComment).put(COMMENT_URI + id)
                .then().log().all()
                .extract().response();
        return a;
    }
}

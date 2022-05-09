package endpoints;

import models.PostDto;
import io.restassured.response.Response;

public class PostEndpoint extends BaseEndpoint {
    final static String POST_URI = "posts/";

    public Response getPost(int id) {
        return given()
                .get(POST_URI + id)
                .then().log().all().extract().response();
    }

    public Response getAllPosts() {
        return given()
                .get(POST_URI).then().log().all().extract().response();

    }

    public Response deletePost(int id) {
        return given().delete(POST_URI + id).then().log().all().extract().response();
    }

    public Response addPost(int userId, String title, String body) {
        var newPost = new PostDto(userId, title, body);
        return given().body(newPost).post(POST_URI).then().log().all().extract().response();
    }

    public Response modifyPost(int id, int userId, String newTitle, String body) {
        var post = new PostDto(id,userId,newTitle,body);
        return given().body(post).put(POST_URI + id).then().log().all().extract().response();
    }


}

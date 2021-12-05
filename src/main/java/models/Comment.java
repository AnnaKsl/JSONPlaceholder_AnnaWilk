package models;
import lombok.Data;

@Data
public class Comment {
    int id;
    int postId;
    String name;
    String email;
    String body;

    public Comment(int id,int postID, String name, String testEmail, String body) {
        this.id = id;
        this.postId = postID;
        this.name = name;
        this.email = testEmail;
        this.body = body;
    }

}

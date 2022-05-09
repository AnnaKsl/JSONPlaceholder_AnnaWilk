package models;


import lombok.Data;

@Data
public class Comment {
    int id;
    int postId;
    String name;
    String email;
    String body;

    public String toString() {
        return "id = " + this.id + ", " + "postId = "
                + this.postId + ", " + "name = " + this.name +", " + "email = " + this.email + ", " + "body = " + this.body;
    }
}



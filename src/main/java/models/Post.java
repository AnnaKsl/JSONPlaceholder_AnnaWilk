package models;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
    int id;
    int userID;
    String title;
    String body;

    public Post(int userID, String title, String body){
        this.userID = userID;
        this.title = title;
        this.body = body;
    }

    public Post(int id,int userID, String title, String body){
        this.id = id;
        this.userID = userID;
        this.title = title;
        this.body = body;
    }

}

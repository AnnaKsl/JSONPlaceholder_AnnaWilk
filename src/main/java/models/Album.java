package models;
import lombok.Data;

@Data
public class Album {
    int userId;
    int id;
    String title;

    public Album(int userid, String title){
        this.userId = userid;
        this.title = title;
    }

    public Album(){

    }
}

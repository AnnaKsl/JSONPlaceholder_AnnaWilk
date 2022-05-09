package models;
import lombok.Data;

@Data
public class AlbumDto {
    int userId;
    int id;
    String title;

    public AlbumDto(int userid, String title){
        this.userId = userid;
        this.title = title;
    }

    public AlbumDto(){

    }
}

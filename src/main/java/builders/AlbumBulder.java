package builders;
import models.AlbumDto;

public class AlbumBulder{

    int userId;
    String title;

    public void setUserId(int userId) {
        this.userId=userId;
    }


    public void setTitle(String title) {
        this.title=title;
    }


    public AlbumDto getResult(){
        return new AlbumDto(userId,title);
    }
}

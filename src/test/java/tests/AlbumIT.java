package tests;

import com.google.gson.Gson;
import endpoints.AlbumEndpoint;
import models.AlbumDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.AlbumSteps;
import utils.JsonFormatter;
import java.util.Arrays;

public class AlbumIT extends BaseIT {

    AlbumEndpoint albumEndpoint = new AlbumEndpoint();
    AlbumSteps albumSteps = new AlbumSteps();
    Gson gson = new Gson();


    @Test
    @DisplayName("Verify all albums")
    public void verifyAllAlbums() {
        var response = albumEndpoint.getAllAlbums();
        verifyResponseStatusCodeIsEqualToOK(response);
        var allAlbums = Arrays.asList(gson.fromJson(response.jsonPath().prettify(), AlbumDto[].class));
        Assertions.assertEquals(allAlbums.size(), 100);
        allAlbums.stream().map(AlbumDto::getTitle)
                .filter(title -> title.contains("bus"))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Verify one album")
    public void verifyOneAlbum() {
        var id = 1;
        var userID = 1;
        var title = "quidem molestiae enim";
        var response = albumEndpoint.getAlbum(id);
        var album = JsonFormatter.convertFromJson(response, AlbumDto.class);
        verifyResponseStatusCodeIsEqualToOK(response);
        albumSteps.verifyAlbum(album, id, userID, title);
    }
}

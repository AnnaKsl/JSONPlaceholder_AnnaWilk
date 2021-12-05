package tests;

import endpoints.AlbumEndpoint;
import steps.AlbumSteps;
import utils.JsonFormatter;
import com.google.gson.Gson;
import models.Album;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AlbumIT extends BaseIT {

    AlbumEndpoint albumEndpoint = new AlbumEndpoint();
    AlbumSteps albumSteps = new AlbumSteps();
    Gson gson = new Gson();


    @Test
    @DisplayName("Verify all albums")
    public void verifyAllAlbums() {
        var response = albumEndpoint.getAllAlbums();
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        List<Album> allAlbums = Arrays.asList(gson.fromJson(response.jsonPath().prettify(), Album[].class));
        Assertions.assertEquals(allAlbums.size(), 100);
    }

    @Test
    @DisplayName("Verify one album")
    public void verifyOneAlbum() {
        int id = 1;
        int userID = 1;
        String title = "quidem molestiae enim";
        var response = albumEndpoint.getAlbum(id);
        Album album = JsonFormatter.convertFromJson(response, Album.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        albumSteps.verifyAlbum(album, id, userID, title);
    }

}

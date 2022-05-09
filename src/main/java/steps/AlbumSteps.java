package steps;

import models.AlbumDto;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class AlbumSteps {


    public void verifyAlbum(AlbumDto album, int id, int userid, String title) {
        var softly = new SoftAssertions();
        softly.assertThat(album.getId()).isEqualTo(id);
        softly.assertThat(album.getUserId()).isEqualTo(userid);
        softly.assertThat(album.getTitle()).isEqualTo(title);
        softly.assertAll();
    }

    public void verifyAlbum(AlbumDto album, int id) {
        Assertions.assertThat(album.getId()).isEqualTo(id);
    }
}

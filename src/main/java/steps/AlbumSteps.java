package steps;

import models.Album;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class AlbumSteps {
    SoftAssertions softly = new SoftAssertions();

    public void verifyAlbum(Album album, int id, int userid, String title) {
        softly.assertThat(album.getId()).isEqualTo(id);
        softly.assertThat(album.getUserId()).isEqualTo(userid);
        softly.assertThat(album.getTitle()).isEqualTo(title);
        softly.assertAll();
    }

    public void verifyAlbum(Album album, int id) {
        Assertions.assertThat(album.getId()).isEqualTo(id);
    }
}

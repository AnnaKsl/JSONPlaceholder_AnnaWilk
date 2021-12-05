package steps;
import models.Post;
import org.assertj.core.api.SoftAssertions;


public class PostSteps {
    SoftAssertions softly = new SoftAssertions();
    public void verifyPost(Post post, int userId, String title, String body) {

        softly.assertThat(post.getUserID()).isEqualTo(userId);
        softly.assertThat(post.getBody()).isEqualTo(body);
        softly.assertThat(post.getTitle()).isEqualTo(title);
        softly.assertAll();
    }

    public void verifyPost(Post post, int id) {
        softly.assertThat(post.getId()).isEqualTo(id);
    }

}

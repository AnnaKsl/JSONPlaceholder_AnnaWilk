package steps;

import models.Comment;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class CommentSteps {
    SoftAssertions softly = new SoftAssertions();

    public void verifyComment(Comment comment, int postId, String name, String email, String body) {

        softly.assertThat(comment.getPostId()).isEqualTo(postId);
        softly.assertThat(comment.getName()).isEqualTo(name);
        softly.assertThat(comment.getEmail()).isEqualTo(email);
        softly.assertThat(comment.getBody()).isEqualTo(body);
        softly.assertAll();
    }

    public void verifyComment(Comment comment, int id) {
        Assertions.assertThat(comment.getId()).isEqualTo(id);
    }
}

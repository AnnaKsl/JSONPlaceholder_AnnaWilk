package steps;

import models.CommentDto;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

public class CommentSteps {

    public void verifyComment(CommentDto comment, int postId, String name, String email, String body) {
        var softly = new SoftAssertions();
        softly.assertThat(comment.getPostId()).isEqualTo(postId);
        softly.assertThat(comment.getName()).as("Name is not as expected").isEqualTo(name);
        softly.assertThat(comment.getEmail()).as("Email is not as expected").isEqualTo(email);
        softly.assertThat(comment.getBody()).as("Body is not as expected").isEqualTo(body);
        softly.assertAll();
    }

    public void verifyComment(CommentDto comment, int id) {
        Assertions.assertThat(comment.getId()).isEqualTo(id);
    }

    public static void filterCommentsWithEvenIds(List<CommentDto> allComments){
        var streamOfComments = allComments.stream();
        streamOfComments.filter(comment -> comment.getId()%2 == 0).forEach(System.out::println);
    }
}

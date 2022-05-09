package steps;
import models.PostDto;
import org.assertj.core.api.SoftAssertions;

import java.util.List;
import java.util.stream.Stream;


public class PostSteps {
    SoftAssertions softly = new SoftAssertions();
    public void verifyPost(PostDto post, int userId, String title, String body) {
        softly.assertThat(post.getUserID()).isEqualTo(userId);
        softly.assertThat(post.getBody()).isEqualTo(body);
        softly.assertThat(post.getTitle()).isEqualTo(title);
        softly.assertAll();
    }
    public static Integer getSpecificPostId(List<PostDto> allPosts, String title) {
        Stream<PostDto> streamOfPosts = allPosts.stream();
        return streamOfPosts
                .filter(post -> post.getTitle().equals(title))
                .map(PostDto::getId)
                .findFirst()
                .orElseThrow();
    }

    public void verifyPost(PostDto post, int id) {
        softly.assertThat(post.getId()).isEqualTo(id);
    }
}

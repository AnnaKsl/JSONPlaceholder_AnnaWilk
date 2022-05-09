package utils;

import models.Comment;
import models.CommentDto;

public final class CommentMapper {

    private static CommentMapper commentMapper;

    private CommentMapper() {
    }

    public static CommentMapper getInstance() {
        if (commentMapper == null) {
            commentMapper = new CommentMapper();
        }
        return commentMapper;
    }

    public Comment mapCommentDtoToComment(CommentDto commentDto) {
        var comment = new Comment();
        comment.setBody(commentDto.getBody().toUpperCase());
        comment.setEmail(commentDto.getEmail());
        comment.setId(commentDto.getId());
        comment.setPostId(commentDto.getPostId());
        comment.setName(commentDto.getName().toUpperCase());
        System.out.println(comment);
        return comment;
    }

    public CommentDto mapCommentToCommentDto(Comment comment) {
        var commentDTO = new CommentDto(comment.getId(),
                comment.getPostId(), comment.getName(), comment.getEmail(), comment.getBody());
        return commentDTO;
    }
}

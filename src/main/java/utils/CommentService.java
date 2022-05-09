package utils;

import com.google.gson.Gson;
import models.Comment;
import models.CommentDto;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CommentService {
    private final static String NEW_LINE = System.lineSeparator();


    public static List<Comment> convertListOfCommentDtoToListOfComments(List<CommentDto> listOfCommentDto) {
        var listOfComments = new ArrayList<Comment>();
        var commentMapper = CommentMapper.getInstance();
        for (CommentDto commentDto : listOfCommentDto
        ) {
            var comment = commentMapper.mapCommentDtoToComment(commentDto);
            listOfComments.add(comment);
        }
        System.out.println(listOfComments.size());
        return listOfComments;
    }

    public static List<CommentDto> convertListOfCommentsToCommentDTO(List<Comment> commentList) {
        var listOfCommentDTO = new ArrayList<CommentDto>();
        var commentMapper = CommentMapper.getInstance();
        for (Comment comment : commentList) {
            var commentDTO = commentMapper.mapCommentToCommentDto(comment);
            listOfCommentDTO.add(commentDTO);
        }
        return listOfCommentDTO;
    }

    public void putCommentsToTextFile(List<Comment> listOf100Comments) {
        Path path = Paths.get("src/main/java/Comments.txt");
        try {
            Files.createFile(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        for (Comment comment : listOf100Comments) {
            addToFile(path, comment);
        }
    }

    public Comment[] extractCommentsFromFile(Path path) {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(String.valueOf(path))) {
            return gson.fromJson(fileReader, Comment[].class);
        } catch (IOException fileNotFoundException) {
            System.out.println("There is no such file!");
            return null;
        }
    }

    private void addToFile(Path path, Comment comment) {
        try {
            Files.write(path, comment.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            Files.write(path, NEW_LINE.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException exception) {
            System.out.println("Check the data!");
        }
    }
}

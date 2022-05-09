package builders;

import models.CommentDto;

public class CommentBuilder{
    int id;
    int postId;
    String name;
    String email;
    String body;

    public void setId(int id){
        this.id = id;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail (String email){
        this.email = email;
    }
    public void setBody(String body){
        this.body = body;
    }

    public CommentDto build(){
        return new CommentDto(id,postId,name,email,body);
    }
}

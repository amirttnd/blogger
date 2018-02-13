package in.tech.blogger.vo;

import in.tech.blogger.domain.Comment;

import java.util.List;

public class CommentVO {

    Comment comment;

    List<Comment> replies;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
}

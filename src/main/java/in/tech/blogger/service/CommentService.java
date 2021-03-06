package in.tech.blogger.service;

import in.tech.blogger.domain.Comment;
import in.tech.blogger.modal.CommentModel;
import in.tech.blogger.repository.CommentRepository;
import in.tech.blogger.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Comment save(CommentModel model) {
        return commentRepository.save(new Comment(model));
    }

    public Boolean delete(String id) {
        Comment comment = commentRepository.findById(id);
        if (comment != null) {
            if (comment.isParent()) {
                List<Comment> replies = commentRepository.findAllByParentId(comment.getId());
                commentRepository.delete(replies);
            }
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }

    public Comment get(String id) {
        return commentRepository.findById(id);
    }

    public List<CommentVO> findAllByReferenceId(String referenceId) {
        CommentVO commentVO;
        List<CommentVO> commentVOs = new ArrayList<>();
        PageRequest pageRequest = new PageRequest(0, 100, Sort.Direction.ASC, "dateCreated");
        List<Comment> parents = commentRepository.findAllByReferenceIdAndParentIdIsNull(referenceId, pageRequest);
        for (Comment parent : parents) {
            commentVO = new CommentVO();
            commentVO.setComment(parent);
            commentVO.setReplies(commentRepository.findAllByParentId(parent.getId(), pageRequest));
            commentVOs.add(commentVO);
        }
        return commentVOs;
    }

    public Long countByReferenceId(String referenceId) {
        return commentRepository.countByReferenceId(referenceId);
    }
}

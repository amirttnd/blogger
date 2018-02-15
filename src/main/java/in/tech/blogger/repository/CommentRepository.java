package in.tech.blogger.repository;

import in.tech.blogger.domain.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment findById(String id);

    List<Comment> findAllByReferenceIdAndParentIdIsNull(String referenceId, Pageable pageable);

    List<Comment> findAllByParentId(String parentId, Pageable pageable);

    List<Comment> findAllByParentId(String parentId);

    Long countByReferenceId(String referenceId);
}

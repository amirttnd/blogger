package in.tech.blogger.repository;

import in.tech.blogger.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by amir on 13/2/18.
 */
public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment findById(String id);

    List<Comment> findAllByReferenceIdAndParentIdIsNull(String referenceId);

    List<Comment> findAllByParentId(String parentId);

    Long countByReferenceId(String referenceId);
}

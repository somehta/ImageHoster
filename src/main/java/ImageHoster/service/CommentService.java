package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    //call this method to create comment for an image inside repository
    public Comment uploadComment(Comment comment) {
        return commentRepository.uploadComment(comment);
    }
}

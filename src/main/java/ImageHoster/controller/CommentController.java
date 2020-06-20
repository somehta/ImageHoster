package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    ImageService imageService;

    //This controller method is called when  request mapping /image/{id}/{title}/comments
    //Two path variables id and title
    //Request parameter comment to save comment inside database
    @RequestMapping( value="/image/{id}/{title}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable("id") Integer id, @PathVariable("title") String title,
                            @RequestParam("comment") String commentText, Model model, Comment newComment, HttpSession session) {
        // Changing code to fetch image using id instead of title.
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(id);
        newComment.setUser(user);
        newComment.setImage(image);
        newComment.setCreatedDate(new Date());
        newComment.setText(commentText);
        commentService.uploadComment(newComment);
        model.addAttribute("image", image);
        model.addAttribute("comments", image.getComments());
        return "images/image";
    }
}

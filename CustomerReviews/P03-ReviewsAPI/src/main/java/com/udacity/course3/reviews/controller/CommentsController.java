package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.JPA.Comment;
import com.udacity.course3.reviews.JPA.Review;
import com.udacity.course3.reviews.mongoDBModel.EmbeddedComment;
import com.udacity.course3.reviews.mongoDBModel.ReviewDocument;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import com.udacity.course3.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    ReviewService reviewService;

    /**
     * Creates a comment for a review.
     * <p>
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Long reviewId, @RequestBody Comment comment) {
        Optional<Review> optReview = reviewRepository.findById(reviewId);
        if (optReview.isPresent()) {
            comment.setReview(optReview.get());
            commentRepository.save(comment);
            reviewService.saveReview(optReview.get());

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<?> listCommentsForReview(@PathVariable("reviewId")Long reviewId) {
        Optional<Review> optReview = reviewRepository.findById(reviewId);
        if (optReview.isPresent()) {
            //List<Comment> commentsList = reviewRepository.findById(reviewId).get().getComments(reviewId);
            List<EmbeddedComment> embeddedComments = reviewService.getReview(reviewId).getEmbeddedComments();

            return embeddedComments;
        }
        else {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }
}
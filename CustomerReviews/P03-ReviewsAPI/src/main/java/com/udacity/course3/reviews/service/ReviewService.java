package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.JPA.Comment;
import com.udacity.course3.reviews.JPA.Product;
import com.udacity.course3.reviews.JPA.Review;
import com.udacity.course3.reviews.mongoDBModel.EmbeddedComment;
import com.udacity.course3.reviews.mongoDBModel.ReviewDocument;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewMongoRepository reviewMongoRepository;
    @Autowired
    private ProductRepository productRepository;

    public void saveReview(Review review) {
        //obtain data from review
        Long reviewId = review.getReviewID();
        int rate = review.getRate();

        //obtain comment data from review
        List<EmbeddedComment> embeddedComments = new ArrayList<EmbeddedComment>();
        for (Comment comment : review.getComments(reviewId)) {
            Long commentId = comment.getCommentId();
            String content = comment.getCommentContent();

            EmbeddedComment embeddedComment = new EmbeddedComment(commentId, content);
            embeddedComments.add(embeddedComment);
        }

        //create reviewDocument
        ReviewDocument reviewDocument = new ReviewDocument();
        reviewDocument.setId(reviewId);
        reviewDocument.setRate(rate);
        reviewDocument.setEmbeddedComments(embeddedComments);

        //save reviewDocument to mongo repository
        reviewMongoRepository.save(reviewDocument);
    }

    public ReviewDocument getReview(Long reviewId) {
        //Use reviewId obtained from review saved in SQL to get the reviewDocument saved in reviewMongoRepository
        return reviewMongoRepository.findById(reviewId).get();
    }

    public List<ReviewDocument> loadReviews(Long productId) {
        //Obtain product from SQL using productId
        Product product = productRepository.findById(productId).get();

        //Obtain SQL list of reviews from product
        List<Review> reviews = product.getReviews();

        //Create list of Mongo reviewDocuments
        List<ReviewDocument> reviewDocuments = new ArrayList<ReviewDocument>();
        for (Review review : reviews) {
            //get SQL id from each review in reviews
            Long id = review.getReviewID();

            //find the corresponding Mongo reviewDoc from the review SQL id
            ReviewDocument reviewDoc = reviewMongoRepository.findById(id).get();
            //add the Mongo reviewDoc to reviewDocuments
            reviewDocuments.add(reviewDoc);
        }

        return reviewDocuments;
    }


}

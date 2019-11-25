package com.udacity.course3.reviews.JPA;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long commentId; //maps SQL COMMENT_ID to commentId

    @Column(name = "CONTENT")
    private String commentContent; //maps SQL CONTENT to commentContent

    @ManyToOne
    @JoinColumn(name = "REVIEW_ID")
    private Review review; //maps SQL REVIEW_ID to review

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Review getReview(Long commentId) {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}

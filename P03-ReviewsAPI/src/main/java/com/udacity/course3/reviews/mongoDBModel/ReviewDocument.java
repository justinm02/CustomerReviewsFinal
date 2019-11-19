package com.udacity.course3.reviews.mongoDBModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("reviewJava")
public class ReviewDocument {
    @Id
    private Long id;

    private int rate;

    private List<EmbeddedComment> embeddedComments = new ArrayList<EmbeddedComment>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public List<EmbeddedComment> getEmbeddedComments() {
        return embeddedComments;
    }

    public void setEmbeddedComments(List<EmbeddedComment> embeddedComments) {
        this.embeddedComments = embeddedComments;
    }
}

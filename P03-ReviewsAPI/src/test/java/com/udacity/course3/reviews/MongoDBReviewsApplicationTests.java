package com.udacity.course3.reviews;

import com.udacity.course3.reviews.mongoDBModel.EmbeddedComment;
import com.udacity.course3.reviews.mongoDBModel.ReviewDocument;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertEquals;

@DataMongoTest
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
public class MongoDBReviewsApplicationTests {
    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private ReviewMongoRepository reviewMongoRepository;

    @DisplayName(
            "given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")

    @Test
    public void saveReview() {
        ReviewDocument reviewDocument  = new ReviewDocument();
        reviewDocument.setId(1L);
        reviewDocument.setRate(5);

        List<EmbeddedComment> comments = new ArrayList<EmbeddedComment>();
        Long id = 0L;
        for (int i = 0; i < 3; i++) {
            comments.add(new EmbeddedComment(id, "hi!"));
            id++;
        }
        reviewDocument.setEmbeddedComments(comments);

        mongoTemplate.save(reviewDocument, "reviewJava");

        assertThat(reviewDocument).isNotNull();
        assertEquals(reviewDocument.getRate(), reviewMongoRepository.findById(1L).get().getRate());
        assertEquals(reviewDocument.getId(), reviewMongoRepository.findById(1L).get().getId());
        assertEquals(reviewDocument.getEmbeddedComments().get(1).getContent(), reviewMongoRepository.findById(1L).get().getEmbeddedComments().get(1).getContent());
    }
}

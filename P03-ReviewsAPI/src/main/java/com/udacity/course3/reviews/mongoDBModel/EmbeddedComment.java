package com.udacity.course3.reviews.mongoDBModel;

public class EmbeddedComment {
    private Long id;
    private String content;

    public EmbeddedComment(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

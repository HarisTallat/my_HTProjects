package com.example.htproject;

public class userFeedBackData {
    String name;
    String feedback;

    public userFeedBackData(String name, String feedback) {
        this.name = name;
        this.feedback = feedback;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


}

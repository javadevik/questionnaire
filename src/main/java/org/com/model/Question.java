package org.com.model;

public class Question {
    private int id;
    private String question;
    private String firstAnswer;
    private int firstId;
    private String secondAnswer;
    private int secondId;

    public Question(int id, String question,
                    String firstAnswer, int firstId,
                    String secondAnswer, int secondId) {
        this.id = id;
        this.question = question;
        this.firstAnswer = firstAnswer;
        this.firstId = firstId;
        this.secondAnswer = secondAnswer;
        this.secondId = secondId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFirstAnswer() {
        return firstAnswer;
    }

    public void setFirstAnswer(String firstAnswer) {
        this.firstAnswer = firstAnswer;
    }

    public int getFirstId() {
        return firstId;
    }

    public void setFirstId(int firstId) {
        this.firstId = firstId;
    }

    public String getSecondAnswer() {
        return secondAnswer;
    }

    public void setSecondAnswer(String secondAnswer) {
        this.secondAnswer = secondAnswer;
    }

    public int getSecondId() {
        return secondId;
    }

    public void setSecondId(int secondId) {
        this.secondId = secondId;
    }
}

package org.com.model;

import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class Questionnaire {

    private final Map<Integer, Question> questions;
    private Question current;
    @Value("${start.question}")
    private int startId;
    private int nextId;

    public Questionnaire(@NotNull Map<Integer, Question> questions) {
        this.questions = questions;
        nextId = startId;
        current = questions.get(nextId);
    }

    public boolean hasNext() {
        return nextId >= 0;
    }

    public void next(String userAnswer) {
        nextId = getNextQuestionId(userAnswer);
        current = questions.get(nextId);
    }

    public boolean hasCounterQuestion() {
        return current != null && (current.getFirstId() != 0 ||
                current.getSecondId() != 0);
    }

    public String getQuestion() {
        return current.getQuestion();
    }

    public String getFistAnswer() {
        return current.getFirstAnswer();
    }

    public String getSecondAnswer() {
        return current.getSecondAnswer();
    }

    public int getNextQuestionId(String userAnswer) {
        if (userAnswer.equalsIgnoreCase(current.getFirstAnswer()))
            return current.getFirstId();
        else if (userAnswer.equalsIgnoreCase(current.getSecondAnswer()))
            return current.getSecondId();
        throw new RuntimeException("Have no correct answer! value={ " + userAnswer + " }");
    }

}

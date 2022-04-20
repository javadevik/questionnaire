package org.com.service.impl;

import com.sun.istack.internal.NotNull;
import org.com.model.Question;
import org.com.service.PathCounter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class PathCounterImpl implements PathCounter {

    private final Map<Integer, Question> questions;
    @Value("${start.question}")
    private int startId;
    private Question current;
    private int nextId;
    private int PATH_COUNT;

    public PathCounterImpl(@NotNull Map<Integer, Question> questions) {
        this.questions = questions;
        nextId = startId;
        current = questions.get(nextId);
        afterConstruct();
    }

    private void afterConstruct() {
        questions
                .forEach((key, value) -> {
                    if (value.getFirstId() > -1)
                        ++PATH_COUNT;
                    if (value.getSecondId() > -1)
                        ++PATH_COUNT;
                });
    }

    @Override
    public int getCountBranches() {
        return PATH_COUNT;
    }

    @Override
    public boolean hasNext() {
        return current != null && (current.getFirstId() >= 0 ||
                current.getSecondId() >= 0);
    }

    @Override
    public boolean hasNext(String answer) {
        current = next(answer);
        return current != null;
    }

    @Override
    public Question next(String answer) {
        if (answer.equalsIgnoreCase(current.getFirstAnswer()))
            return questions.get(current.getFirstId());
        else if (answer.equalsIgnoreCase(current.getSecondAnswer()))
            return questions.get(current.getSecondId());
        return null;
    }

    @Override
    public String getQuestion() {
        return current.getQuestion();
    }

    @Override
    public String getFirstAnswer() {
        return current.getFirstAnswer();
    }

    @Override
    public String getSecondAnswer() {
        return current.getSecondAnswer();
    }

    @Override
    public void initCounterPaths() {
        nextId = startId;
        current = questions.get(nextId);
    }


}

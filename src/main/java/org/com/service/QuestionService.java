package org.com.service;

import org.com.model.Questionnaire;
import org.com.util.InputData;

import java.util.*;

public class QuestionService {

    private final Questionnaire questionnaire;
    private final PathCounter pathCounter;

    public QuestionService(Questionnaire questionnaire, PathCounter pathCounter) {
        this.questionnaire = questionnaire;
        this.pathCounter = pathCounter;
    }

    public void startAsk() {
        while (questionnaire.hasNext()) {
            String question = questionnaire.getQuestion();
            String firstAnswer = questionnaire.getFistAnswer();
            String secondAnswer = questionnaire.getSecondAnswer();

            System.out.println(question + ": " + firstAnswer + "/" + secondAnswer);
            System.out.print("You: ");
            String userAnswer = InputData.getString();

            if (questionnaire.hasCounterQuestion())
                questionnaire.next(userAnswer);
            else
                break;
        }
    }

    public List<Map<String, String>> findAllBranches() {
        List<Map<String, String>> branches = new ArrayList<>();
        for (int i = 1; i <= pathCounter.getCountBranches(); i++) {
            Map<String, String> branch = new LinkedHashMap<>();
            String answerOnCurrentQuestion;
            do {
                if (pathCounter.hasNext()) {
                    int countAnswerOnCurrentQuestion = (int) branches
                            .stream()
                            .filter(m -> m.containsKey(pathCounter.getQuestion()))
                            .count();
                    if (countAnswerOnCurrentQuestion < 1) {
                        branch.put(pathCounter.getQuestion(), pathCounter.getFirstAnswer());
                        answerOnCurrentQuestion = pathCounter.getFirstAnswer();
                    } else {
                        branch.put(pathCounter.getQuestion(), pathCounter.getSecondAnswer());
                        answerOnCurrentQuestion = pathCounter.getSecondAnswer();
                    }
                } else {
                    branch.put(pathCounter.getQuestion(), pathCounter.getFirstAnswer() +
                            "/" + pathCounter.getSecondAnswer());
                    break;
                }
            } while (pathCounter.hasNext(answerOnCurrentQuestion));
            branches.add(branch);
            pathCounter.initCounterPaths();
        }
        return branches;
    }
}

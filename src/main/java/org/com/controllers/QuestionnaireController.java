package org.com.controllers;

import org.com.service.JsonService;
import org.com.service.QuestionService;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

public class QuestionnaireController {

    private final QuestionService service;
    private final JsonService jsonService;
    @Value("${skip.questionnaire}")
    private boolean doesSkipQuestionnaire;
    @Value("${does.count.branches}")
    private boolean doesCountBranches;

    public QuestionnaireController(QuestionService service, JsonService jsonService) {
        this.service = service;
        this.jsonService = jsonService;
    }

    public void startPoll() {
        service.startAsk();
    }

    public void startFindAllBranches() {
        List<Map<String, String>> branches = service.findAllBranches();
        jsonService.write(branches);
    }

    public boolean isDoesSkipQuestionnaire() {
        return doesSkipQuestionnaire;
    }

    public boolean isDoesCountBranches() {
        return doesCountBranches;
    }

}

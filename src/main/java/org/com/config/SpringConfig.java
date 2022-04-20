package org.com.config;

import org.com.controllers.QuestionnaireController;
import org.com.model.Question;
import org.com.model.Questionnaire;
import org.com.service.JsonService;
import org.com.service.PathCounter;
import org.com.service.impl.PathCounterImpl;
import org.com.service.QuestionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ComponentScan("org.com")
@PropertySource("classpath:configuration.properties")
public class SpringConfig {

    @Bean
    public JsonService jsonService() {
        return new JsonService();
    }

    @Bean
    public Map<Integer, Question> questions() {
        return jsonService().read();
    }

    @Bean
    public Questionnaire questionnaire() {
        return new Questionnaire(questions());
    }

    @Bean
    public PathCounter pathCounter() {
        return new PathCounterImpl(questions());
    }

    @Bean
    public QuestionService questionService() {
        return new QuestionService(questionnaire(), pathCounter());
    }

    @Bean
    public QuestionnaireController questionnaireController() {
        return new QuestionnaireController(questionService(), jsonService());
    }

}

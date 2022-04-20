package org.com;

import org.com.config.SpringConfig;
import org.com.controllers.QuestionnaireController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QuestionnaireApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        QuestionnaireController controller = context.getBean("questionnaireController",
                QuestionnaireController.class);

        if (!controller.isDoesSkipQuestionnaire())
            controller.startPoll();
        if (controller.isDoesCountBranches())
            controller.startFindAllBranches();

        context.close();
    }
}

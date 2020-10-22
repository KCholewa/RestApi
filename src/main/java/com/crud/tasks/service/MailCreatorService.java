package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyDetails companyDetails;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("preview", previewMessage(message));
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details", companyDetails);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTrelloCardQtyEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("preview", previewMessage(message));
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details", companyDetails);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/tasks-quantity-mail", context);
    }

    public String previewMessage(String message) {
        String[] previewWord = message.split(" ");
        return previewWord[0]+" "+previewWord[1]+" "+previewWord[2]+" "+previewWord[3]+"....";
    }
}

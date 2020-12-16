package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
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


    public String buildTrelloCardEmail(String message) {

        List<String> appFunctionality = new ArrayList<>();
        appFunctionality.add("You can manage your task!");
        appFunctionality.add("Provides connection with trello account");
        appFunctionality.add("Application allows sending task to trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_details", "TaskCrudAppCreator " +
                "Our goal is to make the world a better place! " +
                "tasks@crud.com");
        context.setVariable("goodbye_message", "Have a nice day!");
        context.setVariable("preview", "Isn't this a wonderful day?");
        context.setVariable("show_button",false);
        context.setVariable("is_friend",true);
        context.setVariable("application_functionality", appFunctionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
    public String buildDailyTrelloEmail(String message) {

        List<String> companyDetails = new ArrayList<>();
        companyDetails.add("TaskCrudAppCreator");
        companyDetails.add("Our goal is to make the world a better place! ");
        companyDetails.add("tasks@crud.com");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_details", companyDetails);
        context.setVariable("goodbye_message", "Have a nice day!");
        context.setVariable("preview", "Good day for making some tasks!");
        context.setVariable("show_button",true);
        context.setVariable("is_friend",true);
        return templateEngine.process("mail/daily-trello-mail", context);
    }
}

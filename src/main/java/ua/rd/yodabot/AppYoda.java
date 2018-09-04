package ua.rd.yodabot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.rd.yodabot.repo.InMemoryTaskRepository;
import ua.rd.yodabot.service.SimpleTaskService;
import ua.rd.yodabot.service.TaskService;

public class AppYoda {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            TaskService taskService = new SimpleTaskService(new InMemoryTaskRepository()); // TODO move creation to IoC
            botsApi.registerBot(new YodaBot(taskService, new MessageService(taskService)));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
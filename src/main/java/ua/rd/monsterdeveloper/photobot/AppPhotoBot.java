package ua.rd.monsterdeveloper.photobot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.rd.yodabot.YodaBot;
import ua.rd.yodabot.repo.InMemoryTaskRepository;
import ua.rd.yodabot.service.MessageService;
import ua.rd.yodabot.service.SimpleTaskService;
import ua.rd.yodabot.service.TaskService;

public class AppPhotoBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new PhotoBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
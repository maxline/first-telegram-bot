package ua.rd.yodabot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.rd.inlinekeyboard.InlineKeyboardBot;

public class AppYoda {
    public static void main(String[] args) {
        //Initialize Api Context
        ApiContextInitializer.init();

        //Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        //Register our bot
        try {
            botsApi.registerBot(new YodaBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
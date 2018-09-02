package ua.rd.inlinekeyboard;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AppInline {
    public static void main(String[] args) {
        //Initialize Api Context
        ApiContextInitializer.init();

        //Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        //Register our bot
        try {
            botsApi.registerBot(new InlineKeyboardBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
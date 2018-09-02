package ua.rd.inlinekeyboard;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class InlineKeyboardBot extends TelegramLongPollingBot {

    MessageService messageService = new MessageService();

    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage()) {
            processMessage(update);
        } else if (update.hasCallbackQuery()) {
            processCallback(update);
        }
    }

    private void processMessage(Update update) {

        if (update.getMessage().hasText()) {
            executeMessage(messageService.generateMessage(update));
        }
    }

    private void processCallback(Update update) {

        executeMessage(new SendMessage().setText(
                update.getCallbackQuery().getData())
                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "seleon_bot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "618832611:AAEIVbJgisNdu931eZvLEH5_FzFxLWaJwMw";
    }
}
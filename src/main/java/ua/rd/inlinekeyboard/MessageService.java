package ua.rd.inlinekeyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    private static final String HELP_COMMAND_TEXT = "Use commands: \n/help \n/start \n/hello \nor just print any phrase";
    private static final String HELLO_COMMAND_TEXT = "Hello, world! This is simple bot!";

    private static final String BUTTON_1_TEXT = "Button 1";
    private static final String BUTTON_2_TEXT = "Button 2";
    private static final String BUTTON_3_TEXT = "Help";

    private static final String BUTTON_1_DATA = "Button \"Button 1\" has been pressed";
    private static final String BUTTON_2_DATA = "Button \"Button 2\" has been pressed";

    public SendMessage generateMessage(Update update) {
        String inputMessage = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();

        String outputMessage;
        SendMessage message;

        if (inputMessage.equals("/help")) {
            outputMessage = HELP_COMMAND_TEXT;
            message = createTextMessage(chat_id, outputMessage);
        } else if (inputMessage.equals("/hello")) {
            outputMessage = HELLO_COMMAND_TEXT;
            message = createTextMessage(chat_id, outputMessage);
        } else if (inputMessage.equals("/start")) {
            message = createInlineKeyBoardMessage(chat_id);
        } else {
            outputMessage = inputMessage + " 11111";
            message = createTextMessage(chat_id, outputMessage);
        }

        return message;
    }

    private SendMessage createTextMessage(long chat_id, String newMessage) {
        return new SendMessage()
                .setChatId(chat_id)
                .setText(newMessage);
    }

    private SendMessage createInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText(BUTTON_1_TEXT);
        inlineKeyboardButton1.setCallbackData(BUTTON_1_DATA);
        inlineKeyboardButton2.setText(BUTTON_2_TEXT);
        inlineKeyboardButton2.setCallbackData(BUTTON_2_DATA);
        inlineKeyboardButton2.setUrl("https://www.google.com");

        inlineKeyboardButton3.setText(BUTTON_3_TEXT);
        inlineKeyboardButton3.setCallbackData(HELP_COMMAND_TEXT);

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<InlineKeyboardButton>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<InlineKeyboardButton>();

        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow2.add(inlineKeyboardButton3);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<List<InlineKeyboardButton>>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);

        return new SendMessage().setChatId(chatId).setText("Buttons example").setReplyMarkup(inlineKeyboardMarkup);
    }
}

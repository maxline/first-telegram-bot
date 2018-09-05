package ua.rd.yodabot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ua.rd.yodabot.domain.Task;
import ua.rd.yodabot.service.TaskService;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    //TODO create dinamivally updated help commands list
    private static final String HELP_COMMAND_TEXT = "Use commands: \n/help \n/buttons \n/hello \nor just print any phrase";
    private static final String HELLO_COMMAND_TEXT = "Hello, world! This is simple bot!";

    private static final String BUTTON_1_TEXT = "All";
    private static final String BUTTON_ADD_TEXT = "Add";
    private static final String BUTTON_2_TEXT = "Button 2";
    private static final String BUTTON_3_TEXT = "Help";

    private static final String BUTTON_1_DATA = "/all";
    private static final String BUTTON_2_DATA = "Button \"Button 2\" has been pressed";

    private TaskService taskService;

    public MessageService(TaskService taskService) {
        this.taskService = taskService;
    }

    public SendMessage generateMessage(Update update) {
        String inputMessage = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();

        String textMessage;
        SendMessage sendMessage;

        if (inputMessage.equals("/help")) {
            textMessage = HELP_COMMAND_TEXT;
            sendMessage = createTextMessage(chat_id, textMessage);
        } else if (inputMessage.equals("/hello")) {
            textMessage = HELLO_COMMAND_TEXT;
            sendMessage = createTextMessage(chat_id, textMessage);
        } else if (inputMessage.equals("/buttons")) {
            sendMessage = createInlineKeyBoardMessage(chat_id);
        } else {
            taskService.add(inputMessage);
            textMessage = inputMessage + " - added";
            sendMessage = createTextMessage(chat_id, textMessage);
        }

        return sendMessage;
    }

    private SendMessage createTextMessage(long chat_id, String newMessage) {
        return new SendMessage()
                .setChatId(chat_id)
                .setText(newMessage);
    }

    private SendMessage createInlineKeyBoardMessage(long chatId) {
        //String button1Data = taskService.getById(1L).toString();
        String button1Data = "Task{id=1, description=Открыть окно 1234567890,,ddd}";
        System.out.println(button1Data);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        InlineKeyboardButton buttonAdd = new InlineKeyboardButton();

        button1.setText(BUTTON_1_TEXT);
        button1.setCallbackData(BUTTON_1_DATA);
        //button1.setCallbackData(taskService.getById(1L).toString()); //TODO doesn't work because of length limit
        button2.setText(BUTTON_2_TEXT);
        button2.setCallbackData(BUTTON_2_DATA);
        button2.setUrl("https://www.google.com");

        button3.setText(BUTTON_3_TEXT);
        button3.setCallbackData(HELP_COMMAND_TEXT);
        buttonAdd.setText(BUTTON_ADD_TEXT);
        buttonAdd.setCallbackData("111");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();

        row1.add(button1);
        row1.add(buttonAdd);
        row1.add(button2);
        row2.add(button3);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        inlineKeyboardMarkup.setKeyboard(rowList);

        return new SendMessage().setChatId(chatId).setText("Buttons example").setReplyMarkup(inlineKeyboardMarkup);
    }

    public SendMessage generateCallbackMessage(Update update){

        String updatedData = update.getCallbackQuery().getData();

        String updatedText ="";
        if (updatedData.equals("/all")){
            //long id = Integer.valueOf(update.getCallbackQuery().getData());

            for(Task task:taskService.getAll()){
                updatedText+= task.getDescription() + "\n";
            }
        } else {
            updatedText = updatedData;
        }

        return new SendMessage().setText(
                updatedText)
                .setChatId(update.getCallbackQuery().getMessage().getChatId());
    }
}

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

class TestKeyBoards {
    public InlineKeyboardMarkup firstTest(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowslist = new ArrayList<>();
        List <InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton().setText("Первый вариант").setCallbackData("1"));
        List <InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(new InlineKeyboardButton().setText("Второй вариант").setCallbackData("2"));
        rowslist.add(row);
        rowslist.add(row1);
        inlineKeyboardMarkup.setKeyboard(rowslist);

        return inlineKeyboardMarkup;

    }

    public InlineKeyboardMarkup getInlineKeyboard(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowslist = new ArrayList<>();
        List <InlineKeyboardButton> row = new ArrayList<>();
        row.add(new InlineKeyboardButton().setText("Первый ингридиент").setCallbackData("i1"));
        List <InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(new InlineKeyboardButton().setText("Второй ингридиент").setCallbackData("i1"));
        List <InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineKeyboardButton().setText("Третий ингридиент").setCallbackData("i1"));
        List <InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(new InlineKeyboardButton().setText("Четвертый ингридиент").setCallbackData("i1"));
        List <InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(new InlineKeyboardButton().setText("Пятый ингридиент").setCallbackData("i1"));
        rowslist.add(row);
        rowslist.add(row1);
        rowslist.add(row2);
        rowslist.add(row3);
        rowslist.add(row4);
        inlineKeyboardMarkup.setKeyboard(rowslist);

        return inlineKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getReplyKeyboard(){
        ReplyKeyboardMarkup replyKeyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowslist = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Первый ингридиент");
        KeyboardRow row1 = new KeyboardRow();
        row1.add("Второй ингридиент");
        KeyboardRow row2 = new KeyboardRow();
        row2.add( "Третий ингридиент");
        KeyboardRow row3 = new KeyboardRow();
        row3.add("Четвертый ингридиент");
        KeyboardRow row4 = new KeyboardRow();
        row4.add("Пятый ингридиент");
        rowslist.add(row);
        rowslist.add(row1);
        rowslist.add(row2);
        rowslist.add(row3);
        rowslist.add(row4);
        replyKeyboard.setKeyboard(rowslist);


        return replyKeyboard;
    }

}

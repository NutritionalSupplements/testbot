import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.sql.SQLException;


public class TestBot extends TelegramLongPollingBot {



    private static final Logger logger = Logger.getLogger(TestBot.class.getName());
    private static final JDBCClass jdbc = new JDBCClass();

    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        String answer = "";
        try {
            answer = jdbc.databaseQuery(update.getMessage().getText());
        } catch (SQLException e) {
            logger.warn(e.getStackTrace());
        }
        if (answer.isEmpty()) {
            answer = "Нет такой добавки в нашей базе.";
            logger.info("Нет такой добавки в базе." + update.getMessage().getText());
        }

        if (update.getMessage().hasText()) {

            message.setChatId(update.getMessage().getChatId()).setText(answer);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                logger.warn(e.getStackTrace());
            }
        }

    }


    public String getBotUsername() {
        return "testNutritionalSupplementsBot" ;
    }

    public String getBotToken() {
        return "904066555:AAGZCo-sezfxXeYyiQJOpGV6na8KSvxT6tU";
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TestBot());
        } catch (TelegramApiRequestException e) {
            logger.error(e.getStackTrace());
        }
    }
}

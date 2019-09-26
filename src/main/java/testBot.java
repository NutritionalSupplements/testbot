import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;



public class testBot extends TelegramLongPollingBot {

    private TesseractProcessing tesseractProcessing = new TesseractProcessing();


    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            message.setChatId(update.getMessage().getChatId());
            message.setText("Пришли фото состава или ингридиент, про который хотел узнать");
        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
            long chat_id = update.getMessage().getChatId();
            SendPhoto sendPhoto = new SendPhoto();
            List<PhotoSize> photos = update.getMessage().getPhoto();
            GetFile getFileRequest = new GetFile();
            getFileRequest.setFileId(photos.get(photos.size() - 1).getFileId());// index 2 - the quality of the photo, what we need
            File file;
            try {
                file = execute(getFileRequest);
            } catch (TelegramApiException e) {
                e.printStackTrace();
                return;
            }
            URL url;
            BufferedImage image;
            try {
              url = new URL(file.getFileUrl(this.getBotToken()));
              image = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            String tesseractResult = "Original Photo: \n"+ tesseractProcessing.doOcr(image)+"\n";
            BufferedImage processedImage = PhotoProcessing.toBlackAndWhite(image);
            String tesseractResultBlackAndWhite = "Black And White + bradly:\n" + tesseractProcessing.doOcr(processedImage);
            SendMessage sendMessage1 = new SendMessage().setChatId(chat_id).setText(tesseractResult);
            SendMessage sendMessage2 =new SendMessage().setChatId(chat_id).setText(tesseractResultBlackAndWhite);
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(processedImage,"jpg", os);
                InputStream fis = new ByteArrayInputStream(os.toByteArray());
                sendPhoto.setChatId(chat_id).setPhoto("test", fis );
                execute(sendPhoto);
                execute(sendMessage1);
                execute(sendMessage2);

            } catch (TelegramApiException | IOException e) {
                e.printStackTrace();
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
            telegramBotsApi.registerBot(new testBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}

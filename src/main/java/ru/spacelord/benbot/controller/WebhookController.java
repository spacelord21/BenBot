package ru.spacelord.benbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.spacelord.benbot.model.TelegramBot;

import java.util.List;
import java.util.Locale;


@RestController
public class WebhookController {

    private final TelegramBot telegramBot;


    @Autowired
    public WebhookController(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        Message message = update.getMessage();
        if(message != null) {
            String inputMessage = message.getText().toLowerCase(Locale.ROOT);
            if(inputMessage.contains("бен")) {
                List<SendSticker> stickers = telegramBot.onGroupMessageReceived(message);
                stickers.forEach(sticker -> {
                    try {
                        telegramBot.execute(sticker);
                        Thread.sleep(1000);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        return null;
    }
}

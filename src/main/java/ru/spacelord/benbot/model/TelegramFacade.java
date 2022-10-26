package ru.spacelord.benbot.model;



import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Component
public class TelegramFacade {

    private final HashMap<Integer,List<SendSticker>> stickersMap = new HashMap<>();

    @Bean
    public void initializeStickers() {
        SendSticker sticker1 = new SendSticker(); //yes
        sticker1.setSticker(new InputFile("CAACAgIAAxkBAAMuY1ggfmfphM7umfmk6cCkkyBqDF4AAmQTAAJuOjlJnGEWjCzP2jUqBA"));
        SendSticker sticker2 = new SendSticker(); // no
        sticker2.setSticker(new InputFile("CAACAgIAAxkBAAMsY1ggec6AwLmPwM8wGy1zp6o_q9QAAu8WAAIu8DlJuou_cOasfMsqBA"));
        SendSticker sticker3 = new SendSticker(); // haha
        sticker3.setSticker(new InputFile("CAACAgIAAxkBAAMwY1gggfNDhwcBJeqtKz-oUJA_zvwAAhMcAAIxRzBJSs_I3I4-C4MqBA"));
        SendSticker sticker4 = new SendSticker(); // calm
        sticker4.setSticker(new InputFile("CAACAgIAAxkBAAMyY1ggh-wnDaAW0kbA2i6c7mwW6QoAAjsXAAIlcDBJd6Ck7QfYSGsqBA"));
        SendSticker sticker5 = new SendSticker(); // paper1
        sticker5.setSticker(new InputFile("CAACAgIAAxkBAAIBU2NZQWKXtqDu7EqpCp_rzn8f_XRXAAKhFwACW8w5SdsK0UqIHRQlKgQ"));
        SendSticker sticker6 = new SendSticker(); // paper2
        sticker6.setSticker(new InputFile("CAACAgIAAxkBAAIBVWNZQX_IFmNi7U1GvmtMnmM57nGMAAIOGQACSLAwSfjbTMc40oVhKgQ"));
        List<SendSticker> stickers1 = new ArrayList<>();
        stickers1.add(sticker1); // yes
        List<SendSticker> stickers2 = new ArrayList<>();
        stickers2.add(sticker2); // no
        List<SendSticker> stickers3 = new ArrayList<>();
        stickers3.add(sticker3); // haha
        List<SendSticker> stickers4 = new ArrayList<>();
        stickers4.add(sticker3);stickers4.add(sticker1); // haha yes
        List<SendSticker> stickers5 = new ArrayList<>();
        stickers5.add(sticker3);stickers5.add(sticker2); // haha no
        List<SendSticker> stickers6 = new ArrayList<>();
        stickers6.add(sticker4);
        List<SendSticker> stickers7 = new ArrayList<>();
        stickers7.add(sticker6);stickers7.add(sticker5);
        stickersMap.put(0,stickers1);stickersMap.put(1,stickers2);stickersMap.put(2,stickers3);
        stickersMap.put(3,stickers4);stickersMap.put(4,stickers5);stickersMap.put(5,stickers6);
        stickersMap.put(6,stickers7);
    }

    public List<SendSticker> handleGroupMessage(Message message) {
        Random random = new Random();
        int n = random.nextInt(7);
        List<SendSticker> sticks = stickersMap.get(n);
        sticks.forEach(stickers -> {
            stickers.setChatId(message.getChatId());
        });
        return sticks;
    }
}

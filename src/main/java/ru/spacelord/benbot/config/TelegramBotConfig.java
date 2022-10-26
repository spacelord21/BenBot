package ru.spacelord.benbot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TelegramBotConfig {
    @Value("${telegrambot.webHookPath}")
    private String webHookPath;
    @Value("${telegrambot.username}")
    private String botUsername;
    @Value("${telegrambot.botToken}")
    private String botToken;
}

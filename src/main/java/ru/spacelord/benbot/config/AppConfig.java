package ru.spacelord.benbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.spacelord.benbot.model.TelegramBot;
import ru.spacelord.benbot.model.TelegramFacade;


@Configuration
public class AppConfig {

    private final TelegramBotConfig telegramBotConfig;

    public AppConfig(TelegramBotConfig telegramBotConfig) {
        this.telegramBotConfig = telegramBotConfig;
    }

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramBotConfig.getWebHookPath()).build();
    }

    @Bean
    public TelegramBot springWebhookBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
        TelegramBot telegramBot = new TelegramBot(setWebhook,telegramFacade);
        telegramBot.setBotPath(telegramBotConfig.getWebHookPath());
        telegramBot.setBotToken(telegramBotConfig.getBotToken());
        telegramBot.setBotUsername(telegramBotConfig.getBotUsername());
        return telegramBot;
    }
}

package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repositories.TelegramBotRepository;
import pro.sky.telegrambot.service.TelegramChatService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBotRepository telegramRepository;

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private TelegramChatService telegramChatService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            telegramChatService.checkUpdate(update);

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}

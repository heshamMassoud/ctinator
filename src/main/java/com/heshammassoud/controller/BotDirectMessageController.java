package com.heshammassoud.controller;

import com.atlassian.stride.model.webhooks.MessageSent;
import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.service.stride.ReplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

@Controller
public class BotDirectMessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotDirectMessageController.class);

    private final ReplierService replierService;

    public BotDirectMessageController(@Nonnull final ReplierService replierService) {
        this.replierService = replierService;
    }

    /**
     * Whenever the bot gets messaged privately, this controller is called.
     * @param messageSent the message sent to the bot.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/bot-direct-message", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void mention(@RequestBody @Nonnull final MessageSent messageSent) {

        LOGGER.info("Got bot direct message callback with text {}", messageSent.getMessage().getText());
        replierService.mainMenuReply(messageSent);
    }

}
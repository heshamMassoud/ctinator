package com.heshammassoud.controller;

import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.models.ActionResponse;
import com.heshammassoud.models.ActionTargetRequest;
import com.heshammassoud.service.stride.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

import static com.heshammassoud.util.stride.MessageUtil.deleteMenu;

@Controller
public class SyncController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncController.class);

    private final MessageService messageService;

    public SyncController(@Nonnull final MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Whenever the sync option in the main menu is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/sync-menu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse menu(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got sync-menu callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivately(actionTargetRequest.getContext(), deleteMenu());
        return ActionResponse.of();
    }

}
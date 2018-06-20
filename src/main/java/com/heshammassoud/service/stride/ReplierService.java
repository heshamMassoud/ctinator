package com.heshammassoud.service.stride;

import com.atlassian.stride.model.context.UserContext;
import com.atlassian.stride.model.webhooks.MessageSent;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

import static com.heshammassoud.util.stride.ContextUtil.toUserContext;
import static com.heshammassoud.util.stride.MessageUtil.getMainMenuReply;

@Service
public class ReplierService {

    private final UserService userService;
    private final MessageService messageService;

    public ReplierService(@Nonnull final UserService userService, @Nonnull final MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    /**
     * Using the sender and the content of the message sent, this method prepares a reply according to the original
     * message being sent and the sender of the message, then sends this reply privately to the sender.
     *
     * @param messageSent the original message sent to stride.
     */
    public void mainMenuReply(@Nonnull final MessageSent messageSent) {
        final String messageContent = messageSent.getMessage().getText();
        final UserContext userContext = toUserContext(messageSent);

        userService.getUser(userContext) // 1. get user.
                   .thenApply(userDetail -> getMainMenuReply(messageContent, userDetail)) // 2. build reply.
                   .thenCompose(reply -> messageService.sendPrivately(userContext, reply)); // 3. send message.
    }

}

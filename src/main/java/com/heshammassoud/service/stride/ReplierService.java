package com.heshammassoud.service.stride;

import com.atlassian.adf.Document;
import com.atlassian.stride.api.model.UserDetail;
import com.atlassian.stride.model.context.ConversationContext;
import com.atlassian.stride.model.context.UserContext;
import com.atlassian.stride.model.webhooks.MessageSent;
import com.heshammassoud.service.commercetools.ProductService;
import io.sphere.sdk.products.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.heshammassoud.util.stride.ContextUtil.toConversationContext;
import static com.heshammassoud.util.stride.ContextUtil.toUserContext;
import static com.heshammassoud.util.stride.MessageUtil.isViewWithUuid;
import static com.heshammassoud.util.stride.MessageUtil.mainMenu;
import static com.heshammassoud.util.stride.MessageUtil.noIdea;
import static com.heshammassoud.util.stride.MessageUtil.pdpDocument;
import static com.heshammassoud.util.stride.MessageUtil.referenceDocument;
import static java.lang.String.format;

@Service
public class ReplierService {

    private final UserService userService;
    private final MessageService messageService;
    private final ProductService productService;

    /**
     * Builds the bot's main menu with a custom header according to the user's initial message to the bot.
     *
     * @param message    the initial message sent by the user.
     * @param userDetail the details of the user to receive the reply.
     * @return a built main menu {@link Document} with a custom header.
     */
    @Nonnull
    public Document getReply(@Nonnull final String message, @Nullable final UserDetail userDetail) {


        // If the user asks to show a product.
        final String patternString = ".*(show|view|display).*(product.*)";
        final Pattern pattern = Pattern.compile(patternString);
        final Matcher matcher = pattern.matcher(message);
        if (matcher.matches()) {
            return referenceDocument();
        }

        // If the user gives the uuid or key of a product.
        if (isViewWithUuid(message)) {
            // Display product dialog
            final String[] strings = message.split(" ");
            final String uuid = strings[strings.length - 1];

            final Product product = productService.getProductById(uuid).join();
            return pdpDocument(product);
        }

        if (userDetail != null) {
            String header;
            final String userDisplayName = userDetail.getDisplayName();
            if (message.toLowerCase().contains("hi ")) {
                header = format("Hi %s, hope you are having a great day! What can I do for you?", userDisplayName);
            } else {
                header = (format("Hi %s, sorry I didn't get that. However, can I help you with one of these things?",
                    userDisplayName));
            }
            return mainMenu(header);
        }

        return noIdea();
    }

    public ReplierService(@Nonnull final UserService userService, @Nonnull final MessageService messageService,
                          @Nonnull final ProductService productService) {
        this.userService = userService;
        this.messageService = messageService;
        this.productService = productService;
    }

    /**
     * Using the sender and the content of the message sent, this method prepares a reply according to the original
     * message being sent and the sender of the message, then sends this reply privately to the sender.
     *
     * @param messageSent the original message sent to stride.
     */
    public void mainMenuReply(@Nonnull final MessageSent messageSent) {
        final String messageContent = messageSent.getMessage().getText();
        final ConversationContext conversationContext = toConversationContext(messageSent);
        final UserContext userContext = toUserContext(messageSent);


        if (messageContent.toLowerCase().contains("hi ")) {
            userService.getUser(userContext) // 1. get user.
                       .thenApply(UserDetail::getDisplayName)
                       .thenApply(displayName -> mainMenu(
                           format("Hi %s, hope you are having a great day! What can I do for you?",
                               displayName))) // 2. build reply.
                       .thenCompose(reply -> messageService.sendPrivately(userContext, reply)); // 3. send message.
        } else {
            messageService.send(conversationContext, getReply(messageContent, null));
        }


    }

}

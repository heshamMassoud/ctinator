package com.heshammassoud.util.stride;

import com.atlassian.stride.model.context.ConversationContext;
import com.atlassian.stride.model.context.UserContext;
import com.atlassian.stride.model.webhooks.MessageSent;

import javax.annotation.Nonnull;

import static com.atlassian.stride.model.context.Context.conversation;
import static com.atlassian.stride.model.context.Context.user;

public final class ContextUtil {

    public static ConversationContext toConversationContext(@Nonnull final MessageSent messageSent) {
        return conversation(messageSent.getCloudId(), messageSent.getConversation().getId());
    }

    public static UserContext toUserContext(@Nonnull final MessageSent messageSent) {
        return user(messageSent.getCloudId(), messageSent.getSender().getId());
    }
}

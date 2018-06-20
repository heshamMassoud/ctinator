package com.heshammassoud.service.stride;

import com.atlassian.adf.Document;
import com.atlassian.stride.api.StrideClient;
import com.atlassian.stride.api.model.EntityCreatedResponse;
import com.atlassian.stride.model.context.UserContext;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

@Service
public class MessageService {

    private final StrideClient strideClient;

    public MessageService(@Nonnull final StrideClient strideClient) {
        this.strideClient = strideClient;
    }


    /**
     * Sends the passed {@code message} privatley to the stride user defined by the passed {@link UserContext}.
     *
     * @param context the sender user context.
     * @param message the message to send.
     * @return a future containing the result of sending the message.
     */
    @Nonnull
    public CompletableFuture<EntityCreatedResponse> sendPrivately(@Nonnull final UserContext context,
                                                                  @Nonnull final Document message) {
        return strideClient.message()
                           .send(message)
                           .toUser(context);
    }

}

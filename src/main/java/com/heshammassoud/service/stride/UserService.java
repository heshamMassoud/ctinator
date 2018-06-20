package com.heshammassoud.service.stride;

import com.atlassian.stride.api.StrideClient;
import com.atlassian.stride.api.model.UserDetail;
import com.atlassian.stride.model.context.UserContext;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    private final StrideClient strideClient;

    public UserService(@Nonnull final StrideClient strideClient) {
        this.strideClient = strideClient;
    }

    /**
     * Gets the {@link UserDetail} object from a given {@link UserContext}.
     *
     * @param context the user context to get the details from.
     * @return a future containing the {@link UserDetail} fetched from the stride client.
     */
    @Nonnull
    public CompletableFuture<UserDetail> getUser(@Nonnull final UserContext context) {
        return strideClient.user()
                           .get()
                           .from(context);
    }

}

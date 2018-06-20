package com.heshammassoud.models;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static java.util.Optional.ofNullable;

public class ActionResponse {
    private String message;
    private String error;
    private NextAction nextAction;

    public ActionResponse(@Nullable final String message,
                          @Nullable final String error, @Nullable final String targetKey) {
        this.message = message;
        this.error = error;
        this.nextAction = ofNullable(targetKey)
                .map(key -> new NextAction(new Target(key))).orElse(null);
    }

    public static ActionResponse of() {
        return new ActionResponse(null, null, null);
    }

    public static ActionResponse ofMessage(@Nonnull final String message) {
        return new ActionResponse(message, null, null);
    }

    public static ActionResponse ofError(@Nonnull final String error) {
        return new ActionResponse(null, error, null);
    }

    public static ActionResponse ofNextAction(@Nonnull final String targetKey) {
        return new ActionResponse(null, null, targetKey);
    }

    public static ActionResponse ofSuccessNextAction(@Nonnull final String message, @Nonnull final String targetKey) {
        return new ActionResponse(message, null, targetKey);
    }

    public static ActionResponse ofErrorNextAction(@Nonnull final String error, @Nonnull final String targetKey) {
        return new ActionResponse(null, error, targetKey);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(@Nullable final String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(@Nullable final String error) {
        this.error = error;
    }

    public NextAction getNextAction() {
        return nextAction;
    }

    public void setNextAction(@Nullable final NextAction nextAction) {
        this.nextAction = nextAction;
    }
}

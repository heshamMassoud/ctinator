package com.heshammassoud.models.stride;

import javax.annotation.Nonnull;
import java.util.Map;

public class ActionGroupAction {
    private String key;
    private String title;
    private String appearance;
    private Action action;

    public ActionGroupAction(@Nonnull final String key, @Nonnull final String title, @Nonnull final String appearance,
                             @Nonnull final Action action) {
        this.key = key;
        this.title = title;
        this.appearance = appearance;
        this.action = action;
    }

    /**
     * Creates an action that could be used in an action group.
     * More info <a href="https://developer.atlassian.com/cloud/stride/learning/adding-actions/">here</a>.
     *
     * @param key        the key of the action.
     * @param title      the title of the action.
     * @param appearance the appearance of the action.
     * @param targetKey  the action target key.
     * @param parameters the parameters to pass to the target.
     * @return the created {@link ActionGroupAction}.
     */
    @Nonnull
    public static ActionGroupAction of(
            @Nonnull final String key,
            @Nonnull final String title,
            @Nonnull final String appearance,
            @Nonnull final String targetKey,
            @Nonnull final Map<String, String> parameters) {

        final Action action = new Action(targetKey, parameters);
        return new ActionGroupAction(key, title, appearance, action);
    }

    public String getKey() {
        return key;
    }

    public void setKey(@Nonnull final String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull final String title) {
        this.title = title;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(@Nonnull final String appearance) {
        this.appearance = appearance;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(@Nonnull final Action action) {
        this.action = action;
    }
}

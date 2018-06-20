package com.heshammassoud.util.stride;

import com.atlassian.adf.inline.Mark;
import com.heshammassoud.models.stride.ActionTarget;

import javax.annotation.Nonnull;

public final class DocumentUtil {
    /**
     * Builds a {@link Mark} of type "action" with the specified {@code title} and {@code targetKey}.
     *
     * <p>More info: about this action mark
     * <a href="https://developer.atlassian.com/cloud/stride/learning/adding-actions/">here</a>.
     *
     * @param title     the title of the action.
     * @param targetKey the key of the action.
     * @return the  {@link Mark} of type action.
     */
    @Nonnull
    public static Mark createActionMark(@Nonnull final String title, @Nonnull final String targetKey) {
        return Mark.mark("action")
                   .attribute("title", title)
                   .anyAttribute("target", new ActionTarget(targetKey));
    }
}

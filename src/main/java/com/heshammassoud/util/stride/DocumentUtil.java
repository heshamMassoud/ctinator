package com.heshammassoud.util.stride;

import com.atlassian.adf.inline.Mark;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    public static Mark createActionMark(@Nonnull final String title, @Nonnull final String targetKey,
                                        @Nonnull final String productId) {
        final ObjectNode parameters = JsonNodeFactory.instance.objectNode();
        parameters.put("id", productId);


        return Mark.mark("action")
                   .attribute("title", title)
                   .anyAttribute("target", new ActionTarget(targetKey))
                   .anyAttribute("parameters", parameters);
    }
}

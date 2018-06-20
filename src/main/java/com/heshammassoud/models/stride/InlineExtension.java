package com.heshammassoud.models.stride;

import com.atlassian.adf.InlineNode;
import com.atlassian.adf.base.AbstractNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.annotation.Nonnull;

@JsonTypeName("inlineExtension")
public class InlineExtension extends AbstractNode<InlineExtension> implements InlineNode {
    @JsonIgnore
    @Override
    public String text() {
        return strAttribute("text").orElse("");
    }


    /**
     * Creates an {@link InlineExtension} of extensionKey value: "actionGroup." and the passed parameters.
     *
     * <p>More on this can be found
     * <a href="https://developer.atlassian.com/cloud/stride/learning/adding-actions/">here</a>.
     *
     * @param parameters the parameters to pass to the inline extension.
     * @return an {@link InlineExtension} of extensionKey value: "actionGroup." and the passed parameters.
     */
    @Nonnull
    public static InlineExtension of(@Nonnull final InlineExtensionParameters parameters) {

        final InlineExtension inlineExtension = new InlineExtension();
        inlineExtension.anyAttribute("extensionType", "com.atlassian.stride");
        inlineExtension.anyAttribute("extensionKey", "actionGroup");
        inlineExtension.anyAttribute("parameters", parameters);
        return inlineExtension;
    }

    @Nonnull
    public static InlineExtension ofActionGroup(@Nonnull final String actionGroupKey,
                                                @Nonnull final ActionGroupAction... actions) {

        return of(new ActionGroupParameters(actionGroupKey, actions));
    }
}

package com.heshammassoud.models.stride;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class ActionGroupParameters extends InlineExtensionParameters {
    private List<ActionGroupAction> actions;

    public ActionGroupParameters(@Nonnull final String key, @Nonnull final ActionGroupAction... actions) {
        super(key);
        this.actions = Arrays.asList(actions);
    }

    public List<ActionGroupAction> getActions() {
        return actions;
    }

    public void setActions(@Nonnull final ActionGroupAction... actions) {
        this.actions = Arrays.asList(actions);
    }
}

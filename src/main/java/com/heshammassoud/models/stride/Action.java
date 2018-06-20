package com.heshammassoud.models.stride;

import javax.annotation.Nonnull;
import java.util.Map;

public class Action {
    private ActionTarget target;
    private Map<String, String> parameters;

    public Action(@Nonnull final String targetKey,
                  @Nonnull final Map<String, String> parameters) {
        this.target = new ActionTarget(targetKey);
        this.parameters = parameters;
    }

    public ActionTarget getTarget() {
        return target;
    }

    public void setTarget(@Nonnull final ActionTarget target) {
        this.target = target;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(@Nonnull final Map<String, String> parameters) {
        this.parameters = parameters;
    }
}

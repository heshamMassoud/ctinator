package com.heshammassoud.models;

import com.atlassian.stride.model.context.Context;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class ActionTargetRequest {
    private String source;
    private String target;
    private Context context;
    private Map<String, Object> parameters;

    public ActionTargetRequest() {
    }

    public ActionTargetRequest(@Nonnull final String source, @Nonnull final String target,
                               @Nonnull final Context context,
                               @Nonnull final Map<String, Object> parameters) {
        this.source = source;
        this.target = target;
        this.context = context;
        this.parameters = parameters;
    }

    public String getSource() {
        return source;
    }

    public void setSource(@Nonnull final String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(@Nonnull final String target) {
        this.target = target;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(@Nonnull final Context context) {
        this.context = context;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(@Nullable final Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}

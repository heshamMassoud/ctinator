package com.heshammassoud.models.stride;

import javax.annotation.Nonnull;

public class ActionTarget {
    private String key;

    public ActionTarget(@Nonnull final String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(@Nonnull final String key) {
        this.key = key;
    }
}

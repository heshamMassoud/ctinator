package com.heshammassoud.models.stride;

import javax.annotation.Nonnull;

public class InlineExtensionParameters {
    private String key;

    public InlineExtensionParameters(@Nonnull final String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(@Nonnull final String key) {
        this.key = key;
    }
}

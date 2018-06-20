package com.heshammassoud.models;

import javax.annotation.Nonnull;

public class Target {
    private String target;

    public Target(@Nonnull final String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(@Nonnull final String target) {
        this.target = target;
    }
}

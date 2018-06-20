package com.heshammassoud.models;

import javax.annotation.Nonnull;

public class NextAction {
    private Target target;

    public NextAction(@Nonnull final Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(@Nonnull final Target target) {
        this.target = target;
    }
}

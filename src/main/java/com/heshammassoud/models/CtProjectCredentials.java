package com.heshammassoud.models;

import javax.annotation.Nonnull;

public class CtProjectCredentials {

    private String projectKey;
    private String clientId;
    private String clientSecret;


    public CtProjectCredentials(@Nonnull final String projectKey, @Nonnull final String clientId,
                                @Nonnull final String clientSecret) {
        this.projectKey = projectKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setProjectKey(@Nonnull final String projectKey) {
        this.projectKey = projectKey;
    }

    public void setClientId(@Nonnull final String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(@Nonnull final String clientSecret) {
        this.clientSecret = clientSecret;
    }
}

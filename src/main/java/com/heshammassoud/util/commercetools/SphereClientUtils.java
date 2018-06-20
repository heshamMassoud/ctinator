package com.heshammassoud.util.commercetools;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereClientConfig;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.String.format;

public final class SphereClientUtils {
    private static final String CTP_CREDENTIALS_PROPERTIES = "application.properties";
    private static final SphereClientConfig CTP_SOURCE_CLIENT_CONFIG = getCtpSourceClientConfig();
    public static final SphereClient CTP_CLIENT = ClientConfigurationUtils.createClient(CTP_SOURCE_CLIENT_CONFIG);

    public static void closeCtpClient() {
        CTP_CLIENT.close();
    }

    private static SphereClientConfig getCtpSourceClientConfig() {
        return getCtpClientConfig( "stride.", "STRIDE");
    }

    private static SphereClientConfig getCtpClientConfig(@Nonnull final String propertiesPrefix,
                                                         @Nonnull final String envVarPrefix) {
        try {
            final InputStream propStream = SphereClientUtils.class.getClassLoader()
                                                                  .getResourceAsStream(CTP_CREDENTIALS_PROPERTIES);
            if (propStream != null) {
                final Properties ctpCredsProperties = new Properties();
                ctpCredsProperties.load(propStream);
                return SphereClientConfig.ofProperties(ctpCredsProperties, propertiesPrefix);
            }
        } catch (Exception exception) {
            throw new IllegalStateException(format("CTP credentials file \"%s\" found, but CTP properties"
                    + " for prefix \"%s\" can't be read", CTP_CREDENTIALS_PROPERTIES, propertiesPrefix), exception);
        }

        return SphereClientConfig.ofEnvironmentVariables(envVarPrefix);
    }
}

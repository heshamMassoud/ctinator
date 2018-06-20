package com.heshammassoud;

import com.atlassian.stride.spring.StrideSpringConfiguration;
import com.heshammassoud.util.commercetools.SphereClientUtils;
import io.sphere.sdk.client.SphereClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(StrideSpringConfiguration.class)
public class SpringConfiguration {
    @Bean
    public SphereClient sphereClient() {
        return SphereClientUtils.CTP_CLIENT;
    }
}

package com.heshammassoud.controller;

import com.atlassian.stride.spring.config.model.AppConfigHolder;
import com.atlassian.stride.spring.config.suppliers.ServiceConfigSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;

@Controller
public class DescriptorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DescriptorController.class);

    private final ServiceConfigSupplier serviceConfigSupplier;
    private final AppConfigHolder appConfigHolder;

    public DescriptorController(@Nonnull final ServiceConfigSupplier serviceConfigSupplier,
                                @Nonnull final AppConfigHolder appConfigHolder) {
        this.serviceConfigSupplier = serviceConfigSupplier;
        this.appConfigHolder = appConfigHolder;
    }

    /**
     * Defines the descriptor endpoint for describing the bot json.
     *
     * @return a {@link ModelAndView} containing the appName and the serviceConfig.
     */
    @GetMapping(path = "descriptor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView descriptor() {
        return new ModelAndView("descriptor").addObject("appName", appConfigHolder.getName())
                .addObject("serviceConfig", serviceConfigSupplier.getServiceConfig());
    }

    @GetMapping("healthcheck")
    @ResponseStatus(HttpStatus.OK)
    void healthcheck() {
    }

    @PostConstruct
    public void init() {
        LOGGER.info("App config instance: {} / {}", appConfigHolder.getInstance(), appConfigHolder.getInstances());
    }

}
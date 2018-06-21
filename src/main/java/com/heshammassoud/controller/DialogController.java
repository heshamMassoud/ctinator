package com.heshammassoud.controller;


import com.atlassian.stride.spring.auth.AuthorizeJwtParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;
import java.time.LocalTime;

@Controller
public class DialogController {

    public DialogController() {
    }

    /**
     * Defines the descriptor endpoint for describing the bot json.
     *
     * @return a {@link ModelAndView} containing the appName and the serviceConfig.
     */
    @AuthorizeJwtParameter
    @GetMapping({"/productdialog", "/productdialog/"})
    public String renderDialogView(@Nonnull final Model model) {
        model.addAttribute("msg", "A message from the controller");
        model.addAttribute("time", LocalTime.now());
        return "productdialog";
    }

}

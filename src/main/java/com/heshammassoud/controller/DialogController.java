package com.heshammassoud.controller;


import com.heshammassoud.service.commercetools.ProductService;
import io.sphere.sdk.products.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;

import static java.util.Locale.ENGLISH;

@Controller
public class DialogController {

    private final ProductService productService;

    public DialogController(@Nonnull final ProductService productService) {
        this.productService = productService;
    }

    /**
     * Defines the descriptor endpoint for describing the bot json.
     *
     * @return a {@link ModelAndView} containing the appName and the serviceConfig.
     */
    @GetMapping( {"/productdialog/", "/productdialog"})
    public String renderDialogView(@RequestParam("id") final String id,
                                   @RequestParam("gwt") final String gwt,
                                   @Nonnull final Model model) {

        final Product product = productService.getProductById(id).join();
        model.addAttribute("productName", product.getMasterData().getCurrent().getName().get(ENGLISH));
        model.addAttribute("mcUrl",
            "https://admin.commercetools.com/java-sync-target-dev2/products/" + id);
        model.addAttribute("playgroundUrl",
            "https://impex.commercetools.com/playground?endpoint=products&method=read-id&resourceId="
                + id);
        return "productdialog";
    }

}

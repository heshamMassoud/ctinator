package com.heshammassoud.controller;


import com.heshammassoud.service.commercetools.ProductService;
import io.sphere.sdk.products.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String renderDialogView(@Nonnull final Model model) {

        final String id = "195e1c96-c840-4f92-85c8-5f15f62067f8";
        final Product product = productService.getProductById(id).join();
        model.addAttribute("productName", product.getMasterData().getCurrent().getName().get(ENGLISH));
        model.addAttribute("mcUrl",
            "https://admin.commercetools.com/java-sync-target-dev2/products/" + id);
        model.addAttribute("playgroundUrl",
            "https://impex.commercetools.com/playground?endpoint=products&method=read-id&resourceId="
                + id);
        model.addAttribute("desc", "Full LED headlights produce light at 5,500 Kelvin, that's"
            + " roughly the same as daylight. This could help your eyes perceive more contrast. They experience less "
            + "strain. And those dark stretches of road become a little less daunting.");
        return "productdialog";
    }

}

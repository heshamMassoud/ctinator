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

        final String productName = product.getMasterData().getCurrent().getName().get(ENGLISH);
        final String description = product.getMasterData().getCurrent().getDescription().get(ENGLISH);
        final String masterVariantSku = product.getMasterData().getCurrent().getMasterVariant().getSku();
        final String varian2 = product.getMasterData().getCurrent().getVariant(2).getSku();
        final String varian3 = product.getMasterData().getCurrent().getVariant(3).getSku();


        model.addAttribute("productName", productName);
        model.addAttribute("mcUrl",
            "https://admin.commercetools.com/java-sync-target-dev2/products/" + id);
        model.addAttribute("playgroundUrl",
            "https://impex.commercetools.com/playground?endpoint=products&method=read-id&resourceId="
                + id);
        model.addAttribute("desc", description);
        model.addAttribute("mv", masterVariantSku);
        model.addAttribute("v2", varian2);
        model.addAttribute("v3", varian3);
        return "productdialog";
    }

}

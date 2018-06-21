package com.heshammassoud.controller;


import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.models.ActionResponse;
import com.heshammassoud.models.ActionTargetRequest;
import com.heshammassoud.service.commercetools.ProductService;
import io.sphere.sdk.products.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;

import static java.util.Locale.ENGLISH;

@Controller
public class DialogController {

    private final ProductService productService;

    public DialogController(@Nonnull final ProductService productService) {
        this.productService = productService;
    }

    /*    *
     * Defines the descriptor endpoint for describing the bot json.
     *
     * @return a {@link ModelAndView} containing the appName and the serviceConfig.

    @GetMapping( {"/productdialog", "/productdialog/"})
    @ResponseStatus(HttpStatus.OK)
    public String renderDialogView(@RequestParam("id") final String id, @Nonnull final Model model) {

        final Product product = productService.getProductById(id).join();
        model.addAttribute("productName", product.getMasterData().getCurrent().getName().get(ENGLISH));
        model.addAttribute("mcUrl",
            "https://admin.commercetools.com/java-sync-target-dev2/products/" + id);
        model.addAttribute("playgroundUrl",
            "https://impex.commercetools.com/playground?endpoint=products&method=read-id&resourceId="
                + id);
        return "productdialog";
    }*/

    /**
     * Defines the descriptor endpoint for describing the bot json.
     *
     * @return a {@link ModelAndView} containing the appName and the serviceConfig.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = {"/productdialog", "/productdialog/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse renderDialogView(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        final String id = (String) actionTargetRequest.getParameters().get("id");

        final Product product = productService.getProductById(id).join();
        final String name = product.getMasterData().getCurrent().getName().get(ENGLISH);
        return name == null ? ActionResponse.of() : ActionResponse.ofMessage(name);
    }

}

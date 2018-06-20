package com.heshammassoud.controller;

import com.atlassian.adf.Document;
import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.models.ActionResponse;
import com.heshammassoud.models.ActionTargetRequest;
import com.heshammassoud.service.commercetools.ProductService;
import com.heshammassoud.service.stride.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

import static com.heshammassoud.util.stride.MessageUtil.confirmProductsDelete;
import static com.heshammassoud.util.stride.MessageUtil.deleteMenu;
import static com.heshammassoud.util.stride.MessageUtil.mainMenu;

@Controller
public class DeleteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteController.class);

    private final MessageService messageService;
    private final ProductService productService;

    public DeleteController(@Nonnull final MessageService messageService,
                            @Nonnull final ProductService productService) {
        this.messageService = messageService;
        this.productService = productService;
    }

    /**
     * Whenever the delete option in the main menu is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @PostMapping(path = "/delete-menu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse menu(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got delete-menu callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivately(actionTargetRequest.getContext(), deleteMenu());
        return ActionResponse.of();
    }

    /**
     * Whenever the products option in the delete is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/products-delete-menu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse products(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        final Document replyMessage = productService.getTotalProducts()
                                                    .thenApply(totalNumberOfProducts ->
                                                            confirmProductsDelete(System.getenv("PROJECT_KEY"), 3000)).join();

        messageService.sendPrivately(actionTargetRequest.getContext(), replyMessage);

        return ActionResponse.of();
    }

    /**
     * Whenever the yes option in the products confirm delete is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/products-delete-yes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse productsConfirmYes(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got products-delete-yes callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivately(actionTargetRequest.getContext(),
                mainMenu("Deleting products from  \"project-x-key\" ..... Fasten your seat belt, "
                        + "this may take some time. Deleted 230/3000. "));

        productService.deleteAllProducts()
                      .thenCompose(aVoid ->
                              messageService.sendPrivately(actionTargetRequest.getContext(),
                                      mainMenu("Deleting products from  \"project-x-key\"..... "
                                              + "Fasten your seat belt, this may take some time..")))
                      .thenCompose(response -> messageService.sendPrivately(actionTargetRequest.getContext(),
                              Document.fromMarkdown("Products deleted Successfully! @gif success!")))
                      .exceptionally(throwable -> {
                          messageService.sendPrivately(actionTargetRequest.getContext(),
                                  Document.fromMarkdown("Sorry, I couldn't delete all the products. @gif sad"
                                          + throwable.getMessage()));
                          return null;
                      });
        return ActionResponse.of();
    }

    /**
     * Whenever the no option in the products confirm delete is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/products-delete-no", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse productsConfirmNo(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got products-delete-no callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivately(actionTargetRequest.getContext(),
                mainMenu("It seems that you are not ready yet for that. Anything else I can do for you?"));
        return ActionResponse.of();
    }

    /**
     * Whenever the categories option in the delete is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/categories-delete-menu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse categories(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got categories-delete-menu callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivately(actionTargetRequest.getContext(), confirmProductsDelete("NOT YET IMPLEMENTED", 0));
        return ActionResponse.of();
    }

    /**
     * Whenever the inventories option in the delete is chosen, this controller is called.
     *
     * @param actionTargetRequest the payload sent from the action.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/inventories-delete-menu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse inventories(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {

        LOGGER.info("Got inventories-delete-menu callback with payload {}", actionTargetRequest.toString());
        messageService.sendPrivately(actionTargetRequest.getContext(), confirmProductsDelete("NOT YET IMPLEMENTED", 0));
        return ActionResponse.of();
    }

}
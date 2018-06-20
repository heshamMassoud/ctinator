package com.heshammassoud.service;

import com.atlassian.adf.Document;
import com.atlassian.stride.api.StrideClient;
import com.atlassian.stride.model.context.UserContext;
import com.heshammassoud.models.stride.ActionGroupAction;
import com.heshammassoud.models.stride.InlineExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static com.heshammassoud.models.stride.InlineExtension.ofActionGroup;
import static java.util.Collections.singletonList;

@Service
public class CtService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtService.class);
    private final StrideClient strideClient;

    public CtService(@Nonnull final StrideClient strideClient) {
        this.strideClient = strideClient;
    }

    /**
     * jrogerjgiejriogjieorger gre geroij geirj ioerg.
     *
     * @param userContext jirgioerj.
     */
    public void listCtOptions(@Nonnull final UserContext userContext) {
        final Map<String, String> parameters = new HashMap<>();

        final ActionGroupAction viewProducts = ActionGroupAction.of("view-products", "View Products",
                "primary", "viewProducts", parameters);
        final ActionGroupAction viewProductTypes = ActionGroupAction.of("view-product-types",
                "View Product Types",
                "primary", "viewProductTypes", parameters);
        final ActionGroupAction viewInventories = ActionGroupAction.of("view-inventories",
                "View Inventory Entries",
                "primary", "viewInventories", parameters);

        final ActionGroupAction deleteProducts = ActionGroupAction.of("delete-products", "Delete Products",
                "primary", "deleteProducts", parameters);
        final ActionGroupAction deleteProductTypes =
                ActionGroupAction.of("delete-product-types", "Delete Product Types", "primary",
                        "deleteProductTypes", parameters);
        final ActionGroupAction deleteInventories = ActionGroupAction.of("delete-inventories",
                "Delete Inventory Entries", "primary", "deleteInventories", parameters);

        final ActionGroupAction syncProducts = ActionGroupAction.of("sync-products",
                "Sync Products to another project", "primary", "syncProducts", parameters);
        final ActionGroupAction syncProductTypes = ActionGroupAction.of("sync-product-types",
                "Sync Product Types to another project", "primary", "syncProductTypes", parameters);
        final ActionGroupAction syncInventories = ActionGroupAction.of("sync-inventories",
                "Sync Inventory Entries to another project",
                "primary", "syncInventories", parameters);

        final InlineExtension ctPlaygroundActionGroup =
                ofActionGroup("ct-playground-list",
                        viewProducts, viewProductTypes, viewInventories,
                        deleteProducts, deleteProductTypes, deleteInventories,
                        syncProducts, syncProductTypes, syncInventories);

        final Document ctpgDoc = Document.create()
                                            .paragraph(paragraph -> paragraph.text(
                                                    "Please choose one of the following options:"))
                                            .paragraph(p -> p.children(singletonList(ctPlaygroundActionGroup)));


        strideClient.message().send(ctpgDoc).toUser(userContext);
    }

    public void listTtOptions() {

    }
}

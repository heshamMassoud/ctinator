package com.heshammassoud.util.stride;

import com.atlassian.adf.Document;
import com.atlassian.adf.block.codeblock.Language;
import com.atlassian.stride.api.model.UserDetail;
import com.heshammassoud.models.stride.ActionGroupAction;
import com.heshammassoud.models.stride.InlineExtension;

import javax.annotation.Nonnull;

import static com.heshammassoud.models.stride.InlineExtension.ofActionGroup;
import static java.lang.String.format;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;

public final class MessageUtil {

    /**
     * Builds the bot's main menu with a custom header according to the user's initial message to the bot.
     * @param message the initial message sent by the user.
     * @param userDetail the details of the user to receive the reply.
     * @return a built main menu {@link Document} with a custom header.
     */
    @Nonnull
    public static Document getMainMenuReply(@Nonnull final String message, @Nonnull final UserDetail userDetail) {

        final String userDisplayName = userDetail.getDisplayName();
        String header;
        if (message.toLowerCase().contains("hi ")) {
            header = format("Hi %s, hope you are having a great day! What can I do for you?", userDisplayName);
        } else {
            header = (format("Hi %s, sorry I didn't get that. However, can I help you with one of these things?",
                    userDisplayName));
        }
        return mainMenu(header);
    }

    /**
     * Builds the bot's main menu with a custom header.
     * @param menuHeader the header to prepend to the bot's main menu.
     * @return a built main menu {@link Document} with a custom header.
     */
    @Nonnull
    public static Document mainMenu(@Nonnull final String menuHeader) {
        final ActionGroupAction syncMenuOption =
                ActionGroupAction.of("sync-menu-option",
                        "Sync data between two commercetools projects.",
                        "primary", "syncMenuOption", emptyMap());

        final ActionGroupAction deleteMenuOption =
                ActionGroupAction.of("delete-menu-option",
                        "Delete all data of one resource (e.g. products, categories, etc..)"
                                + " in a commercetools project.",
                        "primary", "deleteMenuOption", emptyMap());

        final ActionGroupAction viewMenuOption =
                ActionGroupAction.of("view-menu-option",
                        "View data in a commercetools project.",
                        "primary", "viewMenuOption", emptyMap());

        final InlineExtension mainMenuActionGroup = ofActionGroup("mainMenu",
                syncMenuOption,
                deleteMenuOption,
                viewMenuOption);

        return Document.create()
                       .paragraph(paragraph -> paragraph.text(menuHeader))
                       .paragraph(paragraph -> paragraph.children(singletonList(mainMenuActionGroup)));
    }

    /**
     * Builds the bot's delete menu.
     * @return a built main menu {@link Document} with a custom header.
     */
    @Nonnull
    public static Document deleteMenu() {

        final String menuHeader = "Right now, I only know how to delete for you the following: ";
        final ActionGroupAction productsDeleteOption =
                ActionGroupAction.of("products-delete-option",
                        "Products",
                        "default", "productsDeleteOption", emptyMap());

        final ActionGroupAction categoriesDeleteOption =
                ActionGroupAction.of("categories-delete-option",
                        "Categories",
                        "default", "categoriesDeleteOption", emptyMap());

        final ActionGroupAction inventoriesDeleteOption =
                ActionGroupAction.of("inventories-delete-option",
                        "Inventory Entries",
                        "default", "inventoriesDeleteOption", emptyMap());

        final InlineExtension deleteMenuActionGroup = ofActionGroup("deleteMenu",
                productsDeleteOption,
                categoriesDeleteOption,
                inventoriesDeleteOption);

        return Document.create()
                       .paragraph(paragraph -> paragraph.text(menuHeader))
                       .paragraph(paragraph -> paragraph.children(singletonList(deleteMenuActionGroup)));
    }

    /**
     * Builds the bot's delete menu.
     * @return a built main menu {@link Document} with a custom header.
     */
    @Nonnull
    public static Document confirmProductsDelete(@Nonnull final String projectKey,
                                                 final int numberOfProducts) {

        final String menuHeader =
                format("Are you sure you want to delete all %d products in '%s'?", numberOfProducts, projectKey);

        final ActionGroupAction yesOption =
                ActionGroupAction.of("products-delete-yes-confirm-option",
                        "Yes",
                        "default", "productsDeleteYes", emptyMap());

        final ActionGroupAction noOption =
                ActionGroupAction.of("products-delete-no-confirm-option",
                        "No",
                        "default", "productsDeleteNo", emptyMap());



        final InlineExtension confirmationActionGroup =
                ofActionGroup("deleteProductsConfirm", yesOption, noOption);

        return Document.create()
                       .paragraph(paragraph -> paragraph.text(menuHeader))
                       .paragraph(paragraph -> paragraph.children(singletonList(confirmationActionGroup)));
    }

    /**
     * A reference menu to use stuff from.
     * @return a built reference menu.
     */
    @Nonnull
    private Document referenceDocument() {
        return Document.create()
                       .paragraph(p -> p
                               .text("Here is some ")
                               .strong("bold test")
                               .text(" and ")
                               .em("text in italics")
                               .text(" as well as ")
                               .link(" a link", "https://www.atlassian.com")
                               .text(" , emojis ")
                               .emoji(":smile:")
                               .emoji(":rofl:")
                               .emoji(":nerd:")
                               .text(" and some code: ")
                               .code("var i = 0;")
                               .text(" and a bullet list"))
                       .bulletList(b -> b
                               .item("With one bullet point")
                               .item("And another"))
                       .info(p -> p
                               .paragraph("and an info panel with some text, with some more code below"))
                       .code(Language.javascript, "var i = 0;\nwhile(true) {\n  i++;\n}")
                       .paragraph("And a card")
                       .card("With a title", c -> c.attrs()
                                                   .link("https://www.atlassian.com")
                                                   .description("With some description, and a couple of attributes")
                                                   .background("https://www.atlassian.com")
                                                   .detail(d -> d
                                                           .title("Type")
                                                           .text("Task")
                                                           .icon("https://ecosystem.atlassian.net/secure/"
                                                                   + "viewavatar?size=xsmall&avatarId"
                                                                   + "=15318&avatarType=issuetype", "Task"))
                                                   .detail(d -> d
                                                           .title("User")
                                                           .text("Joe Blog")
                                                           .icon("https://ecosystem.atlassian.net/secure/"
                                                                   + "viewavatar?size=xsmall&avatarId="
                                                                   + "15318&avatarType=issuetype", "Task")));
    }


}

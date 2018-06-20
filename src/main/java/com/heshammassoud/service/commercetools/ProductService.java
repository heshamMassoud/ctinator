package com.heshammassoud.service.commercetools;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereRequest;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.commands.ProductDeleteCommand;
import io.sphere.sdk.products.commands.ProductUpdateCommand;
import io.sphere.sdk.products.commands.updateactions.Unpublish;
import io.sphere.sdk.products.queries.ProductQuery;
import io.sphere.sdk.queries.PagedQueryResult;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static com.heshammassoud.util.commercetools.CtpQueryUtils.queryAndCompose;

@Service
public class ProductService {
    private SphereClient sphereClient;

    public ProductService(@Nonnull final SphereClient sphereClient) {
        this.sphereClient = sphereClient;
    }

    /**
     * Gets a future containing the total number of products.
     */
    @Nonnull
    public CompletableFuture<Long> getTotalProducts() {
        return sphereClient.execute(ProductQuery.of())
                           .thenApply(PagedQueryResult::getTotal)
                           .toCompletableFuture();
    }

    /**
     * Unpublishes all published products, then deletes all products from the CTP project defined by the
     * {@code ctpClient}.
     */
    @Nonnull
    public CompletableFuture<Void> deleteAllProducts() {
        return queryAndCompose(sphereClient, ProductQuery.of(), product -> safeDeleteProduct(sphereClient, product));
    }

    /**
     * If the {@code product} is published, issues an unpublish request followed by a delete. Otherwise,
     * issues a delete request right away.
     *
     * @param ctpClient defines the CTP project to delete the product from.
     * @param product   the product to be deleted.
     * @return a {@link CompletionStage} containing the deleted product.
     */
    @Nonnull
    private static CompletionStage<Product> safeDeleteProduct(@Nonnull final SphereClient ctpClient,
                                                              @Nonnull final Product product) {
        return product.getMasterData().isPublished()
                ? unpublishAndDeleteProduct(ctpClient, product) : deleteProduct(ctpClient, product);
    }

    /**
     * Issues an unpublish request followed by a delete.
     *
     * @param ctpClient defines the CTP project to delete the product from.
     * @param product   the product to be unpublished and deleted.
     * @return a {@link CompletionStage} containing the deleted product.
     */
    @Nonnull
    private static CompletionStage<Product> unpublishAndDeleteProduct(@Nonnull final SphereClient ctpClient,
                                                                      @Nonnull final Product product) {
        return ctpClient.execute(buildUnpublishRequest(product))
                        .thenCompose(unpublishedProduct ->
                                deleteProduct(ctpClient, unpublishedProduct));
    }

    /**
     * Note: This method assumes the product is unpublished.
     *
     * @param ctpClient the client defining the CTP project to delete the product from.
     * @param product   the product to be deleted.
     * @return a {@link CompletionStage} containing the deleted product. If the product supplied was already unpublished
     *         the method will return a completion stage that completed exceptionally.
     */
    @Nonnull
    private static CompletionStage<Product> deleteProduct(@Nonnull final SphereClient ctpClient,
                                                          @Nonnull final Product product) {
        return ctpClient.execute(ProductDeleteCommand.of(product));
    }

    /**
     * Builds an unpublish request for the supplied product.
     *
     * @param product defines the product to build an un publish request for.
     * @return an unpublish request for the supplied product.
     */
    @Nonnull
    private static SphereRequest<Product> buildUnpublishRequest(@Nonnull final Product product) {
        return ProductUpdateCommand.of(product, Unpublish.of());
    }

}

package com.heshammassoud.util.commercetools;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.models.Resource;
import io.sphere.sdk.queries.QueryDsl;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import static io.sphere.sdk.queries.QueryExecutionUtils.DEFAULT_PAGE_SIZE;

public final class CtpQueryUtils {

    /**
     * Queries all elements matching a query by using a limit based pagination with a combination of id sorting and a
     * page size 500. More on the algorithm can be found here: http://dev.commercetools.com/http-api.html#offset.
     *
     * <p>The method takes a callback {@link Function} that returns a result of type {@code <S>} that is returned on
     * every page of elements queried. Eventually, the method returns a {@link CompletionStage} that contains a list of
     * all the results of the callbacks returned from every page.
     *
     * <p>NOTE: This method fetches all paged results sequentially as opposed to fetching the pages in parallel.
     *
     * @param client     commercetools client
     * @param query      query containing predicates and expansion paths
     * @param pageMapper callback function that is called on every page queried
     * @param <T>        type of one query result element
     * @param <C>        type of the query
     * @param <S>        type of the returned result of the callback function on every page.
     * @return a completion stage containing a list of mapped pages as a result.
     */
    @Nonnull
    public static <T extends Resource, C extends QueryDsl<T, C>, S> CompletionStage<List<S>>
        queryAll(@Nonnull final SphereClient client, @Nonnull final QueryDsl<T, C> query,
                 @Nonnull final Function<List<T>, S> pageMapper) {
        return queryAll(client, query, pageMapper, DEFAULT_PAGE_SIZE);
    }

    /**
     * Queries all elements matching a query by using a limit based pagination with a combination of id sorting and a
     * page size 500. More on the algorithm can be found here: http://dev.commercetools.com/http-api.html#offset
     *
     * <p>The method takes a consumer {@link Consumer} that is applied on on every page of elements queried.
     *
     * <p>NOTE: This method fetches all paged results sequentially as opposed to fetching the pages in parallel.
     *
     * @param client       commercetools client
     * @param query        query containing predicates and expansion paths
     * @param pageConsumer consumer applied on every page queried
     * @param <T>          type of one query result element
     * @param <C>          type of the query
     * @return a completion stage containing void as a result after the consumer was applied on all pages.
     */
    @Nonnull
    public static <T extends Resource, C extends QueryDsl<T, C>> CompletionStage<Void>
        queryAll(@Nonnull final SphereClient client, @Nonnull final QueryDsl<T, C> query,
                 @Nonnull final Consumer<List<T>> pageConsumer) {
        return queryAll(client, query, pageConsumer, DEFAULT_PAGE_SIZE);
    }

    /**
     * Queries all elements matching a query by using a limit based pagination with a combination of id sorting and the
     * supplied {@code pageSize}.
     * More on the algorithm can be found here: http://dev.commercetools.com/http-api.html#offset.
     *
     * <p>The method takes a callback {@link Function} that returns a result of type {@code <S>} that is returned on
     * every page of elements queried. Eventually, the method returns a {@link CompletionStage} that contains a list of
     * all the results of the callbacks returned from every page.
     *
     * <p>NOTE: This method fetches all paged results sequentially as opposed to fetching the pages in parallel.
     *
     * @param client     commercetools client
     * @param query      query containing predicates and expansion paths
     * @param pageMapper callback function that is called on every page queried
     * @param <T>        type of one query result element
     * @param <C>        type of the query
     * @param <S>        type of the returned result of the callback function on every page.
     * @param pageSize   the page size.
     * @return a completion stage containing a list of mapped pages as a result.
     */
    @Nonnull
    public static <T extends Resource, C extends QueryDsl<T, C>, S> CompletionStage<List<S>>
        queryAll(@Nonnull final SphereClient client, @Nonnull final QueryDsl<T, C> query,
                 @Nonnull final Function<List<T>, S> pageMapper, final int pageSize) {
        final QueryAll<T, C, S> queryAll = QueryAll.of(client, query, pageSize);
        return queryAll.run(pageMapper);
    }

    /**
     * Queries all elements matching a query by using a limit based pagination with a combination of id sorting and the
     * supplied {@code pageSize}.
     * More on the algorithm can be found here: http://dev.commercetools.com/http-api.html#offset
     *
     * <p>The method takes a consumer {@link Consumer} that is applied on on every page of elements queried.
     *
     * <p>NOTE: This method fetches all paged results sequentially as opposed to fetching the pages in parallel.
     *
     * @param client       commercetools client
     * @param query        query containing predicates and expansion paths
     * @param pageConsumer consumer applied on every page queried
     * @param <T>          type of one query result element
     * @param <C>          type of the query
     * @param pageSize     the page size
     * @return a completion stage containing void as a result after the consumer was applied on all pages.
     */
    @Nonnull
    public static <T extends Resource, C extends QueryDsl<T, C>> CompletionStage<Void>
        queryAll(@Nonnull final SphereClient client, @Nonnull final QueryDsl<T, C> query,
                 @Nonnull final Consumer<List<T>> pageConsumer, final int pageSize) {
        final QueryAll<T, C, Void> queryAll = QueryAll.of(client, query, pageSize);
        return queryAll.run(pageConsumer);
    }

    /**
     * Applies the {@code resourceToStageMapper} function on each page, resulting from the {@code query} executed by
     * the {@code ctpClient}, to map each resource to a {@link CompletionStage} and then executes these stages in
     * parallel within each page.
     *
     *  @param ctpClient             defines the CTP project to apply the query on.
     * @param query                 query that should be made on the CTP project.
     * @param resourceToStageMapper defines a mapper function that should be applied on each resource, in the fetched
 *                              page from the query on the specified CTP project, to map it to a
 *                              {@link CompletionStage} which will be executed (in a blocking fashion) after
     *
     */
    public static <T extends Resource, C extends QueryDsl<T, C>, S> CompletableFuture<Void> queryAndCompose(
            @Nonnull final SphereClient ctpClient,
            @Nonnull final QueryDsl<T, C> query,
            @Nonnull final Function<T, CompletionStage<S>> resourceToStageMapper) {

        final Consumer<List<T>> pageConsumer = pageElements ->
            CompletableFuture.allOf(pageElements.stream()
                                                .map(resourceToStageMapper)
                                                .map(CompletionStage::toCompletableFuture)
                                                .toArray(CompletableFuture[]::new))
                             .join();

        return CtpQueryUtils.queryAll(ctpClient, query, pageConsumer)
                            .toCompletableFuture();
    }

    private CtpQueryUtils() {
    }
}

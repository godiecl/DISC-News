/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 *
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */

package com.durrutia.dnews.controller;

import com.durrutia.dnews.model.Article;
import com.durrutia.dnews.model.NewsApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.lang3.time.StopWatch;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase Principal que contiene los metodos de acceso a las noticias.
 */
@Slf4j
public final class ArticleController {

    /**
     * Des-Serializador GSON
     */
    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting() // TODO: Eliminar en modo produccion
            .create();

    /**
     * API Key
     */
    private static final String apiKey = "8dd673e94a9e4086a41b4cde0b6aa1c5";

    /**
     * URL desde donde se obtendran los {@link Article}.
     */
    private static final String url = "https://newsapi.org/v2/top-headlines?";

    /**
     * Cliente OkHttp
     */
    private final OkHttpClient okHttpClient = new OkHttpClient();

    /**
     * Obtencion de {@link Article}s desde Internet.
     *
     * @return the {@link List} of {@link Article}.
     */
    public List<Article> getArticles(final String source) throws IOException {

        // Cronometro
        final StopWatch stopWatch = StopWatch.createStarted();

        // URL to get news
        final String apiUrl = url + "sources=" + source + "&sortBy=latest&apiKey=" + apiKey;

        log.debug("Getting Articles, using url: {}", apiUrl);

        // Peticion
        final Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        // Respuesta
        final Response response = okHttpClient.newCall(request).execute();
        final String json = response.body().string();

        final NewsApi newsApi = gson.fromJson(json, NewsApi.class);

        // Fix de la fecha
        for (final Article article : newsApi.getArticles()) {

            // Fix en el caso de que no hayan fechas
            if (article.getPublishedAt() != null) {
                final DateTime dateTime = ISODateTimeFormat.dateTimeNoMillis().parseDateTime(article.getPublishedAt());
                article.setPublishedAtDateTime(dateTime);
            }
        }

        try {
            return newsApi.getArticles();
        } finally {
            log.debug("Articles in: {}", stopWatch);
        }
    }

}

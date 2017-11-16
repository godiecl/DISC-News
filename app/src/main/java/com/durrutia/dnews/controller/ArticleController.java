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

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase Principal que contiene los metodos de acceso a las noticias.
 */
public final class ArticleController {

    /**
     * Des-Serializador GSON
     */
    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting() // TODO: Eliminar en modo produccion
            .create();

    /**
     * URL desde donde se obtendran los {@link Article}.
     */
    private static final String url = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=latest&apiKey=638da3177b274f35a68cc0c539e4d294";

    /**
     * Cliente OkHttp
     */
    private final OkHttpClient okHttpClient = new OkHttpClient();

    /**
     * Obtencion de {@link Article}s desde Internet.
     *
     * @return the {@link List} of {@link Article}.
     */
    public List<Article> getArticles() throws IOException {

        // Peticion
        final Request request = new Request.Builder()
                .url(url)
                .build();

        // Respuesta
        final Response response = okHttpClient.newCall(request).execute();
        final String json = response.body().string();

        final NewsApi newsApi = gson.fromJson(json, NewsApi.class);

        // Fix de la fecha
        for (final Article article : newsApi.getArticles()) {
            final DateTime dateTime = ISODateTimeFormat.dateTimeNoMillis().parseDateTime(article.getPublishedAt());
            // article.setPublishedAtDateTime(dateTime);
        }

        return newsApi.getArticles();

    }


}

/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 *
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */

package com.durrutia.dnews;

import com.durrutia.dnews.controller.ArticleController;
import com.durrutia.dnews.model.Article;

import org.apache.commons.lang3.time.StopWatch;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Diego Urrutia Astorga.
 */
@Slf4j
public final class TestNewsController {

    /**
     * @throws IOException
     */
    @Test
    public void testConnection() throws IOException {

        final StopWatch stopWatch = StopWatch.createStarted();
        log.debug("Starting test connection ..");

        final ArticleController nc = new ArticleController();
        final List<Article> articles = nc.getArticles("techcrunch,ars-technica,engadget,buzzfeed,wired");
        Assertions.assertThat(articles).isNotNull();
        Assertions.assertThat(articles).isNotEmpty();

        log.debug("Size: {}", articles.size());

        for (final Article article : articles) {
            log.debug("Article: {}", article);
            log.debug("Article Pretty: {}", article.getPrettyPublishedAt());
        }

        log.info("Test in: {}", stopWatch);

    }

}

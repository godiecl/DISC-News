/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 *
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */

package com.durrutia.dnews.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Clase generada via http://www.jsonschema2pojo.org/
 */
// @Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class Article {

    /**
     * Author
     */
    @Getter
    String author;

    /**
     * Title
     */
    @Getter
    String title;

    /**
     * Description
     */
    @Getter
    String description;

    /**
     * URL: main link
     */
    @Getter
    String url;

    /**
     * URL: link to image
     */
    @Getter
    String urlToImage;

    /**
     * Date: 2017-11-16T19:40:25Z
     */
    String publishedAt;

    /**
     * Source
     */
    @Getter
    Source source;

    /**
     * Fecha
     */
    @Getter
    DateTime publishedAtDateTime;

    /**
     * @return the String representation.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * Fix the article
     *
     * @param article to fix.
     */
    public static void fix(final Article article) {

        if (article.publishedAt != null) {

            // Fixing date
            article.publishedAtDateTime = EntityUtils.parse(article.publishedAt);

        }

    }


    /**
     * Internal article source.
     */
    @Builder
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    public static final class Source {

        /**
         *
         */
        @Getter
        String id;

        /**
         *
         */
        @Getter
        String name;

        /**
         * @return the String representation.
         */
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }

    }

}

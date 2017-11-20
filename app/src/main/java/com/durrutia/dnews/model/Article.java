/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 *
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */

package com.durrutia.dnews.model;

import com.durrutia.dnews.dao.AppDatabase;
import com.durrutia.dnews.dao.DateTimeConverter;
import com.durrutia.dnews.dao.SourceConverter;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import java.util.UUID;

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
@Table(database = AppDatabase.class)
@AllArgsConstructor
@NoArgsConstructor
public final class Article {

    /**
     * ID
     */
    @PrimaryKey
    @Getter
    UUID id;

    /**
     * Author
     */
    @Getter
    @Column
    String author;

    /**
     * Title
     */
    @Getter
    @Column
    String title;

    /**
     * Description
     */
    @Getter
    @Column
    String description;

    /**
     * URL: main link
     */
    @Getter
    @Column
    String url;

    /**
     * URL: link to image
     */
    @Getter
    @Column
    String urlToImage;

    /**
     * Date: 2017-11-16T19:40:25Z
     */
    String publishedAt;

    /**
     * Source
     */
    @Getter
    @Column(typeConverter = SourceConverter.class)
    Source source;

    /**
     * Fecha
     */
    @Getter
    @Column(typeConverter = DateTimeConverter.class)
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

            // Output the date time in ISO8601 format (yyyy-MM-ddTHH:mm:ss.SSSZZ)
            article.publishedAt = article.publishedAtDateTime.toString();

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

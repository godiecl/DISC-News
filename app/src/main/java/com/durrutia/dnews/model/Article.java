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
import org.ocpsoft.prettytime.PrettyTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

/**
 * Clase generada via http://www.jsonschema2pojo.org/
 */
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
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
    @Getter
    String publishedAt;

    /**
     * Source
     */
    @Getter
    Source source;

    /**
     * Fecha
     */
    @Setter
    @NonFinal
    DateTime publishedAtDateTime;

    /**
     * Formateador de fecha
     */
    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    /**
     * @return the String representation.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the PrettyTime
     */
    public String getPrettyPublishedAt() {
        if (this.publishedAtDateTime == null) {
            return null;
        }
        return PRETTY_TIME.format(this.publishedAtDateTime.toDate());
    }

    /**
     *
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

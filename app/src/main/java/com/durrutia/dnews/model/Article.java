/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 *
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */

package com.durrutia.dnews.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

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
     * Date
     * FIXME: Detect the format
     */
    @Getter
    String publishedAt;

    /**
     * @return the String representation.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}

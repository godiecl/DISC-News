/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com This work is licensed under the Creative Commons Attribution 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/
 */

package com.durrutia.dnews.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * Clase generada via http://www.jsonschema2pojo.org/
 */
@Builder
public final class NewsApi {

    @Getter
    private String status;

    @Getter
    private String source;

    @Getter
    private String sortBy;

    @Getter
    private List<Article> articles;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}

package com.durrutia.dnews.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author Diego Urrutia Astorga.
 */
public final class TestArticle {

    /**
     * Simple test del constructor via patron builder.
     */
    @Test
    public void testConstructor() {

        final Article article = Article.builder()
                .author("Diego Urrutia Astorga")
                .title("Example Title")
                .description("Example Description")
                .url("http://durrutia.com")
                .build();

        Assertions.assertThat(article).isNotNull();

        Assertions.assertThat(article.getAuthor()).isNotBlank();

    }

}

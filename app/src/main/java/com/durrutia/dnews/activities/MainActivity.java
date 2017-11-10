/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/
 */

package com.durrutia.dnews.activities;

import android.app.ListActivity;
import android.os.Bundle;

import com.durrutia.dnews.adapters.ArticleAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Diego Urrutia Astorga
 */
@Slf4j
public class MainActivity extends ListActivity {

    /**
     * Adapter
     */
    private ArticleAdapter articleAdapter = new ArticleAdapter();

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        log.debug("onCreate.");
        this.setListAdapter(articleAdapter);

    }
}

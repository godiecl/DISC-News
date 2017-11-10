/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/
 */

package com.durrutia.dnews.activities;

import android.app.ListActivity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import com.durrutia.dnews.adapters.ArticleAdapter;
import com.durrutia.dnews.tasks.GetArticlesTask;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Diego Urrutia Astorga
 */
@Slf4j
public class MainActivity extends ListActivity {

    /**
     * Adapter
     */
    private ArticleAdapter articleAdapter;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        log.debug("onCreate.");

        int[] colors = { 0, 0xFFFF0000, 0} ;
        this.getListView().setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        this.getListView().setDividerHeight(5);

        // Adaptador
        this.articleAdapter = new ArticleAdapter(this);
        super.setListAdapter(this.articleAdapter);

        // Background task
        final GetArticlesTask getArticlesTask = new GetArticlesTask(this.articleAdapter);

        // Execute order 1313!
        getArticlesTask.execute();

    }
}

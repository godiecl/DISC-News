/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/
 */

package com.durrutia.dnews.activities;

import android.app.ListActivity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.durrutia.dnews.adapters.ArticleDBFlowAdapter;
import com.durrutia.dnews.tasks.GetSaveArticlesTask;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Diego Urrutia Astorga
 */
@Slf4j
public final class MainActivity extends ListActivity implements GetSaveArticlesTask.TaskListener {

    /**
     * Adapter de {@link com.durrutia.dnews.model.Article}.
     */
    private BaseAdapter articleAdapter;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Row division
        int[] colors = {0, 0xFFFF0000, 0};
        this.getListView().setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        this.getListView().setDividerHeight(5);

        // Adaptador de articles
        this.articleAdapter = new ArticleDBFlowAdapter(this);
        super.setListAdapter(this.articleAdapter);

        // Background task: Get Articles from Internet
        // final GetArticlesTask getArticlesTask = new GetArticlesTask(this.articleAdapter);

        // Execute order 66 in background!
        // getArticlesTask.execute();

        if (this.articleAdapter.isEmpty()) {
            log.debug("Adapter empty, running background task ..");
            final GetSaveArticlesTask getSaveArticlesTask = new GetSaveArticlesTask();
            getSaveArticlesTask.execute(this);
        }
    }

    /**
     * GetSaveArticlesTask terminated
     */
    @Override
    public void taskFinished() {
        log.debug("Finished!");
        this.articleAdapter.notifyDataSetChanged();
    }
}

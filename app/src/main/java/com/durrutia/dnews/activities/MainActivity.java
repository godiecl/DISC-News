/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/
 */

package com.durrutia.dnews.activities;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.durrutia.dnews.adapters.ArticleDBFlowAdapter;
import com.durrutia.dnews.tasks.GetSaveArticlesTask;
import com.squareup.seismic.ShakeDetector;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Diego Urrutia Astorga
 */
@Slf4j
public final class MainActivity extends ListActivity implements GetSaveArticlesTask.TaskListener, ShakeDetector.Listener {

    /**
     * Adapter de {@link com.durrutia.dnews.model.Article}.
     */
    private BaseAdapter articleAdapter;

    /**
     * Running background task
     */
    private GetSaveArticlesTask getSaveArticlesTask;

    /**
     * Shake Detector
     */
    private ShakeDetector shakeDetector;

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
            this.runGetAndSaveArticlesTask();
        }

        this.shakeDetector = new ShakeDetector(this);

    }

    /**
     * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when
     * the activity had been stopped, but is now again being displayed to the
     * user.  It will be followed by {@link #onResume}.
     * <p>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onCreate
     * @see #onStop
     * @see #onResume
     */
    @Override
    protected void onStart() {
        super.onStart();

        final SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        shakeDetector.start(sensorManager);

    }

    /**
     * Called when you are no longer visible to the user.  You will next
     * receive either {@link #onRestart}, {@link #onDestroy}, or nothing,
     * depending on later user activity.
     * <p>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onRestart
     * @see #onResume
     * @see #onSaveInstanceState
     * @see #onDestroy
     */
    @Override
    protected void onStop() {
        super.onStop();

        shakeDetector.stop();
    }

    /**
     *
     */
    private void runGetAndSaveArticlesTask() {

        if (this.getSaveArticlesTask != null) {
            Toast.makeText(this, "Already downloading Articles ..", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show little message
        Toast.makeText(this, "Downloading Articles ..", Toast.LENGTH_LONG).show();

        log.debug("Starting GetSaveArticlesTask ..");
        this.getSaveArticlesTask = new GetSaveArticlesTask(this);
        this.getSaveArticlesTask.execute();

    }

    /**
     * Aviso que se termino la obtencion de los {@link com.durrutia.dnews.model.Article}.
     *
     * @param newsArticles
     */
    @Override
    public void taskFinished(int newsArticles) {

        // Show little message
        Toast.makeText(this, "New Articles: " + newsArticles, Toast.LENGTH_LONG).show();

        log.debug("Finished!");
        this.articleAdapter.notifyDataSetChanged();

        // Clean the task!
        this.getSaveArticlesTask = null;

    }

    /**
     * Called on the main thread when the device is shaken.
     */
    @Override
    public void hearShake() {

        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(150);
        }

        this.runGetAndSaveArticlesTask();
    }



}

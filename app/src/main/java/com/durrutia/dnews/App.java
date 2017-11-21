/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/
 */
package com.durrutia.dnews;

import android.app.Application;

import com.durrutia.dnews.dao.AppDatabase;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.apache.commons.lang3.time.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Diego Urrutia Astorga on 2017-11-09.
 */
@Slf4j
public final class App extends Application {

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Timer
        final StopWatch stopWatch = StopWatch.createStarted();

        // Facebook Fresco
        {
            // Pipeline config
            final ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                    .setDownsampleEnabled(true)
                    .build();

            // Initialize Fresco
            Fresco.initialize(this, imagePipelineConfig);
        }

        // DBFLow
        {
            // Initialize DBFLow
            FlowManager.init(FlowConfig.builder(this)
                    .addDatabaseConfig(DatabaseConfig.builder(AppDatabase.class).databaseName("articlestore").build())
                    // .openDatabasesOnInit(true)
                    .build()
            );
        }

        // Timming
        log.debug("Initialization in: {}", stopWatch);

    }

}

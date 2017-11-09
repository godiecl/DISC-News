/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/
 */
package com.durrutia.dnews;

import android.app.Application;

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

        log.debug("onCreate.");

    }

}

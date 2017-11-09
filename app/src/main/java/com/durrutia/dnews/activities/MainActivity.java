/*
 * Copyright (c) 2017 by Diego Urrutia-Astorga http://durrutia.com
 * This work is licensed under the Creative Commons Attribution 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/
 */

package com.durrutia.dnews.activities;

import android.app.Activity;
import android.os.Bundle;

import com.durrutia.dnews.R;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Diego Urrutia Astorga
 */
@Slf4j
public class MainActivity extends Activity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log.debug("onCreate.");

    }
}

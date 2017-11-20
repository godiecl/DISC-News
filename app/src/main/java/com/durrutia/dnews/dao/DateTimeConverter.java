package com.durrutia.dnews.dao;

import com.durrutia.dnews.model.EntityUtils;
import com.raizlabs.android.dbflow.converter.TypeConverter;

import org.joda.time.DateTime;

/**
 * @author Diego Urrutia Astorga
 */
public final class DateTimeConverter extends TypeConverter<String, DateTime> {
    /**
     * Converts the ModelClass into a DataClass
     *
     * @param model this will be called upon syncing
     * @return The DataClass value that converts into a SQLite type
     */
    @Override
    public String getDBValue(DateTime model) {
        return model.toString();
    }

    /**
     * Converts a DataClass from the DB into a ModelClass
     *
     * @param data This will be called when the model is loaded from the DB
     * @return The ModelClass value that gets set in a Model that holds the data class.
     */
    @Override
    public DateTime getModelValue(String data) {
        return EntityUtils.parse(data);
    }
}

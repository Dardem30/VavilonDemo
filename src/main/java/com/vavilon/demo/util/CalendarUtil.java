package com.vavilon.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarUtil {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd_HH-mm-ss";
    private static final DateFormat dateTimeFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);

    public static String formatDateTime(final Date date) {
        return dateTimeFormatter.format(date);
    }
}

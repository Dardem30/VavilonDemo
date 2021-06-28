package com.vavilon.demo.util;

import java.util.Date;

public class CommonUtils {

    public static String addTimestampToFileName(final String originalFilename) {
        final int dotIndex = originalFilename.lastIndexOf(".");
        final String justName = originalFilename.substring(0, dotIndex);
        final String extension = originalFilename.substring(dotIndex);
        return justName + "_" + CalendarUtil.formatDateTime(new Date()) + extension;
    }
}

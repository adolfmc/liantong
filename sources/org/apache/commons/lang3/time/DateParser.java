package org.apache.commons.lang3.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface DateParser {
    Locale getLocale();

    String getPattern();

    TimeZone getTimeZone();

    Date parse(String str) throws ParseException;

    Date parse(String str, ParsePosition parsePosition);

    boolean parse(String str, ParsePosition parsePosition, Calendar calendar);

    Object parseObject(String str) throws ParseException;

    Object parseObject(String str, ParsePosition parsePosition);
}

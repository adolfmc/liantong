package android.net.http;

import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
final class LegacyHttpDateTime {
    private static final String HTTP_DATE_ANSIC_REGEXP = "[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})";
    private static final String HTTP_DATE_RFC_REGEXP = "([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])";
    private static final Pattern HTTP_DATE_RFC_PATTERN = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
    private static final Pattern HTTP_DATE_ANSIC_PATTERN = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");

    LegacyHttpDateTime() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\452516_dexfile_execute.dex */
    public static class TimeOfDay {
        int hour;
        int minute;
        int second;

        TimeOfDay(int i, int i2, int i3) {
            this.hour = i;
            this.minute = i2;
            this.second = i3;
        }
    }

    public static long parse(String str) throws IllegalArgumentException {
        int month;
        int year;
        int i;
        TimeOfDay timeOfDay;
        Matcher matcher = HTTP_DATE_RFC_PATTERN.matcher(str);
        if (matcher.find()) {
            i = getDate(matcher.group(1));
            month = getMonth(matcher.group(2));
            year = getYear(matcher.group(3));
            timeOfDay = getTime(matcher.group(4));
        } else {
            Matcher matcher2 = HTTP_DATE_ANSIC_PATTERN.matcher(str);
            if (matcher2.find()) {
                month = getMonth(matcher2.group(1));
                int date = getDate(matcher2.group(2));
                TimeOfDay time = getTime(matcher2.group(3));
                year = getYear(matcher2.group(4));
                i = date;
                timeOfDay = time;
            } else {
                throw new IllegalArgumentException();
            }
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.set(1, year);
        gregorianCalendar.set(2, month);
        gregorianCalendar.set(5, i);
        gregorianCalendar.set(11, timeOfDay.hour);
        gregorianCalendar.set(12, timeOfDay.minute);
        gregorianCalendar.set(13, timeOfDay.second);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar.getTimeInMillis();
    }

    private static int getDate(String str) {
        if (str.length() == 2) {
            return ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0');
        }
        return str.charAt(0) - '0';
    }

    private static int getMonth(String str) {
        switch (((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291) {
            case 9:
                return 11;
            case 10:
                return 1;
            case 22:
                return 0;
            case 26:
                return 7;
            case 29:
                return 2;
            case 32:
                return 3;
            case 35:
                return 9;
            case 36:
                return 4;
            case 37:
                return 8;
            case 40:
                return 6;
            case 42:
                return 5;
            case 48:
                return 10;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static int getYear(String str) {
        if (str.length() == 2) {
            int charAt = ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0');
            if (charAt >= 70) {
                return charAt + 1900;
            }
            return charAt + 2000;
        } else if (str.length() == 3) {
            return ((str.charAt(0) - '0') * 100) + ((str.charAt(1) - '0') * 10) + (str.charAt(2) - '0') + 1900;
        } else {
            if (str.length() == 4) {
                return ((str.charAt(0) - '0') * 1000) + ((str.charAt(1) - '0') * 100) + ((str.charAt(2) - '0') * 10) + (str.charAt(3) - '0');
            }
            return 1970;
        }
    }

    private static TimeOfDay getTime(String str) {
        int i;
        int i2;
        int i3;
        int charAt = str.charAt(0) - '0';
        if (str.charAt(1) != ':') {
            i = 2;
            charAt = (charAt * 10) + (str.charAt(1) - '0');
        } else {
            i = 1;
        }
        int i4 = i + 1 + 1 + 1 + 1;
        return new TimeOfDay(charAt, ((str.charAt(i2) - '0') * 10) + (str.charAt(i3) - '0'), ((str.charAt(i4) - '0') * 10) + (str.charAt(i4 + 1) - '0'));
    }
}

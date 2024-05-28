package org.codehaus.jackson.map.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ISO8601Utils {
    private static final String GMT_ID = "GMT";
    private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone("GMT");

    public static String format(Date date) {
        return format(date, false, TIMEZONE_GMT);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_GMT);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00a9 A[Catch: IllegalArgumentException -> 0x00d8, NumberFormatException -> 0x00f0, IndexOutOfBoundsException -> 0x0108, TryCatch #2 {NumberFormatException -> 0x00f0, IllegalArgumentException -> 0x00d8, IndexOutOfBoundsException -> 0x0108, blocks: (B:3:0x0004, B:5:0x004b, B:7:0x005d, B:17:0x009b, B:19:0x00a9, B:21:0x00d2, B:22:0x00d7, B:14:0x006f, B:15:0x0085, B:16:0x0086), top: B:32:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00d2 A[Catch: IllegalArgumentException -> 0x00d8, NumberFormatException -> 0x00f0, IndexOutOfBoundsException -> 0x0108, TryCatch #2 {NumberFormatException -> 0x00f0, IllegalArgumentException -> 0x00d8, IndexOutOfBoundsException -> 0x0108, blocks: (B:3:0x0004, B:5:0x004b, B:7:0x005d, B:17:0x009b, B:19:0x00a9, B:21:0x00d2, B:22:0x00d7, B:14:0x006f, B:15:0x0085, B:16:0x0086), top: B:32:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Date parse(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.map.util.ISO8601Utils.parse(java.lang.String):java.util.Date");
    }

    private static void checkOffset(String str, int i, char c) throws IndexOutOfBoundsException {
        char charAt = str.charAt(i);
        if (charAt == c) {
            return;
        }
        throw new IndexOutOfBoundsException("Expected '" + c + "' character but found '" + charAt + "'");
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        int i3 = 0;
        if (i < i2) {
            int i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str);
            }
            i3 = -digit;
            i = i4;
        }
        while (i < i2) {
            int i5 = i + 1;
            int digit2 = Character.digit(str.charAt(i), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str);
            }
            i3 = (i3 * 10) - digit2;
            i = i5;
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }
}

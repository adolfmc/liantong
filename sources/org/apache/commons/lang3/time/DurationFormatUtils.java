package org.apache.commons.lang3.time;

import com.sdk.p285a.C6960d;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DurationFormatUtils {
    public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.SSS'S'";

    /* renamed from: y */
    static final Object f26268y = "y";

    /* renamed from: M */
    static final Object f26263M = "M";

    /* renamed from: d */
    static final Object f26265d = C6960d.f18019d;

    /* renamed from: H */
    static final Object f26262H = "H";

    /* renamed from: m */
    static final Object f26266m = "m";

    /* renamed from: s */
    static final Object f26267s = "s";

    /* renamed from: S */
    static final Object f26264S = "S";

    public static String formatDurationHMS(long j) {
        return formatDuration(j, "HH:mm:ss.SSS");
    }

    public static String formatDurationISO(long j) {
        return formatDuration(j, "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.SSS'S'", false);
    }

    public static String formatDuration(long j, String str) {
        return formatDuration(j, str, true);
    }

    public static String formatDuration(long j, String str, boolean z) {
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        Validate.inclusiveBetween(0L, Long.MAX_VALUE, j, "durationMillis must not be negative");
        Token[] lexx = lexx(str);
        if (Token.containsTokenWithValue(lexx, f26265d)) {
            long j8 = j / 86400000;
            j2 = j - (86400000 * j8);
            j3 = j8;
        } else {
            j2 = j;
            j3 = 0;
        }
        if (Token.containsTokenWithValue(lexx, f26262H)) {
            long j9 = j2 / 3600000;
            j2 -= 3600000 * j9;
            j4 = j9;
        } else {
            j4 = 0;
        }
        if (Token.containsTokenWithValue(lexx, f26266m)) {
            long j10 = j2 / 60000;
            j2 -= 60000 * j10;
            j5 = j10;
        } else {
            j5 = 0;
        }
        if (Token.containsTokenWithValue(lexx, f26267s)) {
            long j11 = j2 / 1000;
            j7 = j2 - (1000 * j11);
            j6 = j11;
        } else {
            j6 = 0;
            j7 = j2;
        }
        return format(lexx, 0L, 0L, j3, j4, j5, j6, j7, z);
    }

    public static String formatDurationWords(long j, boolean z, boolean z2) {
        String formatDuration = formatDuration(j, "d' days 'H' hours 'm' minutes 's' seconds'");
        if (z) {
            formatDuration = " " + formatDuration;
            String replaceOnce = StringUtils.replaceOnce(formatDuration, " 0 days", "");
            if (replaceOnce.length() != formatDuration.length()) {
                String replaceOnce2 = StringUtils.replaceOnce(replaceOnce, " 0 hours", "");
                if (replaceOnce2.length() != replaceOnce.length()) {
                    formatDuration = StringUtils.replaceOnce(replaceOnce2, " 0 minutes", "");
                    if (formatDuration.length() != formatDuration.length()) {
                        formatDuration = StringUtils.replaceOnce(formatDuration, " 0 seconds", "");
                    }
                } else {
                    formatDuration = replaceOnce;
                }
            }
            if (formatDuration.length() != 0) {
                formatDuration = formatDuration.substring(1);
            }
        }
        if (z2) {
            String replaceOnce3 = StringUtils.replaceOnce(formatDuration, " 0 seconds", "");
            if (replaceOnce3.length() != formatDuration.length()) {
                formatDuration = StringUtils.replaceOnce(replaceOnce3, " 0 minutes", "");
                if (formatDuration.length() != replaceOnce3.length()) {
                    String replaceOnce4 = StringUtils.replaceOnce(formatDuration, " 0 hours", "");
                    if (replaceOnce4.length() != formatDuration.length()) {
                        formatDuration = StringUtils.replaceOnce(replaceOnce4, " 0 days", "");
                    }
                } else {
                    formatDuration = replaceOnce3;
                }
            }
        }
        return StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(" " + formatDuration, " 1 seconds", " 1 second"), " 1 minutes", " 1 minute"), " 1 hours", " 1 hour"), " 1 days", " 1 day").trim();
    }

    public static String formatPeriodISO(long j, long j2) {
        return formatPeriod(j, j2, "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.SSS'S'", false, TimeZone.getDefault());
    }

    public static String formatPeriod(long j, long j2, String str) {
        return formatPeriod(j, j2, str, true, TimeZone.getDefault());
    }

    public static String formatPeriod(long j, long j2, String str, boolean z, TimeZone timeZone) {
        Validate.isTrue(j <= j2, "startMillis must not be greater than endMillis", new Object[0]);
        Token[] lexx = lexx(str);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(new Date(j));
        Calendar calendar2 = Calendar.getInstance(timeZone);
        calendar2.setTime(new Date(j2));
        int i = calendar2.get(14) - calendar.get(14);
        int i2 = calendar2.get(13) - calendar.get(13);
        int i3 = calendar2.get(12) - calendar.get(12);
        int i4 = calendar2.get(11) - calendar.get(11);
        int i5 = calendar2.get(5) - calendar.get(5);
        int i6 = calendar2.get(2) - calendar.get(2);
        int i7 = calendar2.get(1) - calendar.get(1);
        while (i < 0) {
            i += 1000;
            i2--;
        }
        while (i2 < 0) {
            i2 += 60;
            i3--;
        }
        while (i3 < 0) {
            i3 += 60;
            i4--;
        }
        while (i4 < 0) {
            i4 += 24;
            i5--;
        }
        if (Token.containsTokenWithValue(lexx, f26263M)) {
            while (i5 < 0) {
                i5 += calendar.getActualMaximum(5);
                i6--;
                calendar.add(2, 1);
            }
            while (i6 < 0) {
                i6 += 12;
                i7--;
            }
            if (!Token.containsTokenWithValue(lexx, f26268y) && i7 != 0) {
                while (i7 != 0) {
                    i6 += i7 * 12;
                    i7 = 0;
                }
            }
        } else {
            if (!Token.containsTokenWithValue(lexx, f26268y)) {
                int i8 = calendar2.get(1);
                if (i6 < 0) {
                    i8--;
                }
                while (calendar.get(1) != i8) {
                    int actualMaximum = i5 + (calendar.getActualMaximum(6) - calendar.get(6));
                    if ((calendar instanceof GregorianCalendar) && calendar.get(2) == 1 && calendar.get(5) == 29) {
                        actualMaximum++;
                    }
                    calendar.add(1, 1);
                    i5 = actualMaximum + calendar.get(6);
                }
                i7 = 0;
            }
            while (calendar.get(2) != calendar2.get(2)) {
                i5 += calendar.getActualMaximum(5);
                calendar.add(2, 1);
            }
            i6 = 0;
            while (i5 < 0) {
                i5 += calendar.getActualMaximum(5);
                i6--;
                calendar.add(2, 1);
            }
        }
        if (!Token.containsTokenWithValue(lexx, f26265d)) {
            i4 += i5 * 24;
            i5 = 0;
        }
        if (!Token.containsTokenWithValue(lexx, f26262H)) {
            i3 += i4 * 60;
            i4 = 0;
        }
        if (!Token.containsTokenWithValue(lexx, f26266m)) {
            i2 += i3 * 60;
            i3 = 0;
        }
        if (!Token.containsTokenWithValue(lexx, f26267s)) {
            i += i2 * 1000;
            i2 = 0;
        }
        return format(lexx, i7, i6, i5, i4, i3, i2, i, z);
    }

    static String format(Token[] tokenArr, long j, long j2, long j3, long j4, long j5, long j6, long j7, boolean z) {
        int i;
        int i2;
        Token[] tokenArr2 = tokenArr;
        StringBuilder sb = new StringBuilder();
        int length = tokenArr2.length;
        int i3 = 0;
        boolean z2 = false;
        while (i3 < length) {
            Token token = tokenArr2[i3];
            Object value = token.getValue();
            int count = token.getCount();
            if (value instanceof StringBuilder) {
                sb.append(value.toString());
                i2 = length;
                i = i3;
            } else if (value.equals(f26268y)) {
                sb.append(paddedValue(j, z, count));
                i2 = length;
                i = i3;
                z2 = false;
            } else if (value.equals(f26263M)) {
                i = i3;
                sb.append(paddedValue(j2, z, count));
                i2 = length;
                z2 = false;
            } else {
                i = i3;
                if (value.equals(f26265d)) {
                    sb.append(paddedValue(j3, z, count));
                    i2 = length;
                    z2 = false;
                } else if (value.equals(f26262H)) {
                    sb.append(paddedValue(j4, z, count));
                    i2 = length;
                    z2 = false;
                } else if (value.equals(f26266m)) {
                    sb.append(paddedValue(j5, z, count));
                    i2 = length;
                    z2 = false;
                } else if (value.equals(f26267s)) {
                    i2 = length;
                    sb.append(paddedValue(j6, z, count));
                    z2 = true;
                } else {
                    i2 = length;
                    if (value.equals(f26264S)) {
                        if (z2) {
                            sb.append(paddedValue(j7, true, z ? Math.max(3, count) : 3));
                        } else {
                            sb.append(paddedValue(j7, z, count));
                        }
                        z2 = false;
                    }
                }
            }
            i3 = i + 1;
            length = i2;
            tokenArr2 = tokenArr;
        }
        return sb.toString();
    }

    private static String paddedValue(long j, boolean z, int i) {
        String l = Long.toString(j);
        return z ? StringUtils.leftPad(l, i, '0') : l;
    }

    static Token[] lexx(String str) {
        Object obj;
        ArrayList arrayList = new ArrayList(str.length());
        boolean z = false;
        StringBuilder sb = null;
        Token token = null;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!z || charAt == '\'') {
                if (charAt != '\'') {
                    if (charAt == 'H') {
                        obj = f26262H;
                    } else if (charAt == 'M') {
                        obj = f26263M;
                    } else if (charAt == 'S') {
                        obj = f26264S;
                    } else if (charAt == 'd') {
                        obj = f26265d;
                    } else if (charAt == 'm') {
                        obj = f26266m;
                    } else if (charAt == 's') {
                        obj = f26267s;
                    } else if (charAt == 'y') {
                        obj = f26268y;
                    } else {
                        if (sb == null) {
                            sb = new StringBuilder();
                            arrayList.add(new Token(sb));
                        }
                        sb.append(charAt);
                        obj = null;
                    }
                } else if (z) {
                    z = false;
                    sb = null;
                    obj = null;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    arrayList.add(new Token(sb2));
                    obj = null;
                    sb = sb2;
                    z = true;
                }
                if (obj != null) {
                    if (token != null && token.getValue().equals(obj)) {
                        token.increment();
                    } else {
                        token = new Token(obj);
                        arrayList.add(token);
                    }
                    sb = null;
                }
            } else {
                sb.append(charAt);
            }
        }
        if (z) {
            throw new IllegalArgumentException("Unmatched quote in format: " + str);
        }
        return (Token[]) arrayList.toArray(new Token[arrayList.size()]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Token {
        private int count;
        private final Object value;

        static boolean containsTokenWithValue(Token[] tokenArr, Object obj) {
            for (Token token : tokenArr) {
                if (token.getValue() == obj) {
                    return true;
                }
            }
            return false;
        }

        Token(Object obj) {
            this.value = obj;
            this.count = 1;
        }

        Token(Object obj, int i) {
            this.value = obj;
            this.count = i;
        }

        void increment() {
            this.count++;
        }

        int getCount() {
            return this.count;
        }

        Object getValue() {
            return this.value;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Token) {
                Token token = (Token) obj;
                if (this.value.getClass() == token.value.getClass() && this.count == token.count) {
                    Object obj2 = this.value;
                    if (obj2 instanceof StringBuilder) {
                        return obj2.toString().equals(token.value.toString());
                    }
                    if (obj2 instanceof Number) {
                        return obj2.equals(token.value);
                    }
                    return obj2 == token.value;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public String toString() {
            return StringUtils.repeat(this.value.toString(), this.count);
        }
    }
}

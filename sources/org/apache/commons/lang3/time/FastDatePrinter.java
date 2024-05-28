package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.exception.ExceptionUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class FastDatePrinter implements Serializable, DatePrinter {
    public static final int FULL = 0;
    public static final int LONG = 1;
    private static final int MAX_DIGITS = 10;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static final ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = new ConcurrentHashMap(7);
    private static final long serialVersionUID = 1;
    private final Locale mLocale;
    private transient int mMaxLengthEstimate;
    private final String mPattern;
    private transient Rule[] mRules;
    private final TimeZone mTimeZone;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface NumberRule extends Rule {
        void appendTo(Appendable appendable, int i) throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface Rule {
        void appendTo(Appendable appendable, Calendar calendar) throws IOException;

        int estimateLength();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FastDatePrinter(String str, TimeZone timeZone, Locale locale) {
        this.mPattern = str;
        this.mTimeZone = timeZone;
        this.mLocale = locale;
        init();
    }

    private void init() {
        List<Rule> parsePattern = parsePattern();
        this.mRules = (Rule[]) parsePattern.toArray(new Rule[parsePattern.size()]);
        int length = this.mRules.length;
        int i = 0;
        while (true) {
            length--;
            if (length >= 0) {
                i += this.mRules[length].estimateLength();
            } else {
                this.mMaxLengthEstimate = i;
                return;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0133  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.util.List<org.apache.commons.lang3.time.FastDatePrinter.Rule> parsePattern() {
        /*
            Method dump skipped, instructions count: 514
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.FastDatePrinter.parsePattern():java.util.List");
    }

    protected String parseToken(String str, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        int i = iArr[0];
        int length = str.length();
        char charAt = str.charAt(i);
        if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
            sb.append(charAt);
            while (true) {
                int i2 = i + 1;
                if (i2 >= length || str.charAt(i2) != charAt) {
                    break;
                }
                sb.append(charAt);
                i = i2;
            }
        } else {
            sb.append('\'');
            boolean z = false;
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 != '\'') {
                    if (!z && ((charAt2 >= 'A' && charAt2 <= 'Z') || (charAt2 >= 'a' && charAt2 <= 'z'))) {
                        i--;
                        break;
                    }
                    sb.append(charAt2);
                } else {
                    int i3 = i + 1;
                    if (i3 >= length || str.charAt(i3) != '\'') {
                        z = !z;
                    } else {
                        sb.append(charAt2);
                        i = i3;
                    }
                }
                i++;
            }
        }
        iArr[0] = i;
        return sb.toString();
    }

    protected NumberRule selectNumberRule(int i, int i2) {
        switch (i2) {
            case 1:
                return new UnpaddedNumberField(i);
            case 2:
                return new TwoDigitNumberField(i);
            default:
                return new PaddedNumberField(i, i2);
        }
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    @Deprecated
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Date) {
            return format((Date) obj, stringBuffer);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj, stringBuffer);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), stringBuffer);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown class: ");
        sb.append(obj == null ? "<null>" : obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String format(Object obj) {
        if (obj instanceof Date) {
            return format((Date) obj);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown class: ");
        sb.append(obj == null ? "<null>" : obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(long j) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTimeInMillis(j);
        return applyRulesToString(newCalendar);
    }

    private String applyRulesToString(Calendar calendar) {
        return ((StringBuilder) applyRules(calendar, (Calendar) new StringBuilder(this.mMaxLengthEstimate))).toString();
    }

    private Calendar newCalendar() {
        return Calendar.getInstance(this.mTimeZone, this.mLocale);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(Date date) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTime(date);
        return applyRulesToString(newCalendar);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(Calendar calendar) {
        return ((StringBuilder) format(calendar, (Calendar) new StringBuilder(this.mMaxLengthEstimate))).toString();
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(long j, StringBuffer stringBuffer) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTimeInMillis(j);
        return (StringBuffer) applyRules(newCalendar, (Calendar) stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Date date, StringBuffer stringBuffer) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTime(date);
        return (StringBuffer) applyRules(newCalendar, (Calendar) stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Calendar calendar, StringBuffer stringBuffer) {
        return format(calendar.getTime(), stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(long j, B b) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTimeInMillis(j);
        return (B) applyRules(newCalendar, (Calendar) b);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(Date date, B b) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTime(date);
        return (B) applyRules(newCalendar, (Calendar) b);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(Calendar calendar, B b) {
        if (!calendar.getTimeZone().equals(this.mTimeZone)) {
            calendar = (Calendar) calendar.clone();
            calendar.setTimeZone(this.mTimeZone);
        }
        return (B) applyRules(calendar, (Calendar) b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public StringBuffer applyRules(Calendar calendar, StringBuffer stringBuffer) {
        return (StringBuffer) applyRules(calendar, (Calendar) stringBuffer);
    }

    private <B extends Appendable> B applyRules(Calendar calendar, B b) {
        try {
            for (Rule rule : this.mRules) {
                rule.appendTo(b, calendar);
            }
        } catch (IOException e) {
            ExceptionUtils.rethrow(e);
        }
        return b;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String getPattern() {
        return this.mPattern;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public Locale getLocale() {
        return this.mLocale;
    }

    public int getMaxLengthEstimate() {
        return this.mMaxLengthEstimate;
    }

    public boolean equals(Object obj) {
        if (obj instanceof FastDatePrinter) {
            FastDatePrinter fastDatePrinter = (FastDatePrinter) obj;
            return this.mPattern.equals(fastDatePrinter.mPattern) && this.mTimeZone.equals(fastDatePrinter.mTimeZone) && this.mLocale.equals(fastDatePrinter.mLocale);
        }
        return false;
    }

    public int hashCode() {
        return this.mPattern.hashCode() + ((this.mTimeZone.hashCode() + (this.mLocale.hashCode() * 13)) * 13);
    }

    public String toString() {
        return "FastDatePrinter[" + this.mPattern + "," + this.mLocale + "," + this.mTimeZone.getID() + "]";
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendDigits(Appendable appendable, int i) throws IOException {
        appendable.append((char) ((i / 10) + 48));
        appendable.append((char) ((i % 10) + 48));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void appendFullDigits(java.lang.Appendable r5, int r6, int r7) throws java.io.IOException {
        /*
            r0 = 10
            r1 = 48
            r2 = 10000(0x2710, float:1.4013E-41)
            if (r6 >= r2) goto L50
            r2 = 4
            r3 = 1000(0x3e8, float:1.401E-42)
            r4 = 100
            if (r6 >= r3) goto L16
            r2 = 3
            if (r6 >= r4) goto L16
            r2 = 2
            if (r6 >= r0) goto L16
            r2 = 1
        L16:
            int r7 = r7 - r2
        L17:
            if (r7 <= 0) goto L1f
            r5.append(r1)
            int r7 = r7 + (-1)
            goto L17
        L1f:
            switch(r2) {
                case 1: goto L4a;
                case 2: goto L3b;
                case 3: goto L2c;
                case 4: goto L23;
                default: goto L22;
            }
        L22:
            goto L73
        L23:
            int r7 = r6 / 1000
            int r7 = r7 + r1
            char r7 = (char) r7
            r5.append(r7)
            int r6 = r6 % 1000
        L2c:
            if (r6 < r4) goto L38
            int r7 = r6 / 100
            int r7 = r7 + r1
            char r7 = (char) r7
            r5.append(r7)
            int r6 = r6 % 100
            goto L3b
        L38:
            r5.append(r1)
        L3b:
            if (r6 < r0) goto L47
            int r7 = r6 / 10
            int r7 = r7 + r1
            char r7 = (char) r7
            r5.append(r7)
            int r6 = r6 % 10
            goto L4a
        L47:
            r5.append(r1)
        L4a:
            int r6 = r6 + r1
            char r6 = (char) r6
            r5.append(r6)
            goto L73
        L50:
            char[] r0 = new char[r0]
            r2 = 0
        L53:
            if (r6 == 0) goto L61
            int r3 = r2 + 1
            int r4 = r6 % 10
            int r4 = r4 + r1
            char r4 = (char) r4
            r0[r2] = r4
            int r6 = r6 / 10
            r2 = r3
            goto L53
        L61:
            if (r2 >= r7) goto L69
            r5.append(r1)
            int r7 = r7 + (-1)
            goto L61
        L69:
            int r2 = r2 + (-1)
            if (r2 < 0) goto L73
            char r6 = r0[r2]
            r5.append(r6)
            goto L69
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.FastDatePrinter.appendFullDigits(java.lang.Appendable, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class CharacterLiteral implements Rule {
        private final char mValue;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 1;
        }

        CharacterLiteral(char c) {
            this.mValue = c;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendable.append(this.mValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class StringLiteral implements Rule {
        private final String mValue;

        StringLiteral(String str) {
            this.mValue = str;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mValue.length();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendable.append(this.mValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TextField implements Rule {
        private final int mField;
        private final String[] mValues;

        TextField(int i, String[] strArr) {
            this.mField = i;
            this.mValues = strArr;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            int length = this.mValues.length;
            int i = 0;
            while (true) {
                length--;
                if (length < 0) {
                    return i;
                }
                int length2 = this.mValues[length].length();
                if (length2 > i) {
                    i = length2;
                }
            }
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendable.append(this.mValues[calendar.get(this.mField)]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class UnpaddedNumberField implements NumberRule {
        private final int mField;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 4;
        }

        UnpaddedNumberField(int i) {
            this.mField = i;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            if (i < 10) {
                appendable.append((char) (i + 48));
            } else if (i < 100) {
                FastDatePrinter.appendDigits(appendable, i);
            } else {
                FastDatePrinter.appendFullDigits(appendable, i, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class UnpaddedMonthField implements NumberRule {
        static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        UnpaddedMonthField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            if (i >= 10) {
                FastDatePrinter.appendDigits(appendable, i);
            } else {
                appendable.append((char) (i + 48));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class PaddedNumberField implements NumberRule {
        private final int mField;
        private final int mSize;

        PaddedNumberField(int i, int i2) {
            if (i2 < 3) {
                throw new IllegalArgumentException();
            }
            this.mField = i;
            this.mSize = i2;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mSize;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            FastDatePrinter.appendFullDigits(appendable, i, this.mSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TwoDigitNumberField implements NumberRule {
        private final int mField;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitNumberField(int i) {
            this.mField = i;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            if (i < 100) {
                FastDatePrinter.appendDigits(appendable, i);
            } else {
                FastDatePrinter.appendFullDigits(appendable, i, 2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TwoDigitYearField implements NumberRule {
        static final TwoDigitYearField INSTANCE = new TwoDigitYearField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitYearField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(1) % 100);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            FastDatePrinter.appendDigits(appendable, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TwoDigitMonthField implements NumberRule {
        static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitMonthField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            FastDatePrinter.appendDigits(appendable, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TwelveHourField implements NumberRule {
        private final NumberRule mRule;

        TwelveHourField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int i = calendar.get(10);
            if (i == 0) {
                i = calendar.getLeastMaximum(10) + 1;
            }
            this.mRule.appendTo(appendable, i);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int i) throws IOException {
            this.mRule.appendTo(appendable, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TwentyFourHourField implements NumberRule {
        private final NumberRule mRule;

        TwentyFourHourField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int i = calendar.get(11);
            if (i == 0) {
                i = calendar.getMaximum(11) + 1;
            }
            this.mRule.appendTo(appendable, i);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int i) throws IOException {
            this.mRule.appendTo(appendable, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class DayInWeekField implements NumberRule {
        private final NumberRule mRule;

        DayInWeekField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int i = calendar.get(7);
            this.mRule.appendTo(appendable, i != 1 ? i - 1 : 7);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int i) throws IOException {
            this.mRule.appendTo(appendable, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class WeekYear implements NumberRule {
        private final NumberRule mRule;

        WeekYear(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            this.mRule.appendTo(appendable, calendar.getWeekYear());
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int i) throws IOException {
            this.mRule.appendTo(appendable, i);
        }
    }

    static String getTimeZoneDisplay(TimeZone timeZone, boolean z, int i, Locale locale) {
        TimeZoneDisplayKey timeZoneDisplayKey = new TimeZoneDisplayKey(timeZone, z, i, locale);
        String str = cTimeZoneDisplayCache.get(timeZoneDisplayKey);
        if (str == null) {
            String displayName = timeZone.getDisplayName(z, i, locale);
            String putIfAbsent = cTimeZoneDisplayCache.putIfAbsent(timeZoneDisplayKey, displayName);
            return putIfAbsent != null ? putIfAbsent : displayName;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TimeZoneNameRule implements Rule {
        private final String mDaylight;
        private final Locale mLocale;
        private final String mStandard;
        private final int mStyle;

        TimeZoneNameRule(TimeZone timeZone, Locale locale, int i) {
            this.mLocale = locale;
            this.mStyle = i;
            this.mStandard = FastDatePrinter.getTimeZoneDisplay(timeZone, false, i, locale);
            this.mDaylight = FastDatePrinter.getTimeZoneDisplay(timeZone, true, i, locale);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return Math.max(this.mStandard.length(), this.mDaylight.length());
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            TimeZone timeZone = calendar.getTimeZone();
            if (calendar.get(16) != 0) {
                appendable.append(FastDatePrinter.getTimeZoneDisplay(timeZone, true, this.mStyle, this.mLocale));
            } else {
                appendable.append(FastDatePrinter.getTimeZoneDisplay(timeZone, false, this.mStyle, this.mLocale));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TimeZoneNumberRule implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
        static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
        final boolean mColon;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 5;
        }

        TimeZoneNumberRule(boolean z) {
            this.mColon = z;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int i = calendar.get(15) + calendar.get(16);
            if (i < 0) {
                appendable.append('-');
                i = -i;
            } else {
                appendable.append('+');
            }
            int i2 = i / 3600000;
            FastDatePrinter.appendDigits(appendable, i2);
            if (this.mColon) {
                appendable.append(':');
            }
            FastDatePrinter.appendDigits(appendable, (i / 60000) - (i2 * 60));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Iso8601_Rule implements Rule {
        final int length;
        static final Iso8601_Rule ISO8601_HOURS = new Iso8601_Rule(3);
        static final Iso8601_Rule ISO8601_HOURS_MINUTES = new Iso8601_Rule(5);
        static final Iso8601_Rule ISO8601_HOURS_COLON_MINUTES = new Iso8601_Rule(6);

        static Iso8601_Rule getRule(int i) {
            switch (i) {
                case 1:
                    return ISO8601_HOURS;
                case 2:
                    return ISO8601_HOURS_MINUTES;
                case 3:
                    return ISO8601_HOURS_COLON_MINUTES;
                default:
                    throw new IllegalArgumentException("invalid number of X");
            }
        }

        Iso8601_Rule(int i) {
            this.length = i;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.length;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int i = calendar.get(15) + calendar.get(16);
            if (i == 0) {
                appendable.append("Z");
                return;
            }
            if (i < 0) {
                appendable.append('-');
                i = -i;
            } else {
                appendable.append('+');
            }
            int i2 = i / 3600000;
            FastDatePrinter.appendDigits(appendable, i2);
            int i3 = this.length;
            if (i3 < 5) {
                return;
            }
            if (i3 == 6) {
                appendable.append(':');
            }
            FastDatePrinter.appendDigits(appendable, (i / 60000) - (i2 * 60));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TimeZoneDisplayKey {
        private final Locale mLocale;
        private final int mStyle;
        private final TimeZone mTimeZone;

        TimeZoneDisplayKey(TimeZone timeZone, boolean z, int i, Locale locale) {
            this.mTimeZone = timeZone;
            if (z) {
                this.mStyle = Integer.MIN_VALUE | i;
            } else {
                this.mStyle = i;
            }
            this.mLocale = locale;
        }

        public int hashCode() {
            return (((this.mStyle * 31) + this.mLocale.hashCode()) * 31) + this.mTimeZone.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof TimeZoneDisplayKey) {
                TimeZoneDisplayKey timeZoneDisplayKey = (TimeZoneDisplayKey) obj;
                return this.mTimeZone.equals(timeZoneDisplayKey.mTimeZone) && this.mStyle == timeZoneDisplayKey.mStyle && this.mLocale.equals(timeZoneDisplayKey.mLocale);
            }
            return false;
        }
    }
}

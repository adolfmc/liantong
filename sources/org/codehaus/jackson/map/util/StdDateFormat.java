package org.codehaus.jackson.map.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.codehaus.jackson.p467io.NumberInput;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StdDateFormat extends DateFormat {
    protected static final String[] ALL_FORMATS = {"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"};
    protected static final DateFormat DATE_FORMAT_ISO8601;
    protected static final DateFormat DATE_FORMAT_ISO8601_Z;
    protected static final DateFormat DATE_FORMAT_PLAIN;
    protected static final DateFormat DATE_FORMAT_RFC1123;
    protected static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    protected static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final StdDateFormat instance;
    protected transient DateFormat _formatISO8601;
    protected transient DateFormat _formatISO8601_z;
    protected transient DateFormat _formatPlain;
    protected transient DateFormat _formatRFC1123;

    static {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        DATE_FORMAT_RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        DATE_FORMAT_RFC1123.setTimeZone(timeZone);
        DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DATE_FORMAT_ISO8601.setTimeZone(timeZone);
        DATE_FORMAT_ISO8601_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DATE_FORMAT_ISO8601_Z.setTimeZone(timeZone);
        DATE_FORMAT_PLAIN = new SimpleDateFormat("yyyy-MM-dd");
        DATE_FORMAT_PLAIN.setTimeZone(timeZone);
        instance = new StdDateFormat();
    }

    @Override // java.text.DateFormat, java.text.Format
    public StdDateFormat clone() {
        return new StdDateFormat();
    }

    public static DateFormat getBlueprintISO8601Format() {
        return DATE_FORMAT_ISO8601;
    }

    public static DateFormat getISO8601Format(TimeZone timeZone) {
        DateFormat dateFormat = (DateFormat) DATE_FORMAT_ISO8601.clone();
        dateFormat.setTimeZone(timeZone);
        return dateFormat;
    }

    public static DateFormat getBlueprintRFC1123Format() {
        return DATE_FORMAT_RFC1123;
    }

    public static DateFormat getRFC1123Format(TimeZone timeZone) {
        DateFormat dateFormat = (DateFormat) DATE_FORMAT_RFC1123.clone();
        dateFormat.setTimeZone(timeZone);
        return dateFormat;
    }

    @Override // java.text.DateFormat
    public Date parse(String str) throws ParseException {
        String[] strArr;
        String trim = str.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = parse(trim, parsePosition);
        if (parse != null) {
            return parse;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : ALL_FORMATS) {
            if (sb.length() > 0) {
                sb.append("\", \"");
            } else {
                sb.append('\"');
            }
            sb.append(str2);
        }
        sb.append('\"');
        throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", trim, sb.toString()), parsePosition.getErrorIndex());
    }

    @Override // java.text.DateFormat
    public Date parse(String str, ParsePosition parsePosition) {
        char charAt;
        if (looksLikeISO8601(str)) {
            return parseAsISO8601(str, parsePosition);
        }
        int length = str.length();
        do {
            length--;
            if (length < 0 || (charAt = str.charAt(length)) < '0') {
                break;
            }
        } while (charAt <= '9');
        if (length < 0 && NumberInput.inLongRange(str, false)) {
            return new Date(Long.parseLong(str));
        }
        return parseAsRFC1123(str, parsePosition);
    }

    @Override // java.text.DateFormat
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (this._formatISO8601 == null) {
            this._formatISO8601 = (DateFormat) DATE_FORMAT_ISO8601.clone();
        }
        return this._formatISO8601.format(date, stringBuffer, fieldPosition);
    }

    protected boolean looksLikeISO8601(String str) {
        return str.length() >= 5 && Character.isDigit(str.charAt(0)) && Character.isDigit(str.charAt(3)) && str.charAt(4) == '-';
    }

    protected Date parseAsISO8601(String str, ParsePosition parsePosition) {
        DateFormat dateFormat;
        int length = str.length();
        int i = length - 1;
        char charAt = str.charAt(i);
        if (length <= 10 && Character.isDigit(charAt)) {
            dateFormat = this._formatPlain;
            if (dateFormat == null) {
                dateFormat = (DateFormat) DATE_FORMAT_PLAIN.clone();
                this._formatPlain = dateFormat;
            }
        } else if (charAt == 'Z') {
            DateFormat dateFormat2 = this._formatISO8601_z;
            if (dateFormat2 == null) {
                dateFormat2 = (DateFormat) DATE_FORMAT_ISO8601_Z.clone();
                this._formatISO8601_z = dateFormat2;
            }
            if (str.charAt(length - 4) == ':') {
                StringBuilder sb = new StringBuilder(str);
                sb.insert(i, ".000");
                str = sb.toString();
                dateFormat = dateFormat2;
            } else {
                dateFormat = dateFormat2;
            }
        } else if (hasTimeZone(str)) {
            int i2 = length - 3;
            char charAt2 = str.charAt(i2);
            if (charAt2 == ':') {
                StringBuilder sb2 = new StringBuilder(str);
                sb2.delete(i2, length - 2);
                str = sb2.toString();
            } else if (charAt2 == '+' || charAt2 == '-') {
                str = str + "00";
            }
            int length2 = str.length();
            if (Character.isDigit(str.charAt(length2 - 9))) {
                StringBuilder sb3 = new StringBuilder(str);
                sb3.insert(length2 - 5, ".000");
                str = sb3.toString();
            }
            dateFormat = this._formatISO8601;
            if (dateFormat == null) {
                dateFormat = (DateFormat) DATE_FORMAT_ISO8601.clone();
                this._formatISO8601 = dateFormat;
            }
        } else {
            StringBuilder sb4 = new StringBuilder(str);
            if ((length - str.lastIndexOf(84)) - 1 <= 8) {
                sb4.append(".000");
            }
            sb4.append('Z');
            str = sb4.toString();
            dateFormat = this._formatISO8601_z;
            if (dateFormat == null) {
                dateFormat = (DateFormat) DATE_FORMAT_ISO8601_Z.clone();
                this._formatISO8601_z = dateFormat;
            }
        }
        return dateFormat.parse(str, parsePosition);
    }

    protected Date parseAsRFC1123(String str, ParsePosition parsePosition) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = (DateFormat) DATE_FORMAT_RFC1123.clone();
        }
        return this._formatRFC1123.parse(str, parsePosition);
    }

    private static final boolean hasTimeZone(String str) {
        char charAt;
        char charAt2;
        int length = str.length();
        if (length >= 6) {
            char charAt3 = str.charAt(length - 6);
            return charAt3 == '+' || charAt3 == '-' || (charAt = str.charAt(length + (-5))) == '+' || charAt == '-' || (charAt2 = str.charAt(length + (-3))) == '+' || charAt2 == '-';
        }
        return false;
    }
}

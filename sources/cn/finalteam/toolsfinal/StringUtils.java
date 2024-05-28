package cn.finalteam.toolsfinal;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StringUtils {
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0) && isBlank(str);
    }

    public static int length(CharSequence charSequence) {
        if (charSequence == null) {
            return 0;
        }
        return charSequence.length();
    }

    public static String nullStrToEmpty(Object obj) {
        return obj == null ? "" : obj instanceof String ? (String) obj : obj.toString();
    }

    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char charAt = str.charAt(0);
        if (!Character.isLetter(charAt) || Character.isUpperCase(charAt)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(Character.toUpperCase(charAt));
        sb.append(str.substring(1));
        return sb.toString();
    }

    public static String utf8Encode(String str) {
        if (isEmpty(str) || str.getBytes().length == str.length()) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
        }
    }

    public static String utf8Encode(String str, String str2) {
        if (isEmpty(str) || str.getBytes().length == str.length()) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str2;
        }
    }

    public static String getHrefInnerHtml(String str) {
        if (isEmpty(str)) {
            return "";
        }
        Matcher matcher = Pattern.compile(".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*", 2).matcher(str);
        return matcher.matches() ? matcher.group(1) : str;
    }

    public static String htmlEscapeCharsToString(String str) {
        return isEmpty(str) ? str : str.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    public static String fullWidthToHalfWidth(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 12288) {
                charArray[i] = ' ';
            } else if (charArray[i] >= 65281 && charArray[i] <= 65374) {
                charArray[i] = (char) (charArray[i] - 65248);
            } else {
                charArray[i] = charArray[i];
            }
        }
        return new String(charArray);
    }

    public static String halfWidthToFullWidth(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                charArray[i] = 12288;
            } else if (charArray[i] >= '!' && charArray[i] <= '~') {
                charArray[i] = (char) (charArray[i] + 65248);
            } else {
                charArray[i] = charArray[i];
            }
        }
        return new String(charArray);
    }

    public static String sqliteEscape(String str) {
        return str.replace("/", "//").replace("'", "''").replace("[", "/[").replace("]", "/]").replace("%", "/%").replace("&", "/&").replace("_", "/_").replace("(", "/(").replace(")", "/)");
    }
}

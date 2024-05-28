package com.chinaunicon.jtwifilib.core.utils;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JtStrUtil {
    public static int parseInt(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static double parseDouble(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public static float parseFloat(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0f;
        }
        return Float.parseFloat(str);
    }

    public static long parseLong(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        return Long.parseLong(str);
    }

    public static void setText(TextView textView, String str) {
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        } else {
            textView.setText("");
        }
    }

    public static void setTextHtml(TextView textView, String str) {
        Spanned fromHtml;
        if (!TextUtils.isEmpty(str)) {
            if (Build.VERSION.SDK_INT >= 24) {
                fromHtml = Html.fromHtml(str, 0);
            } else {
                fromHtml = Html.fromHtml(str);
            }
            textView.setText(fromHtml);
            return;
        }
        textView.setText("");
    }

    public static void setText(TextView textView, SpannableString spannableString) {
        if (!TextUtils.isEmpty(spannableString)) {
            textView.setText(spannableString);
        } else {
            textView.setText("");
        }
    }

    public static void setText(TextView textView, Spanned spanned) {
        if (!TextUtils.isEmpty(spanned)) {
            textView.setText(spanned);
        } else {
            textView.setText("");
        }
    }

    public static void setText(TextView textView, int i, Context context) {
        if (textView == null || context == null) {
            return;
        }
        setText(textView, context.getString(i));
    }

    public static String getResString(int i, Context context) {
        return context == null ? "" : context.getString(i);
    }

    public static String parseEmpty(String str) {
        return ((str == null || "null".equals(str.trim())) ? "" : "").trim();
    }

    public static String format(String str) {
        if (str.lastIndexOf("\\u002") > -1) {
            if ("\\u0020".lastIndexOf("\\u002" + str.charAt(str.lastIndexOf("\\u002") + 5)) > -1) {
                System.out.println(str);
                return str;
            }
            String replace = str.replace("\\u002" + str.charAt(str.lastIndexOf("\\u002") + 5), "\\u0020" + str.charAt(str.lastIndexOf("\\u002") + 5));
            format(replace);
            return replace;
        }
        return str;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static int chineseLength(String str) {
        int i = 0;
        if (isEmpty(str)) {
            return 0;
        }
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i + 1;
            if (str.substring(i, i3).matches("[Α-￥]")) {
                i2 += 2;
            }
            i = i3;
        }
        return i2;
    }

    public static int strLength(String str) {
        int i = 0;
        if (isEmpty(str)) {
            return 0;
        }
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i + 1;
            i2 = str.substring(i, i3).matches("[Α-￥]") ? i2 + 2 : i2 + 1;
            i = i3;
        }
        return i2;
    }

    public static int subStringLength(String str, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            int i4 = i2 + 1;
            i3 = str.substring(i2, i4).matches("[Α-￥]") ? i3 + 2 : i3 + 1;
            if (i3 >= i) {
                return i2;
            }
            i2 = i4;
        }
        return 0;
    }

    public static Boolean isMobileNo(String str) {
        boolean z = false;
        try {
            return Boolean.valueOf(Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(str).matches());
        } catch (Exception unused) {
            return z;
        }
    }

    public static boolean isMobileNO(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("[1][358]\\d{9}");
    }

    public static Boolean isNumberLetter(String str) {
        if (!str.matches("^[A-Za-z0-9]+$")) {
            return false;
        }
        return true;
    }

    public static Boolean isNumber(String str) {
        if (!str.matches("^[0-9]+$")) {
            return false;
        }
        return true;
    }

    public static Boolean isEmail(String str) {
        if (!str.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
            return false;
        }
        return true;
    }

    public static Boolean isChinese(String str) {
        if (!isEmpty(str)) {
            boolean z = true;
            int i = 0;
            while (i < str.length()) {
                int i2 = i + 1;
                if (!str.substring(i, i2).matches("[Α-￥]")) {
                    z = false;
                }
                i = i2;
            }
            return z;
        }
        return true;
    }

    public static Boolean isContainChinese(String str) {
        int i = 0;
        boolean z = false;
        if (!isEmpty(str)) {
            while (i < str.length()) {
                int i2 = i + 1;
                if (str.substring(i, i2).matches("[Α-￥]")) {
                    z = true;
                }
                i = i2;
            }
        }
        return z;
    }

    public static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine + "\n");
            } catch (IOException unused) {
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
                throw th;
            }
        }
        if (sb.indexOf("\n") != -1 && sb.lastIndexOf("\n") == sb.length() - 1) {
            sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n") + 1);
        }
        try {
            inputStream.close();
        } catch (IOException unused3) {
            return sb.toString();
        }
    }

    public static String dateTimeFormat(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            if (isEmpty(str)) {
                return null;
            }
            String[] split = str.split(" ");
            if (split.length > 0) {
                for (String str2 : split) {
                    if (str2.indexOf("-") != -1) {
                        String[] split2 = str2.split("-");
                        for (int i = 0; i < split2.length; i++) {
                            sb.append(strFormat2(split2[i]));
                            if (i < split2.length - 1) {
                                sb.append("-");
                            }
                        }
                    } else if (str2.indexOf(":") != -1) {
                        sb.append(" ");
                        String[] split3 = str2.split(":");
                        for (int i2 = 0; i2 < split3.length; i2++) {
                            sb.append(strFormat2(split3[i2]));
                            if (i2 < split3.length - 1) {
                                sb.append(":");
                            }
                        }
                    }
                }
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String strFormat2(String str) {
        try {
            if (str.length() <= 1) {
                return "0" + str;
            }
            return str;
        } catch (Exception unused) {
            return str;
        }
    }

    public static String cutString(String str, int i) {
        return cutString(str, i, "");
    }

    public static String cutString(String str, int i, String str2) {
        if (strlen(str, "GBK") <= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char c = charArray[i2];
            stringBuffer.append(c);
            i3 = c > 256 ? i3 + 2 : i3 + 1;
            if (i3 < i) {
                i2++;
            } else if (str2 != null) {
                stringBuffer.append(str2);
            }
        }
        return stringBuffer.toString();
    }

    public static String cutStringFromChar(String str, String str2, int i) {
        int indexOf;
        int i2;
        return (isEmpty(str) || (indexOf = str.indexOf(str2)) == -1 || str.length() <= (i2 = indexOf + i)) ? "" : str.substring(i2);
    }

    public static int strlen(String str, String str2) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        try {
            return str.getBytes(str2).length;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String getSizeDesc(long j) {
        String str = "B";
        if (j >= 1024) {
            str = "K";
            j >>= 10;
            if (j >= 1024) {
                str = "M";
                j >>= 10;
                if (j >= 1024) {
                    str = "G";
                    j >>= 10;
                }
            }
        }
        return j + str;
    }

    public static long ip2int(String str) {
        String[] split = str.replace(".", ",").split(",");
        return (Long.valueOf(split[0]).longValue() << 24) | (Long.valueOf(split[1]).longValue() << 16) | (Long.valueOf(split[2]).longValue() << 8) | Long.valueOf(split[3]).longValue();
    }

    public static void main(String[] strArr) {
        System.out.println(dateTimeFormat("2012-3-2 12:2:20"));
    }
}

package com.froad.eid.unify.utils;

import android.text.TextUtils;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class StringUtil {
    private static final String TAG = "StringUtil";

    public static String byte2HexStr(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b);
            int length = hexString.length();
            if (length > 2) {
                hexString = hexString.substring(length - 2);
            } else if (length == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }

    public static String formatString(int i, String str) {
        String str2 = "";
        if (str != null && !"".equals(str)) {
            str2 = str.replaceAll(" ", "");
            if (str2.length() >= i) {
                StringBuffer stringBuffer = new StringBuffer();
                String substring = str2.substring(0, i);
                String substring2 = str2.substring(i);
                if (!"".equals(substring2)) {
                    substring2 = substring2.replaceAll("(.{4})", "$1 ");
                }
                stringBuffer.append(substring);
                stringBuffer.append(substring2);
                stringBuffer.insert(i, " ");
                str2 = stringBuffer.toString();
            }
        }
        return str2.trim();
    }

    public static Double getDoubleData(Object obj) {
        if (obj != null && isNotEmpty(obj.toString())) {
            return Double.valueOf(obj.toString());
        }
        return null;
    }

    public static Float getFloatData(Object obj) {
        if (obj != null && isNotEmpty(obj.toString())) {
            return Float.valueOf(obj.toString());
        }
        return null;
    }

    public static Integer getIntegerData(Object obj) {
        if (obj != null && isNotEmpty(obj.toString())) {
            return Integer.valueOf(obj.toString());
        }
        return null;
    }

    public static String getNotNullStr(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static byte[] hexString2ByteArray(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            return null;
        }
        byte[] bArr = new byte[length / 2];
        int i = 0;
        while (i < length) {
            int i2 = i + 2;
            bArr[i / 2] = (byte) Integer.parseInt(str.substring(i, i2), 16);
            i = i2;
        }
        return bArr;
    }

    public static boolean isCerID(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("^[0-9]{16}[0-9]{1,}[0-9xX]");
    }

    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }

    public static boolean isFloatType(String str, String str2) {
        if (isNotEmpty(str)) {
            return Pattern.compile(str2.equals("0+") ? "^\\d+(\\.\\d+)?$" : str2.equals("+") ? "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$" : str2.equals("-0") ? "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$" : str2.equals("-") ? "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$" : "^(-?\\d+)(\\.\\d+)?$").matcher(str).matches();
        }
        return false;
    }

    public static boolean isMobile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("^1\\d{10}$");
    }

    public static boolean isMobileSafe(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("^1[0-9]{2}[*]{4}\\d{4}$");
    }

    public static boolean isNotEmpty(String str) {
        return (str == null || "".equals(str.replaceAll(" ", "")) || "null".equalsIgnoreCase(str.replaceAll(" ", ""))) ? false : true;
    }

    public static boolean isNumeric(String str) {
        return str.matches("^[-+]?\\d+(\\.\\d+)?$");
    }

    public static boolean isNumeric(String str, int i) {
        String str2;
        if (isNotEmpty(str)) {
            Pattern pattern = null;
            if (i == 1) {
                str2 = "[0-9]*";
            } else if (i != -1) {
                if (i == 0) {
                    str2 = "-?[0-9]+.?[0-9]+";
                }
                return pattern.matcher(str).matches();
            } else {
                str2 = "^-?[0-9]+";
            }
            pattern = Pattern.compile(str2);
            return pattern.matcher(str).matches();
        }
        return false;
    }

    public static boolean isTrimEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String jointHandler(String str, String str2) {
        if (!isNotEmpty(str)) {
            str = "--";
        }
        return "<big><font color='#eb4f4f'>" + str + "</big></font><small>" + str2 + "</small>";
    }

    public static String string2HexStr(String str, String str2) {
        try {
            return byte2HexStr(str.getBytes(str2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String tirmALL(String str) {
        return isEmpty(str) ? "" : str.trim().replaceAll(" ", "");
    }

    public static String toNotNullString(String str) {
        return isNotEmpty(str) ? str : "";
    }

    public static boolean verifyCertNum(String str, int i) {
        TMKeyLog.m16310d("StringUtil", "verifyNum>>>s:" + str + ">>>t:" + i);
        if (str.equals("")) {
            return true;
        }
        String str2 = "";
        switch (i) {
            case 0:
                str2 = "[^0-9a-zA-Z*\\s]";
                break;
            case 1:
            case 7:
                str2 = "[^0-9a-zA-Z\\s]]";
                break;
            case 2:
            case 4:
                str2 = "[^0-9\\s]";
                break;
            case 3:
                str2 = "[^0-9a-zA-Z*]";
                break;
            case 5:
                str2 = "[^0-9xX\\s]";
                break;
            case 6:
                str2 = "[^0-9*\\s]";
                break;
        }
        if (Pattern.compile(str2).matcher(str).find()) {
            TMKeyLog.m16310d("StringUtil", "find true replaceAll");
            return false;
        }
        return true;
    }
}

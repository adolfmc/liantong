package cn.finalteam.toolsfinal;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class JsonFormatUtils {
    public static String formatJson(String str) {
        int length;
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        while (str.length() > 0) {
            String token = getToken(str);
            str = str.substring(token.length());
            arrayList.add(token.trim());
        }
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            int length2 = ((String) arrayList.get(i2)).getBytes().length;
            if (length2 > i && i2 < arrayList.size() - 1 && ((String) arrayList.get(i2 + 1)).equals(":")) {
                i = length2;
            }
        }
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        int i4 = 0;
        while (i3 < arrayList.size()) {
            String str2 = (String) arrayList.get(i3);
            if (str2.equals(",")) {
                sb.append(str2);
                doFill(sb, i4, "\t");
            } else if (str2.equals(":")) {
                sb.append(" ");
                sb.append(str2);
                sb.append(" ");
            } else if (str2.equals("{")) {
                int i5 = i3 + 1;
                if (((String) arrayList.get(i5)).equals("}")) {
                    sb.append("{ }");
                    i3 = i5;
                } else {
                    i4++;
                    sb.append(str2);
                    doFill(sb, i4, "\t");
                }
            } else if (str2.equals("}")) {
                i4--;
                doFill(sb, i4, "\t");
                sb.append(str2);
            } else if (str2.equals("[")) {
                int i6 = i3 + 1;
                if (((String) arrayList.get(i6)).equals("]")) {
                    sb.append("[ ]");
                    i3 = i6;
                } else {
                    i4++;
                    sb.append(str2);
                    doFill(sb, i4, "\t");
                }
            } else if (str2.equals("]")) {
                i4--;
                doFill(sb, i4, "\t");
                sb.append(str2);
            } else {
                sb.append(str2);
                if (i3 < arrayList.size() - 1 && ((String) arrayList.get(i3 + 1)).equals(":") && (length = i - str2.getBytes().length) > 0) {
                    for (int i7 = 0; i7 < length; i7++) {
                        sb.append(" ");
                    }
                }
            }
            i3++;
        }
        return sb.toString();
    }

    private static String getToken(String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        while (str.length() > 0) {
            String substring = str.substring(0, 1);
            str = str.substring(1);
            if (!z && (substring.equals(":") || substring.equals("{") || substring.equals("}") || substring.equals("[") || substring.equals("]") || substring.equals(","))) {
                if (sb.toString().trim().length() == 0) {
                    sb.append(substring);
                }
            } else if (substring.equals("\\")) {
                sb.append(substring);
                sb.append(str.substring(0, 1));
                str = str.substring(1);
            } else if (substring.equals("\"")) {
                sb.append(substring);
                if (z) {
                    break;
                }
                z = true;
            } else {
                sb.append(substring);
            }
        }
        return sb.toString();
    }

    private static void doFill(StringBuilder sb, int i, String str) {
        sb.append("\n");
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(str);
        }
    }
}

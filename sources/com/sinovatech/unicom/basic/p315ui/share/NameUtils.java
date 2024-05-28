package com.sinovatech.unicom.basic.p315ui.share;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.share.NameUtils */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NameUtils {
    public static final String EMPTY = "";
    public static final int FOUR = 4;
    public static final int ONE = 1;
    public static final int THREE = 3;
    public static final int TWO = 2;
    public static final int ZERO = 0;

    public static String left(String str, int i) {
        if (str == null) {
            return null;
        }
        return i < 0 ? "" : str.length() <= i ? str : str.substring(0, i);
    }

    public static String right(String str, int i) {
        if (str == null) {
            return null;
        }
        return i < 0 ? "" : str.length() <= i ? str : str.substring(str.length() - i);
    }

    public static String checkNameLength(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 1) {
            return str;
        }
        if (str.length() == 2) {
            return left(str, 1) + "*";
        } else if (str.length() == 3) {
            return left(str, 1) + "*" + right(str, 1);
        } else {
            return left(str, 1) + "**" + right(str, 1);
        }
    }
}

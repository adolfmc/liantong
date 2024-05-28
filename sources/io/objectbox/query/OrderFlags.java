package io.objectbox.query;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class OrderFlags {
    public static final int CASE_SENSITIVE = 2;
    public static final int DESCENDING = 1;
    public static final int NULLS_LAST = 8;
    public static final int NULLS_ZERO = 16;
    public static final int UNSIGNED = 4;
    public static final String[] names = {"DESCENDING", "CASE_SENSITIVE", "", "UNSIGNED", "", "", "", "NULLS_LAST", "", "", "", "", "", "", "", "NULLS_ZERO"};

    private OrderFlags() {
    }

    public static String name(int i) {
        return names[i - 1];
    }
}

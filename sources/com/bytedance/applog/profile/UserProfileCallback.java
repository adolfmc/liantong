package com.bytedance.applog.profile;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface UserProfileCallback {
    public static final int NET_ERROR = 1;
    public static final int NOT_SATISFY = 3;
    public static final int NO_NET = 0;
    public static final int REQUEST_LIMIT = 4;
    public static final int RESULT_ERROR = 2;

    void onFail(int i);

    void onSuccess();
}

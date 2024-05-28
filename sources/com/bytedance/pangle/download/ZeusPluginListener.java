package com.bytedance.pangle.download;

import android.support.annotation.Keep;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ZeusPluginListener {
    public static final int CODE_DOWNLOAD_FAILED = 13;
    public static final int CODE_DOWNLOAD_PROGRESS = 11;
    public static final int CODE_DOWNLOAD_START = 10;
    public static final int CODE_DOWNLOAD_SUCCESS = 12;
    public static final int CODE_INSTALL_FAILED = 22;
    public static final int CODE_INSTALL_START = 20;
    public static final int CODE_INSTALL_SUCCESS = 21;

    void onEvent(int i, String str);
}

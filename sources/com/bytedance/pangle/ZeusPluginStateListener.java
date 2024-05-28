package com.bytedance.pangle;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class ZeusPluginStateListener {
    public static final int EVENT_DOWNLOAD_FAILED = 4;
    public static final int EVENT_DOWNLOAD_PROGRESS = 2;
    public static final int EVENT_DOWNLOAD_START = 1;
    public static final int EVENT_DOWNLOAD_SUCCESS = 3;
    public static final int EVENT_INSTALL_FAILED = 7;
    public static final int EVENT_INSTALL_START = 5;
    public static final int EVENT_INSTALL_SUCCESS = 6;
    public static final int EVENT_LOAD_FAILED = 10;
    public static final int EVENT_LOAD_START = 8;
    public static final int EVENT_LOAD_SUCCESS = 9;
    public static final int EVENT_REQUEST_FINISH = 0;
    private static final Handler mHandler = new Handler(Looper.getMainLooper());

    @Deprecated
    public void onPluginStateChange(String str, int i, Object... objArr) {
    }

    public void onStateChangeOnCurThread(String str, int i, Object... objArr) {
    }

    public static void postStateChange(@Nullable final String str, final int i, final Object... objArr) {
        mHandler.post(new Runnable() { // from class: com.bytedance.pangle.ZeusPluginStateListener.1
            @Override // java.lang.Runnable
            public final void run() {
                List<ZeusPluginStateListener> list = C3865h.m16801a().f9217b;
                if (list == null || list.size() <= 0) {
                    return;
                }
                for (ZeusPluginStateListener zeusPluginStateListener : list) {
                    zeusPluginStateListener.onPluginStateChange(TextUtils.isEmpty(str) ? "UNKNOWN" : str, i, objArr);
                }
            }
        });
        List<ZeusPluginStateListener> list = C3865h.m16801a().f9217b;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (ZeusPluginStateListener zeusPluginStateListener : list) {
            zeusPluginStateListener.onStateChangeOnCurThread(TextUtils.isEmpty(str) ? "UNKNOWN" : str, i, objArr);
        }
    }
}

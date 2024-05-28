package com.huawei.hms.adapter.sysobs;

import android.os.Handler;
import android.os.Looper;
import com.huawei.hms.support.log.HMSLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ApkResolutionFailedManager {

    /* renamed from: c */
    private static final ApkResolutionFailedManager f10962c = new ApkResolutionFailedManager();

    /* renamed from: a */
    private final Handler f10963a = new Handler(Looper.getMainLooper());

    /* renamed from: b */
    private final Map<String, Runnable> f10964b = new HashMap(2);

    private ApkResolutionFailedManager() {
    }

    public static ApkResolutionFailedManager getInstance() {
        return f10962c;
    }

    public void postTask(String str, Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            HMSLog.m14112e("ApkResolutionFailedManager", "postTask is not in main thread");
            return;
        }
        this.f10964b.put(str, runnable);
        this.f10963a.postDelayed(runnable, 2000L);
    }

    public void removeTask(String str) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            HMSLog.m14112e("ApkResolutionFailedManager", "removeTask is not in main thread");
            return;
        }
        Runnable remove = this.f10964b.remove(str);
        if (remove == null) {
            HMSLog.m14112e("ApkResolutionFailedManager", "cancel runnable is null");
        } else {
            this.f10963a.removeCallbacks(remove);
        }
    }

    public void removeValueOnly(String str) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            HMSLog.m14112e("ApkResolutionFailedManager", "removeValueOnly is not in main thread");
        } else {
            this.f10964b.remove(str);
        }
    }
}

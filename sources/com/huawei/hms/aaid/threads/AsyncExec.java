package com.huawei.hms.aaid.threads;

import android.util.Log;
import com.huawei.hms.opendevice.AsyncThreadFactory;
import com.huawei.hms.opendevice.TaskWrapper;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AsyncExec {

    /* renamed from: a */
    private static final ThreadPoolExecutor f10888a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new AsyncThreadFactory("SeqIO"), new ThreadPoolExecutor.AbortPolicy());

    public static void submitSeqIO(Runnable runnable) {
        try {
            f10888a.execute(new TaskWrapper(runnable));
        } catch (Exception e) {
            Log.e("HmsPushThreads", "submit seq io task failed, Exception:" + e);
        }
    }
}

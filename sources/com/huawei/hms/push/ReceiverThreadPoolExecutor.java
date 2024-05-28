package com.huawei.hms.push;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.push.o */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ReceiverThreadPoolExecutor {

    /* renamed from: a */
    private static final Object f11667a = new Object();

    /* renamed from: b */
    private static ThreadPoolExecutor f11668b = new ThreadPoolExecutor(1, 50, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: a */
    public static ThreadPoolExecutor m14201a() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (f11667a) {
            threadPoolExecutor = f11668b;
        }
        return threadPoolExecutor;
    }
}

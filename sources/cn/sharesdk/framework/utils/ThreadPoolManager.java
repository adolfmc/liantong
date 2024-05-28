package cn.sharesdk.framework.utils;

import android.text.TextUtils;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ThreadPoolManager {

    /* renamed from: a */
    public static ThreadPoolExecutor f2976a = new ThreadPoolExecutor(3, 3, 3, TimeUnit.MINUTES, new LinkedBlockingQueue());

    static {
        f2976a.allowCoreThreadTimeOut(true);
    }

    /* renamed from: a */
    public static void m21678a(Runnable runnable) {
        f2976a.execute(runnable);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: ThreadPoolManager.java */
    /* renamed from: cn.sharesdk.framework.utils.j$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class AbstractRunnableC1783a implements Runnable {
        /* renamed from: a */
        public abstract void m21677a() throws Throwable;

        /* renamed from: a */
        public void m21676a(Throwable th) {
        }

        /* renamed from: b */
        public String m21675b() {
            return "";
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (!TextUtils.isEmpty(m21675b())) {
                    Thread.currentThread().setName(m21675b());
                }
                m21677a();
            } catch (Throwable th) {
                try {
                    m21676a(th);
                } catch (Throwable unused) {
                }
                SSDKLog.m21740b().m21732d(th);
            }
        }
    }
}

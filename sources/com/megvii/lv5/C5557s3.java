package com.megvii.lv5;

import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.megvii.lv5.C5541q3;
import com.megvii.lv5.InterfaceC5509m3;
import java.util.concurrent.BlockingQueue;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.s3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5557s3 extends Thread {

    /* renamed from: a */
    public final BlockingQueue<AbstractC5652x3<?>> f13273a;

    /* renamed from: b */
    public final InterfaceC5549r3 f13274b;

    /* renamed from: c */
    public final InterfaceC5509m3 f13275c;

    /* renamed from: d */
    public final InterfaceC5381a4 f13276d;

    /* renamed from: e */
    public volatile boolean f13277e = false;

    public C5557s3(BlockingQueue<AbstractC5652x3<?>> blockingQueue, InterfaceC5549r3 interfaceC5549r3, InterfaceC5509m3 interfaceC5509m3, InterfaceC5381a4 interfaceC5381a4) {
        this.f13273a = blockingQueue;
        this.f13274b = interfaceC5549r3;
        this.f13275c = interfaceC5509m3;
        this.f13276d = interfaceC5381a4;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        InterfaceC5509m3.C5510a c5510a;
        Process.setThreadPriority(10);
        while (true) {
            SystemClock.elapsedRealtime();
            try {
                AbstractC5652x3<?> take = this.f13273a.take();
                try {
                    take.m12899a("network-queue-take");
                    if (Build.VERSION.SDK_INT >= 14) {
                        TrafficStats.setThreadStatsTag(take.f13905d);
                    }
                    C5622u3 m12992a = ((C5617t4) this.f13274b).m12992a(take);
                    take.m12899a("network-http-complete");
                    if (m12992a.f13748d && take.f13910i) {
                        take.m12897c("not-modified");
                    } else {
                        C5668z3<?> mo12889a = take.mo12889a(m12992a);
                        take.m12899a("network-parse-complete");
                        if (take.f13909h && (c5510a = mo12889a.f13971b) != null) {
                            ((C5630v4) this.f13275c).m12951a(take.f13904c, c5510a);
                            take.m12899a("network-cache-written");
                        }
                        take.f13910i = true;
                        C5541q3 c5541q3 = (C5541q3) this.f13276d;
                        c5541q3.getClass();
                        take.f13910i = true;
                        take.m12899a("post-response");
                        c5541q3.f13194a.execute(new C5541q3.RunnableC5543b(c5541q3, take, mo12889a, null));
                    }
                } catch (C5416d4 e) {
                    SystemClock.elapsedRealtime();
                    take.getClass();
                    C5541q3 c5541q32 = (C5541q3) this.f13276d;
                    c5541q32.getClass();
                    take.m12899a("post-error");
                    c5541q32.f13194a.execute(new C5541q3.RunnableC5543b(c5541q32, take, new C5668z3(e), null));
                } catch (Exception e2) {
                    C5440f4.m13536a("Unhandled exception %s", e2.toString());
                    C5416d4 c5416d4 = new C5416d4(e2);
                    SystemClock.elapsedRealtime();
                    C5541q3 c5541q33 = (C5541q3) this.f13276d;
                    c5541q33.getClass();
                    take.m12899a("post-error");
                    c5541q33.f13194a.execute(new C5541q3.RunnableC5543b(c5541q33, take, new C5668z3(c5416d4), null));
                }
            } catch (InterruptedException unused) {
                if (this.f13277e) {
                    return;
                }
            }
        }
    }
}

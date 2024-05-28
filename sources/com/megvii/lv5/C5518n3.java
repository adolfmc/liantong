package com.megvii.lv5;

import android.os.Process;
import com.megvii.lv5.C5541q3;
import com.megvii.lv5.C5630v4;
import com.megvii.lv5.InterfaceC5509m3;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.n3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5518n3 extends Thread {

    /* renamed from: f */
    public static final boolean f13056f = C5440f4.f12600a;

    /* renamed from: a */
    public final BlockingQueue<AbstractC5652x3<?>> f13057a;

    /* renamed from: b */
    public final BlockingQueue<AbstractC5652x3<?>> f13058b;

    /* renamed from: c */
    public final InterfaceC5509m3 f13059c;

    /* renamed from: d */
    public final InterfaceC5381a4 f13060d;

    /* renamed from: e */
    public volatile boolean f13061e = false;

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.n3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5519a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ AbstractC5652x3 f13062a;

        public RunnableC5519a(AbstractC5652x3 abstractC5652x3) {
            this.f13062a = abstractC5652x3;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C5518n3.this.f13058b.put(this.f13062a);
            } catch (InterruptedException unused) {
            }
        }
    }

    public C5518n3(BlockingQueue<AbstractC5652x3<?>> blockingQueue, BlockingQueue<AbstractC5652x3<?>> blockingQueue2, InterfaceC5509m3 interfaceC5509m3, InterfaceC5381a4 interfaceC5381a4) {
        this.f13057a = blockingQueue;
        this.f13058b = blockingQueue2;
        this.f13059c = interfaceC5509m3;
        this.f13060d = interfaceC5381a4;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        long length;
        C5630v4.C5632b c5632b;
        AbstractC5652x3<?> take;
        InterfaceC5509m3.C5510a m12952a;
        if (f13056f) {
            C5440f4.m13535b("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        C5630v4 c5630v4 = (C5630v4) this.f13059c;
        synchronized (c5630v4) {
            if (c5630v4.f13771c.exists()) {
                File[] listFiles = c5630v4.f13771c.listFiles();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        try {
                            length = file.length();
                            c5632b = new C5630v4.C5632b(new BufferedInputStream(new FileInputStream(file)), length);
                        } catch (IOException unused) {
                            file.delete();
                        }
                        try {
                            C5630v4.C5631a m12946a = C5630v4.C5631a.m12946a(c5632b);
                            m12946a.f13773a = length;
                            c5630v4.m12950a(m12946a.f13774b, m12946a);
                            c5632b.close();
                        } catch (Throwable th) {
                            c5632b.close();
                            throw th;
                        }
                    }
                }
            } else if (!c5630v4.f13771c.mkdirs()) {
                C5440f4.m13536a("Unable to create cache dir %s", c5630v4.f13771c.getAbsolutePath());
            }
        }
        while (true) {
            try {
                take = this.f13057a.take();
                take.m12899a("cache-queue-take");
                m12952a = ((C5630v4) this.f13059c).m12952a(take.f13904c);
            } catch (InterruptedException unused2) {
                if (this.f13061e) {
                    return;
                }
            }
            if (m12952a == null) {
                take.m12899a("cache-miss");
            } else if (m12952a.f12891e < System.currentTimeMillis()) {
                take.m12899a("cache-hit-expired");
                take.f13912k = m12952a;
            } else {
                take.m12899a("cache-hit");
                C5668z3<?> mo12889a = take.mo12889a(new C5622u3(200, m12952a.f12887a, m12952a.f12893g, false, 0L));
                take.m12899a("cache-hit-parsed");
                if (!(m12952a.f12892f < System.currentTimeMillis())) {
                    C5541q3 c5541q3 = (C5541q3) this.f13060d;
                    c5541q3.getClass();
                    take.f13910i = true;
                    take.m12899a("post-response");
                    c5541q3.f13194a.execute(new C5541q3.RunnableC5543b(c5541q3, take, mo12889a, null));
                } else {
                    take.m12899a("cache-hit-refresh-needed");
                    take.f13912k = m12952a;
                    mo12889a.f13973d = true;
                    InterfaceC5381a4 interfaceC5381a4 = this.f13060d;
                    RunnableC5519a runnableC5519a = new RunnableC5519a(take);
                    C5541q3 c5541q32 = (C5541q3) interfaceC5381a4;
                    c5541q32.getClass();
                    take.f13910i = true;
                    take.m12899a("post-response");
                    c5541q32.f13194a.execute(new C5541q3.RunnableC5543b(c5541q32, take, mo12889a, runnableC5519a));
                }
            }
            this.f13058b.put(take);
        }
    }
}

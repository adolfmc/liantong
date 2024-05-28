package com.xiaomi.push;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* renamed from: com.xiaomi.push.af */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11139af {

    /* renamed from: a */
    private int f21464a;

    /* renamed from: a */
    private Handler f21465a;

    /* renamed from: a */
    private C11142a f21466a;

    /* renamed from: a */
    private volatile AbstractC11143b f21467a;

    /* renamed from: a */
    private volatile boolean f21468a;

    /* renamed from: b */
    private final boolean f21469b;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.af$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class AbstractC11143b {
        /* renamed from: a */
        public void m4911a() {
        }

        /* renamed from: b */
        public abstract void mo2611b();

        /* renamed from: c */
        public void mo2610c() {
        }
    }

    public C11139af() {
        this(false);
    }

    public C11139af(boolean z) {
        this(z, 0);
    }

    public C11139af(boolean z, int i) {
        this.f21465a = null;
        this.f21468a = false;
        this.f21464a = 0;
        this.f21465a = new Handler(Looper.getMainLooper()) { // from class: com.xiaomi.push.af.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                AbstractC11143b abstractC11143b = (AbstractC11143b) message.obj;
                if (message.what == 0) {
                    abstractC11143b.m4911a();
                } else if (message.what == 1) {
                    abstractC11143b.mo2610c();
                }
                super.handleMessage(message);
            }
        };
        this.f21469b = z;
        this.f21464a = i;
    }

    /* renamed from: a */
    public synchronized void m4920a(AbstractC11143b abstractC11143b) {
        if (this.f21466a == null) {
            this.f21466a = new C11142a();
            this.f21466a.setDaemon(this.f21469b);
            this.f21468a = false;
            this.f21466a.start();
        }
        this.f21466a.m4912a(abstractC11143b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void m4921a() {
        this.f21466a = null;
        this.f21468a = true;
    }

    /* renamed from: a */
    public void m4919a(final AbstractC11143b abstractC11143b, long j) {
        this.f21465a.postDelayed(new Runnable() { // from class: com.xiaomi.push.af.2
            @Override // java.lang.Runnable
            public void run() {
                C11139af.this.m4920a(abstractC11143b);
            }
        }, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.af$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11142a extends Thread {

        /* renamed from: a */
        private final LinkedBlockingQueue<AbstractC11143b> f21474a;

        public C11142a() {
            super("PackageProcessor");
            this.f21474a = new LinkedBlockingQueue<>();
        }

        /* renamed from: a */
        public void m4912a(AbstractC11143b abstractC11143b) {
            try {
                this.f21474a.add(abstractC11143b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* renamed from: a */
        private void m4913a(int i, AbstractC11143b abstractC11143b) {
            try {
                C11139af.this.f21465a.sendMessage(C11139af.this.f21465a.obtainMessage(i, abstractC11143b));
            } catch (Exception e) {
                AbstractC11049b.m5276a(e);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            long j = C11139af.this.f21464a > 0 ? C11139af.this.f21464a : Long.MAX_VALUE;
            while (!C11139af.this.f21468a) {
                try {
                    AbstractC11143b poll = this.f21474a.poll(j, TimeUnit.SECONDS);
                    C11139af.this.f21467a = poll;
                    if (poll == null) {
                        if (C11139af.this.f21464a > 0) {
                            C11139af.this.m4921a();
                        }
                    } else {
                        m4913a(0, poll);
                        poll.mo2611b();
                        m4913a(1, poll);
                    }
                } catch (InterruptedException e) {
                    AbstractC11049b.m5276a(e);
                }
            }
        }
    }
}

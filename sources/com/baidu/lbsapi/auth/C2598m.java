package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.lbsapi.auth.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2598m extends Thread {

    /* renamed from: a */
    Handler f4993a;

    /* renamed from: b */
    private Object f4994b;

    /* renamed from: c */
    private boolean f4995c;

    C2598m() {
        this.f4993a = null;
        this.f4994b = new Object();
        this.f4995c = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2598m(String str) {
        super(str);
        this.f4993a = null;
        this.f4994b = new Object();
        this.f4995c = false;
    }

    /* renamed from: a */
    public void m19642a() {
        if (C2583a.f4967a) {
            C2583a.m19676a("Looper thread quit()");
        }
        this.f4993a.getLooper().quit();
    }

    /* renamed from: b */
    public void m19641b() {
        synchronized (this.f4994b) {
            try {
                if (!this.f4995c) {
                    this.f4994b.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: c */
    public void m19640c() {
        synchronized (this.f4994b) {
            this.f4995c = true;
            this.f4994b.notifyAll();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.f4993a = new Handler();
        if (C2583a.f4967a) {
            C2583a.m19676a("new Handler() finish!!");
        }
        Looper.loop();
        if (C2583a.f4967a) {
            C2583a.m19676a("LooperThread run() thread id:" + String.valueOf(Thread.currentThread().getId()));
        }
    }
}

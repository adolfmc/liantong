package com.baidu.location.p137b;

import com.baidu.location.p137b.C2634e;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2643g extends TimerTask {

    /* renamed from: a */
    final /* synthetic */ Timer f5230a;

    /* renamed from: b */
    final /* synthetic */ C2634e.C2637c f5231b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2643g(C2634e.C2637c c2637c, Timer timer) {
        this.f5231b = c2637c;
        this.f5230a = timer;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        if (!this.f5231b.f5217d) {
            this.f5231b.m19478c();
        }
        this.f5230a.cancel();
        this.f5230a.purge();
    }
}

package com.megvii.lv5;

import android.os.Handler;
import com.megvii.lv5.C5668z3;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.q3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5541q3 implements InterfaceC5381a4 {

    /* renamed from: a */
    public final Executor f13194a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.q3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class ExecutorC5542a implements Executor {

        /* renamed from: a */
        public final /* synthetic */ Handler f13195a;

        public ExecutorC5542a(C5541q3 c5541q3, Handler handler) {
            this.f13195a = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f13195a.post(runnable);
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.q3$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5543b implements Runnable {

        /* renamed from: a */
        public final AbstractC5652x3 f13196a;

        /* renamed from: b */
        public final C5668z3 f13197b;

        /* renamed from: c */
        public final Runnable f13198c;

        public RunnableC5543b(C5541q3 c5541q3, AbstractC5652x3 abstractC5652x3, C5668z3 c5668z3, Runnable runnable) {
            this.f13196a = abstractC5652x3;
            this.f13197b = c5668z3;
            this.f13198c = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f13196a.getClass();
            C5668z3 c5668z3 = this.f13197b;
            C5416d4 c5416d4 = c5668z3.f13972c;
            if (c5416d4 == null) {
                this.f13196a.mo12872a((AbstractC5652x3) c5668z3.f13970a);
            } else {
                C5668z3.InterfaceC5669a interfaceC5669a = this.f13196a.f13906e;
                if (interfaceC5669a != null) {
                    interfaceC5669a.mo12874a(c5416d4);
                }
            }
            if (this.f13197b.f13973d) {
                this.f13196a.m12899a("intermediate-response");
            } else {
                this.f13196a.m12897c("done");
            }
            Runnable runnable = this.f13198c;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public C5541q3(Handler handler) {
        this.f13194a = new ExecutorC5542a(this, handler);
    }
}

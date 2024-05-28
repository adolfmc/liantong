package p472q0;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import p472q0.C13686k;

/* renamed from: q0.k */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13686k {

    /* renamed from: h */
    public static final Handler f27536h = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    public final AbstractC13679d f27537a;

    /* renamed from: b */
    public C13694p f27538b;

    /* renamed from: c */
    public final String f27539c;

    /* renamed from: d */
    public boolean f27540d;

    /* renamed from: e */
    public boolean f27541e;

    /* renamed from: f */
    public final RunnableC13687a f27542f;

    /* renamed from: g */
    public final RunnableC13688b f27543g;

    /* renamed from: q0.k$a */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class RunnableC13687a implements Runnable {
        public RunnableC13687a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ void m138a() {
            C13686k.this.m140a();
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i;
            WindowManager m134a = C13686k.this.f27538b.m134a();
            if (m134a == null) {
                return;
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.format = -3;
            layoutParams.flags = 152;
            C13686k c13686k = C13686k.this;
            layoutParams.packageName = c13686k.f27539c;
            AbstractC13679d abstractC13679d = c13686k.f27537a;
            layoutParams.gravity = abstractC13679d.f27519c;
            layoutParams.x = abstractC13679d.f27521e;
            layoutParams.y = abstractC13679d.f27522f;
            layoutParams.verticalMargin = abstractC13679d.f27524h;
            layoutParams.horizontalMargin = abstractC13679d.f27523g;
            abstractC13679d.getClass();
            layoutParams.windowAnimations = 16973828;
            C13686k c13686k2 = C13686k.this;
            if (c13686k2.f27541e) {
                layoutParams.type = Build.VERSION.SDK_INT >= 26 ? 2038 : 2003;
            }
            try {
                m134a.addView(c13686k2.f27537a.f27517a, layoutParams);
                Handler handler = C13686k.f27536h;
                Runnable runnable = new Runnable() { // from class: q0.-$$Lambda$k$a$VfAUHIhl7eGUEvWj2B0GOsyz8a4
                    @Override // java.lang.Runnable
                    public final void run() {
                        C13686k.RunnableC13687a.this.m138a();
                    }
                };
                AbstractC13679d abstractC13679d2 = C13686k.this.f27537a;
                if (abstractC13679d2.f27520d == 1) {
                    abstractC13679d2.getClass();
                    i = 3500;
                } else {
                    abstractC13679d2.getClass();
                    i = 2000;
                }
                handler.postDelayed(runnable, i);
                C13686k c13686k3 = C13686k.this;
                c13686k3.f27538b.m133a(c13686k3);
                C13686k.this.f27540d = true;
            } catch (WindowManager.BadTokenException | IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: q0.k$b */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class RunnableC13688b implements Runnable {
        public RunnableC13688b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            C13694p c13694p;
            WindowManager m134a;
            try {
                try {
                    m134a = C13686k.this.f27538b.m134a();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    c13694p = C13686k.this.f27538b;
                }
                if (m134a == null) {
                    return;
                }
                m134a.removeViewImmediate(C13686k.this.f27537a.f27517a);
                c13694p = C13686k.this.f27538b;
                c13694p.m132b();
                C13686k.this.f27540d = false;
            } finally {
                C13686k.this.f27538b.m132b();
                C13686k.this.f27540d = false;
            }
        }
    }

    public C13686k(Activity activity, AbstractC13679d abstractC13679d) {
        this((Context) activity, abstractC13679d);
        this.f27541e = false;
        this.f27538b = new C13694p(activity);
    }

    public C13686k(Application application, AbstractC13679d abstractC13679d) {
        this((Context) application, abstractC13679d);
        this.f27541e = true;
        this.f27538b = new C13694p(application);
    }

    public C13686k(Context context, AbstractC13679d abstractC13679d) {
        this.f27542f = new RunnableC13687a();
        this.f27543g = new RunnableC13688b();
        this.f27537a = abstractC13679d;
        this.f27539c = context.getPackageName();
    }

    /* renamed from: a */
    public final void m140a() {
        if (this.f27540d) {
            Handler handler = f27536h;
            handler.removeCallbacks(this.f27542f);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.f27543g.run();
                return;
            }
            handler.removeCallbacks(this.f27543g);
            handler.post(this.f27543g);
        }
    }

    /* renamed from: b */
    public final void m139b() {
        if (this.f27540d) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.f27542f.run();
            return;
        }
        Handler handler = f27536h;
        handler.removeCallbacks(this.f27542f);
        handler.post(this.f27542f);
    }
}

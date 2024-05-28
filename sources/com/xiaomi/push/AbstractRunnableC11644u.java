package com.xiaomi.push;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* renamed from: com.xiaomi.push.u */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractRunnableC11644u implements Runnable {

    /* renamed from: a */
    private Context f23804a;

    /* renamed from: a */
    private File f23805a;

    /* renamed from: a */
    private Runnable f23806a;

    /* renamed from: a */
    protected abstract void mo2277a(Context context);

    private AbstractRunnableC11644u(Context context, File file) {
        this.f23804a = context;
        this.f23805a = file;
    }

    /* renamed from: a */
    public static void m2278a(Context context, File file, final Runnable runnable) {
        new AbstractRunnableC11644u(context, file) { // from class: com.xiaomi.push.u.1
            @Override // com.xiaomi.push.AbstractRunnableC11644u
            /* renamed from: a */
            protected void mo2277a(Context context2) {
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        }.run();
    }

    @Override // java.lang.Runnable
    public final void run() {
        C11643t c11643t = null;
        try {
            try {
                if (this.f23805a == null) {
                    this.f23805a = new File(this.f23804a.getFilesDir(), "default_locker");
                }
                c11643t = C11643t.m2279a(this.f23804a, this.f23805a);
                if (this.f23806a != null) {
                    this.f23806a.run();
                }
                mo2277a(this.f23804a);
                if (c11643t == null) {
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (c11643t == null) {
                    return;
                }
            }
            c11643t.m2280a();
        } catch (Throwable th) {
            if (c11643t != null) {
                c11643t.m2280a();
            }
            throw th;
        }
    }
}

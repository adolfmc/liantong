package cn.sharesdk.framework.authorize;

import android.app.Activity;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.authorize.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AuthorizeParams {

    /* renamed from: a */
    private static volatile AuthorizeParams f2856a;

    /* renamed from: b */
    private Activity f2857b;

    /* renamed from: c */
    private boolean f2858c;

    /* renamed from: a */
    public boolean m21876a() {
        return this.f2858c;
    }

    /* renamed from: a */
    public void m21874a(boolean z) {
        this.f2858c = z;
    }

    /* renamed from: b */
    public Activity m21873b() {
        return this.f2857b;
    }

    /* renamed from: a */
    public void m21875a(Activity activity) {
        this.f2857b = activity;
    }

    /* renamed from: c */
    public static AuthorizeParams m21872c() {
        synchronized (AuthorizeParams.class) {
            if (f2856a == null) {
                synchronized (AuthorizeParams.class) {
                    if (f2856a == null) {
                        f2856a = new AuthorizeParams();
                    }
                }
            }
        }
        return f2856a;
    }
}

package com.vivo.push.p372e;

import android.content.Context;
import com.vivo.push.util.ContextDelegate;

/* renamed from: com.vivo.push.e.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class PushSecurityManager {

    /* renamed from: c */
    private static volatile PushSecurityManager f20964c;

    /* renamed from: a */
    private IRsaSecurity f20965a;

    /* renamed from: b */
    private Context f20966b;

    /* renamed from: a */
    public static synchronized PushSecurityManager m5714a() {
        PushSecurityManager pushSecurityManager;
        synchronized (PushSecurityManager.class) {
            if (f20964c == null) {
                f20964c = new PushSecurityManager();
            }
            pushSecurityManager = f20964c;
        }
        return pushSecurityManager;
    }

    private PushSecurityManager() {
    }

    /* renamed from: a */
    public final synchronized IRsaSecurity m5713a(Context context) {
        if (this.f20965a != null) {
            return this.f20965a;
        } else if (context == null) {
            return null;
        } else {
            if (this.f20965a == null) {
                this.f20966b = ContextDelegate.getContext(context.getApplicationContext());
                this.f20965a = new RsaSecurity(this.f20966b);
            }
            return this.f20965a;
        }
    }
}

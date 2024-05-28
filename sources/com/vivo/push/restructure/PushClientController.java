package com.vivo.push.restructure;

import android.content.Context;
import com.vivo.push.C10992z;
import com.vivo.push.ISubscribe;
import com.vivo.push.p369c.CoreConfigManager;
import com.vivo.push.restructure.p375a.p376a.ClientMessageNodeManager;
import com.vivo.push.restructure.p377b.IPushRely;
import com.vivo.push.restructure.p377b.IPushSystemRely;
import com.vivo.push.restructure.p377b.PushRelyImpl;
import com.vivo.push.restructure.p377b.PushSystemRelyImpl;
import com.vivo.push.restructure.p378c.IReport;
import com.vivo.push.restructure.p378c.ReportImpl;
import com.vivo.push.util.PushClientSdkAppSp;

/* renamed from: com.vivo.push.restructure.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class PushClientController {

    /* renamed from: a */
    private Context f21075a;

    /* renamed from: b */
    private IReport f21076b;

    /* renamed from: c */
    private ClientMessageNodeManager f21077c;

    /* renamed from: d */
    private IPushRely f21078d;

    /* renamed from: e */
    private IPushSystemRely f21079e;

    /* renamed from: f */
    private CoreConfigManager f21080f;

    /* renamed from: g */
    private ISubscribe f21081g;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: PushClientController.java */
    /* renamed from: com.vivo.push.restructure.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C10969a {

        /* renamed from: a */
        static PushClientController f21082a = new PushClientController((byte) 0);
    }

    /* synthetic */ PushClientController(byte b) {
        this();
    }

    private PushClientController() {
    }

    /* renamed from: a */
    public static PushClientController m5593a() {
        return C10969a.f21082a;
    }

    /* renamed from: a */
    public final synchronized void m5592a(Context context) {
        if (context == null) {
            return;
        }
        if (context != null) {
            this.f21075a = context;
        }
        this.f21078d = new PushRelyImpl(new PushClientSdkAppSp(context));
        this.f21076b = new ReportImpl(this.f21078d);
        this.f21077c = new ClientMessageNodeManager();
        this.f21079e = new PushSystemRelyImpl();
        this.f21080f = new CoreConfigManager(context);
        this.f21081g = new C10992z(this.f21080f, m5588e());
    }

    /* renamed from: b */
    public final synchronized Context m5591b() {
        return this.f21075a;
    }

    /* renamed from: c */
    public final IReport m5590c() {
        return this.f21076b;
    }

    /* renamed from: d */
    public final ClientMessageNodeManager m5589d() {
        return this.f21077c;
    }

    /* renamed from: e */
    public final synchronized IPushRely m5588e() {
        return this.f21078d;
    }

    /* renamed from: f */
    public final IPushSystemRely m5587f() {
        return this.f21079e;
    }

    /* renamed from: g */
    public final CoreConfigManager m5586g() {
        return this.f21080f;
    }

    /* renamed from: h */
    public final ISubscribe m5585h() {
        return this.f21081g;
    }
}

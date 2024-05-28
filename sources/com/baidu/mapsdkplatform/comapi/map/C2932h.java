package com.baidu.mapsdkplatform.comapi.map;

import android.content.Context;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapsdkplatform.comapi.commonutils.C2898b;
import com.baidu.mapsdkplatform.comapi.commonutils.p144a.C2892c;
import com.baidu.mapsdkplatform.comapi.map.C2928f;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2932h implements C2892c.InterfaceC2895c {

    /* renamed from: a */
    final /* synthetic */ Context f7303a;

    /* renamed from: b */
    final /* synthetic */ String f7304b;

    /* renamed from: c */
    final /* synthetic */ C2928f.InterfaceC2929a f7305c;

    /* renamed from: d */
    final /* synthetic */ String f7306d;

    /* renamed from: e */
    final /* synthetic */ String f7307e;

    /* renamed from: f */
    final /* synthetic */ C2928f f7308f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2932h(C2928f c2928f, Context context, String str, C2928f.InterfaceC2929a interfaceC2929a, String str2, String str3) {
        this.f7308f = c2928f;
        this.f7303a = context;
        this.f7304b = str;
        this.f7305c = interfaceC2929a;
        this.f7306d = str2;
        this.f7307e = str3;
    }

    @Override // com.baidu.mapsdkplatform.comapi.commonutils.p144a.C2892c.InterfaceC2895c
    /* renamed from: a */
    public void mo18237a() {
        C2928f.InterfaceC2929a interfaceC2929a = this.f7305c;
        if (interfaceC2929a != null) {
            interfaceC2929a.mo18241a(HttpClient.HttpStateError.INNER_ERROR.ordinal(), "loadStyleFile onFailed", null);
        }
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b.m18459a().m18457a("CustomMap loadStyleFile failed");
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.commonutils.p144a.C2892c.InterfaceC2895c
    /* renamed from: a */
    public void mo18236a(File file) {
        boolean m18260a;
        boolean m18246a;
        m18260a = this.f7308f.m18260a(this.f7303a, file, this.f7304b);
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b m18459a = C2898b.m18459a();
            m18459a.m18457a("CustomMap loadStyleFile success ret = " + m18260a);
        }
        if (!m18260a) {
            C2928f.InterfaceC2929a interfaceC2929a = this.f7305c;
            if (interfaceC2929a != null) {
                interfaceC2929a.mo18241a(HttpClient.HttpStateError.INNER_ERROR.ordinal(), "UnZipStyleFile onFailed", null);
                return;
            }
            return;
        }
        this.f7308f.m18257a(this.f7303a, this.f7304b, this.f7306d);
        if (this.f7305c != null) {
            m18246a = this.f7308f.m18246a(this.f7307e);
            this.f7305c.mo18239a(true, m18246a ? this.f7307e : null);
        }
    }
}

package com.baidu.mapsdkplatform.comapi.map;

import android.content.Context;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapsdkplatform.comapi.commonutils.C2898b;
import com.baidu.mapsdkplatform.comapi.map.C2928f;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2931g extends HttpClient.ProtoResultCallback {

    /* renamed from: a */
    final /* synthetic */ Context f7299a;

    /* renamed from: b */
    final /* synthetic */ String f7300b;

    /* renamed from: c */
    final /* synthetic */ C2928f.InterfaceC2929a f7301c;

    /* renamed from: d */
    final /* synthetic */ C2928f f7302d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2931g(C2928f c2928f, Context context, String str, C2928f.InterfaceC2929a interfaceC2929a) {
        this.f7302d = c2928f;
        this.f7299a = context;
        this.f7300b = str;
        this.f7301c = interfaceC2929a;
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onFailed(HttpClient.HttpStateError httpStateError) {
        String m18259a;
        boolean m18246a;
        m18259a = this.f7302d.m18259a(this.f7299a, this.f7300b);
        m18246a = this.f7302d.m18246a(m18259a);
        if (!m18246a) {
            m18259a = null;
        }
        C2928f.InterfaceC2929a interfaceC2929a = this.f7301c;
        if (interfaceC2929a != null) {
            interfaceC2929a.mo18241a(httpStateError.ordinal(), httpStateError.name(), m18259a);
        }
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b m18459a = C2898b.m18459a();
            m18459a.m18457a("CustomMap failed error = " + httpStateError.ordinal());
        }
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onSuccess(String str) {
        this.f7302d.m18243b(this.f7299a, str, this.f7300b, this.f7301c);
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b m18459a = C2898b.m18459a();
            m18459a.m18457a("CustomMap result = " + str);
        }
    }
}

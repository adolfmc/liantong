package com.baidu.platform.comapi;

import android.content.Context;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.mapsdkplatform.comapi.commonutils.C2898b;
import com.baidu.platform.comapi.p150c.C2985a;
import com.baidu.platform.comjni.engine.MessageProxy;
import com.baidu.platform.comjni.engine.NAEngine;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2975a {

    /* renamed from: a */
    static boolean f7513a;

    /* renamed from: b */
    private NAEngine f7514b;

    /* renamed from: a */
    public boolean m18082a() {
        f7513a = false;
        return true;
    }

    /* renamed from: a */
    public boolean m18081a(Context context) {
        f7513a = false;
        this.f7514b = new NAEngine();
        boolean m17673a = NAEngine.m17673a(context, null);
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b m18459a = C2898b.m18459a();
            m18459a.m18457a("initEngine isEngineSuccess = " + m17673a);
        }
        if (m17673a) {
            return m17673a;
        }
        C2985a.m18050a().m18049a("engine_init_failed");
        return false;
    }

    /* renamed from: b */
    public void m18080b() {
        if (f7513a) {
            m18082a();
        }
        MessageProxy.destroy();
        NAEngine.m17669b();
        if (this.f7514b != null) {
            this.f7514b = null;
        }
    }
}

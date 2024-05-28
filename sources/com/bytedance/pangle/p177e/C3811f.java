package com.bytedance.pangle.p177e;

import android.os.SystemClock;
import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p176d.C3793d;
import com.bytedance.pangle.p176d.C3794e;
import com.bytedance.pangle.util.C3950i;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.e.f */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3811f {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.e.f$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC3814a {
        /* renamed from: a */
        boolean mo16892a(String str, int i);
    }

    /* renamed from: a */
    public static void m16894a() {
        if ((C3950i.m16620d() || C3950i.m16618f() || C3950i.m16622b()) && C3793d.m16923a(Zeus.getAppApplication())) {
            C3794e.m16918a(new Runnable() { // from class: com.bytedance.pangle.e.f.1
                @Override // java.lang.Runnable
                public final void run() {
                    SystemClock.sleep(GlobalParam.getInstance().getDexOptDelayTime());
                    C3811f.m16893b();
                }
            });
        }
    }

    /* renamed from: b */
    public static synchronized void m16893b() {
        InterfaceC3814a interfaceC3814a;
        synchronized (C3811f.class) {
            Map<String, ?> all = C3805b.m16906a(Zeus.getAppApplication()).getAll();
            if (all.size() > 0) {
                for (Map.Entry<String, ?> entry : all.entrySet()) {
                    if (C3950i.m16618f()) {
                        interfaceC3814a = new C3808e();
                    } else if (C3950i.m16620d()) {
                        interfaceC3814a = new C3807d();
                    } else if (C3950i.m16622b()) {
                        interfaceC3814a = new C3806c();
                    } else {
                        interfaceC3814a = new InterfaceC3814a() { // from class: com.bytedance.pangle.e.f.2
                            @Override // com.bytedance.pangle.p177e.C3811f.InterfaceC3814a
                            /* renamed from: a */
                            public final boolean mo16892a(String str, int i) {
                                return true;
                            }
                        };
                    }
                    if (interfaceC3814a.mo16892a(entry.getKey(), ((Integer) entry.getValue()).intValue())) {
                        C3805b.m16906a(Zeus.getAppApplication()).edit().remove(entry.getKey()).apply();
                        ZeusLogger.m16792i("Zeus/load_pangle", "fullDex2oat:" + entry.getKey());
                    }
                }
            }
        }
    }
}

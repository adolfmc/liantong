package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.clientreport.manager.C11053a;
import com.xiaomi.push.C11134ae;

/* renamed from: com.xiaomi.push.bj */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11195bj extends C11134ae.AbstractRunnableC11137a {

    /* renamed from: a */
    private Context f21608a;

    @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
    /* renamed from: a */
    public String mo2289a() {
        return "100887";
    }

    public C11195bj(Context context) {
        this.f21608a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (m4722a()) {
                C11053a.m5261a(this.f21608a).m5243c();
                AbstractC11049b.m5270c(this.f21608a.getPackageName() + " perf begin upload");
            }
        } catch (Exception e) {
            AbstractC11049b.m5268d("fail to send perf data. " + e);
        }
    }

    /* renamed from: a */
    private boolean m4722a() {
        return C11053a.m5261a(this.f21608a).m5264a().isPerfUploadSwitchOpen();
    }
}

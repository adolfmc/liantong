package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.packet.impl.C2028d;
import com.alipay.sdk.util.C2048j;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.statistic.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class RunnableC2001b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f3601a;

    /* renamed from: b */
    final /* synthetic */ String f3602b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2001b(Context context, String str) {
        this.f3601a = context;
        this.f3602b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        C2028d c2028d = new C2028d();
        try {
            String m20684b = C2048j.m20684b(this.f3601a, "alipay_cashier_statistic_record", null);
            if (!TextUtils.isEmpty(m20684b) && c2028d.mo20793a(this.f3601a, m20684b) != null) {
                C2048j.m20685b(this.f3601a, "alipay_cashier_statistic_record");
            }
        } catch (Throwable unused) {
        }
        try {
            if (TextUtils.isEmpty(this.f3602b)) {
                return;
            }
            c2028d.mo20793a(this.f3601a, this.f3602b);
        } catch (IOException unused2) {
            C2048j.m20686a(this.f3601a, "alipay_cashier_statistic_record", this.f3602b);
        } catch (Throwable unused3) {
        }
    }
}

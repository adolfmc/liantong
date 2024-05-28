package com.unionpay;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.unionpay.p362a.C10738c;
import com.unionpay.p362a.C10739d;
import com.unionpay.utils.C10915b;
import com.unionpay.utils.C10922i;
import com.unionpay.utils.UPUtils;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unionpay.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10754d implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C10739d f20680a;

    /* renamed from: b */
    final /* synthetic */ Context f20681b;

    /* renamed from: c */
    final /* synthetic */ String f20682c;

    /* renamed from: d */
    final /* synthetic */ String f20683d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10754d(C10739d c10739d, Context context, String str, String str2) {
        this.f20680a = c10739d;
        this.f20681b = context;
        this.f20682c = str;
        this.f20683d = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context m5981q;
        try {
            C10739d c10739d = this.f20680a;
            m5981q = UPPayAssistEx.m5981q();
            C10738c c10738c = new C10738c(c10739d, C10915b.m5861a(m5981q));
            if (c10738c.m5964a() == 0) {
                String m5963b = c10738c.m5963b();
                if (this.f20681b == null || TextUtils.isEmpty(m5963b)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(m5963b);
                String m5834a = C10922i.m5834a(jSONObject, "sign");
                String m5834a2 = C10922i.m5834a(jSONObject, "configs");
                if (TextUtils.isEmpty(m5834a) || TextUtils.isEmpty(m5834a2)) {
                    return;
                }
                int i = 0;
                try {
                    i = Integer.parseInt(this.f20682c);
                } catch (Exception unused) {
                }
                String str = new String(Base64.decode(m5834a2, 2));
                String m5851b = C10915b.m5851b(UPUtils.m5868a(str + this.f20683d));
                String forConfig = UPUtils.forConfig(i, m5834a);
                if (TextUtils.isEmpty(forConfig) || !forConfig.equals(m5851b)) {
                    return;
                }
                UPUtils.m5869a(this.f20681b, m5963b, "scan_configs");
                UPUtils.m5869a(this.f20681b, this.f20682c, "scan_mode");
                UPUtils.m5869a(this.f20681b, this.f20683d, "scan_random");
            }
        } catch (Exception unused2) {
        }
    }
}

package com.bytedance.applog.picker;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.bytedance.applog.AbstractC3579f;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.C3591h;
import com.bytedance.applog.C3685s0;
import com.sdk.p285a.C6960d;
import org.json.JSONArray;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DomSender extends AbstractC3579f implements Handler.Callback {

    /* renamed from: f */
    public Context f8710f;

    /* renamed from: g */
    public String f8711g;

    /* renamed from: h */
    public String f8712h;

    /* renamed from: i */
    public String f8713i;

    /* renamed from: j */
    public int f8714j;

    /* renamed from: k */
    public int f8715k;

    /* renamed from: l */
    public JSONArray f8716l;

    /* renamed from: m */
    public String f8717m;

    /* renamed from: n */
    public Handler f8718n;

    /* renamed from: com.bytedance.applog.picker.DomSender$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3664a implements C3685s0.InterfaceC3686a {
        public C3664a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x00d2  */
        @Override // com.bytedance.applog.C3685s0.InterfaceC3686a
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void mo17113a(com.bytedance.applog.C3672q0 r18, java.util.List<com.bytedance.applog.C3565d1> r19, java.util.List<com.bytedance.applog.C3672q0> r20) {
            /*
                Method dump skipped, instructions count: 355
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.picker.DomSender.C3664a.mo17113a(com.bytedance.applog.q0, java.util.List, java.util.List):void");
        }
    }

    public DomSender(C3591h c3591h, String str) {
        super(c3591h);
        this.f8718n = new Handler(Looper.getMainLooper(), this);
        this.f8710f = c3591h.f8464c;
        this.f8711g = c3591h.f8469h.f8936d.optString("aid", "");
        this.f8712h = c3591h.f8469h.m17008f();
        String str2 = (String) AppLog.getHeaderValue("resolution", null);
        if (!TextUtils.isEmpty(str2)) {
            String[] split = str2.split("x");
            this.f8715k = Integer.valueOf(split[0]).intValue();
            this.f8714j = Integer.valueOf(split[1]).intValue();
        }
        this.f8713i = str;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: c */
    public boolean mo17162c() {
        new C3685s0().m17121a((C3685s0.InterfaceC3686a) new C3664a(), Looper.myLooper(), true);
        return true;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: d */
    public String mo17161d() {
        return C6960d.f18019d;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: e */
    public long[] mo17160e() {
        return new long[]{1000};
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: g */
    public boolean mo17159g() {
        return true;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: h */
    public long mo17158h() {
        return 1000L;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Toast.makeText(this.f8710f, (String) message.obj, 0).show();
        return true;
    }
}

package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.C11577az;
import com.xiaomi.push.service.C11579ba;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.gd */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11402gd {

    /* renamed from: a */
    private static volatile C11402gd f22426a;

    /* renamed from: a */
    private final Context f22427a;

    /* renamed from: a */
    private Map<String, InterfaceC11403ge> f22428a = new HashMap();

    /* renamed from: a */
    public static C11402gd m3687a(Context context) {
        if (context == null) {
            AbstractC11049b.m5268d("[TinyDataManager]:mContext is null, TinyDataManager.getInstance(Context) failed.");
            return null;
        }
        if (f22426a == null) {
            synchronized (C11402gd.class) {
                if (f22426a == null) {
                    f22426a = new C11402gd(context);
                }
            }
        }
        return f22426a;
    }

    private C11402gd(Context context) {
        this.f22427a = context;
    }

    /* renamed from: a */
    public void m3686a(InterfaceC11403ge interfaceC11403ge, String str) {
        if (interfaceC11403ge == null) {
            AbstractC11049b.m5268d("[TinyDataManager]: please do not add null mUploader to TinyDataManager.");
        } else if (TextUtils.isEmpty(str)) {
            AbstractC11049b.m5268d("[TinyDataManager]: can not add a provider from unkown resource.");
        } else {
            m3688a().put(str, interfaceC11403ge);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public InterfaceC11403ge m3689a() {
        InterfaceC11403ge interfaceC11403ge = this.f22428a.get("UPLOADER_PUSH_CHANNEL");
        if (interfaceC11403ge != null) {
            return interfaceC11403ge;
        }
        InterfaceC11403ge interfaceC11403ge2 = this.f22428a.get("UPLOADER_HTTP");
        if (interfaceC11403ge2 != null) {
            return interfaceC11403ge2;
        }
        return null;
    }

    /* renamed from: a */
    Map<String, InterfaceC11403ge> m3688a() {
        return this.f22428a;
    }

    /* renamed from: a */
    public boolean m3685a(C11408gj c11408gj, String str) {
        if (TextUtils.isEmpty(str)) {
            AbstractC11049b.m5282a("pkgName is null or empty, upload ClientUploadDataItem failed.");
            return false;
        } else if (C11577az.m2596a(c11408gj, false)) {
            return false;
        } else {
            if (TextUtils.isEmpty(c11408gj.m3651d())) {
                c11408gj.m3644f(C11577az.m2598a());
            }
            c11408gj.m3642g(str);
            C11579ba.m2584a(this.f22427a, c11408gj);
            return true;
        }
    }
}

package com.p319ss.android.downloadlib.exception;

import android.text.TextUtils;
import android.util.Log;
import com.p319ss.android.download.api.p323ox.InterfaceC9834mb;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.appdownloader.p340u.C10151h;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.exception.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9971b implements InterfaceC9834mb {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.exception.b$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class C9972mb {

        /* renamed from: mb */
        private static C9971b f19217mb = new C9971b();
    }

    /* renamed from: mb */
    public static C9971b m7285mb() {
        return C9972mb.f19217mb;
    }

    @Override // com.p319ss.android.download.api.p323ox.InterfaceC9834mb
    /* renamed from: mb */
    public void mo7282mb(Throwable th, String str) {
        m7280mb(true, th, str);
    }

    /* renamed from: mb */
    public void m7280mb(boolean z, Throwable th, String str) {
        if (m7279ox()) {
            return;
        }
        if (th == null) {
            th = new Throwable();
        }
        if (z) {
            m7277ox(th);
        }
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str)) {
            str = th.getMessage();
        }
        C10050jb.m7040mb(jSONObject, "msg", str);
        C10050jb.m7040mb(jSONObject, "stack", Log.getStackTraceString(th));
        C9940x.m7345ww().mo7341mb("service_ttdownloader", 1, jSONObject);
    }

    /* renamed from: mb */
    public void m7284mb(String str) {
        m7281mb(true, str);
    }

    /* renamed from: mb */
    public void m7281mb(boolean z, String str) {
        if (m7279ox()) {
            return;
        }
        if (z) {
            m7277ox(new RuntimeException(str));
        }
        JSONObject jSONObject = new JSONObject();
        C10050jb.m7040mb(jSONObject, "msg", str);
        C10050jb.m7040mb(jSONObject, "stack", m7283mb(new Throwable()));
        C9940x.m7345ww().mo7341mb("service_ttdownloader", 2, jSONObject);
    }

    /* renamed from: ox */
    public void m7278ox(String str) {
        m7276ox(true, str);
    }

    /* renamed from: ox */
    public void m7276ox(boolean z, String str) {
        if (m7279ox()) {
            return;
        }
        if (z) {
            m7277ox(new RuntimeException(str));
        }
        JSONObject jSONObject = new JSONObject();
        C10050jb.m7040mb(jSONObject, "msg", str);
        C10050jb.m7040mb(jSONObject, "stack", m7283mb(new Throwable()));
        C9940x.m7345ww().mo7341mb("service_ttdownloader", 3, jSONObject);
    }

    /* renamed from: ox */
    private void m7277ox(Throwable th) {
        if (C10151h.m6583ox(C9940x.getContext())) {
            throw new C9973mb(th);
        }
    }

    /* renamed from: mb */
    public static String m7283mb(Throwable th) {
        try {
            return Log.getStackTraceString(th);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: ox */
    private boolean m7279ox() {
        return C9940x.m7364lz().optInt("enable_monitor", 1) != 1;
    }
}

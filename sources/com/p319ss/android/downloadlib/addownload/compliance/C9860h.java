package com.p319ss.android.downloadlib.addownload.compliance;

import com.p319ss.android.downloadlib.addownload.model.C9916h;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.addownload.compliance.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C9860h {
    /* renamed from: mb */
    public static void m7670mb(String str, long j) {
        C9916h m7453h = C9923u.m7451mb().m7453h(j);
        if (m7453h.m7475on()) {
            return;
        }
        m7453h.f19100b.setRefer(str);
        AdEventHandler.m7315mb().m7294ox("lp_app_dialog_click", m7453h);
    }

    /* renamed from: ox */
    public static void m7667ox(String str, long j) {
        m7668mb(str, null, j);
    }

    /* renamed from: mb */
    public static void m7668mb(String str, JSONObject jSONObject, long j) {
        AdEventHandler.m7315mb().m7293ox(str, jSONObject, C9923u.m7451mb().m7453h(j));
    }

    /* renamed from: mb */
    public static void m7669mb(String str, C9916h c9916h) {
        AdEventHandler.m7315mb().m7294ox(str, c9916h);
    }

    /* renamed from: mb */
    public static void m7671mb(int i, C9916h c9916h) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("error_code", Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdEventHandler.m7315mb().m7293ox("lp_compliance_error", jSONObject, c9916h);
    }

    /* renamed from: mb */
    public static void m7672mb(int i, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("error_code", Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdEventHandler.m7315mb().m7293ox("lp_compliance_error", jSONObject, C9923u.m7451mb().m7453h(j));
    }
}

package com.p319ss.android.downloadlib.addownload.model;

import com.p319ss.android.downloadlib.utils.C10050jb;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.model.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9921mb {

    /* renamed from: b */
    public long f19114b;

    /* renamed from: h */
    public String f19115h;

    /* renamed from: hj */
    public String f19116hj;

    /* renamed from: ko */
    public String f19117ko;

    /* renamed from: mb */
    public long f19118mb;

    /* renamed from: ox */
    public long f19119ox;

    /* renamed from: u */
    public String f19120u;

    /* renamed from: ww */
    public volatile long f19121ww;

    public C9921mb() {
    }

    public C9921mb(long j, long j2, long j3, String str, String str2, String str3, String str4) {
        this.f19118mb = j;
        this.f19119ox = j2;
        this.f19114b = j3;
        this.f19116hj = str;
        this.f19115h = str2;
        this.f19120u = str3;
        this.f19117ko = str4;
    }

    /* renamed from: mb */
    public JSONObject m7459mb() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mDownloadId", this.f19118mb);
            jSONObject.put("mAdId", this.f19119ox);
            jSONObject.put("mExtValue", this.f19114b);
            jSONObject.put("mPackageName", this.f19116hj);
            jSONObject.put("mAppName", this.f19115h);
            jSONObject.put("mLogExtra", this.f19120u);
            jSONObject.put("mFileName", this.f19117ko);
            jSONObject.put("mTimeStamp", this.f19121ww);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: mb */
    public static C9921mb m7458mb(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        C9921mb c9921mb = new C9921mb();
        try {
            c9921mb.f19118mb = C10050jb.m7041mb(jSONObject, "mDownloadId");
            c9921mb.f19119ox = C10050jb.m7041mb(jSONObject, "mAdId");
            c9921mb.f19114b = C10050jb.m7041mb(jSONObject, "mExtValue");
            c9921mb.f19116hj = jSONObject.optString("mPackageName");
            c9921mb.f19115h = jSONObject.optString("mAppName");
            c9921mb.f19120u = jSONObject.optString("mLogExtra");
            c9921mb.f19117ko = jSONObject.optString("mFileName");
            c9921mb.f19121ww = C10050jb.m7041mb(jSONObject, "mTimeStamp");
            return c9921mb;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

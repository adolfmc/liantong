package com.p319ss.android.download.api.model;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p319ss.android.download.api.p320b.C9779ox;
import com.p319ss.android.downloadlib.addownload.C9940x;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.ss.android.download.api.model.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9832ox {

    /* renamed from: b */
    private final String f18806b;

    /* renamed from: h */
    private final long f18807h;

    /* renamed from: hj */
    private final boolean f18808hj;

    /* renamed from: io */
    private final JSONObject f18809io;

    /* renamed from: jb */
    private final int f18810jb;

    /* renamed from: je */
    private final Object f18811je;

    /* renamed from: ko */
    private final long f18812ko;

    /* renamed from: lc */
    private final String f18813lc;

    /* renamed from: lz */
    private final JSONObject f18814lz;

    /* renamed from: mb */
    private String f18815mb;

    /* renamed from: nk */
    private final String f18816nk;

    /* renamed from: o */
    private final boolean f18817o;

    /* renamed from: ox */
    private final String f18818ox;

    /* renamed from: u */
    private final String f18819u;

    /* renamed from: ww */
    private final JSONObject f18820ww;

    /* renamed from: x */
    private final List<String> f18821x;

    C9832ox(C9833mb c9833mb) {
        this.f18815mb = c9833mb.f18832mb;
        this.f18818ox = c9833mb.f18835ox;
        this.f18806b = c9833mb.f18822b;
        this.f18808hj = c9833mb.f18825hj;
        this.f18807h = c9833mb.f18824h;
        this.f18819u = c9833mb.f18836u;
        this.f18812ko = c9833mb.f18829ko;
        this.f18820ww = c9833mb.f18837ww;
        this.f18814lz = c9833mb.f18831lz;
        this.f18821x = c9833mb.f18827jb;
        this.f18810jb = c9833mb.f18828je;
        this.f18811je = c9833mb.f18833nk;
        this.f18817o = c9833mb.f18830lc;
        this.f18813lc = c9833mb.f18826io;
        this.f18809io = c9833mb.f18823e;
        this.f18816nk = c9833mb.f18834o;
    }

    @NBSInstrumented
    /* renamed from: com.ss.android.download.api.model.ox$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class C9833mb {

        /* renamed from: b */
        private String f18822b;

        /* renamed from: e */
        private JSONObject f18823e;

        /* renamed from: h */
        private long f18824h;

        /* renamed from: io */
        private String f18826io;

        /* renamed from: jb */
        private List<String> f18827jb;

        /* renamed from: je */
        private int f18828je;

        /* renamed from: ko */
        private long f18829ko;

        /* renamed from: lz */
        private JSONObject f18831lz;

        /* renamed from: mb */
        private String f18832mb;

        /* renamed from: nk */
        private Object f18833nk;

        /* renamed from: o */
        private String f18834o;

        /* renamed from: ox */
        private String f18835ox;

        /* renamed from: u */
        private String f18836u;

        /* renamed from: ww */
        private JSONObject f18837ww;

        /* renamed from: x */
        private Map<String, Object> f18838x;

        /* renamed from: hj */
        private boolean f18825hj = false;

        /* renamed from: lc */
        private boolean f18830lc = false;

        /* renamed from: mb */
        public C9833mb m7833mb(boolean z) {
            this.f18830lc = z;
            return this;
        }

        /* renamed from: mb */
        public C9833mb m7836mb(String str) {
            this.f18835ox = str;
            return this;
        }

        /* renamed from: ox */
        public C9833mb m7828ox(String str) {
            this.f18822b = str;
            return this;
        }

        /* renamed from: mb */
        public C9833mb m7839mb(long j) {
            this.f18824h = j;
            return this;
        }

        /* renamed from: ox */
        public C9833mb m7830ox(long j) {
            this.f18829ko = j;
            return this;
        }

        /* renamed from: b */
        public C9833mb m7851b(String str) {
            this.f18836u = str;
            return this;
        }

        /* renamed from: ox */
        public C9833mb m7826ox(boolean z) {
            this.f18825hj = z;
            return this;
        }

        /* renamed from: mb */
        public C9833mb m7834mb(JSONObject jSONObject) {
            this.f18837ww = jSONObject;
            return this;
        }

        /* renamed from: ox */
        public C9833mb m7827ox(JSONObject jSONObject) {
            this.f18831lz = jSONObject;
            return this;
        }

        /* renamed from: mb */
        public C9833mb m7835mb(List<String> list) {
            this.f18827jb = list;
            return this;
        }

        /* renamed from: mb */
        public C9833mb m7840mb(int i) {
            this.f18828je = i;
            return this;
        }

        /* renamed from: mb */
        public C9833mb m7837mb(Object obj) {
            this.f18833nk = obj;
            return this;
        }

        /* renamed from: hj */
        public C9833mb m7848hj(String str) {
            this.f18834o = str;
            return this;
        }

        /* renamed from: mb */
        public C9832ox m7841mb() {
            if (TextUtils.isEmpty(this.f18832mb)) {
                this.f18832mb = "umeng";
            }
            JSONObject jSONObject = new JSONObject();
            if (this.f18837ww == null) {
                this.f18837ww = new JSONObject();
            }
            try {
                if (this.f18838x != null && !this.f18838x.isEmpty()) {
                    for (Map.Entry<String, Object> entry : this.f18838x.entrySet()) {
                        if (!this.f18837ww.has(entry.getKey())) {
                            this.f18837ww.putOpt(entry.getKey(), entry.getValue());
                        }
                    }
                }
                if (this.f18830lc) {
                    this.f18826io = this.f18822b;
                    this.f18823e = new JSONObject();
                    if (this.f18825hj) {
                        JSONObject jSONObject2 = this.f18823e;
                        JSONObject jSONObject3 = this.f18837ww;
                        jSONObject2.put("ad_extra_data", !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3));
                    } else {
                        Iterator<String> keys = this.f18837ww.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            this.f18823e.put(next, this.f18837ww.get(next));
                        }
                    }
                    this.f18823e.put("category", this.f18832mb);
                    this.f18823e.put("tag", this.f18835ox);
                    this.f18823e.put("value", this.f18824h);
                    this.f18823e.put("ext_value", this.f18829ko);
                    if (!TextUtils.isEmpty(this.f18834o)) {
                        this.f18823e.put("refer", this.f18834o);
                    }
                    if (this.f18831lz != null) {
                        this.f18823e = C9779ox.m7965mb(this.f18831lz, this.f18823e);
                    }
                    if (this.f18825hj) {
                        if (!this.f18823e.has("log_extra") && !TextUtils.isEmpty(this.f18836u)) {
                            this.f18823e.put("log_extra", this.f18836u);
                        }
                        this.f18823e.put("is_ad_event", "1");
                    }
                }
                if (this.f18825hj) {
                    JSONObject jSONObject4 = this.f18837ww;
                    jSONObject.put("ad_extra_data", !(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : NBSJSONObjectInstrumentation.toString(jSONObject4));
                    if (!jSONObject.has("log_extra") && !TextUtils.isEmpty(this.f18836u)) {
                        jSONObject.put("log_extra", this.f18836u);
                    }
                    jSONObject.put("is_ad_event", "1");
                } else {
                    jSONObject.put("extra", this.f18837ww);
                }
                if (!TextUtils.isEmpty(this.f18834o)) {
                    jSONObject.putOpt("refer", this.f18834o);
                }
                if (this.f18831lz != null) {
                    jSONObject = C9779ox.m7965mb(this.f18831lz, jSONObject);
                }
                this.f18837ww = jSONObject;
            } catch (Exception e) {
                C9940x.m7363m().mo7282mb(e, "DownloadEventModel build");
            }
            return new C9832ox(this);
        }
    }

    /* renamed from: mb */
    public String m7859mb() {
        return this.f18815mb;
    }

    /* renamed from: ox */
    public String m7856ox() {
        return this.f18818ox;
    }

    /* renamed from: b */
    public String m7867b() {
        return this.f18806b;
    }

    /* renamed from: hj */
    public boolean m7865hj() {
        return this.f18808hj;
    }

    /* renamed from: h */
    public long m7866h() {
        return this.f18807h;
    }

    /* renamed from: u */
    public String m7855u() {
        return this.f18819u;
    }

    /* renamed from: ko */
    public long m7862ko() {
        return this.f18812ko;
    }

    /* renamed from: ww */
    public JSONObject m7854ww() {
        return this.f18820ww;
    }

    /* renamed from: lz */
    public JSONObject m7860lz() {
        return this.f18814lz;
    }

    /* renamed from: x */
    public List<String> m7853x() {
        return this.f18821x;
    }

    /* renamed from: jb */
    public int m7864jb() {
        return this.f18810jb;
    }

    /* renamed from: je */
    public Object m7863je() {
        return this.f18811je;
    }

    /* renamed from: nk */
    public boolean m7858nk() {
        return this.f18817o;
    }

    /* renamed from: o */
    public String m7857o() {
        return this.f18813lc;
    }

    /* renamed from: lc */
    public JSONObject m7861lc() {
        return this.f18809io;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("category: ");
        sb.append(this.f18815mb);
        sb.append("\ttag: ");
        sb.append(this.f18818ox);
        sb.append("\tlabel: ");
        sb.append(this.f18806b);
        sb.append("\nisAd: ");
        sb.append(this.f18808hj);
        sb.append("\tadId: ");
        sb.append(this.f18807h);
        sb.append("\tlogExtra: ");
        sb.append(this.f18819u);
        sb.append("\textValue: ");
        sb.append(this.f18812ko);
        sb.append("\nextJson: ");
        sb.append(this.f18820ww);
        sb.append("\nparamsJson: ");
        sb.append(this.f18814lz);
        sb.append("\nclickTrackUrl: ");
        List<String> list = this.f18821x;
        sb.append(list != null ? list.toString() : "");
        sb.append("\teventSource: ");
        sb.append(this.f18810jb);
        sb.append("\textraObject: ");
        Object obj = this.f18811je;
        sb.append(obj != null ? obj.toString() : "");
        sb.append("\nisV3: ");
        sb.append(this.f18817o);
        sb.append("\tV3EventName: ");
        sb.append(this.f18813lc);
        sb.append("\tV3EventParams: ");
        JSONObject jSONObject = this.f18809io;
        if (jSONObject != null) {
            str = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } else {
            str = "";
        }
        sb.append(str);
        return sb.toString();
    }
}

package com.bytedance.applog;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.o1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3654o1 extends C3687s1 {

    /* renamed from: n */
    public String f8667n;

    /* renamed from: o */
    public String f8668o;

    /* renamed from: p */
    public ArrayList<String> f8669p;

    /* renamed from: q */
    public ArrayList<String> f8670q;

    /* renamed from: r */
    public int f8671r;

    /* renamed from: s */
    public int f8672s;

    /* renamed from: t */
    public int f8673t;

    /* renamed from: u */
    public int f8674u;

    /* renamed from: v */
    public boolean f8675v;

    /* renamed from: w */
    public ArrayList<String> f8676w;

    public C3654o1(String str, String str2, int i, int i2, int i3, int i4, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        super("bav2b_click", true, null);
        this.f8667n = str;
        this.f8668o = str2;
        this.f8669p = arrayList;
        this.f8670q = arrayList2;
        this.f8671r = i;
        this.f8672s = i2;
        this.f8673t = i3;
        this.f8674u = i4;
    }

    @Override // com.bytedance.applog.C3687s1
    /* renamed from: i */
    public void mo17119i() {
        if (this.f8806k == null) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("element_path", this.f8668o);
            jSONObject.put("page_key", this.f8667n);
            ArrayList<String> arrayList = this.f8670q;
            if (arrayList != null && arrayList.size() > 0) {
                jSONObject.put("positions", new JSONArray((Collection) this.f8670q));
            }
            ArrayList<String> arrayList2 = this.f8669p;
            if (arrayList2 != null && arrayList2.size() > 0) {
                jSONObject.put("texts", new JSONArray((Collection) this.f8669p));
            }
            jSONObject.put("element_width", this.f8671r);
            jSONObject.put("element_height", this.f8672s);
            jSONObject.put("touch_x", this.f8673t);
            jSONObject.put("touch_y", this.f8674u);
            this.f8806k = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        }
    }

    public C3654o1(String str, String str2, int i, int i2, int i3, int i4, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        super("bav2b_click", true, null);
        this.f8667n = str;
        this.f8668o = str2;
        this.f8669p = arrayList;
        this.f8670q = arrayList2;
        this.f8671r = i;
        this.f8672s = i2;
        this.f8673t = i3;
        this.f8674u = i4;
        this.f8676w = arrayList3;
    }
}

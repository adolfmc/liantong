package com.bytedance.applog;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.q0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3672q0 extends C3654o1 {

    /* renamed from: A */
    public int f8766A;

    /* renamed from: B */
    public boolean f8767B;

    /* renamed from: C */
    public int[] f8768C;

    /* renamed from: D */
    public int f8769D;

    /* renamed from: E */
    public int f8770E;

    /* renamed from: F */
    public List<C3672q0> f8771F;

    /* renamed from: x */
    public int[] f8772x;

    /* renamed from: y */
    public int f8773y;

    /* renamed from: z */
    public int f8774z;

    public C3672q0(C3654o1 c3654o1) {
        super(c3654o1.f8667n, c3654o1.f8668o, c3654o1.f8671r, c3654o1.f8672s, c3654o1.f8673t, c3654o1.f8674u, c3654o1.f8669p, c3654o1.f8670q, c3654o1.f8676w);
        this.f8771F = new ArrayList();
        this.f8667n = c3654o1.f8667n;
        this.f8668o = c3654o1.f8668o;
        this.f8670q = c3654o1.f8670q;
        this.f8669p = c3654o1.f8669p;
    }

    /* renamed from: j */
    public JSONObject m17140j() {
        try {
            JSONObject jSONObject = new JSONObject();
            if (this.f8772x != null && this.f8772x.length > 1) {
                jSONObject.put("x", this.f8772x[0]);
                jSONObject.put("y", this.f8772x[1]);
            }
            jSONObject.put("width", this.f8773y);
            jSONObject.put("height", this.f8774z);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

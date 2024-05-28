package com.mob.commons.p229a;

import com.mob.MobSDK;
import com.mob.commons.C5855l;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.mob.commons.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5702b extends AbstractRunnableC5704c {
    public C5702b() {
        super(C5855l.m12238a("002(hghj"), 0L, C5855l.m12238a("005Xhghjgg?fl"), 86400L);
        m12757c();
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        m12777l();
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: b */
    protected void mo12712b() {
        m12774a(m12750j());
    }

    /* renamed from: l */
    private void m12777l() {
        C6152DH.requester(MobSDK.getContext()).getCarrier().getCarrierName().getCLoc().getMnbclfo().request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.b.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                int i;
                int intValue;
                int intValue2;
                int intValue3;
                int i2;
                int i3;
                int i4;
                int i5;
                int i6;
                try {
                    i = Integer.parseInt(dHResponse.getCarrier());
                } catch (Throwable unused) {
                    i = -1;
                }
                Object cLoc = dHResponse.getCLoc();
                if (cLoc == null) {
                    return;
                }
                if (C5855l.m12238a("016Gilfefh(f_il$hiiIhegf8efkMfkgfUg").equals(cLoc.getClass().getSimpleName())) {
                    int intValue4 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethodNoThrow(cLoc, C5855l.m12238a("022RggYhk_hk=f?hjShFglPkfk(fkgf gLhe fk0fkJkEfifeNh"), -1, new Object[0]), -1)).intValue();
                    int intValue5 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethodNoThrow(cLoc, C5855l.m12238a("023]gg hkZhk7fQhj1h_gl-kfk'fkgf%gUhegfYgCggfk*k2fife6h"), -1, new Object[0]), -1)).intValue();
                    i3 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethodNoThrow(cLoc, C5855l.m12238a("016Cgg hk4hk8f-hj@h*gl'kfkWfkgf,g$gkfe"), -1, new Object[0]), -1)).intValue();
                    i4 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethodNoThrow(cLoc, C5855l.m12238a("011]gg:hk7glfmhj7khJfhgkfe"), -1, new Object[0]), -1)).intValue();
                    i2 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethodNoThrow(cLoc, C5855l.m12238a("012Tgg+hk%gjXhkDhhgfflfngkfe"), -1, new Object[0]), -1)).intValue();
                    i5 = intValue4;
                    i6 = intValue5;
                    intValue3 = -1;
                    intValue = -1;
                    intValue2 = -1;
                } else {
                    intValue = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethodNoThrow(cLoc, C5855l.m12238a("006>ggJhk$imhj_e"), -1, new Object[0]), -1)).intValue();
                    intValue2 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethodNoThrow(cLoc, C5855l.m12238a("006.gg6hk[he,fe"), -1, new Object[0]), -1)).intValue();
                    intValue3 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethodNoThrow(cLoc, C5855l.m12238a("0066gg2hkQilfkfe"), -1, new Object[0]), -1)).intValue();
                    i2 = -1;
                    i3 = -1;
                    i4 = -1;
                    i5 = -1;
                    i6 = -1;
                }
                HashMap<String, Object> hashMap = null;
                if (i != -1 && intValue2 != -1 && intValue3 != -1) {
                    hashMap = new HashMap<>();
                    hashMap.put(C5855l.m12238a("003ife"), Integer.valueOf(intValue2));
                    hashMap.put(C5855l.m12238a("004ehii"), Integer.valueOf(intValue3));
                    if (intValue != -1) {
                        hashMap.put(C5855l.m12238a("003l)hjRe"), Integer.valueOf(intValue));
                    }
                }
                if (i != -1 && i3 != -1 && i4 != -1 && i2 != -1) {
                    if (hashMap == null) {
                        hashMap = new HashMap<>();
                    }
                    hashMap.put(C5855l.m12238a("003.hgfkfe"), Integer.valueOf(i3));
                    hashMap.put(C5855l.m12238a("003Shjfkfe"), Integer.valueOf(i4));
                    hashMap.put(C5855l.m12238a("003g$fkfe"), Integer.valueOf(i2));
                    if (i5 != -1) {
                        hashMap.put(C5855l.m12238a("003ifk"), Integer.valueOf(i5));
                    }
                    if (i6 != -1) {
                        hashMap.put(C5855l.m12238a("003i8gf<g"), Integer.valueOf(i6));
                    }
                }
                if (hashMap != null) {
                    hashMap.put(C5855l.m12238a("007ef8flflfkDh(fl"), Integer.valueOf(i));
                    hashMap.put(C5855l.m12238a("009DhjfkfhgfOlgfMfhLh"), dHResponse.getCarrierName());
                    ArrayList<HashMap<String, Object>> mnbclfo = dHResponse.getMnbclfo();
                    if (mnbclfo != null && mnbclfo.size() > 0) {
                        hashMap.put(C5855l.m12238a("006ghf-flhgfm"), mnbclfo);
                    }
                    C5702b.this.m12762a("BSIOMT", hashMap, true);
                }
            }
        });
    }
}

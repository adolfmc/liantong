package com.mob.commons.p229a;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5855l;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.C6219j;
import com.mob.tools.utils.Data;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import org.json.JSONObject;

/* renamed from: com.mob.commons.a.n */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5735n extends AbstractRunnableC5704c {
    public C5735n() {
        super(C5855l.m12238a("002?hhfk"), 0L, C5855l.m12238a("005_hhfkgg^fl"), 3600L);
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        m12657l();
        C6219j.m10995a().m10992a(getClass().getName(), new C6219j.InterfaceC6223a() { // from class: com.mob.commons.a.n.1
            @Override // com.mob.tools.utils.C6219j.InterfaceC6223a
            /* renamed from: a */
            public void mo10990a() {
                if (C5735n.this.mo12709e()) {
                    C5735n.this.m12657l();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public void m12657l() {
        C6152DH.requester(MobSDK.getContext()).getMwfo().getMwlfo().request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.n.2
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                HashMap<String, Object> hashMap = new HashMap<>();
                HashMap<String, Object> mwfo = dHResponse.getMwfo();
                if (mwfo == null) {
                    return;
                }
                String str = (String) mwfo.get("bsmt");
                String str2 = (String) mwfo.get("ssmt");
                if (!TextUtils.isEmpty(str)) {
                    ArrayList<HashMap<String, Object>> mwlfo = dHResponse.getMwlfo();
                    if (mwlfo != null && !mwlfo.isEmpty()) {
                        Iterator<HashMap<String, Object>> it = mwlfo.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            HashMap<String, Object> next = it.next();
                            Object obj = next.get(C5855l.m12238a("005$hkglglgkhm"));
                            if (obj != null && String.valueOf(obj).equals(str)) {
                                hashMap.putAll(next);
                                break;
                            }
                        }
                        hashMap.remove(C5855l.m12238a("005$hkglglgkhm"));
                        hashMap.remove(C5855l.m12238a("0041glglgkhm"));
                    }
                } else if (TextUtils.isEmpty(str2) || C5855l.m12238a("014Fkgfi5g[fn>gVgfhhSgBkhhjhjfkfeki").equalsIgnoreCase(str2)) {
                    return;
                }
                hashMap.putAll(mwfo);
                hashMap.put("ssmt", str2);
                hashMap.put("bsmt", str);
                C5735n.this.m12762a("WIMT", hashMap, true);
                TreeMap treeMap = new TreeMap();
                treeMap.put("ssmt", str2);
                treeMap.put("bsmt", str);
                JSONObject jSONObject = new JSONObject(treeMap);
                C5741aa.m12650a().m12641a(C5741aa.f14142i, Data.MD5(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)));
            }
        });
    }
}

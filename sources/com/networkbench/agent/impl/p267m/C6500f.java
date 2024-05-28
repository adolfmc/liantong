package com.networkbench.agent.impl.p267m;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.HarvestConnection;
import com.networkbench.agent.impl.harvest.p260a.EnumC6455q;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.m.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6500f extends AbstractC6497d {

    /* renamed from: f */
    private static C6499e f16462f;

    /* renamed from: g */
    private static C6500f f16463g;

    @Override // com.networkbench.agent.impl.p267m.AbstractC6497d
    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo9741a(HarvestConnection harvestConnection) {
        super.mo9741a(harvestConnection);
    }

    private C6500f() {
        this.f16449b = C6638h.m8963w().m9076K();
        if (this.f16449b == null) {
            return;
        }
        this.f16450c = "NBSUserProfile";
        this.f16452e = EnumC6455q.USER_PROFILE;
    }

    /* renamed from: a */
    public static C6500f m9742a() {
        if (f16463g == null) {
            f16463g = new C6500f();
        }
        return f16463g;
    }

    /* renamed from: a */
    public synchronized void m9740a(String str, String str2, Long l, String str3, String str4, Map<String, Object> map) {
        if (this.f16449b == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            f16448a.mo10115e("add user profile error!the userId is illegal!");
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10140b("add user profile error!the userId is illegal!", new Object[0]);
            }
            return;
        }
        if (map != null) {
            for (String str5 : map.keySet()) {
                if (!C6653u.m8696h(str5)) {
                    f16448a.mo10115e("add user profile error!the user properties is illegal!");
                    if (C6638h.m8963w().m8979n()) {
                        C6396h.m10140b("add user profile error!the user properties is illegal!", new Object[0]);
                    }
                    return;
                }
            }
            for (Object obj : map.values()) {
                if (!C6653u.m8727b(obj)) {
                    f16448a.mo10115e("add user profile error!the user properties is illegal!");
                    if (C6638h.m8963w().m8979n()) {
                        C6396h.m10140b("add user profile error!the user properties is illegal!", new Object[0]);
                    }
                    return;
                }
            }
        }
        f16462f = new C6499e(str, str2, l, str3, str4, map);
    }

    @Override // com.networkbench.agent.impl.p267m.AbstractC6497d
    /* renamed from: b */
    public void mo9739b() {
        mo9750e();
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        C6499e c6499e = f16462f;
        if (c6499e == null) {
            return jsonObject;
        }
        jsonObject.add("id", new JsonPrimitive(c6499e.m9748a()));
        jsonObject.add("name", new JsonPrimitive(f16462f.m9747b()));
        jsonObject.add("signupTime", new JsonPrimitive((Number) f16462f.m9746c()));
        jsonObject.add("province", new JsonPrimitive(f16462f.m9745d()));
        jsonObject.add("city", new JsonPrimitive(f16462f.m9744e()));
        jsonObject.add("properties", C6653u.m8735a(f16462f.m9743f()));
        return jsonObject;
    }

    @Override // com.networkbench.agent.impl.p267m.AbstractC6497d
    /* renamed from: d */
    protected synchronized void mo9737d() {
        f16462f = null;
    }

    @Override // com.networkbench.agent.impl.p267m.AbstractC6497d
    /* renamed from: c */
    protected boolean mo9738c() {
        return f16462f == null;
    }
}

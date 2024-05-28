package com.networkbench.agent.impl.p255g;

import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.com.google.gson.JsonObject;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.g.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6409b implements InterfaceC6403a {

    /* renamed from: b */
    private static final InterfaceC6393e f16191b = C6394f.m10150a();

    /* renamed from: a */
    protected HashMap<String, JsonObject> f16192a;

    /* renamed from: c */
    private EnumC6421f f16193c;

    /* renamed from: d */
    private long f16194d;

    public C6409b(EnumC6421f enumC6421f) {
        m10067a(enumC6421f);
        this.f16192a = new HashMap<>();
    }

    /* renamed from: a */
    void m10067a(EnumC6421f enumC6421f) {
        this.f16193c = enumC6421f;
    }

    /* renamed from: a */
    public void m10068a(long j) {
        this.f16194d = j;
    }

    @Override // com.networkbench.agent.impl.p255g.InterfaceC6403a
    /* renamed from: a */
    public EnumC6421f mo10069a() {
        return this.f16193c;
    }

    @Override // com.networkbench.agent.impl.p255g.InterfaceC6403a
    /* renamed from: b */
    public long mo10065b() {
        return this.f16194d;
    }

    public String toString() {
        return "BaseMeasurement{type=" + this.f16193c + "'', startTime=" + this.f16194d + '}';
    }

    /* renamed from: a */
    public void m10066a(HashMap<String, JsonObject> hashMap) {
        this.f16192a = hashMap;
    }
}

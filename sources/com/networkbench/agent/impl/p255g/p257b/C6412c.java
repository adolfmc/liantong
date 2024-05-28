package com.networkbench.agent.impl.p255g.p257b;

import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p239a.p240a.C6225a;
import com.networkbench.agent.impl.p239a.p240a.C6226b;
import com.networkbench.agent.impl.p255g.C6409b;
import com.networkbench.agent.impl.p255g.EnumC6421f;
import com.networkbench.agent.impl.util.C6642k;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.g.b.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6412c extends C6409b {

    /* renamed from: b */
    C6226b f16216b;

    /* renamed from: c */
    private C6410a f16217c;

    /* renamed from: d */
    private C6411b f16218d;

    /* renamed from: e */
    private C6225a f16219e;

    public C6412c(NBSTransactionState nBSTransactionState) {
        super(EnumC6421f.MixNetwork);
        this.f16216b = nBSTransactionState.getTransactionData();
        if (nBSTransactionState.isNetworkExist()) {
            this.f16219e = nBSTransactionState.getErrorData();
        }
        if (nBSTransactionState.getStatusCode() == 0) {
            nBSTransactionState.setStatusCode(-1);
        }
        m10032f();
    }

    /* renamed from: c */
    public boolean m10035c() {
        return C6642k.m8905d(this.f16216b.m10953r());
    }

    /* renamed from: d */
    public C6410a m10034d() {
        return this.f16217c;
    }

    /* renamed from: e */
    public C6411b m10033e() {
        return this.f16218d;
    }

    /* renamed from: f */
    private void m10032f() {
        C6225a c6225a = this.f16219e;
        if (c6225a != null) {
            this.f16218d = new C6411b(this.f16216b, c6225a.f15365a, this.f16219e.f15366b, this.f16219e.f15367c);
        }
        this.f16217c = new C6410a(this.f16216b);
        C6642k.m8910b(this.f16217c);
    }

    /* renamed from: a */
    public void m10036a(String str) {
        C6411b c6411b = this.f16218d;
        if (c6411b != null) {
            if (str == null) {
                str = "";
            }
            c6411b.m10052c(str);
        }
    }
}

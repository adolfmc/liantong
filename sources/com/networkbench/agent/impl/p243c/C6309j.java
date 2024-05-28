package com.networkbench.agent.impl.p243c;

import com.networkbench.agent.impl.socket.AbstractC6619p;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6309j extends AbstractC6619p {

    /* renamed from: n */
    private int f15835n;

    /* renamed from: j */
    private String f15832j = "";

    /* renamed from: i */
    private String f15831i = "";

    /* renamed from: l */
    private int f15833l = 0;

    /* renamed from: m */
    private int f15834m = 0;

    public C6309j() {
        this.f17042f = false;
        this.f17043g = false;
    }

    /* renamed from: a */
    public void m10498a(int i) {
        this.f15835n = i;
    }

    @Override // com.networkbench.agent.impl.socket.AbstractC6619p
    /* renamed from: a */
    public void mo9222a(boolean z) {
        this.f17042f = z;
    }

    /* renamed from: a */
    public boolean m10499a() {
        return this.f17042f;
    }

    @Override // com.networkbench.agent.impl.socket.AbstractC6619p
    /* renamed from: b */
    public void mo9221b(boolean z) {
        this.f17043g = z;
    }

    @Override // com.networkbench.agent.impl.socket.AbstractC6619p
    /* renamed from: h */
    public void mo9214h() {
        this.f17037a = 2;
        this.f17038b = this.f15832j + ":" + this.f15833l;
        if (!this.f15831i.isEmpty()) {
            this.f17038b = this.f15831i + "/" + this.f17038b;
        }
        this.f17039c = this.f15834m;
        this.f17040d = this.f15835n;
        this.f17041e = "";
    }

    /* renamed from: b */
    public boolean m10496b() {
        return this.f17043g;
    }

    /* renamed from: c */
    public String m10493c() {
        return this.f15831i;
    }

    /* renamed from: a */
    public void m10497a(String str) {
        this.f15831i = str;
    }

    /* renamed from: d */
    public String m10491d() {
        return this.f15832j;
    }

    /* renamed from: b */
    public void m10494b(String str) {
        this.f15832j = str;
    }

    /* renamed from: e */
    public int m10490e() {
        return this.f15833l;
    }

    /* renamed from: b */
    public void m10495b(int i) {
        this.f15833l = i;
    }

    /* renamed from: f */
    public int m10489f() {
        return this.f15834m;
    }

    /* renamed from: c */
    public void m10492c(int i) {
        this.f15834m = i;
    }

    @Override // com.networkbench.agent.impl.socket.AbstractC6619p
    public String toString() {
        return "hostname :" + this.f15831i + "  hostAddress:" + this.f15832j + "   port:" + this.f15833l + "   connectPeriod: " + this.f15834m;
    }
}

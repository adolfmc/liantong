package com.networkbench.agent.impl.p243c;

import com.networkbench.agent.impl.socket.AbstractC6619p;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6248a extends AbstractC6619p {

    /* renamed from: j */
    private int f15492j;

    /* renamed from: l */
    private double f15493l;

    /* renamed from: n */
    private int f15495n;

    /* renamed from: o */
    private int f15496o;

    /* renamed from: p */
    private int f15497p;

    /* renamed from: i */
    private String f15491i = "";

    /* renamed from: m */
    private String f15494m = "";

    /* renamed from: q */
    private String f15498q = "";

    /* renamed from: r */
    private String f15499r = "";

    /* renamed from: a */
    public void m10877a(String str) {
        this.f15499r = str;
    }

    /* renamed from: a */
    public String m10880a() {
        return this.f15491i;
    }

    /* renamed from: b */
    public void m10874b(String str) {
        this.f15491i = str;
    }

    /* renamed from: b */
    public int m10876b() {
        return this.f15492j;
    }

    /* renamed from: a */
    public void m10878a(int i) {
        this.f15492j = i;
    }

    /* renamed from: c */
    public String m10873c() {
        return this.f15494m;
    }

    /* renamed from: c */
    public void m10871c(String str) {
        this.f15494m = str;
    }

    /* renamed from: d */
    public int m10870d() {
        return this.f15495n;
    }

    /* renamed from: b */
    public void m10875b(int i) {
        this.f15495n = i;
    }

    /* renamed from: e */
    public int m10868e() {
        return this.f15496o;
    }

    /* renamed from: c */
    public void m10872c(int i) {
        this.f15496o = i;
    }

    /* renamed from: f */
    public int m10867f() {
        return this.f15497p;
    }

    /* renamed from: d */
    public void m10869d(int i) {
        this.f15497p = i;
    }

    /* renamed from: g */
    public String m10866g() {
        return this.f15498q;
    }

    @Override // com.networkbench.agent.impl.socket.AbstractC6619p
    /* renamed from: d */
    public void mo9219d(String str) {
        this.f15498q = str;
    }

    @Override // com.networkbench.agent.impl.socket.AbstractC6619p
    /* renamed from: h */
    public void mo9214h() {
        this.f17037a = 1;
        String str = this.f15491i;
        if (!this.f15499r.isEmpty()) {
            str = str + "/" + this.f15499r;
        }
        this.f17038b = str;
        this.f17039c = this.f15492j;
        this.f17040d = this.f15495n;
        this.f17041e = this.f15498q;
    }

    /* renamed from: i */
    public double m10865i() {
        return this.f15493l;
    }

    /* renamed from: a */
    public void m10879a(double d) {
        this.f15493l = d;
    }

    @Override // com.networkbench.agent.impl.socket.AbstractC6619p
    public String toString() {
        return "DnsEvent{host='" + this.f15491i + "', dnsConsumeTime=" + this.f15492j + ", beginTimeStamp=" + this.f15493l + ", destIpList='" + this.f15494m + "', isHttp=" + this.f17042f + ", errorNumber=" + this.f15495n + ", retValue=" + this.f15496o + ", port=" + this.f15497p + ", desc='" + this.f15498q + "'}";
    }
}

package com.gmrz.appsdk.attestation;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.appsdk.attestation.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class KeyDescription {

    /* renamed from: a */
    private KeyASecurityType f10255a;

    /* renamed from: b */
    private KeyASecurityType f10256b;

    /* renamed from: c */
    private AuthorizationList f10257c;

    /* renamed from: d */
    private AuthorizationList f10258d;

    /* renamed from: a */
    public KeyASecurityType m15863a() {
        return this.f10255a;
    }

    /* renamed from: a */
    public void m15860a(byte[] bArr) {
    }

    /* renamed from: b */
    public KeyASecurityType m15859b() {
        return this.f10256b;
    }

    /* renamed from: b */
    public void m15858b(int i) {
    }

    /* renamed from: c */
    public void m15855c(int i) {
        this.f10256b = KeyASecurityType.convert(i);
    }

    /* renamed from: d */
    public AuthorizationList m15854d() {
        return this.f10258d;
    }

    /* renamed from: d */
    public void m15853d(int i) {
    }

    /* renamed from: a */
    public void m15862a(int i) {
        this.f10255a = KeyASecurityType.convert(i);
    }

    /* renamed from: b */
    public void m15857b(AuthorizationList authorizationList) {
        this.f10258d = authorizationList;
    }

    /* renamed from: c */
    public AuthorizationList m15856c() {
        return this.f10257c;
    }

    /* renamed from: a */
    public void m15861a(AuthorizationList authorizationList) {
        this.f10257c = authorizationList;
    }
}

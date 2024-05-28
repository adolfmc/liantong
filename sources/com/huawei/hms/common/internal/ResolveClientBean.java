package com.huawei.hms.common.internal;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ResolveClientBean {

    /* renamed from: a */
    private final int f11164a;

    /* renamed from: b */
    private final AnyClient f11165b;

    /* renamed from: c */
    private int f11166c;

    public ResolveClientBean(AnyClient anyClient, int i) {
        this.f11165b = anyClient;
        this.f11164a = Objects.hashCode(anyClient);
        this.f11166c = i;
    }

    public void clientReconnect() {
        this.f11165b.connect(this.f11166c, true);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ResolveClientBean)) {
            return false;
        }
        return this.f11165b.equals(((ResolveClientBean) obj).f11165b);
    }

    public AnyClient getClient() {
        return this.f11165b;
    }

    public int hashCode() {
        return this.f11164a;
    }
}

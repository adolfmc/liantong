package com.huawei.hms.support.api.client;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class BundleResult {

    /* renamed from: a */
    private int f11717a;

    /* renamed from: b */
    private Bundle f11718b;

    public BundleResult(int i, Bundle bundle) {
        this.f11717a = i;
        this.f11718b = bundle;
    }

    public int getResultCode() {
        return this.f11717a;
    }

    public Bundle getRspBody() {
        return this.f11718b;
    }

    public void setResultCode(int i) {
        this.f11717a = i;
    }

    public void setRspBody(Bundle bundle) {
        this.f11718b = bundle;
    }
}

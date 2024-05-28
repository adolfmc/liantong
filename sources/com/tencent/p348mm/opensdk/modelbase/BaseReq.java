package com.tencent.p348mm.opensdk.modelbase;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.channel.p350a.C10372a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbase.BaseReq */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class BaseReq {
    public String openId;
    public String transaction;

    public abstract boolean checkArgs();

    public void fromBundle(Bundle bundle) {
        this.transaction = C10372a.m6226a(bundle, "_wxapi_basereq_transaction");
        this.openId = C10372a.m6226a(bundle, "_wxapi_basereq_openid");
    }

    public abstract int getType();

    public void toBundle(Bundle bundle) {
        bundle.putInt("_wxapi_command_type", getType());
        bundle.putString("_wxapi_basereq_transaction", this.transaction);
        bundle.putString("_wxapi_basereq_openid", this.openId);
    }
}

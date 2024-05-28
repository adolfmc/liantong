package com.tencent.p348mm.opensdk.modelpay;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelpay.PayResp */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PayResp extends BaseResp {
    public String extData;
    public String prepayId;
    public String returnKey;

    public PayResp() {
    }

    public PayResp(Bundle bundle) {
        fromBundle(bundle);
    }

    @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
    public boolean checkArgs() {
        return true;
    }

    @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.prepayId = bundle.getString("_wxapi_payresp_prepayid");
        this.returnKey = bundle.getString("_wxapi_payresp_returnkey");
        this.extData = bundle.getString("_wxapi_payresp_extdata");
    }

    @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
    public int getType() {
        return 5;
    }

    @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putString("_wxapi_payresp_prepayid", this.prepayId);
        bundle.putString("_wxapi_payresp_returnkey", this.returnKey);
        bundle.putString("_wxapi_payresp_extdata", this.extData);
    }
}

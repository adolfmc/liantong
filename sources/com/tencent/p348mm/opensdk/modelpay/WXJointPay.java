package com.tencent.p348mm.opensdk.modelpay;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelpay.WXJointPay */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXJointPay {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelpay.WXJointPay$JointPayReq */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class JointPayReq extends PayReq {
        @Override // com.tencent.p348mm.opensdk.modelpay.PayReq, com.tencent.p348mm.opensdk.modelbase.BaseReq
        public boolean checkArgs() {
            return super.checkArgs();
        }

        @Override // com.tencent.p348mm.opensdk.modelpay.PayReq, com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.opensdk.modelpay.PayReq, com.tencent.p348mm.opensdk.modelbase.BaseReq
        public int getType() {
            return 27;
        }

        @Override // com.tencent.p348mm.opensdk.modelpay.PayReq, com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelpay.WXJointPay$JointPayResp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class JointPayResp extends PayResp {
        public JointPayResp() {
        }

        public JointPayResp(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.opensdk.modelpay.PayResp, com.tencent.p348mm.opensdk.modelbase.BaseResp
        public boolean checkArgs() {
            return super.checkArgs();
        }

        @Override // com.tencent.p348mm.opensdk.modelpay.PayResp, com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.opensdk.modelpay.PayResp, com.tencent.p348mm.opensdk.modelbase.BaseResp
        public int getType() {
            return 27;
        }

        @Override // com.tencent.p348mm.opensdk.modelpay.PayResp, com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
        }
    }
}

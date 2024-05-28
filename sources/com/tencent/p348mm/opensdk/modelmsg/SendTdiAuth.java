package com.tencent.p348mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelmsg.SendAuth;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.SendTdiAuth */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SendTdiAuth {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelmsg.SendTdiAuth$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Resp extends SendAuth.Resp {
        private static final String KEY_AUTH_BUFFER = "_wxapi_sendauth_resp_tdi_buffer";
        private static final String TAG = "MicroMsg.SDK.SendTdiAuth.Resp";
        public byte[] tdiAuthBuffer;

        public Resp(Bundle bundle) {
            super(bundle);
        }

        @Override // com.tencent.p348mm.opensdk.modelmsg.SendAuth.Resp, com.tencent.p348mm.opensdk.modelbase.BaseResp
        public boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.p348mm.opensdk.modelmsg.SendAuth.Resp, com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.tdiAuthBuffer = bundle.getByteArray("_wxapi_sendauth_resp_tdi_buffer");
        }

        @Override // com.tencent.p348mm.opensdk.modelmsg.SendAuth.Resp, com.tencent.p348mm.opensdk.modelbase.BaseResp
        public int getType() {
            return 31;
        }

        @Override // com.tencent.p348mm.opensdk.modelmsg.SendAuth.Resp, com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putByteArray("_wxapi_sendauth_resp_tdi_buffer", this.tdiAuthBuffer);
        }
    }

    private SendTdiAuth() {
    }
}

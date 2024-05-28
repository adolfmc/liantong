package com.tencent.p348mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbiz.SubscribeMiniProgramMsg */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class SubscribeMiniProgramMsg {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.SubscribeMiniProgramMsg$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Req extends BaseReq {
        private static final int LENGTH_LIMIT = 1024;
        private static final String TAG = "MicroMsg.SDK.SubscribeMessage.Req";
        public String miniProgramAppId;

        public Req() {
        }

        public Req(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public boolean checkArgs() {
            String str = this.miniProgramAppId;
            if (str == null || str.length() == 0) {
                C10384Log.m6210e("MicroMsg.SDK.SubscribeMessage.Req", "checkArgs fail, miniProgramAppId is null");
                return false;
            }
            return true;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.miniProgramAppId = bundle.getString("_wxapi_subscribeminiprogram_req_miniprogramappid");
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public int getType() {
            return 23;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_subscribeminiprogram_req_miniprogramappid", this.miniProgramAppId);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelbiz.SubscribeMiniProgramMsg$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Resp extends BaseResp {
        private static final String TAG = "MicroMsg.SDK.SubscribeMessage.Resp";
        public String nickname;
        public String unionId;

        public Resp() {
        }

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.unionId = bundle.getString("_wxapi_subscribeminiprogram_resp_unionId");
            this.nickname = bundle.getString("_wxapi_subscribeminiprogram_resp_nickname");
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public int getType() {
            return 23;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_subscribeminiprogram_resp_unionId", this.unionId);
            bundle.putString("_wxapi_subscribeminiprogram_resp_nickname", this.nickname);
        }
    }

    private SubscribeMiniProgramMsg() {
    }
}

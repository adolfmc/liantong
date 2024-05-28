package com.tencent.p348mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelbase.BaseReq;
import com.tencent.p348mm.sdk.modelbase.BaseResp;
import com.tencent.p348mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelmsg.SendMessageToWX */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SendMessageToWX {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelmsg.SendMessageToWX$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Req extends BaseReq {
        private static final int FAV_CONTENT_LENGTH_LIMIT = 26214400;
        private static final String TAG = "MicroMsg.SDK.SendMessageToWX.Req";
        public static final int WXSceneFavorite = 2;
        public static final int WXSceneSession = 0;
        public static final int WXSceneTimeline = 1;
        public WXMediaMessage message;
        public int scene;

        public Req() {
        }

        public Req(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public boolean checkArgs() {
            WXMediaMessage wXMediaMessage = this.message;
            if (wXMediaMessage == null) {
                C10393b.m6195a("MicroMsg.SDK.SendMessageToWX.Req", "checkArgs fail ,message is null");
                return false;
            }
            if (wXMediaMessage.mediaObject.type() == 6 && this.scene == 2) {
                ((WXFileObject) this.message.mediaObject).setContentLengthLimit(26214400);
            }
            return this.message.checkArgs();
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.message = WXMediaMessage.Builder.fromBundle(bundle);
            this.scene = bundle.getInt("_wxapi_sendmessagetowx_req_scene");
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public int getType() {
            return 2;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putAll(WXMediaMessage.Builder.toBundle(this.message));
            bundle.putInt("_wxapi_sendmessagetowx_req_scene", this.scene);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelmsg.SendMessageToWX$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Resp extends BaseResp {
        public Resp() {
        }

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseResp
        public boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseResp
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseResp
        public int getType() {
            return 2;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
        }
    }

    private SendMessageToWX() {
    }
}

package com.tencent.p348mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;
import com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.SendMessageToWX */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SendMessageToWX {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelmsg.SendMessageToWX$IWXSceneDataObject */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface IWXSceneDataObject {
        boolean checkArgs();

        int getJumpType();

        void serialize(Bundle bundle);

        void unserialize(Bundle bundle);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelmsg.SendMessageToWX$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Req extends BaseReq {
        private static final int FAV_CONTENT_LENGTH_LIMIT = 26214400;
        private static final String SCENE_DATA_OBJECT_KEY_IDENTIFIER = "_scene_data_object_identifier";
        private static final String TAG = "MicroMsg.SDK.SendMessageToWX.Req";
        public static final int WXSceneFavorite = 2;
        public static final int WXSceneSession = 0;
        public static final int WXSceneSpecifiedContact = 3;
        public static final int WXSceneStatus = 4;
        public static final int WXSceneTimeline = 1;
        public WXMediaMessage message;
        public int scene;
        public IWXSceneDataObject sceneDataObject;
        public String userOpenId;

        public Req() {
        }

        public Req(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public boolean checkArgs() {
            String str;
            String str2;
            WXMediaMessage wXMediaMessage = this.message;
            if (wXMediaMessage == null) {
                str = "MicroMsg.SDK.SendMessageToWX.Req";
                str2 = "checkArgs fail ,message is null";
            } else {
                if (wXMediaMessage.mediaObject.type() == 6 && this.scene == 2) {
                    ((WXFileObject) this.message.mediaObject).setContentLengthLimit(26214400);
                }
                if (this.scene == 3 && this.userOpenId == null) {
                    str = "MicroMsg.SDK.SendMessageToWX.Req";
                    str2 = "Send specifiedContact userOpenId can not be null.";
                } else if (this.scene == 3 && this.openId == null) {
                    str = "MicroMsg.SDK.SendMessageToWX.Req";
                    str2 = "Send specifiedContact openid can not be null.";
                } else if (this.scene != 4) {
                    return this.message.checkArgs();
                } else {
                    if (this.sceneDataObject != null) {
                        return this.message.getType() == 1 ? this.sceneDataObject.checkArgs() : this.message.checkArgs() && this.sceneDataObject.checkArgs();
                    }
                    str = "MicroMsg.SDK.SendMessageToWX.Req";
                    str2 = "checkArgs fail, sceneDataObject is null";
                }
            }
            C10384Log.m6210e(str, str2);
            return false;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.message = WXMediaMessage.Builder.fromBundle(bundle);
            this.scene = bundle.getInt("_wxapi_sendmessagetowx_req_scene");
            this.userOpenId = bundle.getString("_wxapi_sendmessagetowx_req_use_open_id");
            String string = bundle.getString("_scene_data_object_identifier");
            if (string != null) {
                try {
                    IWXSceneDataObject iWXSceneDataObject = (IWXSceneDataObject) Class.forName(string).newInstance();
                    this.sceneDataObject = iWXSceneDataObject;
                    iWXSceneDataObject.unserialize(bundle);
                } catch (Exception e) {
                    C10384Log.m6210e("MicroMsg.SDK.SendMessageToWX.Req", "get WXSceneDataObject from bundle failed: unknown ident " + string + ", ex = " + e.getMessage());
                }
            }
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public int getType() {
            return 2;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putAll(WXMediaMessage.Builder.toBundle(this.message));
            bundle.putInt("_wxapi_sendmessagetowx_req_scene", this.scene);
            bundle.putInt("_wxapi_sendmessagetowx_req_media_type", this.message.getType());
            bundle.putString("_wxapi_sendmessagetowx_req_use_open_id", this.userOpenId);
            IWXSceneDataObject iWXSceneDataObject = this.sceneDataObject;
            if (iWXSceneDataObject != null) {
                bundle.putString("_scene_data_object_identifier", iWXSceneDataObject.getClass().getName());
                this.sceneDataObject.serialize(bundle);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelmsg.SendMessageToWX$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Resp extends BaseResp {
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
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public int getType() {
            return 2;
        }

        @Override // com.tencent.p348mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
        }
    }

    private SendMessageToWX() {
    }
}

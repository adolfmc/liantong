package com.tencent.p348mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.WXStateSceneDataObject */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXStateSceneDataObject implements SendMessageToWX.IWXSceneDataObject {
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXStateSceneDataObject";
    private static final String WX_STATE_JUMP_INFO_KEY_IDENTIFIER = "_wxapi_scene_data_state_jump_info_identifier";
    public String stateId;
    public IWXStateJumpInfo stateJumpInfo;
    public String stateTitle;
    public String token;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelmsg.WXStateSceneDataObject$IWXStateJumpInfo */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface IWXStateJumpInfo {
        public static final int WX_STATE_JUMP_TYPE_CHANNEL_PROFILE = 3;
        public static final int WX_STATE_JUMP_TYPE_MINI_PROGRAM = 2;
        public static final int WX_STATE_JUMP_TYPE_UNKNOWN = 0;
        public static final int WX_STATE_JUMP_TYPE_URL = 1;

        boolean checkArgs();

        void serialize(Bundle bundle);

        int type();

        void unserialize(Bundle bundle);
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.SendMessageToWX.IWXSceneDataObject
    public boolean checkArgs() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5 = this.stateId;
        if ((str5 == null || str5.length() <= 10240) && (((str = this.stateTitle) == null || str.length() <= 10240) && ((str2 = this.token) == null || str2.length() <= 10240))) {
            IWXStateJumpInfo iWXStateJumpInfo = this.stateJumpInfo;
            if (iWXStateJumpInfo != null) {
                return iWXStateJumpInfo.checkArgs();
            }
            str3 = "MicroMsg.SDK.WXStateSceneDataObject";
            str4 = "checkArgs fail, statsJumpInfo is null";
        } else {
            str3 = "MicroMsg.SDK.WXStateSceneDataObject";
            str4 = "checkArgs fail, stateId is invalid";
        }
        C10384Log.m6210e(str3, str4);
        return false;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.SendMessageToWX.IWXSceneDataObject
    public int getJumpType() {
        IWXStateJumpInfo iWXStateJumpInfo = this.stateJumpInfo;
        if (iWXStateJumpInfo != null) {
            return iWXStateJumpInfo.type();
        }
        return 0;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.SendMessageToWX.IWXSceneDataObject
    public void serialize(Bundle bundle) {
        bundle.putString("_wxapi_scene_data_state_id", this.stateId);
        bundle.putString("_wxapi_scene_data_state_title", this.stateTitle);
        bundle.putString("_wxapi_scene_data_state_token", this.token);
        IWXStateJumpInfo iWXStateJumpInfo = this.stateJumpInfo;
        if (iWXStateJumpInfo != null) {
            bundle.putString("_wxapi_scene_data_state_jump_info_identifier", iWXStateJumpInfo.getClass().getName());
            this.stateJumpInfo.serialize(bundle);
        }
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.SendMessageToWX.IWXSceneDataObject
    public void unserialize(Bundle bundle) {
        this.stateId = bundle.getString("_wxapi_scene_data_state_id");
        this.stateTitle = bundle.getString("_wxapi_scene_data_state_title");
        this.token = bundle.getString("_wxapi_scene_data_state_token");
        String string = bundle.getString("_wxapi_scene_data_state_jump_info_identifier");
        if (string != null) {
            try {
                IWXStateJumpInfo iWXStateJumpInfo = (IWXStateJumpInfo) Class.forName(string).newInstance();
                this.stateJumpInfo = iWXStateJumpInfo;
                iWXStateJumpInfo.unserialize(bundle);
            } catch (Exception e) {
                C10384Log.m6210e("MicroMsg.SDK.WXStateSceneDataObject", "get WXSceneDataObject from bundle failed: unknown ident " + string + ", ex = " + e.getMessage());
            }
        }
    }
}

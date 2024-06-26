package com.tencent.p348mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.WXStateJumpMiniProgramInfo */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXStateJumpMiniProgramInfo implements WXStateSceneDataObject.IWXStateJumpInfo {
    private static final String TAG = "MicroMsg.SDK.WXStateJumpUrlInfo";
    public int miniProgramType;
    public String path;
    public String username;

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public boolean checkArgs() {
        String str = this.username;
        if (str == null || str.length() <= 0) {
            C10384Log.m6210e("MicroMsg.SDK.WXStateJumpUrlInfo", "checkArgs fail, username is null");
            return false;
        }
        return true;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public void serialize(Bundle bundle) {
        bundle.putString("wx_state_jump_mini_program_username", this.username);
        bundle.putString("wx_state_jump_mini_program_path", this.path);
        bundle.putInt("wx_state_jump_mini_program_type", this.miniProgramType);
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public int type() {
        return 2;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public void unserialize(Bundle bundle) {
        this.username = bundle.getString("wx_state_jump_mini_program_username", "");
        this.path = bundle.getString("wx_state_jump_mini_program_path", "");
        this.miniProgramType = bundle.getInt("wx_state_jump_mini_program_type", 0);
    }
}

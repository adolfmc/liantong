package com.tencent.p348mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.WXStateJumpChannelProfileInfo */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXStateJumpChannelProfileInfo implements WXStateSceneDataObject.IWXStateJumpInfo {
    private static final String TAG = "MicroMsg.SDK.WXStateJumpUrlInfo";
    private static final int USERNAME_LENGTH_LIMIT = 1024;
    public String username;

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public boolean checkArgs() {
        String str;
        String str2;
        String str3 = this.username;
        if (str3 == null || str3.length() <= 0) {
            str = "MicroMsg.SDK.WXStateJumpUrlInfo";
            str2 = "checkArgs fail, username is null";
        } else if (this.username.length() < 1024) {
            return true;
        } else {
            str = "MicroMsg.SDK.WXStateJumpUrlInfo";
            str2 = "checkArgs fail, username length exceed limit";
        }
        C10384Log.m6210e(str, str2);
        return false;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public void serialize(Bundle bundle) {
        bundle.putString("wx_state_jump_channel_profile_username", this.username);
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public int type() {
        return 3;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public void unserialize(Bundle bundle) {
        this.username = bundle.getString("wx_state_jump_channel_profile_username", "");
    }
}

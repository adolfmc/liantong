package com.tencent.p348mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.WXStateJumpUrlInfo */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXStateJumpUrlInfo implements WXStateSceneDataObject.IWXStateJumpInfo {
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXStateJumpUrlInfo";
    public String jumpUrl;

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public boolean checkArgs() {
        String str;
        String str2;
        String str3 = this.jumpUrl;
        if (str3 == null || str3.length() <= 0) {
            str = "MicroMsg.SDK.WXStateJumpUrlInfo";
            str2 = "checkArgs fail, jumpUrl is null";
        } else if (this.jumpUrl.length() < 10240) {
            return true;
        } else {
            str = "MicroMsg.SDK.WXStateJumpUrlInfo";
            str2 = "checkArgs fail, jumpUrl is invalid";
        }
        C10384Log.m6210e(str, str2);
        return false;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public void serialize(Bundle bundle) {
        bundle.putString("wx_state_jump_url", this.jumpUrl);
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public int type() {
        return 1;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject.IWXStateJumpInfo
    public void unserialize(Bundle bundle) {
        this.jumpUrl = bundle.getString("wx_state_jump_url", "");
    }
}

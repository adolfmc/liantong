package com.tencent.p348mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbiz.WXChannelJumpUrlInfo */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXChannelJumpUrlInfo extends WXChannelBaseJumpInfo {
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXChannelJumpUrlInfo";
    public String url;

    @Override // com.tencent.p348mm.opensdk.modelbiz.WXChannelBaseJumpInfo, com.tencent.p348mm.opensdk.modelbiz.IWXChannelJumpInfo
    public boolean checkArgs() {
        String str;
        String str2;
        String str3 = this.url;
        if (str3 == null || str3.length() <= 0) {
            str = "MicroMsg.SDK.WXChannelJumpUrlInfo";
            str2 = "checkArgs fail, url is null";
        } else if (this.url.length() < 10240) {
            return true;
        } else {
            str = "MicroMsg.SDK.WXChannelJumpUrlInfo";
            str2 = "checkArgs fail, url is invalid";
        }
        C10384Log.m6210e(str, str2);
        return false;
    }

    @Override // com.tencent.p348mm.opensdk.modelbiz.WXChannelBaseJumpInfo, com.tencent.p348mm.opensdk.modelbiz.IWXChannelJumpInfo
    public void serialize(Bundle bundle) {
        super.serialize(bundle);
        bundle.putString("wx_channel_jump_url", this.url);
    }

    @Override // com.tencent.p348mm.opensdk.modelbiz.IWXChannelJumpInfo
    public int type() {
        return 2;
    }

    @Override // com.tencent.p348mm.opensdk.modelbiz.WXChannelBaseJumpInfo, com.tencent.p348mm.opensdk.modelbiz.IWXChannelJumpInfo
    public void unserialize(Bundle bundle) {
        super.unserialize(bundle);
        this.url = bundle.getString("wx_channel_jump_url");
    }
}

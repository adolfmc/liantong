package com.tencent.p348mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbiz.WXChannelJumpMiniProgramInfo */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXChannelJumpMiniProgramInfo extends WXChannelBaseJumpInfo {
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXChannelJumpMiniProgramInfo";
    public String path;
    public String username;

    @Override // com.tencent.p348mm.opensdk.modelbiz.WXChannelBaseJumpInfo, com.tencent.p348mm.opensdk.modelbiz.IWXChannelJumpInfo
    public boolean checkArgs() {
        String str;
        String str2;
        String str3 = this.username;
        if (str3 == null || str3.length() <= 0) {
            str = "MicroMsg.SDK.WXChannelJumpMiniProgramInfo";
            str2 = "checkArgs fail, username is null";
        } else {
            String str4 = this.path;
            if (str4 == null || str4.length() < 10240) {
                return super.checkArgs();
            }
            str = "MicroMsg.SDK.WXChannelJumpMiniProgramInfo";
            str2 = "checkArgs fail, path is invalid";
        }
        C10384Log.m6210e(str, str2);
        return false;
    }

    @Override // com.tencent.p348mm.opensdk.modelbiz.WXChannelBaseJumpInfo, com.tencent.p348mm.opensdk.modelbiz.IWXChannelJumpInfo
    public void serialize(Bundle bundle) {
        super.serialize(bundle);
        bundle.putString("wx_channel_jump_mini_program_username", this.username);
        bundle.putString("wx_channel_jump_mini_program_path", this.path);
    }

    @Override // com.tencent.p348mm.opensdk.modelbiz.IWXChannelJumpInfo
    public int type() {
        return 1;
    }

    @Override // com.tencent.p348mm.opensdk.modelbiz.WXChannelBaseJumpInfo, com.tencent.p348mm.opensdk.modelbiz.IWXChannelJumpInfo
    public void unserialize(Bundle bundle) {
        super.unserialize(bundle);
        this.username = bundle.getString("wx_channel_jump_mini_program_username");
        this.path = bundle.getString("wx_channel_jump_mini_program_path");
    }
}

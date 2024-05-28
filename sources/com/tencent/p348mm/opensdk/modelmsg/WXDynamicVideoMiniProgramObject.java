package com.tencent.p348mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import com.tencent.p348mm.opensdk.utils.C10386b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.WXDynamicVideoMiniProgramObject */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXDynamicVideoMiniProgramObject extends WXMiniProgramObject {
    private static final String TAG = "MicroMsg.SDK.WXDynamicVideoMiniProgramObject";
    public String appThumbUrl;
    public String videoSource;

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMiniProgramObject, com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        String str;
        String str2;
        if (C10386b.m6202b(this.webpageUrl)) {
            str = "MicroMsg.SDK.WXDynamicVideoMiniProgramObject";
            str2 = "webPageUrl is null";
        } else if (C10386b.m6202b(this.userName)) {
            str = "MicroMsg.SDK.WXDynamicVideoMiniProgramObject";
            str2 = "userName is null";
        } else {
            int i = this.miniprogramType;
            if (i >= 0 && i <= 2) {
                return true;
            }
            str = "MicroMsg.SDK.WXDynamicVideoMiniProgramObject";
            str2 = "miniprogram type should between MINIPTOGRAM_TYPE_RELEASE and MINIPROGRAM_TYPE_PREVIEW";
        }
        C10384Log.m6210e(str, str2);
        return false;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMiniProgramObject, com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putString("_wxminiprogram_webpageurl", this.webpageUrl);
        bundle.putString("_wxminiprogram_username", this.userName);
        bundle.putString("_wxminiprogram_path", this.path);
        bundle.putString("_wxminiprogram_videoSource", this.videoSource);
        bundle.putString("_wxminiprogram_appThumbUrl", this.appThumbUrl);
        bundle.putBoolean("_wxminiprogram_withsharetiket", this.withShareTicket);
        bundle.putInt("_wxminiprogram_type", this.miniprogramType);
        bundle.putInt("_wxminiprogram_disableforward", this.disableforward);
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMiniProgramObject, com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public int type() {
        return 46;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMiniProgramObject, com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.webpageUrl = bundle.getString("_wxminiprogram_webpageurl");
        this.userName = bundle.getString("_wxminiprogram_username");
        this.path = bundle.getString("_wxminiprogram_path");
        this.videoSource = bundle.getString("_wxminiprogram_videoSource");
        this.appThumbUrl = bundle.getString("_wxminiprogram_appThumbUrl");
        this.withShareTicket = bundle.getBoolean("_wxminiprogram_withsharetiket");
        this.miniprogramType = bundle.getInt("_wxminiprogram_type");
        this.disableforward = bundle.getInt("_wxminiprogram_disableforward");
    }
}

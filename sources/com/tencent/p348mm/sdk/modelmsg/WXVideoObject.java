package com.tencent.p348mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelmsg.WXVideoObject */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXVideoObject implements WXMediaMessage.IMediaObject {
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXVideoObject";
    public String videoLowBandUrl;
    public String videoUrl;

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        String str;
        String str2;
        String str3;
        String str4 = this.videoUrl;
        if ((str4 == null || str4.length() == 0) && ((str = this.videoLowBandUrl) == null || str.length() == 0)) {
            str2 = "MicroMsg.SDK.WXVideoObject";
            str3 = "both arguments are null";
        } else {
            String str5 = this.videoUrl;
            if (str5 == null || str5.length() <= 10240) {
                String str6 = this.videoLowBandUrl;
                if (str6 == null || str6.length() <= 10240) {
                    return true;
                }
                str2 = "MicroMsg.SDK.WXVideoObject";
                str3 = "checkArgs fail, videoLowBandUrl is too long";
            } else {
                str2 = "MicroMsg.SDK.WXVideoObject";
                str3 = "checkArgs fail, videoUrl is too long";
            }
        }
        C10393b.m6195a(str2, str3);
        return false;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putString("_wxvideoobject_videoUrl", this.videoUrl);
        bundle.putString("_wxvideoobject_videoLowBandUrl", this.videoLowBandUrl);
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public int type() {
        return 4;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.videoUrl = bundle.getString("_wxvideoobject_videoUrl");
        this.videoLowBandUrl = bundle.getString("_wxvideoobject_videoLowBandUrl");
    }
}

package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXMediaMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WXVideoObject implements WXMediaMessage.IMediaObject {
    public String videoLowBandUrl;
    public String videoUrl;

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public int type() {
        return 4;
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putString("_wxvideoobject_videoUrl", this.videoUrl);
        bundle.putString("_wxvideoobject_videoLowBandUrl", this.videoLowBandUrl);
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.videoUrl = bundle.getString("_wxvideoobject_videoUrl");
        this.videoLowBandUrl = bundle.getString("_wxvideoobject_videoLowBandUrl");
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        String str;
        String str2 = this.videoUrl;
        if ((str2 == null || str2.length() == 0) && ((str = this.videoLowBandUrl) == null || str.length() == 0)) {
            SSDKLog.m21740b().m21744a("both arguments are null", new Object[0]);
            return false;
        }
        String str3 = this.videoUrl;
        if (str3 != null && str3.length() > 10240) {
            SSDKLog.m21740b().m21744a("checkArgs fail, videoUrl is too long", new Object[0]);
            return false;
        }
        String str4 = this.videoLowBandUrl;
        if (str4 == null || str4.length() <= 10240) {
            return true;
        }
        SSDKLog.m21740b().m21744a("checkArgs fail, videoLowBandUrl is too long", new Object[0]);
        return false;
    }
}

package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXMediaMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WXMusicObject implements WXMediaMessage.IMediaObject {
    public String musicDataUrl;
    public String musicLowBandDataUrl;
    public String musicLowBandUrl;
    public String musicUrl;
    public String songAlbumUrl;
    public String songLyric;

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public int type() {
        return 3;
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putString("_wxmusicobject_musicUrl", this.musicUrl);
        bundle.putString("_wxmusicobject_musicLowBandUrl", this.musicLowBandUrl);
        bundle.putString("_wxmusicobject_musicDataUrl", this.musicDataUrl);
        bundle.putString("_wxmusicobject_musicLowBandDataUrl", this.musicLowBandDataUrl);
        bundle.putString("_wxmusicobject_musicAlbumUrl", this.songAlbumUrl);
        bundle.putString("_wxmusicobject_musicLyric", this.songLyric);
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.musicUrl = bundle.getString("_wxmusicobject_musicUrl");
        this.musicLowBandUrl = bundle.getString("_wxmusicobject_musicLowBandUrl");
        this.musicDataUrl = bundle.getString("_wxmusicobject_musicDataUrl");
        this.musicLowBandDataUrl = bundle.getString("_wxmusicobject_musicLowBandDataUrl");
        this.songAlbumUrl = bundle.getString("_wxmusicobject_musicAlbumUrl");
        this.songLyric = bundle.getString("_wxmusicobject_musicLyric");
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        if (TextUtils.isEmpty(this.musicUrl) && TextUtils.isEmpty(this.musicLowBandUrl)) {
            SSDKLog.m21740b().m21744a("both musicUrl and musicLowBandUrl are null", new Object[0]);
            return false;
        }
        String str = this.musicUrl;
        if (str != null && str.length() > 10240) {
            SSDKLog.m21740b().m21744a("checkArgs fail, musicUrl is too long", new Object[0]);
            return false;
        }
        String str2 = this.musicLowBandUrl;
        if (str2 != null && str2.length() > 10240) {
            SSDKLog.m21740b().m21744a("checkArgs fail, musicLowBandUrl is too long", new Object[0]);
            return false;
        }
        String str3 = this.songAlbumUrl;
        if (str3 != null && str3.length() > 10240) {
            SSDKLog.m21740b().m21744a("checkArgs fail, songAlbumUrl is too long", new Object[0]);
            return false;
        }
        String str4 = this.songLyric;
        if (str4 == null || str4.length() <= 32768) {
            return true;
        }
        SSDKLog.m21740b().m21744a("checkArgs fail, songLyric is too long", new Object[0]);
        return false;
    }
}

package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WXEmojiObject implements WXMediaMessage.IMediaObject {
    public byte[] emojiData;
    public String emojiPath;

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public int type() {
        return 8;
    }

    public WXEmojiObject() {
    }

    public WXEmojiObject(byte[] bArr) {
        this.emojiData = bArr;
    }

    public WXEmojiObject(String str) {
        this.emojiPath = str;
    }

    public void setEmojiData(byte[] bArr) {
        this.emojiData = bArr;
    }

    public void setEmojiPath(String str) {
        this.emojiPath = str;
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wxemojiobject_emojiData", this.emojiData);
        bundle.putString("_wxemojiobject_emojiPath", this.emojiPath);
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.emojiData = bundle.getByteArray("_wxemojiobject_emojiData");
        this.emojiPath = bundle.getString("_wxemojiobject_emojiPath");
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        byte[] bArr = this.emojiData;
        if ((bArr == null || bArr.length == 0) && TextUtils.isEmpty(this.emojiPath)) {
            SSDKLog.m21740b().m21744a("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, both arguments is null");
            return false;
        }
        byte[] bArr2 = this.emojiData;
        if (bArr2 != null && bArr2.length > 10485760) {
            SSDKLog.m21740b().m21744a("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, emojiData is too large");
            return false;
        }
        String str = this.emojiPath;
        if (str != null) {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    SSDKLog.m21740b().m21744a("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, emojiPath not found");
                    return false;
                } else if (file.length() > 10485760) {
                    SSDKLog.m21740b().m21744a("MicroMsg.SDK.WXEmojiObject", "checkArgs fail, emojiSize is too large");
                    return false;
                }
            } catch (Throwable th) {
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21744a("ShareSDk", " WXEmojiObject catch don't worry will be two style " + th);
                int m21229a = WechatTools.m21233a().m21229a(this.emojiPath);
                if (m21229a != 0 && m21229a > 10485760) {
                    return false;
                }
            }
        }
        return true;
    }
}

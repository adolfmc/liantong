package cn.sharesdk.wechat.utils;

import android.graphics.Bitmap;
import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WXImageObject implements WXMediaMessage.IMediaObject {
    public byte[] imageData;
    public String imagePath;
    public String imageUrl;

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public int type() {
        return 2;
    }

    public WXImageObject() {
    }

    public WXImageObject(byte[] bArr) {
        this.imageData = bArr;
    }

    public WXImageObject(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            this.imageData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            SSDKLog.m21740b().m21742a(e);
        }
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wximageobject_imageData", this.imageData);
        bundle.putString("_wximageobject_imagePath", this.imagePath);
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.imageData = bundle.getByteArray("_wximageobject_imageData");
        this.imagePath = bundle.getString("_wximageobject_imagePath");
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        String str;
        String str2;
        byte[] bArr = this.imageData;
        if ((bArr == null || bArr.length == 0) && (((str = this.imagePath) == null || str.length() == 0) && ((str2 = this.imageUrl) == null || str2.length() == 0))) {
            SSDKLog.m21740b().m21744a("checkArgs fail, all arguments are null", new Object[0]);
            return false;
        }
        byte[] bArr2 = this.imageData;
        if (bArr2 != null && bArr2.length > 26214400) {
            SSDKLog.m21740b().m21744a("checkArgs fail, content is too large", new Object[0]);
            return false;
        }
        String str3 = this.imagePath;
        if (str3 != null && str3.length() > 10240) {
            SSDKLog.m21740b().m21744a("checkArgs fail, path is invalid", new Object[0]);
            return false;
        }
        if (this.imagePath != null) {
            try {
                if (WechatTools.m21233a().m21229a(this.imagePath) > 26214400) {
                    SSDKLog.m21740b().m21744a("checkArgs fail, image content is too large", new Object[0]);
                    return false;
                }
            } catch (Throwable th) {
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21744a("ShareSDK", " WXImageObject catch don't worry will betwo style " + th);
                int m21229a = WechatTools.m21233a().m21229a(this.imagePath);
                if (m21229a != 0 && m21229a > 10485760) {
                    return false;
                }
            }
        }
        String str4 = this.imageUrl;
        if (str4 == null || str4.length() <= 10240) {
            return true;
        }
        SSDKLog.m21740b().m21744a("checkArgs fail, url is invalid", new Object[0]);
        return false;
    }
}

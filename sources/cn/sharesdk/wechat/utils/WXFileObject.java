package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXMediaMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WXFileObject implements WXMediaMessage.IMediaObject {
    private int contentLengthLimit;
    public byte[] fileData;
    public String filePath;

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public int type() {
        return 6;
    }

    public WXFileObject() {
        this.contentLengthLimit = 1920991232;
        this.fileData = null;
        this.filePath = null;
    }

    public WXFileObject(byte[] bArr) {
        this.contentLengthLimit = 1920991232;
        this.fileData = bArr;
    }

    public WXFileObject(String str) {
        this.contentLengthLimit = 1920991232;
        this.filePath = str;
    }

    public void setFileData(byte[] bArr) {
        this.fileData = bArr;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    private int getFileSize(String str) {
        return WechatTools.m21233a().m21229a(str);
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wxfileobject_fileData", this.fileData);
        bundle.putString("_wxfileobject_filePath", this.filePath);
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.fileData = bundle.getByteArray("_wxfileobject_fileData");
        this.filePath = bundle.getString("_wxfileobject_filePath");
    }

    public void setContentLengthLimit(int i) {
        this.contentLengthLimit = i;
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        String str;
        byte[] bArr = this.fileData;
        if ((bArr == null || bArr.length == 0) && ((str = this.filePath) == null || str.length() == 0)) {
            SSDKLog.m21740b().m21744a("checkArgs fail, both arguments is null", new Object[0]);
            return false;
        }
        byte[] bArr2 = this.fileData;
        if (bArr2 != null && bArr2.length > this.contentLengthLimit) {
            SSDKLog.m21740b().m21744a("checkArgs fail, fileData is too large", new Object[0]);
            return false;
        }
        String str2 = this.filePath;
        if (str2 != null) {
            try {
                if (getFileSize(str2) > this.contentLengthLimit) {
                    SSDKLog.m21740b().m21744a("checkArgs fail, fileSize is too large", new Object[0]);
                    return false;
                }
            } catch (Throwable th) {
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21744a("ShareSDK", " WXAppExendObject catch don't worry will be two style " + th);
                int m21229a = WechatTools.m21233a().m21229a(this.filePath);
                if (m21229a != 0 && m21229a > this.contentLengthLimit) {
                    return false;
                }
            }
        }
        return true;
    }
}

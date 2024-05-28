package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXMediaMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WXAppExtendObject implements WXMediaMessage.IMediaObject {
    private static final int CONTENT_LENGTH_LIMIT = 10485760;
    private static final int PATH_LENGTH_LIMIT = 10240;
    public String extInfo;
    public byte[] fileData;
    public String filePath;

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public int type() {
        return 7;
    }

    public WXAppExtendObject() {
    }

    public WXAppExtendObject(String str, byte[] bArr) {
        this.extInfo = str;
        this.fileData = bArr;
    }

    public WXAppExtendObject(String str, String str2) {
        this.extInfo = str;
        this.filePath = str2;
    }

    private int getFileSize(String str) {
        return WechatTools.m21233a().m21229a(str);
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putString("_wxappextendobject_extInfo", this.extInfo);
        bundle.putByteArray("_wxappextendobject_fileData", this.fileData);
        bundle.putString("_wxappextendobject_filePath", this.filePath);
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.extInfo = bundle.getString("_wxappextendobject_extInfo");
        this.fileData = bundle.getByteArray("_wxappextendobject_fileData");
        this.filePath = bundle.getString("_wxappextendobject_filePath");
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        String str;
        byte[] bArr;
        String str2 = this.extInfo;
        if ((str2 == null || str2.length() == 0) && (((str = this.filePath) == null || str.length() == 0) && ((bArr = this.fileData) == null || bArr.length == 0))) {
            SSDKLog.m21740b().m21744a("checkArgs fail, all arguments is null", new Object[0]);
            return false;
        }
        String str3 = this.extInfo;
        if (str3 == null || str3.length() <= 2048) {
            String str4 = this.filePath;
            if (str4 == null || str4.length() <= 10240) {
                String str5 = this.filePath;
                if (str5 == null || getFileSize(str5) <= 10485760) {
                    byte[] bArr2 = this.fileData;
                    if (bArr2 == null || bArr2.length <= 10485760) {
                        return true;
                    }
                    SSDKLog.m21740b().m21744a("checkArgs fail, fileData is too large", new Object[0]);
                    return false;
                }
                SSDKLog.m21740b().m21744a("checkArgs fail, fileSize is too large", new Object[0]);
                return false;
            }
            SSDKLog.m21740b().m21744a("checkArgs fail, filePath is invalid", new Object[0]);
            return false;
        }
        SSDKLog.m21740b().m21744a("checkArgs fail, extInfo is invalid", new Object[0]);
        return false;
    }
}

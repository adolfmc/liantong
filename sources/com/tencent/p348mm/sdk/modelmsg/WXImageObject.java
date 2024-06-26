package com.tencent.p348mm.sdk.modelmsg;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.tencent.p348mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.sdk.p354b.C10393b;
import java.io.ByteArrayOutputStream;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelmsg.WXImageObject */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXImageObject implements WXMediaMessage.IMediaObject {
    private static final int CONTENT_LENGTH_LIMIT = 10485760;
    private static final int PATH_LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXImageObject";
    private static final int URL_LENGTH_LIMIT = 10240;
    public byte[] imageData;
    public String imagePath;
    public String imageUrl;

    public WXImageObject() {
    }

    public WXImageObject(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            this.imageData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WXImageObject(byte[] bArr) {
        this.imageData = bArr;
    }

    private int getFileSize(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        File file = new File(str);
        if (file.exists()) {
            return (int) file.length();
        }
        return 0;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        String str;
        String str2;
        String str3;
        String str4;
        byte[] bArr = this.imageData;
        if ((bArr == null || bArr.length == 0) && (((str = this.imagePath) == null || str.length() == 0) && ((str2 = this.imageUrl) == null || str2.length() == 0))) {
            str3 = "MicroMsg.SDK.WXImageObject";
            str4 = "checkArgs fail, all arguments are null";
        } else {
            byte[] bArr2 = this.imageData;
            if (bArr2 == null || bArr2.length <= 10485760) {
                String str5 = this.imagePath;
                if (str5 == null || str5.length() <= 10240) {
                    String str6 = this.imagePath;
                    if (str6 == null || getFileSize(str6) <= 10485760) {
                        String str7 = this.imageUrl;
                        if (str7 == null || str7.length() <= 10240) {
                            return true;
                        }
                        str3 = "MicroMsg.SDK.WXImageObject";
                        str4 = "checkArgs fail, url is invalid";
                    } else {
                        str3 = "MicroMsg.SDK.WXImageObject";
                        str4 = "checkArgs fail, image content is too large";
                    }
                } else {
                    str3 = "MicroMsg.SDK.WXImageObject";
                    str4 = "checkArgs fail, path is invalid";
                }
            } else {
                str3 = "MicroMsg.SDK.WXImageObject";
                str4 = "checkArgs fail, content is too large";
            }
        }
        C10393b.m6195a(str3, str4);
        return false;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wximageobject_imageData", this.imageData);
        bundle.putString("_wximageobject_imagePath", this.imagePath);
        bundle.putString("_wximageobject_imageUrl", this.imageUrl);
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public int type() {
        return 2;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.imageData = bundle.getByteArray("_wximageobject_imageData");
        this.imagePath = bundle.getString("_wximageobject_imagePath");
        this.imageUrl = bundle.getString("_wximageobject_imageUrl");
    }
}

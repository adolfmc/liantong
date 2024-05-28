package com.tencent.p348mm.opensdk.modelmsg;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import com.tencent.p348mm.opensdk.utils.C10386b;
import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.WXImageObject */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXImageObject implements WXMediaMessage.IMediaObject {
    private static final int CONTENT_LENGTH_LIMIT = 26214400;
    private static final int PATH_LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXImageObject";
    public byte[] imageData;
    public String imagePath;

    public WXImageObject() {
    }

    public WXImageObject(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            this.imageData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.SDK.WXImageObject", "WXImageObject <init>, exception:" + e.getMessage());
        }
    }

    public WXImageObject(byte[] bArr) {
        this.imageData = bArr;
    }

    private int getFileSize(String str) {
        return C10386b.m6204a(str);
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        String str;
        String str2;
        String str3;
        byte[] bArr = this.imageData;
        if ((bArr == null || bArr.length == 0) && ((str = this.imagePath) == null || str.length() == 0)) {
            str2 = "MicroMsg.SDK.WXImageObject";
            str3 = "checkArgs fail, all arguments are null";
        } else {
            byte[] bArr2 = this.imageData;
            if (bArr2 == null || bArr2.length <= 26214400) {
                String str4 = this.imagePath;
                if (str4 == null || str4.length() <= 10240) {
                    String str5 = this.imagePath;
                    if (str5 == null || getFileSize(str5) <= 26214400) {
                        return true;
                    }
                    str2 = "MicroMsg.SDK.WXImageObject";
                    str3 = "checkArgs fail, image content is too large";
                } else {
                    str2 = "MicroMsg.SDK.WXImageObject";
                    str3 = "checkArgs fail, path is invalid";
                }
            } else {
                str2 = "MicroMsg.SDK.WXImageObject";
                str3 = "checkArgs fail, content is too large";
            }
        }
        C10384Log.m6210e(str2, str3);
        return false;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wximageobject_imageData", this.imageData);
        bundle.putString("_wximageobject_imagePath", this.imagePath);
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public int type() {
        return 2;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.imageData = bundle.getByteArray("_wximageobject_imageData");
        this.imagePath = bundle.getString("_wximageobject_imagePath");
    }
}

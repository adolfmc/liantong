package com.tencent.p348mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.sdk.p354b.C10393b;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelmsg.WXFileObject */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXFileObject implements WXMediaMessage.IMediaObject {
    private static final int CONTENT_LENGTH_LIMIT = 10485760;
    private static final String TAG = "MicroMsg.SDK.WXFileObject";
    private int contentLengthLimit;
    public byte[] fileData;
    public String filePath;

    public WXFileObject() {
        this.contentLengthLimit = 10485760;
        this.fileData = null;
        this.filePath = null;
    }

    public WXFileObject(String str) {
        this.contentLengthLimit = 10485760;
        this.filePath = str;
    }

    public WXFileObject(byte[] bArr) {
        this.contentLengthLimit = 10485760;
        this.fileData = bArr;
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
        byte[] bArr = this.fileData;
        if ((bArr == null || bArr.length == 0) && ((str = this.filePath) == null || str.length() == 0)) {
            str2 = "MicroMsg.SDK.WXFileObject";
            str3 = "checkArgs fail, both arguments is null";
        } else {
            byte[] bArr2 = this.fileData;
            if (bArr2 == null || bArr2.length <= this.contentLengthLimit) {
                String str4 = this.filePath;
                if (str4 == null || getFileSize(str4) <= this.contentLengthLimit) {
                    return true;
                }
                str2 = "MicroMsg.SDK.WXFileObject";
                str3 = "checkArgs fail, fileSize is too large";
            } else {
                str2 = "MicroMsg.SDK.WXFileObject";
                str3 = "checkArgs fail, fileData is too large";
            }
        }
        C10393b.m6195a(str2, str3);
        return false;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wxfileobject_fileData", this.fileData);
        bundle.putString("_wxfileobject_filePath", this.filePath);
    }

    public void setContentLengthLimit(int i) {
        this.contentLengthLimit = i;
    }

    public void setFileData(byte[] bArr) {
        this.fileData = bArr;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public int type() {
        return 6;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.fileData = bundle.getByteArray("_wxfileobject_fileData");
        this.filePath = bundle.getString("_wxfileobject_filePath");
    }
}

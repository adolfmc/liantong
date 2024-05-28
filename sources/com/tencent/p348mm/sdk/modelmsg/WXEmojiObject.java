package com.tencent.p348mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.sdk.p354b.C10393b;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelmsg.WXEmojiObject */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXEmojiObject implements WXMediaMessage.IMediaObject {
    private static final int CONTENT_LENGTH_LIMIT = 10485760;
    private static final String TAG = "MicroMsg.SDK.WXEmojiObject";
    public byte[] emojiData;
    public String emojiPath;

    public WXEmojiObject() {
        this.emojiData = null;
        this.emojiPath = null;
    }

    public WXEmojiObject(String str) {
        this.emojiPath = str;
    }

    public WXEmojiObject(byte[] bArr) {
        this.emojiData = bArr;
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
        byte[] bArr = this.emojiData;
        if ((bArr == null || bArr.length == 0) && ((str = this.emojiPath) == null || str.length() == 0)) {
            str2 = "MicroMsg.SDK.WXEmojiObject";
            str3 = "checkArgs fail, both arguments is null";
        } else {
            byte[] bArr2 = this.emojiData;
            if (bArr2 == null || bArr2.length <= 10485760) {
                String str4 = this.emojiPath;
                if (str4 == null || getFileSize(str4) <= 10485760) {
                    return true;
                }
                str2 = "MicroMsg.SDK.WXEmojiObject";
                str3 = "checkArgs fail, emojiSize is too large";
            } else {
                str2 = "MicroMsg.SDK.WXEmojiObject";
                str3 = "checkArgs fail, emojiData is too large";
            }
        }
        C10393b.m6195a(str2, str3);
        return false;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wxemojiobject_emojiData", this.emojiData);
        bundle.putString("_wxemojiobject_emojiPath", this.emojiPath);
    }

    public void setEmojiData(byte[] bArr) {
        this.emojiData = bArr;
    }

    public void setEmojiPath(String str) {
        this.emojiPath = str;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public int type() {
        return 8;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.emojiData = bundle.getByteArray("_wxemojiobject_emojiData");
        this.emojiPath = bundle.getString("_wxemojiobject_emojiPath");
    }
}

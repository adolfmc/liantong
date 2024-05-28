package com.tencent.p348mm.sdk.modelmsg;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelmsg.WXTextObject */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXTextObject implements WXMediaMessage.IMediaObject {
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXTextObject";
    public String text;

    public WXTextObject() {
        this(null);
    }

    public WXTextObject(String str) {
        this.text = str;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        String str = this.text;
        if (str == null || str.length() == 0 || this.text.length() > 10240) {
            C10393b.m6195a("MicroMsg.SDK.WXTextObject", "checkArgs fail, text is invalid");
            return false;
        }
        return true;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putString("_wxtextobject_text", this.text);
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public int type() {
        return 1;
    }

    @Override // com.tencent.p348mm.sdk.modelmsg.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.text = bundle.getString("_wxtextobject_text");
    }
}

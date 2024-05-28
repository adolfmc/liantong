package com.tencent.p348mm.opensdk.modelmsg;

import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.WXEmojiSharedObject */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXEmojiSharedObject implements WXMediaMessage.IMediaObject {
    private static final String TAG = "MicroMsg.SDK.WXEmojiSharedObject";
    public int packageflag;
    public String packageid;
    public String thumburl;
    public String url;

    public WXEmojiSharedObject() {
    }

    public WXEmojiSharedObject(String str, int i, String str2, String str3) {
        this.thumburl = str;
        this.packageflag = i;
        this.packageid = str2;
        this.url = str3;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        if (TextUtils.isEmpty(this.packageid) || TextUtils.isEmpty(this.thumburl) || TextUtils.isEmpty(this.url) || this.packageflag == -1) {
            C10384Log.m6210e("MicroMsg.SDK.WXEmojiSharedObject", "checkArgs fail, packageid or thumburl is invalid");
            return false;
        }
        return true;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putString("_wxemojisharedobject_thumburl", this.thumburl);
        bundle.putInt("_wxemojisharedobject_packageflag", this.packageflag);
        bundle.putString("_wxemojisharedobject_packageid", this.packageid);
        bundle.putString("_wxemojisharedobject_url", this.url);
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public int type() {
        return 15;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.thumburl = bundle.getString("_wxwebpageobject_thumburl");
        this.packageflag = bundle.getInt("_wxwebpageobject_packageflag");
        this.packageid = bundle.getString("_wxwebpageobject_packageid");
        this.url = bundle.getString("_wxwebpageobject_url");
    }
}

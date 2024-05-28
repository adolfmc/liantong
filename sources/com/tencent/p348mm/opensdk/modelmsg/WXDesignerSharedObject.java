package com.tencent.p348mm.opensdk.modelmsg;

import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelmsg.WXDesignerSharedObject */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXDesignerSharedObject implements WXMediaMessage.IMediaObject {
    private static final String TAG = "MicroMsg.SDK.WXEmojiSharedObject";
    public String designerName;
    public String designerRediretctUrl;
    public int designerUIN;
    public String thumburl;
    public String url;

    public WXDesignerSharedObject() {
    }

    public WXDesignerSharedObject(String str, int i, String str2, String str3) {
        this.url = str2;
        this.thumburl = str;
        this.designerUIN = i;
        this.designerName = str3;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        if (this.designerUIN == 0 || TextUtils.isEmpty(this.thumburl) || TextUtils.isEmpty(this.url)) {
            C10384Log.m6210e("MicroMsg.SDK.WXEmojiSharedObject", "checkArgs fail, packageid or thumburl is invalid");
            return false;
        }
        return true;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putString("_wxemojisharedobject_thumburl", this.thumburl);
        bundle.putInt("_wxemojisharedobject_designer_uin", this.designerUIN);
        bundle.putString("_wxemojisharedobject_designer_name", this.designerName);
        bundle.putString("_wxemojisharedobject_designer_rediretcturl", this.designerRediretctUrl);
        bundle.putString("_wxemojisharedobject_url", this.url);
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public int type() {
        return 25;
    }

    @Override // com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.thumburl = bundle.getString("_wxwebpageobject_thumburl");
        this.designerUIN = bundle.getInt("_wxemojisharedobject_designer_uin");
        this.designerName = bundle.getString("_wxemojisharedobject_designer_name");
        this.designerRediretctUrl = bundle.getString("_wxemojisharedobject_designer_rediretcturl");
        this.url = bundle.getString("_wxwebpageobject_url");
    }
}

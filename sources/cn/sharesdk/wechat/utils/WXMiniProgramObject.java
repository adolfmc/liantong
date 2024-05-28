package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WXMiniProgramObject implements WXMediaMessage.IMediaObject {
    public static final int MINIPROGRAM_TYPE_PREVIEW = 2;
    public static final int MINIPROGRAM_TYPE_TEST = 1;
    public static final int MINIPTOGRAM_TYPE_RELEASE = 0;
    public String path;
    public String userName;
    public String webpageUrl;
    public boolean withShareTicket;
    public int miniprogramType = 0;
    public int disableforward = 0;
    public boolean isUpdatableMessage = false;
    public boolean isSecretMessage = false;
    private HashMap<String, String> extraInfoMap = null;

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public int type() {
        return 36;
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putString("_wxminiprogram_webpageurl", this.webpageUrl);
        bundle.putString("_wxminiprogram_username", this.userName);
        bundle.putString("_wxminiprogram_path", this.path);
        bundle.putBoolean("_wxminiprogram_withsharetiket", this.withShareTicket);
        bundle.putInt("_wxminiprogram_type", this.miniprogramType);
        bundle.putInt("_wxminiprogram_disableforward", this.disableforward);
        bundle.putBoolean("_wxminiprogram_isupdatablemsg", this.isUpdatableMessage);
        bundle.putBoolean("_wxminiprogram_issecretmsg", this.isSecretMessage);
        HashMap<String, String> hashMap = this.extraInfoMap;
        if (hashMap != null) {
            bundle.putSerializable("_wxminiprogram_extrainfo", hashMap);
        }
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public void unserialize(Bundle bundle) {
        this.webpageUrl = bundle.getString("_wxminiprogram_webpageurl");
        this.userName = bundle.getString("_wxminiprogram_username");
        this.path = bundle.getString("_wxminiprogram_path");
        this.withShareTicket = bundle.getBoolean("_wxminiprogram_withsharetiket");
        this.miniprogramType = bundle.getInt("_wxminiprogram_type");
        this.disableforward = bundle.getInt("_wxminiprogram_disableforward");
        this.isUpdatableMessage = bundle.getBoolean("_wxminiprogram_isupdatablemsg");
        this.isSecretMessage = bundle.getBoolean("_wxminiprogram_issecretmsg");
        this.extraInfoMap = (HashMap) bundle.getSerializable("_wxminiprogram_extrainfo");
    }

    @Override // cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject
    public boolean checkArgs() {
        String str = this.webpageUrl;
        if (str == null || str.length() == 0 || this.webpageUrl.length() > 10240) {
            SSDKLog.m21740b().m21744a("checkArgs fail, webpageUrl is invalid", new Object[0]);
            return false;
        }
        String str2 = this.userName;
        if (str2 == null || str2.length() == 0 || this.userName.length() > 10240) {
            SSDKLog.m21740b().m21744a("checkArgs fail, userName is invalid", new Object[0]);
            return false;
        }
        int i = this.miniprogramType;
        if (i < 0 || i > 2) {
            SSDKLog.m21740b().m21744a("checkArgs fail", "miniprogram type should between MINIPTOGRAM_TYPE_RELEASE and MINIPROGRAM_TYPE_PREVIEW");
            return false;
        }
        return true;
    }

    public void putExtra(String str, String str2) {
        if (this.extraInfoMap == null) {
            this.extraInfoMap = new HashMap<>();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.extraInfoMap.put(str, str2);
    }

    public String getExtra(String str, String str2) {
        HashMap<String, String> hashMap = this.extraInfoMap;
        if (hashMap == null) {
            return null;
        }
        String str3 = hashMap.get(str);
        return str3 != null ? str3 : str2;
    }
}

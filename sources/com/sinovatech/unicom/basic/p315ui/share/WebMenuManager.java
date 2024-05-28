package com.sinovatech.unicom.basic.p315ui.share;

import android.text.TextUtils;
import com.sinovatech.unicom.p318ui.App;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.share.WebMenuManager */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WebMenuManager {
    public static final String HuaBao = "huabaofenxiang";
    public static final String REFRESH = "shuaxin";
    private static final String ShareDefaultContent = "查余量办业务，用中国联通APP。还可以免费免流量看视频看小说，无广告哦~http://u.10010.cn/download";
    private static final String ShareDefaultJson = "{\"shareType\":\"url\",\"shareTitle\":\"中国联通APP\",\"shareContent\":\"查余量办业务，用中国联通APP。还可以免费免流量看视频看小说，无广告哦~\",\"shareURL\":\"http://u.10010.cn/download\",\"shareIconURL\":\"http://img.client.10010.com/mobileService/view/client/images/uniClientIcon.png\"}";
    private static final String ShareLongDefaultJson = "{\"shareType\":\"longScreenshot\",\"shareTitle\":\"中国联通APP\",\"shareContent\":\"查余量办业务，用中国联通APP。还可以免费免流量看视频看小说，无广告哦~\",\"shareURL\":\"http://u.10010.cn/download\",\"shareIconURL\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20210325/png/20210325145817.png\"}";
    public static final String WoKouLing = "wokouling";
    public static final String ZiDingYiHuaBao = "zidingyi";
    public static final String cai = "cai";
    public static final String email = "email";
    public static final String fenxiang = "fenxiang";
    public static final String jietu = "jietufenxiang";
    public static final String longshot = "jietufenxiang";

    /* renamed from: qq */
    public static final String f18434qq = "qq";
    public static final String qzone = "qzone";
    public static final String shortmessage = "shortmessage";
    public static final String shoucang = "shoucang";
    public static final String shouye = "shouye";
    public static final String sinaweibo = "sinaweibo";
    public static final String tencentweibo = "tencentweibo";
    public static final String tiaozhuan = "tiaozhuan";
    public static final String tucao = "tucao";
    public static final String wechat = "wechat";
    public static final String wechatmoments = "wechatmoments";
    public static final String wodeshoucang = "wodeshoucang";
    public static final String wokouling = "copyUrl";

    public static String getDefaultLongShareJson() {
        return "{\"shareType\":\"longScreenshot\",\"shareTitle\":\"中国联通APP\",\"shareContent\":\"查余量办业务，用中国联通APP。还可以免费免流量看视频看小说，无广告哦~\",\"shareURL\":\"http://u.10010.cn/download\",\"shareIconURL\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20210325/png/20210325145817.png\"}";
    }

    public static String getDefaultShareContent() {
        return "查余量办业务，用中国联通APP。还可以免费免流量看视频看小说，无广告哦~http://u.10010.cn/download";
    }

    public static String getNewDefaultJson() {
        return "{\n    \"shareContent\":\"手机新用户专享福利，话费+流量双重红包!\",\n    \"shareIconURL\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20210325/png/20210325101313.png\",\n    \"provider\":\"10010\",\n    \"shareTitle\":\"新人百元礼包\",\n    \"shareType\":\"url\",\n    \"headUrl\":\"https://wap.10010.com/mobileService/clickCountLogRecord/pageClickCount.htm?flag=new\",\n    \"shareURL\":\"https://m.client.10010.com/mobileService/openPlatform/openPlatLine.htm?to_url=https://m.client.10010.com/newuser_century/static/textdl/userLogin\",\n    \"huabaoIconUrl\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20210325/png/20210325151945.png\",\n    \"businessCode\":\"111111\",\n    \"default\":\"1\",\n    \"tip_title\":\"推荐好友办理业务赚手厅话费券\",\n    \"tip_content\":\"1.点击微信分享好友或微信群。\\n2.3个月内好友在手厅办理的业务，你都将获得相应的话费券\\n3.话费券明细在手厅一我的-推荐有礼’处查看。\"\n}";
    }

    public static List<String> getAllShareList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("shortmessage");
        arrayList.add("wechat");
        arrayList.add("wechatmoments");
        arrayList.add("qq");
        arrayList.add("qzone");
        arrayList.add("sinaweibo");
        arrayList.add("jietufenxiang");
        return arrayList;
    }

    public static List<String> getTypeAllShareList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("wokouling");
        arrayList.add("huabaofenxiang");
        return arrayList;
    }

    public static List<String> getLongshotShareList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("wechat");
        arrayList.add("wechatmoments");
        arrayList.add("qq");
        arrayList.add("qzone");
        return arrayList;
    }

    public static String getAllShareString() {
        Iterator<String> it;
        String str = "";
        while (getAllShareList().iterator().hasNext()) {
            str = str + it.next() + ",";
        }
        return str.lastIndexOf(",") == str.length() + (-1) ? str.substring(0, str.length() - 1) : str;
    }

    public static String getLongshotShareString() {
        Iterator<String> it;
        String str = "";
        while (getLongshotShareList().iterator().hasNext()) {
            str = str + it.next() + ",";
        }
        return str.lastIndexOf(",") == str.length() + (-1) ? str.substring(0, str.length() - 1) : str;
    }

    public static List<String> getAllMenuCodeList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("fenxiang");
        arrayList.add("tucao");
        arrayList.add("shouye");
        arrayList.add("tiaozhuan");
        arrayList.add("jietufenxiang");
        arrayList.add("copyUrl");
        arrayList.add("shoucang");
        arrayList.add("wodeshoucang");
        arrayList.add("shuaxin");
        return arrayList;
    }

    public static List<WebMenuEntity> getDefaultMenuList(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WebMenuEntity("fenxiang", "分享", getDefaultShareContent(), "shortmessage,wechat,wechatmoments,qq,qzone,sinaweibo,wokouling,huabaofenxiang", getDefaultJson("url", str), ""));
        arrayList.add(new WebMenuEntity("jietufenxiang", "截图分享", getDefaultShareContent(), getLongshotShareString(), getLongDefaultJson("longScreenshot", str), ""));
        arrayList.add(new WebMenuEntity("tucao", "吐槽", "", "", "", ""));
        arrayList.add(new WebMenuEntity("shouye", "回到首页", "", "", "", ""));
        return arrayList;
    }

    public static boolean isExitInAllMenuConfig(String str) {
        for (String str2 : getAllMenuCodeList()) {
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExitInAllShareList(String str) {
        for (String str2 : getAllShareList()) {
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    public static List<WebMenuEntity> parseWebMenuJsonData(String str) {
        TextUtils.isEmpty(str);
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONObject(str).getJSONArray("config");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String trim = jSONObject.getString("code").trim();
                String trim2 = jSONObject.optString("title").trim();
                String trim3 = jSONObject.optString("desc").trim();
                String trim4 = jSONObject.optString("shareList").trim();
                String trim5 = jSONObject.optString("menuId").trim();
                String trim6 = jSONObject.optString("shareJson", "").trim();
                if (isExitInAllMenuConfig(trim)) {
                    arrayList.add(new WebMenuEntity(trim, trim2, trim3, trim4, trim6, trim5));
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<WebMenuEntity> parseWebMenuJsonData(String str, String str2) {
        try {
            ArrayList arrayList = new ArrayList();
            if (str != null && !str.startsWith("{")) {
                str = "";
            }
            if (TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    str2 = "中国联通APP";
                }
                return getDefaultMenuList(str2);
            }
            JSONArray jSONArray = new JSONObject(str).getJSONArray("config");
            boolean z = false;
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String trim = jSONObject.getString("code").trim();
                String trim2 = jSONObject.optString("title").trim();
                String trim3 = jSONObject.optString("desc").trim();
                String trim4 = jSONObject.optString("shareList").trim();
                String trim5 = jSONObject.optString("menuId").trim();
                String trim6 = jSONObject.optString("shareJson", "").trim();
                String defaultJson = (!TextUtils.isEmpty(trim) && trim.equalsIgnoreCase("fenxiang") && (TextUtils.isEmpty(trim6) || trim6.equalsIgnoreCase("null"))) ? getDefaultJson("url", "") : trim6;
                if (isExitInAllMenuConfig(trim)) {
                    arrayList.add(new WebMenuEntity(trim, trim2, trim3, trim4, defaultJson, trim5));
                }
                if ("tucao".equalsIgnoreCase(trim)) {
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(new WebMenuEntity("tucao", "吐槽", "", "", "", ""));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.WebMenuManager$WebMenuEntity */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class WebMenuEntity {
        private String appId;
        public String code;
        public String desc;
        public boolean isCollect;
        public String menuId;
        private String productId;
        public String shareJson;
        public String shareList;
        public String title;

        public boolean isCollect() {
            return this.isCollect;
        }

        public void setCollect(boolean z) {
            this.isCollect = z;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String str) {
            this.appId = str;
        }

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String str) {
            this.productId = str;
        }

        public WebMenuEntity(String str, String str2, String str3, String str4, String str5, String str6) {
            this.code = str;
            this.title = str2;
            this.desc = str3;
            this.shareList = str4;
            this.menuId = str6;
            this.shareJson = str5;
        }
    }

    private static String getLongDefaultJson(String str, String str2) {
        return "{\"shareType\":\"" + str + "\",\"shareTitle\":\"" + str2 + "\",\"shareContent\":\"查余量办业务，用中国联通APP。还可以免费免流量看视频看小说，无广告哦~\",\"shareURL\":\"http://u.10010.cn/download\",\"shareIconURL\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20210325/png/20210325101313.png\"}";
    }

    private static String getDefaultJson(String str, String str2) {
        String string = App.getSharePreferenceUtil().getString("share_default_share");
        return (TextUtils.isEmpty(string) || TextUtils.equals("{}", string)) ? getNewDefaultJson() : string;
    }
}

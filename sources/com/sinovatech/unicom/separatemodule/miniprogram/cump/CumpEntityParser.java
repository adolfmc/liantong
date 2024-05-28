package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.text.TextUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.dic.ManagerWebCacheDictionary;
import com.sinovatech.unicom.separatemodule.miniprogram.lowcode.storagejsplugin.LowcodeJSStorageBox;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CumpEntityParser {
    private static boolean isRightDomain(String str, String str2) {
        try {
            String host = new URL(str2).getHost();
            if (host.endsWith("10010.com")) {
                return true;
            }
            return host.endsWith("10010.cn");
        } catch (Exception e) {
            MsLogUtil.m7979d(str, "加载小程序 isRightDomain报错：" + e.getMessage());
            return false;
        }
    }

    public static CumpResponse parse(String str, CumpResponse cumpResponse, JSONObject jSONObject, Box<CumpEntity> box, String str2) throws Exception {
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        boolean z;
        CumpResponse cumpResponse2;
        String str11;
        String str12;
        String str13;
        Box<CumpEntity> box2;
        MsLogUtil.m7979d(str, "CumpEntityParser 开始解析");
        CumpResponse cumpResponse3 = new CumpResponse();
        if (cumpResponse != null) {
            cumpResponse3 = cumpResponse;
        }
        String string = jSONObject.getString("timestamp");
        JSONObject jSONObject2 = jSONObject.getJSONObject("appInfo");
        String string2 = jSONObject2.getString("appId");
        String string3 = jSONObject2.getString("appName");
        String string4 = jSONObject2.getString("appImg");
        String string5 = jSONObject2.getString("appHighImg");
        String optString = jSONObject2.optString("appToken");
        String optString2 = jSONObject2.optString("appSecret");
        String string6 = jSONObject2.getString("appDesc");
        String optString3 = jSONObject2.optString("desktopIcon", "");
        String optString4 = jSONObject2.optString("privacyUrl", "");
        CumpResponse cumpResponse4 = cumpResponse3;
        boolean equals = "1".equals(jSONObject2.optString("authorityState", "0"));
        String string7 = jSONObject.getString("publishMethod");
        String optString5 = jSONObject.optString("homePageKey", "");
        String optString6 = jSONObject.optString("componentTimestamp", "");
        JSONObject jSONObject3 = jSONObject.getJSONObject("versionInfo");
        String string8 = jSONObject3.getString("officialVersion");
        String optString7 = jSONObject3.optString("trialVersionNum", "");
        String optString8 = jSONObject3.optString("officialVersionNum", "");
        if ("2".equals(string7)) {
            str3 = "";
            str4 = "";
            str5 = optString8;
        } else {
            String string9 = jSONObject3.getString("officialMd5");
            str3 = jSONObject3.getString("officialPackageUrl");
            str4 = string9;
            str5 = optString8;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("realmInfo");
        boolean z2 = false;
        if (optJSONArray != null) {
            str6 = string5;
            str7 = optString3;
            str8 = str4;
            str9 = "";
            int i = 0;
            while (i < optJSONArray.length()) {
                str9 = str9 + ((JSONObject) optJSONArray.get(i)).getString("realmUrl") + ",";
                i++;
                optJSONArray = optJSONArray;
            }
        } else {
            str6 = string5;
            str7 = optString3;
            str8 = str4;
            str9 = "";
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("grantInfo_client");
        String str14 = "";
        if (optJSONArray2 != null) {
            String str15 = "";
            str10 = str9;
            int i2 = 0;
            while (i2 < optJSONArray2.length()) {
                JSONArray jSONArray = optJSONArray2;
                str15 = str15 + optJSONArray2.getJSONObject(i2).getString("plugCode") + ",";
                i2++;
                optJSONArray2 = jSONArray;
            }
            str14 = str15;
        } else {
            str10 = str9;
        }
        String optString9 = jSONObject.optString("returnType", "2");
        CumpEntity findUnique = box.query().equal(CumpEntity_.appId, string2).build().findUnique();
        if ((TextUtils.isEmpty(str3) || !isRightDomain(str, str3)) && !"2".equals(string7)) {
            z = false;
        } else {
            if (findUnique == null) {
                findUnique = new CumpEntity();
                str11 = string;
                str12 = string7;
                str13 = str3;
                z2 = true;
            } else if (str2.equals(optString9) && findUnique.getServerPublishType().equals(optString9)) {
                str13 = str3;
                Date date = new Date(Long.parseLong(string));
                str11 = string;
                str12 = string7;
                Date date2 = new Date(Long.parseLong(findUnique.getTimestamp()));
                MsLogUtil.m7979d(str, "时间戳检查 新时间：" + date.toLocaleString() + " 旧时间：" + date2.toLocaleString());
                MsLogUtil.m7979d(str, "版本号检查 新版本号：" + string8 + " 旧版本号：" + findUnique.getOfficialVersion());
                MsLogUtil.m7979d(str, "innerMiniP检查 新：" + equals + " 旧：" + findUnique.isInnerMiniP());
                MsLogUtil.m7979d(str, "publishType检查 传参：" + str2 + " 返回：" + optString9);
                if (date.after(date2) && ((!string8.equals(findUnique.getOfficialVersion()) && str2.equals("2")) || str2.equals("1"))) {
                    z2 = true;
                }
            } else {
                str11 = string;
                str12 = string7;
                str13 = str3;
                MsLogUtil.m7979d(str, "publishType前后不一致，需要重新更新");
                z2 = true;
            }
            MsLogUtil.m7979d(str, "更新小程序基本信息缓存 存储数据库");
            findUnique.setAppId(string2);
            findUnique.setAppName(string3);
            findUnique.setAppDesc(string6);
            findUnique.setAppImg(string4);
            findUnique.setAppHighImg(str6);
            findUnique.setDesktopIcon(str7);
            findUnique.setPrivacyUrl(optString4);
            findUnique.setAppSecret(optString2);
            findUnique.setAppToken(optString);
            findUnique.setOfficialMd5(str8);
            findUnique.setOfficialPackageUrl(str13);
            findUnique.setOldVersion(findUnique.getOfficialVersion());
            findUnique.setOfficialVersion(string8);
            findUnique.setTrialVersionNum(optString7);
            findUnique.setOfficialVersionNum(str5);
            findUnique.setTimestamp(str11);
            findUnique.setRealmUrlList(str10);
            findUnique.setPlugCodeList(str14);
            findUnique.setServerPublishType(optString9);
            findUnique.setLatestUpdateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            findUnique.setPublishMethod(str12);
            findUnique.setHomePageKey(optString5);
            String string10 = App.getSharePreferenceUtil().getString("HomeCumpAppIdConfig");
            if (TextUtils.isEmpty(string10)) {
                string10 = URLSet.getHomeTuiJianDefUrlAndCumpUrl("appId");
            }
            if (string2.equals(string10)) {
                findUnique.setInnerMiniP(true);
                box2 = box;
            } else {
                findUnique.setInnerMiniP(equals);
                box2 = box;
            }
            box2.put((Box<CumpEntity>) findUnique);
            if (z2) {
                MsLogUtil.m7979d(str, "有新版本，删除磁盘上的缓存文件");
                ManagerWebCacheDictionary.clearEdopAppRuntimeDic(string2);
                LowcodeJSStorageBox.clear(string2);
            } else {
                MsLogUtil.m7979d(str, "没有新版本");
            }
            z = z2;
        }
        try {
            if (!optString6.equals(App.getSharePreferenceUtil().getString("WebviewResourceIntercepterCache"))) {
                MsLogUtil.m7979d(str, "低代码组件缓存时间戳有变动，清空所有的组件文件");
                ManagerWebCacheDictionary.clearWebviewResourceInterceptDic();
                App.getSharePreferenceUtil().putString("WebviewResourceIntercepterCache", optString6);
            }
            cumpResponse2 = cumpResponse4;
        } catch (Exception e) {
            e.printStackTrace();
            cumpResponse2 = cumpResponse4;
        }
        cumpResponse2.setRequestUpdateCache(z);
        cumpResponse2.setCumpEntity(findUnique);
        return cumpResponse2;
    }
}

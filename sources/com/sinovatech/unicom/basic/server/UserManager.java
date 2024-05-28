package com.sinovatech.unicom.basic.server;

import android.text.TextUtils;
import android.util.Base64;
import com.megvii.livenesslib.util.SDCardUtil;
import com.sinovatech.unicom.basic.datacenter.SelectAccountDataCenter;
import com.sinovatech.unicom.basic.p314po.BindNumberEntity;
import com.sinovatech.unicom.basic.p314po.LoginAccountEntity;
import com.sinovatech.unicom.basic.p315ui.CityChangeManager;
import com.sinovatech.unicom.common.DBOpenHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserManager {
    public static final String GuhuaType = "02";
    public static final String PhoneType = "01";
    private static UserManager instance = null;
    public static final String registerType = "05";
    private String LOG = "UserManager";
    private SharePreferenceUtil preference = App.getSharePreferenceUtil();
    private DBOpenHelper dbOpenHelper = new DBOpenHelper(App.getInstance());
    private SelectAccountDataCenter selectAccountDataCenter = new SelectAccountDataCenter(App.getInstance());

    public boolean isTopLevel() {
        return false;
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    private UserManager() {
    }

    public void saveUserAccountName(String str) {
        this.preference.putString("accountName", setJiaMiStr(str));
    }

    public String getUserAccountName() {
        return getJieMiStr(this.preference.getString("accountName"));
    }

    public void saveUserAreaid(String str) {
        this.preference.putString("areaid", str);
    }

    public void removeUserAreaid() {
        this.preference.remove("areaid");
    }

    public String getUserAreaid() {
        return this.preference.getString("areaid");
    }

    public void saveMenuNetType(String str) {
        this.preference.putString("MenuNetType", str);
    }

    public String getMenuNetType() {
        return this.preference.getString("MenuNetType");
    }

    public boolean isYiwang() {
        return "0".equals(getYwCode(getDefaultPhoneNumber()));
    }

    public void saveUserAreaame(String str) {
        this.preference.putString("areaname", str);
    }

    public void removeUserAreaname() {
        this.preference.remove("areaname");
    }

    public String getUserAreaname() {
        return this.preference.getString("areaname");
    }

    public void saveUserAreaCode(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putString("areacode" + getCurrentPhoneNumber(), str);
    }

    public String getUserAreaCode() {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        return sharePreferenceUtil.getString("areacode" + getCurrentPhoneNumber());
    }

    public void saveMobileProvinceName(String str) {
        this.preference.putString("saveMobileProvinceName", str);
    }

    public String getMobileProvinceName() {
        return this.preference.getString("saveMobileProvinceName");
    }

    public void saveUserPassword(String str) {
        this.preference.putString("password", str);
    }

    public String getUserPassword() {
        return this.preference.getString("password");
    }

    public void removeUserPassword() {
        this.preference.remove("password");
    }

    public void saveUserToken(String str) {
        this.preference.putString("login_token", str);
    }

    public String getUserToken() {
        return this.preference.getString("login_token");
    }

    public void removeToken() {
        this.preference.remove("login_token");
    }

    public void saveOnlineToken(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putString("token_online" + str, str2);
    }

    public String getOnlineToken(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        return sharePreferenceUtil.getString("token_online" + str);
    }

    public void saveEcsToken(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putString("ecs_token" + str, str2);
    }

    public String getEcsToken(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        return sharePreferenceUtil.getString("ecs_token" + str);
    }

    public void saveYwCode(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putString("ywCode" + str, str2);
    }

    public String getYwCode(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        return sharePreferenceUtil.getString("ywCode" + str);
    }

    public String getYwCodeDefault() {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        return sharePreferenceUtil.getString("ywCode" + getInstance().getDefaultPhoneNumber());
    }

    public void removeOnlineToken(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putString("token_online" + str, "");
    }

    public void saveInvalidat(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putString("Invalidat" + str, str2);
    }

    public String getInvalidat(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        return sharePreferenceUtil.getString("Invalidat" + str);
    }

    public void saveUserTouxiangURL(String str) {
        this.preference.putString("usertouxiangurl", str);
    }

    public String getUserTouxiangURL() {
        return this.preference.getString("usertouxiangurl");
    }

    public void saveUserPhotoURL(String str, String str2) {
        this.preference.putString(str + "_usertouxiangurl", str2);
    }

    public String getUserPhotoURL(String str) {
        return this.preference.getString(str + "_usertouxiangurl");
    }

    public void removeUserTouxiangURL() {
        this.preference.remove("usertouxiangurl");
    }

    public void saveAutoLoginStatus(boolean z) {
        this.preference.putBoolean("autologin", Boolean.valueOf(z));
    }

    public boolean getAutoLoginStatus() {
        return this.preference.getBoolean("autologin");
    }

    public void saveCollectionOwner(String str) {
        this.preference.putString("collectionOwner", str);
    }

    public String getCollectionOwner() {
        return this.preference.getString("collectionOwner");
    }

    public void removeCollectionOwner() {
        this.preference.remove("collectionOwner");
    }

    public String getCurrentPhoneNumber() {
        String jieMiStr = getJieMiStr(this.preference.getString("defaultPhoneNumber"));
        return (TextUtils.isEmpty(jieMiStr) || !App.hasLogined()) ? "0" : jieMiStr;
    }

    public String getDefaultPhoneNumber() {
        String jieMiStr = getJieMiStr(this.preference.getString("defaultPhoneNumber"));
        return TextUtils.isEmpty(jieMiStr) ? "0" : jieMiStr;
    }

    public void saveCurrentPhoneNumber(String str) {
        this.preference.putString("defaultPhoneNumber", setJiaMiStr(str));
    }

    public void saveUserBindPhoneNumber(String str) {
        this.preference.putString("bindPhoneNumberStr", setJiaMiStr(str));
    }

    public List<BindNumberEntity> getUserBindPhoneNumber() {
        ArrayList arrayList = new ArrayList();
        String jieMiStr = getJieMiStr(this.preference.getString("bindPhoneNumberStr"));
        if (!jieMiStr.trim().equals("") && !"0".equals(jieMiStr)) {
            for (String str : jieMiStr.split(",")) {
                String[] split = str.split("&");
                if (split != null && split.length == 2) {
                    BindNumberEntity bindNumberEntity = new BindNumberEntity();
                    bindNumberEntity.setName(split[0]);
                    bindNumberEntity.setType(split[1]);
                    arrayList.add(bindNumberEntity);
                }
            }
        }
        return arrayList;
    }

    public void removeUserBindPhoneNumber() {
        this.preference.remove("bindPhoneNumberStr");
    }

    public void savePassBackDesmobile(String str) {
        this.preference.putString("passbackdesmobile", setJiaMiStr(str));
    }

    public String getPassBackDesmobile() {
        return getJieMiStr(this.preference.getString("passbackdesmobile"));
    }

    public void removePassBackDesmobile() {
        this.preference.remove("passbackdesmobile");
    }

    private String getJieMiStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new String(RSACryptos.decryptByPrivateKey(Base64.decode(str, 2), Base64.decode("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJCxhm90liVd3oS5uikBTIvKTQXGxL2E/dVPKLYMgsFV8R/GOv0gxzUJK7IcNr2QRIiHyuWDIHwO+dZb3TNz7ErE+cDZ509Eak8N0scTLvw8PoVRcUEzrGEtSVpmFRmIvJH6+2SC02hI+4OHxDdSTq49QGOyWq/PklXu5WS27PSTAgMBAAECgYA9FoYwhiNh8Uih9pvO9hpwJl/wYWPss7pLFZmViqClqXgctx9ugSk5jf6huuGtlLwqfFJAJeVQGy6FvtEcCQQoQ2kb089XOVijf04XltEqFCUVxkUnE+ENtcKCnOSwNXFNQbP25caAyT/0Sf6FqsXAUkSX0EaUMa9e0k76YWsM2QJBAOpIKlVRPET03Q+nnqhMnwZz0Jp7+ytOVTrXCxZcMy1dzRK08y7S97OCbp+pCUYGrKo+NMjk57HLmqF6Vch+NZUCQQCeG01AEAlsupkQx1PIl9adc8qL8fVCqhqTmZBG39UQM0lVoBBBkUv5nxzTuaobwaKXNbepQVXw+yQPJK3yISeHAkEAlvtY5NDMcXgIOr2APt/aIDNk/RnnXRpHTPsm9wsGJDduIJ8ilUt6PGJTXmt2QX2tqq0aIVl7g5Y+GdCYFfRYHQI/HkMbhieLpkQRCCUe5EYrzfdbzW2ChEAK1jWOaAJvxaoLX1hDxEkLQbwyyFPBO47UkBy4Cq12xalMPZnHsZCnAkEAwIt5qL9RTy9LYDq4NI00zKB+/8iuKtery+V+sdE7xrCfI6tDH1Y/qSz5IcdTq2Da0XokZtZkx2sjPYFaF7T49A==", 2)));
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private String setJiaMiStr(String str) {
        try {
            return Base64.encodeToString(RSACryptos.encryptByPublicKey(str.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQsYZvdJYlXd6EubopAUyLyk0FxsS9hP3VTyi2DILBVfEfxjr9IMc1CSuyHDa9kESIh8rlgyB8DvnWW90zc+xKxPnA2edPRGpPDdLHEy78PD6FUXFBM6xhLUlaZhUZiLyR+vtkgtNoSPuDh8Q3Uk6uPUBjslqvz5JV7uVktuz0kwIDAQAB", 2)), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public void removeCurrentPhoneNumber() {
        this.preference.remove("defaultPhoneNumber");
    }

    public void saveCurrentPhoneType(String str) {
        this.preference.putString("currentPhoneType", str);
    }

    public String getCurrentPhoneType() {
        return this.preference.getString("currentPhoneType");
    }

    public void removeCurrentPhoneType() {
        this.preference.remove("currentPhoneType");
    }

    public void saveLoginType(String str) {
        this.preference.putString("loginType", str);
    }

    public String getLoginType() {
        return App.hasLogined() ? this.preference.getString("loginType") : "";
    }

    public void removeLoginType() {
        this.preference.remove("loginType");
    }

    public int getUserBindPhoneCount() {
        return getUserBindPhoneNumber().size();
    }

    public void saveCurrentProvinceCode(String str) {
        this.preference.putString("currentProvinceCode", str);
    }

    public String getCurrentProvinceCode() {
        if (App.hasLogined()) {
            String string = this.preference.getString("currentProvinceCode");
            return TextUtils.isEmpty(string) ? CityChangeManager.DEFAULT_SELECT_CITY_PROVINCE_CODE : string;
        }
        return getLocateProvinceCode();
    }

    public void removeCurrentProvinceCode() {
        this.preference.remove("currentProvinceCode");
    }

    public void saveCurrentProvinceName(String str) {
        this.preference.putString("currentProvinceName", str);
    }

    public String getCurrentProvinceName() {
        return this.preference.getString("currentProvinceName");
    }

    public void removeCurrentProvinceName() {
        this.preference.remove("currentProvinceName");
    }

    public void saveCurrentCityCode(String str) {
        this.preference.putString("currentCityCode", str);
    }

    public String getCurrentCityCode() {
        if (App.hasLogined()) {
            String string = this.preference.getString("currentCityCode");
            return TextUtils.isEmpty(string) ? CityChangeManager.DEFAULT_SELECT_CITY_CODE : string;
        }
        return getLocateCityCode();
    }

    public void removeCurrentCityCode() {
        this.preference.remove("currentCityCode");
    }

    public void saveLocateProvinceCode(String str) {
        this.preference.putString("locateProvinceCode", str);
    }

    public String getLocateProvinceCode() {
        return TextUtils.isEmpty(this.preference.getString("locateProvinceCode")) ? CityChangeManager.DEFAULT_SELECT_CITY_PROVINCE_CODE : this.preference.getString("locateProvinceCode");
    }

    public void removeLocateProvinceCode() {
        this.preference.remove("locateProvinceCode");
    }

    public void saveLocateCityCode(String str) {
        this.preference.putString("locateCityCode", str);
    }

    public void saveLocateCityName(String str) {
        this.preference.putString(CityChangeManager.PREFERENCE_SELECT_KEY, str);
    }

    public void saveUserLocateCityName(String str) {
        this.preference.putString(CityChangeManager.PREFERENCE_CITY_SELECT_KEY_LOCATION(), str);
    }

    public void saveUserLocateCityTime(long j) {
        String string = App.getSharePreferenceUtil().getString(CityChangeManager.PREFERENCE_SELECT_KEY);
        MsLogUtil.m7979d("huancun", "缓存的城市名称 = " + string);
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putLong(CityChangeManager.PREFERENCE_SELECTCITYTIME_KEY() + string, j);
    }

    public void saveAutoCity(boolean z) {
        this.preference.putBoolean("saveAutoCity", Boolean.valueOf(z));
    }

    public boolean getAutoCity() {
        return this.preference.getBoolean("saveAutoCity");
    }

    public String getLocateCityCode() {
        return TextUtils.isEmpty(this.preference.getString("locateCityCode")) ? CityChangeManager.DEFAULT_SELECT_CITY_CODE : this.preference.getString("locateCityCode");
    }

    public void removeLocateCityCode() {
        this.preference.remove("locateCityCode");
    }

    public void saveKeyVersion(String str) {
        this.preference.putString("keyVersion", str);
    }

    public String getKeyVersion() {
        return this.preference.getString("keyVersion");
    }

    public void removeKeyVersion() {
        this.preference.remove("keyVersion");
    }

    public void saveSelectAccountName(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.selectAccountDataCenter.insertSelectAccountData(str, str2, str3, str4, str5, str6, str7);
        this.selectAccountDataCenter.updateSelectAccountIcon(str, getUserTouxiangURL());
    }

    public void saveSelectAccountName(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.selectAccountDataCenter.insertSelectAccountData(str, str2, str3, str4, str5, str6, str7, str8);
        this.selectAccountDataCenter.updateSelectAccountIcon(str, getUserTouxiangURL());
    }

    public void saveSelectAccountName(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.selectAccountDataCenter.insertSelectAccountData(str, str2, str3, str4, str5, str6, str7, str8, str9);
        this.selectAccountDataCenter.updateSelectAccountIcon(str, str7);
    }

    public void saveIconurl(String str, String str2) {
        this.selectAccountDataCenter.updateSelectAccountIcon(str, str2);
    }

    public LoginAccountEntity getSelectAccountName(String str, String str2) {
        return this.selectAccountDataCenter.getSelectAccountData(str, str2);
    }

    public List<LoginAccountEntity> getSelectAccountNameList(String str) {
        return this.selectAccountDataCenter.getSelectAccountDataList(str, null);
    }

    public List<LoginAccountEntity> getBindAccountNameList(String str) {
        return this.selectAccountDataCenter.getSelectAccountDataList(str, "1");
    }

    public void removeSelectAccountName(String str) {
        this.selectAccountDataCenter.deleteSelectAccountData(str);
    }

    public void updateSelectAccountIcon(String str, String str2) {
        this.selectAccountDataCenter.updateSelectAccountIcon(str, str2);
    }

    public String getShowFlower() {
        return this.preference.getString("showflower");
    }

    public void saveShowFlower(String str) {
        this.preference.putString("showflower", str);
    }

    public void removeShowFlower() {
        this.preference.remove("showflower");
    }

    public String getLoginAppId() {
        String string = this.preference.getString("loginAppId");
        if (TextUtils.isEmpty(string)) {
            try {
                string = FileTools.readAppid(new File(SDCardUtil.getDocumentFileUrl("") + "appid"));
                if (TextUtils.isEmpty(string)) {
                    string = "ChinaunicomMobileBusiness";
                }
            } catch (Exception e) {
                e.printStackTrace();
                string = "ChinaunicomMobileBusiness";
            }
        }
        if (string != null) {
            try {
                string = string.trim();
            } catch (Exception e2) {
                e2.printStackTrace();
                return string;
            }
        }
        if (TextUtils.isEmpty(string)) {
            string = "ChinaunicomMobileBusiness";
        }
        return string != null ? string.length() < 5 ? "ChinaunicomMobileBusiness" : string : string;
    }

    public void saveLoginAppId(String str) {
        if (str != null) {
            str = str.trim();
        }
        if (TextUtils.isEmpty(str)) {
            PvCurrencyLogUtils.pvCurrencyLog("", 3, "appid0001", "", "", "appid为空", "", "", "", "");
            return;
        }
        this.preference.putString("loginAppId", str);
        try {
            FileTools.saveAppid(str, new File(SDCardUtil.getDocumentFileUrl("") + "appid"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeAllAccount() {
        this.selectAccountDataCenter.deleteAllAccountData();
    }

    public void saveLimitInterface(String str) {
        this.preference.putString("limitInterface", str);
    }

    public void removeLimitInterface() {
        this.preference.remove("limitInterface");
    }

    public String getLimitInterface() {
        return this.preference.getString("limitInterface");
    }

    public void saveMainMemberFlag(String str) {
        this.preference.putString("main_member_flag", str);
    }

    public void removeMainMemberFlag() {
        this.preference.remove("main_member_flag");
    }

    public String getMainMemberFlag() {
        return this.preference.getString("main_member_flag");
    }

    public void putAgeFlag(String str) {
        this.preference.putString("ageFlag", str);
    }

    public String getAgeFlag() {
        return this.preference.getString("ageFlag");
    }

    public String getFaceType() {
        return this.preference.getString("face_login_type");
    }

    public void setFaceType(String str) {
        this.preference.putString("face_login_type", str);
    }

    public String getBirthdayMonth() {
        return this.preference.getString(getCurrentPhoneNumber() + "birthdayMonth");
    }

    public void setBirthdayMonth(String str) {
        this.preference.putString(getCurrentPhoneNumber() + "birthdayMonth", str);
    }

    public void setIdCardKey(String str) {
        this.preference.putString("broad_login_idcard", setJiaMiStr(str));
    }

    public String getIdCardKey() {
        return getJieMiStr(this.preference.getString("broad_login_idcard"));
    }

    public void setIdCardKeyJiaMi(String str) {
        this.preference.putString("broad_login_idcard_jiami", str);
    }

    public String getIdCardKeyJiaMi() {
        return this.preference.getString("broad_login_idcard_jiami");
    }

    public void savePriToken(String str) {
        this.preference.putString("pri_token", str);
    }

    public String getPriToken() {
        return this.preference.getString("pri_token");
    }

    public void setLevel(int i) {
        MsLogUtil.m7979d("JIA-LEVEL", "设置用户星级:" + i);
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putInt("user_level_" + getCurrentPhoneNumber(), i);
    }

    public int getLevel() {
        if (App.hasLogined()) {
            SharePreferenceUtil sharePreferenceUtil = this.preference;
            return sharePreferenceUtil.getInt("user_level_" + getCurrentPhoneNumber());
        }
        return 0;
    }

    public String getXid(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        return sharePreferenceUtil.getString("login_aes" + EncodeHelper.encoderByMd5(str));
    }

    public void setXid(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putString("login_aes" + EncodeHelper.encoderByMd5(str), str2);
    }

    public String getLoid(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        return sharePreferenceUtil.getString("login_loid" + EncodeHelper.encoderByMd5(str));
    }

    public void setLoid(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putString("login_loid" + EncodeHelper.encoderByMd5(str), str2);
    }

    public String getDuid(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        return sharePreferenceUtil.getString("login_duid" + EncodeHelper.encoderByMd5(str));
    }

    public void setDuid(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putString("login_duid" + EncodeHelper.encoderByMd5(str), str2);
    }

    public String getLoginTimestamp(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        return sharePreferenceUtil.getString("login_timestamp" + EncodeHelper.encoderByMd5(str));
    }

    public void setLoginTimestamp(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = this.preference;
        sharePreferenceUtil.putString("login_timestamp" + EncodeHelper.encoderByMd5(str), str2);
    }

    public void setSimplePwdType(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.trim();
        }
        if ("null".equals(str)) {
            str = "";
        }
        this.preference.putString("simplepwd_type", str);
    }

    public String getSimplePwdType() {
        return this.preference.getString("simplepwd_type");
    }
}

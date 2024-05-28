package com.sinovatech.unicom.separatemodule.login.fengkong;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoginLogPv {
    public static final String FIRST_OTHER_LOGIN_10 = "first_other_login_10";
    public static final String LOGIN_LEAVE_02 = "login_leave_02";
    public static final String RE_LOGIN_LEAVE_05 = "re_login_leave_05";
    public static final String RE_OTHER_LOGIN_11 = "RE_OTHER_LOGIN_11";
    public static final String RE_SMS_LOGIN_04 = "re_sms_resend_04";
    public static final String RE_VOICE_SEND_07 = "re_voice_send_07";
    public static final String SMS_LOGIN_01 = "sms_login_01";
    public static final String SMS_RESEND_03 = "sms_resend_03";
    public static final String VOICE_LEAVE_09 = "voice_leave_09";
    public static final String VOICE_LOGIN_08 = "voice_login_08";
    public static final String VOICE_SEND_06 = "voice_send_06";
    private String loginType;
    private String mobile;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private Map<String, Integer> sendSmsCountMap = new HashMap();
    private Map<String, Integer> sendVoiceCountMap = new HashMap();
    private Map<String, Boolean> overFlagMap = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$uploadLogin$0(String str) throws Exception {
    }

    public void log(String str) {
        if (isOverFlag()) {
            return;
        }
        String str2 = "";
        String str3 = "";
        char c = 65535;
        switch (str.hashCode()) {
            case 130028798:
                if (str.equals(VOICE_LEAVE_09)) {
                    c = '\b';
                    break;
                }
                break;
            case 168152837:
                if (str.equals(RE_VOICE_SEND_07)) {
                    c = 6;
                    break;
                }
                break;
            case 354500285:
                if (str.equals(SMS_LOGIN_01)) {
                    c = 0;
                    break;
                }
                break;
            case 549544051:
                if (str.equals(FIRST_OTHER_LOGIN_10)) {
                    c = '\t';
                    break;
                }
                break;
            case 575168267:
                if (str.equals(VOICE_LOGIN_08)) {
                    c = 7;
                    break;
                }
                break;
            case 631461910:
                if (str.equals(RE_SMS_LOGIN_04)) {
                    c = 3;
                    break;
                }
                break;
            case 693310161:
                if (str.equals(RE_OTHER_LOGIN_11)) {
                    c = '\n';
                    break;
                }
                break;
            case 967054496:
                if (str.equals(LOGIN_LEAVE_02)) {
                    c = 1;
                    break;
                }
                break;
            case 1379025936:
                if (str.equals(VOICE_SEND_06)) {
                    c = 5;
                    break;
                }
                break;
            case 1842335009:
                if (str.equals(SMS_RESEND_03)) {
                    c = 2;
                    break;
                }
                break;
            case 2084694063:
                if (str.equals(RE_LOGIN_LEAVE_05)) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                str2 = "01短信验证码";
                str3 = "01首次获取，输入验证码并提交登录";
                setOverFlagMap();
                break;
            case 1:
                str2 = "01短信验证码";
                str3 = "02首次获取，离开登录页（未重新获取）";
                break;
            case 2:
                str2 = "01短信验证码";
                str3 = "03首次获取，验证码倒计时结束，重新获取验证码";
                break;
            case 3:
                str2 = "01短信验证码";
                str3 = "04非首次获取，输入验证码并提交登录";
                setOverFlagMap();
                break;
            case 4:
                str2 = "01短信验证码";
                str3 = "05非首次获取，离开登录页（未重新获取）";
                break;
            case 5:
                str2 = "02语音验证码";
                str3 = "06首次获取，获取语音验证码";
                break;
            case 6:
                str2 = "02语音验证码";
                str3 = "07非首次获取，获取语音验证码";
                break;
            case 7:
                str2 = "02语音验证码";
                str3 = "08获取语音验证码，输入验证码并提交登录";
                setOverFlagMap();
                break;
            case '\b':
                str2 = "02语音验证码";
                str3 = "09获取语音验证码，离开登录页";
                break;
            case '\t':
                str2 = "03其它方式登录";
                str3 = "10首次获取验证码后，使用其他登录方式登录";
                setOverFlagMap();
                break;
            case '\n':
                str2 = "03其它方式登录";
                str3 = "11非首次获取验证码后，使用其他登录方式登录";
                setOverFlagMap();
                break;
        }
        uploadLogin(str2, str3);
        MsLogUtil.m7979d("LoginLogPv-dhy", str);
    }

    public void setOverFlagMap() {
        if (TextUtils.isEmpty(this.mobile)) {
            return;
        }
        this.overFlagMap.put(this.mobile, true);
    }

    public boolean isOverFlag() {
        Boolean bool = this.overFlagMap.get(this.mobile);
        if (bool == null) {
            bool = false;
        }
        return bool.booleanValue();
    }

    public void addSmsCount() {
        if (TextUtils.isEmpty(this.mobile)) {
            return;
        }
        this.sendSmsCountMap.put(this.mobile, Integer.valueOf(getSmsCount() + getVoiceCount().intValue() + 1));
    }

    public int getSmsCount() {
        if (this.sendSmsCountMap == null || TextUtils.isEmpty(this.mobile)) {
            return -1;
        }
        Integer num = this.sendSmsCountMap.get(this.mobile);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public void addVoiceCount() {
        if (TextUtils.isEmpty(this.mobile)) {
            return;
        }
        this.sendVoiceCountMap.put(this.mobile, Integer.valueOf(getVoiceCount().intValue() + getSmsCount() + 1));
    }

    public Integer getVoiceCount() {
        if (this.sendVoiceCountMap != null && !TextUtils.isEmpty(this.mobile)) {
            Integer num = this.sendVoiceCountMap.get(this.mobile);
            if (num == null) {
                return 0;
            }
            return num;
        }
        return -1;
    }

    public void setMobile(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mobile = "0";
        } else {
            this.mobile = str;
        }
    }

    public void setLoginType(String str) {
        this.loginType = str;
    }

    public void uploadLogin(String str, String str2) {
        MsLogUtil.m7979d("LoginLogPv", "remark3:" + str2);
        HashMap hashMap = new HashMap();
        hashMap.put("transId", "S2ndpage1243");
        hashMap.put("bizcode", "S2n");
        hashMap.put("upTime", this.sdf.format(new Date()));
        hashMap.put("mobile", this.mobile);
        hashMap.put("touchcode", "");
        hashMap.put("provId", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("cityId", UserManager.getInstance().getCurrentCityCode());
        hashMap.put("page", "");
        hashMap.put("urlApp", "");
        hashMap.put("version", App.getInstance().getResources().getString(2131886969));
        hashMap.put("clientType", "Android");
        hashMap.put("remark1", DeviceHelper.getDeviceID(false));
        hashMap.put("remark2", LoginManager.getAccountType());
        hashMap.put("remark3", str2);
        String str3 = "";
        if ("1".equals(this.loginType)) {
            str3 = "密码登录";
        } else if ("2".equals(this.loginType)) {
            str3 = "宽带账号/编码登录";
        } else if ("3".equals(this.loginType)) {
            str3 = "短信验证码登录";
        } else if ("4".equals(this.loginType)) {
            str3 = "固话";
        } else if ("5".equals(this.loginType)) {
            str3 = "身份证登录";
        }
        hashMap.put("remark4", str3);
        hashMap.put("sessionid", App.getPvLogSessionId());
        hashMap.put("biz_proecess", "1");
        hashMap.put("page_new_old_user", CacheDataCenter.getInstance().getPageNewOldUser());
        hashMap.put("actCode", str);
        hashMap.put("titleName", "");
        hashMap.put("menuId", "");
        hashMap.put("upType", "01短信验证码登录");
        hashMap.put("baseConvert", "");
        App.getAsyncHttpClient(5, 5, 5).rxPost(new ConfigManager(App.getInstance()).getStasticsUploadUrlKey(), hashMap).subscribeOn(Schedulers.m1934io()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.login.fengkong.-$$Lambda$LoginLogPv$GYa0QZyg9k077b-Q2blhwnT83CY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LoginLogPv.lambda$uploadLogin$0((String) obj);
            }
        });
    }
}

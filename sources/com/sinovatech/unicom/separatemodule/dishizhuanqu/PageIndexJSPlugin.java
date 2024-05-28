package com.sinovatech.unicom.separatemodule.dishizhuanqu;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.eventbus.CustomActivityEvent;
import com.sinovatech.unicom.basic.eventbus.FinishActivityEvent;
import com.sinovatech.unicom.basic.p315ui.activity.CustomMainActivity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.home.util.DialogUtils;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.LaunchModeUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/pageIndex")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PageIndexJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        return null;
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String optString = this.parameterJO.optString("type");
            char c = 1;
            if (!TextUtils.isEmpty(optString)) {
                if (TextUtils.equals("get", optString)) {
                    String string = App.getSharePreferenceUtil().getString("unicom_app_main_type");
                    if (TextUtils.isEmpty(string)) {
                        string = "0";
                    }
                    String string2 = TextUtils.equals("0", string) ? "" : App.getSharePreferenceUtil().getString("unicom_app_main_url");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("pageType", string);
                    jSONObject.put("pageName", string2);
                    callbackSuccess(jSONObject);
                    return;
                }
                JSONObject optJSONObject = this.parameterJO.optJSONObject("pageInfo");
                if (optJSONObject == null || !TextUtils.equals("set", optString)) {
                    return;
                }
                String optString2 = optJSONObject.optString("pageType");
                String optString3 = optJSONObject.optString("pageName");
                switch (optString2.hashCode()) {
                    case 48:
                        if (optString2.equals("0")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 49:
                        if (optString2.equals("1")) {
                            break;
                        }
                        c = 65535;
                        break;
                    case 50:
                        if (optString2.equals("2")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 51:
                    default:
                        c = 65535;
                        break;
                    case 52:
                        if (optString2.equals("4")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        if (App.getSharePreferenceUtil().getBoolean("BasicCustom")) {
                            DialogUtils.checkYinsiTiShiDialog(this.activityContext, "3");
                            return;
                        } else {
                            LaunchModeUtils.changeAppNorml(this.activityContext, new LaunchModeUtils.OnChangeLaunchModeListener() { // from class: com.sinovatech.unicom.separatemodule.dishizhuanqu.PageIndexJSPlugin.1
                                @Override // com.sinovatech.unicom.common.LaunchModeUtils.OnChangeLaunchModeListener
                                public void onSuccess() {
                                    UIUtils.relaunchApp();
                                }

                                @Override // com.sinovatech.unicom.common.LaunchModeUtils.OnChangeLaunchModeListener
                                public void onFail(String str) {
                                    PageIndexJSPlugin.this.callbackFail("false", str);
                                }
                            });
                            return;
                        }
                    case 1:
                        LaunchModeUtils.changeCare(this.activityContext, new LaunchModeUtils.OnChangeLaunchModeListener() { // from class: com.sinovatech.unicom.separatemodule.dishizhuanqu.PageIndexJSPlugin.2
                            @Override // com.sinovatech.unicom.common.LaunchModeUtils.OnChangeLaunchModeListener
                            public void onSuccess() {
                                EventBusUtils.post(new FinishActivityEvent(0));
                                Intent intent = new Intent(PageIndexJSPlugin.this.activityContext, CustomMainActivity.class);
                                intent.putExtra("ms_unicom_url", "ms_unicom_guanhuai");
                                PageIndexJSPlugin.this.activityContext.startActivity(intent);
                            }

                            @Override // com.sinovatech.unicom.common.LaunchModeUtils.OnChangeLaunchModeListener
                            public void onFail(String str) {
                                PageIndexJSPlugin.this.callbackFail("false", str);
                            }
                        });
                        return;
                    case 2:
                        LaunchModeUtils.changeLanguage(optString3, new LaunchModeUtils.OnChangeLaunchModeLanguageListener() { // from class: com.sinovatech.unicom.separatemodule.dishizhuanqu.PageIndexJSPlugin.3
                            @Override // com.sinovatech.unicom.common.LaunchModeUtils.OnChangeLaunchModeLanguageListener
                            public void onSuccess(LanguageEntity languageEntity) {
                                PageIndexJSPlugin.this.appLanguage(languageEntity);
                            }

                            @Override // com.sinovatech.unicom.common.LaunchModeUtils.OnChangeLaunchModeLanguageListener
                            public void onFail(String str) {
                                PageIndexJSPlugin.this.callbackFail("false", str);
                            }
                        });
                        return;
                    case 3:
                        DialogUtils.checkYinsiTiShiDialog(this.activityContext, "4");
                        return;
                    default:
                        callbackFail("false", "不支持该种方式的自定义启动页!");
                        return;
                }
            }
            App.isCityToHome = true;
            this.activityContext.startActivity(new Intent(this.activityContext, MainActivity.class));
            this.activityContext.finish();
        } catch (Exception e) {
            callbackFail(e.getMessage());
        }
    }

    public void appNormal() {
        StatisticsUploadUtils.upload(this.activityContext, "7", "H5-设置", "", "", "原生首页-打开", "");
        EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH));
        App.getSharePreferenceUtil().putBoolean("CareHome", false);
        LanguageUtil.setLanguage(LanguageUtil.CHN_CN, "");
        App.mainTagFromOtherActivity = MainActivity.Fragment_Home;
        this.activityContext.startActivity(new Intent(this.activityContext, MainActivity.class));
    }

    public void appCare() {
        try {
            if (LanguageUtil.isWeiWen()) {
                LanguageUtil.setLanguage(LanguageUtil.CHN_CN, "");
            }
            App.getSharePreferenceUtil().putString("unicom_app_main_type", "1");
            App.getSharePreferenceUtil().putString("unicom_app_main_url", "ms_unicom_guanhuai");
            EventBusUtils.post(new FinishActivityEvent(0));
            Intent intent = new Intent(this.activityContext, CustomMainActivity.class);
            intent.putExtra("ms_unicom_url", "ms_unicom_guanhuai");
            this.activityContext.startActivity(intent);
            App.getSharePreferenceUtil().putBoolean("CareHome", true);
            StatisticsUploadUtils.upload(this.activityContext, "7", "H5-设置", "", "", "关怀版切换按钮-打开", "");
        } catch (Exception e) {
            callbackFail("false", "设置关怀版异常:" + e.getMessage());
        }
    }

    public void appLanguage(LanguageEntity languageEntity) {
        try {
            StatisticsUploadUtils.upload("7", "H5-设置-多语言", "", "", languageEntity.getLanguageCode(), "");
            App.getSharePreferenceUtil().putBoolean("CareHome", false);
            LanguageUtil.getInstance().applyLanguage(languageEntity.getLanguageCode(), languageEntity.getRightToleft(), languageEntity.getUrl());
        } catch (Exception e) {
            callbackFail("false", "设置多语言异常:" + e.getMessage());
        }
    }

    private boolean isShowGuanHuai() {
        try {
            if (!App.hasLogined() || UserManager.getInstance().isYiwang()) {
                return false;
            }
            String careProvinceSwitch = new ConfigManager(this.activityContext).getCareProvinceSwitch();
            String currentProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
            if (TextUtils.isEmpty(careProvinceSwitch)) {
                return false;
            }
            if (careProvinceSwitch.contains("098") || careProvinceSwitch.contains(currentProvinceCode)) {
                String loginType = UserManager.getInstance().getLoginType();
                if (!"01".equals(loginType) && !"06".equals(loginType) && !"999".equals(loginType) && !"27".equals(loginType)) {
                    if (!"23".equals(loginType)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isShowLanguage() {
        try {
            if (TextUtils.equals("0", new ConfigManager(this.activityContext).getLanguageSettingSwitch())) {
                return App.hasLogined();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

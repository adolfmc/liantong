package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity;
import com.sinovatech.unicom.basic.p315ui.utils.UnicomHomeConstants;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.MultilingualManagerCustomDialog;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.PreferenceConstUtils;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.MultilingualManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MultilingualManager {
    public static final String CARE_SEVEN_KEY = "CARE_SEVEN_KEY";
    private static String TAG = "MultilingualManager";
    private static MultilingualManager multilingualManager;
    private static Dialog usaDialog;
    private LanguageEntity entity;

    public static MultilingualManager getInstance() {
        if (multilingualManager == null) {
            multilingualManager = new MultilingualManager();
        }
        return multilingualManager;
    }

    public void showDialog(final AppCompatActivity appCompatActivity, final int i) {
        try {
            if (i == 2) {
                getNetCareDialogContent(appCompatActivity);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("version", appCompatActivity.getString(2131886969));
            hashMap.put("languageType", LanguageUtil.getInstance().getLocalLanguage().getLanguageCode());
            ((ObservableSubscribeProxy) App.getAsyncHttpClient(2, 2, 2).rxPost(URLSet.getWeiWenDialogUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, Map<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.manager.MultilingualManager.3
                @Override // io.reactivex.functions.Function
                public Map<String, String> apply(@NonNull String str) throws Exception {
                    JSONObject optJSONObject;
                    try {
                        String str2 = MultilingualManager.TAG;
                        MsLogUtil.m7979d(str2, "多语言接口数据\n" + str);
                        JSONObject jSONObject = new JSONObject(str);
                        String optString = jSONObject.optString("code");
                        JSONObject optJSONObject2 = jSONObject.optJSONObject("data");
                        HashMap hashMap2 = new HashMap();
                        if ("0000".equals(optString) && optJSONObject2 != null && TextUtils.equals("1", optJSONObject2.optString("popFlag")) && (optJSONObject = optJSONObject2.optJSONObject("popInfo")) != null) {
                            String optString2 = optJSONObject.optString("conten");
                            String optString3 = optJSONObject.optString("btnLeft");
                            String optString4 = optJSONObject.optString("btnRight");
                            String optString5 = optJSONObject.optString("butStatus");
                            hashMap2.put("dialogContent", optString2);
                            hashMap2.put("btnLeft", optString3);
                            hashMap2.put("btnRight", optString4);
                            hashMap2.put("butStatus", optString5);
                        }
                        JSONObject optJSONObject3 = optJSONObject2.optJSONObject("care");
                        if (optJSONObject3 != null) {
                            hashMap2.put("care_title", optJSONObject3.optString("title"));
                            hashMap2.put("care_butStatus", optJSONObject3.optString("butStatus"));
                            hashMap2.put("care_clean", optJSONObject3.optString("clean"));
                            hashMap2.put("care_switchBut", optJSONObject3.optString("switchBut"));
                            return hashMap2;
                        }
                        return null;
                    } catch (Exception e) {
                        String str3 = MultilingualManager.TAG;
                        MsLogUtil.m7977e(str3, "多语言接口异常:" + e.getMessage());
                        return null;
                    }
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity))).subscribe(new Consumer<Map<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.manager.MultilingualManager.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Map<String, String> map) throws Exception {
                    if (map != null) {
                        try {
                            String str = map.get("dialogContent");
                            String str2 = map.get("btnLeft");
                            String str3 = map.get("btnRight");
                            String str4 = map.get("butStatus");
                            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                                MultilingualManager.this.showLanguageDiaLog(appCompatActivity, str, str2, str3, str4, i, map);
                            }
                        } catch (Exception e) {
                            String str5 = MultilingualManager.TAG;
                            MsLogUtil.m7977e(str5, "多语言弹窗展示异常:" + e.getMessage());
                        }
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.manager.MultilingualManager.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str = MultilingualManager.TAG;
                    MsLogUtil.m7977e(str, "多语言接口异常" + th.getMessage());
                    if (i == 3) {
                        MultilingualManager.this.getNetCareDialogContent(appCompatActivity);
                    }
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "多语言接口异常:" + e.getMessage());
        }
    }

    public void showLanguageDiaLog(final AppCompatActivity appCompatActivity, String str, String str2, String str3, String str4, final int i, final Map<String, String> map) {
        try {
            UnicomHomeConstants.isShowLanguageWindow = false;
            App.getSharePreferenceUtil().putString(PreferenceConstUtils.first_Switch_Refuse(LanguageUtil.getInstance().getLocalLanguage().getLanguageCode()), "1");
            this.entity = LanguageUtil.getInstance().isExist(LanguageUtil.getInstance().getLocalLanguage().getLanguageCode());
            if (UIUtils.isDismissDialog(appCompatActivity, usaDialog)) {
                usaDialog.dismiss();
            }
            if (this.entity != null) {
                usaDialog = MultilingualManagerCustomDialog.create(appCompatActivity, str, true, str2, str3, str4, LanguageUtil.getInstance().getLocalLanguage().getLanguageCode().equals(LanguageUtil.USA), new MultilingualManagerCustomDialog.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.manager.MultilingualManager.4
                    @Override // com.sinovatech.unicom.basic.view.MultilingualManagerCustomDialog.CustomeDialogListener
                    public void onClickOk() {
                        try {
                            if (MultilingualManager.this.entity == null || !App.hasLogined() || TextUtils.isEmpty(MultilingualManager.this.entity.getUrl())) {
                                return;
                            }
                            StatisticsUploadUtils.upload("S2ndpage1037", "首页", MultilingualManager.this.entity.getLanguageCode(), "", "确定", "");
                            LanguageUtil.getInstance().applyLanguage(MultilingualManager.this.entity.getLanguageCode(), MultilingualManager.this.entity.getRightToleft(), MultilingualManager.this.entity.getUrl());
                        } catch (Exception e) {
                            UIUtils.logE(e.getMessage());
                        }
                    }

                    @Override // com.sinovatech.unicom.basic.view.MultilingualManagerCustomDialog.CustomeDialogListener
                    public void onClickCancel() {
                        StatisticsUploadUtils.upload("S2ndpage1037", "首页", MultilingualManager.this.entity.getLanguageCode(), "", "取消", "");
                        if (i == 3) {
                            MultilingualManager.this.showCareDialog(appCompatActivity, map);
                        }
                    }

                    @Override // com.sinovatech.unicom.basic.view.MultilingualManagerCustomDialog.CustomeDialogListener
                    public void onShow() {
                        LanguageUtil.getInstance().setMultilingualDialogValue(1);
                        LanguageUtil.getInstance().saveSevenDayTime(System.currentTimeMillis());
                    }

                    @Override // com.sinovatech.unicom.basic.view.MultilingualManagerCustomDialog.CustomeDialogListener
                    public void isChecked(boolean z) {
                        if (z) {
                            LanguageUtil.getInstance().saveSevenDayTime(0L);
                        } else {
                            LanguageUtil.getInstance().saveSevenDayTime(System.currentTimeMillis());
                        }
                    }
                });
                if (UIUtils.isShowDialog(appCompatActivity, usaDialog)) {
                    usaDialog.show();
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7978e("显示多语言弹窗异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCareDialog(final Activity activity, Map<String, String> map) {
        if (map != null) {
            try {
                if (map.size() == 0) {
                    return;
                }
                String str = map.get("care_title");
                String str2 = map.get("care_butStatus");
                String str3 = map.get("care_clean");
                String str4 = map.get("care_switchBut");
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                    saveCareDialogTime(System.currentTimeMillis());
                    App.getSharePreferenceUtil().putBoolean("isCareModel", true);
                    CustomDialogManager.show(activity, "", str, str3, str4, true, str2, new CustomDialogManager.CustomeDialogCheckBoxListener() { // from class: com.sinovatech.unicom.basic.ui.manager.MultilingualManager.5
                        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogCheckBoxListener
                        public void onCancle() {
                        }

                        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogCheckBoxListener
                        public void onClickOk() {
                            Intent intent = new Intent(activity, SettingNewActivity.class);
                            intent.putExtra("opencare", true);
                            activity.startActivity(intent);
                        }

                        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogCheckBoxListener
                        public void onCheck(boolean z) {
                            if (z) {
                                MultilingualManager.this.saveCareDialogTime(0L);
                            } else {
                                MultilingualManager.this.saveCareDialogTime(System.currentTimeMillis());
                            }
                        }
                    });
                }
            } catch (Exception e) {
                String str5 = TAG;
                MsLogUtil.m7977e(str5, "关怀版弹窗异常:" + e.getMessage());
            }
        }
    }

    public int checkDialog() {
        try {
            boolean isMultilingualDialog = LanguageUtil.getInstance().isMultilingualDialog();
            if (isMultilingualDialog && isShowCareDialog()) {
                return 3;
            }
            if (!isMultilingualDialog || isShowCareDialog()) {
                if (isMultilingualDialog || !isShowCareDialog()) {
                    MsLogUtil.m7980d("无可用条件");
                    return 0;
                }
                return 2;
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private boolean isGeneral() {
        if (App.hasLogined() && !UserManager.getInstance().isYiwang()) {
            String careProvinceSwitch = new ConfigManager().getCareProvinceSwitch();
            String userAreaid = UserManager.getInstance().getUserAreaid();
            if (TextUtils.isEmpty(careProvinceSwitch)) {
                return false;
            }
            if ((careProvinceSwitch.contains("098") || careProvinceSwitch.contains(userAreaid)) && "01".equals(UserManager.getInstance().getCurrentPhoneType())) {
                return "1".equals(UserManager.getInstance().getAgeFlag()) || UIUtils.isBigSystemFontSize(App.getInstance());
            }
            return false;
        }
        return false;
    }

    private boolean isShowCareDialog() {
        return isAnswer() && isGeneral();
    }

    private boolean isAnswer() {
        if (isCareDialogToSevenDay() && App.getSharePreferenceUtil().getBoolean("isCareModel")) {
            saveCareDialogTime(0L);
            return true;
        } else if (App.getSharePreferenceUtil().getBoolean("isCareModel") || App.getSharePreferenceUtil().getLong(CARE_SEVEN_KEY) != 0) {
            return (!App.getSharePreferenceUtil().getBoolean("isCareModel") || App.getSharePreferenceUtil().getLong(CARE_SEVEN_KEY) == 0) ? false : false;
        } else {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveCareDialogTime(long j) {
        App.getSharePreferenceUtil().putLong(CARE_SEVEN_KEY, j);
    }

    public boolean isCareDialogToSevenDay() {
        long j = App.getSharePreferenceUtil().getLong(CARE_SEVEN_KEY);
        if (j == 0) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(5, 7);
        long timeInMillis = calendar.getTimeInMillis();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        long timeInMillis2 = calendar2.getTimeInMillis();
        MsLogUtil.m7979d("时间值", timeInMillis2 + "当前时间");
        MsLogUtil.m7979d("时间值", timeInMillis + "七天后的时间");
        return timeInMillis2 >= timeInMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getNetCareDialogContent(final AppCompatActivity appCompatActivity) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("version", appCompatActivity.getString(2131886969));
            hashMap.put("languageType", LanguageUtil.getInstance().getLocalLanguage().getLanguageCode());
            ((ObservableSubscribeProxy) App.getAsyncHttpClient(2, 2, 2).rxPost(URLSet.getWeiWenDialogUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, Map<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.manager.MultilingualManager.8
                @Override // io.reactivex.functions.Function
                public Map<String, String> apply(@NonNull String str) throws Exception {
                    try {
                        JSONObject optJSONObject = new JSONObject(str).optJSONObject("data");
                        if (optJSONObject != null) {
                            HashMap hashMap2 = new HashMap();
                            JSONObject optJSONObject2 = optJSONObject.optJSONObject("care");
                            if (optJSONObject2 != null) {
                                hashMap2.put("care_title", optJSONObject2.optString("title"));
                                hashMap2.put("care_butStatus", optJSONObject2.optString("butStatus"));
                                hashMap2.put("care_clean", optJSONObject2.optString("clean"));
                                hashMap2.put("care_switchBut", optJSONObject2.optString("switchBut"));
                                return hashMap2;
                            }
                            return null;
                        }
                        return null;
                    } catch (Exception e) {
                        String str2 = MultilingualManager.TAG;
                        MsLogUtil.m7977e(str2, "关怀版接口异常:" + e.getMessage());
                        return null;
                    }
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity))).subscribe(new Consumer<Map<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.manager.MultilingualManager.6
                @Override // io.reactivex.functions.Consumer
                public void accept(Map<String, String> map) throws Exception {
                    try {
                        MultilingualManager.this.showCareDialog(appCompatActivity, map);
                    } catch (Exception e) {
                        String str = MultilingualManager.TAG;
                        MsLogUtil.m7977e(str, "关怀弹窗展示异常:" + e.getMessage());
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.manager.MultilingualManager.7
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str = MultilingualManager.TAG;
                    MsLogUtil.m7977e(str, "关怀版弹窗接口异常" + th.getMessage());
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "关怀版本:" + e.getMessage());
        }
    }
}

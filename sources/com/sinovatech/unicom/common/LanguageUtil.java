package com.sinovatech.unicom.common;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import com.megvii.livenesslib.util.AndroidRomUtil;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.eventbus.CustomActivityEvent;
import com.sinovatech.unicom.basic.eventbus.MainTabEvent;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LanguageUtil {
    public static final String BURMESE = "burmese";
    public static final String CHN_CN = "chinese";
    public static final String CHN_HASAKE = "kaza";
    public static final String CHN_WEIWUER = "uighur";
    public static final String CHN_ZANGYU = "tibetan";
    public static String CURRENT_NUMBER = "CURRENT_NUMBER";
    public static final String LOCALE_JSON_VALUE = "CUSTOM_APP_LOCALE_JSON_VALUE";
    public static final String LOCALE_KEY = "CUSTOM_APP_LOCALE_KEY";
    public static final String LOCALE_KEY_NOW = "CUSTOM_APP_LOCALE_KEY_NOW";
    public static final String LOCALE_LEFT_TO_RIGHT = "LOCALE_LEFT_TO_RIGHT";
    public static final String LOCALE_LINK_URL = "LOCALE_LINK_URL";
    public static String SEVEN_DAY = "unicom_seven_day";
    private static final String TAG = "LanguageUtil";
    public static final String TIMESTAMP_KEY = "CUSTOM_APP_LOCALE_TIMESTAMP_KEY";
    public static final String USA = "english";
    public static String language = "";
    private static Box<LanguageEntity> languageBox = null;
    private static LanguageUtil mSingleton = null;
    public static String maxLanguageLengthName = "";
    private String languageMsg = "";

    /* renamed from: tf */
    private Typeface f18450tf = Typeface.createFromAsset(App.getInstance().getAssets(), "fonts/UkijTuzTom.ttf");

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface LanguageListener {
        void onChanegFail(String str);

        void onChanegSuccess();
    }

    public String getText(String str, TextView textView) {
        return str;
    }

    public boolean isOpenWeiWen() {
        String uighur = ConfigManager.getUighur();
        String loginType = UserManager.getInstance().getLoginType();
        return ("01".equals(loginType) || "06".equals(loginType) || "999".equals(loginType) || "27".equals(loginType) || "23".equals(loginType)) && App.hasLogined() && !UserManager.getInstance().isYiwang() && TextUtils.equals("1", uighur);
    }

    private LanguageUtil() {
        language = App.getSharePreferenceUtil().getString(LOCALE_KEY_NOW);
        languageBox = App.getBoxStore().boxFor(LanguageEntity.class);
    }

    public static LanguageUtil getInstance() {
        if (mSingleton == null) {
            synchronized (LanguageUtil.class) {
                if (mSingleton == null) {
                    mSingleton = new LanguageUtil();
                }
            }
        }
        return mSingleton;
    }

    public String getLanguageJson() {
        return (isChinese() || isUSA()) ? "" : App.getSharePreferenceUtil().getString(LOCALE_JSON_VALUE);
    }

    public void setLanguage_now(String str) {
        language = str;
    }

    public String getDirection() {
        return App.getSharePreferenceUtil().getString(LOCALE_LEFT_TO_RIGHT);
    }

    public String getLanguage() {
        return !getConfigLanguageSwitch() ? CHN_CN : App.getSharePreferenceUtil().getString(LOCALE_KEY_NOW);
    }

    public boolean isChinese() {
        return TextUtils.isEmpty(language) || TextUtils.equals(CHN_CN, language);
    }

    public boolean isUSA() {
        return TextUtils.isEmpty(language) || TextUtils.equals(USA, language);
    }

    public String getLanguageLinkurl() {
        return App.getSharePreferenceUtil().getString(LOCALE_LINK_URL);
    }

    public boolean isHasLinkurl() {
        return !TextUtils.isEmpty(getLanguageLinkurl());
    }

    public LanguageEntity getLocal() {
        LanguageEntity languageEntity = new LanguageEntity();
        try {
            String string = App.getSharePreferenceUtil().getString(LOCALE_KEY);
            String direction = getDirection();
            if (!getConfigLanguageSwitch()) {
                languageEntity.setLanguageCode(CHN_CN);
                return languageEntity;
            } else if (!TextUtils.isEmpty(string)) {
                languageEntity.setLanguageCode(string);
                languageEntity.setRightToleft(direction);
                return languageEntity;
            } else {
                return getLocalLanguage();
            }
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
            return languageEntity;
        }
    }

    public LanguageEntity getLocalLanguage() {
        Locale locale;
        String str;
        String str2;
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setRightToleft("left");
        languageEntity.setLanguageCode(CHN_CN);
        try {
            Configuration configuration = Resources.getSystem().getConfiguration();
            char c = 0;
            if (Build.VERSION.SDK_INT >= 24) {
                locale = configuration.getLocales().get(0);
            } else {
                locale = configuration.locale;
            }
            locale.getDisplayCountry();
            String locale2 = locale.toString();
            switch (locale2.hashCode()) {
                case 93905309:
                    if (locale2.equals("bo_CN")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 96646644:
                    if (locale2.equals("en_US")) {
                        break;
                    }
                    c = 65535;
                    break;
                case 102097834:
                    if (locale2.equals("kk_CN")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 111213880:
                    if (locale2.equals("ug_CN")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 115861276:
                    if (locale2.equals("zh_CN")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 115861428:
                    if (locale2.equals("zh_HK")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 115861812:
                    if (locale2.equals("zh_TW")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    str = USA;
                    str2 = "left";
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    str = CHN_CN;
                    str2 = "left";
                    break;
                case 5:
                    str = CHN_HASAKE;
                    str2 = "right";
                    break;
                case 6:
                    str = CHN_WEIWUER;
                    str2 = "right";
                    break;
                default:
                    if (!locale.getLanguage().equals(new Locale("en").getLanguage())) {
                        if (!locale.getLanguage().equals(new Locale("my").getLanguage())) {
                            str = CHN_CN;
                            str2 = "left";
                            break;
                        } else {
                            str = BURMESE;
                            str2 = "left";
                            break;
                        }
                    } else {
                        str = USA;
                        str2 = "left";
                        break;
                    }
            }
            languageEntity.setRightToleft(str2);
            languageEntity.setLanguageCode(str);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "获取系统语言异常:" + e.getMessage());
        }
        return languageEntity;
    }

    public void applyLanguage(String str, String str2, String str3) {
        try {
            App.getSharePreferenceUtil().putString(TIMESTAMP_KEY, "");
            setLanguage(str, str2, str3);
            if (App.getSharePreferenceUtil().getBoolean("CareHome")) {
                App.getSharePreferenceUtil().putBoolean("CareHome", false);
                EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH));
            }
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.common.LanguageUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    LanguageUtil.getInstance().relaunchApp();
                }
            }, 500L);
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toast("设置多语言失败,请重试!");
        }
    }

    public static void setLanguage(String str, String str2, String str3) {
        App.getSharePreferenceUtil().putString("unicom_app_main_type", TextUtils.equals(CHN_CN, str) ? "0" : "2");
        App.getSharePreferenceUtil().putString("unicom_app_main_url", str3);
        App.getSharePreferenceUtil().putString(LOCALE_KEY, str);
        getInstance().setLanguage_now(str);
        App.getSharePreferenceUtil().putString(LOCALE_KEY_NOW, getInstance().getLocal().getLanguageCode());
        App.getSharePreferenceUtil().putString(LOCALE_LEFT_TO_RIGHT, str2);
        App.getSharePreferenceUtil().putString(LOCALE_LINK_URL, str3);
    }

    public static void setLanguage(String str, String str2) {
        setLanguage(str, str2, "");
    }

    public String getTimestamp() {
        return App.getSharePreferenceUtil().getString(TIMESTAMP_KEY);
    }

    public void getLanguageData(String str, final LanguageListener languageListener) {
        if (languageListener == null) {
            App.getSharePreferenceUtil().putString(TIMESTAMP_KEY, "");
        }
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, CHN_CN) || TextUtils.equals(str, USA)) {
            if (languageListener != null) {
                App.getSharePreferenceUtil().putString(LOCALE_JSON_VALUE, "");
                languageListener.onChanegSuccess();
                return;
            }
            return;
        }
        hashMap.put("timestamp", getTimestamp());
        hashMap.put("language", str);
        this.languageMsg = "";
        App.getAsyncHttpClient().rxGet(URLSet.getLanguageDataUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, Boolean>() { // from class: com.sinovatech.unicom.common.LanguageUtil.4
            @Override // io.reactivex.functions.Function
            public Boolean apply(String str2) throws Exception {
                try {
                    UIUtils.logD(LanguageUtil.TAG, "语言字典 json = " + str2);
                    JSONObject jSONObject = new JSONObject(str2);
                    String optString = jSONObject.optString("code");
                    LanguageUtil.this.languageMsg = jSONObject.optString("msg");
                    String optString2 = jSONObject.optString("needUpdate");
                    if (TextUtils.equals("0000", optString)) {
                        String optString3 = jSONObject.optString("timestamp");
                        if (!TextUtils.isEmpty(optString3)) {
                            App.getSharePreferenceUtil().putString(LanguageUtil.TIMESTAMP_KEY, optString3);
                        }
                        if (TextUtils.equals("1", optString2)) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            if (!TextUtils.isEmpty(!(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject))) {
                                App.getSharePreferenceUtil().putString(LanguageUtil.LOCALE_JSON_VALUE, !(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject));
                                EventBusUtils.post(new MainTabEvent(EventBusUtils.EVENT_MAIN_TAB_CHANGE));
                            }
                        }
                        return true;
                    }
                    return false;
                } catch (Exception unused) {
                    return false;
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.common.LanguageUtil.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    LanguageListener languageListener2 = languageListener;
                    if (languageListener2 != null) {
                        languageListener2.onChanegSuccess();
                        return;
                    }
                    return;
                }
                LanguageListener languageListener3 = languageListener;
                if (languageListener3 != null) {
                    languageListener3.onChanegFail(TextUtils.isEmpty(LanguageUtil.this.languageMsg) ? "系统异常，请稍等再试～" : LanguageUtil.this.languageMsg);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.common.LanguageUtil.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                UIUtils.logE(LanguageUtil.TAG, th.getMessage());
                LanguageListener languageListener2 = languageListener;
                if (languageListener2 != null) {
                    languageListener2.onChanegFail("系统异常，请稍等再试～");
                }
            }
        });
    }

    public boolean isChangeLanguage() {
        String string = App.getSharePreferenceUtil().getString(LOCALE_KEY);
        String string2 = App.getSharePreferenceUtil().getString(LOCALE_KEY_NOW);
        if (!TextUtils.isEmpty(string)) {
            return !TextUtils.equals(string, string2);
        }
        String languageCode = getLocal().getLanguageCode();
        if (!TextUtils.isEmpty(string2)) {
            return !TextUtils.equals(string2, languageCode);
        }
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2)) {
            App.getSharePreferenceUtil().putString(LOCALE_KEY_NOW, languageCode);
            return !TextUtils.equals(languageCode, CHN_CN);
        }
        return false;
    }

    public String getText(String str, TextView textView, boolean z) {
        try {
            if (getConfigLanguageSwitch() && !TextUtils.isEmpty(language) && !TextUtils.equals(CHN_CN, language)) {
                textView.setLineSpacing(0.0f, 1.0f);
                if (z) {
                    textView.setSingleLine(false);
                    textView.setMaxLines(2);
                    textView.setMinLines(2);
                    textView.setLines(2);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    textView.setGravity(49);
                }
                if (TextUtils.equals("right", getDirection())) {
                    textView.setTextDirection(4);
                }
                if (this.f18450tf != null && (TextUtils.equals(CHN_WEIWUER, getLanguage()) || TextUtils.equals(CHN_HASAKE, getLanguage()))) {
                    textView.setTypeface(this.f18450tf);
                }
                String languageJson = getLanguageJson();
                if (TextUtils.isEmpty(languageJson)) {
                    return str;
                }
                String optString = new JSONObject(languageJson).optString(str);
                if (TextUtils.isEmpty(optString)) {
                    return str;
                }
                if ((AndroidRomUtil.isHuawei() || BrandUtil.isXiaoMi() || AndroidRomUtil.isOneplus()) && TextUtils.equals(CHN_ZANGYU, getLanguage())) {
                    textView.setLineSpacing(-((UIUtils.getScreenWidth(App.getInstance()) * 7) / 360), 1.0f);
                }
                return optString;
            }
            return str;
        } catch (Exception e) {
            UIUtils.logE(TAG, e.getMessage());
            return str;
        }
    }

    public void relaunchApp() {
        Intent launchAppIntent = getLaunchAppIntent(App.getInstance().getPackageName());
        if (launchAppIntent == null) {
            Log.e("AppUtils", "Didn't exist launcher activity.");
            return;
        }
        launchAppIntent.addFlags(335577088);
        App.getInstance().startActivity(launchAppIntent);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public Intent getLaunchAppIntent(String str) {
        String launcherActivity = getLauncherActivity(str);
        if (TextUtils.isEmpty(launcherActivity)) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setClassName(str, launcherActivity);
        return intent.addFlags(268435456);
    }

    public String getLauncherActivity(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(str);
        List<ResolveInfo> queryIntentActivities = App.getInstance().getPackageManager().queryIntentActivities(intent, 0);
        return (queryIntentActivities == null || queryIntentActivities.size() == 0) ? "" : queryIntentActivities.get(0).activityInfo.name;
    }

    public String getMoileSuffix() {
        String string = App.getSharePreferenceUtil().getString(LOCALE_KEY_NOW);
        string = (TextUtils.isEmpty(string) || TextUtils.equals(CHN_CN, string)) ? "" : "";
        return "_" + string;
    }

    public boolean getConfigLanguageSwitch() {
        return TextUtils.equals("0", App.getSharePreferenceUtil().getString(ConfigManager.languageSettingSwitch));
    }

    public static boolean isWeiWen() {
        return TextUtils.equals(CHN_WEIWUER, getInstance().getLanguage());
    }

    public void loadLanuageData(AppCompatActivity appCompatActivity) {
        try {
            if (languageBox == null) {
                languageBox = App.getBoxStore().boxFor(LanguageEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getLanguageList(), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, List<LanguageEntity>>() { // from class: com.sinovatech.unicom.common.LanguageUtil.7
            @Override // io.reactivex.functions.Function
            public List<LanguageEntity> apply(String str) throws Exception {
                ArrayList arrayList = new ArrayList();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String optString = jSONObject.optString("code");
                    String optString2 = jSONObject.optString("msg");
                    if (TextUtils.equals("0000", optString)) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("dataList");
                        if (optJSONArray != null) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    String optString3 = optJSONObject.optString("languageName");
                                    String optString4 = optJSONObject.optString("languageDesc");
                                    String optString5 = optJSONObject.optString("languageCode");
                                    String optString6 = optJSONObject.optString("rightToleft");
                                    String optString7 = optJSONObject.optString("url");
                                    String optString8 = optJSONObject.optString("switchLanguagePop");
                                    String optString9 = optJSONObject.optString("cancelBtnPop");
                                    String optString10 = optJSONObject.optString("confirmBtnPop");
                                    String optString11 = optJSONObject.optString("reminderPop");
                                    String optString12 = optJSONObject.optString("showDirectionFlag");
                                    String optString13 = optJSONObject.optString("ywshow");
                                    if (TextUtils.isEmpty(optString8) || TextUtils.isEmpty(optString9) || TextUtils.isEmpty(optString10) || TextUtils.isEmpty(optString11)) {
                                        optString8 = "";
                                        optString9 = "";
                                        optString10 = "";
                                        optString11 = "";
                                    }
                                    LanguageEntity languageEntity = new LanguageEntity();
                                    languageEntity.setLanguageName(optString3);
                                    languageEntity.setLanguageCode(optString5);
                                    languageEntity.setRightToleft(optString6);
                                    languageEntity.setLanguageDesc(optString4);
                                    languageEntity.setUrl(optString7);
                                    languageEntity.setYwshow(optString13);
                                    languageEntity.setSwitchLanguagePop(optString8);
                                    languageEntity.setCancelBtnPop(optString9);
                                    languageEntity.setConfirmBtnPop(optString10);
                                    languageEntity.setReminderPop(optString11);
                                    languageEntity.setShowDirectionFlag(optString12);
                                    arrayList.add(languageEntity);
                                }
                            }
                        }
                        return arrayList;
                    }
                    if (TextUtils.isEmpty(optString2)) {
                        optString2 = "系统异常，请稍等再试～";
                    }
                    throw new Exception(optString2);
                } catch (Exception e2) {
                    UIUtils.logD(LanguageUtil.TAG, e2.getMessage());
                    throw new Exception("系统异常，请稍等再试～");
                }
            }
        }).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity))).subscribe(new Consumer<List<LanguageEntity>>() { // from class: com.sinovatech.unicom.common.LanguageUtil.5
            @Override // io.reactivex.functions.Consumer
            public void accept(List<LanguageEntity> list) throws Exception {
                try {
                    LanguageUtil.languageBox.removeAll();
                    if (list == null || list.size() <= 0) {
                        return;
                    }
                    LanguageUtil.languageBox.put((Collection) list);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.common.LanguageUtil.6
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0057 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0058 A[Catch: Exception -> 0x0066, TRY_LEAVE, TryCatch #0 {Exception -> 0x0066, blocks: (B:3:0x0001, B:6:0x0008, B:9:0x0021, B:11:0x002c, B:16:0x0036, B:18:0x0044, B:20:0x004c, B:25:0x0058), top: B:31:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity isExist(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            boolean r1 = com.sinovatech.unicom.p318ui.App.hasLogined()     // Catch: java.lang.Exception -> L66
            if (r1 != 0) goto L8
            return r0
        L8:
            io.objectbox.Box<com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity> r1 = com.sinovatech.unicom.common.LanguageUtil.languageBox     // Catch: java.lang.Exception -> L66
            io.objectbox.query.QueryBuilder r1 = r1.query()     // Catch: java.lang.Exception -> L66
            io.objectbox.Property<com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity> r2 = com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity_.languageCode     // Catch: java.lang.Exception -> L66
            io.objectbox.query.QueryBuilder r6 = r1.equal(r2, r6)     // Catch: java.lang.Exception -> L66
            io.objectbox.query.Query r6 = r6.build()     // Catch: java.lang.Exception -> L66
            java.lang.Object r6 = r6.findFirst()     // Catch: java.lang.Exception -> L66
            com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity r6 = (com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity) r6     // Catch: java.lang.Exception -> L66
            if (r6 != 0) goto L21
            return r0
        L21:
            com.sinovatech.unicom.basic.server.UserManager r1 = com.sinovatech.unicom.basic.server.UserManager.getInstance()     // Catch: java.lang.Exception -> L66
            boolean r1 = r1.isYiwang()     // Catch: java.lang.Exception -> L66
            r2 = 1
            if (r1 != 0) goto L35
            boolean r1 = com.sinovatech.unicom.basic.server.LoginManager.isKuandaiOrGuhua()     // Catch: java.lang.Exception -> L66
            if (r1 == 0) goto L33
            goto L35
        L33:
            r1 = 0
            goto L36
        L35:
            r1 = r2
        L36:
            com.sinovatech.unicom.basic.server.UserManager r3 = com.sinovatech.unicom.basic.server.UserManager.getInstance()     // Catch: java.lang.Exception -> L66
            java.lang.String r3 = r3.getCurrentPhoneNumber()     // Catch: java.lang.Exception -> L66
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L66
            if (r4 != 0) goto L55
            java.lang.String r4 = "1"
            boolean r4 = r3.startsWith(r4)     // Catch: java.lang.Exception -> L66
            if (r4 == 0) goto L54
            int r3 = r3.length()     // Catch: java.lang.Exception -> L66
            r4 = 11
            if (r3 == r4) goto L55
        L54:
            r1 = r2
        L55:
            if (r1 != 0) goto L58
            return r6
        L58:
            java.lang.String r1 = "true"
            java.lang.String r2 = r6.getYwshow()     // Catch: java.lang.Exception -> L66
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L66
            if (r1 == 0) goto L6a
            return r6
        L66:
            r6 = move-exception
            r6.printStackTrace()
        L6a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.common.LanguageUtil.isExist(java.lang.String):com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity");
    }

    public Observable<List<LanguageEntity>> getLanguageList() {
        return Observable.create(new ObservableOnSubscribe<List<LanguageEntity>>() { // from class: com.sinovatech.unicom.common.LanguageUtil.8
            /* JADX WARN: Removed duplicated region for block: B:24:0x005f A[Catch: Exception -> 0x00bf, TryCatch #0 {Exception -> 0x00bf, blocks: (B:3:0x0004, B:5:0x0016, B:7:0x001c, B:9:0x0027, B:14:0x0031, B:16:0x003f, B:18:0x0047, B:21:0x0050, B:22:0x0059, B:24:0x005f, B:26:0x0067, B:28:0x0071, B:30:0x007d, B:31:0x007f, B:32:0x0083, B:34:0x0090, B:36:0x009a, B:38:0x00a6, B:39:0x00a8, B:40:0x00ac, B:41:0x00b3), top: B:46:0x0004 }] */
            @Override // io.reactivex.ObservableOnSubscribe
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void subscribe(io.reactivex.ObservableEmitter<java.util.List<com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity>> r8) throws java.lang.Exception {
                /*
                    r7 = this;
                    java.lang.String r0 = ""
                    com.sinovatech.unicom.common.LanguageUtil.maxLanguageLengthName = r0
                    io.objectbox.Box r0 = com.sinovatech.unicom.common.LanguageUtil.access$100()     // Catch: java.lang.Exception -> Lbf
                    io.objectbox.query.QueryBuilder r0 = r0.query()     // Catch: java.lang.Exception -> Lbf
                    io.objectbox.query.Query r0 = r0.build()     // Catch: java.lang.Exception -> Lbf
                    java.util.List r0 = r0.find()     // Catch: java.lang.Exception -> Lbf
                    if (r0 == 0) goto Lb3
                    int r1 = r0.size()     // Catch: java.lang.Exception -> Lbf
                    if (r1 <= 0) goto Lb3
                    com.sinovatech.unicom.basic.server.UserManager r1 = com.sinovatech.unicom.basic.server.UserManager.getInstance()     // Catch: java.lang.Exception -> Lbf
                    boolean r1 = r1.isYiwang()     // Catch: java.lang.Exception -> Lbf
                    r2 = 1
                    if (r1 != 0) goto L30
                    boolean r1 = com.sinovatech.unicom.basic.server.LoginManager.isKuandaiOrGuhua()     // Catch: java.lang.Exception -> Lbf
                    if (r1 == 0) goto L2e
                    goto L30
                L2e:
                    r1 = 0
                    goto L31
                L30:
                    r1 = r2
                L31:
                    com.sinovatech.unicom.basic.server.UserManager r3 = com.sinovatech.unicom.basic.server.UserManager.getInstance()     // Catch: java.lang.Exception -> Lbf
                    java.lang.String r3 = r3.getCurrentPhoneNumber()     // Catch: java.lang.Exception -> Lbf
                    boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> Lbf
                    if (r4 != 0) goto L50
                    java.lang.String r4 = "1"
                    boolean r4 = r3.startsWith(r4)     // Catch: java.lang.Exception -> Lbf
                    if (r4 == 0) goto L4f
                    int r3 = r3.length()     // Catch: java.lang.Exception -> Lbf
                    r4 = 11
                    if (r3 == r4) goto L50
                L4f:
                    r1 = r2
                L50:
                    java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Exception -> Lbf
                    r2.<init>()     // Catch: java.lang.Exception -> Lbf
                    java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> Lbf
                L59:
                    boolean r3 = r0.hasNext()     // Catch: java.lang.Exception -> Lbf
                    if (r3 == 0) goto Lac
                    java.lang.Object r3 = r0.next()     // Catch: java.lang.Exception -> Lbf
                    com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity r3 = (com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity) r3     // Catch: java.lang.Exception -> Lbf
                    if (r1 != 0) goto L83
                    java.lang.String r4 = r3.getLanguageName()     // Catch: java.lang.Exception -> Lbf
                    boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> Lbf
                    if (r5 != 0) goto L7f
                    int r5 = r4.length()     // Catch: java.lang.Exception -> Lbf
                    java.lang.String r6 = com.sinovatech.unicom.common.LanguageUtil.maxLanguageLengthName     // Catch: java.lang.Exception -> Lbf
                    int r6 = r6.length()     // Catch: java.lang.Exception -> Lbf
                    if (r5 <= r6) goto L7f
                    com.sinovatech.unicom.common.LanguageUtil.maxLanguageLengthName = r4     // Catch: java.lang.Exception -> Lbf
                L7f:
                    r2.add(r3)     // Catch: java.lang.Exception -> Lbf
                    goto L59
                L83:
                    java.lang.String r4 = "true"
                    java.lang.String r5 = r3.getYwshow()     // Catch: java.lang.Exception -> Lbf
                    boolean r4 = r4.equals(r5)     // Catch: java.lang.Exception -> Lbf
                    if (r4 == 0) goto L59
                    java.lang.String r4 = r3.getLanguageName()     // Catch: java.lang.Exception -> Lbf
                    boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> Lbf
                    if (r5 != 0) goto La8
                    int r5 = r4.length()     // Catch: java.lang.Exception -> Lbf
                    java.lang.String r6 = com.sinovatech.unicom.common.LanguageUtil.maxLanguageLengthName     // Catch: java.lang.Exception -> Lbf
                    int r6 = r6.length()     // Catch: java.lang.Exception -> Lbf
                    if (r5 <= r6) goto La8
                    com.sinovatech.unicom.common.LanguageUtil.maxLanguageLengthName = r4     // Catch: java.lang.Exception -> Lbf
                La8:
                    r2.add(r3)     // Catch: java.lang.Exception -> Lbf
                    goto L59
                Lac:
                    r8.onNext(r2)     // Catch: java.lang.Exception -> Lbf
                    r8.onComplete()     // Catch: java.lang.Exception -> Lbf
                    goto Le2
                Lb3:
                    java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Exception -> Lbf
                    r0.<init>()     // Catch: java.lang.Exception -> Lbf
                    r8.onNext(r0)     // Catch: java.lang.Exception -> Lbf
                    r8.onComplete()     // Catch: java.lang.Exception -> Lbf
                    goto Le2
                Lbf:
                    r0 = move-exception
                    java.lang.String r1 = ""
                    com.sinovatech.unicom.common.LanguageUtil.maxLanguageLengthName = r1
                    java.lang.Throwable r1 = new java.lang.Throwable
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "读取多语言数据异常"
                    r2.append(r3)
                    java.lang.String r0 = r0.getMessage()
                    r2.append(r0)
                    java.lang.String r0 = r2.toString()
                    r1.<init>(r0)
                    r8.onError(r1)
                Le2:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.common.LanguageUtil.C81168.subscribe(io.reactivex.ObservableEmitter):void");
            }
        }).subscribeOn(Schedulers.m1934io());
    }

    public boolean isShowLanguage() {
        try {
            if (TextUtils.equals("0", App.getSharePreferenceUtil().getString(ConfigManager.languageSettingSwitch))) {
                return App.hasLogined();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLocalLanguage(String str) {
        LanguageEntity localLanguage;
        return isShowLanguage() && (localLanguage = getLocalLanguage()) != null && TextUtils.equals(str, localLanguage.getLanguageCode());
    }

    public boolean isShowUSADialog() {
        return App.hasLogined() && getMultilingualValue() == 0 && isLocalLanguage(USA);
    }

    public int getMultilingualValue() {
        return App.getSharePreferenceUtil().getInt("app_home_usa_dialog", 0);
    }

    public void setMultilingualDialogValue(int i) {
        App.getSharePreferenceUtil().putInt("app_home_usa_dialog", i);
    }

    public boolean isMultilingualDialog() {
        try {
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "判断是否需要弹多语言弹窗异常:" + e.getMessage());
        }
        if (App.hasLogined() && !getInstance().getLocalLanguage().getLanguageCode().equals(CHN_CN)) {
            String uighur = ConfigManager.getUighur();
            if (!getInstance().getLocalLanguage().getLanguageCode().equals(CHN_WEIWUER) || TextUtils.equals("1", uighur)) {
                String string = App.getSharePreferenceUtil().getString(PreferenceConstUtils.first_Switch_Refuse(getInstance().getLocalLanguage().getLanguageCode()));
                if (getInstance().getLocalLanguage().getLanguageCode().equals(USA) && isToSevenDay()) {
                    saveSevenDayTime(0L);
                    setMultilingualDialogValue(0);
                    string = "0";
                }
                if (!TextUtils.equals("1", string)) {
                    setMultilingualDialogValue(0);
                    UserManager.getInstance().getLoginType();
                    if ("01".equals(UserManager.getInstance().getCurrentPhoneType()) && getMultilingualValue() == 0 && isShowLanguage()) {
                        return !UserManager.getInstance().isYiwang();
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public void saveSevenDayTime(long j) {
        App.getSharePreferenceUtil().putLong(SEVEN_DAY, j);
    }

    public boolean isToSevenDay() {
        long j = App.getSharePreferenceUtil().getLong(SEVEN_DAY);
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
}

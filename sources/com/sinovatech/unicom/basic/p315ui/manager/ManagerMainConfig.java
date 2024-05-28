package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.adapter.SimplePermissionAdapter;
import com.p284qw.soul.permission.bean.Permission;
import com.sinovatech.unicom.basic.p315ui.view.UpdateCustomDialog;
import com.sinovatech.unicom.basic.p315ui.view.UpdateProgressDialogView;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.ConfigParser;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.clearcache.ClearCacheUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerMainConfig {
    private static final String TAG = "ManagerMainConfig";
    public static boolean isGaryPub;
    private ConfigManager configManager;
    private Activity context;
    private UpdateProgressDialogView progressDialogView;
    private SharePreferenceUtil preference = App.getSharePreferenceUtil();
    private UserManager userManager = UserManager.getInstance();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig$ConfigListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ConfigListener {
        void onBack();

        void onExit();
    }

    public ManagerMainConfig(Activity activity) {
        this.context = activity;
        this.configManager = new ConfigManager(activity);
    }

    public void loadConfigInfo(final boolean z, final ConfigListener configListener) {
        String uuid = UUID.randomUUID().toString();
        String str = "010038" + uuid.replaceAll("-", "").substring(0, 26);
        this.preference.putString("unikey", str);
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.context.getString(2131886969));
        hashMap.put("unikey", str);
        hashMap.put("deviceCode", DeviceHelper.getDeviceID(false));
        hashMap.put("appId", this.userManager.getLoginAppId());
        hashMap.put("mobile", this.userManager.getCurrentPhoneNumber());
        App.getAsyncHttpClient(5, 5, 5, 5).rxGet(URLSet.getConfig_url(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function<String, JSONObject>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig.3
            @Override // io.reactivex.functions.Function
            public JSONObject apply(String str2) throws Exception {
                JSONObject optJSONObject = new JSONObject(ConfigParser.parseConfigResponse(ManagerMainConfig.this.context, str2)).optJSONObject(ManagerMainConfig.this.context.getString(2131886969));
                if (optJSONObject == null) {
                    App.getSharePreferenceUtil().putString("versionupdate_desc", "");
                    return new JSONObject();
                }
                return optJSONObject;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JSONObject>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig.1
            @Override // io.reactivex.functions.Consumer
            public void accept(JSONObject jSONObject) throws Exception {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "配置项接口解析完成");
                if (ClearCacheUtils.checkClear() && z) {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "弹出清除缓存弹窗");
                    ManagerMainConfig.this.showClearCacheDialog(jSONObject, z, configListener);
                    return;
                }
                ManagerMainConfig.this.checkUpdate(jSONObject, z, configListener);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "配置项接口失败" + th.getMessage());
                UIUtils.logD("读取配置接口报错：" + th.getMessage());
                try {
                    if (configListener != null) {
                        configListener.onBack();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ConfigListener configListener2 = configListener;
                    if (configListener2 != null) {
                        configListener2.onBack();
                    }
                }
            }
        });
    }

    public void showClearCacheDialog(final JSONObject jSONObject, final boolean z, final ConfigListener configListener) {
        CustomDialogManager.show(this.context, "", "我们检测到APP数据异常，将为您清空数据，请您点击确定，再重新打开APP。", false, "取消", "确定", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig.4
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "清除数据弹窗消失");
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "清除数据弹窗显示");
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                try {
                    long j = App.getSharePreferenceUtil().getLong("unicom_app_crash_time");
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "确定清除数据应用退出");
                    PvCurrencyLogUtils.pvLogLL("", "S2ndpage1161", ",", ",", ",", "", "", "", j + "");
                    new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ClearCacheUtils.clearCache();
                        }
                    }, 1000L);
                } catch (Exception e) {
                    String str = ManagerMainConfig.TAG;
                    MsLogUtil.m7977e(str, "清除数据异常:" + e.getMessage());
                }
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
                App.getSharePreferenceUtil().putLong("unicom_app_crash_time", 0L);
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "取消清除数据 检测升级弹窗");
                ManagerMainConfig.this.checkUpdate(jSONObject, z, configListener);
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "清除数据弹窗点击返回键");
            }
        });
    }

    public void checkUpdate(JSONObject jSONObject, boolean z, final ConfigListener configListener) {
        String welcomeShowUpdateDialogFlag = this.configManager.getWelcomeShowUpdateDialogFlag();
        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "不需要清除缓存 是否在启动页弹出升级弹窗标识为:" + welcomeShowUpdateDialogFlag);
        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", z ? "当前是启动页" : "当前是首页");
        if (!TextUtils.equals("1", welcomeShowUpdateDialogFlag) && z) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "开关关闭 不解析升级弹窗 进入应用 onback");
            if (configListener != null) {
                configListener.onBack();
            }
        } else if (TextUtils.equals("1", welcomeShowUpdateDialogFlag) && !z && App.isWelcomeStartApp) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "开关开启 不解析升级弹窗 进入应用");
            if (configListener != null) {
                configListener.onBack();
            }
        } else {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "开关开启 解释升级数据");
            if (jSONObject != null) {
                final String optString = jSONObject.optString("type");
                String optString2 = jSONObject.optString("desc");
                String optString3 = jSONObject.optString("alert_desc");
                String optString4 = jSONObject.optString("url");
                if (("3".equals(optString) || "4".equals(optString) || !DeviceHelper.isTopFiveMarket()) && DeviceHelper.is64BitCpu()) {
                    String optString5 = jSONObject.optString("packageUrlfor64");
                    if (!TextUtils.isEmpty(optString5)) {
                        optString4 = optString5;
                    }
                }
                final String decodeByAESUpdate = EncodeHelper.decodeByAESUpdate(optString4);
                if (TextUtils.isEmpty(decodeByAESUpdate)) {
                    if (configListener != null) {
                        configListener.onBack();
                        return;
                    }
                    return;
                }
                String optString6 = jSONObject.optString("popOptions");
                final String optString7 = jSONObject.optString("backgroundPicture");
                final String optString8 = jSONObject.optString("upgradeButtonImage");
                final String optString9 = jSONObject.optString("cancelButtonImage");
                if ("3".equals(optString.trim()) || "4".equals(optString.trim())) {
                    App.getSharePreferenceUtil().putString("versionupdate_desc", "");
                } else {
                    App.getSharePreferenceUtil().putString("versionupdate_desc", optString2);
                }
                if (!TextUtils.isEmpty(optString6) && optString6.equals("1")) {
                    if (!"0".equals(optString.trim())) {
                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "图片升级");
                        GlideApp.with(this.context).load(optString7).into((RequestBuilder<Drawable>) new SimpleTarget<Drawable>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig.5
                            @Override // com.bumptech.glide.request.target.Target
                            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                                onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                            }

                            public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                                try {
                                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "图片下载成功");
                                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                                    if (!"3".equals(optString.trim()) && !"1".equals(optString.trim())) {
                                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "不是灰度升级 直接弹窗提示升级");
                                        ManagerMainConfig.this.showUpdateDialog(bitmap, optString8, optString9, decodeByAESUpdate, optString, optString7, configListener);
                                    }
                                    String string = ManagerMainConfig.this.preference.getString(optString7 + optString);
                                    if (!TextUtils.isEmpty(string)) {
                                        if (ManagerMainConfig.this.isJianCe(string)) {
                                            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "灰度升级数据 超过7天 提示升级");
                                            ManagerMainConfig.this.showUpdateDialog(bitmap, optString8, optString9, decodeByAESUpdate, optString, optString7, configListener);
                                        } else {
                                            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "灰度升级 未超过7天 返回 onback");
                                            if (configListener != null) {
                                                configListener.onBack();
                                            }
                                        }
                                    } else {
                                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "没缓存过灰度数据 直接提示升级");
                                        ManagerMainConfig.this.showUpdateDialog(bitmap, optString8, optString9, decodeByAESUpdate, optString, optString7, configListener);
                                    }
                                } catch (Exception e) {
                                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "升级弹窗数据异常 返回  onback");
                                    ConfigListener configListener2 = configListener;
                                    if (configListener2 != null) {
                                        configListener2.onBack();
                                    }
                                    String str = ManagerMainConfig.TAG;
                                    MsLogUtil.m7977e(str, "升级弹窗异常:" + e.getMessage());
                                }
                            }

                            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                            public void onLoadFailed(@Nullable Drawable drawable) {
                                super.onLoadFailed(drawable);
                                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "图片下载失败 返回 onback");
                                ConfigListener configListener2 = configListener;
                                if (configListener2 != null) {
                                    configListener2.onBack();
                                }
                            }
                        });
                        return;
                    }
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "不满足图片升级条件 返回 onback");
                    if (configListener != null) {
                        configListener.onBack();
                        return;
                    }
                    return;
                } else if (!TextUtils.isEmpty(optString6) && optString6.equals("0") && !TextUtils.isEmpty(optString3) && !TextUtils.isEmpty(optString2)) {
                    if (!"0".equals(optString.trim())) {
                        if ("3".equals(optString.trim()) || "1".equals(optString.trim())) {
                            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "灰度升级");
                            String string = this.preference.getString(optString7 + optString);
                            if (!TextUtils.isEmpty(string)) {
                                if (isJianCe(string)) {
                                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "灰度升级 超过七天 弹出升级弹窗");
                                    showNewUpdateDialog(decodeByAESUpdate, optString, optString3, optString2, optString7, configListener);
                                    return;
                                }
                                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "弹出灰度升级 未超过七天 不在提示升级 onback");
                                if (configListener != null) {
                                    configListener.onBack();
                                    return;
                                }
                                return;
                            }
                            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "灰度升级未缓存过时间戳直接升级");
                            showNewUpdateDialog(decodeByAESUpdate, optString, optString3, optString2, optString7, configListener);
                            return;
                        }
                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "不是灰度直接提示升级");
                        showNewUpdateDialog(decodeByAESUpdate, optString, optString3, optString2, optString7, configListener);
                        return;
                    }
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "文字描述升级 不符合条件 updatatype = " + optString + " onback");
                    if (configListener != null) {
                        configListener.onBack();
                        return;
                    }
                    return;
                } else {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "返回的数据 不是文字升级 也不是图片升级 onback");
                    if (configListener != null) {
                        configListener.onBack();
                        return;
                    }
                    return;
                }
            }
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "没有升级数据 onBack");
            if (configListener != null) {
                configListener.onBack();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUpdateDialog(Bitmap bitmap, String str, String str2, final String str3, String str4, String str5, final ConfigListener configListener) {
        ManagerMainConfig managerMainConfig;
        final boolean z;
        final boolean z2;
        boolean z3;
        if ("1".equals(str4.trim())) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "建议升级");
            managerMainConfig = this;
            z3 = true;
            z = false;
            z2 = false;
        } else if ("2".equals(str4.trim())) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "强制升级");
            managerMainConfig = this;
            z2 = true;
            z = false;
            z3 = false;
        } else if ("3".equals(str4.trim())) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "灰度升级");
            managerMainConfig = this;
            z = true;
            z3 = true;
            z2 = false;
        } else if ("4".equals(str4.trim())) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "灰度强制升级");
            managerMainConfig = this;
            z = true;
            z2 = true;
            z3 = false;
        } else {
            managerMainConfig = this;
            z = false;
            z2 = false;
            z3 = false;
        }
        UpdateCustomDialog.show(managerMainConfig.context, str4, str5, bitmap, str, str2, z3, new UpdateCustomDialog.UpdateCustomDialogListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig.6
            @Override // com.sinovatech.unicom.basic.p315ui.view.UpdateCustomDialog.UpdateCustomDialogListener
            public void onClickOk() {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "点击确定按钮 进行升级");
                ManagerMainConfig.isGaryPub = z;
                ManagerMainConfig.this.download(str3, z2, configListener);
            }

            @Override // com.sinovatech.unicom.basic.p315ui.view.UpdateCustomDialog.UpdateCustomDialogListener
            public void onClickCancel() {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "点击取消按钮");
                if (configListener != null) {
                    if (z2) {
                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "强制升级 退出 onexit");
                        configListener.onExit();
                        return;
                    }
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "非强制升级 返回 onback");
                    configListener.onBack();
                }
            }
        });
    }

    private void showNewUpdateDialog(final String str, String str2, String str3, String str4, String str5, final ConfigListener configListener) {
        ManagerMainConfig managerMainConfig;
        final boolean z;
        final boolean z2;
        boolean z3;
        if ("1".equals(str2.trim())) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "弹窗类型 建议升级");
            managerMainConfig = this;
            z3 = true;
            z = false;
            z2 = false;
        } else if ("2".equals(str2.trim())) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "弹窗类型 强制升级");
            managerMainConfig = this;
            z2 = true;
            z = false;
            z3 = false;
        } else if ("3".equals(str2.trim())) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "弹窗类型 灰度升级");
            managerMainConfig = this;
            z = true;
            z3 = true;
            z2 = false;
        } else if ("4".equals(str2.trim())) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "灰度强制升级");
            managerMainConfig = this;
            z = true;
            z2 = true;
            z3 = false;
        } else {
            managerMainConfig = this;
            z = false;
            z2 = false;
            z3 = false;
        }
        UpdateCustomDialog.Newshow(managerMainConfig.context, str2, str5, str3, str4, z3, new UpdateCustomDialog.UpdateCustomDialogListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig.7
            @Override // com.sinovatech.unicom.basic.p315ui.view.UpdateCustomDialog.UpdateCustomDialogListener
            public void onClickOk() {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "点击确定按钮 下载apk");
                ManagerMainConfig.isGaryPub = z;
                ManagerMainConfig.this.download(str, z2, configListener);
            }

            @Override // com.sinovatech.unicom.basic.p315ui.view.UpdateCustomDialog.UpdateCustomDialogListener
            public void onClickCancel() {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "升级弹窗 点击取消");
                if (configListener != null) {
                    if (z2) {
                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "强制升级 退出 onexit");
                        configListener.onExit();
                        return;
                    }
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "非强制升级 onback");
                    configListener.onBack();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003f A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isJianCe(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            java.lang.String r7 = timeStamp2Date(r7, r1)     // Catch: java.lang.Exception -> L40
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat     // Catch: java.lang.Exception -> L40
            java.lang.String r3 = "yyyy-MM-dd HH:mm:ss"
            r2.<init>(r3)     // Catch: java.lang.Exception -> L40
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L40
            r3.<init>()     // Catch: java.lang.Exception -> L40
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L40
            r3.append(r4)     // Catch: java.lang.Exception -> L40
            java.lang.String r4 = ""
            r3.append(r4)     // Catch: java.lang.Exception -> L40
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L40
            java.lang.String r3 = timeStamp2Date(r3, r1)     // Catch: java.lang.Exception -> L40
            java.util.Date r7 = r2.parse(r7)     // Catch: java.text.ParseException -> L32 java.lang.Exception -> L40
            java.util.Date r1 = r2.parse(r3)     // Catch: java.text.ParseException -> L30 java.lang.Exception -> L40
            goto L37
        L30:
            r2 = move-exception
            goto L34
        L32:
            r2 = move-exception
            r7 = r1
        L34:
            r2.printStackTrace()     // Catch: java.lang.Exception -> L40
        L37:
            boolean r7 = r6.isLatestWeek(r1, r7)     // Catch: java.lang.Exception -> L40
            if (r7 == 0) goto L3f
            r7 = 1
            return r7
        L3f:
            return r0
        L40:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.manager.ManagerMainConfig.isJianCe(java.lang.String):boolean");
    }

    public boolean isLatestWeek(Date date, Date date2) {
        try {
            return !URLEnvironmentConfig.isForPublish() ? (date == null || date2 == null || ((int) (date.getTime() - date2.getTime())) <= 20000) ? false : true : (date == null || date2 == null || ((int) ((date.getTime() - date2.getTime()) / 86400000)) <= 7) ? false : true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String timeStamp2Date(String str, String str2) {
        if (str == null || str.isEmpty() || str.equals("null")) {
            return "";
        }
        return new SimpleDateFormat((str2 == null || str2.isEmpty()) ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(str).longValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void download(final String str, final boolean z, final ConfigListener configListener) {
        PermissionDialog.show("版本升级为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
        SoulPermission.getInstance().checkAndRequestPermission("android.permission.WRITE_EXTERNAL_STORAGE", new SimplePermissionAdapter() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainConfig.8
            @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
            public void onPermissionOk(Permission permission) {
                super.onPermissionOk(permission);
                PermissionDialog.dimissDialog();
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "下载apk 有存储权限");
                try {
                    ManagerMainConfig.this.progressDialogView = new UpdateProgressDialogView(ManagerMainConfig.this.context, z, configListener);
                    ManagerMainConfig.this.progressDialogView.startDownLoad(str);
                } catch (Exception e) {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "下载出现问题 跳转浏览器");
                    if (configListener != null) {
                        if (z) {
                            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "强制升级 退出应用  onexit");
                            configListener.onExit();
                        } else {
                            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "非强制升级 onback");
                            configListener.onBack();
                        }
                    }
                    e.printStackTrace();
                    UIUtils.toast("下载出现问题，正在为您转向浏览器继续下载！");
                    ManagerMainConfig.this.downloadByBrowser(str);
                }
            }

            @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
            public void onPermissionDenied(Permission permission) {
                super.onPermissionDenied(permission);
                PermissionDialog.dimissDialog();
                UIUtils.toast("需要您开启存储卡权限");
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "没有存储权限 跳转浏览器下载");
                if (configListener != null) {
                    if (z) {
                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "强制升级 退步应用 onexit");
                        configListener.onExit();
                    } else {
                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "非强制升级 返回  onback");
                        configListener.onBack();
                    }
                }
                ManagerMainConfig.this.downloadByBrowser(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadByBrowser(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(Html.fromHtml(str).toString()));
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        try {
            this.context.startActivity(intent);
        } catch (Exception unused) {
            try {
                intent.setComponent(null);
                this.context.startActivity(intent);
            } catch (Exception unused2) {
            }
        }
    }

    public void setDownLoadProgress(int i, int i2) {
        UpdateProgressDialogView updateProgressDialogView = this.progressDialogView;
        if (updateProgressDialogView != null) {
            updateProgressDialogView.setProgressBarProgress(i, i2);
        }
    }
}

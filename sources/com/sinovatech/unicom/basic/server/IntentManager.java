package com.sinovatech.unicom.basic.server;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p201hb.omapi.union.sim.SmartCard;
import com.p201hb.omapi.union.sim.listener.ConnectListener;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.adapter.SimplePermissionAdapter;
import com.p284qw.soul.permission.bean.Permission;
import com.sinovatech.unicom.basic.p314po.AdvertiseEntity;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.CustomMainActivity;
import com.sinovatech.unicom.basic.p315ui.activity.ExtranetUserActivity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.LookHistoryActivity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.activity.NetWorkExceptionActivity;
import com.sinovatech.unicom.basic.p315ui.activity.ServicePageActivity;
import com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeFuChuangEntity;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.InitUtils;
import com.sinovatech.unicom.common.IntentUtils;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity;
import com.sinovatech.unicom.separatemodule.audience.smallvideo.SearchVideoActivity;
import com.sinovatech.unicom.separatemodule.audience.smallvideo.SmallVideoActivity;
import com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity;
import com.sinovatech.unicom.separatemodule.capture.CapuActivity2;
import com.sinovatech.unicom.separatemodule.messagenotification.activity.MessageNotificationActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.notice.NoticMainActivity;
import com.sinovatech.unicom.separatemodule.quanxianshuoming.activity.QuanXianShuoMingActivity;
import com.sinovatech.unicom.separatemodule.search.SearchEntity;
import com.sinovatech.unicom.separatemodule.search.SearchManager;
import com.sinovatech.unicom.separatemodule.security.SecurityActivity;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity;
import com.tencent.qqmini.util.MiniGameUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class IntentManager {
    private static boolean checkISRequestDirectAccess(String str) {
        try {
            for (String str2 : new String[]{"LOCAL-IPCALL"}) {
                if (str2.equals(str.trim())) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static void generateIntentAndGo(Activity activity, MenuEntity menuEntity, String str) {
        try {
            if (menuEntity.isNeedLogin()) {
                if (!App.hasLogined()) {
                    if ("YES".equals(menuEntity.getBroadband())) {
                        Intent intent = new Intent(activity, LoginActivity.class);
                        intent.putExtra("directAccess", checkISRequestDirectAccess(menuEntity.getMenuURL()));
                        intent.putExtra("url", menuEntity.getMenuURL());
                        intent.putExtra("title", menuEntity.getMenuTitle());
                        intent.putExtra("menuId", menuEntity.getMenuId());
                        intent.putExtra("backMode", menuEntity.getBackMode());
                        intent.putExtra("requestType", str);
                        intent.putExtra("broadband", menuEntity.getBroadband());
                        activity.startActivity(intent);
                        return;
                    }
                    Intent intent2 = new Intent(activity, LoginBindActivity.class);
                    intent2.putExtra("directAccess", checkISRequestDirectAccess(menuEntity.getMenuURL()));
                    intent2.putExtra("url", menuEntity.getMenuURL());
                    intent2.putExtra("title", menuEntity.getMenuTitle());
                    intent2.putExtra("menuId", menuEntity.getMenuId());
                    intent2.putExtra("backMode", menuEntity.getBackMode());
                    intent2.putExtra("requestType", str);
                    intent2.putExtra("diffType", menuEntity.getDiffType());
                    activity.startActivity(intent2);
                    return;
                } else if ("YES".equals(menuEntity.getBroadband()) && !"03".equals(UserManager.getInstance().getLoginType())) {
                    Intent intent3 = new Intent(activity, LoginActivity.class);
                    intent3.putExtra("directAccess", checkISRequestDirectAccess(menuEntity.getMenuURL()));
                    intent3.putExtra("url", menuEntity.getMenuURL());
                    intent3.putExtra("title", menuEntity.getMenuTitle());
                    intent3.putExtra("menuId", menuEntity.getMenuId());
                    intent3.putExtra("backMode", menuEntity.getBackMode());
                    intent3.putExtra("requestType", str);
                    intent3.putExtra("broadband", menuEntity.getBroadband());
                    activity.startActivity(intent3);
                    return;
                } else if ("YES".equals(menuEntity.getGuhua()) && !"02".equals(UserManager.getInstance().getLoginType())) {
                    Intent intent4 = new Intent(activity, LoginActivity.class);
                    intent4.putExtra("directAccess", checkISRequestDirectAccess(menuEntity.getMenuURL()));
                    intent4.putExtra("url", menuEntity.getMenuURL());
                    intent4.putExtra("title", menuEntity.getMenuTitle());
                    intent4.putExtra("menuId", menuEntity.getMenuId());
                    intent4.putExtra("backMode", menuEntity.getBackMode());
                    intent4.putExtra("requestType", str);
                    intent4.putExtra("guhua", menuEntity.getGuhua());
                    activity.startActivity(intent4);
                    return;
                }
            }
            if (handleLocal(activity, menuEntity.getMenuTitle(), menuEntity.getMenuURL())) {
                return;
            }
            gotoWebViewActivity(activity, menuEntity.getMenuURL(), menuEntity.getMenuTitle(), menuEntity.getBackMode(), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateIntentAndGo(Activity activity, String str) {
        generateIntentAndGo(activity, str, "", true, "1", "get");
    }

    public static void generateIntentAndGo(Activity activity, String str, String str2) {
        generateIntentAndGo(activity, str, str2, true, "1", "get");
    }

    public static void generateIntentAndGo(Activity activity, String str, String str2, boolean z, String str3) {
        generateIntentAndGo(activity, str, str2, z, "1", str3);
    }

    public static void generateIntentAndGo(Activity activity, String str, String str2, boolean z, String str3, String str4) {
        if (z) {
            try {
                if (!App.hasLogined()) {
                    Intent intent = new Intent(activity, LoginBindActivity.class);
                    intent.putExtra("directAccess", checkISRequestDirectAccess(str));
                    intent.putExtra("url", str);
                    intent.putExtra("title", str2);
                    intent.putExtra("backMode", str3);
                    intent.putExtra("requestType", str4);
                    activity.startActivity(intent);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (handleLocal(activity, str2, str) || TextUtils.isEmpty(str)) {
            return;
        }
        gotoWebViewActivity(activity, str, str2, str3, str4);
    }

    public static boolean liveHandleLocal(Activity activity, String str, String str2, JSONObject jSONObject) {
        if (str2.startsWith("https://native/smallvideo/personalcenter")) {
            Intent intent = new Intent(activity, SearchVideoActivity.class);
            if ("user".equals(jSONObject.optString("type"))) {
                intent = new Intent(activity, SmallVideoActivity.class);
                intent.setFlags(335544320);
                App.getSharePreferenceUtil().putString(SmallVideoActivity.SP_KEY, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                intent.putExtra("videoList", SmallVideoActivity.SP_KEY);
            } else {
                App.getSharePreferenceUtil().putString(SearchVideoActivity.SP_PARAM_KEY, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                intent.putExtra("videoList", SearchVideoActivity.SP_PARAM_KEY);
            }
            intent.putExtra("type", "smallVideo");
            activity.startActivity(intent);
            return true;
        } else if (str2.startsWith("https://native/smallvideo/spcl/listplay")) {
            Intent intent2 = new Intent(activity, SearchVideoActivity.class);
            intent2.putExtra("type", jSONObject.optString("type"));
            App.getSharePreferenceUtil().putString(SearchVideoActivity.SP_PARAM_KEY, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            intent2.putExtra("params", SearchVideoActivity.SP_PARAM_KEY);
            activity.startActivity(intent2);
            return true;
        } else if (str2.startsWith("https://native/live/segmented/smallvideo")) {
            Intent intent3 = new Intent(activity, AudienceMainActivity.class);
            intent3.putExtra("type", jSONObject.optString("type"));
            intent3.putExtra("customVideo", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            activity.startActivity(intent3);
            return true;
        } else {
            return handleLocal(activity, str, str2);
        }
    }

    public static boolean handleLocal(Activity activity, String str, String str2) {
        return handleLocal(activity, str, str2, null);
    }

    public static boolean handleLocal(final Activity activity, final String str, String str2, Bundle bundle) {
        if ("LOCAL-YYTFB".equalsIgnoreCase(str2.trim())) {
            Intent intent = new Intent(activity, BaiduMapMainActivity.class);
            intent.putExtra("YYT", "");
            activity.startActivity(intent);
            return true;
        } else if ("LOCAL-YYTFB5G".equalsIgnoreCase(str2.trim())) {
            Intent intent2 = new Intent(activity, BaiduMapMainActivity.class);
            intent2.putExtra("YYT", "5G");
            activity.startActivity(intent2);
            return true;
        } else if ("LOCAL-FXKHD".equalsIgnoreCase(str2.trim())) {
            return true;
        } else {
            if ("LOCAL-SCAN".equalsIgnoreCase(str2.trim()) || "Native_ScanVC".equalsIgnoreCase(str2.trim())) {
                PermissionDialog.show("扫一扫为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                SoulPermission.getInstance().checkAndRequestPermission("android.permission.CAMERA", new SimplePermissionAdapter() { // from class: com.sinovatech.unicom.basic.server.IntentManager.1
                    @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
                    public void onPermissionOk(Permission permission) {
                        super.onPermissionOk(permission);
                        PermissionDialog.dimissDialog();
                        new AvoidOnResult(activity).startForResult(CapuActivity2.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.server.IntentManager.1.1
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent3) {
                                if (i == -1) {
                                    String stringExtra = intent3.getStringExtra("SCAN_RESULT");
                                    if (!TextUtils.isEmpty(stringExtra) && stringExtra.startsWith("http")) {
                                        IntentManager.gotoWebViewActivity(activity, stringExtra, "");
                                    } else {
                                        CustomDialogManager.show(activity, "温馨提示", stringExtra);
                                    }
                                }
                            }
                        });
                    }

                    @Override // com.p284qw.soul.permission.adapter.SimplePermissionAdapter, com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
                    public void onPermissionDenied(Permission permission) {
                        super.onPermissionDenied(permission);
                        PermissionDialog.dimissDialog();
                        UIUtils.toast("未开启权限");
                    }
                });
                return true;
            } else if ("LOCAL-ZXKF".equalsIgnoreCase(str2.trim())) {
                return true;
            } else {
                if ("LOCAL-AQZX".equalsIgnoreCase(str2.trim())) {
                    activity.startActivity(new Intent(activity, SecurityActivity.class));
                    return true;
                } else if ("LOCAL-SC".equalsIgnoreCase(str2.trim())) {
                    return true;
                } else {
                    if ("LOCAL-SZ".equalsIgnoreCase(str2.trim())) {
                        activity.startActivity(new Intent(activity, SettingNewActivity.class));
                        return true;
                    } else if ("浏览记录".equals(str)) {
                        activity.startActivity(new Intent(activity, LookHistoryActivity.class));
                        return true;
                    } else if ("宽带测速".equals(str)) {
                        WebParamsEntity webParamsEntity = new WebParamsEntity();
                        webParamsEntity.setUrl(str2.trim());
                        webParamsEntity.setTitle(str);
                        webParamsEntity.setBackMode("1");
                        webParamsEntity.setRequestType("get");
                        webParamsEntity.setNeedTitle(true);
                        webParamsEntity.setYule(false);
                        Intent intent3 = new Intent(activity, WebDetailActivity.class);
                        intent3.putExtra(WebFragment.webParams, webParamsEntity);
                        activity.startActivity(intent3);
                        return true;
                    } else if ("LOCAL-IPCALL".equals(str2.trim())) {
                        return true;
                    } else {
                        if (!TextUtils.isEmpty(str2) && str2.contains("LOCAL-YYTFB5G")) {
                            Intent intent4 = new Intent(activity, BaiduMapMainActivity.class);
                            intent4.putExtra("YYT", "5G");
                            activity.startActivity(intent4);
                            return true;
                        } else if (!TextUtils.isEmpty(str2) && str2.contains("LOCAL-YYTFB")) {
                            activity.startActivity(new Intent(activity, BaiduMapMainActivity.class));
                            return true;
                        } else if (!TextUtils.isEmpty(str2) && str2.contains("LOCAL-YYTFBMESSAGE")) {
                            activity.startActivity(new Intent(activity, BaiduMapMainActivity.class));
                            return true;
                        } else if ("Native_SettingVC".equals(str2.trim()) || "LOCAL-SETTING".equals(str2.trim()) || "LOCAL_SETTING".equals(str2.trim())) {
                            activity.startActivity(new Intent(activity, SettingNewActivity.class));
                            return true;
                        } else if ("LOCAL_LOGIN_BIND".equals(str2.trim()) || "LOCAL-LOGIN_BIND".equals(str2.trim())) {
                            activity.startActivity(new Intent(activity, LoginBindActivity.class));
                            return true;
                        } else if ("LOCAL_MESSAGE".equals(str2.trim()) || "LOCAL-MESSAGE".equals(str2.trim())) {
                            NoticMainActivity.gotoNotice(activity, 1);
                            return true;
                        } else if ("LOCAL-KUAIXUN".equals(str2.trim())) {
                            NoticMainActivity.gotoNotice(activity, 0);
                            return true;
                        } else if ("LOCAL-LIUYAN".equals(str2.trim())) {
                            NoticMainActivity.gotoNotice(activity, 2);
                            return true;
                        } else if ("LOCAL_SEARCH".equals(str2.trim()) || "LOCAL-SEARCH".equals(str2.trim())) {
                            new SearchManager(activity).loadScrollKeywordFromBox(new Observer<List<SearchEntity>>() { // from class: com.sinovatech.unicom.basic.server.IntentManager.2
                                @Override // io.reactivex.Observer
                                public void onComplete() {
                                }

                                @Override // io.reactivex.Observer
                                public void onSubscribe(Disposable disposable) {
                                }

                                @Override // io.reactivex.Observer
                                public void onNext(List<SearchEntity> list) {
                                    JSONArray jSONArray = new JSONArray();
                                    for (int i = 0; i < list.size() && i < 3; i++) {
                                        try {
                                            JSONObject jSONObject = new JSONObject();
                                            jSONObject.put("title", list.get(i).getTitle());
                                            jSONObject.put("url", list.get(i).getUrl());
                                            jSONArray.put(jSONObject);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("homedata", jSONArray);
                                    hashMap.put("location", str);
                                    IntentManager.gotoUnicomMiniProgram(activity, "ms_unicom_search2020", hashMap);
                                }

                                @Override // io.reactivex.Observer
                                public void onError(Throwable th) {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("homedata", new JSONArray());
                                    hashMap.put("location", str);
                                    IntentManager.gotoUnicomMiniProgram(activity, "ms_unicom_search2020", hashMap);
                                }
                            });
                            return true;
                        } else if ("LOCAL-NONETPAGE".equals(str2.trim())) {
                            activity.startActivity(new Intent(activity, NetWorkExceptionActivity.class));
                            return true;
                        } else if ("LOCAL-COINDOWNLOAD".equals(str2.trim()) || "LOCAL-SYMLXZZQ".equals(str2.trim())) {
                            return true;
                        } else {
                            if (!TextUtils.isEmpty(str2) && str2.contains("LOCAL-FINDER")) {
                                if (str2.contains("?")) {
                                    try {
                                        WebFragment.findder = str2.substring(str2.indexOf("?") + 1);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                WebFragment.isRelogin = true;
                                App.mainTagFromOtherActivity = MainActivity.Fragment_Ad;
                                activity.startActivity(new Intent(activity, MainActivity.class));
                                return true;
                            } else if (str2.contains("q.qq.com/a/sdk")) {
                                MiniGameUtils.showToast();
                                return true;
                            } else if (str2.startsWith(URLSet.getLanjieZhiboUrl())) {
                                String valueByName = getValueByName(str2, "userId");
                                String valueByName2 = getValueByName(str2, "from");
                                String valueByName3 = getValueByName(str2, "shareUserNumSc");
                                String valueByName4 = getValueByName(str2, "liveChannel");
                                String valueByName5 = getValueByName(str2, "liveShareInfo");
                                String valueByName6 = getValueByName(str2, "liveShareProvince");
                                String valueByName7 = getValueByName(str2, "liveShareCity");
                                String valueByName8 = getValueByName(str2, "liveShareChannel");
                                String valueByName9 = getValueByName(str2, "isToMultiLive");
                                String valueByName10 = getValueByName(str2, "isToSlowLive");
                                String valueByName11 = getValueByName(str2, "isToOneCity");
                                Intent intent5 = new Intent(activity, AudienceActivity.class);
                                if (!valueByName2.equals("fromJS") && TextUtils.isEmpty(valueByName) && !valueByName2.equals("task")) {
                                    intent5 = new Intent(activity, AudienceMainActivity.class);
                                }
                                if (!TextUtils.isEmpty(valueByName9) && "Y".equals(valueByName9) && !valueByName2.equals("task") && !TextUtils.isEmpty(valueByName)) {
                                    intent5 = new Intent(activity, AudienceMainActivity.class);
                                }
                                if ("Y".equals(valueByName10)) {
                                    if (!TextUtils.isEmpty(valueByName)) {
                                        intent5 = new Intent(activity, AudienceActivity.class);
                                    } else {
                                        intent5 = new Intent(activity, AudienceMainActivity.class);
                                    }
                                    intent5.putExtra("isToSlowLive", valueByName10);
                                }
                                if ("Y".equals(valueByName11)) {
                                    if (!TextUtils.isEmpty(valueByName)) {
                                        intent5 = new Intent(activity, AudienceActivity.class);
                                    } else {
                                        intent5 = new Intent(activity, AudienceMainActivity.class);
                                    }
                                    intent5.putExtra("isToOneCity", valueByName11);
                                }
                                intent5.putExtra("userId", valueByName);
                                intent5.putExtra("from", valueByName2);
                                intent5.putExtra("shareUserNumSc", valueByName3);
                                intent5.putExtra("liveChannel", valueByName4);
                                intent5.putExtra("isToMultiLive", valueByName9);
                                intent5.putExtra("shareInfo", valueByName5);
                                intent5.putExtra("shareCity", valueByName7);
                                intent5.putExtra("shareProvince", valueByName6);
                                if (TextUtils.isEmpty(valueByName8)) {
                                    valueByName8 = "0";
                                }
                                intent5.putExtra("shareChannel", valueByName8);
                                activity.startActivity(intent5);
                                return true;
                            } else if (str2.contains("/zhibo/index.html#/smallvideo")) {
                                String valueByName12 = getValueByName(str2, "liveChannel");
                                String valueByName13 = getValueByName(str2, "videoId");
                                Intent intent6 = new Intent(activity, AudienceMainActivity.class);
                                intent6.putExtra("type", "smallVideo");
                                intent6.putExtra("liveChannel", valueByName12);
                                intent6.putExtra("singleVideo", valueByName13);
                                activity.startActivity(intent6);
                                return true;
                            } else if (str2.endsWith(URLSet.getInterceptLive())) {
                                Intent intent7 = new Intent(activity, AudienceMainActivity.class);
                                intent7.putExtra("type", "smallVideo");
                                activity.startActivity(intent7);
                                return true;
                            } else if (str2.startsWith("https://native/minusonescreen/spcl")) {
                                String valueByName14 = getValueByName(str2, "liveChannel");
                                String valueByName15 = getValueByName(str2, "tabCode");
                                Intent intent8 = new Intent(activity, SmallVideoActivity.class);
                                intent8.putExtra("type", AudienceMainActivity.RING_FLAG);
                                if (TextUtils.isEmpty(valueByName15)) {
                                    valueByName15 = "4235";
                                }
                                intent8.putExtra(AudienceMainActivity.RING_FLAG, valueByName15);
                                intent8.putExtra("liveChannel", valueByName14);
                                activity.startActivity(intent8);
                                return true;
                            } else if (str2.startsWith("https://native/minusonescreen/home")) {
                                String valueByName16 = getValueByName(str2, "index");
                                String valueByName17 = getValueByName(str2, "liveChannel");
                                Intent intent9 = new Intent(activity, VideoCenterActivity.class);
                                intent9.putExtra("typeIndex", valueByName16);
                                intent9.putExtra("liveChannel", valueByName17);
                                activity.startActivity(intent9);
                                return true;
                            } else if (str2.contains("zhibo/index.html#/huifangplayer?")) {
                                Intent intent10 = new Intent(activity, AudiencePlayBackActivity.class);
                                String valueByName18 = getValueByName(str2, "userId");
                                String valueByName19 = getValueByName(str2, "videoId");
                                intent10.putExtra("userId", valueByName18);
                                intent10.putExtra("videoId", valueByName19);
                                activity.startActivity(intent10);
                                return true;
                            } else if (str2.contains("LOCAL-NATIVELIVE")) {
                                activity.startActivity(new Intent(activity, AudienceMainActivity.class));
                                return true;
                            } else if (str2.contains("LOCAL-ZXDL")) {
                                generateIntentAndGo(activity, "file://", "");
                                return true;
                            } else if (str2.contains("LOCAL-TOUTIAOSHORTVIDEO")) {
                                return true;
                            } else {
                                if (str2.contains("type=simbox2")) {
                                    SmartCard.Companion.connect(new ConnectListener() { // from class: com.sinovatech.unicom.basic.server.-$$Lambda$IntentManager$Fc6OZV-9Pa4hJo8OUo-9qoTR6Es
                                        @Override // com.p201hb.omapi.union.sim.listener.ConnectListener
                                        public final void finish(int i) {
                                            UIUtils.logD("type=simbox", "---" + i);
                                        }
                                    });
                                    WebParamsEntity webParamsEntity2 = new WebParamsEntity();
                                    webParamsEntity2.setUrl(str2);
                                    webParamsEntity2.setTitle(str);
                                    webParamsEntity2.setBackMode("1");
                                    webParamsEntity2.setRequestType("get");
                                    webParamsEntity2.setNeedTitle(true);
                                    webParamsEntity2.setYule(false);
                                    Intent intent11 = new Intent(activity, WebDetailActivity.class);
                                    intent11.putExtra(WebFragment.webParams, webParamsEntity2);
                                    new AvoidOnResult(activity).startForResult(intent11, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.server.-$$Lambda$IntentManager$O6s19GQ186y2j76OE4wHbrS8ZM4
                                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                                        public final void onActivityResult(int i, Intent intent12) {
                                            SmartCard.Companion.close();
                                        }
                                    });
                                    return true;
                                } else if (str2.contains("LOCAL-SIMYIKATONG")) {
                                    InitUtils.initJSCard(UserManager.getInstance().getCurrentPhoneNumber());
                                    Intent intent12 = new Intent();
                                    intent12.setAction("android.intent.action.VIEW");
                                    intent12.setData(Uri.parse("jscard://load.server:8888/main"));
                                    activity.startActivity(intent12);
                                    TYCJBoxManager.getInstance().collectClickSdk(activity, "S2ndpage1214", "", "一卡通", str2, "com.crb.jscard", "0");
                                    return true;
                                } else if (str2.contains("LOCAL-QUANXIANSHUOMING")) {
                                    if (TextUtils.isEmpty(str)) {
                                        str = "调取权限说明";
                                    }
                                    Intent intent13 = new Intent(activity, QuanXianShuoMingActivity.class);
                                    intent13.putExtra("title", str);
                                    activity.startActivity(intent13);
                                    return true;
                                } else if ("Native_SettingNotificationCenter".equals(str2.trim())) {
                                    activity.startActivity(new Intent(activity, MessageNotificationActivity.class));
                                    return true;
                                } else if ("LOCAL-YWCENTER".equals(str2.trim())) {
                                    Intent intent14 = new Intent(activity, ExtranetUserActivity.class);
                                    if (bundle != null) {
                                        intent14.putExtras(bundle);
                                    }
                                    activity.startActivity(intent14);
                                    return true;
                                } else if ("LOCAL-SingleServer".equals(str2.trim())) {
                                    activity.startActivity(new Intent(activity, ServicePageActivity.class));
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static String getValueByName(String str, String str2) {
        String[] split;
        for (String str3 : str.substring(str.indexOf("?") + 1).split("&")) {
            if (str3.contains(str2)) {
                return str3.replace(str2 + "=", "");
            }
        }
        return "";
    }

    public static void generateAdvertiseIntentAndGo(Activity activity, AdvertiseEntity advertiseEntity, String str) {
        try {
            if (advertiseEntity.isNeedLogin() && !App.hasLogined()) {
                Intent intent = new Intent(activity, LoginBindActivity.class);
                intent.putExtra("directAccess", checkISRequestDirectAccess(advertiseEntity.getAdvertiseTargetURL()));
                intent.putExtra("url", advertiseEntity.getAdvertiseTargetURL());
                intent.putExtra("title", advertiseEntity.getAdvertiseTitle());
                intent.putExtra("backMode", advertiseEntity.getAdvertiseBackMode());
                intent.putExtra("requestType", str);
                intent.putExtra("diffType", advertiseEntity.getDiffType());
                activity.startActivity(intent);
            } else if (!TextUtils.isEmpty(advertiseEntity.getAdvertiseTargetType()) && "local".equalsIgnoreCase(advertiseEntity.getAdvertiseTargetType().trim())) {
                handleLocal(activity, advertiseEntity.getAdvertiseTitle(), advertiseEntity.getAdvertiseTargetURL());
            } else if (TextUtils.isEmpty(advertiseEntity.getAdvertiseTargetURL())) {
            } else {
                gotoWebViewActivity(activity, advertiseEntity.getAdvertiseTargetURL(), advertiseEntity.getAdvertiseTitle(), advertiseEntity.getAdvertiseBackMode(), str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateFuChuangIntentAndGo(Activity activity, HomeMergeFuChuangEntity homeMergeFuChuangEntity, String str) {
        try {
            if (homeMergeFuChuangEntity.isNeedLogin() && !App.hasLogined()) {
                Intent intent = new Intent(activity, LoginBindActivity.class);
                intent.putExtra("directAccess", checkISRequestDirectAccess(homeMergeFuChuangEntity.getAdvertiseTargetURL()));
                intent.putExtra("url", homeMergeFuChuangEntity.getAdvertiseTargetURL());
                intent.putExtra("title", homeMergeFuChuangEntity.getAdvertiseTitle());
                intent.putExtra("backMode", homeMergeFuChuangEntity.getAdvertiseBackMode());
                intent.putExtra("requestType", str);
                intent.putExtra("diffType", "");
                activity.startActivity(intent);
            } else if (!TextUtils.isEmpty(homeMergeFuChuangEntity.getAdvertiseTargetType()) && "local".equalsIgnoreCase(homeMergeFuChuangEntity.getAdvertiseTargetType().trim())) {
                handleLocal(activity, homeMergeFuChuangEntity.getAdvertiseTitle(), homeMergeFuChuangEntity.getAdvertiseTargetURL());
            } else if (TextUtils.isEmpty(homeMergeFuChuangEntity.getAdvertiseTargetURL())) {
            } else {
                gotoWebViewActivity(activity, homeMergeFuChuangEntity.getAdvertiseTargetURL(), homeMergeFuChuangEntity.getAdvertiseTitle(), homeMergeFuChuangEntity.getAdvertiseBackMode(), str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gotoWebViewActivity(Activity activity, String str, String str2) {
        if (TextUtils.isEmpty(str) || handleLocal(activity, str2, str) || TextUtils.isEmpty(str)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle(str2);
        webParamsEntity.setBackMode("1");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public static void gotoWebViewActivityBundle(Activity activity, String str, String str2, Bundle bundle) {
        if (TextUtils.isEmpty(str) || handleLocal(activity, str2, str, bundle) || TextUtils.isEmpty(str)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle(str2);
        webParamsEntity.setBackMode("1");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public static void gotoWebViewActivity(Activity activity, String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || handleLocal(activity, str2, str) || TextUtils.isEmpty(str)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle(str2);
        webParamsEntity.setBackMode("1");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        webParamsEntity.setNoAppendParams(z);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public static void gotoWebViewActivity(Activity activity, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || handleLocal(activity, str2, str)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle(str2);
        webParamsEntity.setBackMode(str3);
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public static void gotoWebViewActivity(Activity activity, String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str) || handleLocal(activity, str2, str)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle(str2);
        webParamsEntity.setBackMode(str3);
        webParamsEntity.setRequestType(str4);
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public static void gotoWebViewActivity(Activity activity, String str, String str2, String str3, String str4, boolean z) {
        if (TextUtils.isEmpty(str) || handleLocal(activity, str2, str)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle(str2);
        webParamsEntity.setBackMode(str3);
        webParamsEntity.setRequestType(str4);
        webParamsEntity.setNeedTitle(z);
        webParamsEntity.setYule(false);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public static void gotoWebViewActivity(Activity activity, String str, String str2, String str3, String str4, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str) || handleLocal(activity, str2, str)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle(str2);
        webParamsEntity.setBackMode(str3);
        webParamsEntity.setRequestType(str4);
        webParamsEntity.setNeedTitle(z);
        webParamsEntity.setYule(z2);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public static void gotoUnicomMiniProgram(Activity activity, String str, Map<String, Object> map) {
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle("");
        webParamsEntity.setBackMode("1");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(false);
        webParamsEntity.setYule(false);
        webParamsEntity.setType("UnicomMiniProgram");
        String uuid = UUID.randomUUID().toString();
        App.navigateParamsCacheMap.put(uuid, map);
        webParamsEntity.setNavigateParamsUUID(uuid);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public static void intentFilter(Activity activity) {
        intentFilter(activity, false);
    }

    public static void intentFilter(Activity activity, boolean z) {
        intentFilter(activity, z, false);
    }

    public static void intentFilter(Activity activity, boolean z, boolean z2) {
        if (!App.isCityToHome && z && IntentUtils.isShowCustomCityActivity()) {
            setMainLanuchMode("3", ConfigManager.getJumpActUrl());
            Intent intent = new Intent(activity, CustomMainActivity.class);
            intent.putExtra("ms_unicom_url", ConfigManager.getJumpActUrl());
            activity.startActivity(intent);
            activity.finish();
        } else if (App.getSharePreferenceUtil().getBoolean("CareHome")) {
            setMainLanuchMode("1", "ms_unicom_guanhuai");
            Intent intent2 = new Intent(activity, CustomMainActivity.class);
            intent2.putExtra("ms_unicom_url", "ms_unicom_guanhuai");
            activity.startActivity(intent2);
            activity.finish();
        } else if (TextUtils.equals(LanguageUtil.CHN_WEIWUER, LanguageUtil.getInstance().getLanguage()) && LanguageUtil.getInstance().isOpenWeiWen() && LanguageUtil.getInstance().getConfigLanguageSwitch()) {
            setMainLanuchMode("2", "ms_unicom_weiwen");
            Intent intent3 = new Intent(activity, CustomMainActivity.class);
            intent3.putExtra("ms_unicom_url", "ms_unicom_weiwen");
            activity.startActivity(intent3);
            activity.finish();
        } else if (TextUtils.equals(LanguageUtil.USA, LanguageUtil.getInstance().getLanguage()) && App.hasLogined() && LanguageUtil.getInstance().getConfigLanguageSwitch()) {
            setMainLanuchMode("2", "ms_unicom_english");
            Intent intent4 = new Intent(activity, CustomMainActivity.class);
            intent4.putExtra("ms_unicom_url", "ms_unicom_english");
            activity.startActivity(intent4);
            activity.finish();
        } else if (!LanguageUtil.getInstance().isChinese() && LanguageUtil.getInstance().getConfigLanguageSwitch() && LanguageUtil.getInstance().isHasLinkurl()) {
            setMainLanuchMode("2", LanguageUtil.getInstance().getLanguageLinkurl());
            Intent intent5 = new Intent(activity, CustomMainActivity.class);
            intent5.putExtra("ms_unicom_url", LanguageUtil.getInstance().getLanguageLinkurl());
            activity.startActivity(intent5);
            activity.finish();
        } else {
            setMainLanuchMode("0", "");
            if (activity instanceof MainActivity) {
                return;
            }
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        }
    }

    private static void setMainLanuchMode(String str, String str2) {
        MsLogUtil.m7979d("lanuchMode", String.format("type = %s , url = %s", str, str2));
        App.getSharePreferenceUtil().putString("unicom_app_main_type", str);
        App.getSharePreferenceUtil().putString("unicom_app_main_url", str2);
    }

    public static void gotoWebViewActivityWithParams(Activity activity, String str, String str2, Map<String, Object> map) {
        MsLogUtil.m7979d("getFreezeHtml", "paramsMap---:" + map.toString());
        if (TextUtils.isEmpty(str)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle(str2);
        webParamsEntity.setBackMode("1");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        String uuid = UUID.randomUUID().toString();
        App.navigateParamsCacheMap.put(uuid, map);
        webParamsEntity.setNavigateParamsUUID(uuid);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public static void goYwUserInfoActivity(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, ExtranetUserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("infoUrl", str2);
        bundle.putString("nickName", str);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
}

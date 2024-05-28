package com.sinovatech.unicom.p318ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.p086v7.app.AppCompatActivity;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.boxcenter.LoginConfigDataCenter;
import com.sinovatech.unicom.basic.broadcast.ScreenBroadCastManager;
import com.sinovatech.unicom.basic.eventbus.FinishActivityEvent;
import com.sinovatech.unicom.basic.eventbus.WebSocketEvent;
import com.sinovatech.unicom.basic.p314po.LoginConfigEntity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMainLogin;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.KacaoPvLog;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.huidu.ManagerHuiDu;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJUploadManager;
import com.sinovatech.unicom.separatemodule.user.UserFragment;
import com.sinovatech.unicom.separatemodule.webrtc.RtcHelper;
import com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceHelper;
import com.sinovatech.unicom.separatemodule.webrtc.RtcWebInstance;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.IFloatWindow;
import com.yhao.floatwindow.PermissionUtil;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.ui.BaseActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaseActivity extends AppCompatActivity {
    private static final long BACK_GROUND_TIME = 120000;
    public static long appOnCreateTime;
    private static long backGroundTime;
    public static long countusetime = System.currentTimeMillis();
    public static boolean flag;
    private Activity activityContext;

    /* renamed from: am */
    public ActivityManager f18640am;
    private AnquanzhognxinCheckStart anquanzhongxinLock;
    private boolean locationFlag;
    private ManagerMainLogin managerMainLogin;
    OnOpenHuaWeiZheDie onOpenHuaWeiZheDie;
    private ScreenshotHandler ssHandler;
    private ScreenshotObserver ssObserver;
    private UserManager userManager;
    public int[] quickResources = {2131231190, 2131230819, 2131232040, 2131232039, 2131231975, 2131232425, 2131232041, 2131231273, 2131232225, 2131232433};
    private boolean isLoadLanguage = false;

    /* renamed from: com.sinovatech.unicom.ui.BaseActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97121 implements ScreenBroadCastManager.ScreenBroadCastListener {
        final /* synthetic */ BaseActivity this$0;

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sinovatech.unicom.ui.BaseActivity$1$1 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        class C97131 implements Consumer<Boolean> {
            final /* synthetic */ C97121 this$1;

            C97131(C97121 c97121) {
                JniLib.m15918cV(this, c97121, 173);
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
            }
        }

        C97121(BaseActivity baseActivity) {
            JniLib.m15918cV(this, baseActivity, 176);
        }

        @Override // com.sinovatech.unicom.basic.broadcast.ScreenBroadCastManager.ScreenBroadCastListener
        public void onScreenOFF() {
            JniLib.m15918cV(this, 174);
        }

        @Override // com.sinovatech.unicom.basic.broadcast.ScreenBroadCastManager.ScreenBroadCastListener
        public void onScreenOn() {
            JniLib.m15918cV(this, 175);
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.BaseActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97153 implements AnquanzhognxinCheckStart.StartCallBack {
        final /* synthetic */ BaseActivity this$0;
        final /* synthetic */ Consumer val$consumer;

        C97153(BaseActivity baseActivity, Consumer consumer) {
            JniLib.m15918cV(this, baseActivity, consumer, 180);
        }

        @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart.StartCallBack
        public void complete() {
            JniLib.m15918cV(this, 178);
        }

        @Override // com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart.StartCallBack
        public void onCancel() {
            JniLib.m15918cV(this, 179);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.ui.BaseActivity$OnOpenHuaWeiZheDie */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnOpenHuaWeiZheDie {
        void onZheDieChange();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAnquanzognxin(Consumer<Boolean> consumer) {
        JniLib.m15918cV(this, consumer, 37);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void comeHome(ComponentName componentName) {
        JniLib.m15918cV(this, componentName, 38);
    }

    private boolean fixOrientation() {
        return JniLib.m15917cZ(this, 39);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ComponentName getTopActivity() {
        Object m15920cL = JniLib.m15920cL(this, 40);
        if (m15920cL == null) {
            return null;
        }
        return (ComponentName) m15920cL;
    }

    private boolean isTranslucentOrFloating() {
        return JniLib.m15917cZ(this, 41);
    }

    private void setQuickAccess() {
        JniLib.m15918cV(this, 42);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        JniLib.m15918cV(this, configuration, 32);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 33);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        JniLib.m15918cV(this, 34);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFinishEvent(FinishActivityEvent finishActivityEvent) {
        JniLib.m15918cV(this, finishActivityEvent, 35);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 36);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (flag) {
            App.sbManager.registerScreenBroadCastReceiver(getApplicationContext());
        }
        final ComponentName topActivity = getTopActivity();
        if (flag && topActivity != null && !"group.pals.android.lib.ui.lockpattern.LockPatternActivity".equals(topActivity.getClassName().trim()) && !App.reEnterAfterCloseApplication_ForGesturePassword) {
            countusetime = System.currentTimeMillis();
            try {
                IFloatWindow iFloatWindow = FloatWindow.get();
                if (iFloatWindow != null && !iFloatWindow.isShowing() && PermissionUtil.hasPermission(this.activityContext)) {
                    iFloatWindow.show();
                }
            } catch (Exception e) {
                MsLogUtil.m7978e("切换到前台显示浮窗异常:" + e.getMessage());
            }
            new Handler().postDelayed(new Runnable(this) { // from class: com.sinovatech.unicom.ui.BaseActivity.2
                final /* synthetic */ BaseActivity this$0;

                {
                    JniLib.m15918cV(this, this, 177);
                }

                @Override // java.lang.Runnable
                public void run() {
                    EventBusUtils.post(new WebSocketEvent(EventBusUtils.EVENTCODE_REFRESH_WEBSOCKET));
                }
            }, 1000L);
            App.reLoadDefaultMenuData = true;
            App.reLoadAdvertise = true;
            App.reRefreshUserAccount = true;
            if (backGroundTime > 0) {
                LoginConfigEntity entity = new LoginConfigDataCenter().getEntity();
                String start_interva = entity.getStart_interva();
                long currentTimeMillis = System.currentTimeMillis() - backGroundTime;
                if ("on".equals(start_interva)) {
                    try {
                        if (currentTimeMillis > ((long) (Integer.parseInt(entity.getIntervalTime()) * 1000))) {
                            realExit();
                            LanguageUtil.getInstance().relaunchApp();
                            return;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            this.managerMainLogin.welcomRefreshToken(this.activityContext, "2", new ManagerMainLogin.LoginCompleteInterface() { // from class: com.sinovatech.unicom.ui.-$$Lambda$BaseActivity$hbyyDB1tVfU8z2zRYWtqe8n8eyY
                @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerMainLogin.LoginCompleteInterface
                public final void complete() {
                    JniLib.m15918cV(this, 122);
                }
            });
            String str = "";
            String str2 = "其他-浏览";
            Activity activity = this.activityContext;
            if (activity instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) activity;
                if (mainActivity.isHOMETAB()) {
                    str = "homePage";
                    str2 = "首页-浏览";
                }
                if (mainActivity.isUSERTAB()) {
                    str = "myPage";
                    str2 = "我的-浏览";
                }
            }
            PvCurrencyLogUtils.pvSwitchApp("Q001", str, str2);
            KacaoPvLog.log(this, "热启动");
            WebHostSafelyManager.getInstance().loadHostSafetyConfig();
            ManagerHuiDu.getInstance().loadHuiduConfig();
        }
        getContentResolver().unregisterContentObserver(this.ssObserver);
        getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.ssObserver);
        flag = false;
        App.reEnterAfterCloseApplication_ForGesturePassword = false;
        RtcWebInstance.getInstance().setFlag(false);
        if (RtcHelper.getInstance() != null) {
            RtcHelper.getInstance().hideOrResumeWindows(false);
        }
        if (RtcVoiceHelper.getInstance() != null) {
            RtcVoiceHelper.getInstance().hideOrResumeWindows(false);
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        String str;
        String str2;
        super.onStop();
        try {
            ComponentName topActivity = getTopActivity();
            if ((topActivity != null && !getPackageName().equals(topActivity.getPackageName().trim()) && !PackageFilter.isExitInClassNameFilterCollection(topActivity.getClassName().trim()) && !App.fromGalleryForGesture) || !App.isTopProcess) {
                IFloatWindow iFloatWindow = FloatWindow.get();
                if (iFloatWindow != null && iFloatWindow.isShowing()) {
                    iFloatWindow.hide();
                }
                if (App.sbManager != null) {
                    App.sbManager.unRegisterScreenBroadCastReceiver(getApplicationContext());
                }
                flag = true;
                if (!App.realexit) {
                    String string = App.getInstance().getString(2131886213);
                    UIUtils.toast(string + "已切换至后台运行");
                    EventBusUtils.post(new WebSocketEvent(EventBusUtils.EVENTCODE_CLOSE_WEBSOCKET));
                    RtcWebInstance.getInstance().setFlag(true);
                    if (RtcHelper.getInstance() != null) {
                        RtcHelper.getInstance().hideOrResumeWindows(true);
                    }
                    if (RtcVoiceHelper.getInstance() != null) {
                        RtcVoiceHelper.getInstance().hideOrResumeWindows(true);
                    }
                    setQuickAccess();
                    if (AudienceActivity.bdCloudVideoView != null && AudienceActivity.bdCloudVideoView.isPlaying()) {
                        AudienceActivity.bdCloudVideoView.pause();
                        AudienceActivity.bdCloudVideoView.stopPlayback();
                    }
                    backGroundTime = System.currentTimeMillis();
                }
                String str3 = "";
                String str4 = "其他-退出";
                if (this.activityContext instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) this.activityContext;
                    if (mainActivity.isHOMETAB()) {
                        str3 = "homePage";
                        str4 = "首页-退出";
                    }
                    if (mainActivity.isUSERTAB()) {
                        str3 = "myPage";
                        str4 = "我的-退出";
                    }
                }
                PvCurrencyLogUtils.pvSwitchApp("Q002", str3, str4);
                long currentTimeMillis = (System.currentTimeMillis() - countusetime) / 1000;
                if (currentTimeMillis != 0) {
                    PvCurrencyLogUtils.pvAppTime("staytime0001", currentTimeMillis + "");
                }
                MsLogUtil.m7979d("countusetime", "使用的时间：" + currentTimeMillis);
                try {
                    String valueOf = String.valueOf(appOnCreateTime);
                    String valueOf2 = String.valueOf(countusetime);
                    if (App.realexit) {
                        backGroundTime = System.currentTimeMillis();
                    }
                    String valueOf3 = String.valueOf(backGroundTime);
                    String valueOf4 = String.valueOf(backGroundTime - countusetime);
                    String valueOf5 = String.valueOf(backGroundTime - appOnCreateTime);
                    String str5 = App.realexit ? "退出应用" : "进入后台";
                    Activity topActivity2 = SoulPermission.getInstance().getTopActivity();
                    if (topActivity2 == null) {
                        str = "-";
                        str2 = str;
                    } else if (topActivity2 instanceof WebDetailActivity) {
                        WebDetailActivity webDetailActivity = (WebDetailActivity) topActivity2;
                        String webUrl = webDetailActivity.getWebUrl();
                        str2 = webDetailActivity.getWebTitle();
                        str = webUrl;
                    } else {
                        str = topActivity2.getClass().getSimpleName();
                        str2 = "-";
                    }
                    TYCJBoxManager.getInstance().collectAppOut(valueOf, valueOf2, valueOf3, valueOf4, valueOf5, str5, str, str2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                TYCJUploadManager.upoloadTongyicaiji();
                MsLogUtil.m7979d("TYCJUploadManager", "用户切后台或者退出应用开始上传");
            }
            getContentResolver().unregisterContentObserver(this.ssObserver);
            this.ssHandler.hideScreenshot();
            App.fromGalleryForGesture = false;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007a A[Catch: Exception -> 0x00c4, TryCatch #1 {Exception -> 0x00c4, blocks: (B:5:0x0012, B:7:0x0024, B:11:0x002e, B:13:0x0034, B:30:0x0076, B:35:0x0091, B:32:0x007a, B:33:0x0082, B:34:0x008a, B:20:0x0054, B:23:0x005f, B:26:0x006a, B:36:0x0094, B:38:0x00a6, B:40:0x00b1, B:46:0x00bf, B:39:0x00ab, B:43:0x00ba), top: B:54:0x0012, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0082 A[Catch: Exception -> 0x00c4, TryCatch #1 {Exception -> 0x00c4, blocks: (B:5:0x0012, B:7:0x0024, B:11:0x002e, B:13:0x0034, B:30:0x0076, B:35:0x0091, B:32:0x007a, B:33:0x0082, B:34:0x008a, B:20:0x0054, B:23:0x005f, B:26:0x006a, B:36:0x0094, B:38:0x00a6, B:40:0x00b1, B:46:0x00bf, B:39:0x00ab, B:43:0x00ba), top: B:54:0x0012, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008a A[Catch: Exception -> 0x00c4, TryCatch #1 {Exception -> 0x00c4, blocks: (B:5:0x0012, B:7:0x0024, B:11:0x002e, B:13:0x0034, B:30:0x0076, B:35:0x0091, B:32:0x007a, B:33:0x0082, B:34:0x008a, B:20:0x0054, B:23:0x005f, B:26:0x006a, B:36:0x0094, B:38:0x00a6, B:40:0x00b1, B:46:0x00bf, B:39:0x00ab, B:43:0x00ba), top: B:54:0x0012, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void addDefaultQuick() {
        /*
            r8 = this;
            com.sinovatech.unicom.basic.boxcenter.CacheDataCenter r0 = com.sinovatech.unicom.basic.boxcenter.CacheDataCenter.getInstance()
            boolean r0 = r0.getFirstOpen()
            if (r0 == 0) goto Lc8
            com.sinovatech.unicom.basic.boxcenter.CacheDataCenter r0 = com.sinovatech.unicom.basic.boxcenter.CacheDataCenter.getInstance()
            java.lang.String r0 = r0.getQuickAccessData()
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lc4
            r1.<init>(r0)     // Catch: java.lang.Exception -> Lc4
            java.lang.String r0 = "data"
            org.json.JSONArray r0 = r1.getJSONArray(r0)     // Catch: java.lang.Exception -> Lc4
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Exception -> Lc4
            r1.<init>()     // Catch: java.lang.Exception -> Lc4
            if (r0 == 0) goto Lc3
            int r2 = r0.length()     // Catch: java.lang.Exception -> Lc4
            if (r2 != 0) goto L2c
            goto Lc3
        L2c:
            r2 = 0
            r3 = r2
        L2e:
            int r4 = r0.length()     // Catch: java.lang.Exception -> Lc4
            if (r3 >= r4) goto L94
            org.json.JSONObject r4 = r0.optJSONObject(r3)     // Catch: java.lang.Exception -> Lc4
            java.lang.String r5 = "title"
            java.lang.String r4 = r4.optString(r5)     // Catch: java.lang.Exception -> Lc4
            r5 = -1
            int r6 = r4.hashCode()     // Catch: java.lang.Exception -> Lc4
            r7 = -195690751(0xfffffffff455ff01, float:-6.7818074E31)
            if (r6 == r7) goto L6a
            r7 = 635160940(0x25dbc96c, float:3.8126933E-16)
            if (r6 == r7) goto L5f
            r7 = 641876147(0x264240b3, float:6.7394955E-16)
            if (r6 == r7) goto L54
            goto L75
        L54:
            java.lang.String r6 = "余量查询"
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> Lc4
            if (r4 == 0) goto L75
            r4 = r2
            goto L76
        L5f:
            java.lang.String r6 = "交费充值"
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> Lc4
            if (r4 == 0) goto L75
            r4 = 2
            goto L76
        L6a:
            java.lang.String r6 = "话费与账单"
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> Lc4
            if (r4 == 0) goto L75
            r4 = 1
            goto L76
        L75:
            r4 = r5
        L76:
            switch(r4) {
                case 0: goto L8a;
                case 1: goto L82;
                case 2: goto L7a;
                default: goto L79;
            }     // Catch: java.lang.Exception -> Lc4
        L79:
            goto L91
        L7a:
            org.json.JSONObject r4 = r0.optJSONObject(r3)     // Catch: java.lang.Exception -> Lc4
            r1.put(r4)     // Catch: java.lang.Exception -> Lc4
            goto L91
        L82:
            org.json.JSONObject r4 = r0.optJSONObject(r3)     // Catch: java.lang.Exception -> Lc4
            r1.put(r4)     // Catch: java.lang.Exception -> Lc4
            goto L91
        L8a:
            org.json.JSONObject r4 = r0.optJSONObject(r3)     // Catch: java.lang.Exception -> Lc4
            r1.put(r4)     // Catch: java.lang.Exception -> Lc4
        L91:
            int r3 = r3 + 1
            goto L2e
        L94:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lc4
            r0.<init>()     // Catch: java.lang.Exception -> Lc4
            java.lang.String r2 = "data"
            r0.put(r2, r1)     // Catch: java.lang.Exception -> Lc4
            com.sinovatech.unicom.basic.boxcenter.CacheDataCenter r2 = com.sinovatech.unicom.basic.boxcenter.CacheDataCenter.getInstance()     // Catch: java.lang.Exception -> Lc4
            boolean r3 = r0 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> Lc4
            if (r3 != 0) goto Lab
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> Lc4
            goto Lb1
        Lab:
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch: java.lang.Exception -> Lc4
            java.lang.String r0 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r0)     // Catch: java.lang.Exception -> Lc4
        Lb1:
            r2.setUseQuickData(r0)     // Catch: java.lang.Exception -> Lc4
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> Lc4
            r2 = 25
            if (r0 < r2) goto Lc8
            r8.saveQuickData(r1)     // Catch: java.lang.Exception -> Lbe
            goto Lc8
        Lbe:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Exception -> Lc4
            goto Lc8
        Lc3:
            return
        Lc4:
            r0 = move-exception
            r0.printStackTrace()
        Lc8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.p318ui.BaseActivity.addDefaultQuick():void");
    }

    private void saveQuickData(JSONArray jSONArray) {
        if (Build.VERSION.SDK_INT >= 25) {
            try {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    Intent intent = new Intent();
                    intent.setAction("");
                    intent.putExtra("url", jSONArray.optJSONObject(i).optString("linkUrl"));
                    intent.putExtra("title", jSONArray.optJSONObject(i).optString("title"));
                    intent.putExtra("enterType", "quickAccessType");
                    intent.setClassName("com.sinovatech.unicom.ui", "com.sinovatech.unicom.basic.ui.activity.WelcomeClient");
                    arrayList.add(new ShortcutInfo.Builder(this, jSONArray.optJSONObject(i).optString("title")).setIntent(intent).setRank(i).setShortLabel(jSONArray.optJSONObject(i).optString("title")).setIcon(Icon.createWithResource(this, this.quickResources[Integer.parseInt(jSONArray.optJSONObject(i).optString("iconType")) - 1])).build());
                }
                Intent intent2 = new Intent();
                intent2.setAction("");
                intent2.putExtra("url", "QuickAccessActivity");
                intent2.putExtra("enterType", "quickAccessType");
                intent2.setClassName("com.sinovatech.unicom.ui", "com.sinovatech.unicom.basic.ui.activity.WelcomeClient");
                arrayList.add(new ShortcutInfo.Builder(this, "setting").setIntent(intent2).setShortLabel("更多设置").setRank(3).setIcon(Icon.createWithResource(this, 2131232081)).build());
                if (arrayList.size() > 0) {
                    ShortcutManager shortcutManager = (ShortcutManager) getSystemService(ShortcutManager.class);
                    shortcutManager.removeAllDynamicShortcuts();
                    shortcutManager.addDynamicShortcuts(arrayList);
                    CacheDataCenter.getInstance().setFirstOpen("1");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setOnOpenHuaWeiZheDie(OnOpenHuaWeiZheDie onOpenHuaWeiZheDie) {
        this.onOpenHuaWeiZheDie = onOpenHuaWeiZheDie;
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return super.getResources();
    }

    private void realExit() {
        App.setLogined(LoginStateConst.UNLOGIN);
        try {
            App.realexit = true;
            App.homeCardBg = false;
            App.cardBgMap.clear();
            UserFragment.currentPhone = "";
            ManagerLocation.releaseManagerLocation();
            this.managerMainLogin.stopRefreshOnlineToken();
            App.isCityToHome = false;
            App.isShowFingerdialog = false;
            App.getSharePreferenceUtil().putString("lat", "");
            App.getSharePreferenceUtil().putString("long", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

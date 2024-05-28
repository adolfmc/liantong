package com.sinovatech.unicom.common;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.cloud.media.player.BDCloudMediaPlayer;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.CrashUtils;
import com.crb.jscard.JSCardService;
import com.mob.MobSDK;
import com.networkbench.agent.impl.NBSAppAgent;
import com.p201hb.omapi.union.sim.SmartCard;
import com.p281qq.p282e.comm.managers.GDTAdSdk;
import com.p281qq.p282e.comm.managers.setting.GlobalSetting;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.advtise.Constants;
import com.sinovatech.unicom.separatemodule.audience.BDCloudSdkInitManager;
import com.sinovatech.unicom.separatemodule.chuanshanjia.TTAdManagerHolder;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.huidu.ManagerHuiDu;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;
import com.sinovatech.unicom.separatemodule.security.LockPatternUtil;
import com.sinovatech.unicom.separatemodule.unicompay.UnicomPayUtils;
import com.tencent.p348mm.opensdk.openapi.WXAPIFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class InitUtils {
    private static final String TAG = "InitUtils";
    private static boolean mIsServiceAlive;
    private static Intent mServiceIntent;

    public static void initDPSdk(Application application) {
    }

    public static void init(Application application) {
        initBugly(application);
        initWeixinSDK(application);
    }

    public static void initMobSDK(Application application) {
        try {
            MobSDK.init(application, "2abdab54e079", "bf9046de0702f5d691fbc27abe648a86");
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("初始化[分享]SDK异常 " + e.getMessage());
        }
    }

    public static void initTtadSDK(Application application) {
        try {
            TTAdManagerHolder.init(application);
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("初始化[初始换穿山甲]SDK异常 " + e.getMessage());
        }
    }

    public static void initYLHSDK(Application application) {
        try {
            GlobalSetting.setEnableCollectAppInstallStatus(false);
            GDTAdSdk.init(application, Constants.APPID);
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7977e("初始化[优量汇]SDK异常 ", e.getMessage());
        }
    }

    public static void initWeixinSDK(Application application) {
        try {
            App.iwxapi = WXAPIFactory.createWXAPI(application, "wxa13d0b8c5270d1ff", true);
            App.iwxapi.registerApp("wxa13d0b8c5270d1ff");
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7977e("初始化[微信]SDK异常 ", e.getMessage());
        }
    }

    private static void initBugly(Application application) {
        try {
            CrashUtils.init(new CrashUtils.OnCrashListener() { // from class: com.sinovatech.unicom.common.InitUtils.1
                @Override // com.blankj.utilcode.util.CrashUtils.OnCrashListener
                public void onCrash(CrashUtils.CrashInfo crashInfo) {
                    App.getSharePreferenceUtil().putLong("unicom_app_crash_time", System.currentTimeMillis());
                    MsLogUtil.m7979d("JIA-CRASH", "程序异常:" + crashInfo.toString());
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7978e("全局捕获异常:" + e.getMessage());
        }
    }

    private static String getProcessName(int i) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i + "/cmdline"));
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                readLine = readLine.trim();
            }
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
                MsLogUtil.m7978e("获取进程名称关闭流异常");
            }
            return readLine;
        } catch (Throwable th2) {
            th = th2;
            try {
                th.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        MsLogUtil.m7978e("获取进程名称关闭流异常");
                    }
                }
                return null;
            } catch (Throwable th3) {
                BufferedReader bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        MsLogUtil.m7978e("获取进程名称关闭流异常");
                    }
                }
                throw th3;
            }
        }
    }

    public static void initJSCard(String str) {
        try {
            JSCardService.initialize(App.getInstance(), str);
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("初始化[高频应用一卡通]SDK异常 " + e.getMessage());
        }
    }

    private static boolean isOwnProcess() {
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) App.getInstance().getSystemService("activity");
            if (activityManager != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == myPid && App.getInstance().getPackageManager().getApplicationInfo(App.getInstance().getPackageName(), 0).packageName.equals(runningAppProcessInfo.processName)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void initApp(Application application) {
        try {
            if (isOwnProcess()) {
                Log.d("APP", "APP启动 InitUtils-initAPP-内部执行");
                App.getAsyncHttpClient().addNetworkInterceptor(new App.DevicIdInterceptor());
                App.getAsyncHttpClient().addNetworkInterceptor(new App.OkHttpUserAgentInterceptor());
                if (URLSet.PrePublic_mode) {
                    App.getAsyncHttpClient().addNetworkInterceptor(new App.PrePublicInterceptor());
                }
                if (App.getInstance().getPackageName().endsWith("beta")) {
                    App.getAsyncHttpClient().addNetworkInterceptor(new App.TiyanBeataInterceptor());
                }
                if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
                    App.getAsyncHttpClient().addAuthenticator(new App.TestAuthenticator2());
                }
                App.getAsyncHttpClient().addNetworkInterceptor(new ManagerHuiDu.HuiDuHeaderInterceptor());
                initDTGZ(application);
                initUniComCollect(application);
                initMobSDK(application);
                initTtadSDK(application);
                initYLHSDK(application);
                initBaiDuMap(application);
                initBaiduLive(application);
                initBdPlayer();
                initLockPatter(application);
                initUnicomPaySdk(application);
                initPush(application);
                try {
                    SmartCard.Companion.init(application);
                } catch (Exception e) {
                    e.printStackTrace();
                    MsLogUtil.m7978e("初始化[sim卡保险箱]异常" + e.getMessage());
                }
            }
        } catch (Exception e2) {
            Log.e("初始化sdk", "initApp: " + e2.getMessage());
        }
    }

    public static void initPush(Application application) {
        try {
            PushManager.getInstance().init(application);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initDTGZ(Application application) {
        try {
            NBSAppAgent.setLicenseKey("2f0f8655a7414449bcada3e4dd3daf4f").setRedirectHost("playback.newbuy.chinaunicom.cn/tyapp").setStartOption(511).startInApplication(application.getApplicationContext());
            NBSAppAgent.setUserIdentifier(DeviceHelper.getDeviceID(true));
        } catch (Exception e) {
            MsLogUtil.m7978e("初始化动态感知sdk异常:" + e.getMessage());
        }
    }

    public static void initUniComCollect(Application application) {
        try {
            UnicomCollectManager.getInstance().init(application);
        } catch (Exception e) {
            MsLogUtil.m7978e("初始化动态感知sdk埋点异常:" + e.getMessage());
        }
    }

    public static void initLockPatter(Application application) {
        try {
            LockPatternUtil.init(application, 5, 4);
        } catch (Exception e) {
            MsLogUtil.m7977e("InitUtils", "初始化手势密码异常:" + e.getMessage());
        }
    }

    private static void initBaiDuMap(Application application) {
        try {
            SDKInitializer.setAgreePrivacy(application, true);
            SDKInitializer.initialize(application);
            LocationClient.setAgreePrivacy(true);
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("初始化[百度地图]SDK异常 " + e.getMessage());
        }
    }

    private static void disableAPIDialog() {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mHiddenApiWarningShown");
            declaredField.setAccessible(true);
            declaredField.setBoolean(invoke, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initBaiduLive(Context context) {
        try {
            BDCloudSdkInitManager.getInstance().onCreate(context, "801126566693444609041");
            disableAPIDialog();
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("初始化[直播]SDK异常 " + e.getMessage());
        }
    }

    private static void initBdPlayer() {
        try {
            BDCloudMediaPlayer.setAppId("87683699241530982401p");
            BDCloudMediaPlayer.setAK("3927c43912004909bf897578e93bc5f9");
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("初始化[百度播放器]SDK失败 " + e.getMessage());
        }
    }

    private static void startKeepAliveService() {
        Intent intent = mServiceIntent;
        App.getInstance().startService(mServiceIntent);
        mIsServiceAlive = true;
    }

    private static void stopKeepAliveService() {
        if (mIsServiceAlive) {
            App.getInstance().stopService(mServiceIntent);
            mServiceIntent = null;
            mIsServiceAlive = false;
        }
    }

    private static void initUnicomPaySdk(Application application) {
        try {
            UnicomPayUtils.initSdk(application);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

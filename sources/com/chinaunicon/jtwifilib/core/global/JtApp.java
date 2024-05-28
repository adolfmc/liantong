package com.chinaunicon.jtwifilib.core.global;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.CookieManager;
import android.webkit.WebView;
import androidx.annotation.RequiresApi;
import com.chinaunicon.jtwifilib.core.crashreport.CrashHandler;
import com.chinaunicon.jtwifilib.core.net.callback.OkCallback;
import com.chinaunicon.jtwifilib.core.net.parser.OkJsonParser;
import com.chinaunicon.jtwifilib.core.net.proxy.OkHttpProxy;
import com.chinaunicon.jtwifilib.core.utils.AesEncryptUtil;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.core.utils.JtToastUtil;
import com.chinaunicon.jtwifilib.core.utils.SharedPreferencesHelp;
import com.chinaunicon.jtwifilib.core.utils.StringUtil;
import com.chinaunicon.jtwifilib.jtcommon.JtDataBuffer;
import com.chinaunicon.jtwifilib.jtcommon.JtOnAuthSuccessListener;
import com.chinaunicon.jtwifilib.jtcommon.model.JtInitParams;
import com.chinaunicon.jtwifilib.jtcommon.model.JtLogModel;
import com.chinaunicon.jtwifilib.jtcommon.model.JtNetConfig;
import com.chinaunicon.jtwifilib.jtcommon.model.JtNetPremission;
import com.chinaunicon.jtwifilib.jtcommon.p188db.DBManager;
import com.chinaunicon.jtwifilib.jtcommon.p188db.DatabaseManager;
import com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtApp {
    public static Context context;
    private static DisplayMetrics displayMetrics;
    public static Application mApplication;
    private static JtApp mInstance;
    private DBManager dbManager;
    public ExecutorService executorService;
    public ExecutorService executorServiceDb;
    private JtOnAuthSuccessListener onAuthSuccessListener;
    private String appKey = "";
    private boolean isSuccess = false;

    public static synchronized JtApp getInstance() {
        JtApp jtApp;
        synchronized (JtApp.class) {
            if (mInstance == null) {
                mInstance = new JtApp();
            }
            jtApp = mInstance;
        }
        return jtApp;
    }

    public JtApp init(Application application, boolean z) {
        CrashHandler.getInstance().init(application.getApplicationContext());
        mApplication = application;
        context = mApplication.getApplicationContext();
        this.dbManager = new DBManager(context);
        this.dbManager.copyDBFile();
        DatabaseManager.initializeInstance(this.dbManager);
        initJLog(z);
        this.executorService = Executors.newFixedThreadPool(1);
        this.executorServiceDb = Executors.newFixedThreadPool(10);
        return this;
    }

    public DBManager getManager() {
        return this.dbManager;
    }

    public void uploadLog() {
        Runnable runnable = new Runnable() { // from class: com.chinaunicon.jtwifilib.core.global.JtApp.1
            @Override // java.lang.Runnable
            public void run() {
                int count = JtApp.this.dbManager.getCount();
                if (count <= 1000) {
                    List<JtLogModel> selectLog = JtApp.this.dbManager.selectLog("0,1000");
                    JtL.m16342e("上报数据：" + JtGsonUtil.getInstance().toJson((Object) selectLog));
                    JtUploadLog.getInstance(JtApp.context).upListUserLog(selectLog, JtApp.this.dbManager);
                    return;
                }
                int i = count / 1000;
                for (int i2 = 0; i2 < i; i2++) {
                    DBManager dBManager = JtApp.this.dbManager;
                    List<JtLogModel> selectLog2 = dBManager.selectLog((i2 * 1000) + ",1000");
                    JtL.m16342e("上报数据：" + JtGsonUtil.getInstance().toJson((Object) selectLog2));
                    JtUploadLog.getInstance(JtApp.context).upListUserLog(selectLog2, JtApp.this.dbManager);
                }
            }
        };
        if (this.executorServiceDb.isShutdown()) {
            return;
        }
        this.executorServiceDb.execute(runnable);
    }

    public void clearToken() {
        JtDataBuffer.token = "";
    }

    public String getAppKey() {
        return !TextUtils.isEmpty(this.appKey) ? this.appKey : "";
    }

    public boolean getAppkeySuccess() {
        return this.isSuccess;
    }

    public JtApp setAppKey(String str) {
        if (TextUtils.isEmpty(str)) {
            JtToastUtil.showShort(context, "APPkey不能为空");
            return this;
        }
        this.appKey = str;
        int i = SharedPreferencesHelp.getInstance(context, "version").getInt("JT_VERSION_CODE", 0);
        JtInitParams jtInitParams = new JtInitParams();
        jtInitParams.setAppKey(str);
        jtInitParams.setPlatform("android");
        jtInitParams.setVersion(i + "");
        jtInitParams.setSdkVersion("1.1.2");
        jtInitParams.setUuid(StringUtil.getOnly(context));
        jtInitParams.setTime(System.currentTimeMillis() + "");
        String string = SharedPreferencesHelp.getInstance(context, "uuid").getString("user", "");
        String string2 = SharedPreferencesHelp.getInstance(context, "uuid").getString("province", "");
        String string3 = SharedPreferencesHelp.getInstance(context, "uuid").getString("city", "");
        String string4 = SharedPreferencesHelp.getInstance(context, "uuid").getString("provinceCode", "");
        String string5 = SharedPreferencesHelp.getInstance(context, "uuid").getString("cityCode", "");
        jtInitParams.setUser(string);
        jtInitParams.setProvince(string2);
        jtInitParams.setCity(string3);
        jtInitParams.setProvinceCode(string4);
        jtInitParams.setCityCode(string5);
        String json = JtGsonUtil.getInstance().toJson(jtInitParams);
        JtL.m16342e("源码：" + json);
        JtUploadLog.getInstance(context).updateData("1", json, "CONFIG_PULL_START");
        String str2 = "";
        try {
            str2 = AesEncryptUtil.encryptString(json);
        } catch (Exception unused) {
        }
        JtL.m16342e("验证信息：" + str2);
        OkHttpProxy.post().setCustomRequestBodyEntity(str2).url("http://120.52.12.5:18080/zw_interface/api/user/updateVersionAndCheckAuth").tag(this).enqueue(new OkCallback<String>(new OkJsonParser<String>() { // from class: com.chinaunicon.jtwifilib.core.global.JtApp.2
        }) { // from class: com.chinaunicon.jtwifilib.core.global.JtApp.3
            @Override // com.chinaunicon.jtwifilib.core.net.callback.OkCallback
            public void onSuccess(int i2, String str3) {
                try {
                    String decryptString = AesEncryptUtil.decryptString(str3);
                    JtL.m16342e(decryptString);
                    if (i2 == 0) {
                        JtNetConfig jtNetConfig = (JtNetConfig) JtGsonUtil.getInstance().fromJson(decryptString, (Class<Object>) JtNetConfig.class);
                        if ("0".equals(jtNetConfig.getIsSuceess())) {
                            JtUploadLog.getInstance(JtApp.context).updateData("1", decryptString, "CONFIG_PULL_SUCCESS");
                            JtApp.this.isSuccess = true;
                            if (JtApp.this.onAuthSuccessListener != null) {
                                JtApp.this.onAuthSuccessListener.success();
                            }
                        } else {
                            JtUploadLog jtUploadLog = JtUploadLog.getInstance(JtApp.context);
                            jtUploadLog.updateData("2", "初始化失败！请检查appkey:" + JtApp.this.appKey + ",当前环境：true", "ERROR_PULL_CONFIG");
                            JtApp.this.isSuccess = false;
                            if (JtApp.this.onAuthSuccessListener != null) {
                                JtApp.this.onAuthSuccessListener.filed();
                            }
                        }
                        JtApp.this.saveAsConfig(jtNetConfig);
                        return;
                    }
                    JtUploadLog jtUploadLog2 = JtUploadLog.getInstance(JtApp.context);
                    jtUploadLog2.updateData("2", "初始化失败！客户端未获取到初始化配置:" + i2 + ":" + str3 + true, "ERROR_PULL_CONFIG");
                    if (JtApp.this.onAuthSuccessListener != null) {
                        JtApp.this.onAuthSuccessListener.filed();
                    }
                } catch (Exception e) {
                    JtUploadLog jtUploadLog3 = JtUploadLog.getInstance(JtApp.context);
                    jtUploadLog3.updateData("2", "初始化失败！客户端未获取到初始化配置" + e.getMessage() + true, "ERROR_PULL_CONFIG");
                    if (JtApp.this.onAuthSuccessListener != null) {
                        JtApp.this.onAuthSuccessListener.filed();
                    }
                }
            }

            @Override // com.chinaunicon.jtwifilib.core.net.callback.OkCallback
            public void onFailure(Exception exc) {
                if (JtApp.this.onAuthSuccessListener != null) {
                    JtApp.this.onAuthSuccessListener.filed();
                }
                JtUploadLog jtUploadLog = JtUploadLog.getInstance(JtApp.context);
                jtUploadLog.updateData("2", "初始化失败！客户端未获取到初始化配置" + exc.getMessage(), "ERROR_PULL_CONFIG");
            }
        });
        return this;
    }

    public void setOnAuthSuccessListener(JtOnAuthSuccessListener jtOnAuthSuccessListener) {
        this.onAuthSuccessListener = jtOnAuthSuccessListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAsConfig(final JtNetConfig jtNetConfig) {
        this.executorService.execute(new Runnable() { // from class: com.chinaunicon.jtwifilib.core.global.JtApp.4
            @Override // java.lang.Runnable
            public void run() {
                JtApp.this.dbManager.copyDBFile();
                JtApp.this.dbManager.dropTable();
                JtNetConfig jtNetConfig2 = jtNetConfig;
                JtApp.this.dbManager.insertConfig(TextUtils.isEmpty(jtNetConfig2.getIsSuceess()) ? "" : jtNetConfig2.getIsSuceess(), TextUtils.isEmpty(jtNetConfig2.getVersion()) ? "" : jtNetConfig2.getVersion(), TextUtils.isEmpty(jtNetConfig2.getDownload()) ? "" : jtNetConfig2.getDownload(), TextUtils.isEmpty(jtNetConfig2.getIsLatest()) ? "" : jtNetConfig2.getIsLatest());
                List<JtNetPremission> cmd = jtNetConfig2.getCmd();
                if (cmd != null) {
                    int size = cmd.size();
                    for (int i = 0; i < size; i++) {
                        JtApp.this.dbManager.insertPremission(cmd.get(i).getName());
                    }
                }
            }
        });
    }

    private void initJLog(boolean z) {
        JtL.isDebug = z;
    }

    public String getFilesDirPath() {
        return context.getFilesDir().getAbsolutePath();
    }

    public String getCacheDirPath() {
        return context.getCacheDir().getAbsolutePath();
    }

    public Context getContext() {
        return context;
    }

    public Application getmApplication() {
        return mApplication;
    }

    @RequiresApi(api = 21)
    public void clearCache(Context context2) {
        try {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().removeSessionCookies(null);
            new WebView(context2).clearCache(true);
            clearCacheFolder(new File(context2.getCacheDir().getParent() + "/app_webview"), System.currentTimeMillis());
        } catch (Exception unused) {
        }
    }

    private int clearCacheFolder(File file, long j) {
        File[] listFiles;
        if (file == null || !file.isDirectory()) {
            return 0;
        }
        try {
            int i = 0;
            for (File file2 : file.listFiles()) {
                try {
                    if (file2.isDirectory()) {
                        i += clearCacheFolder(file2, j);
                    }
                    if (file2.lastModified() < j && file2.delete()) {
                        i++;
                    }
                } catch (Exception unused) {
                    return i;
                }
            }
            return i;
        } catch (Exception unused2) {
            return 0;
        }
    }
}

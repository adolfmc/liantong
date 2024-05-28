package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.content.Context;
import android.support.annotation.Nullable;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener2;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.dic.ManagerWebCacheDictionary;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.aes.utils.AesEncryptUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogEnu;
import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogManager;
import com.sinovatech.unicom.separatemodule.miniprogram.lowcode.storagejsplugin.LowcodeJSStorageBox;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import net.lingala.zip4j.ZipFile;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CumpLanucher {
    public static String TAG = "cumpTag";
    private static CumpLanucher instance;
    private Context context;
    private Date step2_logStartTime;
    private String fileType = JtClient.UXUE_TEMP_FILE_SUFFIX;
    private String publishType = "2";
    private String serverPublishType = "";
    private int lowcodeAppUpdateNoticeFlag = 0;
    private Box<CumpEntity> cumpBox = App.getBoxStore().boxFor(CumpEntity.class);
    private Box<CumpLowCodeRenderEntity> lowcodeRenderBox = App.getBoxStore().boxFor(CumpLowCodeRenderEntity.class);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface lanuchListener {
        void onLanuchError(String str, String str2);

        void onLanuchRetry(String str, boolean z);

        void onLanuchStart(CumpEntity cumpEntity);

        void onLanuchSuccess(CumpEntity cumpEntity);

        void onLaunchDataAsyncUpdate(CumpEntity cumpEntity);

        void onLaunchVersionUpdate(CumpEntity cumpEntity, String str);
    }

    static /* synthetic */ int access$608(CumpLanucher cumpLanucher) {
        int i = cumpLanucher.lowcodeAppUpdateNoticeFlag;
        cumpLanucher.lowcodeAppUpdateNoticeFlag = i + 1;
        return i;
    }

    public static synchronized CumpLanucher getInstance(Context context) {
        CumpLanucher cumpLanucher;
        synchronized (CumpLanucher.class) {
            if (instance == null) {
                synchronized (CumpLanucher.class) {
                    if (instance == null) {
                        instance = new CumpLanucher(context);
                    }
                }
            }
            cumpLanucher = instance;
        }
        return cumpLanucher;
    }

    private CumpLanucher(Context context) {
        this.context = context;
    }

    public void insertDebugAppInfo(CumpEntity cumpEntity) {
        CumpEntity findFirst = this.cumpBox.query().equal(CumpEntity_.appId, cumpEntity.getAppId()).build().findFirst();
        if (findFirst != null) {
            this.cumpBox.remove((Box<CumpEntity>) findFirst);
        }
        this.cumpBox.put((Box<CumpEntity>) cumpEntity);
    }

    public CumpEntity getAppInfoFromBox(String str) {
        try {
            return this.cumpBox.query().equal(CumpEntity_.appId, str).build().findUnique();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadCumpInfoByAppId(final CumpEntity cumpEntity, Consumer<CumpResponse> consumer, Consumer<Throwable> consumer2) {
        final Date date = new Date();
        final String officialVersion = cumpEntity.getOfficialVersion();
        final String appId = cumpEntity.getAppId();
        String timestamp = cumpEntity.getTimestamp();
        HashMap hashMap = new HashMap();
        hashMap.put("appId", appId);
        hashMap.put("timestamp", timestamp);
        hashMap.put("publishType", this.publishType);
        String str = TAG;
        MsLogUtil.m7979d(str, "CumpLanucher-loadCumpInfoByAppId 根据AppId查小程序信息  appId参数=" + ((String) hashMap.get("appId")) + "  publishType参数=" + ((String) hashMap.get("publishType")));
        CumpReqResManager.createRequest(this.context, URLSet.getEdopAppIdInfoUrl(), "getAppBasiclnfo", hashMap).observeOn(Schedulers.computation()).flatMap(new Function<CumpResponse, ObservableSource<CumpResponse>>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.3
            @Override // io.reactivex.functions.Function
            public ObservableSource<CumpResponse> apply(@NonNull CumpResponse cumpResponse) throws Exception {
                String str2 = CumpLanucher.TAG;
                MsLogUtil.m7979d(str2, "CumpLanucher-loadCumpInfoByAppId 接口返回:" + cumpResponse.toString());
                if ("0000".equals(cumpResponse.getRespCode())) {
                    JSONObject body = cumpResponse.getBody();
                    String str3 = appId;
                    cumpResponse = CumpEntityParser.parse(CumpLanucher.TAG, cumpResponse, body, CumpLanucher.this.cumpBox, CumpLanucher.this.publishType);
                }
                return Observable.just(cumpResponse);
            }
        }).doOnNext(new Consumer<CumpResponse>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.2
            @Override // io.reactivex.functions.Consumer
            public void accept(CumpResponse cumpResponse) throws Exception {
                String str2;
                String str3;
                String respMsg;
                try {
                    CumpEntity cumpEntity2 = (CumpEntity) CumpLanucher.this.cumpBox.query().equal(CumpEntity_.appId, cumpEntity.getAppId()).build().findUnique();
                    if (cumpEntity2 != null) {
                        str2 = cumpEntity2.getAppName();
                        str3 = cumpEntity2.getOfficialVersion();
                    } else {
                        str2 = "";
                        str3 = "";
                    }
                    String str4 = "0000".equals(cumpResponse.getRespCode()) ? CumpLogEnu.urlStatus_Success : CumpLogEnu.urlStatus_Fail;
                    if ("0000".equals(cumpResponse.getRespCode())) {
                        JSONObject body = cumpResponse.getBody();
                        respMsg = !(body instanceof JSONObject) ? body.toString() : NBSJSONObjectInstrumentation.toString(body);
                    } else {
                        respMsg = cumpResponse.getRespMsg();
                    }
                    CumpLogManager.getInstance(CumpLanucher.this.context).log_SCE01(CumpLogEnu.APP_PRO_01, date, new Date(), appId, str2, URLSet.getEdopAppIdInfoUrl(), respMsg, str4, officialVersion, str3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                String str2;
                String str3;
                try {
                    CumpEntity cumpEntity2 = (CumpEntity) CumpLanucher.this.cumpBox.query().equal(CumpEntity_.appId, cumpEntity.getAppId()).build().findUnique();
                    if (cumpEntity2 != null) {
                        str2 = cumpEntity2.getAppName();
                        str3 = cumpEntity2.getOfficialVersion();
                    } else {
                        str2 = "";
                        str3 = "";
                    }
                    CumpLogManager.getInstance(CumpLanucher.this.context).log_SCE01(CumpLogEnu.APP_PRO_01, date, new Date(), appId, str2, URLSet.getEdopAppIdInfoUrl(), th.getMessage(), CumpLogEnu.urlStatus_Fail, officialVersion, str3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, consumer2);
    }

    public void lanuchCump(final CumpEntity cumpEntity, String str, final lanuchListener lanuchlistener) {
        this.publishType = str;
        String str2 = TAG;
        MsLogUtil.m7979d(str2, "CumpLanucher-lanuchCump 请求启动小程序 appId=" + cumpEntity.getAppId() + " appName=" + cumpEntity.getAppName());
        CumpEntity findFirst = this.cumpBox.query().equal(CumpEntity_.appId, cumpEntity.getAppId()).build().findFirst();
        if (findFirst == null) {
            loadCumpInfoByAppId(cumpEntity, new Consumer<CumpResponse>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.4
                @Override // io.reactivex.functions.Consumer
                public void accept(CumpResponse cumpResponse) throws Exception {
                    String str3 = CumpLanucher.TAG;
                    MsLogUtil.m7979d(str3, "CumpLanucher-lanuchCump 根据AppId查询结果:" + cumpResponse.getRespCode() + " " + cumpResponse.getRespMsg());
                    CumpEntity cumpEntity2 = (CumpEntity) CumpLanucher.this.cumpBox.query().equal(CumpEntity_.appId, cumpEntity.getAppId()).build().findUnique();
                    if ("0000".equals(cumpResponse.getRespCode()) && cumpEntity2 != null) {
                        lanuchlistener.onLanuchStart(cumpEntity2);
                        CumpLanucher.this.preEnvir(cumpEntity2, lanuchlistener);
                        return;
                    }
                    String str4 = CumpLanucher.TAG;
                    MsLogUtil.m7979d(str4, "CumpLanucher-lanuchCump 找不到小程序信息: " + cumpResponse.getRespCode() + " " + cumpResponse.getRespMsg());
                    CumpLanucher.this.deleteApp(cumpEntity.getAppId());
                    lanuchlistener.onLanuchError(cumpResponse.getRespCode(), cumpResponse.getRespMsg());
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.5
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str3 = CumpLanucher.TAG;
                    MsLogUtil.m7977e(str3, "CumpLanucher-lanuchCump 错误：" + th.getMessage());
                    CumpLanucher.this.deleteApp(cumpEntity.getAppId());
                    lanuchListener lanuchlistener2 = lanuchlistener;
                    lanuchlistener2.onLanuchError("", "小程序启动失败,请重新进入[" + th.getMessage() + "]");
                }
            });
            return;
        }
        lanuchlistener.onLanuchStart(findFirst);
        preEnvir(findFirst, lanuchlistener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preEnvir(CumpEntity cumpEntity, lanuchListener lanuchlistener) {
        if ("2".equals(cumpEntity.getPublishMethod())) {
            checkLowcodeRender(cumpEntity, lanuchlistener);
        } else {
            checkZipFile(cumpEntity, lanuchlistener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void noticeLowcodeAppUpdate(final CumpEntity cumpEntity, final lanuchListener lanuchlistener) {
        loadLowcodeRender(new Consumer<CumpLowCodeRenderEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.6
            @Override // io.reactivex.functions.Consumer
            public void accept(CumpLowCodeRenderEntity cumpLowCodeRenderEntity) throws Exception {
                if (cumpLowCodeRenderEntity != null && cumpLowCodeRenderEntity.isRequestUpdate()) {
                    MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkLowcodeRender 低代码渲染器需要升级，删除本地文件，然后提示升级小程序");
                    CumpLanucher.this.deleteLowcodeRender();
                    lanuchlistener.onLaunchVersionUpdate(cumpEntity, CumpLanucher.this.serverPublishType);
                    return;
                }
                MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkLowcodeRender 低代码渲染器不需要升级");
                if (CumpLanucher.this.lowcodeAppUpdateNoticeFlag >= 1) {
                    lanuchlistener.onLaunchVersionUpdate(cumpEntity, CumpLanucher.this.serverPublishType);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.7
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                if (CumpLanucher.this.lowcodeAppUpdateNoticeFlag >= 1) {
                    lanuchlistener.onLaunchVersionUpdate(cumpEntity, CumpLanucher.this.serverPublishType);
                }
            }
        });
    }

    private void checkZipFile(final CumpEntity cumpEntity, final lanuchListener lanuchlistener) {
        try {
            this.step2_logStartTime = new Date();
            File createAPPDirs = createAPPDirs(cumpEntity.getAppId());
            if (new File(createAPPDirs, cumpEntity.getAppId() + this.fileType).exists()) {
                MsLogUtil.m7979d(TAG, "CumpLanucher-checkZipFile 存在小程序部署包zip文件");
                startApp(cumpEntity, lanuchlistener);
            } else {
                MsLogUtil.m7979d(TAG, "CumpLanucher-checkZipFile 找不到小程序部署包zip文件，开始下载流程");
                Observable.create(new ObservableOnSubscribe<CumpEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.10
                    @Override // io.reactivex.ObservableOnSubscribe
                    public void subscribe(final ObservableEmitter<CumpEntity> observableEmitter) throws Exception {
                        String officialPackageUrl = cumpEntity.getOfficialPackageUrl();
                        String absolutePath = CumpLanucher.this.createAPPDirs(cumpEntity.getAppId()).getAbsolutePath();
                        DownloadTask build = new DownloadTask.Builder(officialPackageUrl, absolutePath, cumpEntity.getAppId() + CumpLanucher.this.fileType).setMinIntervalMillisCallbackProcess(300).setPassIfAlreadyCompleted(false).setConnectionCount(1).build();
                        if (StatusUtil.isSameTaskPendingOrRunning(build)) {
                            build.cancel();
                        }
                        build.execute(new DownloadListener2() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.10.1
                            @Override // com.liulishuo.okdownload.DownloadListener
                            public void taskStart(@android.support.annotation.NonNull DownloadTask downloadTask) {
                                String str = CumpLanucher.TAG;
                                MsLogUtil.m7979d(str, "CumpLanucher-checkZipFile 下载zip文件taskStart：" + downloadTask.getUrl());
                            }

                            @Override // com.liulishuo.okdownload.DownloadListener
                            public void taskEnd(@android.support.annotation.NonNull DownloadTask downloadTask, @android.support.annotation.NonNull EndCause endCause, @Nullable Exception exc) {
                                String str = "";
                                if (endCause != null) {
                                    str = "" + endCause.toString();
                                    MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkZipFile 下载zip文件 cause：" + endCause.toString() + " status:" + StatusUtil.getStatus(downloadTask).name());
                                }
                                if (exc != null) {
                                    str = str + exc.getMessage();
                                    MsLogUtil.m7977e(CumpLanucher.TAG, "CumpLanucher-checkZipFile 下载zip文件 realCause：" + exc.getMessage() + " status:" + StatusUtil.getStatus(downloadTask).name());
                                }
                                if (StatusUtil.getStatus(downloadTask) == StatusUtil.Status.COMPLETED || (endCause != null && endCause.compareTo(EndCause.COMPLETED) == 0)) {
                                    MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkZipFile 下载zip文件 complete：" + downloadTask.getFile().getAbsolutePath());
                                    observableEmitter.onNext(cumpEntity);
                                    observableEmitter.onComplete();
                                    return;
                                }
                                MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkZipFile 下载zip文件失败，删除。");
                                observableEmitter.onError(new RuntimeException("下载zip文件失败 errorMsg:" + str + " status:" + StatusUtil.getStatus(downloadTask).name()));
                            }
                        });
                    }
                }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CumpEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.8
                    @Override // io.reactivex.functions.Consumer
                    public void accept(CumpEntity cumpEntity2) throws Exception {
                        CumpLanucher.this.startApp(cumpEntity2, lanuchlistener);
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.9
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) {
                        String str = CumpLanucher.TAG;
                        MsLogUtil.m7977e(str, "CumpLanucher-checkZipFile 错误：" + th.getMessage());
                        try {
                            ManagerWebCacheDictionary.clearEdopAppRuntimeDic(cumpEntity.getAppId());
                            lanuchlistener.onLanuchRetry(CumpLanucher.this.serverPublishType, true);
                            CumpLogManager.getInstance(CumpLanucher.this.context).log_SCE01(CumpLogEnu.APP_PRO_02, CumpLanucher.this.step2_logStartTime, new Date(), cumpEntity.getAppId(), cumpEntity.getAppName(), cumpEntity.getOfficialPackageUrl(), th.getMessage(), CumpLogEnu.urlStatus_Fail, "", cumpEntity.getOfficialVersion());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            MsLogUtil.m7977e(str, "CumpLanucher-checkZipFile 错误：" + e.getMessage());
            try {
                ManagerWebCacheDictionary.clearEdopAppRuntimeDic(cumpEntity.getAppId());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            lanuchlistener.onLanuchError("", "小程序启动失败,请重新进入[" + e.getMessage() + "]");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startApp(final CumpEntity cumpEntity, final lanuchListener lanuchlistener) {
        File file;
        try {
            file = new File(createAPPDirs(cumpEntity.getAppId()), "index.html");
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            MsLogUtil.m7977e(str, "CumpLanucher-startApp 错误:" + e.getMessage());
            try {
                ManagerWebCacheDictionary.clearEdopAppRuntimeDic(cumpEntity.getAppId());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            lanuchlistener.onLanuchError("", "小程序启动失败,请重新进入[" + e.getMessage() + "]");
            try {
                CumpLogManager.getInstance(this.context).log_SCE01(CumpLogEnu.APP_PRO_02, this.step2_logStartTime, new Date(), cumpEntity.getAppId(), cumpEntity.getAppName(), cumpEntity.getOfficialPackageUrl(), e.getMessage(), CumpLogEnu.urlStatus_Fail, "", cumpEntity.getOfficialVersion());
                return;
            } catch (Exception e3) {
                e3.printStackTrace();
                return;
            }
        }
        if (file.exists()) {
            MsLogUtil.m7979d(TAG, "CumpLanucher-startApp 运行时环境有资源，开始启动小程序");
            lanuchlistener.onLanuchSuccess(cumpEntity);
        } else {
            MsLogUtil.m7979d(TAG, "CumpLanucher-startApp 准备解压zip文件到运行时环境");
            unzipFile(createAPPDirs(cumpEntity.getAppId()) + File.separator + cumpEntity.getAppId() + this.fileType, createAPPDirs(cumpEntity.getAppId()).getAbsolutePath());
            if (file.exists()) {
                MsLogUtil.m7979d(TAG, "CumpLanucher-startApp 解压完成，开始启动小程序");
                lanuchlistener.onLanuchSuccess(cumpEntity);
                try {
                    CumpLogManager.getInstance(this.context).log_SCE01(CumpLogEnu.APP_PRO_02, this.step2_logStartTime, new Date(), cumpEntity.getAppId(), cumpEntity.getAppName(), cumpEntity.getOfficialPackageUrl(), "", CumpLogEnu.urlStatus_Success, "", cumpEntity.getOfficialVersion());
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            } else {
                MsLogUtil.m7979d(TAG, "CumpLanucher-startApp 解压完成，检查目录层级不对，删除缓存文件");
                ManagerWebCacheDictionary.clearEdopAppRuntimeDic(cumpEntity.getAppId());
                lanuchlistener.onLanuchError("", "小程序启动失败,请重新进入[根目录下找不到index.html文件]");
                try {
                    CumpLogManager.getInstance(this.context).log_SCE01(CumpLogEnu.APP_PRO_02, this.step2_logStartTime, new Date(), cumpEntity.getAppId(), cumpEntity.getAppName(), cumpEntity.getOfficialPackageUrl(), "根目录下找不到index.html文件", CumpLogEnu.urlStatus_Fail, "", cumpEntity.getOfficialVersion());
                    return;
                } catch (Exception e5) {
                    e5.printStackTrace();
                    return;
                }
            }
            e.printStackTrace();
            String str2 = TAG;
            MsLogUtil.m7977e(str2, "CumpLanucher-startApp 错误:" + e.getMessage());
            ManagerWebCacheDictionary.clearEdopAppRuntimeDic(cumpEntity.getAppId());
            lanuchlistener.onLanuchError("", "小程序启动失败,请重新进入[" + e.getMessage() + "]");
            CumpLogManager.getInstance(this.context).log_SCE01(CumpLogEnu.APP_PRO_02, this.step2_logStartTime, new Date(), cumpEntity.getAppId(), cumpEntity.getAppName(), cumpEntity.getOfficialPackageUrl(), e.getMessage(), CumpLogEnu.urlStatus_Fail, "", cumpEntity.getOfficialVersion());
            return;
        }
        loadCumpInfoByAppId(cumpEntity, new Consumer<CumpResponse>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.11
            @Override // io.reactivex.functions.Consumer
            public void accept(CumpResponse cumpResponse) throws Exception {
                if ("0000".equals(cumpResponse.getRespCode())) {
                    lanuchlistener.onLaunchDataAsyncUpdate(cumpResponse.getCumpEntity());
                    if (cumpResponse.isRequestUpdateCache()) {
                        MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-startApp 检测到缓存文件不存在，说明版本有变化，缓存文件被删除了");
                        lanuchlistener.onLaunchVersionUpdate(cumpEntity, CumpLanucher.this.serverPublishType);
                        return;
                    }
                    return;
                }
                String str3 = CumpLanucher.TAG;
                MsLogUtil.m7979d(str3, "CumpLanucher-startApp " + cumpResponse.getRespCode() + " " + cumpResponse.getRespMsg());
                lanuchlistener.onLanuchError(cumpResponse.getRespCode(), cumpResponse.getRespMsg());
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.12
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
            }
        });
    }

    private void checkLowcodeRender(final CumpEntity cumpEntity, final lanuchListener lanuchlistener) {
        try {
            this.step2_logStartTime = new Date();
            if (new File(createLowcodeRenderDirs(), "index.html").exists()) {
                MsLogUtil.m7979d(TAG, "CumpLanucher-checkLowcodeRender 已经存在低代码渲染器环境，开始启动小程序");
                lanuchlistener.onLanuchSuccess(cumpEntity);
                try {
                    CumpLogManager.getInstance(this.context).log_SCE01(CumpLogEnu.APP_PRO_02, this.step2_logStartTime, new Date(), cumpEntity.getAppId(), cumpEntity.getAppName(), cumpEntity.getHomePageKey(), "", CumpLogEnu.urlStatus_Success, "", cumpEntity.getOfficialVersion());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.lowcodeAppUpdateNoticeFlag = 0;
                loadCumpInfoByAppId(cumpEntity, new Consumer<CumpResponse>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.13
                    @Override // io.reactivex.functions.Consumer
                    public void accept(CumpResponse cumpResponse) throws Exception {
                        if ("0000".equals(cumpResponse.getRespCode())) {
                            lanuchlistener.onLaunchDataAsyncUpdate(cumpResponse.getCumpEntity());
                            if (cumpResponse.isRequestUpdateCache()) {
                                MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkLowcodeRender 低代码小程序基础信息有改变，需要提示升级");
                                CumpLanucher.access$608(CumpLanucher.this);
                            }
                            CumpLanucher.this.noticeLowcodeAppUpdate(cumpEntity, lanuchlistener);
                            return;
                        }
                        String str = CumpLanucher.TAG;
                        MsLogUtil.m7979d(str, "CumpLanucher-checkLowcodeRender " + cumpResponse.getRespCode() + " " + cumpResponse.getRespMsg());
                        lanuchlistener.onLanuchError(cumpResponse.getRespCode(), cumpResponse.getRespMsg());
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.14
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        CumpLanucher.this.noticeLowcodeAppUpdate(cumpEntity, lanuchlistener);
                    }
                });
                return;
            }
            MsLogUtil.m7979d(TAG, "CumpLanucher-checkLowcodeRender 不存在低代码渲染器环境");
            deleteLowcodeRender();
            loadLowcodeRender(new C899715(cumpEntity, lanuchlistener), new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.16
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                    String str = CumpLanucher.TAG;
                    MsLogUtil.m7977e(str, "CumpLanucher-checkLowcodeRender 错误：" + th.getMessage());
                    CumpLanucher.this.deleteLowcodeRender();
                    lanuchlistener.onLanuchError("", "");
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            String str = TAG;
            MsLogUtil.m7977e(str, "CumpLanucher-checkLowcodeRender 错误：" + e2.getMessage());
            deleteLowcodeRender();
            lanuchlistener.onLanuchError("", "小程序启动失败,请重新进入[" + e2.getMessage() + "]");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher$15 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C899715 implements Consumer<CumpLowCodeRenderEntity> {
        final /* synthetic */ CumpEntity val$cumpEntity;
        final /* synthetic */ lanuchListener val$listener;

        C899715(CumpEntity cumpEntity, lanuchListener lanuchlistener) {
            this.val$cumpEntity = cumpEntity;
            this.val$listener = lanuchlistener;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(final CumpLowCodeRenderEntity cumpLowCodeRenderEntity) throws Exception {
            if (cumpLowCodeRenderEntity == null) {
                CumpLanucher.this.deleteLowcodeRender();
                this.val$listener.onLanuchError("", "");
                return;
            }
            Observable.create(new ObservableOnSubscribe<CumpEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.15.3
                @Override // io.reactivex.ObservableOnSubscribe
                public void subscribe(final ObservableEmitter<CumpEntity> observableEmitter) throws Exception {
                    String downloadUrl = cumpLowCodeRenderEntity.getDownloadUrl();
                    String absolutePath = CumpLanucher.this.createLowcodeRenderDirs().getAbsolutePath();
                    DownloadTask build = new DownloadTask.Builder(downloadUrl, absolutePath, "lowcoderender" + CumpLanucher.this.fileType).setMinIntervalMillisCallbackProcess(300).setPassIfAlreadyCompleted(false).setConnectionCount(1).build();
                    if (StatusUtil.isSameTaskPendingOrRunning(build)) {
                        build.cancel();
                    }
                    build.execute(new DownloadListener2() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.15.3.1
                        @Override // com.liulishuo.okdownload.DownloadListener
                        public void taskStart(@android.support.annotation.NonNull DownloadTask downloadTask) {
                            String str = CumpLanucher.TAG;
                            MsLogUtil.m7979d(str, "CumpLanucher-checkLowcodeRender 下载zip文件taskStart：" + downloadTask.getUrl());
                        }

                        @Override // com.liulishuo.okdownload.DownloadListener
                        public void taskEnd(@android.support.annotation.NonNull DownloadTask downloadTask, @android.support.annotation.NonNull EndCause endCause, @Nullable Exception exc) {
                            String str = "";
                            if (endCause != null) {
                                str = "" + endCause.toString();
                                MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkLowcodeRender 下载zip文件 cause：" + endCause.toString() + " status:" + StatusUtil.getStatus(downloadTask).name());
                            }
                            if (exc != null) {
                                str = str + exc.getMessage();
                                MsLogUtil.m7977e(CumpLanucher.TAG, "CumpLanucher-checkLowcodeRender 下载zip文件 realCause：" + exc.getMessage() + " status:" + StatusUtil.getStatus(downloadTask).name());
                            }
                            if (StatusUtil.getStatus(downloadTask) == StatusUtil.Status.COMPLETED || (endCause != null && endCause.compareTo(EndCause.COMPLETED) == 0)) {
                                MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkLowcodeRender 下载zip文件 complete：" + downloadTask.getFile().getAbsolutePath());
                                try {
                                    CumpLanucher.this.unzipFile(downloadTask.getFile().getAbsolutePath(), CumpLanucher.this.createLowcodeRenderDirs().getAbsolutePath());
                                    MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkLowcodeRender 解压低代码渲染器成功");
                                    observableEmitter.onNext(C899715.this.val$cumpEntity);
                                    observableEmitter.onComplete();
                                    return;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkLowcodeRender 解压低代码渲染器 " + e.getMessage());
                                    observableEmitter.onError(new RuntimeException("解压LowCodeRender文件失败 errorMsg:" + str + " status:" + StatusUtil.getStatus(downloadTask).name()));
                                    return;
                                }
                            }
                            MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkLowcodeRender 下载zip文件失败，删除。");
                            observableEmitter.onError(new RuntimeException("下载LowCodeRender文件失败 errorMsg:" + str + " status:" + StatusUtil.getStatus(downloadTask).name()));
                        }
                    });
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CumpEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.15.1
                @Override // io.reactivex.functions.Consumer
                public void accept(CumpEntity cumpEntity) throws Exception {
                    MsLogUtil.m7979d(CumpLanucher.TAG, "CumpLanucher-checkLowcodeRender 下载解压完成，开始启动小程序");
                    C899715.this.val$listener.onLanuchSuccess(cumpEntity);
                    try {
                        CumpLogManager.getInstance(CumpLanucher.this.context).log_SCE01(CumpLogEnu.APP_PRO_02, CumpLanucher.this.step2_logStartTime, new Date(), cumpEntity.getAppId(), cumpEntity.getAppName(), cumpEntity.getHomePageKey(), "", CumpLogEnu.urlStatus_Success, "", cumpEntity.getOfficialVersion());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.15.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str = CumpLanucher.TAG;
                    MsLogUtil.m7977e(str, "CumpLanucher-checkLowcodeRender 错误：" + th.getMessage());
                    CumpLanucher.this.deleteLowcodeRender();
                    C899715.this.val$listener.onLanuchRetry(CumpLanucher.this.serverPublishType, true);
                    try {
                        CumpLogManager.getInstance(CumpLanucher.this.context).log_SCE01(CumpLogEnu.APP_PRO_02, CumpLanucher.this.step2_logStartTime, new Date(), C899715.this.val$cumpEntity.getAppId(), C899715.this.val$cumpEntity.getAppName(), C899715.this.val$cumpEntity.getHomePageKey(), th.getMessage(), CumpLogEnu.urlStatus_Fail, "", C899715.this.val$cumpEntity.getOfficialVersion());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void loadLowcodeRender(Consumer<CumpLowCodeRenderEntity> consumer, Consumer<Throwable> consumer2) {
        CumpReqResManager.createRequest(this.context, URLSet.getEdopAppIdInfoUrl(), "queryRendererDetail", null).observeOn(Schedulers.computation()).map(new Function<CumpResponse, CumpLowCodeRenderEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher.17
            @Override // io.reactivex.functions.Function
            public CumpLowCodeRenderEntity apply(CumpResponse cumpResponse) throws Exception {
                String str = CumpLanucher.TAG;
                MsLogUtil.m7979d(str, "查询低代码渲染器配置信息 " + cumpResponse.toString());
                CumpLowCodeRenderEntity cumpLowCodeRenderEntity = (CumpLowCodeRenderEntity) CumpLanucher.this.lowcodeRenderBox.query().build().findFirst();
                if ("0000".equals(cumpResponse.getRespCode())) {
                    String string = cumpResponse.getBody().getString("version");
                    String optString = cumpResponse.getBody().optString("checksum");
                    String string2 = cumpResponse.getBody().getString("downloadUrl");
                    if (!string2.startsWith("http")) {
                        string2 = AesEncryptUtil.decrypt(string2);
                    }
                    String optString2 = cumpResponse.getBody().optString("releaseNotes");
                    if (cumpLowCodeRenderEntity == null) {
                        cumpLowCodeRenderEntity = new CumpLowCodeRenderEntity();
                        cumpLowCodeRenderEntity.setVersion(string);
                        cumpLowCodeRenderEntity.setChecksum(optString);
                        cumpLowCodeRenderEntity.setDownloadUrl(string2);
                        cumpLowCodeRenderEntity.setReleaseNotes(optString2);
                        cumpLowCodeRenderEntity.setRequestUpdate(true);
                    } else if (!cumpLowCodeRenderEntity.getVersion().equals(string)) {
                        cumpLowCodeRenderEntity.setVersion(string);
                        cumpLowCodeRenderEntity.setChecksum(optString);
                        cumpLowCodeRenderEntity.setDownloadUrl(string2);
                        cumpLowCodeRenderEntity.setReleaseNotes(optString2);
                        cumpLowCodeRenderEntity.setRequestUpdate(true);
                    } else {
                        cumpLowCodeRenderEntity.setRequestUpdate(false);
                    }
                    CumpLanucher.this.lowcodeRenderBox.put((Box) cumpLowCodeRenderEntity);
                }
                return cumpLowCodeRenderEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unzipFile(String str, String str2) throws Exception {
        new ZipFile(str).extractAll(str2);
    }

    public File createAPPDirs(String str) throws Exception {
        return ManagerWebCacheDictionary.getEdopAppRuntimeDic(true, str);
    }

    public File createLowcodeRenderDirs() throws Exception {
        return ManagerWebCacheDictionary.getEdopLowcodeRenderDic(true);
    }

    public void deleteApp(String str) {
        try {
            ManagerWebCacheDictionary.clearEdopAppRuntimeDic(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            CumpEntity appInfoFromBox = getAppInfoFromBox(str);
            if (appInfoFromBox != null) {
                this.cumpBox.remove((Box<CumpEntity>) appInfoFromBox);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteLowcodeRender() {
        try {
            this.lowcodeRenderBox.removeAll();
            ManagerWebCacheDictionary.clearEdopLowcodeRenderDic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCache() {
        try {
            this.cumpBox.removeAll();
            this.lowcodeRenderBox.removeAll();
            ManagerWebCacheDictionary.clearEdopRootDic();
            LowcodeJSStorageBox.clearAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.sinovatech.unicom.separatemodule.miniprogram.cumphome;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener2;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntityParser;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity_;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpResponse;
import com.sinovatech.unicom.separatemodule.miniprogram.dic.CumpResouceUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.net.URL;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MainTabCumpLauncher {
    public static String TAG = "MainTabCumpLauncher";
    private Context context;
    private LoadCumpConfigListener loadCumpConfigListener;
    private String transId;
    private final String FileType = JtClient.UXUE_TEMP_FILE_SUFFIX;
    private final String EdopHomeStatus_Start = "start";
    private final String EdopHomeStatus_Complete = PrefetchCumpLauncher.PrefetchStatus_Complete;
    private String publishTypeParam = "2";
    private Box<CumpEntity> cumpBox = App.getBoxStore().boxFor(CumpEntity.class);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface LoadCumpConfigListener {
        void onLoadComplete();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface PrepareLoadUrlListener {
        void loadCumpUrl(CumpEntity cumpEntity, String str);

        void loadWebUrl();
    }

    public MainTabCumpLauncher(Context context, String str) {
        this.transId = "";
        this.context = context;
        this.transId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEdopHomeStatus(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        sharePreferenceUtil.putString("EdopHomeStatus_" + str, str2);
    }

    private String getEdopHomeStatus(String str) {
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        String string = sharePreferenceUtil.getString("EdopHomeStatus_" + str);
        return TextUtils.isEmpty(string) ? "start" : string;
    }

    public void loadCumpConfig(String str) {
        loadCumpConfig(str, false, new LoadCumpConfigListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.1
            @Override // com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.LoadCumpConfigListener
            public void onLoadComplete() {
            }
        });
    }

    /* JADX WARN: Finally extract failed */
    public void loadCumpConfig(final String str, boolean z, final LoadCumpConfigListener loadCumpConfigListener) {
        try {
            String str2 = TAG;
            MsLogUtil.m7979d(str2, "加载小程序 start " + str);
            updateEdopHomeStatus(str, "start");
            this.loadCumpConfigListener = loadCumpConfigListener;
            if (!z && PrefetchCumpLauncher.PrefetchStatus_Complete.equals(PrefetchCumpLauncher.getInstance(this.context).getPrefetchStatus(str))) {
                PrefetchCumpLauncher.getInstance(this.context).updatePrefetchStatus(str, PrefetchCumpLauncher.PrefetchStatus_Complete);
                CumpEntity appInfoFromBox = CumpResouceUtils.getAppInfoFromBox(str);
                if (appInfoFromBox != null) {
                    String str3 = TAG;
                    MsLogUtil.m7979d(str3, "加载小程序 已经执行过预加载流程 直接开始检查并下载资源流程 " + str);
                    checkAndDownloadOfflineResource(appInfoFromBox);
                    return;
                }
                String str4 = TAG;
                MsLogUtil.m7979d(str4, "加载小程序 已经执行过预加载流程 小程序已降级迁退 终止此流程 " + str);
                updateEdopHomeStatus(str, PrefetchCumpLauncher.PrefetchStatus_Complete);
                loadCumpConfigListener.onLoadComplete();
                MsLogUtil.m7979d(TAG, "加载小程序 complete");
                return;
            }
            if (URLEnvironmentConfig.isForPublish()) {
                this.publishTypeParam = "2";
            } else {
                this.publishTypeParam = App.getSharePreferenceUtil().getBoolean("HomeCumpPublishType") ? "1" : "2";
            }
            String replace = URLSet.getEdopHomeConfigUrl().replace("{publishType}", this.publishTypeParam).replace("{appVersion}", this.context.getString(2131886969)).replace("{appId}", str);
            String str5 = TAG;
            MsLogUtil.m7979d(str5, "加载小程序 拼接接口：" + replace);
            App.getAsyncHttpClient(5, 5, 5, 5).rxGet(replace, null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).flatMap(new Function<String, ObservableSource<CumpEntity>>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.4
                @Override // io.reactivex.functions.Function
                public ObservableSource<CumpEntity> apply(String str6) throws Exception {
                    try {
                        String str7 = MainTabCumpLauncher.TAG;
                        MsLogUtil.m7979d(str7, "加载小程序 接口返回：" + str6);
                        JSONObject jSONObject = new JSONObject(str6);
                        JSONObject jSONObject2 = jSONObject.getJSONObject("response").getJSONObject("head");
                        String optString = jSONObject2.optString("respCode", "");
                        String optString2 = jSONObject2.optString("respMsg", "");
                        JSONObject jSONObject3 = jSONObject.getJSONObject("response").getJSONObject("body");
                        if ("0000".equals(optString)) {
                            CumpResponse parse = CumpEntityParser.parse(MainTabCumpLauncher.TAG, new CumpResponse(), jSONObject3, MainTabCumpLauncher.this.cumpBox, MainTabCumpLauncher.this.publishTypeParam);
                            if (parse.getCumpEntity() != null) {
                                return Observable.just(parse.getCumpEntity());
                            }
                            MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "加载小程序 数据缺失");
                            return Observable.error(new HomeCumpLanucherException("数据缺失"));
                        }
                        String str8 = MainTabCumpLauncher.TAG;
                        MsLogUtil.m7979d(str8, "加载小程序 降级迁退 " + optString + " " + optString2);
                        MainTabCumpLauncher mainTabCumpLauncher = MainTabCumpLauncher.this;
                        return Observable.error(new HomeCumpLanucherException(optString + " " + optString2));
                    } catch (Exception e) {
                        String str9 = MainTabCumpLauncher.TAG;
                        MsLogUtil.m7979d(str9, "加载小程序 解析flatmap 运行错误：" + e.toString());
                        return Observable.error(new HomeCumpLanucherException(e.toString()));
                    }
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CumpEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.2
                @Override // io.reactivex.functions.Consumer
                public void accept(CumpEntity cumpEntity) throws Exception {
                    MainTabCumpLauncher.this.checkAndDownloadOfflineResource(cumpEntity);
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.3
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str6 = MainTabCumpLauncher.TAG;
                    MsLogUtil.m7979d(str6, "加载小程序 consumer onError " + th.toString());
                    boolean z2 = th instanceof HomeCumpLanucherException;
                    if (z2) {
                        MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "加载小程序 consumer onError 降级处理");
                        CumpResouceUtils.deleteApp(str);
                    }
                    MainTabCumpLauncher.this.updateEdopHomeStatus(str, PrefetchCumpLauncher.PrefetchStatus_Complete);
                    MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "加载小程序 complete");
                    loadCumpConfigListener.onLoadComplete();
                    if (z2) {
                        MainTabCumpLogManager.getInstance(MainTabCumpLauncher.this.context).log_SCE01_Sub02(MainTabCumpLauncher.this.transId, str, th.toString());
                    } else {
                        MainTabCumpLogManager.getInstance(MainTabCumpLauncher.this.context).log_SCE01_Sub01(MainTabCumpLauncher.this.transId, str, th.toString());
                    }
                }
            });
        } catch (Exception e) {
            try {
                try {
                    String str6 = TAG;
                    MsLogUtil.m7979d(str6, "加载小程序 最外层catch 运行错误：" + e.toString());
                    CumpResouceUtils.deleteApp(str);
                    updateEdopHomeStatus(str, PrefetchCumpLauncher.PrefetchStatus_Complete);
                    MsLogUtil.m7979d(TAG, "加载小程序 complete");
                    MainTabCumpLogManager.getInstance(this.context).log_SCE01_Sub02(this.transId, str, e.toString());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                loadCumpConfigListener.onLoadComplete();
            } catch (Throwable th) {
                loadCumpConfigListener.onLoadComplete();
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndDownloadOfflineResource(final CumpEntity cumpEntity) {
        MsLogUtil.m7979d(TAG, "加载小程序 开始检查资源和下载资源流程");
        Observable.create(new ObservableOnSubscribe<CumpEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.7
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(final ObservableEmitter<CumpEntity> observableEmitter) throws Exception {
                if (!MainTabCumpLauncher.this.isExitFile(cumpEntity.getAppId())) {
                    String officialPackageUrl = cumpEntity.getOfficialPackageUrl();
                    String absolutePath = MainTabCumpLauncher.this.createAPPDirs(cumpEntity.getAppId()).getAbsolutePath();
                    DownloadTask build = new DownloadTask.Builder(officialPackageUrl, absolutePath, cumpEntity.getAppId() + JtClient.UXUE_TEMP_FILE_SUFFIX).setMinIntervalMillisCallbackProcess(300).setPassIfAlreadyCompleted(false).setConnectionCount(1).build();
                    if (StatusUtil.isSameTaskPendingOrRunning(build)) {
                        build.cancel();
                    }
                    build.execute(new DownloadListener2() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.7.1
                        @Override // com.liulishuo.okdownload.DownloadListener
                        public void taskStart(@NonNull DownloadTask downloadTask) {
                            String str = MainTabCumpLauncher.TAG;
                            MsLogUtil.m7979d(str, "加载小程序 开始下载" + downloadTask.getUrl());
                        }

                        @Override // com.liulishuo.okdownload.DownloadListener
                        public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc) {
                            String str = "";
                            if (endCause != null) {
                                try {
                                    str = "" + endCause.toString();
                                    MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "加载小程序 下载完成 cause：" + endCause.toString() + " status:" + StatusUtil.getStatus(downloadTask).name());
                                } catch (Exception e) {
                                    observableEmitter.onError(new RuntimeException("下载文件taskEnd运行时错误" + e.toString()));
                                    return;
                                }
                            }
                            if (exc != null) {
                                str = str + exc.getMessage();
                                MsLogUtil.m7977e(MainTabCumpLauncher.TAG, "加载小程序 下载完成 realCause：" + exc.getMessage() + " status:" + StatusUtil.getStatus(downloadTask).name());
                            }
                            if (StatusUtil.getStatus(downloadTask) != StatusUtil.Status.COMPLETED && (endCause == null || endCause.compareTo(EndCause.COMPLETED) != 0)) {
                                observableEmitter.onError(new RuntimeException("下载文件失败 errorMsg:" + str + " status:" + StatusUtil.getStatus(downloadTask).name()));
                                return;
                            }
                            MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "加载小程序 下载成功：" + downloadTask.getFile().getAbsolutePath());
                            MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "加载小程序 准备解压zip文件");
                            CumpResouceUtils.unzipFile(downloadTask.getFile().getAbsolutePath(), MainTabCumpLauncher.this.createAPPDirs(cumpEntity.getAppId()).getAbsolutePath());
                            if (MainTabCumpLauncher.this.isExitFile(cumpEntity.getAppId())) {
                                observableEmitter.onNext(cumpEntity);
                                observableEmitter.onComplete();
                                MainTabCumpLogManager.getInstance(MainTabCumpLauncher.this.context).log_SCE01_Sub05(MainTabCumpLauncher.this.transId, cumpEntity.getAppId());
                                return;
                            }
                            observableEmitter.onError(new RuntimeException("解压完成后检查小程序资源文件缺失"));
                        }
                    });
                    MainTabCumpLogManager mainTabCumpLogManager = MainTabCumpLogManager.getInstance(MainTabCumpLauncher.this.context);
                    String str = MainTabCumpLauncher.this.transId;
                    String appId = cumpEntity.getAppId();
                    mainTabCumpLogManager.log_SCE01_Sub03(str, appId, "旧版本号" + cumpEntity.getOldVersion() + "+新版本号" + cumpEntity.getOfficialVersion() + "+更新时间" + cumpEntity.getTimestamp() + "+发布类型" + cumpEntity.getServerPublishType());
                    return;
                }
                MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "加载小程序 资源已经存在 不需要重新下载");
                observableEmitter.onNext(cumpEntity);
                observableEmitter.onComplete();
                MainTabCumpLogManager mainTabCumpLogManager2 = MainTabCumpLogManager.getInstance(MainTabCumpLauncher.this.context);
                String str2 = MainTabCumpLauncher.this.transId;
                String appId2 = cumpEntity.getAppId();
                mainTabCumpLogManager2.log_SCE01_Sub04(str2, appId2, "旧版本号" + cumpEntity.getOldVersion() + "+新版本号" + cumpEntity.getOfficialVersion() + "+更新时间" + cumpEntity.getTimestamp() + "+发布类型" + cumpEntity.getServerPublishType());
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CumpEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.5
            @Override // io.reactivex.functions.Consumer
            public void accept(CumpEntity cumpEntity2) throws Exception {
                MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "加载小程序 checkAndDownloadOfflineResource onNext");
                MainTabCumpLauncher.this.updateEdopHomeStatus(cumpEntity2.getAppId(), PrefetchCumpLauncher.PrefetchStatus_Complete);
                MainTabCumpLauncher.this.loadCumpConfigListener.onLoadComplete();
                MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "加载小程序 complete");
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.6
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                try {
                    try {
                        String str = MainTabCumpLauncher.TAG;
                        MsLogUtil.m7979d(str, "加载小程序 checkAndDownloadOfflineResource onError " + th.toString());
                        CumpResouceUtils.deleteApp(cumpEntity.getAppId());
                        MainTabCumpLauncher.this.updateEdopHomeStatus(cumpEntity.getAppId(), PrefetchCumpLauncher.PrefetchStatus_Complete);
                        MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "加载小程序 complete");
                        MainTabCumpLogManager.getInstance(MainTabCumpLauncher.this.context).log_SCE01_Sub06(MainTabCumpLauncher.this.transId, cumpEntity.getAppId(), th.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } finally {
                    MainTabCumpLauncher.this.loadCumpConfigListener.onLoadComplete();
                }
            }
        });
    }

    private boolean isRightDomain(String str) {
        try {
            String host = new URL(str).getHost();
            if (host.endsWith("10010.com")) {
                return true;
            }
            return host.endsWith("10010.cn");
        } catch (Exception e) {
            String str2 = TAG;
            MsLogUtil.m7979d(str2, "HomeCumpLanucher_首页加载小程序 isRightDomain报错：" + e.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isExitFile(String str) {
        return CumpResouceUtils.isExitFile(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File createAPPDirs(String str) throws Exception {
        return CumpResouceUtils.createAPPDirs(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class HomeCumpLanucherException extends RuntimeException {
        public HomeCumpLanucherException(String str) {
            super(str);
        }
    }

    public void prepareLoadUrl(final Context context, boolean z, int i, boolean z2, String str, final PrepareLoadUrlListener prepareLoadUrlListener) {
        final String str2;
        try {
            String str3 = TAG;
            MsLogUtil.m7979d(str3, "prepareLoadUrl tabPosiiton：" + i + " urlPriorityConfig：" + z2 + " cumpUrl：" + str);
            if (i == 0 && !z2) {
                String str4 = TAG;
                MsLogUtil.m7979d(str4, "首页小程序开关 " + z2);
                prepareLoadUrlListener.loadWebUrl();
                return;
            }
            if (!TextUtils.isEmpty(str) && (str.startsWith("https://edop_unicom") || str.startsWith("http://edop_unicom"))) {
                String replace = str.replace("http://edop_unicom/?", "http://edop_unicom?").replace("https://edop_unicom/?", "https://edop_unicom?");
                if (!replace.startsWith("https://edop_unicom?") && !replace.startsWith("http://edop_unicom?")) {
                    prepareLoadUrlListener.loadWebUrl();
                    return;
                }
                if (replace.contains("&path=")) {
                    String str5 = replace.split("&path=")[1];
                    replace = replace.split("&path=")[0];
                    str2 = str5;
                } else {
                    str2 = "";
                }
                final String queryParameter = Uri.parse(replace).getQueryParameter("appid");
                if (i != 0 && i != 99) {
                    MsLogUtil.m7979d(TAG, "prepareLoadUrl 执行非非非首页推荐的逻辑");
                    loadCumpConfig(queryParameter, z, new LoadCumpConfigListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.8
                        @Override // com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher.LoadCumpConfigListener
                        public void onLoadComplete() {
                            MsLogUtil.m7979d(MainTabCumpLauncher.TAG, "prepareLoadUrl onLoadComplete");
                            MainTabCumpLauncher.this.prepareLoadCumpUrl(context, queryParameter, str2, prepareLoadUrlListener);
                        }
                    });
                    return;
                }
                MsLogUtil.m7979d(TAG, "prepareLoadUrl 执行首页推荐的逻辑");
                prepareLoadCumpUrl(context, queryParameter, str2, prepareLoadUrlListener);
                return;
            }
            prepareLoadUrlListener.loadWebUrl();
        } catch (Exception e) {
            e.printStackTrace();
            prepareLoadUrlListener.loadWebUrl();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareLoadCumpUrl(Context context, String str, String str2, PrepareLoadUrlListener prepareLoadUrlListener) {
        String str3;
        try {
            CumpEntity preparedCumpEntity = getPreparedCumpEntity(str);
            if (preparedCumpEntity == null) {
                prepareLoadUrlListener.loadWebUrl();
                return;
            }
            if (TextUtils.isEmpty(str2)) {
                str3 = createAPPDirs(preparedCumpEntity.getAppId()) + File.separator + "index.html?time=" + System.currentTimeMillis();
            } else {
                str3 = createAPPDirs(preparedCumpEntity.getAppId()) + File.separator + str2;
            }
            prepareLoadUrlListener.loadCumpUrl(preparedCumpEntity, "file:///" + str3);
        } catch (Exception e) {
            e.printStackTrace();
            prepareLoadUrlListener.loadWebUrl();
        }
    }

    public CumpEntity getPreparedCumpEntity(String str) {
        try {
            CumpEntity findFirst = this.cumpBox.query().equal(CumpEntity_.appId, str).build().findFirst();
            if (findFirst == null) {
                String str2 = TAG;
                MsLogUtil.m7979d(str2, "小程序状态检查 appId=" + str + " 状态=" + getEdopHomeStatus(str) + " 小程序数据库信息不存在");
            } else {
                String str3 = TAG;
                MsLogUtil.m7979d(str3, "小程序状态检查 appId=" + str + " 状态=" + getEdopHomeStatus(str) + " isExitFile=" + isExitFile(findFirst.getAppId()));
            }
            if (findFirst == null || !PrefetchCumpLauncher.PrefetchStatus_Complete.equals(getEdopHomeStatus(str))) {
                return null;
            }
            if (isExitFile(findFirst.getAppId())) {
                return findFirst;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.sinovatech.unicom.separatemodule.appdownload;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.dueeeke.videoplayer.util.NetworkUtil;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.tms.TMSUtil;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AppDownloadManager implements View.OnClickListener {
    private static final String TAG = "AppDownloadManager";
    private AppCompatActivity activityContext;
    private String apkUrl;
    private String appName;
    private RelativeLayout download_PB;
    private String fileName;
    private TextView jinduTV;
    private StatusUtil.Status mCompleted;
    private RelativeLayout mDownload;
    private TextView mDownload_text;
    private ProgressBar mProgress;
    private File parentFile;
    private String pkgName;
    private String pvType;
    private DownloadTask mTask = null;
    private boolean downloadError = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum UPTYPE {
        LIJIQIANWANG,
        RELOAD,
        CONTUNI,
        LIJIANZHUANG,
        LIJIDAKAI
    }

    public AppDownloadManager(AppCompatActivity appCompatActivity, RelativeLayout relativeLayout, String str, String str2, String str3, String str4) {
        this.mCompleted = null;
        this.apkUrl = "https://js.a.kspkg.com/bs2/fes/kwai-android-LTST-gifmakerrelease-7.5.20.14363_x32_047531.apk";
        this.fileName = "kwai.apk";
        this.pkgName = "com.smile.gifmaker";
        this.appName = "美团";
        this.pvType = "";
        this.activityContext = appCompatActivity;
        this.mDownload = (RelativeLayout) relativeLayout.findViewById(2131296895);
        this.mDownload.setOnClickListener(this);
        this.mDownload.setVisibility(0);
        this.download_PB = (RelativeLayout) relativeLayout.findViewById(2131296896);
        this.download_PB.setOnClickListener(this);
        this.download_PB.setVisibility(8);
        this.mDownload_text = (TextView) relativeLayout.findViewById(2131296905);
        this.mProgress = (ProgressBar) relativeLayout.findViewById(2131298231);
        this.jinduTV = (TextView) relativeLayout.findViewById(2131297536);
        this.mDownload_text.setText("立即前往");
        this.mCompleted = null;
        long time = new Date().getTime();
        this.fileName = time + ".apk";
        this.apkUrl = str;
        this.pkgName = str2;
        this.appName = str3;
        if ("kaiping".equals(str4)) {
            this.pvType = "开屏广告";
        } else if ("banner".equals(str4)) {
            this.pvType = "首页banner广告";
        } else {
            this.pvType = "首页弹窗";
        }
        clearDownFile();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        switch (view.getId()) {
            case 2131296895:
                upPV(UPTYPE.LIJIQIANWANG);
                getDownload();
                break;
            case 2131296896:
                DownloadTask downloadTask = this.mTask;
                if (downloadTask != null) {
                    if (this.downloadError) {
                        clearDownFile();
                        getDownload();
                        upPV(UPTYPE.RELOAD);
                    } else if (StatusUtil.isSameTaskPendingOrRunning(downloadTask)) {
                        this.mTask.cancel();
                    } else {
                        startDownload(this.mTask);
                        upPV(UPTYPE.CONTUNI);
                    }
                    this.downloadError = false;
                    break;
                }
                break;
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    public void getDownload() {
        String str;
        String str2;
        String str3;
        if (!TMSUtil.isPkgInstalled(App.getInstance().getApplicationContext(), this.pkgName)) {
            this.parentFile = App.getInstance().getExternalCacheDir();
            this.mTask = new DownloadTask.Builder(this.apkUrl, this.parentFile.getAbsolutePath(), this.fileName).setMinIntervalMillisCallbackProcess(100).setPassIfAlreadyCompleted(false).setConnectionCount(1).build();
            if (this.mCompleted == StatusUtil.Status.COMPLETED) {
                AppCompatActivity appCompatActivity = this.activityContext;
                TMSUtil.installApkByPath(appCompatActivity, this.parentFile.getAbsolutePath() + File.separator + this.fileName);
                upPV(UPTYPE.LIJIANZHUANG);
                return;
            }
            String nETType = DeviceHelper.getNETType(this.activityContext);
            int networkType = NetworkUtil.getNetworkType(this.activityContext);
            if (networkType == 0 || networkType == 1) {
                CustomDialogManager.show((Activity) this.activityContext, "温馨提示", "当前无法连接到互联网，请检查您的网络环境", true, "取消", "去设置", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.appdownload.AppDownloadManager.1
                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                    public void onClickOk() {
                        try {
                            AppDownloadManager.this.activityContext.startActivity(new Intent("android.settings.SETTINGS"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            if ("Wifi".equals(nETType)) {
                str = "温馨提示";
                str2 = "是否确定下载?";
                str3 = "确定";
            } else {
                str = "下载提醒";
                str2 = "当前非WIFI环境,是否使用流量继续下载?";
                str3 = "继续下载";
            }
            CustomDialogManager.show((Activity) this.activityContext, str, str2, true, "取消", str3, true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.appdownload.AppDownloadManager.2
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    AppDownloadManager appDownloadManager = AppDownloadManager.this;
                    appDownloadManager.startDownload(appDownloadManager.mTask);
                }
            });
            return;
        }
        this.activityContext.startActivity(this.activityContext.getPackageManager().getLaunchIntentForPackage(this.pkgName));
        upPV(UPTYPE.LIJIDAKAI);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDownload(final DownloadTask downloadTask) {
        new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.appdownload.AppDownloadManager.3
            @Override // java.lang.Runnable
            public void run() {
                downloadTask.execute(new MyDownListener());
            }
        }).start();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class MyDownListener extends DownloadListener4WithSpeed {
        private String readableTotalLength;
        private long totalLength;

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void blockEnd(@NonNull DownloadTask downloadTask, int i, BlockInfo blockInfo, @NonNull SpeedCalculator speedCalculator) {
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectEnd(@NonNull DownloadTask downloadTask, int i, int i2, @NonNull Map<String, List<String>> map) {
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectStart(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progressBlock(@NonNull DownloadTask downloadTask, int i, long j, @NonNull SpeedCalculator speedCalculator) {
        }

        MyDownListener() {
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskStart(@NonNull DownloadTask downloadTask) {
            UIUtils.logD("okdownload", "okdownload-taskStart " + downloadTask.getFilename() + " " + StatusUtil.getStatus(downloadTask).name());
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void infoReady(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, boolean z, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel listener4SpeedModel) {
            this.totalLength = breakpointInfo.getTotalLength();
            this.readableTotalLength = Util.humanReadableBytes(this.totalLength, true);
            UIUtils.logD("okdownload", "okdownload-infoReady " + downloadTask.getFilename() + " " + StatusUtil.getStatus(downloadTask).name());
            AppDownloadManager.this.mProgress.setMax((int) this.totalLength);
            AppDownloadManager.this.mProgress.setProgress((int) breakpointInfo.getTotalOffset());
            AppDownloadManager.this.jinduTV.setText("0%");
            AppDownloadManager.this.download_PB.setVisibility(0);
            AppDownloadManager.this.mDownload.setVisibility(8);
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progress(@NonNull DownloadTask downloadTask, long j, @NonNull SpeedCalculator speedCalculator) {
            String str = Util.humanReadableBytes(j, true) + "/" + this.readableTotalLength;
            UIUtils.logD("okdownload", "okdownload-progress " + downloadTask.getFilename() + " " + (str + "(" + speedCalculator.speed() + ")"));
            TextView textView = AppDownloadManager.this.mDownload_text;
            StringBuilder sb = new StringBuilder();
            sb.append("下载进度:");
            sb.append(str);
            textView.setText(sb.toString());
            AppDownloadManager.this.mProgress.setMax((int) this.totalLength);
            AppDownloadManager.this.mProgress.setProgress((int) j);
            AppDownloadManager.this.jinduTV.setText(((int) ((((float) j) / ((float) this.totalLength)) * 100.0f)) + "%");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, @NonNull SpeedCalculator speedCalculator) {
            UIUtils.logD("okdownload", "okdownload-taskEnd " + downloadTask.getFilename() + " " + endCause.name() + " " + StatusUtil.getStatus(downloadTask).name());
            AppDownloadManager.this.mCompleted = StatusUtil.getStatus(downloadTask);
            if (StatusUtil.getStatus(downloadTask) == StatusUtil.Status.COMPLETED) {
                AppDownloadManager.this.mDownload_text.setText("立即安装");
                AppDownloadManager.this.mDownload.setVisibility(0);
                AppDownloadManager.this.download_PB.setVisibility(8);
            } else if (StatusUtil.getStatus(downloadTask) == StatusUtil.Status.IDLE) {
                AppDownloadManager.this.jinduTV.setText("继续下载");
                AppDownloadManager.this.download_PB.setVisibility(0);
                AppDownloadManager.this.mDownload.setVisibility(8);
            } else {
                AppDownloadManager.this.jinduTV.setText("重新下载");
                AppDownloadManager.this.download_PB.setVisibility(0);
                AppDownloadManager.this.mDownload.setVisibility(8);
                AppDownloadManager.this.downloadError = true;
            }
        }
    }

    private static void clearDownFile() {
        File[] listFiles;
        try {
            for (File file : App.getInstance().getExternalCacheDir().listFiles()) {
                if (file.getName().endsWith(".apk")) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void upPV(UPTYPE uptype) {
        switch (uptype) {
            case LIJIQIANWANG:
                StatisticsUploadUtils.upload(this.activityContext, "hzyl0001", this.pvType, "立即前往", "", this.appName, this.apkUrl);
                return;
            case LIJIANZHUANG:
                StatisticsUploadUtils.upload(this.activityContext, "hzyl0001", this.pvType, "立即安装", "", this.appName, this.apkUrl);
                return;
            case RELOAD:
                StatisticsUploadUtils.upload(this.activityContext, "hzyl0001", this.pvType, "重新下载", "", this.appName, this.apkUrl);
                return;
            case CONTUNI:
                StatisticsUploadUtils.upload(this.activityContext, "hzyl0001", this.pvType, "继续下载", "", this.appName, this.apkUrl);
                return;
            case LIJIDAKAI:
                StatisticsUploadUtils.upload(this.activityContext, "hzyl0001", this.pvType, "立即打开", "", this.appName, this.apkUrl);
                return;
            default:
                return;
        }
    }
}

package com.sinovatech.unicom.separatemodule.gamedownload;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tms.TMSUtil;
import com.sinovatech.unicom.separatemodule.tms.mianliu.FreeUrlAdapterUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameSingleDownloadActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private Activity activityContext;
    private ImageButton backButton;
    private DonutProgress downPB;
    private DownloadTask downloadTask;
    private String fileName;
    private GameEntity gameEntity;
    private ImageView iconImage;
    private boolean isMianliu = false;
    private TextView mainTitle;
    private File parentFile;
    private RelativeLayout progressLayout;
    private ImageView resumeImage;
    private TextView startDownButton;
    private TextView subTitle;
    private TextView titleView;
    private ProgressBar waitPB;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 82);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC87791 implements View.OnClickListener {
        View$OnClickListenerC87791() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            GameSingleDownloadActivity.this.closeTask();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC87812 implements View.OnClickListener {
        View$OnClickListenerC87812() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            try {
                if (!TMSUtil.isPkgInstalled(App.getInstance().getApplicationContext(), GameSingleDownloadActivity.this.gameEntity.getPackageName())) {
                    if (StatusUtil.isCompleted(GameSingleDownloadActivity.this.downloadTask)) {
                        GameSingleDownloadActivity.this.listenerInstall();
                        Context applicationContext = App.getInstance().getApplicationContext();
                        TMSUtil.installApkByPath(applicationContext, GameSingleDownloadActivity.this.downloadTask.getParentFile().getAbsolutePath() + File.separator + GameSingleDownloadActivity.this.fileName);
                    } else {
                        GameSingleDownloadActivity.this.startDownload();
                    }
                } else {
                    GameSingleDownloadActivity.this.startAdApp();
                }
            } catch (Exception e) {
                UIUtils.toast("程序异常【" + e.getMessage() + "】");
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC87823 implements View.OnClickListener {
        View$OnClickListenerC87823() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (StatusUtil.isSameTaskPendingOrRunning(GameSingleDownloadActivity.this.downloadTask)) {
                GameSingleDownloadActivity.this.progressLayout.setVisibility(0);
                GameSingleDownloadActivity.this.startDownButton.setVisibility(8);
                GameSingleDownloadActivity.this.resumeImage.setImageResource(2131231267);
                GameSingleDownloadActivity.this.downloadTask.cancel();
            } else {
                GameSingleDownloadActivity.this.progressLayout.setVisibility(0);
                GameSingleDownloadActivity.this.startDownButton.setVisibility(8);
                GameSingleDownloadActivity.this.resumeImage.setImageResource(2131231266);
                new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GameSingleDownloadActivity.this.downloadTask.execute(new MyDownListener());
                    }
                }).start();
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    private void clearDownFile() {
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

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        initDownloadTaskStatus(false);
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            return closeTask();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void startDownload() {
        if (SystemServiceUtils.netIsAvailable()) {
            if (DeviceHelper.getNETType(this.activityContext).equalsIgnoreCase("Wifi")) {
                executeDownload();
                return;
            } else if (TMSUtil.checkIsUnicomSimCard(this.activityContext) && this.isMianliu) {
                executeDownload();
                return;
            } else {
                Observable.create(new ObservableOnSubscribe<Integer>() { // from class: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity.5
                    @Override // io.reactivex.ObservableOnSubscribe
                    public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                        observableEmitter.onNext(Integer.valueOf(((HttpURLConnection) NBSInstrumentation.openConnection(new URL(GameSingleDownloadActivity.this.downloadTask.getUrl()).openConnection())).getContentLength()));
                        observableEmitter.onComplete();
                    }
                }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() { // from class: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity.4
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                        GameSingleDownloadActivity.this.progressLayout.setVisibility(8);
                        GameSingleDownloadActivity.this.startDownButton.setVisibility(8);
                        GameSingleDownloadActivity.this.waitPB.setVisibility(0);
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(Integer num) {
                        GameSingleDownloadActivity.this.progressLayout.setVisibility(8);
                        GameSingleDownloadActivity.this.startDownButton.setVisibility(0);
                        GameSingleDownloadActivity.this.waitPB.setVisibility(8);
                        BigDecimal scale = new BigDecimal((num.intValue() / 1024.0f) / 1024.0f).setScale(2, 4);
                        Activity activity = GameSingleDownloadActivity.this.activityContext;
                        CustomDialogManager.show(activity, "温馨提示", "立即下载将会消耗您" + scale + "M流量，是否继续下载？", 17, true, "稍后下载", "立即下载", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity.4.1
                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                            public void onClickOk() {
                                GameSingleDownloadActivity.this.executeDownload();
                            }
                        });
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable th) {
                        GameSingleDownloadActivity.this.progressLayout.setVisibility(8);
                        GameSingleDownloadActivity.this.startDownButton.setVisibility(0);
                        GameSingleDownloadActivity.this.waitPB.setVisibility(8);
                        CustomDialogManager.show(GameSingleDownloadActivity.this.activityContext, "流量提醒", "使用移动网络会消耗您的流量，是否继续下载？", 17, true, "取消", "下载", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity.4.2
                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                            public void onClickOk() {
                                GameSingleDownloadActivity.this.executeDownload();
                            }
                        });
                    }
                });
                return;
            }
        }
        CustomDialogManager.show(this.activityContext, "", "网络连接失败，请检查网络设置！");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeDownload() {
        this.progressLayout.setVisibility(0);
        this.startDownButton.setVisibility(8);
        this.resumeImage.setImageResource(2131231266);
        this.downPB.setProgress(0.0f);
        new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity.6
            @Override // java.lang.Runnable
            public void run() {
                GameSingleDownloadActivity.this.downloadTask.execute(new MyDownListener());
            }
        }).start();
    }

    private void handleMianliu() {
        Observable.create(new ObservableOnSubscribe<String>() { // from class: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity.8
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                try {
                    String downloadUrl = GameSingleDownloadActivity.this.gameEntity.getDownloadUrl();
                    FreeUrlAdapterUtil freeUrlAdapterUtil = new FreeUrlAdapterUtil(GameSingleDownloadActivity.this.activityContext);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(downloadUrl);
                    List<String> freeUrl = freeUrlAdapterUtil.getFreeUrl(arrayList);
                    if (freeUrl.size() == 1 && !TextUtils.isEmpty(freeUrl.get(0))) {
                        downloadUrl = freeUrl.get(0);
                        GameSingleDownloadActivity.this.isMianliu = true;
                    }
                    MsLogUtil.m7979d("GameDownload", "构造下载任务时，应用的下载地址：" + downloadUrl);
                    GameSingleDownloadActivity gameSingleDownloadActivity = GameSingleDownloadActivity.this;
                    gameSingleDownloadActivity.fileName = TMSUtil.getMD5(downloadUrl) + ".apk";
                    GameSingleDownloadActivity.this.downloadTask = new DownloadTask.Builder(downloadUrl, GameSingleDownloadActivity.this.parentFile.getAbsolutePath(), GameSingleDownloadActivity.this.fileName).setMinIntervalMillisCallbackProcess(100).setPassIfAlreadyCompleted(false).setConnectionCount(1).build();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (GameSingleDownloadActivity.this.downloadTask == null) {
                    observableEmitter.onError(new RuntimeException("构造下载任务错误"));
                }
                observableEmitter.onNext("success");
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity.7
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                GameSingleDownloadActivity.this.waitPB.setVisibility(0);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                GameSingleDownloadActivity.this.waitPB.setVisibility(8);
                GameSingleDownloadActivity.this.initDownloadTaskStatus(true);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                GameSingleDownloadActivity.this.waitPB.setVisibility(8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDownloadTaskStatus(boolean z) {
        try {
            if (this.downloadTask != null) {
                StatusUtil.getStatus(this.downloadTask);
                if (!TMSUtil.isPkgInstalled(App.getInstance().getApplicationContext(), this.gameEntity.getPackageName())) {
                    if (StatusUtil.isCompleted(this.downloadTask)) {
                        MsLogUtil.m7979d("GameDownload", this.gameEntity.getMainTitle() + " 已经下载完了");
                        this.progressLayout.setVisibility(8);
                        this.startDownButton.setVisibility(0);
                        this.startDownButton.setText("安装");
                        return;
                    }
                    MsLogUtil.m7979d("GameDownload", this.gameEntity.getMainTitle() + " 没有下载完 " + StatusUtil.getStatus(this.downloadTask).name() + " " + StatusUtil.isSameTaskPendingOrRunning(this.downloadTask));
                    BreakpointInfo breakpointInfo = OkDownload.with().breakpointStore().get(this.downloadTask.getId());
                    if (breakpointInfo != null) {
                        MsLogUtil.m7979d("GameDownload", this.gameEntity.getMainTitle() + " 有断点信息：" + breakpointInfo.getTotalLength() + " " + breakpointInfo.getTotalOffset());
                        this.progressLayout.setVisibility(0);
                        this.startDownButton.setVisibility(8);
                        if (StatusUtil.isSameTaskPendingOrRunning(this.downloadTask)) {
                            this.resumeImage.setImageResource(2131231266);
                        } else {
                            this.resumeImage.setImageResource(2131231267);
                        }
                        this.downPB.setMax((int) breakpointInfo.getTotalLength());
                        this.downPB.setProgress((float) breakpointInfo.getTotalOffset());
                    } else {
                        MsLogUtil.m7979d("GameDownload", this.gameEntity.getMainTitle() + " 没有断点信息");
                        this.progressLayout.setVisibility(8);
                        this.startDownButton.setVisibility(0);
                        this.startDownButton.setText("下载");
                    }
                    if (z) {
                        this.startDownButton.performClick();
                        return;
                    }
                    return;
                }
                MsLogUtil.m7979d("GameDownload", this.gameEntity.getMainTitle() + " 已经安装了");
                this.progressLayout.setVisibility(8);
                this.startDownButton.setVisibility(0);
                this.startDownButton.setText("打开");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void listenerInstall() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        this.activityContext.registerReceiver(new BroadcastReceiver() { // from class: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity.9
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
            }
        }, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAdApp() {
        try {
            this.activityContext.startActivity(this.activityContext.getPackageManager().getLaunchIntentForPackage(this.gameEntity.getPackageName()));
        } catch (Throwable th) {
            th.printStackTrace();
            UIUtils.logE("TMSSDK", "腾讯广告-打开APP并提交任务错误：" + th.getMessage());
        }
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
            MsLogUtil.m7979d("GameDownload", "okdownload-taskStart " + downloadTask.getFilename() + " " + StatusUtil.getStatus(downloadTask).name());
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void infoReady(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, boolean z, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel listener4SpeedModel) {
            this.totalLength = breakpointInfo.getTotalLength();
            this.readableTotalLength = Util.humanReadableBytes(this.totalLength, true);
            MsLogUtil.m7979d("GameDownload", "okdownload-infoReady " + downloadTask.getFilename() + " " + StatusUtil.getStatus(downloadTask).name());
            GameSingleDownloadActivity.this.downPB.setMax((int) this.totalLength);
            GameSingleDownloadActivity.this.downPB.setProgress((float) breakpointInfo.getTotalOffset());
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progress(@NonNull DownloadTask downloadTask, long j, @NonNull SpeedCalculator speedCalculator) {
            String str = (Util.humanReadableBytes(j, true) + "/" + this.readableTotalLength) + "(" + speedCalculator.speed() + ")";
            GameSingleDownloadActivity.this.downPB.setMax((int) this.totalLength);
            GameSingleDownloadActivity.this.downPB.setProgress((float) j);
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, @NonNull SpeedCalculator speedCalculator) {
            MsLogUtil.m7979d("GameDownload", "okdownload-taskEnd " + downloadTask.getFilename() + " " + endCause.name() + " " + StatusUtil.getStatus(downloadTask).name());
            if (StatusUtil.getStatus(downloadTask) == StatusUtil.Status.COMPLETED) {
                GameSingleDownloadActivity.this.progressLayout.setVisibility(8);
                GameSingleDownloadActivity.this.startDownButton.setVisibility(0);
                GameSingleDownloadActivity.this.startDownButton.setText("安装");
            } else if (StatusUtil.getStatus(downloadTask) == StatusUtil.Status.IDLE) {
                GameSingleDownloadActivity.this.progressLayout.setVisibility(0);
                GameSingleDownloadActivity.this.startDownButton.setVisibility(8);
                GameSingleDownloadActivity.this.resumeImage.setImageResource(2131231267);
            } else {
                GameSingleDownloadActivity.this.progressLayout.setVisibility(0);
                GameSingleDownloadActivity.this.startDownButton.setVisibility(8);
                GameSingleDownloadActivity.this.resumeImage.setImageResource(2131231267);
            }
        }
    }

    public boolean isRunning() {
        DownloadTask downloadTask = this.downloadTask;
        return downloadTask != null && StatusUtil.isSameTaskPendingOrRunning(downloadTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean closeTask() {
        if (isRunning()) {
            CustomDialogManager.show(this.activityContext, "温馨提示", "应用下载中，退出本页面将无法继续下载，是否退出？", 17, true, "继续下载", "稍后下载", false, true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.gamedownload.GameSingleDownloadActivity.10
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    GameSingleDownloadActivity.this.destroyDownloadTask();
                    GameSingleDownloadActivity.this.finish();
                }
            });
            return false;
        }
        destroyDownloadTask();
        finish();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyDownloadTask() {
        DownloadTask downloadTask = this.downloadTask;
        if (downloadTask != null) {
            downloadTask.cancel();
            OkDownload.with().breakpointStore().remove(this.downloadTask.getId());
        }
    }
}

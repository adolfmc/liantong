package com.sinovatech.unicom.basic.p315ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.content.FileProvider;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.megvii.livenesslib.util.SDCardUtil;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMainConfig;
import com.sinovatech.unicom.basic.view.CircleProgress;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/* renamed from: com.sinovatech.unicom.basic.ui.view.UpdateProgressDialogView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UpdateProgressDialogView {
    private static final String TAG = "UpdateProgressDialogView";
    private Button button;
    private Activity context;
    private Dialog dialog;
    private boolean isForce;
    private ManagerMainConfig.ConfigListener listener;
    private Handler mHandler;
    private TextView num;

    /* renamed from: pv */
    private CircleProgress f18435pv;
    DownloadTask task;
    private TextView tip;
    private TextView total;
    private boolean isDownloadCompleted = false;
    private String apkPath = "";

    public UpdateProgressDialogView(final Activity activity, final boolean z, final ManagerMainConfig.ConfigListener configListener) {
        this.context = activity;
        this.isForce = z;
        this.listener = configListener;
        this.mHandler = new MyHandler(activity);
        this.dialog = new Dialog(activity, 2131951850);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
        double d = i;
        attributes.width = (int) (0.6d * d);
        attributes.height = -2;
        this.dialog.getWindow().setAttributes(attributes);
        View inflate = activity.getLayoutInflater().inflate(2131493502, (ViewGroup) null);
        this.f18435pv = (CircleProgress) inflate.findViewById(2131299274);
        this.button = (Button) inflate.findViewById(2131296558);
        this.button.setText("取消");
        this.tip = (TextView) inflate.findViewById(2131299276);
        this.num = (TextView) inflate.findViewById(2131299275);
        this.total = (TextView) inflate.findViewById(2131299277);
        this.button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.UpdateProgressDialogView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (UpdateProgressDialogView.this.isDownloadCompleted) {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "下载完成  点击安装apk");
                    UpdateProgressDialogView.this.anzhuangApk();
                }
                if (!z) {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "点击确定 非强制升级 onback");
                    UpdateProgressDialogView.this.dialog.dismiss();
                    ManagerMainConfig.ConfigListener configListener2 = configListener;
                    if (configListener2 != null) {
                        configListener2.onBack();
                    }
                } else {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "点击确定 强制升级 onexit");
                    ManagerMainConfig.ConfigListener configListener3 = configListener;
                    if (configListener3 != null) {
                        configListener3.onExit();
                    }
                    activity.finish();
                }
                if (UpdateProgressDialogView.this.task != null) {
                    UpdateProgressDialogView.this.task.cancel();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.dialog.setContentView(inflate);
        this.dialog.setCancelable(false);
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.show();
        int i2 = (int) (d * 0.65d);
        this.dialog.getWindow().setLayout(i2, i2);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.sinovatech.unicom.basic.ui.view.UpdateProgressDialogView$3] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.sinovatech.unicom.basic.ui.view.UpdateProgressDialogView$2] */
    public void startDownLoad(final String str) {
        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "准备下载apk");
        if (DeviceHelper.isTopFiveMarket() && !ManagerMainConfig.isGaryPub) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "是五大厂商系统 并且不是灰度升级 跳转应用市场");
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("market://details?id=com.sinovatech.unicom.ui"));
                this.context.startActivity(intent);
                this.dialog.dismiss();
                if (this.listener != null) {
                    if (this.isForce) {
                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "跳转市场 强制升级 退出应用 onexit");
                        this.listener.onExit();
                    } else {
                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "跳转市场 非强制升级 返回 onback");
                        this.listener.onBack();
                    }
                }
                return;
            } catch (Exception e) {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "跳转市场异常 本地下载apk");
                e.printStackTrace();
                new Thread() { // from class: com.sinovatech.unicom.basic.ui.view.UpdateProgressDialogView.2
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        UpdateProgressDialogView.this.downloadApkFile(str);
                    }
                }.start();
                return;
            }
        }
        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "不是五大厂商系统 本地下载apk");
        new Thread() { // from class: com.sinovatech.unicom.basic.ui.view.UpdateProgressDialogView.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                UpdateProgressDialogView.this.downloadApkFile(str);
            }
        }.start();
    }

    public void downloadApkFile(String str) {
        try {
            this.apkPath = SDCardUtil.getOwnFileUrl("APK");
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "本地下载创建下载任务");
            this.task = new DownloadTask.Builder(str, new File(this.apkPath)).setFilename("Unicom_ShouJiYingYeTing.apk").setMinIntervalMillisCallbackProcess(1000).setPassIfAlreadyCompleted(false).setAutoCallbackToUIThread(true).setConnectionCount(1).setPreAllocateLength(false).build();
            this.task.execute(new MyDownloadListener4WithSpeed());
        } catch (Exception unused) {
            Dialog dialog = this.dialog;
            if (dialog != null) {
                dialog.cancel();
            }
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "创建下载任务异常 跳转浏览器下载apk");
            if (this.listener != null) {
                if (this.isForce) {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "强制升级 退出应用 onexit");
                    this.listener.onExit();
                } else {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "非强制升级 返回 onback");
                    this.listener.onBack();
                }
            }
            UIUtils.toast("下载出现问题，正在为您转向浏览器继续下载！");
            downloadByBrowser(str);
        }
    }

    private void downloadByBrowser(String str) {
        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "执行跳转至浏览器进行下载apk");
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

    /* renamed from: com.sinovatech.unicom.basic.ui.view.UpdateProgressDialogView$MyHandler */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class MyHandler extends Handler {
        private final WeakReference<Activity> mActivity;

        public MyHandler(Activity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.mActivity.get() == null) {
                return;
            }
            if (message.what == 3000) {
                UpdateProgressDialogView.this.setProgressBarProgress(message.arg1, message.arg2);
            } else if (message.what == 1000) {
                UpdateProgressDialogView.this.setApkInfo(message.arg2);
            }
        }
    }

    public void setApkInfo(int i) {
        if (i != 0) {
            String str = ((i / 1024) / 1024) + "M";
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new ForegroundColorSpan(-1703897), 0, str.length(), 33);
            spannableStringBuilder.append((CharSequence) new SpannableString("共")).append((CharSequence) spannableString);
            this.total.setText(spannableStringBuilder);
            this.num.setText("0%");
        }
    }

    public void setProgressBarProgress(int i, int i2) {
        int i3 = (int) ((i / i2) * 100.0f);
        try {
            this.f18435pv.setValue(i3);
            TextView textView = this.num;
            textView.setText(i3 + "%");
        } catch (Exception e) {
            DownloadTask downloadTask = this.task;
            if (downloadTask != null) {
                downloadTask.cancel();
            }
            String str = TAG;
            MsLogUtil.m7977e(str, "设置进度条异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void anzhuangApk() {
        try {
            File file = new File(this.apkPath + "Unicom_ShouJiYingYeTing.apk");
            if (Build.VERSION.SDK_INT >= 24) {
                Uri uriForFile = FileProvider.getUriForFile(this.context, "com.sinovatech.unicom.ui.fileprovider", file);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setFlags(268435456);
                intent.addFlags(1);
                intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
                this.context.startActivity(intent);
            } else {
                Uri fromFile = Uri.fromFile(file);
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setDataAndType(fromFile, "application/vnd.android.package-archive");
                intent2.setFlags(268435456);
                this.context.startActivity(intent2);
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                UIUtils.toast("安装失败");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.sinovatech.unicom.basic.ui.view.UpdateProgressDialogView$4] */
    public void startDownLoadForAnchor(final String str) {
        new Thread() { // from class: com.sinovatech.unicom.basic.ui.view.UpdateProgressDialogView.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Looper.prepare();
                UpdateProgressDialogView.this.downloadApkFile(str);
                Looper.loop();
            }
        }.start();
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.view.UpdateProgressDialogView$MyDownloadListener4WithSpeed */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MyDownloadListener4WithSpeed extends DownloadListener4WithSpeed {
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

        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskStart(@NonNull DownloadTask downloadTask) {
        }

        public MyDownloadListener4WithSpeed() {
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void infoReady(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, boolean z, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel listener4SpeedModel) {
            try {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "初始化下载任务 获取apk信息");
                this.totalLength = breakpointInfo.getTotalLength();
                Message obtainMessage = UpdateProgressDialogView.this.mHandler.obtainMessage();
                obtainMessage.what = 1000;
                obtainMessage.arg2 = (int) this.totalLength;
                UpdateProgressDialogView.this.mHandler.sendMessage(obtainMessage);
            } catch (Exception unused) {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "初始化下载任务 获取apk信息异常");
                MsLogUtil.m7977e(UpdateProgressDialogView.TAG, "升级弹窗初始化下载失败");
                if (downloadTask != null) {
                    downloadTask.cancel();
                }
            }
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progress(@NonNull DownloadTask downloadTask, long j, @NonNull SpeedCalculator speedCalculator) {
            try {
                Message obtainMessage = UpdateProgressDialogView.this.mHandler.obtainMessage();
                obtainMessage.what = 3000;
                obtainMessage.arg1 = (int) j;
                obtainMessage.arg2 = (int) this.totalLength;
                UpdateProgressDialogView.this.mHandler.sendMessage(obtainMessage);
                UpdateProgressDialogView.this.isDownloadCompleted = false;
            } catch (Exception e) {
                if (downloadTask != null) {
                    downloadTask.cancel();
                }
                String str = UpdateProgressDialogView.TAG;
                MsLogUtil.m7977e(str, "升级弹窗设置进度异常:" + e.getMessage());
            }
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, @NonNull SpeedCalculator speedCalculator) {
            try {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "下载apk结束");
                if (StatusUtil.isCompleted(downloadTask)) {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "apk下载完成");
                    UpdateProgressDialogView.this.isDownloadCompleted = true;
                    UpdateProgressDialogView.this.button.setText("安装");
                    UpdateProgressDialogView.this.tip.setText("下载完成");
                } else {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "apk下载失败");
                    UpdateProgressDialogView.this.isDownloadCompleted = false;
                }
            } catch (Exception e) {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "下载apk结束 出现异常");
                if (downloadTask != null) {
                    downloadTask.cancel();
                }
                UpdateProgressDialogView.this.isDownloadCompleted = false;
                String str = UpdateProgressDialogView.TAG;
                MsLogUtil.m7977e(str, "升级弹窗下载异常:" + e.getMessage());
            }
        }
    }
}

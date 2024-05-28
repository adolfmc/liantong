package com.sinovatech.unicom.separatemodule.gamedistribution;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.content.FileProvider;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
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
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.tms.TMSUtil;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/gameDownloadTaskExecution")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameTaskExecutionJSPlugin extends BaseJSPlugin {
    private File parentFile;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("onExecSync ");
        JSONObject jSONObject = this.parameterJO;
        sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        MsLogUtil.m7980d(sb.toString());
        this.parentFile = GameDownloadUtils.createDic();
        String string = this.parameterJO.getString("type");
        String string2 = this.parameterJO.getJSONObject("originData").getString("pkgName");
        if (string.equals("isInstalled")) {
            return callbackSuccessSync(Boolean.valueOf(isInstalled(string2)));
        }
        return callbackSuccessSync(new JSONObject());
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("onExec ");
        JSONObject jSONObject = this.parameterJO;
        sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        MsLogUtil.m7980d(sb.toString());
        try {
            this.parentFile = GameDownloadUtils.createDic();
            String string = this.parameterJO.getString("type");
            String string2 = this.parameterJO.getJSONObject("originData").getString("mainTitle");
            String string3 = this.parameterJO.getJSONObject("originData").getString("downloadUrl");
            String string4 = this.parameterJO.getJSONObject("originData").getString("pkgName");
            if (string.equals("startDownload")) {
                DownloadTask createDownloadTask = GameDownloadUtils.createDownloadTask(this.parentFile, string4, string3);
                if (StatusUtil.isSameTaskPendingOrRunning(createDownloadTask)) {
                    createDownloadTask.cancel();
                }
                createDownloadTask.enqueue(new MyDownListener(string2, string4, string3));
            } else if (string.equals("stopDownload")) {
                GameDownloadUtils.createDownloadTask(this.parentFile, string4, string3).cancel();
                callbackSuccess(null);
            } else if (string.equals("delete")) {
                DownloadTask createDownloadTask2 = GameDownloadUtils.createDownloadTask(this.parentFile, string4, string3);
                createDownloadTask2.cancel();
                OkDownload.with().breakpointStore().remove(createDownloadTask2.getId());
                createDownloadTask2.getFile().delete();
                callbackSuccess(null);
            } else if (string.equals("installGame")) {
                boolean optBoolean = this.parameterJO.optBoolean("autoDeletePkg", true);
                listenerGameInstall(optBoolean, this.parentFile.getAbsolutePath() + File.separator + string4 + ".apk");
                installGame(this.parentFile.getAbsolutePath() + File.separator + string4 + ".apk");
            } else if (string.equals("openGame")) {
                openGame(string4);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackFail(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("msg", str);
            callbackFail(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class MyDownListener extends DownloadListener4WithSpeed {
        private String downloadUrl;
        private String mainTitle;
        private String pkgName;
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

        public MyDownListener(String str, String str2, String str3) {
            this.mainTitle = str;
            this.pkgName = str2;
            this.downloadUrl = str3;
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskStart(@NonNull DownloadTask downloadTask) {
            try {
                MsLogUtil.m7980d("okdownload-taskStart " + downloadTask.getFilename() + " " + StatusUtil.getStatus(downloadTask).name());
                GameDownloadTask taskBreakpointInfo = getTaskBreakpointInfo(downloadTask, "RUNNING");
                Gson gson = new Gson();
                JSONObject jSONObject = new JSONObject(!(gson instanceof Gson) ? gson.toJson(taskBreakpointInfo) : NBSGsonInstrumentation.toJson(gson, taskBreakpointInfo));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("action", "taskStart");
                jSONObject2.put("data", jSONObject);
                GameTaskExecutionJSPlugin.this.callbackSuccess(jSONObject2);
            } catch (Exception e) {
                e.printStackTrace();
                GameTaskExecutionJSPlugin.this.callbackFail(e.getMessage());
            }
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void infoReady(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, boolean z, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel listener4SpeedModel) {
            this.totalLength = breakpointInfo.getTotalLength();
            this.readableTotalLength = Util.humanReadableBytes(this.totalLength, true);
            MsLogUtil.m7980d("okdownload-infoReady " + downloadTask.getFilename() + " " + StatusUtil.getStatus(downloadTask).name());
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progress(@NonNull DownloadTask downloadTask, long j, @NonNull SpeedCalculator speedCalculator) {
            String humanReadableBytes = Util.humanReadableBytes(j, true);
            String str = humanReadableBytes + "/" + this.readableTotalLength;
            handleProgressUpdate(downloadTask, this.totalLength, this.readableTotalLength, j, humanReadableBytes, speedCalculator.speed());
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, @NonNull SpeedCalculator speedCalculator) {
            String str = "CANCEL";
            if (endCause != null) {
                try {
                    if (endCause.compareTo(EndCause.SAME_TASK_BUSY) == 0) {
                        str = "CANCEL";
                    } else if (endCause.compareTo(EndCause.PRE_ALLOCATE_FAILED) == 0) {
                        str = "CANCEL";
                    } else if (endCause.compareTo(EndCause.FILE_BUSY) == 0) {
                        str = "CANCEL";
                    } else if (endCause.compareTo(EndCause.CANCELED) == 0) {
                        str = "CANCEL";
                    } else if (endCause.compareTo(EndCause.COMPLETED) == 0) {
                        str = "COMPLETED";
                    } else if (endCause.compareTo(EndCause.ERROR) == 0) {
                        str = "ERROR";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    GameTaskExecutionJSPlugin.this.callbackFail(e.getMessage());
                    return;
                }
            }
            String str2 = "";
            if (exc != null) {
                str2 = "" + exc.getMessage();
            }
            MsLogUtil.m7980d("okdownload-taskEnd " + downloadTask.getFilename() + " " + str + " " + str2);
            GameDownloadTask taskBreakpointInfo = getTaskBreakpointInfo(downloadTask, str);
            taskBreakpointInfo.setErrorMsg(str2);
            Gson gson = new Gson();
            JSONObject jSONObject = new JSONObject(!(gson instanceof Gson) ? gson.toJson(taskBreakpointInfo) : NBSGsonInstrumentation.toJson(gson, taskBreakpointInfo));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("action", "taskEnd");
            jSONObject2.put("data", jSONObject);
            GameTaskExecutionJSPlugin.this.callbackSuccess(jSONObject2);
        }

        private void handleProgressUpdate(DownloadTask downloadTask, long j, String str, long j2, String str2, String str3) {
            try {
                GameDownloadTask gameDownloadTask = new GameDownloadTask(this.mainTitle, this.pkgName, this.downloadUrl, j, j2, str, str2, str3, "RUNNING");
                Gson gson = new Gson();
                String json = !(gson instanceof Gson) ? gson.toJson(gameDownloadTask) : NBSGsonInstrumentation.toJson(gson, gameDownloadTask);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("action", "progressUpdate");
                jSONObject.put("data", new JSONObject(json));
                GameTaskExecutionJSPlugin.this.callbackSuccess(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
                GameTaskExecutionJSPlugin.this.callbackFail(e.getMessage());
            }
        }

        private GameDownloadTask getTaskBreakpointInfo(DownloadTask downloadTask, String str) throws Exception {
            String str2;
            String str3;
            long j;
            long j2;
            String str4;
            BreakpointInfo breakpointInfo = OkDownload.with().breakpointStore().get(downloadTask.getId());
            if (breakpointInfo != null) {
                long totalLength = breakpointInfo.getTotalLength();
                long totalOffset = breakpointInfo.getTotalOffset();
                String humanReadableBytes = Util.humanReadableBytes(totalLength, true);
                j2 = totalOffset;
                str3 = Util.humanReadableBytes(totalOffset, true);
                j = totalLength;
                str2 = humanReadableBytes;
            } else {
                str2 = "";
                str3 = "";
                j = 0;
                j2 = 0;
            }
            if (StatusUtil.isCompleted(downloadTask)) {
                str4 = "COMPLETED";
            } else if (StatusUtil.getStatus(downloadTask) == StatusUtil.Status.PENDING) {
                str4 = "PENDING";
            } else {
                str4 = StatusUtil.getStatus(downloadTask) == StatusUtil.Status.RUNNING ? "RUNNING" : str;
            }
            return new GameDownloadTask(this.mainTitle, this.pkgName, this.downloadUrl, j, j2, str2, str3, "", str4);
        }
    }

    private void installGame(String str) throws Exception {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setDataAndType(FileProvider.getUriForFile(this.activityContext, "com.sinovatech.unicom.ui.fileprovider", new File(str)), "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        }
        intent.setFlags(268435456);
        intent.addFlags(1);
        this.activityContext.startActivity(intent);
    }

    private void listenerGameInstall(final boolean z, final String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        App.getInstance().registerReceiver(new BroadcastReceiver() { // from class: com.sinovatech.unicom.separatemodule.gamedistribution.GameTaskExecutionJSPlugin.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                App.getInstance().unregisterReceiver(this);
                GameTaskExecutionJSPlugin.this.callbackSuccess(null);
                if (z) {
                    File file = new File(str);
                    if (file.exists()) {
                        file.deleteOnExit();
                    }
                }
            }
        }, intentFilter);
    }

    private void openGame(String str) throws Exception {
        this.activityContext.startActivity(this.activityContext.getPackageManager().getLaunchIntentForPackage(str));
        callbackSuccess(null);
    }

    private boolean isInstalled(String str) throws Exception {
        return TMSUtil.isPkgInstalled(App.getInstance().getApplicationContext(), str);
    }
}

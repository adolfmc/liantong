package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.download;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.content.FileProvider;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import io.objectbox.Box;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/downloadFile")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DownloadFileJSPlugin extends BaseJSPlugin {
    private static String reqTempFilePath;
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
        this.parentFile = FileDownloadUtils.createDic(getCurrentCumpAppId(), getCurrentURL());
        String string = this.parameterJO.getString("type");
        String string2 = this.parameterJO.getString("url");
        JSONObject optJSONObject = this.parameterJO.optJSONObject("header");
        reqTempFilePath = "";
        if ("createFileDownloadTask".equals(string)) {
            try {
                return callbackSuccessSync(initFileDownloadTask(string2, optJSONObject));
            } catch (Exception e) {
                e.printStackTrace();
                return callbackFailSync(e.getMessage());
            }
        }
        return callbackFailSync("downloadFile接口type不匹配");
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("onExec ");
        JSONObject jSONObject = this.parameterJO;
        sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        MsLogUtil.m7980d(sb.toString());
        try {
            this.parentFile = FileDownloadUtils.createDic(getCurrentCumpAppId(), getCurrentURL());
            String string = this.parameterJO.getString("type");
            final String string2 = this.parameterJO.getString("url");
            JSONObject optJSONObject = this.parameterJO.optJSONObject("header");
            reqTempFilePath = "";
            if (string.equals("createFileDownloadTask")) {
                callbackSuccess(initFileDownloadTask(string2, optJSONObject));
            } else if (string.equals("startDownload")) {
                this.webFragment.addLifeListener("/MsJSPlugin/downloadFile", new BaseWebFragment.LifeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.download.DownloadFileJSPlugin.1
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.LifeListener
                    public void onDestory() {
                        try {
                            for (Map.Entry<String, DownloadTask> entry : App.fileDownlaodTaskMap.entrySet()) {
                                DownloadTask value = entry.getValue();
                                try {
                                    if (StatusUtil.isSameTaskPendingOrRunning(value)) {
                                        MsLogUtil.m7980d(value.getFilename() + " 正在下载，webviewfragment销毁时自动停止下载任务");
                                        value.cancel();
                                    }
                                    if (!StatusUtil.isCompleted(value)) {
                                        OkDownload.with().breakpointStore().remove(value.getId());
                                        value.getFile().delete();
                                        FileDownloadTask fileDownTaskByUrl = FileDownloadUtils.getFileDownTaskByUrl(value.getUrl());
                                        if (fileDownTaskByUrl != null) {
                                            FileDownloadUtils.getBox().remove((Box<FileDownloadTask>) fileDownTaskByUrl);
                                        }
                                    }
                                } catch (Exception e) {
                                    MsLogUtil.m7978e("webviewfragment销毁时自动停止下载任务 报错:" + e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            App.fileDownlaodTaskMap.clear();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
                new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.download.DownloadFileJSPlugin.2
                    /* JADX WARN: Removed duplicated region for block: B:34:0x00fc A[Catch: Exception -> 0x016e, TryCatch #0 {Exception -> 0x016e, blocks: (B:2:0x0000, B:21:0x00b0, B:23:0x00b7, B:25:0x00c7, B:26:0x00d1, B:28:0x00d7, B:32:0x00f6, B:34:0x00fc, B:35:0x0102, B:38:0x010a, B:20:0x0094, B:39:0x0147, B:41:0x015e, B:42:0x0161, B:5:0x000e, B:17:0x0066, B:16:0x0063), top: B:47:0x0000, inners: #1 }] */
                    /* JADX WARN: Removed duplicated region for block: B:37:0x0108  */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void run() {
                        /*
                            Method dump skipped, instructions count: 408
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.download.DownloadFileJSPlugin.RunnableC91142.run():void");
                    }
                }).start();
            } else if (string.equals("stopDownload")) {
                reqTempFilePath = this.parameterJO.optString("reqTempFilePath");
                FileDownloadTask fileDownTaskByUrl = FileDownloadUtils.getFileDownTaskByUrl(string2);
                FileDownloadUtils.createFileDownloadTask(this.parentFile, string2, fileDownTaskByUrl != null ? fileDownTaskByUrl.getFileName() : "", null).cancel();
                callbackSuccess(new JSONObject());
            } else if (string.equals("deleteTask")) {
                FileDownloadTask fileDownTaskByUrl2 = FileDownloadUtils.getFileDownTaskByUrl(string2);
                DownloadTask createFileDownloadTask = FileDownloadUtils.createFileDownloadTask(this.parentFile, string2, fileDownTaskByUrl2 != null ? fileDownTaskByUrl2.getFileName() : "", null);
                createFileDownloadTask.cancel();
                OkDownload.with().breakpointStore().remove(createFileDownloadTask.getId());
                createFileDownloadTask.getFile().delete();
                FileDownloadUtils.getBox().remove((Box<FileDownloadTask>) fileDownTaskByUrl2);
                callbackSuccess(new JSONObject());
            } else if (string.equals("openFile")) {
                FileDownloadTask fileDownTaskByUrl3 = FileDownloadUtils.getFileDownTaskByUrl(string2);
                openFile(FileDownloadUtils.createFileDownloadTask(this.parentFile, string2, fileDownTaskByUrl3 != null ? fileDownTaskByUrl3.getFileName() : "", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail(e.getMessage());
        }
    }

    private JSONObject initFileDownloadTask(String str, JSONObject jSONObject) throws Exception {
        long j;
        long j2;
        String str2;
        String absolutePath;
        FileDownloadTask fileDownTaskByUrl = FileDownloadUtils.getFileDownTaskByUrl(str);
        DownloadTask createFileDownloadTask = FileDownloadUtils.createFileDownloadTask(this.parentFile, str, fileDownTaskByUrl != null ? fileDownTaskByUrl.getFileName() : "", jSONObject);
        BreakpointInfo breakpointInfo = OkDownload.with().breakpointStore().get(createFileDownloadTask.getId());
        if (breakpointInfo != null) {
            j = breakpointInfo.getTotalLength();
            j2 = breakpointInfo.getTotalOffset();
        } else {
            j = 0;
            j2 = 0;
        }
        if (!StatusUtil.isCompleted(createFileDownloadTask)) {
            if (StatusUtil.getStatus(createFileDownloadTask) == StatusUtil.Status.PENDING) {
                str2 = "WAITING";
                absolutePath = "";
            } else if (StatusUtil.getStatus(createFileDownloadTask) == StatusUtil.Status.RUNNING) {
                str2 = "RUNNING";
                absolutePath = "";
            } else if (breakpointInfo == null || breakpointInfo.getTotalOffset() <= 0) {
                str2 = "INITED";
                absolutePath = "";
            } else {
                str2 = "PAUSED";
                absolutePath = "";
            }
        } else {
            str2 = "COMPLETED";
            absolutePath = createFileDownloadTask.getFile().getAbsolutePath();
            j = createFileDownloadTask.getFile().length();
            j2 = j;
        }
        FileDownloadTask fileDownloadTask = new FileDownloadTask(str, absolutePath, j, j2, "", str2);
        Gson gson = new Gson();
        String json = !(gson instanceof Gson) ? gson.toJson(fileDownloadTask) : NBSGsonInstrumentation.toJson(gson, fileDownloadTask);
        MsLogUtil.m7980d("createDownloadTask返回：" + createFileDownloadTask.getFilename() + " ### " + json);
        return new JSONObject(json);
    }

    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class MyDownListener extends DownloadListener4WithSpeed {
        private String downloadUrl;
        private long totalLength;

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void blockEnd(@NonNull DownloadTask downloadTask, int i, BlockInfo blockInfo, @NonNull SpeedCalculator speedCalculator) {
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progressBlock(@NonNull DownloadTask downloadTask, int i, long j, @NonNull SpeedCalculator speedCalculator) {
        }

        public MyDownListener(String str) {
            this.downloadUrl = str;
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskStart(@NonNull DownloadTask downloadTask) {
            try {
                MsLogUtil.m7980d("任务状态监听：taskStart " + downloadTask.getFilename() + " " + StatusUtil.getStatus(downloadTask).name());
                FileDownloadTask taskBreakpointInfo = getTaskBreakpointInfo(downloadTask, "WAITING");
                Gson gson = new Gson();
                JSONObject jSONObject = new JSONObject(!(gson instanceof Gson) ? gson.toJson(taskBreakpointInfo) : NBSGsonInstrumentation.toJson(gson, taskBreakpointInfo));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("action", "taskStart");
                jSONObject2.put("data", jSONObject);
                DownloadFileJSPlugin.this.callbackSuccess(jSONObject2);
            } catch (Exception e) {
                e.printStackTrace();
                DownloadFileJSPlugin.this.callbackFail(e.getMessage());
            }
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void infoReady(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, boolean z, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel listener4SpeedModel) {
            this.totalLength = breakpointInfo.getTotalLength();
            MsLogUtil.m7980d("任务状态监听：infoReady " + downloadTask.getFilename() + " " + StatusUtil.getStatus(downloadTask).name());
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectStart(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
            MsLogUtil.m7980d("任务状态监听：connectStart");
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectEnd(@NonNull DownloadTask downloadTask, int i, int i2, @NonNull Map<String, List<String>> map) {
            MsLogUtil.m7980d("任务状态监听：connectEnd");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progress(@NonNull DownloadTask downloadTask, long j, @NonNull SpeedCalculator speedCalculator) {
            String str = j + "/" + this.totalLength;
            handleProgressUpdate(downloadTask, this.totalLength, j, speedCalculator.speed());
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, @NonNull SpeedCalculator speedCalculator) {
            String str = "";
            if (endCause != null) {
                try {
                    if (endCause.compareTo(EndCause.SAME_TASK_BUSY) == 0) {
                        str = "PAUSED";
                    } else if (endCause.compareTo(EndCause.PRE_ALLOCATE_FAILED) == 0) {
                        str = "PAUSED";
                    } else if (endCause.compareTo(EndCause.FILE_BUSY) == 0) {
                        str = "PAUSED";
                    } else if (endCause.compareTo(EndCause.CANCELED) == 0) {
                        str = "PAUSED";
                    } else if (endCause.compareTo(EndCause.COMPLETED) == 0) {
                        str = "COMPLETED";
                    } else if (endCause.compareTo(EndCause.ERROR) == 0) {
                        str = "ERROR";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    DownloadFileJSPlugin.this.callbackFail(e.getMessage());
                    return;
                }
            }
            String str2 = "";
            if (exc != null) {
                str2 = "" + exc.getMessage();
            }
            MsLogUtil.m7980d("任务状态监听：taskEnd " + downloadTask.getFilename() + " " + str + " " + str2);
            FileDownloadTask taskBreakpointInfo = getTaskBreakpointInfo(downloadTask, str);
            taskBreakpointInfo.setErrorMsg(str2);
            if ("PAUSED".equals(taskBreakpointInfo.getTaskStatus()) && "yes".equals(DownloadFileJSPlugin.reqTempFilePath)) {
                taskBreakpointInfo.setTempFilePath(downloadTask.getFile().getAbsolutePath());
            }
            Gson gson = new Gson();
            JSONObject jSONObject = new JSONObject(!(gson instanceof Gson) ? gson.toJson(taskBreakpointInfo) : NBSGsonInstrumentation.toJson(gson, taskBreakpointInfo));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("action", "taskEnd");
            jSONObject2.put("data", jSONObject);
            DownloadFileJSPlugin.this.callbackSuccess(jSONObject2);
            try {
                if (StatusUtil.isCompleted(downloadTask)) {
                    try {
                        if (!Environment.getExternalStorageState().equals("mounted") || App.getInstance().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                            String insertImage = MediaStore.Images.Media.insertImage(DownloadFileJSPlugin.this.activityContext.getContentResolver(), downloadTask.getFile().getAbsolutePath(), downloadTask.getFile().getName(), "image:" + downloadTask.getFile().getName());
                            MsLogUtil.m7980d("任务状态监听：插入媒体库：" + insertImage.toString());
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    MediaScannerConnection.scanFile(DownloadFileJSPlugin.this.activityContext, new String[]{downloadTask.getFile().getAbsolutePath()}, null, null);
                    ((DownloadManager) DownloadFileJSPlugin.this.activityContext.getSystemService("download")).addCompletedDownload(downloadTask.getFilename(), downloadTask.getFilename(), true, FileDownloadUtils.getFileDownTaskByUrl(downloadTask.getUrl()).getFileContentType(), downloadTask.getFile().getAbsolutePath(), downloadTask.getFile().length(), false);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                MsLogUtil.m7978e("任务状态监听：文件下载完成，系统扫描报错：" + e3.getMessage());
            }
        }

        private void handleProgressUpdate(DownloadTask downloadTask, long j, long j2, String str) {
            try {
                FileDownloadTask fileDownloadTask = new FileDownloadTask(this.downloadUrl, "", j, j2, str, "RUNNING");
                Gson gson = new Gson();
                String json = !(gson instanceof Gson) ? gson.toJson(fileDownloadTask) : NBSGsonInstrumentation.toJson(gson, fileDownloadTask);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("action", "progressUpdate");
                jSONObject.put("data", new JSONObject(json));
                DownloadFileJSPlugin.this.callbackSuccess(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
                DownloadFileJSPlugin.this.callbackFail(e.getMessage());
            }
        }

        private FileDownloadTask getTaskBreakpointInfo(DownloadTask downloadTask, String str) throws Exception {
            long j;
            String str2;
            String absolutePath;
            long length;
            long j2;
            BreakpointInfo breakpointInfo = OkDownload.with().breakpointStore().get(downloadTask.getId());
            long j3 = 0;
            if (breakpointInfo != null) {
                j3 = breakpointInfo.getTotalLength();
                j = breakpointInfo.getTotalOffset();
            } else {
                j = 0;
            }
            if (!StatusUtil.isCompleted(downloadTask)) {
                if (!TextUtils.isEmpty(str)) {
                    str2 = str;
                    absolutePath = "";
                    length = j3;
                    j2 = j;
                } else if (StatusUtil.getStatus(downloadTask) == StatusUtil.Status.PENDING) {
                    str2 = "WAITING";
                    absolutePath = "";
                    length = j3;
                    j2 = j;
                } else if (StatusUtil.getStatus(downloadTask) == StatusUtil.Status.RUNNING) {
                    str2 = "RUNNING";
                    absolutePath = "";
                    length = j3;
                    j2 = j;
                } else if (StatusUtil.getStatus(downloadTask) == StatusUtil.Status.IDLE) {
                    str2 = "PAUSED";
                    absolutePath = "";
                    length = j3;
                    j2 = j;
                } else {
                    str2 = "INITED";
                    absolutePath = "";
                    length = j3;
                    j2 = j;
                }
            } else {
                str2 = "COMPLETED";
                absolutePath = downloadTask.getFile().getAbsolutePath();
                length = downloadTask.getFile().length();
                j2 = length;
            }
            return new FileDownloadTask(this.downloadUrl, absolutePath, length, j2, "", str2);
        }
    }

    private void openFile(DownloadTask downloadTask) {
        try {
            if (StatusUtil.isCompleted(downloadTask)) {
                if (downloadTask.getFilename().contains(".apk")) {
                    callbackFail("不允许打开apk可安装文件");
                    return;
                }
                FileDownloadTask fileDownTaskByUrl = FileDownloadUtils.getFileDownTaskByUrl(downloadTask.getUrl());
                String fileContentType = fileDownTaskByUrl != null ? fileDownTaskByUrl.getFileContentType() : "";
                if (TextUtils.isEmpty(fileContentType)) {
                    UIUtils.toastLong("未知的文件类型");
                    callbackFail("未知的文件类型");
                    return;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                if (Build.VERSION.SDK_INT >= 24) {
                    intent.setDataAndType(FileProvider.getUriForFile(this.activityContext, "com.sinovatech.unicom.ui.fileprovider", downloadTask.getFile()), fileContentType);
                } else {
                    intent.setDataAndType(Uri.fromFile(downloadTask.getFile()), fileContentType);
                }
                intent.setFlags(268435456);
                intent.addFlags(1);
                this.activityContext.startActivity(intent);
                callbackSuccess(new JSONObject());
                return;
            }
            callbackFail("不能预览尚未下载完成的文件");
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail(e.getMessage());
        }
    }
}

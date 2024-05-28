package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.content.FileProvider;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.megvii.livenesslib.util.SDCardUtil;
import com.sinovatech.unicom.basic.p315ui.share.ImageMergeUtil;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/FileSendMethod")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FileSendMethodJSPlugin extends BaseJSPlugin {
    private static final String TAG = "SystemSendJSPlugin";
    private DownloadTask downloadTask;
    private Disposable subscribe;
    private final String PDF_URL = "PDF_URL";
    private final String IMAGE_URL = "IMAGE_URL";
    private final String BASE64 = "BASE64";
    private boolean downloadStatus = false;
    private String FILE_PATH = SDCardUtil.getOwnFileUrl(Environment.DIRECTORY_DOWNLOADS) + "PDF" + File.separator;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        PermissionDialog.show("为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
        new RxPermissions(this.activityContext).request("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.share.FileSendMethodJSPlugin.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                PermissionDialog.dimissDialog();
                if (bool.booleanValue()) {
                    FileSendMethodJSPlugin.this.parserJson();
                } else {
                    FileSendMethodJSPlugin.this.callbackPdfFail("15", "未开启存储卡权限");
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.share.FileSendMethodJSPlugin.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                PermissionDialog.dimissDialog();
                FileSendMethodJSPlugin.this.callbackPdfFail("15", "未开启存储卡权限");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r3v11, types: [com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.share.FileSendMethodJSPlugin$4] */
    /* JADX WARN: Type inference failed for: r3v16, types: [com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.share.FileSendMethodJSPlugin$3] */
    public void parserJson() {
        delFile();
        this.downloadStatus = false;
        if (this.parameterJO == null) {
            callbackPdfFail("11", "参数异常");
            return;
        }
        final String optString = this.parameterJO.optString("type");
        if (TextUtils.isEmpty(optString)) {
            callbackPdfFail("11", "参数异常");
            return;
        }
        JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
        if (optJSONObject == null) {
            callbackPdfFail("11", "参数异常");
            return;
        }
        final String optString2 = optJSONObject.optString("fileName");
        if (TextUtils.isEmpty(optString2) || !optString2.contains(".pdf")) {
            callbackPdfFail("11", "参数异常");
        } else if (TextUtils.equals("PDF_URL", optString)) {
            final String optString3 = optJSONObject.optString("pdfUrl");
            if (TextUtils.isEmpty(optString3)) {
                callbackPdfFail("11", "参数异常");
                return;
            }
            new Thread() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.share.FileSendMethodJSPlugin.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    FileSendMethodJSPlugin.this.downloadFile(optString, optString3, optString2);
                }
            }.start();
            setDownloadTimeOut();
        } else {
            if (TextUtils.equals("BASE64", optString)) {
                String optString4 = optJSONObject.optString("base64Encoding");
                if (TextUtils.isEmpty(optString4)) {
                    callbackPdfFail("11", "参数异常");
                    return;
                }
                convertImageToPdf("BASE64", optString4, this.FILE_PATH + optString2);
            }
            if (TextUtils.equals("IMAGE_URL", optString)) {
                final String optString5 = optJSONObject.optString("imageUrl");
                if (TextUtils.isEmpty(optString5)) {
                    callbackPdfFail("11", "参数异常");
                    return;
                }
                new Thread() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.share.FileSendMethodJSPlugin.4
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        FileSendMethodJSPlugin.this.downloadFile(optString, optString5, optString2);
                    }
                }.start();
                setDownloadTimeOut();
            }
        }
    }

    private void delFile() {
        try {
            List<File> listFilesInDir = FileUtils.listFilesInDir(this.FILE_PATH);
            StringBuilder sb = new StringBuilder();
            sb.append("删除前文件数量 = ");
            int i = 0;
            sb.append(listFilesInDir == null ? 0 : listFilesInDir.size());
            MsLogUtil.m7979d(TAG, sb.toString());
            if (listFilesInDir != null) {
                Iterator<File> it = listFilesInDir.iterator();
                while (it.hasNext()) {
                    MsLogUtil.m7979d(TAG, "已经存在的文件名称:" + it.next().getName());
                }
            }
            FileUtils.deleteAllInDir(this.FILE_PATH);
            FileUtils.createOrExistsDir(this.FILE_PATH);
            List<File> listFilesInDir2 = FileUtils.listFilesInDir(this.FILE_PATH);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("删除后文件数量 = ");
            if (listFilesInDir != null) {
                i = listFilesInDir2.size();
            }
            sb2.append(i);
            MsLogUtil.m7979d(TAG, sb2.toString());
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, e.getMessage());
            callbackPdfFail("10", "删除文件失败!" + e.getMessage());
        }
    }

    public static boolean deleteDirectory(File file) {
        File[] listFiles;
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    deleteDirectory(file2);
                } else {
                    file2.delete();
                }
            }
        }
        return file.delete();
    }

    private void setDownloadTimeOut() {
        this.subscribe = ((ObservableSubscribeProxy) Observable.timer(10000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle((AppCompatActivity) this.activityContext))).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.share.FileSendMethodJSPlugin.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Long l) throws Exception {
                if (FileSendMethodJSPlugin.this.downloadStatus || FileSendMethodJSPlugin.this.downloadTask == null) {
                    return;
                }
                if (!StatusUtil.isCompleted(FileSendMethodJSPlugin.this.downloadTask)) {
                    FileSendMethodJSPlugin.this.downloadTask.cancel();
                }
                FileSendMethodJSPlugin.this.callbackPdfFail("10", "下载超时");
            }
        });
    }

    public void downloadFile(String str, String str2, String str3) {
        try {
            String changFileName = changFileName(str3);
            MsLogUtil.m7979d(TAG, "本地下载创建下载任务" + changFileName);
            this.downloadTask = new DownloadTask.Builder(str2, new File(this.FILE_PATH)).setFilename(changFileName).setMinIntervalMillisCallbackProcess(1000).setPassIfAlreadyCompleted(false).setAutoCallbackToUIThread(true).setConnectionCount(1).setPreAllocateLength(false).build();
            this.downloadTask.execute(new MyDownloadListener4WithSpeed(str));
        } catch (Exception e) {
            callbackPdfFail("10", "下载文件异常:" + e.getMessage());
            MsLogUtil.m7977e(TAG, "下载失败:" + e.getMessage());
        }
    }

    private String changFileName(String str) {
        File file = new File(this.FILE_PATH + str);
        if (file.exists() && file.isFile() && !file.delete()) {
            String substring = str.substring(0, str.lastIndexOf("."));
            MsLogUtil.m7979d(TAG, "存在同名文件");
            str = substring + "_1.pdf";
        }
        MsLogUtil.m7979d(TAG, "修改后的文件名为" + str);
        return str;
    }

    public void toSystemSend(File file) {
        if (file != null) {
            try {
                if (file.exists()) {
                    if (FileUtils.getSize(file).length() <= 0) {
                        MsLogUtil.m7979d(TAG, "文件为空");
                        callbackPdfFail("10", "文件为空");
                        return;
                    }
                    Uri uriForFile = FileProvider.getUriForFile(this.activityContext, "com.sinovatech.unicom.ui.fileprovider", file);
                    String type = this.activityContext.getContentResolver().getType(uriForFile);
                    MsLogUtil.m7979d(TAG, "推断的类型为:" + type);
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType(type);
                    intent.putExtra("android.intent.extra.STREAM", uriForFile);
                    Intent createChooser = Intent.createChooser(intent, "中国联通");
                    if (!IntentUtils.isIntentAvailable(createChooser)) {
                        callbackPdfFail("14", "当前不支持该拉起方式");
                        return;
                    }
                    this.activityContext.startActivity(createChooser);
                    if (this.subscribe != null && !this.subscribe.isDisposed()) {
                        this.subscribe.dispose();
                    }
                    callbackSuccess("0", "拉起成功", null);
                    return;
                }
            } catch (Exception e) {
                callbackPdfFail("10", "拉起失败:" + e.getMessage());
                MsLogUtil.m7977e(TAG, "发送方法异常:" + e.getMessage());
                return;
            }
        }
        MsLogUtil.m7979d(TAG, "文件不存在");
        callbackPdfFail("10", "文件不存在");
    }

    public void convertImageToPdf(String str, String str2, String str3) {
        Bitmap decodeFile;
        PdfDocument pdfDocument = new PdfDocument();
        try {
            if (TextUtils.equals("BASE64", str)) {
                decodeFile = ImageMergeUtil.stringtoBitmap(str2);
            } else if (TextUtils.equals("IMAGE_URL", str)) {
                decodeFile = BitmapFactory.decodeFile(str2);
            } else {
                callbackPdfFail("10", str + "类型不支持转换为pdf");
                return;
            }
            if (decodeFile == null) {
                callbackPdfFail("10", "图片转换失败");
                return;
            }
            PdfDocument.Page startPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(decodeFile.getWidth(), decodeFile.getHeight(), 1).create());
            startPage.getCanvas().drawBitmap(decodeFile, 0.0f, 0.0f, (Paint) null);
            pdfDocument.finishPage(startPage);
            File file = new File(str3);
            pdfDocument.writeTo(new FileOutputStream(file));
            pdfDocument.close();
            toSystemSend(file);
        } catch (IOException e) {
            e.printStackTrace();
            callbackPdfFail("10", "图片转换pdf异常:" + e.getMessage());
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MyDownloadListener4WithSpeed extends DownloadListener4WithSpeed {
        private String type;

        public MyDownloadListener4WithSpeed(String str) {
            this.type = "";
            this.type = str;
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskStart(@NonNull DownloadTask downloadTask) {
            MsLogUtil.m7979d(FileSendMethodJSPlugin.TAG, "taskStart");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void infoReady(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, boolean z, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel listener4SpeedModel) {
            MsLogUtil.m7979d(FileSendMethodJSPlugin.TAG, "初始化下载任务");
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectStart(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
            MsLogUtil.m7979d(FileSendMethodJSPlugin.TAG, "connectStart");
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectEnd(@NonNull DownloadTask downloadTask, int i, int i2, @NonNull Map<String, List<String>> map) {
            MsLogUtil.m7979d(FileSendMethodJSPlugin.TAG, "connectEnd");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progressBlock(@NonNull DownloadTask downloadTask, int i, long j, @NonNull SpeedCalculator speedCalculator) {
            MsLogUtil.m7979d(FileSendMethodJSPlugin.TAG, "progressBlock");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progress(@NonNull DownloadTask downloadTask, long j, @NonNull SpeedCalculator speedCalculator) {
            MsLogUtil.m7979d(FileSendMethodJSPlugin.TAG, j + "");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void blockEnd(@NonNull DownloadTask downloadTask, int i, BlockInfo blockInfo, @NonNull SpeedCalculator speedCalculator) {
            MsLogUtil.m7979d(FileSendMethodJSPlugin.TAG, "blockEnd");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, @NonNull SpeedCalculator speedCalculator) {
            try {
                if (downloadTask == null) {
                    FileSendMethodJSPlugin.this.callbackPdfFail("13", "下载文件失败");
                    return;
                }
                if (downloadTask != null) {
                    downloadTask.cancel();
                }
                MsLogUtil.m7979d(FileSendMethodJSPlugin.TAG, "下载文件结束");
                if (StatusUtil.isCompleted(downloadTask)) {
                    FileSendMethodJSPlugin.this.downloadStatus = true;
                    MsLogUtil.m7979d(FileSendMethodJSPlugin.TAG, "文件下载完成,文件名称[" + downloadTask.getFilename() + "],文件路径[" + downloadTask.getFile().getAbsolutePath() + "]");
                    String filename = downloadTask.getFilename();
                    String substring = filename.substring(0, filename.lastIndexOf("."));
                    if (TextUtils.equals("PDF_URL", this.type)) {
                        FileSendMethodJSPlugin.this.toSystemSend(downloadTask.getFile());
                        return;
                    } else if (!TextUtils.equals("IMAGE_URL", this.type)) {
                        FileSendMethodJSPlugin.this.callbackPdfFail("10", "暂不支持该类型转换为PDF");
                        return;
                    } else {
                        FileSendMethodJSPlugin fileSendMethodJSPlugin = FileSendMethodJSPlugin.this;
                        String absolutePath = downloadTask.getFile().getAbsolutePath();
                        fileSendMethodJSPlugin.convertImageToPdf("IMAGE_URL", absolutePath, FileSendMethodJSPlugin.this.FILE_PATH + substring + ".pdf");
                        return;
                    }
                }
                FileSendMethodJSPlugin.this.callbackPdfFail("13", "下载文件失败");
                MsLogUtil.m7979d(FileSendMethodJSPlugin.TAG, "文件下载失败");
            } catch (Exception e) {
                MsLogUtil.m7977e(FileSendMethodJSPlugin.TAG, "下载文件结束 出现异常");
                FileSendMethodJSPlugin fileSendMethodJSPlugin2 = FileSendMethodJSPlugin.this;
                fileSendMethodJSPlugin2.callbackPdfFail("10", "下载异常:" + e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackPdfFail(String str, String str2) {
        Disposable disposable = this.subscribe;
        if (disposable != null && !disposable.isDisposed()) {
            this.subscribe.dispose();
        }
        MsLogUtil.m7979d(TAG, String.format("status = %s , msg = %s", str, str2));
        callbackFail(str, str2);
    }
}

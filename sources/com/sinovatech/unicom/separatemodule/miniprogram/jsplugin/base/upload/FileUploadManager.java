package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload;

import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.ProgressRequestBody;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FileUploadManager {
    public static final String TAG = "UploadFileJSPlugin";
    private static FileUploadManager uploadManager;
    private Map<String, Call> callMap = new HashMap();

    public static FileUploadManager getInstance() {
        synchronized (FileUploadManager.class) {
            if (uploadManager == null) {
                uploadManager = new FileUploadManager();
            }
        }
        return uploadManager;
    }

    public void uploadFile(FileUploadParams fileUploadParams, final FileUploadListener fileUploadListener) throws Exception {
        MsLogUtil.m7979d(TAG, "uploadFile FileUplaodParams=" + fileUploadParams.toString());
        final long[] jArr = {0};
        final long[] jArr2 = {System.currentTimeMillis()};
        try {
            File file = new File(fileUploadParams.getFilePath());
            long uploadStartPosition = fileUploadParams.getUploadStartPosition();
            long uploadLength = fileUploadParams.getUploadLength();
            if (uploadStartPosition < 0) {
                throw new RuntimeException("起始字节位置不能小于0");
            }
            if (uploadStartPosition + uploadLength > file.length()) {
                throw new RuntimeException("上传字节数超出了文件的总长度");
            }
            OkHttpClient okHttpClient = App.getAsyncHttpClient(fileUploadParams.getTimeOut(), fileUploadParams.getTimeOut(), fileUploadParams.getTimeOut(), fileUploadParams.getTimeOut()).getOkHttpClient();
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            if (fileUploadParams.getFormData() != null) {
                for (Map.Entry<String, String> entry : fileUploadParams.getFormData().entrySet()) {
                    builder.addFormDataPart(entry.getKey(), entry.getValue());
                }
            }
            builder.addFormDataPart(fileUploadParams.getFileKeyName(), file.getName(), new ProgressRequestBody(new FileSkipRequestBody(file, uploadStartPosition, uploadLength, MediaType.parse("application/octet-stream")), new ProgressRequestBody.ProgressCallback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.FileUploadManager.1
                @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.ProgressRequestBody.ProgressCallback
                public void onProgress(long j, long j2) {
                    String str = "0";
                    long currentTimeMillis = System.currentTimeMillis();
                    long[] jArr3 = jArr2;
                    if (currentTimeMillis - jArr3[0] >= 500) {
                        try {
                            str = new DecimalFormat("0.00").format((((float) ((j - jArr[0]) / 1024)) / ((float) (currentTimeMillis - jArr3[0]))) * 1000.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        jArr[0] = j;
                        jArr2[0] = currentTimeMillis;
                        FileUploadListener fileUploadListener2 = fileUploadListener;
                        fileUploadListener2.onProgressUpdate(j, j2, str + "KB/S");
                    }
                }
            }));
            MultipartBody build = builder.build();
            Request.Builder builder2 = new Request.Builder();
            builder2.cacheControl(new CacheControl.Builder().noCache().build());
            builder2.url(fileUploadParams.getUrl());
            if (fileUploadParams.getHeader() != null) {
                for (Map.Entry<String, String> entry2 : fileUploadParams.getHeader().entrySet()) {
                    builder2.header(entry2.getKey(), entry2.getValue());
                }
            }
            builder2.post(build);
            Call newCall = okHttpClient.newCall(builder2.build());
            newCall.enqueue(new Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.FileUploadManager.2
                @Override // okhttp3.Callback
                public void onFailure(@NotNull Call call, @NotNull IOException iOException) {
                    iOException.printStackTrace();
                    MsLogUtil.m7977e(FileUploadManager.TAG, "uploadFile onFailure isCanceled=" + call.isCanceled() + " exception=" + iOException.getMessage());
                    if (call.isCanceled()) {
                        fileUploadListener.onCancel();
                    } else {
                        fileUploadListener.onError(iOException.getMessage());
                    }
                }

                @Override // okhttp3.Callback
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    MsLogUtil.m7979d(FileUploadManager.TAG, "uploadFile onResponse isCanceled=" + call.isCanceled());
                    int code = response.code();
                    String string = response.body().string();
                    MsLogUtil.m7979d(FileUploadManager.TAG, "uploadFile onResponse response=" + string);
                    fileUploadListener.onComplete(code, string);
                }
            });
            this.callMap.put(fileUploadParams.getUploadId(), newCall);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "uploadFile 运行报错 " + e.getMessage());
            e.printStackTrace();
            fileUploadListener.onError(e.getMessage());
        }
    }

    public void stopUpload(String str) throws Exception {
        Map<String, Call> map = this.callMap;
        if (map != null) {
            map.get(str).cancel();
        }
    }

    public void stopAllUpload() throws Exception {
        Map<String, Call> map = this.callMap;
        if (map != null) {
            map.keySet().forEach(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.FileUploadManager.3
                @Override // java.util.function.Consumer
                public void accept(String str) {
                    ((Call) FileUploadManager.this.callMap.get(str)).cancel();
                }
            });
        }
    }
}

package com.sinovatech.unicom.separatemodule.huotijiance;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.mabeijianxi.smallvideorecord2.DeviceUtils;
import com.mabeijianxi.smallvideorecord2.JianXiCamera;
import com.mabeijianxi.smallvideorecord2.LocalMediaCompress;
import com.mabeijianxi.smallvideorecord2.model.AutoVBRMode;
import com.mabeijianxi.smallvideorecord2.model.LocalMediaConfig;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p226hw.videoprocessor.VideoProcessor;
import com.p226hw.videoprocessor.util.VideoProgressListener;
import com.sinovatech.unicom.separatemodule.huotijiance.util.FileUtils;
import com.sinovatech.unicom.separatemodule.zhihuiwojia.CommonUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HuoTiUpload {
    private static final String TAG = "HuoTiUpload";
    private HuoTiEntity mEntity;
    private Handler mHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiUpload.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            HuoTiUpload.this.compressionTwo((String) message.obj);
        }
    };
    private String mVideoUrl;
    private MyAsyncHttpResponseHandler myHttpHandler;
    private WeakReference<Activity> reference;

    public HuoTiUpload(Activity activity, HuoTiEntity huoTiEntity, String str, boolean z, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        this.mVideoUrl = "";
        this.reference = new WeakReference<>(activity);
        this.mEntity = huoTiEntity;
        this.mVideoUrl = str;
        this.myHttpHandler = myAsyncHttpResponseHandler;
        initSmallVideo(activity);
    }

    public void initSmallVideo(Context context) {
        File file = new File(FileUtils.getSaveDir(context));
        if (DeviceUtils.isZte()) {
            if (file.exists()) {
                JianXiCamera.setVideoCachePath(file + File.separator);
                return;
            }
            JianXiCamera.setVideoCachePath(file.getPath().replace("/sdcard/", "/sdcard-ext/") + File.separator + "UnicomVideo" + File.separator + "YT" + File.separator);
            return;
        }
        JianXiCamera.setVideoCachePath(file + File.separator);
    }

    public void compressionOne() {
        Log.e(TAG, "=======compressionOne()==========");
        if (TextUtils.isEmpty(this.mVideoUrl)) {
            this.myHttpHandler.onFailure(new Throwable("视频文件不存在"), "视频文件不存在");
        } else if (getFileSize(this.mVideoUrl) < 650.0d) {
            upLoadVideoFile(this.mVideoUrl);
        } else {
            new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiUpload.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        final String str = FileUtils.getSaveDir((Context) HuoTiUpload.this.reference.get()) + File.separator + "YT_" + new SimpleDateFormat("yyyyMMdd_HHmmss", HuoTiUpload.this.getLocale((Activity) HuoTiUpload.this.reference.get())).format(new Date()) + ".mp4";
                        VideoProcessor.processor((Context) HuoTiUpload.this.reference.get()).input(HuoTiUpload.this.mVideoUrl).output(str).outWidth(480).outHeight(280).bitrate(500000).frameRate(10).progressListener(new VideoProgressListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiUpload.1.1
                            @Override // com.p226hw.videoprocessor.util.VideoProgressListener
                            public void onProgress(float f) {
                                if (f == 1.0f) {
                                    Log.e(HuoTiUpload.TAG, "=======VideoProcessor:  本地压缩======");
                                    Message obtain = Message.obtain();
                                    obtain.obj = str;
                                    HuoTiUpload.this.mHandler.sendMessageDelayed(obtain, 1000L);
                                }
                                String str2 = HuoTiUpload.TAG;
                                Log.e(str2, "======onProgress: " + f + "=======");
                            }
                        }).process();
                    } catch (Exception e) {
                        e.printStackTrace();
                        HuoTiUpload.this.myHttpHandler.onFailure(new Throwable("文件上传失败"), "文件上传失败");
                    }
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void compressionTwo(String str) {
        Log.e(TAG, "===========compressionTwo()============");
        try {
            final LocalMediaConfig build = new LocalMediaConfig.Buidler().setVideoPath(str).captureThumbnailsTime(1).doH264Compress(new AutoVBRMode(30)).setFramerate(0).setScale(1.0f).build();
            new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.HuoTiUpload.2
                @Override // java.lang.Runnable
                public void run() {
                    HuoTiUpload.this.upLoadVideoFile(new LocalMediaCompress(build).startCompress().getVideoPath());
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            this.myHttpHandler.onFailure(new Throwable("文件上传失败"), "文件上传失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upLoadVideoFile(String str) {
        JSONObject jSONObject;
        try {
            Log.e(TAG, "============upLoadVideoFile()=============");
            this.mHandler.removeCallbacksAndMessages(null);
            if (!TextUtils.isEmpty(str) && new File(str).exists()) {
                String formData = this.mEntity.getFormData();
                if (!TextUtils.isEmpty(formData)) {
                    jSONObject = new JSONObject(formData);
                } else {
                    jSONObject = new JSONObject();
                }
                jSONObject.put(this.mEntity.getName(), CommonUtils.fileToBase64(str));
                new MyAsyncHttpClient(this.reference.get()).postJson(this.mEntity.getUrl(), !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), null, this.myHttpHandler);
                return;
            }
            this.myHttpHandler.onFailure(new Throwable("文件上传失败"), "文件上传失败");
        } catch (Exception e) {
            e.printStackTrace();
            this.myHttpHandler.onFailure(new Throwable("文件上传失败"), "文件上传失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Locale getLocale(Activity activity) {
        Configuration configuration = activity.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            return getSystemLocale(configuration);
        }
        return getSystemLocaleLegacy(configuration);
    }

    public Locale getSystemLocaleLegacy(Configuration configuration) {
        return configuration.locale;
    }

    @TargetApi(24)
    public static Locale getSystemLocale(Configuration configuration) {
        return configuration.getLocales().get(0);
    }

    private double getFileSize(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return ((float) file.length()) / 1024.0f;
            }
            return 0.0d;
        } catch (Exception unused) {
            return 0.0d;
        }
    }
}

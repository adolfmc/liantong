package com.sinovatech.unicom.separatemodule.huotijiance.duwenzi;

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
import com.sinovatech.unicom.separatemodule.huotijiance.HuoTiEntity;
import com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpClient;
import com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpResponseHandler;
import com.sinovatech.unicom.separatemodule.huotijiance.util.FileUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.zhihuiwojia.CommonUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HuoTiWenZiUpload {
    private static final String TAG = "HuoTiWenZiUpload";
    private int frame;
    private HuoTiEntity mEntity;
    private Handler mHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiWenZiUpload.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            HuoTiWenZiUpload.this.compressionTwo((String) message.obj);
        }
    };
    private String mVideoUrl;
    private MyAsyncHttpResponseHandler myHttpHandler;
    private WeakReference<Activity> reference;

    public HuoTiWenZiUpload(Activity activity, HuoTiEntity huoTiEntity, String str, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        this.mVideoUrl = "";
        this.frame = 10;
        this.reference = new WeakReference<>(activity);
        this.mEntity = huoTiEntity;
        this.mVideoUrl = str;
        this.myHttpHandler = myAsyncHttpResponseHandler;
        if (!TextUtils.isEmpty(huoTiEntity.getFrame())) {
            try {
                this.frame = Integer.parseInt(huoTiEntity.getFrame());
            } catch (Exception unused) {
                this.frame = 10;
            }
        }
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
        MsLogUtil.m7979d(TAG, "=======compressionOne()==========");
        if (TextUtils.isEmpty(this.mVideoUrl)) {
            this.myHttpHandler.onFailure(new Throwable("视频文件不存在"), "视频文件不存在");
        } else if (getFileSize(this.mVideoUrl) < 500.0d) {
            upLoadVideoFile(this.mVideoUrl);
        } else {
            new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiWenZiUpload.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        final String str = FileUtils.getSaveDir((Context) HuoTiWenZiUpload.this.reference.get()) + "YT_" + new SimpleDateFormat("yyyyMMdd_HHmmss", HuoTiWenZiUpload.this.getLocale((Activity) HuoTiWenZiUpload.this.reference.get())).format(new Date()) + ".mp4";
                        VideoProcessor.processor((Context) HuoTiWenZiUpload.this.reference.get()).input(HuoTiWenZiUpload.this.mVideoUrl).output(str).frameRate(HuoTiWenZiUpload.this.frame).outWidth(480).outHeight(280).bitrate(500000).progressListener(new VideoProgressListener() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiWenZiUpload.1.1
                            @Override // com.p226hw.videoprocessor.util.VideoProgressListener
                            public void onProgress(float f) {
                                if (f == 1.0f) {
                                    Log.e(HuoTiWenZiUpload.TAG, "=======VideoProcessor:  本地压缩======");
                                    Message obtain = Message.obtain();
                                    obtain.obj = str;
                                    HuoTiWenZiUpload.this.mHandler.sendMessageDelayed(obtain, 1000L);
                                }
                                String str2 = HuoTiWenZiUpload.TAG;
                                Log.e(str2, "======onProgress: " + f + "=======");
                            }
                        }).process();
                    } catch (Exception e) {
                        e.printStackTrace();
                        HuoTiWenZiUpload.this.myHttpHandler.onFailure(new Throwable("文件上传失败"), "文件上传失败");
                    }
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void compressionTwo(String str) {
        Log.e(TAG, "===========compressionTwo()============");
        try {
            final LocalMediaConfig build = new LocalMediaConfig.Buidler().setVideoPath(str).captureThumbnailsTime(1).doH264Compress(new AutoVBRMode(30)).setFramerate(this.frame).setScale(1.0f).build();
            new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.duwenzi.HuoTiWenZiUpload.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        HuoTiWenZiUpload.this.upLoadVideoFile(new LocalMediaCompress(build).startCompress().getVideoPath());
                    } catch (Exception e) {
                        e.printStackTrace();
                        HuoTiWenZiUpload.this.myHttpHandler.onFailure(new Throwable("文件上传失败"), "文件上传失败");
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            this.myHttpHandler.onFailure(new Throwable("文件上传失败"), "文件上传失败");
        }
    }

    public void upLoadVideoFile(String str) {
        JSONObject jSONObject;
        try {
            Log.e(TAG, "============upLoadVideoFile()=============");
            this.mHandler.removeCallbacksAndMessages(null);
            if (!TextUtils.isEmpty(str) && new File(str).exists()) {
                if (!this.mEntity.isUpLoad()) {
                    this.myHttpHandler.onSuccess(200, "活体文字录制成功", str);
                    return;
                }
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
            if (!this.mEntity.isUpLoad()) {
                this.myHttpHandler.onSuccess(200, "录制失败", str);
            } else {
                this.myHttpHandler.onFailure(new Throwable("文件上传失败"), "文件上传失败");
            }
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

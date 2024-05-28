package com.baidu.p120ar.arplay.message;

import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.util.Log;
import com.baidu.p120ar.arplay.component.AudioPlayerManager;
import com.baidu.p120ar.arplay.component.VibrateManager;
import com.baidu.p120ar.arplay.component.VideoPlayerManager;
import com.baidu.p120ar.arplay.component.bean.AudioBean;
import com.baidu.p120ar.arplay.component.bean.TelBean;
import com.baidu.p120ar.arplay.component.bean.VibrateBean;
import com.baidu.p120ar.arplay.component.bean.VideoBean;
import com.baidu.p120ar.arplay.core.engine.ARPDataInteraction;
import com.baidu.p120ar.arplay.core.engine.ARPEngine;
import com.baidu.p120ar.arplay.core.message.ARPMessage;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import com.baidu.p120ar.arplay.util.NetStateReceiver;
import com.baidu.p120ar.arplay.webview.GLWebView;
import com.baidu.p120ar.arplay.webview.GLWebViewManager;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.message.ArSdkMessageHandler */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ArSdkMessageHandler implements ARPMessage.MessageHandler {
    private static final String PHONE_CALL = "phone_call";
    private static final String STATISTIC_TYPE = "type";
    private static final String VIBRATE = "vibrate";
    private Context mContext;
    private ARPDataInteraction.VideoUpdateCallback mVideoUpdateCallback = new ARPDataInteraction.VideoUpdateCallback() { // from class: com.baidu.ar.arplay.message.ArSdkMessageHandler.1
        @Override // com.baidu.p120ar.arplay.core.engine.ARPDataInteraction.VideoUpdateCallback
        public void onUpdateVideoFrame(String str, int i, String str2, String str3) {
            SurfaceTexture videoSurfaceTexture = VideoPlayerManager.getInstance().getVideoSurfaceTexture(str);
            if (videoSurfaceTexture != null) {
                try {
                    if (VideoPlayerManager.getInstance().getVideoMaterialWrapperTextureId(str) != i) {
                        VideoPlayerManager.getInstance().resetMediaSurface(str, i);
                    }
                    videoSurfaceTexture.updateTexImage();
                } catch (RuntimeException unused) {
                    VideoPlayerManager.getInstance().resetMediaSurface(str, i);
                }
            }
        }
    };

    public ArSdkMessageHandler(Context context) {
        this.mContext = context.getApplicationContext();
        NetStateReceiver.registerNetworkStateReceiver(this.mContext);
        videoUpdateCallback();
    }

    public void registerMessage() {
        ARPMessage.getInstance().registerMessageHandler(0, this);
        ARPEngine.getInstance().initDataStore(this.mContext.getSharedPreferences("baiduar_lua_data_store", 0));
    }

    @Override // com.baidu.p120ar.arplay.core.message.ARPMessage.MessageHandler
    public void handleMessage(int i, int i2, HashMap<String, Object> hashMap) {
        switch (i) {
            case 0:
                if (i2 == -2) {
                    VideoPlayerManager.getInstance().releaseMediaPlayer();
                    AudioPlayerManager.getInstance().releaseMediaPlayer();
                    return;
                }
                return;
            case 8:
                ARPEngine.getInstance().onCaseLoadCompleted(hashMap);
                return;
            case 9:
                ARPEngine.getInstance().onCaseUnloadCompleted();
                return;
            case 13:
                ARPEngine.getInstance().onTempleLoadCompleted(hashMap);
                return;
            case 1001:
            case 1003:
            case 1005:
            case 1007:
            case 1012:
                parseAudioData(hashMap, i);
                return;
            case 1021:
            case 1023:
            case 1025:
            case 1027:
                parseVideoData(hashMap, i);
                return;
            case 1401:
                parseTelData(hashMap);
                return;
            case 1501:
                parseVibrateData(hashMap);
                return;
            case 1901:
                parseLuaMessage(hashMap);
                return;
            default:
                return;
        }
    }

    private void parseLuaMessage(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return;
        }
        String obj2String = MsgParamsUtil.obj2String(hashMap.get("event_name"), "");
        GLWebView.WebViewData webViewData = new GLWebView.WebViewData();
        if ("load_webview".equals(obj2String)) {
            webViewData.textureId = MsgParamsUtil.obj2Int(hashMap.get("texture_id"), 0);
            webViewData.width = MsgParamsUtil.obj2Int(hashMap.get("width"), 0);
            webViewData.height = MsgParamsUtil.obj2Int(hashMap.get("height"), 0);
            webViewData.url = MsgParamsUtil.obj2String(hashMap.get("url"), null);
            webViewData.isRemote = MsgParamsUtil.obj2Int(hashMap.get("is_remote"), 0) == 1;
            GLWebViewManager.getInstance().loadGLWebView(webViewData);
        } else if ("update_webview_js".equals(obj2String)) {
            webViewData.textureId = MsgParamsUtil.obj2Int(hashMap.get("texture_id"), 0);
            webViewData.jsCode = MsgParamsUtil.obj2String(hashMap.get("js_code"), null);
            GLWebViewManager.getInstance().updateWebView(webViewData);
        } else if ("load_native_webview".equals(obj2String)) {
            webViewData.url = MsgParamsUtil.obj2String(hashMap.get("url"), null);
            webViewData.isRemote = MsgParamsUtil.obj2Int(hashMap.get("is_remote"), 0) == 1;
            GLWebViewManager.getInstance().loadNativeWebView(webViewData);
        }
    }

    private void parseAudioData(HashMap<String, Object> hashMap, int i) {
        if (hashMap == null) {
            return;
        }
        AudioBean audioBean = new AudioBean();
        if (hashMap.get("url") != null) {
            audioBean.setUrl(MsgParamsUtil.obj2String(hashMap.get("url"), null));
        }
        if (hashMap.get("delay") != null) {
            audioBean.setDelay(((Float) hashMap.get("delay")).floatValue());
        }
        if (hashMap.get("id") != null) {
            audioBean.setId(MsgParamsUtil.obj2String(hashMap.get("id"), null));
        }
        if (hashMap.get("loop") != null) {
            int intValue = ((Integer) hashMap.get("loop")).intValue();
            audioBean.setLoop(intValue);
            if (intValue <= 0) {
                audioBean.setLoopForever(true);
            }
        }
        if (hashMap.get("target") != null) {
            audioBean.setTargetName(MsgParamsUtil.obj2String(hashMap.get("target"), null));
        }
        if (hashMap.get("from_time") != null) {
            audioBean.setFromTime(MsgParamsUtil.obj2Int(hashMap.get("from_time"), 0));
        }
        if (i == 1001) {
            AudioPlayerManager.getInstance().openAudio(audioBean, hashMap);
        } else if (i == 1003) {
            AudioPlayerManager.getInstance().pauseAudio(audioBean, hashMap);
        } else if (i == 1005) {
            AudioPlayerManager.getInstance().resumeAudio(audioBean, hashMap);
        } else if (i == 1007) {
            AudioPlayerManager.getInstance().stopAudio(audioBean, hashMap);
        } else if (i != 1012) {
        } else {
            AudioPlayerManager.getInstance().resetAudio(audioBean, hashMap);
        }
    }

    private void parseVideoData(HashMap<String, Object> hashMap, int i) {
        if (hashMap == null) {
            return;
        }
        VideoBean videoBean = new VideoBean();
        if (hashMap.get("url") != null) {
            videoBean.setUrl(MsgParamsUtil.obj2String(hashMap.get("url"), null));
        }
        if (hashMap.get("id") != null) {
            videoBean.setId(MsgParamsUtil.obj2String(hashMap.get("id"), null));
        }
        if (hashMap.get("texture_id") != null) {
            videoBean.setTextureid(MsgParamsUtil.obj2Int(hashMap.get("texture_id"), -1));
        }
        if (hashMap.get("loop") != null) {
            int obj2Int = MsgParamsUtil.obj2Int(hashMap.get("loop"), 0);
            videoBean.setLoop(MsgParamsUtil.obj2Int(hashMap.get("loop"), 0));
            if (obj2Int <= 0) {
                videoBean.setLoopForever(true);
            }
        }
        if (hashMap.get("target") != null) {
            videoBean.setTargetName(MsgParamsUtil.obj2String(hashMap.get("target"), null));
        }
        if (hashMap.get("from_time") != null) {
            videoBean.setFromTime(MsgParamsUtil.obj2Int(hashMap.get("from_time"), 0));
            Log.e("VideoTest", "bean fromTime: " + videoBean.getFromTime());
        }
        if (i == 1021) {
            VideoPlayerManager.getInstance().openVideo(videoBean, hashMap);
        } else if (i == 1023) {
            VideoPlayerManager.getInstance().pauseVideo(videoBean, hashMap);
        } else if (i == 1025) {
            VideoPlayerManager.getInstance().resumeVideo(videoBean, hashMap);
        } else if (i != 1027) {
        } else {
            VideoPlayerManager.getInstance().stopVideo(videoBean, hashMap);
        }
    }

    public void release() {
        this.mVideoUpdateCallback = null;
        NetStateReceiver.unRegisterNetworkStateReceiver(this.mContext);
    }

    private void parseVibrateData(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return;
        }
        VibrateBean vibrateBean = new VibrateBean();
        if (hashMap.get("type") != null) {
            vibrateBean.setType(MsgParamsUtil.obj2Int(hashMap.get("type"), 0));
        }
        if (hashMap.get("interval") != null) {
            vibrateBean.setInterval(MsgParamsUtil.obj2Int(hashMap.get("interval"), 0));
        }
        if (hashMap.get("pattern") != null) {
            vibrateBean.setPattern(MsgParamsUtil.obj2String(hashMap.get("pattern"), null));
        }
        openVibrate(vibrateBean);
    }

    private void openVibrate(VibrateBean vibrateBean) {
        sendStatistic("vibrate");
        switch (vibrateBean.getType()) {
            case 0:
                VibrateManager.getInstance(this.mContext).playVibrate(vibrateBean.getInterval());
                return;
            case 1:
                long[] jArr = null;
                String[] split = vibrateBean.getPattern() != null ? vibrateBean.getPattern().split(",") : null;
                if (split != null && split.length > 0) {
                    int length = split.length;
                    jArr = new long[length];
                    for (int i = 0; i < length; i++) {
                        try {
                            jArr[i] = Long.parseLong(split[i]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
                VibrateManager.getInstance(this.mContext).playVibrate(jArr);
                return;
            default:
                return;
        }
    }

    private void parseTelData(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return;
        }
        TelBean telBean = new TelBean();
        Object obj = hashMap.get("number");
        if (obj != null) {
            telBean.setNumber((String) obj);
        }
        openCall(telBean);
    }

    private void openCall(TelBean telBean) {
        sendStatistic("phone_call");
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setFlags(268435456);
        intent.setData(Uri.parse("tel:" + telBean.getNumber()));
        if (intent.resolveActivity(this.mContext.getPackageManager()) != null) {
            this.mContext.startActivity(intent);
        }
    }

    private void videoUpdateCallback() {
        ARPEngine.getInstance().setVideoUpdateCallback(this.mVideoUpdateCallback);
    }

    private void sendStatistic(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("type", str);
        handleMessage(1801, 0, hashMap);
    }
}

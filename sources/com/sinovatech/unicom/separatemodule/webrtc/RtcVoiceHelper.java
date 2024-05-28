package com.sinovatech.unicom.separatemodule.webrtc;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceModule;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RtcVoiceHelper {
    private Activity activityContext;
    private String beautyLevel;
    private RtcVoiceModule.CallBack callBack;

    /* renamed from: group  reason: collision with root package name */
    private ViewGroup f27863group;
    private RtcVoiceModule module;
    private String roomId;
    private int type;

    private RtcVoiceHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class RtcHelperInstance {
        private static final RtcVoiceHelper INSTANCE = new RtcVoiceHelper();

        private RtcHelperInstance() {
        }
    }

    public static RtcVoiceHelper getInstance() {
        return RtcHelperInstance.INSTANCE;
    }

    public void buildModul(Activity activity, ViewGroup viewGroup, String str, int i, String str2, RtcVoiceModule.CallBack callBack) {
        this.activityContext = activity;
        this.f27863group = viewGroup;
        this.roomId = str;
        this.type = i;
        this.beautyLevel = str2;
        this.callBack = callBack;
        this.module = new RtcVoiceModule(activity, viewGroup, str, i, str2, callBack);
    }

    public void destory() {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.recycleResource();
            this.module = null;
        }
    }

    public void setUserNick(String str) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.setUserNick(str);
        }
    }

    public void setWaitType(RtcVoiceModule.CallBack callBack) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.connectRtc(callBack);
        }
    }

    public void setConnectRoom(String str, String str2, RtcVoiceModule.CallBack callBack) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.connectRtc(str, str2, callBack);
        }
    }

    public void zoom(boolean z) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.zoomView(z);
        }
    }

    public void pauseStreaming(boolean z) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            if (z) {
                rtcVoiceModule.pauseStreaming();
            } else {
                rtcVoiceModule.resumeStreaming();
            }
        }
    }

    public void setMonitorChangeAudio(RtcVoiceModule.CallBack callBack) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.monitorChangeAudio(callBack);
        }
    }

    public void setOnlyAudio() {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.toVoice();
        }
    }

    public boolean getDisableBack() {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            return rtcVoiceModule.isDisableBack();
        }
        return false;
    }

    public void setSwitchCamera(boolean z) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.switchCamera(z);
        }
    }

    public void hideOrResumeWindows(boolean z) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            try {
                rtcVoiceModule.hideOrResumeWindows(z);
            } catch (Exception e) {
                MsLogUtil.m7978e("RtcVoiceHelper--hideOrResumeWindows==" + e.getMessage());
            }
        }
    }

    public void setTips(String str) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.setTips(str);
        }
    }

    public boolean getRtcStatus() {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            return rtcVoiceModule.getRtcStatus();
        }
        return false;
    }

    public void addAlertView(View view) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.addAlertView(view);
        }
    }

    public void hiddenAlertView() {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.removeAlertView();
        }
    }

    public void hiddenWebRtcView(boolean z) {
        RtcVoiceModule rtcVoiceModule = this.module;
        if (rtcVoiceModule != null) {
            rtcVoiceModule.hiddenWebRtcView(z);
        }
    }
}

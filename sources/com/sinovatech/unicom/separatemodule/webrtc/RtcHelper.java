package com.sinovatech.unicom.separatemodule.webrtc;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.webrtc.RtcModule;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RtcHelper {
    private Activity activityContext;
    private String beautyLevel;
    private RtcModule.CallBack callBack;

    /* renamed from: group  reason: collision with root package name */
    private ViewGroup f27861group;
    private RtcModule module;
    private String role;
    private String roomId;
    private int type;

    private RtcHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class RtcHelperInstance {
        private static final RtcHelper INSTANCE = new RtcHelper();

        private RtcHelperInstance() {
        }
    }

    public static RtcHelper getInstance() {
        return RtcHelperInstance.INSTANCE;
    }

    public void buildModul(Activity activity, ViewGroup viewGroup, String str, int i, String str2, String str3, RtcModule.CallBack callBack) {
        this.activityContext = activity;
        this.f27861group = viewGroup;
        this.roomId = str;
        this.type = i;
        this.beautyLevel = str2;
        this.role = str3;
        this.callBack = callBack;
        this.module = new RtcModule(activity, viewGroup, str, i, str2, str3, callBack);
    }

    public void destory() {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            rtcModule.recycleResource();
            this.module = null;
        }
    }

    public void setWaitType(RtcModule.CallBack callBack) {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            rtcModule.connectRtc(callBack);
        }
    }

    public void setUserNick(String str) {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            rtcModule.setUserNick(str);
        }
    }

    public void setConnectRoom(String str, String str2, RtcModule.CallBack callBack) {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            rtcModule.connectRtc(str, str2, callBack);
        }
    }

    public void zoom(boolean z) {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            rtcModule.zoomView(z);
        }
    }

    public void pauseStreaming(boolean z) {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            if (z) {
                rtcModule.pauseStreaming();
            } else {
                rtcModule.resumeStreaming();
            }
        }
    }

    public boolean getDisableBack() {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            return rtcModule.isDisableBack();
        }
        return false;
    }

    public void setSwitchCamera(boolean z) {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            rtcModule.remoteCameraStaus(z);
        }
    }

    public void hideOrResumeWindows(boolean z) {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            try {
                rtcModule.hideOrResumeWindows(z);
            } catch (Exception e) {
                MsLogUtil.m7978e("RtcHelper--hideOrResumeWindows==" + e.getMessage());
            }
        }
    }

    public void setTips(String str) {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            rtcModule.setTips(str);
        }
    }

    public boolean getRtcStatus() {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            return rtcModule.getRtcStatus();
        }
        return false;
    }

    public void addAlertView(View view) {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            rtcModule.addAlertView(view);
        }
    }

    public void hiddenAlertView() {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            rtcModule.removeAlertView();
        }
    }

    public void hiddenWebRtcView(boolean z) {
        RtcModule rtcModule = this.module;
        if (rtcModule != null) {
            rtcModule.hiddenWebRtcView(z);
        }
    }
}

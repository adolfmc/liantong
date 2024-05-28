package com.tydic.softphone;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Configs {
    public static final String AUDIO_AUTO_GAIN_CONTROL_CONSTRAINT = "googAutoGainControl";
    public static final String AUDIO_ECHO_CANCELLATION_CONSTRAINT = "googEchoCancellation";
    public static final String AUDIO_HIGH_PASS_FILTER_CONSTRAINT = "googHighpassFilter";
    public static final String AUDIO_NOISE_SUPPRESSION_CONSTRAINT = "googNoiseSuppression";
    public static final String PLUGIN_SIP = "janus.plugin.sip";
    public static final String PLUGIN_VIDEO_ROOM = "janus.plugin.videoroom";
    public static final String STUN_URI = "stun:113.200.129.50 3478";
    public static String TALK_CLOSE = "通话中断";
    public static String TALK_CLOSE_MSG = "通话中断，请重试";
    public static String TURN_URI = "turn:113.200.129.52 3478";
    public static String TURN_USER_NAME = "webrtc";
    public static String TURN_USER_PASSWORD = "1234";

    public static void showFloatToast(Context context, String str) {
        int height = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
        Toast makeText = Toast.makeText(context, str, 0);
        makeText.setGravity(80, 0, height / 9);
        makeText.show();
    }
}

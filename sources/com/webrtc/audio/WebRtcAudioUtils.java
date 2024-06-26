package com.webrtc.audio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import com.webrtc.Logging;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class WebRtcAudioUtils {
    private static final String TAG = "WebRtcAudioUtilsExternal";

    @TargetApi(24)
    public static String audioSourceToString(int i) {
        switch (i) {
            case 0:
                return "DEFAULT";
            case 1:
                return "MIC";
            case 2:
                return "VOICE_UPLINK";
            case 3:
                return "VOICE_DOWNLINK";
            case 4:
                return "VOICE_CALL";
            case 5:
                return "CAMCORDER";
            case 6:
                return "VOICE_RECOGNITION";
            case 7:
                return "VOICE_COMMUNICATION";
            case 8:
            default:
                return "INVALID";
            case 9:
                return "UNPROCESSED";
            case 10:
                return "VOICE_PERFORMANCE";
        }
    }

    public static String channelMaskToString(int i) {
        return i != 12 ? i != 16 ? "INVALID" : "IN_MONO" : "IN_STEREO";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String deviceTypeToString(int i) {
        switch (i) {
            case 0:
                return "TYPE_UNKNOWN";
            case 1:
                return "TYPE_BUILTIN_EARPIECE";
            case 2:
                return "TYPE_BUILTIN_SPEAKER";
            case 3:
                return "TYPE_WIRED_HEADSET";
            case 4:
                return "TYPE_WIRED_HEADPHONES";
            case 5:
                return "TYPE_LINE_ANALOG";
            case 6:
                return "TYPE_LINE_DIGITAL";
            case 7:
                return "TYPE_BLUETOOTH_SCO";
            case 8:
                return "TYPE_BLUETOOTH_A2DP";
            case 9:
                return "TYPE_HDMI";
            case 10:
                return "TYPE_HDMI_ARC";
            case 11:
                return "TYPE_USB_DEVICE";
            case 12:
                return "TYPE_USB_ACCESSORY";
            case 13:
                return "TYPE_DOCK";
            case 14:
                return "TYPE_FM";
            case 15:
                return "TYPE_BUILTIN_MIC";
            case 16:
                return "TYPE_FM_TUNER";
            case 17:
                return "TYPE_TV_TUNER";
            case 18:
                return "TYPE_TELEPHONY";
            case 19:
                return "TYPE_AUX_LINE";
            case 20:
                return "TYPE_IP";
            case 21:
                return "TYPE_BUS";
            case 22:
                return "TYPE_USB_HEADSET";
            default:
                return "TYPE_UNKNOWN";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String modeToString(int i) {
        switch (i) {
            case 0:
                return "MODE_NORMAL";
            case 1:
                return "MODE_RINGTONE";
            case 2:
                return "MODE_IN_CALL";
            case 3:
                return "MODE_IN_COMMUNICATION";
            default:
                return "MODE_INVALID";
        }
    }

    private static String streamTypeToString(int i) {
        switch (i) {
            case 0:
                return "STREAM_VOICE_CALL";
            case 1:
                return "STREAM_SYSTEM";
            case 2:
                return "STREAM_RING";
            case 3:
                return "STREAM_MUSIC";
            case 4:
                return "STREAM_ALARM";
            case 5:
                return "STREAM_NOTIFICATION";
            default:
                return "STREAM_INVALID";
        }
    }

    WebRtcAudioUtils() {
    }

    public static boolean runningOnJellyBeanMR1OrHigher() {
        return Build.VERSION.SDK_INT >= 17;
    }

    public static boolean runningOnJellyBeanMR2OrHigher() {
        return Build.VERSION.SDK_INT >= 18;
    }

    public static boolean runningOnLollipopOrHigher() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean runningOnMarshmallowOrHigher() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean runningOnNougatOrHigher() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean runningOnOreoOrHigher() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static boolean runningOnOreoMR1OrHigher() {
        return Build.VERSION.SDK_INT >= 27;
    }

    public static String getThreadInfo() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + "]";
    }

    public static boolean runningOnEmulator() {
        return Build.HARDWARE.equals("goldfish") && Build.BRAND.startsWith("generic_");
    }

    static void logDeviceInfo(String str) {
        Logging.m5305d(str, "Android SDK: " + Build.VERSION.SDK_INT + ", Release: " + Build.VERSION.RELEASE + ", Brand: " + Build.BRAND + ", Device: " + Build.DEVICE + ", Id: " + Build.ID + ", Hardware: " + Build.HARDWARE + ", Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", Product: " + Build.PRODUCT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void logAudioState(String str, Context context, AudioManager audioManager) {
        logDeviceInfo(str);
        logAudioStateBasic(str, context, audioManager);
        logAudioStateVolume(str, audioManager);
        logAudioDeviceInfo(str, audioManager);
    }

    private static void logAudioStateBasic(String str, Context context, AudioManager audioManager) {
        Logging.m5305d(str, "Audio State: audio mode: " + modeToString(audioManager.getMode()) + ", has mic: " + hasMicrophone(context) + ", mic muted: " + audioManager.isMicrophoneMute() + ", music active: " + audioManager.isMusicActive() + ", speakerphone: " + audioManager.isSpeakerphoneOn() + ", BT SCO: " + audioManager.isBluetoothScoOn());
    }

    @SuppressLint({"NewApi"})
    private static void logAudioStateVolume(String str, AudioManager audioManager) {
        boolean z;
        int[] iArr = {0, 3, 2, 4, 5, 1};
        Logging.m5305d(str, "Audio State: ");
        if (runningOnLollipopOrHigher()) {
            z = audioManager.isVolumeFixed();
            Logging.m5305d(str, "  fixed volume=" + z);
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        for (int i : iArr) {
            StringBuilder sb = new StringBuilder();
            sb.append("  " + streamTypeToString(i) + ": ");
            sb.append("volume=");
            sb.append(audioManager.getStreamVolume(i));
            sb.append(", max=");
            sb.append(audioManager.getStreamMaxVolume(i));
            logIsStreamMute(str, audioManager, i, sb);
            Logging.m5305d(str, sb.toString());
        }
    }

    @TargetApi(23)
    private static void logIsStreamMute(String str, AudioManager audioManager, int i, StringBuilder sb) {
        if (runningOnMarshmallowOrHigher()) {
            sb.append(", muted=");
            sb.append(audioManager.isStreamMute(i));
        }
    }

    @TargetApi(23)
    private static void logAudioDeviceInfo(String str, AudioManager audioManager) {
        if (runningOnMarshmallowOrHigher()) {
            AudioDeviceInfo[] devices = audioManager.getDevices(3);
            if (devices.length != 0) {
                Logging.m5305d(str, "Audio Devices: ");
                for (AudioDeviceInfo audioDeviceInfo : devices) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("  ");
                    sb.append(deviceTypeToString(audioDeviceInfo.getType()));
                    sb.append(audioDeviceInfo.isSource() ? "(in): " : "(out): ");
                    if (audioDeviceInfo.getChannelCounts().length > 0) {
                        sb.append("channels=");
                        sb.append(Arrays.toString(audioDeviceInfo.getChannelCounts()));
                        sb.append(", ");
                    }
                    if (audioDeviceInfo.getEncodings().length > 0) {
                        sb.append("encodings=");
                        sb.append(Arrays.toString(audioDeviceInfo.getEncodings()));
                        sb.append(", ");
                    }
                    if (audioDeviceInfo.getSampleRates().length > 0) {
                        sb.append("sample rates=");
                        sb.append(Arrays.toString(audioDeviceInfo.getSampleRates()));
                        sb.append(", ");
                    }
                    sb.append("id=");
                    sb.append(audioDeviceInfo.getId());
                    Logging.m5305d(str, sb.toString());
                }
            }
        }
    }

    @TargetApi(24)
    public static String audioEncodingToString(int i) {
        if (i != 0) {
            switch (i) {
                case 2:
                    return "PCM_16BIT";
                case 3:
                    return "PCM_8BIT";
                case 4:
                    return "PCM_FLOAT";
                case 5:
                    return "AC3";
                case 6:
                    return "AC3";
                case 7:
                    return "DTS";
                case 8:
                    return "DTS_HD";
                case 9:
                    return "MP3";
                default:
                    return "Invalid encoding: " + i;
            }
        }
        return "INVALID";
    }

    private static boolean hasMicrophone(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.microphone");
    }
}

package com.webrtc;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Surface;
import com.webrtc.EglBase;
import com.webrtc.EglBase14;
import com.webrtc.VideoEncoderFactory;
import com.webrtc.VideoFrame;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@TargetApi(19)
@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class MediaCodecVideoEncoder {
    private static final int BITRATE_ADJUSTMENT_FPS = 30;
    private static final double BITRATE_CORRECTION_MAX_SCALE = 4.0d;
    private static final double BITRATE_CORRECTION_SEC = 3.0d;
    private static final int BITRATE_CORRECTION_STEPS = 20;
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String H263_MIME_TYPE = "video/3gpp";
    private static final String H264_MIME_TYPE = "video/avc";
    private static final int MAXIMUM_INITIAL_FPS = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_L_MS = 15000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 20000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "MediaCodecVideoEncoder";
    private static final int VIDEO_AVCLevel3 = 256;
    private static final int VIDEO_AVCProfileHigh = 8;
    private static final int VIDEO_ControlRateConstant = 2;
    private static final int VIDEO_ControlRateDisable = 0;
    private static final int VIDEO_ControlRateVariable = 1;
    private static int VIDEO_HEIGHT = 0;
    private static int VIDEO_WIDTH = 0;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors;
    @Nullable
    private static MediaCodecVideoEncoderErrorCallback errorCallback;
    @Nullable
    private static MediaCodecVideoEncoder runningInstance;
    @Nullable
    private static EglBase staticEglBase;
    private double bitrateAccumulator;
    private double bitrateAccumulatorMax;
    private int bitrateAdjustmentScaleExp;
    private double bitrateObservationTimeMs;
    private int colorFormat;
    @Nullable
    private ByteBuffer configData;
    @Nullable
    private GlRectDrawer drawer;
    @Nullable
    private EglBase14 eglBase;
    private long forcedKeyFrameMs;
    private int height;
    @Nullable
    private Surface inputSurface;
    private long lastKeyFrameMs;
    @Nullable
    private MediaCodec mediaCodec;
    @Nullable
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int profile;
    private int targetBitrateBps;
    private int targetFps;
    private VideoCodecType type;
    private int width;
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static final MediaCodecProperties qcomVp8HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp8HwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties intelVp8HwProperties = new MediaCodecProperties("OMX.Intel.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp9HwProperties = new MediaCodecProperties("OMX.qcom.", 24, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp9HwProperties = new MediaCodecProperties("OMX.Exynos.", 24, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties[] vp9HwList = {qcomVp9HwProperties, exynosVp9HwProperties};
    private static final MediaCodecProperties qcomH264HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties exynosH264HwProperties = new MediaCodecProperties("OMX.Exynos.", 21, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties FreescaH264HwProperties = new MediaCodecProperties("OMX.Freesca", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties hisiH264HwProperties = new MediaCodecProperties("OMX.hisi.", 21, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties myMTKH264HwProperties = new MediaCodecProperties("OMX.MTK.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties RockchipH264HwProperties = new MediaCodecProperties("OMX.rk.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties AllwinnerH264HwProperties = new MediaCodecProperties("OMX.allwinner.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties NvidiaH264HwProperties = new MediaCodecProperties("OMX.Nvidia.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties huaweiIMGH264HwProperties = new MediaCodecProperties("OMX.IMG.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties googH264HwProperties = new MediaCodecProperties("OMX.google.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties sprdH264HwProperties = new MediaCodecProperties("OMX.sprd.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties amlogicH264HwProperties = new MediaCodecProperties("OMX.amlogic.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties exynosH264HighProfileHwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties qcomH264HighProfileHwProperties = new MediaCodecProperties("OMX.qcom.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties hisiH264HighProfileHwProperties = new MediaCodecProperties("OMX.hisi.", 23, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties myMTKH264HighProfileHwProperties = new MediaCodecProperties("OMX.MTK.", 27, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties[] h264HighProfileHwList = {exynosH264HighProfileHwProperties, qcomH264HighProfileHwProperties, hisiH264HighProfileHwProperties, myMTKH264HighProfileHwProperties};
    private static final MediaCodecProperties GOOGLE_H263_HW_PROPERTIES = new MediaCodecProperties("OMX.google.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties MTK_H263_HW_PROPERTIES = new MediaCodecProperties("OMX.MTK.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties[] H263_HW_LIST = {GOOGLE_H263_HW_PROPERTIES, MTK_H263_HW_PROPERTIES};
    private static final String[] H264_HW_EXCEPTION_MODELS = {"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    private static int bitrateMode = 2;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int[] supportedColorList = {19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
    private static final int[] supportedSurfaceColorList = {2130708361};
    private String bitrateModePreset = PeerConnectionFactory.fieldTrialsFindFullName("BRTC-Encoder-BitrateMode");
    private BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum BitrateAdjustmentType {
        NO_ADJUSTMENT,
        FRAMERATE_ADJUSTMENT,
        DYNAMIC_ADJUSTMENT
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface MediaCodecVideoEncoderErrorCallback {
        void onMediaCodecVideoEncoderCriticalError(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeCreateEncoder(VideoCodecInfo videoCodecInfo, boolean z);

    private static native void nativeFillInputBuffer(long j, int i, ByteBuffer byteBuffer, int i2, ByteBuffer byteBuffer2, int i3, ByteBuffer byteBuffer3, int i4);

    public static VideoEncoderFactory createFactory() {
        return new DefaultVideoEncoderFactory(new HwEncoderFactory());
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class HwEncoderFactory implements VideoEncoderFactory {
        private final VideoCodecInfo[] supportedHardwareCodecs = getSupportedHardwareCodecs();

        @Override // com.webrtc.VideoEncoderFactory
        @CalledByNative
        public /* synthetic */ VideoEncoderFactory.VideoEncoderSelector getEncoderSelector() {
            return VideoEncoderFactory.CC.$default$getEncoderSelector(this);
        }

        @Override // com.webrtc.VideoEncoderFactory
        @CalledByNative
        public /* synthetic */ VideoCodecInfo[] getImplementations() {
            VideoCodecInfo[] supportedCodecs;
            supportedCodecs = getSupportedCodecs();
            return supportedCodecs;
        }

        HwEncoderFactory() {
        }

        private static boolean isSameCodec(VideoCodecInfo videoCodecInfo, VideoCodecInfo videoCodecInfo2) {
            if (videoCodecInfo.name.equalsIgnoreCase(videoCodecInfo2.name)) {
                if (videoCodecInfo.name.equalsIgnoreCase("H264")) {
                    return H264Utils.isSameH264Profile(videoCodecInfo.params, videoCodecInfo2.params);
                }
                return true;
            }
            return false;
        }

        private static boolean isCodecSupported(VideoCodecInfo[] videoCodecInfoArr, VideoCodecInfo videoCodecInfo) {
            for (VideoCodecInfo videoCodecInfo2 : videoCodecInfoArr) {
                if (isSameCodec(videoCodecInfo2, videoCodecInfo)) {
                    return true;
                }
            }
            return false;
        }

        private static VideoCodecInfo[] getSupportedHardwareCodecs() {
            ArrayList arrayList = new ArrayList();
            if (MediaCodecVideoEncoder.isVp8HwSupported()) {
                Logging.m5305d(MediaCodecVideoEncoder.TAG, "VP8 HW Encoder supported.");
                arrayList.add(new VideoCodecInfo("VP8", new HashMap()));
            }
            if (MediaCodecVideoEncoder.isVp9HwSupported()) {
                Logging.m5305d(MediaCodecVideoEncoder.TAG, "VP9 HW Encoder supported.");
                arrayList.add(new VideoCodecInfo("VP9", new HashMap()));
            }
            if (MediaCodecVideoDecoder.isH264HighProfileHwSupported()) {
                Logging.m5305d(MediaCodecVideoEncoder.TAG, "H.264 High Profile HW Encoder supported.");
                arrayList.add(H264Utils.DEFAULT_H264_HIGH_PROFILE_CODEC);
            }
            if (MediaCodecVideoEncoder.isH264HwSupported()) {
                Logging.m5305d(MediaCodecVideoEncoder.TAG, "H.264 HW Encoder supported.");
                arrayList.add(H264Utils.DEFAULT_H264_BASELINE_PROFILE_CODEC);
            }
            if (MediaCodecVideoEncoder.isH263HwSupported()) {
                Logging.m5305d(MediaCodecVideoEncoder.TAG, "H.263 HW Encoder supported.");
                arrayList.add(new VideoCodecInfo("H263", new HashMap()));
            }
            return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
        }

        @Override // com.webrtc.VideoEncoderFactory
        public VideoCodecInfo[] getSupportedCodecs() {
            return this.supportedHardwareCodecs;
        }

        @Override // com.webrtc.VideoEncoderFactory
        @Nullable
        public VideoEncoder createEncoder(final VideoCodecInfo videoCodecInfo) {
            if (!isCodecSupported(this.supportedHardwareCodecs, videoCodecInfo)) {
                Logging.m5305d(MediaCodecVideoEncoder.TAG, "No HW video encoder for codec " + videoCodecInfo.name);
                return null;
            }
            Logging.m5305d(MediaCodecVideoEncoder.TAG, "Create HW video encoder for " + videoCodecInfo.name);
            return new WrappedNativeVideoEncoder() { // from class: com.webrtc.MediaCodecVideoEncoder.HwEncoderFactory.1
                @Override // com.webrtc.WrappedNativeVideoEncoder, com.webrtc.VideoEncoder
                public boolean isHardwareEncoder() {
                    return true;
                }

                @Override // com.webrtc.WrappedNativeVideoEncoder, com.webrtc.VideoEncoder
                public long createNativeVideoEncoder() {
                    return MediaCodecVideoEncoder.nativeCreateEncoder(videoCodecInfo, MediaCodecVideoEncoder.staticEglBase instanceof EglBase14);
                }
            };
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum VideoCodecType {
        VIDEO_CODEC_UNKNOWN,
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264,
        VIDEO_CODEC_H263;

        @CalledByNative("VideoCodecType")
        static VideoCodecType fromNativeIndex(int i) {
            return values()[i];
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum H264Profile {
        CONSTRAINED_BASELINE(0),
        BASELINE(1),
        MAIN(2),
        CONSTRAINED_HIGH(3),
        HIGH(4);
        
        private final int value;

        H264Profile(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class MediaCodecProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecPrefix;
        public final int minSdk;

        MediaCodecProperties(String str, int i, BitrateAdjustmentType bitrateAdjustmentType) {
            this.codecPrefix = str;
            this.minSdk = i;
            this.bitrateAdjustmentType = bitrateAdjustmentType;
        }
    }

    public static void setEglContext(EglBase.Context context) {
        if (staticEglBase != null) {
            Logging.m5301w(TAG, "Egl context already set.");
            staticEglBase.release();
        }
        staticEglBase = EglBase.CC.create(context);
    }

    public static void disposeEglContext() {
        EglBase eglBase = staticEglBase;
        if (eglBase != null) {
            eglBase.release();
            staticEglBase = null;
        }
    }

    @Nullable
    static EglBase.Context getEglContext() {
        EglBase eglBase = staticEglBase;
        if (eglBase == null) {
            return null;
        }
        return eglBase.getEglBaseContext();
    }

    private static MediaCodecProperties[] vp8HwList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(qcomVp8HwProperties);
        arrayList.add(exynosVp8HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-IntelVP8").equals("Enabled")) {
            arrayList.add(intelVp8HwProperties);
        }
        return (MediaCodecProperties[]) arrayList.toArray(new MediaCodecProperties[arrayList.size()]);
    }

    private static final MediaCodecProperties[] h264HwList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(qcomH264HwProperties);
        arrayList.add(exynosH264HwProperties);
        arrayList.add(FreescaH264HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("BRTC.HisiH264HW").equals("Enabled")) {
            Logging.m5302v(TAG, "enable hisiH264HW");
            arrayList.add(hisiH264HwProperties);
        }
        arrayList.add(myMTKH264HwProperties);
        arrayList.add(RockchipH264HwProperties);
        arrayList.add(AllwinnerH264HwProperties);
        arrayList.add(NvidiaH264HwProperties);
        arrayList.add(huaweiIMGH264HwProperties);
        arrayList.add(googH264HwProperties);
        arrayList.add(amlogicH264HwProperties);
        arrayList.add(sprdH264HwProperties);
        PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MediaTekH264").equals("Enabled");
        return (MediaCodecProperties[]) arrayList.toArray(new MediaCodecProperties[arrayList.size()]);
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback mediaCodecVideoEncoderErrorCallback) {
        Logging.m5305d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoEncoderErrorCallback;
    }

    public static void disableVp8HwCodec() {
        Logging.m5301w(TAG, "VP8 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.m5301w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.m5301w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/avc");
    }

    public static boolean isVp8HwSupported() {
        return (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) || findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedColorList, 0, 0) == null) ? false : true;
    }

    @Nullable
    public static EncoderProperties vp8HwEncoderProperties() {
        if (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE)) {
            return null;
        }
        return findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedColorList, 0, 0);
    }

    public static boolean isVp9HwSupported() {
        return (hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) || findHwEncoder(VP9_MIME_TYPE, vp9HwList, supportedColorList, 0, 0) == null) ? false : true;
    }

    public static boolean isH264HwSupported() {
        return (hwEncoderDisabledTypes.contains("video/avc") || findHwEncoder("video/avc", h264HwList(), supportedColorList, 0, 0) == null) ? false : true;
    }

    public static boolean isH264HighProfileHwSupported() {
        return (hwEncoderDisabledTypes.contains("video/avc") || findHwEncoder("video/avc", h264HighProfileHwList, supportedColorList, 0, 0) == null) ? false : true;
    }

    public static boolean isH263HwSupported() {
        return (hwEncoderDisabledTypes.contains(H263_MIME_TYPE) || findHwEncoder(H263_MIME_TYPE, H263_HW_LIST, supportedColorList, 0, 0) == null) ? false : true;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) || findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedSurfaceColorList, 0, 0) == null) ? false : true;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) || findHwEncoder(VP9_MIME_TYPE, vp9HwList, supportedSurfaceColorList, 0, 0) == null) ? false : true;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains("video/avc") || findHwEncoder("video/avc", h264HwList(), supportedSurfaceColorList, 0, 0) == null) ? false : true;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class EncoderProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecName;
        public final int colorFormat;

        public EncoderProperties(String str, int i, BitrateAdjustmentType bitrateAdjustmentType) {
            this.codecName = str;
            this.colorFormat = i;
            this.bitrateAdjustmentType = bitrateAdjustmentType;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    private static EncoderProperties findHwEncoder(String str, MediaCodecProperties[] mediaCodecPropertiesArr, int[] iArr, int i, int i2) {
        MediaCodecInfo mediaCodecInfo;
        boolean z;
        String str2;
        boolean z2;
        int[] iArr2;
        int[] iArr3;
        EncoderProperties encoderProperties = null;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        if (str.equals("video/avc") && Arrays.asList(H264_HW_EXCEPTION_MODELS).contains(Build.MODEL)) {
            Logging.m5301w(TAG, "Model: " + Build.MODEL + " has black listed H.264 encoder.");
            return null;
        }
        int i3 = 0;
        while (i3 < MediaCodecList.getCodecCount()) {
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i3);
            } catch (IllegalArgumentException e) {
                Logging.m5303e(TAG, "Cannot retrieve encoder codec info", e);
                mediaCodecInfo = encoderProperties;
            }
            if (mediaCodecInfo != 0 && mediaCodecInfo.isEncoder()) {
                String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                int length = supportedTypes.length;
                int i4 = 0;
                while (true) {
                    z = true;
                    if (i4 >= length) {
                        str2 = encoderProperties;
                        z2 = false;
                        break;
                    } else if (supportedTypes[i4].equals(str)) {
                        String name = mediaCodecInfo.getName();
                        String str3 = name;
                        if (i * i2 < 25364) {
                            boolean startsWith = name.startsWith("OMX.hisi.");
                            str3 = name;
                            if (startsWith) {
                                str3 = encoderProperties;
                            }
                        }
                        if (str3 == null || !str3.startsWith("OMX.IMG.TOPAZ")) {
                            z2 = false;
                            str2 = str3;
                        } else {
                            z2 = true;
                            str2 = str3;
                        }
                    } else {
                        i4++;
                    }
                }
                if (str2 == 0) {
                    continue;
                } else {
                    Logging.m5302v(TAG, "Found candidate encoder " + str2);
                    BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
                    int length2 = mediaCodecPropertiesArr.length;
                    int i5 = 0;
                    while (true) {
                        if (i5 >= length2) {
                            z = false;
                            break;
                        }
                        MediaCodecProperties mediaCodecProperties = mediaCodecPropertiesArr[i5];
                        if (str2.startsWith(mediaCodecProperties.codecPrefix)) {
                            if (Build.VERSION.SDK_INT < mediaCodecProperties.minSdk) {
                                Logging.m5301w(TAG, "Codec " + str2 + " is disabled due to SDK version " + Build.VERSION.SDK_INT);
                            } else if (mediaCodecProperties.bitrateAdjustmentType != BitrateAdjustmentType.NO_ADJUSTMENT) {
                                bitrateAdjustmentType = mediaCodecProperties.bitrateAdjustmentType;
                                Logging.m5301w(TAG, "Codec " + str2 + " requires bitrate adjustment: " + bitrateAdjustmentType);
                            }
                        }
                        i5++;
                    }
                    if (z) {
                        try {
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                            for (int i6 : capabilitiesForType.colorFormats) {
                                Logging.m5302v(TAG, "   Color: 0x" + Integer.toHexString(i6));
                            }
                            for (int i7 : iArr) {
                                if (z2 && i7 == 19) {
                                    Logging.m5305d(TAG, "OMX.IMG.TOPAZ not support color formate 19");
                                } else {
                                    for (int i8 : capabilitiesForType.colorFormats) {
                                        if (i8 == i7) {
                                            Logging.m5305d(TAG, "Found target encoder for mime " + str + " : " + str2 + ". Color: 0x" + Integer.toHexString(i8) + ". Bitrate adjustment: " + bitrateAdjustmentType);
                                            return new EncoderProperties(str2, i8, bitrateAdjustmentType);
                                        }
                                    }
                                    continue;
                                }
                            }
                            continue;
                        } catch (IllegalArgumentException e2) {
                            Logging.m5303e(TAG, "Cannot retrieve encoder capabilities", e2);
                        }
                    } else {
                        continue;
                    }
                }
            }
            i3++;
            encoderProperties = null;
        }
        return encoderProperties;
    }

    @CalledByNative
    MediaCodecVideoEncoder() {
    }

    private void checkOnMediaCodecThread() {
        if (this.mediaCodecThread.getId() == Thread.currentThread().getId()) {
            return;
        }
        throw new RuntimeException("MediaCodecVideoEncoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
    }

    public static void printStackTrace() {
        Thread thread;
        MediaCodecVideoEncoder mediaCodecVideoEncoder = runningInstance;
        if (mediaCodecVideoEncoder == null || (thread = mediaCodecVideoEncoder.mediaCodecThread) == null) {
            return;
        }
        StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace.length > 0) {
            Logging.m5305d(TAG, "MediaCodecVideoEncoder stacks trace:");
            for (StackTraceElement stackTraceElement : stackTrace) {
                Logging.m5305d(TAG, stackTraceElement.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static MediaCodec createByCodecName(String str) {
        try {
            return MediaCodec.createByCodecName(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @CalledByNativeUnchecked
    boolean initEncode(VideoCodecType videoCodecType, int i, int i2, int i3, int i4, int i5, boolean z) {
        String str;
        EncoderProperties findHwEncoder;
        boolean z2;
        boolean z3;
        int parseInt;
        Logging.m5305d(TAG, "Java initEncode: " + videoCodecType + ". Profile: " + i + " : " + i2 + " x " + i3 + ". @ " + i4 + " kbps. Fps: " + i5 + ". Encode from texture : " + z);
        VIDEO_WIDTH = i2;
        VIDEO_HEIGHT = i3;
        this.profile = i;
        this.width = i2;
        this.height = i3;
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("Forgot to release()?");
        }
        int i6 = 100;
        if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP8) {
            str = VP8_MIME_TYPE;
            findHwEncoder = findHwEncoder(VP8_MIME_TYPE, vp8HwList(), z ? supportedSurfaceColorList : supportedColorList, i2, i3);
            z2 = false;
        } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP9) {
            str = VP9_MIME_TYPE;
            findHwEncoder = findHwEncoder(VP9_MIME_TYPE, vp9HwList, z ? supportedSurfaceColorList : supportedColorList, i2, i3);
            z2 = false;
        } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_H264) {
            EncoderProperties findHwEncoder2 = findHwEncoder("video/avc", h264HwList(), z ? supportedSurfaceColorList : supportedColorList, i2, i3);
            if (i == H264Profile.CONSTRAINED_HIGH.getValue()) {
                if (findHwEncoder("video/avc", h264HighProfileHwList, z ? supportedSurfaceColorList : supportedColorList, i2, i3) != null) {
                    Logging.m5305d(TAG, "High profile H.264 encoder supported.");
                    z3 = true;
                    z2 = z3;
                    str = "video/avc";
                    findHwEncoder = findHwEncoder2;
                    i6 = 20;
                } else {
                    Logging.m5305d(TAG, "High profile H.264 encoder requested, but not supported. Use baseline.");
                }
            }
            z3 = false;
            z2 = z3;
            str = "video/avc";
            findHwEncoder = findHwEncoder2;
            i6 = 20;
        } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_H263) {
            str = H263_MIME_TYPE;
            findHwEncoder = findHwEncoder(H263_MIME_TYPE, H263_HW_LIST, z ? supportedSurfaceColorList : supportedColorList, i2, i3);
            i6 = 1;
            z2 = false;
        } else {
            throw new RuntimeException("initEncode: Non-supported codec " + videoCodecType);
        }
        if (findHwEncoder == null) {
            throw new RuntimeException("Can not find HW encoder for " + videoCodecType);
        }
        runningInstance = this;
        this.colorFormat = findHwEncoder.colorFormat;
        this.bitrateAdjustmentType = findHwEncoder.bitrateAdjustmentType;
        int min = this.bitrateAdjustmentType != BitrateAdjustmentType.FRAMERATE_ADJUSTMENT ? Math.min(i5, 30) : 30;
        this.forcedKeyFrameMs = 0L;
        this.lastKeyFrameMs = -1L;
        if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP8 && findHwEncoder.codecName.startsWith(qcomVp8HwProperties.codecPrefix)) {
            if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) {
                this.forcedKeyFrameMs = 15000L;
            } else if (Build.VERSION.SDK_INT == 23) {
                this.forcedKeyFrameMs = 20000L;
            } else if (Build.VERSION.SDK_INT > 23) {
                this.forcedKeyFrameMs = 15000L;
            }
        }
        if (!TextUtils.isEmpty(this.bitrateModePreset) && ((parseInt = Integer.parseInt(this.bitrateModePreset)) == 0 || parseInt == 1 || parseInt == 2)) {
            bitrateMode = parseInt;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Color format: ");
        sb.append(this.colorFormat);
        sb.append(". Bitrate adjustment: ");
        sb.append(this.bitrateAdjustmentType);
        sb.append(". Key frame interval: ");
        int i7 = min;
        sb.append(this.forcedKeyFrameMs);
        sb.append(" . Initial fps: ");
        sb.append(i7);
        sb.append(". BitrateMode: ");
        sb.append(bitrateMode);
        Logging.m5305d(TAG, sb.toString());
        this.targetBitrateBps = i4 * 1000;
        this.targetFps = i7;
        this.bitrateAccumulatorMax = this.targetBitrateBps / 8.0d;
        this.bitrateAccumulator = 0.0d;
        this.bitrateObservationTimeMs = 0.0d;
        this.bitrateAdjustmentScaleExp = 0;
        this.mediaCodecThread = Thread.currentThread();
        try {
            MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i2, i3);
            createVideoFormat.setInteger("bitrate", this.targetBitrateBps);
            createVideoFormat.setInteger("bitrate-mode", bitrateMode);
            createVideoFormat.setInteger("color-format", findHwEncoder.colorFormat);
            createVideoFormat.setInteger("frame-rate", this.targetFps);
            createVideoFormat.setInteger("i-frame-interval", i6);
            if (z2) {
                createVideoFormat.setInteger("profile", 8);
                createVideoFormat.setInteger("level", 256);
            }
            Logging.m5305d(TAG, "  Format: " + createVideoFormat);
            this.mediaCodec = createByCodecName(findHwEncoder.codecName);
            this.type = videoCodecType;
            if (this.mediaCodec == null) {
                Logging.m5304e(TAG, "Can not create media encoder");
                release();
                return false;
            }
            this.mediaCodec.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            if (z) {
                this.eglBase = EglBase.CC.createEgl14((EglBase14.Context) getEglContext(), EglBase.CONFIG_RECORDABLE);
                this.inputSurface = this.mediaCodec.createInputSurface();
                this.eglBase.createSurface(this.inputSurface);
                this.drawer = new GlRectDrawer();
            }
            this.mediaCodec.start();
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            Logging.m5305d(TAG, "Output buffers: " + this.outputBuffers.length);
            return true;
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "initEncode failed", e);
            release();
            return false;
        }
    }

    @CalledByNativeUnchecked
    ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        Logging.m5305d(TAG, "Input buffers: " + inputBuffers.length);
        return inputBuffers;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void checkKeyFrameRequired(boolean r7, long r8) {
        /*
            r6 = this;
            r0 = 500(0x1f4, double:2.47E-321)
            long r8 = r8 + r0
            r0 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 / r0
            long r0 = r6.lastKeyFrameMs
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L10
            r6.lastKeyFrameMs = r8
        L10:
            r0 = 0
            if (r7 != 0) goto L22
            long r4 = r6.forcedKeyFrameMs
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 <= 0) goto L22
            long r1 = r6.lastKeyFrameMs
            long r1 = r1 + r4
            int r1 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r1 <= 0) goto L22
            r1 = 1
            goto L23
        L22:
            r1 = r0
        L23:
            if (r7 != 0) goto L27
            if (r1 == 0) goto L49
        L27:
            if (r7 == 0) goto L31
            java.lang.String r7 = "MediaCodecVideoEncoder"
            java.lang.String r1 = "Sync frame request"
            com.webrtc.Logging.m5305d(r7, r1)
            goto L38
        L31:
            java.lang.String r7 = "MediaCodecVideoEncoder"
            java.lang.String r1 = "Sync frame forced"
            com.webrtc.Logging.m5305d(r7, r1)
        L38:
            android.os.Bundle r7 = new android.os.Bundle
            r7.<init>()
            java.lang.String r1 = "request-sync"
            r7.putInt(r1, r0)
            android.media.MediaCodec r0 = r6.mediaCodec
            r0.setParameters(r7)
            r6.lastKeyFrameMs = r8
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webrtc.MediaCodecVideoEncoder.checkKeyFrameRequired(boolean, long):void");
    }

    @CalledByNativeUnchecked
    boolean encodeBuffer(boolean z, int i, int i2, long j) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j);
            this.mediaCodec.queueInputBuffer(i, 0, i2, j, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "encodeBuffer failed", e);
            return false;
        }
    }

    @CalledByNativeUnchecked
    boolean encodeFrame(long j, boolean z, VideoFrame videoFrame, int i, long j2) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j2);
            VideoFrame.Buffer buffer = videoFrame.getBuffer();
            if (buffer instanceof VideoFrame.TextureBuffer) {
                this.eglBase.makeCurrent();
                GLES20.glClear(16384);
                VideoFrameDrawer.drawTexture(this.drawer, (VideoFrame.TextureBuffer) buffer, new Matrix(), this.width, this.height, 0, 0, this.width, this.height);
                this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(j2));
            } else {
                VideoFrame.I420Buffer i420 = buffer.toI420();
                int i2 = (this.height + 1) / 2;
                ByteBuffer dataY = i420.getDataY();
                ByteBuffer dataU = i420.getDataU();
                ByteBuffer dataV = i420.getDataV();
                int strideY = i420.getStrideY();
                int strideU = i420.getStrideU();
                int strideV = i420.getStrideV();
                if (dataY.capacity() < this.height * strideY) {
                    throw new RuntimeException("Y-plane buffer size too small.");
                }
                if (dataU.capacity() < strideU * i2) {
                    throw new RuntimeException("U-plane buffer size too small.");
                }
                if (dataV.capacity() < i2 * strideV) {
                    throw new RuntimeException("V-plane buffer size too small.");
                }
                nativeFillInputBuffer(j, i, dataY, strideY, dataU, strideU, dataV, strideV);
                i420.release();
                this.mediaCodec.queueInputBuffer(i, 0, ((this.width * this.height) * 3) / 2, j2, 0);
            }
            return true;
        } catch (RuntimeException e) {
            Logging.m5303e(TAG, "encodeFrame failed", e);
            return false;
        }
    }

    @CalledByNativeUnchecked
    void release() {
        Logging.m5305d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final C1CaughtException c1CaughtException = new C1CaughtException();
        boolean z = false;
        if (this.mediaCodec != null) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable() { // from class: com.webrtc.MediaCodecVideoEncoder.1
                @Override // java.lang.Runnable
                public void run() {
                    Logging.m5305d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread");
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.stop();
                    } catch (Exception e) {
                        Logging.m5303e(MediaCodecVideoEncoder.TAG, "Media encoder stop failed", e);
                    }
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.release();
                    } catch (Exception e2) {
                        Logging.m5303e(MediaCodecVideoEncoder.TAG, "Media encoder release failed", e2);
                        c1CaughtException.f21263e = e2;
                    }
                    Logging.m5305d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread done");
                    countDownLatch.countDown();
                }
            }).start();
            if (!ThreadUtils.awaitUninterruptibly(countDownLatch, 5000L)) {
                Logging.m5304e(TAG, "Media encoder release timeout");
                z = true;
            }
            this.mediaCodec = null;
        }
        this.mediaCodecThread = null;
        GlRectDrawer glRectDrawer = this.drawer;
        if (glRectDrawer != null) {
            glRectDrawer.release();
            this.drawer = null;
        }
        EglBase14 eglBase14 = this.eglBase;
        if (eglBase14 != null) {
            eglBase14.release();
            this.eglBase = null;
        }
        Surface surface = this.inputSurface;
        if (surface != null) {
            surface.release();
            this.inputSurface = null;
        }
        runningInstance = null;
        if (z) {
            codecErrors++;
            if (errorCallback != null) {
                Logging.m5304e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
            throw new RuntimeException("Media encoder release timeout.");
        } else if (c1CaughtException.f21263e != null) {
            RuntimeException runtimeException = new RuntimeException(c1CaughtException.f21263e);
            runtimeException.setStackTrace(ThreadUtils.concatStackTraces(c1CaughtException.f21263e.getStackTrace(), runtimeException.getStackTrace()));
            throw runtimeException;
        } else {
            Logging.m5305d(TAG, "Java releaseEncoder done");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.webrtc.MediaCodecVideoEncoder$1CaughtException  reason: invalid class name */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C1CaughtException {

        /* renamed from: e */
        Exception f21263e;

        C1CaughtException() {
        }
    }

    @CalledByNativeUnchecked
    private boolean setRates(int i, int i2) {
        int i3;
        checkOnMediaCodecThread();
        int i4 = i * 1000;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            double d = i4;
            this.bitrateAccumulatorMax = d / 8.0d;
            int i5 = this.targetBitrateBps;
            if (i5 > 0 && i4 < i5) {
                this.bitrateAccumulator = (this.bitrateAccumulator * d) / i5;
            }
        }
        this.targetBitrateBps = i4;
        this.targetFps = i2;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT && (i3 = this.targetFps) > 0) {
            i4 = (this.targetBitrateBps * 30) / i3;
            Logging.m5302v(TAG, "setRates: " + i + " -> " + (i4 / 1000) + " kbps. Fps: " + this.targetFps);
        } else if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            Logging.m5302v(TAG, "setRates: " + i + " kbps. Fps: " + this.targetFps + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
            int i6 = this.bitrateAdjustmentScaleExp;
            if (i6 != 0) {
                i4 = (int) (i4 * getBitrateScale(i6));
            }
        } else {
            Logging.m5302v(TAG, "setRates: " + i + " kbps. Fps: " + this.targetFps);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("video-bitrate", i4);
            this.mediaCodec.setParameters(bundle);
            return true;
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "setRates failed", e);
            return false;
        }
    }

    @CalledByNativeUnchecked
    int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0L);
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long presentationTimestampUs;

        public OutputBufferInfo(int i, ByteBuffer byteBuffer, boolean z, long j) {
            this.index = i;
            this.buffer = byteBuffer;
            this.isKeyFrame = z;
            this.presentationTimestampUs = j;
        }

        @CalledByNative("OutputBufferInfo")
        int getIndex() {
            return this.index;
        }

        @CalledByNative("OutputBufferInfo")
        ByteBuffer getBuffer() {
            return this.buffer;
        }

        @CalledByNative("OutputBufferInfo")
        boolean isKeyFrame() {
            return this.isKeyFrame;
        }

        @CalledByNative("OutputBufferInfo")
        long getPresentationTimestampUs() {
            return this.presentationTimestampUs;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00ad A[Catch: IllegalStateException -> 0x0176, TryCatch #0 {IllegalStateException -> 0x0176, blocks: (B:3:0x0003, B:5:0x0014, B:10:0x001f, B:11:0x006b, B:13:0x0071, B:15:0x0075, B:16:0x0098, B:19:0x00ad, B:24:0x00d0, B:26:0x00d9, B:28:0x00df, B:30:0x0135, B:34:0x0145, B:38:0x0155, B:44:0x015f, B:45:0x0175), top: B:49:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0142  */
    @android.support.annotation.Nullable
    @com.webrtc.CalledByNativeUnchecked
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    com.webrtc.MediaCodecVideoEncoder.OutputBufferInfo dequeueOutputBuffer() {
        /*
            Method dump skipped, instructions count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webrtc.MediaCodecVideoEncoder.dequeueOutputBuffer():com.webrtc.MediaCodecVideoEncoder$OutputBufferInfo");
    }

    private double getBitrateScale(int i) {
        return Math.pow(BITRATE_CORRECTION_MAX_SCALE, i / 20.0d);
    }

    private void reportEncodedFrame(int i) {
        if (this.targetFps == 0 || this.bitrateAdjustmentType != BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            return;
        }
        int i2 = this.targetFps;
        this.bitrateAccumulator += i - (this.targetBitrateBps / (i2 * 8.0d));
        this.bitrateObservationTimeMs += 1000.0d / i2;
        double d = this.bitrateAccumulatorMax * BITRATE_CORRECTION_SEC;
        this.bitrateAccumulator = Math.min(this.bitrateAccumulator, d);
        this.bitrateAccumulator = Math.max(this.bitrateAccumulator, -d);
        if (this.bitrateObservationTimeMs > 3000.0d) {
            Logging.m5305d(TAG, "Acc: " + ((int) this.bitrateAccumulator) + ". Max: " + ((int) this.bitrateAccumulatorMax) + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
            double d2 = this.bitrateAccumulator;
            double d3 = this.bitrateAccumulatorMax;
            boolean z = true;
            if (d2 > d3) {
                this.bitrateAdjustmentScaleExp -= (int) ((d2 / d3) + 0.5d);
                this.bitrateAccumulator = d3;
            } else if (d2 < (-d3)) {
                this.bitrateAdjustmentScaleExp += (int) (((-d2) / d3) + 0.5d);
                this.bitrateAccumulator = -d3;
            } else {
                z = false;
            }
            if (z) {
                this.bitrateAdjustmentScaleExp = Math.min(this.bitrateAdjustmentScaleExp, 20);
                this.bitrateAdjustmentScaleExp = Math.max(this.bitrateAdjustmentScaleExp, -20);
                Logging.m5305d(TAG, "Adjusting bitrate scale to " + this.bitrateAdjustmentScaleExp + ". Value: " + getBitrateScale(this.bitrateAdjustmentScaleExp));
                setRates(this.targetBitrateBps / 1000, this.targetFps);
            }
            this.bitrateObservationTimeMs = 0.0d;
        }
    }

    @CalledByNativeUnchecked
    boolean releaseOutputBuffer(int i) {
        checkOnMediaCodecThread();
        try {
            this.mediaCodec.releaseOutputBuffer(i, false);
            return true;
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "releaseOutputBuffer failed", e);
            return false;
        }
    }

    @CalledByNative
    int getColorFormat() {
        return this.colorFormat;
    }

    @CalledByNative
    static boolean isTextureBuffer(VideoFrame.Buffer buffer) {
        return buffer instanceof VideoFrame.TextureBuffer;
    }
}

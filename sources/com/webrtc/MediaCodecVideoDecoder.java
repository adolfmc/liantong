package com.webrtc;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.Surface;
import com.webrtc.EglBase;
import com.webrtc.VideoDecoderFactory;
import com.webrtc.VideoFrame;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class MediaCodecVideoDecoder {
    private static final int DEQUEUE_INPUT_TIMEOUT = 500000;
    private static final String FORMAT_KEY_CROP_BOTTOM = "crop-bottom";
    private static final String FORMAT_KEY_CROP_LEFT = "crop-left";
    private static final String FORMAT_KEY_CROP_RIGHT = "crop-right";
    private static final String FORMAT_KEY_CROP_TOP = "crop-top";
    private static final String FORMAT_KEY_SLICE_HEIGHT = "slice-height";
    private static final String FORMAT_KEY_STRIDE = "stride";
    private static final String H263_MIME_TYPE = "video/3gpp";
    private static final String H264_MIME_TYPE = "video/avc";
    private static final long MAX_DECODE_TIME_MS = 200;
    private static final int MAX_QUEUED_OUTPUTBUFFERS = 3;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "MediaCodecVideoDecoder";
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors = 0;
    @Nullable
    private static EglBase eglBase = null;
    @Nullable
    private static MediaCodecVideoDecoderErrorCallback errorCallback = null;
    @Nullable
    private static MediaCodecVideoDecoder runningInstance = null;
    private static final String supportedHisiH264HighProfileHwCodecPrefix = "OMX.hisi.";
    private static final String supportedMediaTekH264HighProfileHwCodecPrefix = "OMX.MTK.";
    @Nullable
    private static MediaCodecVideoDecoderObserver videoDecoderObserver;
    private int colorFormat;
    private final Queue<TimeStamps> decodeStartTimeMs = new ArrayDeque();
    private final Queue<DecodedOutputBuffer> dequeuedSurfaceOutputBuffers = new ArrayDeque();
    private int droppedFrames;
    private boolean hasDecodedFirstFrame;
    private int height;
    private ByteBuffer[] inputBuffers;
    @Nullable
    private MediaCodec mediaCodec;
    @Nullable
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int sliceHeight;
    private int stride;
    @Nullable
    private Surface surface;
    @Nullable
    private TextureListener textureListener;
    private int width;
    private static Set<String> hwDecoderDisabledTypes = new HashSet();
    private static final String supportedQcomH264HighProfileHwCodecPrefix = "OMX.qcom.";
    private static final String supportedExynosH264HighProfileHwCodecPrefix = "OMX.Exynos.";
    private static final String[] supportedVp9HwCodecPrefixes = {supportedQcomH264HighProfileHwCodecPrefix, supportedExynosH264HighProfileHwCodecPrefix};
    private static final String[] SUPPORTED_H263_HW_CODEC_PREFIXES = {"OMX.google"};
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka = 2141391873;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka = 2141391874;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka = 2141391875;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final List<Integer> supportedColorList = Arrays.asList(19, 21, 2141391872, Integer.valueOf((int) COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka), Integer.valueOf((int) COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka), Integer.valueOf((int) COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka), Integer.valueOf((int) COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m));

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface MediaCodecVideoDecoderErrorCallback {
        void onMediaCodecVideoDecoderCriticalError(int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface MediaCodecVideoDecoderObserver {
        void onSEIRecv(ByteBuffer byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeCreateDecoder(String str, boolean z);

    public static VideoDecoderFactory createFactory() {
        return new DefaultVideoDecoderFactory(new HwDecoderFactory());
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class HwDecoderFactory implements VideoDecoderFactory {
        private final VideoCodecInfo[] supportedHardwareCodecs = getSupportedHardwareCodecs();

        @Override // com.webrtc.VideoDecoderFactory
        @Nullable
        @Deprecated
        public /* synthetic */ VideoDecoder createDecoder(String str) {
            return VideoDecoderFactory.CC.$default$createDecoder(this, str);
        }

        HwDecoderFactory() {
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
            if (MediaCodecVideoDecoder.isVp8HwSupported()) {
                Logging.m5305d(MediaCodecVideoDecoder.TAG, "VP8 HW Decoder supported.");
                arrayList.add(new VideoCodecInfo("VP8", new HashMap()));
            }
            if (MediaCodecVideoDecoder.isVp9HwSupported()) {
                Logging.m5305d(MediaCodecVideoDecoder.TAG, "VP9 HW Decoder supported.");
                arrayList.add(new VideoCodecInfo("VP9", new HashMap()));
            }
            if (MediaCodecVideoDecoder.isH264HighProfileHwSupported()) {
                Logging.m5305d(MediaCodecVideoDecoder.TAG, "H.264 High Profile HW Decoder supported.");
                arrayList.add(H264Utils.DEFAULT_H264_HIGH_PROFILE_CODEC);
            }
            if (MediaCodecVideoDecoder.isH264HwSupported()) {
                Logging.m5305d(MediaCodecVideoDecoder.TAG, "H.264 HW Decoder supported.");
                arrayList.add(H264Utils.DEFAULT_H264_BASELINE_PROFILE_CODEC);
            }
            if (MediaCodecVideoDecoder.isH263HwSupported()) {
                Logging.m5305d(MediaCodecVideoDecoder.TAG, "H.263 HW Encoder supported.");
                arrayList.add(new VideoCodecInfo("H263", new HashMap()));
            }
            return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
        }

        @Override // com.webrtc.VideoDecoderFactory
        public VideoCodecInfo[] getSupportedCodecs() {
            return this.supportedHardwareCodecs;
        }

        @Override // com.webrtc.VideoDecoderFactory
        @Nullable
        public VideoDecoder createDecoder(final VideoCodecInfo videoCodecInfo) {
            if (!isCodecSupported(this.supportedHardwareCodecs, videoCodecInfo)) {
                Logging.m5305d(MediaCodecVideoDecoder.TAG, "No HW video decoder for codec " + videoCodecInfo.name);
                return null;
            }
            Logging.m5305d(MediaCodecVideoDecoder.TAG, "Create HW video decoder for " + videoCodecInfo.name);
            return new WrappedNativeVideoDecoder() { // from class: com.webrtc.MediaCodecVideoDecoder.HwDecoderFactory.1
                @Override // com.webrtc.WrappedNativeVideoDecoder, com.webrtc.VideoDecoder
                public long createNativeVideoDecoder() {
                    return MediaCodecVideoDecoder.nativeCreateDecoder(videoCodecInfo.name, MediaCodecVideoDecoder.useSurface());
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

    private static final String[] supportedVp8HwCodecPrefixes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(supportedQcomH264HighProfileHwCodecPrefix);
        arrayList.add("OMX.Nvidia.");
        arrayList.add(supportedExynosH264HighProfileHwCodecPrefix);
        arrayList.add("OMX.Intel.");
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MediaTekVP8").equals("Enabled") && Build.VERSION.SDK_INT >= 24) {
            arrayList.add(supportedMediaTekH264HighProfileHwCodecPrefix);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static final String[] supportedH264HwCodecPrefixes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(supportedQcomH264HighProfileHwCodecPrefix);
        arrayList.add("OMX.Intel.");
        if (PeerConnectionFactory.fieldTrialsFindFullName("BRTC.HisiH264HW").equals("Enabled")) {
            arrayList.add(supportedHisiH264HighProfileHwCodecPrefix);
        }
        arrayList.add(supportedExynosH264HighProfileHwCodecPrefix);
        if (PeerConnectionFactory.fieldTrialsFindFullName("BRTC.MTK.H264.Decode").equals("Enabled")) {
            arrayList.add(supportedMediaTekH264HighProfileHwCodecPrefix);
        }
        arrayList.add("OMX.rk.");
        arrayList.add("OMX.allwinner.");
        arrayList.add("OMX.Nvidia.");
        arrayList.add("OMX.IMG.");
        arrayList.add("OMX.amlogic.");
        arrayList.add("OMX.google.");
        arrayList.add("OMX.sprd.");
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static void setEglContext(EglBase.Context context) {
        if (eglBase != null) {
            Logging.m5301w(TAG, "Egl context already set.");
            eglBase.release();
        }
        eglBase = EglBase.CC.create(context);
    }

    public static void disposeEglContext() {
        EglBase eglBase2 = eglBase;
        if (eglBase2 != null) {
            eglBase2.release();
            eglBase = null;
        }
    }

    static boolean useSurface() {
        return eglBase != null;
    }

    public static void setErrorCallback(MediaCodecVideoDecoderErrorCallback mediaCodecVideoDecoderErrorCallback) {
        Logging.m5305d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoDecoderErrorCallback;
    }

    public static void setVideoDecoderObserver(MediaCodecVideoDecoderObserver mediaCodecVideoDecoderObserver) {
        Logging.m5305d(TAG, "Set video decoder observer");
        videoDecoderObserver = mediaCodecVideoDecoderObserver;
    }

    public static void disableVp8HwCodec() {
        Logging.m5301w(TAG, "VP8 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.m5301w(TAG, "VP9 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.m5301w(TAG, "H.264 decoding is disabled by application.");
        hwDecoderDisabledTypes.add("video/avc");
    }

    public static void disableH263HwCodec() {
        Logging.m5301w(TAG, "H.263 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(H263_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return (hwDecoderDisabledTypes.contains(VP8_MIME_TYPE) || findDecoder(VP8_MIME_TYPE, supportedVp8HwCodecPrefixes()) == null) ? false : true;
    }

    public static boolean isVp9HwSupported() {
        return (hwDecoderDisabledTypes.contains(VP9_MIME_TYPE) || findDecoder(VP9_MIME_TYPE, supportedVp9HwCodecPrefixes) == null) ? false : true;
    }

    public static boolean isH264HwSupported() {
        return (hwDecoderDisabledTypes.contains("video/avc") || findDecoder("video/avc", supportedH264HwCodecPrefixes()) == null) ? false : true;
    }

    public static boolean isH264HighProfileHwSupported() {
        if (hwDecoderDisabledTypes.contains("video/avc")) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 21 || findDecoder("video/avc", new String[]{supportedQcomH264HighProfileHwCodecPrefix}) == null) {
            if (Build.VERSION.SDK_INT < 23 || findDecoder("video/avc", new String[]{supportedExynosH264HighProfileHwCodecPrefix}) == null) {
                if (!PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MediaTekH264").equals("Enabled") || Build.VERSION.SDK_INT < 27 || findDecoder("video/avc", new String[]{supportedMediaTekH264HighProfileHwCodecPrefix}) == null) {
                    return Build.VERSION.SDK_INT >= 23 && findDecoder("video/avc", new String[]{supportedHisiH264HighProfileHwCodecPrefix}) != null;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    @CalledByNativeUnchecked
    public static boolean isH263HwSupported() {
        return (hwDecoderDisabledTypes.contains(H263_MIME_TYPE) || findDecoder(H263_MIME_TYPE, SUPPORTED_H263_HW_CODEC_PREFIXES) == null) ? false : true;
    }

    public static void printStackTrace() {
        Thread thread;
        MediaCodecVideoDecoder mediaCodecVideoDecoder = runningInstance;
        if (mediaCodecVideoDecoder == null || (thread = mediaCodecVideoDecoder.mediaCodecThread) == null) {
            return;
        }
        StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace.length > 0) {
            Logging.m5305d(TAG, "MediaCodecVideoDecoder stacks trace:");
            for (StackTraceElement stackTraceElement : stackTrace) {
                Logging.m5305d(TAG, stackTraceElement.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class DecoderProperties {
        public final String codecName;
        public final int colorFormat;

        public DecoderProperties(String str, int i) {
            this.codecName = str;
            this.colorFormat = i;
        }
    }

    @Nullable
    private static DecoderProperties findDecoder(String str, String[] strArr) {
        MediaCodecInfo mediaCodecInfo;
        String str2;
        boolean z;
        int[] iArr;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        Logging.m5305d(TAG, "Trying to find HW decoder for mime " + str);
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.m5303e(TAG, "Cannot retrieve decoder codec info", e);
                mediaCodecInfo = null;
            }
            if (mediaCodecInfo != null && !mediaCodecInfo.isEncoder()) {
                String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                int length = supportedTypes.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        str2 = null;
                        break;
                    } else if (supportedTypes[i2].equals(str)) {
                        str2 = mediaCodecInfo.getName();
                        break;
                    } else {
                        i2++;
                    }
                }
                if (str2 == null) {
                    continue;
                } else {
                    Logging.m5305d(TAG, "Found candidate decoder " + str2);
                    int length2 = strArr.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length2) {
                            z = false;
                            break;
                        } else if (str2.startsWith(strArr[i3])) {
                            z = true;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (z) {
                        try {
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                            for (int i4 : capabilitiesForType.colorFormats) {
                                Logging.m5302v(TAG, "   Color: 0x" + Integer.toHexString(i4));
                            }
                            for (Integer num : supportedColorList) {
                                int intValue = num.intValue();
                                int[] iArr2 = capabilitiesForType.colorFormats;
                                int length3 = iArr2.length;
                                for (int i5 = 0; i5 < length3; i5++) {
                                    int i6 = iArr2[i5];
                                    if (i6 == intValue) {
                                        Logging.m5305d(TAG, "Found target decoder " + str2 + ". Color: 0x" + Integer.toHexString(i6));
                                        if (Build.MODEL.contains("NV2001") || ((Build.MODEL.contains("Y13") || Build.MODEL.contains("R7")) && Build.MANUFACTURER.contains("rockchip"))) {
                                            i6 = 21;
                                            Logging.m5305d(TAG, "On Duer NV2001 .  enforce Color: 0x" + Integer.toHexString(21));
                                        }
                                        return new DecoderProperties(str2, i6);
                                    }
                                }
                            }
                            continue;
                        } catch (IllegalArgumentException e2) {
                            Logging.m5303e(TAG, "Cannot retrieve decoder capabilities", e2);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        Logging.m5305d(TAG, "No HW decoder found for mime " + str);
        return null;
    }

    @CalledByNative
    MediaCodecVideoDecoder() {
    }

    private void checkOnMediaCodecThread() throws IllegalStateException {
        if (this.mediaCodecThread.getId() == Thread.currentThread().getId()) {
            return;
        }
        throw new IllegalStateException("MediaCodecVideoDecoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
    }

    @CalledByNativeUnchecked
    private boolean initDecode(VideoCodecType videoCodecType, int i, int i2) {
        String str;
        String[] strArr;
        SurfaceTextureHelper create;
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("initDecode: Forgot to release()?");
        }
        if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP8) {
            str = VP8_MIME_TYPE;
            strArr = supportedVp8HwCodecPrefixes();
        } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP9) {
            str = VP9_MIME_TYPE;
            strArr = supportedVp9HwCodecPrefixes;
        } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_H264) {
            str = "video/avc";
            strArr = supportedH264HwCodecPrefixes();
        } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_H263) {
            str = H263_MIME_TYPE;
            strArr = SUPPORTED_H263_HW_CODEC_PREFIXES;
        } else {
            throw new RuntimeException("initDecode: Non-supported codec " + videoCodecType);
        }
        DecoderProperties findDecoder = findDecoder(str, strArr);
        if (findDecoder == null) {
            throw new RuntimeException("Cannot find HW decoder for " + videoCodecType);
        }
        Logging.m5305d(TAG, "Java initDecode: " + videoCodecType + " : " + i + " x " + i2 + ". Color: 0x" + Integer.toHexString(findDecoder.colorFormat) + ". Use Surface: " + useSurface());
        runningInstance = this;
        this.mediaCodecThread = Thread.currentThread();
        try {
            this.width = i;
            this.height = i2;
            this.stride = i;
            this.sliceHeight = i2;
            if (useSurface() && (create = SurfaceTextureHelper.create("Decoder SurfaceTextureHelper", eglBase.getEglBaseContext())) != null) {
                this.textureListener = new TextureListener(create);
                this.textureListener.setSize(i, i2);
                this.surface = new Surface(create.getSurfaceTexture());
            }
            MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i, i2);
            if (!useSurface()) {
                createVideoFormat.setInteger("color-format", findDecoder.colorFormat);
            }
            Logging.m5305d(TAG, "  Format: " + createVideoFormat);
            this.mediaCodec = MediaCodecVideoEncoder.createByCodecName(findDecoder.codecName);
            if (this.mediaCodec == null) {
                Logging.m5304e(TAG, "Can not create media decoder");
                return false;
            }
            this.mediaCodec.configure(createVideoFormat, this.surface, (MediaCrypto) null, 0);
            this.mediaCodec.start();
            this.colorFormat = findDecoder.colorFormat;
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            this.inputBuffers = this.mediaCodec.getInputBuffers();
            this.decodeStartTimeMs.clear();
            this.hasDecodedFirstFrame = false;
            this.dequeuedSurfaceOutputBuffers.clear();
            this.droppedFrames = 0;
            Logging.m5305d(TAG, "Input buffers: " + this.inputBuffers.length + ". Output buffers: " + this.outputBuffers.length);
            return true;
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "initDecode failed", e);
            return false;
        }
    }

    @CalledByNativeUnchecked
    private void reset(int i, int i2) {
        if (this.mediaCodecThread == null || this.mediaCodec == null) {
            throw new RuntimeException("Incorrect reset call for non-initialized decoder.");
        }
        Logging.m5305d(TAG, "Java reset: " + i + " x " + i2);
        this.mediaCodec.flush();
        this.width = i;
        this.height = i2;
        TextureListener textureListener = this.textureListener;
        if (textureListener != null) {
            textureListener.setSize(i, i2);
        }
        this.decodeStartTimeMs.clear();
        this.dequeuedSurfaceOutputBuffers.clear();
        this.hasDecodedFirstFrame = false;
        this.droppedFrames = 0;
    }

    @CalledByNativeUnchecked
    private void release() {
        Logging.m5305d(TAG, "Java releaseDecoder. Total number of dropped frames: " + this.droppedFrames);
        checkOnMediaCodecThread();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() { // from class: com.webrtc.MediaCodecVideoDecoder.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Logging.m5305d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread");
                    MediaCodecVideoDecoder.this.mediaCodec.stop();
                    MediaCodecVideoDecoder.this.mediaCodec.release();
                    Logging.m5305d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread done");
                } catch (Exception e) {
                    Logging.m5303e(MediaCodecVideoDecoder.TAG, "Media decoder release failed", e);
                }
                countDownLatch.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(countDownLatch, 5000L)) {
            Logging.m5304e(TAG, "Media decoder release timeout");
            codecErrors++;
            if (errorCallback != null) {
                Logging.m5304e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoDecoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        runningInstance = null;
        if (useSurface()) {
            this.surface.release();
            this.surface = null;
            this.textureListener.release();
        }
        Logging.m5305d(TAG, "Java releaseDecoder done");
    }

    @CalledByNativeUnchecked
    private void foundSei(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.capacity());
            byteBuffer.rewind();
            allocate.put(byteBuffer);
            byteBuffer.rewind();
            allocate.flip();
            MediaCodecVideoDecoderObserver mediaCodecVideoDecoderObserver = videoDecoderObserver;
            if (mediaCodecVideoDecoderObserver != null) {
                mediaCodecVideoDecoderObserver.onSEIRecv(allocate);
            }
        }
    }

    @CalledByNativeUnchecked
    private int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(500000L);
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    @CalledByNativeUnchecked
    private boolean queueInputBuffer(int i, int i2, long j, long j2, long j3) {
        checkOnMediaCodecThread();
        try {
            this.inputBuffers[i].position(0);
            this.inputBuffers[i].limit(i2);
            this.decodeStartTimeMs.add(new TimeStamps(SystemClock.elapsedRealtime(), j2, j3));
            this.mediaCodec.queueInputBuffer(i, 0, i2, j, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.m5303e(TAG, "decode failed", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TimeStamps {
        private final long decodeStartTimeMs;
        private final long ntpTimeStampMs;
        private final long timeStampMs;

        public TimeStamps(long j, long j2, long j3) {
            this.decodeStartTimeMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class DecodedOutputBuffer {
        private final long decodeTimeMs;
        private final long endDecodeTimeMs;
        private final int index;
        private final long ntpTimeStampMs;
        private final int offset;
        private final long presentationTimeStampMs;
        private final int size;
        private final long timeStampMs;

        public DecodedOutputBuffer(int i, int i2, int i3, long j, long j2, long j3, long j4, long j5) {
            this.index = i;
            this.offset = i2;
            this.size = i3;
            this.presentationTimeStampMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
            this.decodeTimeMs = j4;
            this.endDecodeTimeMs = j5;
        }

        @CalledByNative("DecodedOutputBuffer")
        int getIndex() {
            return this.index;
        }

        @CalledByNative("DecodedOutputBuffer")
        int getOffset() {
            return this.offset;
        }

        @CalledByNative("DecodedOutputBuffer")
        int getSize() {
            return this.size;
        }

        @CalledByNative("DecodedOutputBuffer")
        long getPresentationTimestampMs() {
            return this.presentationTimeStampMs;
        }

        @CalledByNative("DecodedOutputBuffer")
        long getTimestampMs() {
            return this.timeStampMs;
        }

        @CalledByNative("DecodedOutputBuffer")
        long getNtpTimestampMs() {
            return this.ntpTimeStampMs;
        }

        @CalledByNative("DecodedOutputBuffer")
        long getDecodeTimeMs() {
            return this.decodeTimeMs;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class DecodedTextureBuffer {
        private final long decodeTimeMs;
        private final long frameDelayMs;
        private final long ntpTimeStampMs;
        private final long presentationTimeStampMs;
        private final long timeStampMs;
        private final VideoFrame.Buffer videoFrameBuffer;

        public DecodedTextureBuffer(VideoFrame.Buffer buffer, long j, long j2, long j3, long j4, long j5) {
            this.videoFrameBuffer = buffer;
            this.presentationTimeStampMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
            this.decodeTimeMs = j4;
            this.frameDelayMs = j5;
        }

        @CalledByNative("DecodedTextureBuffer")
        VideoFrame.Buffer getVideoFrameBuffer() {
            return this.videoFrameBuffer;
        }

        @CalledByNative("DecodedTextureBuffer")
        long getPresentationTimestampMs() {
            return this.presentationTimeStampMs;
        }

        @CalledByNative("DecodedTextureBuffer")
        long getTimeStampMs() {
            return this.timeStampMs;
        }

        @CalledByNative("DecodedTextureBuffer")
        long getNtpTimestampMs() {
            return this.ntpTimeStampMs;
        }

        @CalledByNative("DecodedTextureBuffer")
        long getDecodeTimeMs() {
            return this.decodeTimeMs;
        }

        @CalledByNative("DecodedTextureBuffer")
        long getFrameDelayMs() {
            return this.frameDelayMs;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class TextureListener implements VideoSink {
        @Nullable
        private DecodedOutputBuffer bufferToRender;
        private final Object newFrameLock = new Object();
        @Nullable
        private DecodedTextureBuffer renderedBuffer;
        private final SurfaceTextureHelper surfaceTextureHelper;

        public TextureListener(SurfaceTextureHelper surfaceTextureHelper) {
            this.surfaceTextureHelper = surfaceTextureHelper;
            surfaceTextureHelper.startListening(this);
        }

        public void addBufferToRender(DecodedOutputBuffer decodedOutputBuffer) {
            if (this.bufferToRender != null) {
                Logging.m5304e(MediaCodecVideoDecoder.TAG, "Unexpected addBufferToRender() called while waiting for a texture.");
                throw new IllegalStateException("Waiting for a texture.");
            } else {
                this.bufferToRender = decodedOutputBuffer;
            }
        }

        public boolean isWaitingForTexture() {
            boolean z;
            synchronized (this.newFrameLock) {
                z = this.bufferToRender != null;
            }
            return z;
        }

        public void setSize(int i, int i2) {
            this.surfaceTextureHelper.setTextureSize(i, i2);
        }

        @Override // com.webrtc.VideoSink
        public void onFrame(VideoFrame videoFrame) {
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    Logging.m5304e(MediaCodecVideoDecoder.TAG, "Unexpected onFrame() called while already holding a texture.");
                    throw new IllegalStateException("Already holding a texture.");
                }
                VideoFrame.Buffer buffer = videoFrame.getBuffer();
                buffer.retain();
                this.renderedBuffer = new DecodedTextureBuffer(buffer, this.bufferToRender.presentationTimeStampMs, this.bufferToRender.timeStampMs, this.bufferToRender.ntpTimeStampMs, this.bufferToRender.decodeTimeMs, SystemClock.elapsedRealtime() - this.bufferToRender.endDecodeTimeMs);
                this.bufferToRender = null;
                this.newFrameLock.notifyAll();
            }
        }

        @Nullable
        public DecodedTextureBuffer dequeueTextureBuffer(int i) {
            DecodedTextureBuffer decodedTextureBuffer;
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer == null && i > 0 && isWaitingForTexture()) {
                    try {
                        this.newFrameLock.wait(i);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                decodedTextureBuffer = this.renderedBuffer;
                this.renderedBuffer = null;
            }
            return decodedTextureBuffer;
        }

        public void release() {
            this.surfaceTextureHelper.stopListening();
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    this.renderedBuffer.getVideoFrameBuffer().release();
                    this.renderedBuffer = null;
                }
            }
            this.surfaceTextureHelper.dispose();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0117, code lost:
        throw new java.lang.RuntimeException("Unexpected size change. Configured " + r22.width + "*" + r22.height + ". New " + r6 + "*" + r7);
     */
    @android.support.annotation.Nullable
    @com.webrtc.CalledByNativeUnchecked
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.webrtc.MediaCodecVideoDecoder.DecodedOutputBuffer dequeueOutputBuffer(int r23) {
        /*
            Method dump skipped, instructions count: 598
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webrtc.MediaCodecVideoDecoder.dequeueOutputBuffer(int):com.webrtc.MediaCodecVideoDecoder$DecodedOutputBuffer");
    }

    @Nullable
    @CalledByNativeUnchecked
    private DecodedTextureBuffer dequeueTextureBuffer(int i) {
        checkOnMediaCodecThread();
        if (!useSurface()) {
            throw new IllegalStateException("dequeueTexture() called for byte buffer decoding.");
        }
        DecodedOutputBuffer dequeueOutputBuffer = dequeueOutputBuffer(i);
        if (dequeueOutputBuffer != null) {
            this.dequeuedSurfaceOutputBuffers.add(dequeueOutputBuffer);
        }
        MaybeRenderDecodedTextureBuffer();
        DecodedTextureBuffer dequeueTextureBuffer = this.textureListener.dequeueTextureBuffer(i);
        if (dequeueTextureBuffer != null) {
            MaybeRenderDecodedTextureBuffer();
            return dequeueTextureBuffer;
        } else if (this.dequeuedSurfaceOutputBuffers.size() >= Math.min(3, this.outputBuffers.length) || (i > 0 && !this.dequeuedSurfaceOutputBuffers.isEmpty())) {
            this.droppedFrames++;
            DecodedOutputBuffer remove = this.dequeuedSurfaceOutputBuffers.remove();
            if (i > 0) {
                Logging.m5301w(TAG, "Draining decoder. Dropping frame with TS: " + remove.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames);
            } else {
                Logging.m5301w(TAG, "Too many output buffers " + this.dequeuedSurfaceOutputBuffers.size() + ". Dropping frame with TS: " + remove.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames);
            }
            this.mediaCodec.releaseOutputBuffer(remove.index, false);
            return new DecodedTextureBuffer(null, remove.presentationTimeStampMs, remove.timeStampMs, remove.ntpTimeStampMs, remove.decodeTimeMs, SystemClock.elapsedRealtime() - remove.endDecodeTimeMs);
        } else {
            return null;
        }
    }

    private void MaybeRenderDecodedTextureBuffer() {
        if (this.dequeuedSurfaceOutputBuffers.isEmpty() || this.textureListener.isWaitingForTexture()) {
            return;
        }
        DecodedOutputBuffer remove = this.dequeuedSurfaceOutputBuffers.remove();
        this.textureListener.addBufferToRender(remove);
        this.mediaCodec.releaseOutputBuffer(remove.index, true);
    }

    @CalledByNativeUnchecked
    private void returnDecodedOutputBuffer(int i) throws IllegalStateException, MediaCodec.CodecException {
        checkOnMediaCodecThread();
        if (useSurface()) {
            throw new IllegalStateException("returnDecodedOutputBuffer() called for surface decoding.");
        }
        this.mediaCodec.releaseOutputBuffer(i, false);
    }

    @CalledByNative
    ByteBuffer[] getInputBuffers() {
        return this.inputBuffers;
    }

    @CalledByNative
    ByteBuffer[] getOutputBuffers() {
        return this.outputBuffers;
    }

    @CalledByNative
    int getColorFormat() {
        return this.colorFormat;
    }

    @CalledByNative
    int getWidth() {
        return this.width;
    }

    @CalledByNative
    int getHeight() {
        return this.height;
    }

    @CalledByNative
    int getStride() {
        return this.stride;
    }

    @CalledByNative
    int getSliceHeight() {
        return this.sliceHeight;
    }
}

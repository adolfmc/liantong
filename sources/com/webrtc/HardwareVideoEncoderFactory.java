package com.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.webrtc.EglBase;
import com.webrtc.EglBase14;
import com.webrtc.VideoEncoderFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class HardwareVideoEncoderFactory implements VideoEncoderFactory {
    private static final List<String> H264_HW_EXCEPTION_MODELS = Arrays.asList("SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4");
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_L_MS = 15000;
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 20000;
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "HardwareVideoEncoderFactory";
    @Nullable
    private final Predicate<MediaCodecInfo> codecAllowedPredicate;
    private final boolean enableH264HighProfile;
    private final boolean enableIntelVp8Encoder;
    @Nullable
    private final EglBase14.Context sharedContext;

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

    public HardwareVideoEncoderFactory(EglBase.Context context, boolean z, boolean z2) {
        this(context, z, z2, null);
    }

    public HardwareVideoEncoderFactory(EglBase.Context context, boolean z, boolean z2, @Nullable Predicate<MediaCodecInfo> predicate) {
        if (context instanceof EglBase14.Context) {
            this.sharedContext = (EglBase14.Context) context;
        } else {
            Logging.m5301w(TAG, "No shared EglBase.Context.  Encoders will not use texture mode.");
            this.sharedContext = null;
        }
        this.enableIntelVp8Encoder = z;
        this.enableH264HighProfile = z2;
        this.codecAllowedPredicate = predicate;
    }

    @Deprecated
    public HardwareVideoEncoderFactory(boolean z, boolean z2) {
        this(null, z, z2);
    }

    @Override // com.webrtc.VideoEncoderFactory
    @Nullable
    public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
        VideoCodecMimeType valueOf;
        MediaCodecInfo findCodecForType;
        if (Build.VERSION.SDK_INT >= 19 && (findCodecForType = findCodecForType((valueOf = VideoCodecMimeType.valueOf(videoCodecInfo.name)))) != null) {
            String name = findCodecForType.getName();
            String mimeType = valueOf.mimeType();
            Integer selectColorFormat = MediaCodecUtils.selectColorFormat(MediaCodecUtils.TEXTURE_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(mimeType));
            Integer selectColorFormat2 = MediaCodecUtils.selectColorFormat(MediaCodecUtils.ENCODER_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(mimeType));
            if (valueOf == VideoCodecMimeType.H264) {
                boolean isSameH264Profile = H264Utils.isSameH264Profile(videoCodecInfo.params, MediaCodecUtils.getCodecProperties(valueOf, true));
                boolean isSameH264Profile2 = H264Utils.isSameH264Profile(videoCodecInfo.params, MediaCodecUtils.getCodecProperties(valueOf, false));
                if (!isSameH264Profile && !isSameH264Profile2) {
                    return null;
                }
                if (isSameH264Profile && !isH264HighProfileSupported(findCodecForType)) {
                    return null;
                }
            }
            return new HardwareVideoEncoder(new MediaCodecWrapperFactoryImpl(), name, valueOf, selectColorFormat, selectColorFormat2, videoCodecInfo.params, getKeyFrameIntervalSec(valueOf), getForcedKeyFrameIntervalMs(valueOf, name), createBitrateAdjuster(valueOf, name), this.sharedContext);
        }
        return null;
    }

    @Override // com.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        VideoCodecMimeType[] videoCodecMimeTypeArr;
        if (Build.VERSION.SDK_INT < 19) {
            return new VideoCodecInfo[0];
        }
        ArrayList arrayList = new ArrayList();
        for (VideoCodecMimeType videoCodecMimeType : new VideoCodecMimeType[]{VideoCodecMimeType.VP8, VideoCodecMimeType.VP9, VideoCodecMimeType.H264, VideoCodecMimeType.H263}) {
            MediaCodecInfo findCodecForType = findCodecForType(videoCodecMimeType);
            if (findCodecForType != null) {
                String name = videoCodecMimeType.name();
                if (videoCodecMimeType == VideoCodecMimeType.H264 && isH264HighProfileSupported(findCodecForType)) {
                    arrayList.add(new VideoCodecInfo(name, MediaCodecUtils.getCodecProperties(videoCodecMimeType, true)));
                }
                arrayList.add(new VideoCodecInfo(name, MediaCodecUtils.getCodecProperties(videoCodecMimeType, false)));
            }
        }
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
    }

    @Nullable
    private MediaCodecInfo findCodecForType(VideoCodecMimeType videoCodecMimeType) {
        int i = 0;
        while (true) {
            MediaCodecInfo mediaCodecInfo = null;
            if (i >= MediaCodecList.getCodecCount()) {
                return null;
            }
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.m5303e(TAG, "Cannot retrieve encoder codec info", e);
            }
            if (mediaCodecInfo != null && mediaCodecInfo.isEncoder() && isSupportedCodec(mediaCodecInfo, videoCodecMimeType)) {
                return mediaCodecInfo;
            }
            i++;
        }
    }

    private boolean isSupportedCodec(MediaCodecInfo mediaCodecInfo, VideoCodecMimeType videoCodecMimeType) {
        boolean z;
        boolean z2;
        String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
        int length = supportedTypes.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                z2 = false;
                break;
            } else if (supportedTypes[i].equals(videoCodecMimeType.mimeType())) {
                String name = mediaCodecInfo.getName();
                if (name.startsWith("OMX.hisi.")) {
                    return false;
                }
                z2 = !TextUtils.isEmpty(name) && name.startsWith("OMX.IMG.TOPAZ");
                z = true;
            } else {
                i++;
            }
        }
        return z && MediaCodecUtils.selectColorFormat(MediaCodecUtils.ENCODER_COLOR_FORMATS, mediaCodecInfo.getCapabilitiesForType(videoCodecMimeType.mimeType()), z2) != null && isHardwareSupportedInCurrentSdk(mediaCodecInfo, videoCodecMimeType) && isMediaCodecAllowed(mediaCodecInfo);
    }

    private boolean isHardwareSupportedInCurrentSdk(MediaCodecInfo mediaCodecInfo, VideoCodecMimeType videoCodecMimeType) {
        switch (videoCodecMimeType) {
            case VP8:
                return isHardwareSupportedInCurrentSdkVp8(mediaCodecInfo);
            case VP9:
                return isHardwareSupportedInCurrentSdkVp9(mediaCodecInfo);
            case H264:
                return isHardwareSupportedInCurrentSdkH264(mediaCodecInfo);
            case H263:
                return isHardwareSupportedInCurrentSdkH263(mediaCodecInfo);
            default:
                return false;
        }
    }

    private boolean isHardwareSupportedInCurrentSdkVp8(MediaCodecInfo mediaCodecInfo) {
        String name = mediaCodecInfo.getName();
        return (name.startsWith("OMX.qcom.") && Build.VERSION.SDK_INT >= 19) || (name.startsWith("OMX.Exynos.") && Build.VERSION.SDK_INT >= 23) || (name.startsWith("OMX.Intel.") && Build.VERSION.SDK_INT >= 21 && PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-IntelVP8").equals("Enabled") && this.enableIntelVp8Encoder);
    }

    private boolean isHardwareSupportedInCurrentSdkVp9(MediaCodecInfo mediaCodecInfo) {
        String name = mediaCodecInfo.getName();
        return (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Exynos.")) && Build.VERSION.SDK_INT >= 24;
    }

    private boolean isHardwareSupportedInCurrentSdkH263(MediaCodecInfo mediaCodecInfo) {
        String name = mediaCodecInfo.getName();
        return (name.startsWith("OMX.google.") || name.startsWith("OMX.MTK.")) && Build.VERSION.SDK_INT >= 19;
    }

    private boolean isHardwareSupportedInCurrentSdkH264(MediaCodecInfo mediaCodecInfo) {
        if (H264_HW_EXCEPTION_MODELS.contains(Build.MODEL)) {
            return false;
        }
        String name = mediaCodecInfo.getName();
        return (name.startsWith("OMX.qcom.") && Build.VERSION.SDK_INT >= 19) || (name.startsWith("OMX.Exynos.") && Build.VERSION.SDK_INT >= 21) || ((name.startsWith("OMX.Freesca") && Build.VERSION.SDK_INT >= 21) || ((name.startsWith("OMX.hisi.") && Build.VERSION.SDK_INT >= 21 && PeerConnectionFactory.fieldTrialsFindFullName("BRTC.HisiH264HW").equals("Enabled")) || ((name.startsWith("OMX.MTK.") && Build.VERSION.SDK_INT >= 21) || ((name.startsWith("OMX.rk.") && Build.VERSION.SDK_INT >= 21) || ((name.startsWith("OMX.allwinner.") && Build.VERSION.SDK_INT >= 21) || ((name.startsWith("OMX.Nvidia.") && Build.VERSION.SDK_INT >= 21) || ((name.startsWith("OMX.IMG.") && Build.VERSION.SDK_INT >= 21) || ((name.startsWith("OMX.google.") && Build.VERSION.SDK_INT >= 21) || ((name.startsWith("OMX.sprd.") && Build.VERSION.SDK_INT >= 19) || (name.startsWith("OMX.amlogic.") && Build.VERSION.SDK_INT >= 21))))))))));
    }

    private boolean isMediaCodecAllowed(MediaCodecInfo mediaCodecInfo) {
        Predicate<MediaCodecInfo> predicate = this.codecAllowedPredicate;
        if (predicate == null) {
            return true;
        }
        return predicate.test(mediaCodecInfo);
    }

    private int getKeyFrameIntervalSec(VideoCodecMimeType videoCodecMimeType) {
        switch (videoCodecMimeType) {
            case VP8:
            case VP9:
                return 100;
            case H264:
                return 20;
            case H263:
                return 1;
            default:
                throw new IllegalArgumentException("Unsupported VideoCodecMimeType " + videoCodecMimeType);
        }
    }

    private int getForcedKeyFrameIntervalMs(VideoCodecMimeType videoCodecMimeType, String str) {
        if (videoCodecMimeType == VideoCodecMimeType.VP8 && str.startsWith("OMX.qcom.")) {
            if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) {
                return 15000;
            }
            if (Build.VERSION.SDK_INT == 23) {
                return 20000;
            }
            return Build.VERSION.SDK_INT > 23 ? 15000 : 0;
        }
        return 0;
    }

    private BitrateAdjuster createBitrateAdjuster(VideoCodecMimeType videoCodecMimeType, String str) {
        if (str.startsWith("OMX.Exynos.")) {
            if (videoCodecMimeType == VideoCodecMimeType.VP8) {
                return new DynamicBitrateAdjuster();
            }
            return new FramerateBitrateAdjuster();
        }
        return new BaseBitrateAdjuster();
    }

    private boolean isH264HighProfileSupported(MediaCodecInfo mediaCodecInfo) {
        return (this.enableH264HighProfile && Build.VERSION.SDK_INT > 23 && mediaCodecInfo.getName().startsWith("OMX.Exynos.")) || (mediaCodecInfo.getName().startsWith("OMX.qcom.") && Build.VERSION.SDK_INT >= 21) || ((mediaCodecInfo.getName().startsWith("OMX.hisi.") && Build.VERSION.SDK_INT >= 23) || (mediaCodecInfo.getName().startsWith("OMX.MTK.") && Build.VERSION.SDK_INT >= 27));
    }
}

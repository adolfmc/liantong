package org.webrtc;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.os.Build;
import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class MediaCodecUtils {
    static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka = 2141391874;
    static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka = 2141391873;
    static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka = 2141391875;
    static final String EXYNOS_PREFIX = "OMX.Exynos.";
    static final String INTEL_PREFIX = "OMX.Intel.";
    static final String NVIDIA_PREFIX = "OMX.Nvidia.";
    static final String QCOM_PREFIX = "OMX.qcom.";
    private static final String TAG = "MediaCodecUtils";
    static final String[] SOFTWARE_IMPLEMENTATION_PREFIXES = {"OMX.google.", "OMX.SEC.", "c2.android"};
    static final int[] DECODER_COLOR_FORMATS = {19, 21, 2141391872, 2141391873, 2141391874, 2141391875, 2141391876};
    static final int[] ENCODER_COLOR_FORMATS = {19, 21, 2141391872, 2141391876};
    static final int[] TEXTURE_COLOR_FORMATS = getTextureColorFormats();

    private static int[] getTextureColorFormats() {
        return Build.VERSION.SDK_INT >= 18 ? new int[]{2130708361} : new int[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Integer selectColorFormat(int[] iArr, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        int[] iArr2;
        for (int i : iArr) {
            for (int i2 : codecCapabilities.colorFormats) {
                if (i2 == i) {
                    return Integer.valueOf(i2);
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean codecSupportsType(MediaCodecInfo mediaCodecInfo, VideoCodecMimeType videoCodecMimeType) {
        for (String str : mediaCodecInfo.getSupportedTypes()) {
            if (videoCodecMimeType.mimeType().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> getCodecProperties(VideoCodecMimeType videoCodecMimeType, boolean z) {
        switch (videoCodecMimeType) {
            case VP8:
            case VP9:
                return new HashMap();
            case H264:
                return H264Utils.getDefaultH264Params(z);
            default:
                throw new IllegalArgumentException("Unsupported codec: " + videoCodecMimeType);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isHardwareAccelerated(MediaCodecInfo mediaCodecInfo) {
        if (Build.VERSION.SDK_INT >= 29) {
            return isHardwareAcceleratedQOrHigher(mediaCodecInfo);
        }
        return !isSoftwareOnly(mediaCodecInfo);
    }

    @TargetApi(29)
    private static boolean isHardwareAcceleratedQOrHigher(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isHardwareAccelerated();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSoftwareOnly(MediaCodecInfo mediaCodecInfo) {
        if (Build.VERSION.SDK_INT >= 29) {
            return isSoftwareOnlyQOrHigher(mediaCodecInfo);
        }
        String name = mediaCodecInfo.getName();
        for (String str : SOFTWARE_IMPLEMENTATION_PREFIXES) {
            if (name.startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(29)
    private static boolean isSoftwareOnlyQOrHigher(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isSoftwareOnly();
    }

    private MediaCodecUtils() {
    }
}

package com.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.support.annotation.Nullable;
import com.webrtc.EglBase;
import com.webrtc.VideoDecoder;
import com.webrtc.VideoDecoderFactory;
import java.util.ArrayList;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class MediaCodecVideoDecoderFactory implements VideoDecoderFactory {
    private static final String TAG = "MediaCodecVideoDecoderFactory";
    @Nullable
    private final Predicate<MediaCodecInfo> codecAllowedPredicate;
    private VideoDecoder.VideoDecoderCallback mDecoderCallback;
    @Nullable
    private final EglBase.Context sharedContext;

    @Override // com.webrtc.VideoDecoderFactory
    @Nullable
    @Deprecated
    public /* synthetic */ VideoDecoder createDecoder(String str) {
        return VideoDecoderFactory.CC.$default$createDecoder(this, str);
    }

    public MediaCodecVideoDecoderFactory(@Nullable EglBase.Context context, @Nullable Predicate<MediaCodecInfo> predicate) {
        this.sharedContext = context;
        this.codecAllowedPredicate = predicate;
    }

    @Override // com.webrtc.VideoDecoderFactory
    @Nullable
    public VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        VideoCodecMimeType valueOf = VideoCodecMimeType.valueOf(videoCodecInfo.getName());
        MediaCodecInfo findCodecForType = findCodecForType(valueOf);
        if (findCodecForType == null) {
            return null;
        }
        AndroidVideoDecoder androidVideoDecoder = new AndroidVideoDecoder(new MediaCodecWrapperFactoryImpl(), findCodecForType.getName(), valueOf, MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(valueOf.mimeType())).intValue(), this.sharedContext);
        androidVideoDecoder.setVideoDecoderCallback(this.mDecoderCallback);
        return androidVideoDecoder;
    }

    @Override // com.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        VideoCodecMimeType[] videoCodecMimeTypeArr;
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
        MediaCodecInfo mediaCodecInfo;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.m5303e(TAG, "Cannot retrieve decoder codec info", e);
                mediaCodecInfo = null;
            }
            if (mediaCodecInfo != null && !mediaCodecInfo.isEncoder() && isSupportedCodec(mediaCodecInfo, videoCodecMimeType)) {
                return mediaCodecInfo;
            }
        }
        return null;
    }

    private boolean isSupportedCodec(MediaCodecInfo mediaCodecInfo, VideoCodecMimeType videoCodecMimeType) {
        mediaCodecInfo.getName();
        return MediaCodecUtils.codecSupportsType(mediaCodecInfo, videoCodecMimeType) && MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, mediaCodecInfo.getCapabilitiesForType(videoCodecMimeType.mimeType())) != null && isHardwareSupportedInCurrentSdk(mediaCodecInfo, videoCodecMimeType) && isCodecAllowed(mediaCodecInfo);
    }

    private boolean isHardwareSupportedInCurrentSdk(MediaCodecInfo mediaCodecInfo, VideoCodecMimeType videoCodecMimeType) {
        switch (videoCodecMimeType) {
            case VP8:
                return true;
            case VP9:
                return true;
            case H264:
                return isSupportedInCurrentSdkH264();
            case H263:
                return isSupportedInCurrentSdkH263(mediaCodecInfo);
            default:
                return true;
        }
    }

    private boolean isSupportedInCurrentSdkH264() {
        return PeerConnectionFactory.fieldTrialsFindFullName("BRTC.HisiH264HW").equals("Enabled") && PeerConnectionFactory.fieldTrialsFindFullName("BRTC.MTK.H264.Decode").equals("Enabled");
    }

    private boolean isSupportedInCurrentSdkH263(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.getName().startsWith("OMX.google.");
    }

    private boolean isCodecAllowed(MediaCodecInfo mediaCodecInfo) {
        Predicate<MediaCodecInfo> predicate = this.codecAllowedPredicate;
        if (predicate == null) {
            return true;
        }
        return predicate.test(mediaCodecInfo);
    }

    private boolean isH264HighProfileSupported(MediaCodecInfo mediaCodecInfo) {
        String name = mediaCodecInfo.getName();
        if (Build.VERSION.SDK_INT < 21 || !name.startsWith("OMX.qcom.")) {
            if (Build.VERSION.SDK_INT < 23 || !name.startsWith("OMX.Exynos.")) {
                if (Build.VERSION.SDK_INT < 23 || !name.startsWith("OMX.hisi.")) {
                    return PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MediaTekH264").equals("Enabled") && Build.VERSION.SDK_INT >= 27 && name.startsWith("OMX.MTK.");
                }
                return true;
            }
            return true;
        }
        return true;
    }

    public void setVideoDecoderCallback(VideoDecoder.VideoDecoderCallback videoDecoderCallback) {
        this.mDecoderCallback = videoDecoderCallback;
    }
}

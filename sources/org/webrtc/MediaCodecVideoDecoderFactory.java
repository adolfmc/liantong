package org.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import org.webrtc.EglBase;
import org.webrtc.VideoDecoderFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class MediaCodecVideoDecoderFactory implements VideoDecoderFactory {
    private static final String TAG = "MediaCodecVideoDecoderFactory";
    @Nullable
    private final Predicate<MediaCodecInfo> codecAllowedPredicate;
    @Nullable
    private final EglBase.Context sharedContext;

    @Override // org.webrtc.VideoDecoderFactory
    @Nullable
    @Deprecated
    public /* synthetic */ VideoDecoder createDecoder(String str) {
        return VideoDecoderFactory.CC.$default$createDecoder(this, str);
    }

    public MediaCodecVideoDecoderFactory(@Nullable EglBase.Context context, @Nullable Predicate<MediaCodecInfo> predicate) {
        this.sharedContext = context;
        this.codecAllowedPredicate = predicate;
    }

    @Override // org.webrtc.VideoDecoderFactory
    @Nullable
    public VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        VideoCodecMimeType valueOf = VideoCodecMimeType.valueOf(videoCodecInfo.getName());
        MediaCodecInfo findCodecForType = findCodecForType(valueOf);
        if (findCodecForType == null) {
            return null;
        }
        return new AndroidVideoDecoder(new MediaCodecWrapperFactoryImpl(), findCodecForType.getName(), valueOf, MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(valueOf.mimeType())).intValue(), this.sharedContext);
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        VideoCodecMimeType[] videoCodecMimeTypeArr;
        ArrayList arrayList = new ArrayList();
        for (VideoCodecMimeType videoCodecMimeType : new VideoCodecMimeType[]{VideoCodecMimeType.VP8, VideoCodecMimeType.VP9, VideoCodecMimeType.H264}) {
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
                Logging.m204e("MediaCodecVideoDecoderFactory", "Cannot retrieve decoder codec info", e);
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
        if (MediaCodecUtils.codecSupportsType(mediaCodecInfo, videoCodecMimeType) && MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, mediaCodecInfo.getCapabilitiesForType(videoCodecMimeType.mimeType())) != null) {
            return isCodecAllowed(mediaCodecInfo);
        }
        return false;
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
            return Build.VERSION.SDK_INT >= 23 && name.startsWith("OMX.Exynos.");
        }
        return true;
    }
}

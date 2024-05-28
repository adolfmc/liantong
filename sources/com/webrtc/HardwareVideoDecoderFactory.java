package com.webrtc;

import android.media.MediaCodecInfo;
import android.support.annotation.Nullable;
import com.webrtc.EglBase;
import com.webrtc.Predicate;
import com.webrtc.VideoDecoder;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class HardwareVideoDecoderFactory extends MediaCodecVideoDecoderFactory {
    private static final Predicate<MediaCodecInfo> defaultAllowedPredicate = new Predicate<MediaCodecInfo>() { // from class: com.webrtc.HardwareVideoDecoderFactory.1
        @Override // com.webrtc.Predicate
        public /* synthetic */ Predicate<T> and(Predicate<? super T> predicate) {
            return Predicate.CC.$default$and(this, predicate);
        }

        @Override // com.webrtc.Predicate
        public /* synthetic */ Predicate<T> negate() {
            return Predicate.CC.$default$negate(this);
        }

        @Override // com.webrtc.Predicate
        /* renamed from: or */
        public /* synthetic */ Predicate<T> mo5298or(Predicate<? super T> predicate) {
            return Predicate.CC.$default$or(this, predicate);
        }

        @Override // com.webrtc.Predicate
        public boolean test(MediaCodecInfo mediaCodecInfo) {
            return MediaCodecUtils.isHardwareAccelerated(mediaCodecInfo);
        }
    };

    @Override // com.webrtc.MediaCodecVideoDecoderFactory, com.webrtc.VideoDecoderFactory
    @Nullable
    public /* bridge */ /* synthetic */ VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        return super.createDecoder(videoCodecInfo);
    }

    @Override // com.webrtc.MediaCodecVideoDecoderFactory, com.webrtc.VideoDecoderFactory
    public /* bridge */ /* synthetic */ VideoCodecInfo[] getSupportedCodecs() {
        return super.getSupportedCodecs();
    }

    @Override // com.webrtc.MediaCodecVideoDecoderFactory
    public /* bridge */ /* synthetic */ void setVideoDecoderCallback(VideoDecoder.VideoDecoderCallback videoDecoderCallback) {
        super.setVideoDecoderCallback(videoDecoderCallback);
    }

    @Deprecated
    public HardwareVideoDecoderFactory() {
        this(null);
    }

    public HardwareVideoDecoderFactory(@Nullable EglBase.Context context) {
        this(context, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public HardwareVideoDecoderFactory(@android.support.annotation.Nullable com.webrtc.EglBase.Context r2, @android.support.annotation.Nullable com.webrtc.Predicate<android.media.MediaCodecInfo> r3) {
        /*
            r1 = this;
            if (r3 != 0) goto L5
            com.webrtc.Predicate<android.media.MediaCodecInfo> r3 = com.webrtc.HardwareVideoDecoderFactory.defaultAllowedPredicate
            goto Lb
        L5:
            com.webrtc.Predicate<android.media.MediaCodecInfo> r0 = com.webrtc.HardwareVideoDecoderFactory.defaultAllowedPredicate
            com.webrtc.Predicate r3 = r3.and(r0)
        Lb:
            r1.<init>(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webrtc.HardwareVideoDecoderFactory.<init>(com.webrtc.EglBase$Context, com.webrtc.Predicate):void");
    }
}

package org.webrtc;

import android.media.MediaCodecInfo;
import android.support.annotation.Nullable;
import org.webrtc.EglBase;
import org.webrtc.Predicate;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HardwareVideoDecoderFactory extends MediaCodecVideoDecoderFactory {
    private static final Predicate<MediaCodecInfo> defaultAllowedPredicate = new Predicate<MediaCodecInfo>() { // from class: org.webrtc.HardwareVideoDecoderFactory.1
        @Override // org.webrtc.Predicate
        public /* synthetic */ Predicate<T> and(Predicate<? super T> predicate) {
            return Predicate.CC.$default$and(this, predicate);
        }

        @Override // org.webrtc.Predicate
        public /* synthetic */ Predicate<T> negate() {
            return Predicate.CC.$default$negate(this);
        }

        @Override // org.webrtc.Predicate
        /* renamed from: or */
        public /* synthetic */ Predicate<T> mo199or(Predicate<? super T> predicate) {
            return Predicate.CC.$default$or(this, predicate);
        }

        @Override // org.webrtc.Predicate
        public boolean test(MediaCodecInfo mediaCodecInfo) {
            return MediaCodecUtils.isHardwareAccelerated(mediaCodecInfo);
        }
    };

    @Override // org.webrtc.MediaCodecVideoDecoderFactory, org.webrtc.VideoDecoderFactory
    @Nullable
    public /* bridge */ /* synthetic */ VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        return super.createDecoder(videoCodecInfo);
    }

    @Override // org.webrtc.MediaCodecVideoDecoderFactory, org.webrtc.VideoDecoderFactory
    public /* bridge */ /* synthetic */ VideoCodecInfo[] getSupportedCodecs() {
        return super.getSupportedCodecs();
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
    public HardwareVideoDecoderFactory(@android.support.annotation.Nullable org.webrtc.EglBase.Context r2, @android.support.annotation.Nullable org.webrtc.Predicate<android.media.MediaCodecInfo> r3) {
        /*
            r1 = this;
            if (r3 != 0) goto L5
            org.webrtc.Predicate<android.media.MediaCodecInfo> r3 = org.webrtc.HardwareVideoDecoderFactory.defaultAllowedPredicate
            goto Lb
        L5:
            org.webrtc.Predicate<android.media.MediaCodecInfo> r0 = org.webrtc.HardwareVideoDecoderFactory.defaultAllowedPredicate
            org.webrtc.Predicate r3 = r3.and(r0)
        Lb:
            r1.<init>(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.HardwareVideoDecoderFactory.<init>(org.webrtc.EglBase$Context, org.webrtc.Predicate):void");
    }
}

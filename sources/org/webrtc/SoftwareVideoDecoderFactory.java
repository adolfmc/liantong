package org.webrtc;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SoftwareVideoDecoderFactory implements VideoDecoderFactory {
    @Override // org.webrtc.VideoDecoderFactory
    @Nullable
    @Deprecated
    public VideoDecoder createDecoder(String str) {
        return createDecoder(new VideoCodecInfo(str, new HashMap()));
    }

    @Override // org.webrtc.VideoDecoderFactory
    @Nullable
    public VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        if (videoCodecInfo.getName().equalsIgnoreCase("VP8")) {
            return new LibvpxVp8Decoder();
        }
        if (videoCodecInfo.getName().equalsIgnoreCase("VP9") && LibvpxVp9Decoder.nativeIsSupported()) {
            return new LibvpxVp9Decoder();
        }
        return null;
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        return supportedCodecs();
    }

    static VideoCodecInfo[] supportedCodecs() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new VideoCodecInfo("VP8", new HashMap()));
        if (LibvpxVp9Decoder.nativeIsSupported()) {
            arrayList.add(new VideoCodecInfo("VP9", new HashMap()));
        }
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
    }
}

package com.webrtc;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SoftwareVideoDecoderFactory implements VideoDecoderFactory {
    @Override // com.webrtc.VideoDecoderFactory
    @Nullable
    public VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        return null;
    }

    @Override // com.webrtc.VideoDecoderFactory
    @Nullable
    @Deprecated
    public VideoDecoder createDecoder(String str) {
        return createDecoder(new VideoCodecInfo(str, new HashMap()));
    }

    @Override // com.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        return supportedCodecs();
    }

    static VideoCodecInfo[] supportedCodecs() {
        ArrayList arrayList = new ArrayList();
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
    }
}

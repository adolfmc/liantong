package com.webrtc;

import android.support.annotation.Nullable;
import com.webrtc.EglBase;
import com.webrtc.VideoEncoderFactory;
import java.util.Arrays;
import java.util.LinkedHashSet;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultVideoEncoderFactory implements VideoEncoderFactory {
    private final VideoEncoderFactory hardwareVideoEncoderFactory;
    private final VideoEncoderFactory softwareVideoEncoderFactory = new SoftwareVideoEncoderFactory();

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

    public DefaultVideoEncoderFactory(EglBase.Context context, boolean z, boolean z2) {
        this.hardwareVideoEncoderFactory = new HardwareVideoEncoderFactory(context, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultVideoEncoderFactory(VideoEncoderFactory videoEncoderFactory) {
        this.hardwareVideoEncoderFactory = videoEncoderFactory;
    }

    @Override // com.webrtc.VideoEncoderFactory
    @Nullable
    public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
        VideoEncoder createEncoder = this.softwareVideoEncoderFactory.createEncoder(videoCodecInfo);
        VideoEncoder createEncoder2 = this.hardwareVideoEncoderFactory.createEncoder(videoCodecInfo);
        if (createEncoder2 == null || createEncoder == null) {
            return createEncoder2 != null ? createEncoder2 : createEncoder;
        }
        return new VideoEncoderFallback(createEncoder, createEncoder2);
    }

    @Override // com.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(Arrays.asList(this.softwareVideoEncoderFactory.getSupportedCodecs()));
        linkedHashSet.addAll(Arrays.asList(this.hardwareVideoEncoderFactory.getSupportedCodecs()));
        return (VideoCodecInfo[]) linkedHashSet.toArray(new VideoCodecInfo[linkedHashSet.size()]);
    }
}

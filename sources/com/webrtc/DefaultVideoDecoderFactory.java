package com.webrtc;

import android.support.annotation.Nullable;
import com.webrtc.EglBase;
import com.webrtc.VideoDecoder;
import com.webrtc.VideoDecoderFactory;
import java.util.Arrays;
import java.util.LinkedHashSet;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultVideoDecoderFactory implements VideoDecoderFactory {
    private final VideoDecoderFactory hardwareVideoDecoderFactory;
    @Nullable
    private final VideoDecoderFactory platformSoftwareVideoDecoderFactory;
    private final VideoDecoderFactory softwareVideoDecoderFactory;

    @Override // com.webrtc.VideoDecoderFactory
    @Nullable
    @Deprecated
    public /* synthetic */ VideoDecoder createDecoder(String str) {
        return VideoDecoderFactory.CC.$default$createDecoder(this, str);
    }

    public DefaultVideoDecoderFactory(@Nullable EglBase.Context context) {
        this.softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();
        this.hardwareVideoDecoderFactory = new HardwareVideoDecoderFactory(context);
        this.platformSoftwareVideoDecoderFactory = new PlatformSoftwareVideoDecoderFactory(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultVideoDecoderFactory(VideoDecoderFactory videoDecoderFactory) {
        this.softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();
        this.hardwareVideoDecoderFactory = videoDecoderFactory;
        this.platformSoftwareVideoDecoderFactory = null;
    }

    @Override // com.webrtc.VideoDecoderFactory
    @Nullable
    public VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        VideoDecoderFactory videoDecoderFactory;
        VideoDecoder createDecoder = this.softwareVideoDecoderFactory.createDecoder(videoCodecInfo);
        VideoDecoder createDecoder2 = this.hardwareVideoDecoderFactory.createDecoder(videoCodecInfo);
        if (createDecoder == null && (videoDecoderFactory = this.platformSoftwareVideoDecoderFactory) != null) {
            createDecoder = videoDecoderFactory.createDecoder(videoCodecInfo);
        }
        if (createDecoder2 == null || createDecoder == null) {
            return createDecoder2 != null ? createDecoder2 : createDecoder;
        }
        return new VideoDecoderFallback(createDecoder, createDecoder2);
    }

    @Override // com.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(Arrays.asList(this.softwareVideoDecoderFactory.getSupportedCodecs()));
        linkedHashSet.addAll(Arrays.asList(this.hardwareVideoDecoderFactory.getSupportedCodecs()));
        VideoDecoderFactory videoDecoderFactory = this.platformSoftwareVideoDecoderFactory;
        if (videoDecoderFactory != null) {
            linkedHashSet.addAll(Arrays.asList(videoDecoderFactory.getSupportedCodecs()));
        }
        return (VideoCodecInfo[]) linkedHashSet.toArray(new VideoCodecInfo[linkedHashSet.size()]);
    }

    public void setVideoDecoderCallback(VideoDecoder.VideoDecoderCallback videoDecoderCallback) {
        VideoDecoderFactory videoDecoderFactory = this.hardwareVideoDecoderFactory;
        if (videoDecoderFactory != null && (videoDecoderFactory instanceof HardwareVideoDecoderFactory)) {
            ((HardwareVideoDecoderFactory) videoDecoderFactory).setVideoDecoderCallback(videoDecoderCallback);
        }
        VideoDecoderFactory videoDecoderFactory2 = this.platformSoftwareVideoDecoderFactory;
        if (videoDecoderFactory2 == null || !(videoDecoderFactory2 instanceof PlatformSoftwareVideoDecoderFactory)) {
            return;
        }
        ((PlatformSoftwareVideoDecoderFactory) videoDecoderFactory2).setVideoDecoderCallback(videoDecoderCallback);
    }
}

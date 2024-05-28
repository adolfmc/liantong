package com.megvii.livenesslib.kas.encoder;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.EGLContext;
import android.util.Log;
import android.view.Surface;
import com.megvii.livenesslib.kas.encoder.MediaEncoder;
import com.megvii.livenesslib.kas.p227gl.RenderHandler;
import java.io.IOException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MediaVideoEncoder extends MediaEncoder {
    private static final float BPP = 0.033333335f;
    private static final boolean DEBUG = false;
    private static final int FRAME_RATE = 25;
    private static final String MIME_TYPE = "video/avc";
    private static final String TAG = "MediaVideoEncoder";
    protected static int[] recognizedFormats = {2130708361};
    private final int mHeight;
    private RenderHandler mRenderHandler;
    private Surface mSurface;
    private final int mWidth;

    public MediaVideoEncoder(MediaMuxerWrapper mediaMuxerWrapper, MediaEncoder.MediaEncoderListener mediaEncoderListener, int i, int i2) {
        super(mediaMuxerWrapper, mediaEncoderListener);
        i = (i & 1) == 1 ? i - 1 : i;
        i2 = (i2 & 1) == 1 ? i2 - 1 : i2;
        this.mWidth = i;
        this.mHeight = i2;
        this.mRenderHandler = RenderHandler.createHandler(TAG);
    }

    public boolean frameAvailableSoon(float[] fArr) {
        boolean frameAvailableSoon = super.frameAvailableSoon();
        if (frameAvailableSoon) {
            this.mRenderHandler.draw(fArr);
        }
        return frameAvailableSoon;
    }

    public boolean frameAvailableSoon(float[] fArr, float[] fArr2) {
        boolean frameAvailableSoon = super.frameAvailableSoon();
        if (frameAvailableSoon) {
            this.mRenderHandler.draw(fArr, fArr2);
        }
        return frameAvailableSoon;
    }

    @Override // com.megvii.livenesslib.kas.encoder.MediaEncoder
    public boolean frameAvailableSoon() {
        boolean frameAvailableSoon = super.frameAvailableSoon();
        if (frameAvailableSoon) {
            this.mRenderHandler.draw((float[]) null);
        }
        return frameAvailableSoon;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.megvii.livenesslib.kas.encoder.MediaEncoder
    public void prepare() throws IOException {
        this.mTrackIndex = -1;
        this.mIsEOS = false;
        this.mMuxerStarted = false;
        if (selectVideoCodec("video/avc") == null) {
            Log.e(TAG, "Unable to find an appropriate codec for video/avc");
            return;
        }
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mWidth, this.mHeight);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", calcBitRate());
        createVideoFormat.setInteger("frame-rate", 25);
        createVideoFormat.setInteger("i-frame-interval", 10);
        this.mMediaCodec = MediaCodec.createEncoderByType("video/avc");
        this.mMediaCodec.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mSurface = this.mMediaCodec.createInputSurface();
        this.mMediaCodec.start();
        if (this.mListener != null) {
            try {
                this.mListener.onPrepared(this);
            } catch (Exception e) {
                Log.e(TAG, "prepare:", e);
            }
        }
    }

    public void setEglContext(EGLContext eGLContext, int i) {
        this.mRenderHandler.setEglContext(eGLContext, i, this.mSurface, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.megvii.livenesslib.kas.encoder.MediaEncoder
    public void release() {
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
            this.mSurface = null;
        }
        RenderHandler renderHandler = this.mRenderHandler;
        if (renderHandler != null) {
            renderHandler.release();
            this.mRenderHandler = null;
        }
        super.release();
    }

    private int calcBitRate() {
        int i = (int) (this.mWidth * 0.8333334f * this.mHeight);
        Log.i(TAG, String.format("bitrate=%5.2f[Kb/s]", Float.valueOf(i / 1024.0f)));
        return i;
    }

    protected static final MediaCodecInfo selectVideoCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str) && selectColorFormat(codecInfoAt, str) > 0) {
                        return codecInfoAt;
                    }
                }
                continue;
            }
        }
        return null;
    }

    protected static final int selectColorFormat(MediaCodecInfo mediaCodecInfo, String str) {
        try {
            Thread.currentThread().setPriority(10);
            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
            Thread.currentThread().setPriority(5);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= capabilitiesForType.colorFormats.length) {
                    break;
                }
                int i3 = capabilitiesForType.colorFormats[i2];
                if (isRecognizedViewoFormat(i3)) {
                    i = i3;
                    break;
                }
                i2++;
            }
            if (i == 0) {
                Log.e(TAG, "couldn't find a good color format for " + mediaCodecInfo.getName() + " / " + str);
            }
            return i;
        } catch (Throwable th) {
            Thread.currentThread().setPriority(5);
            throw th;
        }
    }

    private static final boolean isRecognizedViewoFormat(int i) {
        int[] iArr = recognizedFormats;
        int length = iArr != null ? iArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (recognizedFormats[i2] == i) {
                return true;
            }
        }
        return false;
    }

    @Override // com.megvii.livenesslib.kas.encoder.MediaEncoder
    protected void signalEndOfInputStream() {
        this.mMediaCodec.signalEndOfInputStream();
        this.mIsEOS = true;
    }
}

package com.p226hw.videoprocessor;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import com.p226hw.videoprocessor.util.FrameDropper;
import com.p226hw.videoprocessor.util.InputSurface;
import com.p226hw.videoprocessor.util.OutputSurface;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.Nullable;

/* renamed from: com.hw.videoprocessor.VideoDecodeThread */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class VideoDecodeThread extends Thread {
    private AtomicBoolean mDecodeDone;
    private MediaCodec mDecoder;
    private boolean mDropFrames;
    private Integer mDstFrameRate;
    private Integer mEndTimeMs;
    private Exception mException;
    private MediaExtractor mExtractor;
    private FrameDropper mFrameDropper;
    private InputSurface mInputSurface;
    private OutputSurface mOutputSurface;
    private Float mSpeed;
    private Integer mSrcFrameRate;
    private Integer mStartTimeMs;
    private IVideoEncodeThread mVideoEncodeThread;
    private int mVideoIndex;

    public VideoDecodeThread(IVideoEncodeThread iVideoEncodeThread, MediaExtractor mediaExtractor, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Float f, boolean z, int i, AtomicBoolean atomicBoolean) {
        super("VideoProcessDecodeThread");
        this.mExtractor = mediaExtractor;
        this.mStartTimeMs = num;
        this.mEndTimeMs = num2;
        this.mSpeed = f;
        this.mVideoIndex = i;
        this.mDecodeDone = atomicBoolean;
        this.mVideoEncodeThread = iVideoEncodeThread;
        this.mDstFrameRate = num4;
        this.mSrcFrameRate = num3;
        this.mDropFrames = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0026, code lost:
        if (r1 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0028, code lost:
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0029, code lost:
        r3.mException = r1;
        com.p226hw.videoprocessor.util.C5140CL.m13757e(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0057, code lost:
        if (r1 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:?, code lost:
        return;
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r3 = this;
            super.run()
            r3.doDecode()     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
            com.hw.videoprocessor.util.InputSurface r0 = r3.mInputSurface
            if (r0 == 0) goto Ld
            r0.release()
        Ld:
            com.hw.videoprocessor.util.OutputSurface r0 = r3.mOutputSurface
            if (r0 == 0) goto L14
            r0.release()
        L14:
            android.media.MediaCodec r0 = r3.mDecoder     // Catch: java.lang.Exception -> L23
            if (r0 == 0) goto L5a
            android.media.MediaCodec r0 = r3.mDecoder     // Catch: java.lang.Exception -> L23
            r0.stop()     // Catch: java.lang.Exception -> L23
            android.media.MediaCodec r0 = r3.mDecoder     // Catch: java.lang.Exception -> L23
            r0.release()     // Catch: java.lang.Exception -> L23
            goto L5a
        L23:
            r0 = move-exception
            java.lang.Exception r1 = r3.mException
            if (r1 != 0) goto L29
        L28:
            r1 = r0
        L29:
            r3.mException = r1
            com.p226hw.videoprocessor.util.C5140CL.m13757e(r0)
            goto L5a
        L2f:
            r0 = move-exception
            goto L5b
        L31:
            r0 = move-exception
            r3.mException = r0     // Catch: java.lang.Throwable -> L2f
            com.p226hw.videoprocessor.util.C5140CL.m13757e(r0)     // Catch: java.lang.Throwable -> L2f
            com.hw.videoprocessor.util.InputSurface r0 = r3.mInputSurface
            if (r0 == 0) goto L3e
            r0.release()
        L3e:
            com.hw.videoprocessor.util.OutputSurface r0 = r3.mOutputSurface
            if (r0 == 0) goto L45
            r0.release()
        L45:
            android.media.MediaCodec r0 = r3.mDecoder     // Catch: java.lang.Exception -> L54
            if (r0 == 0) goto L5a
            android.media.MediaCodec r0 = r3.mDecoder     // Catch: java.lang.Exception -> L54
            r0.stop()     // Catch: java.lang.Exception -> L54
            android.media.MediaCodec r0 = r3.mDecoder     // Catch: java.lang.Exception -> L54
            r0.release()     // Catch: java.lang.Exception -> L54
            goto L5a
        L54:
            r0 = move-exception
            java.lang.Exception r1 = r3.mException
            if (r1 != 0) goto L29
            goto L28
        L5a:
            return
        L5b:
            com.hw.videoprocessor.util.InputSurface r1 = r3.mInputSurface
            if (r1 == 0) goto L62
            r1.release()
        L62:
            com.hw.videoprocessor.util.OutputSurface r1 = r3.mOutputSurface
            if (r1 == 0) goto L69
            r1.release()
        L69:
            android.media.MediaCodec r1 = r3.mDecoder     // Catch: java.lang.Exception -> L78
            if (r1 == 0) goto L83
            android.media.MediaCodec r1 = r3.mDecoder     // Catch: java.lang.Exception -> L78
            r1.stop()     // Catch: java.lang.Exception -> L78
            android.media.MediaCodec r1 = r3.mDecoder     // Catch: java.lang.Exception -> L78
            r1.release()     // Catch: java.lang.Exception -> L78
            goto L83
        L78:
            r1 = move-exception
            java.lang.Exception r2 = r3.mException
            if (r2 != 0) goto L7e
            r2 = r1
        L7e:
            r3.mException = r2
            com.p226hw.videoprocessor.util.C5140CL.m13757e(r1)
        L83:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.VideoDecodeThread.run():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012a  */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doDecode() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 800
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.VideoDecodeThread.doDecode():void");
    }

    public Exception getException() {
        return this.mException;
    }
}

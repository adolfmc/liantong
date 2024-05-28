package com.sinovatech.unicom.separatemodule.huotijiance;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.media.Image;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Environment;
import android.view.Surface;
import android.view.SurfaceControl;
import com.sinovatech.unicom.hub.utils.MsLogUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class VideoMarkUtils {
    private static final String OUTPUT_FILE = Environment.getExternalStorageDirectory() + "/output.mp4";
    private static final int OUTPUT_VIDEO_BIT_RATE = 5000000;
    private static final int OUTPUT_VIDEO_FRAME_RATE = 30;
    private static final int OUTPUT_VIDEO_IFRAME_INTERVAL = 5;
    private static final String OUTPUT_VIDEO_MIME_TYPE = "video/avc";

    public static List<Bitmap> extractFrames(String str) {
        ArrayList arrayList = new ArrayList();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        int parseLong = ((int) (Long.parseLong(mediaMetadataRetriever.extractMetadata(9)) / 1000)) * 10;
        for (int i = 0; i < parseLong; i++) {
            Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(((i * 1000) * 1000) / 10);
            if (frameAtTime != null) {
                arrayList.add(frameAtTime);
            }
        }
        return arrayList;
    }

    public static void combineBitmapsToVideo(List<Bitmap> list) throws IOException {
        MediaMuxer mediaMuxer;
        MediaCodec mediaCodec;
        MediaFormat createVideoFormat;
        try {
            int width = list.get(0).getWidth();
            int height = list.get(0).getHeight();
            mediaMuxer = new MediaMuxer(OUTPUT_FILE, 0);
            try {
                createVideoFormat = MediaFormat.createVideoFormat("video/avc", width, height);
                createVideoFormat.setInteger("frame-rate", 30);
                createVideoFormat.setInteger("bitrate", 5000000);
                createVideoFormat.setInteger("i-frame-interval", 5);
                createVideoFormat.setInteger("color-format", 2130708361);
                mediaCodec = MediaCodec.createEncoderByType("video/avc");
            } catch (Throwable th) {
                th = th;
                mediaCodec = null;
            }
            try {
                mediaCodec.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                mediaCodec.start();
                MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                int createInputSurfaceTexture = createInputSurfaceTexture(width, height);
                if (Build.VERSION.SDK_INT >= 30) {
                    mediaCodec.setInputSurface(new Surface(new SurfaceControl.Builder().setName("input surface").setBufferSize(width, height).setFormat(1).build()));
                } else {
                    SurfaceTexture surfaceTexture = new SurfaceTexture(createInputSurfaceTexture);
                    surfaceTexture.setDefaultBufferSize(width, height);
                    mediaCodec.setInputSurface(new Surface(surfaceTexture));
                }
                int addTrack = mediaMuxer.addTrack(createVideoFormat);
                mediaMuxer.start();
                for (Bitmap bitmap : list) {
                    drawBitmapToSurface(bitmap, createInputSurfaceTexture);
                    drainEncoder(mediaCodec, bufferInfo, mediaMuxer, addTrack);
                }
                drainEncoder(mediaCodec, bufferInfo, mediaMuxer, addTrack);
                if (mediaCodec != null) {
                    mediaCodec.stop();
                    mediaCodec.release();
                }
                mediaMuxer.stop();
                mediaMuxer.release();
            } catch (Throwable th2) {
                th = th2;
                if (mediaCodec != null) {
                    mediaCodec.stop();
                    mediaCodec.release();
                }
                if (mediaMuxer != null) {
                    mediaMuxer.stop();
                    mediaMuxer.release();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            mediaMuxer = null;
            mediaCodec = null;
        }
    }

    private static int createInputSurfaceTexture(int i, int i2) {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        int[] iArr = new int[2];
        EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1);
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        EGL14.eglChooseConfig(eglGetDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344}, 0, eGLConfigArr, 0, eGLConfigArr.length, new int[1], 0);
        EGLContext eglCreateContext = EGL14.eglCreateContext(eglGetDisplay, eGLConfigArr[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eglGetDisplay, eGLConfigArr[0], new int[]{12375, i, 12374, i2, 12344}, 0);
        EGL14.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext);
        int[] iArr2 = new int[1];
        GLES20.glGenTextures(1, iArr2, 0);
        GLES20.glBindTexture(36197, iArr2[0]);
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        return iArr2[0];
    }

    private static void drawBitmapToSurface(Bitmap bitmap, int i) {
        SurfaceTexture surfaceTexture = new SurfaceTexture(i);
        surfaceTexture.setDefaultBufferSize(bitmap.getWidth(), bitmap.getHeight());
        Surface surface = new Surface(surfaceTexture);
        try {
            Canvas lockCanvas = surface.lockCanvas(null);
            lockCanvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            surface.unlockCanvasAndPost(lockCanvas);
        } finally {
            surface.release();
            surfaceTexture.release();
        }
    }

    private static void drainEncoder(MediaCodec mediaCodec, MediaCodec.BufferInfo bufferInfo, MediaMuxer mediaMuxer, int i) {
        ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
        while (true) {
            int dequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, 0L);
            if (dequeueOutputBuffer == -1) {
                return;
            }
            if (dequeueOutputBuffer == -2) {
                i = mediaMuxer.addTrack(mediaCodec.getOutputFormat());
                mediaMuxer.start();
            } else if (dequeueOutputBuffer >= 0) {
                mediaMuxer.writeSampleData(i, outputBuffers[dequeueOutputBuffer], bufferInfo);
                mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                if ((bufferInfo.flags & 4) != 0) {
                    return;
                }
            } else {
                continue;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0023, code lost:
        r0.selectTrack(r2);
        r6 = android.media.MediaCodec.createDecoderByType(r4);
        r6.configure(r3, (android.view.Surface) null, (android.media.MediaCrypto) null, 0);
        r6.start();
        decodeFrames(r0, r6);
        r6.stop();
        r6.release();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void getAllFrames(java.lang.String r6) {
        /*
            android.media.MediaExtractor r0 = new android.media.MediaExtractor
            r0.<init>()
            r0.setDataSource(r6)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            int r6 = r0.getTrackCount()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            r1 = 0
            r2 = r1
        Le:
            if (r2 >= r6) goto L44
            android.media.MediaFormat r3 = r0.getTrackFormat(r2)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.lang.String r4 = "mime"
            java.lang.String r4 = r3.getString(r4)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.lang.String r5 = "video/"
            boolean r5 = r4.startsWith(r5)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            if (r5 == 0) goto L3b
            r0.selectTrack(r2)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            android.media.MediaCodec r6 = android.media.MediaCodec.createDecoderByType(r4)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            r2 = 0
            r6.configure(r3, r2, r2, r1)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            r6.start()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            decodeFrames(r0, r6)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            r6.stop()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            r6.release()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            goto L44
        L3b:
            int r2 = r2 + 1
            goto Le
        L3e:
            r6 = move-exception
            goto L48
        L40:
            r6 = move-exception
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L3e
        L44:
            r0.release()
            return
        L48:
            r0.release()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.huotijiance.VideoMarkUtils.getAllFrames(java.lang.String):void");
    }

    private static void decodeFrames(MediaExtractor mediaExtractor, MediaCodec mediaCodec) {
        long j;
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        ByteBuffer[] inputBuffers = mediaCodec.getInputBuffers();
        ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
        ArrayList<Bitmap> arrayList = new ArrayList();
        while (true) {
            int dequeueInputBuffer = mediaCodec.dequeueInputBuffer(-1L);
            j = 0;
            if (dequeueInputBuffer >= 0) {
                int readSampleData = mediaExtractor.readSampleData(inputBuffers[dequeueInputBuffer], 0);
                if (readSampleData < 0) {
                    mediaCodec.queueInputBuffer(dequeueInputBuffer, 0, 0, 0L, 4);
                    break;
                } else {
                    mediaCodec.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, mediaExtractor.getSampleTime(), 0);
                    mediaExtractor.advance();
                }
            }
            int dequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, 0L);
            if (dequeueOutputBuffer >= 0) {
                ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                arrayList.add(YUVToBitmap(mediaCodec.getOutputImage(dequeueOutputBuffer)));
                mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                if ((bufferInfo.flags & 4) != 0) {
                    break;
                }
            }
        }
        try {
            MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", 640, 480);
            createVideoFormat.setInteger("color-format", 2130708361);
            createVideoFormat.setInteger("bitrate", 221184000);
            createVideoFormat.setInteger("frame-rate", 30);
            createVideoFormat.setInteger("i-frame-interval", 1);
            MediaMuxer mediaMuxer = new MediaMuxer(OUTPUT_FILE, 0);
            int addTrack = mediaMuxer.addTrack(createVideoFormat);
            mediaMuxer.start();
            for (Bitmap bitmap : arrayList) {
                ByteBuffer convertBitmapToYUV = convertBitmapToYUV(bitmap);
                MediaCodec.BufferInfo bufferInfo2 = new MediaCodec.BufferInfo();
                bufferInfo2.presentationTimeUs = j;
                bufferInfo2.size = convertBitmapToYUV.limit();
                bufferInfo2.offset = 0;
                bufferInfo2.flags = 1;
                mediaMuxer.writeSampleData(addTrack, convertBitmapToYUV, bufferInfo2);
                j += 33333;
            }
            mediaMuxer.stop();
            mediaMuxer.release();
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7999d("异常", e.getMessage());
        }
    }

    private static long computePresentationTime(int i, int i2) {
        return (i * 1000000) / i2;
    }

    private static ByteBuffer convertBitmapToYUV(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[(i * 3) / 2];
        encodeYUV420SP(bArr, iArr, width, height);
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
        allocate.put(bArr);
        allocate.flip();
        return allocate;
    }

    private static void encodeYUV420SP(byte[] bArr, int[] iArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i2) {
            int i7 = i3;
            int i8 = i5;
            int i9 = 0;
            while (i9 < i) {
                int i10 = iArr[i6];
                int i11 = (iArr[i6] & 16711680) >> 16;
                int i12 = (iArr[i6] & 65280) >> 8;
                int i13 = iArr[i6] & 255;
                int i14 = (((((i11 * (-38)) - (i12 * 74)) + (i13 * 112)) + 128) >> 8) + 128;
                int i15 = (((((i11 * 112) - (i12 * 94)) - (i13 * 18)) + 128) >> 8) + 128;
                int i16 = i8 + 1;
                bArr[i8] = (byte) clamp((((((i11 * 66) + (i12 * 129)) + (i13 * 25)) + 128) >> 8) + 16, 0, 255);
                if (i4 % 2 == 0 && i6 % 2 == 0) {
                    int i17 = i7 + 1;
                    bArr[i7] = (byte) clamp(i14, 0, 255);
                    i7 = i17 + 1;
                    bArr[i17] = (byte) clamp(i15, 0, 255);
                }
                i6++;
                i9++;
                i8 = i16;
            }
            i4++;
            i5 = i8;
            i3 = i7;
        }
    }

    private static int clamp(int i, int i2, int i3) {
        return Math.max(i2, Math.min(i, i3));
    }

    private static Bitmap YUVToBitmap(Image image) {
        if (image == null) {
            return null;
        }
        Image.Plane[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        ByteBuffer buffer2 = planes[1].getBuffer();
        ByteBuffer buffer3 = planes[2].getBuffer();
        int remaining = buffer.remaining();
        int remaining2 = buffer2.remaining();
        int remaining3 = buffer3.remaining();
        int i = remaining + remaining2;
        byte[] bArr = new byte[i + remaining3];
        buffer.get(bArr, 0, remaining);
        buffer2.get(bArr, remaining, remaining2);
        buffer3.get(bArr, i, remaining3);
        int[] iArr = new int[image.getWidth() * image.getHeight()];
        YUV420ToRGB(iArr, bArr, image.getWidth(), image.getHeight(), planes[0].getRowStride(), planes[1].getRowStride(), planes[1].getPixelStride());
        Bitmap createBitmap = Bitmap.createBitmap(iArr, image.getWidth(), image.getHeight(), Bitmap.Config.ARGB_8888);
        image.close();
        return createBitmap;
    }

    private static void YUV420ToRGB(int[] iArr, byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        int i7 = 0;
        while (i6 < i2) {
            int i8 = 0;
            int i9 = i7;
            while (i8 < i) {
                int i10 = bArr[(i6 * i3) + i8] & 255;
                int i11 = (i2 * i3) + ((i6 / 2) * i4) + ((i8 / 2) * i5);
                int i12 = bArr[i11 + 1] & 255;
                int i13 = bArr[i11] & 255;
                int i14 = (i13 * 1) + i10;
                int i15 = i10 - ((int) ((i12 * 0.344f) + (i13 * 0.714f)));
                iArr[i9] = Math.max(0, Math.min(255, i10 + (i12 * 1))) | (Math.max(0, Math.min(255, i15)) << 8) | (Math.max(0, Math.min(255, i14)) << 16) | (-16777216);
                i8++;
                i9++;
            }
            i6++;
            i7 = i9;
        }
    }

    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [android.graphics.Rect, android.graphics.Paint] */
    /* JADX WARN: Type inference failed for: r5v4 */
    public static void convert(List<Bitmap> list) throws IOException {
        long j;
        ByteBuffer byteBuffer;
        File file = new File(OUTPUT_FILE);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        Bitmap bitmap = list.get(0);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", width, height);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", width * height * 5);
        createVideoFormat.setInteger("frame-rate", 24);
        createVideoFormat.setInteger("i-frame-interval", 1);
        MediaCodec createEncoderByType = MediaCodec.createEncoderByType("video/avc");
        ?? r5 = 0;
        createEncoderByType.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        Surface createInputSurface = createEncoderByType.createInputSurface();
        createEncoderByType.start();
        MediaMuxer mediaMuxer = new MediaMuxer(file.getAbsolutePath(), 0);
        int addTrack = mediaMuxer.addTrack(createVideoFormat);
        mediaMuxer.start();
        long j2 = 0;
        for (Bitmap bitmap2 : list) {
            Canvas lockCanvas = createInputSurface.lockCanvas(r5);
            lockCanvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) r5);
            createEncoderByType.queueInputBuffer(0, 0, 0, j2, 4);
            while (true) {
                MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                j = 41666;
                int dequeueOutputBuffer = createEncoderByType.dequeueOutputBuffer(bufferInfo, j);
                if (dequeueOutputBuffer >= 0) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        byteBuffer = createEncoderByType.getOutputBuffer(dequeueOutputBuffer);
                    } else {
                        byteBuffer = createEncoderByType.getOutputBuffers()[dequeueOutputBuffer];
                    }
                    mediaMuxer.writeSampleData(addTrack, byteBuffer, bufferInfo);
                    createEncoderByType.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((bufferInfo.flags & 4) != 0) {
                        break;
                    }
                } else if (dequeueOutputBuffer == -1) {
                    break;
                }
            }
            createInputSurface.unlockCanvasAndPost(lockCanvas);
            j2 += j;
            r5 = 0;
        }
        createEncoderByType.stop();
        createEncoderByType.release();
        mediaMuxer.stop();
        mediaMuxer.release();
    }
}

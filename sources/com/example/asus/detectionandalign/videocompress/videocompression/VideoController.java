package com.example.asus.detectionandalign.videocompress.videocompression;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@SuppressLint({"NewApi"})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class VideoController {
    public static final int COMPRESS_QUALITY_HIGH = 1;
    public static final int COMPRESS_QUALITY_LOW = 3;
    public static final int COMPRESS_QUALITY_MEDIUM = 2;
    private static volatile VideoController Instance = null;
    public static final String MIME_TYPE = "video/avc";
    private static final int PROCESSOR_TYPE_INTEL = 2;
    private static final int PROCESSOR_TYPE_MTK = 3;
    private static final int PROCESSOR_TYPE_OTHER = 0;
    private static final int PROCESSOR_TYPE_QCOM = 1;
    private static final int PROCESSOR_TYPE_SEC = 4;
    private static final int PROCESSOR_TYPE_TI = 5;
    public static File cachedFile;
    public String path;
    private boolean videoConvertFirstWrite = true;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface CompressProgressListener {
        void onProgress(float f);
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class VideoConvertRunnable implements Runnable {
        private String destPath;
        private String videoPath;

        private VideoConvertRunnable(String str, String str2) {
            this.videoPath = str;
            this.destPath = str2;
        }

        public static void runConversion(final String str, final String str2) {
            new Thread(new Runnable() { // from class: com.example.asus.detectionandalign.videocompress.videocompression.VideoController.VideoConvertRunnable.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread thread = new Thread(new VideoConvertRunnable(str, str2), "VideoConvertRunnable");
                        thread.start();
                        thread.join();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("tmessages", e.getMessage());
                    }
                }
            }).start();
        }

        @Override // java.lang.Runnable
        public void run() {
            VideoController.getInstance().convertVideo(this.videoPath, this.destPath, 0, null);
        }
    }

    public static native int convertVideoFrame(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, int i3, int i4, int i5);

    public static void copyFile(File file, File file2) {
        FileChannel channel = new FileInputStream(file).getChannel();
        FileChannel channel2 = new FileOutputStream(file2).getChannel();
        try {
            channel.transferTo(1L, channel.size(), channel2);
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (channel2 != null) {
                channel2.close();
            }
        }
    }

    private void didWriteData(boolean z, boolean z2) {
        if (this.videoConvertFirstWrite) {
            this.videoConvertFirstWrite = false;
        }
    }

    public static VideoController getInstance() {
        VideoController videoController = Instance;
        if (videoController == null) {
            synchronized (VideoController.class) {
                videoController = Instance;
                if (videoController == null) {
                    videoController = new VideoController();
                    Instance = videoController;
                }
            }
        }
        return videoController;
    }

    private static boolean isRecognizedFormat(int i) {
        if (i == 39 || i == 2130706688) {
            return true;
        }
        switch (i) {
            case 19:
            case 20:
            case 21:
                return true;
            default:
                return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0088, code lost:
        if (r10 == (-1)) goto L13;
     */
    @android.annotation.TargetApi(16)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private long readAndWriteTrack(android.media.MediaExtractor r22, com.example.asus.detectionandalign.videocompress.videocompression.MP4Builder r23, android.media.MediaCodec.BufferInfo r24, long r25, long r27, java.io.File r29, boolean r30) {
        /*
            r21 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            r5 = r21
            r6 = r30
            int r7 = r5.selectTrack(r0, r6)
            r8 = -1
            if (r7 < 0) goto L9b
            r0.selectTrack(r7)
            android.media.MediaFormat r10 = r0.getTrackFormat(r7)
            int r11 = r1.addTrack(r10, r6)
            java.lang.String r12 = "max-input-size"
            int r10 = r10.getInteger(r12)
            r12 = 0
            int r14 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            r15 = 0
            if (r14 <= 0) goto L30
            r0.seekTo(r3, r15)
            goto L33
        L30:
            r0.seekTo(r12, r15)
        L33:
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocateDirect(r10)
            r16 = r8
            r4 = r15
        L3a:
            if (r4 != 0) goto L97
            int r10 = r22.getSampleTrackIndex()
            r18 = 1
            if (r10 != r7) goto L85
            int r10 = r0.readSampleData(r3, r15)
            r2.size = r10
            int r10 = r2.size
            if (r10 >= 0) goto L53
            r2.size = r15
            r19 = r12
            goto L8a
        L53:
            long r12 = r22.getSampleTime()
            r2.presentationTimeUs = r12
            if (r14 <= 0) goto L62
            int r10 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r10 != 0) goto L62
            long r12 = r2.presentationTimeUs
            goto L64
        L62:
            r12 = r16
        L64:
            r19 = 0
            int r10 = (r27 > r19 ? 1 : (r27 == r19 ? 0 : -1))
            if (r10 < 0) goto L74
            long r8 = r2.presentationTimeUs
            int r8 = (r8 > r27 ? 1 : (r8 == r27 ? 0 : -1))
            if (r8 >= 0) goto L71
            goto L74
        L71:
            r16 = r12
            goto L8a
        L74:
            r2.offset = r15
            int r8 = r22.getSampleFlags()
            r2.flags = r8
            r1.writeSampleData(r11, r3, r2, r6)
            r22.advance()
            r16 = r12
            goto L8d
        L85:
            r19 = r12
            r8 = -1
            if (r10 != r8) goto L8d
        L8a:
            r8 = r18
            goto L8e
        L8d:
            r8 = r15
        L8e:
            if (r8 == 0) goto L92
            r4 = r18
        L92:
            r12 = r19
            r8 = -1
            goto L3a
        L97:
            r0.unselectTrack(r7)
            return r16
        L9b:
            r0 = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.videocompress.videocompression.VideoController.readAndWriteTrack(android.media.MediaExtractor, com.example.asus.detectionandalign.videocompress.videocompression.MP4Builder, android.media.MediaCodec$BufferInfo, long, long, java.io.File, boolean):long");
    }

    public static MediaCodecInfo selectCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        MediaCodecInfo mediaCodecInfo = null;
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                MediaCodecInfo mediaCodecInfo2 = mediaCodecInfo;
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        if (!codecInfoAt.getName().equals("OMX.SEC.avc.enc") || codecInfoAt.getName().equals("OMX.SEC.AVC.Encoder")) {
                            return codecInfoAt;
                        }
                        mediaCodecInfo2 = codecInfoAt;
                    }
                }
                mediaCodecInfo = mediaCodecInfo2;
            }
        }
        return mediaCodecInfo;
    }

    @SuppressLint({"NewApi"})
    public static int selectColorFormat(MediaCodecInfo mediaCodecInfo, String str) {
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
        int i = 0;
        for (int i2 = 0; i2 < capabilitiesForType.colorFormats.length; i2++) {
            int i3 = capabilitiesForType.colorFormats[i2];
            if (isRecognizedFormat(i3)) {
                if (!mediaCodecInfo.getName().equals("OMX.SEC.AVC.Encoder") || i3 != 19) {
                    return i3;
                }
                i = i3;
            }
        }
        return i;
    }

    @TargetApi(16)
    private int selectTrack(MediaExtractor mediaExtractor, boolean z) {
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            String string = mediaExtractor.getTrackFormat(i).getString("mime");
            if (z) {
                if (string.startsWith("audio/")) {
                    return i;
                }
            } else if (string.startsWith("video/")) {
                return i;
            }
        }
        return -5;
    }

    private void startVideoConvertFromQueue(String str, String str2) {
        VideoConvertRunnable.runConversion(str, str2);
    }

    /*  JADX ERROR: Failed to decode insn: 0x0438: UNKNOWN(0xCB3F), method: com.example.asus.detectionandalign.videocompress.videocompression.VideoController.convertVideo(java.lang.String, java.lang.String, int, com.example.asus.detectionandalign.videocompress.videocompression.VideoController$CompressProgressListener):boolean
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0438: UNKNOWN(0xCB3F)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    @android.annotation.TargetApi(16)
    public boolean convertVideo(java.lang.String r51, java.lang.String r52, int r53, com.example.asus.detectionandalign.videocompress.videocompression.VideoController.CompressProgressListener r54) {
        /*
            Method dump skipped, instructions count: 2534
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.videocompress.videocompression.VideoController.convertVideo(java.lang.String, java.lang.String, int, com.example.asus.detectionandalign.videocompress.videocompression.VideoController$CompressProgressListener):boolean");
    }

    public void scheduleVideoConvert(String str, String str2) {
        startVideoConvertFromQueue(str, str2);
    }
}

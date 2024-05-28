package com.p226hw.videoprocessor.util;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import android.view.Surface;
import com.p226hw.videoprocessor.VideoProcessor;
import com.p226hw.videoprocessor.VideoUtil;
import com.p226hw.videoprocessor.jssrc.SSRC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hw.videoprocessor.util.AudioUtil */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AudioUtil {
    static final String TAG = "VideoProcessor";
    public static int VOLUMN_MAX_RATIO;
    private static final Map<Integer, Integer> freqIdxMap = new HashMap();

    static {
        freqIdxMap.put(96000, 0);
        freqIdxMap.put(88200, 1);
        freqIdxMap.put(64000, 2);
        freqIdxMap.put(48000, 3);
        freqIdxMap.put(44100, 4);
        freqIdxMap.put(32000, 5);
        freqIdxMap.put(24000, 6);
        freqIdxMap.put(22050, 7);
        freqIdxMap.put(16000, 8);
        freqIdxMap.put(12000, 9);
        freqIdxMap.put(11025, 10);
        freqIdxMap.put(8000, 11);
        freqIdxMap.put(7350, 12);
        VOLUMN_MAX_RATIO = 1;
    }

    public static void adjustPcmVolume(String str, String str2, int i) throws IOException {
        if (i == 100) {
            copyFile(str, str2);
            return;
        }
        float normalizeVolume = normalizeVolume(i);
        byte[] bArr = new byte[2048];
        FileInputStream fileInputStream = new FileInputStream(str);
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        while (fileInputStream.read(bArr) != -1) {
            try {
                for (int i2 = 0; i2 < bArr.length; i2 += 2) {
                    int i3 = i2 + 1;
                    int i4 = (int) (((short) ((bArr[i2] & 255) | ((bArr[i3] & 255) << 8))) * normalizeVolume);
                    if (i4 > 32767) {
                        i4 = 32767;
                    } else if (i4 < -32768) {
                        i4 = -32768;
                    }
                    bArr[i2] = (byte) (i4 & 255);
                    bArr[i3] = (byte) ((i4 >>> 8) & 255);
                }
                fileOutputStream.write(bArr);
            } finally {
                fileInputStream.close();
                fileOutputStream.close();
            }
        }
    }

    public static void adjustAacVolume(Context context, VideoProcessor.MediaSource mediaSource, String str, int i, @Nullable VideoProgressListener videoProgressListener) throws IOException {
        String str2 = "temp_aac_" + System.currentTimeMillis();
        File file = new File(VideoUtil.getVideoCacheDir(context), str2 + ".pcm");
        File file2 = new File(VideoUtil.getVideoCacheDir(context), str2 + "_2.pcm");
        File file3 = new File(VideoUtil.getVideoCacheDir(context), str2 + ".wav");
        decodeToPCM(mediaSource, file.getAbsolutePath(), null, null);
        adjustPcmVolume(file.getAbsolutePath(), file2.getAbsolutePath(), i);
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaSource.setDataSource(mediaExtractor);
        MediaFormat trackFormat = mediaExtractor.getTrackFormat(VideoUtil.selectTrack(mediaExtractor, true));
        int integer = trackFormat.getInteger("sample-rate");
        int integer2 = trackFormat.getInteger("channel-count");
        new PcmToWavUtil(integer, integer2 == 2 ? 12 : 16, integer2, 2).pcmToWav(file2.getAbsolutePath(), file3.getAbsolutePath());
        encodeWAVToAAC(file3.getPath(), str, trackFormat, videoProgressListener);
    }

    private static float normalizeVolume(int i) {
        return (i / 100.0f) * VOLUMN_MAX_RATIO;
    }

    public static void mixPcm(String str, String str2, String str3, int i, int i2) throws IOException {
        float normalizeVolume = normalizeVolume(i);
        float normalizeVolume2 = normalizeVolume(i2);
        byte[] bArr = new byte[2048];
        byte[] bArr2 = new byte[2048];
        byte[] bArr3 = new byte[2048];
        FileInputStream fileInputStream = new FileInputStream(str);
        FileInputStream fileInputStream2 = new FileInputStream(str2);
        FileOutputStream fileOutputStream = new FileOutputStream(str3);
        boolean z = false;
        boolean z2 = false;
        while (true) {
            if (!z || !z2) {
                boolean z3 = true;
                if (!z) {
                    try {
                        z = fileInputStream.read(bArr) == -1;
                        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
                    } catch (Throwable th) {
                        fileInputStream.close();
                        fileInputStream2.close();
                        fileOutputStream.close();
                        throw th;
                    }
                }
                if (!z2) {
                    if (fileInputStream2.read(bArr2) != -1) {
                        z3 = false;
                    }
                    for (int i3 = 0; i3 < bArr2.length; i3 += 2) {
                        int i4 = i3 + 1;
                        int i5 = (int) ((((short) ((bArr2[i3] & 255) | ((bArr2[i4] & 255) << 8))) * normalizeVolume2) + (((short) ((bArr[i3] & 255) | ((bArr[i4] & 255) << 8))) * normalizeVolume));
                        if (i5 > 32767) {
                            i5 = 32767;
                        } else if (i5 < -32768) {
                            i5 = -32768;
                        }
                        bArr3[i3] = (byte) (i5 & 255);
                        bArr3[i4] = (byte) ((i5 >>> 8) & 255);
                    }
                    z2 = z3;
                }
                fileOutputStream.write(bArr3);
            } else {
                fileInputStream.close();
                fileInputStream2.close();
                fileOutputStream.close();
                return;
            }
        }
    }

    public static void stereoToMono(String str, String str2) throws IOException {
        stereoToMonoSimple(str, str2, 2);
    }

    public static void stereoToMonoSimple(String str, String str2, int i) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        byte[] bArr = new byte[i * 1024];
        byte[] bArr2 = new byte[1024];
        while (fileInputStream.read(bArr) != -1) {
            for (int i2 = 0; i2 < bArr2.length; i2 += 2) {
                int i3 = i * i2;
                bArr2[i2] = bArr[i3];
                bArr2[i2 + 1] = bArr[i3 + 1];
            }
            fileOutputStream.write(bArr2);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void copyFile(VideoProcessor.MediaSource mediaSource, String str) throws IOException {
        if (mediaSource.inputPath != null) {
            copyFile(mediaSource.inputPath, str);
            return;
        }
        ParcelFileDescriptor openFileDescriptor = mediaSource.context.getContentResolver().openFileDescriptor(mediaSource.inputUri, "rw");
        FileChannel channel = new FileOutputStream(str).getChannel();
        FileChannel channel2 = new FileInputStream(openFileDescriptor.getFileDescriptor()).getChannel();
        channel2.transferTo(0L, channel2.size(), channel);
    }

    public static void copyFile(String str, String str2) throws IOException {
        FileChannel channel = new FileOutputStream(str2).getChannel();
        FileChannel channel2 = new FileInputStream(str).getChannel();
        channel2.transferTo(0L, channel2.size(), channel);
    }

    public static boolean isStereo(String str) throws IOException {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.setDataSource(str);
        int trackCount = mediaExtractor.getTrackCount();
        MediaFormat mediaFormat = null;
        for (int i = 0; i < trackCount; i++) {
            mediaFormat = mediaExtractor.getTrackFormat(i);
            if (mediaFormat.getString("mime").startsWith("audio/")) {
                break;
            }
        }
        mediaExtractor.release();
        return mediaFormat != null && mediaFormat.getInteger("channel-count") > 1;
    }

    public static Pair<Integer, Integer> checkAndAdjustAudioFormat(String str, String str2, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
        int i;
        int integer = mediaFormat.containsKey("channel-count") ? mediaFormat.getInteger("channel-count") : 1;
        int integer2 = mediaFormat2.containsKey("channel-count") ? mediaFormat2.getInteger("channel-count") : 1;
        int integer3 = mediaFormat.containsKey("sample-rate") ? mediaFormat.getInteger("sample-rate") : 44100;
        int integer4 = mediaFormat2.containsKey("sample-rate") ? mediaFormat2.getInteger("sample-rate") : 44100;
        if (integer == integer2 && integer3 == integer4 && integer <= 2) {
            return new Pair<>(Integer.valueOf(integer), Integer.valueOf(integer3));
        }
        File file = new File(str + ".temp");
        File file2 = new File(str2 + ".temp");
        try {
            if (integer != integer2 || integer > 2 || integer2 > 2) {
                if (integer > 1) {
                    try {
                        stereoToMonoSimple(str, file.getAbsolutePath(), integer);
                        File file3 = new File(str);
                        file3.delete();
                        file.renameTo(file3);
                        i = 1;
                    } catch (Exception e) {
                        e = e;
                        C5140CL.m13757e(e);
                        Pair<Integer, Integer> pair = new Pair<>(Integer.valueOf(integer), Integer.valueOf(integer3));
                        file.delete();
                        file2.exists();
                        return pair;
                    }
                } else {
                    i = integer;
                }
                if (integer2 > 1) {
                    stereoToMonoSimple(str2, file2.getAbsolutePath(), integer2);
                    File file4 = new File(str2);
                    file4.delete();
                    file2.renameTo(file4);
                    integer2 = 1;
                }
                integer = 1;
            } else {
                i = integer;
            }
            if (integer3 != integer4) {
                int min = Math.min(integer3, integer4);
                if (integer3 != min) {
                    try {
                        reSamplePcm(str, file.getAbsolutePath(), integer3, min, i);
                        File file5 = new File(str);
                        file5.delete();
                        file.renameTo(file5);
                    } catch (Exception e2) {
                        e = e2;
                        integer3 = min;
                        C5140CL.m13757e(e);
                        Pair<Integer, Integer> pair2 = new Pair<>(Integer.valueOf(integer), Integer.valueOf(integer3));
                        file.delete();
                        file2.exists();
                        return pair2;
                    }
                }
                if (integer4 != min) {
                    reSamplePcm(str2, file2.getAbsolutePath(), integer4, min, integer2);
                    File file6 = new File(str2);
                    file6.delete();
                    file2.renameTo(file6);
                }
                integer3 = min;
            }
            Pair<Integer, Integer> pair3 = new Pair<>(Integer.valueOf(integer), Integer.valueOf(integer3));
            file.delete();
            file2.exists();
            return pair3;
        } catch (Throwable th) {
            file.delete();
            file2.exists();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00cf A[Catch: IOException -> 0x00cb, TryCatch #8 {IOException -> 0x00cb, blocks: (B:59:0x00c7, B:63:0x00cf, B:65:0x00d4, B:67:0x00d9), top: B:75:0x00c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d4 A[Catch: IOException -> 0x00cb, TryCatch #8 {IOException -> 0x00cb, blocks: (B:59:0x00c7, B:63:0x00cf, B:65:0x00d4, B:67:0x00d9), top: B:75:0x00c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d9 A[Catch: IOException -> 0x00cb, TRY_LEAVE, TryCatch #8 {IOException -> 0x00cb, blocks: (B:59:0x00c7, B:63:0x00cf, B:65:0x00d4, B:67:0x00d9), top: B:75:0x00c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.FileOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.File checkAndFillPcm(java.io.File r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.util.AudioUtil.checkAndFillPcm(java.io.File, int, int):java.io.File");
    }

    public static void decodeToPCM(VideoProcessor.MediaSource mediaSource, String str, Integer num, Integer num2) throws IOException {
        boolean z;
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaSource.setDataSource(mediaExtractor);
        int selectTrack = VideoUtil.selectTrack(mediaExtractor, true);
        mediaExtractor.selectTrack(selectTrack);
        Integer num3 = num == null ? 0 : num;
        mediaExtractor.seekTo(num3.intValue(), 2);
        MediaFormat trackFormat = mediaExtractor.getTrackFormat(selectTrack);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(trackFormat.containsKey("max-input-size") ? trackFormat.getInteger("max-input-size") : 100000);
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        MediaCodec createDecoderByType = MediaCodec.createDecoderByType(trackFormat.getString("mime"));
        createDecoderByType.configure(trackFormat, (Surface) null, (MediaCrypto) null, 0);
        createDecoderByType.start();
        FileChannel channel = new FileOutputStream(new File(str)).getChannel();
        boolean z2 = false;
        boolean z3 = false;
        while (!z2) {
            if (!z3) {
                try {
                    int dequeueInputBuffer = createDecoderByType.dequeueInputBuffer(2500L);
                    if (dequeueInputBuffer >= 0) {
                        long sampleTime = mediaExtractor.getSampleTime();
                        if (sampleTime == -1) {
                            z = true;
                        } else if (sampleTime < num3.intValue()) {
                            mediaExtractor.advance();
                        } else {
                            z = num2 != null && sampleTime > ((long) num2.intValue());
                        }
                        if (z) {
                            createDecoderByType.queueInputBuffer(dequeueInputBuffer, 0, 0, 0L, 4);
                            z3 = true;
                        } else {
                            bufferInfo.size = mediaExtractor.readSampleData(allocateDirect, 0);
                            bufferInfo.presentationTimeUs = sampleTime;
                            bufferInfo.flags = mediaExtractor.getSampleFlags();
                            createDecoderByType.getInputBuffer(dequeueInputBuffer).put(allocateDirect);
                            C5140CL.m13753it("VideoProcessor", "audio decode queueInputBuffer " + (bufferInfo.presentationTimeUs / 1000), new Object[0]);
                            createDecoderByType.queueInputBuffer(dequeueInputBuffer, 0, bufferInfo.size, bufferInfo.presentationTimeUs, bufferInfo.flags);
                            mediaExtractor.advance();
                        }
                    }
                } catch (Throwable th) {
                    channel.close();
                    mediaExtractor.release();
                    createDecoderByType.stop();
                    createDecoderByType.release();
                    throw th;
                }
            }
            while (!z2) {
                int dequeueOutputBuffer = createDecoderByType.dequeueOutputBuffer(bufferInfo, 2500L);
                if (dequeueOutputBuffer == -1) {
                    break;
                } else if (dequeueOutputBuffer == -2) {
                    C5140CL.m13753it("VideoProcessor", "audio decode newFormat = " + createDecoderByType.getOutputFormat(), new Object[0]);
                } else if (dequeueOutputBuffer < 0) {
                    C5140CL.m13756et("VideoProcessor", "unexpected result from audio decoder.dequeueOutputBuffer: " + dequeueOutputBuffer, new Object[0]);
                } else {
                    if (bufferInfo.flags == 4) {
                        z2 = true;
                    } else {
                        ByteBuffer outputBuffer = createDecoderByType.getOutputBuffer(dequeueOutputBuffer);
                        C5140CL.m13753it("VideoProcessor", "audio decode saveFrame " + (bufferInfo.presentationTimeUs / 1000), new Object[0]);
                        channel.write(outputBuffer);
                    }
                    createDecoderByType.releaseOutputBuffer(dequeueOutputBuffer, false);
                }
            }
        }
        channel.close();
        mediaExtractor.release();
        createDecoderByType.stop();
        createDecoderByType.release();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01e1 A[Catch: all -> 0x0244, TryCatch #1 {all -> 0x0244, blocks: (B:34:0x01b9, B:38:0x01e1, B:40:0x0203, B:42:0x0208, B:43:0x020a, B:45:0x020f, B:51:0x0225, B:53:0x022f), top: B:69:0x01b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0208 A[Catch: all -> 0x0244, TryCatch #1 {all -> 0x0244, blocks: (B:34:0x01b9, B:38:0x01e1, B:40:0x0203, B:42:0x0208, B:43:0x020a, B:45:0x020f, B:51:0x0225, B:53:0x022f), top: B:69:0x01b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x020f A[Catch: all -> 0x0244, TryCatch #1 {all -> 0x0244, blocks: (B:34:0x01b9, B:38:0x01e1, B:40:0x0203, B:42:0x0208, B:43:0x020a, B:45:0x020f, B:51:0x0225, B:53:0x022f), top: B:69:0x01b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x022d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void encodeWAVToAAC(java.lang.String r33, java.lang.String r34, android.media.MediaFormat r35, @org.jetbrains.annotations.Nullable com.p226hw.videoprocessor.util.VideoProgressListener r36) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 606
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.util.AudioUtil.encodeWAVToAAC(java.lang.String, java.lang.String, android.media.MediaFormat, com.hw.videoprocessor.util.VideoProgressListener):void");
    }

    public static boolean reSamplePcm(String str, String str2, int i, int i2, int i3) {
        try {
            new SSRC(new FileInputStream(str), new FileOutputStream(str2), i, i2, 2, 2, i3, (int) new File(str).length(), 0.0d, 0, true);
            return true;
        } catch (IOException e) {
            C5140CL.m13757e(e);
            e.printStackTrace();
            return true;
        }
    }

    public static void reversePcm(String str, String str2) throws IOException {
        FileOutputStream fileOutputStream;
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(str, "r");
            try {
                fileOutputStream = new FileOutputStream(str2);
                try {
                    long j = 2;
                    byte[] bArr = new byte[2];
                    for (long length = randomAccessFile.length() - j; length >= 0; length -= j) {
                        randomAccessFile.seek(length);
                        randomAccessFile.read(bArr);
                        fileOutputStream.write(bArr);
                    }
                    randomAccessFile.close();
                    fileOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            randomAccessFile = null;
        }
    }

    public static long writeAudioTrack(MediaExtractor mediaExtractor, MediaMuxer mediaMuxer, int i, Integer num, Integer num2, VideoProgressListener videoProgressListener) throws IOException {
        return writeAudioTrack(mediaExtractor, mediaMuxer, i, num, num2, 0L, videoProgressListener);
    }

    public static long writeAudioTrack(MediaExtractor mediaExtractor, MediaMuxer mediaMuxer, int i, Integer num, Integer num2, long j, VideoProgressListener videoProgressListener) throws IOException {
        int selectTrack = VideoUtil.selectTrack(mediaExtractor, true);
        mediaExtractor.selectTrack(selectTrack);
        Integer num3 = num == null ? 0 : num;
        mediaExtractor.seekTo(num3.intValue(), 2);
        MediaFormat trackFormat = mediaExtractor.getTrackFormat(selectTrack);
        long j2 = trackFormat.getLong("durationUs");
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(trackFormat.getInteger("max-input-size"));
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        long j3 = j;
        while (true) {
            long sampleTime = mediaExtractor.getSampleTime();
            if (sampleTime == -1) {
                break;
            } else if (sampleTime < num3.intValue()) {
                mediaExtractor.advance();
            } else if (num2 != null && sampleTime > num2.intValue()) {
                break;
            } else {
                if (videoProgressListener != null) {
                    float intValue = ((float) (sampleTime - num3.intValue())) / ((float) (num2 == null ? j2 : num2.intValue() - num3.intValue()));
                    if (intValue < 0.0f) {
                        intValue = 0.0f;
                    }
                    if (intValue > 1.0f) {
                        intValue = 1.0f;
                    }
                    videoProgressListener.onProgress(intValue);
                }
                bufferInfo.presentationTimeUs = (sampleTime - num3.intValue()) + j;
                bufferInfo.flags = mediaExtractor.getSampleFlags();
                bufferInfo.size = mediaExtractor.readSampleData(allocateDirect, 0);
                if (bufferInfo.size < 0) {
                    break;
                }
                C5140CL.m13755i("writeAudioSampleData,time:" + (((float) bufferInfo.presentationTimeUs) / 1000.0f), new Object[0]);
                mediaMuxer.writeSampleData(i, allocateDirect, bufferInfo);
                long j4 = bufferInfo.presentationTimeUs;
                mediaExtractor.advance();
                j3 = j4;
            }
        }
        return j3;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0438 A[Catch: all -> 0x04ba, TryCatch #2 {all -> 0x04ba, blocks: (B:98:0x040f, B:102:0x0438, B:104:0x0458, B:106:0x045d, B:108:0x0469, B:110:0x046e, B:114:0x048a, B:120:0x0499, B:122:0x04a5, B:113:0x0480), top: B:138:0x040f }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x045d A[Catch: all -> 0x04ba, TryCatch #2 {all -> 0x04ba, blocks: (B:98:0x040f, B:102:0x0438, B:104:0x0458, B:106:0x045d, B:108:0x0469, B:110:0x046e, B:114:0x048a, B:120:0x0499, B:122:0x04a5, B:113:0x0480), top: B:138:0x040f }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x046e A[Catch: all -> 0x04ba, TryCatch #2 {all -> 0x04ba, blocks: (B:98:0x040f, B:102:0x0438, B:104:0x0458, B:106:0x045d, B:108:0x0469, B:110:0x046e, B:114:0x048a, B:120:0x0499, B:122:0x04a5, B:113:0x0480), top: B:138:0x040f }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x036f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0372  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void writeAudioTrackDecode(android.content.Context r33, android.media.MediaExtractor r34, android.media.MediaMuxer r35, int r36, java.lang.Integer r37, java.lang.Integer r38, @org.jetbrains.annotations.NotNull java.lang.Float r39, @org.jetbrains.annotations.Nullable com.p226hw.videoprocessor.util.VideoProgressListener r40) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.util.AudioUtil.writeAudioTrackDecode(android.content.Context, android.media.MediaExtractor, android.media.MediaMuxer, int, java.lang.Integer, java.lang.Integer, java.lang.Float, com.hw.videoprocessor.util.VideoProgressListener):void");
    }

    public static void removeAudioTrack(String str, String str2) throws IOException {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.setDataSource(str);
        try {
            int selectTrack = VideoUtil.selectTrack(mediaExtractor, false);
            mediaExtractor.selectTrack(selectTrack);
            MediaFormat trackFormat = mediaExtractor.getTrackFormat(selectTrack);
            MediaMuxer mediaMuxer = new MediaMuxer(str2, 0);
            int addTrack = mediaMuxer.addTrack(trackFormat);
            mediaMuxer.start();
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(trackFormat.getInteger("max-input-size"));
            while (true) {
                long sampleTime = mediaExtractor.getSampleTime();
                if (sampleTime != -1) {
                    int sampleFlags = mediaExtractor.getSampleFlags();
                    int readSampleData = mediaExtractor.readSampleData(allocateDirect, 0);
                    bufferInfo.presentationTimeUs = sampleTime;
                    bufferInfo.flags = sampleFlags;
                    bufferInfo.size = readSampleData;
                    mediaMuxer.writeSampleData(addTrack, allocateDirect, bufferInfo);
                    mediaExtractor.advance();
                } else {
                    mediaMuxer.stop();
                    mediaMuxer.release();
                    return;
                }
            }
        } finally {
            mediaExtractor.release();
        }
    }

    public static void replaceAudioTrack(String str, String str2, String str3, boolean z) throws IOException {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.setDataSource(str);
        MediaExtractor mediaExtractor2 = new MediaExtractor();
        mediaExtractor2.setDataSource(str2);
        try {
            int selectTrack = VideoUtil.selectTrack(mediaExtractor, false);
            int selectTrack2 = VideoUtil.selectTrack(mediaExtractor2, true);
            mediaExtractor.selectTrack(selectTrack);
            mediaExtractor2.selectTrack(selectTrack2);
            MediaFormat trackFormat = mediaExtractor2.getTrackFormat(selectTrack2);
            MediaFormat trackFormat2 = mediaExtractor.getTrackFormat(selectTrack);
            MediaMuxer mediaMuxer = new MediaMuxer(str3, 0);
            int addTrack = mediaMuxer.addTrack(trackFormat);
            int addTrack2 = mediaMuxer.addTrack(trackFormat2);
            mediaMuxer.start();
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(trackFormat2.getInteger("max-input-size"));
            long j = 0;
            long j2 = 0;
            while (true) {
                long sampleTime = mediaExtractor.getSampleTime();
                if (sampleTime == -1) {
                    break;
                }
                int sampleFlags = mediaExtractor.getSampleFlags();
                int readSampleData = mediaExtractor.readSampleData(allocateDirect, 0);
                bufferInfo.presentationTimeUs = sampleTime;
                bufferInfo.flags = sampleFlags;
                bufferInfo.size = readSampleData;
                mediaMuxer.writeSampleData(addTrack2, allocateDirect, bufferInfo);
                mediaExtractor.advance();
                j2 = sampleTime;
                j = 0;
            }
            int integer = 1024000000 / trackFormat.getInteger("sample-rate");
            ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(getAudioMaxBufferSize(trackFormat));
            long j3 = j;
            long j4 = j3;
            while (j3 < j2) {
                mediaExtractor2.seekTo(j, 2);
                while (true) {
                    long sampleTime2 = mediaExtractor2.getSampleTime();
                    if (sampleTime2 == -1) {
                        break;
                    }
                    j3 = sampleTime2 + j4;
                    if (j3 > j2) {
                        break;
                    }
                    int sampleFlags2 = mediaExtractor2.getSampleFlags();
                    int readSampleData2 = mediaExtractor2.readSampleData(allocateDirect2, 0);
                    bufferInfo.presentationTimeUs = j3;
                    bufferInfo.flags = sampleFlags2;
                    bufferInfo.size = readSampleData2;
                    mediaMuxer.writeSampleData(addTrack, allocateDirect2, bufferInfo);
                    mediaExtractor2.advance();
                }
                j4 = j3 + integer;
                if (!z) {
                    break;
                }
                j = 0;
            }
            mediaMuxer.stop();
            mediaMuxer.release();
        } finally {
            mediaExtractor.release();
            mediaExtractor2.release();
        }
    }

    public static int getAudioMaxBufferSize(MediaFormat mediaFormat) {
        if (mediaFormat.containsKey("max-input-size")) {
            return mediaFormat.getInteger("max-input-size");
        }
        return 100000;
    }

    public static int getAudioBitrate(MediaFormat mediaFormat) {
        if (mediaFormat.containsKey("bitrate")) {
            return mediaFormat.getInteger("bitrate");
        }
        return 192000;
    }

    public static void checkCsd(MediaFormat mediaFormat, int i, int i2, int i3) {
        int intValue = freqIdxMap.containsKey(Integer.valueOf(i2)) ? freqIdxMap.get(Integer.valueOf(i2)).intValue() : 4;
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.put(0, (byte) ((i << 3) | (intValue >> 1)));
        allocate.put(1, (byte) (((intValue & 1) << 7) | (i3 << 3)));
        mediaFormat.setByteBuffer("csd-0", allocate);
    }
}

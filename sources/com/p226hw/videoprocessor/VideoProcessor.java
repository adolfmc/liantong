package com.p226hw.videoprocessor;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import android.net.Uri;
import com.megvii.livenesslib.util.SDCardUtil;
import com.p226hw.videoprocessor.util.AudioUtil;
import com.p226hw.videoprocessor.util.C5140CL;
import com.p226hw.videoprocessor.util.VideoMultiStepProgress;
import com.p226hw.videoprocessor.util.VideoProgressAve;
import com.p226hw.videoprocessor.util.VideoProgressListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@TargetApi(21)
/* renamed from: com.hw.videoprocessor.VideoProcessor */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class VideoProcessor {
    public static boolean AUDIO_MIX_REPEAT = true;
    public static final int DEFAULT_AAC_BITRATE = 192000;
    public static int DEFAULT_FRAME_RATE = 20;
    public static final int DEFAULT_I_FRAME_INTERVAL = 1;
    static final String OUTPUT_MIME_TYPE = "video/avc";
    static final String TAG = "VideoProcessor";
    static final int TIMEOUT_USEC = 2500;

    public static void scaleVideo(Context context, Uri uri, String str, int i, int i2) throws Exception {
        processor(context).input(uri).output(str).outWidth(i).outHeight(i2).process();
    }

    public static void cutVideo(Context context, Uri uri, String str, int i, int i2) throws Exception {
        processor(context).input(uri).output(str).startTimeMs(i).endTimeMs(i2).process();
    }

    public static void changeVideoSpeed(Context context, Uri uri, String str, float f) throws Exception {
        processor(context).input(uri).output(str).speed(f).process();
    }

    public static void reverseVideo(Context context, MediaSource mediaSource, String str, boolean z, @Nullable VideoProgressListener videoProgressListener) throws Exception {
        File file = new File(SDCardUtil.getOwnFileUrl("Video"), System.currentTimeMillis() + ".temp");
        File file2 = new File(SDCardUtil.getOwnFileUrl("Video"), System.currentTimeMillis() + ".temp2");
        try {
            MediaExtractor mediaExtractor = new MediaExtractor();
            mediaSource.setDataSource(mediaExtractor);
            mediaExtractor.selectTrack(VideoUtil.selectTrack(mediaExtractor, false));
            ArrayList arrayList = new ArrayList();
            int i = 0;
            int i2 = 0;
            while (true) {
                int sampleFlags = mediaExtractor.getSampleFlags();
                if (sampleFlags > 0 && (sampleFlags & 1) != 0) {
                    i++;
                }
                long sampleTime = mediaExtractor.getSampleTime();
                if (sampleTime < 0) {
                    break;
                }
                arrayList.add(Long.valueOf(sampleTime));
                i2++;
                mediaExtractor.advance();
            }
            mediaExtractor.release();
            if (i2 != i && i2 != i + 1) {
                VideoMultiStepProgress videoMultiStepProgress = new VideoMultiStepProgress(new float[]{0.45f, 0.1f, 0.45f}, videoProgressListener);
                videoMultiStepProgress.setCurrentStep(0);
                float f = i;
                float f2 = ((i2 - i) / f) + 1.0f;
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaSource.setDataSource(mediaMetadataRetriever);
                int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(20));
                int parseInt2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
                try {
                    processor(context).input(mediaSource).output(file.getAbsolutePath()).bitrate((int) (parseInt * f2)).iFrameInterval(0).progressListener(videoMultiStepProgress).process();
                } catch (MediaCodec.CodecException e) {
                    C5140CL.m13757e(e);
                    processor(context).input(mediaSource).output(file.getAbsolutePath()).bitrate((int) (parseInt * f2)).iFrameInterval(-1).progressListener(videoMultiStepProgress).process();
                }
                videoMultiStepProgress.setCurrentStep(1);
                reverseVideoNoDecode(new MediaSource(file.getAbsolutePath()), file2.getAbsolutePath(), z, null, videoMultiStepProgress);
                int i3 = (int) (f / (parseInt2 / 1000.0f));
                if (i3 == 0) {
                    i3 = 1;
                }
                videoMultiStepProgress.setCurrentStep(2);
                processor(context).input(file2.getAbsolutePath()).output(str).bitrate(parseInt).iFrameInterval(i3).progressListener(videoMultiStepProgress).process();
            }
            reverseVideoNoDecode(mediaSource, str, z, arrayList, videoProgressListener);
        } finally {
            file.delete();
            file2.delete();
        }
    }

    public static void processVideo(@NotNull Context context, @NotNull Processor processor) throws Exception {
        int i;
        int i2;
        int i3;
        MediaMuxer mediaMuxer;
        Integer num;
        int i4;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        processor.input.setDataSource(mediaMetadataRetriever);
        int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
        int parseInt2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
        int parseInt3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
        int parseInt4 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(20));
        int parseInt5 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        mediaMetadataRetriever.release();
        if (processor.bitrate == null) {
            processor.bitrate = Integer.valueOf(parseInt4);
        }
        if (processor.iFrameInterval == null) {
            processor.iFrameInterval = 1;
        }
        if (processor.outWidth != null) {
            parseInt = processor.outWidth.intValue();
        }
        if (processor.outHeight != null) {
            parseInt2 = processor.outHeight.intValue();
        }
        if (parseInt % 2 != 0) {
            parseInt++;
        }
        if (parseInt2 % 2 != 0) {
            parseInt2++;
        }
        if (parseInt3 == 90 || parseInt3 == 270) {
            i = parseInt;
            i2 = parseInt2;
        } else {
            i2 = parseInt;
            i = parseInt2;
        }
        MediaExtractor mediaExtractor = new MediaExtractor();
        processor.input.setDataSource(mediaExtractor);
        int selectTrack = VideoUtil.selectTrack(mediaExtractor, false);
        int selectTrack2 = VideoUtil.selectTrack(mediaExtractor, true);
        MediaMuxer mediaMuxer2 = new MediaMuxer(processor.output, 0);
        boolean booleanValue = processor.changeAudioSpeed == null ? true : processor.changeAudioSpeed.booleanValue();
        Integer num2 = processor.endTimeMs;
        if (selectTrack2 >= 0) {
            MediaFormat trackFormat = mediaExtractor.getTrackFormat(selectTrack2);
            int audioBitrate = AudioUtil.getAudioBitrate(trackFormat);
            int integer = trackFormat.getInteger("channel-count");
            int integer2 = trackFormat.getInteger("sample-rate");
            int audioMaxBufferSize = AudioUtil.getAudioMaxBufferSize(trackFormat);
            MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", integer2, integer);
            createAudioFormat.setInteger("bitrate", audioBitrate);
            createAudioFormat.setInteger("aac-profile", 2);
            createAudioFormat.setInteger("max-input-size", audioMaxBufferSize);
            if (!booleanValue) {
                mediaMuxer = mediaMuxer2;
                long j = parseInt5 * 1000;
                long j2 = trackFormat.getLong("durationUs");
                if (processor.startTimeMs != null || processor.endTimeMs != null || processor.speed != null) {
                    if (processor.startTimeMs != null && processor.endTimeMs != null) {
                        j = (processor.endTimeMs.intValue() - processor.startTimeMs.intValue()) * 1000;
                    }
                    if (processor.speed != null) {
                        j = ((float) j) / processor.speed.floatValue();
                    }
                    if (j >= j2) {
                        j = j2;
                    }
                    createAudioFormat.setLong("durationUs", j);
                    num2 = Integer.valueOf((processor.startTimeMs == null ? 0 : processor.startTimeMs.intValue()) + ((int) (j / 1000)));
                    i3 = 2;
                    AudioUtil.checkCsd(createAudioFormat, i3, integer2, integer);
                    i4 = mediaMuxer.addTrack(createAudioFormat);
                    num = num2;
                }
            } else if (processor.startTimeMs == null && processor.endTimeMs == null && processor.speed == null) {
                mediaMuxer = mediaMuxer2;
            } else {
                long j3 = trackFormat.getLong("durationUs");
                if (processor.startTimeMs != null && processor.endTimeMs != null) {
                    j3 = (processor.endTimeMs.intValue() - processor.startTimeMs.intValue()) * 1000;
                }
                if (processor.speed != null) {
                    j3 = ((float) j3) / processor.speed.floatValue();
                }
                createAudioFormat.setLong("durationUs", j3);
                mediaMuxer = mediaMuxer2;
            }
            i3 = 2;
            AudioUtil.checkCsd(createAudioFormat, i3, integer2, integer);
            i4 = mediaMuxer.addTrack(createAudioFormat);
            num = num2;
        } else {
            i3 = 2;
            mediaMuxer = mediaMuxer2;
            num = num2;
            i4 = 0;
        }
        mediaExtractor.selectTrack(selectTrack);
        if (processor.startTimeMs == null) {
            mediaExtractor.seekTo(0L, 0);
        } else {
            mediaExtractor.seekTo(processor.startTimeMs.intValue() * 1000, 0);
        }
        VideoProgressAve videoProgressAve = new VideoProgressAve(processor.listener);
        videoProgressAve.setSpeed(processor.speed);
        videoProgressAve.setStartTimeMs(processor.startTimeMs == null ? 0 : processor.startTimeMs.intValue());
        if (processor.endTimeMs != null) {
            parseInt5 = processor.endTimeMs.intValue();
        }
        videoProgressAve.setEndTimeMs(parseInt5);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        VideoEncodeThread videoEncodeThread = new VideoEncodeThread(mediaExtractor, mediaMuxer, processor.bitrate.intValue(), i2, i, processor.iFrameInterval.intValue(), processor.frameRate == null ? DEFAULT_FRAME_RATE : processor.frameRate.intValue(), selectTrack, atomicBoolean, countDownLatch);
        int frameRate = VideoUtil.getFrameRate(processor.input);
        if (frameRate <= 0) {
            frameRate = (int) Math.ceil(VideoUtil.getAveFrameRate(processor.input));
        }
        VideoDecodeThread videoDecodeThread = new VideoDecodeThread(videoEncodeThread, mediaExtractor, processor.startTimeMs, processor.endTimeMs, Integer.valueOf(frameRate), Integer.valueOf(processor.frameRate == null ? DEFAULT_FRAME_RATE : processor.frameRate.intValue()), processor.speed, processor.dropFrames, selectTrack, atomicBoolean);
        AudioProcessThread audioProcessThread = new AudioProcessThread(context, processor.input, mediaMuxer, processor.startTimeMs, num, booleanValue ? processor.speed : null, i4, countDownLatch);
        videoEncodeThread.setProgressAve(videoProgressAve);
        audioProcessThread.setProgressAve(videoProgressAve);
        videoDecodeThread.start();
        videoEncodeThread.start();
        audioProcessThread.start();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            videoDecodeThread.join();
            videoEncodeThread.join();
            long currentTimeMillis2 = System.currentTimeMillis();
            audioProcessThread.join();
            C5140CL.m13749w(String.format("编解码:%dms,音频:%dms", Long.valueOf(currentTimeMillis2 - currentTimeMillis), Long.valueOf(System.currentTimeMillis() - currentTimeMillis)), new Object[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            mediaMuxer.release();
            mediaExtractor.release();
        } catch (Exception e2) {
            C5140CL.m13757e(e2);
        }
        if (videoEncodeThread.getException() != null) {
            throw videoEncodeThread.getException();
        }
        if (videoDecodeThread.getException() != null) {
            throw videoDecodeThread.getException();
        }
        if (audioProcessThread.getException() != null) {
            throw audioProcessThread.getException();
        }
    }

    public static void reverseVideoNoDecode(MediaSource mediaSource, String str, boolean z) throws IOException {
        reverseVideoNoDecode(mediaSource, str, z, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0130 A[Catch: all -> 0x0214, Exception -> 0x0216, TryCatch #1 {Exception -> 0x0216, blocks: (B:12:0x007a, B:14:0x0080, B:16:0x008c, B:19:0x00a5, B:48:0x0130, B:50:0x013b, B:52:0x014a, B:55:0x0161, B:84:0x01f7, B:58:0x017a, B:60:0x0183, B:63:0x0192, B:65:0x019d, B:67:0x01a7, B:68:0x01b3, B:71:0x01bc, B:74:0x01d0, B:76:0x01d5, B:79:0x01e2, B:81:0x01ef, B:22:0x00bc, B:24:0x00c1, B:27:0x00ce, B:28:0x00d5, B:31:0x00e1, B:35:0x00f0, B:38:0x0106, B:40:0x010b, B:43:0x011a, B:45:0x0124, B:85:0x01fd), top: B:96:0x007a, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01f7 A[Catch: all -> 0x0214, Exception -> 0x0216, TryCatch #1 {Exception -> 0x0216, blocks: (B:12:0x007a, B:14:0x0080, B:16:0x008c, B:19:0x00a5, B:48:0x0130, B:50:0x013b, B:52:0x014a, B:55:0x0161, B:84:0x01f7, B:58:0x017a, B:60:0x0183, B:63:0x0192, B:65:0x019d, B:67:0x01a7, B:68:0x01b3, B:71:0x01bc, B:74:0x01d0, B:76:0x01d5, B:79:0x01e2, B:81:0x01ef, B:22:0x00bc, B:24:0x00c1, B:27:0x00ce, B:28:0x00d5, B:31:0x00e1, B:35:0x00f0, B:38:0x0106, B:40:0x010b, B:43:0x011a, B:45:0x0124, B:85:0x01fd), top: B:96:0x007a, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void reverseVideoNoDecode(com.p226hw.videoprocessor.VideoProcessor.MediaSource r27, java.lang.String r28, boolean r29, java.util.List<java.lang.Long> r30, @org.jetbrains.annotations.Nullable com.p226hw.videoprocessor.util.VideoProgressListener r31) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 552
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.VideoProcessor.reverseVideoNoDecode(com.hw.videoprocessor.VideoProcessor$MediaSource, java.lang.String, boolean, java.util.List, com.hw.videoprocessor.util.VideoProgressListener):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x021f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0224  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void adjustVideoVolume(android.content.Context r32, com.p226hw.videoprocessor.VideoProcessor.MediaSource r33, java.lang.String r34, int r35, float r36, float r37) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1157
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.VideoProcessor.adjustVideoVolume(android.content.Context, com.hw.videoprocessor.VideoProcessor$MediaSource, java.lang.String, int, float, float):void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:59|60|61|62|(5:(1:108)(4:66|67|68|(11:99|100|(2:72|73)(1:98)|74|75|76|(2:78|79)(1:94)|80|81|83|56))|80|81|83|56)|70|(0)(0)|74|75|76|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x05db, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x05e0, code lost:
        r5 = r25;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x058c A[Catch: all -> 0x05ad, TRY_LEAVE, TryCatch #4 {all -> 0x05ad, blocks: (B:122:0x055b, B:128:0x058c, B:136:0x05b9), top: B:211:0x055b }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x05b9 A[Catch: all -> 0x05ad, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x05ad, blocks: (B:122:0x055b, B:128:0x058c, B:136:0x05b9), top: B:211:0x055b }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x05bf  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x04b3 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void mixAudioTrack(android.content.Context r38, final com.p226hw.videoprocessor.VideoProcessor.MediaSource r39, final com.p226hw.videoprocessor.VideoProcessor.MediaSource r40, java.lang.String r41, java.lang.Integer r42, java.lang.Integer r43, int r44, int r45, float r46, float r47) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1894
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.VideoProcessor.mixAudioTrack(android.content.Context, com.hw.videoprocessor.VideoProcessor$MediaSource, com.hw.videoprocessor.VideoProcessor$MediaSource, java.lang.String, java.lang.Integer, java.lang.Integer, int, int, float, float):void");
    }

    public static Processor processor(Context context) {
        return new Processor(context);
    }

    /* renamed from: com.hw.videoprocessor.VideoProcessor$Processor */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Processor {
        @Nullable
        private Integer bitrate;
        @Nullable
        private Boolean changeAudioSpeed;
        private Context context;
        private boolean dropFrames = true;
        @Nullable
        private Integer endTimeMs;
        @Nullable
        private Integer frameRate;
        @Nullable
        private Integer iFrameInterval;
        private MediaSource input;
        @Nullable
        private VideoProgressListener listener;
        @Nullable
        private Integer outHeight;
        @Nullable
        private Integer outWidth;
        private String output;
        @Nullable
        private Float speed;
        @Nullable
        private Integer startTimeMs;

        public Processor(Context context) {
            this.context = context;
        }

        public Processor input(MediaSource mediaSource) {
            this.input = mediaSource;
            return this;
        }

        public Processor input(Uri uri) {
            this.input = new MediaSource(this.context, uri);
            return this;
        }

        public Processor input(String str) {
            this.input = new MediaSource(str);
            return this;
        }

        public Processor output(String str) {
            this.output = str;
            return this;
        }

        public Processor outWidth(int i) {
            this.outWidth = Integer.valueOf(i);
            return this;
        }

        public Processor outHeight(int i) {
            this.outHeight = Integer.valueOf(i);
            return this;
        }

        public Processor startTimeMs(int i) {
            this.startTimeMs = Integer.valueOf(i);
            return this;
        }

        public Processor endTimeMs(int i) {
            this.endTimeMs = Integer.valueOf(i);
            return this;
        }

        public Processor speed(float f) {
            this.speed = Float.valueOf(f);
            return this;
        }

        public Processor changeAudioSpeed(boolean z) {
            this.changeAudioSpeed = Boolean.valueOf(z);
            return this;
        }

        public Processor bitrate(int i) {
            this.bitrate = Integer.valueOf(i);
            return this;
        }

        public Processor frameRate(int i) {
            this.frameRate = Integer.valueOf(i);
            return this;
        }

        public Processor iFrameInterval(int i) {
            this.iFrameInterval = Integer.valueOf(i);
            return this;
        }

        public Processor dropFrames(boolean z) {
            this.dropFrames = z;
            return this;
        }

        public Processor progressListener(VideoProgressListener videoProgressListener) {
            this.listener = videoProgressListener;
            return this;
        }

        public void process() throws Exception {
            VideoProcessor.processVideo(this.context, this);
        }
    }

    /* renamed from: com.hw.videoprocessor.VideoProcessor$MediaSource */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class MediaSource {
        public Context context;
        public String inputPath;
        public Uri inputUri;

        public MediaSource(String str) {
            this.inputPath = str;
        }

        public MediaSource(Context context, Uri uri) {
            this.context = context;
            this.inputUri = uri;
        }

        public void setDataSource(MediaMetadataRetriever mediaMetadataRetriever) {
            String str = this.inputPath;
            if (str != null) {
                mediaMetadataRetriever.setDataSource(str);
            } else {
                mediaMetadataRetriever.setDataSource(this.context, this.inputUri);
            }
        }

        public void setDataSource(MediaExtractor mediaExtractor) throws IOException {
            String str = this.inputPath;
            if (str != null) {
                mediaExtractor.setDataSource(str);
            } else {
                mediaExtractor.setDataSource(this.context, this.inputUri, (Map<String, String>) null);
            }
        }
    }
}

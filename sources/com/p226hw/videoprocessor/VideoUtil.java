package com.p226hw.videoprocessor;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import android.util.Pair;
import com.megvii.livenesslib.util.SDCardUtil;
import com.p226hw.videoprocessor.VideoProcessor;
import com.p226hw.videoprocessor.util.AudioUtil;
import com.p226hw.videoprocessor.util.C5140CL;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hw.videoprocessor.VideoUtil */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class VideoUtil {
    public static List<File> splitVideo(Context context, VideoProcessor.MediaSource mediaSource, String str, int i, int i2, Integer num, float f, Integer num2) throws Exception {
        int i3 = (int) (i * f);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaSource.setDataSource(mediaMetadataRetriever);
        int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        LinkedList<Pair> linkedList = new LinkedList();
        int i4 = 0;
        while (true) {
            if (parseInt <= 0) {
                break;
            }
            parseInt -= i3;
            if (parseInt < i2) {
                linkedList.add(new Pair(Integer.valueOf(i4), Integer.valueOf(i4 + i3 + parseInt)));
                break;
            }
            Integer valueOf = Integer.valueOf(i4);
            i4 += i3;
            linkedList.add(new Pair(valueOf, Integer.valueOf(i4)));
        }
        ArrayList arrayList = new ArrayList(linkedList.size());
        for (Pair pair : linkedList) {
            File file = new File(str, pair.first + ".mp4");
            VideoProcessor.processor(context).input(mediaSource).output(file.getAbsolutePath()).startTimeMs(((Integer) pair.first).intValue()).endTimeMs(((Integer) pair.second).intValue()).speed(f).bitrate(num.intValue()).iFrameInterval(num2.intValue()).process();
            arrayList.add(file);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void combineVideos(List<File> list, String str, Integer num, Integer num2) throws Exception {
        MediaExtractor mediaExtractor;
        int i;
        long j;
        long j2;
        int i2;
        if (list == null || list.size() == 0) {
            return;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        int i3 = 0;
        mediaMetadataRetriever.setDataSource(list.get(0).getAbsolutePath());
        int parseInt = num == null ? Integer.parseInt(mediaMetadataRetriever.extractMetadata(20)) : num.intValue();
        MediaExtractor mediaExtractor2 = new MediaExtractor();
        mediaExtractor2.setDataSource(list.get(0).getAbsolutePath());
        int selectTrack = selectTrack(mediaExtractor2, false);
        boolean z = 1;
        int selectTrack2 = selectTrack(mediaExtractor2, true);
        MediaMuxer mediaMuxer = new MediaMuxer(str, 0);
        int addTrack = mediaMuxer.addTrack(mediaExtractor2.getTrackFormat(selectTrack));
        boolean z2 = selectTrack2 >= 0;
        int addTrack2 = z2 ? mediaMuxer.addTrack(mediaExtractor2.getTrackFormat(selectTrack2)) : 0;
        mediaMuxer.start();
        long j3 = 0;
        int i4 = selectTrack2;
        long j4 = 0;
        int i5 = 0;
        while (i5 < list.size()) {
            if (i5 > 0) {
                MediaExtractor mediaExtractor3 = new MediaExtractor();
                mediaExtractor3.setDataSource(list.get(i5).getAbsolutePath());
                mediaExtractor = mediaExtractor3;
                i = selectTrack(mediaExtractor3, z);
            } else {
                int i6 = i4;
                mediaExtractor = mediaExtractor2;
                i = i6;
            }
            if (z2) {
                long writeAudioTrack = AudioUtil.writeAudioTrack(mediaExtractor, mediaMuxer, addTrack2, null, null, j4, null);
                mediaExtractor.unselectTrack(i);
                j = writeAudioTrack;
            } else {
                j = j3;
            }
            int i7 = i;
            MediaExtractor mediaExtractor4 = mediaExtractor;
            int i8 = i5;
            long j5 = j;
            long appendVideoTrack = appendVideoTrack(mediaExtractor, mediaMuxer, addTrack, null, null, j4, parseInt, num2.intValue(), i5 == 0 ? z : i3, false);
            long j6 = appendVideoTrack > j5 ? appendVideoTrack : j5;
            C5140CL.m13755i("片段" + i8 + "已合成,audioFrameTime:" + (((float) j5) / 1000.0f) + " videoFrameTime:" + (((float) appendVideoTrack) / 1000.0f), new Object[i3]);
            long j7 = j6 + 33000;
            mediaExtractor4.release();
            long currentTimeMillis = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            sb.append(list.get(i8).getAbsolutePath());
            sb.append(".rev");
            String sb2 = sb.toString();
            VideoProcessor.reverseVideoNoDecode(new VideoProcessor.MediaSource(list.get(i8).getAbsolutePath()), sb2, true);
            long currentTimeMillis2 = System.currentTimeMillis();
            C5140CL.m13758e("reverseVideoNoDecode:" + (currentTimeMillis2 - currentTimeMillis) + "ms", new Object[i3]);
            MediaExtractor mediaExtractor5 = new MediaExtractor();
            mediaExtractor5.setDataSource(sb2);
            if (z2) {
                i2 = selectTrack(mediaExtractor5, true);
                long writeAudioTrack2 = AudioUtil.writeAudioTrack(mediaExtractor5, mediaMuxer, addTrack2, null, null, j7, null);
                mediaExtractor5.unselectTrack(i2);
                j2 = writeAudioTrack2;
            } else {
                j2 = j5;
                i2 = i7;
            }
            long j8 = j2;
            long appendVideoTrack2 = appendVideoTrack(mediaExtractor5, mediaMuxer, addTrack, null, null, j7, parseInt, num2.intValue(), false, i8 == list.size() + (-1));
            j3 = j8;
            long j9 = appendVideoTrack2 > j3 ? appendVideoTrack2 : j3;
            C5140CL.m13755i("反序片段" + i8 + "已合成,audioFrameTime:" + (((float) j3) / 1000.0f) + " videoFrameTime:" + (((float) appendVideoTrack2) / 1000.0f), new Object[0]);
            j4 = j9 + 33000;
            mediaExtractor5.release();
            new File(sb2).delete();
            i5 = i8 + 1;
            i4 = i2;
            i3 = 0;
            z = 1;
            mediaExtractor2 = mediaExtractor5;
        }
        mediaMuxer.release();
    }

    public static int selectTrack(MediaExtractor mediaExtractor, boolean z) {
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

    static long appendVideoTrack(MediaExtractor mediaExtractor, MediaMuxer mediaMuxer, int i, Integer num, Integer num2, long j, int i2, int i3, boolean z, boolean z2) throws Exception {
        int selectTrack = selectTrack(mediaExtractor, false);
        mediaExtractor.selectTrack(selectTrack);
        Integer num3 = num == null ? 0 : num;
        mediaExtractor.seekTo(num3.intValue(), 2);
        MediaFormat trackFormat = mediaExtractor.getTrackFormat(selectTrack);
        int integer = trackFormat.getInteger("width");
        int integer2 = trackFormat.getInteger("height");
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        VideoAppendEncodeThread videoAppendEncodeThread = new VideoAppendEncodeThread(mediaExtractor, mediaMuxer, i2, integer, integer2, i3, selectTrack, atomicBoolean, j, z, z2, i);
        VideoDecodeThread videoDecodeThread = new VideoDecodeThread(videoAppendEncodeThread, mediaExtractor, num3 == null ? null : Integer.valueOf(num3.intValue() / 1000), num2 != null ? Integer.valueOf(num2.intValue() / 1000) : null, null, null, null, false, selectTrack, atomicBoolean);
        videoDecodeThread.start();
        videoAppendEncodeThread.start();
        try {
            videoDecodeThread.join();
            videoAppendEncodeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            mediaExtractor.release();
        } catch (Exception e2) {
            C5140CL.m13757e(e2);
        }
        if (videoAppendEncodeThread.getException() != null) {
            throw videoAppendEncodeThread.getException();
        }
        if (videoDecodeThread.getException() != null) {
            throw videoDecodeThread.getException();
        }
        return videoAppendEncodeThread.getLastFrametimeUs();
    }

    public static int getBitrateForAllKeyFrameVideo(VideoProcessor.MediaSource mediaSource) throws IOException {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaSource.setDataSource(mediaExtractor);
        int i = 0;
        mediaExtractor.selectTrack(selectTrack(mediaExtractor, false));
        int i2 = 0;
        while (true) {
            int sampleFlags = mediaExtractor.getSampleFlags();
            if (sampleFlags > 0 && (sampleFlags & 1) != 0) {
                i++;
            }
            if (mediaExtractor.getSampleTime() < 0) {
                break;
            }
            i2++;
            mediaExtractor.advance();
        }
        mediaExtractor.release();
        float f = ((i2 - i) / i) + 1.0f;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaSource.setDataSource(mediaMetadataRetriever);
        int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(20));
        mediaMetadataRetriever.release();
        return i2 == i ? parseInt : (int) (f * parseInt);
    }

    public static Pair<Integer, Integer> getVideoFrameCount(String str) throws IOException {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.setDataSource(str);
        int i = 0;
        mediaExtractor.selectTrack(selectTrack(mediaExtractor, false));
        int i2 = 0;
        while (true) {
            int sampleFlags = mediaExtractor.getSampleFlags();
            if (sampleFlags > 0 && (sampleFlags & 1) != 0) {
                i++;
            }
            if (mediaExtractor.getSampleTime() >= 0) {
                i2++;
                mediaExtractor.advance();
            } else {
                mediaExtractor.release();
                return new Pair<>(Integer.valueOf(i), Integer.valueOf(i2));
            }
        }
    }

    public static int getFrameRate(VideoProcessor.MediaSource mediaSource) {
        MediaExtractor mediaExtractor = new MediaExtractor();
        try {
            mediaSource.setDataSource(mediaExtractor);
            MediaFormat trackFormat = mediaExtractor.getTrackFormat(selectTrack(mediaExtractor, false));
            return trackFormat.containsKey("frame-rate") ? trackFormat.getInteger("frame-rate") : -1;
        } catch (IOException e) {
            C5140CL.m13757e(e);
            return -1;
        } finally {
            mediaExtractor.release();
        }
    }

    public static float getAveFrameRate(VideoProcessor.MediaSource mediaSource) throws IOException {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaSource.setDataSource(mediaExtractor);
        int i = 0;
        mediaExtractor.selectTrack(selectTrack(mediaExtractor, false));
        long j = 0;
        while (true) {
            long sampleTime = mediaExtractor.getSampleTime();
            if (sampleTime >= 0) {
                i++;
                mediaExtractor.advance();
                j = sampleTime;
            } else {
                mediaExtractor.release();
                return i / ((((float) j) / 1000.0f) / 1000.0f);
            }
        }
    }

    public static void seekToLastFrame(MediaExtractor mediaExtractor, int i, int i2) {
        int i3 = i2 * 1000;
        if (mediaExtractor.getSampleTrackIndex() != i) {
            mediaExtractor.selectTrack(i);
        }
        mediaExtractor.seekTo(i3, 0);
        while (i3 > 0 && mediaExtractor.getSampleTrackIndex() != i) {
            i3 -= 10000;
            mediaExtractor.seekTo(i3, 0);
        }
    }

    public static File getVideoCacheDir(Context context) {
        File file = new File(SDCardUtil.getOwnFileUrl("Video"), "video/");
        file.mkdirs();
        return file;
    }

    public static boolean trySetProfileAndLevel(MediaCodec mediaCodec, String str, MediaFormat mediaFormat, int i, int i2) {
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr = mediaCodec.getCodecInfo().getCapabilitiesForType(str).profileLevels;
        if (codecProfileLevelArr == null) {
            return false;
        }
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : codecProfileLevelArr) {
            if (codecProfileLevel.profile == i && codecProfileLevel.level == i2) {
                mediaFormat.setInteger("profile", i);
                mediaFormat.setInteger("level", i2);
                return true;
            }
        }
        return false;
    }

    public static int getMaxSupportBitrate(MediaCodec mediaCodec, String str) {
        try {
            return mediaCodec.getCodecInfo().getCapabilitiesForType(str).getVideoCapabilities().getBitrateRange().getUpper().intValue();
        } catch (Exception e) {
            C5140CL.m13757e(e);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Long> getFrameTimeStampsList(MediaExtractor mediaExtractor) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            long sampleTime = mediaExtractor.getSampleTime();
            if (sampleTime < 0) {
                return arrayList;
            }
            arrayList.add(Long.valueOf(sampleTime));
            mediaExtractor.advance();
        }
    }

    public static Pair<Integer, Integer> getVideoSize(VideoProcessor.MediaSource mediaSource) throws IOException {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaSource.setDataSource(mediaExtractor);
        MediaFormat trackFormat = mediaExtractor.getTrackFormat(selectTrack(mediaExtractor, false));
        return new Pair<>(Integer.valueOf(trackFormat.getInteger("width")), Integer.valueOf(trackFormat.getInteger("height")));
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String savaVideoToMediaStore(android.content.Context r3, java.lang.String r4, java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.String r1 = "_display_name"
            r0.put(r1, r5)
            java.lang.String r5 = "mime_type"
            r0.put(r5, r7)
            java.lang.String r5 = "description"
            r0.put(r5, r6)
            int r5 = android.os.Build.VERSION.SDK_INT
            android.content.ContentResolver r3 = r3.getContentResolver()
            r5 = 0
            android.net.Uri r6 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Exception -> L4f
            android.net.Uri r6 = r3.insert(r6, r0)     // Catch: java.lang.Exception -> L4f
            if (r6 != 0) goto L24
            return r5
        L24:
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch: java.lang.Exception -> L4d
            java.lang.String r0 = "w"
            android.os.ParcelFileDescriptor r0 = r3.openFileDescriptor(r6, r0)     // Catch: java.lang.Exception -> L4d
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Exception -> L4d
            java.io.FileDescriptor r0 = r0.getFileDescriptor()     // Catch: java.lang.Exception -> L4d
            r1.<init>(r0)     // Catch: java.lang.Exception -> L4d
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L4d
            r0.<init>(r4)     // Catch: java.lang.Exception -> L4d
        L3d:
            int r4 = r0.read(r7)     // Catch: java.lang.Exception -> L4d
            r2 = -1
            if (r4 != r2) goto L48
            r1.flush()     // Catch: java.lang.Exception -> L4d
            goto L59
        L48:
            r2 = 0
            r1.write(r7, r2, r4)     // Catch: java.lang.Exception -> L4d
            goto L3d
        L4d:
            r4 = move-exception
            goto L51
        L4f:
            r4 = move-exception
            r6 = r5
        L51:
            r4.printStackTrace()
            if (r6 == 0) goto L59
            r3.delete(r6, r5, r5)
        L59:
            if (r6 == 0) goto L5f
            java.lang.String r5 = r6.toString()
        L5f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.VideoUtil.savaVideoToMediaStore(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }
}

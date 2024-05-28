package com.sinovatech.unicom.separatemodule.huotijiance.util;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import android.util.Log;
import android.util.SparseIntArray;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HWCodec {
    public static final int MEDIA_TYPE_AUDIO = 2;
    public static final int MEDIA_TYPE_UNKNOWN = 0;
    public static final int MEDIA_TYPE_VIDEO = 1;
    public static final String MIME_TYPE_AAC = "audio/mp4a-latm";
    public static final String MIME_TYPE_AVC = "video/avc";
    private static final String TAG = "HWCodec";

    public static int getMediaType(MediaFormat mediaFormat) {
        String string = mediaFormat.getString("mime");
        if (string.startsWith("video/")) {
            return 1;
        }
        return string.startsWith("audio/") ? 2 : 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v22, types: [android.media.MediaMuxer] */
    public static boolean transcode(String str, String str2) {
        MediaMuxer mediaMuxer;
        MediaMuxer mediaMuxer2;
        MediaExtractor mediaExtractor = null;
        try {
            try {
                MediaExtractor mediaExtractor2 = new MediaExtractor();
                try {
                    mediaExtractor2.setDataSource(str);
                    str = new MediaMuxer(str2, 0);
                } catch (IOException e) {
                    e = e;
                    str = null;
                } catch (Exception e2) {
                    e = e2;
                    str = null;
                } catch (Throwable th) {
                    th = th;
                    str = 0;
                }
                try {
                    doTranscode(mediaExtractor2, str);
                    try {
                        mediaExtractor2.release();
                        str.stop();
                        str.release();
                        return true;
                    } catch (Exception e3) {
                        Log.e("HWCodec", "doTranscode close exception: " + e3.getLocalizedMessage());
                        return true;
                    }
                } catch (IOException e4) {
                    e = e4;
                    mediaExtractor = mediaExtractor2;
                    mediaMuxer2 = str;
                    Log.e("HWCodec", "doTranscode io exception: " + e.getLocalizedMessage());
                    if (mediaExtractor != null) {
                        try {
                            mediaExtractor.release();
                        } catch (Exception e5) {
                            Log.e("HWCodec", "doTranscode close exception: " + e5.getLocalizedMessage());
                            return false;
                        }
                    }
                    if (mediaMuxer2 != null) {
                        mediaMuxer2.stop();
                        mediaMuxer2.release();
                    }
                    return false;
                } catch (Exception e6) {
                    e = e6;
                    mediaExtractor = mediaExtractor2;
                    mediaMuxer = str;
                    Log.e("HWCodec", "doTranscode exception: " + e.getLocalizedMessage());
                    if (mediaExtractor != null) {
                        try {
                            mediaExtractor.release();
                        } catch (Exception e7) {
                            Log.e("HWCodec", "doTranscode close exception: " + e7.getLocalizedMessage());
                            return false;
                        }
                    }
                    if (mediaMuxer != null) {
                        mediaMuxer.stop();
                        mediaMuxer.release();
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    mediaExtractor = mediaExtractor2;
                    if (mediaExtractor != null) {
                        try {
                            mediaExtractor.release();
                        } catch (Exception e8) {
                            Log.e("HWCodec", "doTranscode close exception: " + e8.getLocalizedMessage());
                            throw th;
                        }
                    }
                    if (str != 0) {
                        str.stop();
                        str.release();
                    }
                    throw th;
                }
            } catch (IOException e9) {
                e = e9;
                mediaMuxer2 = null;
            } catch (Exception e10) {
                e = e10;
                mediaMuxer = null;
            } catch (Throwable th3) {
                th = th3;
                str = 0;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private static void doTranscode(MediaExtractor mediaExtractor, MediaMuxer mediaMuxer) throws IOException {
        int trackCount = mediaExtractor.getTrackCount();
        SparseIntArray sparseIntArray = new SparseIntArray(trackCount);
        for (int i = 0; i < trackCount; i++) {
            MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
            if (getMediaType(trackFormat) == 0) {
                sparseIntArray.put(i, -1);
            } else {
                sparseIntArray.put(i, mediaMuxer.addTrack(trackFormat));
            }
        }
        mediaMuxer.start();
        for (int i2 = 0; i2 < trackCount; i2++) {
            int i3 = sparseIntArray.get(i2);
            if (i3 != -1) {
                mediaExtractor.selectTrack(i2);
                MediaFormat trackFormat2 = mediaExtractor.getTrackFormat(i2);
                int integer = trackFormat2.getInteger("max-input-size");
                boolean z = getMediaType(trackFormat2) == 1;
                long integer2 = z ? 1000000 / trackFormat2.getInteger("frame-rate") : 0L;
                ByteBuffer allocate = ByteBuffer.allocate(integer);
                MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                while (true) {
                    int readSampleData = mediaExtractor.readSampleData(allocate, 0);
                    if (readSampleData < 0) {
                        break;
                    }
                    bufferInfo.offset = 0;
                    bufferInfo.size = readSampleData;
                    bufferInfo.flags = mediaExtractor.getSampleFlags();
                    if (Build.VERSION.SDK_INT >= 24 || !z) {
                        bufferInfo.presentationTimeUs = mediaExtractor.getSampleTime();
                    } else {
                        bufferInfo.presentationTimeUs += integer2;
                    }
                    mediaMuxer.writeSampleData(i3, allocate, bufferInfo);
                    mediaExtractor.advance();
                }
                mediaExtractor.unselectTrack(i2);
            }
        }
    }
}

package com.sinovatech.unicom.separatemodule.huotijiance.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.view.Surface;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TimeWatermark {
    private long mDuration;
    private int mFrameRate;
    private int mHeight;
    private int mWidth;
    private float mTextSize = 30.0f;
    private int mTextColor = -1;
    private SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm:ss.SSS");
    private Paint mPaint = new Paint();

    public TimeWatermark(int i, int i2, int i3, long j) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mFrameRate = i3;
        this.mDuration = j;
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mTextColor);
        this.mPaint.setTextSize(this.mTextSize);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    public Bitmap drawText(long j) {
        Bitmap createBitmap = Bitmap.createBitmap(this.mWidth, this.mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        String format = this.mTimeFormat.format(new Date(j));
        Rect rect = new Rect();
        this.mPaint.getTextBounds(format, 0, format.length(), rect);
        canvas.drawText(format, (this.mWidth - rect.width()) / 2.0f, (this.mHeight - rect.height()) - this.mTextSize, this.mPaint);
        return createBitmap;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0099 A[Catch: IOException -> 0x0156, TryCatch #0 {IOException -> 0x0156, blocks: (B:3:0x0002, B:7:0x0056, B:9:0x005c, B:11:0x0066, B:17:0x0099, B:22:0x00a9, B:26:0x00be, B:28:0x00c8, B:29:0x00cb, B:31:0x00cf, B:43:0x012e, B:36:0x0111, B:40:0x0119, B:12:0x0077, B:46:0x0140), top: B:51:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0136 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addTimeWatermark(java.lang.String r23, java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.huotijiance.util.TimeWatermark.addTimeWatermark(java.lang.String, java.lang.String):void");
    }

    private int getVideoTrackIndex(MediaExtractor mediaExtractor) {
        for (int i = 0; i < mediaExtractor.getTrackCount(); i++) {
            if (mediaExtractor.getTrackFormat(i).getString("mime").startsWith("video/")) {
                mediaExtractor.selectTrack(i);
                return i;
            }
        }
        return -1;
    }

    private int getFrameRate(MediaExtractor mediaExtractor, int i) {
        return mediaExtractor.getTrackFormat(i).getInteger("frame-rate");
    }

    private long getDuration(MediaExtractor mediaExtractor) {
        return mediaExtractor.getTrackFormat(getVideoTrackIndex(mediaExtractor)).getLong("durationUs");
    }

    private MediaCodec createDecoder(MediaExtractor mediaExtractor, int i) throws IOException {
        MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
        MediaCodec createDecoderByType = MediaCodec.createDecoderByType(trackFormat.getString("mime"));
        createDecoderByType.configure(trackFormat, (Surface) null, (MediaCrypto) null, 0);
        return createDecoderByType;
    }

    private MediaCodec createEncoder(MediaFormat mediaFormat, int i) throws IOException {
        MediaCodec createEncoderByType = MediaCodec.createEncoderByType("video/avc");
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", mediaFormat.getInteger("width"), mediaFormat.getInteger("height"));
        createVideoFormat.setInteger("bitrate", 8000000);
        createVideoFormat.setInteger("frame-rate", i);
        createVideoFormat.setInteger("i-frame-interval", 10);
        createEncoderByType.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        createEncoderByType.start();
        return createEncoderByType;
    }
}

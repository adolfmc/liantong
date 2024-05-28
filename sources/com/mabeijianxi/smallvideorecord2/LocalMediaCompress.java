package com.mabeijianxi.smallvideorecord2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import com.mabeijianxi.smallvideorecord2.model.LocalMediaConfig;
import com.mabeijianxi.smallvideorecord2.model.MediaObject;
import com.mabeijianxi.smallvideorecord2.model.OnlyCompressOverBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LocalMediaCompress extends MediaRecorderBase {
    private final LocalMediaConfig localMediaConfig;
    private final String mNeedCompressVideo;
    private final OnlyCompressOverBean mOnlyCompressOverBean;
    protected String scaleWH = "";

    @Override // com.mabeijianxi.smallvideorecord2.IMediaRecorder
    public MediaObject.MediaPart startRecord() {
        return null;
    }

    public LocalMediaCompress(LocalMediaConfig localMediaConfig) {
        this.localMediaConfig = localMediaConfig;
        this.compressConfig = localMediaConfig.getCompressConfig();
        CAPTURE_THUMBNAILS_TIME = localMediaConfig.getCaptureThumbnailsTime();
        if (localMediaConfig.getFrameRate() > 0) {
            setTranscodingFrameRate(localMediaConfig.getFrameRate());
        }
        this.mNeedCompressVideo = localMediaConfig.getVideoPath();
        this.mOnlyCompressOverBean = new OnlyCompressOverBean();
        this.mOnlyCompressOverBean.setVideoPath(this.mNeedCompressVideo);
    }

    private String getScaleWH(String str, float f) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        String extractMetadata = mediaMetadataRetriever.extractMetadata(24);
        String extractMetadata2 = mediaMetadataRetriever.extractMetadata(18);
        String extractMetadata3 = mediaMetadataRetriever.extractMetadata(19);
        int intValue = (int) (Integer.valueOf(extractMetadata2).intValue() / f);
        int intValue2 = (int) (Integer.valueOf(extractMetadata3).intValue() / f);
        if (intValue2 % 2 != 0) {
            intValue2++;
        }
        if (intValue % 2 != 0) {
            intValue++;
        }
        return (extractMetadata.equals("90") || extractMetadata.equals("270")) ? String.format("%dx%d", Integer.valueOf(intValue2), Integer.valueOf(intValue)) : (extractMetadata.equals("0") || extractMetadata.equals("180") || extractMetadata.equals("360")) ? String.format("%dx%d", Integer.valueOf(intValue), Integer.valueOf(intValue2)) : "";
    }

    private void correcAttribute(String str, String str2) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        String extractMetadata = mediaMetadataRetriever.extractMetadata(24);
        String extractMetadata2 = mediaMetadataRetriever.extractMetadata(18);
        String extractMetadata3 = mediaMetadataRetriever.extractMetadata(19);
        if (extractMetadata.equals("90") || extractMetadata.equals("270")) {
            SMALL_VIDEO_WIDTH = Integer.valueOf(extractMetadata2).intValue();
            SMALL_VIDEO_HEIGHT = Integer.valueOf(extractMetadata3).intValue();
            String checkPicRotaing = checkPicRotaing(Integer.valueOf(extractMetadata).intValue(), str2);
            if (TextUtils.isEmpty(checkPicRotaing)) {
                return;
            }
            this.mOnlyCompressOverBean.setPicPath(checkPicRotaing);
        } else if (extractMetadata.equals("0") || extractMetadata.equals("180") || extractMetadata.equals("360")) {
            SMALL_VIDEO_HEIGHT = Integer.valueOf(extractMetadata2).intValue();
            SMALL_VIDEO_WIDTH = Integer.valueOf(extractMetadata3).intValue();
        }
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase
    public String getScaleWH() {
        return this.scaleWH;
    }

    private String checkPicRotaing(int i, String str) {
        return savePhoto(rotaingImageView(i, BitmapFactory.decodeFile(str)));
    }

    private Bitmap rotaingImageView(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private String savePhoto(Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        File file = new File(this.mMediaObject.getOutputDirectory(), UUID.randomUUID().toString() + ".jpg");
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e = e;
            fileOutputStream = null;
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
                throw th;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        try {
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return file.toString();
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream.flush();
                fileOutputStream.close();
                throw th;
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            e.printStackTrace();
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
                return null;
            } catch (IOException e5) {
                e5.printStackTrace();
                return null;
            }
        }
    }

    public OnlyCompressOverBean startCompress() {
        if (TextUtils.isEmpty(this.mNeedCompressVideo)) {
            return this.mOnlyCompressOverBean;
        }
        File file = new File(JianXiCamera.getVideoCachePath());
        if (!FileUtils.checkFile(file)) {
            file.mkdirs();
        }
        String valueOf = String.valueOf(System.currentTimeMillis());
        this.mMediaObject = setOutputDirectory(valueOf, JianXiCamera.getVideoCachePath() + valueOf);
        this.mMediaObject.setOutputTempVideoPath(this.mNeedCompressVideo);
        float scale = this.localMediaConfig.getScale();
        if (scale > 1.0f) {
            this.scaleWH = getScaleWH(this.mNeedCompressVideo, scale);
        }
        boolean booleanValue = doCompress(true).booleanValue();
        this.mOnlyCompressOverBean.setSucceed(booleanValue);
        if (booleanValue) {
            this.mOnlyCompressOverBean.setVideoPath(this.mMediaObject.getOutputTempTranscodingVideoPath());
            this.mOnlyCompressOverBean.setPicPath(this.mMediaObject.getOutputVideoThumbPath());
            correcAttribute(this.mMediaObject.getOutputTempTranscodingVideoPath(), this.mMediaObject.getOutputVideoThumbPath());
        }
        return this.mOnlyCompressOverBean;
    }
}

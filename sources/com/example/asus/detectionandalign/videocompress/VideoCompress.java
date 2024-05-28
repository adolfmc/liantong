package com.example.asus.detectionandalign.videocompress;

import android.os.AsyncTask;
import com.example.asus.detectionandalign.listener.CompressListener;
import com.example.asus.detectionandalign.videocompress.videocompression.VideoController;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class VideoCompress {
    private static final String TAG = "VideoCompress";

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class VideoCompressTask extends AsyncTask<String, Float, Boolean> {
        private CompressListener mListener;
        private int mQuality;

        public VideoCompressTask(CompressListener compressListener, int i) {
            this.mListener = compressListener;
            this.mQuality = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(String... strArr) {
            return Boolean.valueOf(VideoController.getInstance().convertVideo(strArr[0], strArr[1], this.mQuality, new VideoController.CompressProgressListener() { // from class: com.example.asus.detectionandalign.videocompress.VideoCompress.VideoCompressTask.1
                @Override // com.example.asus.detectionandalign.videocompress.videocompression.VideoController.CompressProgressListener
                public void onProgress(float f) {
                    VideoCompressTask.this.publishProgress(Float.valueOf(f));
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute((VideoCompressTask) bool);
            if (this.mListener != null) {
                if (bool.booleanValue()) {
                    this.mListener.onSuccess();
                } else {
                    this.mListener.onFail();
                }
            }
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            CompressListener compressListener = this.mListener;
            if (compressListener != null) {
                compressListener.onStart();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Float... fArr) {
            super.onProgressUpdate((Object[]) fArr);
            CompressListener compressListener = this.mListener;
            if (compressListener != null) {
                compressListener.onProgress(fArr[0].floatValue());
            }
        }
    }

    public static VideoCompressTask compressVideoHigh(String str, String str2, CompressListener compressListener) {
        VideoCompressTask videoCompressTask = new VideoCompressTask(compressListener, 1);
        videoCompressTask.execute(str, str2);
        return videoCompressTask;
    }

    public static VideoCompressTask compressVideoLow(String str, String str2, CompressListener compressListener) {
        VideoCompressTask videoCompressTask = new VideoCompressTask(compressListener, 3);
        videoCompressTask.execute(str, str2);
        return videoCompressTask;
    }

    public static VideoCompressTask compressVideoMedium(String str, String str2, CompressListener compressListener) {
        VideoCompressTask videoCompressTask = new VideoCompressTask(compressListener, 2);
        videoCompressTask.execute(str, str2);
        return videoCompressTask;
    }
}

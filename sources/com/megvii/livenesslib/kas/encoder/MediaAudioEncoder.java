package com.megvii.livenesslib.kas.encoder;

import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Process;
import android.util.Log;
import android.view.Surface;
import com.megvii.livenesslib.kas.encoder.MediaEncoder;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MediaAudioEncoder extends MediaEncoder {
    private static final int[] AUDIO_SOURCES = {1, 0, 5, 7, 6};
    private static final int BIT_RATE = 64000;
    private static final boolean DEBUG = false;
    public static final int FRAMES_PER_BUFFER = 25;
    private static final String MIME_TYPE = "audio/mp4a-latm";
    public static final int SAMPLES_PER_FRAME = 1024;
    private static final int SAMPLE_RATE = 44100;
    private static final String TAG = "MediaAudioEncoder";
    private AudioThread mAudioThread;

    public MediaAudioEncoder(MediaMuxerWrapper mediaMuxerWrapper, MediaEncoder.MediaEncoderListener mediaEncoderListener) {
        super(mediaMuxerWrapper, mediaEncoderListener);
        this.mAudioThread = null;
    }

    @Override // com.megvii.livenesslib.kas.encoder.MediaEncoder
    protected void prepare() throws IOException {
        this.mTrackIndex = -1;
        this.mIsEOS = false;
        this.mMuxerStarted = false;
        if (selectAudioCodec("audio/mp4a-latm") == null) {
            Log.e(TAG, "Unable to find an appropriate codec for audio/mp4a-latm");
            return;
        }
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", 44100, 1);
        createAudioFormat.setInteger("aac-profile", 2);
        createAudioFormat.setInteger("channel-mask", 16);
        createAudioFormat.setInteger("bitrate", BIT_RATE);
        createAudioFormat.setInteger("channel-count", 1);
        this.mMediaCodec = MediaCodec.createEncoderByType("audio/mp4a-latm");
        this.mMediaCodec.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mMediaCodec.start();
        if (this.mListener != null) {
            try {
                this.mListener.onPrepared(this);
            } catch (Exception e) {
                Log.e(TAG, "prepare:", e);
            }
        }
    }

    @Override // com.megvii.livenesslib.kas.encoder.MediaEncoder
    protected void startRecording() {
        super.startRecording();
        if (this.mAudioThread == null) {
            this.mAudioThread = new AudioThread();
            this.mAudioThread.start();
        }
    }

    @Override // com.megvii.livenesslib.kas.encoder.MediaEncoder
    protected void release() {
        this.mAudioThread = null;
        super.release();
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class AudioThread extends Thread {
        private AudioThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            try {
                int minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
                int i = 25600 < minBufferSize ? ((minBufferSize / 1024) + 1) * 1024 * 2 : 25600;
                AudioRecord audioRecord = null;
                for (int i2 : MediaAudioEncoder.AUDIO_SOURCES) {
                    try {
                        AudioRecord audioRecord2 = new AudioRecord(i2, 44100, 16, 2, i);
                        if (audioRecord2.getState() != 1) {
                            audioRecord2 = null;
                        }
                        audioRecord = audioRecord2;
                    } catch (Exception unused) {
                        audioRecord = null;
                    }
                    if (audioRecord != null) {
                        break;
                    }
                }
                if (audioRecord != null) {
                    if (MediaAudioEncoder.this.mIsCapturing) {
                        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1024);
                        audioRecord.startRecording();
                        while (MediaAudioEncoder.this.mIsCapturing && !MediaAudioEncoder.this.mRequestStop && !MediaAudioEncoder.this.mIsEOS) {
                            allocateDirect.clear();
                            int read = audioRecord.read(allocateDirect, 1024);
                            if (read > 0) {
                                allocateDirect.position(read);
                                allocateDirect.flip();
                                MediaAudioEncoder.this.encode(allocateDirect, read, MediaAudioEncoder.this.getPTSUs());
                                MediaAudioEncoder.this.frameAvailableSoon();
                            }
                        }
                        MediaAudioEncoder.this.frameAvailableSoon();
                        audioRecord.stop();
                    }
                    audioRecord.release();
                    return;
                }
                Log.e(MediaAudioEncoder.TAG, "failed to initialize AudioRecord");
            } catch (Exception e) {
                Log.e(MediaAudioEncoder.TAG, "AudioThread#run", e);
            }
        }
    }

    private static final MediaCodecInfo selectAudioCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        return codecInfoAt;
                    }
                }
                continue;
            }
        }
        return null;
    }
}

package com.sinovatech.unicom.separatemodule.audience.util;

import android.util.Log;
import android.widget.TextView;
import com.baidu.cloud.media.player.BDCloudMediaPlayer;
import com.baidu.cloud.media.player.IMediaPlayer;
import java.util.Locale;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PlayLogTextViewHelper extends LogTextViewHelper {
    private IMediaPlayer mPlayer;

    public PlayLogTextViewHelper(TextView textView) {
        super(textView);
    }

    public void setPlayer(IMediaPlayer iMediaPlayer) {
        this.mPlayer = iMediaPlayer;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.util.LogTextViewHelper
    protected String getMediaInfoString() {
        IMediaPlayer iMediaPlayer = this.mPlayer;
        if (iMediaPlayer == null) {
            return "";
        }
        BDCloudMediaPlayer bDCloudMediaPlayer = iMediaPlayer instanceof BDCloudMediaPlayer ? (BDCloudMediaPlayer) iMediaPlayer : null;
        if (bDCloudMediaPlayer == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        switch (bDCloudMediaPlayer.getVideoDecoder()) {
            case 1:
                sb.append("CodecType: avcodec \n");
                break;
            case 2:
                sb.append("CodecType: MediaCodec \n");
                break;
            default:
                sb.append("CodecType: N/A \n");
                break;
        }
        sb.append(String.format(Locale.US, "Fps: %.2f / %.2f \n", Float.valueOf(bDCloudMediaPlayer.getVideoDecodeFramesPerSecond()), Float.valueOf(bDCloudMediaPlayer.getVideoOutputFramesPerSecond())));
        long videoCachedDuration = bDCloudMediaPlayer.getVideoCachedDuration();
        long audioCachedDuration = bDCloudMediaPlayer.getAudioCachedDuration();
        long videoCachedBytes = bDCloudMediaPlayer.getVideoCachedBytes();
        long audioCachedBytes = bDCloudMediaPlayer.getAudioCachedBytes();
        long downloadSpeed = bDCloudMediaPlayer.getDownloadSpeed();
        bDCloudMediaPlayer.getBitRate();
        sb.append(String.format(Locale.US, "VideoCache: %s, %s \n", formatedDurationMilli(videoCachedDuration), formatedSize(videoCachedBytes)));
        sb.append(String.format(Locale.US, "AudioCache: %s, %s\n", formatedDurationMilli(audioCachedDuration), formatedSize(audioCachedBytes)));
        sb.append(String.format(Locale.US, "TcpSpeed: %s\n", formatedSpeed(downloadSpeed, 1000L)));
        Log.d("PlayLogTextViewHelper", "debug log :" + sb.toString());
        return sb.toString();
    }
}

package com.baidu.cloud.media.player;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BDCloudMediaMeta {
    public static final long AV_CH_BACK_CENTER = 256;
    public static final long AV_CH_BACK_LEFT = 16;
    public static final long AV_CH_BACK_RIGHT = 32;
    public static final long AV_CH_FRONT_CENTER = 4;
    public static final long AV_CH_FRONT_LEFT = 1;
    public static final long AV_CH_FRONT_LEFT_OF_CENTER = 64;
    public static final long AV_CH_FRONT_RIGHT = 2;
    public static final long AV_CH_FRONT_RIGHT_OF_CENTER = 128;
    public static final long AV_CH_LAYOUT_2POINT1 = 11;
    public static final long AV_CH_LAYOUT_2_1 = 259;
    public static final long AV_CH_LAYOUT_2_2 = 1539;
    public static final long AV_CH_LAYOUT_3POINT1 = 15;
    public static final long AV_CH_LAYOUT_4POINT0 = 263;
    public static final long AV_CH_LAYOUT_4POINT1 = 271;
    public static final long AV_CH_LAYOUT_5POINT0 = 1543;
    public static final long AV_CH_LAYOUT_5POINT0_BACK = 55;
    public static final long AV_CH_LAYOUT_5POINT1 = 1551;
    public static final long AV_CH_LAYOUT_5POINT1_BACK = 63;
    public static final long AV_CH_LAYOUT_6POINT0 = 1799;
    public static final long AV_CH_LAYOUT_6POINT0_FRONT = 1731;
    public static final long AV_CH_LAYOUT_6POINT1 = 1807;
    public static final long AV_CH_LAYOUT_6POINT1_BACK = 319;
    public static final long AV_CH_LAYOUT_6POINT1_FRONT = 1739;
    public static final long AV_CH_LAYOUT_7POINT0 = 1591;
    public static final long AV_CH_LAYOUT_7POINT0_FRONT = 1735;
    public static final long AV_CH_LAYOUT_7POINT1 = 1599;
    public static final long AV_CH_LAYOUT_7POINT1_WIDE = 1743;
    public static final long AV_CH_LAYOUT_7POINT1_WIDE_BACK = 255;
    public static final long AV_CH_LAYOUT_HEXAGONAL = 311;
    public static final long AV_CH_LAYOUT_MONO = 4;
    public static final long AV_CH_LAYOUT_OCTAGONAL = 1847;
    public static final long AV_CH_LAYOUT_QUAD = 51;
    public static final long AV_CH_LAYOUT_STEREO = 3;
    public static final long AV_CH_LAYOUT_STEREO_DOWNMIX = 1610612736;
    public static final long AV_CH_LAYOUT_SURROUND = 7;
    public static final long AV_CH_LOW_FREQUENCY = 8;
    public static final long AV_CH_LOW_FREQUENCY_2 = 34359738368L;
    public static final long AV_CH_SIDE_LEFT = 512;
    public static final long AV_CH_SIDE_RIGHT = 1024;
    public static final long AV_CH_STEREO_LEFT = 536870912;
    public static final long AV_CH_STEREO_RIGHT = 1073741824;
    public static final long AV_CH_SURROUND_DIRECT_LEFT = 8589934592L;
    public static final long AV_CH_SURROUND_DIRECT_RIGHT = 17179869184L;
    public static final long AV_CH_TOP_BACK_CENTER = 65536;
    public static final long AV_CH_TOP_BACK_LEFT = 32768;
    public static final long AV_CH_TOP_BACK_RIGHT = 131072;
    public static final long AV_CH_TOP_CENTER = 2048;
    public static final long AV_CH_TOP_FRONT_CENTER = 8192;
    public static final long AV_CH_TOP_FRONT_LEFT = 4096;
    public static final long AV_CH_TOP_FRONT_RIGHT = 16384;
    public static final long AV_CH_WIDE_LEFT = 2147483648L;
    public static final long AV_CH_WIDE_RIGHT = 4294967296L;
    public static final int FF_PROFILE_H264_BASELINE = 66;
    public static final int FF_PROFILE_H264_CAVLC_444 = 44;
    public static final int FF_PROFILE_H264_CONSTRAINED = 512;
    public static final int FF_PROFILE_H264_CONSTRAINED_BASELINE = 578;
    public static final int FF_PROFILE_H264_EXTENDED = 88;
    public static final int FF_PROFILE_H264_HIGH = 100;
    public static final int FF_PROFILE_H264_HIGH_10 = 110;
    public static final int FF_PROFILE_H264_HIGH_10_INTRA = 2158;
    public static final int FF_PROFILE_H264_HIGH_422 = 122;
    public static final int FF_PROFILE_H264_HIGH_422_INTRA = 2170;
    public static final int FF_PROFILE_H264_HIGH_444 = 144;
    public static final int FF_PROFILE_H264_HIGH_444_INTRA = 2292;
    public static final int FF_PROFILE_H264_HIGH_444_PREDICTIVE = 244;
    public static final int FF_PROFILE_H264_INTRA = 2048;
    public static final int FF_PROFILE_H264_MAIN = 77;
    public static final String IJKM_KEY_AUDIO_STREAM = "audio";
    public static final String IJKM_KEY_BITRATE = "bitrate";
    public static final String IJKM_KEY_CHANNEL_LAYOUT = "channel_layout";
    public static final String IJKM_KEY_CODEC_LEVEL = "codec_level";
    public static final String IJKM_KEY_CODEC_LONG_NAME = "codec_long_name";
    public static final String IJKM_KEY_CODEC_NAME = "codec_name";
    public static final String IJKM_KEY_CODEC_PIXEL_FORMAT = "codec_pixel_format";
    public static final String IJKM_KEY_CODEC_PROFILE = "codec_profile";
    public static final String IJKM_KEY_CODEC_PROFILE_ID = "codec_profile_id";
    public static final String IJKM_KEY_DURATION_US = "duration_us";
    public static final String IJKM_KEY_FORMAT = "format";
    public static final String IJKM_KEY_FPS_DEN = "fps_den";
    public static final String IJKM_KEY_FPS_NUM = "fps_num";
    public static final String IJKM_KEY_HEIGHT = "height";
    public static final String IJKM_KEY_LANGUAGE = "language";
    public static final String IJKM_KEY_SAMPLE_RATE = "sample_rate";
    public static final String IJKM_KEY_SAR_DEN = "sar_den";
    public static final String IJKM_KEY_SAR_NUM = "sar_num";
    public static final String IJKM_KEY_START_US = "start_us";
    public static final String IJKM_KEY_STREAMS = "streams";
    public static final String IJKM_KEY_TBR_DEN = "tbr_den";
    public static final String IJKM_KEY_TBR_NUM = "tbr_num";
    public static final String IJKM_KEY_TYPE = "type";
    public static final String IJKM_KEY_VIDEO_STREAM = "video";
    public static final String IJKM_KEY_WIDTH = "width";
    public static final String IJKM_VAL_TYPE__AUDIO = "audio";
    public static final String IJKM_VAL_TYPE__TIMEDTEXT = "timedtext";
    public static final String IJKM_VAL_TYPE__UNKNOWN = "unknown";
    public static final String IJKM_VAL_TYPE__VIDEO = "video";
    public BDCloudStreamMeta mAudioStream;
    public long mBitrate;
    public long mDurationUS;
    public String mFormat;
    public Bundle mMediaMeta;
    public long mStartUS;
    public final ArrayList<BDCloudStreamMeta> mStreams = new ArrayList<>();
    public BDCloudStreamMeta mVideoStream;

    public String getString(String str) {
        return this.mMediaMeta.getString(str);
    }

    public int getInt(String str) {
        return getInt(str, 0);
    }

    public int getInt(String str, int i) {
        String string = getString(str);
        if (TextUtils.isEmpty(string)) {
            return i;
        }
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public long getLong(String str) {
        return getLong(str, 0L);
    }

    public long getLong(String str, long j) {
        String string = getString(str);
        if (TextUtils.isEmpty(string)) {
            return j;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public ArrayList<Bundle> getParcelableArrayList(String str) {
        return this.mMediaMeta.getParcelableArrayList(str);
    }

    public String getDurationInline() {
        long j = (this.mDurationUS + 5000) / 1000000;
        long j2 = j / 60;
        return String.format(Locale.US, "%02d:%02d:%02d", Long.valueOf(j2 / 60), Long.valueOf(j2 % 60), Long.valueOf(j % 60));
    }

    public static BDCloudMediaMeta parse(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        BDCloudMediaMeta bDCloudMediaMeta = new BDCloudMediaMeta();
        bDCloudMediaMeta.mMediaMeta = bundle;
        bDCloudMediaMeta.mFormat = bDCloudMediaMeta.getString("format");
        bDCloudMediaMeta.mDurationUS = bDCloudMediaMeta.getLong("duration_us");
        bDCloudMediaMeta.mStartUS = bDCloudMediaMeta.getLong("start_us");
        bDCloudMediaMeta.mBitrate = bDCloudMediaMeta.getLong("bitrate");
        int i = -1;
        int i2 = bDCloudMediaMeta.getInt("video", -1);
        int i3 = bDCloudMediaMeta.getInt("audio", -1);
        ArrayList<Bundle> parcelableArrayList = bDCloudMediaMeta.getParcelableArrayList("streams");
        if (parcelableArrayList == null) {
            return bDCloudMediaMeta;
        }
        Iterator<Bundle> it = parcelableArrayList.iterator();
        while (it.hasNext()) {
            Bundle next = it.next();
            i++;
            if (next != null) {
                BDCloudStreamMeta bDCloudStreamMeta = new BDCloudStreamMeta(i);
                bDCloudStreamMeta.mMeta = next;
                bDCloudStreamMeta.mType = bDCloudStreamMeta.getString("type");
                bDCloudStreamMeta.mLanguage = bDCloudStreamMeta.getString("language");
                if (!TextUtils.isEmpty(bDCloudStreamMeta.mType)) {
                    bDCloudStreamMeta.mCodecName = bDCloudStreamMeta.getString("codec_name");
                    bDCloudStreamMeta.mCodecProfile = bDCloudStreamMeta.getString("codec_profile");
                    bDCloudStreamMeta.mCodecLongName = bDCloudStreamMeta.getString("codec_long_name");
                    bDCloudStreamMeta.mBitrate = bDCloudStreamMeta.getInt("bitrate");
                    if (bDCloudStreamMeta.mType.equalsIgnoreCase("video")) {
                        bDCloudStreamMeta.mWidth = bDCloudStreamMeta.getInt("width");
                        bDCloudStreamMeta.mHeight = bDCloudStreamMeta.getInt("height");
                        bDCloudStreamMeta.mFpsNum = bDCloudStreamMeta.getInt("fps_num");
                        bDCloudStreamMeta.mFpsDen = bDCloudStreamMeta.getInt("fps_den");
                        bDCloudStreamMeta.mTbrNum = bDCloudStreamMeta.getInt("tbr_num");
                        bDCloudStreamMeta.mTbrDen = bDCloudStreamMeta.getInt("tbr_den");
                        bDCloudStreamMeta.mSarNum = bDCloudStreamMeta.getInt("sar_num");
                        bDCloudStreamMeta.mSarDen = bDCloudStreamMeta.getInt("sar_den");
                        if (i2 == i) {
                            bDCloudMediaMeta.mVideoStream = bDCloudStreamMeta;
                        }
                    } else if (bDCloudStreamMeta.mType.equalsIgnoreCase("audio")) {
                        bDCloudStreamMeta.mSampleRate = bDCloudStreamMeta.getInt("sample_rate");
                        bDCloudStreamMeta.mChannelLayout = bDCloudStreamMeta.getLong("channel_layout");
                        if (i3 == i) {
                            bDCloudMediaMeta.mAudioStream = bDCloudStreamMeta;
                        }
                    }
                    bDCloudMediaMeta.mStreams.add(bDCloudStreamMeta);
                }
            }
        }
        return bDCloudMediaMeta;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class BDCloudStreamMeta {
        public long mBitrate;
        public long mChannelLayout;
        public String mCodecLongName;
        public String mCodecName;
        public String mCodecProfile;
        public int mFpsDen;
        public int mFpsNum;
        public int mHeight;
        public final int mIndex;
        public String mLanguage;
        public Bundle mMeta;
        public int mSampleRate;
        public int mSarDen;
        public int mSarNum;
        public int mTbrDen;
        public int mTbrNum;
        public String mType;
        public int mWidth;

        public BDCloudStreamMeta(int i) {
            this.mIndex = i;
        }

        public String getString(String str) {
            return this.mMeta.getString(str);
        }

        public int getInt(String str) {
            return getInt(str, 0);
        }

        public int getInt(String str, int i) {
            String string = getString(str);
            if (TextUtils.isEmpty(string)) {
                return i;
            }
            try {
                return Integer.parseInt(string);
            } catch (NumberFormatException unused) {
                return i;
            }
        }

        public long getLong(String str) {
            return getLong(str, 0L);
        }

        public long getLong(String str, long j) {
            String string = getString(str);
            if (TextUtils.isEmpty(string)) {
                return j;
            }
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException unused) {
                return j;
            }
        }

        public String getCodecLongNameInline() {
            if (TextUtils.isEmpty(this.mCodecLongName)) {
                return !TextUtils.isEmpty(this.mCodecName) ? this.mCodecName : "N/A";
            }
            return this.mCodecLongName;
        }

        public String getCodecShortNameInline() {
            return !TextUtils.isEmpty(this.mCodecName) ? this.mCodecName : "N/A";
        }

        public String getResolutionInline() {
            return (this.mWidth <= 0 || this.mHeight <= 0) ? "N/A" : (this.mSarNum <= 0 || this.mSarDen <= 0) ? String.format(Locale.US, "%d x %d", Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight)) : String.format(Locale.US, "%d x %d [SAR %d:%d]", Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), Integer.valueOf(this.mSarNum), Integer.valueOf(this.mSarDen));
        }

        public String getFpsInline() {
            int i;
            int i2 = this.mFpsNum;
            return (i2 <= 0 || (i = this.mFpsDen) <= 0) ? "N/A" : String.valueOf(i2 / i);
        }

        public String getBitrateInline() {
            long j = this.mBitrate;
            return j <= 0 ? "N/A" : j < 1000 ? String.format(Locale.US, "%d bit/s", Long.valueOf(this.mBitrate)) : String.format(Locale.US, "%d kb/s", Long.valueOf(this.mBitrate / 1000));
        }

        public String getSampleRateInline() {
            return this.mSampleRate <= 0 ? "N/A" : String.format(Locale.US, "%d Hz", Integer.valueOf(this.mSampleRate));
        }

        public String getChannelLayoutInline() {
            long j = this.mChannelLayout;
            return j <= 0 ? "N/A" : j == 4 ? "mono" : j == 3 ? "stereo" : String.format(Locale.US, "%x", Long.valueOf(this.mChannelLayout));
        }
    }
}

package com.mabeijianxi.smallvideorecord2.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class MediaRecorderConfig implements Parcelable {
    public static final Parcelable.Creator<MediaRecorderConfig> CREATOR = new Parcelable.Creator<MediaRecorderConfig>() { // from class: com.mabeijianxi.smallvideorecord2.model.MediaRecorderConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaRecorderConfig createFromParcel(Parcel parcel) {
            return new MediaRecorderConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaRecorderConfig[] newArray(int i) {
            return new MediaRecorderConfig[i];
        }
    };
    private final boolean FULL_SCREEN;
    private final boolean GO_HOME;
    private final int MAX_FRAME_RATE;
    private final int MIN_FRAME_RATE;
    private final int RECORD_TIME_MAX;
    private final int RECORD_TIME_MIN;
    private final int SMALL_VIDEO_HEIGHT;
    private final int SMALL_VIDEO_WIDTH;
    private final int VIDEO_BITRATE;
    private final int captureThumbnailsTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private MediaRecorderConfig(Buidler buidler) {
        this.FULL_SCREEN = buidler.FULL_SCREEN;
        this.RECORD_TIME_MAX = buidler.RECORD_TIME_MAX;
        this.RECORD_TIME_MIN = buidler.RECORD_TIME_MIN;
        this.MAX_FRAME_RATE = buidler.MAX_FRAME_RATE;
        this.captureThumbnailsTime = buidler.captureThumbnailsTime;
        this.MIN_FRAME_RATE = buidler.MIN_FRAME_RATE;
        this.SMALL_VIDEO_HEIGHT = buidler.SMALL_VIDEO_HEIGHT;
        this.SMALL_VIDEO_WIDTH = buidler.SMALL_VIDEO_WIDTH;
        this.VIDEO_BITRATE = buidler.VIDEO_BITRATE;
        this.GO_HOME = buidler.GO_HOME;
    }

    protected MediaRecorderConfig(Parcel parcel) {
        this.FULL_SCREEN = parcel.readByte() != 0;
        this.RECORD_TIME_MAX = parcel.readInt();
        this.RECORD_TIME_MIN = parcel.readInt();
        this.SMALL_VIDEO_HEIGHT = parcel.readInt();
        this.SMALL_VIDEO_WIDTH = parcel.readInt();
        this.MAX_FRAME_RATE = parcel.readInt();
        this.MIN_FRAME_RATE = parcel.readInt();
        this.VIDEO_BITRATE = parcel.readInt();
        this.captureThumbnailsTime = parcel.readInt();
        this.GO_HOME = parcel.readByte() != 0;
    }

    public boolean isGO_HOME() {
        return this.GO_HOME;
    }

    public boolean getFullScreen() {
        return this.FULL_SCREEN;
    }

    public int getCaptureThumbnailsTime() {
        return this.captureThumbnailsTime;
    }

    public int getMaxFrameRate() {
        return this.MAX_FRAME_RATE;
    }

    public int getMinFrameRate() {
        return this.MIN_FRAME_RATE;
    }

    public int getRecordTimeMax() {
        return this.RECORD_TIME_MAX;
    }

    public int getRecordTimeMin() {
        return this.RECORD_TIME_MIN;
    }

    public int getSmallVideoHeight() {
        return this.SMALL_VIDEO_HEIGHT;
    }

    public int getSmallVideoWidth() {
        return this.SMALL_VIDEO_WIDTH;
    }

    public int getVideoBitrate() {
        return this.VIDEO_BITRATE;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.FULL_SCREEN ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.RECORD_TIME_MAX);
        parcel.writeInt(this.RECORD_TIME_MIN);
        parcel.writeInt(this.SMALL_VIDEO_HEIGHT);
        parcel.writeInt(this.SMALL_VIDEO_WIDTH);
        parcel.writeInt(this.MAX_FRAME_RATE);
        parcel.writeInt(this.MIN_FRAME_RATE);
        parcel.writeInt(this.VIDEO_BITRATE);
        parcel.writeInt(this.captureThumbnailsTime);
        parcel.writeByte(this.GO_HOME ? (byte) 1 : (byte) 0);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Buidler {
        private int VIDEO_BITRATE;
        private int RECORD_TIME_MAX = 6000;
        private int SMALL_VIDEO_HEIGHT = 480;
        private int SMALL_VIDEO_WIDTH = 360;
        private int MAX_FRAME_RATE = 20;
        private int MIN_FRAME_RATE = 8;
        private int captureThumbnailsTime = 1;
        private boolean GO_HOME = false;
        public int RECORD_TIME_MIN = 1500;
        private boolean FULL_SCREEN = false;

        public MediaRecorderConfig build() {
            return new MediaRecorderConfig(this);
        }

        public Buidler captureThumbnailsTime(int i) {
            this.captureThumbnailsTime = i;
            return this;
        }

        public Buidler maxFrameRate(int i) {
            this.MAX_FRAME_RATE = i;
            return this;
        }

        public Buidler minFrameRate(int i) {
            this.MIN_FRAME_RATE = i;
            return this;
        }

        public Buidler recordTimeMax(int i) {
            this.RECORD_TIME_MAX = i;
            return this;
        }

        public Buidler recordTimeMin(int i) {
            this.RECORD_TIME_MIN = i;
            return this;
        }

        public Buidler smallVideoHeight(int i) {
            this.SMALL_VIDEO_HEIGHT = i;
            return this;
        }

        public Buidler smallVideoWidth(int i) {
            this.SMALL_VIDEO_WIDTH = i;
            return this;
        }

        public Buidler videoBitrate(int i) {
            this.VIDEO_BITRATE = i;
            return this;
        }

        public Buidler goHome(boolean z) {
            this.GO_HOME = z;
            return this;
        }

        public Buidler fullScreen(boolean z) {
            this.FULL_SCREEN = z;
            return this;
        }
    }
}

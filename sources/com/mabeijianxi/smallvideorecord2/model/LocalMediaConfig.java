package com.mabeijianxi.smallvideorecord2.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class LocalMediaConfig implements Parcelable {
    public static final Parcelable.Creator<LocalMediaConfig> CREATOR = new Parcelable.Creator<LocalMediaConfig>() { // from class: com.mabeijianxi.smallvideorecord2.model.LocalMediaConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocalMediaConfig createFromParcel(Parcel parcel) {
            return new LocalMediaConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocalMediaConfig[] newArray(int i) {
            return new LocalMediaConfig[i];
        }
    };
    private final int FRAME_RATE;
    private final boolean GO_HOME;
    private final int captureThumbnailsTime;
    private final BaseMediaBitrateConfig compressConfig;
    private final float scale;
    private final String videoAddress;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private LocalMediaConfig(Buidler buidler) {
        this.captureThumbnailsTime = buidler.captureThumbnailsTime;
        this.FRAME_RATE = buidler.FRAME_RATE;
        this.compressConfig = buidler.compressConfig;
        this.videoAddress = buidler.videoPath;
        this.scale = buidler.scale;
        this.GO_HOME = buidler.GO_HOME;
    }

    protected LocalMediaConfig(Parcel parcel) {
        this.FRAME_RATE = parcel.readInt();
        this.captureThumbnailsTime = parcel.readInt();
        this.GO_HOME = parcel.readByte() != 0;
        this.compressConfig = (BaseMediaBitrateConfig) parcel.readParcelable(BaseMediaBitrateConfig.class.getClassLoader());
        this.videoAddress = parcel.readString();
        this.scale = parcel.readFloat();
    }

    public boolean isGO_HOME() {
        return this.GO_HOME;
    }

    public int getCaptureThumbnailsTime() {
        return this.captureThumbnailsTime;
    }

    public int getFrameRate() {
        return this.FRAME_RATE;
    }

    public BaseMediaBitrateConfig getCompressConfig() {
        return this.compressConfig;
    }

    public String getVideoPath() {
        return this.videoAddress;
    }

    public float getScale() {
        return this.scale;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.FRAME_RATE);
        parcel.writeInt(this.captureThumbnailsTime);
        parcel.writeByte(this.GO_HOME ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.compressConfig, i);
        parcel.writeString(this.videoAddress);
        parcel.writeFloat(this.scale);
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Buidler {
        private int FRAME_RATE;
        private BaseMediaBitrateConfig compressConfig;
        private float scale;
        private String videoPath;
        private int captureThumbnailsTime = 1;
        private boolean GO_HOME = false;

        public LocalMediaConfig build() {
            return new LocalMediaConfig(this);
        }

        public Buidler captureThumbnailsTime(int i) {
            this.captureThumbnailsTime = i;
            return this;
        }

        public Buidler doH264Compress(BaseMediaBitrateConfig baseMediaBitrateConfig) {
            this.compressConfig = baseMediaBitrateConfig;
            return this;
        }

        public Buidler goHome(boolean z) {
            this.GO_HOME = z;
            return this;
        }

        public Buidler setFramerate(int i) {
            this.FRAME_RATE = i;
            return this;
        }

        public Buidler setVideoPath(String str) {
            this.videoPath = str;
            return this;
        }

        public Buidler setScale(float f) {
            if (f <= 1.0f) {
                this.scale = 1.0f;
            } else {
                this.scale = f;
            }
            return this;
        }
    }
}

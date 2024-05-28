package com.baidu.rtc.config;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.rtc.RtcParameterSettings;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BRTCScreenShareParams implements Parcelable {
    public static final Parcelable.Creator<BRTCScreenShareParams> CREATOR = new Parcelable.Creator<BRTCScreenShareParams>() { // from class: com.baidu.rtc.config.BRTCScreenShareParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BRTCScreenShareParams createFromParcel(Parcel parcel) {
            return new BRTCScreenShareParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BRTCScreenShareParams[] newArray(int i) {
            return new BRTCScreenShareParams[i];
        }
    };
    public BRTCScreenShareAudioParams mAudioCaptureParams;
    public boolean mEnableAudioCapture;
    public boolean mEnableVideoCapture;
    public BRTCScreenShareVideoParams mVideoCaptureParams;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isChangeVideoFormat(BRTCScreenShareParams bRTCScreenShareParams) {
        if (bRTCScreenShareParams == null) {
            return false;
        }
        return (bRTCScreenShareParams.mVideoCaptureParams.videoFps == this.mVideoCaptureParams.videoFps && bRTCScreenShareParams.mVideoCaptureParams.videoWidth == this.mVideoCaptureParams.videoWidth && bRTCScreenShareParams.mVideoCaptureParams.videoHeight == this.mVideoCaptureParams.videoHeight) ? false : true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mEnableVideoCapture ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mEnableAudioCapture ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.mVideoCaptureParams, i);
        parcel.writeParcelable(this.mAudioCaptureParams, i);
    }

    public void readFromParcel(Parcel parcel) {
        this.mEnableVideoCapture = parcel.readByte() != 0;
        this.mEnableAudioCapture = parcel.readByte() != 0;
        this.mVideoCaptureParams = (BRTCScreenShareVideoParams) parcel.readParcelable(BRTCScreenShareVideoParams.class.getClassLoader());
        this.mAudioCaptureParams = (BRTCScreenShareAudioParams) parcel.readParcelable(BRTCScreenShareAudioParams.class.getClassLoader());
    }

    public BRTCScreenShareParams() {
        this.mEnableVideoCapture = true;
        this.mEnableAudioCapture = false;
        this.mVideoCaptureParams = new BRTCScreenShareVideoParams();
        this.mAudioCaptureParams = new BRTCScreenShareAudioParams();
    }

    protected BRTCScreenShareParams(Parcel parcel) {
        this.mEnableVideoCapture = true;
        this.mEnableAudioCapture = false;
        this.mVideoCaptureParams = new BRTCScreenShareVideoParams();
        this.mAudioCaptureParams = new BRTCScreenShareAudioParams();
        this.mEnableVideoCapture = parcel.readByte() != 0;
        this.mEnableAudioCapture = parcel.readByte() != 0;
        this.mVideoCaptureParams = (BRTCScreenShareVideoParams) parcel.readParcelable(BRTCScreenShareVideoParams.class.getClassLoader());
        this.mAudioCaptureParams = (BRTCScreenShareAudioParams) parcel.readParcelable(BRTCScreenShareAudioParams.class.getClassLoader());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class BRTCScreenShareVideoParams extends RtcParameterSettings.RtcVideoEncodeParams implements Parcelable {
        public static final Parcelable.Creator<BRTCScreenShareVideoParams> CREATOR = new Parcelable.Creator<BRTCScreenShareVideoParams>() { // from class: com.baidu.rtc.config.BRTCScreenShareParams.BRTCScreenShareVideoParams.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BRTCScreenShareVideoParams createFromParcel(Parcel parcel) {
                return new BRTCScreenShareVideoParams(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BRTCScreenShareVideoParams[] newArray(int i) {
                return new BRTCScreenShareVideoParams[i];
            }
        };
        public int videoFps;
        public int videoHeight;
        public int videoMaxkbps;
        public int videoWidth;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public BRTCScreenShareVideoParams() {
            this.videoWidth = 720;
            this.videoHeight = 1280;
            this.videoFps = 10;
            this.videoMaxkbps = 1500;
        }

        protected BRTCScreenShareVideoParams(Parcel parcel) {
            this.videoWidth = 720;
            this.videoHeight = 1280;
            this.videoFps = 10;
            this.videoMaxkbps = 1500;
            this.videoResolution = parcel.readString();
            this.videoWidth = parcel.readInt();
            this.videoHeight = parcel.readInt();
            this.videoFps = parcel.readInt();
            this.videoMaxkbps = parcel.readInt();
            this.videoMinkbps = parcel.readInt();
            this.encodeBitrateMode = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.videoResolution);
            parcel.writeInt(this.videoWidth);
            parcel.writeInt(this.videoHeight);
            parcel.writeInt(this.videoFps);
            parcel.writeInt(this.videoMaxkbps);
            parcel.writeInt(this.videoMinkbps);
            parcel.writeInt(this.encodeBitrateMode);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class BRTCScreenShareAudioParams implements Parcelable {
        public static final Parcelable.Creator<BRTCScreenShareAudioParams> CREATOR = new Parcelable.Creator<BRTCScreenShareAudioParams>() { // from class: com.baidu.rtc.config.BRTCScreenShareParams.BRTCScreenShareAudioParams.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BRTCScreenShareAudioParams createFromParcel(Parcel parcel) {
                return new BRTCScreenShareAudioParams(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BRTCScreenShareAudioParams[] newArray(int i) {
                return new BRTCScreenShareAudioParams[i];
            }
        };
        public int channel;
        public int sampleRate;
        public int volume;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public BRTCScreenShareAudioParams() {
            this.channel = 2;
            this.sampleRate = 48000;
            this.volume = 100;
        }

        protected BRTCScreenShareAudioParams(Parcel parcel) {
            this.channel = 2;
            this.sampleRate = 48000;
            this.volume = 100;
            this.channel = parcel.readInt();
            this.sampleRate = parcel.readInt();
            this.volume = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.channel);
            parcel.writeInt(this.sampleRate);
            parcel.writeInt(this.volume);
        }
    }
}

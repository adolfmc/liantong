package com.baidu.cloud.media.player.misc;

import android.text.TextUtils;
import com.baidu.cloud.media.player.BDCloudMediaMeta;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BDCloudTrackInfo implements ITrackInfo {
    private BDCloudMediaMeta.BDCloudStreamMeta mStreamMeta;
    private int mTrackType = 0;

    public BDCloudTrackInfo(BDCloudMediaMeta.BDCloudStreamMeta bDCloudStreamMeta) {
        this.mStreamMeta = bDCloudStreamMeta;
    }

    public void setMediaMeta(BDCloudMediaMeta.BDCloudStreamMeta bDCloudStreamMeta) {
        this.mStreamMeta = bDCloudStreamMeta;
    }

    @Override // com.baidu.cloud.media.player.misc.ITrackInfo
    public IMediaFormat getFormat() {
        return new BDCloudMediaFormat(this.mStreamMeta);
    }

    @Override // com.baidu.cloud.media.player.misc.ITrackInfo
    public String getLanguage() {
        BDCloudMediaMeta.BDCloudStreamMeta bDCloudStreamMeta = this.mStreamMeta;
        return (bDCloudStreamMeta == null || TextUtils.isEmpty(bDCloudStreamMeta.mLanguage)) ? "und" : this.mStreamMeta.mLanguage;
    }

    @Override // com.baidu.cloud.media.player.misc.ITrackInfo
    public int getTrackType() {
        return this.mTrackType;
    }

    public void setTrackType(int i) {
        this.mTrackType = i;
    }

    public String toString() {
        return getClass().getSimpleName() + '{' + getInfoInline() + "}";
    }

    @Override // com.baidu.cloud.media.player.misc.ITrackInfo
    public String getInfoInline() {
        StringBuilder sb = new StringBuilder(128);
        switch (this.mTrackType) {
            case 1:
                sb.append("VIDEO");
                sb.append(", ");
                sb.append(this.mStreamMeta.getCodecShortNameInline());
                sb.append(", ");
                sb.append(this.mStreamMeta.getBitrateInline());
                sb.append(", ");
                sb.append(this.mStreamMeta.getResolutionInline());
                break;
            case 2:
                sb.append("AUDIO");
                sb.append(", ");
                sb.append(this.mStreamMeta.getCodecShortNameInline());
                sb.append(", ");
                sb.append(this.mStreamMeta.getBitrateInline());
                sb.append(", ");
                sb.append(this.mStreamMeta.getSampleRateInline());
                break;
            case 3:
                sb.append("TIMEDTEXT");
                break;
            case 4:
                sb.append("SUBTITLE");
                break;
            default:
                sb.append("UNKNOWN");
                break;
        }
        return sb.toString();
    }
}

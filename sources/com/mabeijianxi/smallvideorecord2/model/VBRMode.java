package com.mabeijianxi.smallvideorecord2.model;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class VBRMode extends BaseMediaBitrateConfig {
    public VBRMode(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("maxBitrate or bitrate value error!");
        }
        this.maxBitrate = i;
        this.bitrate = i2;
        this.mode = 1;
    }
}

package com.mabeijianxi.smallvideorecord2.model;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CBRMode extends BaseMediaBitrateConfig {
    public CBRMode(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("bufSize or bitrate value error!");
        }
        this.bufSize = i;
        this.bitrate = i2;
        this.mode = 2;
    }
}

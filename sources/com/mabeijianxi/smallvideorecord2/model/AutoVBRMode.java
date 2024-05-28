package com.mabeijianxi.smallvideorecord2.model;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AutoVBRMode extends BaseMediaBitrateConfig {
    public AutoVBRMode() {
        this.mode = 3;
    }

    public AutoVBRMode(int i) {
        if (i < 0 || i > 51) {
            throw new IllegalArgumentException("crfSize 在0~51之间");
        }
        this.crfSize = i;
        this.mode = 3;
    }
}

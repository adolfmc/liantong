package com.bumptech.glide.gifdecoder;

import android.support.annotation.ColorInt;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
class GifFrame {
    static final int DISPOSAL_BACKGROUND = 2;
    static final int DISPOSAL_NONE = 1;
    static final int DISPOSAL_PREVIOUS = 3;
    static final int DISPOSAL_UNSPECIFIED = 0;
    int bufferFrameStart;
    int delay;
    int dispose;

    /* renamed from: ih */
    int f8235ih;
    boolean interlace;

    /* renamed from: iw */
    int f8236iw;

    /* renamed from: ix */
    int f8237ix;

    /* renamed from: iy */
    int f8238iy;
    @ColorInt
    int[] lct;
    int transIndex;
    boolean transparency;
}

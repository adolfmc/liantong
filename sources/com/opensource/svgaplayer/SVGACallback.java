package com.opensource.svgaplayer;

import com.example.asus.detectionandalign.DetectionAuthentic;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SVGACallback.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGACallback;", "", "onFinished", "", "onPause", "onRepeat", "onStep", DetectionAuthentic.FRAME, "", "percentage", "", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface SVGACallback {
    void onFinished();

    void onPause();

    void onRepeat();

    void onStep(int i, double d);
}

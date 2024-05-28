package com.p226hw.videoprocessor;

import android.view.Surface;
import java.util.concurrent.CountDownLatch;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hw.videoprocessor.IVideoEncodeThread */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IVideoEncodeThread {
    CountDownLatch getEglContextLatch();

    Surface getSurface();
}

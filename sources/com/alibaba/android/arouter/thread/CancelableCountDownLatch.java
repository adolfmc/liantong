package com.alibaba.android.arouter.thread;

import java.util.concurrent.CountDownLatch;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CancelableCountDownLatch extends CountDownLatch {
    public CancelableCountDownLatch(int i) {
        super(i);
    }

    public void cancel() {
        while (getCount() > 0) {
            countDown();
        }
    }
}

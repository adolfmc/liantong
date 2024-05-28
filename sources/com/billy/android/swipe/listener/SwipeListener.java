package com.billy.android.swipe.listener;

import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.SwipeConsumer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface SwipeListener {
    void onConsumerAttachedToWrapper(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer);

    void onConsumerDetachedFromWrapper(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer);

    void onSwipeClosed(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i);

    void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i);

    void onSwipeProcess(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i, boolean z, float f);

    void onSwipeRelease(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i, float f, float f2, float f3);

    void onSwipeStart(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i);

    void onSwipeStateChanged(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i, int i2, float f);
}

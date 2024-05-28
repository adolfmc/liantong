package com.billy.android.swipe.consumer;

import android.view.View;
import com.billy.android.swipe.SwipeConsumer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TranslucentSlidingConsumer extends SlidingConsumer {
    @Override // com.billy.android.swipe.consumer.DrawerConsumer
    protected void changeDrawerViewVisibility(int i) {
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer
    public View getDrawerView(int i) {
        return null;
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public void initChildrenFormXml() {
    }

    @Override // com.billy.android.swipe.consumer.SlidingConsumer, com.billy.android.swipe.consumer.DrawerConsumer
    protected void layoutDrawerView() {
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer
    public TranslucentSlidingConsumer setDrawerView(int i, View view) {
        return this;
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public SwipeConsumer setOverSwipeFactor(float f) {
        return this;
    }

    public TranslucentSlidingConsumer() {
        setDrawerViewRequired(false);
    }
}

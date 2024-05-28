package com.billy.android.swipe;

import com.billy.android.swipe.listener.SimpleSwipeListener;
import java.util.LinkedList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SwipeConsumerExclusiveGroup {
    private SwipeConsumer curSwipeConsumer;
    private List<SwipeConsumer> list;
    private boolean lockOther;
    private SimpleSwipeListener singleListener;
    private boolean smooth;

    public SwipeConsumerExclusiveGroup() {
        this.list = new LinkedList();
        this.lockOther = false;
        this.singleListener = new SimpleSwipeListener() { // from class: com.billy.android.swipe.SwipeConsumerExclusiveGroup.1
            @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
            public void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                SwipeConsumerExclusiveGroup.this.markAsCurrent(swipeConsumer);
            }

            @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
            public void onSwipeClosed(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                if (swipeConsumer == SwipeConsumerExclusiveGroup.this.curSwipeConsumer) {
                    SwipeConsumerExclusiveGroup.this.markNoCurrent();
                }
            }
        };
        this.smooth = true;
    }

    public SwipeConsumerExclusiveGroup(boolean z) {
        this.list = new LinkedList();
        this.lockOther = false;
        this.singleListener = new SimpleSwipeListener() { // from class: com.billy.android.swipe.SwipeConsumerExclusiveGroup.1
            @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
            public void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                SwipeConsumerExclusiveGroup.this.markAsCurrent(swipeConsumer);
            }

            @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
            public void onSwipeClosed(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                if (swipeConsumer == SwipeConsumerExclusiveGroup.this.curSwipeConsumer) {
                    SwipeConsumerExclusiveGroup.this.markNoCurrent();
                }
            }
        };
        this.smooth = z;
    }

    public void markNoCurrent() {
        SwipeConsumer swipeConsumer = this.curSwipeConsumer;
        if (swipeConsumer != null) {
            swipeConsumer.close(this.smooth);
            this.curSwipeConsumer = null;
        }
        if (this.lockOther) {
            for (SwipeConsumer swipeConsumer2 : this.list) {
                if (swipeConsumer2.isAllDirectionsLocked()) {
                    swipeConsumer2.unlockAllDirections();
                }
            }
        }
    }

    public void markAsCurrent(SwipeConsumer swipeConsumer) {
        markAsCurrent(swipeConsumer, this.smooth);
    }

    public void markAsCurrent(SwipeConsumer swipeConsumer, boolean z) {
        if (this.curSwipeConsumer == swipeConsumer) {
            return;
        }
        this.curSwipeConsumer = swipeConsumer;
        for (SwipeConsumer swipeConsumer2 : this.list) {
            if (swipeConsumer2 != this.curSwipeConsumer) {
                if (this.lockOther && !swipeConsumer2.isAllDirectionsLocked()) {
                    swipeConsumer2.lockAllDirections();
                }
                swipeConsumer2.close(z);
            }
        }
    }

    public void add(SwipeConsumer swipeConsumer) {
        if (this.list.contains(swipeConsumer)) {
            return;
        }
        this.list.add(swipeConsumer);
        swipeConsumer.addListener(this.singleListener);
    }

    public void remove(SwipeConsumer swipeConsumer) {
        if (swipeConsumer != null) {
            this.list.remove(swipeConsumer);
            swipeConsumer.removeListener(this.singleListener);
        }
    }

    public void clear() {
        while (!this.list.isEmpty()) {
            SwipeConsumer remove = this.list.remove(0);
            if (remove != null) {
                remove.removeListener(this.singleListener);
            }
        }
    }

    public boolean isLockOther() {
        return this.lockOther;
    }

    public void setLockOther(boolean z) {
        this.lockOther = z;
    }

    public SwipeConsumer getCurSwipeConsumer() {
        return this.curSwipeConsumer;
    }

    public boolean isSmooth() {
        return this.smooth;
    }

    public void setSmooth(boolean z) {
        this.smooth = z;
    }
}

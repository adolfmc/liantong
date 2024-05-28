package com.billy.android.swipe;

import android.content.Context;
import android.view.View;
import com.billy.android.swipe.calculator.ScaledCalculator;
import com.billy.android.swipe.consumer.DrawerConsumer;
import com.billy.android.swipe.consumer.SlidingConsumer;
import com.billy.android.swipe.listener.SimpleSwipeListener;
import com.billy.android.swipe.refresh.ClassicFooter;
import com.billy.android.swipe.refresh.ClassicHeader;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SmartSwipeRefresh {
    private static SmartSwipeRefreshViewCreator mCreator;
    private RefreshView mActiveRefreshView;
    private DrawerConsumer mConsumer;
    private SmartSwipeRefreshDataLoader mDataLoader;
    private SmartSwipeRefreshFooter mFooter;
    private SmartSwipeRefreshHeader mHeader;
    private boolean mHorizontal;
    private boolean mNoMoreData;
    private SimpleSwipeListener swipeListener = new SimpleSwipeListener() { // from class: com.billy.android.swipe.SmartSwipeRefresh.1
        /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
        /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
        @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onSwipeStart(com.billy.android.swipe.SmartSwipeWrapper r1, com.billy.android.swipe.SwipeConsumer r2, int r3) {
            /*
                r0 = this;
                com.billy.android.swipe.SmartSwipeRefresh r1 = com.billy.android.swipe.SmartSwipeRefresh.this
                r2 = 0
                com.billy.android.swipe.SmartSwipeRefresh.access$002(r1, r2)
                r1 = 4
                if (r3 == r1) goto L1b
                r1 = 8
                if (r3 == r1) goto L11
                switch(r3) {
                    case 1: goto L1b;
                    case 2: goto L11;
                    default: goto L10;
                }
            L10:
                goto L24
            L11:
                com.billy.android.swipe.SmartSwipeRefresh r1 = com.billy.android.swipe.SmartSwipeRefresh.this
                com.billy.android.swipe.SmartSwipeRefresh$SmartSwipeRefreshFooter r2 = com.billy.android.swipe.SmartSwipeRefresh.access$200(r1)
                com.billy.android.swipe.SmartSwipeRefresh.access$002(r1, r2)
                goto L24
            L1b:
                com.billy.android.swipe.SmartSwipeRefresh r1 = com.billy.android.swipe.SmartSwipeRefresh.this
                com.billy.android.swipe.SmartSwipeRefresh$SmartSwipeRefreshHeader r2 = com.billy.android.swipe.SmartSwipeRefresh.access$100(r1)
                com.billy.android.swipe.SmartSwipeRefresh.access$002(r1, r2)
            L24:
                com.billy.android.swipe.SmartSwipeRefresh r1 = com.billy.android.swipe.SmartSwipeRefresh.this
                com.billy.android.swipe.SmartSwipeRefresh$RefreshView r1 = com.billy.android.swipe.SmartSwipeRefresh.access$000(r1)
                if (r1 == 0) goto L35
                com.billy.android.swipe.SmartSwipeRefresh r1 = com.billy.android.swipe.SmartSwipeRefresh.this
                com.billy.android.swipe.SmartSwipeRefresh$RefreshView r1 = com.billy.android.swipe.SmartSwipeRefresh.access$000(r1)
                r1.onStartDragging()
            L35:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.billy.android.swipe.SmartSwipeRefresh.C33491.onSwipeStart(com.billy.android.swipe.SmartSwipeWrapper, com.billy.android.swipe.SwipeConsumer, int):void");
        }

        @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
        public void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
            if (SmartSwipeRefresh.this.mDataLoader != null) {
                if (SmartSwipeRefresh.this.mActiveRefreshView != SmartSwipeRefresh.this.mHeader) {
                    if (SmartSwipeRefresh.this.mActiveRefreshView == SmartSwipeRefresh.this.mFooter) {
                        swipeConsumer.lockAllDirections();
                        SmartSwipeRefresh.this.mActiveRefreshView.onDataLoading();
                        if (!SmartSwipeRefresh.this.mNoMoreData) {
                            SmartSwipeRefresh.this.mDataLoader.onLoadMore(SmartSwipeRefresh.this);
                            return;
                        } else {
                            SmartSwipeRefresh.this.finished(true);
                            return;
                        }
                    }
                    return;
                }
                swipeConsumer.lockAllDirections();
                SmartSwipeRefresh.this.mActiveRefreshView.onDataLoading();
                SmartSwipeRefresh.this.mDataLoader.onRefresh(SmartSwipeRefresh.this);
                return;
            }
            SmartSwipeRefresh.this.finished(false);
        }

        @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
        public void onSwipeClosed(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
            swipeConsumer.unlockAllDirections();
            if (SmartSwipeRefresh.this.mActiveRefreshView != null) {
                SmartSwipeRefresh.this.mActiveRefreshView.onReset();
                SmartSwipeRefresh.this.mActiveRefreshView = null;
            }
        }

        @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
        public void onSwipeProcess(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i, boolean z, float f) {
            if (SmartSwipeRefresh.this.mActiveRefreshView != null) {
                SmartSwipeRefresh.this.mActiveRefreshView.onProgress(!z, f);
            }
        }
    };
    private Runnable mResetRunnable = new Runnable() { // from class: com.billy.android.swipe.SmartSwipeRefresh.3
        @Override // java.lang.Runnable
        public void run() {
            SmartSwipeRefresh.this.mConsumer.smoothClose();
            SmartSwipeRefresh.this.mConsumer.unlockAllDirections();
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface RefreshView {
        View getView();

        void onDataLoading();

        long onFinish(boolean z);

        void onInit(boolean z);

        void onProgress(boolean z, float f);

        void onReset();

        void onStartDragging();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface SmartSwipeRefreshDataLoader {
        void onLoadMore(SmartSwipeRefresh smartSwipeRefresh);

        void onRefresh(SmartSwipeRefresh smartSwipeRefresh);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface SmartSwipeRefreshFooter extends RefreshView {
        void setNoMoreData(boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface SmartSwipeRefreshHeader extends RefreshView {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface SmartSwipeRefreshViewCreator {
        SmartSwipeRefreshFooter createRefreshFooter(Context context);

        SmartSwipeRefreshHeader createRefreshHeader(Context context);
    }

    public static void setDefaultRefreshViewCreator(SmartSwipeRefreshViewCreator smartSwipeRefreshViewCreator) {
        mCreator = smartSwipeRefreshViewCreator;
    }

    public static SmartSwipeRefresh drawerMode(View view, boolean z) {
        return drawerMode(view, z, true);
    }

    public static SmartSwipeRefresh drawerMode(View view, boolean z, boolean z2) {
        return create(view, new DrawerConsumer(), z, z2);
    }

    public static SmartSwipeRefresh behindMode(View view, boolean z) {
        return behindMode(view, z, true);
    }

    public static SmartSwipeRefresh behindMode(View view, boolean z, boolean z2) {
        return slideMode(view, 0.0f, z, z2);
    }

    public static SmartSwipeRefresh translateMode(View view, boolean z) {
        return translateMode(view, z, true);
    }

    public static SmartSwipeRefresh translateMode(View view, boolean z, boolean z2) {
        return slideMode(view, 1.0f, z, z2);
    }

    public static SmartSwipeRefresh scaleMode(View view, boolean z) {
        return scaleMode(view, z, true);
    }

    public static SmartSwipeRefresh scaleMode(View view, boolean z, boolean z2) {
        return slideMode(view, 0.5f, z, z2);
    }

    public static SmartSwipeRefresh slideMode(View view, float f, boolean z, boolean z2) {
        return create(view, new SlidingConsumer().setRelativeMoveFactor(f), z, z2);
    }

    public static SmartSwipeRefresh create(View view, DrawerConsumer drawerConsumer, boolean z, boolean z2) {
        SmartSwipeRefresh smartSwipeRefresh = new SmartSwipeRefresh();
        smartSwipeRefresh.mConsumer = (DrawerConsumer) ((DrawerConsumer) SmartSwipe.wrap(view).addConsumer(drawerConsumer)).setDisableSwipeOnSettling(true).addListener(smartSwipeRefresh.swipeListener).setSwipeDistanceCalculator(new ScaledCalculator(0.4f)).setReleaseMode(5).setOverSwipeFactor(0.5f).enableNestedFlyTop(false).enableNestedFlyLeft(false).m17389as(DrawerConsumer.class);
        smartSwipeRefresh.mHorizontal = z;
        if (z2) {
            SmartSwipeRefreshViewCreator smartSwipeRefreshViewCreator = mCreator;
            if (smartSwipeRefreshViewCreator != null) {
                smartSwipeRefresh.setHeader(smartSwipeRefreshViewCreator.createRefreshHeader(view.getContext()));
                smartSwipeRefresh.setFooter(mCreator.createRefreshFooter(view.getContext()));
            } else {
                smartSwipeRefresh.setHeader(new ClassicHeader(view.getContext()));
                smartSwipeRefresh.setFooter(new ClassicFooter(view.getContext()));
            }
        }
        return smartSwipeRefresh;
    }

    public SmartSwipeRefresh disableRefresh() {
        this.mConsumer.disableDirection(this.mHorizontal ? 1 : 4);
        return this;
    }

    public SmartSwipeRefresh disableLoadMore() {
        this.mConsumer.disableDirection(this.mHorizontal ? 2 : 8);
        return this;
    }

    public SmartSwipeRefresh startRefresh() {
        openDirection(this.mHorizontal ? 1 : 4);
        return this;
    }

    public SmartSwipeRefresh startLoadMore() {
        openDirection(this.mHorizontal ? 2 : 8);
        return this;
    }

    private void openDirection(final int i) {
        this.mConsumer.lockAllDirections();
        this.mConsumer.getWrapper().post(new Runnable() { // from class: com.billy.android.swipe.SmartSwipeRefresh.2
            @Override // java.lang.Runnable
            public void run() {
                SmartSwipeRefresh.this.mConsumer.open(true, i);
            }
        });
    }

    public SmartSwipeRefresh finished(boolean z) {
        RefreshView refreshView = this.mActiveRefreshView;
        if (refreshView != null) {
            if (z && refreshView == this.mHeader) {
                setNoMoreData(false);
            }
            long onFinish = this.mActiveRefreshView.onFinish(z);
            if (onFinish > 0) {
                this.mConsumer.getWrapper().postDelayed(this.mResetRunnable, onFinish);
                return null;
            }
        }
        this.mConsumer.smoothClose();
        return this;
    }

    public SmartSwipeRefreshDataLoader getDataLoader() {
        return this.mDataLoader;
    }

    public SmartSwipeRefresh setDataLoader(SmartSwipeRefreshDataLoader smartSwipeRefreshDataLoader) {
        this.mDataLoader = smartSwipeRefreshDataLoader;
        return this;
    }

    public boolean isNoMoreData() {
        return this.mNoMoreData;
    }

    public SmartSwipeRefresh setNoMoreData(boolean z) {
        this.mNoMoreData = z;
        SmartSwipeRefreshFooter smartSwipeRefreshFooter = this.mFooter;
        if (smartSwipeRefreshFooter != null) {
            smartSwipeRefreshFooter.setNoMoreData(z);
        }
        return this;
    }

    public SmartSwipeRefreshHeader getHeader() {
        return this.mHeader;
    }

    public SmartSwipeRefresh setHeader(SmartSwipeRefreshHeader smartSwipeRefreshHeader) {
        this.mHeader = smartSwipeRefreshHeader;
        if (smartSwipeRefreshHeader != null) {
            smartSwipeRefreshHeader.onInit(this.mHorizontal);
        }
        this.mConsumer.setDrawerView(this.mHorizontal ? 1 : 4, smartSwipeRefreshHeader == null ? null : smartSwipeRefreshHeader.getView());
        return this;
    }

    public SmartSwipeRefreshFooter getFooter() {
        return this.mFooter;
    }

    public SmartSwipeRefresh setFooter(SmartSwipeRefreshFooter smartSwipeRefreshFooter) {
        this.mFooter = smartSwipeRefreshFooter;
        if (smartSwipeRefreshFooter != null) {
            smartSwipeRefreshFooter.onInit(this.mHorizontal);
        }
        this.mConsumer.setDrawerView(this.mHorizontal ? 2 : 8, smartSwipeRefreshFooter == null ? null : smartSwipeRefreshFooter.getView());
        return this;
    }

    public boolean isHorizontal() {
        return this.mHorizontal;
    }

    public DrawerConsumer getSwipeConsumer() {
        return this.mConsumer;
    }
}

package cn.finalteam.toolsfinal.adapter;

import android.os.Build;
import android.support.p083v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class RecyclingPagerAdapter extends PagerAdapter {
    static final int IGNORE_ITEM_VIEW_TYPE = -1;
    private final RecycleBin recycleBin;

    public int getItemViewType(int i) {
        return 0;
    }

    public abstract View getView(int i, View view, ViewGroup viewGroup);

    public int getViewTypeCount() {
        return 1;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public final boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public RecyclingPagerAdapter() {
        this(new RecycleBin());
    }

    RecyclingPagerAdapter(RecycleBin recycleBin) {
        this.recycleBin = recycleBin;
        recycleBin.setViewTypeCount(getViewTypeCount());
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public void notifyDataSetChanged() {
        this.recycleBin.scrapActiveViews();
        super.notifyDataSetChanged();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        int itemViewType = getItemViewType(i);
        View view = getView(i, itemViewType != -1 ? this.recycleBin.getScrapView(i, itemViewType) : null, viewGroup);
        viewGroup.addView(view);
        return view;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        View view = (View) obj;
        viewGroup.removeView(view);
        int itemViewType = getItemViewType(i);
        if (itemViewType != -1) {
            this.recycleBin.addScrapView(view, i, itemViewType);
        }
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class RecycleBin {
        private SparseArray<View> currentScrapViews;
        private SparseArray<View>[] scrapViews;
        private int viewTypeCount;
        private View[] activeViews = new View[0];
        private int[] activeViewTypes = new int[0];

        protected boolean shouldRecycleViewType(int i) {
            return i >= 0;
        }

        public void setViewTypeCount(int i) {
            if (i < 1) {
                throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
            }
            SparseArray<View>[] sparseArrayArr = new SparseArray[i];
            for (int i2 = 0; i2 < i; i2++) {
                sparseArrayArr[i2] = new SparseArray<>();
            }
            this.viewTypeCount = i;
            this.currentScrapViews = sparseArrayArr[0];
            this.scrapViews = sparseArrayArr;
        }

        View getScrapView(int i, int i2) {
            if (this.viewTypeCount == 1) {
                return retrieveFromScrap(this.currentScrapViews, i);
            }
            if (i2 >= 0) {
                SparseArray<View>[] sparseArrayArr = this.scrapViews;
                if (i2 < sparseArrayArr.length) {
                    return retrieveFromScrap(sparseArrayArr[i2], i);
                }
                return null;
            }
            return null;
        }

        void addScrapView(View view, int i, int i2) {
            if (this.viewTypeCount == 1) {
                this.currentScrapViews.put(i, view);
            } else {
                this.scrapViews[i2].put(i, view);
            }
            if (Build.VERSION.SDK_INT >= 14) {
                view.setAccessibilityDelegate(null);
            }
        }

        void scrapActiveViews() {
            View[] viewArr = this.activeViews;
            int[] iArr = this.activeViewTypes;
            boolean z = this.viewTypeCount > 1;
            SparseArray<View> sparseArray = this.currentScrapViews;
            for (int length = viewArr.length - 1; length >= 0; length--) {
                View view = viewArr[length];
                if (view != null) {
                    int i = iArr[length];
                    viewArr[length] = null;
                    iArr[length] = -1;
                    if (shouldRecycleViewType(i)) {
                        if (z) {
                            sparseArray = this.scrapViews[i];
                        }
                        sparseArray.put(length, view);
                        if (Build.VERSION.SDK_INT >= 14) {
                            view.setAccessibilityDelegate(null);
                        }
                    }
                }
            }
            pruneScrapViews();
        }

        private void pruneScrapViews() {
            int length = this.activeViews.length;
            int i = this.viewTypeCount;
            SparseArray<View>[] sparseArrayArr = this.scrapViews;
            for (int i2 = 0; i2 < i; i2++) {
                SparseArray<View> sparseArray = sparseArrayArr[i2];
                int size = sparseArray.size();
                int i3 = size - length;
                int i4 = size - 1;
                int i5 = 0;
                while (i5 < i3) {
                    sparseArray.remove(sparseArray.keyAt(i4));
                    i5++;
                    i4--;
                }
            }
        }

        static View retrieveFromScrap(SparseArray<View> sparseArray, int i) {
            int size = sparseArray.size();
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    int keyAt = sparseArray.keyAt(i2);
                    View view = sparseArray.get(keyAt);
                    if (keyAt == i) {
                        sparseArray.remove(keyAt);
                        return view;
                    }
                }
                int i3 = size - 1;
                View valueAt = sparseArray.valueAt(i3);
                sparseArray.remove(sparseArray.keyAt(i3));
                return valueAt;
            }
            return null;
        }
    }
}

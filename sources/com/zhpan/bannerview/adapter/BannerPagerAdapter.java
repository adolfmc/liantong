package com.zhpan.bannerview.adapter;

import android.support.annotation.NonNull;
import android.support.p083v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.zhpan.bannerview.holder.HolderCreator;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.utils.PositionUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BannerPagerAdapter<T, VH extends ViewHolder> extends PagerAdapter {
    public static final int MAX_VALUE = Integer.MAX_VALUE;
    private HolderCreator holderCreator;
    private boolean isCanLoop;
    private List<T> mList;
    private PageClickListener mPageClickListener;
    private List<View> mViewList = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface PageClickListener {
        void onPageClick(int i);
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    public BannerPagerAdapter(List<T> list, HolderCreator<VH> holderCreator) {
        this.mList = list;
        this.holderCreator = holderCreator;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        if (!this.isCanLoop || this.mList.size() <= 1) {
            return this.mList.size();
        }
        return Integer.MAX_VALUE;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        View findViewByPosition = findViewByPosition(viewGroup, PositionUtils.getRealPosition(this.isCanLoop, i, this.mList.size()));
        viewGroup.addView(findViewByPosition);
        return findViewByPosition;
    }

    private View findViewByPosition(ViewGroup viewGroup, int i) {
        for (View view : this.mViewList) {
            if (((Integer) view.getTag()).intValue() == i && view.getParent() == null) {
                return view;
            }
        }
        View view2 = getView(i, viewGroup);
        view2.setTag(Integer.valueOf(i));
        this.mViewList.add(view2);
        return view2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View getView(int i, ViewGroup viewGroup) {
        ViewHolder<T> createViewHolder = this.holderCreator.createViewHolder();
        if (createViewHolder == null) {
            throw new NullPointerException("can not return a null holder");
        }
        return createView(createViewHolder, i, viewGroup);
    }

    private View createView(ViewHolder<T> viewHolder, int i, ViewGroup viewGroup) {
        List<T> list = this.mList;
        if (list == null || list.size() <= 0) {
            return null;
        }
        View createView = viewHolder.createView(viewGroup, viewGroup.getContext(), i);
        viewHolder.onBind(viewGroup.getContext(), this.mList.get(i), i, this.mList.size());
        setViewListener(createView, i);
        return createView;
    }

    private void setViewListener(View view, final int i) {
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.zhpan.bannerview.adapter.-$$Lambda$BannerPagerAdapter$shXwBs3X7_tE5wi5qmTnhv3Mhr8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    BannerPagerAdapter.lambda$setViewListener$0(BannerPagerAdapter.this, i, view2);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$setViewListener$0(BannerPagerAdapter bannerPagerAdapter, int i, View view) {
        PageClickListener pageClickListener = bannerPagerAdapter.mPageClickListener;
        if (pageClickListener != null) {
            pageClickListener.onPageClick(i);
        }
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public void finishUpdate(@NonNull ViewGroup viewGroup) {
        super.finishUpdate(viewGroup);
    }

    public void setPageClickListener(PageClickListener pageClickListener) {
        this.mPageClickListener = pageClickListener;
    }

    public void setCanLoop(boolean z) {
        this.isCanLoop = z;
    }
}

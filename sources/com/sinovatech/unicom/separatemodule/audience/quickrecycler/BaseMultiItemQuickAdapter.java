package com.sinovatech.unicom.separatemodule.audience.quickrecycler;

import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import android.view.ViewGroup;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.entity.IExpandable;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.entity.MultiItemEntity;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class BaseMultiItemQuickAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    private static final int DEFAULT_VIEW_TYPE = -255;
    public static final int TYPE_NOT_FOUND = -404;
    private SparseIntArray layouts;

    public BaseMultiItemQuickAdapter(List<T> list) {
        super(list);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
    protected int getDefItemViewType(int i) {
        MultiItemEntity multiItemEntity = (MultiItemEntity) this.mData.get(i);
        if (multiItemEntity != null) {
            return multiItemEntity.getItemType();
        }
        return -255;
    }

    protected void setDefaultViewTypeLayout(@LayoutRes int i) {
        addItemType(-255, i);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
    protected K onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return createBaseViewHolder(viewGroup, getLayoutId(i));
    }

    private int getLayoutId(int i) {
        return this.layouts.get(i, TYPE_NOT_FOUND);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addItemType(int i, @LayoutRes int i2) {
        if (this.layouts == null) {
            this.layouts = new SparseIntArray();
        }
        this.layouts.put(i, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
    public void remove(@IntRange(from = 0) int i) {
        if (this.mData == null || i < 0 || i >= this.mData.size()) {
            return;
        }
        MultiItemEntity multiItemEntity = (MultiItemEntity) this.mData.get(i);
        if (multiItemEntity instanceof IExpandable) {
            removeAllChild((IExpandable) multiItemEntity, i);
        }
        removeDataFromParent(multiItemEntity);
        super.remove(i);
    }

    protected void removeAllChild(IExpandable iExpandable, int i) {
        List subItems;
        if (!iExpandable.isExpanded() || (subItems = iExpandable.getSubItems()) == null || subItems.size() == 0) {
            return;
        }
        int size = subItems.size();
        for (int i2 = 0; i2 < size; i2++) {
            remove(i + 1);
        }
    }

    protected void removeDataFromParent(T t) {
        int parentPosition = getParentPosition(t);
        if (parentPosition >= 0) {
            ((IExpandable) this.mData.get(parentPosition)).getSubItems().remove(t);
        }
    }

    public int getParentPositionInAll(int i) {
        List<T> data = getData();
        MultiItemEntity multiItemEntity = (MultiItemEntity) getItem(i);
        if (!isExpandable(multiItemEntity)) {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (isExpandable((MultiItemEntity) data.get(i2))) {
                    return i2;
                }
            }
            return -1;
        }
        IExpandable iExpandable = (IExpandable) multiItemEntity;
        for (int i3 = i - 1; i3 >= 0; i3--) {
            MultiItemEntity multiItemEntity2 = (MultiItemEntity) data.get(i3);
            if (isExpandable(multiItemEntity2) && iExpandable.getLevel() > ((IExpandable) multiItemEntity2).getLevel()) {
                return i3;
            }
        }
        return -1;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
    public boolean isExpandable(MultiItemEntity multiItemEntity) {
        return multiItemEntity != null && (multiItemEntity instanceof IExpandable);
    }
}

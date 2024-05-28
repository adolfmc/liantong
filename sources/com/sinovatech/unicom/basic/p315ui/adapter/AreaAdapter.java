package com.sinovatech.unicom.basic.p315ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p314po.AreaEntity;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.AreaAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AreaAdapter extends BaseAdapter implements Filterable {
    private int containerRes;
    private Context context;
    private OnItemAdapterListener listener;
    private ArrayFilter mFilter;
    private final Object mLock = new Object();
    private List<AreaEntity> mObjects = new ArrayList();
    private List<AreaEntity> mOriginalValues;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.AreaAdapter$OnItemAdapterListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemAdapterListener {
        void onItemSelected(String str, String str2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public AreaAdapter(Context context, int i, List<AreaEntity> list, OnItemAdapterListener onItemAdapterListener) {
        this.context = context;
        this.containerRes = i;
        this.mOriginalValues = list;
        this.listener = onItemAdapterListener;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.mFilter == null) {
            this.mFilter = new ArrayFilter();
        }
        return this.mFilter;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mObjects.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mObjects.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View inflate = View.inflate(this.context, this.containerRes, null);
        ((TextView) inflate.findViewById(2131298755)).setText(this.mObjects.get(i).getAreacode() + this.mObjects.get(i).getAreaname());
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.AreaAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                AreaAdapter.this.listener.onItemSelected(((AreaEntity) AreaAdapter.this.mObjects.get(i)).getAreaname(), ((AreaEntity) AreaAdapter.this.mObjects.get(i)).getAreaid());
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        return inflate;
    }

    public List<AreaEntity> getAllItems() {
        return this.mOriginalValues;
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.AreaAdapter$ArrayFilter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class ArrayFilter extends Filter {
        private ArrayFilter() {
        }

        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (TextUtils.isEmpty(charSequence)) {
                synchronized (AreaAdapter.this.mLock) {
                    ArrayList arrayList = new ArrayList(AreaAdapter.this.mOriginalValues);
                    filterResults.values = arrayList;
                    filterResults.count = arrayList.size();
                }
                return filterResults;
            }
            String lowerCase = charSequence.toString().trim().toLowerCase();
            int size = AreaAdapter.this.mOriginalValues.size();
            ArrayList arrayList2 = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                if ((((AreaEntity) AreaAdapter.this.mOriginalValues.get(i)).getAreacode() + ((AreaEntity) AreaAdapter.this.mOriginalValues.get(i)).getAreaname() + ((AreaEntity) AreaAdapter.this.mOriginalValues.get(i)).getPinyin()).toLowerCase().contains(lowerCase)) {
                    arrayList2.add((AreaEntity) AreaAdapter.this.mOriginalValues.get(i));
                }
            }
            filterResults.values = arrayList2;
            filterResults.count = arrayList2.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            AreaAdapter.this.mObjects = (List) filterResults.values;
            if (filterResults.count > 0) {
                AreaAdapter.this.notifyDataSetChanged();
            } else {
                AreaAdapter.this.notifyDataSetInvalidated();
            }
        }
    }
}

package com.sinovatech.unicom.basic.p315ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p314po.LoginAccountEntity;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.PhoneAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PhoneAdapter extends BaseAdapter implements Filterable {
    private int containerRes;
    private Context context;
    private OnPhoneAdapterListener listener;
    private ArrayFilter mFilter;
    private List<LoginAccountEntity> mOriginalValues;
    private final Object mLock = new Object();
    public boolean isClickSelectButton = false;
    public boolean isClickSelectItem = false;
    private List<LoginAccountEntity> mObjects = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.PhoneAdapter$OnPhoneAdapterListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnPhoneAdapterListener {
        void onDeleteItem(LoginAccountEntity loginAccountEntity);

        void onItemSelected(LoginAccountEntity loginAccountEntity);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public PhoneAdapter(Context context, int i, List<LoginAccountEntity> list, OnPhoneAdapterListener onPhoneAdapterListener) {
        this.context = context;
        this.containerRes = i;
        this.mOriginalValues = list;
        this.listener = onPhoneAdapterListener;
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
        TextView textView = (TextView) inflate.findViewById(2131297823);
        ImageView imageView = (ImageView) inflate.findViewById(2131297820);
        final LoginAccountEntity loginAccountEntity = this.mObjects.get(i);
        if (TextUtils.isEmpty(loginAccountEntity.getAccountnameJiami())) {
            textView.setText(loginAccountEntity.getAccountname());
        } else {
            textView.setText(loginAccountEntity.getAccountnameJiami());
        }
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.PhoneAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                PhoneAdapter.this.listener.onItemSelected(loginAccountEntity);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.PhoneAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                PhoneAdapter.this.mOriginalValues.remove((LoginAccountEntity) PhoneAdapter.this.mObjects.remove(i));
                PhoneAdapter.this.notifyDataSetChanged();
                PhoneAdapter.this.listener.onDeleteItem(loginAccountEntity);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        return inflate;
    }

    public List<LoginAccountEntity> getAllItems() {
        return this.mOriginalValues;
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.PhoneAdapter$ArrayFilter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class ArrayFilter extends Filter {
        private ArrayFilter() {
        }

        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (TextUtils.isEmpty(charSequence) || PhoneAdapter.this.isClickSelectButton) {
                synchronized (PhoneAdapter.this.mLock) {
                    ArrayList arrayList = new ArrayList(PhoneAdapter.this.mOriginalValues);
                    filterResults.values = arrayList;
                    filterResults.count = arrayList.size();
                }
                return filterResults;
            } else if (PhoneAdapter.this.isClickSelectItem) {
                ArrayList arrayList2 = new ArrayList();
                filterResults.values = arrayList2;
                filterResults.count = arrayList2.size();
                return filterResults;
            } else {
                String lowerCase = charSequence.toString().trim().toLowerCase();
                int size = PhoneAdapter.this.mOriginalValues.size();
                ArrayList arrayList3 = new ArrayList(size);
                for (int i = 0; i < size; i++) {
                    LoginAccountEntity loginAccountEntity = (LoginAccountEntity) PhoneAdapter.this.mOriginalValues.get(i);
                    if (loginAccountEntity.getAccountname().toLowerCase().startsWith(lowerCase)) {
                        arrayList3.add(loginAccountEntity);
                    }
                }
                filterResults.values = arrayList3;
                filterResults.count = arrayList3.size();
                return filterResults;
            }
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            PhoneAdapter.this.mObjects = (List) filterResults.values;
            if (filterResults.count > 0) {
                PhoneAdapter.this.notifyDataSetChanged();
            } else {
                PhoneAdapter.this.notifyDataSetInvalidated();
            }
        }
    }
}

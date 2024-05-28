package com.sinovatech.unicom.separatemodule.user.flowlayout;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import java.util.Collection;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class FlowLayoutAdapter<T> {
    private List<T> list_bean;
    private OnDataSetChangedListener onDataSetChangedListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnDataSetChangedListener {
        void onDataSetChanged();
    }

    public int getItemCount() {
        return 0;
    }

    public abstract void onBindViewHolder(ViewHolder viewHolder, int i, T t);

    public abstract int onCreateViewHolder(int i, T t);

    public abstract void onItemClick(int i, T t);

    public void onItemLongClick(int i, T t) {
    }

    public FlowLayoutAdapter(List<T> list) {
        this.list_bean = list;
    }

    public int getCount() {
        int itemCount = getItemCount();
        return (itemCount == 0 || itemCount > this.list_bean.size()) ? this.list_bean.size() : itemCount;
    }

    public View getView(FlowLayout flowLayout, final int i) {
        View inflate = LayoutInflater.from(flowLayout.getContext()).inflate(onCreateViewHolder(i, this.list_bean.get(i)), (ViewGroup) flowLayout, false);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.flowlayout.FlowLayoutAdapter.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                FlowLayoutAdapter flowLayoutAdapter = FlowLayoutAdapter.this;
                flowLayoutAdapter.onItemClick(i, flowLayoutAdapter.list_bean.get(i));
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        inflate.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.flowlayout.FlowLayoutAdapter.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                NBSActionInstrumentation.onLongClickEventEnter(view, this);
                FlowLayoutAdapter flowLayoutAdapter = FlowLayoutAdapter.this;
                flowLayoutAdapter.onItemLongClick(i, flowLayoutAdapter.list_bean.get(i));
                NBSActionInstrumentation.onLongClickEventExit();
                return true;
            }
        });
        onBindViewHolder(new ViewHolder(inflate), i, this.list_bean.get(i));
        return inflate;
    }

    public void remove(int i) {
        this.list_bean.remove(i);
        notifyDataSetChanged();
    }

    public void add(T t) {
        this.list_bean.add(t);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        OnDataSetChangedListener onDataSetChangedListener = this.onDataSetChangedListener;
        if (onDataSetChangedListener != null) {
            onDataSetChangedListener.onDataSetChanged();
        }
    }

    public void addNoNotify(T t) {
        this.list_bean.add(t);
    }

    public void addToHead(T t) {
        this.list_bean.add(0, t);
    }

    public int addAll(List<T> list) {
        this.list_bean.addAll(list);
        notifyDataSetChanged();
        return list.size();
    }

    public void addAll(Collection<T> collection) {
        this.list_bean.addAll(collection);
        notifyDataSetChanged();
    }

    public int clearAddAll(List<T> list) {
        this.list_bean.clear();
        this.list_bean.addAll(list);
        notifyDataSetChanged();
        return list.size();
    }

    public void addAllToHead(List<T> list) {
        this.list_bean.addAll(0, list);
        notifyDataSetChanged();
    }

    public void clear() {
        this.list_bean.clear();
        notifyDataSetChanged();
    }

    public void setOnDataSetChangedListener(OnDataSetChangedListener onDataSetChangedListener) {
        this.onDataSetChangedListener = onDataSetChangedListener;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class ViewHolder {
        private SparseArray<View> array_view = new SparseArray<>();
        private View itemView;

        public ViewHolder(View view) {
            this.itemView = view;
        }

        /* JADX WARN: Incorrect return type in method signature: <T:Landroid/view/View;>(I)TT; */
        public View getView(int i) {
            View view = this.array_view.get(i);
            if (view == null) {
                View findViewById = this.itemView.findViewById(i);
                this.array_view.put(i, findViewById);
                return findViewById;
            }
            return view;
        }

        public ViewHolder setVisible(int i) {
            getView(i).setVisibility(0);
            return this;
        }

        public ViewHolder setInVisible(int i) {
            getView(i).setVisibility(4);
            return this;
        }

        public void setViewGone(int i) {
            getView(i).setVisibility(8);
        }

        public void setViewVisible(int i) {
            getView(i).setVisibility(0);
        }

        public void setText(int i, String str) {
            ((TextView) getView(i)).setText(nullToString(str));
        }

        public String nullToString(Object obj) {
            return obj == null ? "" : obj.toString();
        }

        public void setPriceText(int i, String str) {
            ((TextView) getView(i)).setText("¥" + str);
        }

        public void setCountText(int i, String str) {
            ((TextView) getView(i)).setText("x" + str);
        }

        public void setCountText(int i, int i2) {
            ((TextView) getView(i)).setText("x" + i2);
        }

        public void setPriceText(int i, int i2) {
            ((TextView) getView(i)).setText("¥" + i2);
        }

        public void setPriceText(int i, float f) {
            ((TextView) getView(i)).setText("¥" + f);
        }

        public void setText(int i, int i2) {
            ((TextView) getView(i)).setText(String.valueOf(nullToString(Integer.valueOf(i2))));
        }

        public void setTextColor(int i, int i2) {
            ((TextView) getView(i)).setTextColor(i2);
        }

        public String getTVText(int i) {
            return ((TextView) getView(i)).getText().toString().trim();
        }

        public String getETText(int i) {
            return ((EditText) getView(i)).getText().toString().trim();
        }

        public void setBackgroundResource(int i, int i2) {
            getView(i).setBackgroundResource(i2);
        }

        public void setImageBitmap(int i, Bitmap bitmap) {
            ((ImageView) getView(i)).setImageBitmap(bitmap);
        }

        public void setImageResource(int i, int i2) {
            ((ImageView) getView(i)).setImageResource(i2);
        }

        public void setProgress(int i, int i2) {
            ((ProgressBar) getView(i)).setProgress(i2);
        }

        public void setOnClickListener(int i, View.OnClickListener onClickListener) {
            getView(i).setOnClickListener(onClickListener);
        }

        public void setOnLongClickListener(int i, View.OnLongClickListener onLongClickListener) {
            getView(i).setOnLongClickListener(onLongClickListener);
        }
    }
}

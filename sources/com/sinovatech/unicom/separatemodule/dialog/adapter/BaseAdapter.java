package com.sinovatech.unicom.separatemodule.dialog.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.p083v4.content.ContextCompat;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction;
import com.sinovatech.unicom.separatemodule.dialog.adapter.BaseAdapter.ViewHolder;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class BaseAdapter<VH extends BaseAdapter<?>.ViewHolder> extends RecyclerView.Adapter<VH> implements ResourcesAction {
    @Nullable
    private SparseArray<OnChildClickListener> mChildClickListeners;
    @Nullable
    private SparseArray<OnChildLongClickListener> mChildLongClickListeners;
    private final Context mContext;
    @Nullable
    private OnItemClickListener mItemClickListener;
    @Nullable
    private OnItemLongClickListener mItemLongClickListener;
    private int mPositionOffset = 0;
    private RecyclerView mRecyclerView;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnChildClickListener {
        void onChildClick(RecyclerView recyclerView, View view, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnChildLongClickListener {
        boolean onChildLongClick(RecyclerView recyclerView, View view, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemClickListener {
        void onItemClick(RecyclerView recyclerView, View view, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView recyclerView, View view, int i);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
    @ColorInt
    public /* synthetic */ int getColor(@ColorRes int i) {
        int color;
        color = ContextCompat.getColor(getContext(), i);
        return color;
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
    public /* synthetic */ Drawable getDrawable(@DrawableRes int i) {
        Drawable drawable;
        drawable = ContextCompat.getDrawable(getContext(), i);
        return drawable;
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
    public /* synthetic */ Resources getResources() {
        Resources resources;
        resources = getContext().getResources();
        return resources;
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
    public /* synthetic */ String getString(@StringRes int i) {
        String string;
        string = getContext().getString(i);
        return string;
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
    public /* synthetic */ String getString(@StringRes int i, Object... objArr) {
        String string;
        string = getResources().getString(i, objArr);
        return string;
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
    public /* synthetic */ <S> S getSystemService(@NonNull Class<S> cls) {
        Object systemService;
        systemService = ContextCompat.getSystemService(getContext(), cls);
        return (S) systemService;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        onBindViewHolder((BaseAdapter<VH>) ((ViewHolder) viewHolder), i);
    }

    public BaseAdapter(Context context) {
        this.mContext = context;
        if (this.mContext == null) {
            throw new IllegalArgumentException("are you ok?");
        }
    }

    public final void onBindViewHolder(@NonNull VH vh, int i) {
        this.mPositionOffset = i - vh.getAdapterPosition();
        vh.onBindView(i);
    }

    @Nullable
    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
    public Context getContext() {
        return this.mContext;
    }

    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public abstract void onBindView(int i);

        public ViewHolder(BaseAdapter baseAdapter, @LayoutRes int i) {
            this(LayoutInflater.from(baseAdapter.getContext()).inflate(i, (ViewGroup) baseAdapter.mRecyclerView, false));
        }

        public ViewHolder(View view) {
            super(view);
            if (BaseAdapter.this.mItemClickListener != null) {
                view.setOnClickListener(this);
            }
            if (BaseAdapter.this.mItemLongClickListener != null) {
                view.setOnLongClickListener(this);
            }
            if (BaseAdapter.this.mChildClickListeners != null) {
                for (int i = 0; i < BaseAdapter.this.mChildClickListeners.size(); i++) {
                    View findViewById = findViewById(BaseAdapter.this.mChildClickListeners.keyAt(i));
                    if (findViewById != null) {
                        findViewById.setOnClickListener(this);
                    }
                }
            }
            if (BaseAdapter.this.mChildLongClickListeners != null) {
                for (int i2 = 0; i2 < BaseAdapter.this.mChildLongClickListeners.size(); i2++) {
                    View findViewById2 = findViewById(BaseAdapter.this.mChildLongClickListeners.keyAt(i2));
                    if (findViewById2 != null) {
                        findViewById2.setOnLongClickListener(this);
                    }
                }
            }
        }

        protected final int getViewHolderPosition() {
            return getLayoutPosition() + BaseAdapter.this.mPositionOffset;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OnChildClickListener onChildClickListener;
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            int viewHolderPosition = getViewHolderPosition();
            if (viewHolderPosition < 0 || viewHolderPosition >= BaseAdapter.this.getItemCount()) {
                NBSActionInstrumentation.onClickEventExit();
            } else if (view == getItemView()) {
                if (BaseAdapter.this.mItemClickListener != null) {
                    BaseAdapter.this.mItemClickListener.onItemClick(BaseAdapter.this.mRecyclerView, view, viewHolderPosition);
                }
                NBSActionInstrumentation.onClickEventExit();
            } else {
                if (BaseAdapter.this.mChildClickListeners != null && (onChildClickListener = (OnChildClickListener) BaseAdapter.this.mChildClickListeners.get(view.getId())) != null) {
                    onChildClickListener.onChildClick(BaseAdapter.this.mRecyclerView, view, viewHolderPosition);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            OnChildLongClickListener onChildLongClickListener;
            NBSActionInstrumentation.onLongClickEventEnter(view, this);
            int viewHolderPosition = getViewHolderPosition();
            if (viewHolderPosition < 0 || viewHolderPosition >= BaseAdapter.this.getItemCount()) {
                NBSActionInstrumentation.onLongClickEventExit();
                return false;
            } else if (view == getItemView()) {
                if (BaseAdapter.this.mItemLongClickListener != null) {
                    boolean onItemLongClick = BaseAdapter.this.mItemLongClickListener.onItemLongClick(BaseAdapter.this.mRecyclerView, view, viewHolderPosition);
                    NBSActionInstrumentation.onLongClickEventExit();
                    return onItemLongClick;
                }
                NBSActionInstrumentation.onLongClickEventExit();
                return false;
            } else if (BaseAdapter.this.mChildLongClickListeners != null && (onChildLongClickListener = (OnChildLongClickListener) BaseAdapter.this.mChildLongClickListeners.get(view.getId())) != null) {
                boolean onChildLongClick = onChildLongClickListener.onChildLongClick(BaseAdapter.this.mRecyclerView, view, viewHolderPosition);
                NBSActionInstrumentation.onLongClickEventExit();
                return onChildLongClick;
            } else {
                NBSActionInstrumentation.onLongClickEventExit();
                return false;
            }
        }

        public final View getItemView() {
            return this.itemView;
        }

        public final <V extends View> V findViewById(@IdRes int i) {
            return (V) getItemView().findViewById(i);
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager generateDefaultLayoutManager;
        this.mRecyclerView = recyclerView;
        if (this.mRecyclerView.getLayoutManager() != null || (generateDefaultLayoutManager = generateDefaultLayoutManager(this.mContext)) == null) {
            return;
        }
        this.mRecyclerView.setLayoutManager(generateDefaultLayoutManager);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        this.mRecyclerView = null;
    }

    protected RecyclerView.LayoutManager generateDefaultLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        checkRecyclerViewState();
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnChildClickListener(@IdRes int i, @Nullable OnChildClickListener onChildClickListener) {
        checkRecyclerViewState();
        if (this.mChildClickListeners == null) {
            this.mChildClickListeners = new SparseArray<>();
        }
        this.mChildClickListeners.put(i, onChildClickListener);
    }

    public void setOnItemLongClickListener(@Nullable OnItemLongClickListener onItemLongClickListener) {
        checkRecyclerViewState();
        this.mItemLongClickListener = onItemLongClickListener;
    }

    public void setOnChildLongClickListener(@IdRes int i, @Nullable OnChildLongClickListener onChildLongClickListener) {
        checkRecyclerViewState();
        if (this.mChildLongClickListeners == null) {
            this.mChildLongClickListeners = new SparseArray<>();
        }
        this.mChildLongClickListeners.put(i, onChildLongClickListener);
    }

    private void checkRecyclerViewState() {
        if (this.mRecyclerView != null) {
            throw new IllegalStateException("are you ok?");
        }
    }
}

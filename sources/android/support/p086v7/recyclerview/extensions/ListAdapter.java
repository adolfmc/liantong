package android.support.p086v7.recyclerview.extensions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.p086v7.util.AdapterListUpdateCallback;
import android.support.p086v7.util.DiffUtil;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.RecyclerView.ViewHolder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v7.recyclerview.extensions.ListAdapter */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class ListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final AsyncListDiffer<T> mHelper;

    protected ListAdapter(@NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this.mHelper = new AsyncListDiffer<>(new AdapterListUpdateCallback(this), new AsyncDifferConfig.Builder(itemCallback).build());
    }

    protected ListAdapter(@NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mHelper = new AsyncListDiffer<>(new AdapterListUpdateCallback(this), asyncDifferConfig);
    }

    public void submitList(@Nullable List<T> list) {
        this.mHelper.submitList(list);
    }

    protected T getItem(int i) {
        return this.mHelper.getCurrentList().get(i);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mHelper.getCurrentList().size();
    }
}

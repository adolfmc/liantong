package com.liulishuo.okdownload.core.listener.assist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ListenerModel;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ListenerModelHandler<T extends ListenerModel> implements ListenerAssist {
    private Boolean alwaysRecoverModel;
    private final ModelCreator<T> creator;
    final SparseArray<T> modelList = new SparseArray<>();
    volatile T singleTaskModel;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ListenerModel {
        int getId();

        void onInfoValid(@NonNull BreakpointInfo breakpointInfo);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ModelCreator<T extends ListenerModel> {
        T create(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenerModelHandler(ModelCreator<T> modelCreator) {
        this.creator = modelCreator;
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public boolean isAlwaysRecoverAssistModel() {
        Boolean bool = this.alwaysRecoverModel;
        return bool != null && bool.booleanValue();
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public void setAlwaysRecoverAssistModel(boolean z) {
        this.alwaysRecoverModel = Boolean.valueOf(z);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public void setAlwaysRecoverAssistModelIfNotSet(boolean z) {
        if (this.alwaysRecoverModel == null) {
            this.alwaysRecoverModel = Boolean.valueOf(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public T addAndGetModel(@NonNull DownloadTask downloadTask, @Nullable BreakpointInfo breakpointInfo) {
        T create = this.creator.create(downloadTask.getId());
        synchronized (this) {
            if (this.singleTaskModel == null) {
                this.singleTaskModel = create;
            } else {
                this.modelList.put(downloadTask.getId(), create);
            }
            if (breakpointInfo != null) {
                create.onInfoValid(breakpointInfo);
            }
        }
        return create;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public T getOrRecoverModel(@NonNull DownloadTask downloadTask, @Nullable BreakpointInfo breakpointInfo) {
        T t;
        int id = downloadTask.getId();
        synchronized (this) {
            t = (this.singleTaskModel == null || this.singleTaskModel.getId() != id) ? null : this.singleTaskModel;
        }
        if (t == null) {
            t = this.modelList.get(id);
        }
        return (t == null && isAlwaysRecoverAssistModel()) ? addAndGetModel(downloadTask, breakpointInfo) : t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public T removeOrCreate(@NonNull DownloadTask downloadTask, @Nullable BreakpointInfo breakpointInfo) {
        T t;
        int id = downloadTask.getId();
        synchronized (this) {
            if (this.singleTaskModel != null && this.singleTaskModel.getId() == id) {
                t = this.singleTaskModel;
                this.singleTaskModel = null;
            } else {
                t = this.modelList.get(id);
                this.modelList.remove(id);
            }
        }
        if (t == null) {
            t = this.creator.create(id);
            if (breakpointInfo != null) {
                t.onInfoValid(breakpointInfo);
            }
        }
        return t;
    }
}

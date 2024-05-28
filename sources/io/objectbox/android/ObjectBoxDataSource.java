package io.objectbox.android;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import io.objectbox.android.ObjectBoxLiveData;
import io.objectbox.query.Query;
import io.objectbox.reactive.DataObserver;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ObjectBoxDataSource<T> extends PositionalDataSource<T> {
    private final DataObserver<List<T>> observer = new DataObserver<List<T>>() { // from class: io.objectbox.android.ObjectBoxDataSource.1
        @Override // io.objectbox.reactive.DataObserver
        public /* bridge */ /* synthetic */ void onData(@NonNull Object obj) {
            onData((List) ((List) obj));
        }

        public void onData(@NonNull List<T> list) {
            ObjectBoxDataSource.this.invalidate();
        }
    };
    private final Query<T> query;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.arch.lifecycle.LiveData, io.objectbox.android.ObjectBoxLiveData] */
    private List<T> loadRange(int liveData, int i) {
        ?? liveData2 = new LiveData();
        ((ObjectBoxLiveData) liveData2).listener = new ObjectBoxLiveData.C120691();
        ((ObjectBoxLiveData) liveData2).query = i;
        return;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.arch.lifecycle.LiveData, io.objectbox.android.ObjectBoxLiveData] */
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams liveData, @NonNull PositionalDataSource.LoadInitialCallback<T> loadInitialCallback) {
        ?? liveData2 = new LiveData();
        ((ObjectBoxLiveData) liveData2).listener = new ObjectBoxLiveData.C120691();
        ((ObjectBoxLiveData) liveData2).query = loadInitialCallback;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.arch.lifecycle.LiveData, io.objectbox.android.ObjectBoxLiveData] */
    public void loadRange(@NonNull PositionalDataSource.LoadRangeParams liveData, @NonNull PositionalDataSource.LoadRangeCallback<T> loadRangeCallback) {
        ?? liveData2 = new LiveData();
        ((ObjectBoxLiveData) liveData2).listener = new ObjectBoxLiveData.C120691();
        ((ObjectBoxLiveData) liveData2).query = loadRangeCallback;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Factory<Item> extends DataSource.Factory<Integer, Item> {
        private final Query<Item> query;

        public Factory(Query<Item> query) {
            this.query = query;
        }

        public DataSource<Integer, Item> create() {
            return new ObjectBoxDataSource(this.query);
        }
    }

    public ObjectBoxDataSource(Query<T> query) {
        this.query = query;
        query.subscribe().onlyChanges().weak().observer(this.observer);
    }
}

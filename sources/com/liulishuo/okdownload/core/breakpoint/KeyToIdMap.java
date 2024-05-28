package com.liulishuo.okdownload.core.breakpoint;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import java.util.HashMap;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class KeyToIdMap {
    @NonNull
    private final SparseArray<String> idToKeyMap;
    @NonNull
    private final HashMap<String, Integer> keyToIdMap;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyToIdMap() {
        this(new HashMap(), new SparseArray());
    }

    KeyToIdMap(@NonNull HashMap<String, Integer> hashMap, @NonNull SparseArray<String> sparseArray) {
        this.keyToIdMap = hashMap;
        this.idToKeyMap = sparseArray;
    }

    @Nullable
    public Integer get(@NonNull DownloadTask downloadTask) {
        Integer num = this.keyToIdMap.get(generateKey(downloadTask));
        if (num != null) {
            return num;
        }
        return null;
    }

    public void remove(int i) {
        String str = this.idToKeyMap.get(i);
        if (str != null) {
            this.keyToIdMap.remove(str);
            this.idToKeyMap.remove(i);
        }
    }

    public void add(@NonNull DownloadTask downloadTask, int i) {
        String generateKey = generateKey(downloadTask);
        this.keyToIdMap.put(generateKey, Integer.valueOf(i));
        this.idToKeyMap.put(i, generateKey);
    }

    String generateKey(@NonNull DownloadTask downloadTask) {
        return downloadTask.getUrl() + downloadTask.getUri() + downloadTask.getFilename();
    }
}

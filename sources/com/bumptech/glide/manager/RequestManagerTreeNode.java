package com.bumptech.glide.manager;

import android.support.annotation.NonNull;
import com.bumptech.glide.RequestManager;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface RequestManagerTreeNode {
    @NonNull
    Set<RequestManager> getDescendants();
}

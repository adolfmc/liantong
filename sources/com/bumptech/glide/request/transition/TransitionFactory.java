package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface TransitionFactory<R> {
    Transition<R> build(DataSource dataSource, boolean z);
}

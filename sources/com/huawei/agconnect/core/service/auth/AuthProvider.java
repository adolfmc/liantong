package com.huawei.agconnect.core.service.auth;

import com.huawei.hmf.tasks.Task;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface AuthProvider {
    void addTokenListener(OnTokenListener onTokenListener);

    Task<Token> getTokens();

    Task<Token> getTokens(boolean z);

    String getUid();

    void removeTokenListener(OnTokenListener onTokenListener);
}

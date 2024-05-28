package com.huawei.agconnect.core.service.auth;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface Token {
    long getExpiration();

    long getIssuedAt();

    long getNotBefore();

    String getTokenString();
}

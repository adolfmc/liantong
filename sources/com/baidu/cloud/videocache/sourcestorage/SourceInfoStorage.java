package com.baidu.cloud.videocache.sourcestorage;

import com.baidu.cloud.videocache.C2577m;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface SourceInfoStorage {
    C2577m get(String str);

    void put(String str, C2577m c2577m);

    void release();
}

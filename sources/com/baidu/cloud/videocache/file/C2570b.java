package com.baidu.cloud.videocache.file;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.videocache.file.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2570b extends ass {

    /* renamed from: a */
    private final long f4878a;

    public C2570b(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.f4878a = j;
    }

    @Override // com.baidu.cloud.videocache.file.ass
    /* renamed from: a */
    protected boolean mo19816a(File file, long j, int i) {
        return j <= this.f4878a;
    }
}

package com.baidu.cloud.videocache.file;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.videocache.file.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2569a extends ass {

    /* renamed from: a */
    private final int f4873a;

    public C2569a(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Max count must be positive number!");
        }
        this.f4873a = i;
    }

    @Override // com.baidu.cloud.videocache.file.ass
    /* renamed from: a */
    protected boolean mo19816a(File file, long j, int i) {
        return i <= this.f4873a;
    }
}

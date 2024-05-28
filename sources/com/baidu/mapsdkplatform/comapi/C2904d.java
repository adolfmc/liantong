package com.baidu.mapsdkplatform.comapi;

import java.io.File;
import java.io.FilenameFilter;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: NativeLoader.java */
/* renamed from: com.baidu.mapsdkplatform.comapi.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2904d implements FilenameFilter {

    /* renamed from: a */
    final /* synthetic */ String f7178a;

    /* renamed from: b */
    final /* synthetic */ NativeLoader f7179b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2904d(NativeLoader nativeLoader, String str) {
        this.f7179b = nativeLoader;
        this.f7178a = str;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return (str == null || !str.contains("libBaiduMapSDK_") || str.contains(this.f7178a)) ? false : true;
    }
}

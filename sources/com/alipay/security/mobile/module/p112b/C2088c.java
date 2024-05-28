package com.alipay.security.mobile.module.p112b;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.b.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2088c implements FileFilter {

    /* renamed from: a */
    final /* synthetic */ C2087b f3990a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2088c(C2087b c2087b) {
        this.f3990a = c2087b;
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }
}

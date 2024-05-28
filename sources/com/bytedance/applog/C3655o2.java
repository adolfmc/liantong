package com.bytedance.applog;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* renamed from: com.bytedance.applog.o2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3655o2 implements FileFilter {
    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return Pattern.matches("cpu[0-9]", file.getName());
    }
}

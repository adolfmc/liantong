package com.baidu.mapsdkplatform.comapi.p142b.p143a;

import com.baidu.mapsdkplatform.comapi.util.StorageSettings;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import java.io.File;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: NativeCrashUtil.java */
/* renamed from: com.baidu.mapsdkplatform.comapi.b.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2887d implements Runnable {

    /* renamed from: a */
    final /* synthetic */ NativeCrashUtil f7132a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2887d(NativeCrashUtil nativeCrashUtil) {
        this.f7132a = nativeCrashUtil;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        File[] listFiles;
        String str2;
        if (StorageSettings.m18149a().m18145b() == null) {
            return;
        }
        str = NativeCrashUtil.f7127a;
        File file = new File(str);
        if (!file.exists() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        try {
            Arrays.sort(listFiles, new SortClassByTime());
        } catch (Exception unused) {
        }
        int length = listFiles.length;
        if (length > 10) {
            length = 10;
        }
        for (int i = 0; i < length; i++) {
            File file2 = listFiles[i];
            if (!file2.isDirectory() && file2.exists() && file2.isFile()) {
                String name = file2.getName();
                str2 = NativeCrashUtil.f7128b;
                if (name.contains(str2)) {
                    if (file2.getName().endsWith(".txt")) {
                        this.f7132a.m18492a(file2);
                    } else if (file2.getName().endsWith(JtClient.UXUE_TEMP_FILE_SUFFIX) && file2.exists()) {
                        this.f7132a.m18492a(file2);
                    }
                }
            }
        }
        if (listFiles.length > 10) {
            this.f7132a.m18488a(listFiles);
        }
    }
}

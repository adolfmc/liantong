package com.chinaunicon.jtwifilib.core.crashreport;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileUtils {
    private static final String TAG = "com.chinaunicon.jtwifilib.core.crashreport.FileUtils";

    public static Collection<File> listFiles(String str) {
        return listFiles(str, null, false);
    }

    public static Collection<File> listFiles(String str, boolean z) {
        return listFiles(str, null, z);
    }

    public static Collection<File> listFiles(String str, String[] strArr) {
        return listFiles(str, strArr, false);
    }

    public static Collection<File> listFiles(String str, String[] strArr, boolean z) {
        if (str == null || TextUtils.isEmpty(str)) {
            Log.e(TAG, "路径不能为空");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        if (file.isFile()) {
            arrayList.add(file);
        } else {
            if (strArr != null) {
                for (String str2 : strArr) {
                    File[] listFiles = file.listFiles(new FileFlitter(str2));
                    if (listFiles != null) {
                        for (File file2 : listFiles) {
                            arrayList.add(file2);
                        }
                    }
                }
            } else {
                for (File file3 : file.listFiles()) {
                    arrayList.add(file3);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class FileFlitter implements FilenameFilter {
        private String type;

        public FileFlitter(String str) {
            this.type = str;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(this.type);
        }
    }
}

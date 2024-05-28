package com.sinovatech.unicom.separatemodule.cropimg;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class IOUtils {
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean deleteFile(String str) {
        File file = new File(str);
        return file.exists() && file.isFile() && file.delete();
    }
}

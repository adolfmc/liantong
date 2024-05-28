package cn.ltzf.passguard;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LTPassGuardDriver {
    private static boolean first = false;
    private static final String strLibName = "PassGuard";

    public static void loadLibrary(Context context) {
        if (first) {
            return;
        }
        try {
            InputStream open = context.getResources().getAssets().open("PassGuard");
            File file = new File(context.getCacheDir() + "/PassGuard");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read == -1) {
                    open.close();
                    fileOutputStream.close();
                    System.load(file.getAbsolutePath());
                    first = true;
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

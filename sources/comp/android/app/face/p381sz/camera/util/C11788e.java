package comp.android.app.face.p381sz.camera.util;

import android.os.Environment;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: comp.android.app.face.sz.camera.util.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11788e {

    /* renamed from: a */
    private static final File f24011a = Environment.getExternalStorageDirectory();

    /* renamed from: b */
    private static String f24012b = "";

    /* renamed from: c */
    private static String f24013c = "JCamera";

    /* renamed from: a */
    public static boolean m2138a(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}

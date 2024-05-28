package com.gmrz.authenticationso.utils;

import android.content.Context;
import com.gmrz.android.client.utils.Logger;
import com.gmrz.authKernel.C4434R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileHelper {
    private static final String FILE_NAME = "wrapkeyFile";
    private static final String FOLDER_NAME = "fido_ksfile";
    private static final String TAG = "FileHelper";

    public static String createDirectoryForKsTA(Context context) {
        File dir = context.getDir("fido_ksfile", 0);
        String absolutePath = dir.getAbsolutePath();
        Logger.m15883w("FileHelper", "ks cert local storage file path: " + absolutePath);
        for (String str : dir.list()) {
            if (str.contains("wrapkeyFile")) {
                Logger.m15883w("FileHelper", "wrapKeyFile is already exits");
                return absolutePath;
            }
        }
        if (copyWrapKeyFile(context) == 0) {
            Logger.m15883w("FileHelper", "wrapKeyFile copy to local storage success");
        }
        return absolutePath;
    }

    private static int copyWrapKeyFile(Context context) {
        try {
            InputStream openRawResource = context.getResources().openRawResource(C4434R.C4435raw.warpkey);
            copy(openRawResource, new File(context.getDir("fido_ksfile", 0).getAbsoluteFile() + "/wrapkeyFile"));
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static void copy(InputStream inputStream, File file) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    return;
                }
            }
        } finally {
            inputStream.close();
        }
    }
}

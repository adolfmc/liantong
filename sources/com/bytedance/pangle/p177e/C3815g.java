package com.bytedance.pangle.p177e;

import android.content.SharedPreferences;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p176d.C3792c;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.e.g */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3815g {
    /* renamed from: a */
    public static String m16887a(String str, int i) {
        int m16885b = m16885b(str, i);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 1; i2 <= m16885b; i2++) {
            sb.append(new C3816a(new File(C3792c.m16925i(str, i)), i2 == 1 ? "classes.dex" : "classes" + i2 + ".dex").getAbsolutePath());
            sb.append(":");
        }
        if (sb.length() != 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static void m16886a(ZipFile zipFile, ZipEntry zipEntry, C3816a c3816a, String str) {
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        File createTempFile = File.createTempFile("tmp-".concat(String.valueOf(str)), ".dex", c3816a.getParentFile());
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(createTempFile));
            byte[] bArr = new byte[1048576];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            if (!createTempFile.setReadOnly()) {
                throw new IOException("Failed to mark readonly \"" + createTempFile.getAbsolutePath() + "\" (tmp of \"" + c3816a.getAbsolutePath() + "\")");
            } else if (createTempFile.renameTo(c3816a)) {
            } else {
                throw new IOException("Failed to rename \"" + createTempFile.getAbsolutePath() + "\" to \"" + c3816a.getAbsolutePath() + "\"");
            }
        } finally {
            m16890a(inputStream);
            createTempFile.delete();
        }
    }

    /* renamed from: a */
    private static void m16890a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            ZeusLogger.m16787w("Zeus/install_pangle", "Plugin-MultiDex Failed to close resource", e);
        }
    }

    /* renamed from: a */
    public static void m16889a(File file) {
        File[] listFiles;
        if (!file.exists() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        for (File file2 : listFiles) {
            ZeusLogger.m16792i("Zeus/install_pangle", "Plugin-MultiDex Trying to delete old file " + file2.getPath() + " of size " + file2.length());
            if (!file2.delete()) {
                ZeusLogger.m16788w("Zeus/install_pangle", "Plugin-MultiDex Failed to delete old file " + file2.getPath());
            }
        }
    }

    /* renamed from: a */
    public static SharedPreferences m16891a() {
        return Zeus.getAppApplication().getSharedPreferences("plugin-multidex.version", 0);
    }

    /* renamed from: b */
    private static int m16885b(String str, int i) {
        return m16888a((str + "-" + i) + ".dex.number");
    }

    /* renamed from: a */
    private static int m16888a(String str) {
        return m16891a().getInt(str, 0);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.e.g$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3816a extends File {
        public C3816a(File file, String str) {
            super(file, str);
        }
    }
}

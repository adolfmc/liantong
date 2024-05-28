package com.chinaunicon.jtwifilib.core.utils;

import androidx.annotation.RequiresApi;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ZipFileUtil {
    @RequiresApi(api = 24)
    public static int upZipFile(String str, String str2) throws Exception {
        ZipFile zipFile = new ZipFile(new File(str), Charset.forName(EncodeUtil.getEncode(str, true)));
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        byte[] bArr = new byte[1024];
        while (entries.hasMoreElements()) {
            ZipEntry nextElement = entries.nextElement();
            if (nextElement.isDirectory()) {
                new File(new String((str2 + nextElement.getName()).getBytes("8859_1"), "GB2312")).mkdir();
            } else {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(getRealFileName(str2, nextElement.getName())));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(nextElement));
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                bufferedInputStream.close();
                bufferedOutputStream.close();
            }
        }
        zipFile.close();
        return 0;
    }

    public static File getRealFileName(String str, String str2) {
        String[] split = str2.split("/");
        if (split.length > 1) {
            for (int i = 0; i < split.length - 1; i++) {
                str = str + split[i] + "/";
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            return new File(str, split[split.length - 1]);
        }
        return new File(str, str2);
    }
}

package com.chinaunicon.jtwifilib.jtcommon.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JtStreamTool {
    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] readStream(InputStream inputStream) throws IOException {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr2);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr2, 0, read);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray.length >= 4) {
            bArr = new byte[byteArray.length - 4];
            System.arraycopy(byteArray, 4, bArr, 0, bArr.length);
        } else {
            bArr = new byte[0];
        }
        inputStream.close();
        byteArrayOutputStream.close();
        return bArr;
    }
}

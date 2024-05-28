package com.huawei.hms.framework.network.grs.p220h;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.h.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4953c {

    /* renamed from: a */
    private static final String f11325a = "c";

    /* renamed from: a */
    public static String m14857a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                if (!new File(str).isDirectory()) {
                    inputStream = context.getAssets().open(str);
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            byteArrayOutputStream.flush();
                            return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                }
            } catch (IOException unused) {
                Logger.m15043w(f11325a, "local config file is not exist.filename is {%s}", str);
            }
            return "";
        } finally {
            IoUtils.closeSecure((OutputStream) byteArrayOutputStream);
            IoUtils.closeSecure(inputStream);
        }
    }
}

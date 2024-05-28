package cn.finalteam.toolsfinal.p093io;

import android.os.Build;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.finalteam.toolsfinal.io.StringCodingUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StringCodingUtils {
    public static byte[] getBytes(String str, Charset charset) {
        if (Build.VERSION.SDK_INT < 9) {
            try {
                return str.getBytes(charset.name());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return str.getBytes(charset);
    }
}

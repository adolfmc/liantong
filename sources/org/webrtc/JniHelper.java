package org.webrtc;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class JniHelper {
    JniHelper() {
    }

    @CalledByNative
    static byte[] getStringBytes(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("ISO-8859-1 is unsupported");
        }
    }

    @CalledByNative
    static Object getStringClass() {
        return String.class;
    }

    @CalledByNative
    static Object getKey(Map.Entry entry) {
        return entry.getKey();
    }

    @CalledByNative
    static Object getValue(Map.Entry entry) {
        return entry.getValue();
    }
}

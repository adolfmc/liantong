package com.xuhao.didi.socket.common.interfaces.utils;

import com.xuhao.didi.core.utils.SLog;
import java.util.Iterator;
import java.util.ServiceLoader;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SPIUtils {
    public static <E> E load(Class<E> cls) {
        if (cls == null) {
            SLog.m2258e("load null clz error!");
            return null;
        }
        Iterator it = ServiceLoader.load(cls, cls.getClassLoader()).iterator();
        try {
            if (it.hasNext()) {
                return (E) it.next();
            }
        } catch (Throwable th) {
            SLog.m2258e("load " + cls.getSimpleName() + " error! " + th.getMessage());
        }
        return null;
    }
}

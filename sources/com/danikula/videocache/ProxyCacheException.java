package com.danikula.videocache;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ProxyCacheException extends Exception {
    private static final String LIBRARY_VERSION = ". Version: 2.7.1";

    public ProxyCacheException(String str) {
        super(str + ". Version: 2.7.1");
    }

    public ProxyCacheException(String str, Throwable th) {
        super(str + ". Version: 2.7.1", th);
    }

    public ProxyCacheException(Throwable th) {
        super("No explanation error. Version: 2.7.1", th);
    }
}

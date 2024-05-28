package com.froad.libloadso;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FFTeIDJni {
    private Context mContext;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class SingletonHolder {
        private static final FFTeIDJni INSTANCE = new FFTeIDJni();

        private SingletonHolder() {
        }
    }

    public static FFTeIDJni getJNI() {
        return SingletonHolder.INSTANCE;
    }

    public native byte[] getEncKey(byte[] bArr);

    public native String getSM3Hex(byte[] bArr, boolean z);

    public FFTeIDJni setContext(Context context) {
        this.mContext = context;
        return this;
    }
}

package com.davemorrissey.labs.subscaleview.decoder;

import android.support.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DecoderFactory<T> {
    @NonNull
    T make() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}

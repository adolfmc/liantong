package org.bouncycastle.jcajce.util;

import java.security.PrivateKey;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PrivateKeyAnnotator {
    public static AnnotatedPrivateKey annotate(PrivateKey privateKey, String str) {
        return new AnnotatedPrivateKey(privateKey, str);
    }

    public static AnnotatedPrivateKey annotate(PrivateKey privateKey, Map<String, Object> map) {
        return new AnnotatedPrivateKey(privateKey, Collections.unmodifiableMap(new HashMap(map)));
    }
}

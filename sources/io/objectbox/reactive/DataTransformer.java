package io.objectbox.reactive;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface DataTransformer<FROM, TO> {
    TO transform(FROM from) throws Exception;
}

package io.reactivex.internal.fuseable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface QueueFuseable<T> extends SimpleQueue<T> {
    public static final int ANY = 3;
    public static final int ASYNC = 2;
    public static final int BOUNDARY = 4;
    public static final int NONE = 0;
    public static final int SYNC = 1;

    int requestFusion(int i);
}

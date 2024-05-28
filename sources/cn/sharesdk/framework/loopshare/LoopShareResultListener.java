package cn.sharesdk.framework.loopshare;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface LoopShareResultListener<T> {
    void onError(Throwable th);

    void onResult(T t);
}

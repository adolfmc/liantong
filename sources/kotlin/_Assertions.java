package kotlin;

import kotlin.jvm.JvmPlatformAnnotations;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: AssertionsJVM.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, m1890d2 = {"Lkotlin/_Assertions;", "", "()V", "ENABLED", "", "ENABLED$annotations", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
@PublishedApi
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class _Assertions {
    @JvmPlatformAnnotations
    public static final boolean ENABLED;
    public static final _Assertions INSTANCE;

    @PublishedApi
    public static /* synthetic */ void ENABLED$annotations() {
    }

    static {
        _Assertions _assertions = new _Assertions();
        INSTANCE = _assertions;
        ENABLED = _assertions.getClass().desiredAssertionStatus();
    }

    private _Assertions() {
    }
}

package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Functions;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b¨\u0006\u0007"}, m1890d2 = {"assert", "", "value", "", "lazyMessage", "Lkotlin/Function0;", "", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/PreconditionsKt")
/* renamed from: kotlin.PreconditionsKt__AssertionsJVMKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
class AssertionsJVM {
    @InlineOnly
    /* renamed from: assert  reason: not valid java name */
    private static final void m24486assert(boolean z) {
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Assertion failed");
        }
    }

    @InlineOnly
    /* renamed from: assert  reason: not valid java name */
    private static final void m24487assert(boolean z, Functions<? extends Object> functions) {
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(functions.invoke());
        }
    }
}

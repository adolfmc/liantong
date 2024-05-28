package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [S] */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: _Sequences.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m1890d2 = {"<anonymous>", "", "S", "T", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m1889k = 3, m1888mv = {1, 1, 16})
@DebugMetadata(m1882c = "kotlin.sequences.SequencesKt___SequencesKt$scanReduce$1", m1881f = "_Sequences.kt", m1880i = {0, 0, 0, 1, 1, 1}, m1879l = {1492, 1495}, m1878m = "invokeSuspend", m1877n = {"$this$sequence", "iterator", "accumulator", "$this$sequence", "iterator", "accumulator"}, m1876s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* loaded from: E:\11617560_dexfile_execute.dex */
final class SequencesKt___SequencesKt$scanReduce$1<S> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $operation;
    final /* synthetic */ Sequence $this_scanReduce;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private SequenceScope f24907p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$scanReduce$1(Sequence sequence, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_scanReduce = sequence;
        this.$operation = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SequencesKt___SequencesKt$scanReduce$1 sequencesKt___SequencesKt$scanReduce$1 = new SequencesKt___SequencesKt$scanReduce$1(this.$this_scanReduce, this.$operation, completion);
        sequencesKt___SequencesKt$scanReduce$1.f24907p$ = (SequenceScope) obj;
        return sequencesKt___SequencesKt$scanReduce$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$scanReduce$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L2e;
                case 1: goto L20;
                case 2: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L11:
            java.lang.Object r1 = r6.L$2
            java.lang.Object r2 = r6.L$1
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r6.L$0
            kotlin.sequences.SequenceScope r3 = (kotlin.sequences.SequenceScope) r3
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r6
            goto L54
        L20:
            java.lang.Object r1 = r6.L$2
            java.lang.Object r2 = r6.L$1
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r6.L$0
            kotlin.sequences.SequenceScope r3 = (kotlin.sequences.SequenceScope) r3
            kotlin.ResultKt.throwOnFailure(r7)
            goto L53
        L2e:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.sequences.SequenceScope r3 = r6.f24907p$
            kotlin.sequences.Sequence r7 = r6.$this_scanReduce
            java.util.Iterator r2 = r7.iterator()
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L74
            java.lang.Object r1 = r2.next()
            r6.L$0 = r3
            r6.L$1 = r2
            r6.L$2 = r1
            r7 = 1
            r6.label = r7
            java.lang.Object r7 = r3.yield(r1, r6)
            if (r7 != r0) goto L53
            return r0
        L53:
            r7 = r6
        L54:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L74
            kotlin.jvm.functions.Function2 r4 = r7.$operation
            java.lang.Object r5 = r2.next()
            java.lang.Object r1 = r4.invoke(r1, r5)
            r7.L$0 = r3
            r7.L$1 = r2
            r7.L$2 = r1
            r4 = 2
            r7.label = r4
            java.lang.Object r4 = r3.yield(r1, r7)
            if (r4 != r0) goto L54
            return r0
        L74:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$scanReduce$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

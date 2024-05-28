package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [S] */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: _Sequences.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m1890d2 = {"<anonymous>", "", "S", "T", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m1889k = 3, m1888mv = {1, 1, 16})
@DebugMetadata(m1882c = "kotlin.sequences.SequencesKt___SequencesKt$scanReduceIndexed$1", m1881f = "_Sequences.kt", m1880i = {0, 0, 0, 1, 1, 1, 1}, m1879l = {1522, 1526}, m1878m = "invokeSuspend", m1877n = {"$this$sequence", "iterator", "accumulator", "$this$sequence", "iterator", "accumulator", "index"}, m1876s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "I$0"})
/* loaded from: E:\11617560_dexfile_execute.dex */
final class SequencesKt___SequencesKt$scanReduceIndexed$1<S> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3 $operation;
    final /* synthetic */ Sequence $this_scanReduceIndexed;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private SequenceScope f24908p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$scanReduceIndexed$1(Sequence sequence, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_scanReduceIndexed = sequence;
        this.$operation = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SequencesKt___SequencesKt$scanReduceIndexed$1 sequencesKt___SequencesKt$scanReduceIndexed$1 = new SequencesKt___SequencesKt$scanReduceIndexed$1(this.$this_scanReduceIndexed, this.$operation, completion);
        sequencesKt___SequencesKt$scanReduceIndexed$1.f24908p$ = (SequenceScope) obj;
        return sequencesKt___SequencesKt$scanReduceIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$scanReduceIndexed$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 1
            switch(r1) {
                case 0: goto L34;
                case 1: goto L26;
                case 2: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L12:
            int r1 = r9.I$0
            java.lang.Object r2 = r9.L$2
            java.lang.Object r3 = r9.L$1
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r9.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r9
            r8 = r2
            r2 = r1
            r1 = r8
            goto L59
        L26:
            java.lang.Object r1 = r9.L$2
            java.lang.Object r3 = r9.L$1
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r9.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L58
        L34:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlin.sequences.SequenceScope r4 = r9.f24908p$
            kotlin.sequences.Sequence r10 = r9.$this_scanReduceIndexed
            java.util.Iterator r3 = r10.iterator()
            boolean r10 = r3.hasNext()
            if (r10 == 0) goto L89
            java.lang.Object r1 = r3.next()
            r9.L$0 = r4
            r9.L$1 = r3
            r9.L$2 = r1
            r9.label = r2
            java.lang.Object r10 = r4.yield(r1, r9)
            if (r10 != r0) goto L58
            return r0
        L58:
            r10 = r9
        L59:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L89
            kotlin.jvm.functions.Function3 r5 = r10.$operation
            int r6 = r2 + 1
            if (r2 >= 0) goto L68
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L68:
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.boxing.boxInt(r2)
            java.lang.Object r7 = r3.next()
            java.lang.Object r2 = r5.invoke(r2, r1, r7)
            r10.L$0 = r4
            r10.L$1 = r3
            r10.L$2 = r2
            r10.I$0 = r6
            r1 = 2
            r10.label = r1
            java.lang.Object r1 = r4.yield(r2, r10)
            if (r1 != r0) goto L86
            return r0
        L86:
            r1 = r2
            r2 = r6
            goto L59
        L89:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$scanReduceIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

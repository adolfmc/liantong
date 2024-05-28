package com.bumptech.glide;

import android.support.annotation.NonNull;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class GenericTransitionOptions<TranscodeType> extends TransitionOptions<GenericTransitionOptions<TranscodeType>, TranscodeType> {
    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> withNoTransition() {
        return new GenericTransitionOptions().dontTransition();
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(int i) {
        return new GenericTransitionOptions().transition(i);
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(@NonNull ViewPropertyTransition.Animator animator) {
        return new GenericTransitionOptions().transition(animator);
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(@NonNull TransitionFactory<? super TranscodeType> transitionFactory) {
        return new GenericTransitionOptions().transition(transitionFactory);
    }
}

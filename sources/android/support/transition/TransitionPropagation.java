package android.support.transition;

import android.view.ViewGroup;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class TransitionPropagation {
    public abstract void captureValues(TransitionValues transitionValues);

    public abstract String[] getPropagationProperties();

    public abstract long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2);
}

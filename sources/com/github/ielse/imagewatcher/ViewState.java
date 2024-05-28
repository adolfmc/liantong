package com.github.ielse.imagewatcher;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ViewState {
    float alpha;
    int height;
    int mTag;
    float scaleX;
    float scaleY;
    float translationX;
    float translationY;
    int width;
    static final int STATE_ORIGIN = C4376R.C4379id.state_origin;
    static final int STATE_THUMB = C4376R.C4379id.state_thumb;
    static final int STATE_DEFAULT = C4376R.C4379id.state_default;
    static final int STATE_CURRENT = C4376R.C4379id.state_current;
    static final int STATE_TEMP = C4376R.C4379id.state_temp;
    static final int STATE_DRAG = C4376R.C4379id.state_touch_drag;
    static final int STATE_EXIT = C4376R.C4379id.state_exit;
    static final int STATE_TOUCH_SCALE = C4376R.C4379id.state_touch_scale;

    private ViewState(int i) {
        this.mTag = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewState write(View view, int i) {
        if (view == null) {
            return null;
        }
        ViewState read = read(view, i);
        if (read == null) {
            read = new ViewState(i);
            view.setTag(i, read);
        }
        read.width = view.getWidth();
        read.height = view.getHeight();
        read.translationX = view.getTranslationX();
        read.translationY = view.getTranslationY();
        read.scaleX = view.getScaleX();
        read.scaleY = view.getScaleY();
        read.alpha = view.getAlpha();
        return read;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewState read(View view, int i) {
        if (view == null || view.getTag(i) == null) {
            return null;
        }
        return (ViewState) view.getTag(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clear(View view, int i) {
        if (view == null) {
            return;
        }
        view.setTag(i, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewState copy(ViewState viewState, int i) {
        ViewState viewState2 = new ViewState(i);
        viewState2.width = viewState.width;
        viewState2.height = viewState.height;
        viewState2.translationX = viewState.translationX;
        viewState2.translationY = viewState.translationY;
        viewState2.scaleX = viewState.scaleX;
        viewState2.scaleY = viewState.scaleY;
        viewState2.alpha = viewState.alpha;
        return viewState2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void restore(View view, int i) {
        ViewState read = read(view, i);
        if (read != null) {
            view.setTranslationX(read.translationX);
            view.setTranslationY(read.translationY);
            view.setScaleX(read.scaleX);
            view.setScaleY(read.scaleY);
            view.setAlpha(read.alpha);
            if (view.getLayoutParams().width == read.width && view.getLayoutParams().height == read.height) {
                return;
            }
            view.getLayoutParams().width = read.width;
            view.getLayoutParams().height = read.height;
            view.requestLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ValueAnimatorBuilder restoreByAnim(final View view, int i) {
        ValueAnimator valueAnimator;
        ViewState read;
        if (view != null) {
            final ViewState write = write(view, STATE_CURRENT);
            if (write.width == 0 && write.height == 0 && (read = read(view, STATE_ORIGIN)) != null) {
                write.width(read.width).height(read.height);
            }
            final ViewState read2 = read(view, i);
            if (read2 != null) {
                valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(200L);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.ielse.imagewatcher.ViewState.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        float floatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                        view.setTranslationX(write.translationX + ((read2.translationX - write.translationX) * floatValue));
                        view.setTranslationY(write.translationY + ((read2.translationY - write.translationY) * floatValue));
                        view.setScaleX(write.scaleX + ((read2.scaleX - write.scaleX) * floatValue));
                        view.setScaleY(write.scaleY + ((read2.scaleY - write.scaleY) * floatValue));
                        view.setAlpha(write.alpha + ((read2.alpha - write.alpha) * floatValue));
                        if (write.width == read2.width || write.height == read2.height || read2.width == 0 || read2.height == 0) {
                            return;
                        }
                        view.getLayoutParams().width = (int) (write.width + ((read2.width - write.width) * floatValue));
                        view.getLayoutParams().height = (int) (write.height + ((read2.height - write.height) * floatValue));
                        view.requestLayout();
                    }
                });
                return new ValueAnimatorBuilder(valueAnimator);
            }
        }
        valueAnimator = null;
        return new ValueAnimatorBuilder(valueAnimator);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class ValueAnimatorBuilder {
        ValueAnimator mAnimator;

        ValueAnimatorBuilder(ValueAnimator valueAnimator) {
            this.mAnimator = valueAnimator;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ValueAnimatorBuilder addListener(Animator.AnimatorListener animatorListener) {
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null) {
                valueAnimator.addListener(animatorListener);
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ValueAnimator create() {
            return this.mAnimator;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewState scaleX(float f) {
        this.scaleX = f;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewState scaleXBy(float f) {
        this.scaleX *= f;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewState scaleY(float f) {
        this.scaleY = f;
        return this;
    }

    ViewState scaleYBy(float f) {
        this.scaleY *= f;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewState width(int i) {
        this.width = i;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewState height(int i) {
        this.height = i;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewState translationX(float f) {
        this.translationX = f;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewState translationY(float f) {
        this.translationY = f;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewState alpha(float f) {
        this.alpha = f;
        return this;
    }
}

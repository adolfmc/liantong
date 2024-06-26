package androidx.navigation;

import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class NavOptions {
    @AnimRes
    @AnimatorRes
    private int mEnterAnim;
    @AnimRes
    @AnimatorRes
    private int mExitAnim;
    @AnimRes
    @AnimatorRes
    private int mPopEnterAnim;
    @AnimRes
    @AnimatorRes
    private int mPopExitAnim;
    @IdRes
    private int mPopUpTo;
    private boolean mPopUpToInclusive;
    private boolean mSingleTop;

    NavOptions(boolean z, @IdRes int i, boolean z2, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        this.mSingleTop = z;
        this.mPopUpTo = i;
        this.mPopUpToInclusive = z2;
        this.mEnterAnim = i2;
        this.mExitAnim = i3;
        this.mPopEnterAnim = i4;
        this.mPopExitAnim = i5;
    }

    public boolean shouldLaunchSingleTop() {
        return this.mSingleTop;
    }

    @IdRes
    public int getPopUpTo() {
        return this.mPopUpTo;
    }

    public boolean isPopUpToInclusive() {
        return this.mPopUpToInclusive;
    }

    @AnimRes
    @AnimatorRes
    public int getEnterAnim() {
        return this.mEnterAnim;
    }

    @AnimRes
    @AnimatorRes
    public int getExitAnim() {
        return this.mExitAnim;
    }

    @AnimRes
    @AnimatorRes
    public int getPopEnterAnim() {
        return this.mPopEnterAnim;
    }

    @AnimRes
    @AnimatorRes
    public int getPopExitAnim() {
        return this.mPopExitAnim;
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static final class Builder {
        boolean mPopUpToInclusive;
        boolean mSingleTop;
        @IdRes
        int mPopUpTo = -1;
        @AnimRes
        @AnimatorRes
        int mEnterAnim = -1;
        @AnimRes
        @AnimatorRes
        int mExitAnim = -1;
        @AnimRes
        @AnimatorRes
        int mPopEnterAnim = -1;
        @AnimRes
        @AnimatorRes
        int mPopExitAnim = -1;

        @NonNull
        public Builder setLaunchSingleTop(boolean z) {
            this.mSingleTop = z;
            return this;
        }

        @NonNull
        public Builder setPopUpTo(@IdRes int i, boolean z) {
            this.mPopUpTo = i;
            this.mPopUpToInclusive = z;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: IPUT  (r1v0 ?? I:int), (r0 I:androidx.navigation.NavOptions$Builder) androidx.navigation.NavOptions.Builder.mEnterAnim int, block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavOptions, androidx.navigation.NavOptions$Builder] */
        @NonNull
        public NavOptions build() {
            ?? r0;
            r0.mEnterAnim = this;
            return r0;
        }

        @NonNull
        public Builder setEnterAnim(@AnimRes @AnimatorRes int i) {
            this.mEnterAnim = i;
            return this;
        }

        @NonNull
        public Builder setExitAnim(@AnimRes @AnimatorRes int i) {
            this.mExitAnim = i;
            return this;
        }

        @NonNull
        public Builder setPopEnterAnim(@AnimRes @AnimatorRes int i) {
            this.mPopEnterAnim = i;
            return this;
        }

        @NonNull
        public Builder setPopExitAnim(@AnimRes @AnimatorRes int i) {
            this.mPopExitAnim = i;
            return this;
        }
    }
}

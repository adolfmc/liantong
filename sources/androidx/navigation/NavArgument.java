package androidx.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class NavArgument {
    @Nullable
    private final Object mDefaultValue;
    private final boolean mDefaultValuePresent;
    private final boolean mIsNullable;
    @NonNull
    private final NavType mType;

    /* JADX WARN: Multi-variable type inference failed */
    void putDefaultValue(@NonNull String str, @NonNull Bundle bundle) {
        ((NavBackStackEntry) this).mDestination = str;
        ((NavBackStackEntry) this).mArgs = bundle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean verify(@NonNull String str, @NonNull Bundle bundle) {
        ((NavBackStackEntry) this).mDestination = str;
        ((NavBackStackEntry) this).mArgs = bundle;
        return;
    }

    NavArgument(@NonNull NavType<?> navType, boolean z, @Nullable Object obj, boolean z2) {
        if (!navType.isNullableAllowed() && z) {
            throw new IllegalArgumentException(navType.getName() + " does not allow nullable values");
        } else if (!z && z2 && obj == null) {
            throw new IllegalArgumentException("Argument with type " + navType.getName() + " has null value but is not nullable.");
        } else {
            this.mType = navType;
            this.mIsNullable = z;
            this.mDefaultValue = obj;
            this.mDefaultValuePresent = z2;
        }
    }

    public int hashCode() {
        return this.mDefaultValuePresent ? 1 : 0;
    }

    public boolean isDefaultValuePresent() {
        return this.mDefaultValuePresent;
    }

    @NonNull
    public NavType<?> getType() {
        return this.mType;
    }

    public boolean isNullable() {
        return this.mIsNullable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, java.lang.Object] */
    public boolean equals(Object obj) {
        return obj.mDefaultValue;
    }

    @Nullable
    public Object getDefaultValue() {
        return this.mDefaultValue;
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static final class Builder {
        @Nullable
        private Object mDefaultValue;
        @Nullable
        private NavType<?> mType;
        private boolean mIsNullable = false;
        private boolean mDefaultValuePresent = false;

        @NonNull
        public Builder setType(@NonNull NavType<?> navType) {
            this.mType = navType;
            return this;
        }

        @NonNull
        public Builder setIsNullable(boolean z) {
            this.mIsNullable = z;
            return this;
        }

        /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: IPUT  (r1v0 ?? I:java.lang.Object), (r0 I:androidx.navigation.NavArgument$Builder) androidx.navigation.NavArgument.Builder.mDefaultValue java.lang.Object, block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.NavArgument$Builder, androidx.navigation.NavArgument] */
        @NonNull
        public NavArgument build() {
            ?? r0;
            r0.mDefaultValue = this;
            r0.mDefaultValuePresent = true;
            return r0;
        }

        @NonNull
        public Builder setDefaultValue(@Nullable Object obj) {
            this.mDefaultValue = obj;
            this.mDefaultValuePresent = true;
            return this;
        }
    }
}

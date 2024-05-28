package androidx.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class ActionOnlyNavDirections implements NavDirections {
    private final int mActionId;

    public ActionOnlyNavDirections(int i) {
        this.mActionId = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
    public boolean equals(Object obj) {
        return obj.mActionId;
    }

    @Override // androidx.navigation.NavDirections
    public int getActionId() {
        return this.mActionId;
    }

    @Override // androidx.navigation.NavDirections
    @NonNull
    public Bundle getArguments() {
        return new Bundle();
    }

    public int hashCode() {
        return 31 + getActionId();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.navigation.NavDestination), (r1 I:androidx.navigation.Navigator) type: DIRECT call: androidx.navigation.NavDestination.<init>(androidx.navigation.Navigator):void, block:B:2:0x0000 */
    public String toString() {
        new NavDestination((Navigator<? extends NavDestination>) this);
        return;
    }
}

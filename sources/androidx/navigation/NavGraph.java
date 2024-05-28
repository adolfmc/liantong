package androidx.navigation;

import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.util.SparseArrayCompat;
import androidx.navigation.NavDestination;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class NavGraph extends NavDestination implements Iterable<NavDestination> {
    final SparseArrayCompat<NavDestination> mNodes;
    private int mStartDestId;
    private String mStartDestIdName;

    public NavGraph(@NonNull Navigator<? extends NavGraph> navigator) {
        super(navigator);
        this.mNodes = new SparseArrayCompat<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.navigation.NavDestination, void] */
    public final void addAll(@NonNull NavGraph navGraph) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.navigation.NavDestination, void] */
    public final void addDestination(@NonNull NavDestination navDestination) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.navigation.NavDestination, void] */
    public final void addDestinations(@NonNull Collection<NavDestination> collection) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.navigation.NavDestination, void] */
    public final void addDestinations(@NonNull NavDestination... navDestinationArr) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0001: INVOKE  (r2 I:androidx.navigation.NavDestination) = (r1 I:androidx.navigation.NavGraph), (r2 I:int), (r0 I:boolean) type: VIRTUAL call: androidx.navigation.NavGraph.findNode(int, boolean):androidx.navigation.NavDestination, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.navigation.NavGraph] */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.navigation.NavDestination, void] */
    public final void clear() {
    }

    @Nullable
    public final NavDestination findNode(@IdRes int i) {
        return findNode(i, true);
    }

    @Override // java.lang.Iterable
    @NonNull
    public final Iterator<NavDestination> iterator() {
        return new Iterator<NavDestination>() { // from class: androidx.navigation.NavGraph.1
            private int mIndex = -1;
            private boolean mWentToNext = false;

            @Override // java.util.Iterator
            public void remove() {
                NavInflater.sTmpValue = new ThreadLocal<>();
            }

            /* JADX WARN: Type inference failed for: r0v0, types: [boolean, androidx.navigation.NavDestination] */
            @Override // java.util.Iterator
            public boolean hasNext() {
                return next();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public NavDestination next() {
                return next();
            }
        };
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @android.support.annotation.Nullable
    final androidx.navigation.NavDestination findNode(@android.support.annotation.IdRes int r0, boolean r1) {
        /*
            r-1 = this;
            int r0 = r1.mStartDestId
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavGraph.findNode(int, boolean):androidx.navigation.NavDestination");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String, int] */
    @Override // androidx.navigation.NavDestination
    @NonNull
    String getDisplayName() {
        return this.mStartDestId;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String, int] */
    @NonNull
    String getStartDestDisplayName() {
        return this.mStartDestId;
    }

    @IdRes
    public final int getStartDestination() {
        return this.mStartDestId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.navigation.NavDestination
    @Nullable
    NavDestination.DeepLinkMatch matchDeepLink(@NonNull Uri uri) {
        this.mStartDestId = uri;
        this.mStartDestIdName = null;
        return;
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.navigation.NavDestination
    public void onInflate(@android.support.annotation.NonNull android.content.Context r0, @android.support.annotation.NonNull android.util.AttributeSet r1) {
        /*
            r-1 = this;
            r0.mStartDestId = r1
            r1 = 0
            r0.mStartDestIdName = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavGraph.onInflate(android.content.Context, android.util.AttributeSet):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void remove(@NonNull NavDestination navDestination) {
        this.mStartDestId = navDestination;
        this.mStartDestIdName = null;
    }

    public final void setStartDestination(@IdRes int i) {
        this.mStartDestId = i;
        this.mStartDestIdName = null;
    }
}

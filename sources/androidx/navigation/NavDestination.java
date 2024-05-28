package androidx.navigation;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.util.SparseArrayCompat;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class NavDestination {
    private static final HashMap<String, Class> sClasses = new HashMap<>();
    private SparseArrayCompat<NavAction> mActions;
    private HashMap<String, NavArgument> mArguments;
    private ArrayList<NavDeepLink> mDeepLinks;
    private int mId;
    private String mIdName;
    private CharSequence mLabel;
    private final String mNavigatorName;
    private NavGraph mParent;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public @interface ClassType {
        Class value();
    }

    boolean supportsActions() {
        return true;
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    static class DeepLinkMatch implements Comparable<DeepLinkMatch> {
        @NonNull
        private final NavDestination mDestination;
        private final boolean mIsExactDeepLink;
        @NonNull
        private final Bundle mMatchingArgs;

        @Override // java.lang.Comparable
        public int compareTo(DeepLinkMatch deepLinkMatch) {
            return compareTo(deepLinkMatch);
        }

        DeepLinkMatch(@NonNull NavDestination navDestination, @NonNull Bundle bundle, boolean z) {
            this.mDestination = navDestination;
            this.mMatchingArgs = bundle;
            this.mIsExactDeepLink = z;
        }

        @NonNull
        NavDestination getDestination() {
            return this.mDestination;
        }

        @NonNull
        Bundle getMatchingArgs() {
            return this.mMatchingArgs;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    protected static <C> Class<? extends C> parseClassFromName(@NonNull Context context, @NonNull String str, @NonNull Class<? extends C> cls) {
        if (str.charAt(0) == '.') {
            str = context.getPackageName() + str;
        }
        Class cls2 = sClasses.get(str);
        if (cls2 == null) {
            try {
                cls2 = Class.forName(str, true, context.getClassLoader());
                sClasses.put(str, cls2);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
        if (cls.isAssignableFrom(cls2)) {
            return cls2;
        }
        throw new IllegalArgumentException(str + " must be a subclass of " + cls);
    }

    @NonNull
    static String getDisplayName(@NonNull Context context, int i) {
        try {
            return context.getResources().getResourceName(i);
        } catch (Resources.NotFoundException unused) {
            return Integer.toString(i);
        }
    }

    public NavDestination(@NonNull Navigator<? extends NavDestination> navigator) {
        this(NavigatorProvider.getNameForNavigator(navigator.getClass()));
    }

    public NavDestination(@NonNull String str) {
        this.mNavigatorName = str;
    }

    final void setParent(NavGraph navGraph) {
        this.mParent = navGraph;
    }

    @Nullable
    public final NavGraph getParent() {
        return this.mParent;
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public final void addArgument(@android.support.annotation.NonNull java.lang.String r0, @android.support.annotation.NonNull androidx.navigation.NavArgument r1) {
        /*
            r-1 = this;
            int r0 = r1.mId
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavDestination.addArgument(java.lang.String, androidx.navigation.NavArgument):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addDeepLink(@NonNull String str) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.os.Bundle, int] */
    @Nullable
    Bundle addInDefaultArgs(@Nullable Bundle bundle) {
        return bundle.mId;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [int, int[]] */
    @NonNull
    int[] buildDeepLinkIds() {
        return this.mId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.navigation.NavAction, int] */
    @Nullable
    public final NavAction getAction(@IdRes int i) {
        return i.mId;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map<java.lang.String, androidx.navigation.NavArgument>, int] */
    @NonNull
    public final Map<String, NavArgument> getArguments() {
        return this.mId;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String, int] */
    @NonNull
    String getDisplayName() {
        return this.mId;
    }

    @IdRes
    public final int getId() {
        return this.mId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    DeepLinkMatch matchDeepLink(@NonNull Uri uri) {
        this.mId = uri;
        this.mIdName = null;
        return;
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @android.support.annotation.CallSuper
    public void onInflate(@android.support.annotation.NonNull android.content.Context r0, @android.support.annotation.NonNull android.util.AttributeSet r1) {
        /*
            r-1 = this;
            r0.mId = r1
            r1 = 0
            r0.mIdName = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavDestination.onInflate(android.content.Context, android.util.AttributeSet):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public final void putAction(@android.support.annotation.IdRes int r0, @android.support.annotation.IdRes int r1) {
        /*
            r-1 = this;
            r0.mId = r1
            r1 = 0
            r0.mIdName = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavDestination.putAction(int, int):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public final void putAction(@android.support.annotation.IdRes int r0, @android.support.annotation.NonNull androidx.navigation.NavAction r1) {
        /*
            r-1 = this;
            r0.mId = r1
            r1 = 0
            r0.mIdName = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavDestination.putAction(int, androidx.navigation.NavAction):void");
    }

    public final void removeAction(@IdRes int i) {
        this.mId = i;
        this.mIdName = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void removeArgument(@NonNull String str) {
        this.mId = str;
        this.mIdName = null;
    }

    public final void setId(@IdRes int i) {
        this.mId = i;
        this.mIdName = null;
    }

    public final void setLabel(@Nullable CharSequence charSequence) {
        this.mLabel = charSequence;
    }

    @Nullable
    public final CharSequence getLabel() {
        return this.mLabel;
    }

    @NonNull
    public final String getNavigatorName() {
        return this.mNavigatorName;
    }
}

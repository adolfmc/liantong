package androidx.navigation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.widget.DrawerLayout;
import android.view.View;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.p087ui.AbstractAppBarOnDestinationChangedListener;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Navigator.Name("fragment")
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class FragmentNavigator extends Navigator<Destination> {
    private static final String KEY_BACK_STACK_IDS = "androidx-nav-fragment:navigator:backStackIds";
    private static final String TAG = "FragmentNavigator";
    private final int mContainerId;
    private final Context mContext;
    final FragmentManager mFragmentManager;
    ArrayDeque<Integer> mBackStack = new ArrayDeque<>();
    boolean mIsPendingBackStackOperation = false;
    private final FragmentManager.OnBackStackChangedListener mOnBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() { // from class: androidx.navigation.fragment.FragmentNavigator.1
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:androidx.navigation.NavDestination), (r1 I:androidx.navigation.Navigator) type: DIRECT call: androidx.navigation.NavDestination.<init>(androidx.navigation.Navigator):void, block:B:2:0x0000 */
        @Override // android.support.p083v4.app.FragmentManager.OnBackStackChangedListener
        public void onBackStackChanged() {
            new NavDestination((Navigator<? extends NavDestination>) this);
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:java.lang.Object) type: DIRECT call: java.lang.Object.<init>():void, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener, java.lang.Object] */
    @Override // androidx.navigation.Navigator
    public void onRestoreState(@Nullable Bundle bundle) {
        ?? obj = new Object();
        ((AbstractAppBarOnDestinationChangedListener) obj).mContext = this;
        ((AbstractAppBarOnDestinationChangedListener) obj).mTopLevelDestinations = bundle.getTopLevelDestinations();
        DrawerLayout drawerLayout = bundle.getDrawerLayout();
        if (drawerLayout != null) {
            ((AbstractAppBarOnDestinationChangedListener) obj).mDrawerLayoutWeakReference = new WeakReference<>(drawerLayout);
        } else {
            ((AbstractAppBarOnDestinationChangedListener) obj).mDrawerLayoutWeakReference = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:java.lang.Object) type: DIRECT call: java.lang.Object.<init>():void, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0003: IPUT  (r1 I:android.content.Context), (r0 I:androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener) androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener.mContext android.content.Context, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener, java.lang.Object] */
    @Override // androidx.navigation.Navigator
    @Nullable
    public Bundle onSaveState() {
        Context context;
        ?? obj = new Object();
        ((AbstractAppBarOnDestinationChangedListener) obj).mContext = context;
        ((AbstractAppBarOnDestinationChangedListener) obj).mTopLevelDestinations = getTopLevelDestinations();
        DrawerLayout drawerLayout = getDrawerLayout();
        if (drawerLayout != null) {
            ((AbstractAppBarOnDestinationChangedListener) obj).mDrawerLayoutWeakReference = new WeakReference<>(drawerLayout);
            return;
        }
        ((AbstractAppBarOnDestinationChangedListener) obj).mDrawerLayoutWeakReference = null;
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: INVOKE  (r0 I:java.lang.Object) type: DIRECT call: java.lang.Object.<init>():void, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0003: IPUT  (r1 I:android.content.Context), (r0 I:androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener) androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener.mContext android.content.Context, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener, java.lang.Object] */
    @Override // androidx.navigation.Navigator
    public boolean popBackStack() {
        Context context;
        ?? obj = new Object();
        ((AbstractAppBarOnDestinationChangedListener) obj).mContext = context;
        ((AbstractAppBarOnDestinationChangedListener) obj).mTopLevelDestinations = getTopLevelDestinations();
        DrawerLayout drawerLayout = getDrawerLayout();
        if (drawerLayout != null) {
            ((AbstractAppBarOnDestinationChangedListener) obj).mDrawerLayoutWeakReference = new WeakReference<>(drawerLayout);
            return;
        }
        ((AbstractAppBarOnDestinationChangedListener) obj).mDrawerLayoutWeakReference = null;
        return;
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @android.support.annotation.NonNull
    private java.lang.String generateBackStackName(int r0, int r1) {
        /*
            r-1 = this;
            androidx.navigation.fragment.FragmentNavigator$Destination r0 = r1.createDestination()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.fragment.FragmentNavigator.generateBackStackName(int, int):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.navigation.fragment.FragmentNavigator$Destination, int] */
    private int getDestId(@Nullable String str) {
        return str.createDestination();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0002: INVOKE  (r1 I:androidx.navigation.NavDestination) = 
      (r0 I:androidx.navigation.fragment.FragmentNavigator)
      (r1 I:androidx.navigation.fragment.FragmentNavigator$Destination)
      (r2 I:android.os.Bundle)
      (r3 I:androidx.navigation.NavOptions)
      (r4 I:androidx.navigation.Navigator$Extras)
     type: VIRTUAL call: androidx.navigation.fragment.FragmentNavigator.navigate(androidx.navigation.fragment.FragmentNavigator$Destination, android.os.Bundle, androidx.navigation.NavOptions, androidx.navigation.Navigator$Extras):androidx.navigation.NavDestination, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0000: CHECK_CAST (r1 I:androidx.navigation.fragment.FragmentNavigator$Destination) = (androidx.navigation.fragment.FragmentNavigator$Destination) (r1 I:??[OBJECT, ARRAY]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0002: INVOKE  (r1 I:androidx.navigation.NavDestination) = 
      (r0v0 ?? I:androidx.navigation.fragment.FragmentNavigator)
      (r1v1 ?? I:androidx.navigation.fragment.FragmentNavigator$Destination)
      (r2 I:android.os.Bundle)
      (r3 I:androidx.navigation.NavOptions)
      (r4 I:androidx.navigation.Navigator$Extras)
     type: VIRTUAL call: androidx.navigation.fragment.FragmentNavigator.navigate(androidx.navigation.fragment.FragmentNavigator$Destination, android.os.Bundle, androidx.navigation.NavOptions, androidx.navigation.Navigator$Extras):androidx.navigation.NavDestination, block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0002: INVOKE  (r1 I:androidx.navigation.NavDestination) = 
      (r0v0 ?? I:androidx.navigation.fragment.FragmentNavigator)
      (r1v1 ?? I:androidx.navigation.fragment.FragmentNavigator$Destination)
      (r2v0 ?? I:android.os.Bundle)
      (r3 I:androidx.navigation.NavOptions)
      (r4 I:androidx.navigation.Navigator$Extras)
     type: VIRTUAL call: androidx.navigation.fragment.FragmentNavigator.navigate(androidx.navigation.fragment.FragmentNavigator$Destination, android.os.Bundle, androidx.navigation.NavOptions, androidx.navigation.Navigator$Extras):androidx.navigation.NavDestination, block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.fragment.FragmentNavigator] */
    /* JADX WARN: Type inference failed for: r1v2, types: [boolean, androidx.navigation.NavDestination] */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r3v0, types: [androidx.navigation.NavOptions] */
    boolean isBackStackEqual() {
        Destination navigate;
        ?? navigate2;
        ?? navigate3;
        ?? navigate4;
        return navigate2.navigate(navigate, navigate3, navigate4, this);
    }

    public FragmentNavigator(@NonNull Context context, @NonNull FragmentManager fragmentManager, int i) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mContainerId = i;
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @Override // androidx.navigation.Navigator
    @android.support.annotation.Nullable
    public androidx.navigation.NavDestination navigate(@android.support.annotation.NonNull androidx.navigation.fragment.FragmentNavigator.Destination r-1, @android.support.annotation.Nullable android.os.Bundle r0, @android.support.annotation.Nullable androidx.navigation.NavOptions r1, @android.support.annotation.Nullable androidx.navigation.Navigator.Extras r2) {
        /*
            r-2 = this;
            android.support.v4.app.FragmentManager r0 = r2.mFragmentManager
            android.support.v4.app.FragmentManager$OnBackStackChangedListener r1 = r2.mOnBackStackChangedListener
            r0.addOnBackStackChangedListener(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.fragment.FragmentNavigator.navigate(androidx.navigation.fragment.FragmentNavigator$Destination, android.os.Bundle, androidx.navigation.NavOptions, androidx.navigation.Navigator$Extras):androidx.navigation.NavDestination");
    }

    @Override // androidx.navigation.Navigator
    public void onBackPressAdded() {
        this.mFragmentManager.addOnBackStackChangedListener(this.mOnBackStackChangedListener);
    }

    @Override // androidx.navigation.Navigator
    public void onBackPressRemoved() {
        this.mFragmentManager.removeOnBackStackChangedListener(this.mOnBackStackChangedListener);
    }

    @Override // androidx.navigation.Navigator
    @NonNull
    public Destination createDestination() {
        return new Destination(this);
    }

    @NonNull
    public Fragment instantiateFragment(@NonNull Context context, @NonNull FragmentManager fragmentManager, @NonNull String str, @Nullable Bundle bundle) {
        return Fragment.instantiate(context, str, bundle);
    }

    @NavDestination.ClassType(Fragment.class)
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class Destination extends NavDestination {
        private String mClassName;

        public Destination(@NonNull NavigatorProvider navigatorProvider) {
            this(navigatorProvider.getNavigator(FragmentNavigator.class));
        }

        public Destination(@NonNull Navigator<? extends Destination> navigator) {
            super(navigator);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: IPUT  (r1v0 ?? I:java.lang.String), (r0 I:androidx.navigation.fragment.FragmentNavigator$Destination) androidx.navigation.fragment.FragmentNavigator.Destination.mClassName java.lang.String, block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.fragment.FragmentNavigator$Destination, java.lang.String] */
        @NonNull
        public final String getClassName() {
            ?? r0;
            r0.mClassName = this;
            return r0;
        }

        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        @Override // androidx.navigation.NavDestination
        @android.support.annotation.CallSuper
        public void onInflate(@android.support.annotation.NonNull android.content.Context r0, @android.support.annotation.NonNull android.util.AttributeSet r1) {
            /*
                r-1 = this;
                r0.mClassName = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.fragment.FragmentNavigator.Destination.onInflate(android.content.Context, android.util.AttributeSet):void");
        }

        @NonNull
        public final Destination setClassName(@NonNull String str) {
            this.mClassName = str;
            return this;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class Extras implements Navigator.Extras {
        private final LinkedHashMap<View, String> mSharedElements = new LinkedHashMap<>();

        Extras(Map<View, String> map) {
            this.mSharedElements.putAll(map);
        }

        @NonNull
        public Map<View, String> getSharedElements() {
            return Collections.unmodifiableMap(this.mSharedElements);
        }

        /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
        public static final class Builder {
            private final LinkedHashMap<View, String> mSharedElements = new LinkedHashMap<>();

            @NonNull
            public Builder addSharedElement(@NonNull View view, @NonNull String str) {
                this.mSharedElements.put(view, str);
                return this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.fragment.FragmentNavigator$Extras$Builder, androidx.navigation.fragment.FragmentNavigator$Extras] */
            @NonNull
            public Builder addSharedElements(@NonNull Map<View, String> map) {
                return new Extras(map.mSharedElements);
            }

            @NonNull
            public Extras build() {
                return new Extras(this.mSharedElements);
            }
        }
    }
}

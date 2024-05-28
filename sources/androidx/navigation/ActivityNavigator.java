package androidx.navigation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.ActivityOptionsCompat;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigator;

@Navigator.Name("activity")
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class ActivityNavigator extends Navigator<Destination> {
    private static final String EXTRA_NAV_CURRENT = "android-support-navigation:ActivityNavigator:current";
    private static final String EXTRA_NAV_SOURCE = "android-support-navigation:ActivityNavigator:source";
    private static final String EXTRA_POP_ENTER_ANIM = "android-support-navigation:ActivityNavigator:popEnterAnim";
    private static final String EXTRA_POP_EXIT_ANIM = "android-support-navigation:ActivityNavigator:popExitAnim";
    private Context mContext;
    private Activity mHostActivity;

    @Override // androidx.navigation.Navigator
    @Nullable
    public NavDestination navigate(@NonNull Destination destination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        return navigate(destination, bundle, navOptions, extras);
    }

    public ActivityNavigator(@NonNull Context context) {
        this.mContext = context;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                this.mHostActivity = (Activity) context;
                return;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
    }

    public static void applyPopAnimationsToPendingTransition(@NonNull Activity activity) {
        Intent intent = activity.getIntent();
        if (intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra(EXTRA_POP_ENTER_ANIM, -1);
        int intExtra2 = intent.getIntExtra(EXTRA_POP_EXIT_ANIM, -1);
        if (intExtra == -1 && intExtra2 == -1) {
            return;
        }
        if (intExtra == -1) {
            intExtra = 0;
        }
        if (intExtra2 == -1) {
            intExtra2 = 0;
        }
        activity.overridePendingTransition(intExtra, intExtra2);
    }

    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: IPUT  (r1v0 ?? I:androidx.navigation.NavController), (r0 I:androidx.navigation.NavController$1) androidx.navigation.NavController.1.this$0 androidx.navigation.NavController, block:B:2:0x0000 */
    @Override // androidx.navigation.Navigator
    public boolean popBackStack() {
        new NavigatorProvider();
        return;
    }

    @NonNull
    final Context getContext() {
        return this.mContext;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.navigation.Navigator
    @NonNull
    public Destination createDestination() {
        return new Destination(this);
    }

    @NavDestination.ClassType(Activity.class)
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class Destination extends NavDestination {
        private String mDataPattern;
        private Intent mIntent;

        @Override // androidx.navigation.NavDestination
        boolean supportsActions() {
            return false;
        }

        public Destination(@NonNull NavigatorProvider navigatorProvider) {
            this(navigatorProvider.getNavigator(ActivityNavigator.class));
        }

        public Destination(@NonNull Navigator<? extends Destination> navigator) {
            super(navigator);
        }

        @NonNull
        public final Destination setIntent(@Nullable Intent intent) {
            this.mIntent = intent;
            return this;
        }

        @Nullable
        public final Intent getIntent() {
            return this.mIntent;
        }

        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        @Override // androidx.navigation.NavDestination
        @android.support.annotation.CallSuper
        public void onInflate(@android.support.annotation.NonNull android.content.Context r0, @android.support.annotation.NonNull android.util.AttributeSet r1) {
            /*
                r-1 = this;
                r0.mDataPattern = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.ActivityNavigator.Destination.onInflate(android.content.Context, android.util.AttributeSet):void");
        }

        @NonNull
        public final Destination setAction(@Nullable String str) {
            this.mDataPattern = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public final Destination setComponentName(@Nullable ComponentName componentName) {
            this.mDataPattern = componentName;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public final Destination setData(@Nullable Uri uri) {
            this.mDataPattern = uri;
            return this;
        }

        @NonNull
        public final Destination setDataPattern(@Nullable String str) {
            this.mDataPattern = str;
            return this;
        }

        @Nullable
        public final String getAction() {
            return this.mDataPattern;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [android.content.ComponentName, java.lang.String] */
        @Nullable
        public final ComponentName getComponent() {
            return this.mDataPattern;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [android.net.Uri, java.lang.String] */
        @Nullable
        public final Uri getData() {
            return this.mDataPattern;
        }

        @Nullable
        public final String getDataPattern() {
            return this.mDataPattern;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class Extras implements Navigator.Extras {
        private final ActivityOptionsCompat mActivityOptions;
        private final int mFlags;

        Extras(int i, @Nullable ActivityOptionsCompat activityOptionsCompat) {
            this.mFlags = i;
            this.mActivityOptions = activityOptionsCompat;
        }

        public int getFlags() {
            return this.mFlags;
        }

        @Nullable
        public ActivityOptionsCompat getActivityOptions() {
            return this.mActivityOptions;
        }

        /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
        public static final class Builder {
            private ActivityOptionsCompat mActivityOptions;
            private int mFlags;

            @NonNull
            public Builder addFlags(int i) {
                this.mFlags = i | this.mFlags;
                return this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: IPUT  (r1v0 ?? I:android.support.v4.app.ActivityOptionsCompat), (r0 I:androidx.navigation.ActivityNavigator$Extras$Builder) androidx.navigation.ActivityNavigator.Extras.Builder.mActivityOptions android.support.v4.app.ActivityOptionsCompat, block:B:2:0x0000 */
            /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.ActivityNavigator$Extras$Builder, androidx.navigation.ActivityNavigator$Extras] */
            @NonNull
            public Extras build() {
                ?? r0;
                r0.mActivityOptions = this;
                return r0;
            }

            @NonNull
            public Builder setActivityOptions(@NonNull ActivityOptionsCompat activityOptionsCompat) {
                this.mActivityOptions = activityOptionsCompat;
                return this;
            }
        }
    }
}

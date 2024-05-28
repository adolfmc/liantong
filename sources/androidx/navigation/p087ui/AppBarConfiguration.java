package androidx.navigation.p087ui;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.widget.DrawerLayout;
import android.view.Menu;
import androidx.navigation.NavGraph;
import java.util.HashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: androidx.navigation.ui.AppBarConfiguration */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class AppBarConfiguration {
    @Nullable
    private final DrawerLayout mDrawerLayout;
    @Nullable
    private final OnNavigateUpListener mFallbackOnNavigateUpListener;
    @NonNull
    private final Set<Integer> mTopLevelDestinations;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: androidx.navigation.ui.AppBarConfiguration$OnNavigateUpListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnNavigateUpListener {
        boolean onNavigateUp();
    }

    private AppBarConfiguration(@NonNull Set<Integer> set, @Nullable DrawerLayout drawerLayout, @Nullable OnNavigateUpListener onNavigateUpListener) {
        this.mTopLevelDestinations = set;
        this.mDrawerLayout = drawerLayout;
        this.mFallbackOnNavigateUpListener = onNavigateUpListener;
    }

    @NonNull
    public Set<Integer> getTopLevelDestinations() {
        return this.mTopLevelDestinations;
    }

    @Nullable
    public DrawerLayout getDrawerLayout() {
        return this.mDrawerLayout;
    }

    @Nullable
    public OnNavigateUpListener getFallbackOnNavigateUpListener() {
        return this.mFallbackOnNavigateUpListener;
    }

    /* renamed from: androidx.navigation.ui.AppBarConfiguration$Builder */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static final class Builder {
        @Nullable
        private DrawerLayout mDrawerLayout;
        @Nullable
        private OnNavigateUpListener mFallbackOnNavigateUpListener;
        @NonNull
        private final Set<Integer> mTopLevelDestinations = new HashSet();

        public Builder(@NonNull NavGraph navGraph) {
            this.mTopLevelDestinations.add(Integer.valueOf(NavigationUI.findStartDestination(navGraph).getId()));
        }

        public Builder(@NonNull Menu menu) {
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                this.mTopLevelDestinations.add(Integer.valueOf(menu.getItem(i).getItemId()));
            }
        }

        public Builder(@NonNull int... iArr) {
            for (int i : iArr) {
                this.mTopLevelDestinations.add(Integer.valueOf(i));
            }
        }

        public Builder(@NonNull Set<Integer> set) {
            this.mTopLevelDestinations.addAll(set);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 0, insn: 0x0000: IPUT  (r1v0 ?? I:android.support.v4.widget.DrawerLayout), (r0 I:androidx.navigation.ui.AppBarConfiguration$Builder) androidx.navigation.ui.AppBarConfiguration.Builder.mDrawerLayout android.support.v4.widget.DrawerLayout, block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.navigation.ui.AppBarConfiguration$Builder, androidx.navigation.ui.AppBarConfiguration] */
        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public AppBarConfiguration build() {
            ?? r0;
            r0.mDrawerLayout = this;
            return r0;
        }

        @NonNull
        public Builder setDrawerLayout(@Nullable DrawerLayout drawerLayout) {
            this.mDrawerLayout = drawerLayout;
            return this;
        }

        @NonNull
        public Builder setFallbackOnNavigateUpListener(@Nullable OnNavigateUpListener onNavigateUpListener) {
            this.mFallbackOnNavigateUpListener = onNavigateUpListener;
            return this;
        }
    }
}

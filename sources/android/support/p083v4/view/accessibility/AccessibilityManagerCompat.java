package android.support.p083v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityManager;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class AccessibilityManagerCompat {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Deprecated
    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat$AccessibilityStateChangeListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface AccessibilityStateChangeListener {
        @Deprecated
        void onAccessibilityStateChanged(boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Deprecated
    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class AccessibilityStateChangeListenerCompat implements AccessibilityStateChangeListener {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat$TouchExplorationStateChangeListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface TouchExplorationStateChangeListener {
        void onTouchExplorationStateChanged(boolean z);
    }

    @Deprecated
    public static boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, AccessibilityStateChangeListener accessibilityStateChangeListener) {
        if (accessibilityStateChangeListener == null) {
            return false;
        }
        return accessibilityManager.addAccessibilityStateChangeListener(new AccessibilityStateChangeListenerWrapper(accessibilityStateChangeListener));
    }

    @Deprecated
    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, AccessibilityStateChangeListener accessibilityStateChangeListener) {
        if (accessibilityStateChangeListener == null) {
            return false;
        }
        return accessibilityManager.removeAccessibilityStateChangeListener(new AccessibilityStateChangeListenerWrapper(accessibilityStateChangeListener));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat$AccessibilityStateChangeListenerWrapper */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class AccessibilityStateChangeListenerWrapper implements AccessibilityManager.AccessibilityStateChangeListener {
        AccessibilityStateChangeListener mListener;

        AccessibilityStateChangeListenerWrapper(@NonNull AccessibilityStateChangeListener accessibilityStateChangeListener) {
            this.mListener = accessibilityStateChangeListener;
        }

        public int hashCode() {
            return this.mListener.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mListener.equals(((AccessibilityStateChangeListenerWrapper) obj).mListener);
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean z) {
            this.mListener.onAccessibilityStateChanged(z);
        }
    }

    @Deprecated
    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager accessibilityManager) {
        return accessibilityManager.getInstalledAccessibilityServiceList();
    }

    @Deprecated
    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager accessibilityManager, int i) {
        return accessibilityManager.getEnabledAccessibilityServiceList(i);
    }

    @Deprecated
    public static boolean isTouchExplorationEnabled(AccessibilityManager accessibilityManager) {
        return accessibilityManager.isTouchExplorationEnabled();
    }

    public static boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilityManager, TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
        if (Build.VERSION.SDK_INT < 19 || touchExplorationStateChangeListener == null) {
            return false;
        }
        return accessibilityManager.addTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(touchExplorationStateChangeListener));
    }

    public static boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilityManager, TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
        if (Build.VERSION.SDK_INT < 19 || touchExplorationStateChangeListener == null) {
            return false;
        }
        return accessibilityManager.removeTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(touchExplorationStateChangeListener));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @RequiresApi(19)
    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class TouchExplorationStateChangeListenerWrapper implements AccessibilityManager.TouchExplorationStateChangeListener {
        final TouchExplorationStateChangeListener mListener;

        TouchExplorationStateChangeListenerWrapper(@NonNull TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
            this.mListener = touchExplorationStateChangeListener;
        }

        public int hashCode() {
            return this.mListener.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mListener.equals(((TouchExplorationStateChangeListenerWrapper) obj).mListener);
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            this.mListener.onTouchExplorationStateChanged(z);
        }
    }

    private AccessibilityManagerCompat() {
    }
}

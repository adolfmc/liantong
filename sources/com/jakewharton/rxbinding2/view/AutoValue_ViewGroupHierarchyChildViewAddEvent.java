package com.jakewharton.rxbinding2.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class AutoValue_ViewGroupHierarchyChildViewAddEvent extends ViewGroupHierarchyChildViewAddEvent {
    private final View child;
    private final ViewGroup view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ViewGroupHierarchyChildViewAddEvent(ViewGroup viewGroup, View view) {
        if (viewGroup == null) {
            throw new NullPointerException("Null view");
        }
        this.view = viewGroup;
        if (view == null) {
            throw new NullPointerException("Null child");
        }
        this.child = view;
    }

    @Override // com.jakewharton.rxbinding2.view.ViewGroupHierarchyChangeEvent
    @NonNull
    public ViewGroup view() {
        return this.view;
    }

    @Override // com.jakewharton.rxbinding2.view.ViewGroupHierarchyChangeEvent
    @NonNull
    public View child() {
        return this.child;
    }

    public String toString() {
        return "ViewGroupHierarchyChildViewAddEvent{view=" + this.view + ", child=" + this.child + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ViewGroupHierarchyChildViewAddEvent) {
            ViewGroupHierarchyChildViewAddEvent viewGroupHierarchyChildViewAddEvent = (ViewGroupHierarchyChildViewAddEvent) obj;
            return this.view.equals(viewGroupHierarchyChildViewAddEvent.view()) && this.child.equals(viewGroupHierarchyChildViewAddEvent.child());
        }
        return false;
    }

    public int hashCode() {
        return ((this.view.hashCode() ^ 1000003) * 1000003) ^ this.child.hashCode();
    }
}

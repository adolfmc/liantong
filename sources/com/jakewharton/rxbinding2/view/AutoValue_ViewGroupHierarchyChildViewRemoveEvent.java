package com.jakewharton.rxbinding2.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class AutoValue_ViewGroupHierarchyChildViewRemoveEvent extends ViewGroupHierarchyChildViewRemoveEvent {
    private final View child;
    private final ViewGroup view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ViewGroupHierarchyChildViewRemoveEvent(ViewGroup viewGroup, View view) {
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
        return "ViewGroupHierarchyChildViewRemoveEvent{view=" + this.view + ", child=" + this.child + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ViewGroupHierarchyChildViewRemoveEvent) {
            ViewGroupHierarchyChildViewRemoveEvent viewGroupHierarchyChildViewRemoveEvent = (ViewGroupHierarchyChildViewRemoveEvent) obj;
            return this.view.equals(viewGroupHierarchyChildViewRemoveEvent.view()) && this.child.equals(viewGroupHierarchyChildViewRemoveEvent.child());
        }
        return false;
    }

    public int hashCode() {
        return ((this.view.hashCode() ^ 1000003) * 1000003) ^ this.child.hashCode();
    }
}

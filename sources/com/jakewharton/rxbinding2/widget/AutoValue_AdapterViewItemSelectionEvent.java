package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class AutoValue_AdapterViewItemSelectionEvent extends AdapterViewItemSelectionEvent {

    /* renamed from: id */
    private final long f12197id;
    private final int position;
    private final View selectedView;
    private final AdapterView<?> view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AdapterViewItemSelectionEvent(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = adapterView;
        if (view == null) {
            throw new NullPointerException("Null selectedView");
        }
        this.selectedView = view;
        this.position = i;
        this.f12197id = j;
    }

    @Override // com.jakewharton.rxbinding2.widget.AdapterViewSelectionEvent
    @NonNull
    public AdapterView<?> view() {
        return this.view;
    }

    @Override // com.jakewharton.rxbinding2.widget.AdapterViewItemSelectionEvent
    @NonNull
    public View selectedView() {
        return this.selectedView;
    }

    @Override // com.jakewharton.rxbinding2.widget.AdapterViewItemSelectionEvent
    public int position() {
        return this.position;
    }

    @Override // com.jakewharton.rxbinding2.widget.AdapterViewItemSelectionEvent
    /* renamed from: id */
    public long mo13744id() {
        return this.f12197id;
    }

    public String toString() {
        return "AdapterViewItemSelectionEvent{view=" + this.view + ", selectedView=" + this.selectedView + ", position=" + this.position + ", id=" + this.f12197id + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AdapterViewItemSelectionEvent) {
            AdapterViewItemSelectionEvent adapterViewItemSelectionEvent = (AdapterViewItemSelectionEvent) obj;
            return this.view.equals(adapterViewItemSelectionEvent.view()) && this.selectedView.equals(adapterViewItemSelectionEvent.selectedView()) && this.position == adapterViewItemSelectionEvent.position() && this.f12197id == adapterViewItemSelectionEvent.mo13744id();
        }
        return false;
    }

    public int hashCode() {
        long j = this.f12197id;
        return ((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.selectedView.hashCode()) * 1000003) ^ this.position) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}

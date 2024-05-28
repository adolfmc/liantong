package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class AutoValue_AdapterViewItemLongClickEvent extends AdapterViewItemLongClickEvent {
    private final View clickedView;

    /* renamed from: id */
    private final long f12196id;
    private final int position;
    private final AdapterView<?> view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AdapterViewItemLongClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = adapterView;
        if (view == null) {
            throw new NullPointerException("Null clickedView");
        }
        this.clickedView = view;
        this.position = i;
        this.f12196id = j;
    }

    @Override // com.jakewharton.rxbinding2.widget.AdapterViewItemLongClickEvent
    @NonNull
    public AdapterView<?> view() {
        return this.view;
    }

    @Override // com.jakewharton.rxbinding2.widget.AdapterViewItemLongClickEvent
    @NonNull
    public View clickedView() {
        return this.clickedView;
    }

    @Override // com.jakewharton.rxbinding2.widget.AdapterViewItemLongClickEvent
    public int position() {
        return this.position;
    }

    @Override // com.jakewharton.rxbinding2.widget.AdapterViewItemLongClickEvent
    /* renamed from: id */
    public long mo13745id() {
        return this.f12196id;
    }

    public String toString() {
        return "AdapterViewItemLongClickEvent{view=" + this.view + ", clickedView=" + this.clickedView + ", position=" + this.position + ", id=" + this.f12196id + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AdapterViewItemLongClickEvent) {
            AdapterViewItemLongClickEvent adapterViewItemLongClickEvent = (AdapterViewItemLongClickEvent) obj;
            return this.view.equals(adapterViewItemLongClickEvent.view()) && this.clickedView.equals(adapterViewItemLongClickEvent.clickedView()) && this.position == adapterViewItemLongClickEvent.position() && this.f12196id == adapterViewItemLongClickEvent.mo13745id();
        }
        return false;
    }

    public int hashCode() {
        long j = this.f12196id;
        return ((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.clickedView.hashCode()) * 1000003) ^ this.position) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}

package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.widget.TextView;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class AutoValue_TextViewAfterTextChangeEvent extends TextViewAfterTextChangeEvent {
    private final Editable editable;
    private final TextView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TextViewAfterTextChangeEvent(TextView textView, @Nullable Editable editable) {
        if (textView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = textView;
        this.editable = editable;
    }

    @Override // com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent
    @NonNull
    public TextView view() {
        return this.view;
    }

    @Override // com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent
    @Nullable
    public Editable editable() {
        return this.editable;
    }

    public String toString() {
        return "TextViewAfterTextChangeEvent{view=" + this.view + ", editable=" + ((Object) this.editable) + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextViewAfterTextChangeEvent) {
            TextViewAfterTextChangeEvent textViewAfterTextChangeEvent = (TextViewAfterTextChangeEvent) obj;
            if (this.view.equals(textViewAfterTextChangeEvent.view())) {
                Editable editable = this.editable;
                if (editable == null) {
                    if (textViewAfterTextChangeEvent.editable() == null) {
                        return true;
                    }
                } else if (editable.equals(textViewAfterTextChangeEvent.editable())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.view.hashCode() ^ 1000003) * 1000003;
        Editable editable = this.editable;
        return hashCode ^ (editable == null ? 0 : editable.hashCode());
    }
}

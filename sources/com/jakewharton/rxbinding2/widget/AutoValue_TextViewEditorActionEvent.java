package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class AutoValue_TextViewEditorActionEvent extends TextViewEditorActionEvent {
    private final int actionId;
    private final KeyEvent keyEvent;
    private final TextView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TextViewEditorActionEvent(TextView textView, int i, @Nullable KeyEvent keyEvent) {
        if (textView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = textView;
        this.actionId = i;
        this.keyEvent = keyEvent;
    }

    @Override // com.jakewharton.rxbinding2.widget.TextViewEditorActionEvent
    @NonNull
    public TextView view() {
        return this.view;
    }

    @Override // com.jakewharton.rxbinding2.widget.TextViewEditorActionEvent
    public int actionId() {
        return this.actionId;
    }

    @Override // com.jakewharton.rxbinding2.widget.TextViewEditorActionEvent
    @Nullable
    public KeyEvent keyEvent() {
        return this.keyEvent;
    }

    public String toString() {
        return "TextViewEditorActionEvent{view=" + this.view + ", actionId=" + this.actionId + ", keyEvent=" + this.keyEvent + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextViewEditorActionEvent) {
            TextViewEditorActionEvent textViewEditorActionEvent = (TextViewEditorActionEvent) obj;
            if (this.view.equals(textViewEditorActionEvent.view()) && this.actionId == textViewEditorActionEvent.actionId()) {
                KeyEvent keyEvent = this.keyEvent;
                if (keyEvent == null) {
                    if (textViewEditorActionEvent.keyEvent() == null) {
                        return true;
                    }
                } else if (keyEvent.equals(textViewEditorActionEvent.keyEvent())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.view.hashCode() ^ 1000003) * 1000003) ^ this.actionId) * 1000003;
        KeyEvent keyEvent = this.keyEvent;
        return hashCode ^ (keyEvent == null ? 0 : keyEvent.hashCode());
    }
}

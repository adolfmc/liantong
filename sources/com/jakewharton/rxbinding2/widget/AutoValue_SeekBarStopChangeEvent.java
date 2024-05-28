package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.widget.SeekBar;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class AutoValue_SeekBarStopChangeEvent extends SeekBarStopChangeEvent {
    private final SeekBar view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SeekBarStopChangeEvent(SeekBar seekBar) {
        if (seekBar == null) {
            throw new NullPointerException("Null view");
        }
        this.view = seekBar;
    }

    @Override // com.jakewharton.rxbinding2.widget.SeekBarChangeEvent
    @NonNull
    public SeekBar view() {
        return this.view;
    }

    public String toString() {
        return "SeekBarStopChangeEvent{view=" + this.view + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SeekBarStopChangeEvent) {
            return this.view.equals(((SeekBarStopChangeEvent) obj).view());
        }
        return false;
    }

    public int hashCode() {
        return this.view.hashCode() ^ 1000003;
    }
}

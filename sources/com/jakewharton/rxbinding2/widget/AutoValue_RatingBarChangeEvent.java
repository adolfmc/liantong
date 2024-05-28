package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.widget.RatingBar;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class AutoValue_RatingBarChangeEvent extends RatingBarChangeEvent {
    private final boolean fromUser;
    private final float rating;
    private final RatingBar view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_RatingBarChangeEvent(RatingBar ratingBar, float f, boolean z) {
        if (ratingBar == null) {
            throw new NullPointerException("Null view");
        }
        this.view = ratingBar;
        this.rating = f;
        this.fromUser = z;
    }

    @Override // com.jakewharton.rxbinding2.widget.RatingBarChangeEvent
    @NonNull
    public RatingBar view() {
        return this.view;
    }

    @Override // com.jakewharton.rxbinding2.widget.RatingBarChangeEvent
    public float rating() {
        return this.rating;
    }

    @Override // com.jakewharton.rxbinding2.widget.RatingBarChangeEvent
    public boolean fromUser() {
        return this.fromUser;
    }

    public String toString() {
        return "RatingBarChangeEvent{view=" + this.view + ", rating=" + this.rating + ", fromUser=" + this.fromUser + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RatingBarChangeEvent) {
            RatingBarChangeEvent ratingBarChangeEvent = (RatingBarChangeEvent) obj;
            return this.view.equals(ratingBarChangeEvent.view()) && Float.floatToIntBits(this.rating) == Float.floatToIntBits(ratingBarChangeEvent.rating()) && this.fromUser == ratingBarChangeEvent.fromUser();
        }
        return false;
    }

    public int hashCode() {
        return ((((this.view.hashCode() ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.rating)) * 1000003) ^ (this.fromUser ? 1231 : 1237);
    }
}

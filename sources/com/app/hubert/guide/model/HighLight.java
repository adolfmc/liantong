package com.app.hubert.guide.model;

import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface HighLight {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum Shape {
        CIRCLE,
        RECTANGLE,
        OVAL,
        ROUND_RECTANGLE
    }

    @Nullable
    HighlightOptions getOptions();

    float getRadius();

    RectF getRectF(View view);

    int getRound();

    Shape getShape();
}

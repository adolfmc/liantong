package android.support.design.widget;

import android.graphics.Outline;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CircularBorderDrawableLollipop extends CircularBorderDrawable {
    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        copyBounds(this.rect);
        outline.setOval(this.rect);
    }
}

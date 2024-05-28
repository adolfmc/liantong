package android.support.design.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.C0884R;
import android.support.p086v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TabItem extends View {
    public final int customLayout;
    public final Drawable icon;
    public final CharSequence text;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0884R.styleable.TabItem);
        this.text = obtainStyledAttributes.getText(C0884R.styleable.TabItem_android_text);
        this.icon = obtainStyledAttributes.getDrawable(C0884R.styleable.TabItem_android_icon);
        this.customLayout = obtainStyledAttributes.getResourceId(C0884R.styleable.TabItem_android_layout, 0);
        obtainStyledAttributes.recycle();
    }
}

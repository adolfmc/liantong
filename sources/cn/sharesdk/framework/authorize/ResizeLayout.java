package cn.sharesdk.framework.authorize;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ResizeLayout extends LinearLayout {

    /* renamed from: a */
    private OnResizeListener f2850a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnResizeListener {
        void OnResize(int i, int i2, int i3, int i4);
    }

    /* renamed from: a */
    public void m21877a(OnResizeListener onResizeListener) {
        this.f2850a = onResizeListener;
    }

    public ResizeLayout(Context context) {
        super(context);
    }

    public ResizeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        OnResizeListener onResizeListener = this.f2850a;
        if (onResizeListener != null) {
            onResizeListener.OnResize(i, i2, i3, i4);
        }
    }
}

package com.baidu.mapapi.map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.baidu.mapapi.map.WearMapView;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SwipeDismissView extends RelativeLayout {

    /* renamed from: a */
    WearMapView.OnDismissCallback f6405a;

    public SwipeDismissView(Context context, AttributeSet attributeSet, int i, View view) {
        super(context, attributeSet, i);
        this.f6405a = null;
        m18869a(context, view);
    }

    public SwipeDismissView(Context context, AttributeSet attributeSet, View view) {
        super(context, attributeSet);
        this.f6405a = null;
        m18869a(context, view);
    }

    public SwipeDismissView(Context context, View view) {
        super(context);
        this.f6405a = null;
        m18869a(context, view);
    }

    /* renamed from: a */
    void m18869a(Context context, View view) {
        setOnTouchListener(new SwipeDismissTouchListener(view, new Object(), new C2786z(this)));
    }

    public void setCallback(WearMapView.OnDismissCallback onDismissCallback) {
        this.f6405a = onDismissCallback;
    }
}

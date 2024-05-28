package cn.anicert.verification.lib_identify.p092ui.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import cn.anicert.verification.lib_identify.p092ui.widgets.AbstractView$OnClickListenerC1595g;
import com.tfd.sdk.LF8bOvWP4;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.anicert.verification.lib_identify.ui.widgets.MyEditView */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MyEditView extends View implements AbstractView$OnClickListenerC1595g.InterfaceC1596a {

    /* renamed from: a */
    public Handler f2618a;

    /* renamed from: b */
    private C1588a f2619b;

    /* renamed from: c */
    private Paint f2620c;

    /* renamed from: d */
    private Paint f2621d;

    /* renamed from: e */
    private int f2622e;

    /* renamed from: f */
    private int f2623f;

    /* renamed from: g */
    private int f2624g;

    /* renamed from: h */
    private int f2625h;

    /* renamed from: i */
    private int f2626i;

    /* renamed from: j */
    private List<String> f2627j;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.anicert.verification.lib_identify.ui.widgets.MyEditView$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C1588a extends AbstractView$OnClickListenerC1595g<MyEditView> {
        C1588a(Context context) {
            super(context);
        }
    }

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(198);
    }

    public MyEditView(Context context) {
        super(context);
        this.f2627j = new ArrayList(8);
        m22141a(context);
    }

    public MyEditView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2627j = new ArrayList(8);
        m22141a(context);
    }

    public MyEditView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2627j = new ArrayList(8);
        m22141a(context);
    }

    /* renamed from: a */
    private native void m22141a(Context context);

    /* renamed from: f */
    private native void m22136f();

    /* renamed from: a */
    public native void m22143a();

    /* renamed from: a */
    public native void m22142a(Activity activity);

    @Override // cn.anicert.verification.lib_identify.p092ui.widgets.AbstractView$OnClickListenerC1595g.InterfaceC1596a
    /* renamed from: a */
    public native void mo22118a(String str);

    /* renamed from: b */
    public native void m22140b();

    /* renamed from: c */
    public native void m22139c();

    /* renamed from: d */
    public native String m22138d();

    @Override // android.view.View
    public native boolean dispatchTouchEvent(MotionEvent motionEvent);

    /* renamed from: e */
    public native void m22137e();

    @Override // android.view.View
    protected native void onDraw(Canvas canvas);

    public native void setOnConfirmListener(View.OnClickListener onClickListener);
}

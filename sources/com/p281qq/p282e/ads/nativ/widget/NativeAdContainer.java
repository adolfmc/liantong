package com.p281qq.p282e.ads.nativ.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.p281qq.p282e.comm.util.GDTLogger;

/* renamed from: com.qq.e.ads.nativ.widget.NativeAdContainer */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NativeAdContainer extends FrameLayout {

    /* renamed from: a */
    private ViewStatusListener f17862a;

    /* renamed from: b */
    private ViewStatus f17863b;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.ads.nativ.widget.NativeAdContainer$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static /* synthetic */ class C68661 {

        /* renamed from: a */
        static final /* synthetic */ int[] f17864a;

        static {
            int[] iArr = new int[ViewStatus.values().length];
            f17864a = iArr;
            try {
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17864a[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.ads.nativ.widget.NativeAdContainer$ViewStatus */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    enum ViewStatus {
        INIT,
        ATTACHED,
        DETACHED
    }

    public NativeAdContainer(Context context) {
        super(context);
        this.f17863b = ViewStatus.INIT;
    }

    public NativeAdContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17863b = ViewStatus.INIT;
    }

    public NativeAdContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f17863b = ViewStatus.INIT;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        ViewStatusListener viewStatusListener = this.f17862a;
        if (viewStatusListener != null) {
            viewStatusListener.onDispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        GDTLogger.m8235d("NativeAdContainer onAttachedToWindow");
        this.f17863b = ViewStatus.ATTACHED;
        ViewStatusListener viewStatusListener = this.f17862a;
        if (viewStatusListener != null) {
            viewStatusListener.onAttachToWindow();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        GDTLogger.m8235d("NativeAdContainer onDetachedFromWindow");
        this.f17863b = ViewStatus.DETACHED;
        ViewStatusListener viewStatusListener = this.f17862a;
        if (viewStatusListener != null) {
            viewStatusListener.onDetachFromWindow();
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        GDTLogger.m8235d("onWindowFocusChanged: hasWindowFocus: " + z);
        ViewStatusListener viewStatusListener = this.f17862a;
        if (viewStatusListener != null) {
            viewStatusListener.onWindowFocusChanged(z);
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        GDTLogger.m8235d("onWindowVisibilityChanged: visibility: " + i);
        ViewStatusListener viewStatusListener = this.f17862a;
        if (viewStatusListener != null) {
            viewStatusListener.onWindowVisibilityChanged(i);
        }
    }

    public void setViewStatusListener(ViewStatusListener viewStatusListener) {
        this.f17862a = viewStatusListener;
        if (viewStatusListener != null) {
            int ordinal = this.f17863b.ordinal();
            if (ordinal == 1) {
                this.f17862a.onAttachToWindow();
            } else if (ordinal != 2) {
            } else {
                this.f17862a.onDetachFromWindow();
            }
        }
    }
}

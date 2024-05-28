package com.example.asus.detectionandalign.PercentRelativeLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.example.asus.detectionandalign.PercentRelativeLayout.C4240a;

/* renamed from: com.example.asus.detectionandalign.PercentRelativeLayout.PercentRelativeLayout */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4238PercentRelativeLayout extends RelativeLayout {

    /* renamed from: a */
    private final C4240a f9748a;

    /* renamed from: com.example.asus.detectionandalign.PercentRelativeLayout.PercentRelativeLayout$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C4239a extends RelativeLayout.LayoutParams implements C4240a.InterfaceC4242b {

        /* renamed from: a */
        private C4240a.C4241a f9749a;

        public C4239a(int i, int i2) {
            super(i, i2);
        }

        public C4239a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f9749a = C4240a.m16288a(context, attributeSet);
        }

        @Override // com.example.asus.detectionandalign.PercentRelativeLayout.C4240a.InterfaceC4242b
        /* renamed from: a */
        public C4240a.C4241a mo16279a() {
            if (this.f9749a == null) {
                this.f9749a = new C4240a.C4241a();
            }
            return this.f9749a;
        }

        @Override // android.view.ViewGroup.LayoutParams
        protected void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            C4240a.m16286a(this, typedArray, i, i2);
        }
    }

    public C4238PercentRelativeLayout(Context context) {
        super(context);
        this.f9748a = new C4240a(this);
    }

    public C4238PercentRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9748a = new C4240a(this);
    }

    public C4238PercentRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f9748a = new C4240a(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.RelativeLayout, android.view.ViewGroup
    /* renamed from: a */
    public C4239a generateDefaultLayoutParams() {
        return new C4239a(-1, -1);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup
    /* renamed from: a */
    public C4239a generateLayoutParams(AttributeSet attributeSet) {
        return new C4239a(getContext(), attributeSet);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f9748a.m16290a();
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        this.f9748a.m16289a(i, i2);
        super.onMeasure(i, i2);
        if (this.f9748a.m16285b()) {
            super.onMeasure(i, i2);
        }
    }
}

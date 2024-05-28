package com.unicom.pay.widget.ticker;

import android.graphics.Paint;
import com.unicom.pay.widget.ticker.TickerView;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.unicom.pay.widget.ticker.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10730c {

    /* renamed from: a */
    public final Paint f20566a;

    /* renamed from: c */
    public float f20568c;

    /* renamed from: d */
    public float f20569d;

    /* renamed from: b */
    public final Map<Character, Float> f20567b = new HashMap(256);

    /* renamed from: e */
    public TickerView.EnumC10726e f20570e = TickerView.EnumC10726e.ANY;

    public C10730c(Paint paint) {
        this.f20566a = paint;
        m6024a();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<java.lang.Character, java.lang.Float>] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.HashMap, java.util.Map<java.lang.Character, java.lang.Float>] */
    /* renamed from: a */
    public final float m6023a(char c) {
        if (c == 0) {
            return 0.0f;
        }
        Float f = (Float) this.f20567b.get(Character.valueOf(c));
        if (f != null) {
            return f.floatValue();
        }
        float measureText = this.f20566a.measureText(Character.toString(c));
        this.f20567b.put(Character.valueOf(c), Float.valueOf(measureText));
        return measureText;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<java.lang.Character, java.lang.Float>] */
    /* renamed from: a */
    public final void m6024a() {
        this.f20567b.clear();
        Paint.FontMetrics fontMetrics = this.f20566a.getFontMetrics();
        float f = fontMetrics.bottom;
        float f2 = fontMetrics.top;
        this.f20568c = f - f2;
        this.f20569d = -f2;
    }
}

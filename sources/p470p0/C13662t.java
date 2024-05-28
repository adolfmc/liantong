package p470p0;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;

/* renamed from: p0.t */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13662t {

    /* renamed from: a */
    public CharSequence f27499a;

    /* renamed from: h */
    public ClickableSpan f27506h;

    /* renamed from: b */
    public int f27500b = 33;
    @ColorInt

    /* renamed from: c */
    public int f27501c = 301989888;
    @ColorInt

    /* renamed from: d */
    public int f27502d = 301989888;
    @ColorInt

    /* renamed from: e */
    public int f27503e = 301989888;

    /* renamed from: f */
    public float f27504f = -1.0f;

    /* renamed from: g */
    public float f27505g = -1.0f;

    /* renamed from: i */
    public SpannableStringBuilder f27507i = new SpannableStringBuilder();

    public C13662t(@NonNull CharSequence charSequence) {
        this.f27499a = charSequence;
    }

    /* renamed from: a */
    public final void m166a() {
        int length = this.f27507i.length();
        this.f27507i.append(this.f27499a);
        int length2 = this.f27507i.length();
        int i = this.f27501c;
        if (i != 301989888) {
            this.f27507i.setSpan(new ForegroundColorSpan(i), length, length2, this.f27500b);
            this.f27501c = 301989888;
        }
        int i2 = this.f27502d;
        if (i2 != 301989888) {
            this.f27507i.setSpan(new BackgroundColorSpan(i2), length, length2, this.f27500b);
            this.f27502d = 301989888;
        }
        int i3 = this.f27503e;
        if (i3 != 301989888) {
            this.f27507i.setSpan(new QuoteSpan(i3), length, length2, 0);
            this.f27503e = 301989888;
        }
        float f = this.f27504f;
        if (f != -1.0f) {
            this.f27507i.setSpan(new RelativeSizeSpan(f), length, length2, this.f27500b);
            this.f27504f = -1.0f;
        }
        float f2 = this.f27505g;
        if (f2 != -1.0f) {
            this.f27507i.setSpan(new ScaleXSpan(f2), length, length2, this.f27500b);
            this.f27505g = -1.0f;
        }
        ClickableSpan clickableSpan = this.f27506h;
        if (clickableSpan != null) {
            this.f27507i.setSpan(clickableSpan, length, length2, this.f27500b);
            this.f27506h = null;
        }
        this.f27500b = 33;
    }
}

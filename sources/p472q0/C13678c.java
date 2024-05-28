package p472q0;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;
import p474r0.InterfaceC13717c;

/* renamed from: q0.c */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13678c implements InterfaceC13717c<TextView> {
    @Override // p474r0.InterfaceC13717c
    /* renamed from: a */
    public /* synthetic */ int mo124a() {
        return InterfaceC13717c.CC.$default$a(this);
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: a */
    public final TextView mo123a(Context context) {
        TextView textView = new TextView(context);
        textView.setId(16908299);
        textView.setGravity(17);
        textView.setTextColor(-285212673);
        textView.setTextSize(0, TypedValue.applyDimension(2, 14.0f, context.getResources().getDisplayMetrics()));
        int applyDimension = (int) TypedValue.applyDimension(1, 24.0f, context.getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 16.0f, context.getResources().getDisplayMetrics());
        int i = Build.VERSION.SDK_INT;
        if (i >= 16) {
            textView.setPaddingRelative(applyDimension, applyDimension2, applyDimension, applyDimension2);
        } else {
            textView.setPadding(applyDimension, applyDimension2, applyDimension, applyDimension2);
        }
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-2013265920);
        gradientDrawable.setCornerRadius(TypedValue.applyDimension(1, 10.0f, context.getResources().getDisplayMetrics()));
        if (i >= 16) {
            textView.setBackground(gradientDrawable);
        } else {
            textView.setBackgroundDrawable(gradientDrawable);
        }
        if (i >= 21) {
            textView.setZ(TypedValue.applyDimension(1, 3.0f, context.getResources().getDisplayMetrics()));
        }
        textView.setMaxLines(5);
        return textView;
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: b */
    public /* synthetic */ int mo122b() {
        return InterfaceC13717c.CC.$default$b(this);
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: c */
    public /* synthetic */ int mo121c() {
        return InterfaceC13717c.CC.$default$c(this);
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: d */
    public /* synthetic */ float mo120d() {
        return InterfaceC13717c.CC.$default$d(this);
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: e */
    public /* synthetic */ float mo119e() {
        return InterfaceC13717c.CC.$default$e(this);
    }
}

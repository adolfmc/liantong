package cn.ltzf.passguard;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Region;
import android.view.View;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class LTTipView extends View {
    private int height;
    private Context m_context;
    private String m_tag;

    /* renamed from: p */
    private Path f2722p;
    private String position;

    /* renamed from: re */
    public Region f2723re;
    private int width;

    /* renamed from: x */
    private int f2724x;

    /* renamed from: y */
    private int f2725y;

    public LTTipView(Context context, String str, int i, int i2, String str2, int[] iArr) {
        super(context);
        this.f2722p = new Path();
        this.m_tag = "";
        this.m_context = null;
        this.width = 0;
        this.height = 0;
        this.f2724x = 0;
        this.f2725y = 0;
        this.f2723re = new Region();
        this.m_tag = str;
        this.m_context = context;
        this.width = i;
        this.height = i2;
        this.position = str2;
        this.f2724x = iArr[0];
        this.f2725y = iArr[1];
    }

    private int measureHeight(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int i2 = this.width * 2;
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }

    private int measureWidth(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int i2 = this.width;
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }

    public static int sp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0134  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onDraw(android.graphics.Canvas r10) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.ltzf.passguard.LTTipView.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(measureWidth(i), measureHeight(i2));
    }
}

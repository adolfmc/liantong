package com.bytedance.applog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import java.util.Locale;
import org.json.JSONArray;

/* renamed from: com.bytedance.applog.p0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3658p0 extends View {

    /* renamed from: a */
    public Paint f8684a;

    /* renamed from: b */
    public String[] f8685b;

    /* renamed from: c */
    public long f8686c;

    /* renamed from: d */
    public long f8687d;

    /* renamed from: e */
    public long[] f8688e;

    /* renamed from: f */
    public String f8689f;

    /* renamed from: g */
    public long[] f8690g;

    /* renamed from: h */
    public Paint f8691h;

    /* renamed from: i */
    public Paint f8692i;

    /* renamed from: j */
    public Paint f8693j;

    /* renamed from: k */
    public Paint f8694k;

    /* renamed from: l */
    public float[] f8695l;

    /* renamed from: m */
    public float[] f8696m;

    /* renamed from: n */
    public final int[] f8697n;

    /* renamed from: o */
    public final int[] f8698o;

    /* renamed from: p */
    public boolean f8699p;

    public C3658p0(Context context) {
        super(context);
        this.f8689f = "正在刷新";
        this.f8697n = new int[]{-23248, -1129416};
        this.f8698o = new int[]{-566118, -11082290};
        this.f8694k = new Paint(1);
        this.f8694k.setColor(this.f8697n[1]);
        this.f8694k.setStrokeWidth(5.0f);
        this.f8684a = new Paint(1);
        this.f8684a.setColor(this.f8698o[1]);
        this.f8684a.setStrokeWidth(5.0f);
        this.f8693j = new Paint(1);
        this.f8693j.setColor(-1);
        this.f8693j.setStyle(Paint.Style.STROKE);
        this.f8693j.setStrokeWidth(1.0f);
        this.f8693j.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.f8691h = new Paint(1);
        this.f8691h.setColor(-1);
        this.f8691h.setTextSize(C3554b2.m17332a(getContext(), 12.0f));
        this.f8692i = new Paint(1);
        this.f8692i.setColor(-1);
        this.f8692i.setTextSize(C3554b2.m17332a(getContext(), 12.0f));
        this.f8692i.setTextAlign(Paint.Align.CENTER);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    /* renamed from: a */
    public void m17182a(String str, JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3) {
        int length = jSONArray != null ? jSONArray.length() : 0;
        this.f8689f = str != null ? str : "暂无数据";
        this.f8685b = new String[length];
        this.f8688e = new long[length];
        this.f8690g = new long[length];
        long j = Long.MIN_VALUE;
        int i = 0;
        long j2 = Long.MAX_VALUE;
        while (true) {
            if (i >= length) {
                break;
            }
            long optLong = jSONArray2 != null ? jSONArray2.optLong(i, 0L) : 0L;
            long optLong2 = jSONArray3 != null ? jSONArray3.optLong(i, 0L) : 0L;
            if (optLong > j) {
                j = optLong;
            }
            if (optLong < j2) {
                j2 = optLong;
            }
            if (optLong2 > j) {
                j = optLong2;
            }
            if (optLong2 < j2) {
                j2 = optLong2;
            }
            this.f8685b[i] = jSONArray.optString(i, "");
            this.f8688e[i] = optLong;
            this.f8690g[i] = optLong2;
            i++;
        }
        if (j == Long.MIN_VALUE) {
            j = 0;
        }
        this.f8686c = j;
        if (j2 == Long.MAX_VALUE) {
            j2 = 0;
        }
        this.f8687d = j2;
        long j3 = this.f8686c;
        if (j3 == this.f8687d) {
            this.f8687d = j3 - 3;
        }
        int i2 = (length > 1 ? length - 1 : 0) * 4;
        this.f8695l = new float[i2];
        this.f8696m = new float[i2];
        invalidate();
    }

    /* renamed from: a */
    public void m17181a(boolean z) {
        this.f8694k.setColor(z ? this.f8697n[0] : this.f8697n[1]);
        this.f8684a.setColor(z ? this.f8698o[0] : this.f8698o[1]);
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        float f;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        Locale locale;
        Object[] objArr;
        String str;
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int m17332a = C3554b2.m17332a(getContext(), 48.0f);
        int m17332a2 = C3554b2.m17332a(getContext(), 16.0f);
        int i9 = m17332a2 / 2;
        int i10 = m17332a2 / 4;
        if (this.f8699p) {
            canvas.drawText("数据拉取中，请等候...", (width / 2.0f) - m17332a, height / 2.0f, this.f8691h);
            return;
        }
        int i11 = m17332a - m17332a2;
        int i12 = ((C3559c1.f8392d / 2) - m17332a) - m17332a2;
        float f2 = i11;
        canvas.drawText(this.f8689f, i12, f2, this.f8691h);
        int i13 = m17332a + m17332a2 + i12;
        float f3 = i11 - i9;
        canvas.drawRect(i13 - i9, f3, i13, f2, this.f8694k);
        int i14 = i13 + i10;
        canvas.drawText("PV", i14, f2, this.f8691h);
        int i15 = m17332a2 * 2;
        canvas.drawRect(i - i9, f3, i14 + i15, f2, this.f8684a);
        canvas.drawText("UV", i + i10, f2, this.f8691h);
        int i16 = width - m17332a2;
        int i17 = height - i15;
        int i18 = (int) ((this.f8686c - this.f8687d) + 1);
        int i19 = i18 > 8 ? 8 : i18;
        double d = i19 - 1;
        int i20 = (int) (((i17 - m17332a) * 1.0d) / d);
        double d2 = ((this.f8686c - this.f8687d) * 1.0d) / d;
        int i21 = 0;
        while (i21 < i19) {
            int i22 = (i21 * i20) + m17332a;
            int i23 = i20;
            int i24 = i19;
            int i25 = width;
            int i26 = height;
            double d3 = this.f8686c - (i21 * d2);
            if (d3 >= 1.0E10d) {
                locale = Locale.getDefault();
                objArr = new Object[]{Double.valueOf(d3 / 1.0E9d)};
                str = "%.1fB";
            } else if (d3 >= 1.0E9d) {
                locale = Locale.getDefault();
                objArr = new Object[]{Double.valueOf(d3 / 1.0E9d)};
                str = "%.2fB";
            } else if (d3 >= 1.0E7d) {
                locale = Locale.getDefault();
                objArr = new Object[]{Double.valueOf(d3 / 1000000.0d)};
                str = "%.1fM";
            } else if (d3 >= 1000000.0d) {
                locale = Locale.getDefault();
                objArr = new Object[]{Double.valueOf(d3 / 1000000.0d)};
                str = "%.2fM";
            } else if (d3 >= 10000.0d) {
                locale = Locale.getDefault();
                objArr = new Object[]{Double.valueOf(d3 / 1000.0d)};
                str = "%.1fK";
            } else if (d3 >= 1000.0d) {
                locale = Locale.getDefault();
                objArr = new Object[]{Double.valueOf(d3 / 1000.0d)};
                str = "%.2fK";
            } else {
                locale = Locale.getDefault();
                objArr = new Object[]{Double.valueOf(d3)};
                str = "%.1f";
            }
            canvas.drawText(String.format(locale, str, objArr), i9, i22 + i9, this.f8691h);
            float f4 = i22;
            canvas.drawLine(m17332a, f4, i16, f4, this.f8691h);
            i21++;
            i20 = i23;
            i19 = i24;
            i9 = i9;
            width = i25;
            height = i26;
        }
        int i27 = i20;
        int i28 = width;
        int i29 = height;
        float f5 = m17332a;
        int i30 = i17;
        float f6 = i30;
        canvas.drawLine(f5, f5, f5, f6, this.f8691h);
        float f7 = i16;
        canvas.drawLine(f7, f5, f7, f6, this.f8691h);
        String[] strArr = this.f8685b;
        int length = strArr != null ? strArr.length : 0;
        if (length <= 0) {
            canvas.drawText("暂无数据，点这里可刷新", (i28 / 2.0f) - f5, i29 / 2.0f, this.f8691h);
            return;
        }
        int i31 = (int) (((i16 - m17332a) * 1.0d) / length);
        int i32 = (i31 / 2) + m17332a;
        int i33 = 55 / (length > 12 ? 12 : length);
        int i34 = (length + 11) / 12;
        int i35 = 0;
        while (i35 < length) {
            int i36 = (i35 * i31) + i32;
            if (i35 % i34 == 0) {
                int i37 = i30 + m17332a2;
                String str2 = this.f8685b[i35];
                int i38 = length;
                i3 = i30;
                int i39 = i33 * 2;
                if (str2.length() > i39) {
                    str2 = str2.substring(str2.length() - i39).trim();
                }
                if (str2.length() < i33 || (i35 / i34) % 2 != 0) {
                    canvas.drawText(str2, i36, i37, this.f8692i);
                }
                float f8 = i36;
                i4 = m17332a2;
                i6 = i36;
                float f9 = f5;
                i5 = i31;
                i7 = i35;
                i2 = i33;
                f = f5;
                i8 = i38;
                canvas.drawLine(f8, f9, f8, f6, this.f8693j);
            } else {
                i2 = i33;
                f = f5;
                i3 = i30;
                i4 = m17332a2;
                i5 = i31;
                i6 = i36;
                i7 = i35;
                i8 = length;
            }
            double d4 = m17332a;
            double d5 = i27;
            float f10 = i6;
            float f11 = (int) ((((this.f8686c - this.f8688e[i7]) / d2) * d5) + d4);
            canvas.drawCircle(f10, f11, 5.0f, this.f8694k);
            int i40 = i27;
            float f12 = (int) ((((this.f8686c - this.f8690g[i7]) / d2) * d5) + d4);
            canvas.drawCircle(f10, f12, 5.0f, this.f8684a);
            if (i7 != 0) {
                int i41 = i7 * 4;
                int i42 = i41 - 2;
                int i43 = i41 - 1;
                float[] fArr = this.f8696m;
                float[] fArr2 = this.f8695l;
                fArr2[i42] = f10;
                fArr[i42] = f10;
                fArr2[i43] = f12;
                fArr[i43] = f11;
            }
            if (i7 != i8 - 1) {
                int i44 = i7 * 4;
                int i45 = i44 + 1;
                float[] fArr3 = this.f8696m;
                float[] fArr4 = this.f8695l;
                fArr4[i44] = f10;
                fArr3[i44] = f10;
                fArr4[i45] = f12;
                fArr3[i45] = f11;
            }
            i35 = i7 + 1;
            length = i8;
            i31 = i5;
            i30 = i3;
            m17332a2 = i4;
            i33 = i2;
            i27 = i40;
            f5 = f;
        }
        if (length > 1) {
            canvas.drawLines(this.f8696m, this.f8694k);
            canvas.drawLines(this.f8695l, this.f8684a);
        }
    }
}

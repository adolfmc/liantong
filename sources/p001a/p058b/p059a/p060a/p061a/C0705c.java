package p001a.p058b.p059a.p060a.p061a;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.util.Locale;
import org.json.JSONObject;
import p001a.p002a.p003a.p004a.outline;

/* renamed from: a.b.a.a.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0705c {

    /* renamed from: a */
    public String f2117a = "android";

    /* renamed from: b */
    public String f2118b;

    /* renamed from: c */
    public int f2119c;

    /* renamed from: d */
    public String f2120d;

    /* renamed from: e */
    public String f2121e;

    /* renamed from: f */
    public String f2122f;

    /* renamed from: g */
    public int f2123g;

    /* renamed from: h */
    public int f2124h;

    /* renamed from: i */
    public String f2125i;

    /* renamed from: j */
    public String f2126j;

    /* renamed from: k */
    public String f2127k;

    /* renamed from: l */
    public String f2128l;

    /* renamed from: m */
    public String f2129m;

    /* renamed from: n */
    public JSONObject f2130n;

    public C0705c(Context context, String str) {
        int i;
        this.f2129m = "";
        try {
            this.f2118b = Build.VERSION.RELEASE;
            this.f2119c = Build.VERSION.SDK_INT;
            this.f2120d = Build.MODEL;
            this.f2121e = Build.BRAND;
            this.f2122f = Build.CPU_ABI;
            this.f2125i = Locale.getDefault().getLanguage();
            this.f2129m = str;
            this.f2128l = this.f2129m;
            this.f2126j = this.f2128l;
            this.f2127k = this.f2126j;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= 17) {
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            } else {
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            }
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                this.f2123g = displayMetrics.heightPixels;
                i = displayMetrics.widthPixels;
            } else {
                this.f2123g = displayMetrics.widthPixels;
                i = displayMetrics.heightPixels;
            }
            this.f2124h = i;
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a(""), "BaseInfo");
        }
    }

    /* renamed from: a */
    public JSONObject m22341a() {
        if (this.f2130n == null) {
            try {
                this.f2130n = new JSONObject();
                this.f2130n.put("osType", this.f2117a);
                this.f2130n.put("osVersion", this.f2118b);
                this.f2130n.put("osVersionInt", this.f2119c);
                this.f2130n.put("model", this.f2120d);
                this.f2130n.put("brand", this.f2121e);
                this.f2130n.put("arch", this.f2122f);
                this.f2130n.put("screenWidth", this.f2123g);
                this.f2130n.put("screenHeight", this.f2124h);
                this.f2130n.put("language", this.f2125i);
                this.f2130n.put("uniqueId", this.f2126j);
                this.f2130n.put("imei", this.f2127k);
                this.f2130n.put("androidId", this.f2128l);
                this.f2130n.put("cuid", this.f2129m);
            } catch (Exception e) {
                outline.m24438a(e, outline.m24437a(""), "BaseInfo");
            }
        }
        return this.f2130n;
    }
}

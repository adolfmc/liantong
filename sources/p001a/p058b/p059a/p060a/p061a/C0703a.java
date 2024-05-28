package p001a.p058b.p059a.p060a.p061a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import org.json.JSONObject;
import p001a.p002a.p003a.p004a.outline;

/* renamed from: a.b.a.a.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0703a {

    /* renamed from: a */
    public String f2099a;

    /* renamed from: b */
    public String f2100b;

    /* renamed from: c */
    public String f2101c;

    /* renamed from: d */
    public String f2102d;

    /* renamed from: e */
    public String f2103e;

    /* renamed from: f */
    public String f2104f;

    /* renamed from: g */
    public JSONObject f2105g;

    public C0703a(Context context) {
        try {
            this.f2100b = context.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.f2100b, 0);
            this.f2102d = packageInfo.versionName;
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(packageInfo.versionCode);
            this.f2101c = sb.toString();
            this.f2099a = (String) packageManager.getApplicationLabel(packageInfo.applicationInfo);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a(""), "BaseInfo");
        }
    }

    /* renamed from: a */
    public JSONObject m22345a() {
        try {
            if (this.f2105g == null) {
                this.f2105g = new JSONObject();
                this.f2105g.put("appName", this.f2099a);
                this.f2105g.put("packageName", this.f2100b);
                this.f2105g.put("versionCode", this.f2101c);
                this.f2105g.put("versionName", this.f2102d);
            }
            this.f2105g.put("ak", this.f2103e);
            this.f2105g.put("sdkVersion", this.f2104f);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a(""), "BaseInfo");
        }
        return this.f2105g;
    }
}

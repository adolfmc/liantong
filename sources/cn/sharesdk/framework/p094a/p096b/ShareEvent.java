package cn.sharesdk.framework.p094a.p096b;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.b.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareEvent extends BaseEvent {

    /* renamed from: o */
    private static int f2807o;

    /* renamed from: p */
    private static long f2808p;

    /* renamed from: a */
    public int f2809a;

    /* renamed from: b */
    public String f2810b;

    /* renamed from: c */
    public String f2811c;

    /* renamed from: d */
    public C1746a f2812d = new C1746a();

    /* renamed from: m */
    public String f2813m;

    /* renamed from: n */
    public String[] f2814n;

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: a */
    protected String mo21927a() {
        return "[SHR]";
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: b */
    protected int mo21925b() {
        return 5000;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: c */
    protected int mo21924c() {
        return 30;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|');
        sb.append(this.f2809a);
        sb.append('|');
        sb.append(this.f2810b);
        sb.append('|');
        sb.append(TextUtils.isEmpty(this.f2811c) ? "" : this.f2811c);
        String str = "";
        String[] strArr = this.f2814n;
        if (strArr != null && strArr.length > 0) {
            str = "[\"" + TextUtils.join("\",\"", this.f2814n) + "\"]";
        }
        sb.append('|');
        sb.append(str);
        sb.append('|');
        C1746a c1746a = this.f2812d;
        if (c1746a != null) {
            try {
                String encodeToString = Base64.encodeToString(Data.AES128Encode(this.f2792f.substring(0, 16), c1746a.toString()), 0);
                if (encodeToString.contains("\n")) {
                    encodeToString = encodeToString.replace("\n", "");
                }
                sb.append(encodeToString);
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
        }
        sb.append('|');
        if (!TextUtils.isEmpty(this.f2798l)) {
            sb.append(this.f2798l);
        }
        sb.append('|');
        if (!TextUtils.isEmpty(this.f2813m)) {
            try {
                String encodeToString2 = Base64.encodeToString(Data.AES128Encode(this.f2792f.substring(0, 16), this.f2813m), 0);
                if (!TextUtils.isEmpty(encodeToString2) && encodeToString2.contains("\n")) {
                    encodeToString2 = encodeToString2.replace("\n", "");
                }
                sb.append(encodeToString2);
            } catch (Throwable th2) {
                SSDKLog.m21740b().m21737b(th2);
            }
        }
        return sb.toString();
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: d */
    protected long mo21923d() {
        return f2807o;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: e */
    protected long mo21922e() {
        return f2808p;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: f */
    protected void mo21921f() {
        f2807o++;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: a */
    protected void mo21926a(long j) {
        f2808p = j;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: ShareEvent.java */
    /* renamed from: cn.sharesdk.framework.a.b.f$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C1746a {

        /* renamed from: b */
        public String f2816b;

        /* renamed from: g */
        public HashMap<String, Object> f2821g;

        /* renamed from: a */
        public String f2815a = "";

        /* renamed from: c */
        public ArrayList<String> f2817c = new ArrayList<>();

        /* renamed from: d */
        public ArrayList<String> f2818d = new ArrayList<>();

        /* renamed from: e */
        public ArrayList<String> f2819e = new ArrayList<>();

        /* renamed from: f */
        public ArrayList<Bitmap> f2820f = new ArrayList<>();

        public String toString() {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(this.f2816b)) {
                this.f2816b = this.f2816b.trim().replaceAll("\r", "");
                this.f2816b = this.f2816b.trim().replaceAll("\n", "");
                this.f2816b = this.f2816b.trim().replaceAll("\r\n", "");
            }
            hashMap.put("text", this.f2816b);
            hashMap.put("url", this.f2817c);
            ArrayList<String> arrayList = this.f2818d;
            if (arrayList != null && arrayList.size() > 0) {
                hashMap.put("imgs", this.f2818d);
            }
            if (this.f2821g != null) {
                hashMap.put("attch", new Hashon().fromHashMap(this.f2821g));
            }
            return new Hashon().fromHashMap(hashMap);
        }
    }
}

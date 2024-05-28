package cn.sharesdk.framework.p094a.p096b;

import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.Data;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.b.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AuthEvent extends BaseEvent {

    /* renamed from: m */
    private static int f2785m;

    /* renamed from: n */
    private static long f2786n;

    /* renamed from: a */
    public int f2787a;

    /* renamed from: b */
    public String f2788b;

    /* renamed from: c */
    public String f2789c;

    /* renamed from: d */
    public String f2790d;

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: a */
    protected String mo21927a() {
        return "[AUT]";
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: b */
    protected int mo21925b() {
        return 5000;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: c */
    protected int mo21924c() {
        return 5;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|');
        sb.append(this.f2787a);
        sb.append('|');
        sb.append(this.f2788b);
        sb.append('|');
        if (!TextUtils.isEmpty(this.f2790d)) {
            try {
                String encodeToString = Base64.encodeToString(Data.AES128Encode(this.f2792f.substring(0, 16), this.f2790d), 0);
                if (!TextUtils.isEmpty(encodeToString) && encodeToString.contains("\n")) {
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
        if (!TextUtils.isEmpty(this.f2789c)) {
            sb.append(this.f2789c);
        }
        return sb.toString();
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: d */
    protected long mo21923d() {
        return f2785m;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: e */
    protected long mo21922e() {
        return f2786n;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: f */
    protected void mo21921f() {
        f2785m++;
    }

    @Override // cn.sharesdk.framework.p094a.p096b.BaseEvent
    /* renamed from: a */
    protected void mo21926a(long j) {
        f2786n = j;
    }
}

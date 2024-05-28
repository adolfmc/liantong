package com.megvii.lv5;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.megvii.lv5.C5440f4;
import com.megvii.lv5.C5661y3;
import com.megvii.lv5.C5668z3;
import com.megvii.lv5.InterfaceC5509m3;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.x3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC5652x3<T> implements Comparable<AbstractC5652x3<T>> {

    /* renamed from: a */
    public final C5440f4.C5441a f13902a;

    /* renamed from: b */
    public final int f13903b;

    /* renamed from: c */
    public final String f13904c;

    /* renamed from: d */
    public final int f13905d;

    /* renamed from: e */
    public C5668z3.InterfaceC5669a f13906e;

    /* renamed from: f */
    public Integer f13907f;

    /* renamed from: g */
    public C5661y3 f13908g;

    /* renamed from: h */
    public boolean f13909h;

    /* renamed from: i */
    public boolean f13910i;

    /* renamed from: j */
    public C5535p3 f13911j;

    /* renamed from: k */
    public InterfaceC5509m3.C5510a f13912k;

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.x3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5653a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ String f13913a;

        /* renamed from: b */
        public final /* synthetic */ long f13914b;

        public RunnableC5653a(String str, long j) {
            this.f13913a = str;
            this.f13914b = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC5652x3.this.f13902a.m13533a(this.f13913a, this.f13914b);
            AbstractC5652x3.this.f13902a.m13534a(toString());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.x3$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum EnumC5654b {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public AbstractC5652x3(int i, String str, C5668z3.InterfaceC5669a interfaceC5669a) {
        this.f13902a = C5440f4.C5441a.f12601c ? new C5440f4.C5441a() : null;
        this.f13909h = true;
        this.f13910i = false;
        this.f13912k = null;
        this.f13903b = i;
        this.f13904c = str;
        this.f13906e = interfaceC5669a;
        m12900a(new C5535p3());
        this.f13905d = m12898b(str);
    }

    /* renamed from: b */
    public static int m12898b(String str) {
        Uri parse;
        String host;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null || (host = parse.getHost()) == null) {
            return 0;
        }
        return host.hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public AbstractC5652x3<?> m12900a(C5535p3 c5535p3) {
        this.f13911j = c5535p3;
        return this;
    }

    /* renamed from: a */
    public abstract C5668z3<T> mo12889a(C5622u3 c5622u3);

    /* renamed from: a */
    public void mo12901a() {
        this.f13906e = null;
    }

    /* renamed from: a */
    public abstract void mo12872a(T t);

    /* renamed from: a */
    public void m12899a(String str) {
        if (C5440f4.C5441a.f12601c) {
            this.f13902a.m13533a(str, Thread.currentThread().getId());
        }
    }

    /* renamed from: b */
    public byte[] mo12871b() {
        return null;
    }

    /* renamed from: c */
    public String mo12870c() {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }

    /* renamed from: c */
    public void m12897c(String str) {
        C5661y3 c5661y3 = this.f13908g;
        if (c5661y3 != null) {
            synchronized (c5661y3.f13936c) {
                mo12901a();
                c5661y3.f13936c.remove(this);
            }
            synchronized (c5661y3.f13944k) {
                for (C5661y3.InterfaceC5662a interfaceC5662a : c5661y3.f13944k) {
                    interfaceC5662a.m12890a(this);
                }
            }
            if (this.f13909h) {
                synchronized (c5661y3.f13935b) {
                    String str2 = this.f13904c;
                    Queue<AbstractC5652x3<?>> remove = c5661y3.f13935b.remove(str2);
                    if (remove != null) {
                        if (C5440f4.f12600a) {
                            C5440f4.m13535b("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(remove.size()), str2);
                        }
                        c5661y3.f13937d.addAll(remove);
                    }
                }
            }
        }
        if (C5440f4.C5441a.f12601c) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new RunnableC5653a(str, id));
                return;
            }
            this.f13902a.m13533a(str, id);
            this.f13902a.m13534a(toString());
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        AbstractC5652x3 abstractC5652x3 = (AbstractC5652x3) obj;
        abstractC5652x3.getClass();
        return this.f13907f.intValue() - abstractC5652x3.f13907f.intValue();
    }

    /* renamed from: d */
    public Map<String, String> mo12896d() {
        return Collections.emptyMap();
    }

    @Deprecated
    /* renamed from: e */
    public byte[] mo12869e() {
        return null;
    }

    public String toString() {
        return "[ ] " + this.f13904c + " " + ("0x" + Integer.toHexString(this.f13905d)) + " " + EnumC5654b.NORMAL + " " + this.f13907f;
    }
}

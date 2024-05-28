package com.megvii.lv5;

import android.content.Context;
import android.media.MediaMuxer;
import android.text.TextUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.i1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5475i1 {

    /* renamed from: a */
    public String f12797a;

    /* renamed from: b */
    public final MediaMuxer f12798b;

    /* renamed from: c */
    public int f12799c;

    /* renamed from: d */
    public int f12800d;

    /* renamed from: e */
    public boolean f12801e;

    /* renamed from: f */
    public AbstractRunnableC5460h1 f12802f;

    /* renamed from: g */
    public float f12803g = 1.0f;

    /* renamed from: h */
    public EnumC5488k1 f12804h = EnumC5488k1.Normal;

    static {
        new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.US);
    }

    public C5475i1(Context context, String str) {
        try {
            this.f12797a = m13458a(context, str, ".mp4").toString();
            this.f12798b = new MediaMuxer(this.f12797a, 0);
            this.f12800d = 0;
            this.f12799c = 0;
            this.f12801e = false;
        } catch (NullPointerException unused) {
            throw new RuntimeException("This app has no permission of writing external storage");
        }
    }

    /* renamed from: a */
    public float m13459a() {
        return this.f12803g;
    }

    /* renamed from: a */
    public final File m13458a(Context context, String str, String str2) {
        File file = new File(context.getFilesDir(), "megviiVideo");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (TextUtils.isEmpty(str)) {
            str = "meglive_fmp_vedio";
        }
        File file2 = new File(file, str + str2);
        if (file2.exists()) {
            file2.delete();
        }
        if (file.canWrite()) {
            return file2;
        }
        return null;
    }

    /* renamed from: a */
    public void m13457a(C5452g1 c5452g1) {
        AbstractRunnableC5460h1 abstractRunnableC5460h1 = this.f12802f;
        if (abstractRunnableC5460h1 != null) {
            synchronized (abstractRunnableC5460h1.f12696a) {
                if (abstractRunnableC5460h1.f12697b && !abstractRunnableC5460h1.f12699d) {
                    abstractRunnableC5460h1.f12710o.add(c5452g1);
                    abstractRunnableC5460h1.f12698c++;
                    abstractRunnableC5460h1.f12696a.notifyAll();
                }
            }
        }
    }

    /* renamed from: a */
    public void m13456a(AbstractRunnableC5460h1 abstractRunnableC5460h1) {
        if (!(abstractRunnableC5460h1 instanceof C5482j1)) {
            throw new IllegalArgumentException("unsupported encoder");
        }
        if (this.f12802f != null) {
            throw new IllegalArgumentException("Video encoder already added.");
        }
        this.f12802f = abstractRunnableC5460h1;
        this.f12799c = 1;
    }

    /* renamed from: b */
    public EnumC5488k1 m13454b() {
        return this.f12804h;
    }

    /* renamed from: c */
    public void m13453c() {
        AbstractRunnableC5460h1 abstractRunnableC5460h1 = this.f12802f;
        if (abstractRunnableC5460h1 != null) {
            abstractRunnableC5460h1.mo13448c();
        }
    }

    /* renamed from: d */
    public void m13452d() {
        AbstractRunnableC5460h1 abstractRunnableC5460h1 = this.f12802f;
        if (abstractRunnableC5460h1 != null) {
            abstractRunnableC5460h1.m13497f();
        }
    }

    /* renamed from: e */
    public void m13451e() {
        AbstractRunnableC5460h1 abstractRunnableC5460h1 = this.f12802f;
        if (abstractRunnableC5460h1 != null) {
            abstractRunnableC5460h1.m13496g();
        }
        this.f12802f = null;
    }

    /* renamed from: a */
    public void m13455a(LinkedList<C5452g1> linkedList) {
        AbstractRunnableC5460h1 abstractRunnableC5460h1 = this.f12802f;
        if (abstractRunnableC5460h1 != null) {
            synchronized (abstractRunnableC5460h1.f12696a) {
                if (abstractRunnableC5460h1.f12697b && !abstractRunnableC5460h1.f12699d) {
                    abstractRunnableC5460h1.f12710o.addAll(linkedList);
                    abstractRunnableC5460h1.f12698c += linkedList.size();
                    abstractRunnableC5460h1.f12696a.notifyAll();
                }
            }
        }
    }
}

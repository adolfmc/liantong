package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11131ab;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11169au;
import com.xiaomi.push.C11476p;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.xiaomi.push.service.ay */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11574ay implements InterfaceC11522aa {

    /* renamed from: a */
    private static volatile C11574ay f23636a;

    /* renamed from: a */
    private long f23637a;

    /* renamed from: a */
    Context f23638a;

    /* renamed from: a */
    private SharedPreferences f23639a;

    /* renamed from: a */
    private volatile boolean f23641a = false;

    /* renamed from: a */
    private ConcurrentHashMap<String, AbstractRunnableC11576a> f23640a = new ConcurrentHashMap<>();

    /* renamed from: a */
    public static C11574ay m2605a(Context context) {
        if (f23636a == null) {
            synchronized (C11574ay.class) {
                if (f23636a == null) {
                    f23636a = new C11574ay(context);
                }
            }
        }
        return f23636a;
    }

    private C11574ay(Context context) {
        this.f23638a = context.getApplicationContext();
        this.f23639a = context.getSharedPreferences("sync", 0);
    }

    @Override // com.xiaomi.push.service.InterfaceC11522aa
    /* renamed from: a */
    public void mo2606a() {
        if (this.f23641a) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f23637a < 3600000) {
            return;
        }
        this.f23637a = currentTimeMillis;
        this.f23641a = true;
        C11134ae.m4937a(this.f23638a).m4927a(new Runnable() { // from class: com.xiaomi.push.service.ay.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    for (AbstractRunnableC11576a abstractRunnableC11576a : C11574ay.this.f23640a.values()) {
                        abstractRunnableC11576a.run();
                    }
                } catch (Exception e) {
                    AbstractC11049b.m5282a("Sync job exception :" + e.getMessage());
                }
                C11574ay.this.f23641a = false;
            }
        }, (int) (Math.random() * 10.0d));
    }

    /* renamed from: com.xiaomi.push.service.ay$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractRunnableC11576a implements Runnable {

        /* renamed from: a */
        long f23643a;

        /* renamed from: a */
        String f23644a;

        /* renamed from: a */
        abstract void mo2345a(C11574ay c11574ay);

        /* JADX INFO: Access modifiers changed from: package-private */
        public AbstractRunnableC11576a(String str, long j) {
            this.f23644a = str;
            this.f23643a = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (C11574ay.f23636a != null) {
                Context context = C11574ay.f23636a.f23638a;
                if (C11169au.m4834c(context)) {
                    long currentTimeMillis = System.currentTimeMillis();
                    SharedPreferences sharedPreferences = C11574ay.f23636a.f23639a;
                    if (currentTimeMillis - sharedPreferences.getLong(":ts-" + this.f23644a, 0L) > this.f23643a || C11131ab.m4943a(context)) {
                        SharedPreferences.Editor edit = C11574ay.f23636a.f23639a.edit();
                        C11476p.m2938a(edit.putLong(":ts-" + this.f23644a, System.currentTimeMillis()));
                        mo2345a(C11574ay.f23636a);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public String m2600a(String str, String str2) {
        SharedPreferences sharedPreferences = this.f23639a;
        return sharedPreferences.getString(str + ":" + str2, "");
    }

    /* renamed from: a */
    public void m2599a(String str, String str2, String str3) {
        SharedPreferences.Editor edit = f23636a.f23639a.edit();
        C11476p.m2938a(edit.putString(str + ":" + str2, str3));
    }

    /* renamed from: a */
    public void m2604a(AbstractRunnableC11576a abstractRunnableC11576a) {
        if (this.f23640a.putIfAbsent(abstractRunnableC11576a.f23644a, abstractRunnableC11576a) == null) {
            C11134ae.m4937a(this.f23638a).m4927a(abstractRunnableC11576a, ((int) (Math.random() * 30.0d)) + 10);
        }
    }
}

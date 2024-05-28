package com.megvii.lv5;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.e4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5423e4 {

    /* renamed from: c */
    public static C5423e4 f12542c;

    /* renamed from: d */
    public static Context f12543d;

    /* renamed from: a */
    public C5661y3 f12544a;

    /* renamed from: b */
    public int f12545b;

    public C5423e4(Context context) {
        f12543d = context.getApplicationContext();
        this.f12544a = m13555a();
    }

    /* renamed from: a */
    public static synchronized C5423e4 m13554a(Context context) {
        C5423e4 c5423e4;
        synchronized (C5423e4.class) {
            if (f12542c == null) {
                f12542c = new C5423e4(context);
            }
            c5423e4 = f12542c;
        }
        return c5423e4;
    }

    /* renamed from: a */
    public <T> boolean m13553a(AbstractC5652x3<T> abstractC5652x3) {
        if (m13555a() == null) {
            return false;
        }
        abstractC5652x3.f13911j = new C5535p3(this.f12545b, 0, 1.0f);
        C5661y3 m13555a = m13555a();
        m13555a.getClass();
        abstractC5652x3.f13908g = m13555a;
        synchronized (m13555a.f13936c) {
            m13555a.f13936c.add(abstractC5652x3);
        }
        abstractC5652x3.f13907f = Integer.valueOf(m13555a.f13934a.incrementAndGet());
        abstractC5652x3.m12899a("add-to-queue");
        if (!abstractC5652x3.f13909h) {
            m13555a.f13938e.add(abstractC5652x3);
        } else {
            synchronized (m13555a.f13935b) {
                String str = abstractC5652x3.f13904c;
                if (m13555a.f13935b.containsKey(str)) {
                    Queue<AbstractC5652x3<?>> queue = m13555a.f13935b.get(str);
                    if (queue == null) {
                        queue = new LinkedList<>();
                    }
                    queue.add(abstractC5652x3);
                    m13555a.f13935b.put(str, queue);
                    if (C5440f4.f12600a) {
                        C5440f4.m13535b("Request for cacheKey=%s is in flight, putting on hold.", str);
                    }
                } else {
                    m13555a.f13935b.put(str, null);
                    m13555a.f13937d.add(abstractC5652x3);
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    public C5661y3 m13555a() {
        C5557s3[] c5557s3Arr;
        if (this.f12544a == null) {
            Context context = f12543d;
            if (context == null) {
                return null;
            }
            Context applicationContext = context.getApplicationContext();
            File file = new File(applicationContext.getCacheDir(), "volley");
            try {
                String packageName = applicationContext.getPackageName();
                PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(packageName, 0);
                String str = packageName + "/" + packageInfo.versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
            }
            C5661y3 c5661y3 = new C5661y3(new C5630v4(file, 5242880), new C5617t4(Build.VERSION.SDK_INT >= 9 ? new C5655x4(null) : null, new C5623u4(4096)), 4);
            C5518n3 c5518n3 = c5661y3.f13943j;
            if (c5518n3 != null) {
                c5518n3.f13061e = true;
                c5518n3.interrupt();
            }
            for (C5557s3 c5557s3 : c5661y3.f13942i) {
                if (c5557s3 != null) {
                    c5557s3.f13277e = true;
                    c5557s3.interrupt();
                }
            }
            C5518n3 c5518n32 = new C5518n3(c5661y3.f13937d, c5661y3.f13938e, c5661y3.f13939f, c5661y3.f13941h);
            c5661y3.f13943j = c5518n32;
            c5518n32.start();
            for (int i = 0; i < c5661y3.f13942i.length; i++) {
                C5557s3 c5557s32 = new C5557s3(c5661y3.f13938e, c5661y3.f13940g, c5661y3.f13939f, c5661y3.f13941h);
                c5661y3.f13942i[i] = c5557s32;
                c5557s32.start();
            }
            this.f12544a = c5661y3;
        }
        return this.f12544a;
    }
}

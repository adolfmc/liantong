package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11139af;
import com.xiaomi.push.service.C11571ax;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.db */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11266db {

    /* renamed from: a */
    private static volatile C11266db f21823a;

    /* renamed from: a */
    private Context f21824a;

    /* renamed from: a */
    private final ConcurrentLinkedQueue<C11270b> f21825a = new ConcurrentLinkedQueue<>();

    private C11266db(Context context) {
        this.f21824a = context;
        this.f21825a.add(new C11269a());
        m4377b(0L);
    }

    /* renamed from: a */
    public static C11266db m4384a(Context context) {
        if (f21823a == null) {
            synchronized (C11266db.class) {
                if (f21823a == null) {
                    f21823a = new C11266db(context);
                }
            }
        }
        f21823a.f21824a = context;
        return f21823a;
    }

    /* renamed from: a */
    public void m4379a(final String str, final String str2, final Date date, final Date date2, final int i, final boolean z) {
        this.f21825a.add(new C11270b() { // from class: com.xiaomi.push.db.1

            /* renamed from: a */
            File f21828a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.xiaomi.push.C11266db.C11270b, com.xiaomi.push.C11139af.AbstractC11143b
            /* renamed from: b */
            public void mo2611b() {
                try {
                    File file = new File(C11266db.this.f21824a.getFilesDir() + "/.logcache");
                    if (C11646v.m2275a(file)) {
                        file.mkdirs();
                        if (file.isDirectory()) {
                            C11265da c11265da = new C11265da();
                            c11265da.m4392a(i);
                            this.f21828a = c11265da.m4391a(C11266db.this.f21824a, date, date2, file);
                        }
                    }
                } catch (NullPointerException unused) {
                }
            }

            @Override // com.xiaomi.push.C11139af.AbstractC11143b
            /* renamed from: c */
            public void mo2610c() {
                File file = this.f21828a;
                if (file != null && file.exists()) {
                    C11266db.this.f21825a.add(new C11271c(str, str2, this.f21828a, z));
                }
                C11266db.this.m4385a(0L);
            }
        });
        m4377b(0L);
    }

    /* renamed from: a */
    public void m4386a() {
        m4376c();
        m4385a(0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.db$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11270b extends C11139af.AbstractC11143b {

        /* renamed from: a */
        long f21837a = System.currentTimeMillis();

        /* renamed from: a */
        public boolean mo4374a() {
            return true;
        }

        @Override // com.xiaomi.push.C11139af.AbstractC11143b
        /* renamed from: b */
        public void mo2611b() {
        }

        C11270b() {
        }

        /* renamed from: b */
        final boolean m4375b() {
            return System.currentTimeMillis() - this.f21837a > 172800000;
        }
    }

    @NBSInstrumented
    /* renamed from: com.xiaomi.push.db$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C11271c extends C11270b {

        /* renamed from: a */
        int f21839a;

        /* renamed from: a */
        File f21841a;

        /* renamed from: a */
        String f21842a;

        /* renamed from: a */
        boolean f21843a;

        /* renamed from: b */
        String f21844b;

        /* renamed from: b */
        boolean f21845b;

        C11271c(String str, String str2, File file, boolean z) {
            super();
            this.f21842a = str;
            this.f21844b = str2;
            this.f21841a = file;
            this.f21845b = z;
        }

        @Override // com.xiaomi.push.C11266db.C11270b
        /* renamed from: a */
        public boolean mo4374a() {
            return C11169au.m4833d(C11266db.this.f21824a) || (this.f21845b && C11169au.m4849a(C11266db.this.f21824a));
        }

        /* renamed from: c */
        private boolean m4373c() {
            int i;
            SharedPreferences sharedPreferences = C11266db.this.f21824a.getSharedPreferences("log.timestamp", 0);
            String string = sharedPreferences.getString("log.requst", "");
            long currentTimeMillis = System.currentTimeMillis();
            try {
                JSONObject jSONObject = new JSONObject(string);
                currentTimeMillis = jSONObject.getLong("time");
                i = jSONObject.getInt("times");
            } catch (JSONException unused) {
                i = 0;
            }
            if (System.currentTimeMillis() - currentTimeMillis >= 86400000) {
                currentTimeMillis = System.currentTimeMillis();
                i = 0;
            } else if (i > 10) {
                return false;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("time", currentTimeMillis);
                jSONObject2.put("times", i + 1);
                sharedPreferences.edit().putString("log.requst", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2)).commit();
            } catch (JSONException e) {
                AbstractC11049b.m5270c("JSONException on put " + e.getMessage());
            }
            return true;
        }

        @Override // com.xiaomi.push.C11266db.C11270b, com.xiaomi.push.C11139af.AbstractC11143b
        /* renamed from: b */
        public void mo2611b() {
            try {
                if (m4373c()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("uid", C11571ax.m2624a());
                    hashMap.put("token", this.f21844b);
                    hashMap.put("net", C11169au.m4850a(C11266db.this.f21824a));
                    C11169au.m4838a(this.f21842a, hashMap, this.f21841a, "file");
                }
                this.f21843a = true;
            } catch (IOException unused) {
            }
        }

        @Override // com.xiaomi.push.C11139af.AbstractC11143b
        /* renamed from: c */
        public void mo2610c() {
            if (!this.f21843a) {
                this.f21839a++;
                if (this.f21839a < 3) {
                    C11266db.this.f21825a.add(this);
                }
            }
            if (this.f21843a || this.f21839a >= 3) {
                this.f21841a.delete();
            }
            C11266db.this.m4385a((1 << this.f21839a) * 1000);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.db$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C11269a extends C11270b {
        C11269a() {
            super();
        }

        @Override // com.xiaomi.push.C11266db.C11270b, com.xiaomi.push.C11139af.AbstractC11143b
        /* renamed from: b */
        public void mo2611b() {
            C11266db.this.m4378b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m4378b() {
        try {
            File file = new File(this.f21824a.getFilesDir() + "/.logcache");
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
            }
        } catch (NullPointerException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4385a(long j) {
        C11270b peek = this.f21825a.peek();
        if (peek == null || !peek.mo4374a()) {
            return;
        }
        m4377b(j);
    }

    /* renamed from: b */
    private void m4377b(long j) {
        if (this.f21825a.isEmpty()) {
            return;
        }
        C11390fy.m3743a(new C11139af.AbstractC11143b() { // from class: com.xiaomi.push.db.2

            /* renamed from: a */
            C11139af.AbstractC11143b f21834a;

            @Override // com.xiaomi.push.C11139af.AbstractC11143b
            /* renamed from: b */
            public void mo2611b() {
                C11270b c11270b = (C11270b) C11266db.this.f21825a.peek();
                if (c11270b == null || !c11270b.mo4374a()) {
                    return;
                }
                if (C11266db.this.f21825a.remove(c11270b)) {
                    this.f21834a = c11270b;
                }
                C11139af.AbstractC11143b abstractC11143b = this.f21834a;
                if (abstractC11143b != null) {
                    abstractC11143b.mo2611b();
                }
            }

            @Override // com.xiaomi.push.C11139af.AbstractC11143b
            /* renamed from: c */
            public void mo2610c() {
                C11139af.AbstractC11143b abstractC11143b = this.f21834a;
                if (abstractC11143b != null) {
                    abstractC11143b.mo2610c();
                }
            }
        }, j);
    }

    /* renamed from: c */
    private void m4376c() {
        while (!this.f21825a.isEmpty()) {
            C11270b peek = this.f21825a.peek();
            if (peek != null) {
                if (!peek.m4375b() && this.f21825a.size() <= 6) {
                    return;
                }
                AbstractC11049b.m5270c("remove Expired task");
                this.f21825a.remove(peek);
            }
        }
    }
}

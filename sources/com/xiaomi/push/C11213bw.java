package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.service.C11537ah;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.xiaomi.push.bw */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11213bw {

    /* renamed from: a */
    private static volatile C11213bw f21638a;

    /* renamed from: a */
    private Context f21639a;

    /* renamed from: a */
    private AbstractC11212bv f21640a;

    /* renamed from: a */
    private final HashMap<String, AbstractC11211bu> f21642a = new HashMap<>();

    /* renamed from: a */
    private ThreadPoolExecutor f21643a = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: a */
    private final ArrayList<AbstractRunnableC11215a> f21641a = new ArrayList<>();

    private C11213bw(Context context) {
        this.f21639a = context;
    }

    /* renamed from: a */
    public static C11213bw m4683a(Context context) {
        if (f21638a == null) {
            synchronized (C11213bw.class) {
                if (f21638a == null) {
                    f21638a = new C11213bw(context);
                }
            }
        }
        return f21638a;
    }

    /* renamed from: a */
    private void m4684a() {
        C11134ae.m4937a(this.f21639a).m4924b(new C11134ae.AbstractRunnableC11137a() { // from class: com.xiaomi.push.bw.1
            @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
            /* renamed from: a */
            public String mo2289a() {
                return "100957";
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (C11213bw.this.f21641a) {
                    if (C11213bw.this.f21641a.size() > 0) {
                        if (C11213bw.this.f21641a.size() > 1) {
                            C11213bw.this.m4677a(C11213bw.this.f21641a);
                        } else {
                            C11213bw.this.m4676b((AbstractRunnableC11215a) C11213bw.this.f21641a.get(0));
                        }
                        C11213bw.this.f21641a.clear();
                        System.gc();
                    }
                }
            }
        }, C11537ah.m2715a(this.f21639a).m2719a(EnumC11409gk.StatDataProcessFrequency.m3637a(), 5));
    }

    /* renamed from: a */
    public void m4682a(AbstractRunnableC11215a abstractRunnableC11215a) {
        AbstractC11211bu abstractC11211bu;
        if (abstractRunnableC11215a == null) {
            return;
        }
        if (this.f21640a == null) {
            throw new IllegalStateException("should exec init method first!");
        }
        String m4674a = abstractRunnableC11215a.m4674a();
        synchronized (this.f21642a) {
            abstractC11211bu = this.f21642a.get(m4674a);
            if (abstractC11211bu == null) {
                abstractC11211bu = this.f21640a.m4685a(this.f21639a, m4674a);
                this.f21642a.put(m4674a, abstractC11211bu);
            }
        }
        if (this.f21643a.isShutdown()) {
            return;
        }
        abstractRunnableC11215a.m4671a(abstractC11211bu, this.f21639a);
        synchronized (this.f21641a) {
            this.f21641a.add(abstractRunnableC11215a);
            m4684a();
        }
    }

    /* renamed from: b */
    public void m4676b(AbstractRunnableC11215a abstractRunnableC11215a) {
        AbstractC11211bu abstractC11211bu;
        if (abstractRunnableC11215a == null) {
            return;
        }
        if (this.f21640a == null) {
            throw new IllegalStateException("should exec init method first!");
        }
        String m4674a = abstractRunnableC11215a.m4674a();
        synchronized (this.f21642a) {
            abstractC11211bu = this.f21642a.get(m4674a);
            if (abstractC11211bu == null) {
                abstractC11211bu = this.f21640a.m4685a(this.f21639a, m4674a);
                this.f21642a.put(m4674a, abstractC11211bu);
            }
        }
        if (this.f21643a.isShutdown()) {
            return;
        }
        abstractRunnableC11215a.m4671a(abstractC11211bu, this.f21639a);
        m4680a((Runnable) abstractRunnableC11215a);
    }

    /* renamed from: a */
    public void m4680a(Runnable runnable) {
        if (this.f21643a.isShutdown()) {
            return;
        }
        this.f21643a.execute(runnable);
    }

    /* renamed from: a */
    public String m4678a(String str) {
        return m4679a(str).m4686a();
    }

    /* renamed from: a */
    public void m4677a(ArrayList<AbstractRunnableC11215a> arrayList) {
        if (this.f21640a == null) {
            throw new IllegalStateException("should exec setDbHelperFactory method first!");
        }
        HashMap hashMap = new HashMap();
        if (this.f21643a.isShutdown()) {
            return;
        }
        Iterator<AbstractRunnableC11215a> it = arrayList.iterator();
        while (it.hasNext()) {
            AbstractRunnableC11215a next = it.next();
            if (next.m4673a()) {
                next.m4671a(m4679a(next.m4674a()), this.f21639a);
            }
            ArrayList arrayList2 = (ArrayList) hashMap.get(next.m4674a());
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                hashMap.put(next.m4674a(), arrayList2);
            }
            arrayList2.add(next);
        }
        for (String str : hashMap.keySet()) {
            ArrayList arrayList3 = (ArrayList) hashMap.get(str);
            if (arrayList3 != null && arrayList3.size() > 0) {
                C11218c c11218c = new C11218c(str, arrayList3);
                c11218c.m4671a(((AbstractRunnableC11215a) arrayList3.get(0)).f21646a, this.f21639a);
                this.f21643a.execute(c11218c);
            }
        }
    }

    /* renamed from: a */
    private AbstractC11211bu m4679a(String str) {
        AbstractC11211bu abstractC11211bu = this.f21642a.get(str);
        if (abstractC11211bu == null) {
            synchronized (this.f21642a) {
                if (abstractC11211bu == null) {
                    abstractC11211bu = this.f21640a.m4685a(this.f21639a, str);
                    this.f21642a.put(str, abstractC11211bu);
                }
            }
        }
        return abstractC11211bu;
    }

    /* renamed from: com.xiaomi.push.bw$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11218c extends AbstractRunnableC11215a {

        /* renamed from: a */
        private ArrayList<AbstractRunnableC11215a> f21662a;

        public C11218c(String str, ArrayList<AbstractRunnableC11215a> arrayList) {
            super(str);
            this.f21662a = new ArrayList<>();
            this.f21662a.addAll(arrayList);
        }

        @Override // com.xiaomi.push.C11213bw.AbstractRunnableC11215a
        /* renamed from: a */
        public void mo4664a(Context context, SQLiteDatabase sQLiteDatabase) {
            Iterator<AbstractRunnableC11215a> it = this.f21662a.iterator();
            while (it.hasNext()) {
                AbstractRunnableC11215a next = it.next();
                if (next != null) {
                    next.mo4664a(context, sQLiteDatabase);
                }
            }
        }

        @Override // com.xiaomi.push.C11213bw.AbstractRunnableC11215a
        /* renamed from: a */
        public final void mo4665a(Context context) {
            super.mo4665a(context);
            Iterator<AbstractRunnableC11215a> it = this.f21662a.iterator();
            while (it.hasNext()) {
                AbstractRunnableC11215a next = it.next();
                if (next != null) {
                    next.mo4665a(context);
                }
            }
        }
    }

    /* renamed from: com.xiaomi.push.bw$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractRunnableC11215a implements Runnable {

        /* renamed from: a */
        private AbstractRunnableC11215a f21647a;

        /* renamed from: a */
        private String f21648a;

        /* renamed from: a */
        private WeakReference<Context> f21649a;

        /* renamed from: b */
        protected String f21651b;

        /* renamed from: a */
        protected AbstractC11211bu f21646a = null;

        /* renamed from: a */
        private Random f21650a = new Random();

        /* renamed from: a */
        private int f21645a = 0;

        /* renamed from: a */
        public Object mo4675a() {
            return null;
        }

        /* renamed from: a */
        public abstract void mo4664a(Context context, SQLiteDatabase sQLiteDatabase);

        /* renamed from: b */
        public void m4669b(Context context) {
        }

        public AbstractRunnableC11215a(String str) {
            this.f21648a = str;
        }

        /* renamed from: a */
        void m4671a(AbstractC11211bu abstractC11211bu, Context context) {
            this.f21646a = abstractC11211bu;
            this.f21651b = this.f21646a.m4686a();
            this.f21649a = new WeakReference<>(context);
        }

        /* renamed from: a */
        public boolean m4673a() {
            return this.f21646a == null || TextUtils.isEmpty(this.f21651b) || this.f21649a == null;
        }

        /* renamed from: a */
        public void m4670a(AbstractRunnableC11215a abstractRunnableC11215a) {
            this.f21647a = abstractRunnableC11215a;
        }

        /* renamed from: a */
        public void mo4672a(Context context, Object obj) {
            C11213bw.m4683a(context).m4682a(this);
        }

        /* renamed from: a */
        public String m4674a() {
            return this.f21648a;
        }

        @Override // java.lang.Runnable
        public final void run() {
            final Context context;
            WeakReference<Context> weakReference = this.f21649a;
            if (weakReference == null || (context = weakReference.get()) == null || context.getFilesDir() == null || this.f21646a == null || TextUtils.isEmpty(this.f21648a)) {
                return;
            }
            File file = new File(this.f21648a);
            AbstractRunnableC11644u.m2278a(context, new File(file.getParentFile(), C11183ba.m4759b(file.getAbsolutePath())), new Runnable() { // from class: com.xiaomi.push.bw.a.1
                @Override // java.lang.Runnable
                public void run() {
                    SQLiteDatabase sQLiteDatabase = null;
                    try {
                        try {
                            sQLiteDatabase = AbstractRunnableC11215a.this.mo4668a();
                            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                                sQLiteDatabase.beginTransaction();
                                AbstractRunnableC11215a.this.mo4664a(context, sQLiteDatabase);
                                sQLiteDatabase.setTransactionSuccessful();
                            }
                            if (sQLiteDatabase != null) {
                                try {
                                    sQLiteDatabase.endTransaction();
                                } catch (Exception e) {
                                    e = e;
                                    AbstractC11049b.m5276a(e);
                                    AbstractRunnableC11215a.this.mo4665a(context);
                                }
                            }
                            if (AbstractRunnableC11215a.this.f21646a != null) {
                                AbstractRunnableC11215a.this.f21646a.close();
                            }
                        } catch (Exception e2) {
                            AbstractC11049b.m5276a(e2);
                            if (sQLiteDatabase != null) {
                                try {
                                    sQLiteDatabase.endTransaction();
                                } catch (Exception e3) {
                                    e = e3;
                                    AbstractC11049b.m5276a(e);
                                    AbstractRunnableC11215a.this.mo4665a(context);
                                }
                            }
                            if (AbstractRunnableC11215a.this.f21646a != null) {
                                AbstractRunnableC11215a.this.f21646a.close();
                            }
                        }
                        AbstractRunnableC11215a.this.mo4665a(context);
                    } catch (Throwable th) {
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                            } catch (Exception e4) {
                                AbstractC11049b.m5276a(e4);
                                AbstractRunnableC11215a.this.mo4665a(context);
                                throw th;
                            }
                        }
                        if (AbstractRunnableC11215a.this.f21646a != null) {
                            AbstractRunnableC11215a.this.f21646a.close();
                        }
                        AbstractRunnableC11215a.this.mo4665a(context);
                        throw th;
                    }
                }
            });
        }

        /* renamed from: a */
        public SQLiteDatabase mo4668a() {
            return this.f21646a.getWritableDatabase();
        }

        /* renamed from: a */
        void mo4665a(Context context) {
            AbstractRunnableC11215a abstractRunnableC11215a = this.f21647a;
            if (abstractRunnableC11215a != null) {
                abstractRunnableC11215a.mo4672a(context, mo4675a());
            }
            m4669b(context);
        }
    }

    @NBSInstrumented
    /* renamed from: com.xiaomi.push.bw$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractC11217b<T> extends AbstractRunnableC11215a {

        /* renamed from: a */
        private int f21654a;

        /* renamed from: a */
        private String f21655a;

        /* renamed from: a */
        private List<String> f21656a;

        /* renamed from: a */
        private String[] f21657a;

        /* renamed from: b */
        private List<T> f21658b;

        /* renamed from: c */
        private String f21659c;

        /* renamed from: d */
        private String f21660d;

        /* renamed from: e */
        private String f21661e;

        /* renamed from: a */
        public abstract T mo4667a(Context context, Cursor cursor);

        /* renamed from: a */
        public abstract void mo4666a(Context context, List<T> list);

        public AbstractC11217b(String str, List<String> list, String str2, String[] strArr, String str3, String str4, String str5, int i) {
            super(str);
            this.f21658b = new ArrayList();
            this.f21656a = list;
            this.f21655a = str2;
            this.f21657a = strArr;
            this.f21659c = str3;
            this.f21660d = str4;
            this.f21661e = str5;
            this.f21654a = i;
        }

        @Override // com.xiaomi.push.C11213bw.AbstractRunnableC11215a
        /* renamed from: a */
        public void mo4664a(Context context, SQLiteDatabase sQLiteDatabase) {
            String[] strArr;
            this.f21658b.clear();
            List<String> list = this.f21656a;
            if (list == null || list.size() <= 0) {
                strArr = null;
            } else {
                String[] strArr2 = new String[this.f21656a.size()];
                this.f21656a.toArray(strArr2);
                strArr = strArr2;
            }
            int i = this.f21654a;
            String valueOf = i > 0 ? String.valueOf(i) : null;
            String str = this.f21651b;
            String str2 = this.f21655a;
            String[] strArr3 = this.f21657a;
            String str3 = this.f21659c;
            String str4 = this.f21660d;
            String str5 = this.f21661e;
            Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query(str, strArr, str2, strArr3, str3, str4, str5, valueOf) : NBSSQLiteInstrumentation.query(sQLiteDatabase, str, strArr, str2, strArr3, str3, str4, str5, valueOf);
            if (query != null && query.moveToFirst()) {
                do {
                    T mo4667a = mo4667a(context, query);
                    if (mo4667a != null) {
                        this.f21658b.add(mo4667a);
                    }
                } while (query.moveToNext());
                query.close();
            }
            mo4666a(context, (List) this.f21658b);
        }

        @Override // com.xiaomi.push.C11213bw.AbstractRunnableC11215a
        /* renamed from: a */
        public SQLiteDatabase mo4668a() {
            return this.f21646a.getReadableDatabase();
        }
    }

    @NBSInstrumented
    /* renamed from: com.xiaomi.push.bw$e */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11220e extends AbstractRunnableC11215a {

        /* renamed from: a */
        private ContentValues f21665a;

        public C11220e(String str, ContentValues contentValues) {
            super(str);
            this.f21665a = contentValues;
        }

        @Override // com.xiaomi.push.C11213bw.AbstractRunnableC11215a
        /* renamed from: a */
        public void mo4664a(Context context, SQLiteDatabase sQLiteDatabase) {
            String str = this.f21651b;
            ContentValues contentValues = this.f21665a;
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.insert(sQLiteDatabase, str, null, contentValues);
            } else {
                sQLiteDatabase.insert(str, null, contentValues);
            }
        }
    }

    @NBSInstrumented
    /* renamed from: com.xiaomi.push.bw$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11219d extends AbstractRunnableC11215a {

        /* renamed from: a */
        private String f21663a;

        /* renamed from: a */
        protected String[] f21664a;

        public C11219d(String str, String str2, String[] strArr) {
            super(str);
            this.f21663a = str2;
            this.f21664a = strArr;
        }

        @Override // com.xiaomi.push.C11213bw.AbstractRunnableC11215a
        /* renamed from: a */
        public void mo4664a(Context context, SQLiteDatabase sQLiteDatabase) {
            String str = this.f21651b;
            String str2 = this.f21663a;
            String[] strArr = this.f21664a;
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.delete(sQLiteDatabase, str, str2, strArr);
            } else {
                sQLiteDatabase.delete(str, str2, strArr);
            }
        }
    }
}

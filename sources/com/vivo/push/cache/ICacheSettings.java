package com.vivo.push.cache;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.DebugUtil;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.SharePreferenceManager;
import com.vivo.push.util.SystemCache;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.vivo.push.cache.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class ICacheSettings<T> {

    /* renamed from: a */
    protected static final Object f20930a = new Object();

    /* renamed from: b */
    protected List<T> f20931b = new ArrayList();

    /* renamed from: c */
    protected Context f20932c;

    /* renamed from: d */
    private byte[] f20933d;

    /* renamed from: e */
    private byte[] f20934e;

    /* renamed from: a */
    protected abstract String mo5740a();

    /* renamed from: a */
    protected abstract List<T> mo5739a(String str);

    /* renamed from: b */
    abstract String mo5738b(String str) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public ICacheSettings(Context context) {
        this.f20932c = ContextDelegate.getContext(context);
        SharePreferenceManager m5455b = SharePreferenceManager.m5455b();
        m5455b.m5456a(this.f20932c);
        this.f20933d = m5455b.m5453c();
        this.f20934e = m5455b.m5452d();
        m5746c();
    }

    /* renamed from: c */
    public final void m5746c() {
        synchronized (f20930a) {
            DebugUtil.m5390a(mo5740a());
            this.f20931b.clear();
            m5745c(m5747b());
        }
    }

    /* renamed from: c */
    private void m5745c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.m5341d("CacheSettings", "ClientManager init " + mo5740a() + " strApps empty.");
        } else if (str.length() > 10000) {
            LogUtil.m5341d("CacheSettings", "sync " + mo5740a() + " strApps lenght too large");
            m5744d();
        } else {
            try {
                LogUtil.m5341d("CacheSettings", "ClientManager init " + mo5740a() + " strApps : " + str);
                List<T> mo5739a = mo5739a(mo5738b(str));
                if (mo5739a != null) {
                    this.f20931b.addAll(mo5739a);
                }
            } catch (Exception e) {
                m5744d();
                LogUtil.m5341d("CacheSettings", LogUtil.m5351a(e));
            }
        }
    }

    /* renamed from: b */
    private String m5747b() {
        return SystemCache.m5449b(this.f20932c).mo5408a(mo5740a(), null);
    }

    /* renamed from: d */
    private void m5743d(String str) {
        SystemCache.m5449b(this.f20932c).mo5407b(mo5740a(), str);
    }

    /* renamed from: d */
    public final void m5744d() {
        synchronized (f20930a) {
            this.f20931b.clear();
            m5743d("");
            LogUtil.m5341d("CacheSettings", "clear " + mo5740a() + " strApps");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public final byte[] m5742e() {
        byte[] bArr = this.f20933d;
        return (bArr == null || bArr.length <= 0) ? SharePreferenceManager.m5455b().m5453c() : bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public final byte[] m5741f() {
        byte[] bArr = this.f20934e;
        return (bArr == null || bArr.length <= 0) ? SharePreferenceManager.m5455b().m5452d() : bArr;
    }
}

package com.bytedance.applog;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.bytedance.applog.l1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3620l1 extends AbstractC3635n1 {

    /* renamed from: c */
    public final AccountManager f8544c;

    /* renamed from: d */
    public Account f8545d;

    /* renamed from: e */
    public final ConcurrentHashMap<String, String> f8546e = new ConcurrentHashMap<>();

    /* renamed from: com.bytedance.applog.l1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC3621a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ Account f8547a;

        public RunnableC3621a(Account account) {
            this.f8547a = account;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (C3620l1.this.f8546e != null && C3620l1.this.f8546e.size() > 0 && C3620l1.this.f8544c != null) {
                    for (Map.Entry<String, String> entry : C3620l1.this.f8546e.entrySet()) {
                        if (entry != null) {
                            C3620l1.this.f8544c.setUserData(this.f8547a, entry.getKey(), entry.getValue());
                        }
                    }
                    C3620l1.this.f8546e.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public C3620l1(Context context) {
        this.f8544c = AccountManager.get(context);
    }

    /* renamed from: a */
    public void m17246a(Account account) {
        if (account != null) {
            this.f8545d = account;
            ConcurrentHashMap<String, String> concurrentHashMap = this.f8546e;
            if (concurrentHashMap == null || concurrentHashMap.size() <= 0) {
                return;
            }
            this.f8608b.post(new RunnableC3621a(account));
        }
    }

    @Override // com.bytedance.applog.AbstractC3635n1
    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public void mo17068a(String str) {
        ConcurrentHashMap<String, String> concurrentHashMap = this.f8546e;
        if (concurrentHashMap != null && concurrentHashMap.containsKey(str)) {
            this.f8546e.remove(str);
        }
        try {
            if (this.f8545d != null && this.f8544c != null) {
                this.f8544c.setUserData(this.f8545d, str, null);
            }
        } catch (Exception unused) {
        }
        AbstractC3635n1 abstractC3635n1 = this.f8607a;
        if (abstractC3635n1 != null) {
            abstractC3635n1.mo17068a(str);
        }
    }

    @Override // com.bytedance.applog.AbstractC3635n1
    /* renamed from: a */
    public void mo17067a(String str, String str2) {
        Account account = this.f8545d;
        if (account == null) {
            this.f8546e.put(str, str2);
        } else if (str == null || str2 == null) {
        } else {
            try {
                this.f8544c.setUserData(account, str, str2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.bytedance.applog.AbstractC3635n1
    /* renamed from: a */
    public void mo17066a(String str, String[] strArr) {
        if (str == null || strArr == null) {
            return;
        }
        mo17067a(str, TextUtils.join("\n", strArr));
    }

    @Override // com.bytedance.applog.AbstractC3635n1
    /* renamed from: b */
    public String mo17065b(String str) {
        Account account = this.f8545d;
        if (account == null) {
            return this.f8546e.get(str);
        }
        try {
            return this.f8544c.getUserData(account, str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.bytedance.applog.AbstractC3635n1
    /* renamed from: c */
    public String[] mo17064c(String str) {
        String mo17065b = mo17065b(str);
        if (TextUtils.isEmpty(mo17065b)) {
            return null;
        }
        return mo17065b.split("\n");
    }
}

package com.alipay.android.phone.mrpc.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1947o extends AbstractC1952t {

    /* renamed from: b */
    private String f3431b;

    /* renamed from: c */
    private byte[] f3432c;

    /* renamed from: g */
    private boolean f3436g;

    /* renamed from: e */
    private ArrayList<Header> f3434e = new ArrayList<>();

    /* renamed from: f */
    private Map<String, String> f3435f = new HashMap();

    /* renamed from: d */
    private String f3433d = "application/x-www-form-urlencoded";

    public C1947o(String str) {
        this.f3431b = str;
    }

    /* renamed from: a */
    public final String m21101a() {
        return this.f3431b;
    }

    /* renamed from: a */
    public final void m21100a(String str) {
        this.f3433d = str;
    }

    /* renamed from: a */
    public final void m21099a(String str, String str2) {
        if (this.f3435f == null) {
            this.f3435f = new HashMap();
        }
        this.f3435f.put(str, str2);
    }

    /* renamed from: a */
    public final void m21098a(Header header) {
        this.f3434e.add(header);
    }

    /* renamed from: a */
    public final void m21097a(boolean z) {
        this.f3436g = z;
    }

    /* renamed from: a */
    public final void m21096a(byte[] bArr) {
        this.f3432c = bArr;
    }

    /* renamed from: b */
    public final String m21094b(String str) {
        Map<String, String> map = this.f3435f;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    /* renamed from: b */
    public final byte[] m21095b() {
        return this.f3432c;
    }

    /* renamed from: c */
    public final String m21093c() {
        return this.f3433d;
    }

    /* renamed from: d */
    public final ArrayList<Header> m21092d() {
        return this.f3434e;
    }

    /* renamed from: e */
    public final boolean m21091e() {
        return this.f3436g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            C1947o c1947o = (C1947o) obj;
            byte[] bArr = this.f3432c;
            if (bArr == null) {
                if (c1947o.f3432c != null) {
                    return false;
                }
            } else if (!bArr.equals(c1947o.f3432c)) {
                return false;
            }
            String str = this.f3431b;
            if (str == null) {
                if (c1947o.f3431b != null) {
                    return false;
                }
            } else if (!str.equals(c1947o.f3431b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        Map<String, String> map = this.f3435f;
        int hashCode = ((map == null || !map.containsKey("id")) ? 1 : this.f3435f.get("id").hashCode() + 31) * 31;
        String str = this.f3431b;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", this.f3431b, this.f3434e);
    }
}

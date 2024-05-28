package org.json.alipay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.json.alipay.a */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class C13435a {

    /* renamed from: a */
    private ArrayList f27413a;

    public C13435a() {
        this.f27413a = new ArrayList();
    }

    public C13435a(Object obj) {
        this();
        if (!obj.getClass().isArray()) {
            throw new JSONException("JSONArray initial value should be a string or collection or array.");
        }
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f27413a.add(Array.get(obj, i));
        }
    }

    public C13435a(String str) {
        this(new C13438c(str));
    }

    public C13435a(Collection collection) {
        this.f27413a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public C13435a(C13438c c13438c) {
        this();
        char c;
        ArrayList arrayList;
        Object m212d;
        char m213c = c13438c.m213c();
        if (m213c == '[') {
            c = ']';
        } else if (m213c != '(') {
            throw c13438c.m215a("A JSONArray text must start with '['");
        } else {
            c = ')';
        }
        if (c13438c.m213c() == ']') {
            return;
        }
        do {
            c13438c.m217a();
            if (c13438c.m213c() == ',') {
                c13438c.m217a();
                arrayList = this.f27413a;
                m212d = null;
            } else {
                c13438c.m217a();
                arrayList = this.f27413a;
                m212d = c13438c.m212d();
            }
            arrayList.add(m212d);
            char m213c2 = c13438c.m213c();
            if (m213c2 != ')') {
                if (m213c2 != ',' && m213c2 != ';') {
                    if (m213c2 != ']') {
                        throw c13438c.m215a("Expected a ',' or ']'");
                    }
                }
            }
            if (c == m213c2) {
                return;
            }
            throw c13438c.m215a("Expected a '" + new Character(c) + "'");
        } while (c13438c.m213c() != ']');
    }

    /* renamed from: a */
    private String m224a(String str) {
        int size = this.f27413a.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(C13436b.m222a(this.f27413a.get(i)));
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public final int m226a() {
        return this.f27413a.size();
    }

    /* renamed from: a */
    public final Object m225a(int i) {
        Object obj = (i < 0 || i >= this.f27413a.size()) ? null : this.f27413a.get(i);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public String toString() {
        try {
            return "[" + m224a(",") + ']';
        } catch (Exception unused) {
            return null;
        }
    }
}

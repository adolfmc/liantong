package com.baidu.platform.comapi.util;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class JsonBuilder {

    /* renamed from: a */
    private StringBuilder f8034a = new StringBuilder();

    /* renamed from: b */
    private boolean f8035b = false;

    public void reset() {
        this.f8034a.setLength(0);
        this.f8035b = false;
    }

    public JsonBuilder object() {
        m17706a();
        this.f8034a.append("{");
        m17705b();
        return this;
    }

    public JsonBuilder endObject() {
        this.f8034a.append("}");
        m17704c();
        return this;
    }

    public JsonBuilder putStringValue(String str, String str2) {
        if (str2 != null) {
            key(str).value(str2);
        }
        return this;
    }

    public JsonBuilder putObjectValue(String str, String str2) {
        if (str2 != null) {
            key(str).objectValue(str2);
        }
        return this;
    }

    public JsonBuilder key(String str) {
        m17706a();
        this.f8034a.append(JSONObject.quote(str));
        this.f8034a.append(":");
        m17705b();
        return this;
    }

    public JsonBuilder value(boolean z) {
        m17706a();
        this.f8034a.append(z);
        m17704c();
        return this;
    }

    public JsonBuilder value(int i) {
        m17706a();
        this.f8034a.append(i);
        m17704c();
        return this;
    }

    public JsonBuilder value(long j) {
        m17706a();
        this.f8034a.append(j);
        m17704c();
        return this;
    }

    public JsonBuilder value(double d) {
        m17706a();
        this.f8034a.append(String.format("%f", Double.valueOf(d)));
        m17704c();
        return this;
    }

    public JsonBuilder value(String str) {
        m17706a();
        this.f8034a.append(JSONObject.quote(str));
        m17704c();
        return this;
    }

    public JsonBuilder valueDirect(String str) {
        m17706a();
        this.f8034a.append(str);
        m17704c();
        return this;
    }

    public JsonBuilder value(Object obj) {
        if (obj instanceof Number) {
            Number number = (Number) obj;
            if (obj instanceof Byte) {
                return value((int) number.byteValue());
            }
            if (obj instanceof Short) {
                return value((int) number.shortValue());
            }
            if (obj instanceof Integer) {
                return value(number.intValue());
            }
            if (obj instanceof Long) {
                return value(number.longValue());
            }
            if (obj instanceof Float) {
                return value(number.floatValue());
            }
            if (obj instanceof Double) {
                return value(number.doubleValue());
            }
        }
        return value(obj.toString());
    }

    public JsonBuilder objectValue(String str) {
        m17706a();
        this.f8034a.append(str);
        m17704c();
        return this;
    }

    public JsonBuilder arrayValue() {
        m17706a();
        this.f8034a.append("[");
        m17705b();
        return this;
    }

    public JsonBuilder endArrayValue() {
        this.f8034a.append("]");
        m17704c();
        return this;
    }

    /* renamed from: a */
    private void m17706a() {
        if (this.f8035b) {
            this.f8034a.append(",");
        }
    }

    /* renamed from: b */
    private void m17705b() {
        this.f8035b = false;
    }

    /* renamed from: c */
    private void m17704c() {
        this.f8035b = true;
    }

    public String getJson() {
        return this.f8034a.toString();
    }

    public String toString() {
        return getJson();
    }
}

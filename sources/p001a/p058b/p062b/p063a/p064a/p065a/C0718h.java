package p001a.p058b.p062b.p063a.p064a.p065a;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;

/* renamed from: a.b.b.a.a.a.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0718h extends AbstractC0747d {

    /* renamed from: b */
    public boolean f2178b;

    /* renamed from: c */
    public long f2179c;

    /* renamed from: d */
    public String f2180d;

    /* renamed from: e */
    public int f2181e;

    /* renamed from: f */
    public StackTraceElement[] f2182f;

    /* renamed from: g */
    public String f2183g;

    public C0718h() {
    }

    public C0718h(Thread thread, StackTraceElement[] stackTraceElementArr) {
        this.f2178b = false;
        this.f2179c = thread.getId();
        this.f2180d = thread.getName();
        this.f2181e = thread.getPriority();
        this.f2182f = stackTraceElementArr;
        this.f2183g = thread.getState().toString();
    }

    public C0718h(Throwable th) {
        this.f2178b = true;
        this.f2179c = Thread.currentThread().getId();
        this.f2180d = Thread.currentThread().getName();
        this.f2181e = Thread.currentThread().getPriority();
        this.f2182f = th.getStackTrace();
        this.f2183g = Thread.currentThread().getState().toString();
    }

    /* renamed from: a */
    public static C0718h m22327a(JSONObject jSONObject) {
        C0718h c0718h = new C0718h();
        try {
            c0718h.f2178b = jSONObject.getBoolean("crashed");
            c0718h.f2183g = jSONObject.getString("state");
            c0718h.f2179c = jSONObject.getLong("threadNumber");
            c0718h.f2180d = jSONObject.getString("threadId");
            c0718h.f2181e = jSONObject.getInt("priority");
            c0718h.f2182f = m22328a(jSONObject.getJSONArray("stack"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return c0718h;
    }

    /* renamed from: a */
    public static StackTraceElement[] m22328a(JSONArray jSONArray) {
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[jSONArray.length()];
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                StackTraceElement stackTraceElement = new StackTraceElement(jSONArray.getJSONObject(i).getString("className"), jSONArray.getJSONObject(i).getString("methodName"), jSONArray.getJSONObject(i).optString("fileName") != null ? jSONArray.getJSONObject(i).optString("fileName") : "unknown", jSONArray.getJSONObject(i).getInt("lineNumber"));
                int i2 = i + 1;
                stackTraceElementArr[i] = stackTraceElement;
                i = i2 + 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return stackTraceElementArr;
    }

    /* renamed from: a */
    public final JSONArray m22329a() {
        StackTraceElement[] stackTraceElementArr;
        JSONArray jSONArray = new JSONArray();
        for (StackTraceElement stackTraceElement : this.f2182f) {
            try {
                if (stackTraceElement != null) {
                    JSONObject jSONObject = new JSONObject();
                    if (stackTraceElement.getFileName() != null) {
                        jSONObject.put("fileName", stackTraceElement.getFileName());
                    }
                    jSONObject.put("className", stackTraceElement.getClassName());
                    jSONObject.put("methodName", stackTraceElement.getMethodName());
                    jSONObject.put("lineNumber", Integer.valueOf(stackTraceElement.getLineNumber()));
                    jSONArray.put(jSONObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("crashed", Boolean.valueOf(this.f2178b));
            jSONObject.put("state", this.f2183g);
            jSONObject.put("threadNumber", Long.valueOf(this.f2179c));
            jSONObject.put("threadId", this.f2180d);
            jSONObject.put("priority", Integer.valueOf(this.f2181e));
            jSONObject.put("stack", m22329a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}

package p001a.p058b.p062b.p063a.p064a.p076h.p077a;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import com.baidu.uaq.agent.android.trace.InterfaceC3322a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0730g;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.h.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0763a implements InterfaceC3322a {

    /* renamed from: a */
    public static final InterfaceC3321a f2362a = C0749a.f2299a;

    /* renamed from: b */
    public static final Map<String, JSONObject> f2363b = new HashMap();

    /* renamed from: c */
    public Map<String, JSONArray> f2364c = new HashMap();

    /* renamed from: d */
    public Map<String, JSONArray> f2365d = new HashMap();

    @Override // com.baidu.uaq.agent.android.trace.InterfaceC3322a
    /* renamed from: b */
    public JSONObject mo17425b(List<C0730g> list) {
        Iterator<C0730g> it = list.iterator();
        JSONObject jSONObject = new JSONObject();
        while (it.hasNext()) {
            JSONObject mo17432az = it.next().mo17432az();
            if (mo17432az != null) {
                Iterator<String> keys = mo17432az.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next.equals("Trace")) {
                        try {
                            Object obj = mo17432az.get("Trace");
                            String str = null;
                            JSONObject jSONObject2 = obj instanceof JSONObject ? (JSONObject) obj : null;
                            if (jSONObject2 == null || jSONObject2.isNull("TraceId") || jSONObject2.getString("TraceId").isEmpty()) {
                                f2362a.warning("TraceId should not be empty");
                            } else {
                                String string = jSONObject2.getString("TraceId");
                                if (!jSONObject2.isNull("CommData")) {
                                    try {
                                        Object obj2 = jSONObject2.get("CommData");
                                        if (obj2 instanceof JSONObject) {
                                            JSONObject jSONObject3 = (JSONObject) obj2;
                                            if (!jSONObject3.isNull("playID") && !jSONObject3.getString("playID").isEmpty()) {
                                                str = (String) jSONObject3.get("playID");
                                            }
                                        }
                                    } catch (JSONException e) {
                                        f2362a.mo17426a("Caught error while getPlayID: ", e);
                                        C0735a.m22302a(e);
                                    }
                                }
                                String str2 = string + str;
                                Object remove = jSONObject2.remove("Event");
                                if (this.f2364c.containsKey(str2)) {
                                    this.f2364c.get(str2).put(remove);
                                } else {
                                    this.f2364c.put(str2, new JSONArray().put(remove));
                                    f2363b.put(str2, jSONObject2);
                                }
                            }
                        } catch (JSONException e2) {
                            f2362a.mo17426a("Caught error while LssTraceParser parse: ", e2);
                            C0735a.m22302a(e2);
                        }
                    } else {
                        new JSONArray();
                        try {
                            if (this.f2365d.containsKey(next)) {
                                JSONArray jSONArray = this.f2365d.get(next);
                                Object obj3 = mo17432az.get(next);
                                if (obj3 instanceof JSONObject) {
                                    jSONArray.put(obj3);
                                }
                            } else {
                                Object obj4 = mo17432az.get(next);
                                if (obj4 instanceof JSONObject) {
                                    this.f2365d.put(next, new JSONArray().put(obj4));
                                }
                            }
                        } catch (JSONException e3) {
                            f2362a.mo17426a("Caught error while LssTraceParser parse: ", e3);
                            C0735a.m22302a(e3);
                        }
                    }
                    keys.remove();
                }
                it.remove();
            }
        }
        JSONArray jSONArray2 = new JSONArray();
        try {
            for (Map.Entry<String, JSONObject> entry : f2363b.entrySet()) {
                JSONObject value = entry.getValue();
                value.put("Events", this.f2364c.get(entry.getKey()));
                jSONArray2.put(value);
            }
            jSONObject.put("Traces", jSONArray2);
        } catch (JSONException e4) {
            f2362a.mo17426a("Caught error while LssTraceParser parse: ", e4);
            C0735a.m22302a(e4);
        }
        for (Map.Entry<String, JSONArray> entry2 : this.f2365d.entrySet()) {
            try {
                jSONObject.put(entry2.getKey(), this.f2365d.get(entry2.getKey()));
            } catch (JSONException e5) {
                f2362a.mo17426a("Caught error while LssTraceParser parse: ", e5);
                C0735a.m22302a(e5);
            }
        }
        Map<String, JSONObject> map = f2363b;
        if (map != null) {
            map.clear();
        }
        Map<String, JSONArray> map2 = this.f2364c;
        if (map2 != null) {
            map2.clear();
        }
        Map<String, JSONArray> map3 = this.f2365d;
        if (map3 != null) {
            map3.clear();
        }
        return jSONObject;
    }
}

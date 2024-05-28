package p001a.p058b.p062b.p063a.p064a.p079j.p080a;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import com.baidu.uaq.agent.android.transmission.InterfaceC3323a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0732i;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.j.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0767a implements InterfaceC3323a {

    /* renamed from: a */
    public static final InterfaceC3321a f2374a = C0749a.f2299a;

    @Override // com.baidu.uaq.agent.android.transmission.InterfaceC3323a
    /* renamed from: c */
    public JSONArray mo17424c(List<C0732i> list) {
        JSONArray jSONArray = new JSONArray();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        try {
            Iterator<C0732i> it = list.iterator();
            while (it.hasNext()) {
                JSONObject mo17432az = it.next().mo17432az();
                if (mo17432az != null) {
                    String string = mo17432az.getJSONObject("baseInfo").getString("vvid");
                    if (hashMap.containsKey(string)) {
                        JSONArray jSONArray2 = (JSONArray) hashMap2.get(string);
                        mo17432az.remove("baseInfo");
                        jSONArray2.put(mo17432az);
                    } else {
                        hashMap.put(string, new JSONObject().put("baseInfo", mo17432az.remove("baseInfo")));
                        hashMap2.put(string, new JSONArray().put(mo17432az));
                    }
                }
                it.remove();
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                JSONObject jSONObject = (JSONObject) entry.getValue();
                jSONObject.put("events", hashMap2.get((String) entry.getKey()));
                jSONArray.put(jSONObject);
            }
        } catch (JSONException e) {
            f2374a.mo17426a("Caught error while LssDataParser parse: ", e);
            C0735a.m22302a(e);
        }
        return jSONArray;
    }
}

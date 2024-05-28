package p001a.p058b.p059a.p060a;

import com.baidu.cloud.statistics.StatisticLiveImpl;
import com.baidu.uaq.agent.android.customtransmission.MergeBlockCallBack;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import p001a.p058b.p059a.p060a.p061a.C0704b;

@NBSInstrumented
/* renamed from: a.b.a.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0707b implements MergeBlockCallBack {

    /* renamed from: a */
    public final /* synthetic */ StatisticLiveImpl f2135a;

    public C0707b(StatisticLiveImpl statisticLiveImpl) {
        this.f2135a = statisticLiveImpl;
    }

    @Override // com.baidu.uaq.agent.android.customtransmission.MergeBlockCallBack
    public String executeMerge(ArrayList<String> arrayList) {
        C0704b c0704b;
        C0704b c0704b2;
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            c0704b = this.f2135a.commData;
            jSONObject.put("CommData", c0704b.m22342b());
            jSONObject.put("eventName", "info");
            c0704b2 = this.f2135a.commData;
            jSONObject.put("eventInfo", c0704b2.m22344a());
            jSONArray.put(jSONObject);
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                Object nextValue = new JSONTokener(it.next()).nextValue();
                if (nextValue instanceof JSONObject) {
                    jSONArray.put(nextValue);
                } else if (nextValue instanceof JSONArray) {
                    JSONArray jSONArray2 = (JSONArray) nextValue;
                    for (int i = 0; i < jSONArray2.length(); i++) {
                        jSONArray.put(jSONArray2.getJSONObject(i));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
    }
}

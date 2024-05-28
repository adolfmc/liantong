package com.sinovatech.unicom.separatemodule.livepinglun.function;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveReplayEntity;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveReplayFunction implements Function<String, LiveReplayEntity> {
    @Override // io.reactivex.functions.Function
    public LiveReplayEntity apply(@NonNull String str) throws Exception {
        List<LiveReplayEntity.ReplyListEntity> list;
        JSONObject jSONObject = new JSONObject(str);
        Gson gson = new Gson();
        LiveReplayEntity liveReplayEntity = new LiveReplayEntity();
        liveReplayEntity.setDesc(jSONObject.optString("desc"));
        liveReplayEntity.setCode(jSONObject.optString("code"));
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("commentDetail");
            String jSONObject2 = !(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject);
            liveReplayEntity.setCommentDetail((LiveReplayEntity.CommentDetailEntity) (!(gson instanceof Gson) ? gson.fromJson(jSONObject2, (Class<Object>) LiveReplayEntity.CommentDetailEntity.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject2, (Class<Object>) LiveReplayEntity.CommentDetailEntity.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("replyList");
            Type type = new TypeToken<ArrayList<LiveReplayEntity.ReplyListEntity>>() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.function.LiveReplayFunction.1
            }.getType();
            String jSONArray2 = !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
            list = (List) (!(gson instanceof Gson) ? gson.fromJson(jSONArray2, type) : NBSGsonInstrumentation.fromJson(gson, jSONArray2, type));
        } catch (Exception e2) {
            e2.printStackTrace();
            list = arrayList;
        }
        liveReplayEntity.setReplyList(list);
        return liveReplayEntity;
    }
}

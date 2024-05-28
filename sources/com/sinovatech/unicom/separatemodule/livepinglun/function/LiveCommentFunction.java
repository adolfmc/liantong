package com.sinovatech.unicom.separatemodule.livepinglun.function;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveCommentEntity;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveCommentFunction implements Function<String, LiveCommentEntity> {
    @Override // io.reactivex.functions.Function
    public LiveCommentEntity apply(@NonNull String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        LiveCommentEntity liveCommentEntity = new LiveCommentEntity();
        liveCommentEntity.setCode(jSONObject.optString("code"));
        liveCommentEntity.setDesc(jSONObject.optString("desc"));
        liveCommentEntity.setCommentTotleCount(Integer.valueOf(jSONObject.optInt("commentTotleCount")));
        String optString = jSONObject.optString("commentList");
        if (optString.startsWith("[")) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<LiveCommentEntity.CommentListBean>>() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.function.LiveCommentFunction.1
            }.getType();
            liveCommentEntity.setCommentList((List) (!(gson instanceof Gson) ? gson.fromJson(optString, type) : NBSGsonInstrumentation.fromJson(gson, optString, type)));
        } else {
            liveCommentEntity.setCommentList(new ArrayList());
        }
        return liveCommentEntity;
    }
}

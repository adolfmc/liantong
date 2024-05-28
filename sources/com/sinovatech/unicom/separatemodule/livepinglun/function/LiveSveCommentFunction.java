package com.sinovatech.unicom.separatemodule.livepinglun.function;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveSaveCommentEntity;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveSveCommentFunction implements Function<String, LiveSaveCommentEntity> {
    @Override // io.reactivex.functions.Function
    public LiveSaveCommentEntity apply(@NonNull String str) throws Exception {
        new JSONObject();
        Gson gson = new Gson();
        return (LiveSaveCommentEntity) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) LiveSaveCommentEntity.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) LiveSaveCommentEntity.class));
    }
}

package com.sinovatech.unicom.separatemodule.livepinglun.function;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveSvaeReplayEntity;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveSaveReplayFunction implements Function<String, LiveSvaeReplayEntity> {
    @Override // io.reactivex.functions.Function
    public LiveSvaeReplayEntity apply(@NonNull String str) throws Exception {
        new JSONObject();
        Gson gson = new Gson();
        return (LiveSvaeReplayEntity) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) LiveSvaeReplayEntity.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) LiveSvaeReplayEntity.class));
    }
}

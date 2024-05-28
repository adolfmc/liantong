package com.sinovatech.unicom.basic.p315ui.home.function;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.p315ui.home.entity.ServiceViewEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.functions.Function;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.home.function.ServiceZuJianFuction */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ServiceZuJianFuction implements Function<String, ServiceViewEntity> {
    @Override // io.reactivex.functions.Function
    public ServiceViewEntity apply(String str) throws Exception {
        MsLogUtil.m7979d("服务组件", "apply: " + str);
        try {
            if (!TextUtils.isEmpty(str) && str.startsWith("{")) {
                Gson gson = new Gson();
                return (ServiceViewEntity) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) ServiceViewEntity.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) ServiceViewEntity.class));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("我的服务组件报错", "apply: " + e.getMessage());
            return null;
        }
    }
}

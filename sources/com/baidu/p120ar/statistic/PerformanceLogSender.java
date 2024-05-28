package com.baidu.p120ar.statistic;

import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.HttpFactory;
import com.baidu.p120ar.ihttp.IHttpRequest;
import com.baidu.p120ar.utils.UrlUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.statistic.PerformanceLogSender */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class PerformanceLogSender extends LogSender implements ILogSender {
    @Override // com.baidu.p120ar.statistic.LogSender
    protected void appendSignInfo(JSONObject jSONObject) {
    }

    @Override // com.baidu.p120ar.statistic.LogSender
    protected JSONObject appendSingleEventJson(JSONObject jSONObject, EventData eventData) {
        return jSONObject;
    }

    @Override // com.baidu.p120ar.statistic.LogSender
    protected Map<String, Object> extractSameKeys(List<EventData> list) {
        return null;
    }

    @Override // com.baidu.p120ar.statistic.LogSender
    protected void doRequest(JSONObject jSONObject) throws IOException, HttpException {
        IHttpRequest newRequest = HttpFactory.newRequest();
        if (newRequest == null) {
            return;
        }
        String performanceStatisticUrl = UrlUtils.getPerformanceStatisticUrl();
        StringBuilder sb = new StringBuilder();
        sb.append("info=");
        sb.append(URLEncoder.encode(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "UTF-8"));
        newRequest.setUrl(performanceStatisticUrl).setMethod("POST").setBody(sb.toString()).addHeader("Content-Type: application/x-www-form-urlencoded");
        newRequest.execute();
    }
}

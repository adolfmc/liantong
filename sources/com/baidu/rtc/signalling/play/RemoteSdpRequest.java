package com.baidu.rtc.signalling.play;

import android.text.TextUtils;
import com.baidu.rtc.signalling.mode.IJsonSignalling;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.webrtc.Logging;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\567196_dexfile_execute.dex */
public class RemoteSdpRequest implements IJsonSignalling {
    private static final String TAG = "RemoteSdpRequest";
    private final String mediaServerIp;
    private final String sdp;
    private final String streamUrl;
    private final long transactionId;

    public RemoteSdpRequest(String str, long j, String str2, String str3) {
        this.sdp = str;
        this.transactionId = j;
        this.streamUrl = str2;
        this.mediaServerIp = str3;
    }

    @Override // com.baidu.rtc.signalling.mode.IJsonSignalling
    public JSONObject asJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdktag", "BRTC.HTTP.PULL.SDK");
            jSONObject.put("sdp", this.sdp);
            jSONObject.put("transaction", String.valueOf(this.transactionId));
            jSONObject.put("mediaserverip", TextUtils.isEmpty(this.mediaServerIp) ? "" : this.mediaServerIp);
            jSONObject.put("streamurl", this.streamUrl);
        } catch (JSONException e) {
            Logging.m5304e("RemoteSdpRequest", "Caught error while play request as json" + e.getMessage());
        }
        return jSONObject;
    }

    @Override // com.baidu.rtc.signalling.mode.IJsonSignalling
    public String toJSONString() {
        JSONObject asJSONObject = asJSONObject();
        return !(asJSONObject instanceof JSONObject) ? asJSONObject.toString() : NBSJSONObjectInstrumentation.toString(asJSONObject);
    }
}

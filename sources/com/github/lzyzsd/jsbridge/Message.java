package com.github.lzyzsd.jsbridge;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Message {
    private static final String CALLBACK_ID_STR = "callbackId";
    private static final String DATA_STR = "data";
    private static final String HANDLER_NAME_STR = "handlerName";
    private static final String RESPONSE_DATA_STR = "responseData";
    private static final String RESPONSE_ID_STR = "responseId";
    private String callbackId;
    private String data;
    private String handlerName;
    private String responseData;
    private String responseId;

    public String getResponseId() {
        return this.responseId;
    }

    public void setResponseId(String str) {
        this.responseId = str;
    }

    public String getResponseData() {
        return this.responseData;
    }

    public void setResponseData(String str) {
        this.responseData = str;
    }

    public String getCallbackId() {
        return this.callbackId;
    }

    public void setCallbackId(String str) {
        this.callbackId = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public String getHandlerName() {
        return this.handlerName;
    }

    public void setHandlerName(String str) {
        this.handlerName = str;
    }

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("callbackId", getCallbackId());
            jSONObject.put("data", getData());
            jSONObject.put("handlerName", getHandlerName());
            jSONObject.put("responseData", getResponseData());
            jSONObject.put("responseId", getResponseId());
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Message toObject(String str) {
        Message message = new Message();
        try {
            JSONObject jSONObject = new JSONObject(str);
            message.setHandlerName(jSONObject.has("handlerName") ? jSONObject.getString("handlerName") : null);
            message.setCallbackId(jSONObject.has("callbackId") ? jSONObject.getString("callbackId") : null);
            message.setResponseData(jSONObject.has("responseData") ? jSONObject.getString("responseData") : null);
            message.setResponseId(jSONObject.has("responseId") ? jSONObject.getString("responseId") : null);
            message.setData(jSONObject.has("data") ? jSONObject.getString("data") : null);
            return message;
        } catch (JSONException e) {
            e.printStackTrace();
            return message;
        }
    }

    public static List<Message> toArrayList(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                Message message = new Message();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                message.setHandlerName(jSONObject.has("handlerName") ? jSONObject.getString("handlerName") : null);
                message.setCallbackId(jSONObject.has("callbackId") ? jSONObject.getString("callbackId") : null);
                message.setResponseData(jSONObject.has("responseData") ? jSONObject.getString("responseData") : null);
                message.setResponseId(jSONObject.has("responseId") ? jSONObject.getString("responseId") : null);
                message.setData(jSONObject.has("data") ? jSONObject.getString("data") : null);
                arrayList.add(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}

package com.vivo.push.model;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.vivo.push.util.JsonParserUtils;
import com.vivo.push.util.LogUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class UnvarnishedMessage {
    private static final String TAG = "UnvarnishedMessage";
    private String mMessage;
    private long mMsgId;
    private Map<String, String> mParams = new HashMap();
    private int mTargetType;
    private String mTragetContent;

    public UnvarnishedMessage() {
    }

    public UnvarnishedMessage(String str) {
        packToObj(str);
    }

    public long getMsgId() {
        return this.mMsgId;
    }

    public void setMsgId(long j) {
        this.mMsgId = j;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public String getTragetContent() {
        return this.mTragetContent;
    }

    public void setTragetContent(String str) {
        this.mTragetContent = str;
    }

    public int getTargetType() {
        return this.mTargetType;
    }

    public void setTargetType(int i) {
        this.mTargetType = i;
    }

    public Map<String, String> getParams() {
        return this.mParams;
    }

    public void setParams(Map<String, String> map) {
        this.mParams = map;
    }

    public String unpackToJson() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.mTargetType);
        jSONArray.put(this.mTragetContent);
        jSONArray.put(this.mMessage);
        Object obj = this.mParams;
        if (obj == null) {
            obj = new HashMap();
        }
        jSONArray.put(obj);
        return !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
    }

    private void packToObj(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                LogUtil.m5354a(TAG, "unvarnishedMsg pack to obj is null");
                return;
            }
            JSONArray jSONArray = new JSONArray(str);
            this.mTargetType = jSONArray.optInt(0);
            this.mTragetContent = jSONArray.getString(1);
            this.mMessage = jSONArray.getString(2);
            this.mParams = JsonParserUtils.m5373a(new JSONObject(jSONArray.getString(3)));
        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.m5353a(TAG, "unvarnishedMsg pack to obj error", e);
        }
    }
}

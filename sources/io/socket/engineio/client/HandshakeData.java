package io.socket.engineio.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class HandshakeData {
    public long pingInterval;
    public long pingTimeout;
    public String sid;
    public String[] upgrades;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandshakeData(String str) throws JSONException {
        this(new JSONObject(str));
    }

    HandshakeData(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("upgrades");
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = jSONArray.getString(i);
        }
        this.sid = jSONObject.getString("sid");
        this.upgrades = strArr;
        this.pingInterval = jSONObject.getLong("pingInterval");
        this.pingTimeout = jSONObject.getLong("pingTimeout");
    }
}

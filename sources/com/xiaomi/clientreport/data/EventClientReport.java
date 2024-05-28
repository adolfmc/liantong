package com.xiaomi.clientreport.data;

import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EventClientReport extends C11052a {
    public String eventContent;
    public String eventId;
    public long eventTime;
    public int eventType;

    public static EventClientReport getBlankInstance() {
        return new EventClientReport();
    }

    @Override // com.xiaomi.clientreport.data.C11052a
    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            if (json == null) {
                return null;
            }
            json.put("eventId", this.eventId);
            json.put("eventType", this.eventType);
            json.put("eventTime", this.eventTime);
            json.put("eventContent", this.eventContent == null ? "" : this.eventContent);
            return json;
        } catch (JSONException e) {
            AbstractC11049b.m5276a(e);
            return null;
        }
    }

    @Override // com.xiaomi.clientreport.data.C11052a
    public String toJsonString() {
        return super.toJsonString();
    }
}

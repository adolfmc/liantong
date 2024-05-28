package com.xiaomi.clientreport.data;

import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PerfClientReport extends C11052a {
    private static final long DEFAULT_VALUE = -1;
    public int code;
    public long perfCounts = -1;
    public long perfLatencies = -1;

    public static PerfClientReport getBlankInstance() {
        return new PerfClientReport();
    }

    @Override // com.xiaomi.clientreport.data.C11052a
    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            if (json == null) {
                return null;
            }
            json.put("code", this.code);
            json.put("perfCounts", this.perfCounts);
            json.put("perfLatencies", this.perfLatencies);
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

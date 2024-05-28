package com.bytedance.applog;

import android.content.Context;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.c0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3558c0 extends AbstractC3692t {

    /* renamed from: e */
    public final Context f8388e;

    public C3558c0(Context context) {
        super(true, true);
        this.f8388e = context;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        C3735y.m17019a(jSONObject, "language", this.f8388e.getResources().getConfiguration().locale.getLanguage());
        int rawOffset = TimeZone.getDefault().getRawOffset() / 3600000;
        if (rawOffset < -12) {
            rawOffset = -12;
        }
        if (rawOffset > 12) {
            rawOffset = 12;
        }
        jSONObject.put("timezone", rawOffset);
        C3735y.m17019a(jSONObject, "region", Locale.getDefault().getCountry());
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        C3735y.m17019a(jSONObject, "tz_name", timeZone.getID());
        jSONObject.put("tz_offset", timeZone.getOffset(System.currentTimeMillis()) / 1000);
        return true;
    }
}

package com.p319ss.android.socialbase.appdownloader.p338mb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.Iterator;
import org.json.JSONObject;

/* renamed from: com.ss.android.socialbase.appdownloader.mb.b */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10127b extends AbstractC10134mb {

    /* renamed from: hj */
    private final JSONObject f19538hj;

    public C10127b(Context context, DownloadSetting downloadSetting, String str, JSONObject jSONObject) {
        super(context, downloadSetting, str);
        this.f19538hj = jSONObject;
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p338mb.InterfaceC10128h
    /* renamed from: ox */
    public Intent mo6729ox() {
        String optString = this.f19538hj.optString("action");
        String optString2 = this.f19538hj.optString("category");
        int optInt = this.f19538hj.optInt("flags", 1342210048);
        String optString3 = this.f19538hj.optString("path_extra_key");
        String optString4 = this.f19538hj.optString("path_data_key");
        JSONObject optJSONObject = this.f19538hj.optJSONObject("extra");
        JSONObject optJSONObject2 = this.f19538hj.optJSONObject("extra_type");
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        Intent intent = new Intent(optString);
        if (!TextUtils.isEmpty(optString2)) {
            intent.addCategory(optString2);
        }
        if (!TextUtils.isEmpty(optString4)) {
            try {
                intent.setData(Uri.parse(String.format(optString4, this.f19541b)));
            } catch (Throwable unused) {
            }
        }
        intent.setFlags(optInt);
        if (!TextUtils.isEmpty(optString3)) {
            intent.putExtra(optString3, this.f19541b);
        }
        m6735mb(intent, optJSONObject, optJSONObject2);
        return intent;
    }

    /* renamed from: mb */
    private static void m6735mb(@NonNull Intent intent, JSONObject jSONObject, JSONObject jSONObject2) {
        Iterator<String> keys;
        if (jSONObject == null || jSONObject2 == null || jSONObject.length() != jSONObject2.length() || intent == null || (keys = jSONObject.keys()) == null) {
            return;
        }
        while (keys.hasNext()) {
            String next = keys.next();
            String optString = jSONObject2.optString(next);
            if (optString != null) {
                m6734mb(jSONObject, next, optString, intent);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: mb */
    private static void m6734mb(JSONObject jSONObject, String str, String str2, Intent intent) {
        char c;
        switch (str2.hashCode()) {
            case -1325958191:
                if (str2.equals("double")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -891985903:
                if (str2.equals("string")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 104431:
                if (str2.equals("int")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3327612:
                if (str2.equals("long")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 64711720:
                if (str2.equals("boolean")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                intent.putExtra(str, jSONObject.optInt(str));
                return;
            case 1:
                intent.putExtra(str, jSONObject.optBoolean(str));
                return;
            case 2:
                intent.putExtra(str, jSONObject.optLong(str));
                return;
            case 3:
                intent.putExtra(str, jSONObject.optDouble(str));
                return;
            case 4:
                intent.putExtra(str, jSONObject.optString(str));
                return;
            default:
                return;
        }
    }
}

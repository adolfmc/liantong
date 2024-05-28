package com.p319ss.android.socialbase.appdownloader.p338mb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.p319ss.android.socialbase.appdownloader.p340u.C10150b;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.ss.android.socialbase.appdownloader.mb.nk */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10135nk extends AbstractC10134mb {
    public C10135nk(Context context, DownloadSetting downloadSetting, String str) {
        super(context, downloadSetting, str);
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p338mb.InterfaceC10128h
    /* renamed from: ox */
    public Intent mo6729ox() {
        String optString = this.f19543ox.optString("s");
        String m6594mb = C10150b.m6594mb(this.f19543ox.optString("bb"), optString);
        if (TextUtils.isEmpty(m6594mb) || m6594mb.split(",").length != 2) {
            return null;
        }
        String m6594mb2 = C10150b.m6594mb(this.f19543ox.optString("bc"), optString);
        if (TextUtils.isEmpty(m6594mb2) || m6594mb2.split(",").length != 2) {
            return null;
        }
        String[] split = m6594mb.split(",");
        String[] split2 = m6594mb2.split(",");
        String m6594mb3 = C10150b.m6594mb(this.f19543ox.optString("bd"), optString);
        String m6594mb4 = C10150b.m6594mb(this.f19543ox.optString("be"), optString);
        String m6594mb5 = C10150b.m6594mb(this.f19543ox.optString("bf"), optString);
        HashMap hashMap = new HashMap();
        hashMap.put(split[0], split[1]);
        hashMap.put(split2[0], split2[1]);
        hashMap.put(m6594mb3, this.f19541b);
        Intent intent = new Intent();
        intent.setAction(m6594mb5);
        intent.setData(Uri.parse(m6594mb4 + m6730mb(hashMap)));
        intent.addFlags(268468224);
        return intent;
    }

    /* renamed from: mb */
    public static String m6730mb(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuffer.append(entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append(URLEncoder.encode(entry.getValue()));
            stringBuffer.append("&");
        }
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.endsWith("&") ? stringBuffer2.substring(0, stringBuffer2.length() - 1) : stringBuffer2;
    }
}

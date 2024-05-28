package com.p319ss.android.socialbase.appdownloader.p338mb;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.p319ss.android.socialbase.appdownloader.p340u.C10150b;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* renamed from: com.ss.android.socialbase.appdownloader.mb.lz */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10133lz extends AbstractC10134mb {

    /* renamed from: h */
    private String f19539h;

    /* renamed from: hj */
    private String f19540hj;

    public C10133lz(Context context, DownloadSetting downloadSetting, String str, String str2, String str3) {
        super(context, downloadSetting, str);
        this.f19540hj = str2;
        this.f19539h = str3;
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p338mb.InterfaceC10128h
    /* renamed from: ox */
    public Intent mo6729ox() {
        String optString = this.f19543ox.optString("s");
        String m6594mb = C10150b.m6594mb(this.f19543ox.optString("ak"), optString);
        String m6594mb2 = C10150b.m6594mb(this.f19543ox.optString("am"), optString);
        String m6594mb3 = C10150b.m6594mb(this.f19543ox.optString("an"), optString);
        String str = null;
        if (TextUtils.isEmpty(m6594mb3) || m6594mb3.split(",").length != 2) {
            return null;
        }
        String[] split = m6594mb3.split(",");
        String m6594mb4 = C10150b.m6594mb(this.f19543ox.optString("al"), optString);
        String m6594mb5 = C10150b.m6594mb(this.f19543ox.optString("ao"), optString);
        if (TextUtils.isEmpty(m6594mb5) || m6594mb5.split(",").length != 2) {
            return null;
        }
        String[] split2 = m6594mb5.split(",");
        JSONObject optJSONObject = this.f19543ox.optJSONObject("download_dir");
        if (optJSONObject != null) {
            String optString2 = optJSONObject.optString("dir_name");
            if (!TextUtils.isEmpty(optString2) && optString2.contains("%s")) {
                try {
                    str = String.format(optString2, this.f19539h);
                } catch (Throwable unused) {
                    str = this.f19539h;
                }
            } else {
                str = this.f19539h;
            }
            if (str.length() > 255) {
                str = m6594mb4.substring(str.length() - 255);
            }
        }
        Intent intent = new Intent(m6594mb);
        intent.putExtra(split2[0], split2[1]);
        intent.putExtra(m6594mb2, this.f19540hj);
        intent.putExtra(m6594mb4, str);
        intent.putExtra(split[0], Integer.parseInt(split[1]));
        intent.addFlags(268468224);
        return intent;
    }
}

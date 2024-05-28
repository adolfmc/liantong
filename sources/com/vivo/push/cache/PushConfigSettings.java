package com.vivo.push.cache;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.vivo.push.model.ConfigItem;
import com.vivo.push.util.CryptographicTool;
import com.vivo.push.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.vivo.push.cache.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class PushConfigSettings extends ICacheSettings<ConfigItem> {
    @Override // com.vivo.push.cache.ICacheSettings
    /* renamed from: a */
    protected final String mo5740a() {
        return "com.vivo.pushservice.other";
    }

    public PushConfigSettings(Context context) {
        super(context);
    }

    @Override // com.vivo.push.cache.ICacheSettings
    /* renamed from: a */
    public final List<ConfigItem> mo5739a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.trim().split("@#")) {
            String trim = str2.trim();
            String[] split = trim.trim().split(",");
            if (split.length >= 2) {
                try {
                    arrayList.add(new ConfigItem(split[0], trim.substring(split[0].length() + 1)));
                } catch (Exception e) {
                    LogUtil.m5341d("PushConfigSettings", "str2Clients E: ".concat(String.valueOf(e)));
                }
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    public final String m5737c(String str) {
        synchronized (f20930a) {
            for (T t : this.f20931b) {
                if (!TextUtils.isEmpty(t.m5604a()) && t.m5604a().equals(str)) {
                    return t.m5603b();
                }
            }
            return null;
        }
    }

    @Override // com.vivo.push.cache.ICacheSettings
    /* renamed from: b */
    final String mo5738b(String str) throws Exception {
        return new String(CryptographicTool.m5392a(CryptographicTool.m5391a(m5742e()), CryptographicTool.m5391a(m5741f()), Base64.decode(str, 2)), "utf-8");
    }
}

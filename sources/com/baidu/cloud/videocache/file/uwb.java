package com.baidu.cloud.videocache.file;

import android.text.TextUtils;
import com.baidu.cloud.videocache.C2576l;
import com.baidu.cloud.videocache.utils.Logger;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class uwb implements FileNameGenerator {

    /* renamed from: a */
    private ConcurrentHashMap f4883a = new ConcurrentHashMap();

    @Override // com.baidu.cloud.videocache.file.FileNameGenerator
    public String generate(String str) {
        String valueOf;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = (String) this.f4883a.get(str);
        if (TextUtils.isEmpty(str2)) {
            try {
                valueOf = C2576l.m19779d(str.split("[?]")[0]);
            } catch (Exception e) {
                Logger.m19725d(e.getMessage());
                valueOf = String.valueOf((TextUtils.isEmpty(null) ? str : null).hashCode());
            }
            if (TextUtils.isEmpty(valueOf)) {
                valueOf = str;
            }
            this.f4883a.put(str, valueOf);
            Logger.m19725d("generate cacheKey:" + valueOf + " url:" + str);
            return valueOf;
        }
        return str2;
    }
}

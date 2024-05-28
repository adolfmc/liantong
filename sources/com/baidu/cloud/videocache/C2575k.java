package com.baidu.cloud.videocache;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.videocache.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2575k {
    /* renamed from: a */
    public static C2563a m19786a(String str, oia oiaVar) {
        C2571g.m19807a(str);
        C2571g.m19807a(oiaVar);
        int analyzeMediaType = Util.analyzeMediaType(str);
        switch (analyzeMediaType) {
            case 0:
                return new C2563a(new C2564b(str, oiaVar.f4909d, oiaVar.f4910e), new com.baidu.cloud.videocache.file.oia(oiaVar.m19768a(str), oiaVar.f4908c));
            case 1:
                return new ass(new uwb(str, oiaVar.f4909d, oiaVar.f4910e), new com.baidu.cloud.videocache.file.oia(oiaVar.m19768a(str), oiaVar.f4908c));
            default:
                throw new C2574j("Unsupported media type: " + analyzeMediaType);
        }
    }
}

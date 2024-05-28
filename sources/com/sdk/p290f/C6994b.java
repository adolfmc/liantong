package com.sdk.p290f;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sdk.f.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6994b {

    /* renamed from: a */
    public long f18128a = System.currentTimeMillis();

    /* renamed from: b */
    public C6995a f18129b = new C6995a();

    /* renamed from: c */
    public String f18130c = "";

    /* renamed from: com.sdk.f.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class C6995a {

        /* renamed from: a */
        public List<C6996a> f18131a = new ArrayList();

        /* renamed from: b */
        public List<String> f18132b = new ArrayList();

        /* renamed from: c */
        public List<String> f18133c = new ArrayList();

        /* renamed from: d */
        public String f18134d = "";

        /* renamed from: com.sdk.f.b$a$a */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static class C6996a {
            public String toString() {
                return "_$101005Bean{url='', time=0}";
            }
        }

        public String toString() {
            return "StatusBean{_$101005=" + this.f18131a + ", _$302001=" + this.f18132b + ", _$302002=" + this.f18133c + ", _$302003='" + this.f18134d + "'}";
        }
    }

    public String toString() {
        return "MobileLog{time=" + this.f18128a + ", status=" + this.f18129b + '}';
    }
}

package com.sdk.p290f;

import com.sdk.p297m.C7012a;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.f.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6998d {

    /* renamed from: a */
    public static boolean f18135a;

    /* renamed from: b */
    public static boolean f18136b;

    /* renamed from: c */
    public static boolean f18137c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* renamed from: com.sdk.f.d$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static abstract class EnumC7000b {

        /* renamed from: a */
        public static final EnumC7000b f18138a;

        /* renamed from: b */
        public static final EnumC7000b f18139b;

        /* renamed from: c */
        public static final EnumC7000b f18140c;

        /* renamed from: d */
        public static final /* synthetic */ EnumC7000b[] f18141d;

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sdk.f.d$b$a */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum C7001a extends EnumC7000b {
            public C7001a(String str, int i) {
                super(str, i);
            }

            @Override // com.sdk.p290f.C6998d.EnumC7000b
            /* renamed from: a */
            public String mo8168a() {
                return C7012a.m8147a("cucc/host_cucc.properties", "PRODUCE_STATISTICAL");
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sdk.f.d$b$b */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum C7002b extends EnumC7000b {
            public C7002b(String str, int i) {
                super(str, i);
            }

            @Override // com.sdk.p290f.C6998d.EnumC7000b
            /* renamed from: a */
            public String mo8168a() {
                return C7012a.m8147a("cucc/host_cucc.properties", "PRODUCE_DZH");
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sdk.f.d$b$c */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum C7003c extends EnumC7000b {
            public C7003c(String str, int i) {
                super(str, i);
            }

            @Override // com.sdk.p290f.C6998d.EnumC7000b
            /* renamed from: a */
            public String mo8168a() {
                return C7012a.m8147a("cucc/host_cucc.properties", "PRODUCE_TEST");
            }
        }

        static {
            C7001a c7001a = new C7001a("PRODUCE_STATISTICAL", 0);
            f18138a = c7001a;
            C7002b c7002b = new C7002b("PRODUCE_DZH", 1);
            f18139b = c7002b;
            C7003c c7003c = new C7003c("PRODUCE_TEST", 2);
            f18140c = c7003c;
            f18141d = new EnumC7000b[]{c7001a, c7002b, c7003c};
        }

        public EnumC7000b(String str, int i) {
        }

        public static EnumC7000b valueOf(String str) {
            return (EnumC7000b) Enum.valueOf(EnumC7000b.class, str);
        }

        public static EnumC7000b[] values() {
            return (EnumC7000b[]) f18141d.clone();
        }

        /* renamed from: a */
        public abstract String mo8168a();
    }

    static {
        new HashMap();
        f18135a = false;
        f18136b = true;
        f18137c = false;
    }
}

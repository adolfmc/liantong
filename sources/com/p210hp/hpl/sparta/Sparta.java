package com.p210hp.hpl.sparta;

import java.util.Hashtable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.Sparta */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Sparta {
    private static Internment internment_ = new Internment() { // from class: com.hp.hpl.sparta.Sparta.1
        private final Hashtable strings_ = new Hashtable();

        @Override // com.p210hp.hpl.sparta.Sparta.Internment
        public String intern(String str) {
            String str2 = (String) this.strings_.get(str);
            if (str2 == null) {
                this.strings_.put(str, str);
                return str;
            }
            return str2;
        }
    };
    private static CacheFactory cacheFactory_ = new CacheFactory() { // from class: com.hp.hpl.sparta.Sparta.2
        @Override // com.p210hp.hpl.sparta.Sparta.CacheFactory
        public Cache create() {
            return new HashtableCache();
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.hp.hpl.sparta.Sparta$Cache */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Cache {
        Object get(Object obj);

        Object put(Object obj, Object obj2);

        int size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.hp.hpl.sparta.Sparta$CacheFactory */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface CacheFactory {
        Cache create();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.hp.hpl.sparta.Sparta$Internment */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Internment {
        String intern(String str);
    }

    public static String intern(String str) {
        return internment_.intern(str);
    }

    public static void setInternment(Internment internment) {
        internment_ = internment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Cache newCache() {
        return cacheFactory_.create();
    }

    public static void setCacheFactory(CacheFactory cacheFactory) {
        cacheFactory_ = cacheFactory;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.hp.hpl.sparta.Sparta$HashtableCache */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class HashtableCache extends Hashtable implements Cache {
        private HashtableCache() {
        }
    }
}

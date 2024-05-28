package com.huawei.hms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class Objects {

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class ToStringHelper {

        /* renamed from: a */
        private final List<String> f11154a;

        /* renamed from: b */
        private final Object f11155b;

        public final ToStringHelper add(String str, Object obj) {
            String str2 = (String) Preconditions.checkNotNull(str);
            String valueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(str2.length() + valueOf.length() + 1);
            sb.append(str2);
            sb.append("=");
            sb.append(valueOf);
            this.f11154a.add(sb.toString());
            return this;
        }

        public final String toString() {
            String simpleName = this.f11155b.getClass().getSimpleName();
            StringBuilder sb = new StringBuilder(100);
            sb.append(simpleName);
            sb.append('{');
            int size = this.f11154a.size();
            for (int i = 0; i < size; i++) {
                sb.append(this.f11154a.get(i));
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append('}');
            return sb.toString();
        }

        private ToStringHelper(Object obj) {
            this.f11155b = Preconditions.checkNotNull(obj);
            this.f11154a = new ArrayList();
        }
    }

    private Objects() {
        throw new AssertionError("Uninstantiable");
    }

    public static boolean equal(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        return obj != null && obj.equals(obj2);
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj);
    }
}

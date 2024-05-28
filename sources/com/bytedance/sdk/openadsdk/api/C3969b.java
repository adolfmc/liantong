package com.bytedance.sdk.openadsdk.api;

import android.util.SparseArray;
import com.bykv.p167vk.openvk.api.proto.ValueSet;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.bytedance.sdk.openadsdk.api.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3969b {

    /* renamed from: mb */
    private final SparseArray<Object> f9490mb;

    private C3969b(SparseArray<Object> sparseArray) {
        this.f9490mb = sparseArray;
    }

    /* renamed from: mb */
    public static final C3969b m16559mb() {
        return new C3969b(new SparseArray());
    }

    /* renamed from: mb */
    public C3969b m16558mb(int i, Object obj) {
        this.f9490mb.put(i, obj);
        return this;
    }

    /* renamed from: mb */
    public C3969b m16557mb(int i, String str) {
        this.f9490mb.put(i, str);
        return this;
    }

    /* renamed from: mb */
    public C3969b m16556mb(int i, boolean z) {
        this.f9490mb.put(i, Boolean.valueOf(z));
        return this;
    }

    /* renamed from: ox */
    public ValueSet m16555ox() {
        return new C3971mb(this.f9490mb);
    }

    /* renamed from: com.bytedance.sdk.openadsdk.api.b$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static final class C3971mb implements ValueSet {

        /* renamed from: mb */
        private final SparseArray<Object> f9491mb;

        private C3971mb(SparseArray<Object> sparseArray) {
            this.f9491mb = sparseArray;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public <T> T[] arrayValue(int i, Class<T> cls) {
            Object obj = this.f9491mb.get(i);
            if (obj == null) {
                return null;
            }
            Class<?> cls2 = obj.getClass();
            if (cls2.isArray() && cls.isAssignableFrom(cls2.getComponentType())) {
                return (T[]) ((Object[]) obj);
            }
            return null;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public <T> T objectValue(int i, Class<T> cls) {
            Object obj = this.f9491mb.get(i);
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            if (cls.isInstance(obj)) {
                return (T) this.f9491mb.get(i);
            }
            return null;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public String stringValue(int i) {
            return stringValue(i, null);
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public int intValue(int i) {
            return intValue(i, 0);
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public boolean booleanValue(int i) {
            return booleanValue(i, false);
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public long longValue(int i) {
            return longValue(i, 0L);
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public float floatValue(int i) {
            return floatValue(i, 0.0f);
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public double doubleValue(int i) {
            Object obj = this.f9491mb.get(i);
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            if (obj instanceof Double) {
                return ((Double) obj).doubleValue();
            }
            return 0.0d;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public String stringValue(int i, String str) {
            Object obj = this.f9491mb.get(i);
            return (obj instanceof ValueSet.ValueGetter ? ((ValueSet.ValueGetter) obj).get() : obj) instanceof String ? obj.toString() : str;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public int intValue(int i, int i2) {
            Object obj = this.f9491mb.get(i);
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            return obj instanceof Integer ? ((Integer) obj).intValue() : i2;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public boolean booleanValue(int i, boolean z) {
            Object obj = this.f9491mb.get(i);
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public long longValue(int i, long j) {
            Object obj = this.f9491mb.get(i);
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            return obj instanceof Long ? ((Long) obj).longValue() : j;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public float floatValue(int i, float f) {
            Object obj = this.f9491mb.get(i);
            if (obj instanceof ValueSet.ValueGetter) {
                obj = ((ValueSet.ValueGetter) obj).get();
            }
            return obj instanceof Float ? ((Float) obj).floatValue() : f;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public boolean containsKey(int i) {
            return this.f9491mb.indexOfKey(i) >= 0;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public Set<Integer> keys() {
            int size = this.f9491mb.size();
            HashSet hashSet = new HashSet();
            for (int i = 0; i < size; i++) {
                hashSet.add(Integer.valueOf(i));
            }
            return hashSet;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public boolean isEmpty() {
            return size() <= 0;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.ValueSet
        public int size() {
            SparseArray<Object> sparseArray = this.f9491mb;
            if (sparseArray == null) {
                return 0;
            }
            return sparseArray.size();
        }
    }
}

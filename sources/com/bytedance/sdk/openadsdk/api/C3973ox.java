package com.bytedance.sdk.openadsdk.api;

import com.bykv.p167vk.openvk.api.proto.Result;
import com.bykv.p167vk.openvk.api.proto.ValueSet;

/* renamed from: com.bytedance.sdk.openadsdk.api.ox */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3973ox {

    /* renamed from: mb */
    private boolean f9496mb = false;

    /* renamed from: ox */
    private int f9497ox = -1;

    /* renamed from: b */
    private String f9494b = null;

    /* renamed from: hj */
    private ValueSet f9495hj = null;

    private C3973ox() {
    }

    /* renamed from: mb */
    public static final C3973ox m16542mb() {
        return new C3973ox();
    }

    /* renamed from: mb */
    public C3973ox m16539mb(boolean z) {
        this.f9496mb = z;
        return this;
    }

    /* renamed from: mb */
    public C3973ox m16541mb(int i) {
        this.f9497ox = i;
        return this;
    }

    /* renamed from: mb */
    public C3973ox m16540mb(ValueSet valueSet) {
        this.f9495hj = valueSet;
        return this;
    }

    /* renamed from: ox */
    public Result m16538ox() {
        boolean z = this.f9496mb;
        int i = this.f9497ox;
        String str = this.f9494b;
        ValueSet valueSet = this.f9495hj;
        if (valueSet == null) {
            valueSet = C3969b.m16559mb().m16555ox();
        }
        return new C3975mb(z, i, str, valueSet);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.sdk.openadsdk.api.ox$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static final class C3975mb implements Result {

        /* renamed from: b */
        private final String f9498b;

        /* renamed from: hj */
        private final ValueSet f9499hj;

        /* renamed from: mb */
        private final boolean f9500mb;

        /* renamed from: ox */
        private final int f9501ox;

        private C3975mb(boolean z, int i, String str, ValueSet valueSet) {
            this.f9500mb = z;
            this.f9501ox = i;
            this.f9498b = str;
            this.f9499hj = valueSet;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.Result
        public boolean isSuccess() {
            return this.f9500mb;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.Result
        public int code() {
            return this.f9501ox;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.Result
        public String message() {
            return this.f9498b;
        }

        @Override // com.bykv.p167vk.openvk.api.proto.Result
        public ValueSet values() {
            return this.f9499hj;
        }
    }
}

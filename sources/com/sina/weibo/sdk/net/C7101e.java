package com.sina.weibo.sdk.net;

import android.os.Bundle;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.net.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7101e implements InterfaceC7100d {

    /* renamed from: i */
    private String f18311i;

    /* renamed from: j */
    private Bundle f18312j = new Bundle();

    /* renamed from: k */
    private Bundle f18313k = new Bundle();

    /* renamed from: l */
    private Map<String, Object<File>> f18314l = new HashMap();

    /* renamed from: m */
    private Map<String, byte[]> f18315m = new HashMap();

    /* renamed from: n */
    private int f18316n;

    /* renamed from: o */
    private int f18317o;

    public C7101e(C7102a c7102a) {
        this.f18311i = c7102a.f18318i;
        this.f18312j.putAll(c7102a.f18319j);
        this.f18313k.putAll(c7102a.f18320k);
        this.f18314l.putAll(c7102a.f18321l);
        this.f18315m.putAll(c7102a.f18322m);
        this.f18316n = c7102a.f18323n;
        this.f18317o = c7102a.f18324o;
    }

    @Override // com.sina.weibo.sdk.net.InterfaceC7100d
    public final String getUrl() {
        return this.f18311i;
    }

    @Override // com.sina.weibo.sdk.net.InterfaceC7100d
    public final Bundle getParams() {
        return this.f18312j;
    }

    @Override // com.sina.weibo.sdk.net.InterfaceC7100d
    /* renamed from: d */
    public final Bundle mo8055d() {
        return this.f18313k;
    }

    @Override // com.sina.weibo.sdk.net.InterfaceC7100d
    public final int getConnectTimeout() {
        return this.f18316n;
    }

    @Override // com.sina.weibo.sdk.net.InterfaceC7100d
    public final int getReadTimeout() {
        return this.f18317o;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sina.weibo.sdk.net.e$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C7102a {

        /* renamed from: i */
        public String f18318i;

        /* renamed from: j */
        Bundle f18319j = new Bundle();

        /* renamed from: k */
        Bundle f18320k = new Bundle();

        /* renamed from: l */
        Map<String, Object<File>> f18321l = new HashMap();

        /* renamed from: m */
        Map<String, byte[]> f18322m = new HashMap();

        /* renamed from: n */
        int f18323n = 30000;

        /* renamed from: o */
        int f18324o = 60000;

        /* renamed from: a */
        public final C7102a m8053a(String str, Object obj) {
            m8054a(this.f18319j, str, obj);
            return this;
        }

        /* renamed from: b */
        public final C7102a m8052b(String str, Object obj) {
            m8054a(this.f18320k, str, obj);
            return this;
        }

        /* renamed from: a */
        private void m8054a(Bundle bundle, String str, Object obj) {
            if (obj != null) {
                if (obj instanceof String) {
                    bundle.putString(str, String.valueOf(obj));
                } else if (obj instanceof Integer) {
                    bundle.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Short) {
                    bundle.putShort(str, ((Short) obj).shortValue());
                } else if (obj instanceof Character) {
                    bundle.putChar(str, ((Character) obj).charValue());
                } else if (obj instanceof Byte) {
                    bundle.putByte(str, ((Byte) obj).byteValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(str, ((Long) obj).longValue());
                } else if (obj instanceof Float) {
                    bundle.putFloat(str, ((Float) obj).floatValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(str, ((Double) obj).doubleValue());
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof byte[]) {
                    this.f18322m.put(str, (byte[]) obj);
                } else if (obj instanceof Serializable) {
                    bundle.putSerializable(str, (Serializable) obj);
                } else {
                    throw new IllegalArgumentException("Unsupported params type!");
                }
            }
        }

        /* renamed from: e */
        public final C7101e m8051e() {
            return new C7101e(this);
        }
    }
}

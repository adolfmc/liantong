package com.baidu.p166vi;

import android.net.NetworkInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.i */
/* loaded from: E:\10762272_dexfile_execute.dex */
/* synthetic */ class C3335i {

    /* renamed from: a */
    static final /* synthetic */ int[] f8210a = new int[NetworkInfo.State.values().length];

    static {
        try {
            f8210a[NetworkInfo.State.CONNECTED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f8210a[NetworkInfo.State.CONNECTING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f8210a[NetworkInfo.State.DISCONNECTED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f8210a[NetworkInfo.State.DISCONNECTING.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f8210a[NetworkInfo.State.SUSPENDED.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
    }
}

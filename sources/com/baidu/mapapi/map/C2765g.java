package com.baidu.mapapi.map;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.baidu.mapsdkplatform.comapi.map.InterfaceC2920ab;
import java.util.concurrent.locks.Lock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2765g implements InterfaceC2920ab {

    /* renamed from: a */
    final /* synthetic */ BaiduMap f6546a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2765g(BaiduMap baiduMap) {
        this.f6546a = baiduMap;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.InterfaceC2920ab
    /* renamed from: a */
    public Bundle mo18398a(int i, int i2, int i3, Context context) {
        Lock lock;
        Lock lock2;
        TileOverlay tileOverlay;
        Lock lock3;
        TileOverlay tileOverlay2;
        lock = this.f6546a.f5940M;
        lock.lock();
        try {
            tileOverlay = this.f6546a.f5937J;
            if (tileOverlay != null) {
                tileOverlay2 = this.f6546a.f5937J;
                Tile m18842a = tileOverlay2.m18842a(i, i2, i3);
                StringBuilder sb = new StringBuilder();
                sb.append("mapLayerDataReq tile t == null = ");
                sb.append(m18842a == null);
                Log.e("SDKTileLayer", sb.toString());
                if (m18842a != null) {
                    return m18842a.toBundle();
                }
            }
            lock3 = this.f6546a.f5940M;
            lock3.unlock();
            return null;
        } finally {
            lock2 = this.f6546a.f5940M;
            lock2.unlock();
        }
    }
}

package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapsdkplatform.comapi.map.InterfaceC2937m;
import java.util.concurrent.locks.Lock;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2764f implements InterfaceC2937m {

    /* renamed from: a */
    final /* synthetic */ BaiduMap f6545a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2764f(BaiduMap baiduMap) {
        this.f6545a = baiduMap;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.InterfaceC2937m
    /* renamed from: a */
    public Bundle mo18227a(int i, int i2) {
        Lock lock;
        Lock lock2;
        HeatMap heatMap;
        BaiduMap.OnHeatMapDrawFrameCallBack onHeatMapDrawFrameCallBack;
        HeatMap heatMap2;
        BaiduMap.OnHeatMapDrawFrameCallBack onHeatMapDrawFrameCallBack2;
        lock = this.f6545a.f5939L;
        lock.lock();
        try {
            heatMap = this.f6545a.f5938K;
            if (heatMap != null) {
                onHeatMapDrawFrameCallBack = this.f6545a.f5935H;
                if (onHeatMapDrawFrameCallBack != null) {
                    onHeatMapDrawFrameCallBack2 = this.f6545a.f5935H;
                    onHeatMapDrawFrameCallBack2.frameIndex(i);
                }
                heatMap2 = this.f6545a.f5938K;
                HeatMapData data = heatMap2.getData(i, i2);
                if (data == null) {
                    return null;
                }
                return data.toBundle();
            }
            return null;
        } finally {
            lock2 = this.f6545a.f5939L;
            lock2.unlock();
        }
    }
}

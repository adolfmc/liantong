package com.baidu.location;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class BDAbstractLocationListener {
    public void onConnectHotSpotMessage(String str, int i) {
    }

    public void onLocDiagnosticMessage(int i, int i2, String str) {
    }

    public void onReceiveLocString(String str) {
    }

    public abstract void onReceiveLocation(BDLocation bDLocation);

    public void onReceiveVdrLocation(BDLocation bDLocation) {
    }
}

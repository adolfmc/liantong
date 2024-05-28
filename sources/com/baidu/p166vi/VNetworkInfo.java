package com.baidu.p166vi;

import android.net.NetworkInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.VNetworkInfo */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class VNetworkInfo {
    public int state;
    public int type;
    public String typename;

    public VNetworkInfo(NetworkInfo networkInfo) {
        int i;
        this.typename = networkInfo.getTypeName();
        this.type = networkInfo.getType();
        switch (C3335i.f8210a[networkInfo.getState().ordinal()]) {
            case 1:
                i = 2;
                break;
            case 2:
                i = 1;
                break;
            default:
                i = 0;
                break;
        }
        this.state = i;
    }
}

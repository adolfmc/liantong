package com.baidu.mapsdkplatform.comapi.map;

import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.t */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2944t {

    /* renamed from: a */
    private static final String f7350a = "t";

    /* renamed from: b */
    private InterfaceC2943s f7351b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m18201a(Message message) {
        if (message.what != 65289) {
            return;
        }
        int i = message.arg1;
        if (i != 12) {
            switch (i) {
                case -1:
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    break;
                default:
                    switch (i) {
                        case 101:
                        case 102:
                            break;
                        default:
                            return;
                    }
            }
        }
        InterfaceC2943s interfaceC2943s = this.f7351b;
        if (interfaceC2943s != null) {
            interfaceC2943s.mo18202a(message.arg1, message.arg2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m18200a(InterfaceC2943s interfaceC2943s) {
        this.f7351b = interfaceC2943s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m18199b(InterfaceC2943s interfaceC2943s) {
        this.f7351b = null;
    }
}

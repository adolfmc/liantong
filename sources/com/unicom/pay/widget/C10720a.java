package com.unicom.pay.widget;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnectionWrapper;

/* renamed from: com.unicom.pay.widget.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10720a extends InputConnectionWrapper {

    /* renamed from: a */
    public InterfaceC10721a f20511a;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10721a {
        /* renamed from: a */
        boolean m6035a();
    }

    public C10720a() {
        super(null, true);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i, int i2) {
        InterfaceC10721a interfaceC10721a = this.f20511a;
        if (interfaceC10721a == null || !interfaceC10721a.m6035a()) {
            return super.deleteSurroundingText(i, i2);
        }
        return true;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean sendKeyEvent(KeyEvent keyEvent) {
        InterfaceC10721a interfaceC10721a;
        if (keyEvent.getKeyCode() == 67 && keyEvent.getAction() == 0 && (interfaceC10721a = this.f20511a) != null && interfaceC10721a.m6035a()) {
            return true;
        }
        return super.sendKeyEvent(keyEvent);
    }
}

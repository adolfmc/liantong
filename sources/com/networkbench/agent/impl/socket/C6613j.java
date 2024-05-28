package com.networkbench.agent.impl.socket;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6613j {

    /* renamed from: d */
    private static final InterfaceC6393e f17012d = C6394f.m10150a();

    /* renamed from: a */
    private boolean f17013a;

    /* renamed from: b */
    private long f17014b;

    /* renamed from: c */
    private String f17015c = "";

    /* renamed from: e */
    private int f17016e = -1;

    /* renamed from: f */
    private NBSTransactionState f17017f;

    /* renamed from: a */
    public void m9252a(boolean z) {
        this.f17013a = z;
    }

    /* renamed from: a */
    public void m9253a(String str) {
        if (str == null) {
            return;
        }
        this.f17015c = str;
    }

    /* renamed from: a */
    public void m9254a(NBSTransactionState nBSTransactionState) {
        if (nBSTransactionState == null) {
            f17012d.mo10122a("transactionState == null aaaaaaaa");
        }
        this.f17017f = nBSTransactionState;
    }

    /* renamed from: a */
    public NBSTransactionState m9257a() {
        return this.f17017f;
    }

    /* renamed from: a */
    public void m9256a(int i) {
        NBSTransactionState nBSTransactionState = this.f17017f;
        if (nBSTransactionState != null) {
            nBSTransactionState.setTcpHandShakeTime(i);
        }
    }

    /* renamed from: a */
    public void m9255a(long j) {
        this.f17014b = j;
        this.f17013a = false;
    }

    /* renamed from: b */
    public void m9250b(long j) {
        long j2 = j - this.f17014b;
        int i = this.f17016e;
        int i2 = (int) (j2 - i);
        C6621r.m9200a(this.f17015c, i, i2 > 0 ? i2 : 0);
        if (i2 <= 0) {
            i2 = 0;
        }
        m9251b(i2);
    }

    /* renamed from: b */
    private void m9251b(int i) {
        if (this.f17017f == null || TextUtils.isEmpty(this.f17015c)) {
            return;
        }
        f17012d.mo10122a("begin set transaction first remain time");
        this.f17017f.setFirstPacketPeriod(this.f17016e);
        this.f17017f.setRemainPackage(i);
    }

    /* renamed from: c */
    public void m9249c(long j) {
        long j2 = this.f17014b;
        if (j <= j2) {
            InterfaceC6393e interfaceC6393e = f17012d;
            interfaceC6393e.mo10122a("get first package firstReadTime:" + j + ", lastWriteStamp:" + this.f17014b + ", hostName:" + this.f17015c);
        } else if (j - j2 >= 20000) {
            InterfaceC6393e interfaceC6393e2 = f17012d;
            interfaceC6393e2.mo10115e("first package is too big, firstReadTime:" + j + ", lastWriteStamp:" + this.f17014b + ", hostName:" + this.f17015c);
        } else {
            if (!this.f17013a) {
                this.f17013a = true;
                this.f17016e = (int) (j - j2);
            }
            m9250b(j);
        }
    }
}

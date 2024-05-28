package com.sdk.p291g;

import android.content.Context;
import com.sdk.base.framework.bean.AInfo;
import com.sdk.base.framework.bean.DataInfo;
import com.sdk.base.framework.bean.KInfo;
import com.sdk.base.framework.bean.MobileKInfo;
import com.sdk.base.framework.bean.PInfo;
import com.sdk.base.framework.bean.SInfo;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.module.config.BaseConfig;
import com.sdk.p289e.InterfaceC6991a;
import com.sdk.p290f.C6993a;
import com.sdk.p290f.C6998d;
import com.sdk.p290f.InterfaceC6997c;
import com.sdk.p300p.C7032f;
import com.sdk.p304t.C7039a;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sdk.g.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C7005b<T> {

    /* renamed from: j */
    public static final String f18143j = "com.sdk.g.b";

    /* renamed from: k */
    public static final boolean f18144k = C6998d.f18135a;

    /* renamed from: a */
    public PInfo f18145a;

    /* renamed from: b */
    public AInfo f18146b;

    /* renamed from: c */
    public SInfo f18147c;

    /* renamed from: d */
    public ArrayList<KInfo> f18148d;

    /* renamed from: e */
    public InterfaceC6991a<T> f18149e;

    /* renamed from: f */
    public Context f18150f;

    /* renamed from: g */
    public String f18151g;

    /* renamed from: h */
    public InterfaceC6997c f18152h;

    /* renamed from: i */
    public String f18153i;

    public C7005b(Context context, InterfaceC6991a<T> interfaceC6991a, List<String> list, InterfaceC6997c interfaceC6997c) {
        this.f18150f = context;
        this.f18149e = interfaceC6991a;
        this.f18152h = interfaceC6997c;
    }

    /* renamed from: a */
    public final String m8165a(DataInfo dataInfo, String str, String str2) {
        String str3;
        try {
            if (this.f18146b == null) {
                this.f18146b = C6993a.m8169a(this.f18150f, C7039a.f18203e);
            }
            if (this.f18147c == null) {
                SInfo sInfo = new SInfo();
                sInfo.setN(BaseConfig.f18082n);
                sInfo.setC(BaseConfig.f18080c);
                sInfo.setV(BaseConfig.f18083v);
                sInfo.setCm(BaseConfig.f18081cm);
                this.f18147c = sInfo;
            }
            if (this.f18148d == null) {
                this.f18148d = new ArrayList<>();
            }
            ArrayList arrayList = new ArrayList();
            MobileKInfo mobileKInfo = new MobileKInfo();
            mobileKInfo.setCn(C6993a.m8170a(this.f18150f));
            arrayList.add(mobileKInfo);
            if (this.f18145a == null) {
                this.f18145a = C6993a.m8171a();
            }
            str3 = "{app:" + this.f18146b + ",sdk:" + this.f18147c + ",device:" + this.f18145a + ",sim:" + arrayList + ",data:" + dataInfo + "}";
        } catch (Exception e) {
            LogUtils.m8186e(f18143j, e.toString(), Boolean.valueOf(f18144k));
            str3 = null;
        }
        return C7032f.m8136a().f18193a.mo8132a(str, str2, str3);
    }

    /* renamed from: a */
    public void m8167a(int i, int i2, String str) {
        InterfaceC6991a<T> interfaceC6991a = this.f18149e;
        if (interfaceC6991a != null) {
            interfaceC6991a.mo8108a(i, i2, str);
            this.f18149e = null;
        }
    }

    /* renamed from: a */
    public void m8166a(int i, String str, int i2, T t, String str2) {
        InterfaceC6991a<T> interfaceC6991a = this.f18149e;
        if (interfaceC6991a != null) {
            interfaceC6991a.onSuccess(i, str, i2, t, str2);
            this.f18149e = null;
        }
    }
}

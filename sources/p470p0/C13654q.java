package p470p0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.bean.WPRptBean;
import com.unicom.pay.common.callback.DataCallback;
import java.lang.reflect.Type;
import java.util.HashMap;
import p411o.AbstractC12375a;
import p473r.C13707b;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.q */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13654q {

    /* renamed from: p0.q$a */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C13655a extends AbstractC14257d<WPRptBean> {

        /* renamed from: c */
        public final /* synthetic */ DataCallback f27495c;

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: p0.q$a$a */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        class C13656a extends TypeToken<WPResult<WPRptBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13655a(AbstractC12375a abstractC12375a, DataCallback dataCallback) {
            super(abstractC12375a);
            this.f27495c = dataCallback;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPRptBean> wPResult) {
            super.mo10a(wPResult);
            this.f27495c.onResult("");
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPRptBean> wPResult) {
            if (wPResult.getData() != null) {
                this.f27495c.onResult(wPResult.getData().getJumpUrl());
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C13656a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            super.mo11d(str);
            this.f27495c.onResult("");
        }
    }

    /* renamed from: p0.q$b */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C13657b extends AbstractC14257d<WPRptBean> {

        /* renamed from: c */
        public final /* synthetic */ DataCallback f27496c;

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: p0.q$b$a */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        class C13658a extends TypeToken<WPResult<WPRptBean>> {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13657b(AbstractC12375a abstractC12375a, DataCallback dataCallback) {
            super(abstractC12375a);
            this.f27496c = dataCallback;
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: a */
        public final void mo10a(WPResult<WPRptBean> wPResult) {
            super.mo10a(wPResult);
            this.f27496c.onResult("");
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: b */
        public final void mo9b(WPResult<WPRptBean> wPResult) {
            if (wPResult.getData() != null) {
                this.f27496c.onResult(wPResult.getData().getJumpUrl());
            }
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: c */
        public final Type mo8c() {
            return new C13658a().getType();
        }

        @Override // p482w.AbstractC14257d
        /* renamed from: d */
        public final void mo11d(String str) {
            super.mo11d(str);
            this.f27496c.onResult("");
        }
    }

    /* renamed from: a */
    public static void m172a(AbstractC12375a abstractC12375a, String str, DataCallback dataCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("tradeOrderNo", str);
        C14255c.C14256a.f27777a.m29a(C14262f.f27823m, hashMap, false, new C13657b(abstractC12375a, dataCallback));
    }

    /* renamed from: a */
    public static void m171a(AbstractC12375a abstractC12375a, String str, String str2, DataCallback dataCallback) {
        if (!"212".equals(C10546a.C10576i.f20125a.f20060j)) {
            C14255c.C14256a.f27777a.m29a(C14262f.f27806S, C13707b.m126a("jumpUrl", str, "tradeOrderNo", str2), false, new C13655a(abstractC12375a, dataCallback));
            return;
        }
        dataCallback.onResult(str + "&isSdk=1");
    }
}

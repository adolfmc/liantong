package p407m0;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: m0.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12317f extends AbstractC14257d {

    /* renamed from: c */
    public final /* synthetic */ String f24932c;

    /* renamed from: d */
    public final /* synthetic */ C12319g f24933d;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: m0.f$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C12318a extends TypeToken<WPResult> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C12317f(C12319g c12319g, AbstractC12375a abstractC12375a, String str) {
        super(abstractC12375a, 1);
        this.f24933d = c12319g;
        this.f24932c = str;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult wPResult) {
        if (this.f24933d.m1795b()) {
            if ("4066041007".equals(wPResult.getCode())) {
                if (TextUtils.isEmpty(this.f24932c)) {
                    return;
                }
                this.f24933d.m1798a().mo1841c(wPResult.getMsg());
            } else if ("4066041002".equals(wPResult.getCode())) {
                this.f24933d.m1798a().mo1844b();
                this.f24933d.m1798a().mo1845a(wPResult.getMsg());
            } else if ("4066049811".equals(wPResult.getCode())) {
                this.f24933d.m1798a().mo1843b(wPResult.getMsg());
            } else {
                super.mo10a(wPResult);
                this.f24933d.m1798a().mo1847a();
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult wPResult) {
        if (this.f24933d.m1795b()) {
            this.f24933d.m1798a().mo1839w();
            this.f24933d.m1798a().mo1840d();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C12318a().getType();
    }
}

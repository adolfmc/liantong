package p385d0;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPNoticeInfoBean;
import java.util.ArrayList;
import p387e0.C11882r;
import p393h.AbstractC12007d;
import p470p0.C13659r;

@NBSInstrumented
/* renamed from: d0.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11819f extends AbstractC12007d implements C11882r.InterfaceC11884b {

    /* renamed from: k */
    public static final /* synthetic */ int f24075k = 0;

    /* renamed from: h */
    public RecyclerView f24076h;

    /* renamed from: i */
    public C11882r f24077i;

    /* renamed from: j */
    public ArrayList<WPNoticeInfoBean> f24078j = new ArrayList<>();

    @Override // p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return "公告";
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return "wp195";
    }

    @Override // p393h.AbstractC12007d
    /* renamed from: a */
    public final void mo1989a() {
        dismiss();
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C10531R.C10535layout.up_notice_content_dialog, viewGroup, false);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(C13659r.m168b(getContext()), C13659r.m169a(getContext()) / 2));
        m1988a(inflate, getString(C10531R.string.up_notice_dialog_title));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C10531R.C10534id.up_notice_content_rv);
        this.f24076h = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        C11882r c11882r = new C11882r();
        this.f24077i = c11882r;
        this.f24076h.setAdapter(c11882r);
        C11882r c11882r2 = this.f24077i;
        ArrayList<WPNoticeInfoBean> arrayList = this.f24078j;
        c11882r2.f24208a.clear();
        if (arrayList != null) {
            c11882r2.f24208a.addAll(arrayList);
        }
        if (!c11882r2.f24208a.isEmpty()) {
            c11882r2.f24208a.get(0).setExpand(true);
        }
        c11882r2.notifyDataSetChanged();
        m1987a("点击-公告");
        this.f24077i.f24209b = this;
        return inflate;
    }

    @Override // p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return "98U01170wp195";
    }
}

package p412o0;

import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.helper.ItemTouchHelper;
import cn.ltzf.passguard.C1730a;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import com.unicom.pay.qpay.setting.bean.WPBankBean;
import com.unicom.pay.qpay.setting.p361ui.WPQPaySettingActivity;
import java.util.Iterator;
import p470p0.C13652o;

/* renamed from: o0.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12378a extends ItemTouchHelper.Callback {

    /* renamed from: a */
    public boolean f25035a = false;

    /* renamed from: b */
    public InterfaceC12389f f25036b;

    /* renamed from: c */
    public InterfaceC12379a f25037c;

    /* renamed from: d */
    public boolean f25038d;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: o0.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC12379a {
    }

    public C12378a(InterfaceC12389f interfaceC12389f) {
        this.f25036b = interfaceC12389f;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public final void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        ((InterfaceC12384c) viewHolder).mo1785b();
        InterfaceC12379a interfaceC12379a = this.f25037c;
        if (interfaceC12379a == null || !this.f25038d) {
            return;
        }
        WPQPaySettingActivity wPQPaySettingActivity = (WPQPaySettingActivity) interfaceC12379a;
        wPQPaySettingActivity.m6063c(false);
        wPQPaySettingActivity.m1991k("拖动-设置页-调整扣款顺序");
        this.f25038d = false;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public final int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return ItemTouchHelper.Callback.makeMovementFlags(3, 48);
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public final boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public final boolean isLongPressDragEnabled() {
        return this.f25035a;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public final boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        String str;
        WPBankBean wPBankBean;
        String str2;
        this.f25038d = true;
        InterfaceC12389f interfaceC12389f = this.f25036b;
        int adapterPosition = viewHolder.getAdapterPosition();
        int adapterPosition2 = viewHolder2.getAdapterPosition();
        C12380b c12380b = (C12380b) interfaceC12389f;
        c12380b.getClass();
        C13652o.m174a("wcy---", "-------onItemMove  Start");
        C13652o.m174a("wcy---", "fromPosition---" + adapterPosition + ",toPosition---" + adapterPosition2);
        WPBankBean wPBankBean2 = (WPBankBean) c12380b.f25040b.get(adapterPosition);
        StringBuilder m22016a = C1730a.m22016a("插入位置--");
        if (WPPayInfoBean.f20227YE.equals(wPBankBean2.getPayType())) {
            str = "余额";
        } else {
            str = wPBankBean2.getBankName() + wPBankBean2.getMaskNum();
        }
        m22016a.append(str);
        C13652o.m174a("wcy---", m22016a.toString());
        int i = adapterPosition2 > adapterPosition ? adapterPosition2 + 1 : adapterPosition2;
        C13652o.m174a("wcy---", "插入位置--" + i);
        c12380b.f25040b.add(i, wPBankBean2);
        c12380b.f25040b.remove(adapterPosition > adapterPosition2 ? adapterPosition + 1 : adapterPosition);
        C13652o.m174a("wcy---", "----logStart");
        Iterator it = c12380b.f25040b.iterator();
        while (it.hasNext()) {
            if (WPPayInfoBean.f20227YE.equals(((WPBankBean) it.next()).getPayType())) {
                str2 = "余额";
            } else {
                str2 = wPBankBean.getBankName() + wPBankBean.getMaskNum();
            }
            C13652o.m174a("wcy---", str2);
        }
        C13652o.m174a("wcy---", "----logEnd");
        c12380b.notifyItemMoved(adapterPosition, adapterPosition2);
        C13652o.m174a("wcy---", "-------onItemMove  End");
        return true;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public final void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (i != 0) {
            ((InterfaceC12384c) viewHolder).mo1786a();
        }
        super.onSelectedChanged(viewHolder, i);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public final void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        InterfaceC12389f interfaceC12389f = this.f25036b;
        int adapterPosition = viewHolder.getAdapterPosition();
        C12380b c12380b = (C12380b) interfaceC12389f;
        c12380b.f25040b.remove(adapterPosition);
        c12380b.notifyItemChanged(adapterPosition);
    }
}

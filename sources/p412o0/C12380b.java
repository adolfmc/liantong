package p412o0;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import com.unicom.pay.qpay.setting.bean.WPBankBean;
import java.util.ArrayList;
import java.util.List;
import p470p0.C13647j;

/* renamed from: o0.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12380b extends RecyclerView.Adapter<C12382b> implements InterfaceC12389f {

    /* renamed from: a */
    public Context f25039a;

    /* renamed from: b */
    public List<WPBankBean> f25040b = new ArrayList();

    /* renamed from: c */
    public ItemTouchHelper f25041c;

    /* renamed from: d */
    public C12378a f25042d;

    /* renamed from: e */
    public InterfaceC12383c f25043e;

    @NBSInstrumented
    /* renamed from: o0.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class View$OnLongClickListenerC12381a implements View.OnLongClickListener {

        /* renamed from: a */
        public final /* synthetic */ C12382b f25044a;

        public View$OnLongClickListenerC12381a(C12382b c12382b) {
            this.f25044a = c12382b;
        }

        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            NBSActionInstrumentation.onLongClickEventEnter(view, this);
            C12380b.this.f25041c.startDrag(this.f25044a);
            InterfaceC12383c interfaceC12383c = C12380b.this.f25043e;
            NBSActionInstrumentation.onLongClickEventExit();
            return false;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: o0.b$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C12382b extends RecyclerView.ViewHolder implements InterfaceC12384c {

        /* renamed from: a */
        public ImageView f25046a;

        /* renamed from: b */
        public TextView f25047b;

        /* renamed from: c */
        public ImageView f25048c;

        /* renamed from: d */
        public View f25049d;

        public C12382b(@NonNull View view) {
            super(view);
            this.f25046a = (ImageView) view.findViewById(C10531R.C10534id.wopay_bank_logo_iv);
            this.f25047b = (TextView) view.findViewById(C10531R.C10534id.wopay_bank_name_tv);
            this.f25048c = (ImageView) view.findViewById(C10531R.C10534id.wopay_bank_drag_iv);
            this.f25049d = view.findViewById(C10531R.C10534id.wopay_qpay_setting_line);
        }

        @Override // p412o0.InterfaceC12384c
        /* renamed from: a */
        public final void mo1786a() {
            this.itemView.setBackgroundColor(-1);
        }

        @Override // p412o0.InterfaceC12384c
        /* renamed from: b */
        public final void mo1785b() {
            this.itemView.setBackgroundColor(0);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: o0.b$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC12383c {
    }

    public C12380b(Context context) {
        this.f25039a = context;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    /* renamed from: a */
    public final void m1788a(List<WPBankBean> list) {
        this.f25040b.clear();
        this.f25040b.addAll(list);
        notifyDataSetChanged();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @SuppressLint({"ClickableViewAccessibility"})
    /* renamed from: a */
    public final void onBindViewHolder(@NonNull C12382b c12382b, int i) {
        try {
            WPBankBean wPBankBean = (WPBankBean) this.f25040b.get(i);
            c12382b.f25049d.setVisibility(i < getItemCount() - 1 ? 0 : 8);
            C13647j.m178a(this.f25039a, wPBankBean.getIconUrl(), c12382b.f25046a, C10531R.C10533drawable.wp_bank_default);
            if (WPPayInfoBean.f20227YE.equals(wPBankBean.getPayType())) {
                c12382b.f25047b.setText(!TextUtils.isEmpty(wPBankBean.getBankName()) ? wPBankBean.getBankName() : "余额");
            } else {
                c12382b.f25047b.setText(this.f25039a.getString(C10531R.string.wp_setting_bank_name, wPBankBean.getBankName(), wPBankBean.getMaskNum()));
            }
            C12378a c12378a = this.f25042d;
            if (c12378a == null || !c12378a.f25035a) {
                return;
            }
            c12382b.f25048c.setOnLongClickListener(new View$OnLongClickListenerC12381a(c12382b));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.unicom.pay.qpay.setting.bean.WPBankBean>, java.util.ArrayList] */
    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        ?? r0 = this.f25040b;
        if (r0 == 0) {
            return 0;
        }
        return r0.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final C12382b onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new C12382b(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_method_bank_item, viewGroup, false));
    }
}

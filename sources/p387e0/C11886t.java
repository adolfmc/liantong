package p387e0;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import java.util.ArrayList;
import java.util.List;
import p470p0.C13647j;

/* renamed from: e0.t */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11886t extends RecyclerView.Adapter<C11888b> {

    /* renamed from: b */
    public Context f24218b;

    /* renamed from: d */
    public InterfaceC11887a f24220d;

    /* renamed from: c */
    public int f24219c = -1;

    /* renamed from: e */
    public boolean f24221e = true;

    /* renamed from: f */
    public boolean f24222f = false;

    /* renamed from: a */
    public List<WPPayInfoBean> f24217a = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.t$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11887a {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.t$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11888b extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public ImageView f24223a;

        /* renamed from: b */
        public ImageView f24224b;

        /* renamed from: c */
        public TextView f24225c;

        /* renamed from: d */
        public TextView f24226d;

        /* renamed from: e */
        public ImageView f24227e;

        /* renamed from: f */
        public View f24228f;

        public C11888b(@NonNull View view) {
            super(view);
            this.f24223a = (ImageView) view.findViewById(C10531R.C10534id.wopay_other_pay_method_logo_iv);
            this.f24224b = (ImageView) view.findViewById(C10531R.C10534id.wopay_other_pay_method_second_logo_iv);
            this.f24225c = (TextView) view.findViewById(C10531R.C10534id.wopay_other_pay_method_name_tv);
            this.f24226d = (TextView) view.findViewById(C10531R.C10534id.wopay_other_pay_method_discount_tv);
            this.f24227e = (ImageView) view.findViewById(C10531R.C10534id.wopay_other_pay_method_cb);
            this.f24228f = view.findViewById(C10531R.C10534id.up_other_method_line);
        }
    }

    public C11886t(Context context) {
        this.f24218b = context;
    }

    /* renamed from: a */
    public final void m2061a() {
        int i = this.f24219c;
        this.f24219c = -2;
        notifyItemChanged(i);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List<com.unicom.pay.normal.order.bean.WPPayInfoBean>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List<com.unicom.pay.normal.order.bean.WPPayInfoBean>, java.util.ArrayList] */
    /* renamed from: a */
    public final void m2060a(List<WPPayInfoBean> list) {
        if (this.f24222f) {
            this.f24222f = false;
        } else {
            this.f24219c = -1;
        }
        this.f24217a.clear();
        if (list != null) {
            this.f24217a.addAll(list);
        }
        notifyDataSetChanged();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.unicom.pay.normal.order.bean.WPPayInfoBean>, java.util.ArrayList] */
    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        ?? r0 = this.f24217a;
        if (r0 == 0) {
            return 0;
        }
        return r0.size();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.unicom.pay.normal.order.bean.WPPayInfoBean>, java.util.ArrayList] */
    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull C11888b c11888b, int i) {
        C11888b c11888b2 = c11888b;
        try {
            WPPayInfoBean wPPayInfoBean = (WPPayInfoBean) this.f24217a.get(i);
            C13647j.m178a(this.f24218b, wPPayInfoBean.getIconUrl(), c11888b2.f24223a, C10531R.C10533drawable.up_default_method_logo);
            c11888b2.f24224b.setVisibility(!TextUtils.isEmpty(wPPayInfoBean.getSecondIconUrl()) ? 0 : 8);
            C13647j.m178a(this.f24218b, wPPayInfoBean.getSecondIconUrl(), c11888b2.f24224b, C10531R.C10533drawable.up_default_method_logo);
            c11888b2.f24225c.setText(wPPayInfoBean.getToolName());
            if (!TextUtils.isEmpty(wPPayInfoBean.getToolDiscountMsg())) {
                c11888b2.f24226d.setText(wPPayInfoBean.getToolDiscountMsg());
                c11888b2.f24226d.setVisibility(0);
            } else {
                c11888b2.f24226d.setVisibility(8);
            }
            Glide.with(this.f24218b).clear(c11888b2.f24227e);
            if ((wPPayInfoBean.isChecked() && this.f24219c == -1) || this.f24219c == i) {
                this.f24219c = c11888b2.getAdapterPosition();
                c11888b2.f24227e.setSelected(true);
            } else {
                c11888b2.f24227e.setSelected(false);
            }
            c11888b2.f24228f.setVisibility(8);
            c11888b2.itemView.setOnClickListener(new View$OnClickListenerC11885s(this, c11888b2, wPPayInfoBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final C11888b onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new C11888b(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_order_other_pay_method_item, viewGroup, false));
    }
}

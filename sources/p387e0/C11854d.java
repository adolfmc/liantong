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
import com.unicom.pay.normal.discount.bean.WPDiscountDetailBean;
import java.util.ArrayList;
import java.util.List;
import p470p0.C13662t;

/* renamed from: e0.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11854d extends RecyclerView.Adapter<C11855a> {

    /* renamed from: a */
    public List<WPDiscountDetailBean> f24116a;

    /* renamed from: b */
    public int f24117b;

    /* renamed from: c */
    public InterfaceC11856b f24118c;

    /* renamed from: d */
    public InterfaceC11857c f24119d;

    /* renamed from: e */
    public Context f24120e;

    /* renamed from: f */
    public boolean f24121f = true;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.d$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11855a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f24122a;

        /* renamed from: b */
        public TextView f24123b;

        /* renamed from: c */
        public TextView f24124c;

        /* renamed from: d */
        public TextView f24125d;

        /* renamed from: e */
        public TextView f24126e;

        /* renamed from: f */
        public TextView f24127f;

        /* renamed from: g */
        public ImageView f24128g;

        /* renamed from: h */
        public ImageView f24129h;

        public C11855a(@NonNull View view) {
            super(view);
            this.f24122a = (TextView) view.findViewById(C10531R.C10534id.wopay_dzq_amount_tv);
            this.f24123b = (TextView) view.findViewById(C10531R.C10534id.wopay_discount_name_tv);
            this.f24124c = (TextView) view.findViewById(C10531R.C10534id.wopay_dzq_desc_tv);
            this.f24125d = (TextView) view.findViewById(C10531R.C10534id.wopay_dzq_date_tv);
            this.f24126e = (TextView) view.findViewById(C10531R.C10534id.wopay_dzq_discount_tv);
            this.f24127f = (TextView) view.findViewById(C10531R.C10534id.wopay_dzq_need_bank_tv);
            this.f24128g = (ImageView) view.findViewById(C10531R.C10534id.wopay_discount_right_iv);
            this.f24129h = (ImageView) view.findViewById(C10531R.C10534id.wopay_discount_right_cb);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.d$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11856b {
        /* renamed from: a */
        void mo2065a(WPDiscountDetailBean wPDiscountDetailBean, boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.d$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11857c {
        /* renamed from: a */
        void mo2064a();
    }

    public C11854d(Context context, List<WPDiscountDetailBean> list, int i) {
        this.f24120e = context;
        this.f24116a = list == null ? new ArrayList<>() : list;
        this.f24117b = i;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public final void onBindViewHolder(@NonNull C11855a c11855a, int i) {
        try {
            WPDiscountDetailBean wPDiscountDetailBean = this.f24116a.get(i);
            C13662t c13662t = new C13662t("¥");
            String dzqFaceAmt = wPDiscountDetailBean.getDzqFaceAmt();
            c13662t.m166a();
            c13662t.f27499a = dzqFaceAmt;
            c13662t.f27504f = 1.5f;
            c13662t.m166a();
            c11855a.f24122a.setText(c13662t.f27507i);
            TextView textView = c11855a.f24124c;
            if (textView != null) {
                textView.setText(wPDiscountDetailBean.getAmountDesc());
            }
            c11855a.f24125d.setText(wPDiscountDetailBean.getValidityDate());
            if (TextUtils.isEmpty(wPDiscountDetailBean.getDzqDesc())) {
                c11855a.f24127f.setVisibility(8);
            } else {
                c11855a.f24127f.setText(wPDiscountDetailBean.getDzqDesc());
                c11855a.f24127f.setVisibility(0);
            }
            c11855a.f24123b.setText(wPDiscountDetailBean.getDiscountTitle());
            c11855a.f24123b.getPaint().setFakeBoldText(true);
            if (TextUtils.isEmpty(wPDiscountDetailBean.getDiscountDesc())) {
                c11855a.f24126e.setVisibility(8);
            } else {
                c11855a.f24126e.setText(wPDiscountDetailBean.getDiscountDesc());
                c11855a.f24126e.setVisibility(0);
            }
            c11855a.f24128g.setVisibility(8);
            c11855a.f24129h.setVisibility(0);
            Context context = this.f24120e;
            Glide.with(context).clear(c11855a.f24129h);
            if (wPDiscountDetailBean.isChecked()) {
                c11855a.f24129h.setSelected(true);
            } else {
                c11855a.f24129h.setSelected(false);
            }
            c11855a.itemView.setOnClickListener(new View$OnClickListenerC11853c(this, c11855a, wPDiscountDetailBean));
            c11855a.f24126e.setTextColor(this.f24120e.getResources().getColor(C10531R.C10532color.wp_red_color));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<WPDiscountDetailBean> list = this.f24116a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        return this.f24117b;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final C11855a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return i == 1 ? new C11855a(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_discount_dzq_man_jian_item, viewGroup, false)) : new C11855a(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_discount_dzq_zhi_jian_item, viewGroup, false));
    }
}

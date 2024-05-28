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
import com.unicom.pay.normal.discount.bean.WPDiscountInfoBean;
import java.util.List;
import p470p0.C13647j;

/* renamed from: e0.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11849b extends RecyclerView.Adapter<C11850a> {

    /* renamed from: a */
    public Context f24104a;

    /* renamed from: b */
    public List<WPDiscountInfoBean> f24105b;

    /* renamed from: c */
    public InterfaceC11851b f24106c;

    /* renamed from: d */
    public InterfaceC11852c f24107d;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11850a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public ImageView f24108a;

        /* renamed from: b */
        public TextView f24109b;

        /* renamed from: c */
        public TextView f24110c;

        /* renamed from: d */
        public ImageView f24111d;

        /* renamed from: e */
        public ImageView f24112e;

        public C11850a(@NonNull View view) {
            super(view);
            this.f24108a = (ImageView) view.findViewById(C10531R.C10534id.wopay_discount_logo_iv);
            this.f24109b = (TextView) view.findViewById(C10531R.C10534id.wopay_discount_name_tv);
            this.f24110c = (TextView) view.findViewById(C10531R.C10534id.wopay_discount_discount_tv);
            this.f24111d = (ImageView) view.findViewById(C10531R.C10534id.wopay_discount_right_iv);
            this.f24112e = (ImageView) view.findViewById(C10531R.C10534id.wopay_discount_right_cb);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.b$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11851b {
        /* renamed from: a */
        void mo2067a(WPDiscountInfoBean wPDiscountInfoBean, boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.b$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11852c {
    }

    public C11849b(Context context, List<WPDiscountInfoBean> list) {
        this.f24105b = list;
        this.f24104a = context;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<WPDiscountInfoBean> list = this.f24105b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull C11850a c11850a, int i) {
        C11850a c11850a2 = c11850a;
        try {
            WPDiscountInfoBean wPDiscountInfoBean = this.f24105b.get(i);
            C13647j.m178a(this.f24104a, wPDiscountInfoBean.getIconUrl(), c11850a2.f24108a, C10531R.C10533drawable.wp_bank_default);
            c11850a2.f24109b.setText(wPDiscountInfoBean.getDiscountName());
            if (TextUtils.isEmpty(wPDiscountInfoBean.getDiscountDesc())) {
                c11850a2.f24110c.setVisibility(8);
            } else {
                c11850a2.f24110c.setText(wPDiscountInfoBean.getDiscountDesc());
                c11850a2.f24110c.setVisibility(0);
            }
            if ("2".equals(wPDiscountInfoBean.getEventIconType())) {
                c11850a2.f24111d.setVisibility(0);
                c11850a2.f24112e.setVisibility(8);
            } else {
                ("1".equals(wPDiscountInfoBean.getEventIconType()) ? c11850a2.f24111d : c11850a2.f24111d).setVisibility(8);
                c11850a2.f24112e.setVisibility(0);
            }
            Glide.with(this.f24104a).clear(c11850a2.f24112e);
            if (wPDiscountInfoBean.isChecked()) {
                c11850a2.getAdapterPosition();
                c11850a2.f24112e.setSelected(true);
            } else {
                c11850a2.f24112e.setSelected(false);
            }
            c11850a2.itemView.setOnClickListener(new View$OnClickListenerC11848a(this, wPDiscountInfoBean, c11850a2));
            if (!"0".equals(wPDiscountInfoBean.getIsEnable())) {
                c11850a2.f24110c.setTextColor(this.f24104a.getResources().getColor(C10531R.C10532color.wp_red_color));
                return;
            }
            c11850a2.f24110c.setTextColor(this.f24104a.getResources().getColor(C10531R.C10532color.wp_gray_btn));
            if ("1".equals(wPDiscountInfoBean.getEventIconType())) {
                c11850a2.f24112e.setOnClickListener(null);
                c11850a2.f24112e.setEnabled(false);
                c11850a2.itemView.setOnClickListener(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final C11850a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new C11850a(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_discount_item, viewGroup, false));
    }
}

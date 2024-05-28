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
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPFqInfoBean;
import java.util.List;
import p470p0.C13647j;

/* renamed from: e0.j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11866j extends RecyclerView.Adapter<C11867a> {

    /* renamed from: a */
    public Context f24155a;

    /* renamed from: b */
    public List<WPFqInfoBean> f24156b;

    /* renamed from: c */
    public InterfaceC11868b f24157c;

    /* renamed from: d */
    public InterfaceC11869c f24158d;

    /* renamed from: e */
    public int f24159e = -1;

    /* renamed from: f */
    public int f24160f = -1;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.j$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11867a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public ImageView f24161a;

        /* renamed from: b */
        public TextView f24162b;

        /* renamed from: c */
        public TextView f24163c;

        /* renamed from: d */
        public TextView f24164d;

        /* renamed from: e */
        public ImageView f24165e;

        /* renamed from: f */
        public ImageView f24166f;

        /* renamed from: g */
        public View f24167g;

        /* renamed from: h */
        public View f24168h;

        public C11867a(@NonNull View view) {
            super(view);
            this.f24161a = (ImageView) view.findViewById(C10531R.C10534id.up_instalment_bank_logo_iv);
            this.f24162b = (TextView) view.findViewById(C10531R.C10534id.up_instalment_bank_name_tv);
            this.f24163c = (TextView) view.findViewById(C10531R.C10534id.up_instalment_bank_desc_tv);
            this.f24164d = (TextView) view.findViewById(C10531R.C10534id.up_instalment_bank_discount_tv);
            this.f24165e = (ImageView) view.findViewById(C10531R.C10534id.up_instalment_bank_iv);
            this.f24166f = (ImageView) view.findViewById(C10531R.C10534id.up_instalment_bank_cb);
            this.f24167g = view.findViewById(C10531R.C10534id.line);
            this.f24168h = view.findViewById(C10531R.C10534id.up_instalment_bank_blank_v);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.j$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11868b {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.j$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11869c {
    }

    public C11866j(Context context, List<WPFqInfoBean> list) {
        this.f24155a = context;
        this.f24156b = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<WPFqInfoBean> list = this.f24156b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull C11867a c11867a, int i) {
        C11867a c11867a2 = c11867a;
        try {
            WPFqInfoBean wPFqInfoBean = this.f24156b.get(i);
            C13647j.m178a(this.f24155a, wPFqInfoBean.getIconUrl(), c11867a2.f24161a, WPFqInfoBean.WFQ_XYK.equals(wPFqInfoBean.getType()) ? C10531R.C10533drawable.wp_bank_default : C10531R.C10533drawable.up_default_method_logo);
            c11867a2.f24162b.setText(wPFqInfoBean.getMethodName());
            if (!TextUtils.isEmpty(wPFqInfoBean.getMethodDesc())) {
                c11867a2.f24163c.setVisibility(0);
                c11867a2.f24163c.setText(wPFqInfoBean.getMethodDesc());
            } else {
                c11867a2.f24163c.setVisibility(8);
            }
            if (!TextUtils.isEmpty(wPFqInfoBean.getMethodDiscount())) {
                c11867a2.f24164d.setVisibility(0);
                c11867a2.f24164d.setText(wPFqInfoBean.getMethodDiscount());
            } else {
                c11867a2.f24164d.setVisibility(8);
            }
            if ((wPFqInfoBean.isDefaultChecked() && this.f24159e == -1) || this.f24159e == i) {
                this.f24159e = c11867a2.getAdapterPosition();
                c11867a2.f24166f.setSelected(true);
            } else {
                c11867a2.f24166f.setSelected(false);
            }
            if (wPFqInfoBean.isShowNext()) {
                c11867a2.f24165e.setVisibility(0);
                c11867a2.f24166f.setVisibility(8);
            } else {
                if (wPFqInfoBean.isDefaultChecked()) {
                    c11867a2.f24165e.setVisibility(8);
                } else {
                    c11867a2.f24165e.setVisibility(8);
                }
                c11867a2.f24166f.setVisibility(0);
            }
            c11867a2.itemView.setOnClickListener(new View$OnClickListenerC11864h(this, wPFqInfoBean, c11867a2));
            c11867a2.f24166f.setOnClickListener(new View$OnClickListenerC11865i(this, wPFqInfoBean, c11867a2));
            if (this.f24160f - 1 == i) {
                c11867a2.f24168h.setVisibility(0);
                c11867a2.f24167g.setVisibility(8);
                return;
            }
            c11867a2.f24168h.setVisibility(8);
            c11867a2.f24167g.setVisibility(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final C11867a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new C11867a(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.up_instalment_bank_method_item, viewGroup, false));
    }
}

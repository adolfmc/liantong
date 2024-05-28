package p412o0;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.unicom.pay.C10531R;
import com.unicom.pay.qpay.setting.bean.WPLimitBean;
import java.util.List;

/* renamed from: o0.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12386e extends RecyclerView.Adapter<C12387a> {

    /* renamed from: a */
    public List<WPLimitBean> f25053a;

    /* renamed from: b */
    public String f25054b;

    /* renamed from: c */
    public int f25055c = -1;

    /* renamed from: d */
    public Context f25056d;

    /* renamed from: e */
    public InterfaceC12388b f25057e;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: o0.e$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C12387a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public LinearLayout f25058a;

        /* renamed from: b */
        public TextView f25059b;

        /* renamed from: c */
        public ImageView f25060c;

        public C12387a(@NonNull View view) {
            super(view);
            this.f25058a = (LinearLayout) view.findViewById(C10531R.C10534id.wopay_limit_item_ll);
            this.f25059b = (TextView) view.findViewById(C10531R.C10534id.wopay_limit_amount_tv);
            this.f25060c = (ImageView) view.findViewById(C10531R.C10534id.wopay_limit_item_iv);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: o0.e$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC12388b {
    }

    public C12386e(Context context, List<WPLimitBean> list, String str) {
        this.f25056d = context;
        this.f25053a = list;
        this.f25054b = str;
    }

    /* renamed from: a */
    public final WPLimitBean m1784a() {
        return this.f25053a.get(this.f25055c);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<WPLimitBean> list = this.f25053a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull C12387a c12387a, int i) {
        C12387a c12387a2 = c12387a;
        try {
            WPLimitBean wPLimitBean = this.f25053a.get(i);
            c12387a2.f25059b.setText(this.f25056d.getString(C10531R.string.wp_limit_amount, wPLimitBean.getPayLimit()));
            if (this.f25055c == -1 && this.f25054b.equals(wPLimitBean.getPayLimit())) {
                this.f25055c = c12387a2.getAdapterPosition();
            }
            if (i == this.f25055c) {
                c12387a2.f25060c.setSelected(true);
            } else {
                c12387a2.f25060c.setSelected(false);
            }
            c12387a2.f25058a.setOnClickListener(new View$OnClickListenerC12385d(this, c12387a2, wPLimitBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final C12387a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new C12387a(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_setting_limit_item, viewGroup, false));
    }
}

package p387e0;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPToolFqNumInfoBean;
import java.util.List;

/* renamed from: e0.p */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11878p extends RecyclerView.Adapter<C11879a> {

    /* renamed from: a */
    public Context f24199a;

    /* renamed from: b */
    public List<WPToolFqNumInfoBean> f24200b;

    /* renamed from: c */
    public InterfaceC11880b f24201c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.p$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11879a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public LinearLayout f24202a;

        /* renamed from: b */
        public TextView f24203b;

        /* renamed from: c */
        public TextView f24204c;

        public C11879a(@NonNull View view) {
            super(view);
            this.f24202a = (LinearLayout) view.findViewById(C10531R.C10534id.up_instalment_num_item_ll);
            this.f24203b = (TextView) view.findViewById(C10531R.C10534id.up_instalment_num_item_title_tv);
            this.f24204c = (TextView) view.findViewById(C10531R.C10534id.up_instalment_num_item_desc_tv);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.p$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11880b {
    }

    public C11878p(Context context, List<WPToolFqNumInfoBean> list) {
        this.f24199a = context;
        this.f24200b = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<WPToolFqNumInfoBean> list = this.f24200b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull C11879a c11879a, int i) {
        TextView textView;
        Resources resources;
        int i2;
        C11879a c11879a2 = c11879a;
        try {
            WPToolFqNumInfoBean wPToolFqNumInfoBean = this.f24200b.get(i);
            c11879a2.f24202a.setBackgroundResource(wPToolFqNumInfoBean.isChecked() ? C10531R.C10533drawable.up_instalment_item_selected_bg : C10531R.C10533drawable.up_instalment_item_unselected_bg);
            c11879a2.f24203b.setText(wPToolFqNumInfoBean.getFqAmountMsg());
            c11879a2.f24204c.setText(wPToolFqNumInfoBean.getFqFeeInfo());
            if (wPToolFqNumInfoBean.isChecked()) {
                c11879a2.getAdapterPosition();
                c11879a2.f24203b.setTextColor(this.f24199a.getResources().getColor(C10531R.C10532color.wp_red_color));
                c11879a2.f24203b.getPaint().setFakeBoldText(true);
                textView = c11879a2.f24204c;
                resources = this.f24199a.getResources();
                i2 = C10531R.C10532color.wp_red_color;
            } else if ("0".equals(wPToolFqNumInfoBean.getCanUsed())) {
                c11879a2.f24203b.setTextColor(this.f24199a.getResources().getColor(C10531R.C10532color.wp_tip_color));
                c11879a2.f24203b.getPaint().setFakeBoldText(false);
                textView = c11879a2.f24204c;
                resources = this.f24199a.getResources();
                i2 = C10531R.C10532color.wp_tip_color;
            } else {
                c11879a2.f24203b.setTextColor(this.f24199a.getResources().getColor(C10531R.C10532color.wp_title_color));
                c11879a2.f24203b.getPaint().setFakeBoldText(false);
                textView = c11879a2.f24204c;
                resources = this.f24199a.getResources();
                i2 = C10531R.C10532color.wp_tip_color;
            }
            textView.setTextColor(resources.getColor(i2));
            c11879a2.f24202a.setOnClickListener(new View$OnClickListenerC11877o(this, wPToolFqNumInfoBean, c11879a2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final C11879a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new C11879a(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.up_instalment_qs_item, (ViewGroup) null, false));
    }
}

package p469p;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.unicom.pay.C10531R;
import com.unicom.pay.modules.verify.bean.WPPayEndInfoBean;
import java.util.List;
import p470p0.C13647j;

/* renamed from: p.a */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13621a extends RecyclerView.Adapter<C13622a> {

    /* renamed from: a */
    public Context f27447a;

    /* renamed from: b */
    public List<WPPayEndInfoBean> f27448b;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: p.a$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class C13622a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f27449a;

        /* renamed from: b */
        public ImageView f27450b;

        /* renamed from: c */
        public TextView f27451c;

        public C13622a(@NonNull View view) {
            super(view);
            this.f27449a = (TextView) view.findViewById(C10531R.C10534id.up_pay_result_title_tv);
            this.f27450b = (ImageView) view.findViewById(C10531R.C10534id.up_pay_result_logo_iv);
            this.f27451c = (TextView) view.findViewById(C10531R.C10534id.up_pay_result_name_tv);
        }
    }

    public C13621a(Context context, List<WPPayEndInfoBean> list) {
        this.f27447a = context;
        this.f27448b = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<WPPayEndInfoBean> list = this.f27448b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull C13622a c13622a, int i) {
        TextView textView;
        int color;
        C13622a c13622a2 = c13622a;
        try {
            WPPayEndInfoBean wPPayEndInfoBean = this.f27448b.get(i);
            c13622a2.f27449a.setText(wPPayEndInfoBean.getStartMsg());
            c13622a2.f27451c.setText(wPPayEndInfoBean.getEndMsg());
            if (!TextUtils.isEmpty(wPPayEndInfoBean.getImageUrl())) {
                c13622a2.f27450b.setVisibility(0);
                C13647j.m179a(this.f27447a, wPPayEndInfoBean.getImageUrl(), c13622a2.f27450b);
            } else {
                c13622a2.f27450b.setVisibility(8);
            }
            if (!TextUtils.isEmpty(wPPayEndInfoBean.getLineColor())) {
                textView = c13622a2.f27451c;
                color = Color.parseColor(wPPayEndInfoBean.getLineColor());
            } else {
                textView = c13622a2.f27451c;
                color = this.f27447a.getResources().getColor(C10531R.C10532color.wp_primary_text_color);
            }
            textView.setTextColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final C13622a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new C13622a(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.up_pay_result_item, viewGroup, false));
    }
}

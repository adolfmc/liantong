package p387e0;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.discount.bean.WPDiscountDetailBean;
import java.util.ArrayList;
import java.util.List;

/* renamed from: e0.g */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11860g extends RecyclerView.Adapter<View$OnClickListenerC11861a> {

    /* renamed from: a */
    public List<WPDiscountDetailBean> f24135a;

    /* renamed from: b */
    public InterfaceC11862b f24136b;

    /* renamed from: c */
    public InterfaceC11863c f24137c;

    /* renamed from: d */
    public Context f24138d;

    /* renamed from: e */
    public boolean f24139e = true;

    /* renamed from: f */
    public String f24140f;

    @NBSInstrumented
    /* renamed from: e0.g$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC11861a extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a */
        public TextView f24141a;

        /* renamed from: b */
        public ImageView f24142b;

        /* renamed from: c */
        public LinearLayout f24143c;

        /* renamed from: d */
        public TextView f24144d;

        /* renamed from: e */
        public TextView f24145e;

        /* renamed from: f */
        public ImageView f24146f;

        /* renamed from: g */
        public TextView f24147g;

        /* renamed from: h */
        public ConstraintLayout f24148h;

        public View$OnClickListenerC11861a(@NonNull View view) {
            super(view);
            this.f24141a = (TextView) view.findViewById(C10531R.C10534id.wopay_discount_name_tv);
            this.f24142b = (ImageView) view.findViewById(C10531R.C10534id.wopay_discount_right_cb);
            this.f24144d = (TextView) view.findViewById(C10531R.C10534id.wopay_discount_content_hide_tv);
            this.f24145e = (TextView) view.findViewById(C10531R.C10534id.wopay_discount_content_show_tv);
            this.f24146f = (ImageView) view.findViewById(C10531R.C10534id.wopay_discount_show_iv);
            this.f24147g = (TextView) view.findViewById(C10531R.C10534id.wopaay_discount_tag_tv);
            this.f24148h = (ConstraintLayout) view.findViewById(C10531R.C10534id.wopay_discount_detail_cl);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(C10531R.C10534id.wopay_discount_disable_ll);
            this.f24143c = (LinearLayout) view.findViewById(C10531R.C10534id.wopay_discount_content_ll);
            this.f24145e.setOnClickListener(this);
            this.f24144d.setOnClickListener(this);
            this.f24146f.setOnClickListener(this);
            this.f24143c.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (view.getId() == C10531R.C10534id.wopay_discount_content_hide_tv || view.getId() == C10531R.C10534id.wopay_discount_content_show_tv || view.getId() == C10531R.C10534id.wopay_discount_show_iv || view.getId() == C10531R.C10534id.wopay_discount_content_ll) {
                if (TextUtils.isEmpty(this.f24144d.getText())) {
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                boolean z = this.f24144d.getVisibility() == 0;
                this.f24144d.setVisibility(z ? 8 : 0);
                this.f24145e.setVisibility(z ? 0 : 8);
                this.f24146f.setRotation(z ? 180.0f : 0.0f);
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.g$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11862b {
        /* renamed from: a */
        void mo2063a(WPDiscountDetailBean wPDiscountDetailBean, boolean z, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.g$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11863c {
    }

    public C11860g(Context context, List<WPDiscountDetailBean> list) {
        this.f24138d = context;
        this.f24135a = list == null ? new ArrayList<>() : list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<WPDiscountDetailBean> list = this.f24135a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull View$OnClickListenerC11861a view$OnClickListenerC11861a, int i) {
        View$OnClickListenerC11861a view$OnClickListenerC11861a2 = view$OnClickListenerC11861a;
        try {
            WPDiscountDetailBean wPDiscountDetailBean = this.f24135a.get(i);
            view$OnClickListenerC11861a2.f24141a.setText(wPDiscountDetailBean.getDiscountTitle());
            TextView textView = view$OnClickListenerC11861a2.f24145e;
            if (textView != null) {
                textView.postDelayed(new RunnableC11858e(view$OnClickListenerC11861a2, wPDiscountDetailBean), 80L);
            }
            if ("0".equals(wPDiscountDetailBean.getCanCheck())) {
                view$OnClickListenerC11861a2.f24142b.setVisibility(8);
                view$OnClickListenerC11861a2.f24147g.setVisibility(8);
                view$OnClickListenerC11861a2.f24141a.setTextColor(Color.parseColor("#999999"));
                view$OnClickListenerC11861a2.f24148h.setBackgroundResource(wPDiscountDetailBean.isChecked() ? C10531R.C10533drawable.wp_white_red_line_card : C10531R.C10533drawable.wp_white_card);
                return;
            }
            view$OnClickListenerC11861a2.f24141a.setTextColor(Color.parseColor("#171E24"));
            view$OnClickListenerC11861a2.f24142b.setVisibility(0);
            Glide.with(this.f24138d).clear(view$OnClickListenerC11861a2.f24142b);
            if (wPDiscountDetailBean.isChecked()) {
                view$OnClickListenerC11861a2.getAdapterPosition();
                view$OnClickListenerC11861a2.f24142b.setSelected(true);
            } else {
                view$OnClickListenerC11861a2.f24142b.setSelected(false);
            }
            view$OnClickListenerC11861a2.f24148h.setBackgroundResource(wPDiscountDetailBean.isChecked() ? C10531R.C10533drawable.wp_white_red_line_card : C10531R.C10533drawable.wp_white_card);
            if (!TextUtils.isEmpty(wPDiscountDetailBean.getMarketSubscript())) {
                view$OnClickListenerC11861a2.f24147g.setVisibility(0);
                view$OnClickListenerC11861a2.f24147g.setText(wPDiscountDetailBean.getMarketSubscript());
            } else {
                view$OnClickListenerC11861a2.f24147g.setVisibility(8);
            }
            view$OnClickListenerC11861a2.itemView.setOnClickListener(new View$OnClickListenerC11859f(this, view$OnClickListenerC11861a2, wPDiscountDetailBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final View$OnClickListenerC11861a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new View$OnClickListenerC11861a(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_discount_expand_item, viewGroup, false));
    }
}

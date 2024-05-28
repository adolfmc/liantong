package p387e0;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPFqInfoBean;
import java.util.ArrayList;
import java.util.List;
import p470p0.C13647j;
import p470p0.C13659r;

/* renamed from: e0.n */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11873n extends RecyclerView.Adapter<C11874a> {

    /* renamed from: a */
    public Context f24178a;

    /* renamed from: c */
    public InterfaceC11875b f24180c;

    /* renamed from: d */
    public int f24181d = -1;

    /* renamed from: e */
    public boolean f24182e = true;

    /* renamed from: b */
    public List<WPFqInfoBean> f24179b = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.n$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11874a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public LinearLayout f24183a;

        /* renamed from: b */
        public ImageView f24184b;

        /* renamed from: c */
        public TextView f24185c;

        /* renamed from: d */
        public FlexboxLayout f24186d;

        /* renamed from: e */
        public TextView f24187e;

        /* renamed from: f */
        public ImageView f24188f;

        /* renamed from: g */
        public ImageView f24189g;

        /* renamed from: h */
        public FlexboxLayout f24190h;

        /* renamed from: i */
        public TextView f24191i;

        /* renamed from: j */
        public TextView f24192j;

        /* renamed from: k */
        public RecyclerView f24193k;

        /* renamed from: l */
        public View f24194l;

        /* renamed from: m */
        public ImageView f24195m;

        public C11874a(C11873n c11873n, @NonNull View view) {
            super(view);
            this.f24183a = (LinearLayout) view.findViewById(C10531R.C10534id.up_instalment_item_ll);
            this.f24184b = (ImageView) view.findViewById(C10531R.C10534id.up_instalment_item_logo_iv);
            this.f24185c = (TextView) view.findViewById(C10531R.C10534id.up_instalment_item_name_iv);
            this.f24186d = (FlexboxLayout) view.findViewById(C10531R.C10534id.up_instalment_discount_ll);
            this.f24187e = (TextView) view.findViewById(C10531R.C10534id.up_instalment_item_discount_tv);
            this.f24188f = (ImageView) view.findViewById(C10531R.C10534id.up_discount_arrow_iv);
            this.f24189g = (ImageView) view.findViewById(C10531R.C10534id.up_instalment_item_cb);
            this.f24190h = (FlexboxLayout) view.findViewById(C10531R.C10534id.up_instalment_item_bank_ll);
            this.f24191i = (TextView) view.findViewById(C10531R.C10534id.up_instalment_item_bank_name_tv);
            this.f24192j = (TextView) view.findViewById(C10531R.C10534id.up_instalment_item_bank_desc_tv);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(C10531R.C10534id.up_instalment_item_qs_rv);
            this.f24193k = recyclerView;
            recyclerView.setHasFixedSize(true);
            this.f24193k.setNestedScrollingEnabled(false);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(c11873n.f24178a, 2);
            gridLayoutManager.setMeasurementCacheEnabled(true);
            this.f24193k.setLayoutManager(gridLayoutManager);
            this.f24193k.addItemDecoration(new C11876c(C13659r.m170a(8.0f)));
            this.f24194l = view.findViewById(C10531R.C10534id.line);
            this.f24195m = (ImageView) view.findViewById(C10531R.C10534id.up_instalment_item_bank_iv);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.n$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11875b {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.n$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11876c extends RecyclerView.ItemDecoration {

        /* renamed from: a */
        public int f24196a;

        public C11876c(int i) {
            this.f24196a = i;
        }

        @Override // android.support.p086v7.widget.RecyclerView.ItemDecoration
        public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            rect.left = this.f24196a;
            if (recyclerView.getChildLayoutPosition(view) % 2 == 0) {
                rect.left = 0;
            }
        }
    }

    public C11873n(Context context) {
        this.f24178a = context;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.unicom.pay.normal.order.bean.WPFqInfoBean>, java.util.ArrayList] */
    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        ?? r0 = this.f24179b;
        if (r0 == 0) {
            return 0;
        }
        return r0.size();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.unicom.pay.normal.order.bean.WPFqInfoBean>, java.util.ArrayList] */
    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull C11874a c11874a, int i) {
        C11874a c11874a2 = c11874a;
        try {
            WPFqInfoBean wPFqInfoBean = (WPFqInfoBean) this.f24179b.get(i);
            c11874a2.f24183a.setOnClickListener(new View$OnClickListenerC11870k(this, wPFqInfoBean, c11874a2));
            C13647j.m178a(this.f24178a, wPFqInfoBean.getIconUrl(), c11874a2.f24184b, WPFqInfoBean.WFQ_XYK.equals(wPFqInfoBean.getType()) ? C10531R.C10533drawable.wp_bank_default : C10531R.C10533drawable.up_default_method_logo);
            c11874a2.f24185c.setText(wPFqInfoBean.getMethodName());
            int i2 = 8;
            if (wPFqInfoBean.getFqTitle() != null && !TextUtils.isEmpty(wPFqInfoBean.getFqTitle().getMethodMsg())) {
                c11874a2.f24186d.setVisibility(0);
                c11874a2.f24187e.setText(wPFqInfoBean.getFqTitle().getMethodMsg());
                if ("1".equals(wPFqInfoBean.getFqTitle().getDiscountNext())) {
                    c11874a2.f24188f.setVisibility(0);
                    c11874a2.f24186d.setOnClickListener(new View$OnClickListenerC11871l(this, c11874a2, wPFqInfoBean));
                } else {
                    c11874a2.f24188f.setVisibility(8);
                }
            } else {
                c11874a2.f24186d.setVisibility(4);
            }
            Glide.with(this.f24178a).clear(c11874a2.f24189g);
            if ((wPFqInfoBean.isDefaultChecked() && this.f24181d == -1) || this.f24181d == i) {
                this.f24181d = c11874a2.getAdapterPosition();
                c11874a2.f24189g.setSelected(true);
            } else {
                c11874a2.f24189g.setSelected(false);
            }
            if (wPFqInfoBean.getFqTitle() != null) {
                c11874a2.f24190h.setVisibility(0);
                c11874a2.f24191i.setText(wPFqInfoBean.getFqTitle().getMethodName());
                c11874a2.f24192j.setText(wPFqInfoBean.getFqTitle().getMethodDesc());
                c11874a2.f24192j.setVisibility(!TextUtils.isEmpty(wPFqInfoBean.getFqTitle().getMethodDesc()) ? 0 : 8);
            } else {
                c11874a2.f24190h.setVisibility(8);
            }
            c11874a2.f24195m.setImageResource(C10531R.C10533drawable.up_right_arrow);
            c11874a2.f24190h.setOnClickListener(new View$OnClickListenerC11872m(this, c11874a2, wPFqInfoBean));
            if (wPFqInfoBean.getFqNumInfos() != null && !wPFqInfoBean.getFqNumInfos().isEmpty()) {
                c11874a2.f24193k.setVisibility(0);
            } else {
                c11874a2.f24193k.setVisibility(8);
            }
            View view = c11874a2.f24194l;
            if (i != getItemCount() - 1) {
                i2 = 0;
            }
            view.setVisibility(i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final C11874a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new C11874a(this, LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.up_instalment_item, viewGroup, false));
    }
}

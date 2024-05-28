package p387e0;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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
import com.bytedance.applog.tracker.Tracker;
import com.google.android.flexbox.FlexboxLayout;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.normal.discount.bean.WPDiscountListBean;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import com.unicom.pay.normal.order.bean.WPPayInfoUpdateBean;
import com.unicom.pay.normal.order.bean.WPToolFqNumInfoBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p385d0.C11824k;
import p387e0.C11878p;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p470p0.C13647j;
import p470p0.C13659r;
import p470p0.C13662t;
import p482w.C14255c;

/* renamed from: e0.u */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11889u extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a */
    public Context f24229a;

    /* renamed from: b */
    public List<WPPayInfoBean> f24230b;

    /* renamed from: c */
    public InterfaceC11900j f24231c;

    /* renamed from: d */
    public InterfaceC11898h f24232d;

    /* renamed from: e */
    public InterfaceC11899i f24233e;

    /* renamed from: f */
    public int f24234f;

    /* renamed from: g */
    public boolean f24235g;

    /* renamed from: h */
    public boolean f24236h;

    /* renamed from: i */
    public boolean f24237i;

    /* renamed from: j */
    public boolean f24238j;

    /* renamed from: k */
    public String f24239k;

    @NBSInstrumented
    /* renamed from: e0.u$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC11890a implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ C11902l f24240a;

        /* renamed from: b */
        public final /* synthetic */ WPPayInfoBean f24241b;

        /* renamed from: c */
        public final /* synthetic */ int f24242c;

        public View$OnClickListenerC11890a(C11902l c11902l, WPPayInfoBean wPPayInfoBean, int i) {
            this.f24240a = c11902l;
            this.f24241b = wPPayInfoBean;
            this.f24242c = i;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (!C11889u.this.f24236h) {
                NBSActionInstrumentation.onClickEventExit();
            } else if (this.f24240a.f24283j.isSelected()) {
                NBSActionInstrumentation.onClickEventExit();
            } else {
                if ("1".equals(this.f24241b.getEventIconType())) {
                    C11889u.m2053a(C11889u.this, this.f24240a.f24283j);
                } else if ("2".equals(this.f24241b.getEventIconType())) {
                    C13647j.m180a(C11889u.this.f24229a, C10531R.C10533drawable.up_item_loading, this.f24240a.f24282i);
                }
                InterfaceC11900j interfaceC11900j = C11889u.this.f24231c;
                if (interfaceC11900j != null) {
                    ((WPOrderActivity) interfaceC11900j).m6113a(this.f24241b, this.f24240a, this.f24242c);
                }
                C11889u c11889u = C11889u.this;
                if (c11889u.f24232d != null) {
                    C13647j.m180a(c11889u.f24229a, C10531R.C10533drawable.up_item_loading, this.f24240a.f24283j);
                    ((WPOrderActivity) C11889u.this.f24232d).m6112a(this.f24241b, (C11904n) this.f24240a);
                }
                InterfaceC11899i interfaceC11899i = C11889u.this.f24233e;
                if (interfaceC11899i != null) {
                    ((C11824k) interfaceC11899i).m2079a(this.f24241b);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        }
    }

    @NBSInstrumented
    /* renamed from: e0.u$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC11891b implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ C11903m f24244a;

        /* renamed from: b */
        public final /* synthetic */ WPPayInfoBean f24245b;

        /* renamed from: c */
        public final /* synthetic */ RecyclerView.ViewHolder f24246c;

        /* renamed from: d */
        public final /* synthetic */ int f24247d;

        public View$OnClickListenerC11891b(C11903m c11903m, WPPayInfoBean wPPayInfoBean, RecyclerView.ViewHolder viewHolder, int i) {
            this.f24244a = c11903m;
            this.f24245b = wPPayInfoBean;
            this.f24246c = viewHolder;
            this.f24247d = i;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (!C11889u.this.f24236h) {
                NBSActionInstrumentation.onClickEventExit();
            } else if (this.f24244a.getAdapterPosition() == -1) {
                NBSActionInstrumentation.onClickEventExit();
            } else {
                Object tag = this.f24244a.itemView.getTag();
                if (!(tag != null && ((Boolean) tag).booleanValue())) {
                    this.f24244a.f24271a.setText(this.f24245b.getToolName());
                    this.f24244a.f24272b.setVisibility(8);
                }
                C11889u c11889u = C11889u.this;
                c11889u.f24237i = true;
                C13647j.m180a(c11889u.f24229a, C10531R.C10533drawable.up_item_loading, this.f24244a.f24273c);
                InterfaceC11900j interfaceC11900j = C11889u.this.f24231c;
                if (interfaceC11900j != null) {
                    ((WPOrderActivity) interfaceC11900j).m6113a(this.f24245b, this.f24246c, this.f24247d);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        }
    }

    @NBSInstrumented
    /* renamed from: e0.u$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC11892c implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ C11904n f24249a;

        /* renamed from: b */
        public final /* synthetic */ WPPayInfoBean f24250b;

        public View$OnClickListenerC11892c(C11904n c11904n, WPPayInfoBean wPPayInfoBean) {
            this.f24249a = c11904n;
            this.f24250b = wPPayInfoBean;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (!C11889u.this.f24236h) {
                NBSActionInstrumentation.onClickEventExit();
            } else if (this.f24249a.getAdapterPosition() == -1) {
                NBSActionInstrumentation.onClickEventExit();
            } else {
                int adapterPosition = this.f24249a.getAdapterPosition();
                C11889u c11889u = C11889u.this;
                if (adapterPosition != c11889u.f24234f) {
                    if (this.f24249a.f24283j.isSelected()) {
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    } else if (C11889u.this.f24231c != null) {
                        if (WPPayInfoBean.EVENT_TYPE_CHECK.equals(this.f24250b.getEventType())) {
                            C11889u.m2053a(C11889u.this, this.f24249a.f24283j);
                        }
                        ((WPOrderActivity) C11889u.this.f24231c).m6114a(this.f24250b, (RecyclerView.ViewHolder) this.f24249a);
                    }
                } else {
                    InterfaceC11900j interfaceC11900j = c11889u.f24231c;
                    if (interfaceC11900j != null) {
                        ((WPOrderActivity) interfaceC11900j).m6114a(this.f24250b, (RecyclerView.ViewHolder) this.f24249a);
                    }
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        }
    }

    @NBSInstrumented
    /* renamed from: e0.u$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC11893d implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ C11904n f24252a;

        /* renamed from: b */
        public final /* synthetic */ WPPayInfoBean f24253b;

        /* renamed from: c */
        public final /* synthetic */ int f24254c;

        public View$OnClickListenerC11893d(C11904n c11904n, WPPayInfoBean wPPayInfoBean, int i) {
            this.f24252a = c11904n;
            this.f24253b = wPPayInfoBean;
            this.f24254c = i;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (!C11889u.this.f24236h) {
                NBSActionInstrumentation.onClickEventExit();
            } else if (this.f24252a.getAdapterPosition() == -1) {
                NBSActionInstrumentation.onClickEventExit();
            } else if (this.f24252a.f24283j.isSelected()) {
                InterfaceC11898h interfaceC11898h = C11889u.this.f24232d;
                if (interfaceC11898h != null) {
                    ((WPOrderActivity) interfaceC11898h).m6112a(this.f24253b, this.f24252a);
                }
                NBSActionInstrumentation.onClickEventExit();
            } else {
                if ("1".equals(this.f24253b.getEventIconType())) {
                    C11889u.m2053a(C11889u.this, this.f24252a.f24283j);
                } else if ("2".equals(this.f24253b.getEventIconType())) {
                    C13647j.m180a(C11889u.this.f24229a, C10531R.C10533drawable.up_item_loading, this.f24252a.f24282i);
                }
                InterfaceC11900j interfaceC11900j = C11889u.this.f24231c;
                if (interfaceC11900j != null) {
                    ((WPOrderActivity) interfaceC11900j).m6113a(this.f24253b, this.f24252a, this.f24254c);
                }
                C11889u c11889u = C11889u.this;
                if (c11889u.f24232d != null) {
                    C13647j.m180a(c11889u.f24229a, C10531R.C10533drawable.up_item_loading, this.f24252a.f24283j);
                    ((WPOrderActivity) C11889u.this.f24232d).m6112a(this.f24253b, this.f24252a);
                }
                InterfaceC11899i interfaceC11899i = C11889u.this.f24233e;
                if (interfaceC11899i != null) {
                    ((C11824k) interfaceC11899i).m2079a(this.f24253b);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.u$e */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11894e implements C11878p.InterfaceC11880b {

        /* renamed from: a */
        public final /* synthetic */ C11902l f24256a;

        /* renamed from: b */
        public final /* synthetic */ WPPayInfoBean f24257b;

        public C11894e(C11902l c11902l, WPPayInfoBean wPPayInfoBean) {
            this.f24256a = c11902l;
            this.f24257b = wPPayInfoBean;
        }
    }

    @NBSInstrumented
    /* renamed from: e0.u$f */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC11895f implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ WPPayInfoBean f24259a;

        public View$OnClickListenerC11895f(WPPayInfoBean wPPayInfoBean) {
            this.f24259a = wPPayInfoBean;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            UnicomPaySDK.getInstance().getNativeFunctionCallback().openWebview(C11889u.this.f24229a, this.f24259a.getFqInfo().getFqTail().getFqAgreeUrl());
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: e0.u$g */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC11896g implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ WPDiscountListBean f24261a;

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: e0.u$g$a */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public class RunnableC11897a implements Runnable {
            public RunnableC11897a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                C11889u.this.notifyDataSetChanged();
            }
        }

        public RunnableC11896g(WPDiscountListBean wPDiscountListBean) {
            this.f24261a = wPDiscountListBean;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                int size = C11889u.this.f24230b.size();
                int size2 = this.f24261a.getUpdatePayInfos().size();
                int size3 = this.f24261a.getActivityList().size();
                for (int i = 0; i < size; i++) {
                    for (int i2 = 0; i2 < size3; i2++) {
                        if (this.f24261a.getActivityList().get(i2).getToolId().equals(C11889u.this.f24230b.get(i).getToolId())) {
                            C11889u.this.f24230b.set(i, this.f24261a.getActivityList().get(i2));
                        }
                    }
                    for (int i3 = 0; i3 < size2; i3++) {
                        if (this.f24261a.getUpdatePayInfos().get(i3).getToolId().equals(C11889u.this.f24230b.get(i).getToolId())) {
                            C11889u.this.f24230b.set(i, this.f24261a.getUpdatePayInfos().get(i3));
                        }
                    }
                }
                ((AbstractDialogInterface$OnCancelListenerC12000b) C11889u.this.f24229a).runOnUiThread(new RunnableC11897a());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.u$h */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11898h {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.u$i */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11899i {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.u$j */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11900j {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.u$k */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11901k extends RecyclerView.ItemDecoration {

        /* renamed from: a */
        public int f24264a;

        public C11901k(int i) {
            this.f24264a = i;
        }

        @Override // android.support.p086v7.widget.RecyclerView.ItemDecoration
        public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            rect.left = this.f24264a;
            if (recyclerView.getChildLayoutPosition(view) % 2 == 0) {
                rect.left = 0;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.u$l */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11902l extends C11904n {

        /* renamed from: l */
        public TextView f24265l;

        /* renamed from: m */
        public RecyclerView f24266m;

        /* renamed from: n */
        public LinearLayout f24267n;

        /* renamed from: o */
        public TextView f24268o;

        /* renamed from: p */
        public TextView f24269p;

        /* renamed from: q */
        public TextView f24270q;

        public C11902l(C11889u c11889u, @NonNull View view) {
            super(view);
            this.f24265l = (TextView) view.findViewById(C10531R.C10534id.wp_wfq_head_tv);
            this.f24266m = (RecyclerView) view.findViewById(C10531R.C10534id.up_instalment_item_qs_rv);
            this.f24267n = (LinearLayout) view.findViewById(C10531R.C10534id.wp_wfq_proto_ll);
            this.f24268o = (TextView) view.findViewById(C10531R.C10534id.wp_wfq_proto_agree_tv);
            this.f24269p = (TextView) view.findViewById(C10531R.C10534id.wp_wfq_proto_apr_tv);
            this.f24270q = (TextView) view.findViewById(C10531R.C10534id.wp_wfq_proto_apr_split_tv);
            this.f24266m.setHasFixedSize(true);
            this.f24266m.setNestedScrollingEnabled(false);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(c11889u.f24229a, 2);
            gridLayoutManager.setMeasurementCacheEnabled(true);
            this.f24266m.setLayoutManager(gridLayoutManager);
            this.f24266m.addItemDecoration(new C11901k(C13659r.m170a(8.0f)));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.u$m */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11903m extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f24271a;

        /* renamed from: b */
        public View f24272b;

        /* renamed from: c */
        public ImageView f24273c;

        public C11903m(@NonNull View view) {
            super(view);
            this.f24271a = (TextView) view.findViewById(C10531R.C10534id.wopay_pay_method_other_tv);
            this.f24272b = view.findViewById(C10531R.C10534id.wopay_more_dot_v);
            this.f24273c = (ImageView) view.findViewById(C10531R.C10534id.up_unicom_more_iv);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.u$n */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11904n extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public LinearLayout f24274a;

        /* renamed from: b */
        public TextView f24275b;

        /* renamed from: c */
        public ImageView f24276c;

        /* renamed from: d */
        public TextView f24277d;

        /* renamed from: e */
        public TextView f24278e;

        /* renamed from: f */
        public FlexboxLayout f24279f;

        /* renamed from: g */
        public TextView f24280g;

        /* renamed from: h */
        public ImageView f24281h;

        /* renamed from: i */
        public ImageView f24282i;

        /* renamed from: j */
        public ImageView f24283j;

        /* renamed from: k */
        public View f24284k;

        public C11904n(@NonNull View view) {
            super(view);
            this.f24274a = (LinearLayout) view.findViewById(C10531R.C10534id.wopay_order_detail_up_item_root_ll);
            this.f24275b = (TextView) view.findViewById(C10531R.C10534id.wopay_pay_method_title_tv);
            this.f24276c = (ImageView) view.findViewById(C10531R.C10534id.wopay_pay_method_logo_iv);
            this.f24277d = (TextView) view.findViewById(C10531R.C10534id.wopay_pay_method_name_tv);
            this.f24278e = (TextView) view.findViewById(C10531R.C10534id.wopay_pay_method_recommend_tv);
            this.f24279f = (FlexboxLayout) view.findViewById(C10531R.C10534id.up_pay_info_discount_ll);
            this.f24280g = (TextView) view.findViewById(C10531R.C10534id.wopay_pay_method_discount_tv);
            this.f24281h = (ImageView) view.findViewById(C10531R.C10534id.up_discount_arrow_iv);
            this.f24282i = (ImageView) view.findViewById(C10531R.C10534id.wopay_method_iv);
            this.f24283j = (ImageView) view.findViewById(C10531R.C10534id.wopay_method_cb);
            this.f24284k = view.findViewById(C10531R.C10534id.up_method_split_line);
        }
    }

    public C11889u(Context context) {
        this.f24234f = -1;
        this.f24236h = true;
        this.f24237i = false;
        this.f24238j = true;
        this.f24235g = true;
        this.f24229a = context;
        this.f24230b = new ArrayList();
    }

    public C11889u(Context context, List<WPPayInfoBean> list) {
        this.f24234f = -1;
        this.f24236h = true;
        this.f24237i = false;
        this.f24238j = true;
        this.f24235g = false;
        this.f24229a = context;
        this.f24230b = list;
    }

    /* renamed from: a */
    public static void m2053a(C11889u c11889u, ImageView imageView) {
        C13647j.m180a(c11889u.f24229a, C10531R.C10533drawable.up_item_loading, imageView);
    }

    /* renamed from: a */
    public final List<WPPayInfoUpdateBean> m2059a() {
        WPPayInfoUpdateBean wPPayInfoUpdateBean;
        ArrayList arrayList = new ArrayList();
        try {
            for (WPPayInfoBean wPPayInfoBean : this.f24230b) {
                if (!WPPayInfoBean.f20227YE.equals(wPPayInfoBean.getToolCode()) && !WPPayInfoBean.f20222KJ.equals(wPPayInfoBean.getToolCode())) {
                    if (WPPayInfoBean.WFQ.equals(wPPayInfoBean.getToolCode()) && wPPayInfoBean.getFqInfo() != null && wPPayInfoBean.getFqInfo().getFqNumInfos() != null) {
                        wPPayInfoUpdateBean = new WPPayInfoUpdateBean();
                        String str = "";
                        Iterator<WPToolFqNumInfoBean> it = wPPayInfoBean.getFqInfo().getFqNumInfos().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            WPToolFqNumInfoBean next = it.next();
                            if (next.isChecked()) {
                                str = next.getFqNumExpand();
                                break;
                            }
                        }
                        if (TextUtils.isEmpty(str)) {
                            str = wPPayInfoBean.getToolExpand();
                        }
                        wPPayInfoUpdateBean.setToolExpand(str);
                        arrayList.add(wPPayInfoUpdateBean);
                    }
                }
                wPPayInfoUpdateBean = new WPPayInfoUpdateBean();
                wPPayInfoUpdateBean.setToolExpand(wPPayInfoBean.getToolExpand());
                arrayList.add(wPPayInfoUpdateBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<WPPayInfoBean> list = this.f24230b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        List<WPPayInfoBean> list = this.f24230b;
        if (list == null) {
            return 0;
        }
        if (WPPayInfoBean.MORE.equals(list.get(i).getToolCode())) {
            return 1;
        }
        if (this.f24235g) {
            return WPPayInfoBean.ACTIVITY.equals(this.f24230b.get(i).getToolGroup()) ? 3 : 2;
        }
        return 0;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TextView textView;
        String toolDiscountMsg;
        try {
            WPPayInfoBean wPPayInfoBean = this.f24230b.get(i);
            int itemViewType = getItemViewType(i);
            if (itemViewType == 0) {
                m2055a(wPPayInfoBean, (C11904n) viewHolder, i, itemViewType);
            } else if (itemViewType == 2) {
                C11902l c11902l = (C11902l) viewHolder;
                m2055a(wPPayInfoBean, c11902l, i, itemViewType);
                m2056a(wPPayInfoBean, c11902l);
            } else if (itemViewType == 3) {
                C11902l c11902l2 = (C11902l) viewHolder;
                m2055a(wPPayInfoBean, c11902l2, i, itemViewType);
                m2056a(wPPayInfoBean, c11902l2);
                c11902l2.f24279f.setOnClickListener(new View$OnClickListenerC11890a(c11902l2, wPPayInfoBean, itemViewType));
                if (TextUtils.isEmpty(wPPayInfoBean.getToolDiscountMsg()) || wPPayInfoBean.getToolDiscountMsg().length() <= 15) {
                    textView = c11902l2.f24280g;
                    toolDiscountMsg = wPPayInfoBean.getToolDiscountMsg();
                } else {
                    textView = c11902l2.f24280g;
                    toolDiscountMsg = wPPayInfoBean.getToolDiscountMsg().substring(0, 15);
                }
                textView.setText(toolDiscountMsg);
            } else {
                C11903m c11903m = (C11903m) viewHolder;
                if (!this.f24237i && !TextUtils.isEmpty(wPPayInfoBean.getToolDiscountMsg())) {
                    c11903m.f24271a.setText(wPPayInfoBean.getToolName() + " " + wPPayInfoBean.getToolDiscountMsg());
                    c11903m.f24272b.setVisibility(0);
                } else {
                    c11903m.f24271a.setText(wPPayInfoBean.getToolName());
                    c11903m.f24272b.setVisibility(8);
                }
                Context context = this.f24229a;
                Glide.with(context).clear(c11903m.f24273c);
                c11903m.f24273c.setImageResource(C10531R.C10533drawable.up_right_arrow);
                c11903m.itemView.setOnClickListener(new View$OnClickListenerC11891b(c11903m, wPPayInfoBean, viewHolder, itemViewType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return i == 1 ? new C11903m(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_order_pay_method_other_item, viewGroup, false)) : this.f24235g ? new C11902l(this, LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_order_pay_method_item, viewGroup, false)) : new C11904n(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_order_pay_method_list_item, viewGroup, false));
    }

    /* renamed from: a */
    public final void m2056a(WPPayInfoBean wPPayInfoBean, C11902l c11902l) {
        View view;
        try {
            if (c11902l.f24275b != null) {
                if (TextUtils.isEmpty(this.f24239k) || c11902l.getAdapterPosition() != 0) {
                    c11902l.f24275b.setVisibility(8);
                } else {
                    c11902l.f24275b.setText(this.f24239k);
                    c11902l.f24275b.setVisibility(0);
                }
            }
            if (wPPayInfoBean.getFqInfo() == null || !(this.f24238j || c11902l.f24283j.isSelected())) {
                c11902l.f24266m.setVisibility(8);
                c11902l.f24267n.setVisibility(8);
                view = c11902l.f24265l;
            } else {
                c11902l.f24266m.setVisibility(0);
                C11878p c11878p = new C11878p(this.f24229a, wPPayInfoBean.getFqInfo().getFqNumInfos());
                c11878p.f24201c = new C11894e(c11902l, wPPayInfoBean);
                c11902l.f24266m.setAdapter(c11878p);
                if (wPPayInfoBean.getFqInfo().getFqHead() == null || TextUtils.isEmpty(wPPayInfoBean.getFqInfo().getFqHead().getFqHeadMsg())) {
                    c11902l.f24265l.setVisibility(8);
                } else {
                    c11902l.f24265l.setVisibility(0);
                    c11902l.f24265l.setText(wPPayInfoBean.getFqInfo().getFqHead().getFqHeadMsg());
                }
                if ((wPPayInfoBean.getFqInfo().getFqTail() == null || TextUtils.isEmpty(wPPayInfoBean.getFqInfo().getFqTail().getFqAgree())) && TextUtils.isEmpty(wPPayInfoBean.getFqInfo().getFqTail().getApr())) {
                    c11902l.f24268o.setOnClickListener(null);
                    view = c11902l.f24267n;
                } else {
                    c11902l.f24267n.setVisibility(0);
                    c11902l.f24268o.setText(wPPayInfoBean.getFqInfo().getFqTail().getFqAgree());
                    c11902l.f24268o.setOnClickListener(new View$OnClickListenerC11895f(wPPayInfoBean));
                    c11902l.f24269p.setText(wPPayInfoBean.getFqInfo().getFqTail().getApr());
                    if (!TextUtils.isEmpty(wPPayInfoBean.getFqInfo().getFqTail().getApr()) && !TextUtils.isEmpty(wPPayInfoBean.getFqInfo().getFqTail().getFqAgree())) {
                        c11902l.f24270q.setVisibility(0);
                        return;
                    }
                    view = c11902l.f24270q;
                }
            }
            view.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m2055a(WPPayInfoBean wPPayInfoBean, C11904n c11904n, int i, int i2) {
        LinearLayout linearLayout;
        Resources resources;
        int i3;
        int i4;
        View view;
        TextView textView;
        String toolDiscountMsg;
        View view2;
        TextView textView2;
        String toolDiscountMsg2;
        ImageView imageView;
        int i5;
        try {
            if (WPPayInfoBean.QPAY.equals(wPPayInfoBean.getToolCode())) {
                linearLayout = c11904n.f24274a;
                resources = this.f24229a.getResources();
                i3 = C10531R.C10532color.wp_qpay_method_bg;
            } else {
                linearLayout = c11904n.f24274a;
                resources = this.f24229a.getResources();
                i3 = C10531R.C10532color.wp_transparent;
            }
            linearLayout.setBackgroundColor(resources.getColor(i3));
            if (TextUtils.isEmpty(wPPayInfoBean.getIconUrl())) {
                c11904n.f24276c.setVisibility(8);
            } else {
                c11904n.f24276c.setVisibility(0);
                Context context = this.f24229a;
                String iconUrl = wPPayInfoBean.getIconUrl();
                ImageView imageView2 = c11904n.f24276c;
                if (!WPPayInfoBean.f20221BK.equals(wPPayInfoBean.getToolCode()) && !WPPayInfoBean.BKYH.equals(wPPayInfoBean.getToolCode()) && !WPPayInfoBean.f20222KJ.equals(wPPayInfoBean.getToolCode()) && !WPPayInfoBean.WFQ.equals(wPPayInfoBean.getToolCode())) {
                    i4 = C10531R.C10533drawable.up_default_method_logo;
                    C13647j.m178a(context, iconUrl, imageView2, i4);
                }
                i4 = C10531R.C10533drawable.wp_bank_default;
                C13647j.m178a(context, iconUrl, imageView2, i4);
            }
            C13662t c13662t = new C13662t(wPPayInfoBean.getToolName());
            String toolDesc = !TextUtils.isEmpty(wPPayInfoBean.getToolDesc()) ? wPPayInfoBean.getToolDesc() : "";
            c13662t.m166a();
            c13662t.f27499a = toolDesc;
            c13662t.f27501c = Color.parseColor(this.f24235g ? "#333333" : "#80333333");
            c13662t.m166a();
            c11904n.f24277d.setText(c13662t.f27507i);
            c11904n.f24278e.setVisibility((!this.f24235g || TextUtils.isEmpty(wPPayInfoBean.getRecommendMsg())) ? 8 : 0);
            c11904n.f24278e.setText(wPPayInfoBean.getRecommendMsg());
            if (this.f24235g) {
                c11904n.f24279f.setOnClickListener(null);
                if (TextUtils.isEmpty(wPPayInfoBean.getToolDiscountMsg()) || wPPayInfoBean.getToolDiscountMsg().length() <= 11) {
                    textView2 = c11904n.f24280g;
                    toolDiscountMsg2 = wPPayInfoBean.getToolDiscountMsg();
                } else {
                    textView2 = c11904n.f24280g;
                    toolDiscountMsg2 = wPPayInfoBean.getToolDiscountMsg().substring(0, 11);
                }
                textView2.setText(toolDiscountMsg2);
                if ("1".equals(wPPayInfoBean.getToolDiscountIconType())) {
                    c11904n.f24281h.setVisibility(0);
                } else {
                    c11904n.f24281h.setVisibility(8);
                }
                if ("1".equals(wPPayInfoBean.getToolDiscountNext())) {
                    c11904n.f24279f.setOnClickListener(new View$OnClickListenerC11892c(c11904n, wPPayInfoBean));
                } else {
                    c11904n.f24279f.setOnClickListener(null);
                }
                if ("1".equals(wPPayInfoBean.getToolDiscountIconState())) {
                    c11904n.f24280g.setTextColor(this.f24229a.getResources().getColor(C10531R.C10532color.wp_red_color));
                    imageView = c11904n.f24281h;
                    i5 = C10531R.C10533drawable.up_discount_arrow_enable;
                } else {
                    c11904n.f24280g.setTextColor(this.f24229a.getResources().getColor(C10531R.C10532color.wp_tip_color));
                    imageView = c11904n.f24281h;
                    i5 = C10531R.C10533drawable.up_discount_arrow_disable;
                }
                imageView.setImageResource(i5);
                c11904n.f24280g.setVisibility(0);
                if (TextUtils.isEmpty(wPPayInfoBean.getToolDiscountMsg())) {
                    view = c11904n.f24279f;
                    view.setVisibility(8);
                } else {
                    view2 = c11904n.f24279f;
                    view2.setVisibility(0);
                }
            } else if (TextUtils.isEmpty(wPPayInfoBean.getToolDiscountMsg())) {
                view = c11904n.f24280g;
                view.setVisibility(8);
            } else {
                if (wPPayInfoBean.getToolDiscountMsg().length() > 11) {
                    textView = c11904n.f24280g;
                    toolDiscountMsg = wPPayInfoBean.getToolDiscountMsg().substring(0, 11);
                } else {
                    textView = c11904n.f24280g;
                    toolDiscountMsg = wPPayInfoBean.getToolDiscountMsg();
                }
                textView.setText(toolDiscountMsg);
                view2 = c11904n.f24280g;
                view2.setVisibility(0);
            }
            c11904n.f24280g.setSingleLine();
            c11904n.f24280g.setEllipsize(TextUtils.TruncateAt.END);
            if (!this.f24235g) {
                c11904n.f24280g.setBackground(null);
            }
            Glide.with(this.f24229a).clear(c11904n.f24283j);
            if ((wPPayInfoBean.isChecked() && this.f24234f == -1) || this.f24234f == i) {
                this.f24234f = c11904n.getAdapterPosition();
                c11904n.f24283j.setSelected(true);
            } else {
                c11904n.f24283j.setSelected(false);
            }
            Glide.with(this.f24229a).clear(c11904n.f24282i);
            c11904n.f24282i.setImageResource(C10531R.C10533drawable.up_right_arrow);
            if ("2".equals(wPPayInfoBean.getEventIconType())) {
                c11904n.f24282i.setVisibility(0);
                c11904n.f24283j.setVisibility(8);
            } else {
                if ("1".equals(wPPayInfoBean.getEventIconType())) {
                    c11904n.f24282i.setVisibility(8);
                } else {
                    c11904n.f24282i.setVisibility(8);
                }
                c11904n.f24283j.setVisibility(0);
            }
            c11904n.itemView.setOnClickListener(new View$OnClickListenerC11893d(c11904n, wPPayInfoBean, i2));
            c11904n.f24284k.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m2057a(WPPayInfoBean wPPayInfoBean) {
        List<WPPayInfoBean> list;
        if (this.f24234f < 0 || (list = this.f24230b) == null || list.isEmpty()) {
            return;
        }
        WPPayInfoBean wPPayInfoBean2 = this.f24230b.get(this.f24234f);
        if (wPPayInfoBean2 != null && !TextUtils.isEmpty(wPPayInfoBean2.getToolId()) && wPPayInfoBean2.getToolId().equals(wPPayInfoBean.getToolId())) {
            wPPayInfoBean2.copy(wPPayInfoBean);
        }
        notifyItemChanged(this.f24234f);
    }

    /* renamed from: a */
    public final synchronized void m2058a(WPDiscountListBean wPDiscountListBean) {
        List<WPPayInfoBean> list;
        try {
            list = this.f24230b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null && !list.isEmpty()) {
            if (wPDiscountListBean != null && wPDiscountListBean.getUpdatePayInfos() != null) {
                if (wPDiscountListBean.getActivityList() == null) {
                    return;
                }
                RunnableC11896g runnableC11896g = new RunnableC11896g(wPDiscountListBean);
                C14255c c14255c = C14255c.C14256a.f27777a;
                c14255c.getClass();
                try {
                    c14255c.f27776a.submit(runnableC11896g);
                } catch (Exception unused) {
                }
            }
        }
    }
}

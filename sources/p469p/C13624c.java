package p469p;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.modules.result.bean.WPBannerInfoBean;
import com.unicom.pay.modules.result.p356ui.WPPayResultActivity;
import java.util.ArrayList;
import p470p0.C13647j;
import p470p0.C13659r;

/* renamed from: p.c */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13624c extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a */
    public Context f27453a;

    /* renamed from: b */
    public ArrayList<WPBannerInfoBean> f27454b;

    /* renamed from: c */
    public InterfaceC13633i f27455c;

    /* renamed from: p.c$a */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C13625a extends SimpleTarget<Drawable> {

        /* renamed from: a */
        public final /* synthetic */ C13631g f27456a;

        /* renamed from: b */
        public final /* synthetic */ float f27457b;

        /* renamed from: c */
        public final /* synthetic */ WPBannerInfoBean f27458c;

        public C13625a(C13631g c13631g, float f, WPBannerInfoBean wPBannerInfoBean) {
            this.f27456a = c13631g;
            this.f27457b = f;
            this.f27458c = wPBannerInfoBean;
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public final void onLoadFailed(@Nullable Drawable drawable) {
            super.onLoadFailed(drawable);
        }

        @Override // com.bumptech.glide.request.target.Target
        public final void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            Drawable drawable = (Drawable) obj;
            this.f27456a.f27472a.setImageDrawable(drawable);
            try {
                if (drawable.getMinimumHeight() > 0 && drawable.getMinimumWidth() > 0) {
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.f27456a.f27472a.getLayoutParams();
                    layoutParams.height = (int) (drawable.getMinimumHeight() * (this.f27457b / drawable.getMinimumWidth()));
                    this.f27456a.f27472a.setLayoutParams(layoutParams);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f27456a.f27472a.setOnClickListener(new View$OnClickListenerC13623b(this));
        }
    }

    /* renamed from: p.c$b */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C13626b extends SimpleTarget<Drawable> {

        /* renamed from: a */
        public final /* synthetic */ C13634j f27460a;

        /* renamed from: b */
        public final /* synthetic */ float f27461b;

        public C13626b(C13634j c13634j, float f) {
            this.f27460a = c13634j;
            this.f27461b = f;
        }

        @Override // com.bumptech.glide.request.target.Target
        public final void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            Drawable drawable = (Drawable) obj;
            this.f27460a.f27475a.setImageDrawable(drawable);
            try {
                if (drawable.getMinimumHeight() <= 0 || drawable.getMinimumWidth() <= 0) {
                    return;
                }
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f27460a.f27475a.getLayoutParams();
                layoutParams.height = (int) (drawable.getMinimumHeight() * (this.f27461b / drawable.getMinimumWidth()));
                this.f27460a.f27475a.setLayoutParams(layoutParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NBSInstrumented
    /* renamed from: p.c$c */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC13627c implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ WPBannerInfoBean f27462a;

        /* renamed from: b */
        public final /* synthetic */ C13634j f27463b;

        public View$OnClickListenerC13627c(WPBannerInfoBean wPBannerInfoBean, C13634j c13634j) {
            this.f27462a = wPBannerInfoBean;
            this.f27463b = c13634j;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            InterfaceC13633i interfaceC13633i = C13624c.this.f27455c;
            if (interfaceC13633i != null) {
                ((WPPayResultActivity) interfaceC13633i).m6163a(this.f27462a, this.f27463b.getAdapterPosition());
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: p.c$d */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C13628d extends SimpleTarget<Drawable> {

        /* renamed from: a */
        public final /* synthetic */ C13632h f27465a;

        /* renamed from: b */
        public final /* synthetic */ float f27466b;

        public C13628d(C13632h c13632h, float f) {
            this.f27465a = c13632h;
            this.f27466b = f;
        }

        @Override // com.bumptech.glide.request.target.Target
        public final void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            Drawable drawable = (Drawable) obj;
            this.f27465a.f27474b.setImageDrawable(drawable);
            try {
                if (drawable.getMinimumHeight() <= 0 || drawable.getMinimumWidth() <= 0) {
                    return;
                }
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f27465a.f27474b.getLayoutParams();
                layoutParams.height = (int) (drawable.getMinimumHeight() * (this.f27466b / drawable.getMinimumWidth()));
                this.f27465a.f27474b.setLayoutParams(layoutParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: p.c$e */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C13629e extends SimpleTarget<Drawable> {

        /* renamed from: a */
        public final /* synthetic */ C13632h f27467a;

        /* renamed from: b */
        public final /* synthetic */ float f27468b;

        public C13629e(C13632h c13632h, float f) {
            this.f27467a = c13632h;
            this.f27468b = f;
        }

        @Override // com.bumptech.glide.request.target.Target
        public final void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            Drawable drawable = (Drawable) obj;
            this.f27467a.f27473a.setImageDrawable(drawable);
            try {
                if (drawable.getMinimumHeight() <= 0 || drawable.getMinimumWidth() <= 0) {
                    return;
                }
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f27467a.f27473a.getLayoutParams();
                layoutParams.height = (int) (drawable.getMinimumHeight() * (this.f27468b / drawable.getMinimumWidth()));
                this.f27467a.f27473a.setLayoutParams(layoutParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NBSInstrumented
    /* renamed from: p.c$f */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC13630f implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ WPBannerInfoBean f27469a;

        /* renamed from: b */
        public final /* synthetic */ C13632h f27470b;

        public View$OnClickListenerC13630f(WPBannerInfoBean wPBannerInfoBean, C13632h c13632h) {
            this.f27469a = wPBannerInfoBean;
            this.f27470b = c13632h;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            InterfaceC13633i interfaceC13633i = C13624c.this.f27455c;
            if (interfaceC13633i != null) {
                ((WPPayResultActivity) interfaceC13633i).m6163a(this.f27469a, this.f27470b.getAdapterPosition());
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: p.c$g */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class C13631g extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public ImageView f27472a;

        public C13631g(@NonNull View view) {
            super(view);
            this.f27472a = (ImageView) view.findViewById(C10531R.C10534id.wopay_pay_result_banner_iv);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: p.c$h */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class C13632h extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public ImageView f27473a;

        /* renamed from: b */
        public ImageView f27474b;

        public C13632h(@NonNull View view) {
            super(view);
            this.f27473a = (ImageView) view.findViewById(C10531R.C10534id.wopay_pay_result_banner1_iv);
            this.f27474b = (ImageView) view.findViewById(C10531R.C10534id.wopay_pay_result_banner2_iv);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: p.c$i */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface InterfaceC13633i {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: p.c$j */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class C13634j extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public ImageView f27475a;

        /* renamed from: b */
        public TextView f27476b;

        public C13634j(@NonNull View view) {
            super(view);
            this.f27475a = (ImageView) view.findViewById(C10531R.C10534id.wopay_pay_result_banner_iv);
            this.f27476b = (TextView) view.findViewById(C10531R.C10534id.wopay_pay_result_banner_get_discount_tv);
        }
    }

    public C13624c(Context context, ArrayList<WPBannerInfoBean> arrayList) {
        this.f27453a = context;
        this.f27454b = arrayList;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        ArrayList<WPBannerInfoBean> arrayList = this.f27454b;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        ArrayList<WPBannerInfoBean> arrayList = this.f27454b;
        if (arrayList == null || "h5Jump".equals(arrayList.get(i).getBannerType())) {
            return 0;
        }
        if ("speedPayOpen".equals(this.f27454b.get(i).getBannerType())) {
            return 1;
        }
        if ("changeText".equals(this.f27454b.get(i).getBannerType())) {
            return 2;
        }
        return "changeBackGround".equals(this.f27454b.get(i).getBannerType()) ? 3 : 0;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TextView textView;
        Resources resources;
        int i2;
        try {
            float m168b = C13659r.m168b(this.f27453a);
            int itemViewType = getItemViewType(i);
            WPBannerInfoBean wPBannerInfoBean = this.f27454b.get(i);
            if (itemViewType != 0 && itemViewType != 1) {
                if (itemViewType != 2) {
                    if (itemViewType == 3) {
                        C13632h c13632h = (C13632h) viewHolder;
                        if (wPBannerInfoBean.isChange()) {
                            c13632h.f27474b.setVisibility(0);
                            c13632h.f27473a.setVisibility(8);
                            C13647j.m177a(this.f27453a, wPBannerInfoBean.getImgUrl2(), new C13628d(c13632h, m168b));
                        } else {
                            c13632h.f27474b.setVisibility(8);
                            c13632h.f27473a.setVisibility(0);
                            C13647j.m177a(this.f27453a, wPBannerInfoBean.getImgUrl(), new C13629e(c13632h, m168b));
                        }
                        c13632h.itemView.setOnClickListener(new View$OnClickListenerC13630f(wPBannerInfoBean, c13632h));
                        return;
                    }
                    return;
                }
                C13634j c13634j = (C13634j) viewHolder;
                C13647j.m177a(this.f27453a, wPBannerInfoBean.getImgUrl(), new C13626b(c13634j, m168b));
                c13634j.itemView.setOnClickListener(new View$OnClickListenerC13627c(wPBannerInfoBean, c13634j));
                if (wPBannerInfoBean.isChange()) {
                    c13634j.f27476b.setText(wPBannerInfoBean.getButtonInfo2());
                    c13634j.f27476b.setEnabled(false);
                    textView = c13634j.f27476b;
                    resources = this.f27453a.getResources();
                    i2 = C10531R.C10532color.wp_red_color;
                } else {
                    c13634j.f27476b.setText(wPBannerInfoBean.getButtonInfo1());
                    c13634j.f27476b.setEnabled(true);
                    textView = c13634j.f27476b;
                    resources = this.f27453a.getResources();
                    i2 = C10531R.C10532color.wp_white;
                }
                textView.setTextColor(resources.getColor(i2));
                return;
            }
            C13647j.m177a(this.f27453a, wPBannerInfoBean.getImgUrl(), new C13625a((C13631g) viewHolder, m168b, wPBannerInfoBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return (i == 0 || i == 1) ? new C13631g(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_result_banner_item, viewGroup, false)) : i == 3 ? new C13632h(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_result_2_banner_item, viewGroup, false)) : new C13634j(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.wp_result_banner_tu_wen_item, viewGroup, false));
    }
}

package p387e0;

import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.bean.WPNoticeInfoBean;
import java.util.ArrayList;

/* renamed from: e0.r */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11882r extends RecyclerView.Adapter<C11883a> {

    /* renamed from: a */
    public ArrayList<WPNoticeInfoBean> f24208a = new ArrayList<>();

    /* renamed from: b */
    public InterfaceC11884b f24209b;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.r$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C11883a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public LinearLayout f24210a;

        /* renamed from: b */
        public TextView f24211b;

        /* renamed from: c */
        public ImageView f24212c;

        /* renamed from: d */
        public TextView f24213d;

        public C11883a(@NonNull View view) {
            super(view);
            this.f24210a = (LinearLayout) view.findViewById(C10531R.C10534id.up_notice_dialog_title_ll);
            this.f24211b = (TextView) view.findViewById(C10531R.C10534id.up_notice_dialog_title_tv);
            this.f24212c = (ImageView) view.findViewById(C10531R.C10534id.up_notice_dialog_arrow_iv);
            this.f24213d = (TextView) view.findViewById(C10531R.C10534id.up_notice_dialog_content_tv);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: e0.r$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11884b {
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        ArrayList<WPNoticeInfoBean> arrayList = this.f24208a;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull C11883a c11883a, int i) {
        C11883a c11883a2 = c11883a;
        try {
            WPNoticeInfoBean wPNoticeInfoBean = this.f24208a.get(i);
            c11883a2.f24211b.setText(wPNoticeInfoBean.getNoticeTitle());
            if (wPNoticeInfoBean.isExpand()) {
                c11883a2.f24213d.setVisibility(0);
                c11883a2.f24212c.setRotation(270.0f);
            } else {
                c11883a2.f24213d.setVisibility(8);
                c11883a2.f24212c.setRotation(90.0f);
            }
            if (!TextUtils.isEmpty(wPNoticeInfoBean.getNoticeDesc())) {
                c11883a2.f24212c.setVisibility(0);
                c11883a2.f24213d.setText(Html.fromHtml(wPNoticeInfoBean.getNoticeDesc()));
            } else {
                c11883a2.f24212c.setVisibility(8);
            }
            c11883a2.f24210a.setOnClickListener(new View$OnClickListenerC11881q(this, wPNoticeInfoBean, c11883a2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public final C11883a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new C11883a(LayoutInflater.from(viewGroup.getContext()).inflate(C10531R.C10535layout.up_notice_dialog_item, viewGroup, false));
    }
}

package cn.sharesdk.system.text.login.p098a;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cn.sharesdk.system.text.login.gui.CountryListView;
import com.mob.tools.utils.ResHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.system.text.login.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CountryListPageLayout {

    /* renamed from: a */
    LinearLayout f3045a;

    /* renamed from: b */
    Context f3046b;

    public CountryListPageLayout(Context context) {
        this.f3045a = null;
        this.f3046b = null;
        this.f3046b = context;
        this.f3045a = new LinearLayout(this.f3046b);
        this.f3045a.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.f3045a.setOrientation(1);
        this.f3045a.setBackgroundColor(-1);
        this.f3045a.addView(CountryTitleLayout.m21560a(this.f3046b, true));
        m21561a(this.f3045a);
    }

    /* renamed from: a */
    public LinearLayout m21562a() {
        return this.f3045a;
    }

    /* renamed from: a */
    protected void m21561a(LinearLayout linearLayout) {
        CountryListView countryListView = new CountryListView(this.f3046b);
        countryListView.setId(ResHelper.getIdRes(this.f3046b, "ssdk_sms_id_clCountry"));
        countryListView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0f));
        linearLayout.addView(countryListView);
    }
}

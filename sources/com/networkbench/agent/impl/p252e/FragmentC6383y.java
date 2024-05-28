package com.networkbench.agent.impl.p252e;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p247d.C6275g;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@TargetApi(11)
/* renamed from: com.networkbench.agent.impl.e.y */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FragmentC6383y extends Fragment implements View.OnClickListener {

    /* renamed from: A */
    private static final int f16105A = 1118480;

    /* renamed from: b */
    private static int f16106b = 13;

    /* renamed from: c */
    private static int f16107c = 20;

    /* renamed from: d */
    private static int f16108d = 150;

    /* renamed from: e */
    private static int f16109e = 150;

    /* renamed from: f */
    private static int f16110f = 1000;

    /* renamed from: g */
    private static int f16111g = 1193046;

    /* renamed from: h */
    private static int f16112h = 8947302;

    /* renamed from: i */
    private static final float f16113i = 18.0f;

    /* renamed from: j */
    private static final int f16114j = 1048575;

    /* renamed from: k */
    private static final int f16115k = 69905;

    /* renamed from: l */
    private static final int f16116l = 69906;

    /* renamed from: m */
    private static final int f16117m = 69907;

    /* renamed from: n */
    private static final int f16118n = 69908;

    /* renamed from: o */
    private static final int f16119o = 69909;

    /* renamed from: p */
    private static final int f16120p = 32;

    /* renamed from: t */
    private static Bitmap f16121t = null;

    /* renamed from: u */
    private static final String f16122u = "selectpage.png";

    /* renamed from: w */
    private static final int f16124w = -591618;

    /* renamed from: y */
    private static final int f16126y = -11440145;

    /* renamed from: z */
    private static final int f16127z = 69911;

    /* renamed from: a */
    private Context f16128a;

    /* renamed from: q */
    private EditText f16129q;

    /* renamed from: r */
    private InterfaceC6368p f16130r;

    /* renamed from: s */
    private C6365n f16131s;

    /* renamed from: v */
    private static final InterfaceC6393e f16123v = C6394f.m10150a();

    /* renamed from: x */
    private static C6373t f16125x = new C6373t();

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.app.Fragment
    public void onPause() {
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        Tracker.onResume(this);
        super.onResume();
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    static {
        C6369q.m10273a().m10272a(new Runnable() { // from class: com.networkbench.agent.impl.e.y.1
            @Override // java.lang.Runnable
            public void run() {
                Harvest.getInstance().getHarvestConnection().getActionDefinerHost();
            }
        });
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        f16121t = C6371s.f16044a;
        f16125x.m10252e(C6353e.f15991a);
        f16125x.m10258b(C6353e.f15992b);
        if (C6275g.f15646a == null) {
            f16125x.m10254d(C6255f.f15554c.toJsonString());
        } else {
            f16125x.m10254d(C6275g.f15646a.toJsonString());
        }
        this.f16130r = new InterfaceC6368p() { // from class: com.networkbench.agent.impl.e.y.2
            @Override // com.networkbench.agent.impl.p252e.InterfaceC6368p
            /* renamed from: a */
            public void mo10188a(int i) {
                String str = "上传失败-未知错误";
                if (i == 200) {
                    str = "上传成功";
                } else if (i != 8888) {
                    switch (i) {
                        case 500:
                            str = "上传失败-后台逻辑异常";
                            break;
                        case 501:
                            str = "上传失败-Token失效，请刷新页面重新扫描二维码";
                            break;
                        case 502:
                            str = "上传失败-请检查AppID";
                            break;
                        case 503:
                            str = "上传失败-上传图片错误";
                            break;
                    }
                } else {
                    str = "获取上传的URL错误";
                }
                FragmentC6383y.m10195b(str);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m10195b(final String str) {
        if (C6353e.m10315a().m10311c() == null || C6353e.m10315a().m10311c().get() == null) {
            return;
        }
        C6353e.m10315a().m10311c().get().runOnUiThread(new Runnable() { // from class: com.networkbench.agent.impl.e.y.3
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(C6353e.m10315a().m10311c().get(), str, 1).show();
            }
        });
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f16128a = layoutInflater.getContext();
        this.f16131s = new C6365n(this.f16128a);
        float f = this.f16128a.getResources().getDisplayMetrics().density;
        f16107c = (int) (7.0f * f);
        f16106b = (int) (13.0f * f);
        f16108d = (int) (40.0f * f);
        f16109e = (int) (50.0f * f);
        f16110f = (int) (f * 400.0f);
        RelativeLayout m10279a = this.f16131s.m10279a();
        m10279a.addView(m10193c());
        RelativeLayout m10196b = m10196b();
        ((RelativeLayout.LayoutParams) m10196b.getLayoutParams()).addRule(3, 69911);
        m10279a.addView(m10196b);
        return m10279a;
    }

    /* renamed from: b */
    private RelativeLayout m10196b() {
        RelativeLayout relativeLayout = new RelativeLayout(this.f16128a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        int i = f16107c;
        layoutParams.leftMargin = i * 2;
        layoutParams.topMargin = i * 2;
        layoutParams.rightMargin = i * 2;
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setBackgroundColor(-1);
        TextView textView = new TextView(this.f16128a);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(14);
        textView.setId(1118481);
        textView.setText("页面截图");
        textView.setTextSize(18.0f);
        layoutParams2.topMargin = f16107c;
        textView.setLayoutParams(layoutParams2);
        relativeLayout.addView(textView);
        View view = new View(this.f16128a);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, 1);
        layoutParams3.addRule(3, 1118481);
        layoutParams3.topMargin = f16107c;
        view.setBackgroundColor(-7829368);
        view.setId(1118484);
        view.setLayoutParams(layoutParams3);
        relativeLayout.addView(view);
        ImageView m10190f = m10190f();
        m10190f.setId(f16112h);
        m10190f.setLayoutParams(m10200a(f16121t.getWidth(), f16121t.getHeight(), m10190f));
        ((RelativeLayout.LayoutParams) m10190f.getLayoutParams()).addRule(3, 1118484);
        ((RelativeLayout.LayoutParams) m10190f.getLayoutParams()).topMargin = f16107c;
        ((RelativeLayout.LayoutParams) m10190f.getLayoutParams()).bottomMargin = f16107c;
        m10190f.setImageBitmap(f16121t);
        relativeLayout.addView(m10190f);
        return relativeLayout;
    }

    /* renamed from: c */
    private RelativeLayout m10193c() {
        RelativeLayout relativeLayout = new RelativeLayout(this.f16128a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        relativeLayout.setPadding(0, f16108d, 0, 0);
        relativeLayout.setBackgroundColor(-1);
        relativeLayout.setId(69911);
        relativeLayout.setLayoutParams(layoutParams);
        TextView textView = new TextView(this.f16128a);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = f16107c;
        textView.setLayoutParams(layoutParams2);
        textView.setText("< 返回");
        textView.setTextSize(18.0f);
        textView.setTextColor(-11440145);
        textView.setOnClickListener(this);
        textView.setId(69905);
        TextView textView2 = new TextView(this.f16128a);
        textView2.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        ((RelativeLayout.LayoutParams) textView2.getLayoutParams()).addRule(14);
        textView2.setText("可视化页面命名");
        textView2.setTextSize(18.0f);
        relativeLayout.addView(textView);
        relativeLayout.addView(textView2);
        relativeLayout.addView(m10192d());
        View view = new View(this.f16128a);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, 1);
        layoutParams3.addRule(3, 1118480);
        layoutParams3.topMargin = f16107c;
        view.setBackgroundColor(-7829368);
        view.setLayoutParams(layoutParams3);
        relativeLayout.addView(view);
        return relativeLayout;
    }

    /* renamed from: d */
    private LinearLayout m10192d() {
        LinearLayout linearLayout = new LinearLayout(this.f16128a);
        linearLayout.setId(f16111g);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, 69905);
        int i = f16107c;
        layoutParams.leftMargin = i * 2;
        layoutParams.topMargin = i * 2;
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setId(1118480);
        EditText m10194b = m10194b("请输入页面名称", 69907);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.weight = 3.0f;
        m10194b.setLayoutParams(layoutParams2);
        this.f16129q = m10194b;
        TextView m10197a = m10197a("上传", 1048575);
        linearLayout.addView(m10194b);
        linearLayout.addView(m10197a);
        return linearLayout;
    }

    @TargetApi(17)
    /* renamed from: a */
    private TextView m10197a(String str, int i) {
        TextView textView = new TextView(this.f16128a);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 1.0f));
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = 17;
        textView.setText(str);
        textView.setTextColor(-11440145);
        textView.setTextSize(18.0f);
        textView.setId(i);
        textView.setTextAlignment(4);
        textView.setOnClickListener(this);
        return textView;
    }

    /* renamed from: b */
    private EditText m10194b(String str, int i) {
        EditText editText = new EditText(this.f16128a);
        editText.setHint(str);
        editText.setSingleLine();
        editText.setId(i);
        editText.setBackground(m10191e());
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        return editText;
    }

    /* renamed from: a */
    private LinearLayout m10201a(int i, int i2) {
        LinearLayout linearLayout = new LinearLayout(this.f16128a);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        int i3 = f16107c;
        layoutParams.leftMargin = i3;
        layoutParams.rightMargin = i3;
        layoutParams.topMargin = i3;
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }

    /* renamed from: e */
    private ShapeDrawable m10191e() {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setColor(-16777216);
        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
        shapeDrawable.getPaint().setStrokeWidth(2.0f);
        return shapeDrawable;
    }

    /* renamed from: f */
    private ImageView m10190f() {
        ImageView imageView = new ImageView(this.f16128a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, f16110f);
        layoutParams.addRule(14);
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    /* renamed from: a */
    private RelativeLayout.LayoutParams m10200a(int i, int i2, View view) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = (i * layoutParams.height) / i2;
        return layoutParams;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 69905) {
            getActivity().getFragmentManager().popBackStack();
        } else if (id != 1048575) {
        } else {
            if (TextUtils.isEmpty(this.f16129q.getText().toString().trim())) {
                Toast.makeText(C6638h.m8963w().m9076K(), "页面名称为空， 请设置相应页面", 1).show();
                return;
            }
            m10189g();
            C6369q.m10273a().m10272a(new RunnableC6387a());
            getActivity().getFragmentManager().popBackStack();
        }
    }

    /* renamed from: g */
    private void m10189g() {
        String obj = this.f16129q.getText().toString();
        String str = C6356g.m10303a(getActivity()) + File.separator + "selectpage.png";
        C6356g.m10302a(str, f16121t);
        C6373t c6373t = f16125x;
        if (obj == null) {
            obj = "";
        }
        c6373t.m10256c(obj);
        f16125x.m10251f(str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.e.y$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class RunnableC6387a implements Runnable {
        public RunnableC6387a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Harvest.getInstance().getHarvestConnection().sendVisualInfo(FragmentC6383y.this.f16130r, FragmentC6383y.f16125x);
        }
    }
}

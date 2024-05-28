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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import java.io.File;
import java.util.ArrayList;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.x */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FragmentC6378x extends Fragment implements View.OnClickListener {

    /* renamed from: b */
    private static int f16076b = 13;

    /* renamed from: c */
    private static int f16077c = 20;

    /* renamed from: d */
    private static int f16078d = 150;

    /* renamed from: e */
    private static int f16079e = 150;

    /* renamed from: f */
    private static int f16080f = 1000;

    /* renamed from: g */
    private static int f16081g = 1193046;

    /* renamed from: h */
    private static int f16082h = 8947302;

    /* renamed from: i */
    private static final float f16083i = 18.0f;

    /* renamed from: j */
    private static final int f16084j = 1048575;

    /* renamed from: k */
    private static final int f16085k = 69905;

    /* renamed from: l */
    private static final int f16086l = 69906;

    /* renamed from: m */
    private static final int f16087m = 69907;

    /* renamed from: n */
    private static final int f16088n = 69908;

    /* renamed from: o */
    private static final int f16089o = 32;

    /* renamed from: t */
    private static Bitmap f16090t = null;

    /* renamed from: u */
    private static final String f16091u = "select.png";

    /* renamed from: v */
    private static final InterfaceC6393e f16092v = C6394f.m10150a();

    /* renamed from: w */
    private static C6377w f16093w = new C6377w();

    /* renamed from: x */
    private static final int f16094x = -11440145;

    /* renamed from: y */
    private static final int f16095y = 69911;

    /* renamed from: z */
    private static final int f16096z = 1118499;

    /* renamed from: a */
    private Context f16097a;

    /* renamed from: p */
    private EditText f16098p;

    /* renamed from: q */
    private EditText f16099q;

    /* renamed from: r */
    private InterfaceC6368p f16100r;

    /* renamed from: s */
    private C6365n f16101s;

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
        C6369q.m10273a().m10272a(new Runnable() { // from class: com.networkbench.agent.impl.e.x.1
            @Override // java.lang.Runnable
            public void run() {
                Harvest.getInstance().getHarvestConnection().getActionDefinerHost();
            }
        });
    }

    @Override // android.app.Fragment
    @TargetApi(11)
    public void onAttach(Context context) {
        super.onAttach(context);
        f16090t = C6351d.f15979b;
        f16093w.m10236a(C6353e.f15991a);
        f16093w.m10234b(C6353e.f15992b);
        f16093w.m10228e(getArguments().getString("viewid"));
        f16093w.m10230d(Harvest.currentActivityName);
        f16093w.m10230d(m10211b());
        this.f16100r = new InterfaceC6368p() { // from class: com.networkbench.agent.impl.e.x.2
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
                FragmentC6378x.m10210b(str);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m10210b(final String str) {
        if (C6353e.m10315a().m10311c() == null || C6353e.m10315a().m10311c().get() == null) {
            return;
        }
        C6353e.m10315a().m10311c().get().runOnUiThread(new Runnable() { // from class: com.networkbench.agent.impl.e.x.3
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(C6353e.m10315a().m10311c().get(), str, 1).show();
            }
        });
    }

    /* renamed from: b */
    private String m10211b() {
        String string = getArguments().getString("activity");
        return TextUtils.isEmpty(string) ? Harvest.currentActivityName : string;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f16097a = layoutInflater.getContext();
        this.f16101s = new C6365n(this.f16097a);
        float f = this.f16097a.getResources().getDisplayMetrics().density;
        f16077c = (int) (7.0f * f);
        f16076b = (int) (13.0f * f);
        f16078d = (int) (40.0f * f);
        f16079e = (int) (50.0f * f);
        f16080f = (int) (f * 350.0f);
        RelativeLayout m10279a = this.f16101s.m10279a();
        m10279a.addView(m10209c());
        m10279a.addView(m10207d());
        LinearLayout linearLayout = new LinearLayout(this.f16097a);
        linearLayout.setId(f16081g);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, 1118499);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.addView(m10213a("页面名称：", "输入页面名称", 69907));
        linearLayout.addView(m10213a("操作名称：", "输入操作名称", 69908));
        m10279a.addView(linearLayout);
        ImageView m10204f = m10204f();
        m10204f.setId(f16082h);
        m10204f.setLayoutParams(m10218a(f16090t.getWidth(), f16090t.getHeight(), m10204f));
        ((RelativeLayout.LayoutParams) m10204f.getLayoutParams()).addRule(3, f16081g);
        ((RelativeLayout.LayoutParams) m10204f.getLayoutParams()).topMargin = f16077c;
        m10204f.setImageBitmap(f16090t);
        m10279a.addView(m10204f);
        Button button = new Button(this.f16097a);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(14);
        layoutParams2.addRule(12);
        layoutParams2.bottomMargin = m10217a(this.f16097a, 300);
        button.setLayoutParams(layoutParams2);
        button.setBackgroundColor(-11440145);
        button.setText("上传");
        button.setId(1048575);
        button.setOnClickListener(this);
        m10279a.addView(button);
        return m10279a;
    }

    /* renamed from: a */
    public static int m10217a(Context context, int i) {
        return i / (context.getResources().getDisplayMetrics().densityDpi / C0567f.f1819h);
    }

    /* renamed from: c */
    private RelativeLayout m10209c() {
        RelativeLayout relativeLayout = new RelativeLayout(this.f16097a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        relativeLayout.setPadding(0, f16078d, 0, 0);
        relativeLayout.setBackgroundColor(-1);
        relativeLayout.setId(69911);
        relativeLayout.setLayoutParams(layoutParams);
        TextView textView = new TextView(this.f16097a);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = f16077c;
        textView.setLayoutParams(layoutParams2);
        textView.setText("< 返回");
        textView.setTextSize(18.0f);
        textView.setTextColor(-11440145);
        textView.setOnClickListener(this);
        textView.setId(69905);
        TextView textView2 = new TextView(this.f16097a);
        textView2.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        ((RelativeLayout.LayoutParams) textView2.getLayoutParams()).addRule(14);
        textView2.setText("可视化操作命名");
        textView2.setTextSize(18.0f);
        relativeLayout.addView(textView);
        relativeLayout.addView(textView2);
        View view = new View(this.f16097a);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, 1);
        layoutParams3.addRule(3, 69905);
        layoutParams3.topMargin = f16077c;
        view.setBackgroundColor(-7829368);
        view.setLayoutParams(layoutParams3);
        relativeLayout.addView(view);
        return relativeLayout;
    }

    /* renamed from: d */
    private RelativeLayout m10207d() {
        RelativeLayout relativeLayout = new RelativeLayout(this.f16097a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        int i = f16077c;
        layoutParams.leftMargin = i;
        layoutParams.rightMargin = i;
        layoutParams.topMargin = i;
        layoutParams.addRule(3, 69911);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setBackgroundColor(-11440145);
        relativeLayout.setId(1118499);
        Spinner spinner = new Spinner(this.f16097a);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        spinner.setId(1118500);
        layoutParams2.addRule(15);
        int i2 = f16077c;
        layoutParams2.topMargin = i2;
        layoutParams2.bottomMargin = i2;
        ArrayList arrayList = new ArrayList();
        arrayList.add("点击");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.f16097a, 17367048, arrayList);
        arrayAdapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        relativeLayout.addView(spinner);
        TextView textView = new TextView(this.f16097a);
        textView.setTextColor(-16777216);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(1, 1118500);
        if (f16093w != null) {
            textView.setText(f16093w.m10233c() + "&" + f16093w.m10231d());
        } else {
            textView.setText("action should show here");
        }
        textView.setSingleLine(true);
        layoutParams3.addRule(15);
        textView.setLayoutParams(layoutParams3);
        relativeLayout.addView(textView);
        return relativeLayout;
    }

    /* renamed from: c */
    private TextView m10208c(String str) {
        TextView textView = new TextView(this.f16097a);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setText(str);
        textView.setTextColor(-16777216);
        textView.setTextSize(18.0f);
        return textView;
    }

    /* renamed from: d */
    private TextView m10206d(String str) {
        TextView textView = new TextView(this.f16097a);
        textView.setTextColor(-16777216);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int i = f16077c;
        layoutParams.leftMargin = i;
        layoutParams.rightMargin = i;
        textView.setLayoutParams(layoutParams);
        textView.setText(str);
        textView.setTextSize(18.0f);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        return textView;
    }

    /* renamed from: a */
    private EditText m10214a(String str, int i) {
        EditText editText = new EditText(this.f16097a);
        editText.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        editText.setHint(str);
        editText.setSingleLine();
        editText.setId(i);
        editText.setTextColor(-16777216);
        editText.setBackground(m10205e());
        editText.setTextSize(18.0f);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        return editText;
    }

    /* renamed from: a */
    private LinearLayout m10219a(int i, int i2) {
        LinearLayout linearLayout = new LinearLayout(this.f16097a);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        int i3 = f16077c;
        layoutParams.leftMargin = i3;
        layoutParams.rightMargin = i3;
        layoutParams.topMargin = i3;
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }

    /* renamed from: a */
    private LinearLayout m10213a(String str, String str2, int i) {
        LinearLayout m10219a = m10219a(-1, -2);
        m10219a.setOrientation(0);
        m10219a.addView(m10208c(str));
        EditText m10214a = m10214a(str2, i);
        if (i != 69906) {
            if (i == 69907) {
                this.f16098p = m10214a;
            } else if (i == 69908) {
                this.f16099q = m10214a;
            }
        }
        m10219a.addView(m10214a);
        return m10219a;
    }

    /* renamed from: e */
    private ShapeDrawable m10205e() {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setColor(-16777216);
        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
        shapeDrawable.getPaint().setStrokeWidth(2.0f);
        return shapeDrawable;
    }

    /* renamed from: a */
    private Button m10212a(String str, boolean z) {
        Button button = new Button(this.f16097a);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (z) {
            layoutParams.rightMargin = f16079e;
        }
        button.setBackgroundColor(-7829368);
        button.setLayoutParams(layoutParams);
        button.setTextSize(18.0f);
        button.setGravity(17);
        button.setText(str);
        return button;
    }

    /* renamed from: f */
    private ImageView m10204f() {
        ImageView imageView = new ImageView(this.f16097a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, f16080f);
        layoutParams.addRule(14);
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    /* renamed from: a */
    private RelativeLayout.LayoutParams m10218a(int i, int i2, View view) {
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
            if (TextUtils.isEmpty(f16093w.m10231d())) {
                Toast.makeText(C6638h.m8963w().m9076K(), "ID为空， 请设置相应控件ID", 1).show();
                return;
            }
            m10203g();
            C6369q.m10273a().m10272a(new RunnableC6382a());
            getActivity().getFragmentManager().popBackStack();
        }
    }

    /* renamed from: g */
    private void m10203g() {
        String obj = this.f16098p.getText().toString();
        String obj2 = this.f16099q.getText().toString();
        String str = C6356g.m10303a(getActivity()) + File.separator + "select.png";
        C6356g.m10302a(str, f16090t);
        C6377w c6377w = f16093w;
        if (obj == null) {
            obj = "";
        }
        c6377w.m10222h(obj);
        C6377w c6377w2 = f16093w;
        if (obj2 == null) {
            obj2 = "";
        }
        c6377w2.m10224g(obj2);
        f16093w.m10221i(str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.e.x$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class RunnableC6382a implements Runnable {
        public RunnableC6382a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Harvest.getInstance().getHarvestConnection().sendActionDefinerData(FragmentC6378x.this.f16100r, FragmentC6378x.f16093w);
        }
    }
}

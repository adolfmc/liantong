package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.bytedance.applog.C3685s0;
import com.bytedance.applog.View$OnTouchListenerC3744z0;
import com.bytedance.applog.picker.Picker;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
@SuppressLint({"ViewConstructor"})
/* renamed from: com.bytedance.applog.a1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class View$OnClickListenerC3537a1 extends C3727x0 implements View.OnClickListener {

    /* renamed from: e */
    public InitConfig f8345e;

    /* renamed from: f */
    public HashMap<String, List<C3736y0>> f8346f;

    /* renamed from: g */
    public Map<Float, Integer> f8347g;

    /* renamed from: h */
    public List<View$OnTouchListenerC3744z0> f8348h;

    /* renamed from: i */
    public HashMap<String, View$OnTouchListenerC3744z0> f8349i;

    /* renamed from: j */
    public LinearLayout f8350j;

    /* renamed from: k */
    public boolean f8351k;

    /* renamed from: l */
    public double f8352l;

    /* renamed from: m */
    public double f8353m;

    /* renamed from: n */
    public double f8354n;

    /* renamed from: o */
    public AsyncTaskC3540c.InterfaceC3541a f8355o;

    /* renamed from: com.bytedance.applog.a1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3538a implements C3685s0.InterfaceC3686a {
        public C3538a() {
        }

        @Override // com.bytedance.applog.C3685s0.InterfaceC3686a
        /* renamed from: a */
        public void mo17113a(C3672q0 c3672q0, List<C3565d1> list, List<C3672q0> list2) {
            String aid = View$OnClickListenerC3537a1.this.f8345e.getAid();
            String versionName = View$OnClickListenerC3537a1.this.getVersionName();
            String m17147b = View$OnClickListenerC3537a1.this.f8911d.m17147b();
            String marqueeCookie = View$OnClickListenerC3537a1.this.f8911d.getMarqueeCookie();
            View$OnClickListenerC3537a1.this.f8346f = new HashMap<>();
            for (C3672q0 c3672q02 : list2) {
                String str = c3672q02.f8667n;
                List<C3736y0> list3 = View$OnClickListenerC3537a1.this.f8346f.get(str);
                if (list3 == null) {
                    list3 = new ArrayList<>();
                    View$OnClickListenerC3537a1.this.f8346f.put(str, list3);
                }
                list3.add(new C3736y0(c3672q02));
            }
            View$OnClickListenerC3537a1 view$OnClickListenerC3537a1 = View$OnClickListenerC3537a1.this;
            new AsyncTaskC3540c(aid, versionName, m17147b, marqueeCookie, view$OnClickListenerC3537a1.f8346f, view$OnClickListenerC3537a1.f8355o).execute(new Void[0]);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.a1$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C3539b implements AsyncTaskC3540c.InterfaceC3541a {
        public C3539b() {
        }
    }

    /* renamed from: com.bytedance.applog.a1$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class AsyncTaskC3540c extends AsyncTask<Void, Void, JSONObject> {

        /* renamed from: a */
        public String f8358a;

        /* renamed from: b */
        public String f8359b;

        /* renamed from: c */
        public String f8360c;

        /* renamed from: d */
        public HashMap<String, List<C3736y0>> f8361d;

        /* renamed from: e */
        public InterfaceC3541a f8362e;

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.bytedance.applog.a1$c$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public interface InterfaceC3541a {
        }

        public AsyncTaskC3540c(String str, String str2, String str3, String str4, HashMap<String, List<C3736y0>> hashMap, InterfaceC3541a interfaceC3541a) {
            this.f8358a = str;
            this.f8359b = str2;
            this.f8360c = str4;
            this.f8361d = hashMap;
            this.f8362e = interfaceC3541a;
        }

        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Void[] voidArr) {
            return C3634n0.m17216a(this.f8358a, this.f8359b, this.f8360c, true, this.f8361d, (int[]) null);
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            JSONObject jSONObject2 = jSONObject;
            InterfaceC3541a interfaceC3541a = this.f8362e;
            if (interfaceC3541a != null) {
                C3539b c3539b = (C3539b) interfaceC3541a;
                View$OnClickListenerC3537a1.this.f8350j.setVisibility(8);
                if (jSONObject2 != null) {
                    int optInt = jSONObject2.optInt("status", -1);
                    if (optInt == 0) {
                        JSONObject optJSONObject = jSONObject2.optJSONObject("data");
                        if (optJSONObject != null) {
                            View$OnClickListenerC3537a1.this.m17337a(optJSONObject.optJSONArray("results"));
                        }
                    } else if (optInt == 3) {
                        View$OnClickListenerC3537a1.this.f8911d.m17149a("", "");
                        View$OnClickListenerC3537a1.this.f8911d.m17143f();
                        View$OnClickListenerC3537a1.this.m17043a("请重新登录.");
                    } else {
                        StringBuilder m17349a = C3535a.m17349a("查询数据失败：");
                        m17349a.append(jSONObject2.optString("message", String.valueOf(optInt)));
                        View$OnClickListenerC3537a1.this.m17043a(m17349a.toString());
                    }
                }
            }
        }
    }

    public View$OnClickListenerC3537a1(Application application, Picker picker, InitConfig initConfig) {
        super(application, picker);
        this.f8355o = new C3539b();
        this.f8345e = initConfig;
        setBackgroundColor(0);
        this.f8347g = View$OnTouchListenerC3744z0.getDefaultColorStops();
        LayoutInflater.from(application).inflate(C3527R.C3531layout.heat_layout, this);
        this.f8348h = new ArrayList();
        this.f8349i = new HashMap<>();
        this.f8352l = 0.25d;
        this.f8353m = 0.0d;
        this.f8354n = 0.01d;
        this.f8350j = (LinearLayout) findViewById(C3527R.C3530id.progress_container);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getVersionName() {
        try {
            return this.f8908a.getPackageManager().getPackageInfo(this.f8908a.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
            return "1.0.0";
        }
    }

    /* renamed from: a */
    public final void m17337a(JSONArray jSONArray) {
        int[][] iArr;
        int[] iArr2;
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            List<C3736y0> list = optJSONObject.optBoolean("isHtml") ? this.f8346f.get(optJSONObject.optString("tag")) : this.f8346f.get(optJSONObject.optString("pageKey"));
            JSONArray optJSONArray = optJSONObject.optJSONArray("items");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                    if (list != null) {
                        String optString = optJSONObject2.optString("elementPath");
                        for (C3736y0 c3736y0 : list) {
                            if (c3736y0.f8668o.equals(optString)) {
                                JSONArray optJSONArray2 = optJSONObject2.optJSONArray("positions");
                                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                    ArrayList<String> arrayList = new ArrayList<>();
                                    for (int i4 = 0; i4 < optJSONArray2.length(); i4++) {
                                        arrayList.add(optJSONArray2.optString(i4));
                                    }
                                    c3736y0.f8670q = arrayList;
                                }
                                c3736y0.f8943G = optJSONObject2.optDouble("pvHeat");
                                c3736y0.f8944H = optJSONObject2.optDouble("uvHeat");
                                c3736y0.f8945I = optJSONObject2.optInt("pv");
                                c3736y0.f8946J = optJSONObject2.optInt("uv");
                                JSONArray optJSONArray3 = optJSONObject2.optJSONArray("pvGrid");
                                if (optJSONArray3 != null) {
                                    c3736y0.f8947K = new int[optJSONArray3.length()];
                                    for (int i5 = 0; i5 < optJSONArray3.length(); i5++) {
                                        JSONArray optJSONArray4 = optJSONArray3.optJSONArray(i5);
                                        c3736y0.f8947K[i5] = new int[optJSONArray4.length()];
                                        for (int i6 = 0; i6 < optJSONArray4.length(); i6++) {
                                            c3736y0.f8947K[i5][i6] = optJSONArray4.optInt(i6);
                                        }
                                    }
                                }
                                JSONArray optJSONArray5 = optJSONObject2.optJSONArray("uvGrid");
                                if (optJSONArray5 != null) {
                                    c3736y0.f8948L = new int[optJSONArray5.length()];
                                    for (int i7 = 0; i7 < optJSONArray5.length(); i7++) {
                                        JSONArray optJSONArray6 = optJSONArray5.optJSONArray(i7);
                                        c3736y0.f8948L[i7] = new int[optJSONArray6.length()];
                                        for (int i8 = 0; i8 < optJSONArray6.length(); i8++) {
                                            c3736y0.f8947K[i7][i8] = optJSONArray6.optInt(i8);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (String str : this.f8346f.keySet()) {
            List<C3736y0> list2 = this.f8346f.get(str);
            if (list2 != null && list2.size() > 0) {
                if (!list2.get(i).f8675v) {
                    for (C3736y0 c3736y02 : list2) {
                        if (c3736y02.f8773y > 0 && c3736y02.f8774z > 0) {
                            View$OnTouchListenerC3744z0 view$OnTouchListenerC3744z0 = new View$OnTouchListenerC3744z0(getContext());
                            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(c3736y02.f8773y, c3736y02.f8774z);
                            int[] iArr3 = c3736y02.f8772x;
                            layoutParams.leftMargin = iArr3[i];
                            layoutParams.topMargin = iArr3[1];
                            view$OnTouchListenerC3744z0.setMinimum(this.f8353m);
                            view$OnTouchListenerC3744z0.setMaximum(this.f8352l);
                            view$OnTouchListenerC3744z0.setBlur(this.f8354n);
                            view$OnTouchListenerC3744z0.m16992a(new View$OnTouchListenerC3744z0.C3745a(0.5f, 0.5f, c3736y02.f8943G));
                            view$OnTouchListenerC3744z0.setRadius(Math.min(c3736y02.f8773y, c3736y02.f8774z) / 2.0d);
                            view$OnTouchListenerC3744z0.setColorStops(this.f8347g);
                            view$OnTouchListenerC3744z0.setOvalForOnePoint(true);
                            view$OnTouchListenerC3744z0.setTag(c3736y02);
                            this.f8348h.add(view$OnTouchListenerC3744z0);
                            addView(view$OnTouchListenerC3744z0, layoutParams);
                        }
                    }
                } else {
                    View$OnTouchListenerC3744z0 view$OnTouchListenerC3744z02 = this.f8349i.get(str);
                    if (view$OnTouchListenerC3744z02 == null) {
                        view$OnTouchListenerC3744z02 = new View$OnTouchListenerC3744z0(getContext());
                        this.f8349i.put(str, view$OnTouchListenerC3744z02);
                    }
                    for (C3736y0 c3736y03 : list2) {
                        if (c3736y03.f8773y > 0 && c3736y03.f8774z > 0 && (iArr = c3736y03.f8947K) != null) {
                            int length = iArr.length;
                            int length2 = iArr[i].length;
                            int i9 = i;
                            while (i9 < length) {
                                int i10 = i;
                                while (i10 < length2) {
                                    if (c3736y03.f8947K[i9][i10] != 0) {
                                        if (view$OnTouchListenerC3744z02.getData().size() == 0 && view$OnTouchListenerC3744z02.getDataBuffer().size() == 0) {
                                            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(c3736y03.f8769D, c3736y03.f8770E);
                                            int[] iArr4 = c3736y03.f8768C;
                                            layoutParams2.leftMargin = iArr4[i];
                                            layoutParams2.topMargin = iArr4[1];
                                            view$OnTouchListenerC3744z02.setMinimum(this.f8353m);
                                            view$OnTouchListenerC3744z02.setMaximum(this.f8352l);
                                            view$OnTouchListenerC3744z02.setBlur(this.f8354n);
                                            view$OnTouchListenerC3744z02.setRadius(50.0d);
                                            view$OnTouchListenerC3744z02.setColorStops(this.f8347g);
                                            addView(view$OnTouchListenerC3744z02, layoutParams2);
                                        }
                                        int i11 = ((int) (((1.0f / (length2 * 2)) + ((1.0f / length2) * i10)) * c3736y03.f8773y)) + c3736y03.f8772x[i];
                                        int[] iArr5 = c3736y03.f8768C;
                                        view$OnTouchListenerC3744z02.m16992a(new View$OnTouchListenerC3744z0.C3745a(((i11 - iArr5[i]) * 1.0f) / c3736y03.f8769D, (((((int) (((1.0f / (length * 2)) + ((1.0f / length) * i9)) * c3736y03.f8774z)) + iArr2[1]) - iArr5[1]) * 1.0f) / c3736y03.f8770E, c3736y03.f8943G));
                                    }
                                    i10++;
                                    i = 0;
                                }
                                i9++;
                                i = 0;
                            }
                        }
                    }
                    view$OnTouchListenerC3744z02.m16990b();
                    i = 0;
                }
            }
        }
    }

    @Override // com.bytedance.applog.C3727x0
    /* renamed from: b */
    public void mo17042b() {
        removeAllViews();
        addView(this.f8350j);
        this.f8350j.setVisibility(0);
        this.f8348h.clear();
        this.f8349i.clear();
        new C3685s0().m17121a((C3685s0.InterfaceC3686a) new C3538a(), Looper.myLooper(), false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view != this.f8350j) {
            for (View$OnTouchListenerC3744z0 view$OnTouchListenerC3744z0 : this.f8348h) {
                C3736y0 c3736y0 = (C3736y0) view$OnTouchListenerC3744z0.getTag();
                view$OnTouchListenerC3744z0.m16994a();
                view$OnTouchListenerC3744z0.m16992a(this.f8351k ? new View$OnTouchListenerC3744z0.C3745a(0.5f, 0.5f, c3736y0.f8944H) : new View$OnTouchListenerC3744z0.C3745a(0.5f, 0.5f, c3736y0.f8943G));
                view$OnTouchListenerC3744z0.m16990b();
            }
            this.f8351k = !this.f8351k;
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}

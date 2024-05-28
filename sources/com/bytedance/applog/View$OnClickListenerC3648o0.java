package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Build;
import android.support.p083v4.view.PagerAdapter;
import android.support.p083v4.view.ViewPager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.bytedance.applog.picker.PagerSlidingTabStrip;
import com.bytedance.applog.picker.Picker;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
@SuppressLint({"ViewConstructor"})
/* renamed from: com.bytedance.applog.o0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class View$OnClickListenerC3648o0 extends C3727x0 implements ViewPager.OnPageChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /* renamed from: L */
    public static final int f8621L = C3527R.C3530id.tag_view_name;

    /* renamed from: M */
    public static final CharSequence f8622M = "7日";

    /* renamed from: N */
    public static final CharSequence f8623N = "48时";

    /* renamed from: O */
    public static final String[] f8624O = {"背景页面", "控件按钮"};

    /* renamed from: A */
    public final Button f8625A;

    /* renamed from: B */
    public final ImageView f8626B;

    /* renamed from: C */
    public Bitmap f8627C;

    /* renamed from: D */
    public Bitmap f8628D;

    /* renamed from: E */
    public final PagerSlidingTabStrip f8629E;

    /* renamed from: F */
    public final ViewPager f8630F;

    /* renamed from: G */
    public final TextView f8631G;

    /* renamed from: H */
    public String f8632H;

    /* renamed from: I */
    public JSONArray[] f8633I;

    /* renamed from: J */
    public JSONArray[] f8634J;

    /* renamed from: K */
    public JSONArray[] f8635K;

    /* renamed from: e */
    public final View f8636e;

    /* renamed from: f */
    public final InitConfig f8637f;

    /* renamed from: g */
    public final TextView f8638g;

    /* renamed from: h */
    public final C3658p0 f8639h;

    /* renamed from: i */
    public final TextView f8640i;

    /* renamed from: j */
    public final TextView f8641j;

    /* renamed from: k */
    public final EditText f8642k;

    /* renamed from: l */
    public final LinearLayout f8643l;

    /* renamed from: m */
    public final LinearLayout f8644m;

    /* renamed from: n */
    public final LinearLayout f8645n;

    /* renamed from: o */
    public final View f8646o;

    /* renamed from: p */
    public int f8647p;

    /* renamed from: q */
    public String f8648q;

    /* renamed from: r */
    public final C3658p0 f8649r;

    /* renamed from: s */
    public C3672q0 f8650s;

    /* renamed from: t */
    public boolean f8651t;

    /* renamed from: u */
    public boolean f8652u;

    /* renamed from: v */
    public int f8653v;

    /* renamed from: w */
    public String f8654w;

    /* renamed from: x */
    public final Switch f8655x;

    /* renamed from: y */
    public final EditText f8656y;

    /* renamed from: z */
    public final Switch f8657z;

    /* renamed from: com.bytedance.applog.o0$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC3650b extends AsyncTask<Void, String, JSONObject> {

        /* renamed from: a */
        public C3672q0 f8658a;

        /* renamed from: b */
        public String f8659b;

        public AsyncTaskC3650b(C3672q0 c3672q0, String str) {
            this.f8658a = c3672q0;
            this.f8659b = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:37:0x0106  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x01a1  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x01a4  */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public org.json.JSONObject doInBackground(java.lang.Void[] r20) {
            /*
                Method dump skipped, instructions count: 519
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.View$OnClickListenerC3648o0.AsyncTaskC3650b.doInBackground(java.lang.Object[]):java.lang.Object");
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            JSONObject jSONObject2 = jSONObject;
            if (this.f8658a == View$OnClickListenerC3648o0.this.f8650s && jSONObject2 != null) {
                int optInt = jSONObject2.optInt("status", -1);
                if (optInt == 0) {
                    View$OnClickListenerC3648o0.this.f8911d.m17145d();
                    Toast.makeText(View$OnClickListenerC3648o0.this.f8908a, "绑定成功", 1).show();
                } else if (optInt == 3) {
                    View$OnClickListenerC3648o0.this.f8911d.m17149a("", "");
                    View$OnClickListenerC3648o0.this.f8911d.m17143f();
                    View$OnClickListenerC3648o0.this.m17043a("请重新登录.");
                } else {
                    StringBuilder m17349a = C3535a.m17349a("绑定失败: ");
                    m17349a.append(jSONObject2.optString("message", String.valueOf(optInt)));
                    String sb = m17349a.toString();
                    View$OnClickListenerC3648o0.m17191a(View$OnClickListenerC3648o0.this, false);
                    View$OnClickListenerC3648o0.this.m17043a(sb);
                }
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
        }

        @Override // android.os.AsyncTask
        public void onProgressUpdate(String[] strArr) {
            View$OnClickListenerC3648o0.this.m17043a(strArr[0]);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.o0$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C3651c extends PagerAdapter {
        public /* synthetic */ C3651c(C3649a c3649a) {
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
            C3658p0 c3658p0;
            if (i == 0) {
                c3658p0 = View$OnClickListenerC3648o0.this.f8639h;
            } else {
                c3658p0 = View$OnClickListenerC3648o0.this.f8649r;
            }
            viewGroup.removeView(c3658p0);
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public int getCount() {
            return 2;
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public CharSequence getPageTitle(int i) {
            if (i == 0) {
                return View$OnClickListenerC3648o0.f8622M;
            }
            return View$OnClickListenerC3648o0.f8623N;
        }

        @Override // android.support.p083v4.view.PagerAdapter
        @NonNull
        public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
            C3658p0 c3658p0;
            if (i == 0) {
                c3658p0 = View$OnClickListenerC3648o0.this.f8639h;
            } else {
                c3658p0 = View$OnClickListenerC3648o0.this.f8649r;
            }
            viewGroup.addView(c3658p0);
            return c3658p0;
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }
    }

    /* renamed from: com.bytedance.applog.o0$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC3652d extends AsyncTask<Void, Void, JSONObject> {

        /* renamed from: a */
        public final C3672q0 f8662a;

        /* renamed from: b */
        public final String f8663b;

        public AsyncTaskC3652d(C3672q0 c3672q0, String str) {
            this.f8662a = c3672q0;
            this.f8663b = str;
        }

        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Void[] voidArr) {
            C3672q0 c3672q0 = (C3672q0) this.f8662a.m24467clone();
            int childCount = View$OnClickListenerC3648o0.this.f8643l.getChildCount();
            c3672q0.f8669p = new ArrayList<>(childCount);
            for (int i = 1; i < childCount; i++) {
                CheckBox checkBox = (CheckBox) View$OnClickListenerC3648o0.this.f8643l.getChildAt(i);
                if (checkBox.isChecked()) {
                    c3672q0.f8669p.add(checkBox.getText().toString());
                }
            }
            if (!View$OnClickListenerC3648o0.this.f8657z.isChecked() && c3672q0.f8670q != null) {
                c3672q0.f8670q = c3672q0.f8676w;
            }
            return C3634n0.m17219a(View$OnClickListenerC3648o0.this.f8637f.getAid(), View$OnClickListenerC3648o0.m17185e(View$OnClickListenerC3648o0.this), this.f8663b, c3672q0);
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            JSONArray optJSONArray;
            JSONObject jSONObject2 = jSONObject;
            if (this.f8662a != View$OnClickListenerC3648o0.this.f8650s) {
                return;
            }
            String str = "查询数据失败";
            if (jSONObject2 != null) {
                int optInt = jSONObject2.optInt("status", -1);
                if (optInt == 0) {
                    JSONObject optJSONObject = jSONObject2.optJSONObject("data");
                    if (optJSONObject != null && (optJSONArray = optJSONObject.optJSONArray("results")) != null && optJSONArray.length() == 2) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(0);
                        JSONObject optJSONObject3 = optJSONArray.optJSONObject(1);
                        if (optJSONObject2 != null && optJSONObject3 != null) {
                            if (optJSONObject2.optString("elementPath", null) == null) {
                                optJSONObject2 = optJSONObject3;
                                optJSONObject3 = optJSONObject2;
                            }
                            View$OnClickListenerC3648o0.m17192a(View$OnClickListenerC3648o0.this, optJSONObject3, optJSONObject2);
                            return;
                        }
                    }
                } else if (optInt == 3) {
                    str = "请重新登录.";
                    View$OnClickListenerC3648o0.this.f8911d.m17149a("", "");
                    View$OnClickListenerC3648o0.this.f8911d.m17143f();
                } else {
                    StringBuilder m17349a = C3535a.m17349a("查询数据失败：");
                    m17349a.append(jSONObject2.optString("message", String.valueOf(optInt)));
                    str = m17349a.toString();
                }
            }
            View$OnClickListenerC3648o0.this.m17043a(str);
            View$OnClickListenerC3648o0.m17192a(View$OnClickListenerC3648o0.this, null, null);
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            int childCount = View$OnClickListenerC3648o0.this.f8643l.getChildCount();
            ArrayList arrayList = new ArrayList(childCount);
            for (int i = 1; i < childCount; i++) {
                CheckBox checkBox = (CheckBox) View$OnClickListenerC3648o0.this.f8643l.getChildAt(i);
                if (checkBox.isChecked()) {
                    arrayList.add(checkBox.getText().toString());
                }
            }
            if (arrayList.size() > 0) {
                View$OnClickListenerC3648o0.this.f8638g.setText(arrayList.toString());
            } else {
                View$OnClickListenerC3648o0.this.f8638g.setText("匹配任意文字");
            }
            View$OnClickListenerC3648o0.this.f8645n.setVisibility(0);
            View$OnClickListenerC3648o0.this.f8649r.m17182a("正在刷新", null, null, null);
            C3658p0 c3658p0 = View$OnClickListenerC3648o0.this.f8649r;
            c3658p0.f8699p = true;
            c3658p0.invalidate();
            C3658p0 c3658p02 = View$OnClickListenerC3648o0.this.f8639h;
            c3658p02.f8699p = true;
            c3658p02.invalidate();
        }
    }

    /* renamed from: com.bytedance.applog.o0$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC3653e extends AsyncTask<Void, Void, Bitmap[]> {

        /* renamed from: a */
        public final C3672q0 f8665a;

        public AsyncTaskC3653e(C3672q0 c3672q0) {
            this.f8665a = c3672q0;
        }

        @Override // android.os.AsyncTask
        public Bitmap[] doInBackground(Void[] voidArr) {
            C3672q0 c3672q0;
            Context context;
            int[] iArr = this.f8665a.f8772x;
            RectF rectF = new RectF(iArr[0], iArr[1], iArr[0] + c3672q0.f8773y, iArr[1] + c3672q0.f8774z);
            View[] m17319a = C3560c2.m17319a();
            if (!C3559c1.f8389a && (context = AppLog.getContext()) != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                if (Build.VERSION.SDK_INT >= 17) {
                    defaultDisplay.getRealMetrics(displayMetrics);
                } else {
                    defaultDisplay.getMetrics(displayMetrics);
                }
                C3559c1.f8392d = displayMetrics.widthPixels;
                C3559c1.f8393e = displayMetrics.heightPixels;
                double d = C3559c1.f8392d;
                C3559c1.f8394f = 720.0d / d;
                double d2 = C3559c1.f8394f;
                C3559c1.f8390b = (int) (d * d2);
                C3559c1.f8391c = (int) (d2 * C3559c1.f8393e);
                C3559c1.f8389a = true;
                TextPaint textPaint = new TextPaint();
                int i = (int) (displayMetrics.density * 2.0f);
                Rect rect = new Rect();
                textPaint.setAntiAlias(true);
                textPaint.setColor(-13421773);
                textPaint.setTextSize(displayMetrics.density * 14.0f);
                textPaint.getTextBounds("截图失败", 0, 4, rect);
                int i2 = i * 2;
                C3559c1.f8395g = Bitmap.createBitmap(rect.width() + i2, rect.height() + i2, Bitmap.Config.ALPHA_8);
                new Canvas(C3559c1.f8395g).drawText("截图失败", i, rect.height(), textPaint);
            }
            Bitmap bitmap = C3559c1.f8395g;
            Bitmap[] bitmapArr = {bitmap, bitmap};
            try {
                Bitmap m17320a = C3559c1.m17320a(m17319a);
                bitmapArr[0] = m17320a;
                Bitmap copy = m17320a.copy(Bitmap.Config.RGB_565, true);
                Canvas canvas = new Canvas(copy);
                canvas.scale((float) C3559c1.f8394f, (float) C3559c1.f8394f);
                Context context2 = AppLog.getContext();
                Paint paint = new Paint();
                float m17332a = C3554b2.m17332a(context2, 3.0f);
                paint.setColor(1291798564);
                canvas.drawRoundRect(rectF, m17332a, m17332a, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(C3554b2.m17332a(context2, 1.0f));
                paint.setColor(-436254684);
                canvas.drawRoundRect(rectF, m17332a, m17332a, paint);
                bitmapArr[1] = copy;
            } catch (Exception e) {
                C3704u2.m17108a("U SHALL NOT PASS!", e);
            }
            return bitmapArr;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap[] bitmapArr) {
            Bitmap[] bitmapArr2 = bitmapArr;
            C3672q0 c3672q0 = this.f8665a;
            View$OnClickListenerC3648o0 view$OnClickListenerC3648o0 = View$OnClickListenerC3648o0.this;
            if (c3672q0 == view$OnClickListenerC3648o0.f8650s) {
                view$OnClickListenerC3648o0.f8627C = bitmapArr2[0];
                view$OnClickListenerC3648o0.f8628D = bitmapArr2[1];
                view$OnClickListenerC3648o0.f8626B.setImageBitmap(view$OnClickListenerC3648o0.f8628D);
                View$OnClickListenerC3648o0.this.f8626B.getLayoutParams().width = -2;
                View$OnClickListenerC3648o0.this.f8626B.getLayoutParams().height = -2;
            }
        }
    }

    public View$OnClickListenerC3648o0(Application application, Picker picker, InitConfig initConfig) {
        super(application, picker);
        this.f8647p = 0;
        this.f8653v = 0;
        this.f8633I = new JSONArray[4];
        this.f8634J = new JSONArray[4];
        this.f8635K = new JSONArray[4];
        this.f8637f = initConfig;
        LayoutInflater.from(application).inflate(C3527R.C3531layout.bind_layout, this);
        this.f8636e = findViewById(C3527R.C3530id.backButton);
        this.f8636e.setOnClickListener(this);
        this.f8625A = (Button) findViewById(C3527R.C3530id.saveButton);
        this.f8625A.setOnClickListener(this);
        this.f8629E = (PagerSlidingTabStrip) findViewById(C3527R.C3530id.viewpager_title);
        this.f8630F = (ViewPager) findViewById(C3527R.C3530id.chartPager);
        this.f8630F.setAdapter(new C3651c(null));
        this.f8630F.setOnPageChangeListener(this);
        this.f8629E.setViewPager(this.f8630F);
        this.f8639h = new C3658p0(getContext());
        this.f8649r = new C3658p0(getContext());
        this.f8655x = (Switch) findViewById(C3527R.C3530id.typeRadioGroup);
        this.f8655x.setOnCheckedChangeListener(this);
        this.f8641j = (TextView) findViewById(C3527R.C3530id.viewBindId);
        this.f8640i = (TextView) findViewById(C3527R.C3530id.pageBindId);
        this.f8642k = (EditText) findViewById(C3527R.C3530id.viewEdit);
        this.f8656y = (EditText) findViewById(C3527R.C3530id.pageEdit);
        this.f8631G = (TextView) findViewById(C3527R.C3530id.tipText);
        this.f8631G.setOnClickListener(this);
        this.f8657z = (Switch) findViewById(C3527R.C3530id.posSwitch);
        this.f8657z.setOnCheckedChangeListener(this);
        this.f8638g = (TextView) findViewById(C3527R.C3530id.contentText);
        this.f8638g.setOnClickListener(this);
        this.f8643l = (LinearLayout) findViewById(C3527R.C3530id.contentContainer);
        this.f8644m = (LinearLayout) findViewById(C3527R.C3530id.contentContainerOuter);
        this.f8644m.setOnClickListener(this);
        this.f8645n = (LinearLayout) findViewById(C3527R.C3530id.progress_container);
        this.f8626B = (ImageView) findViewById(C3527R.C3530id.shotImage);
        this.f8646o = findViewById(C3527R.C3530id.tipLayout);
        this.f8646o.setOnClickListener(this);
    }

    /* renamed from: a */
    public static /* synthetic */ void m17191a(View$OnClickListenerC3648o0 view$OnClickListenerC3648o0, boolean z) {
        view$OnClickListenerC3648o0.f8645n.setVisibility(z ? 0 : 8);
    }

    /* renamed from: e */
    public static /* synthetic */ String m17185e(View$OnClickListenerC3648o0 view$OnClickListenerC3648o0) {
        try {
            return view$OnClickListenerC3648o0.f8908a.getPackageManager().getPackageInfo(view$OnClickListenerC3648o0.f8908a.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
            return "1.0.0";
        }
    }

    /* renamed from: a */
    public final void m17190a(boolean z) {
        this.f8645n.setVisibility(z ? 0 : 8);
    }

    @Override // com.bytedance.applog.C3727x0
    /* renamed from: a */
    public boolean mo17044a() {
        if (this.f8644m.getVisibility() == 0) {
            this.f8644m.setVisibility(8);
            return true;
        } else if (this.f8646o.getVisibility() == 0) {
            this.f8646o.setVisibility(8);
            return true;
        } else {
            return false;
        }
    }

    @Override // com.bytedance.applog.C3727x0
    /* renamed from: b */
    public void mo17042b() {
        this.f8630F.setCurrentItem(0);
        this.f8656y.setText("");
        int i = 8;
        this.f8640i.setVisibility(8);
        this.f8642k.setText("");
        this.f8641j.setVisibility(8);
        this.f8626B.setImageBitmap(null);
        this.f8646o.setVisibility(8);
        ArrayList<String> arrayList = this.f8650s.f8676w;
        int size = arrayList != null ? arrayList.size() : 0;
        if (size > 0) {
            ((ViewGroup) this.f8657z.getParent()).setVisibility(0);
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < size; i2++) {
                String str = this.f8650s.f8676w.get(i2);
                String str2 = this.f8650s.f8670q.get(i2);
                if (!str.equals(str2)) {
                    if (str.equals("*")) {
                        str = str2;
                    }
                    arrayList2.add(str);
                }
            }
            this.f8657z.setText(arrayList2.toString());
        } else {
            ((ViewGroup) this.f8657z.getParent()).setVisibility(8);
        }
        ArrayList<String> arrayList3 = this.f8650s.f8669p;
        int size2 = arrayList3 != null ? arrayList3.size() : 0;
        if (size2 > 0) {
            int parseColor = Color.parseColor("#212121");
            int color = getResources().getColor(17170443);
            int m17332a = C3554b2.m17332a(getContext(), 15.0f);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, C3554b2.m17332a(getContext(), 56.0f));
            layoutParams.setMargins(0, 1, 0, 0);
            this.f8643l.removeAllViews();
            TextView textView = new TextView(getContext());
            textView.setText("匹配文字");
            textView.setTextColor(Color.parseColor("#212121"));
            textView.setGravity(17);
            textView.setBackgroundColor(-1);
            textView.setTextSize(1, 17.0f);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, C3527R.C3529drawable.picker_close, 0);
            textView.setPadding(m17332a, 0, m17332a, 0);
            this.f8643l.addView(textView, layoutParams);
            for (int i3 = 0; i3 < size2; i3++) {
                CheckBox checkBox = new CheckBox(getContext());
                checkBox.setId(f8621L);
                checkBox.setText(this.f8650s.f8669p.get(i3));
                checkBox.setGravity(19);
                checkBox.setTextColor(parseColor);
                checkBox.setBackgroundColor(color);
                checkBox.setPadding(m17332a, 0, m17332a, 0);
                checkBox.setTextSize(1, 15.0f);
                if (Build.VERSION.SDK_INT >= 17) {
                    checkBox.setLayoutDirection(1);
                }
                checkBox.setOnCheckedChangeListener(this);
                this.f8643l.addView(checkBox, layoutParams);
            }
            ((ViewGroup) this.f8638g.getParent()).setVisibility(0);
        } else {
            ((ViewGroup) this.f8638g.getParent()).setVisibility(8);
        }
        this.f8644m.setVisibility(8);
        this.f8631G.setVisibility((size > 0 || size2 > 0) ? 0 : 0);
        new AsyncTaskC3653e(this.f8650s).execute(new Void[0]);
        new AsyncTaskC3652d(this.f8650s, this.f8911d.getMarqueeCookie()).execute(new Void[0]);
    }

    /* renamed from: c */
    public final void m17188c() {
        int i = this.f8655x.isChecked() ? 0 : 2;
        int i2 = i / 2;
        this.f8639h.m17182a(f8624O[i2], this.f8633I[i], this.f8634J[i], this.f8635K[i]);
        int i3 = i + 1;
        this.f8649r.m17182a(f8624O[i2], this.f8633I[i3], this.f8634J[i3], this.f8635K[i3]);
        C3658p0 c3658p0 = this.f8649r;
        c3658p0.f8699p = false;
        c3658p0.invalidate();
        C3658p0 c3658p02 = this.f8639h;
        c3658p02.f8699p = false;
        c3658p02.invalidate();
        this.f8639h.m17181a(this.f8655x.isChecked());
        this.f8649r.m17181a(this.f8655x.isChecked());
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Tracker.onCheckedChanged(compoundButton, z);
        if (compoundButton == this.f8657z || f8621L == compoundButton.getId()) {
            new AsyncTaskC3652d(this.f8650s, this.f8911d.getMarqueeCookie()).execute(new Void[0]);
        } else if (compoundButton == this.f8655x) {
            m17188c();
        } else {
            C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AsyncTask asyncTaskC3652d;
        String str;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view == this.f8636e) {
            this.f8911d.m17145d();
        } else if (view == this.f8625A) {
            if (this.f8647p == 0 || this.f8653v == 0) {
                str = "无数据，请先刷新";
            } else if (TextUtils.isEmpty(this.f8642k.getText()) && TextUtils.isEmpty(this.f8656y.getText())) {
                str = "请输入控件按钮名称";
            } else if (TextUtils.isEmpty(this.f8656y.getText())) {
                str = "请输入页面名称";
            } else if (TextUtils.isEmpty(this.f8642k.getText())) {
                String trim = this.f8656y.getText().toString().trim();
                if (this.f8653v == 2 && !TextUtils.isEmpty(trim) && TextUtils.equals(this.f8654w, trim) && !this.f8652u) {
                    str = "页面已经绑定";
                } else {
                    m17190a(true);
                    asyncTaskC3652d = new AsyncTaskC3650b(this.f8650s, this.f8911d.getMarqueeCookie());
                    asyncTaskC3652d.execute(new Void[0]);
                }
            } else {
                String trim2 = this.f8656y.getText().toString().trim();
                String trim3 = this.f8642k.getText().toString().trim();
                boolean z = this.f8653v == 2 && !TextUtils.isEmpty(trim2) && TextUtils.equals(this.f8654w, trim2) && !this.f8652u;
                boolean z2 = this.f8647p == 2 && !TextUtils.isEmpty(trim3) && TextUtils.equals(this.f8648q, trim3) && !this.f8651t;
                if (z && z2) {
                    str = "页面及按钮均已绑定";
                } else {
                    m17190a(true);
                    asyncTaskC3652d = new AsyncTaskC3650b(this.f8650s, this.f8911d.getMarqueeCookie());
                    asyncTaskC3652d.execute(new Void[0]);
                }
            }
            m17043a(str);
        } else if (view == this.f8630F) {
            asyncTaskC3652d = new AsyncTaskC3652d(this.f8650s, this.f8911d.getMarqueeCookie());
            asyncTaskC3652d.execute(new Void[0]);
        } else if (view == this.f8631G) {
            this.f8646o.setVisibility(0);
        } else {
            View view2 = this.f8646o;
            if (view == view2) {
                view2.setVisibility(8);
            } else if (view == this.f8638g) {
                this.f8644m.setVisibility(0);
            } else {
                LinearLayout linearLayout = this.f8644m;
                if (view == linearLayout) {
                    linearLayout.setVisibility(8);
                } else {
                    C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
                }
            }
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        NBSActionInstrumentation.onPageSelectedEnter(i, this);
        NBSActionInstrumentation.onPageSelectedExit();
    }

    /* renamed from: a */
    public static /* synthetic */ void m17192a(View$OnClickListenerC3648o0 view$OnClickListenerC3648o0, JSONObject jSONObject, JSONObject jSONObject2) {
        String str;
        String str2;
        String str3;
        String str4;
        if (jSONObject == null || jSONObject2 == null) {
            for (int i = 0; i < 4; i++) {
                view$OnClickListenerC3648o0.f8633I[i] = null;
                view$OnClickListenerC3648o0.f8635K[i] = null;
                view$OnClickListenerC3648o0.f8634J[i] = null;
            }
            view$OnClickListenerC3648o0.f8652u = true;
            view$OnClickListenerC3648o0.f8651t = true;
            view$OnClickListenerC3648o0.f8648q = "";
            view$OnClickListenerC3648o0.f8654w = "";
            view$OnClickListenerC3648o0.f8647p = 0;
            view$OnClickListenerC3648o0.f8653v = 0;
            str = "";
            str2 = null;
            str3 = null;
            str4 = str;
        } else {
            if (view$OnClickListenerC3648o0.f8650s.f8675v) {
                view$OnClickListenerC3648o0.f8632H = jSONObject.optString("pageKey");
            }
            view$OnClickListenerC3648o0.f8633I[0] = jSONObject.optJSONArray("label");
            view$OnClickListenerC3648o0.f8633I[1] = jSONObject.optJSONArray("recentLabel");
            view$OnClickListenerC3648o0.f8633I[2] = jSONObject2.optJSONArray("label");
            view$OnClickListenerC3648o0.f8633I[3] = jSONObject2.optJSONArray("recentLabel");
            view$OnClickListenerC3648o0.f8635K[0] = jSONObject.optJSONArray("uv");
            view$OnClickListenerC3648o0.f8635K[1] = jSONObject.optJSONArray("recentUv");
            view$OnClickListenerC3648o0.f8635K[2] = jSONObject2.optJSONArray("uv");
            view$OnClickListenerC3648o0.f8635K[3] = jSONObject2.optJSONArray("recentUv");
            view$OnClickListenerC3648o0.f8634J[0] = jSONObject.optJSONArray("pv");
            view$OnClickListenerC3648o0.f8634J[1] = jSONObject.optJSONArray("recentPv");
            view$OnClickListenerC3648o0.f8634J[2] = jSONObject2.optJSONArray("pv");
            view$OnClickListenerC3648o0.f8634J[3] = jSONObject2.optJSONArray("recentPv");
            view$OnClickListenerC3648o0.f8652u = jSONObject.optBoolean("needUploadPic", false);
            view$OnClickListenerC3648o0.f8651t = jSONObject2.optBoolean("needUploadPic", false);
            view$OnClickListenerC3648o0.f8654w = jSONObject.optString("desc", "");
            view$OnClickListenerC3648o0.f8648q = jSONObject2.optString("desc", "");
            view$OnClickListenerC3648o0.f8653v = jSONObject.optBoolean("isBind", false) ? 2 : 1;
            view$OnClickListenerC3648o0.f8647p = jSONObject2.optBoolean("isBind", false) ? 2 : 1;
            str4 = jSONObject.optString("actionId", "");
            str = jSONObject2.optString("actionId", "");
            str3 = jSONObject.optString("desc", "");
            str2 = jSONObject2.optString("desc", "");
        }
        view$OnClickListenerC3648o0.m17188c();
        view$OnClickListenerC3648o0.f8640i.setVisibility(8);
        view$OnClickListenerC3648o0.f8656y.setText("");
        int i2 = view$OnClickListenerC3648o0.f8653v;
        if (i2 != 0 && i2 == 2) {
            view$OnClickListenerC3648o0.f8640i.setVisibility(0);
            view$OnClickListenerC3648o0.f8640i.setText("ID " + str4);
            if (!TextUtils.isEmpty(str3)) {
                view$OnClickListenerC3648o0.f8656y.setText(str3);
            }
        }
        view$OnClickListenerC3648o0.f8641j.setVisibility(8);
        view$OnClickListenerC3648o0.f8642k.setText("");
        int i3 = view$OnClickListenerC3648o0.f8647p;
        if (i3 != 0 && i3 == 2) {
            view$OnClickListenerC3648o0.f8641j.setVisibility(0);
            view$OnClickListenerC3648o0.f8641j.setText("ID " + str);
            if (!TextUtils.isEmpty(str2)) {
                view$OnClickListenerC3648o0.f8642k.setText(str2);
            }
        }
        view$OnClickListenerC3648o0.m17190a(false);
    }
}

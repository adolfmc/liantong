package p393h;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.C10546a;
import com.unicom.pay.utils.buried.WPBusinessInfoBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Stack;
import p386e.InterfaceC11846a;
import p393h.AbstractView$OnClickListenerC12011e;
import p395i.C12048b;
import p408n.C12344h;
import p408n.C12348l;
import p411o.AbstractC12375a;
import p411o.InterfaceC12376b;
import p470p0.C13636a;
import p470p0.C13646i;
import p470p0.C13665v;
import p472q0.C13692n;
import p477s0.C14116a;

@NBSInstrumented
/* renamed from: h.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractDialogInterface$OnCancelListenerC12000b<P extends AbstractC12375a> extends AppCompatActivity implements DialogInterface.OnCancelListener, View.OnClickListener, InterfaceC11846a, InterfaceC12016h, InterfaceC12376b {

    /* renamed from: a */
    public P f24311a;

    /* renamed from: b */
    public ImageButton f24312b;

    /* renamed from: c */
    public TextView f24313c;

    /* renamed from: d */
    public TextView f24314d;

    /* renamed from: e */
    public C14116a f24315e;

    /* renamed from: f */
    public long f24316f;

    /* renamed from: g */
    public long f24317g;

    /* renamed from: h */
    public long f24318h;

    /* renamed from: i */
    public WPBusinessInfoBean f24319i;

    /* renamed from: j */
    public WPBusinessInfoBean f24320j;

    /* renamed from: k */
    public C12048b f24321k;

    /* renamed from: h.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C12001a implements AbstractView$OnClickListenerC12011e.InterfaceC12013b {
        public C12001a() {
        }

        @Override // p393h.AbstractView$OnClickListenerC12011e.InterfaceC12013b
        /* renamed from: a */
        public final void mo1983a() {
            P p = AbstractDialogInterface$OnCancelListenerC12000b.this.f24311a;
            if (p != null) {
                p.m1794c();
            }
        }
    }

    /* renamed from: h.b$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC12002b implements Runnable {
        public RunnableC12002b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            C14116a c14116a = AbstractDialogInterface$OnCancelListenerC12000b.this.f24315e;
            if (c14116a != null) {
                c14116a.dismissAllowingStateLoss();
            }
        }
    }

    /* renamed from: h.b$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C12003c implements Application.ActivityLifecycleCallbacks {

        /* renamed from: a */
        public final /* synthetic */ InputMethodManager f24324a;

        /* renamed from: b */
        public final /* synthetic */ Field f24325b;

        /* renamed from: c */
        public final /* synthetic */ Field f24326c;

        /* renamed from: d */
        public final /* synthetic */ Method f24327d;

        public C12003c(InputMethodManager inputMethodManager, Field field, Field field2, Method method) {
            this.f24324a = inputMethodManager;
            this.f24325b = field;
            this.f24326c = field2;
            this.f24327d = method;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
            activity.getWindow().getDecorView().getRootView().getViewTreeObserver().addOnGlobalFocusChangeListener(new View$OnAttachStateChangeListenerC12005e(this.f24324a, this.f24325b, this.f24326c, this.f24327d));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
        }
    }

    /* renamed from: h.b$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class RunnableC12004d implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ String f24328a;

        public RunnableC12004d(String str) {
            this.f24328a = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AbstractDialogInterface$OnCancelListenerC12000b abstractDialogInterface$OnCancelListenerC12000b = AbstractDialogInterface$OnCancelListenerC12000b.this;
            String str = this.f24328a;
            abstractDialogInterface$OnCancelListenerC12000b.getClass();
            C13692n.m135a(str);
        }
    }

    /* renamed from: h.b$e */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class View$OnAttachStateChangeListenerC12005e implements MessageQueue.IdleHandler, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalFocusChangeListener {

        /* renamed from: a */
        public final InputMethodManager f24330a;

        /* renamed from: b */
        public final Field f24331b;

        /* renamed from: c */
        public final Field f24332c;

        /* renamed from: d */
        public final Method f24333d;

        public View$OnAttachStateChangeListenerC12005e(InputMethodManager inputMethodManager, Field field, Field field2, Method method) {
            this.f24330a = inputMethodManager;
            this.f24331b = field;
            this.f24332c = field2;
            this.f24333d = method;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
        public final void onGlobalFocusChanged(View view, View view2) {
            if (view2 == null) {
                return;
            }
            if (view != null) {
                view.removeOnAttachStateChangeListener(this);
            }
            Looper.myQueue().removeIdleHandler(this);
            view2.addOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            Looper.myQueue().removeIdleHandler(this);
            Looper.myQueue().addIdleHandler(this);
        }

        @Override // android.os.MessageQueue.IdleHandler
        public final boolean queueIdle() {
            Field field;
            Object obj;
            Activity activity;
            Method method;
            InputMethodManager inputMethodManager;
            Object[] objArr;
            Context baseContext;
            try {
                if (this.f24332c != null && this.f24333d != null && (field = this.f24331b) != null && (obj = field.get(this.f24330a)) != null) {
                    synchronized (obj) {
                        View view = (View) this.f24332c.get(this.f24330a);
                        if (view != null) {
                            boolean z = true;
                            if (view.getWindowVisibility() != 8) {
                                view.removeOnAttachStateChangeListener(this);
                                view.addOnAttachStateChangeListener(this);
                            } else {
                                Context context = view.getContext();
                                while (!(context instanceof Application)) {
                                    if (!(context instanceof Activity)) {
                                        if (!(context instanceof ContextWrapper) || (baseContext = ((ContextWrapper) context).getBaseContext()) == context) {
                                            break;
                                        }
                                        context = baseContext;
                                    } else {
                                        activity = (Activity) context;
                                        break;
                                    }
                                }
                                activity = null;
                                if (activity != null && activity.getWindow() != null) {
                                    Window window = activity.getWindow();
                                    if (window != null) {
                                        View peekDecorView = window.peekDecorView();
                                        if (peekDecorView.getWindowVisibility() == 8) {
                                            z = false;
                                        }
                                        if (z) {
                                            peekDecorView.requestFocusFromTouch();
                                        } else {
                                            method = this.f24333d;
                                            inputMethodManager = this.f24330a;
                                            objArr = new Object[0];
                                            method.invoke(inputMethodManager, objArr);
                                        }
                                    }
                                }
                                method = this.f24333d;
                                inputMethodManager = this.f24330a;
                                objArr = new Object[0];
                                method.invoke(inputMethodManager, objArr);
                            }
                        }
                    }
                }
            } catch (Exception unused) {
            }
            return false;
        }
    }

    /* renamed from: a */
    public static void m2009a(Application application) {
        int i = Build.VERSION.SDK_INT;
        if (i < 15 || i > 23) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) application.getSystemService("input_method");
        try {
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mServedView");
            declaredField2.setAccessible(true);
            Method declaredMethod = InputMethodManager.class.getDeclaredMethod("finishInputLocked", new Class[0]);
            declaredMethod.setAccessible(true);
            InputMethodManager.class.getDeclaredMethod("focusIn", View.class).setAccessible(true);
            application.registerActivityLifecycleCallbacks(new C12003c(inputMethodManager, declaredField2, declaredField, declaredMethod));
        } catch (Exception unused) {
        }
    }

    /* renamed from: D */
    public String mo56D() {
        return "-";
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: I */
    public final void mo1793I() {
        TextView textView = this.f24313c;
        if (textView != null) {
            textView.postDelayed(new RunnableC12002b(), 600L);
            return;
        }
        C14116a c14116a = this.f24315e;
        if (c14116a != null) {
            c14116a.dismissAllowingStateLoss();
        }
    }

    /* renamed from: O */
    public String mo53O() {
        return "-";
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: Y */
    public final void mo1792Y() {
        if (this.f24315e == null) {
            C14116a c14116a = new C14116a();
            this.f24315e = c14116a;
            c14116a.setCancelable(true);
            this.f24315e.f24345b = new C12001a();
        }
        if (this.f24315e.isAdded() || isFinishing()) {
            return;
        }
        this.f24315e.show(getSupportFragmentManager(), "loadingDialog");
    }

    /* renamed from: Z */
    public final void m2010Z() {
        C12048b c12048b = this.f24321k;
        if (c12048b != null) {
            c12048b.dismissAllowingStateLoss();
        }
    }

    /* renamed from: a */
    public final C12048b m2005a(String str, String str2, C12048b.InterfaceC12052d interfaceC12052d) {
        if (interfaceC12052d == null) {
            interfaceC12052d = new C11999a();
        }
        C12048b.C12049a m1972a = new C12048b.C12049a(this).m1972a(str, -1);
        C12348l.C12349a c12349a = m1972a.f24382a;
        c12349a.f25001j = -1;
        c12349a.f24998g = false;
        c12349a.f24999h = true;
        m1972a.f24382a.f25000i = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        return m1972a.m1971a(str2, interfaceC12052d).m1974a();
    }

    /* renamed from: a */
    public final void m2008a(Bundle bundle, Class<? extends Activity> cls) {
        Intent intent = new Intent(getBaseContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        Activity parent = getParent();
        if (parent == null) {
            startActivity(intent);
        } else {
            parent.startActivity(intent);
        }
    }

    /* renamed from: a */
    public final void m2007a(Class<? extends Activity> cls, int i, Bundle bundle) {
        Intent intent = new Intent(getBaseContext(), cls);
        intent.putExtras(bundle);
        Activity parent = getParent();
        if (parent == null) {
            startActivityForResult(intent, i);
        } else {
            parent.startActivityForResult(intent, i);
        }
    }

    /* renamed from: a0 */
    public abstract P mo2002a0();

    /* renamed from: b0 */
    public final boolean m2001b0() {
        if (System.currentTimeMillis() - this.f24316f < 800) {
            return true;
        }
        this.f24316f = System.currentTimeMillis();
        return false;
    }

    /* renamed from: c0 */
    public final void m2000c0() {
        try {
            if (this.f24319i == null) {
                this.f24319i = WPBusinessInfoBean.generatePageEntity();
            }
            WPTrendsEventsUtils.trendsPageData(this.f24319i, mo56D(), mo36p(), mo53O(), C13646i.m182a(this.f24318h - this.f24317g));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d0 */
    public void mo1999d0() {
        this.f24318h = System.currentTimeMillis();
        m2000c0();
    }

    /* renamed from: e0 */
    public abstract int mo1998e0();

    /* renamed from: f0 */
    public abstract void mo1997f0();

    /* renamed from: g0 */
    public abstract void mo1996g0();

    /* renamed from: h0 */
    public void mo1995h0() {
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: i */
    public final void mo1790i(String str) {
        runOnUiThread(new RunnableC12004d(str));
    }

    /* renamed from: i0 */
    public void mo1994i0() {
        C13665v.m163a(this);
        C13665v.m160c(this);
        C13665v.m162a(this, getResources().getColor(C10531R.C10532color.wp_white));
    }

    /* renamed from: j */
    public final void m1993j(String str) {
        this.f24313c = (TextView) findViewById(C10531R.C10534id.wopay_header_titleTv);
        this.f24314d = (TextView) findViewById(C10531R.C10534id.wopay_header_mobileTv);
        m1992j0();
        this.f24312b = (ImageButton) findViewById(C10531R.C10534id.wapay_header_arrow);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(C10531R.C10534id.wopay_title_layout);
        this.f24312b.setOnClickListener(this);
        this.f24313c.setText(str);
    }

    /* renamed from: j0 */
    public final void m1992j0() {
        C10546a c10546a = C10546a.C10576i.f20125a;
        if (c10546a != null) {
            String str = c10546a.f20056f;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.f24314d.setVisibility(0);
            this.f24314d.setText(str);
        }
    }

    /* renamed from: k */
    public final void m1991k(String str) {
        try {
            if (this.f24320j == null) {
                this.f24320j = WPBusinessInfoBean.generateButtonEntity();
            }
            WPTrendsEventsUtils.trendsPageButtonData(this.f24320j, mo56D(), mo36p(), mo53O(), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: k0 */
    public void mo1990k0() {
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: l */
    public final Activity mo1789l() {
        return this;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        P p = this.f24311a;
        if (p != null) {
            p.m1794c();
        }
    }

    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (m2001b0()) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (view.getId() != C10531R.C10534id.wapay_header_arrow) {
            NBSActionInstrumentation.onClickEventExit();
        } else {
            finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(mo1998e0());
            C13636a.f27477a.push(this);
            mo1995h0();
            P mo2002a0 = mo2002a0();
            this.f24311a = mo2002a0;
            if (mo2002a0 != null) {
                mo2002a0.m1796a(this);
            }
            mo1994i0();
            mo1996g0();
            mo1997f0();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            C14116a c14116a = this.f24315e;
            if (c14116a != null) {
                c14116a.dismissAllowingStateLoss();
            }
            super.onDestroy();
            Stack<FragmentActivity> stack = C13636a.f27477a;
            C13636a.f27477a.remove(this);
            mo1990k0();
            P p = this.f24311a;
            if (p != null) {
                Reference reference = p.f25033a;
                if (reference != null) {
                    reference.clear();
                    p.f25033a = null;
                }
                p.m1794c();
            }
            m2009a(getApplication());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        mo1999d0();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f24317g = System.currentTimeMillis();
        WPTrendsEventsUtils.addWindow(mo53O());
        if (this.f24319i == null) {
            this.f24319i = WPBusinessInfoBean.generatePageEntity();
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        C13636a.f27478b++;
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        C13636a.f27478b--;
    }

    /* renamed from: p */
    public String mo36p() {
        return "-";
    }

    /* renamed from: a */
    public final void m2006a(String str, String str2, C12048b.InterfaceC12050b interfaceC12050b, String str3, C12048b.InterfaceC12052d interfaceC12052d) {
        C12048b.C12049a m1972a = new C12048b.C12049a(this).m1972a(str, -1);
        C12348l.C12349a c12349a = m1972a.f24382a;
        c12349a.f25001j = -1;
        c12349a.f24998g = false;
        C12348l.C12349a c12349a2 = m1972a.f24382a;
        c12349a2.f25000i = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        c12349a2.f24996e = new C12344h(c12349a2, str2, interfaceC12050b);
        m1972a.m1971a(str3, interfaceC12052d).m1974a();
    }

    /* renamed from: a */
    public final void m2004a(String str, String str2, String str3, C12048b.InterfaceC12050b interfaceC12050b, String str4, C12048b.InterfaceC12052d interfaceC12052d) {
        C12048b.C12049a m1972a = new C12048b.C12049a(this).m1973a(str).m1972a(str2, -1);
        m1972a.f24382a.f25001j = -1;
        C12348l.C12349a c12349a = m1972a.f24382a;
        c12349a.f25000i = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        c12349a.f24996e = new C12344h(c12349a, str3, interfaceC12050b);
        m1972a.m1971a(str4, interfaceC12052d).m1974a();
    }

    /* renamed from: a */
    public final void m2003a(String str, String str2, String str3, C12048b.InterfaceC12050b interfaceC12050b, String str4, boolean z, C12048b.InterfaceC12052d interfaceC12052d, boolean z2, boolean z3) {
        C12048b.C12049a m1972a = new C12048b.C12049a(this).m1973a(str).m1972a(str2, 42);
        C12348l.C12349a c12349a = m1972a.f24382a;
        c12349a.f25001j = -1;
        c12349a.f24998g = z2;
        c12349a.f24999h = z3;
        m1972a.f24382a.f25000i = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        this.f24321k = m1972a.m1970a(str3, z, interfaceC12050b).m1969a(str4, z, interfaceC12052d).m1974a();
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: a */
    public final void mo1791a(int i) {
        C13692n.m135a(getResources().getString(i));
    }
}

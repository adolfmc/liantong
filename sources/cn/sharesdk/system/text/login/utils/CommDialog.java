package cn.sharesdk.system.text.login.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.sharesdk.framework.utils.SizeHelper;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.gui.RoundRectLayout;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: cn.sharesdk.system.text.login.utils.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CommDialog extends Dialog implements View.OnClickListener {

    /* renamed from: a */
    private int f3131a;

    /* renamed from: b */
    private int f3132b;

    /* renamed from: c */
    private int f3133c;

    /* renamed from: d */
    private int f3134d;

    /* renamed from: e */
    private int f3135e;

    /* renamed from: f */
    private LinearLayout f3136f;

    /* renamed from: g */
    private TextView f3137g;

    /* renamed from: h */
    private TextView f3138h;

    /* renamed from: i */
    private Button f3139i;

    /* renamed from: j */
    private Button f3140j;

    /* renamed from: k */
    private Button f3141k;

    public CommDialog(Context context, int i) {
        super(context, i);
    }

    /* renamed from: a */
    public void m21461a(int i) {
        this.f3132b = i;
    }

    /* renamed from: b */
    public void m21459b(int i) {
        this.f3131a = i;
    }

    /* renamed from: c */
    public void m21457c(int i) {
        this.f3133c = i;
    }

    /* renamed from: d */
    public void m21456d(int i) {
        this.f3134d = i;
    }

    /* renamed from: e */
    public void m21455e(int i) {
        this.f3135e = i;
    }

    /* renamed from: a */
    public TextView m21462a() {
        return this.f3138h;
    }

    /* renamed from: b */
    public Button m21460b() {
        return this.f3140j;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawableResource(17170445);
        m21458c();
        RoundRectLayout roundRectLayout = new RoundRectLayout(getContext());
        roundRectLayout.setRound(30.0f);
        roundRectLayout.addView(this.f3136f);
        setContentView(roundRectLayout);
    }

    /* renamed from: c */
    private void m21458c() {
        SizeHelper.m21680a(getContext());
        this.f3136f = new LinearLayout(getContext());
        this.f3136f.setOrientation(1);
        this.f3136f.setGravity(17);
        this.f3136f.setBackgroundColor(-1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((ResHelper.getScreenWidth(getContext()) / 4) * 3, -2);
        int m21679b = SizeHelper.m21679b(20);
        this.f3136f.setLayoutParams(layoutParams);
        if (this.f3132b != 0) {
            this.f3137g = new TextView(getContext());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.topMargin = m21679b * 2;
            this.f3137g.setLayoutParams(layoutParams2);
            m21679b = SizeHelper.m21679b(20);
            this.f3137g.setGravity(17);
            this.f3137g.setBackgroundColor(-1);
            this.f3137g.setText(this.f3132b);
            this.f3137g.setTextColor(-13421773);
            this.f3137g.setTextSize(0, SizeHelper.m21679b(26));
            this.f3137g.setGravity(17);
            this.f3137g.setTypeface(Typeface.DEFAULT_BOLD);
            this.f3136f.addView(this.f3137g);
        }
        this.f3138h = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        int i = m21679b * 2;
        layoutParams3.setMargins(0, m21679b, 0, i);
        if (this.f3132b == 0) {
            layoutParams3.setMargins(0, i, 0, i);
        }
        this.f3138h.setLayoutParams(layoutParams3);
        this.f3138h.setGravity(17);
        this.f3138h.setText(this.f3131a);
        this.f3138h.setTextColor(-13421773);
        this.f3138h.setTextSize(0, SizeHelper.m21679b(22));
        this.f3136f.addView(this.f3138h);
        if (this.f3133c != 0 || this.f3134d != 0 || this.f3135e != 0) {
            View view = new View(getContext());
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, SizeHelper.m21679b(2)));
            view.setBackgroundColor(-3487030);
            this.f3136f.addView(view);
        }
        if (this.f3133c != 0) {
            this.f3139i = new Button(getContext());
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
            this.f3139i.setLayoutParams(layoutParams4);
            int m21679b2 = SizeHelper.m21679b(20);
            this.f3139i.setPadding(0, m21679b2, 0, m21679b2);
            this.f3139i.setText(this.f3133c);
            this.f3139i.setTextColor(-14060034);
            this.f3139i.setTextSize(0, SizeHelper.m21679b(24));
            this.f3139i.setBackgroundColor(-1);
            this.f3139i.setOnTouchListener(new View.OnTouchListener() { // from class: cn.sharesdk.system.text.login.utils.a.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        view2.setBackgroundColor(-1513240);
                        return false;
                    } else if (motionEvent.getAction() == 1) {
                        view2.setBackgroundColor(-1);
                        return false;
                    } else {
                        return false;
                    }
                }
            });
            this.f3139i.setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.system.text.login.utils.a.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    CommDialog.this.dismiss();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.f3136f.addView(this.f3139i, layoutParams4);
        }
        if (this.f3135e == 0 || this.f3134d == 0) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.f3141k = new Button(getContext());
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, SizeHelper.m21679b(80), 1.0f);
        layoutParams5.rightMargin = SizeHelper.m21679b(1);
        this.f3141k.setLayoutParams(layoutParams5);
        int m21679b3 = SizeHelper.m21679b(12);
        this.f3141k.setPadding(m21679b3, m21679b3, m21679b3, m21679b3);
        this.f3141k.setBackgroundColor(-1);
        this.f3141k.setText(this.f3134d);
        this.f3141k.setTextColor(-14060034);
        this.f3141k.setTextSize(0, SizeHelper.m21679b(24));
        this.f3141k.setOnTouchListener(new View.OnTouchListener() { // from class: cn.sharesdk.system.text.login.utils.a.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    view2.setBackgroundColor(-1513240);
                    return false;
                } else if (motionEvent.getAction() == 1) {
                    view2.setBackgroundColor(-1);
                    return false;
                } else {
                    return false;
                }
            }
        });
        this.f3141k.setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.system.text.login.utils.a.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                CommDialog.this.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        linearLayout.addView(this.f3141k);
        View view2 = new View(getContext());
        view2.setLayoutParams(new LinearLayout.LayoutParams(SizeHelper.m21679b(2), -1));
        view2.setBackgroundColor(-3487030);
        linearLayout.addView(view2);
        this.f3140j = new Button(getContext());
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, SizeHelper.m21679b(80), 1.0f);
        layoutParams6.rightMargin = SizeHelper.m21679b(1);
        this.f3140j.setLayoutParams(layoutParams6);
        this.f3140j.setPadding(m21679b3, m21679b3, m21679b3, m21679b3);
        this.f3140j.setBackgroundColor(-1);
        this.f3140j.setId(ResHelper.getIdRes(getContext(), "btn_right"));
        this.f3140j.setText(this.f3135e);
        this.f3140j.setTextColor(-14060034);
        this.f3140j.setTextSize(0, SizeHelper.m21679b(24));
        this.f3140j.setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.system.text.login.utils.a.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                NBSActionInstrumentation.onClickEventEnter(view3, this);
                Tracker.onClick(view3);
                CommDialog.this.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f3140j.setOnTouchListener(new View.OnTouchListener() { // from class: cn.sharesdk.system.text.login.utils.a.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view3, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    view3.setBackgroundColor(-1513240);
                    return false;
                } else if (motionEvent.getAction() == 1) {
                    view3.setBackgroundColor(-1);
                    return false;
                } else {
                    return false;
                }
            }
        });
        linearLayout.addView(this.f3140j);
        this.f3136f.addView(linearLayout);
    }

    @Override // android.app.Dialog
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        super.setOnDismissListener(onDismissListener);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (this.f3139i == view || this.f3141k == view) {
            dismiss();
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}

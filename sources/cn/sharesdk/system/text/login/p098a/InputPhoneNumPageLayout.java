package cn.sharesdk.system.text.login.p098a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.SizeHelper;
import cn.sharesdk.system.text.login.utils.CommEditText;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.gui.RoundRectLayout;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.system.text.login.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class InputPhoneNumPageLayout {

    /* renamed from: a */
    private LinearLayout f3061a;

    /* renamed from: b */
    private CommEditText f3062b;

    /* renamed from: c */
    private CommEditText f3063c;

    /* renamed from: d */
    private CommEditText f3064d;

    /* renamed from: e */
    private CommEditText f3065e;

    /* renamed from: f */
    private CommEditText f3066f;

    /* renamed from: g */
    private Button f3067g;

    /* renamed from: h */
    private ImageView f3068h;

    /* renamed from: i */
    private Context f3069i;

    public InputPhoneNumPageLayout(Context context) {
        this.f3069i = context;
    }

    /* renamed from: a */
    public LinearLayout m21546a() {
        m21538h();
        return this.f3061a;
    }

    /* renamed from: h */
    private void m21538h() {
        SizeHelper.m21680a(this.f3069i);
        this.f3061a = new LinearLayout(this.f3069i);
        this.f3061a.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.f3061a.setOrientation(1);
        this.f3061a.setGravity(17);
        this.f3061a.addView(m21537i());
        LinearLayout linearLayout = new LinearLayout(this.f3069i);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        int m21679b = SizeHelper.m21679b(30);
        linearLayout.setPadding(m21679b, 0, m21679b, m21679b);
        linearLayout.setBackgroundColor(-1);
        this.f3061a.addView(linearLayout);
        TextView textView = new TextView(this.f3069i);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        textView.setGravity(17);
        textView.setPadding(0, m21679b, 0, m21679b);
        int stringRes = ResHelper.getStringRes(this.f3069i, "ssdk_sms_top_text");
        if (stringRes > 0) {
            textView.setText(stringRes);
        }
        textView.setTextColor(-14013648);
        textView.setTextSize(0, SizeHelper.m21679b(32));
        linearLayout.addView(textView, layoutParams2);
        LinearLayout linearLayout2 = new LinearLayout(this.f3069i);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.bottomMargin = SizeHelper.m21679b(10);
        linearLayout2.setLayoutParams(layoutParams3);
        this.f3062b = new CommEditText(this.f3069i);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        int m21679b2 = SizeHelper.m21679b(20);
        this.f3062b.setPadding(0, m21679b2, 0, m21679b2);
        this.f3062b.setLayoutParams(layoutParams4);
        this.f3062b.setFocusable(false);
        this.f3062b.setBackgroundColor(-1);
        int stringRes2 = ResHelper.getStringRes(this.f3069i, "ssdk_sms_zone");
        if (stringRes2 > 0) {
            this.f3062b.setText(stringRes2);
        }
        this.f3062b.setTextSize(0, SizeHelper.m21679b(24));
        this.f3063c = new CommEditText(this.f3069i);
        this.f3063c.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 2.0f));
        this.f3063c.setPadding(0, m21679b2, 0, m21679b2);
        this.f3063c.setFocusable(false);
        this.f3063c.setBackgroundColor(-1);
        int stringRes3 = ResHelper.getStringRes(this.f3069i, "ssdk_sms_china");
        if (stringRes3 > 0) {
            this.f3063c.setText(stringRes3);
        }
        this.f3063c.setTextColor(-9910945);
        this.f3063c.setTextSize(0, SizeHelper.m21679b(24));
        this.f3064d = new CommEditText(this.f3069i);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, -2, 1.0f);
        this.f3064d.setGravity(21);
        this.f3064d.setLayoutParams(layoutParams5);
        this.f3064d.setPadding(0, m21679b2, 0, m21679b2);
        this.f3064d.setFocusable(false);
        this.f3064d.setBackgroundColor(-1);
        this.f3064d.setText(">");
        this.f3064d.setTextColor(-7829368);
        this.f3064d.setTextSize(0, SizeHelper.m21679b(24));
        linearLayout2.addView(this.f3062b);
        linearLayout2.addView(this.f3063c);
        linearLayout2.addView(this.f3064d);
        linearLayout.addView(linearLayout2);
        LinearLayout linearLayout3 = new LinearLayout(this.f3069i);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams6.bottomMargin = SizeHelper.m21679b(30);
        linearLayout3.setLayoutParams(layoutParams6);
        this.f3065e = new CommEditText(this.f3069i);
        this.f3065e.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.f3065e.setPadding(0, m21679b2, 0, m21679b2);
        this.f3065e.setBackgroundColor(-1);
        this.f3065e.setText("+86");
        this.f3065e.setTextSize(0, SizeHelper.m21679b(24));
        this.f3065e.setFocusable(false);
        this.f3066f = new CommEditText(this.f3069i);
        this.f3066f.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 3.0f));
        this.f3066f.setPadding(0, m21679b2, 0, m21679b2);
        this.f3066f.setBackgroundColor(-1);
        int stringRes4 = ResHelper.getStringRes(this.f3069i, "ssdk_sms_input_phone_hint");
        if (stringRes4 > 0) {
            this.f3066f.setHint(stringRes4);
        }
        this.f3066f.setTextSize(0, SizeHelper.m21679b(24));
        this.f3066f.setInputType(2);
        linearLayout3.addView(this.f3065e);
        linearLayout3.addView(this.f3066f);
        linearLayout.addView(linearLayout3);
        this.f3067g = new Button(this.f3069i);
        RoundRectLayout roundRectLayout = new RoundRectLayout(this.f3069i);
        roundRectLayout.setRound(20.0f);
        this.f3067g.setBackgroundColor(-10697638);
        this.f3067g.setTextColor(-1);
        this.f3067g.setTextSize(0, SizeHelper.m21679b(24));
        int stringRes5 = ResHelper.getStringRes(this.f3069i, "ssdk_sms_btn_next");
        if (stringRes5 > 0) {
            this.f3067g.setText(stringRes5);
        }
        this.f3067g.setOnTouchListener(new View.OnTouchListener() { // from class: cn.sharesdk.system.text.login.a.d.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    view.setBackgroundColor(-12206056);
                    return false;
                } else if (motionEvent.getAction() == 1) {
                    view.setBackgroundColor(-10697638);
                    return false;
                } else {
                    return false;
                }
            }
        });
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams7.topMargin = SizeHelper.m21679b(10);
        roundRectLayout.addView(this.f3067g, layoutParams7);
        linearLayout.addView(roundRectLayout, layoutParams7);
    }

    /* renamed from: i */
    private TitleLayout m21537i() {
        TitleLayout titleLayout = new TitleLayout(this.f3069i);
        try {
            int bitmapRes = ResHelper.getBitmapRes(this.f3069i, "ssdk_auth_title_back");
            if (bitmapRes > 0) {
                titleLayout.setBackgroundResource(bitmapRes);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        this.f3068h = titleLayout.getBtnBack();
        titleLayout.getBtnRight().setVisibility(8);
        int stringRes = ResHelper.getStringRes(this.f3069i, "ssdk_sms_login");
        if (stringRes > 0) {
            titleLayout.getTvTitle().setText(stringRes);
        }
        ImageView imageView = new ImageView(this.f3069i);
        int bitmapRes2 = ResHelper.getBitmapRes(this.f3069i, "ssdk_logo");
        if (bitmapRes2 > 0) {
            imageView.setImageResource(bitmapRes2);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(0, 0, ResHelper.dipToPx(this.f3069i, 10), 0);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.system.text.login.a.d.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                try {
                    int stringRes2 = ResHelper.getStringRes(InputPhoneNumPageLayout.this.f3069i, "ssdk_website");
                    String string = stringRes2 > 0 ? view.getResources().getString(stringRes2) : null;
                    if (!TextUtils.isEmpty(string)) {
                        InputPhoneNumPageLayout.this.f3069i.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string)));
                    }
                } catch (Throwable th2) {
                    SSDKLog.m21740b().m21742a(th2);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        titleLayout.addView(imageView);
        return titleLayout;
    }

    /* renamed from: b */
    public CommEditText m21544b() {
        return this.f3063c;
    }

    /* renamed from: c */
    public CommEditText m21543c() {
        return this.f3064d;
    }

    /* renamed from: d */
    public CommEditText m21542d() {
        return this.f3065e;
    }

    /* renamed from: e */
    public CommEditText m21541e() {
        return this.f3066f;
    }

    /* renamed from: f */
    public Button m21540f() {
        return this.f3067g;
    }

    /* renamed from: g */
    public ImageView m21539g() {
        return this.f3068h;
    }
}

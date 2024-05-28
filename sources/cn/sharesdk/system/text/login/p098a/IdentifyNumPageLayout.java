package cn.sharesdk.system.text.login.p098a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.InputFilter;
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
/* renamed from: cn.sharesdk.system.text.login.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IdentifyNumPageLayout {

    /* renamed from: a */
    private LinearLayout f3047a;

    /* renamed from: b */
    private CommEditText f3048b;

    /* renamed from: c */
    private CommEditText f3049c;

    /* renamed from: d */
    private CommEditText f3050d;

    /* renamed from: e */
    private CommEditText f3051e;

    /* renamed from: f */
    private CommEditText f3052f;

    /* renamed from: g */
    private TextView f3053g;

    /* renamed from: h */
    private Button f3054h;

    /* renamed from: i */
    private Button f3055i;

    /* renamed from: j */
    private ImageView f3056j;

    /* renamed from: k */
    private Context f3057k;

    public IdentifyNumPageLayout(Context context) {
        this.f3057k = context;
    }

    /* renamed from: a */
    public LinearLayout m21557a() {
        m21548i();
        return this.f3047a;
    }

    /* renamed from: i */
    private void m21548i() {
        SizeHelper.m21680a(this.f3057k);
        this.f3047a = new LinearLayout(this.f3057k);
        this.f3047a.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.f3047a.setOrientation(1);
        this.f3047a.setGravity(17);
        this.f3047a.addView(m21547j());
        LinearLayout linearLayout = new LinearLayout(this.f3057k);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setBackgroundColor(-1);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        int m21679b = SizeHelper.m21679b(30);
        linearLayout.setPadding(m21679b, 0, m21679b, m21679b);
        this.f3047a.addView(linearLayout);
        TextView textView = new TextView(this.f3057k);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        textView.setGravity(17);
        textView.setPadding(0, m21679b, 0, m21679b);
        int stringRes = ResHelper.getStringRes(this.f3057k, "ssdk_sms_top_identify_text");
        if (stringRes > 0) {
            textView.setText(stringRes);
        }
        textView.setTextColor(-16777216);
        textView.setTextSize(0, SizeHelper.m21679b(35));
        linearLayout.addView(textView, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(this.f3057k);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.bottomMargin = SizeHelper.m21679b(10);
        linearLayout2.setLayoutParams(layoutParams2);
        this.f3048b = new CommEditText(this.f3057k);
        this.f3048b.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        int m21679b2 = SizeHelper.m21679b(20);
        this.f3048b.setPadding(0, m21679b2, 0, m21679b2);
        this.f3048b.setBackgroundColor(-1);
        int stringRes2 = ResHelper.getStringRes(this.f3057k, "ssdk_sms_phone");
        if (stringRes2 > 0) {
            this.f3048b.setText(stringRes2);
        }
        this.f3048b.setTextSize(0, SizeHelper.m21679b(24));
        this.f3048b.setFocusable(false);
        this.f3049c = new CommEditText(this.f3057k);
        this.f3049c.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 3.0f));
        this.f3049c.setPadding(0, m21679b2, 0, m21679b2);
        this.f3049c.setBackgroundColor(-1);
        this.f3049c.setTextSize(0, SizeHelper.m21679b(24));
        this.f3049c.setFocusable(false);
        linearLayout2.addView(this.f3048b);
        linearLayout2.addView(this.f3049c);
        linearLayout.addView(linearLayout2);
        LinearLayout linearLayout3 = new LinearLayout(this.f3057k);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        linearLayout3.setLayoutParams(layoutParams3);
        layoutParams3.bottomMargin = SizeHelper.m21679b(30);
        this.f3050d = new CommEditText(this.f3057k);
        this.f3050d.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.f3050d.setPadding(0, m21679b2, 0, m21679b2);
        this.f3050d.setBackgroundColor(-1);
        this.f3050d.setFocusable(false);
        int stringRes3 = ResHelper.getStringRes(this.f3057k, "ssdk_sms_code");
        if (stringRes3 > 0) {
            this.f3050d.setText(stringRes3);
        }
        this.f3050d.setTextSize(0, SizeHelper.m21679b(24));
        this.f3051e = new CommEditText(this.f3057k);
        this.f3051e.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.5f));
        this.f3051e.setPadding(0, m21679b2, 0, m21679b2);
        this.f3051e.setBackgroundColor(-1);
        int stringRes4 = ResHelper.getStringRes(this.f3057k, "ssdk_sms_input_code_hint");
        if (stringRes4 > 0) {
            this.f3051e.setHint(stringRes4);
        }
        this.f3051e.setTextSize(0, SizeHelper.m21679b(24));
        this.f3051e.setInputType(2);
        this.f3051e.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        this.f3052f = new CommEditText(this.f3057k);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -2, 1.5f);
        this.f3052f.setGravity(21);
        this.f3052f.setLayoutParams(layoutParams4);
        this.f3052f.setPadding(0, m21679b2, 0, m21679b2);
        this.f3052f.setBackgroundColor(-1);
        int stringRes5 = ResHelper.getStringRes(this.f3057k, "ssdk_sms_send_again");
        if (stringRes5 > 0) {
            this.f3052f.setText(stringRes5);
        }
        this.f3052f.setTextSize(0, SizeHelper.m21679b(24));
        this.f3052f.setFocusable(false);
        linearLayout3.addView(this.f3050d);
        linearLayout3.addView(this.f3051e);
        linearLayout3.addView(this.f3052f);
        linearLayout.addView(linearLayout3);
        this.f3054h = new Button(this.f3057k);
        RoundRectLayout roundRectLayout = new RoundRectLayout(this.f3057k);
        roundRectLayout.setRound(20.0f);
        SizeHelper.m21679b(20);
        this.f3054h.setBackgroundColor(-10697638);
        this.f3054h.setTextColor(-1);
        int stringRes6 = ResHelper.getStringRes(this.f3057k, "ssdk_sms_btn_submit");
        if (stringRes6 > 0) {
            this.f3054h.setText(stringRes6);
        }
        this.f3054h.setTextSize(0, SizeHelper.m21679b(24));
        this.f3054h.setOnTouchListener(new View.OnTouchListener() { // from class: cn.sharesdk.system.text.login.a.c.1
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
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams5.topMargin = SizeHelper.m21679b(10);
        roundRectLayout.addView(this.f3054h, layoutParams5);
        linearLayout.addView(roundRectLayout, layoutParams5);
        this.f3053g = new TextView(this.f3057k);
        ViewGroup.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
        this.f3053g.setGravity(17);
        int m21679b3 = SizeHelper.m21679b(20);
        this.f3053g.setPadding(0, m21679b3, 0, m21679b3);
        int stringRes7 = ResHelper.getStringRes(this.f3057k, "ssdk_sms_input_voice_code");
        if (stringRes7 > 0) {
            this.f3053g.setText(Html.fromHtml(this.f3057k.getString(stringRes7)));
        }
        this.f3053g.setTextColor(-16777216);
        this.f3053g.setTextSize(0, SizeHelper.m21679b(24));
        this.f3053g.setVisibility(8);
        linearLayout.addView(this.f3053g, layoutParams6);
        this.f3055i = new Button(this.f3057k);
        RoundRectLayout roundRectLayout2 = new RoundRectLayout(this.f3057k);
        roundRectLayout2.setRound(20.0f);
        SizeHelper.m21679b(20);
        this.f3055i.setBackgroundColor(-10697638);
        this.f3055i.setTextColor(-1);
        int stringRes8 = ResHelper.getStringRes(this.f3057k, "ssdk_sms_btn_sende_voice");
        if (stringRes8 > 0) {
            this.f3055i.setText(stringRes8);
        }
        this.f3055i.setTextSize(0, SizeHelper.m21679b(24));
        this.f3055i.setVisibility(4);
        this.f3055i.setOnTouchListener(new View.OnTouchListener() { // from class: cn.sharesdk.system.text.login.a.c.2
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
        roundRectLayout2.addView(this.f3055i, layoutParams7);
        linearLayout.addView(roundRectLayout2, layoutParams7);
    }

    /* renamed from: j */
    private TitleLayout m21547j() {
        TitleLayout titleLayout = new TitleLayout(this.f3057k);
        try {
            int bitmapRes = ResHelper.getBitmapRes(this.f3057k, "ssdk_auth_title_back");
            if (bitmapRes > 0) {
                titleLayout.setBackgroundResource(bitmapRes);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        this.f3056j = titleLayout.getBtnBack();
        titleLayout.getBtnRight().setVisibility(8);
        int stringRes = ResHelper.getStringRes(this.f3057k, "ssdk_sms_login");
        if (stringRes > 0) {
            titleLayout.getTvTitle().setText(stringRes);
        }
        ImageView imageView = new ImageView(this.f3057k);
        int bitmapRes2 = ResHelper.getBitmapRes(this.f3057k, "ssdk_logo");
        if (bitmapRes2 > 0) {
            imageView.setImageResource(bitmapRes2);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(0, 0, ResHelper.dipToPx(this.f3057k, 10), 0);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.system.text.login.a.c.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                try {
                    int stringRes2 = ResHelper.getStringRes(IdentifyNumPageLayout.this.f3057k, "ssdk_website");
                    String string = stringRes2 > 0 ? view.getResources().getString(stringRes2) : null;
                    if (!TextUtils.isEmpty(string)) {
                        IdentifyNumPageLayout.this.f3057k.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string)));
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
    public CommEditText m21555b() {
        return this.f3049c;
    }

    /* renamed from: c */
    public CommEditText m21554c() {
        return this.f3051e;
    }

    /* renamed from: d */
    public CommEditText m21553d() {
        return this.f3052f;
    }

    /* renamed from: e */
    public TextView m21552e() {
        return this.f3053g;
    }

    /* renamed from: f */
    public Button m21551f() {
        return this.f3054h;
    }

    /* renamed from: g */
    public Button m21550g() {
        return this.f3055i;
    }

    /* renamed from: h */
    public ImageView m21549h() {
        return this.f3056j;
    }
}

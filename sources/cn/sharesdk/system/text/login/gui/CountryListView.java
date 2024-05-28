package cn.sharesdk.system.text.login.gui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.sharesdk.framework.utils.SizeHelper;
import cn.sharesdk.framework.utils.ViewRound;
import cn.sharesdk.system.text.login.gui.GroupListView;
import com.mob.tools.gui.RoundRectLayout;
import com.mob.tools.utils.ResHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CountryListView extends RoundRectLayout implements View.OnTouchListener {

    /* renamed from: a */
    private GroupListView f3096a;

    /* renamed from: b */
    private TextView f3097b;

    /* renamed from: c */
    private LinearLayout f3098c;

    /* renamed from: d */
    private CountryAdapter f3099d;

    public CountryListView(Context context) {
        super(context);
        m21503a(context);
    }

    public CountryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m21503a(context);
    }

    public CountryListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m21503a(context);
    }

    /* renamed from: a */
    private void m21503a(Context context) {
        SizeHelper.m21680a(context);
        this.f3096a = new GroupListView(context);
        this.f3096a.m21497a(SizeHelper.m21679b(1));
        int bitmapRes = ResHelper.getBitmapRes(context, "ssdk_country_cl_divider");
        if (bitmapRes > 0) {
            this.f3096a.m21494a(context.getResources().getDrawable(bitmapRes));
        }
        this.f3099d = new CountryAdapter(this.f3096a);
        this.f3096a.m21492a(this.f3099d);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        int m21679b = SizeHelper.m21679b(12);
        layoutParams.setMargins(m21679b, 0, m21679b, 0);
        addView(this.f3096a, layoutParams);
        this.f3097b = new TextView(context);
        this.f3097b.setTextColor(-1);
        this.f3097b.setBackgroundDrawable(ViewRound.m21667a(30.0f, -1358954496));
        this.f3097b.setTextSize(0, SizeHelper.m21679b(80));
        this.f3097b.setTypeface(Typeface.DEFAULT);
        this.f3097b.setVisibility(8);
        this.f3097b.setGravity(17);
        int m21679b2 = SizeHelper.m21679b(120);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(m21679b2, m21679b2);
        layoutParams2.addRule(13);
        addView(this.f3097b, layoutParams2);
        this.f3098c = new LinearLayout(context);
        this.f3098c.setBackgroundDrawable(ViewRound.m21667a(30.0f, 0));
        this.f3098c.setOrientation(1);
        this.f3098c.setOnTouchListener(this);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(11);
        layoutParams3.addRule(15);
        layoutParams3.rightMargin = SizeHelper.m21679b(7);
        addView(this.f3098c, layoutParams3);
        m21499b(context);
    }

    /* renamed from: b */
    private void m21499b(Context context) {
        this.f3098c.removeAllViews();
        SizeHelper.m21680a(context);
        int mo21475a = this.f3099d.mo21475a();
        int m21679b = SizeHelper.m21679b(6);
        int m21679b2 = SizeHelper.m21679b(4);
        for (int i = 0; i < mo21475a; i++) {
            TextView textView = new TextView(context);
            textView.setText(this.f3099d.mo21467b(i));
            textView.setTextSize(0, SizeHelper.m21679b(18));
            textView.setGravity(17);
            textView.setPadding(m21679b, m21679b2, m21679b, m21679b2);
            this.f3098c.addView(textView);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                view.setBackgroundDrawable(ViewRound.m21667a(30.0f, -1358954496));
                m21502a((ViewGroup) view, motionEvent.getX(), motionEvent.getY());
                return true;
            case 1:
            case 3:
                view.setBackgroundDrawable(ViewRound.m21667a(30.0f, 0));
                this.f3097b.setVisibility(8);
                return true;
            case 2:
                m21502a((ViewGroup) view, motionEvent.getX(), motionEvent.getY());
                return true;
            default:
                return true;
        }
    }

    /* renamed from: a */
    public void m21502a(ViewGroup viewGroup, float f, float f2) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView textView = (TextView) viewGroup.getChildAt(i);
            if (f >= textView.getLeft() && f <= textView.getRight() && f2 >= textView.getTop() && f2 <= textView.getBottom()) {
                this.f3096a.m21488b(i);
                this.f3097b.setVisibility(0);
                this.f3097b.setText(textView.getText());
                return;
            }
        }
    }

    /* renamed from: a */
    public void m21500a(String str) {
        this.f3099d.m21469a(str);
        this.f3099d.m21481c();
        if (this.f3099d.mo21475a() == 0) {
            this.f3098c.setVisibility(8);
        } else {
            this.f3098c.setVisibility(0);
        }
        m21499b(getContext());
    }

    /* renamed from: a */
    public void m21501a(GroupListView.OnItemClickListener onItemClickListener) {
        this.f3096a.m21493a(onItemClickListener);
    }

    /* renamed from: a */
    public String[] m21504a(int i, int i2) {
        return this.f3099d.mo21466b(i, i2);
    }
}

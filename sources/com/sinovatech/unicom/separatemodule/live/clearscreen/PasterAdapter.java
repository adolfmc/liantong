package com.sinovatech.unicom.separatemodule.live.clearscreen;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p083v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PasterAdapter extends PagerAdapter {
    private Activity context;
    private List<HashMap<String, String>> list = new ArrayList();

    @Override // android.support.p083v4.view.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    public PasterAdapter(Activity activity) {
        this.context = activity;
    }

    public List<HashMap<String, String>> getList() {
        return this.list;
    }

    public void setList(List<HashMap<String, String>> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Activity activity) {
        this.context = activity;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.list.size();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, final int i) {
        ImageView imageView = new ImageView(this.context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GlideApp.with(App.getInstance()).load(this.list.get(i).get("img_url")).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.live.clearscreen.PasterAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                String str = ((HashMap) PasterAdapter.this.list.get(i)).containsKey("ad_url") ? (String) ((HashMap) PasterAdapter.this.list.get(i)).get("ad_url") : null;
                if (!TextUtils.isEmpty(str)) {
                    IntentManager.gotoWebViewActivity(PasterAdapter.this.context, str, "");
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        viewGroup.addView(imageView);
        return imageView;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }
}

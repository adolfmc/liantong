package com.sinovatech.unicom.separatemodule.user.adapter;

import android.app.Activity;
import android.support.p083v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.adapter.MianGridPagerAdapter;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.view.MeasureGridView;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.hub.utils.MsLogUtil;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.user.entity.UserFootPrintEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserFootGridAdapter extends BaseAdapter {
    public static int currentPosition;
    private Activity activityContext;
    private List<UserFootPrintEntity> entityList;
    int page;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface PageSelectCallBack {
        void page(int i);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public UserFootGridAdapter(Activity activity, List<UserFootPrintEntity> list, int i) {
        this.page = 0;
        this.entityList = list;
        this.activityContext = activity;
        this.page = i;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<UserFootPrintEntity> list = this.entityList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHodler viewHodler;
        if (view == null) {
            viewHodler = new ViewHodler();
            view2 = this.activityContext.getLayoutInflater().inflate(2131493512, viewGroup, false);
            try {
                view2.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
            } catch (Exception e) {
                e.printStackTrace();
            }
            viewHodler.imageView = (ImageView) view2.findViewById(2131299368);
            viewHodler.textView = (TextView) view2.findViewById(2131299369);
            viewHodler.textViewJiaoBiao = (TextView) view2.findViewById(2131297534);
            try {
                viewHodler.textView.setBackgroundResource(0);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            view2.setTag(viewHodler);
        } else {
            ViewHodler viewHodler2 = (ViewHodler) view.getTag();
            viewHodler2.textView.setText((CharSequence) null);
            viewHodler2.imageView.setImageBitmap(null);
            view2 = view;
            viewHodler = viewHodler2;
        }
        try {
            if (i < this.entityList.size()) {
                final UserFootPrintEntity userFootPrintEntity = this.entityList.get(i);
                viewHodler.textView.setText(LanguageUtil.getInstance().getText(userFootPrintEntity.getShoppingCartTitle(), viewHodler.textView, true));
                copeCorner(userFootPrintEntity.getShoppingCartNum(), viewHodler.textViewJiaoBiao);
                GlideApp.with(App.getInstance()).load(userFootPrintEntity.getShoppingCartImgSrc()).placeholder(2131231795).into(viewHodler.imageView);
                view2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.adapter.UserFootGridAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        NBSActionInstrumentation.onClickEventEnter(view3, this);
                        Tracker.onClick(view3);
                        IntentManager.generateIntentAndGo(UserFootGridAdapter.this.activityContext, userFootPrintEntity.getShoppingCartUrls());
                        int i2 = (UserFootGridAdapter.this.page * 4) + i + 1;
                        String str = "506010" + i2;
                        MsLogUtil.m8000d(str);
                        UserFootGridAdapter.this.clickCollect(str, userFootPrintEntity.getShoppingCartTitle(), userFootPrintEntity.getShoppingCartUrls(), i2);
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            MsLogUtil.m8000d(e3.getMessage());
        }
        return view2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickCollect(String str, String str2, String str3, int i) {
        try {
            UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setPageName("我的").setPbName(str2).setCodeId(str).setTargetUrl(str3).setStoreyCode(str.substring(0, 3)).build());
            PvCurrencyLogUtils.sendServicePvLog(str, "我的足迹" + (i + 1), "My page", str2, str3, "2", "1", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copeCorner(String str, TextView textView) {
        if (TextUtils.isEmpty(str) || "0".equals(str)) {
            textView.setVisibility(4);
            return;
        }
        textView.setVisibility(0);
        if (str.length() >= 3) {
            str = "99+";
        }
        if (str.length() <= 2) {
            textView.setText(str);
            textView.setBackground(this.activityContext.getResources().getDrawable(2131232164));
            return;
        }
        textView.setText(str);
        textView.setBackground(this.activityContext.getResources().getDrawable(2131232165));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class ViewHodler {
        private ImageView imageView;
        private TextView textView;
        private TextView textViewJiaoBiao;

        ViewHodler() {
        }
    }

    public void initGridView(List<UserFootPrintEntity> list, ViewPager viewPager, final LinearLayout linearLayout, int i, final PageSelectCallBack pageSelectCallBack) {
        try {
            if (list.size() > 4) {
                linearLayout.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
            }
            if (list.size() > 0) {
                viewPager.setVisibility(0);
            } else {
                viewPager.setVisibility(8);
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i2 = 0;
            for (UserFootPrintEntity userFootPrintEntity : list) {
                arrayList2.add(userFootPrintEntity);
                if (arrayList2.size() == i) {
                    MeasureGridView createGridView = createGridView(arrayList2, -1, 4, i2);
                    i2++;
                    arrayList.add(createGridView);
                    arrayList2 = new ArrayList();
                }
            }
            if (arrayList2.size() > 0) {
                arrayList.add(createGridView(arrayList2, -1, 4, i2));
            }
            viewPager.setAdapter(new MianGridPagerAdapter(arrayList));
            if (arrayList.size() > currentPosition) {
                viewPager.setCurrentItem(currentPosition);
            }
            linearLayout.removeAllViews();
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                ImageView imageView = new ImageView(this.activityContext);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dip2px(4.0f), UIUtils.dip2px(3.0f));
                layoutParams.setMargins(5, 0, 5, 3);
                if (i3 == currentPosition) {
                    imageView.setImageResource(2131232481);
                } else {
                    imageView.setImageResource(2131232480);
                }
                imageView.setLayoutParams(layoutParams);
                linearLayout.addView(imageView);
            }
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.separatemodule.user.adapter.UserFootGridAdapter.2
                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i4) {
                }

                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i4, float f, int i5) {
                }

                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageSelected(int i4) {
                    NBSActionInstrumentation.onPageSelectedEnter(i4, this);
                    for (int i5 = 0; i5 < linearLayout.getChildCount(); i5++) {
                        ImageView imageView2 = (ImageView) linearLayout.getChildAt(i5);
                        if (i5 == i4) {
                            imageView2.setImageResource(2131232481);
                        } else {
                            imageView2.setImageResource(2131232480);
                        }
                    }
                    PageSelectCallBack pageSelectCallBack2 = pageSelectCallBack;
                    if (pageSelectCallBack2 != null) {
                        pageSelectCallBack2.page(i4);
                    }
                    NBSActionInstrumentation.onPageSelectedExit();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MeasureGridView createGridView(List<UserFootPrintEntity> list, int i, int i2, int i3) {
        MeasureGridView measureGridView = new MeasureGridView(this.activityContext);
        measureGridView.setNumColumns(i2);
        measureGridView.setClickable(true);
        measureGridView.setFocusable(false);
        measureGridView.setSelector(2131099991);
        measureGridView.setLayoutParams(new ViewGroup.LayoutParams(UIUtils.getScreenWidth(this.activityContext), i));
        measureGridView.setAdapter((ListAdapter) new UserFootGridAdapter(this.activityContext, list, i3));
        return measureGridView;
    }
}

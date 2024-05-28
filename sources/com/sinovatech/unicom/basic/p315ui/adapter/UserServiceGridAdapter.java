package com.sinovatech.unicom.basic.p315ui.adapter;

import android.app.Activity;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.datacenter.HistoryDataCenter;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.MeasureGridView;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.UserServiceGridAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserServiceGridAdapter extends BaseAdapter {
    public static int MYSERVICE = 0;
    public static int YWUSER = 1;
    public static int currentPosition;
    private AppCompatActivity activityContext;
    private int formType;
    private List<MenuEntity> list;
    private int page;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public UserServiceGridAdapter(AppCompatActivity appCompatActivity, List<MenuEntity> list, int i, int i2) {
        this.formType = 0;
        this.activityContext = appCompatActivity;
        this.list = list;
        this.page = i;
        this.formType = i2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.list.size();
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHodler viewHodler;
        if (view == null) {
            viewHodler = new ViewHodler();
            view2 = this.activityContext.getLayoutInflater().inflate(2131493531, viewGroup, false);
            try {
                view2.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
            } catch (Exception e) {
                e.printStackTrace();
            }
            viewHodler.imageView = (ImageView) view2.findViewById(2131297141);
            viewHodler.textView = (TextView) view2.findViewById(2131297144);
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
        if (i < this.list.size()) {
            final MenuEntity menuEntity = this.list.get(i);
            viewHodler.textView.setText(LanguageUtil.getInstance().getText(menuEntity.getMenuTitle(), viewHodler.textView, true));
            GlideApp.with((FragmentActivity) this.activityContext).asBitmap().placeholder(2131231795).load(menuEntity.getMenuIconURL()).into(viewHodler.imageView);
            view2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.-$$Lambda$UserServiceGridAdapter$m_6CSz3fbEh1uDVMK4WC4OlIw0k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    UserServiceGridAdapter.lambda$getView$0(UserServiceGridAdapter.this, menuEntity, i, view3);
                }
            });
        }
        return view2;
    }

    public static /* synthetic */ void lambda$getView$0(UserServiceGridAdapter userServiceGridAdapter, MenuEntity menuEntity, int i, View view) {
        String str;
        IntentManager.generateIntentAndGo(userServiceGridAdapter.activityContext, menuEntity, "get");
        if (userServiceGridAdapter.formType == MYSERVICE) {
            int i2 = (userServiceGridAdapter.page * 4) + i + 1;
            if (i2 < 10) {
                str = "505010" + i2;
            } else {
                str = "50501" + i2;
            }
            StatisticsEntity statisticsEntity = new StatisticsEntity();
            statisticsEntity.setKey(UUID.randomUUID().toString());
            statisticsEntity.setTransId(str);
            statisticsEntity.setActCode("我的服务");
            statisticsEntity.setUpType("");
            statisticsEntity.setMenuId(menuEntity.getMenuId());
            statisticsEntity.setTitleName(menuEntity.getMenuTitle());
            statisticsEntity.setUrlApp(menuEntity.getMenuURL());
            statisticsEntity.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
            statisticsEntity.setCityId(UserManager.getInstance().getCurrentCityCode());
            statisticsEntity.setProvId(UserManager.getInstance().getCurrentProvinceCode());
            statisticsEntity.setRemark1("");
            statisticsEntity.setRemark2("");
            statisticsEntity.setRemark3("");
            statisticsEntity.setRemark4("");
            statisticsEntity.setVersion(userServiceGridAdapter.activityContext.getResources().getString(2131886969));
            statisticsEntity.setClientType("Android");
            statisticsEntity.setIconUrl(menuEntity.getMenuIconURL());
            statisticsEntity.setTime(String.valueOf(System.currentTimeMillis() / 1000));
            if (App.hasLogined()) {
                new HistoryDataCenter(userServiceGridAdapter.activityContext).insertStatisticsRecord(statisticsEntity);
            }
            PvCurrencyLogUtils.sendServicePvLog(str, "我的服务", "My page", menuEntity.getMenuTitle(), menuEntity.getMenuURL(), "2", "1", "", menuEntity.getMenuId());
            return;
        }
        PvCurrencyLogUtils.sendYwUserPvLog("5010101", "异网个人信息页-导航", "My page", menuEntity.getMenuTitle(), menuEntity.getMenuURL(), "2", "1", "");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.UserServiceGridAdapter$ViewHodler */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class ViewHodler {
        private ImageView imageView;
        private TextView textView;

        ViewHodler() {
        }
    }

    public void initGridView(List<MenuEntity> list, ViewPager viewPager, final LinearLayout linearLayout, int i, int i2) {
        try {
            if (list.size() > 5) {
                linearLayout.setVisibility(0);
            } else {
                linearLayout.setVisibility(4);
            }
            if (list.size() > 0) {
                viewPager.setVisibility(0);
            } else {
                viewPager.setVisibility(8);
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = arrayList2;
            int i3 = 0;
            for (MenuEntity menuEntity : list) {
                arrayList3.add(menuEntity);
                if (arrayList3.size() == i) {
                    i3++;
                    arrayList.add(createGridView(arrayList3, -1, 4, i3, i2));
                    arrayList3 = new ArrayList();
                }
            }
            if (arrayList3.size() > 0) {
                arrayList.add(createGridView(arrayList3, -1, 4, i3, i2));
            }
            viewPager.setAdapter(new MianGridPagerAdapter(arrayList));
            if (arrayList.size() > currentPosition) {
                viewPager.setCurrentItem(currentPosition);
            }
            linearLayout.removeAllViews();
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                ImageView imageView = new ImageView(this.activityContext);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dip2px(4.0f), UIUtils.dip2px(3.0f));
                layoutParams.setMargins(5, 0, 5, 3);
                if (i4 == currentPosition) {
                    imageView.setImageResource(2131232481);
                } else {
                    imageView.setImageResource(2131232480);
                }
                imageView.setLayoutParams(layoutParams);
                linearLayout.addView(imageView);
            }
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.UserServiceGridAdapter.1
                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i5) {
                }

                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i5, float f, int i6) {
                }

                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageSelected(int i5) {
                    NBSActionInstrumentation.onPageSelectedEnter(i5, this);
                    for (int i6 = 0; i6 < linearLayout.getChildCount(); i6++) {
                        ImageView imageView2 = (ImageView) linearLayout.getChildAt(i6);
                        if (i6 == i5) {
                            imageView2.setImageResource(2131232481);
                        } else {
                            imageView2.setImageResource(2131232480);
                        }
                    }
                    NBSActionInstrumentation.onPageSelectedExit();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MeasureGridView createGridView(List<MenuEntity> list, int i, int i2, int i3, int i4) {
        MeasureGridView measureGridView = new MeasureGridView(this.activityContext);
        measureGridView.setNumColumns(i2);
        measureGridView.setClickable(true);
        measureGridView.setFocusable(false);
        measureGridView.setSelector(2131099991);
        measureGridView.setLayoutParams(new ViewGroup.LayoutParams(UIUtils.getScreenWidth((Activity) this.activityContext), i));
        measureGridView.setAdapter((ListAdapter) new UserServiceGridAdapter(this.activityContext, list, i3, i4));
        return measureGridView;
    }
}

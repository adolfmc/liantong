package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.app.hubert.guide.listener.AnimationListenerAdapter;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.p315ui.manager.HomeSearchNewUserManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectConfig;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.SearchEntity;
import com.sinovatech.unicom.separatemodule.search.SearchHuoDongEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.view.HomeSearchView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeSearchView extends LinearLayout {
    private AppCompatActivity activityContext;
    private TextView mImgSearch;
    private View view;
    private ViewFlipper viewFlipper;

    public HomeSearchView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.activityContext = (AppCompatActivity) context;
        this.view = this;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.viewFlipper = (ViewFlipper) this.view.findViewById(2131297206);
        this.mImgSearch = (TextView) this.view.findViewById(2131297204);
    }

    public void setBackGround(boolean z) {
        if (z) {
            this.view.setBackgroundResource(2131231458);
        } else {
            this.view.setBackgroundResource(2131231457);
        }
    }

    public void setData(List<SearchEntity> list, List<SearchHuoDongEntity> list2) {
        if (HomeSearchNewUserManager.isNewUser()) {
            try {
                String newUserGiftBagData = CacheDataCenter.getInstance().getNewUserGiftBagData();
                if (newUserGiftBagData != null && !newUserGiftBagData.isEmpty()) {
                    list2.clear();
                    list.clear();
                    SearchHuoDongEntity searchHuoDongEntity = new SearchHuoDongEntity();
                    JSONObject jSONObject = new JSONObject(newUserGiftBagData);
                    String optString = jSONObject.optString("title");
                    searchHuoDongEntity.setLinkUrl(jSONObject.optString("url"));
                    searchHuoDongEntity.setSearchWordName(optString);
                    list2.add(searchHuoDongEntity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.mImgSearch.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.HomeSearchView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JSONObject jSONObject2;
                String str;
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                try {
                    JSONObject jSONObject3 = new JSONObject();
                    String str2 = "";
                    if (HomeSearchView.this.viewFlipper == null || HomeSearchView.this.viewFlipper.getCurrentView() == null) {
                        jSONObject2 = jSONObject3;
                    } else {
                        TextView textView = (TextView) HomeSearchView.this.viewFlipper.getCurrentView().findViewById(2131297137);
                        jSONObject2 = (JSONObject) textView.getTag();
                        if (textView != null && textView.getText() != null) {
                            str2 = textView.getText().toString();
                        }
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("location", CollectConfig.isShowHome ? "1" : "3");
                    if ((!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2)).contains("title")) {
                        hashMap.put("serviceHallData", jSONObject2);
                        str = jSONObject2.optString("linkUrl");
                    } else {
                        String optString2 = jSONObject2.optString("linkUrl");
                        hashMap.put("serviceHallData", jSONObject2);
                        str = optString2;
                    }
                    if (TextUtils.isEmpty(str)) {
                        IntentManager.gotoUnicomMiniProgram(HomeSearchView.this.activityContext, "ms_unicom_search2020", hashMap);
                    } else {
                        IntentManager.generateIntentAndGo(HomeSearchView.this.activityContext, str);
                    }
                    try {
                        PvCurrencyLogUtils.pvLogMainDJ("1010201", "ms_unicom_search2020", "", str2 + "", "首页搜索框");
                        UnicomHomeLogUtils.getInstance().clickLog("1010201", "搜索");
                    } catch (Exception e2) {
                        MsLogUtil.m7978e(e2.getMessage());
                    }
                    UIUtils.logD("HomeSearchView click = ", hashMap.toString());
                } catch (Exception e3) {
                    UIUtils.logD(e3.getMessage());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.view.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.HomeSearchView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JSONObject jSONObject2;
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                try {
                    JSONObject jSONObject3 = new JSONObject();
                    String str = "";
                    if (HomeSearchView.this.viewFlipper == null || HomeSearchView.this.viewFlipper.getCurrentView() == null) {
                        jSONObject2 = jSONObject3;
                    } else {
                        TextView textView = (TextView) HomeSearchView.this.viewFlipper.getCurrentView().findViewById(2131297137);
                        jSONObject2 = (JSONObject) textView.getTag();
                        if (textView != null && textView.getText() != null) {
                            str = textView.getText().toString();
                        }
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("location", CollectConfig.isShowHome ? "1" : "3");
                    if ((!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2)).contains("title")) {
                        hashMap.put("serviceHallData", jSONObject2);
                    } else {
                        hashMap.put("serviceHallData", jSONObject2);
                    }
                    IntentManager.gotoUnicomMiniProgram(HomeSearchView.this.activityContext, "ms_unicom_search2020", hashMap);
                    try {
                        PvCurrencyLogUtils.pvLogMainDJ("1010201", "ms_unicom_search2020", "", str + "", "首页搜索框");
                        UnicomHomeLogUtils.getInstance().clickLog("1010201", str);
                    } catch (Exception e2) {
                        MsLogUtil.m7978e(e2.getMessage());
                    }
                    UIUtils.logD("HomeSearchView click = ", hashMap.toString());
                } catch (Exception e3) {
                    UIUtils.logD(e3.getMessage());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (SearchHuoDongEntity searchHuoDongEntity2 : list2) {
                arrayList.add(searchHuoDongEntity2.getSearchWordName());
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("searchWordName", searchHuoDongEntity2.getSearchWordName());
                jSONObject2.put("linkUrl", searchHuoDongEntity2.getLinkUrl());
                jSONObject2.put("hallId", searchHuoDongEntity2.getHallId());
                jSONObject2.put("id", searchHuoDongEntity2.getHuodongId());
                jSONObject2.put("isNeedLogin", searchHuoDongEntity2.getIsNeedLogin());
                jSONObject2.put("time", searchHuoDongEntity2.getTime());
                jSONObject2.put("actType", searchHuoDongEntity2.getActType());
                UIUtils.logD("HomeSearchView ob = ", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                arrayList3.add(jSONObject2);
            }
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(list.get(i).getTitle());
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("searchWordName", list.get(i).getTitle());
                jSONObject3.put("linkUrl", list.get(i).getUrl());
                arrayList2.add(jSONObject3);
            }
            this.viewFlipper.removeAllViews();
            int size = list2.size();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.activityContext).inflate(2131493163, (ViewGroup) null);
                TextView textView = (TextView) linearLayout.findViewById(2131297137);
                if (i2 <= size - 1) {
                    textView.setTag(arrayList3.get(i2));
                    JSONObject jSONObject4 = (JSONObject) arrayList3.get(i2);
                    UIUtils.logD("HomeSearchView obj = ", !(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : NBSJSONObjectInstrumentation.toString(jSONObject4));
                } else {
                    int i3 = i2 - size;
                    textView.setTag(arrayList2.get(i3));
                    JSONObject jSONObject5 = (JSONObject) arrayList2.get(i3);
                    UIUtils.logD("HomeSearchView array = ", !(jSONObject5 instanceof JSONObject) ? jSONObject5.toString() : NBSJSONObjectInstrumentation.toString(jSONObject5));
                }
                if (i2 == 0) {
                    UnicomHomeLogUtils.getInstance().putLogData(UnicomHomeLogUtils.LOG_TYPE_SEARCH, new HomeLogEntity("1010201", (String) arrayList.get(i2)));
                }
                textView.setText((CharSequence) arrayList.get(i2));
                this.viewFlipper.addView(linearLayout);
            }
            this.viewFlipper.getInAnimation().setAnimationListener(new AnimationListenerAdapter() { // from class: com.sinovatech.unicom.basic.ui.view.HomeSearchView.3
                @Override // com.app.hubert.guide.listener.AnimationListenerAdapter, android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    HomeLogEntity homeLogEntity = new HomeLogEntity("1010201", "首页搜索框");
                    if (HomeSearchView.this.viewFlipper != null) {
                        View currentView = HomeSearchView.this.viewFlipper.getCurrentView();
                        if (currentView == null) {
                            UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_SEARCH, homeLogEntity);
                            return;
                        }
                        TextView textView2 = (TextView) currentView.findViewById(2131297137);
                        if (textView2 == null || textView2.getText() == null) {
                            UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_SEARCH, homeLogEntity);
                            return;
                        }
                        homeLogEntity.setTitle(textView2.getText().toString());
                        UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_SEARCH, homeLogEntity);
                        return;
                    }
                    UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_SEARCH, homeLogEntity);
                }
            });
            if (list.size() > 1 && !HomeSearchNewUserManager.isNewUser()) {
                this.viewFlipper.startFlipping();
            } else {
                this.viewFlipper.stopFlipping();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

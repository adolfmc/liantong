package com.sinovatech.unicom.separatemodule.recentmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p315ui.manager.HomeSearchNewUserManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.SearchEntity;
import com.sinovatech.unicom.separatemodule.search.SearchHuoDongEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomSearchView extends LinearLayout {
    private AppCompatActivity activityContext;
    public String page;
    private View view;
    private ViewFlipper viewFlipper;

    public void setBackGroundColor(int i) {
    }

    public CustomSearchView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.page = "我的服务页面";
        this.activityContext = (AppCompatActivity) context;
        this.view = this;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.viewFlipper = (ViewFlipper) this.view.findViewById(2131299848);
        this.view.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomSearchView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                IntentManager.gotoUnicomMiniProgram(CustomSearchView.this.activityContext, "ms_unicom_search2020", new HashMap());
                try {
                    PvCurrencyLogUtils.pvLogMainDJ("1010201", "ms_unicom_search2020", "", "", "首页-背景-搜索");
                } catch (Exception e) {
                    MsLogUtil.m7978e(e.getMessage());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
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
        this.view.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomSearchView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JSONObject jSONObject2;
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                try {
                    JSONObject jSONObject3 = new JSONObject();
                    String str = "";
                    if (CustomSearchView.this.viewFlipper == null || CustomSearchView.this.viewFlipper.getCurrentView() == null) {
                        jSONObject2 = jSONObject3;
                    } else {
                        TextView textView = (TextView) CustomSearchView.this.viewFlipper.getCurrentView().findViewById(2131297137);
                        jSONObject2 = (JSONObject) textView.getTag();
                        if (textView != null && textView.getText() != null) {
                            str = textView.getText().toString();
                        }
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("location", "1");
                    if ((!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2)).contains("title")) {
                        hashMap.put("serviceHallData", jSONObject2);
                    } else {
                        hashMap.put("serviceHallData", jSONObject2);
                    }
                    IntentManager.gotoUnicomMiniProgram(CustomSearchView.this.activityContext, "ms_unicom_search2020", hashMap);
                    try {
                        PvCurrencyLogUtils.pvLogMainDJ("1010201", "ms_unicom_search2020", "", str + "", "首页-背景-搜索");
                    } catch (Exception e2) {
                        MsLogUtil.m7978e(e2.getMessage());
                    }
                    UIUtils.logD("HomeSearchView click = ", hashMap.toString());
                } catch (Exception e3) {
                    UIUtils.logD(e3.getMessage());
                }
                PvCurrencyLogUtils.pvXiala(CustomSearchView.this.page, "搜索", "下拉进入小程序");
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
                textView.setTextColor(-10066330);
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
                textView.setText((CharSequence) arrayList.get(i2));
                this.viewFlipper.addView(linearLayout);
            }
            if (list.size() > 1 && !HomeSearchNewUserManager.isNewUser()) {
                this.viewFlipper.startFlipping();
            } else {
                this.viewFlipper.stopFlipping();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean isJsonArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            new JSONArray(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void setPage(String str) {
        this.page = str;
    }
}

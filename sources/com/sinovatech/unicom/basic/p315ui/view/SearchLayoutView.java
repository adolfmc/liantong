package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.search.SearchEntity;
import com.sinovatech.unicom.separatemodule.search.SearchHuoDongEntity;
import com.sinovatech.unicom.separatemodule.search.SearchManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.view.SearchLayoutView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SearchLayoutView extends FrameLayout {
    public static String location = "";
    private AppCompatActivity activityContext;
    private LayoutInflater inflater;
    private SearchManager searchManager;
    private LinearLayout view;
    private ViewFlipper viewFlipper;

    public SearchLayoutView(Context context) {
        super(context);
        this.activityContext = (AppCompatActivity) context;
        this.inflater = LayoutInflater.from(this.activityContext);
        init();
    }

    public SearchLayoutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.activityContext = (AppCompatActivity) context;
        this.inflater = LayoutInflater.from(this.activityContext);
        init();
    }

    private void init() {
        this.searchManager = new SearchManager(this.activityContext);
        this.view = (LinearLayout) this.inflater.inflate(2131493235, (ViewGroup) null);
        this.viewFlipper = (ViewFlipper) this.view.findViewById(2131296692);
        ((ImageView) this.view.findViewById(2131296693)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.SearchLayoutView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                IntentManager.handleLocal(SearchLayoutView.this.activityContext, SearchLayoutView.location, "LOCAL_SEARCH");
                StatisticsUploadUtils.upload("21", "服务-头部", "按钮", "0", "服务", "");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        addView(this.view);
        update();
    }

    public void update() {
        Observable.zip(this.searchManager.loadScrollKeywordFromBox(), this.searchManager.loadScrollHuoDongFromBox(), new BiFunction<List<SearchEntity>, List<SearchHuoDongEntity>, List<SearchEntity>>() { // from class: com.sinovatech.unicom.basic.ui.view.SearchLayoutView.3
            @Override // io.reactivex.functions.BiFunction
            public List<SearchEntity> apply(List<SearchEntity> list, List<SearchHuoDongEntity> list2) throws Exception {
                Log.d("HomeFragment caches", "apply: =" + list + list2);
                try {
                    SearchLayoutView.this.setData(list, list2);
                    return null;
                } catch (Exception e) {
                    UIUtils.logE(e.getMessage());
                    return null;
                }
            }
        }).subscribe(new Observer<List<SearchEntity>>() { // from class: com.sinovatech.unicom.basic.ui.view.SearchLayoutView.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(List<SearchEntity> list) {
                Log.e("HomeFragment", "onNext: =" + list);
            }
        });
    }

    public void setData(List<SearchEntity> list, List<SearchHuoDongEntity> list2) {
        this.view.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.SearchLayoutView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                try {
                    TextView textView = (TextView) SearchLayoutView.this.viewFlipper.getCurrentView().findViewById(2131297137);
                    if (SearchLayoutView.isJsonArray(textView.getTag().toString())) {
                        jSONArray = (JSONArray) textView.getTag();
                    } else {
                        jSONObject = (JSONObject) textView.getTag();
                    }
                    StatisticsUploadUtils.upload("21", "服务-头部", "按钮", "0", "服务", "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    HashMap hashMap = new HashMap();
                    if (jSONArray.length() > 0) {
                        hashMap.put("serviceHallData", jSONArray);
                    } else {
                        hashMap.put("serviceHallData", jSONObject);
                    }
                    hashMap.put("location", SearchLayoutView.location);
                    UIUtils.logD("SearchLayoutView click = ", hashMap.toString());
                    IntentManager.gotoUnicomMiniProgram(SearchLayoutView.this.activityContext, "ms_unicom_search2020", hashMap);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (SearchHuoDongEntity searchHuoDongEntity : list2) {
                arrayList.add(searchHuoDongEntity.getSearchWordName());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("searchWordName", searchHuoDongEntity.getSearchWordName());
                jSONObject.put("linkUrl", searchHuoDongEntity.getLinkUrl());
                jSONObject.put("hallId", searchHuoDongEntity.getHallId());
                jSONObject.put("id", searchHuoDongEntity.getHuodongId());
                jSONObject.put("isNeedLogin", searchHuoDongEntity.getIsNeedLogin());
                jSONObject.put("time", searchHuoDongEntity.getTime());
                jSONObject.put("actType", searchHuoDongEntity.getActType());
                UIUtils.logD("HomeSearchView ob = ", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                arrayList3.add(jSONObject);
            }
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(list.get(i).getTitle());
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("searchWordName", list.get(i).getTitle());
                jSONObject2.put("linkUrl", list.get(i).getUrl());
                arrayList2.add(jSONObject2);
            }
            this.viewFlipper.removeAllViews();
            int size = list2.size();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.activityContext).inflate(2131493163, (ViewGroup) null);
                TextView textView = (TextView) linearLayout.findViewById(2131297137);
                if (i2 <= size - 1) {
                    textView.setTag(arrayList3.get(i2));
                    JSONObject jSONObject3 = (JSONObject) arrayList3.get(i2);
                    UIUtils.logD("HomeSearchView obj = ", !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3));
                } else {
                    int i3 = i2 - size;
                    textView.setTag(arrayList2.get(i3));
                    JSONObject jSONObject4 = (JSONObject) arrayList2.get(i3);
                    UIUtils.logD("HomeSearchView array = ", !(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : NBSJSONObjectInstrumentation.toString(jSONObject4));
                }
                textView.setText((CharSequence) arrayList.get(i2));
                this.viewFlipper.addView(linearLayout);
            }
            if (list.size() > 1) {
                this.viewFlipper.startFlipping();
            } else {
                this.viewFlipper.stopFlipping();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}

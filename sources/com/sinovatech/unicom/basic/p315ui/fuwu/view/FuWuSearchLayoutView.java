package com.sinovatech.unicom.basic.p315ui.fuwu.view;

import android.content.Context;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.search.SearchEntity;
import com.sinovatech.unicom.separatemodule.search.SearchHuoDongEntity;
import com.sinovatech.unicom.separatemodule.search.SearchManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuSearchLayoutView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FuWuSearchLayoutView extends FrameLayout {
    public static String location = "";
    private String TAG;
    private AppCompatActivity activityContext;
    private LayoutInflater inflater;
    private SearchManager searchManager;
    private TextView service_search_text;
    private LinearLayout view;
    private ViewFlipper viewFlipper;

    public FuWuSearchLayoutView(Context context) {
        super(context);
        this.TAG = "新的服务页-->";
        this.activityContext = (AppCompatActivity) context;
        this.inflater = LayoutInflater.from(this.activityContext);
        init();
    }

    public FuWuSearchLayoutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "新的服务页-->";
        this.activityContext = (AppCompatActivity) context;
        this.inflater = LayoutInflater.from(this.activityContext);
        init();
    }

    private void init() {
        this.searchManager = new SearchManager(this.activityContext);
        this.view = (LinearLayout) this.inflater.inflate(2131493207, (ViewGroup) null);
        this.viewFlipper = (ViewFlipper) this.view.findViewById(2131296692);
        this.service_search_text = (TextView) this.view.findViewById(2131298538);
        this.service_search_text.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuSearchLayoutView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                FuWuSearchLayoutView.this.clickUrl(true);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        addView(this.view);
        update();
    }

    public void update() {
        Observable.zip(this.searchManager.loadScrollKeywordFromBox(), this.searchManager.loadScrollHuoDongFromBox(), new BiFunction<List<SearchEntity>, List<SearchHuoDongEntity>, List<SearchEntity>>() { // from class: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuSearchLayoutView.3
            @Override // io.reactivex.functions.BiFunction
            public List<SearchEntity> apply(List<SearchEntity> list, List<SearchHuoDongEntity> list2) throws Exception {
                Log.d(FuWuSearchLayoutView.this.TAG + "HomeFragment caches", "apply: =" + list + list2);
                try {
                    FuWuSearchLayoutView.this.setData(list, list2);
                    return null;
                } catch (Exception e) {
                    UIUtils.logE(e.getMessage());
                    return null;
                }
            }
        }).subscribe(new Observer<List<SearchEntity>>() { // from class: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuSearchLayoutView.2
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
                Log.e(FuWuSearchLayoutView.this.TAG + "HomeFragment", "onNext: =" + list);
            }
        });
    }

    public void setData(List<SearchEntity> list, List<SearchHuoDongEntity> list2) {
        this.view.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuSearchLayoutView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                FuWuSearchLayoutView.this.clickUrl(false);
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
                UIUtils.logD(this.TAG + "HomeSearchView ob = ", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
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
                textView.setTextColor(getResources().getColor(2131099830));
                if (i2 <= size - 1) {
                    textView.setTag(arrayList3.get(i2));
                    String str = this.TAG + "HomeSearchView obj = ";
                    JSONObject jSONObject3 = (JSONObject) arrayList3.get(i2);
                    UIUtils.logD(str, !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3));
                } else {
                    int i3 = i2 - size;
                    textView.setTag(arrayList2.get(i3));
                    String str2 = this.TAG + "HomeSearchView array = ";
                    JSONObject jSONObject4 = (JSONObject) arrayList2.get(i3);
                    UIUtils.logD(str2, !(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : NBSJSONObjectInstrumentation.toString(jSONObject4));
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c6 A[Catch: Exception -> 0x00fa, TryCatch #0 {Exception -> 0x00fa, blocks: (B:27:0x00bb, B:29:0x00c6, B:31:0x00d1, B:30:0x00cc), top: B:36:0x00bb }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cc A[Catch: Exception -> 0x00fa, TryCatch #0 {Exception -> 0x00fa, blocks: (B:27:0x00bb, B:29:0x00c6, B:31:0x00d1, B:30:0x00cc), top: B:36:0x00bb }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void clickUrl(boolean r10) {
        /*
            Method dump skipped, instructions count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.fuwu.view.FuWuSearchLayoutView.clickUrl(boolean):void");
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

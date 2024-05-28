package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.datacenter.UserUnicomInfoDataCenter;
import com.sinovatech.unicom.basic.p314po.CitySelectEntity;
import com.sinovatech.unicom.basic.p315ui.CityChangeManager;
import com.sinovatech.unicom.basic.p315ui.adapter.SortGroupMemberAdapter;
import com.sinovatech.unicom.basic.server.CityListManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.FlowLayout;
import com.sinovatech.unicom.basic.view.SideBarView;
import com.sinovatech.unicom.common.KeybordS;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CitySelectActivity extends Activity {
    public NBSTraceUnit _nbs_trace;
    private CitySelectActivity activityContext;
    private ImageButton backImageBtn;
    private SortGroupMemberAdapter cityAdapter;
    private CityListManager cityListManager;
    private LinearLayout citySelectLayout;
    private UserUnicomInfoDataCenter dataCenter;
    private EditText editText;
    private List<CitySelectEntity> filterDateList;
    private String from;
    private LinearLayout headerLayout;
    private LayoutInflater inflater;
    private List<CitySelectEntity> list;
    private ListView listView;
    private TextView phoneNumberTv;
    private SharePreferenceUtil preferences;
    private LinearLayout quanbudishiLyout;
    private FlowLayout recentLayout;
    private List<CitySelectEntity> recentList;
    private SideBarView sideBar;
    private LinearLayout suoshudiLayout;
    private TextView tipTilteTv;
    private TextView title;
    private LinearLayout titleLayout;
    private UserManager userManager;
    private LinearLayout zuijinshiyongLayout;
    private final String RECENT = "CitySelectActivityRecent";
    private int lastFirstVisibleItem = -1;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(2131492906);
        UIUtils.setStatusBlack(this);
        try {
            this.activityContext = this;
            this.from = getIntent().getStringExtra("from");
            ((LinearLayout) findViewById(2131297574)).setPadding(0, UIUtils.getStatusBarHeight(this), 0, 0);
            this.cityListManager = new CityListManager(this);
            this.dataCenter = new UserUnicomInfoDataCenter(this);
            String userUnicomInfoData = this.dataCenter.getUserUnicomInfoData("0", UserUnicomInfoDataCenter.TYPE_BAIDUMAP_CITY);
            if (TextUtils.isEmpty(userUnicomInfoData)) {
                this.list = this.cityListManager.getList(this.cityListManager.getProvinceData(this));
            } else {
                this.list = this.cityListManager.getList(userUnicomInfoData);
            }
            this.filterDateList = new ArrayList();
            this.cityAdapter = new SortGroupMemberAdapter(this, this.list);
            this.inflater = LayoutInflater.from(this);
            this.userManager = UserManager.getInstance();
            this.preferences = App.getSharePreferenceUtil();
            this.recentList = this.cityListManager.getList(this.preferences.getString("CitySelectActivityRecent"), true);
            this.backImageBtn = (ImageButton) findViewById(2131299563);
            this.headerLayout = (LinearLayout) this.inflater.inflate(2131493039, (ViewGroup) null);
            this.titleLayout = (LinearLayout) findViewById(2131298794);
            this.title = (TextView) findViewById(2131298795);
            this.recentLayout = (FlowLayout) this.headerLayout.findViewById(2131296660);
            this.phoneNumberTv = (TextView) this.headerLayout.findViewById(2131296658);
            this.editText = (EditText) this.headerLayout.findViewById(2131296656);
            this.tipTilteTv = (TextView) findViewById(2131296665);
            this.tipTilteTv.setVisibility(8);
            this.citySelectLayout = (LinearLayout) this.headerLayout.findViewById(2131296661);
            this.suoshudiLayout = (LinearLayout) this.headerLayout.findViewById(2131296663);
            this.zuijinshiyongLayout = (LinearLayout) this.headerLayout.findViewById(2131296659);
            this.quanbudishiLyout = (LinearLayout) this.headerLayout.findViewById(2131296653);
            this.sideBar = (SideBarView) findViewById(2131296662);
            this.listView = (ListView) findViewById(2131296655);
            this.listView.addHeaderView(this.headerLayout);
            this.listView.setAdapter((ListAdapter) this.cityAdapter);
            initRecentLayout(this.recentList);
            if (App.hasLogined() && !TextUtils.isEmpty(this.userManager.getUserAreaname())) {
                this.phoneNumberTv.setText(this.userManager.getUserAreaname());
                this.suoshudiLayout.setVisibility(0);
            } else {
                this.phoneNumberTv.setText("");
                this.suoshudiLayout.setVisibility(8);
            }
            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    NBSActionInstrumentation.onItemClickEnter(view, i, this);
                    Tracker.onItemClick(adapterView, view, i, j);
                    if (i > 0) {
                        if (TextUtils.isEmpty(CitySelectActivity.this.editText.getText().toString())) {
                            CitySelectActivity.this.handlerRecentList((CitySelectEntity) CitySelectActivity.this.list.get(i - 1));
                        } else {
                            CitySelectActivity.this.handlerRecentList((CitySelectEntity) CitySelectActivity.this.filterDateList.get(i - 1));
                        }
                    }
                    NBSActionInstrumentation.onItemClickExit();
                }
            });
            this.listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.2
                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView absListView, int i) {
                    if (KeybordS.isSoftInputShow(CitySelectActivity.this.activityContext)) {
                        KeybordS.closeKeybord(CitySelectActivity.this.editText, CitySelectActivity.this.activityContext);
                    }
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                    View childAt;
                    int sectionForPosition = CitySelectActivity.this.getSectionForPosition(i);
                    int i4 = i + 1;
                    int positionForSection = CitySelectActivity.this.getPositionForSection(CitySelectActivity.this.getSectionForPosition(i4));
                    if (i != CitySelectActivity.this.lastFirstVisibleItem) {
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) CitySelectActivity.this.titleLayout.getLayoutParams();
                        marginLayoutParams.topMargin = 0;
                        CitySelectActivity.this.titleLayout.setLayoutParams(marginLayoutParams);
                        CitySelectActivity.this.title.setText(((CitySelectEntity) CitySelectActivity.this.list.get(CitySelectActivity.this.getPositionForSection(sectionForPosition))).getSortLetters());
                    }
                    if (positionForSection == i4 && (childAt = absListView.getChildAt(0)) != null) {
                        int height = CitySelectActivity.this.titleLayout.getHeight();
                        int bottom = childAt.getBottom();
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) CitySelectActivity.this.titleLayout.getLayoutParams();
                        if (bottom < height) {
                            marginLayoutParams2.topMargin = bottom - height;
                            CitySelectActivity.this.titleLayout.setLayoutParams(marginLayoutParams2);
                        } else if (marginLayoutParams2.topMargin != 0) {
                            marginLayoutParams2.topMargin = 0;
                            CitySelectActivity.this.titleLayout.setLayoutParams(marginLayoutParams2);
                        }
                    }
                    CitySelectActivity.this.lastFirstVisibleItem = i;
                }
            });
            this.sideBar.setOnTouchingLetterChangedListener(new SideBarView.OnTouchingLetterChangedListener() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.3
                @Override // com.sinovatech.unicom.basic.view.SideBarView.OnTouchingLetterChangedListener
                public void onTouchingLetterChanged(String str) {
                    int positionForSection = CitySelectActivity.this.cityAdapter.getPositionForSection(str.charAt(0));
                    if (positionForSection != -1) {
                        CitySelectActivity.this.listView.setSelection(positionForSection + 1);
                    }
                    if ("#".equals(str)) {
                        CitySelectActivity.this.listView.setSelection(0);
                    }
                }
            });
            this.editText.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.4
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    CitySelectActivity.this.titleLayout.setVisibility(8);
                    CitySelectActivity.this.filterData(charSequence.toString());
                }
            });
            this.backImageBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (KeybordS.isSoftInputShow(CitySelectActivity.this.activityContext)) {
                        KeybordS.closeKeybord(CitySelectActivity.this.editText, CitySelectActivity.this.activityContext);
                    }
                    if ("chooseCity".equals(CitySelectActivity.this.from)) {
                        CitySelectActivity.this.setResult(-1, new Intent());
                        MsLogUtil.m7979d("chooseCityJsPlugin", "onKeyDown: back");
                    }
                    CitySelectActivity.this.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.phoneNumberTv.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    double d;
                    double d2;
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    Intent intent = new Intent();
                    String str = "";
                    String str2 = "";
                    String str3 = "";
                    Iterator it = CitySelectActivity.this.list.iterator();
                    while (true) {
                        d = 0.0d;
                        if (!it.hasNext()) {
                            d2 = 0.0d;
                            break;
                        }
                        CitySelectEntity citySelectEntity = (CitySelectEntity) it.next();
                        if (citySelectEntity.getCityName().equals(CitySelectActivity.this.userManager.getUserAreaname())) {
                            str = citySelectEntity.getCityCode();
                            d = citySelectEntity.getLatitude();
                            double longitude = citySelectEntity.getLongitude();
                            str2 = citySelectEntity.getPrivienceCode();
                            str3 = citySelectEntity.getCityName();
                            d2 = longitude;
                            break;
                        }
                    }
                    intent.putExtra("cityName", str3);
                    intent.putExtra("mapCode", str);
                    intent.putExtra("latitude", d);
                    intent.putExtra("longitude", d2);
                    intent.putExtra("privienceCode", str2);
                    UserManager.getInstance().saveUserLocateCityTime(0L);
                    CitySelectActivity.this.setResult(-1, intent);
                    if ("home".equals(CitySelectActivity.this.from)) {
                        UserManager.getInstance().saveLocateCityCode(str);
                        UserManager.getInstance().saveLocateProvinceCode(str2);
                        App.getSharePreferenceUtil().putString(CityChangeManager.PREFERENCE_SELECT_KEY, str3);
                        UserManager.getInstance().saveUserLocateCityName(str3);
                    }
                    CitySelectActivity.this.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.citySelectLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    CitySelectActivity.this.editText.setFocusable(true);
                    CitySelectActivity.this.editText.setFocusableInTouchMode(true);
                    CitySelectActivity.this.editText.requestFocus();
                    CitySelectActivity.this.editText.findFocus();
                    ((InputMethodManager) CitySelectActivity.this.editText.getContext().getSystemService("input_method")).showSoftInput(CitySelectActivity.this.editText, 0);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.editText.setFilters(new InputFilter[]{new InputFilter() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.8
                @Override // android.text.InputFilter
                public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                    return ((charSequence.toString().matches("[一-龥]+") || charSequence.toString().matches("[a-zA-Z /]+")) && !" ".equals(charSequence)) ? charSequence : "";
                }
            }});
            loadNewData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            if ("chooseCity".equals(this.from)) {
                setResult(-1, new Intent());
                MsLogUtil.m7979d("chooseCityJsPlugin", "onKeyDown: 返回");
            }
            finish();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.recentList.size(); i++) {
            CitySelectEntity citySelectEntity = this.recentList.get(i);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("cityCode", citySelectEntity.getCityCode());
                jSONObject.put("cityName", citySelectEntity.getCityName());
                jSONObject.put("pingyin", citySelectEntity.getPingyin());
                jSONObject.put("privienceCode", citySelectEntity.getPrivienceCode());
                jSONObject.put("provienceName", citySelectEntity.getProvienceName());
                jSONObject.put("sortLetters", citySelectEntity.getSortLetters());
                jSONObject.put("mapCode", citySelectEntity.getMapCode());
                jSONObject.put("latitude", citySelectEntity.getLatitude());
                jSONObject.put("longitude", citySelectEntity.getLongitude());
                jSONArray.put(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.preferences.putString("CitySelectActivityRecent", !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerRecentList(CitySelectEntity citySelectEntity) {
        Iterator<CitySelectEntity> it = this.recentList.iterator();
        while (it.hasNext()) {
            if (it.next().getCityName().equals(citySelectEntity.getCityName())) {
                it.remove();
            }
        }
        this.recentList.add(0, citySelectEntity);
        if (this.recentList.size() > 10) {
            this.recentList.subList(0, 9);
        }
        Intent intent = new Intent();
        intent.putExtra("cityName", citySelectEntity.getCityName());
        intent.putExtra("mapCode", citySelectEntity.getCityCode());
        intent.putExtra("latitude", citySelectEntity.getLatitude());
        intent.putExtra("longitude", citySelectEntity.getLongitude());
        intent.putExtra("privienceCode", citySelectEntity.getPrivienceCode());
        intent.putExtra("provinceName", citySelectEntity.getProvienceName());
        intent.putExtra("cityCode", citySelectEntity.getCityCode());
        if ("chooseCity".equals(this.from)) {
            intent.putExtra("type", "00");
        }
        if ("home".equals(this.from)) {
            UserManager.getInstance().saveLocateCityCode(citySelectEntity.getCityCode());
            UserManager.getInstance().saveLocateProvinceCode(citySelectEntity.getPrivienceCode());
            App.getSharePreferenceUtil().putString(CityChangeManager.PREFERENCE_SELECT_KEY, citySelectEntity.getCityName());
            UserManager.getInstance().saveUserLocateCityName(citySelectEntity.getCityName());
        }
        UserManager.getInstance().saveUserLocateCityTime(0L);
        setResult(-1, intent);
        finish();
    }

    private void initRecentLayout(List<CitySelectEntity> list) {
        this.recentLayout.removeAllViews();
        if (list.size() > 0) {
            this.zuijinshiyongLayout.setVisibility(0);
        } else {
            this.zuijinshiyongLayout.setVisibility(8);
        }
        for (int i = 0; i < list.size(); i++) {
            final CitySelectEntity citySelectEntity = list.get(i);
            TextView textView = (TextView) this.inflater.inflate(2131493121, (ViewGroup) this.recentLayout, false);
            String cityName = list.get(i).getCityName();
            if (!TextUtils.isEmpty(cityName) && cityName.length() > 4) {
                cityName = cityName.substring(0, 4) + "..";
            }
            textView.setText(cityName);
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    CitySelectActivity.this.handlerRecentList(citySelectEntity);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.recentLayout.addView(textView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void filterData(String str) {
        this.filterDateList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            this.tipTilteTv.setVisibility(8);
            this.filterDateList = this.list;
            setVisiable(0);
            this.cityAdapter.updateListView(this.filterDateList);
            return;
        }
        this.filterDateList.clear();
        setVisiable(8);
        for (CitySelectEntity citySelectEntity : this.list) {
            if (citySelectEntity.getCityName().indexOf(str.toString()) != -1 || citySelectEntity.getPingyin().startsWith(str.toString().toLowerCase())) {
                this.filterDateList.add(citySelectEntity);
            }
        }
        if (this.filterDateList.size() == 0) {
            this.tipTilteTv.setVisibility(0);
            String obj = this.editText.getText().toString();
            if (Build.VERSION.SDK_INT >= 24) {
                TextView textView = this.tipTilteTv;
                textView.setText(Html.fromHtml("没有查询到<font color='#FF0000'>“" + obj + "”</font>的相关信息", 0));
            } else {
                TextView textView2 = this.tipTilteTv;
                textView2.setText(Html.fromHtml("没有查询到<font color='#FF0000'>“" + obj + "”</font>的相关信息"));
            }
        } else {
            this.tipTilteTv.setVisibility(8);
        }
        this.cityAdapter.updateListView(this.filterDateList, true);
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.10
            @Override // java.lang.Runnable
            public void run() {
                CitySelectActivity.this.editText.setFocusable(true);
                CitySelectActivity.this.editText.setFocusableInTouchMode(true);
                CitySelectActivity.this.editText.requestFocus();
                CitySelectActivity.this.editText.findFocus();
            }
        }, 100L);
    }

    private void setVisiable(int i) {
        if (App.hasLogined() && !TextUtils.isEmpty(this.userManager.getUserAreaname())) {
            this.suoshudiLayout.setVisibility(i);
        }
        if (this.recentList.size() > 0) {
            this.zuijinshiyongLayout.setVisibility(i);
        }
        this.quanbudishiLyout.setVisibility(i);
        this.sideBar.setVisibility(i);
    }

    public int getSectionForPosition(int i) {
        return this.list.get(i).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int i) {
        for (int i2 = 0; i2 < this.list.size(); i2++) {
            if (this.list.get(i2).getSortLetters().toUpperCase().charAt(0) == i) {
                return i2;
            }
        }
        return -1;
    }

    private void loadNewData() {
        App.getAsyncHttpClient().post(URLSet.getCityLatlongUrl(), new RequestParams(), new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.activity.CitySelectActivity.11
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str) {
                super.onSuccess(i, str);
                if (i != 200 || TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    CitySelectActivity.this.list = CitySelectActivity.this.cityListManager.getList(str);
                    if (CitySelectActivity.this.list == null || CitySelectActivity.this.list.size() <= 0) {
                        String userUnicomInfoData = CitySelectActivity.this.dataCenter.getUserUnicomInfoData("0", UserUnicomInfoDataCenter.TYPE_BAIDUMAP_CITY);
                        if (TextUtils.isEmpty(userUnicomInfoData)) {
                            CitySelectActivity.this.list = CitySelectActivity.this.cityListManager.getList(CitySelectActivity.this.cityListManager.getProvinceData(CitySelectActivity.this.activityContext));
                        } else {
                            CitySelectActivity.this.list = CitySelectActivity.this.cityListManager.getList(userUnicomInfoData);
                        }
                    } else {
                        CitySelectActivity.this.cityAdapter.notifyDataSetChanged();
                        CitySelectActivity.this.dataCenter.updataUserUnicomInfoDataCenter("0", str, UserUnicomInfoDataCenter.TYPE_BAIDUMAP_CITY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

package com.sinovatech.unicom.separatemodule.baidumap.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.datacenter.UserUnicomInfoDataCenter;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduBusinessimageAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduCoffeeVolumeAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduServiceLabelAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.entity.CollectEntity;
import com.sinovatech.unicom.separatemodule.baidumap.parser.CollectJsonDataParser;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduCollectActivity extends Activity {
    public NBSTraceUnit _nbs_trace;
    private Activity activityContext = this;
    private String latitude;
    private ArrayList<CollectEntity> list;
    private String longitude;
    private ImageButton mBackBut;
    private LinearLayout mCollect;
    private RecyclerView mCollectRecycler;

    /* renamed from: pd */
    private CustomePorgressDialog f18494pd;

    /* renamed from: sp */
    private SharePreferenceUtil f18495sp;
    private UserUnicomInfoDataCenter unicomInfoDataCenter;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 74);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
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
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ArrayList access$100(BaiduCollectActivity baiduCollectActivity) {
        return baiduCollectActivity.list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Activity access$400(BaiduCollectActivity baiduCollectActivity) {
        return baiduCollectActivity.activityContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduCollectActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class View$OnClickListenerC85421 implements View.OnClickListener {
        View$OnClickListenerC85421() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            BaiduCollectActivity.this.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getData() {
        HashMap hashMap = new HashMap();
        hashMap.put("userLongitude", this.longitude);
        hashMap.put("userLatitude", this.latitude);
        hashMap.put("mobile", UserManager.getInstance().getCurrentPhoneNumber());
        hashMap.put("version", this.activityContext.getString(2131886969));
        String stateion_Collect = URLSet.getStateion_Collect();
        UIUtils.logD("关注URL：" + stateion_Collect);
        UIUtils.logD("关注入参：" + hashMap);
        App.getAsyncHttpClient(20, 20, 20).rxGet(stateion_Collect, hashMap, 0, 0).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new C85453()).observeOn(AndroidSchedulers.mainThread()).subscribe(new C85432());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduCollectActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C85453 implements Function<String, ArrayList<CollectEntity>> {
        C85453() {
        }

        @Override // io.reactivex.functions.Function
        public ArrayList<CollectEntity> apply(String str) throws Exception {
            BaiduCollectActivity.this.unicomInfoDataCenter.updataUserUnicomInfoDataCenter(UserManager.getInstance().getCurrentPhoneNumber(), str, UserUnicomInfoDataCenter.TYPE_VAIDUMAPCOLLECT);
            ArrayList<CollectEntity> arrayList = new ArrayList<>();
            if (TextUtils.isEmpty(str)) {
                str = "[]";
            }
            UIUtils.logD("关注报文：" + str);
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(CollectJsonDataParser.parseSingleBusiness(jSONArray.getJSONObject(i)));
            }
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduCollectActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C85432 implements Observer<ArrayList<CollectEntity>> {
        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        C85432() {
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            BaiduCollectActivity.this.f18494pd.setMessage("努力加载中...");
            BaiduCollectActivity.this.f18494pd.show();
        }

        @Override // io.reactivex.Observer
        public void onNext(ArrayList<CollectEntity> arrayList) {
            BaiduCollectActivity.this.f18494pd.cancel();
            BaiduCollectActivity.this.f18494pd.dismiss();
            BaiduCollectActivity.this.list = arrayList;
            if (arrayList.size() > 0) {
                BaiduCollectActivity.this.mCollect.setVisibility(8);
                BaiduCollectActivity.this.mCollectRecycler.setVisibility(0);
                BaiduCollectActivity.this.mCollectRecycler.setLayoutManager(new LinearLayoutManager(BaiduCollectActivity.this.activityContext));
                BaiduCollectActivity.this.mCollectRecycler.setAdapter(new RecyclerViewCollect(BaiduCollectActivity.this, null));
                return;
            }
            BaiduCollectActivity.this.mCollectRecycler.setVisibility(8);
            BaiduCollectActivity.this.mCollect.setVisibility(0);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            UIUtils.logD("关注报文报错：" + th.getMessage());
            BaiduCollectActivity.this.f18494pd.cancel();
            BaiduCollectActivity.this.f18494pd.dismiss();
            CustomDialogManager.show(BaiduCollectActivity.this.activityContext, "温馨提示", "加载关注失败 是否重新加载?", true, "取消", "确定", true, (CustomDialogManager.SimpleCustomeDialogListener) new C85441());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduCollectActivity$2$1 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public class C85441 implements CustomDialogManager.SimpleCustomeDialogListener {
            /* JADX INFO: Access modifiers changed from: package-private */
            public C85441() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
            public void onClickOk() {
                BaiduCollectActivity.this.getData();
            }
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RecyclerViewCollect extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
            jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: INVOKE  (r20 I:android.app.FragmentManager), (r21 I:android.app.FragmentManager$OnBackStackChangedListener) type: VIRTUAL call: android.app.FragmentManager.addOnBackStackChangedListener(android.app.FragmentManager$OnBackStackChangedListener):void, expected to be less than 13
            	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
            	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
            */
        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@android.support.annotation.NonNull android.support.p086v7.widget.RecyclerView.ViewHolder r11, int r12) {
            /*
                Method dump skipped, instructions count: 1148
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduCollectActivity.RecyclerViewCollect.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public RecyclerViewCollect() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ RecyclerViewCollect(BaiduCollectActivity baiduCollectActivity, View$OnClickListenerC85421 view$OnClickListenerC85421) {
            this();
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyHolder(LayoutInflater.from(BaiduCollectActivity.this).inflate(2131493013, viewGroup, false));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduCollectActivity$RecyclerViewCollect$1 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public class C85461 extends LinearLayoutManager {
            @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public C85461(Context context, int i, boolean z) {
                super(context, i, z);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduCollectActivity$RecyclerViewCollect$2 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public class C85472 implements BaiduServiceLabelAdapter.OnItemClickListener {
            final /* synthetic */ MyHolder val$myHolder;

            /* JADX INFO: Access modifiers changed from: package-private */
            public C85472(MyHolder myHolder) {
                this.val$myHolder = myHolder;
            }

            @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduServiceLabelAdapter.OnItemClickListener
            public void onItemClick() {
                this.val$myHolder.itemView.performClick();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduCollectActivity$RecyclerViewCollect$3 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public class C85483 implements BaiduBusinessimageAdapter.OnItemClickListener {
            final /* synthetic */ MyHolder val$myHolder;

            /* JADX INFO: Access modifiers changed from: package-private */
            public C85483(MyHolder myHolder) {
                this.val$myHolder = myHolder;
            }

            @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduBusinessimageAdapter.OnItemClickListener
            public void onItemClick() {
                this.val$myHolder.itemView.performClick();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NBSInstrumented
        /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduCollectActivity$RecyclerViewCollect$4 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class View$OnClickListenerC85494 implements View.OnClickListener {
            final /* synthetic */ CollectEntity val$entity;

            /* JADX INFO: Access modifiers changed from: package-private */
            public View$OnClickListenerC85494(CollectEntity collectEntity) {
                this.val$entity = collectEntity;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str;
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (!TextUtils.isEmpty(this.val$entity.getEhall_frontAddress())) {
                    try {
                        str = Base64.encodeToString(this.val$entity.getEhall_frontAddress().getBytes("UTF-8"), 2);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        str = "";
                    }
                    StatisticsUploadUtils.upload(BaiduCollectActivity.this.activityContext, "FJ0001", "点击详情", this.val$entity.getTypeIdentifier(), this.val$entity.getId(), this.val$entity.getEpName(), str);
                    IntentManager.generateIntentAndGo(BaiduCollectActivity.this.activityContext, this.val$entity.getEhall_frontAddress(), this.val$entity.getEpName(), false, "get");
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduCollectActivity$RecyclerViewCollect$5 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        class C85505 implements BaiduCoffeeVolumeAdapter.OnItemClickListener {
            final /* synthetic */ MyHolder val$myHolder;

            C85505(MyHolder myHolder) {
                this.val$myHolder = myHolder;
            }

            @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduCoffeeVolumeAdapter.OnItemClickListener
            public void onItemClick() {
                this.val$myHolder.itemView.performClick();
            }
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return BaiduCollectActivity.this.list.size();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public class MyHolder extends RecyclerView.ViewHolder {
            private final LinearLayout mAct;
            private final TextView mActhint;
            private final TextView mActtitle;
            private final LinearLayout mAreaLayout;
            private final RecyclerView mBusinessImage;
            private final TextView mBusinessName;
            private final ImageView mCoupons;
            private final TextView mDistance;
            private final View mDivider;
            private final TextView mGrade;
            private final LinearLayout mLive;
            private final TextView mLivetitle;
            private final ImageView mPicture;
            private final RecyclerView mRecycler_volume;
            private final TextView mRegion;
            private final RecyclerView mServiceLabel;
            private final TextView mSite;
            private final ImageView mStar;
            private final TextView mTime;
            private final TextView mTypeName;

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ LinearLayout access$1000(MyHolder myHolder) {
                return myHolder.mAreaLayout;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ TextView access$1100(MyHolder myHolder) {
                return myHolder.mBusinessName;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ TextView access$1200(MyHolder myHolder) {
                return myHolder.mTime;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ TextView access$1300(MyHolder myHolder) {
                return myHolder.mSite;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ ImageView access$1400(MyHolder myHolder) {
                return myHolder.mStar;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ TextView access$1500(MyHolder myHolder) {
                return myHolder.mGrade;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ TextView access$1600(MyHolder myHolder) {
                return myHolder.mDistance;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ RecyclerView access$1700(MyHolder myHolder) {
                return myHolder.mServiceLabel;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ ImageView access$1800(MyHolder myHolder) {
                return myHolder.mPicture;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ View access$1900(MyHolder myHolder) {
                return myHolder.mDivider;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ TextView access$2000(MyHolder myHolder) {
                return myHolder.mRegion;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ TextView access$2100(MyHolder myHolder) {
                return myHolder.mTypeName;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ TextView access$2200(MyHolder myHolder) {
                return myHolder.mActtitle;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ TextView access$2300(MyHolder myHolder) {
                return myHolder.mActhint;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ RecyclerView access$2400(MyHolder myHolder) {
                return myHolder.mBusinessImage;
            }

            static /* synthetic */ LinearLayout access$2500(MyHolder myHolder) {
                return myHolder.mLive;
            }

            static /* synthetic */ TextView access$2600(MyHolder myHolder) {
                return myHolder.mLivetitle;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ LinearLayout access$800(MyHolder myHolder) {
                return myHolder.mAct;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ ImageView access$900(MyHolder myHolder) {
                return myHolder.mCoupons;
            }

            public MyHolder(View view) {
                super(view);
                this.mBusinessName = (TextView) view.findViewById(2131296556);
                this.mTime = (TextView) view.findViewById(2131298781);
                this.mSite = (TextView) view.findViewById(2131298631);
                this.mPicture = (ImageView) view.findViewById(2131297278);
                this.mCoupons = (ImageView) view.findViewById(2131296721);
                this.mBusinessImage = (RecyclerView) view.findViewById(2131296555);
                this.mAct = (LinearLayout) view.findViewById(2131296288);
                this.mActtitle = (TextView) view.findViewById(2131296319);
                this.mActhint = (TextView) view.findViewById(2131296291);
                this.mDistance = (TextView) view.findViewById(2131296888);
                this.mGrade = (TextView) view.findViewById(2131297094);
                this.mStar = (ImageView) view.findViewById(2131298678);
                this.mLive = (LinearLayout) view.findViewById(2131297633);
                this.mLivetitle = (TextView) view.findViewById(2131297682);
                this.mAreaLayout = (LinearLayout) view.findViewById(2131296375);
                this.mRegion = (TextView) view.findViewById(2131298276);
                this.mTypeName = (TextView) view.findViewById(2131299164);
                this.mServiceLabel = (RecyclerView) view.findViewById(2131298518);
                this.mRecycler_volume = (RecyclerView) view.findViewById(2131298272);
                this.mDivider = view.findViewById(2131296889);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String conversion(String str) {
            DecimalFormat decimalFormat;
            float f = 0.0f;
            try {
                f = Float.valueOf(str).floatValue() / 1000.0f;
                decimalFormat = new DecimalFormat("0.00");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                decimalFormat = null;
            }
            return decimalFormat.format(f);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        getData();
        NBSAppInstrumentation.activityRestartEndIns();
    }
}

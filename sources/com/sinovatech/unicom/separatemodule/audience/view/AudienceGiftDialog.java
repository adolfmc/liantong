package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.view.PagerAdapter;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.entity.GiftEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGiftDialog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AudienceGiftDialog {
    private static int count;
    private static int myJifenNum;
    private static Disposable subscribe;
    private static ImageView tips;
    private static ViewPager viewPager;
    private static AdapterViewpager vpAdapter;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface GiftClick {
        void onClick(String str, String str2);
    }

    static /* synthetic */ int access$208() {
        int i = count;
        count = i + 1;
        return i;
    }

    public static void show(final AppCompatActivity appCompatActivity, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final List<GiftEntity> list, ManagerAudienceLoadData managerAudienceLoadData, GiftClick giftClick) {
        myJifenNum = 0;
        count = 0;
        subscribe = null;
        final Dialog dialog = new Dialog(appCompatActivity, 2131952244);
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(2131492975, (ViewGroup) null);
        final ImageView imageView = (ImageView) linearLayout.findViewById(2131296399);
        final TextView textView = (TextView) linearLayout.findViewById(2131296401);
        ImageView imageView2 = (ImageView) linearLayout.findViewById(2131296400);
        TextView textView2 = (TextView) linearLayout.findViewById(2131296402);
        TextView textView3 = (TextView) linearLayout.findViewById(2131296403);
        tips = (ImageView) linearLayout.findViewById(2131297389);
        final C84571 c84571 = new C84571(appCompatActivity, imageView2, textView2, anchorInfoBean, managerAudienceLoadData, dialog, giftClick, textView3);
        viewPager = (ViewPager) linearLayout.findViewById(2131299541);
        managerAudienceLoadData.loadGiftNum().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGiftDialog$9EsfQhmh1fy0FjV7SDW2MiZ9Fr4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceGiftDialog.lambda$show$0(list, appCompatActivity, c84571, (String) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        managerAudienceLoadData.loadScore().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGiftDialog$pI4t-aXcBGsBRvoo5imCtQnjvew
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceGiftDialog.lambda$show$1(textView, imageView, (String) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGiftDialog$Gdd9LKqKJE_belaEXUqpvq-uQOs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceGiftDialog.lambda$show$2(textView, imageView, (Throwable) obj);
            }
        });
        ((ImageView) linearLayout.findViewById(2131296404)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGiftDialog$iAAF-bu2QOjATfC3SDPUbpIu39s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(linearLayout);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = UIUtils.getScreenWidth((Activity) appCompatActivity);
        attributes.height = -2;
        window.setAttributes(attributes);
        initViewpager(viewPager, list, appCompatActivity, c84571);
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.view.AudienceGiftDialog$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C84571 implements AdapterView.OnItemClickListener {
        final /* synthetic */ AppCompatActivity val$activityContext;
        final /* synthetic */ ImageView val$bottomImage2;
        final /* synthetic */ GiftClick val$giftClick;
        final /* synthetic */ ZhuboDataEntity.AnchorInfoBean val$infoBean;
        final /* synthetic */ ManagerAudienceLoadData val$managerLoad;
        final /* synthetic */ Dialog val$shareDialog;
        final /* synthetic */ TextView val$textView2;
        final /* synthetic */ TextView val$textView3;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onItemClick$0(String str) throws Exception {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onItemClick$2(String str) throws Exception {
        }

        C84571(AppCompatActivity appCompatActivity, ImageView imageView, TextView textView, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ManagerAudienceLoadData managerAudienceLoadData, Dialog dialog, GiftClick giftClick, TextView textView2) {
            this.val$activityContext = appCompatActivity;
            this.val$bottomImage2 = imageView;
            this.val$textView2 = textView;
            this.val$infoBean = anchorInfoBean;
            this.val$managerLoad = managerAudienceLoadData;
            this.val$shareDialog = dialog;
            this.val$giftClick = giftClick;
            this.val$textView3 = textView2;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            NBSActionInstrumentation.onItemClickEnter(view, i, this);
            Tracker.onItemClick(adapterView, view, i, j);
            List<GiftEntity> list = AudienceGiftDialog.vpAdapter.getItemAdapter(AudienceGiftDialog.viewPager.getCurrentItem()).list;
            if (AudienceGiftDialog.vpAdapter.getCount() > 1) {
                for (int i2 = 0; i2 < AudienceGiftDialog.vpAdapter.getItemAdapterCount(); i2++) {
                    if (i2 != AudienceGiftDialog.viewPager.getCurrentItem()) {
                        for (GiftEntity giftEntity : AudienceGiftDialog.vpAdapter.getItemAdapter(i2).list) {
                            giftEntity.setSelected(false);
                        }
                        AudienceGiftDialog.vpAdapter.getItemAdapter(i2).notifyDataSetChanged();
                    }
                }
            }
            for (GiftEntity giftEntity2 : list) {
                giftEntity2.setSelected(false);
            }
            final GiftEntity giftEntity3 = list.get(i);
            if (!giftEntity3.isXianhua()) {
                giftEntity3.setSelected(true);
            }
            AudienceGiftDialog.vpAdapter.getItemAdapter(AudienceGiftDialog.viewPager.getCurrentItem()).update(list);
            GlideApp.with((FragmentActivity) this.val$activityContext).load(giftEntity3.getImgFileNum()).into(this.val$bottomImage2);
            this.val$textView2.setText(giftEntity3.getGiftNum() + "");
            if ("10001".equals(giftEntity3.getGiftCode())) {
                if (giftEntity3.getGiftNum() <= 0) {
                    NBSActionInstrumentation.onItemClickExit();
                    return;
                }
                if (giftEntity3.getGiftNum() > 0) {
                    AudienceGiftDialog.access$208();
                }
                if (AudienceGiftDialog.subscribe != null) {
                    AudienceGiftDialog.subscribe.dispose();
                }
                Observable<Long> observeOn = Observable.timer(500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread());
                final ZhuboDataEntity.AnchorInfoBean anchorInfoBean = this.val$infoBean;
                final ManagerAudienceLoadData managerAudienceLoadData = this.val$managerLoad;
                final Dialog dialog = this.val$shareDialog;
                final GiftClick giftClick = this.val$giftClick;
                Disposable unused = AudienceGiftDialog.subscribe = observeOn.subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGiftDialog$1$rspaQ3IsYBy1ISUtuV4UDc19rtw
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceGiftDialog.C84571.lambda$onItemClick$1(ZhuboDataEntity.AnchorInfoBean.this, managerAudienceLoadData, giftEntity3, dialog, giftClick, (Long) obj);
                    }
                });
                if (giftEntity3.getGiftNum() >= 1) {
                    giftEntity3.setGiftNum(giftEntity3.getGiftNum() - 1);
                }
            } else if (giftEntity3.getGiftNum() > 0) {
                this.val$textView3.setVisibility(0);
                this.val$textView3.setText("赠送");
                TextView textView = this.val$textView3;
                final ZhuboDataEntity.AnchorInfoBean anchorInfoBean2 = this.val$infoBean;
                final ManagerAudienceLoadData managerAudienceLoadData2 = this.val$managerLoad;
                final Dialog dialog2 = this.val$shareDialog;
                final GiftClick giftClick2 = this.val$giftClick;
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGiftDialog$1$PypPUvF7U3-1VYNK8UJhHWhp1zc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        AudienceGiftDialog.C84571.lambda$onItemClick$3(ZhuboDataEntity.AnchorInfoBean.this, giftEntity3, managerAudienceLoadData2, dialog2, giftClick2, view2);
                    }
                });
            } else {
                try {
                    int parseInt = Integer.parseInt(giftEntity3.getGiftPrice());
                    this.val$textView3.setVisibility(0);
                    if (AudienceGiftDialog.myJifenNum > parseInt) {
                        this.val$textView3.setText("去兑换");
                        TextView textView2 = this.val$textView3;
                        final AppCompatActivity appCompatActivity = this.val$activityContext;
                        final Dialog dialog3 = this.val$shareDialog;
                        final ZhuboDataEntity.AnchorInfoBean anchorInfoBean3 = this.val$infoBean;
                        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGiftDialog$1$KB7hodtrAkHiNW6O77ARS_2qxTw
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                AudienceGiftDialog.C84571.lambda$onItemClick$4(AppCompatActivity.this, giftEntity3, dialog3, anchorInfoBean3, view2);
                            }
                        });
                    } else {
                        this.val$textView3.setText("赚积分");
                        TextView textView3 = this.val$textView3;
                        final AppCompatActivity appCompatActivity2 = this.val$activityContext;
                        final Dialog dialog4 = this.val$shareDialog;
                        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGiftDialog$1$IPMIo8TofE9nEnu3aL50wQqSvy4
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                AudienceGiftDialog.C84571.lambda$onItemClick$5(GiftEntity.this, appCompatActivity2, dialog4, view2);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            NBSActionInstrumentation.onItemClickExit();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onItemClick$1(ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ManagerAudienceLoadData managerAudienceLoadData, GiftEntity giftEntity, Dialog dialog, GiftClick giftClick, Long l) throws Exception {
            if (AudienceGiftDialog.count <= 0) {
                return;
            }
            PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "鲜花", "直播详情页", anchorInfoBean.getLiveTitle(), "直播详情页");
            String giftCode = giftEntity.getGiftCode();
            managerAudienceLoadData.sendLiwu(anchorInfoBean, giftCode, AudienceGiftDialog.count + "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGiftDialog$1$PfHHYywjMTSaBSBMurR6oIrTmjI
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceGiftDialog.C84571.lambda$onItemClick$0((String) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            dialog.dismiss();
            String giftCode2 = giftEntity.getGiftCode();
            giftClick.onClick(giftCode2, AudienceGiftDialog.count + "");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onItemClick$3(ZhuboDataEntity.AnchorInfoBean anchorInfoBean, GiftEntity giftEntity, ManagerAudienceLoadData managerAudienceLoadData, Dialog dialog, GiftClick giftClick, View view) {
            String livePullUrl = anchorInfoBean.getLivePullUrl();
            String userName = anchorInfoBean.getUserName();
            PvCurrencyLogUtils.pvLogLive(livePullUrl, 2, userName, giftEntity.getGiftName() + "-赠送", "直播详情页", anchorInfoBean.getLiveTitle(), "直播详情页");
            managerAudienceLoadData.sendLiwu(anchorInfoBean, giftEntity.getGiftCode(), "1").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGiftDialog$1$FKUpu7nYplC22j1D_yhX4IIumFU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceGiftDialog.C84571.lambda$onItemClick$2((String) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            dialog.dismiss();
            giftClick.onClick(giftEntity.getGiftCode(), "1");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onItemClick$4(AppCompatActivity appCompatActivity, GiftEntity giftEntity, Dialog dialog, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, View view) {
            AudienceActivity.IntentGo(appCompatActivity, giftEntity.getPayUrl());
            dialog.dismiss();
            String livePullUrl = anchorInfoBean.getLivePullUrl();
            String userName = anchorInfoBean.getUserName();
            PvCurrencyLogUtils.pvLogLive(livePullUrl, 2, userName, giftEntity.getGiftName() + "-兑换", "直播详情页", anchorInfoBean.getLiveTitle(), "直播详情页");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onItemClick$5(GiftEntity giftEntity, AppCompatActivity appCompatActivity, Dialog dialog, View view) {
            if (!TextUtils.isEmpty(giftEntity.getObtainUrl())) {
                AudienceActivity.IntentGo(appCompatActivity, giftEntity.getObtainUrl());
            } else {
                AudienceActivity.IntentGo(appCompatActivity, URLSet.getAudienceQiandaoUrl());
            }
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(List list, AppCompatActivity appCompatActivity, AdapterView.OnItemClickListener onItemClickListener, String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        if ("0000".equals(jSONObject.optString("statusCode"))) {
            JSONObject optJSONObject = jSONObject.optJSONObject("gift");
            Iterator it = list.iterator();
            while (it.hasNext()) {
                GiftEntity giftEntity = (GiftEntity) it.next();
                JSONObject optJSONObject2 = optJSONObject.optJSONObject(giftEntity.getGiftCode());
                if (optJSONObject2 != null) {
                    giftEntity.setGiftNum(optJSONObject2.optInt("giftNum"));
                }
                giftEntity.setSelected(false);
            }
            initViewpager(viewPager, list, appCompatActivity, onItemClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$1(TextView textView, ImageView imageView, String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("score");
        if ("0000".equals(jSONObject.optString("statusCode"))) {
            textView.setText("可兑换积分:" + optString);
            try {
                myJifenNum = Integer.parseInt(optString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            textView.setText("重新刷新");
        }
        imageView.setImageResource(2131230915);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$2(TextView textView, ImageView imageView, Throwable th) throws Exception {
        textView.setText("重新刷新");
        imageView.setImageResource(2131230915);
    }

    private static void initViewpager(ViewPager viewPager2, List<GiftEntity> list, AppCompatActivity appCompatActivity, AdapterView.OnItemClickListener onItemClickListener) {
        new ArrayList();
        vpAdapter = new AdapterViewpager(createList(list, 8), appCompatActivity, onItemClickListener);
        viewPager2.setAdapter(vpAdapter);
        if (list.size() > 8) {
            tips.setVisibility(0);
        } else {
            tips.setVisibility(4);
        }
        viewPager2.setCurrentItem(0);
        viewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.AudienceGiftDialog.2
            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                NBSActionInstrumentation.onPageSelectedEnter(i, this);
                AudienceGiftDialog.tips.setImageResource(i == 0 ? 2131230903 : 2131230904);
                NBSActionInstrumentation.onPageSelectedExit();
            }
        });
    }

    private static List<List<GiftEntity>> createList(List<GiftEntity> list, int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        int size = list.size() % i == 0 ? list.size() / i : (list.size() / i) + 1;
        int i3 = 0;
        while (i3 < size) {
            ArrayList arrayList2 = new ArrayList();
            int i4 = i3 * i;
            while (true) {
                i2 = i3 + 1;
                if (i4 <= (i * i2) - 1) {
                    if (i4 <= list.size() - 1) {
                        arrayList2.add(list.get(i4));
                    }
                    i4++;
                }
            }
            arrayList.add(arrayList2);
            i3 = i2;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class GiftAdapter extends BaseAdapter {
        private AppCompatActivity activityContext;
        private LayoutInflater inflater;
        public List<GiftEntity> list;

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        public GiftAdapter(List<GiftEntity> list, AppCompatActivity appCompatActivity) {
            this.list = list;
            this.inflater = LayoutInflater.from(appCompatActivity);
            this.activityContext = appCompatActivity;
        }

        public void update(List<GiftEntity> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.list.size();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            GiftEntity giftEntity = this.list.get(i);
            View inflate = this.inflater.inflate(2131492988, viewGroup, false);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131296429);
            TextView textView = (TextView) inflate.findViewById(2131296430);
            GlideApp.with((FragmentActivity) this.activityContext).load(giftEntity.getGiftImgSrc()).into((ImageView) inflate.findViewById(2131296428));
            ((TextView) inflate.findViewById(2131296431)).setText(giftEntity.getGiftName());
            if (giftEntity.isXianhua()) {
                textView.setText(giftEntity.getGiftNum() + "");
                textView.setBackgroundResource(2131230905);
                textView.setTextColor(-1);
            } else {
                textView.setText(giftEntity.getGiftPrice() + "积分");
                textView.setBackgroundResource(0);
                textView.setTextColor(-10066330);
            }
            if (giftEntity.isSelected()) {
                linearLayout.setBackgroundResource(2131230902);
            } else {
                linearLayout.setBackgroundResource(0);
            }
            return inflate;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class AdapterViewpager extends PagerAdapter {
        private AppCompatActivity activityContext;
        private List<GiftAdapter> adapterList = new ArrayList();
        private AdapterView.OnItemClickListener listener;
        private List<List<GiftEntity>> pagerList;

        @Override // android.support.p083v4.view.PagerAdapter
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        public AdapterViewpager(List<List<GiftEntity>> list, AppCompatActivity appCompatActivity, AdapterView.OnItemClickListener onItemClickListener) {
            this.pagerList = list;
            this.activityContext = appCompatActivity;
            this.listener = onItemClickListener;
        }

        public int getItemAdapterCount() {
            return this.adapterList.size();
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public int getCount() {
            return this.pagerList.size();
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(2131492976, (ViewGroup) null);
            GridView gridView = (GridView) inflate.findViewById(2131296405);
            GiftAdapter giftAdapter = new GiftAdapter(this.pagerList.get(i), this.activityContext);
            this.adapterList.add(giftAdapter);
            gridView.setAdapter((ListAdapter) giftAdapter);
            gridView.setOnItemClickListener(this.listener);
            viewGroup.addView(inflate);
            return inflate;
        }

        public GiftAdapter getItemAdapter(int i) {
            return this.adapterList.get(i);
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
            viewGroup.removeView((View) obj);
        }
    }
}

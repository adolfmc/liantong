package com.sinovatech.unicom.basic.p315ui.home.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import com.sinovatech.unicom.basic.p315ui.home.adapter.HomeYwJingZhunAdapter;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeYwJingZhunEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeYwJingZhunEntity_;
import com.sinovatech.unicom.basic.p315ui.home.manager.HomeCardInfoManager;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.home.view.HomeYWJingZhunView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeYWJingZhunView extends LinearLayout {
    private static final String TAG = "HomeYWJingZhunView";
    private Activity activityContext;
    private Box<HomeYwJingZhunEntity> cacheBox;
    private HomeCardInfoManager cardInfoManager;
    private int formType;
    private HomeYwJingZhunAdapter mAdapter;
    private List<HomeYwJingZhunEntity> mList;
    private HomeYwRecycleview recyclerView;

    public HomeYWJingZhunView(Context context, HomeCardInfoManager homeCardInfoManager, int i) {
        super(context);
        this.formType = 0;
        this.activityContext = (Activity) context;
        this.cardInfoManager = homeCardInfoManager;
        this.cacheBox = App.getBoxStore().boxFor(HomeYwJingZhunEntity.class);
        this.formType = i;
        init();
    }

    private void init() {
        try {
            MsLogUtil.m7979d(TAG, "初始化本网精准营销业务控件");
            this.recyclerView = (HomeYwRecycleview) View.inflate(this.activityContext, 2131493179, this).findViewById(2131297129);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.recyclerView.getLayoutParams();
            layoutParams.topMargin = UIUtils.dip2px(12.0f);
            layoutParams.bottomMargin = UIUtils.dip2px(12.0f);
            this.recyclerView.setLayoutParams(layoutParams);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this.activityContext, 0, false));
            this.mList = new ArrayList();
            this.mAdapter = new HomeYwJingZhunAdapter(this.activityContext, this.mList, this.formType);
            this.mAdapter.notifyDataSetChanged();
            this.recyclerView.setAdapter(this.mAdapter);
            this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.basic.ui.home.view.HomeYWJingZhunView.1
                @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 0) {
                        HomeYWJingZhunView.this.addBaoGuangLog();
                    }
                }
            });
        } catch (Exception unused) {
            MsLogUtil.m7977e(TAG, "初始化首页异网精准营销View");
        }
    }

    public void initCache() {
        notifyAdapter(getCache());
    }

    public void notifyAdapter(List<HomeYwJingZhunEntity> list) {
        if (list == null) {
            try {
                list = new ArrayList<>();
            } catch (Exception e) {
                MsLogUtil.m7977e(TAG, "首页异网精准营销刷新adapter异常:" + e.getMessage());
            }
        }
        if (list.size() == 0) {
            List<HomeYwJingZhunEntity> cache = getCache();
            if (cache != null && cache.size() > 0) {
                list.addAll(cache);
            }
            list.addAll(getDefList());
        }
        this.mList.clear();
        this.mList.addAll(list);
        this.mAdapter.notifyDataSetChanged();
        addBaoGuangLog();
        MsLogUtil.m7979d(TAG, "刷新数据");
    }

    public void addBaoGuangLog() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.recyclerView.getLayoutManager();
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        boolean z = false;
        if (findFirstVisibleItemPosition < 0) {
            findFirstVisibleItemPosition = 0;
            z = true;
        }
        if (z) {
            if (findLastVisibleItemPosition < 3) {
                findLastVisibleItemPosition = this.mList.size() > 3 ? 3 : this.mList.size() - 1;
            }
            if (findLastVisibleItemPosition - findFirstVisibleItemPosition >= 4) {
                findLastVisibleItemPosition--;
            }
        } else {
            if (findLastVisibleItemPosition < 4) {
                findLastVisibleItemPosition = this.mList.size() > 4 ? 4 : this.mList.size() - 1;
            }
            if (findLastVisibleItemPosition - findFirstVisibleItemPosition >= 5) {
                findLastVisibleItemPosition--;
            }
        }
        ArrayList arrayList = new ArrayList();
        while (findFirstVisibleItemPosition <= findLastVisibleItemPosition) {
            this.mList.get(findFirstVisibleItemPosition);
            String valueOf = String.valueOf(1100200 + findFirstVisibleItemPosition + 1);
            StringBuilder sb = new StringBuilder();
            sb.append("异网大球卡片位置");
            findFirstVisibleItemPosition++;
            sb.append(findFirstVisibleItemPosition);
            arrayList.add(new HomeLogEntity(valueOf, sb.toString()));
        }
        UnicomHomeLogUtils.getInstance().putLogData(UnicomHomeLogUtils.LOG_TYPE_YWJZ, arrayList);
    }

    public void getData() {
        try {
            this.cardInfoManager.getHomeData(HomeCardInfoManager.YWSP).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.basic.ui.home.view.HomeYWJingZhunView.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    List<HomeYwJingZhunEntity> list = (List) obj;
                    HomeYWJingZhunView.this.setCache(list);
                    HomeYWJingZhunView.this.notifyAdapter(list);
                    MsLogUtil.m7979d(HomeYWJingZhunView.TAG, "首页业务办理接口数据:");
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.home.view.HomeYWJingZhunView.3
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    HomeYWJingZhunView homeYWJingZhunView = HomeYWJingZhunView.this;
                    homeYWJingZhunView.notifyAdapter(homeYWJingZhunView.getCache());
                    MsLogUtil.m7977e(HomeYWJingZhunView.TAG, "首页业务办理接口异常:" + th.getMessage());
                }
            });
        } catch (Exception e) {
            notifyAdapter(getCache());
            MsLogUtil.m7977e(TAG, "首页业务办理接口异常:" + e.getMessage());
        }
    }

    public List<HomeYwJingZhunEntity> getCache() {
        ArrayList arrayList = new ArrayList();
        try {
            MsLogUtil.m7979d(TAG, "获取异网精准营销缓存数据:");
            arrayList.addAll(this.cacheBox.query().equal(HomeYwJingZhunEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find());
            if (arrayList.size() == 0) {
                arrayList.addAll(getDefList());
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "获取异网精准营销缓存数据异常:" + e.getMessage());
        }
        MsLogUtil.m7979d(TAG, "获取异网精准营销缓存数据结果条数:" + arrayList.size());
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCache(List<HomeYwJingZhunEntity> list) {
        if (list == null) {
            try {
                list = new ArrayList<>();
            } catch (Exception e) {
                MsLogUtil.m7977e(TAG, "设置卡片顶部业务办理缓存数据异常:" + e.getMessage());
                return;
            }
        }
        List<HomeYwJingZhunEntity> find = this.cacheBox.query().equal(HomeYwJingZhunEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find();
        if (find != null && find.size() > 0) {
            this.cacheBox.remove(find);
        }
        this.cacheBox.put(list);
    }

    private List<HomeYwJingZhunEntity> getDefList() {
        ArrayList arrayList = new ArrayList();
        try {
            HomeYwJingZhunEntity homeYwJingZhunEntity = new HomeYwJingZhunEntity();
            homeYwJingZhunEntity.setProductRedirecturl("https://card.10010.com/queen/xhzw/xhzw.html?channel=06-0324-1767-b9xk");
            homeYwJingZhunEntity.setProductName("老号继续用");
            homeYwJingZhunEntity.setProductDesc("携号入网");
            homeYwJingZhunEntity.setProductUrl("http://m1.img.10010.com/smart/images/202304060929292634748588096.png");
            HomeYwJingZhunEntity homeYwJingZhunEntity2 = new HomeYwJingZhunEntity();
            homeYwJingZhunEntity2.setProductRedirecturl("https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://card.10010.com/terminal/numberPage?channel=06-0324-a8c6-b7vj");
            homeYwJingZhunEntity2.setProductName("享智能好物");
            homeYwJingZhunEntity2.setProductDesc("号码定制");
            homeYwJingZhunEntity2.setProductUrl("http://m1.img.10010.com/smart/images/2023022114321511070628747506.png");
            HomeYwJingZhunEntity homeYwJingZhunEntity3 = new HomeYwJingZhunEntity();
            homeYwJingZhunEntity3.setProductRedirecturl("https://m.10010.com/queen/new-broadband/band.html?activeId=8818100954349604&channel=06-0324-a3tp-a4gv");
            homeYwJingZhunEntity3.setProductName("免费预约");
            homeYwJingZhunEntity3.setProductDesc("专人上门");
            homeYwJingZhunEntity3.setProductUrl("http://m1.img.10010.com/smart/images/2022053016422210121480865302.png");
            HomeYwJingZhunEntity homeYwJingZhunEntity4 = new HomeYwJingZhunEntity();
            homeYwJingZhunEntity4.setProductRedirecturl("https://m.10010.com/mobileFront/mobile/mobileSalesList.html?channel=06-0324-a8c6-9999");
            homeYwJingZhunEntity4.setProductName("看看新品");
            homeYwJingZhunEntity4.setProductDesc("热门手机");
            homeYwJingZhunEntity4.setProductUrl("http://m1.img.10010.com/smart/images/2021121514135316491898381212.png");
            arrayList.add(homeYwJingZhunEntity);
            arrayList.add(homeYwJingZhunEntity2);
            arrayList.add(homeYwJingZhunEntity3);
            arrayList.add(homeYwJingZhunEntity4);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "获取首页异网精准营销默认数据异常:" + e.getMessage());
        }
        return arrayList;
    }
}

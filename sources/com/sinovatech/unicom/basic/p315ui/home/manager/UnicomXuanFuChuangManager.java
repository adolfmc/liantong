package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeFuChuangEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeFuChuangEntity_;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.audience.view.MyDragView;
import com.sinovatech.unicom.separatemodule.collect.CollectConfig;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.UnicomXuanFuChuangManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomXuanFuChuangManager {
    private static final String TAG = "UnicomXuanFuChuangManager";
    private Box<HomeMergeFuChuangEntity> fuChuangEntityBox;
    private boolean isCloseFuChuang;
    private MyDragView myDragView;
    private boolean showTuiJian = true;
    private ImageView xuanfuImage;
    private ImageView xunfuCloseImage;

    public void initView(MyDragView myDragView) {
        this.showTuiJian = true;
        this.myDragView = myDragView;
        this.xuanfuImage = (ImageView) myDragView.findViewById(2131297214);
        this.xunfuCloseImage = (ImageView) myDragView.findViewById(2131297213);
        this.fuChuangEntityBox = App.getBoxStore().boxFor(HomeMergeFuChuangEntity.class);
    }

    private void setData(final HomeMergeFuChuangEntity homeMergeFuChuangEntity) {
        try {
            if (this.myDragView == null) {
                return;
            }
            if (homeMergeFuChuangEntity != null && !this.isCloseFuChuang) {
                GlideApp.with(App.getInstance()).load(homeMergeFuChuangEntity.getAdvertiseImageURL()).listener(new RequestListener<Drawable>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomXuanFuChuangManager.1
                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                        UnicomXuanFuChuangManager.this.myDragView.setVisibility(8);
                        MsLogUtil.m7979d("IMAGE_JIA", "加载图片失败 隐藏悬浮窗");
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                        MsLogUtil.m7979d("IMAGE_JIA", "图片加载成功 = " + UnicomXuanFuChuangManager.this.showTuiJian);
                        if (UnicomXuanFuChuangManager.this.showTuiJian) {
                            MsLogUtil.m7979d("IMAGE_JIA", "加载图片成功 显示悬浮窗");
                            UnicomXuanFuChuangManager.this.myDragView.setVisibility(0);
                        }
                        return false;
                    }
                }).into(this.xuanfuImage);
                final String goodsId = homeMergeFuChuangEntity.getGoodsId();
                final String actId = homeMergeFuChuangEntity.getActId();
                final String actType = homeMergeFuChuangEntity.getActType();
                this.xuanfuImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$UnicomXuanFuChuangManager$MWrw4NGJuClS_yVAm4VwzZdMvDM
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UnicomXuanFuChuangManager.lambda$setData$0(HomeMergeFuChuangEntity.this, goodsId, actType, actId, view);
                    }
                });
                this.xunfuCloseImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$UnicomXuanFuChuangManager$E1SmLzstHxvFiXb0gPsGfaGuaik
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UnicomXuanFuChuangManager.lambda$setData$1(UnicomXuanFuChuangManager.this, goodsId, actType, actId, homeMergeFuChuangEntity, view);
                    }
                });
            } else {
                MsLogUtil.m7979d("IMAGE_JIA", "设置数据 不需要展示悬浮窗");
                this.myDragView.setVisibility(8);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("IMAGE_JIA", "设置数据异常:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setData$0(HomeMergeFuChuangEntity homeMergeFuChuangEntity, String str, String str2, String str3, View view) {
        UnicomCollectManager unicomCollectManager = UnicomCollectManager.getInstance();
        unicomCollectManager.setTransId("11" + CollectConfig.montageTag1 + "悬浮广告");
        IntentManager.generateFuChuangIntentAndGo(ActivityUtils.getTopActivity(), homeMergeFuChuangEntity, "get");
        StatisticsUploadUtils.upload("11", "首页-悬浮广告", "按钮", str + "," + str2 + "," + str3, homeMergeFuChuangEntity.getAdvertiseTitle(), homeMergeFuChuangEntity.getAdvertiseTargetURL());
    }

    public static /* synthetic */ void lambda$setData$1(UnicomXuanFuChuangManager unicomXuanFuChuangManager, String str, String str2, String str3, HomeMergeFuChuangEntity homeMergeFuChuangEntity, View view) {
        unicomXuanFuChuangManager.isCloseFuChuang = true;
        unicomXuanFuChuangManager.myDragView.setVisibility(8);
        StatisticsUploadUtils.upload("11", "首页-悬浮差号", "按钮", str + "," + str2 + "," + str3, homeMergeFuChuangEntity.getAdvertiseTitle(), homeMergeFuChuangEntity.getAdvertiseTargetURL());
    }

    public void setXuanfuchuangHideOrVisable(boolean z) {
        this.showTuiJian = z;
        MsLogUtil.m7979d("IMAGE_JIA", "切换是否需要显示悬浮窗 = " + z);
        try {
            if (this.myDragView != null) {
                if (!this.isCloseFuChuang && z) {
                    MsLogUtil.m7979d("IMAGE_JIA", "切换 显示悬浮窗");
                    this.myDragView.setVisibility(0);
                } else {
                    MsLogUtil.m7979d("IMAGE_JIA", "切换 隐藏悬浮窗");
                    this.myDragView.setVisibility(8);
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("IMAGE_JIA", "切换异常:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadCacheData() {
        setData(getXuanFuChuangCache());
    }

    public void setXuanFuChuangCacheAndShow(HomeMergeFuChuangEntity homeMergeFuChuangEntity) {
        try {
            setData(homeMergeFuChuangEntity);
            List<HomeMergeFuChuangEntity> find = this.fuChuangEntityBox.query().equal(HomeMergeFuChuangEntity_.ywCode, UserManager.getInstance().isYiwang() ? "0" : "1").and().equal(HomeMergeFuChuangEntity_.cityCode, UserManager.getInstance().getLocateCityCode()).and().equal(HomeMergeFuChuangEntity_.provinceCode, UserManager.getInstance().getLocateProvinceCode()).build().find();
            if (find != null) {
                this.fuChuangEntityBox.remove(find);
            }
            if (homeMergeFuChuangEntity != null) {
                this.fuChuangEntityBox.put((Box<HomeMergeFuChuangEntity>) homeMergeFuChuangEntity);
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7979d(str, "缓存浮窗数据异常" + e.getMessage());
        }
    }

    public HomeMergeFuChuangEntity getXuanFuChuangCache() {
        try {
            return this.fuChuangEntityBox.query().equal(HomeMergeFuChuangEntity_.ywCode, UserManager.getInstance().isYiwang() ? "0" : "1").and().equal(HomeMergeFuChuangEntity_.cityCode, UserManager.getInstance().getLocateCityCode()).and().equal(HomeMergeFuChuangEntity_.provinceCode, UserManager.getInstance().getLocateProvinceCode()).build().findFirst();
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "获取浮窗数据异常:" + e.getMessage());
            return null;
        }
    }
}

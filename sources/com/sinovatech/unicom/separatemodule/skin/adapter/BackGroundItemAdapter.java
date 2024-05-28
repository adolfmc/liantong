package com.sinovatech.unicom.separatemodule.skin.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.cropimg.CuttingImageActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ImageUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import com.sinovatech.unicom.separatemodule.skin.activity.BackgroundDetailctivity;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundTongYongBean;
import com.sinovatech.unicom.separatemodule.skin.event.AddSkinEvent;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BackGroundItemAdapter extends RecyclerView.Adapter<BackGroundVH> {
    private static final String TAG = "BackGroundItemAdapter";
    private List<BackgroundSkinBean> beanList;
    private BackgroundSkinBean currentBean;
    private OnItemDeleteListener deleteListener = null;
    private boolean isMore;
    private Activity mActivity;
    private String showType;
    private BackgroundTongYongBean tongYongBean;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemDeleteListener {
        void onDelete(BackgroundSkinBean backgroundSkinBean);
    }

    public BackGroundItemAdapter(Activity activity, List<BackgroundSkinBean> list, boolean z, BackgroundSkinBean backgroundSkinBean, BackgroundTongYongBean backgroundTongYongBean, String str) {
        this.showType = "";
        this.mActivity = activity;
        this.beanList = list;
        this.isMore = z;
        this.currentBean = backgroundSkinBean;
        this.tongYongBean = backgroundTongYongBean;
        this.showType = str;
    }

    public void setCurrentBean(BackgroundSkinBean backgroundSkinBean) {
        this.currentBean = backgroundSkinBean;
    }

    public void setDeleteListener(OnItemDeleteListener onItemDeleteListener) {
        this.deleteListener = onItemDeleteListener;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public BackGroundVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BackGroundVH(LayoutInflater.from(viewGroup.getContext()).inflate(2131493155, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull BackGroundVH backGroundVH, int i) {
        final BackgroundSkinBean backgroundSkinBean;
        if (backGroundVH != null) {
            try {
                if (this.beanList == null || this.beanList.size() <= 0 || (backgroundSkinBean = this.beanList.get(i)) == null) {
                    return;
                }
                if (this.currentBean != null && !TextUtils.isEmpty(backgroundSkinBean.getProductId()) && TextUtils.equals(this.currentBean.getProductId(), backgroundSkinBean.getProductId())) {
                    Drawable drawable = this.mActivity.getResources().getDrawable(2131230989);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    backGroundVH.mTvTitle.setCompoundDrawables(null, null, drawable, null);
                } else {
                    backGroundVH.mTvTitle.setCompoundDrawables(null, null, null, null);
                }
                if (!TextUtils.equals("custom", backgroundSkinBean.getProductImgUrl())) {
                    if (TextUtils.equals("pifu", backgroundSkinBean.getProductImgUrl())) {
                        backGroundVH.mImgBg.setImageResource(2131230988);
                    } else {
                        ShowImageUtils.showImageView(this.mActivity, backgroundSkinBean.getProductImgUrl(), backGroundVH.mImgBg);
                    }
                } else {
                    backGroundVH.mImgBg.setImageResource(2131230990);
                }
                if (TextUtils.equals("custom_group", this.showType)) {
                    if (TextUtils.equals("custom", backgroundSkinBean.getProductImgUrl())) {
                        backGroundVH.mLinDel.setVisibility(8);
                    } else {
                        backGroundVH.mLinDel.setVisibility(0);
                    }
                    TextView textView = backGroundVH.mTvTitle;
                    textView.setText("自定义首页上传背景" + (i + 1));
                } else {
                    backGroundVH.mTvTitle.setText(backgroundSkinBean.getProductName());
                    backGroundVH.mLinDel.setVisibility(8);
                }
                backGroundVH.mTvDesc.setText(backgroundSkinBean.getProductSubtitle());
                backGroundVH.mLinDel.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        if (!App.hasLogined()) {
                            BackGroundItemAdapter.this.showLoginOutDialog();
                            NBSActionInstrumentation.onClickEventExit();
                            return;
                        }
                        BackGroundItemAdapter.this.showDelDialog(backgroundSkinBean);
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                backGroundVH.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        if (TextUtils.equals("custom", backgroundSkinBean.getProductImgUrl())) {
                            BackGroundItemAdapter.this.selectPic();
                        } else if (!TextUtils.equals("pifu", backgroundSkinBean.getProductImgUrl())) {
                            BackgroundDetailctivity.startNewIntent(BackGroundItemAdapter.this.mActivity, backgroundSkinBean, BackGroundItemAdapter.this.tongYongBean);
                            if (TextUtils.equals("custom_group", backgroundSkinBean.getShowType())) {
                                PvCurrencyLogUtils.pvLogDJ("皮肤首页", "S2ndpage1178", "", "", "", backgroundSkinBean.getGroupName() + "", BackGroundItemAdapter.this.isMore ? "更多" : "", "", "");
                            } else {
                                String str = backgroundSkinBean.getProductName() + "";
                                String str2 = backgroundSkinBean.getGroupName() + "";
                                PvCurrencyLogUtils.pvLogDJ("皮肤首页", "S2ndpage1178", "", "", str, str2, BackGroundItemAdapter.this.isMore ? "更多" : "", backgroundSkinBean.getProductImgUrl() + "", backgroundSkinBean.getProductId() + "");
                            }
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } catch (Exception e) {
                String str = TAG;
                MsLogUtil.m7977e(str, "数据异常:" + e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDelDialog(final BackgroundSkinBean backgroundSkinBean) {
        try {
            BackgroundSkinBean customSkinBean = CacheDataCenter.getInstance().getCustomSkinBean();
            CustomDialogManager.showSkinDialog(this.mActivity, (customSkinBean == null || TextUtils.isEmpty(backgroundSkinBean.getProductId()) || !TextUtils.equals(customSkinBean.getProductId(), backgroundSkinBean.getProductId())) ? "您将删除所上传的自定义首页背景" : "您将删除所上传的自定义首页背景，删除后首页背景将展示APP默认皮肤", true, "取消", "确认删除", true, new CustomDialogManager.SimpleCustomeDialogListener2() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter.3
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener2
                public void onCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener2
                public void onClickOk() {
                    if (!App.hasLogined()) {
                        BackGroundItemAdapter.this.showLoginOutDialog();
                    } else if (BackGroundItemAdapter.this.deleteListener != null) {
                        BackGroundItemAdapter.this.deleteListener.onDelete(backgroundSkinBean);
                    }
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "删除弹窗异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectPic() {
        try {
            if (!App.hasLogined()) {
                showLoginOutDialog();
                return;
            }
            PermissionDialog.show("为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
            new RxPermissions(this.mActivity).request("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter.4
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    PermissionDialog.dimissDialog();
                    if (!bool.booleanValue()) {
                        BackGroundItemAdapter.this.showPermissionDialog();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.PICK");
                    intent.setType("image/*");
                    new AvoidOnResult(BackGroundItemAdapter.this.mActivity).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter.4.1
                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                        public void onActivityResult(int i, Intent intent2) {
                            try {
                                if (!App.hasLogined()) {
                                    BackGroundItemAdapter.this.showLoginOutDialog();
                                } else if (i == -1 && intent2.getData() != null) {
                                    BackGroundItemAdapter.this.cropImg(ImageUtil.getFilePathWithUri(BackGroundItemAdapter.this.mActivity, intent2.getData()));
                                } else {
                                    UIUtils.toast("取消选择");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                UIUtils.toast("程序异常" + e.getMessage());
                            }
                        }
                    });
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter.5
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    PermissionDialog.dimissDialog();
                    BackGroundItemAdapter.this.showPermissionDialog();
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "选择图片异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPermissionDialog() {
        try {
            CustomDialogManager.showSkinDialog(this.mActivity, "此功能需要对APP授权访问相册权限，请您在系统设置内对中国联通APP开放相册权限。", true, "取消", "确认", true, new CustomDialogManager.SimpleCustomeDialogListener2() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter.6
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener2
                public void onCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener2
                public void onClickOk() {
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "隐私弹窗异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cropImg(String str) {
        try {
            if (!App.hasLogined()) {
                showLoginOutDialog();
                return;
            }
            final File file = new File(getChooseImageCacheDir() + File.separator + System.currentTimeMillis() + "_output_crop_image.jpg");
            if (file.exists()) {
                file.delete();
            }
            new AvoidOnResult(this.mActivity).startForResult(CuttingImageActivity.prepare().aspectX(4).aspectY(3).maxWidth(1280).inputPath(str).outputPath(file.getAbsolutePath()).getIntent(this.mActivity), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter.7
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent) {
                    try {
                        if (i == -1 && intent != null) {
                            if (!App.hasLogined()) {
                                BackGroundItemAdapter.this.showLoginOutDialog();
                            } else {
                                BackgroundSkinBean backgroundSkinBean = new BackgroundSkinBean();
                                backgroundSkinBean.setProductImgUrl(file.getAbsolutePath());
                                backgroundSkinBean.setProductSubtitle("APP用户专享");
                                backgroundSkinBean.setGroupName("自定义皮肤");
                                backgroundSkinBean.setShowType("custom_group");
                                backgroundSkinBean.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
                                backgroundSkinBean.setProductId(String.valueOf(System.currentTimeMillis()));
                                AddSkinEvent addSkinEvent = new AddSkinEvent(0);
                                addSkinEvent.setSkinBean(backgroundSkinBean);
                                BackgroundDetailctivity.startNewIntent(BackGroundItemAdapter.this.mActivity, backgroundSkinBean, BackGroundItemAdapter.this.tongYongBean);
                                EventBusUtils.post(addSkinEvent);
                                PvCurrencyLogUtils.pvLogDJ("皮肤首页", "S2ndpage1178", "", "", "", "自定义皮肤", "添加自定义皮肤", "", "");
                            }
                        } else {
                            UIUtils.toastCenter("裁剪取消");
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7980d("裁剪错误" + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            String str2 = TAG;
            MsLogUtil.m7977e(str2, "裁剪图片异常:" + e.getMessage());
        }
    }

    private String getChooseImageCacheDir() {
        File file = new File(this.mActivity.getExternalCacheDir().getAbsolutePath() + File.separator + "haibao_img");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        try {
            int size = this.beanList.size();
            if (this.isMore || size <= 4) {
                return size;
            }
            return 4;
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "皮肤数据数量异常:" + e.getMessage());
            return 0;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class BackGroundVH extends RecyclerView.ViewHolder {
        private ImageView mImgBg;
        private LinearLayout mLinDel;
        private TextView mTvDesc;
        private TextView mTvTitle;

        public BackGroundVH(View view) {
            super(view);
            this.mImgBg = (ImageView) view.findViewById(2131296481);
            this.mTvTitle = (TextView) view.findViewById(2131296482);
            this.mTvDesc = (TextView) view.findViewById(2131296480);
            this.mLinDel = (LinearLayout) view.findViewById(2131296479);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoginOutDialog() {
        CustomDialogManager.show(this.mActivity, "温馨提示", "您的号码在别处登录，本次操作将不会保存", false, "取消", "确认", true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter.8
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }
        });
    }
}

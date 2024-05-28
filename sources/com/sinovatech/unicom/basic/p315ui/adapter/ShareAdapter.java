package com.sinovatech.unicom.basic.p315ui.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.AppUtils;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.eventbus.ShareOnClickEventBus;
import com.sinovatech.unicom.basic.p314po.WeWorkConfigEntity;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerShareConfig;
import com.sinovatech.unicom.basic.p315ui.share.CustomHuaBaoActivity;
import com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.ShareAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ShareAdapter extends RecyclerView.Adapter<ShareViewHolder> {
    private final String TAG = "ShareLogUtil";
    private Activity activityContext;
    private String businessCode;
    private String headUrl;
    private String huabaoIconUrl;
    private ShareManager.ShareListener listenerArg;
    private String provider;
    private String shareContentArg;
    private Dialog shareDialog;
    private String shareIconURL;
    private List<String> shareList;
    private String shareListStr;
    private String shareTitle;
    private String shareType;
    private String shareURL;
    private String tip_content;
    private String tip_title;
    private String wxMiniProgramPath;
    private String wxMiniProgramStatus;
    private String wxMiniProgramType;
    private String wxMiniProgramUserName;

    public ShareAdapter(Activity activity, List<String> list, Dialog dialog, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, ShareManager.ShareListener shareListener, String str11, String str12, String str13, String str14, String str15, String str16) {
        this.shareList = new ArrayList();
        this.activityContext = activity;
        this.shareList = list;
        this.shareDialog = dialog;
        this.shareListStr = str;
        this.shareType = str2;
        this.shareContentArg = str3;
        this.shareTitle = str4;
        this.shareIconURL = str5;
        this.shareURL = str6;
        this.wxMiniProgramStatus = str7;
        this.wxMiniProgramType = str8;
        this.wxMiniProgramUserName = str9;
        this.wxMiniProgramPath = str10;
        this.listenerArg = shareListener;
        this.huabaoIconUrl = str11;
        this.businessCode = str12;
        this.provider = str13;
        this.tip_title = str14;
        this.tip_content = str15;
        this.headUrl = str16;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ShareViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ShareViewHolder(LayoutInflater.from(this.activityContext).inflate(2131493448, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ShareViewHolder shareViewHolder, int i) {
        if (shareViewHolder != null) {
            try {
                final String str = this.shareList.get(i);
                if ("sinaweibo".equals(str)) {
                    shareViewHolder.platTextView.setText("新浪微博");
                    shareViewHolder.platImageView.setImageResource(2131231917);
                } else if ("qq".equals(str)) {
                    shareViewHolder.platTextView.setText("QQ好友");
                    shareViewHolder.platImageView.setImageResource(2131232212);
                } else if ("wechat".equals(str)) {
                    shareViewHolder.platTextView.setText("微信好友");
                    shareViewHolder.platImageView.setImageResource(2131232215);
                } else if ("wechatmoments".equals(str)) {
                    shareViewHolder.platTextView.setText("朋友圈");
                    shareViewHolder.platImageView.setImageResource(2131232216);
                } else if ("qzone".equals(str)) {
                    shareViewHolder.platTextView.setText("QQ空间");
                    shareViewHolder.platImageView.setImageResource(2131232213);
                } else if ("email".equals(str)) {
                    shareViewHolder.platTextView.setText("邮箱");
                    shareViewHolder.platImageView.setImageResource(2131231912);
                } else if ("shortmessage".equals(str)) {
                    shareViewHolder.platTextView.setText("短信");
                    shareViewHolder.platImageView.setImageResource(2131231916);
                } else if ("jietufenxiang".equals(str)) {
                    shareViewHolder.platTextView.setText("截图分享");
                    shareViewHolder.platImageView.setImageResource(2131231913);
                } else if ("wokouling".equals(str)) {
                    shareViewHolder.platTextView.setText("沃口令");
                    shareViewHolder.platImageView.setImageResource(2131232217);
                } else if ("huabaofenxiang".equals(str)) {
                    shareViewHolder.platTextView.setText("画报分享");
                    shareViewHolder.platImageView.setImageResource(2131232211);
                } else if ("zidingyi".equals(str)) {
                    shareViewHolder.platTextView.setText("自定义画报");
                    shareViewHolder.platImageView.setImageResource(2131232752);
                } else if ("0".equals(str)) {
                    Map<String, WeWorkConfigEntity> weWorkMap = ManagerShareConfig.getInstance().getWeWorkMap();
                    if (weWorkMap != null) {
                        shareViewHolder.platTextView.setText(weWorkMap.get("0").getChineseName());
                        shareViewHolder.platImageView.setImageResource(2131232648);
                    }
                } else if ("1".equals(str)) {
                    Map<String, WeWorkConfigEntity> weWorkMap2 = ManagerShareConfig.getInstance().getWeWorkMap();
                    if (weWorkMap2 != null) {
                        shareViewHolder.platTextView.setText(weWorkMap2.get("1").getChineseName());
                        shareViewHolder.platImageView.setImageResource(2131232648);
                    }
                } else {
                    MsLogUtil.m7979d("ShareAdapter", "----无相关type---");
                }
                shareViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.-$$Lambda$ShareAdapter$ZYfktYxau4_Vlw3nICFPp1jivqw
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ShareAdapter.lambda$onBindViewHolder$0(ShareAdapter.this, str, view);
                    }
                });
            } catch (Exception e) {
                UIUtils.logE(e.getMessage());
            }
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x01ca -> B:59:0x01f1). Please submit an issue!!! */
    public static /* synthetic */ void lambda$onBindViewHolder$0(ShareAdapter shareAdapter, final String str, View view) {
        Exception e;
        if ("wokouling".equals(str)) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("businessName", shareAdapter.shareTitle);
                hashMap.put("businessCode", shareAdapter.businessCode);
                hashMap.put("shareUrl", shareAdapter.shareURL);
                hashMap.put("mobile", UserManager.getInstance().getCurrentPhoneNumber());
                hashMap.put("shareImg", shareAdapter.huabaoIconUrl);
                hashMap.put("businessDesc", shareAdapter.shareContentArg);
                String longUrl = ShareLogUtil.getLongUrl(shareAdapter.shareURL, shareAdapter.provider, shareAdapter.headUrl, shareAdapter.shareTitle, shareAdapter.businessCode, "1");
                hashMap.put("longUrl", longUrl);
                UIUtils.logD("ShareLogUtil", "沃口令的长地址 = " + longUrl);
                StatisticsUploadUtils.upload(shareAdapter.activityContext, "S2ndpage1028", str, "url", "", shareAdapter.shareTitle, shareAdapter.shareURL);
                ShareLogUtil.wokouling(shareAdapter.activityContext, hashMap, new ShareLogUtil.WoKouLingListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.ShareAdapter.1
                    @Override // com.sinovatech.unicom.basic.p315ui.share.ShareLogUtil.WoKouLingListener
                    public void onWoKouLingListener(boolean z, String str2) {
                        if (z) {
                            ((ClipboardManager) ShareAdapter.this.activityContext.getSystemService("clipboard")).setText(str2);
                            App.getSharePreferenceUtil().putString("share_wokoulingcontent", str2);
                            ShareManager.showWokoulingDialog(ShareAdapter.this.activityContext, str2, ShareAdapter.this.shareContentArg, ShareAdapter.this.provider, ShareAdapter.this.shareTitle, ShareAdapter.this.businessCode, ShareAdapter.this.shareURL);
                            return;
                        }
                        UIUtils.toast(str2);
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
                UIUtils.logE(e2.getLocalizedMessage());
            }
            Dialog dialog = shareAdapter.shareDialog;
            if (dialog != null && dialog.isShowing()) {
                shareAdapter.shareDialog.cancel();
            }
        } else if ("huabaofenxiang".equals(str)) {
            StatisticsUploadUtils.upload(shareAdapter.activityContext, "S2ndpage1028", str, "image", "", shareAdapter.shareTitle, shareAdapter.shareURL);
            ShareManager.shareHuaBao(shareAdapter.activityContext, shareAdapter.provider, shareAdapter.shareTitle, shareAdapter.huabaoIconUrl, shareAdapter.shareURL, shareAdapter.businessCode, shareAdapter.headUrl, null);
        } else if ("zidingyi".equals(str)) {
            shareAdapter.goCustomHuaBao();
            StatisticsUploadUtils.upload(shareAdapter.activityContext, "S2ndpage1028", str, "image", "", shareAdapter.shareTitle, shareAdapter.shareURL);
        } else if ((TextUtils.equals("qq", str) || TextUtils.equals("qzone", str)) && !AppUtils.isAppInstalled("com.tencent.mobileqq")) {
            UIUtils.toast("列表中未识别到此应用");
            return;
        } else {
            if ("longScreenshot".equals(shareAdapter.shareType) || "localimg".equals(shareAdapter.shareType)) {
                shareAdapter.huabaoIconUrl = "";
            }
            if (!TextUtils.isEmpty(shareAdapter.huabaoIconUrl) && !TextUtils.equals("jietufenxiang", str)) {
                try {
                    if (TextUtils.equals("1", shareAdapter.wxMiniProgramStatus)) {
                        try {
                            if (TextUtils.equals("wechat", str)) {
                                if (!TextUtils.equals("jietufenxiang", str)) {
                                    shareAdapter.shareURL = ShareLogUtil.getLongUrl(shareAdapter.shareURL, shareAdapter.provider, shareAdapter.headUrl, shareAdapter.shareTitle, shareAdapter.businessCode, "2");
                                }
                                ShareManager.share(shareAdapter.activityContext, str, shareAdapter.shareType, shareAdapter.shareContentArg, shareAdapter.shareTitle, shareAdapter.shareIconURL, shareAdapter.shareURL, "", shareAdapter.wxMiniProgramStatus, shareAdapter.wxMiniProgramType, shareAdapter.wxMiniProgramUserName, shareAdapter.wxMiniProgramPath, false, shareAdapter.provider, shareAdapter.businessCode);
                            }
                        } catch (Exception e3) {
                            e = e3;
                            e.printStackTrace();
                            ShareOnClickEventBus shareOnClickEventBus = new ShareOnClickEventBus(0);
                            shareOnClickEventBus.setShareName(str);
                            EventBusUtils.post(shareOnClickEventBus);
                            shareAdapter.shareDialog.cancel();
                        }
                    }
                    ShareManager.shareHuaBao(shareAdapter.activityContext, shareAdapter.provider, shareAdapter.shareTitle, shareAdapter.huabaoIconUrl, shareAdapter.shareURL, shareAdapter.businessCode, shareAdapter.headUrl, new ShareManager.CreateHuaBaoListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.ShareAdapter.2
                        @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.CreateHuaBaoListener
                        public void onResult(boolean z, String str2) {
                            if (z) {
                                ShareManager.share(ShareAdapter.this.activityContext, str, ShareAdapter.this.shareType, ShareAdapter.this.shareContentArg, ShareAdapter.this.shareTitle, ShareAdapter.this.shareIconURL, str2, ShareAdapter.this.shareURL, ShareAdapter.this.wxMiniProgramStatus, ShareAdapter.this.wxMiniProgramType, ShareAdapter.this.wxMiniProgramUserName, ShareAdapter.this.wxMiniProgramPath, true, ShareAdapter.this.provider, ShareAdapter.this.businessCode);
                            } else {
                                UIUtils.toast("分享图片链接错误，无法正确分享");
                            }
                        }
                    });
                } catch (Exception e4) {
                    e = e4;
                    e.printStackTrace();
                    ShareOnClickEventBus shareOnClickEventBus2 = new ShareOnClickEventBus(0);
                    shareOnClickEventBus2.setShareName(str);
                    EventBusUtils.post(shareOnClickEventBus2);
                    shareAdapter.shareDialog.cancel();
                }
            } else {
                ShareManager.share(shareAdapter.activityContext, str, shareAdapter.shareType, shareAdapter.shareContentArg, shareAdapter.shareTitle, shareAdapter.shareIconURL, shareAdapter.shareURL, "", shareAdapter.wxMiniProgramStatus, shareAdapter.wxMiniProgramType, shareAdapter.wxMiniProgramUserName, shareAdapter.wxMiniProgramPath, false, shareAdapter.provider, shareAdapter.businessCode);
            }
        }
        try {
            ShareOnClickEventBus shareOnClickEventBus22 = new ShareOnClickEventBus(0);
            shareOnClickEventBus22.setShareName(str);
            EventBusUtils.post(shareOnClickEventBus22);
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        shareAdapter.shareDialog.cancel();
    }

    private void goCustomHuaBao() {
        boolean z = true;
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (!SoulPermission.getInstance().checkSinglePermission((String) it.next()).isGranted()) {
                    z = false;
                    break;
                }
            }
            if (!z) {
                CustomDialogManager.show(this.activityContext, "存储权限申请", "自定义画报为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。", true, "取消", "确定", true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.ShareAdapter.3
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
                    public void onShow() {
                    }

                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                    public void onClickOk() {
                        try {
                            new RxPermissions(ShareAdapter.this.activityContext).request("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.adapter.ShareAdapter.3.1
                                @Override // io.reactivex.functions.Consumer
                                public void accept(Boolean bool) throws Exception {
                                    if (bool.booleanValue()) {
                                        Intent intent = new Intent(ShareAdapter.this.activityContext, CustomHuaBaoActivity.class);
                                        intent.putExtra("provider", ShareAdapter.this.provider);
                                        intent.putExtra("shareTitle", ShareAdapter.this.shareTitle);
                                        intent.putExtra("huabaoIconUrl", ShareAdapter.this.huabaoIconUrl);
                                        intent.putExtra("shareURL", ShareAdapter.this.shareURL);
                                        intent.putExtra("businessCode", ShareAdapter.this.businessCode);
                                        intent.putExtra("headUrl", ShareAdapter.this.headUrl);
                                        ShareAdapter.this.activityContext.startActivity(intent);
                                        return;
                                    }
                                    UIUtils.toast("您已拒绝该权限 请您到 手机－设置－应用管理－权限管理 中开启。");
                                }
                            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.adapter.ShareAdapter.3.2
                                @Override // io.reactivex.functions.Consumer
                                public void accept(Throwable th) throws Exception {
                                }
                            });
                        } catch (Exception e) {
                            UIUtils.logE(e.getMessage());
                        }
                    }
                });
                return;
            }
            Intent intent = new Intent(this.activityContext, CustomHuaBaoActivity.class);
            intent.putExtra("provider", this.provider);
            intent.putExtra("shareTitle", this.shareTitle);
            intent.putExtra("huabaoIconUrl", this.huabaoIconUrl);
            intent.putExtra("shareURL", this.shareURL);
            intent.putExtra("businessCode", this.businessCode);
            intent.putExtra("headUrl", this.headUrl);
            this.activityContext.startActivity(intent);
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.shareList.size();
    }

    public void addData(List<String> list) {
        this.shareList.addAll(list);
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.ShareAdapter$ShareViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ShareViewHolder extends RecyclerView.ViewHolder {
        ImageView platImageView;
        TextView platTextView;

        public ShareViewHolder(View view) {
            super(view);
            this.platTextView = (TextView) view.findViewById(2131298595);
            this.platImageView = (ImageView) view.findViewById(2131298594);
        }
    }
}

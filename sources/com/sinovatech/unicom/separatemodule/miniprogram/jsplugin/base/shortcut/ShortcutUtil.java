package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.shortcut;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.content.p084pm.ShortcutManagerCompat;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sinovatech.unicom.basic.p315ui.activity.WelcomeClient;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomHintDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ShortcutUtil {
    public static void addPinedShortcutToDesktop(final Activity activity, final String str, final String str2, String str3, final String str4) throws Exception {
        final CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(activity);
        customePorgressDialog.setMessage("正在添加到桌面");
        final ShortcutManager shortcutManager = (ShortcutManager) activity.getSystemService(ShortcutManager.class);
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(activity)) {
            MsLogUtil.m7980d("ShortcutUtil 允许添加");
            List<ShortcutInfo> pinnedShortcuts = shortcutManager.getPinnedShortcuts();
            final boolean z = false;
            for (int i = 0; i < pinnedShortcuts.size(); i++) {
                if (pinnedShortcuts.get(i).getId().equals(str)) {
                    z = true;
                }
            }
            Glide.with(activity).asBitmap().load(str3).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.shortcut.ShortcutUtil.1
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    CustomePorgressDialog.this.cancel();
                    Intent intent = new Intent(activity, WelcomeClient.class);
                    intent.setAction("android.intent.action.MAIN");
                    intent.setData(Uri.parse(str4));
                    intent.addFlags(335577088);
                    ShortcutInfo build = new ShortcutInfo.Builder(activity, str).setIcon(Icon.createWithBitmap(bitmap)).setShortLabel(str2).setIntent(intent).build();
                    if (z) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(build);
                        shortcutManager.updateShortcuts(arrayList);
                        Toast.makeText(activity, "桌面快捷方式已存在", 0).show();
                    } else {
                        shortcutManager.requestPinShortcut(build, null);
                    }
                    SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                    if (sharePreferenceUtil.getBoolean("shortcut-helper-" + str)) {
                        return;
                    }
                    CustomHintDialogManager.show(activity, "已尝试添加到桌面", "若添加失败，请前往系统设置，为中国联通app打开“创建桌面快捷方式”的权限", 17, true, "退出", "了解详情", true, new CustomHintDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.shortcut.ShortcutUtil.1.1
                        @Override // com.sinovatech.unicom.basic.view.CustomHintDialogManager.CustomeDialogListener
                        public void onBackKeyDown() {
                        }

                        @Override // com.sinovatech.unicom.basic.view.CustomHintDialogManager.CustomeDialogListener
                        public void onCancel() {
                        }

                        @Override // com.sinovatech.unicom.basic.view.CustomHintDialogManager.CustomeDialogListener
                        public void onClickCancel() {
                        }

                        @Override // com.sinovatech.unicom.basic.view.CustomHintDialogManager.CustomeDialogListener
                        public void onShow() {
                        }

                        @Override // com.sinovatech.unicom.basic.view.CustomHintDialogManager.CustomeDialogListener
                        public void onClickOk() {
                            IntentManager.gotoWebViewActivity(activity, URLSet.getAddShortcutDestopHelpUrl(), "");
                        }

                        @Override // com.sinovatech.unicom.basic.view.CustomHintDialogManager.CustomeDialogListener
                        public void onCheckBoxStatusChanged(boolean z2) {
                            SharePreferenceUtil sharePreferenceUtil2 = App.getSharePreferenceUtil();
                            sharePreferenceUtil2.putBoolean("shortcut-helper-" + str, Boolean.valueOf(z2));
                        }
                    });
                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadStarted(@Nullable Drawable drawable) {
                    super.onLoadStarted(drawable);
                    CustomePorgressDialog.this.show();
                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(@Nullable Drawable drawable) {
                    super.onLoadFailed(drawable);
                    CustomePorgressDialog.this.cancel();
                    Toast.makeText(activity, "桌面图标下载错误 未能成功添加到桌面 请重试", 1).show();
                }
            });
            return;
        }
        MsLogUtil.m7980d("ShortcutUtil 不允许添加");
        CustomDialogManager.show(activity, "温馨提示", "请前往系统设置，为中国联通app打开“创建桌面快捷方式”的权限", "取消", "了解详情", new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.shortcut.ShortcutUtil.2
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
            public void onClickOk() {
                IntentManager.gotoWebViewActivity(activity, URLSet.getAddShortcutDestopHelpUrl(), "");
            }
        });
    }
}

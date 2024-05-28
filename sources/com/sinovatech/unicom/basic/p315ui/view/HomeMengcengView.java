package com.sinovatech.unicom.basic.p315ui.view;

import android.os.Build;
import android.support.p086v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.binioter.guideview.Component;
import com.binioter.guideview.Guide;
import com.binioter.guideview.GuideBuilder;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.view.HomeMengcengView */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HomeMengcengView {
    private static Guide guide;

    public static void showVideoCenterSmegma(final AppCompatActivity appCompatActivity, final View view) {
        try {
            if ((appCompatActivity.getResources().getConfiguration().screenLayout & 15) != 3 || Build.VERSION.SDK_INT < 24 || appCompatActivity.isInMultiWindowMode()) {
                App.getSharePreferenceUtil().putInt("video_center_count_flag", App.getSharePreferenceUtil().getInt("video_center_count_flag") + 1);
                if (App.getSharePreferenceUtil().getBoolean("video_center_masking_flag")) {
                    return;
                }
                GuideBuilder guideBuilder = new GuideBuilder();
                guideBuilder.setTargetView(view).setAlpha(150).setHighTargetCorner(20).setHighTargetGraphStyle(0);
                guideBuilder.addComponent(new Component() { // from class: com.sinovatech.unicom.basic.ui.view.HomeMengcengView.1
                    @Override // com.binioter.guideview.Component
                    public int getAnchor() {
                        return 3;
                    }

                    @Override // com.binioter.guideview.Component
                    public int getFitPosition() {
                        return 16;
                    }

                    @Override // com.binioter.guideview.Component
                    public int getXOffset() {
                        return 0;
                    }

                    @Override // com.binioter.guideview.Component
                    public int getYOffset() {
                        return 0;
                    }

                    @Override // com.binioter.guideview.Component
                    public View getView(LayoutInflater layoutInflater) {
                        return LayoutInflater.from(AppCompatActivity.this).inflate(2131493307, (ViewGroup) null);
                    }
                });
                guideBuilder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() { // from class: com.sinovatech.unicom.basic.ui.view.HomeMengcengView.2
                    @Override // com.binioter.guideview.GuideBuilder.OnVisibilityChangedListener
                    public void onShown() {
                        App.getSharePreferenceUtil().putBoolean("video_center_masking_flag", true);
                    }

                    @Override // com.binioter.guideview.GuideBuilder.OnVisibilityChangedListener
                    public void onDismiss() {
                        MsLogUtil.m7979d("HomeMengcengView", "onDismiss");
                        HomeMengcengView.showVideoCenterSmegma2(AppCompatActivity.this, view);
                    }
                });
                Guide createGuide = guideBuilder.createGuide();
                createGuide.setShouldCheckLocInWindow(true);
                createGuide.show(appCompatActivity);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("HomeMengcengView", "负一屏新界面引导报错：" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showVideoCenterSmegma2(final AppCompatActivity appCompatActivity, View view) {
        try {
            MsLogUtil.m7979d("HomeMengcengView", "负一屏新界面引导2");
            GuideBuilder guideBuilder = new GuideBuilder();
            guideBuilder.setTargetView(view).setAlpha(150).setHighTargetCorner(20).setOverlayTarget(true).setHighTargetGraphStyle(0);
            guideBuilder.addComponent(new Component() { // from class: com.sinovatech.unicom.basic.ui.view.HomeMengcengView.3
                @Override // com.binioter.guideview.Component
                public int getAnchor() {
                    return 4;
                }

                @Override // com.binioter.guideview.Component
                public int getFitPosition() {
                    return 16;
                }

                @Override // com.binioter.guideview.Component
                public int getXOffset() {
                    return 0;
                }

                @Override // com.binioter.guideview.Component
                public View getView(LayoutInflater layoutInflater) {
                    View inflate = LayoutInflater.from(AppCompatActivity.this).inflate(2131493308, (ViewGroup) null);
                    WindowManager windowManager = AppCompatActivity.this.getWindowManager();
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                    int i = displayMetrics.widthPixels;
                    View findViewById = inflate.findViewById(2131299480);
                    ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
                    layoutParams.width = i;
                    findViewById.setLayoutParams(layoutParams);
                    return inflate;
                }

                @Override // com.binioter.guideview.Component
                public int getYOffset() {
                    return UIUtils.dip2px(40.0f);
                }
            });
            Guide createGuide = guideBuilder.createGuide();
            createGuide.setShouldCheckLocInWindow(false);
            createGuide.show(appCompatActivity);
        } catch (Exception e) {
            MsLogUtil.m7979d("HomeMengcengView", "负一屏新界面引导2报错：" + e.getMessage());
        }
    }

    public static void showVideoRingListMengceng(View view, AppCompatActivity appCompatActivity) {
        try {
            if (((appCompatActivity.getResources().getConfiguration().screenLayout & 15) != 3 || Build.VERSION.SDK_INT < 24 || appCompatActivity.isInMultiWindowMode()) && !App.getSharePreferenceUtil().getBoolean("video_ring_list_masking_flag")) {
                GuideBuilder guideBuilder = new GuideBuilder();
                guideBuilder.setTargetView(view).setAlpha(150).setHighTargetCorner(20).setHighTargetGraphStyle(0);
                guideBuilder.addComponent(new Component() { // from class: com.sinovatech.unicom.basic.ui.view.HomeMengcengView.4
                    @Override // com.binioter.guideview.Component
                    public int getAnchor() {
                        return 2;
                    }

                    @Override // com.binioter.guideview.Component
                    public int getFitPosition() {
                        return 48;
                    }

                    @Override // com.binioter.guideview.Component
                    public int getXOffset() {
                        return -70;
                    }

                    @Override // com.binioter.guideview.Component
                    public int getYOffset() {
                        return 0;
                    }

                    @Override // com.binioter.guideview.Component
                    public View getView(LayoutInflater layoutInflater) {
                        return layoutInflater.inflate(2131493054, (ViewGroup) null);
                    }
                });
                Guide createGuide = guideBuilder.createGuide();
                createGuide.setShouldCheckLocInWindow(false);
                createGuide.show(appCompatActivity);
                App.getSharePreferenceUtil().putBoolean("video_ring_list_masking_flag", true);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("HomeMengcengView", e.getMessage());
        }
    }

    public static void showVideoRingMengceng(final View view, AppCompatActivity appCompatActivity) {
        try {
            if (App.getSharePreferenceUtil().getBoolean("video_ring_masking_flag")) {
                return;
            }
            GuideBuilder guideBuilder = new GuideBuilder();
            guideBuilder.setTargetView(view).setAlpha(150).setHighTargetCorner(20).setHighTargetGraphStyle(0);
            guideBuilder.addComponent(new Component() { // from class: com.sinovatech.unicom.basic.ui.view.HomeMengcengView.5
                @Override // com.binioter.guideview.Component
                public int getAnchor() {
                    return 5;
                }

                @Override // com.binioter.guideview.Component
                public int getFitPosition() {
                    return 48;
                }

                @Override // com.binioter.guideview.Component
                public int getXOffset() {
                    return -120;
                }

                @Override // com.binioter.guideview.Component
                public View getView(LayoutInflater layoutInflater) {
                    View inflate = layoutInflater.inflate(2131493055, (ViewGroup) null);
                    inflate.findViewById(2131299487).setVisibility(view.getId() == 2131297777 ? 0 : 8);
                    return inflate;
                }

                @Override // com.binioter.guideview.Component
                public int getYOffset() {
                    return view.getId() == 2131297777 ? -100 : -140;
                }
            });
            Guide createGuide = guideBuilder.createGuide();
            createGuide.setShouldCheckLocInWindow(false);
            createGuide.show(appCompatActivity);
            App.getSharePreferenceUtil().putBoolean("video_ring_masking_flag", true);
        } catch (Exception e) {
            MsLogUtil.m7979d("HomeMengcengView", e.getMessage());
        }
    }
}

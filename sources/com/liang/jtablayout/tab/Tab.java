package com.liang.jtablayout.tab;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import com.liang.widget.JTabLayout;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface Tab {
    void drawBackground(Canvas canvas);

    CharSequence getContentDescription();

    int getContentWidth();

    Drawable getIcon();

    boolean getInline();

    Drawable getNormalIcon();

    Object getObject();

    int getPosition();

    Drawable getSelectedIcon();

    int getTabBackgroundResId();

    ColorStateList getTabIconTint();

    PorterDuff.Mode getTabIconTintMode();

    JTabLayout getTabLayout();

    int getTabPaddingBottom();

    int getTabPaddingEnd();

    int getTabPaddingStart();

    int getTabPaddingTop();

    ColorStateList getTabRippleColorStateList();

    float getTabTextSize();

    @Nullable
    Object getTag();

    CharSequence getTitle();

    ColorStateList getTitleColor();

    View getView();

    void hideBadge();

    void initTabView();

    boolean isSelected();

    boolean isTabTextBold();

    boolean isUnboundedRipple();

    void reset();

    void select();

    void setBadgeBackgroundColor(int i);

    void setBadgeStroke(int i, int i2);

    void setBadgeTextColor(int i);

    void setBadgeTextSize(float f);

    void setContentDescription(@StringRes int i);

    void setContentDescription(@Nullable CharSequence charSequence);

    Tab setIcon(@DrawableRes int i);

    Tab setIcon(@DrawableRes int i, @DrawableRes int i2);

    Tab setIcon(Drawable drawable);

    Tab setIcon(Drawable drawable, Drawable drawable2);

    Tab setInlineLabel(boolean z);

    Tab setObject(Object obj);

    Tab setPosition(int i);

    Tab setTabBackgroundResId(int i);

    Tab setTabIconTint(@ColorInt int i, @ColorInt int i2);

    Tab setTabIconTint(ColorStateList colorStateList);

    Tab setTabIconTintMode(PorterDuff.Mode mode);

    Tab setTabLayout(JTabLayout jTabLayout);

    Tab setTabPadding(int i, int i2, int i3, int i4);

    Tab setTabRippleColorStateList(ColorStateList colorStateList);

    Tab setTabTextBold(boolean z);

    Tab setTabTextSize(float f);

    void setTag(@Nullable Object obj);

    Tab setTitle(@StringRes int i);

    Tab setTitle(CharSequence charSequence);

    Tab setTitleColor(@ColorInt int i, @ColorInt int i2);

    Tab setTitleColor(ColorStateList colorStateList);

    Tab setUnboundedRipple(boolean z);

    void showBadge(String str);

    void updateBackgroundDrawable();

    void updateColor(float f);

    void updateScale(float f);

    void updateView();
}

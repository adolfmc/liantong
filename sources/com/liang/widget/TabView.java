package com.liang.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.p083v4.content.ContextCompat;
import android.support.p083v4.graphics.drawable.DrawableCompat;
import android.support.p083v4.view.ViewCompat;
import android.support.p086v7.content.res.AppCompatResources;
import android.support.p086v7.widget.TooltipCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.liang.jtablayout.badge.Badge;
import com.liang.jtablayout.ripple.RippleUtils;
import com.liang.jtablayout.tab.Tab;
import com.liang.jtablayout.utils.ColorUtils;
import com.liang.jtablayout.utils.MaterialResources;
import com.liang.jtablayout.utils.ViewUtils;
import com.liang.jtablayoutx.C5196R;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TabView extends FrameLayout implements Tab {
    private View badgeView;
    private Drawable baseBackgroundDrawable;
    private Drawable icon;
    private ImageView iconView;
    private boolean inline;
    private Drawable normalIcon;
    private Object object;
    private FrameLayout.LayoutParams params;
    private JTabLayout parent;
    private int position;
    private Drawable selectedIcon;
    private int tabBackgroundResId;
    private ColorStateList tabIconTint;
    private PorterDuff.Mode tabIconTintMode;
    private int tabPaddingBottom;
    private int tabPaddingEnd;
    private int tabPaddingStart;
    private int tabPaddingTop;
    private ColorStateList tabRippleColorStateList;
    private boolean tabTextBold;
    private float tabTitleSize;
    private View tabView;
    private TextView textView;
    private CharSequence title;
    private ColorStateList titleColor;
    private boolean unboundedRipple;

    @Override // com.liang.jtablayout.tab.Tab
    public View getView() {
        return this;
    }

    public TabView(@NonNull Context context) {
        this(context, null);
    }

    public TabView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TabView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.position = -1;
        this.tabTitleSize = 0.0f;
        this.params = new FrameLayout.LayoutParams(-2, -2);
        setFocusable(true);
        setClickable(true);
        initAttrs(context, attributeSet);
    }

    private void initAttrs(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5196R.styleable.TabItem, 0, 0);
        this.title = obtainStyledAttributes.getText(C5196R.styleable.TabItem_android_title);
        this.icon = obtainStyledAttributes.getDrawable(C5196R.styleable.TabItem_android_icon);
        this.tabTitleSize = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabItem_android_textSize, 0);
        this.tabIconTint = MaterialResources.getColorStateList(context, obtainStyledAttributes, C5196R.styleable.TabItem_android_icon);
        this.tabIconTintMode = ViewUtils.parseTintMode(obtainStyledAttributes.getInt(C5196R.styleable.TabItem_android_tintMode, -1), null);
        this.tabRippleColorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, C5196R.styleable.TabItem_rippleColor);
        this.unboundedRipple = obtainStyledAttributes.getBoolean(C5196R.styleable.TabItem_unboundedRipple, false);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabItem_android_padding, 0);
        this.tabPaddingBottom = dimensionPixelSize;
        this.tabPaddingEnd = dimensionPixelSize;
        this.tabPaddingTop = dimensionPixelSize;
        this.tabPaddingStart = dimensionPixelSize;
        this.tabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabItem_android_paddingStart, this.tabPaddingStart);
        this.tabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabItem_android_paddingTop, this.tabPaddingTop);
        this.tabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabItem_android_paddingEnd, this.tabPaddingEnd);
        this.tabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.TabItem_android_paddingBottom, this.tabPaddingBottom);
        this.tabBackgroundResId = obtainStyledAttributes.getResourceId(C5196R.styleable.TabItem_android_background, 0);
        if (obtainStyledAttributes.hasValue(C5196R.styleable.TabItem_android_textColor)) {
            this.titleColor = MaterialResources.getColorStateList(context, obtainStyledAttributes, C5196R.styleable.TabItem_android_textColor);
        }
        obtainStyledAttributes.recycle();
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void initTabView() {
        removeAllViews();
        this.tabView = setContentView();
        this.iconView = setTabIconView();
        this.textView = setTabTitleView();
        this.badgeView = setTabBadgeView();
        FrameLayout.LayoutParams layoutParams = this.params;
        layoutParams.gravity = 17;
        addView(this.tabView, layoutParams);
        updateBackgroundDrawable();
        updateView();
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Object getObject() {
        return this.object;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setObject(Object obj) {
        this.object = obj;
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public int getPosition() {
        return this.position;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setPosition(int i) {
        this.position = i;
        initTabView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public boolean getInline() {
        return this.inline;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setInlineLabel(boolean z) {
        this.inline = z;
        initTabView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public CharSequence getTitle() {
        return this.title;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTitle(CharSequence charSequence) {
        this.title = charSequence;
        updateView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTitle(@StringRes int i) {
        return setTitle(getContext().getString(i));
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Drawable getIcon() {
        return this.icon;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Drawable getNormalIcon() {
        return this.normalIcon;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Drawable getSelectedIcon() {
        return this.selectedIcon;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setIcon(int i) {
        return setIcon(ContextCompat.getDrawable(getContext(), i));
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setIcon(Drawable drawable) {
        this.icon = drawable;
        updateView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setIcon(int i, int i2) {
        return setIcon(ContextCompat.getDrawable(getContext(), i), ContextCompat.getDrawable(getContext(), i2));
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setIcon(Drawable drawable, Drawable drawable2) {
        this.normalIcon = drawable;
        this.selectedIcon = drawable2;
        updateView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public ColorStateList getTitleColor() {
        return this.titleColor;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTitleColor(int i, int i2) {
        return setTitleColor(ColorUtils.createColorStateList(i, i2));
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTitleColor(ColorStateList colorStateList) {
        this.titleColor = colorStateList;
        updateView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTabIconTint(int i, int i2) {
        return setTabIconTint(ColorUtils.createColorStateList(i, i2));
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTabIconTint(ColorStateList colorStateList) {
        this.tabIconTint = colorStateList;
        updateView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public PorterDuff.Mode getTabIconTintMode() {
        return this.tabIconTintMode;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTabIconTintMode(PorterDuff.Mode mode) {
        this.tabIconTintMode = mode;
        updateView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public int getTabBackgroundResId() {
        return this.tabBackgroundResId;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTabBackgroundResId(int i) {
        this.tabBackgroundResId = i;
        updateBackgroundDrawable();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public float getTabTextSize() {
        return this.tabTitleSize;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTabTextSize(float f) {
        this.tabTitleSize = f;
        updateView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public boolean isTabTextBold() {
        return this.tabTextBold;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTabTextBold(boolean z) {
        this.tabTextBold = z;
        updateView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void setContentDescription(int i) {
        setContentDescription(getContext().getString(i));
    }

    @Override // com.liang.jtablayout.tab.Tab
    public ColorStateList getTabRippleColorStateList() {
        return this.tabRippleColorStateList;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTabRippleColorStateList(ColorStateList colorStateList) {
        this.tabRippleColorStateList = colorStateList;
        updateBackgroundDrawable();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public boolean isUnboundedRipple() {
        return this.unboundedRipple;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setUnboundedRipple(boolean z) {
        this.unboundedRipple = z;
        updateBackgroundDrawable();
        return null;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTabPadding(int i, int i2, int i3, int i4) {
        this.tabPaddingStart = i;
        this.tabPaddingTop = i2;
        this.tabPaddingEnd = i3;
        this.tabPaddingBottom = i4;
        updateView();
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public int getTabPaddingStart() {
        return this.tabPaddingStart;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public int getTabPaddingTop() {
        return this.tabPaddingTop;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public int getTabPaddingEnd() {
        return this.tabPaddingEnd;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public int getTabPaddingBottom() {
        return this.tabPaddingBottom;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public JTabLayout getTabLayout() {
        return this.parent;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public Tab setTabLayout(JTabLayout jTabLayout) {
        this.parent = jTabLayout;
        return this;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void select() {
        JTabLayout jTabLayout = this.parent;
        if (jTabLayout == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        jTabLayout.selectTab(this);
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void updateView() {
        ViewCompat.setPaddingRelative(this, getTabPaddingStart(), getTabPaddingTop(), getTabPaddingEnd(), getTabPaddingBottom());
        if (this.textView != null) {
            if (getTitleColor() != null) {
                this.textView.setTextColor(getTitleColor());
            }
            if (getTabTextSize() > 0.0f) {
                this.textView.setTextSize(0, getTabTextSize());
            }
        }
        updateTextAndIcon(this.textView, this.iconView);
        if (!TextUtils.isEmpty(getContentDescription())) {
            setContentDescription(getContentDescription());
        }
        setSelected(isSelected());
    }

    private void updateTextAndIcon(TextView textView, ImageView imageView) {
        if (textView == null && imageView == null) {
            return;
        }
        Drawable normalIcon = getNormalIcon();
        Drawable selectedIcon = getSelectedIcon();
        Drawable mutate = getIcon() != null ? DrawableCompat.wrap(getIcon()).mutate() : null;
        if (imageView != null) {
            if (normalIcon == null && selectedIcon == null && mutate == null) {
                imageView.setVisibility(8);
                imageView.setImageDrawable(null);
            } else if (normalIcon != null || selectedIcon != null) {
                imageView.setVisibility(0);
                if (!imageView.isSelected() ? normalIcon == null : selectedIcon != null) {
                    normalIcon = selectedIcon;
                }
                imageView.setImageDrawable(normalIcon);
                setVisibility(0);
            } else {
                imageView.setVisibility(0);
                DrawableCompat.setTintList(mutate, getTabIconTint());
                if (getTabIconTintMode() != null) {
                    DrawableCompat.setTintMode(mutate, getTabIconTintMode());
                }
                imageView.setImageDrawable(mutate);
            }
        }
        CharSequence title = getTitle();
        boolean z = !TextUtils.isEmpty(title);
        if (textView != null) {
            if (z) {
                textView.setText(title);
                textView.setVisibility(0);
                setVisibility(0);
            } else {
                textView.setVisibility(8);
                textView.setText((CharSequence) null);
            }
            if (isTabTextBold()) {
                textView.setTypeface(Typeface.defaultFromStyle(isSelected() ? 1 : 0));
            }
        }
        CharSequence contentDescription = getContentDescription();
        if (z) {
            contentDescription = null;
        }
        TooltipCompat.setTooltipText(this, contentDescription);
    }

    protected View setContentView() {
        return LayoutInflater.from(getContext()).inflate(getInline() ? C5196R.C5200layout.tab_item_horizontal : C5196R.C5200layout.tab_item_vertical, (ViewGroup) null);
    }

    protected TextView setTabTitleView() {
        return (TextView) this.tabView.findViewById(C5196R.C5199id.tab_title);
    }

    protected ImageView setTabIconView() {
        return (ImageView) this.tabView.findViewById(C5196R.C5199id.tab_icon);
    }

    protected BadgeView setTabBadgeView() {
        return (BadgeView) this.tabView.findViewById(C5196R.C5199id.tab_badgeView);
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void updateBackgroundDrawable() {
        if (getTabBackgroundResId() != 0) {
            this.baseBackgroundDrawable = AppCompatResources.getDrawable(getContext(), getTabBackgroundResId());
            Drawable drawable = this.baseBackgroundDrawable;
            if (drawable != null && drawable.isStateful()) {
                this.baseBackgroundDrawable.setState(getDrawableState());
            }
        } else {
            this.baseBackgroundDrawable = null;
        }
        Drawable gradientDrawable = new GradientDrawable();
        ((GradientDrawable) gradientDrawable).setColor(0);
        if (getTabRippleColorStateList() != null) {
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setCornerRadius(1.0E-5f);
            gradientDrawable2.setColor(-1);
            ColorStateList convertToRippleDrawableColor = RippleUtils.convertToRippleDrawableColor(getTabRippleColorStateList());
            if (Build.VERSION.SDK_INT >= 21) {
                if (isUnboundedRipple()) {
                    gradientDrawable = null;
                }
                gradientDrawable = new RippleDrawable(convertToRippleDrawableColor, gradientDrawable, isUnboundedRipple() ? null : gradientDrawable2);
            } else {
                Drawable wrap = DrawableCompat.wrap(gradientDrawable2);
                DrawableCompat.setTintList(wrap, convertToRippleDrawableColor);
                gradientDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, wrap});
            }
        }
        ViewCompat.setBackground(this, gradientDrawable);
        if (getTabLayout() != null) {
            getTabLayout().postInvalidate();
        }
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void drawBackground(Canvas canvas) {
        Drawable drawable = this.baseBackgroundDrawable;
        if (drawable != null) {
            drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
            this.baseBackgroundDrawable.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.baseBackgroundDrawable;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = false | this.baseBackgroundDrawable.setState(drawableState);
        }
        if (z) {
            invalidate();
            if (getTabLayout() != null) {
                getTabLayout().invalidate();
            }
        }
    }

    @Override // android.view.View
    public boolean performClick() {
        if (!super.performClick()) {
            playSoundEffect(0);
        }
        select();
        return true;
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        boolean z2 = isSelected() != z;
        super.setSelected(z);
        if (z2 && z && Build.VERSION.SDK_INT < 16) {
            sendAccessibilityEvent(4);
        }
        if (this.iconView != null) {
            Drawable normalIcon = getNormalIcon();
            Drawable selectedIcon = getSelectedIcon();
            Drawable mutate = getIcon() != null ? DrawableCompat.wrap(getIcon()).mutate() : null;
            this.iconView.setSelected(z);
            if (normalIcon == null && selectedIcon == null && mutate == null) {
                this.iconView.setVisibility(8);
            } else {
                this.iconView.setVisibility(0);
                if (normalIcon != null || selectedIcon != null) {
                    ImageView imageView = this.iconView;
                    if (!z ? normalIcon == null : selectedIcon != null) {
                        normalIcon = selectedIcon;
                    }
                    imageView.setImageDrawable(normalIcon);
                }
            }
        }
        TextView textView = this.textView;
        if (textView != null) {
            textView.setSelected(z);
            if (isTabTextBold()) {
                this.textView.setTypeface(Typeface.defaultFromStyle(isSelected() ? 1 : 0));
            }
        }
    }

    @Override // com.liang.jtablayout.tab.Tab
    public int getContentWidth() {
        View[] viewArr;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        for (View view : new View[]{this.textView, this.iconView}) {
            if (view != null && view.getVisibility() == 0) {
                i2 = z ? Math.min(i2, view.getLeft()) : view.getLeft();
                i = z ? Math.max(i, view.getRight()) : view.getRight();
                z = true;
            }
        }
        return i - i2;
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void showBadge(String str) {
        View view = this.badgeView;
        if (view == null || !(view instanceof BadgeView)) {
            return;
        }
        ((BadgeView) view).show(str);
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void hideBadge() {
        View view = this.badgeView;
        if (view == null || !(view instanceof BadgeView)) {
            return;
        }
        ((BadgeView) view).hide();
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void updateColor(float f) {
        this.textView.setTextColor(ColorUtils.getColorFrom(getTitleColor().getColorForState(EMPTY_STATE_SET, -7829368), getTitleColor().getColorForState(SELECTED_STATE_SET, -7829368), f));
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void updateScale(float f) {
        setScaleX(f);
        setScaleY(f);
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void setBadgeTextColor(int i) {
        View view = this.badgeView;
        if (view == null || !(view instanceof Badge)) {
            return;
        }
        ((Badge) view).setBadgeTextColor(i);
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void setBadgeTextSize(float f) {
        View view = this.badgeView;
        if (view == null || !(view instanceof Badge)) {
            return;
        }
        ((Badge) view).setBadgeTextSize(f);
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void setBadgeBackgroundColor(int i) {
        View view = this.badgeView;
        if (view == null || !(view instanceof Badge)) {
            return;
        }
        ((Badge) view).setBadgeBackgroundColor(i);
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void setBadgeStroke(int i, int i2) {
        View view = this.badgeView;
        if (view == null || !(view instanceof Badge)) {
            return;
        }
        ((Badge) view).setBadgeStroke(i, i2);
    }

    @Override // com.liang.jtablayout.tab.Tab
    public void reset() {
        this.parent = null;
        this.object = null;
        setTag(null);
        this.icon = null;
        this.normalIcon = null;
        this.selectedIcon = null;
        this.title = null;
        this.position = -1;
        this.titleColor = null;
        this.tabIconTintMode = null;
        this.tabIconTint = null;
        this.tabTitleSize = 0.0f;
        this.tabRippleColorStateList = null;
        this.tabBackgroundResId = 0;
        this.tabPaddingStart = 0;
        this.tabPaddingTop = 0;
        this.tabPaddingEnd = 0;
        this.tabPaddingBottom = 0;
        setSelected(false);
        updateScale(1.0f);
    }
}

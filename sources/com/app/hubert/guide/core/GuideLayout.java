package com.app.hubert.guide.core;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.app.hubert.guide.listener.AnimationListenerAdapter;
import com.app.hubert.guide.listener.OnLayoutInflatedListener;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.model.HighLight;
import com.app.hubert.guide.model.HighlightOptions;
import com.app.hubert.guide.model.RelativeGuide;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import java.util.List;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class GuideLayout extends FrameLayout {
    public static final int DEFAULT_BACKGROUND_COLOR = -1308622848;
    private Controller controller;
    private float downX;
    private float downY;
    public GuidePage guidePage;
    private OnGuideLayoutDismissListener listener;
    private Paint mPaint;
    private int touchSlop;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnGuideLayoutDismissListener {
        void onGuideLayoutDismiss(GuideLayout guideLayout);
    }

    public GuideLayout(Context context, GuidePage guidePage, Controller controller) {
        super(context);
        init();
        setGuidePage(guidePage);
        this.controller = controller;
    }

    private GuideLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private GuideLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void init() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.mPaint.setMaskFilter(new BlurMaskFilter(10.0f, BlurMaskFilter.Blur.INNER));
        setLayerType(1, null);
        setWillNotDraw(false);
        this.touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    private void setGuidePage(GuidePage guidePage) {
        this.guidePage = guidePage;
        setOnClickListener(new View.OnClickListener() { // from class: com.app.hubert.guide.core.GuideLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (GuideLayout.this.guidePage.isEverywhereCancelable()) {
                    GuideLayout.this.remove();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.downX = motionEvent.getX();
                this.downY = motionEvent.getY();
                break;
            case 1:
            case 3:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (Math.abs(x - this.downX) < this.touchSlop && Math.abs(y - this.downY) < this.touchSlop) {
                    for (HighLight highLight : this.guidePage.getHighLights()) {
                        if (highLight.getRectF((ViewGroup) getParent()).contains(x, y)) {
                            notifyClickListener(highLight);
                            return true;
                        }
                    }
                    performClick();
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void notifyClickListener(HighLight highLight) {
        HighlightOptions options = highLight.getOptions();
        if (options == null || options.onClickListener == null) {
            return;
        }
        options.onClickListener.onClick(this);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int backgroundColor = this.guidePage.getBackgroundColor();
        if (backgroundColor == 0) {
            backgroundColor = DEFAULT_BACKGROUND_COLOR;
        }
        canvas.drawColor(backgroundColor);
        drawHighlights(canvas);
    }

    private void drawHighlights(Canvas canvas) {
        List<HighLight> highLights = this.guidePage.getHighLights();
        if (highLights != null) {
            for (HighLight highLight : highLights) {
                RectF rectF = highLight.getRectF((ViewGroup) getParent());
                switch (highLight.getShape()) {
                    case CIRCLE:
                        canvas.drawCircle(rectF.centerX(), rectF.centerY(), highLight.getRadius(), this.mPaint);
                        break;
                    case OVAL:
                        canvas.drawOval(rectF, this.mPaint);
                        break;
                    case ROUND_RECTANGLE:
                        canvas.drawRoundRect(rectF, highLight.getRound(), highLight.getRound(), this.mPaint);
                        break;
                    default:
                        canvas.drawRect(rectF, this.mPaint);
                        break;
                }
                notifyDrewListener(canvas, highLight, rectF);
            }
        }
    }

    private void notifyDrewListener(Canvas canvas, HighLight highLight, RectF rectF) {
        HighlightOptions options = highLight.getOptions();
        if (options == null || options.onHighlightDrewListener == null) {
            return;
        }
        options.onHighlightDrewListener.onHighlightDrew(canvas, rectF);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addCustomToLayout(this.guidePage);
        Animation enterAnimation = this.guidePage.getEnterAnimation();
        if (enterAnimation != null) {
            startAnimation(enterAnimation);
        }
    }

    private void addCustomToLayout(GuidePage guidePage) {
        removeAllViews();
        int layoutResId = guidePage.getLayoutResId();
        if (layoutResId != 0) {
            View inflate = LayoutInflater.from(getContext()).inflate(layoutResId, (ViewGroup) this, false);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            int[] clickToDismissIds = guidePage.getClickToDismissIds();
            if (clickToDismissIds != null && clickToDismissIds.length > 0) {
                for (int i : clickToDismissIds) {
                    View findViewById = inflate.findViewById(i);
                    if (findViewById != null) {
                        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.app.hubert.guide.core.GuideLayout.2
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                NBSActionInstrumentation.onClickEventEnter(view, this);
                                Tracker.onClick(view);
                                GuideLayout.this.remove();
                                NBSActionInstrumentation.onClickEventExit();
                            }
                        });
                    } else {
                        Log.w("NewbieGuide", "can't find the view by id : " + i + " which used to remove guide page");
                    }
                }
            }
            OnLayoutInflatedListener onLayoutInflatedListener = guidePage.getOnLayoutInflatedListener();
            if (onLayoutInflatedListener != null) {
                onLayoutInflatedListener.onLayoutInflated(inflate, this.controller);
            }
            addView(inflate, layoutParams);
        }
        List<RelativeGuide> relativeGuides = guidePage.getRelativeGuides();
        if (relativeGuides.size() > 0) {
            for (RelativeGuide relativeGuide : relativeGuides) {
                addView(relativeGuide.getGuideLayout((ViewGroup) getParent(), this.controller));
            }
        }
    }

    public void setOnGuideLayoutDismissListener(OnGuideLayoutDismissListener onGuideLayoutDismissListener) {
        this.listener = onGuideLayoutDismissListener;
    }

    public void remove() {
        Animation exitAnimation = this.guidePage.getExitAnimation();
        if (exitAnimation != null) {
            exitAnimation.setAnimationListener(new AnimationListenerAdapter() { // from class: com.app.hubert.guide.core.GuideLayout.3
                @Override // com.app.hubert.guide.listener.AnimationListenerAdapter, android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    GuideLayout.this.dismiss();
                }
            });
            startAnimation(exitAnimation);
            return;
        }
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismiss() {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
            OnGuideLayoutDismissListener onGuideLayoutDismissListener = this.listener;
            if (onGuideLayoutDismissListener != null) {
                onGuideLayoutDismissListener.onGuideLayoutDismiss(this);
            }
        }
    }
}

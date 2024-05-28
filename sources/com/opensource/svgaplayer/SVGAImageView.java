package com.opensource.svgaplayer;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.example.asus.detectionandalign.DetectionAuthentic;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.utils.SVGARange;
import com.opensource.svgaplayer.utils.log.LogUtils;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SVGAImageView.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001:\u0003]^_B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u00103\u001a\u000204J\u0016\u00105\u001a\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000008H\u0002J\b\u00109\u001a\u00020:H\u0002J\n\u0010;\u001a\u0004\u0018\u00010<H\u0002J\u0010\u0010=\u001a\u0002042\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0012\u0010>\u001a\u0002042\b\u0010?\u001a\u0004\u0018\u00010@H\u0002J\u0012\u0010A\u001a\u0002042\b\u0010B\u001a\u0004\u0018\u00010(H\u0002J\b\u0010C\u001a\u000204H\u0014J\u0012\u0010D\u001a\u00020\u00122\b\u0010E\u001a\u0004\u0018\u00010FH\u0017J\u0010\u0010G\u001a\u0002042\u0006\u0010H\u001a\u00020\nH\u0002J\u0006\u0010I\u001a\u000204J\u001a\u0010J\u001a\u0002042\b\u0010K\u001a\u0004\u0018\u00010L2\u0006\u0010M\u001a\u00020\u0012H\u0002J\u000e\u0010N\u001a\u0002042\u0006\u0010O\u001a\u000201J\u0010\u0010P\u001a\u0002042\b\u0010Q\u001a\u0004\u0018\u00010RJ\u001a\u0010P\u001a\u0002042\b\u0010Q\u001a\u0004\u0018\u00010R2\b\u0010S\u001a\u0004\u0018\u00010TJ\b\u0010U\u001a\u000204H\u0002J\u0006\u0010V\u001a\u000204J\u0010\u0010V\u001a\u0002042\u0006\u0010Q\u001a\u00020RH\u0002J\u001a\u0010V\u001a\u0002042\b\u0010K\u001a\u0004\u0018\u00010L2\b\b\u0002\u0010M\u001a\u00020\u0012J\u0016\u0010W\u001a\u0002042\u0006\u0010X\u001a\u00020\u00072\u0006\u0010Y\u001a\u00020\u0012J\u0016\u0010Z\u001a\u0002042\u0006\u0010[\u001a\u00020:2\u0006\u0010Y\u001a\u00020\u0012J\u0006\u0010\\\u001a\u000204J\u000e\u0010\\\u001a\u0002042\u0006\u00103\u001a\u00020\u0012R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010!\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u001a\u0010\"\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006`"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGAImageView;", "Landroid/widget/ImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "callback", "Lcom/opensource/svgaplayer/SVGACallback;", "getCallback", "()Lcom/opensource/svgaplayer/SVGACallback;", "setCallback", "(Lcom/opensource/svgaplayer/SVGACallback;)V", "clearsAfterDetached", "", "getClearsAfterDetached", "()Z", "setClearsAfterDetached", "(Z)V", "clearsAfterStop", "getClearsAfterStop", "setClearsAfterStop", "fillMode", "Lcom/opensource/svgaplayer/SVGAImageView$FillMode;", "getFillMode", "()Lcom/opensource/svgaplayer/SVGAImageView$FillMode;", "setFillMode", "(Lcom/opensource/svgaplayer/SVGAImageView$FillMode;)V", "<set-?>", "isAnimating", "loops", "getLoops", "()I", "setLoops", "(I)V", "mAnimator", "Landroid/animation/ValueAnimator;", "mAnimatorListener", "Lcom/opensource/svgaplayer/SVGAImageView$AnimatorListener;", "mAnimatorUpdateListener", "Lcom/opensource/svgaplayer/SVGAImageView$AnimatorUpdateListener;", "mAntiAlias", "mAutoPlay", "mEndFrame", "mItemClickAreaListener", "Lcom/opensource/svgaplayer/SVGAClickAreaListener;", "mStartFrame", "clear", "", "createParseCompletion", "Lcom/opensource/svgaplayer/SVGAParser$ParseCompletion;", "ref", "Ljava/lang/ref/WeakReference;", "generateScale", "", "getSVGADrawable", "Lcom/opensource/svgaplayer/SVGADrawable;", "loadAttrs", "onAnimationEnd", "animation", "Landroid/animation/Animator;", "onAnimatorUpdate", "animator", "onDetachedFromWindow", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "parserSource", "source", "pauseAnimation", "play", "range", "Lcom/opensource/svgaplayer/utils/SVGARange;", "reverse", "setOnAnimKeyClickListener", "clickListener", "setVideoItem", "videoItem", "Lcom/opensource/svgaplayer/SVGAVideoEntity;", "dynamicItem", "Lcom/opensource/svgaplayer/SVGADynamicEntity;", "setupDrawable", "startAnimation", "stepToFrame", DetectionAuthentic.FRAME, "andPlay", "stepToPercentage", "percentage", "stopAnimation", "AnimatorListener", "AnimatorUpdateListener", "FillMode", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SVGAImageView extends ImageView {
    private final String TAG;
    private HashMap _$_findViewCache;
    @Nullable
    private SVGACallback callback;
    private boolean clearsAfterDetached;
    private boolean clearsAfterStop;
    @NotNull
    private FillMode fillMode;
    private boolean isAnimating;
    private int loops;
    private ValueAnimator mAnimator;
    private final AnimatorListener mAnimatorListener;
    private final AnimatorUpdateListener mAnimatorUpdateListener;
    private boolean mAntiAlias;
    private boolean mAutoPlay;
    private int mEndFrame;
    private SVGAClickAreaListener mItemClickAreaListener;
    private int mStartFrame;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: SVGAImageView.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGAImageView$FillMode;", "", "(Ljava/lang/String;I)V", "Backward", "Forward", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum FillMode {
        Backward,
        Forward
    }

    @JvmOverloads
    public SVGAImageView(@NotNull Context context) {
        this(context, null, 0, 6, null);
    }

    @JvmOverloads
    public SVGAImageView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public /* synthetic */ SVGAImageView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SVGAImageView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "SVGAImageView";
        this.clearsAfterStop = true;
        this.clearsAfterDetached = true;
        this.fillMode = FillMode.Forward;
        this.mAntiAlias = true;
        this.mAutoPlay = true;
        this.mAnimatorListener = new AnimatorListener(this);
        this.mAnimatorUpdateListener = new AnimatorUpdateListener(this);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
        if (attributeSet != null) {
            loadAttrs(attributeSet);
        }
    }

    public final boolean isAnimating() {
        return this.isAnimating;
    }

    public final int getLoops() {
        return this.loops;
    }

    public final void setLoops(int i) {
        this.loops = i;
    }

    public final boolean getClearsAfterStop() {
        return this.clearsAfterStop;
    }

    public final void setClearsAfterStop(boolean z) {
        this.clearsAfterStop = z;
    }

    public final boolean getClearsAfterDetached() {
        return this.clearsAfterDetached;
    }

    public final void setClearsAfterDetached(boolean z) {
        this.clearsAfterDetached = z;
    }

    @NotNull
    public final FillMode getFillMode() {
        return this.fillMode;
    }

    public final void setFillMode(@NotNull FillMode fillMode) {
        Intrinsics.checkParameterIsNotNull(fillMode, "<set-?>");
        this.fillMode = fillMode;
    }

    @Nullable
    public final SVGACallback getCallback() {
        return this.callback;
    }

    public final void setCallback(@Nullable SVGACallback sVGACallback) {
        this.callback = sVGACallback;
    }

    private final void loadAttrs(AttributeSet attributeSet) {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C6848R.styleable.SVGAImageView, 0, 0);
        this.loops = obtainStyledAttributes.getInt(C6848R.styleable.SVGAImageView_loopCount, 0);
        this.clearsAfterStop = obtainStyledAttributes.getBoolean(C6848R.styleable.SVGAImageView_clearsAfterStop, true);
        this.mAntiAlias = obtainStyledAttributes.getBoolean(C6848R.styleable.SVGAImageView_antiAlias, true);
        this.mAutoPlay = obtainStyledAttributes.getBoolean(C6848R.styleable.SVGAImageView_autoPlay, true);
        String string = obtainStyledAttributes.getString(C6848R.styleable.SVGAImageView_fillMode);
        if (string != null) {
            if (Intrinsics.areEqual(string, "0")) {
                this.fillMode = FillMode.Backward;
            } else if (Intrinsics.areEqual(string, "1")) {
                this.fillMode = FillMode.Forward;
            }
        }
        String string2 = obtainStyledAttributes.getString(C6848R.styleable.SVGAImageView_source);
        if (string2 != null) {
            parserSource(string2);
        }
        obtainStyledAttributes.recycle();
    }

    private final void parserSource(String str) {
        WeakReference<SVGAImageView> weakReference = new WeakReference<>(this);
        SVGAParser sVGAParser = new SVGAParser(getContext());
        if (StringsKt.startsWith$default(str, "http://", false, 2, (Object) null) || StringsKt.startsWith$default(str, "https://", false, 2, (Object) null)) {
            sVGAParser.decodeFromURL(new URL(str), createParseCompletion(weakReference));
        } else {
            sVGAParser.decodeFromAssets(str, createParseCompletion(weakReference));
        }
    }

    private final SVGAParser.ParseCompletion createParseCompletion(final WeakReference<SVGAImageView> weakReference) {
        return new SVGAParser.ParseCompletion() { // from class: com.opensource.svgaplayer.SVGAImageView$createParseCompletion$1
            @Override // com.opensource.svgaplayer.SVGAParser.ParseCompletion
            public void onError() {
            }

            @Override // com.opensource.svgaplayer.SVGAParser.ParseCompletion
            public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                Intrinsics.checkParameterIsNotNull(videoItem, "videoItem");
                SVGAImageView sVGAImageView = (SVGAImageView) weakReference.get();
                if (sVGAImageView != null) {
                    sVGAImageView.startAnimation(videoItem);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startAnimation(final SVGAVideoEntity sVGAVideoEntity) {
        post(new Runnable() { // from class: com.opensource.svgaplayer.SVGAImageView$startAnimation$1
            @Override // java.lang.Runnable
            public final void run() {
                boolean z;
                SVGADrawable sVGADrawable;
                boolean z2;
                SVGAVideoEntity sVGAVideoEntity2 = sVGAVideoEntity;
                z = SVGAImageView.this.mAntiAlias;
                sVGAVideoEntity2.setAntiAlias(z);
                SVGAImageView.this.setVideoItem(sVGAVideoEntity);
                sVGADrawable = SVGAImageView.this.getSVGADrawable();
                if (sVGADrawable != null) {
                    ImageView.ScaleType scaleType = SVGAImageView.this.getScaleType();
                    Intrinsics.checkExpressionValueIsNotNull(scaleType, "scaleType");
                    sVGADrawable.setScaleType(scaleType);
                }
                z2 = SVGAImageView.this.mAutoPlay;
                if (z2) {
                    SVGAImageView.this.startAnimation();
                }
            }
        });
    }

    public final void startAnimation() {
        startAnimation(null, false);
    }

    public static /* synthetic */ void startAnimation$default(SVGAImageView sVGAImageView, SVGARange sVGARange, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startAnimation");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        sVGAImageView.startAnimation(sVGARange, z);
    }

    public final void startAnimation(@Nullable SVGARange sVGARange, boolean z) {
        stopAnimation(false);
        play(sVGARange, z);
    }

    private final void play(SVGARange sVGARange, boolean z) {
        LogUtils.INSTANCE.info(this.TAG, "================ start animation ================");
        SVGADrawable sVGADrawable = getSVGADrawable();
        if (sVGADrawable != null) {
            setupDrawable();
            this.mStartFrame = Math.max(0, sVGARange != null ? sVGARange.getLocation() : 0);
            SVGAVideoEntity videoItem = sVGADrawable.getVideoItem();
            this.mEndFrame = Math.min(videoItem.getFrames() - 1, ((sVGARange != null ? sVGARange.getLocation() : 0) + (sVGARange != null ? sVGARange.getLength() : Integer.MAX_VALUE)) - 1);
            ValueAnimator animator = ValueAnimator.ofInt(this.mStartFrame, this.mEndFrame);
            Intrinsics.checkExpressionValueIsNotNull(animator, "animator");
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration((long) ((((this.mEndFrame - this.mStartFrame) + 1) * (1000 / videoItem.getFPS())) / generateScale()));
            int i = this.loops;
            animator.setRepeatCount(i <= 0 ? 99999 : i - 1);
            animator.addUpdateListener(this.mAnimatorUpdateListener);
            animator.addListener(this.mAnimatorListener);
            if (z) {
                animator.reverse();
            } else {
                animator.start();
            }
            this.mAnimator = animator;
        }
    }

    private final void setupDrawable() {
        SVGADrawable sVGADrawable = getSVGADrawable();
        if (sVGADrawable != null) {
            sVGADrawable.setCleared$com_opensource_svgaplayer(false);
            ImageView.ScaleType scaleType = getScaleType();
            Intrinsics.checkExpressionValueIsNotNull(scaleType, "scaleType");
            sVGADrawable.setScaleType(scaleType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SVGADrawable getSVGADrawable() {
        Drawable drawable = getDrawable();
        if (!(drawable instanceof SVGADrawable)) {
            drawable = null;
        }
        return (SVGADrawable) drawable;
    }

    private final double generateScale() {
        Method declaredMethod;
        double d = 1.0d;
        try {
            Class<?> cls = Class.forName("android.animation.ValueAnimator");
            if (cls == null || (declaredMethod = cls.getDeclaredMethod("getDurationScale", new Class[0])) == null) {
                return 1.0d;
            }
            Object invoke = declaredMethod.invoke(cls, new Object[0]);
            if (invoke != null) {
                double floatValue = ((Float) invoke).floatValue();
                if (floatValue == 0.0d) {
                    try {
                        Method declaredMethod2 = cls.getDeclaredMethod("setDurationScale", Float.TYPE);
                        if (declaredMethod2 != null) {
                            declaredMethod2.setAccessible(true);
                            declaredMethod2.invoke(cls, Float.valueOf(1.0f));
                            LogUtils.INSTANCE.info(this.TAG, "The animation duration scale has been reset to 1.0x, because you closed it on developer options.");
                            return 1.0d;
                        }
                        return floatValue;
                    } catch (Exception e) {
                        e = e;
                        d = floatValue;
                        e.printStackTrace();
                        return d;
                    }
                }
                return floatValue;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAnimatorUpdate(ValueAnimator valueAnimator) {
        SVGADrawable sVGADrawable = getSVGADrawable();
        if (sVGADrawable != null) {
            Object animatedValue = valueAnimator != null ? valueAnimator.getAnimatedValue() : null;
            if (animatedValue == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            sVGADrawable.setCurrentFrame$com_opensource_svgaplayer(((Integer) animatedValue).intValue());
            double currentFrame = (sVGADrawable.getCurrentFrame() + 1) / sVGADrawable.getVideoItem().getFrames();
            SVGACallback sVGACallback = this.callback;
            if (sVGACallback != null) {
                sVGACallback.onStep(sVGADrawable.getCurrentFrame(), currentFrame);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAnimationEnd(Animator animator) {
        this.isAnimating = false;
        stopAnimation();
        SVGADrawable sVGADrawable = getSVGADrawable();
        if (!this.clearsAfterStop && sVGADrawable != null) {
            if (this.fillMode == FillMode.Backward) {
                sVGADrawable.setCurrentFrame$com_opensource_svgaplayer(this.mStartFrame);
            } else if (this.fillMode == FillMode.Forward) {
                sVGADrawable.setCurrentFrame$com_opensource_svgaplayer(this.mEndFrame);
            }
        }
        if (this.clearsAfterStop) {
            if (animator == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.animation.ValueAnimator");
            }
            if (((ValueAnimator) animator).getRepeatCount() <= 0) {
                clear();
            }
        }
        SVGACallback sVGACallback = this.callback;
        if (sVGACallback != null) {
            sVGACallback.onFinished();
        }
    }

    public final void clear() {
        SVGADrawable sVGADrawable = getSVGADrawable();
        if (sVGADrawable != null) {
            sVGADrawable.setCleared$com_opensource_svgaplayer(true);
        }
        SVGADrawable sVGADrawable2 = getSVGADrawable();
        if (sVGADrawable2 != null) {
            sVGADrawable2.clear();
        }
        setImageDrawable(null);
    }

    public final void pauseAnimation() {
        stopAnimation(false);
        SVGACallback sVGACallback = this.callback;
        if (sVGACallback != null) {
            sVGACallback.onPause();
        }
    }

    public final void stopAnimation() {
        stopAnimation(this.clearsAfterStop);
    }

    public final void stopAnimation(boolean z) {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.mAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.removeAllListeners();
        }
        ValueAnimator valueAnimator3 = this.mAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.removeAllUpdateListeners();
        }
        SVGADrawable sVGADrawable = getSVGADrawable();
        if (sVGADrawable != null) {
            sVGADrawable.setCleared$com_opensource_svgaplayer(z);
        }
    }

    public final void setVideoItem(@Nullable SVGAVideoEntity sVGAVideoEntity) {
        setVideoItem(sVGAVideoEntity, new SVGADynamicEntity());
    }

    public final void setVideoItem(@Nullable SVGAVideoEntity sVGAVideoEntity, @Nullable SVGADynamicEntity sVGADynamicEntity) {
        if (sVGAVideoEntity == null) {
            setImageDrawable(null);
            return;
        }
        if (sVGADynamicEntity == null) {
            sVGADynamicEntity = new SVGADynamicEntity();
        }
        SVGADrawable sVGADrawable = new SVGADrawable(sVGAVideoEntity, sVGADynamicEntity);
        sVGADrawable.setCleared$com_opensource_svgaplayer(this.clearsAfterStop);
        setImageDrawable(sVGADrawable);
    }

    public final void stepToFrame(int i, boolean z) {
        pauseAnimation();
        SVGADrawable sVGADrawable = getSVGADrawable();
        if (sVGADrawable != null) {
            sVGADrawable.setCurrentFrame$com_opensource_svgaplayer(i);
            if (z) {
                startAnimation();
                ValueAnimator valueAnimator = this.mAnimator;
                if (valueAnimator != null) {
                    valueAnimator.setCurrentPlayTime(Math.max(0.0f, Math.min(1.0f, i / sVGADrawable.getVideoItem().getFrames())) * ((float) valueAnimator.getDuration()));
                }
            }
        }
    }

    public final void stepToPercentage(double d, boolean z) {
        Drawable drawable = getDrawable();
        if (!(drawable instanceof SVGADrawable)) {
            drawable = null;
        }
        SVGADrawable sVGADrawable = (SVGADrawable) drawable;
        if (sVGADrawable != null) {
            int frames = (int) (sVGADrawable.getVideoItem().getFrames() * d);
            if (frames >= sVGADrawable.getVideoItem().getFrames() && frames > 0) {
                frames = sVGADrawable.getVideoItem().getFrames() - 1;
            }
            stepToFrame(frames, z);
        }
    }

    public final void setOnAnimKeyClickListener(@NotNull SVGAClickAreaListener clickListener) {
        Intrinsics.checkParameterIsNotNull(clickListener, "clickListener");
        this.mItemClickAreaListener = clickListener;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(@Nullable MotionEvent motionEvent) {
        SVGAClickAreaListener sVGAClickAreaListener;
        if (motionEvent == null || motionEvent.getAction() != 0) {
            return super.onTouchEvent(motionEvent);
        }
        SVGADrawable sVGADrawable = getSVGADrawable();
        if (sVGADrawable != null) {
            for (Map.Entry<String, int[]> entry : sVGADrawable.getDynamicItem().getMClickMap$com_opensource_svgaplayer().entrySet()) {
                String key = entry.getKey();
                int[] value = entry.getValue();
                if (motionEvent.getX() >= value[0] && motionEvent.getX() <= value[2] && motionEvent.getY() >= value[1] && motionEvent.getY() <= value[3] && (sVGAClickAreaListener = this.mItemClickAreaListener) != null) {
                    sVGAClickAreaListener.onClick(key);
                    return true;
                }
            }
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation(true);
        if (this.clearsAfterDetached) {
            clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SVGAImageView.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\f\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\r\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGAImageView$AnimatorListener;", "Landroid/animation/Animator$AnimatorListener;", "view", "Lcom/opensource/svgaplayer/SVGAImageView;", "(Lcom/opensource/svgaplayer/SVGAImageView;)V", "weakReference", "Ljava/lang/ref/WeakReference;", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class AnimatorListener implements Animator.AnimatorListener {
        private final WeakReference<SVGAImageView> weakReference;

        public AnimatorListener(@NotNull SVGAImageView view) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.weakReference = new WeakReference<>(view);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(@Nullable Animator animator) {
            SVGACallback callback;
            SVGAImageView sVGAImageView = this.weakReference.get();
            if (sVGAImageView == null || (callback = sVGAImageView.getCallback()) == null) {
                return;
            }
            callback.onRepeat();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(@Nullable Animator animator) {
            SVGAImageView sVGAImageView = this.weakReference.get();
            if (sVGAImageView != null) {
                sVGAImageView.onAnimationEnd(animator);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(@Nullable Animator animator) {
            SVGAImageView sVGAImageView = this.weakReference.get();
            if (sVGAImageView != null) {
                sVGAImageView.isAnimating = false;
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(@Nullable Animator animator) {
            SVGAImageView sVGAImageView = this.weakReference.get();
            if (sVGAImageView != null) {
                sVGAImageView.isAnimating = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SVGAImageView.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGAImageView$AnimatorUpdateListener;", "Landroid/animation/ValueAnimator$AnimatorUpdateListener;", "view", "Lcom/opensource/svgaplayer/SVGAImageView;", "(Lcom/opensource/svgaplayer/SVGAImageView;)V", "weakReference", "Ljava/lang/ref/WeakReference;", "onAnimationUpdate", "", "animation", "Landroid/animation/ValueAnimator;", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class AnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        private final WeakReference<SVGAImageView> weakReference;

        public AnimatorUpdateListener(@NotNull SVGAImageView view) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.weakReference = new WeakReference<>(view);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@Nullable ValueAnimator valueAnimator) {
            SVGAImageView sVGAImageView = this.weakReference.get();
            if (sVGAImageView != null) {
                sVGAImageView.onAnimatorUpdate(valueAnimator);
            }
        }
    }
}

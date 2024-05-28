package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.util.ThreadUtil;
import com.sinovatech.unicom.separatemodule.audience.view.heart.HeartHonorLayout;
import java.lang.ref.SoftReference;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import org.limlee.hipraiseanimationlib.HiPraiseAnimationView;
import org.limlee.hipraiseanimationlib.HiPraiseWithCallback;
import org.limlee.hipraiseanimationlib.OnDrawCallback;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AlivcLikeView extends RelativeLayout {
    private static final int[] HEARDS = {2131230970, 2131230971, 2131230972, 2131230973, 2131230974, 2131230975, 2131230976, 2131230977};
    private final String TAG;
    private CountDownTimer countDownTimer;
    private ExecutorService executorService;
    private HeartHonorLayout heartHonorLayout;
    private SparseArray<SoftReference<Bitmap>> mBitmapCacheArray;
    private HiPraiseAnimationView mHiPraiseAnimationView;
    private Random mRandom;
    OnLikeClickListener onLikeClickListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnLikeClickListener {
        void onLike();
    }

    public AlivcLikeView(Context context) {
        super(context);
        this.TAG = AlivcLikeView.class.getName().toString();
        this.mBitmapCacheArray = new SparseArray<>();
        this.executorService = ThreadUtil.newDynamicSingleThreadedExecutor();
        this.mRandom = new Random();
        initView();
    }

    public AlivcLikeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = AlivcLikeView.class.getName().toString();
        this.mBitmapCacheArray = new SparseArray<>();
        this.executorService = ThreadUtil.newDynamicSingleThreadedExecutor();
        this.mRandom = new Random();
        initView();
    }

    public AlivcLikeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = AlivcLikeView.class.getName().toString();
        this.mBitmapCacheArray = new SparseArray<>();
        this.executorService = ThreadUtil.newDynamicSingleThreadedExecutor();
        this.mRandom = new Random();
        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext().getApplicationContext()).inflate(2131492972, (ViewGroup) this, true);
        this.mHiPraiseAnimationView = (HiPraiseAnimationView) inflate.findViewById(2131298217);
        this.heartHonorLayout = (HeartHonorLayout) inflate.findViewById(2131297103);
    }

    public void addPraiseWithCallback() {
        this.mHiPraiseAnimationView.addPraise(new HiPraiseWithCallback(getHeartBitmap(), new OnDrawCallback() { // from class: com.sinovatech.unicom.separatemodule.audience.view.AlivcLikeView.1
            @Override // org.limlee.hipraiseanimationlib.OnDrawCallback
            public void onFinish() {
                AlivcLikeView.this.onLikeClickListener.onLike();
            }
        }));
    }

    public void addPraise(final int i) {
        final HiPraiseWithCallback hiPraiseWithCallback = new HiPraiseWithCallback(getHeartBitmap(), new OnDrawCallback() { // from class: com.sinovatech.unicom.separatemodule.audience.view.AlivcLikeView.2
            @Override // org.limlee.hipraiseanimationlib.OnDrawCallback
            public void onFinish() {
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.AlivcLikeView.3
            @Override // java.lang.Runnable
            public void run() {
                for (int i2 = 0; i2 < i; i2++) {
                    AlivcLikeView.this.mHiPraiseAnimationView.addPraise(hiPraiseWithCallback);
                }
            }
        });
        this.heartHonorLayout.addHeart(randomColor());
    }

    private int randomColor() {
        return Color.rgb(this.mRandom.nextInt(255), this.mRandom.nextInt(255), this.mRandom.nextInt(255));
    }

    private Bitmap getHeartBitmap() {
        int i = HEARDS[new Random().nextInt(HEARDS.length)];
        SoftReference<Bitmap> softReference = this.mBitmapCacheArray.get(i);
        Bitmap bitmap = softReference != null ? softReference.get() : null;
        if (bitmap == null) {
            int dip2px = (UIUtils.dip2px(20.0f) * 13) / 10;
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), i), dip2px, dip2px, true);
            this.mBitmapCacheArray.put(i, new SoftReference<>(createScaledBitmap));
            return createScaledBitmap;
        }
        return bitmap;
    }

    public void onStart() {
        this.mHiPraiseAnimationView.start();
    }

    public void onStop() {
        this.mHiPraiseAnimationView.stop();
        this.mHiPraiseAnimationView = null;
    }

    public void setOnLikeClickListener(OnLikeClickListener onLikeClickListener) {
        this.onLikeClickListener = onLikeClickListener;
    }
}

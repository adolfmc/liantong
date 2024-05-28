package com.sinovatech.unicom.separatemodule.keyboard;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.common.UIUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class EmotionKeyboard {
    private static final String SHARE_PREFERENCE_NAME = "EmotionKeyboard";
    private static final String SHARE_PREFERENCE_SOFT_INPUT_HEIGHT = "soft_input_height";
    private int contentViewHeight;
    private ImageView emotionView;
    private Activity mActivity;
    private View mContentView;
    private EditText mEditText;
    private View mEmotionLayout;
    private InputMethodManager mInputManager;

    /* renamed from: sp */
    private SharedPreferences f18541sp;

    private EmotionKeyboard() {
    }

    public static EmotionKeyboard with(Activity activity) {
        EmotionKeyboard emotionKeyboard = new EmotionKeyboard();
        emotionKeyboard.mActivity = activity;
        emotionKeyboard.mInputManager = (InputMethodManager) activity.getSystemService("input_method");
        emotionKeyboard.f18541sp = activity.getSharedPreferences(SHARE_PREFERENCE_NAME, 0);
        return emotionKeyboard;
    }

    public EmotionKeyboard bindToContent(View view) {
        this.mContentView = view;
        return this;
    }

    public EmotionKeyboard bindToEditText(EditText editText) {
        this.mEditText = editText;
        this.mEditText.requestFocus();
        this.mEditText.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.keyboard.EmotionKeyboard.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1 && EmotionKeyboard.this.mEmotionLayout.isShown()) {
                    EmotionKeyboard.this.lockContentHeight();
                    EmotionKeyboard.this.hideEmotionLayout(true);
                    EmotionKeyboard.this.emotionView.setImageResource(2131231556);
                    EmotionKeyboard.this.mEditText.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.keyboard.EmotionKeyboard.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            EmotionKeyboard.this.unlockContentHeightDelayed();
                        }
                    }, 200L);
                    return false;
                }
                return false;
            }
        });
        return this;
    }

    public EmotionKeyboard bindToEmotionButton(final ImageView imageView) {
        this.emotionView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.keyboard.EmotionKeyboard.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (EmotionKeyboard.this.mEmotionLayout.isShown()) {
                    imageView.setImageResource(2131231556);
                    EmotionKeyboard.this.lockContentHeight();
                    EmotionKeyboard.this.hideEmotionLayout(true);
                    EmotionKeyboard.this.unlockContentHeightDelayed();
                } else {
                    imageView.setImageResource(2131231576);
                    if (EmotionKeyboard.this.isSoftInputShown()) {
                        EmotionKeyboard.this.lockContentHeight();
                        EmotionKeyboard.this.showEmotionLayout();
                        EmotionKeyboard.this.unlockContentHeightDelayed();
                    } else {
                        EmotionKeyboard.this.showEmotionLayout();
                    }
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        return this;
    }

    public EmotionKeyboard setEmotionView(View view) {
        this.mEmotionLayout = view;
        return this;
    }

    public EmotionKeyboard build() {
        this.mActivity.getWindow().setSoftInputMode(19);
        hideSoftInput();
        return this;
    }

    public void showKeyboard() {
        showSoftInput();
    }

    public void hideKeyboard() {
        this.mEmotionLayout.setVisibility(8);
        hideSoftInput();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmotionLayout() {
        if (getSupportSoftInputHeight() == 0) {
            getKeyBoardHeight();
        }
        hideSoftInput();
        this.mEmotionLayout.getLayoutParams().height = UIUtils.dip2px(this.mActivity, 180.0f);
        this.mEmotionLayout.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideEmotionLayout(boolean z) {
        if (this.mEmotionLayout.isShown()) {
            this.mEmotionLayout.setVisibility(8);
            if (z) {
                showSoftInput();
            }
        }
    }

    public boolean getEmotionIsShown() {
        return this.mEmotionLayout.getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lockContentHeight() {
        if (this.contentViewHeight < 200) {
            this.contentViewHeight = this.mContentView.getHeight();
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mContentView.getLayoutParams();
        layoutParams.height = this.contentViewHeight;
        layoutParams.weight = 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unlockContentHeightDelayed() {
        Observable.timer(10L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.separatemodule.keyboard.EmotionKeyboard.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Long l) throws Exception {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) EmotionKeyboard.this.mContentView.getLayoutParams();
                layoutParams.height = 0;
                layoutParams.weight = 1.0f;
            }
        });
    }

    private void showSoftInput() {
        this.mEditText.requestFocus();
        this.mEditText.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.keyboard.EmotionKeyboard.4
            @Override // java.lang.Runnable
            public void run() {
                EmotionKeyboard.this.mInputManager.showSoftInput(EmotionKeyboard.this.mEditText, 0);
            }
        });
    }

    private void hideSoftInput() {
        this.mInputManager.hideSoftInputFromWindow(this.mEditText.getWindowToken(), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSoftInputShown() {
        return getSupportSoftInputHeight() != 0;
    }

    private int getSupportSoftInputHeight() {
        Rect rect = new Rect();
        this.mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int height = this.mActivity.getWindow().getDecorView().getRootView().getHeight() - rect.bottom;
        if (Build.VERSION.SDK_INT >= 20) {
            height -= getSoftButtonsBarHeight();
        }
        if (height > 0) {
            this.f18541sp.edit().putInt(SHARE_PREFERENCE_SOFT_INPUT_HEIGHT, height).apply();
        }
        return height;
    }

    @TargetApi(17)
    private int getSoftButtonsBarHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        this.mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            return i2 - i;
        }
        return 0;
    }

    public int getKeyBoardHeight() {
        return this.f18541sp.getInt(SHARE_PREFERENCE_SOFT_INPUT_HEIGHT, 787);
    }
}

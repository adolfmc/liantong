package cn.ltzf.passguard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.EditText;
import cn.ltzf.passguard.LTPassGuardKeyBoard;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.lang.reflect.Method;
import java.util.HashMap;

@NBSInstrumented
@SuppressLint({"NewApi"})
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class LTPassGuardEdit extends EditText implements View.OnClickListener, View.OnFocusChangeListener, View.OnLongClickListener {
    public static int KEY_CHAOS_PRESS_KEY = 2;
    public static int KEY_CHAOS_SWITCH_VIEW = 1;
    public static int KEY_NONE_CHAOS;
    private static final Handler handler = new Handler(Looper.getMainLooper());
    public static String m_tag;
    private boolean EditTextAlwaysShow;
    private LTPassGuardKeyBoard PassGuardKeyBoardImp;
    private ActionMode.Callback callback;
    private HashMap<LTPassGuardKeyBoard.CONFIG_KEY, Object> config;
    private int lastMoveY;
    private Activity m_context;
    public String m_strid;
    public WebView m_webview;
    private boolean needScrollView;
    private View scrollView;

    public LTPassGuardEdit(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.PassGuardKeyBoardImp = null;
        this.config = null;
        this.m_context = null;
        this.EditTextAlwaysShow = false;
        this.scrollView = null;
        this.m_webview = null;
        this.m_strid = "";
        this.needScrollView = true;
        this.lastMoveY = 0;
        this.m_context = scanForActivity(context);
        this.config = new HashMap<>();
        setInputType(0);
        if (needSetCursorVisiable()) {
            setRawInputType(1);
            setHoneycombCursorVisiable(true);
            setCursorVisible(false);
        }
        disableShowSoftInputOnFocus();
        setShowSoftInputOnFocus(false);
        setLongClickable(false);
        setOnTouchListener(new View.OnTouchListener() { // from class: cn.ltzf.passguard.LTPassGuardEdit.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                LTPassGuardEdit.this.doStartPasswordEditKeyBoard();
                return false;
            }
        });
    }

    private void disableShowSoftInputOnFocus() {
        if (Build.VERSION.SDK_INT >= 21) {
            setShowSoftInputOnFocus(false);
            return;
        }
        try {
            Method method = EditText.class.getMethod("setShowSoftInputOnFocus", Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(this, Boolean.FALSE);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doStartPasswordEditKeyBoard() {
        if (getInputMethodManager().hideSoftInputFromWindow(getWindowToken(), 0, new ResultReceiver(handler) { // from class: cn.ltzf.passguard.LTPassGuardEdit.2
            @Override // android.os.ResultReceiver
            public void onReceiveResult(int i, Bundle bundle) {
                if (LTPassGuardEdit.this.hasFocus()) {
                    LTPassGuardEdit.this.doStartPasswordEditKeyBoard();
                }
            }
        })) {
            return;
        }
        requestFocus();
        setCursorVisible(true);
        StartPassGuardKeyBoard();
    }

    private InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getContext().getSystemService("input_method");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getNeedScrollY() {
        if (this.PassGuardKeyBoardImp != null) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            return this.PassGuardKeyBoardImp.getHeight() - ((((WindowManager) this.m_context.getSystemService("window")).getDefaultDisplay().getHeight() - iArr[1]) - getHeight());
        }
        return 0;
    }

    public static String getSynKeyboardInput() {
        return m_tag;
    }

    private void hideSoftInputFromWindow() {
        getInputMethodManager().hideSoftInputFromWindow(getWindowToken(), 0);
    }

    private boolean needSetCursorVisiable() {
        return Build.VERSION.SDK_INT >= 11;
    }

    private Activity scanForActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private void setHoneycombCursorVisiable(boolean z) {
        setTextIsSelectable(z);
    }

    @JavascriptInterface
    public static void setLicense(String str) {
        LTPassGuardVerify.setLicense(str);
    }

    @JavascriptInterface
    public static void setNO_OFF(boolean z) {
        LTPassGuardKeyBoard.setNO_OFF(z);
    }

    public static void synKeyboardInput(String str) {
        m_tag = str;
    }

    @JavascriptInterface
    public void EditTextAlwaysShow(boolean z) {
        this.EditTextAlwaysShow = z;
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.IS_NEED_TEXTVIEW, Boolean.valueOf(z));
        }
    }

    @JavascriptInterface
    public void StartPassGuardKeyBoard() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard == null || lTPassGuardKeyBoard.isShowing() || this.m_context.isFinishing()) {
            return;
        }
        if (needSetCursorVisiable()) {
            setCursorVisible(true);
        }
        ((InputMethodManager) this.m_context.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        this.PassGuardKeyBoardImp.show();
    }

    @JavascriptInterface
    public void StopPassGuardKeyBoard() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard == null || !lTPassGuardKeyBoard.isShowing()) {
            return;
        }
        this.PassGuardKeyBoardImp.dismiss();
    }

    @JavascriptInterface
    public boolean checkMatch() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.checkMatch();
        }
        return false;
    }

    @JavascriptInterface
    public void clear() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.clear();
            setText("");
        }
    }

    @JavascriptInterface
    public void destory() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.uninitPassGuardKeyBoard();
        }
    }

    public int getKeyboardHeight() {
        return this.PassGuardKeyBoardImp.getHeight();
    }

    @JavascriptInterface
    public String getOutput0() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.getRSACiphertext();
        }
        return null;
    }

    @JavascriptInterface
    public String getOutput1() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.getCiphertext();
        }
        return null;
    }

    @JavascriptInterface
    public String getOutput2() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.getMd5();
        }
        return null;
    }

    @JavascriptInterface
    public int getOutput3() {
        return getText().length();
    }

    public String getOutput4() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.getMD5Ciphertext();
        }
        return null;
    }

    @JavascriptInterface
    public String getOutput5() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.getCipher3text();
        }
        return null;
    }

    @JavascriptInterface
    public String getOutput6() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.getCipher4text();
        }
        return null;
    }

    @JavascriptInterface
    public String getOutput7() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.getMSRSACiphertext();
        }
        return null;
    }

    @JavascriptInterface
    public int[] getPassLevel() {
        int[] iArr = {0};
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        return lTPassGuardKeyBoard != null ? lTPassGuardKeyBoard.getPassLevel() : iArr;
    }

    @JavascriptInterface
    public String getSM2SM4CipherText() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.getSM2SM4CipherText();
        }
        return null;
    }

    @JavascriptInterface
    public String getSM2SM4CipherText(String str) {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.getSM2SM4CipherText(str);
        }
        return null;
    }

    public String getVerison() {
        return "v1.0.2022082201";
    }

    public void initPassGuardKeyBoard() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = new LTPassGuardKeyBoard(this.m_context, new LTPassGuardCallBack() { // from class: cn.ltzf.passguard.LTPassGuardEdit.3
            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void addText(String str) {
                Editable text;
                if (str == null || (text = LTPassGuardEdit.this.getText()) == null) {
                    return;
                }
                if (text.length() <= 0) {
                    LTPassGuardEdit.this.append(str);
                    return;
                }
                int selectionStart = LTPassGuardEdit.this.getSelectionStart() < LTPassGuardEdit.this.getSelectionEnd() ? LTPassGuardEdit.this.getSelectionStart() : LTPassGuardEdit.this.getSelectionEnd();
                int selectionEnd = LTPassGuardEdit.this.getSelectionStart() < LTPassGuardEdit.this.getSelectionEnd() ? LTPassGuardEdit.this.getSelectionEnd() : LTPassGuardEdit.this.getSelectionStart();
                LTPassGuardEdit.this.setText("");
                LTPassGuardEdit lTPassGuardEdit = LTPassGuardEdit.this;
                lTPassGuardEdit.append(((Object) text.subSequence(0, selectionStart)) + str + ((Object) text.subSequence(selectionEnd, text.length())));
                LTPassGuardEdit.this.setSelection(selectionStart + 1);
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void backsapce() {
                Editable text;
                LTPassGuardEdit lTPassGuardEdit;
                int selectionStart = LTPassGuardEdit.this.getSelectionStart() < LTPassGuardEdit.this.getSelectionEnd() ? LTPassGuardEdit.this.getSelectionStart() : LTPassGuardEdit.this.getSelectionEnd();
                int selectionEnd = LTPassGuardEdit.this.getSelectionStart() < LTPassGuardEdit.this.getSelectionEnd() ? LTPassGuardEdit.this.getSelectionEnd() : LTPassGuardEdit.this.getSelectionStart();
                if ((selectionStart == 0 && selectionStart == selectionEnd) || (text = LTPassGuardEdit.this.getText()) == null || text.length() <= 0) {
                    return;
                }
                LTPassGuardEdit.this.setText("");
                if (selectionStart != selectionEnd) {
                    lTPassGuardEdit = LTPassGuardEdit.this;
                } else {
                    lTPassGuardEdit = LTPassGuardEdit.this;
                    selectionStart--;
                }
                lTPassGuardEdit.append(text.subSequence(0, selectionStart));
                LTPassGuardEdit.this.append(text.subSequence(selectionEnd, text.length()));
                LTPassGuardEdit.this.setSelection(selectionStart);
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void clear() {
                LTPassGuardEdit.this.setText("");
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public String getEditText() {
                return LTPassGuardEdit.this.getText().toString();
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public int getEndSelection() {
                return LTPassGuardEdit.this.getSelectionStart() < LTPassGuardEdit.this.getSelectionEnd() ? LTPassGuardEdit.this.getSelectionEnd() : LTPassGuardEdit.this.getSelectionStart();
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public int getStartSelection() {
                return LTPassGuardEdit.this.getSelectionStart() < LTPassGuardEdit.this.getSelectionEnd() ? LTPassGuardEdit.this.getSelectionStart() : LTPassGuardEdit.this.getSelectionEnd();
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void hideAction() {
                if (LTPassGuardEdit.this.needScrollView) {
                    if ((LTPassGuardEdit.this.scrollView != null || (LTPassGuardEdit.this.getParent() instanceof View)) && LTPassGuardEdit.this.lastMoveY >= 0) {
                        LTPassGuardEdit.this.m_context.runOnUiThread(new Runnable() { // from class: cn.ltzf.passguard.LTPassGuardEdit.3.2
                            @Override // java.lang.Runnable
                            public void run() {
                                (LTPassGuardEdit.this.scrollView != null ? LTPassGuardEdit.this.scrollView : (View) LTPassGuardEdit.this.getParent()).scrollBy(0, -LTPassGuardEdit.this.lastMoveY);
                            }
                        });
                    }
                }
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void moveSelectionLeft() {
                int selectionStart = LTPassGuardEdit.this.getSelectionStart();
                if (selectionStart > 0) {
                    LTPassGuardEdit.this.setSelection(selectionStart - 1);
                }
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void moveSelectionRight() {
                int selectionEnd = LTPassGuardEdit.this.getSelectionEnd();
                if (selectionEnd < LTPassGuardEdit.this.length()) {
                    LTPassGuardEdit.this.setSelection(selectionEnd + 1);
                }
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void replaceText() {
                int length = LTPassGuardEdit.this.getText().length();
                int selectionStart = LTPassGuardEdit.this.getSelectionStart();
                int selectionEnd = LTPassGuardEdit.this.getSelectionEnd();
                LTPassGuardEdit.this.setText("");
                for (int i = 0; i != length; i++) {
                    LTPassGuardEdit.this.append("*");
                }
                LTPassGuardEdit.this.setSelection(selectionStart, selectionEnd);
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void setCursorSelection(int i) {
                LTPassGuardEdit.this.setSelection(i);
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void setCursorSelection(int i, int i2) {
                LTPassGuardEdit.this.setSelection(i, i2);
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void showAction() {
                LTPassGuardKeyBoard lTPassGuardKeyBoard2;
                StringBuilder m22016a = C1730a.m22016a("EditTextAlwaysShow==");
                m22016a.append(LTPassGuardEdit.this.EditTextAlwaysShow);
                m22016a.append("scrollView==");
                m22016a.append(LTPassGuardEdit.this.scrollView);
                Log.i("cpf", m22016a.toString());
                if (!LTPassGuardEdit.this.EditTextAlwaysShow && LTPassGuardEdit.this.needScrollView && LTPassGuardEdit.this.scrollView != null) {
                    Log.i("cpf", "进入show判断");
                    LTPassGuardEdit lTPassGuardEdit = LTPassGuardEdit.this;
                    lTPassGuardEdit.lastMoveY = lTPassGuardEdit.getNeedScrollY();
                    LTPassGuardEdit.this.m_context.runOnUiThread(new Runnable() { // from class: cn.ltzf.passguard.LTPassGuardEdit.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (LTPassGuardEdit.this.lastMoveY >= 0) {
                                (LTPassGuardEdit.this.scrollView != null ? LTPassGuardEdit.this.scrollView : (View) LTPassGuardEdit.this.getParent()).scrollBy(0, LTPassGuardEdit.this.lastMoveY);
                            }
                        }
                    });
                    return;
                }
                Log.i("cpf", "进入else判断");
                boolean z = false;
                if (LTPassGuardEdit.this.EditTextAlwaysShow) {
                    lTPassGuardKeyBoard2 = LTPassGuardEdit.this.PassGuardKeyBoardImp;
                    if (LTPassGuardEdit.this.EditTextAlwaysShow || LTPassGuardEdit.this.getNeedScrollY() >= 0) {
                        z = true;
                    }
                } else {
                    lTPassGuardKeyBoard2 = LTPassGuardEdit.this.PassGuardKeyBoardImp;
                }
                lTPassGuardKeyBoard2.needTextView(z);
            }
        }, this.config);
        this.PassGuardKeyBoardImp = lTPassGuardKeyBoard;
        lTPassGuardKeyBoard.m_strid = this.m_strid;
        lTPassGuardKeyBoard.m_webview = this.m_webview;
        this.config = null;
    }

    @JavascriptInterface
    public boolean isKeyBoardShowing() {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            return lTPassGuardKeyBoard.isShowing();
        }
        return false;
    }

    public int isWeakPassword(String str) {
        return this.PassGuardKeyBoardImp.isWeakPassword(str);
    }

    public void needScrollView(boolean z) {
        this.needScrollView = z;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.cleanClip();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        StopPassGuardKeyBoard();
        super.onDetachedFromWindow();
    }

    public void onFocusChange(View view, boolean z) {
        Tracker.onFocusChange(view, z);
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.cleanClip();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z, int i, Rect rect) {
        ((InputMethodManager) this.m_context.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        if (z) {
            StartPassGuardKeyBoard();
        } else {
            if (needSetCursorVisiable()) {
                setCursorVisible(false);
            }
            StopPassGuardKeyBoard();
        }
        super.onFocusChanged(z, i, rect);
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return true;
        }
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard == null || !lTPassGuardKeyBoard.isShowing()) {
            return false;
        }
        StopPassGuardKeyBoard();
        return true;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        NBSActionInstrumentation.onLongClickEventEnter(view, this);
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.cleanClip();
        }
        NBSActionInstrumentation.onLongClickEventExit();
        return false;
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        LTPassGuardKeyBoard lTPassGuardKeyBoard;
        ((InputMethodManager) this.m_context.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        if ((parcelable instanceof Bundle) && (lTPassGuardKeyBoard = this.PassGuardKeyBoardImp) != null) {
            Bundle bundle = (Bundle) parcelable;
            lTPassGuardKeyBoard.onRestoreInstanceState(bundle.getParcelable("keyBoard"));
            parcelable = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        if (this.PassGuardKeyBoardImp != null) {
            ((InputMethodManager) this.m_context.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
            Bundle bundle = new Bundle();
            bundle.putParcelable("instanceState", super.onSaveInstanceState());
            bundle.putParcelable("keyBoard", this.PassGuardKeyBoardImp.onSaveInstanceState());
            return bundle;
        }
        return super.onSaveInstanceState();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        setShowSoftInputOnFocus(false);
        setLongClickable(false);
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 1) {
            if (motionEvent.getX() <= getWidth() && motionEvent.getX() >= 0.0f && motionEvent.getY() <= getHeight() && motionEvent.getY() >= 0.0f) {
                StartPassGuardKeyBoard();
            } else if (needSetCursorVisiable()) {
                setCursorVisible(false);
            }
            ((InputMethodManager) this.m_context.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        } else if (action == 2) {
            setCursorVisible(true);
        }
        return onTouchEvent;
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
    }

    @JavascriptInterface
    public void setButtonPress(boolean z) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.IS_BUTTON_NEED_PRESS, Boolean.valueOf(z));
        }
    }

    public void setButtonPressAnim(boolean z) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.IS_BUTTON_NEED_PRESS_ANIM, Boolean.valueOf(z));
        }
    }

    @JavascriptInterface
    public void setCipherKey(String str) {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.setConfigValue(LTPassGuardKeyBoard.CONFIG_KEY.CIPHER_KEY, str);
        } else {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.CIPHER_KEY, str);
        }
    }

    @JavascriptInterface
    public void setClip(boolean z) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.IS_CLIP, Boolean.valueOf(z));
        }
        if (z) {
            return;
        }
        setLongClickable(false);
        this.callback = new ActionMode.Callback() { // from class: cn.ltzf.passguard.LTPassGuardEdit.4
            @Override // android.view.ActionMode.Callback
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public void onDestroyActionMode(ActionMode actionMode) {
            }

            @Override // android.view.ActionMode.Callback
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        };
    }

    @JavascriptInterface
    public void setEccKey(String str) {
        if (this.PassGuardKeyBoardImp != null) {
            if (str.indexOf("|") == -1 && str.length() == 130) {
                String substring = str.substring(2, str.length());
                str = substring.substring(0, 64) + "|" + substring.substring(64, substring.length());
            }
            this.PassGuardKeyBoardImp.setConfigValue(LTPassGuardKeyBoard.CONFIG_KEY.ECC_KEY, str);
            return;
        }
        if (str.indexOf("|") == -1 && str.length() == 130) {
            String substring2 = str.substring(2, str.length());
            str = substring2.substring(0, 64) + "|" + substring2.substring(64, substring2.length());
        }
        this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.ECC_KEY, str);
    }

    @JavascriptInterface
    public void setEncrypt(boolean z) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.IS_ENCRYPT, Boolean.valueOf(z));
        }
    }

    @JavascriptInterface
    public void setInputRegex(String str) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.INPUT_REGEX, str);
        }
    }

    public void setKeyBoardHideAction(LTdoAction lTdoAction) {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.setConfigValue(LTPassGuardKeyBoard.CONFIG_KEY.KEYBOARD_HIDE_ACTION, lTdoAction);
        } else {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.KEYBOARD_HIDE_ACTION, lTdoAction);
        }
    }

    public void setKeyBoardShowAction(LTdoAction lTdoAction) {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.setConfigValue(LTPassGuardKeyBoard.CONFIG_KEY.KEYBOARD_SHOW_ACTION, lTdoAction);
        } else {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.KEYBOARD_SHOW_ACTION, lTdoAction);
        }
    }

    @JavascriptInterface
    public void setMatchRegex(String str) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.MATCH_REGEX, str);
        }
    }

    @JavascriptInterface
    public void setMaxLength(int i) {
        if (this.PassGuardKeyBoardImp != null || i <= 0) {
            return;
        }
        this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.MAX_LENGTH, Integer.valueOf(i));
    }

    @JavascriptInterface
    public void setPublicKey(String str) {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.setConfigValue(LTPassGuardKeyBoard.CONFIG_KEY.PUBLIC_KEY, str);
        } else {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.PUBLIC_KEY, str);
        }
    }

    @JavascriptInterface
    public void setReorder(int i) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.IS_REORDER, Integer.valueOf(i));
        }
    }

    public void setScrollView(View view) {
        this.scrollView = view;
    }

    public void setShowPassword(boolean z) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.IS_PASSWORD_NEED_SHOW, Boolean.valueOf(z));
        }
    }

    public void setSynKeyboardInput(SynKeyboardInput synKeyboardInput) {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.setConfigValue(LTPassGuardKeyBoard.CONFIG_KEY.SYN_KEYBOARD_INPUT, synKeyboardInput);
        } else {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.SYN_KEYBOARD_INPUT, synKeyboardInput);
        }
    }

    @JavascriptInterface
    public void setWatchOutside(boolean z) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.IS_WATCH_OUTSIDE, Boolean.valueOf(z));
        }
    }

    public void updateConfig(String str) {
        LTPassGuardKeyBoard lTPassGuardKeyBoard = this.PassGuardKeyBoardImp;
        if (lTPassGuardKeyBoard != null) {
            lTPassGuardKeyBoard.setConfigValue(LTPassGuardKeyBoard.CONFIG_KEY.MATCH_REGEX, str);
        }
    }

    @JavascriptInterface
    public void useMSNumberPad(boolean z) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.IS_MSNUMPAD, Boolean.valueOf(z));
        }
    }

    @JavascriptInterface
    public void useNumberPad(boolean z) {
        if (this.PassGuardKeyBoardImp == null) {
            this.config.put(LTPassGuardKeyBoard.CONFIG_KEY.IS_NUMPAD, Boolean.valueOf(z));
        }
    }
}

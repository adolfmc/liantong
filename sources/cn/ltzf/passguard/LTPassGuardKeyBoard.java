package cn.ltzf.passguard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.example.asus.detectionandalign.animation.C4280b;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sdk.p285a.C6960d;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class LTPassGuardKeyBoard {
    private static final int DOUBLE_CLICK_TIME = 200;
    public static int ENCRYPT_ONE = 1;
    public static int ENCRYPT_THREE = 3;
    public static int ENCRYPT_TWO = 2;
    public static int KEY_CHAOS_PRESS_KEY = 2;
    public static int KEY_CHAOS_SWITCH_VIEW = 1;
    public static int KEY_NONE_CHAOS = 0;
    private static final int PASSWORD_SHOW_TIME = 500;
    private static boolean m_bscreenprotect;
    private int key;
    private Context m_context;
    public WebView m_webview;
    private LTPassGuardCallBack passGuardCallBack;
    private LTPassGuardCallBack tempPGCB;
    public String m_strid = "";
    private LTPassGuardCallBack insidePGCB = null;
    private LinearLayout PassGuardView = null;
    private LinearLayout Letter = null;
    private LinearLayout Number = null;
    private LinearLayout Special = null;
    private LinearLayout Numpad = null;
    private LinearLayout Numpad_Ms = null;
    private View.OnClickListener PassGuardClickListener = null;
    private View.OnLongClickListener BackSpaceLongClickListener = null;
    private View.OnTouchListener BackSpaceTouchListener = null;
    private View.OnTouchListener PassGuardTouchListener = null;
    private View.OnTouchListener PassGuardNCTouchListener = null;
    private HashMap<CONFIG_KEY, Object> PassGuardConfig = null;
    private HashMap<String, View> tips = new HashMap<>();
    private boolean isUpperLetter = false;
    private boolean isShift = false;
    private boolean isCaps = false;
    private boolean isLongClick = false;
    private boolean needTextView = false;
    private boolean isShowing = false;
    private String SaveCiphertext = null;
    private boolean waitDouble = true;
    private EditText editText = null;
    private Thread PasswordShowThread = null;
    private LTUICtrl uictrl = null;
    private boolean poupIsShow = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: cn.ltzf.passguard.LTPassGuardKeyBoard$2 */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC17152 implements View.OnClickListener {
        public View$OnClickListenerC17152() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:52:0x01ba, code lost:
            if (r4.getConfigValue(r0) == null) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x01e7, code lost:
            if (r4.getConfigValue(r0) != null) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x01e9, code lost:
            ((cn.ltzf.passguard.SynKeyboardInput) r3.this$0.getConfigValue(r0)).synKeyboardInput("*");
         */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onClick(android.view.View r4) {
            /*
                Method dump skipped, instructions count: 551
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.ltzf.passguard.LTPassGuardKeyBoard.View$OnClickListenerC17152.onClick(android.view.View):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: cn.ltzf.passguard.LTPassGuardKeyBoard$3 */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class View$OnLongClickListenerC17203 implements View.OnLongClickListener {
        public View$OnLongClickListenerC17203() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            NBSActionInstrumentation.onLongClickEventEnter(view, this);
            LTPassGuardKeyBoard.this.isLongClick = true;
            new Thread() { // from class: cn.ltzf.passguard.LTPassGuardKeyBoard.3.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    while (LTPassGuardKeyBoard.this.isLongClick) {
                        try {
                            LTPassGuardKeyBoard.this.PassGuardView.post(new Runnable() { // from class: cn.ltzf.passguard.LTPassGuardKeyBoard.3.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    LTPassGuardKeyBoard.this.deletePlantText();
                                    if (((Boolean) LTPassGuardKeyBoard.this.getConfigValue(CONFIG_KEY.IS_NEED_TEXTVIEW)).booleanValue()) {
                                        LTPassGuardKeyBoard.this.passGuardCallBack.backsapce();
                                    }
                                    LTPassGuardKeyBoard.this.tempPGCB.backsapce();
                                }
                            });
                            Thread.sleep(100L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }.start();
            NBSActionInstrumentation.onLongClickEventExit();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.ltzf.passguard.LTPassGuardKeyBoard$8 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static /* synthetic */ class C17278 {
        public static final /* synthetic */ int[] $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY;

        static {
            int[] iArr = new int[CONFIG_KEY.values().length];
            $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY = iArr;
            try {
                iArr[CONFIG_KEY.IS_PASSWORD_NEED_SHOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.IS_ENCRYPT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.IS_BUTTON_NEED_PRESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.IS_NUMPAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.IS_MSNUMPAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.IS_WATCH_OUTSIDE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.IS_NEED_TEXTVIEW.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.IS_CLIP.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.IS_BUTTON_NEED_PRESS_ANIM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.IS_REORDER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.MAX_LENGTH.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.KEYBOARD_SHOW_ACTION.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.KEYBOARD_HIDE_ACTION.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.SYN_KEYBOARD_INPUT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.INPUT_REGEX.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.MATCH_REGEX.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.CIPHER_KEY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.PUBLIC_KEY.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[CONFIG_KEY.ECC_KEY.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum CONFIG_KEY {
        IS_CLIP,
        SYN_KEYBOARD_INPUT,
        IS_PASSWORD_NEED_SHOW,
        IS_ENCRYPT,
        IS_BUTTON_NEED_PRESS,
        IS_NUMPAD,
        IS_MSNUMPAD,
        IS_WATCH_OUTSIDE,
        IS_REORDER,
        MAX_LENGTH,
        KEYBOARD_SHOW_ACTION,
        KEYBOARD_HIDE_ACTION,
        INPUT_REGEX,
        MATCH_REGEX,
        CIPHER_KEY,
        PUBLIC_KEY,
        ECC_KEY,
        IS_NEED_TEXTVIEW,
        IS_BUTTON_NEED_PRESS_ANIM
    }

    public LTPassGuardKeyBoard(Activity activity, LTPassGuardCallBack lTPassGuardCallBack, HashMap<CONFIG_KEY, Object> hashMap) {
        this.passGuardCallBack = null;
        this.tempPGCB = null;
        this.m_context = null;
        this.key = 0;
        this.key = LTPassGuardEncrypt.makeKey();
        this.m_context = activity;
        this.passGuardCallBack = lTPassGuardCallBack;
        this.tempPGCB = lTPassGuardCallBack;
        initConfig();
        setConfigValue(hashMap);
        initPassGuardShow();
        CONFIG_KEY config_key = CONFIG_KEY.IS_REORDER;
        if ((((Integer) getConfigValue(config_key)).intValue() & KEY_CHAOS_SWITCH_VIEW) == 0 && (((Integer) getConfigValue(config_key)).intValue() & KEY_CHAOS_PRESS_KEY) == 0) {
            return;
        }
        reOrder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addPlantText(String str) {
        String str2 = this.SaveCiphertext;
        String Decrypt = (str2 == null || str2.length() <= 0) ? null : LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key);
        if (Decrypt != null && Decrypt.length() > 0) {
            int startSelection = this.passGuardCallBack.getStartSelection();
            int endSelection = this.passGuardCallBack.getEndSelection();
            if (Decrypt.length() == startSelection || (Decrypt.length() > startSelection && Decrypt.length() > endSelection)) {
                str = Decrypt.substring(0, startSelection) + str + Decrypt.substring(endSelection, Decrypt.length());
            } else {
                str = Decrypt;
            }
        }
        if (str == null || str.length() <= 0) {
            this.SaveCiphertext = null;
        } else {
            this.SaveCiphertext = LTPassGuardEncrypt.Encrypt(str, this.key);
        }
    }

    private void bindKeys(LinearLayout linearLayout) {
        for (int i = 0; i <= linearLayout.getChildCount(); i++) {
            if (linearLayout.getChildAt(i) instanceof LinearLayout) {
                bindKeys((LinearLayout) linearLayout.getChildAt(i));
            } else if ((linearLayout.getChildAt(i) instanceof Button) || (linearLayout.getChildAt(i) instanceof ImageButton)) {
                linearLayout.getChildAt(i).setOnClickListener(this.PassGuardClickListener);
                linearLayout.getChildAt(i).setOnTouchListener(this.PassGuardTouchListener);
                if (linearLayout.getChildAt(i).getTag().equals("backspace")) {
                    linearLayout.getChildAt(i).setOnLongClickListener(this.BackSpaceLongClickListener);
                    linearLayout.getChildAt(i).setOnTouchListener(this.BackSpaceTouchListener);
                }
                linearLayout.setOnTouchListener(this.PassGuardTouchListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeCaps() {
        StateListDrawable stateListDrawable;
        LinearLayout linearLayout = this.Letter;
        if (linearLayout != null) {
            Button button = (Button) linearLayout.findViewWithTag("shift");
            if (((Boolean) getConfigValue(CONFIG_KEY.IS_BUTTON_NEED_PRESS)).booleanValue()) {
                StateListDrawable stateListDrawable2 = new StateListDrawable();
                stateListDrawable2.addState(new int[]{16842919}, this.uictrl.LoadImage(this.isUpperLetter ? "shift_press.png" : "caps_press.png"));
                stateListDrawable2.addState(new int[0], this.uictrl.LoadImage(this.isUpperLetter ? "shift.png" : "caps.png"));
                stateListDrawable = stateListDrawable2;
            } else {
                stateListDrawable = this.uictrl.LoadImage(this.isUpperLetter ? "shift.png" : "caps.png");
            }
            button.setBackgroundDrawable(stateListDrawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeLetter() {
        String upperCase;
        if (this.Letter != null) {
            for (int i = 0; i <= this.Letter.getChildCount() - 1; i++) {
                LinearLayout linearLayout = (LinearLayout) this.Letter.getChildAt(i);
                for (int i2 = 0; i2 <= linearLayout.getChildCount() - 1; i2++) {
                    if ((linearLayout.getChildAt(i2) instanceof Button) && !linearLayout.getChildAt(i2).getTag().equals(" ") && ((String) linearLayout.getChildAt(i2).getTag()).length() <= 1) {
                        Button button = (Button) linearLayout.getChildAt(i2);
                        if (this.isCaps) {
                            button.setText(button.getText().toString().toLowerCase());
                            upperCase = button.getTag().toString().toLowerCase();
                        } else {
                            button.setText(button.getText().toString().toUpperCase());
                            upperCase = button.getTag().toString().toUpperCase();
                        }
                        button.setTag(upperCase);
                    }
                }
            }
            Button button2 = (Button) this.Letter.findViewWithTag("shift");
            if (((Boolean) getConfigValue(CONFIG_KEY.IS_BUTTON_NEED_PRESS)).booleanValue()) {
                StateListDrawable stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(new int[]{16842919}, this.uictrl.LoadScaledImage(this.isCaps ? "shift_press.png" : "shift_on_press.png"));
                stateListDrawable.addState(new int[0], this.uictrl.LoadScaledImage(this.isCaps ? "shift.png" : "shift_on.png"));
                button2.setBackgroundDrawable(stateListDrawable);
            } else {
                button2.setBackgroundDrawable(this.uictrl.LoadScaledImage(this.isCaps ? "shift.png" : "shift_on.png"));
            }
            this.isCaps = !this.isCaps;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeLinearLayout(String str) {
        int height = ((WindowManager) this.m_context.getSystemService("window")).getDefaultDisplay().getHeight();
        int height2 = getHeight();
        if (this.Letter != null && this.Number != null && this.Special != null) {
            new TranslateAnimation(0.0f, 0.0f, height, height2).setDuration(3000L);
            if (str.equals("changeletter")) {
                this.Letter.setVisibility(0);
                this.Number.setVisibility(8);
            } else if (str.equals("changenumber")) {
                this.Letter.setVisibility(8);
                this.Number.setVisibility(0);
            } else if (str.equals("changespecial")) {
                this.Letter.setVisibility(8);
                this.Number.setVisibility(8);
                this.Special.setVisibility(0);
            }
            this.Special.setVisibility(8);
        }
        CONFIG_KEY config_key = CONFIG_KEY.IS_REORDER;
        if ((((Integer) getConfigValue(config_key)).intValue() & KEY_CHAOS_SWITCH_VIEW) == 0 && (((Integer) getConfigValue(config_key)).intValue() & KEY_CHAOS_PRESS_KEY) == 0) {
            return;
        }
        reOrder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkInput(String str) {
        CONFIG_KEY config_key = CONFIG_KEY.INPUT_REGEX;
        if (getConfigValue(config_key) == null) {
            return true;
        }
        return str.matches((String) getConfigValue(config_key));
    }

    private int countHeight() {
        if (this.m_context.getResources().getConfiguration().orientation == 2) {
            Display defaultDisplay = ((WindowManager) this.m_context.getSystemService("window")).getDefaultDisplay();
            Rect rect = new Rect();
            ((Activity) this.m_context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            return ((defaultDisplay.getHeight() - getHeight()) - rect.top) - 1;
        }
        return -2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deletePlantText() {
        StringBuilder sb;
        String str = this.SaveCiphertext;
        String Decrypt = str != null ? LTPassGuardEncrypt.Decrypt(str, this.key) : null;
        int startSelection = this.passGuardCallBack.getStartSelection();
        int endSelection = this.passGuardCallBack.getEndSelection();
        if (startSelection == 0 && startSelection == endSelection) {
            return;
        }
        if (Decrypt != null && Decrypt.length() > 0 && (Decrypt.length() == startSelection || (Decrypt.length() > startSelection && Decrypt.length() > endSelection))) {
            if (startSelection != endSelection) {
                sb = new StringBuilder();
            } else {
                sb = new StringBuilder();
                startSelection--;
            }
            sb.append(Decrypt.substring(0, startSelection));
            sb.append(Decrypt.substring(endSelection, Decrypt.length()));
            Decrypt = sb.toString();
        }
        if (Decrypt == null || Decrypt.length() <= 0) {
            this.SaveCiphertext = null;
        } else {
            this.SaveCiphertext = LTPassGuardEncrypt.Encrypt(Decrypt, this.key);
        }
    }

    private void initConfig() {
        if (this.PassGuardConfig == null) {
            HashMap<CONFIG_KEY, Object> hashMap = new HashMap<>();
            this.PassGuardConfig = hashMap;
            CONFIG_KEY config_key = CONFIG_KEY.IS_PASSWORD_NEED_SHOW;
            Boolean bool = Boolean.FALSE;
            hashMap.put(config_key, bool);
            HashMap<CONFIG_KEY, Object> hashMap2 = this.PassGuardConfig;
            CONFIG_KEY config_key2 = CONFIG_KEY.IS_ENCRYPT;
            Boolean bool2 = Boolean.TRUE;
            hashMap2.put(config_key2, bool2);
            this.PassGuardConfig.put(CONFIG_KEY.IS_BUTTON_NEED_PRESS, bool);
            this.PassGuardConfig.put(CONFIG_KEY.IS_NUMPAD, bool);
            this.PassGuardConfig.put(CONFIG_KEY.IS_CLIP, bool2);
            this.PassGuardConfig.put(CONFIG_KEY.IS_MSNUMPAD, bool);
            this.PassGuardConfig.put(CONFIG_KEY.IS_WATCH_OUTSIDE, bool);
            this.PassGuardConfig.put(CONFIG_KEY.IS_REORDER, Integer.valueOf(KEY_NONE_CHAOS));
            this.PassGuardConfig.put(CONFIG_KEY.MAX_LENGTH, 100);
            this.PassGuardConfig.put(CONFIG_KEY.KEYBOARD_SHOW_ACTION, null);
            this.PassGuardConfig.put(CONFIG_KEY.KEYBOARD_HIDE_ACTION, null);
            this.PassGuardConfig.put(CONFIG_KEY.INPUT_REGEX, null);
            this.PassGuardConfig.put(CONFIG_KEY.MATCH_REGEX, null);
            this.PassGuardConfig.put(CONFIG_KEY.CIPHER_KEY, null);
            this.PassGuardConfig.put(CONFIG_KEY.PUBLIC_KEY, null);
            this.PassGuardConfig.put(CONFIG_KEY.ECC_KEY, null);
            this.PassGuardConfig.put(CONFIG_KEY.IS_NEED_TEXTVIEW, bool);
            this.PassGuardConfig.put(CONFIG_KEY.IS_BUTTON_NEED_PRESS_ANIM, bool);
            this.PassGuardConfig.put(CONFIG_KEY.SYN_KEYBOARD_INPUT, null);
        }
    }

    private void initListener() {
        if (this.PassGuardClickListener == null) {
            this.PassGuardClickListener = new View$OnClickListenerC17152();
        }
        if (this.BackSpaceLongClickListener == null) {
            this.BackSpaceLongClickListener = new View$OnLongClickListenerC17203();
        }
        if (this.BackSpaceTouchListener == null) {
            this.BackSpaceTouchListener = new View.OnTouchListener() { // from class: cn.ltzf.passguard.LTPassGuardKeyBoard.4
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        LTPassGuardKeyBoard.this.isLongClick = false;
                    }
                    return false;
                }
            };
        }
        if (this.PassGuardTouchListener == null && ((Boolean) getConfigValue(CONFIG_KEY.IS_BUTTON_NEED_PRESS_ANIM)).booleanValue()) {
            this.PassGuardTouchListener = new View.OnTouchListener() { // from class: cn.ltzf.passguard.LTPassGuardKeyBoard.5
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    String str = (String) view.getTag();
                    int action = motionEvent.getAction();
                    if (action != 0) {
                        if (action == 1 && !((Boolean) LTPassGuardKeyBoard.this.getConfigValue(CONFIG_KEY.IS_NUMPAD)).booleanValue() && str.length() == 1 && str.compareTo(" ") != 0) {
                            LTPassGuardKeyBoard.this.removeTip(str);
                        }
                    } else if (!LTPassGuardKeyBoard.this.poupIsShow && !((Boolean) LTPassGuardKeyBoard.this.getConfigValue(CONFIG_KEY.IS_NUMPAD)).booleanValue() && str.length() == 1 && str.compareTo(" ") != 0 && !LTPassGuardKeyBoard.isFoldDisplay(LTPassGuardKeyBoard.this.m_context)) {
                        int[] iArr = new int[2];
                        view.getLocationOnScreen(iArr);
                        LTPassGuardKeyBoard.this.addTip(str, iArr[0] + (view.getWidth() / 2), (view.getHeight() / 2) + iArr[1], view.getWidth() * 2, view.getHeight() * 2, iArr);
                    }
                    return false;
                }
            };
        }
    }

    private void initPGCB() {
        this.insidePGCB = new LTPassGuardCallBack() { // from class: cn.ltzf.passguard.LTPassGuardKeyBoard.6
            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void addText(String str) {
                Editable text;
                if (str == null || (text = LTPassGuardKeyBoard.this.editText.getText()) == null) {
                    return;
                }
                if (text.length() <= 0) {
                    LTPassGuardKeyBoard.this.editText.append(str);
                    return;
                }
                int selectionStart = LTPassGuardKeyBoard.this.editText.getSelectionStart() < LTPassGuardKeyBoard.this.editText.getSelectionEnd() ? LTPassGuardKeyBoard.this.editText.getSelectionStart() : LTPassGuardKeyBoard.this.editText.getSelectionEnd();
                int selectionEnd = LTPassGuardKeyBoard.this.editText.getSelectionStart() < LTPassGuardKeyBoard.this.editText.getSelectionEnd() ? LTPassGuardKeyBoard.this.editText.getSelectionEnd() : LTPassGuardKeyBoard.this.editText.getSelectionStart();
                LTPassGuardKeyBoard.this.editText.setText("");
                EditText editText = LTPassGuardKeyBoard.this.editText;
                editText.append(((Object) text.subSequence(0, selectionStart)) + str + ((Object) text.subSequence(selectionEnd, text.length())));
                LTPassGuardKeyBoard.this.editText.setSelection(selectionStart + 1);
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void backsapce() {
                Editable text;
                EditText editText;
                int selectionStart = LTPassGuardKeyBoard.this.editText.getSelectionStart() < LTPassGuardKeyBoard.this.editText.getSelectionEnd() ? LTPassGuardKeyBoard.this.editText.getSelectionStart() : LTPassGuardKeyBoard.this.editText.getSelectionEnd();
                int selectionEnd = LTPassGuardKeyBoard.this.editText.getSelectionStart() < LTPassGuardKeyBoard.this.editText.getSelectionEnd() ? LTPassGuardKeyBoard.this.editText.getSelectionEnd() : LTPassGuardKeyBoard.this.editText.getSelectionStart();
                if ((selectionStart == 0 && selectionStart == selectionEnd) || (text = LTPassGuardKeyBoard.this.editText.getText()) == null || text.length() <= 0) {
                    return;
                }
                LTPassGuardKeyBoard.this.editText.setText("");
                if (selectionStart != selectionEnd) {
                    editText = LTPassGuardKeyBoard.this.editText;
                } else {
                    editText = LTPassGuardKeyBoard.this.editText;
                    selectionStart--;
                }
                editText.append(text.subSequence(0, selectionStart));
                LTPassGuardKeyBoard.this.editText.append(text.subSequence(selectionEnd, text.length()));
                LTPassGuardKeyBoard.this.editText.setSelection(selectionStart);
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void clear() {
                LTPassGuardKeyBoard.this.editText.setText("");
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public String getEditText() {
                return LTPassGuardKeyBoard.this.editText.getText().toString();
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public int getEndSelection() {
                return LTPassGuardKeyBoard.this.editText.getSelectionStart() < LTPassGuardKeyBoard.this.editText.getSelectionEnd() ? LTPassGuardKeyBoard.this.editText.getSelectionEnd() : LTPassGuardKeyBoard.this.editText.getSelectionStart();
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public int getStartSelection() {
                return LTPassGuardKeyBoard.this.editText.getSelectionStart() < LTPassGuardKeyBoard.this.editText.getSelectionEnd() ? LTPassGuardKeyBoard.this.editText.getSelectionStart() : LTPassGuardKeyBoard.this.editText.getSelectionEnd();
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void hideAction() {
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void moveSelectionLeft() {
                int selectionStart = LTPassGuardKeyBoard.this.editText.getSelectionStart();
                if (selectionStart > 0) {
                    LTPassGuardKeyBoard.this.editText.setSelection(selectionStart - 1);
                }
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void moveSelectionRight() {
                int selectionEnd = LTPassGuardKeyBoard.this.editText.getSelectionEnd();
                if (selectionEnd < LTPassGuardKeyBoard.this.editText.length()) {
                    LTPassGuardKeyBoard.this.editText.setSelection(selectionEnd + 1);
                }
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void replaceText() {
                int length = LTPassGuardKeyBoard.this.editText.length();
                int selectionStart = LTPassGuardKeyBoard.this.editText.getSelectionStart();
                int selectionEnd = LTPassGuardKeyBoard.this.editText.getSelectionEnd();
                LTPassGuardKeyBoard.this.editText.setText("");
                for (int i = 0; i != length; i++) {
                    LTPassGuardKeyBoard.this.editText.append("*");
                }
                LTPassGuardKeyBoard.this.editText.setSelection(selectionStart, selectionEnd);
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void setCursorSelection(int i) {
                LTPassGuardKeyBoard.this.editText.setSelection(i);
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void setCursorSelection(int i, int i2) {
                LTPassGuardKeyBoard.this.editText.setSelection(i, i2);
            }

            @Override // cn.ltzf.passguard.LTPassGuardCallBack
            public void showAction() {
            }
        };
    }

    private void initPassGuardShow() {
        if (this.PassGuardView == null) {
            LTUICtrl lTUICtrl = new LTUICtrl();
            this.uictrl = lTUICtrl;
            lTUICtrl.setPkb(this);
            this.uictrl.setContext(this.m_context.getApplicationContext());
            this.uictrl.init();
            LTUICtrl lTUICtrl2 = this.uictrl;
            CONFIG_KEY config_key = CONFIG_KEY.IS_NUMPAD;
            this.PassGuardView = (LinearLayout) lTUICtrl2.toViewInAssets(((Boolean) getConfigValue(config_key)).booleanValue() ? "numpad_s" : "passguard_s", ((Boolean) getConfigValue(CONFIG_KEY.IS_BUTTON_NEED_PRESS)).booleanValue());
            if (((Boolean) getConfigValue(config_key)).booleanValue()) {
                this.Numpad = (LinearLayout) this.PassGuardView.findViewWithTag("NumberGroup");
            } else if (((Boolean) getConfigValue(CONFIG_KEY.IS_MSNUMPAD)).booleanValue()) {
                this.Numpad_Ms = (LinearLayout) this.PassGuardView.findViewWithTag("NumberGroup_Ms");
            } else {
                this.Letter = (LinearLayout) this.PassGuardView.findViewWithTag("LetterGroup");
                this.Number = (LinearLayout) this.PassGuardView.findViewWithTag("NumberGroup");
                this.Special = (LinearLayout) this.PassGuardView.findViewWithTag("SpecialGroup");
            }
            EditText editText = new EditText(this.m_context);
            this.editText = editText;
            editText.setGravity(0);
            this.editText.setLongClickable(false);
            initListener();
            bindKeys(this.PassGuardView);
            this.PassGuardView.setOnTouchListener(new View.OnTouchListener() { // from class: cn.ltzf.passguard.LTPassGuardKeyBoard.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 4 && ((Boolean) LTPassGuardKeyBoard.this.getConfigValue(CONFIG_KEY.IS_WATCH_OUTSIDE)).booleanValue()) {
                        LTPassGuardKeyBoard.this.dismiss();
                        return false;
                    }
                    return false;
                }
            });
        }
    }

    public static boolean isFoldDisplay(Context context) {
        int identifier = context.getResources().getIdentifier("config_lidControlsDisplayFold", "bool", "android");
        if (identifier > 0) {
            return context.getResources().getBoolean(identifier);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reOrder() {
        LinearLayout linearLayout = this.Letter;
        if (linearLayout != null && linearLayout.getVisibility() == 0) {
            String[] strArr = {"a", C4280b.f10047a, "c", C6960d.f18019d, "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
            Vector vector = new Vector();
            Random random = new Random();
            boolean z = true;
            while (z) {
                int nextInt = random.nextInt(26);
                if (!vector.contains(Integer.valueOf(nextInt))) {
                    vector.add(Integer.valueOf(nextInt));
                }
                if (vector.size() == 26) {
                    z = false;
                }
            }
            int i = 0;
            int i2 = 0;
            while (i <= this.Letter.getChildCount() - 2) {
                LinearLayout linearLayout2 = (LinearLayout) this.Letter.getChildAt(i);
                int i3 = i2;
                for (int i4 = 0; i4 <= linearLayout2.getChildCount() - 1; i4++) {
                    if ((linearLayout2.getChildAt(i4) instanceof Button) && !linearLayout2.getChildAt(i4).getTag().equals(" ") && ((String) linearLayout2.getChildAt(i4).getTag()).length() <= 1) {
                        Button button = (Button) linearLayout2.getChildAt(i4);
                        String upperCase = this.isCaps ? strArr[((Integer) vector.get(i3)).intValue()].toString().toUpperCase() : strArr[((Integer) vector.get(i3)).intValue()];
                        String upperCase2 = this.isCaps ? strArr[((Integer) vector.get(i3)).intValue()].toString().toUpperCase() : strArr[((Integer) vector.get(i3)).intValue()];
                        button.setTag(upperCase);
                        button.setText(upperCase2);
                        i3++;
                    }
                }
                i++;
                i2 = i3;
            }
        }
        LinearLayout linearLayout3 = this.Number;
        if (linearLayout3 != null && linearLayout3.getVisibility() == 0) {
            String[] strArr2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
            Vector vector2 = new Vector();
            Random random2 = new Random();
            boolean z2 = true;
            while (z2) {
                int nextInt2 = random2.nextInt(10);
                if (!vector2.contains(Integer.valueOf(nextInt2))) {
                    vector2.add(Integer.valueOf(nextInt2));
                }
                if (vector2.size() == 10) {
                    z2 = false;
                }
            }
            LinearLayout linearLayout4 = (LinearLayout) this.Number.getChildAt(0);
            int i5 = 0;
            for (int i6 = 0; i6 <= linearLayout4.getChildCount() - 1; i6++) {
                if ((linearLayout4.getChildAt(i6) instanceof Button) && ((String) linearLayout4.getChildAt(i6).getTag()).length() <= 1) {
                    Button button2 = (Button) linearLayout4.getChildAt(i6);
                    String str = strArr2[((Integer) vector2.get(i5)).intValue()];
                    String str2 = strArr2[((Integer) vector2.get(i5)).intValue()];
                    button2.setTag(str);
                    button2.setText(str2);
                    i5++;
                }
            }
        }
        if (this.Numpad != null) {
            String[] strArr3 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
            Vector vector3 = new Vector();
            Random random3 = new Random();
            boolean z3 = true;
            while (z3) {
                int nextInt3 = random3.nextInt(10);
                if (!vector3.contains(Integer.valueOf(nextInt3))) {
                    vector3.add(Integer.valueOf(nextInt3));
                }
                if (vector3.size() == 10) {
                    z3 = false;
                }
            }
            int i7 = 0;
            int i8 = 0;
            while (i7 <= this.Numpad.getChildCount() - 1) {
                LinearLayout linearLayout5 = (LinearLayout) this.Numpad.getChildAt(i7);
                int i9 = i8;
                for (int i10 = 0; i10 <= linearLayout5.getChildCount() - 1; i10++) {
                    if ((linearLayout5.getChildAt(i10) instanceof Button) && ((String) linearLayout5.getChildAt(i10).getTag()).length() <= 1) {
                        Button button3 = (Button) linearLayout5.getChildAt(i10);
                        String str3 = strArr3[((Integer) vector3.get(i9)).intValue()];
                        String str4 = strArr3[((Integer) vector3.get(i9)).intValue()];
                        button3.setTag(str3);
                        button3.setText(str4);
                        i9++;
                    }
                }
                i7++;
                i8 = i9;
            }
        }
        if (this.Numpad_Ms != null) {
            String[] strArr4 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
            Vector vector4 = new Vector();
            Random random4 = new Random();
            boolean z4 = true;
            while (z4) {
                int nextInt4 = random4.nextInt(10);
                if (!vector4.contains(Integer.valueOf(nextInt4))) {
                    vector4.add(Integer.valueOf(nextInt4));
                }
                if (vector4.size() == 10) {
                    z4 = false;
                }
            }
            int i11 = 0;
            int i12 = 0;
            while (i11 <= this.Numpad_Ms.getChildCount() - 1) {
                LinearLayout linearLayout6 = (LinearLayout) this.Numpad_Ms.getChildAt(i11);
                int i13 = i12;
                for (int i14 = 0; i14 <= linearLayout6.getChildCount() - 1; i14++) {
                    if ((linearLayout6.getChildAt(i14) instanceof Button) && ((String) linearLayout6.getChildAt(i14).getTag()).length() <= 1) {
                        Button button4 = (Button) linearLayout6.getChildAt(i14);
                        String str5 = strArr4[((Integer) vector4.get(i13)).intValue()];
                        String str6 = strArr4[((Integer) vector4.get(i13)).intValue()];
                        button4.setTag(str5);
                        button4.setText(str6);
                        i13++;
                    }
                }
                i11++;
                i12 = i13;
            }
        }
    }

    public static void setNO_OFF(boolean z) {
        m_bscreenprotect = z;
    }

    public void addTip(String str, int i, int i2, int i3, int i4, int[] iArr) {
        String str2 = "center";
        double width = ((Activity) this.m_context).getWindowManager().getDefaultDisplay().getWidth();
        double d = i / width;
        if (d > 0.0d && d < 0.15d) {
            str2 = "left";
        } else if (d <= 0.9d || d >= 1.0d) {
            double d2 = i3 / width;
            if (d2 > 0.19d && d2 < 0.26d) {
                str2 = "bottom";
            }
        } else {
            str2 = "right";
        }
        LTTipView lTTipView = new LTTipView(this.m_context, str, i3, i4, str2, iArr);
        this.tips.put(str, lTTipView);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2, 8216, 1);
        layoutParams.gravity = 51;
        lTTipView.measure(0, 0);
        layoutParams.x = i - (lTTipView.getMeasuredWidth() / 2);
        layoutParams.y = (i2 - lTTipView.getMeasuredHeight()) + 8;
        layoutParams.windowAnimations = 16973826;
        ((WindowManager) this.m_context.getSystemService("window")).addView(lTTipView, layoutParams);
        this.poupIsShow = !this.poupIsShow;
    }

    public boolean checkMatch() {
        CONFIG_KEY config_key = CONFIG_KEY.MATCH_REGEX;
        if (getConfigValue(config_key) == null) {
            return true;
        }
        String str = this.SaveCiphertext;
        if (str == null || str.length() <= 0) {
            return false;
        }
        String Decrypt = LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key);
        String str2 = (String) getConfigValue(config_key);
        return Decrypt.matches((String) getConfigValue(config_key));
    }

    @SuppressLint({"NewApi"})
    public void cleanClip() {
        if (((Boolean) getConfigValue(CONFIG_KEY.IS_CLIP)).booleanValue()) {
            return;
        }
        ClipboardManager clipboardManager = (ClipboardManager) this.m_context.getSystemService("clipboard");
        ClipData newPlainText = ClipData.newPlainText("", "");
        clipboardManager.setPrimaryClip(newPlainText);
        clipboardManager.setPrimaryClip(newPlainText);
        clipboardManager.setPrimaryClip(newPlainText);
    }

    public void clear() {
        this.SaveCiphertext = null;
        this.editText.setText("");
    }

    public void dismiss() {
        Context context;
        this.passGuardCallBack.hideAction();
        if (this.PassGuardView != null) {
            if (this.needTextView) {
                String editText = this.insidePGCB.getEditText();
                if (editText != null) {
                    this.tempPGCB.clear();
                    this.tempPGCB.addText(editText);
                } else {
                    this.tempPGCB.clear();
                }
                this.tempPGCB.setCursorSelection(this.insidePGCB.getStartSelection(), this.insidePGCB.getEndSelection());
            }
            if (!((Activity) this.m_context).isFinishing() && (context = this.m_context) != null) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                if (windowManager != null) {
                    windowManager.removeView(this.PassGuardView);
                }
                this.isShowing = false;
            }
            CONFIG_KEY config_key = CONFIG_KEY.KEYBOARD_HIDE_ACTION;
            if (getConfigValue(config_key) != null) {
                ((LTdoAction) getConfigValue(config_key)).doActionFunction();
            }
        }
    }

    public String getCipher2text() {
        String str = this.SaveCiphertext;
        if (str != null && str.length() > 0) {
            CONFIG_KEY config_key = CONFIG_KEY.CIPHER_KEY;
            if (((String) getConfigValue(config_key)) != null) {
                CONFIG_KEY config_key2 = CONFIG_KEY.ECC_KEY;
                if (((String) getConfigValue(config_key2)) != null) {
                    String Decrypt = LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key);
                    return LTPassGuardEncrypt.getCipher2((String) getConfigValue(config_key2), (String) getConfigValue(config_key), Decrypt);
                }
            }
        }
        return null;
    }

    public String getCipher3text() {
        String str = this.SaveCiphertext;
        if (str == null || str.length() <= 0 || ((String) getConfigValue(CONFIG_KEY.CIPHER_KEY)) == null || ((String) getConfigValue(CONFIG_KEY.PUBLIC_KEY)) == null) {
            return null;
        }
        return LTPassGuardEncrypt.getCipher3(LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key));
    }

    public String getCipher4text() {
        String str = this.SaveCiphertext;
        if (str != null && str.length() > 0) {
            CONFIG_KEY config_key = CONFIG_KEY.CIPHER_KEY;
            if (((String) getConfigValue(config_key)) != null && ((String) getConfigValue(CONFIG_KEY.PUBLIC_KEY)) != null) {
                return LTPassGuardEncrypt.getCipher4((String) getConfigValue(config_key), LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key));
            }
        }
        return null;
    }

    public String getCiphertext() {
        String str = this.SaveCiphertext;
        if (str != null && str.length() > 0) {
            CONFIG_KEY config_key = CONFIG_KEY.CIPHER_KEY;
            if (((String) getConfigValue(config_key)) != null) {
                return LTPassGuardEncrypt.getCipherText(LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key), (String) getConfigValue(config_key));
            }
        }
        return null;
    }

    public Object getConfigValue(CONFIG_KEY config_key) {
        if (config_key == null || !this.PassGuardConfig.containsKey(config_key)) {
            return null;
        }
        return this.PassGuardConfig.get(config_key);
    }

    public int getHeight() {
        LinearLayout linearLayout = this.PassGuardView;
        if (linearLayout != null) {
            linearLayout.measure(0, 0);
            return this.PassGuardView.getMeasuredHeight();
        }
        return 0;
    }

    public String getMD5Ciphertext() {
        String str = this.SaveCiphertext;
        if (str != null && str.length() > 0) {
            CONFIG_KEY config_key = CONFIG_KEY.CIPHER_KEY;
            if (((String) getConfigValue(config_key)) != null) {
                return LTPassGuardEncrypt.getCipherText(getMd5(), (String) getConfigValue(config_key));
            }
        }
        return null;
    }

    public String getMSRSACiphertext() {
        String str = this.SaveCiphertext;
        if (str != null && str.length() > 0) {
            CONFIG_KEY config_key = CONFIG_KEY.CIPHER_KEY;
            if (((String) getConfigValue(config_key)) != null) {
                CONFIG_KEY config_key2 = CONFIG_KEY.PUBLIC_KEY;
                if (((String) getConfigValue(config_key2)) != null) {
                    return LTPassGuardEncrypt.getMSRSAEncrypt(LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key), (String) getConfigValue(config_key), (String) getConfigValue(config_key2));
                }
            }
        }
        return null;
    }

    public String getMd5() {
        String str = this.SaveCiphertext;
        if (str == null || str.length() <= 0) {
            return null;
        }
        return LTPassGuardEncrypt.getMd5(LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key));
    }

    public int[] getPassLevel() {
        int[] iArr = {0, 3};
        String str = this.SaveCiphertext;
        return (str == null || str.length() <= 0) ? iArr : LTPassGuardEncrypt.passlevel(LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key));
    }

    public String getRSACiphertext() {
        String str = this.SaveCiphertext;
        if (str != null && str.length() > 0) {
            CONFIG_KEY config_key = CONFIG_KEY.CIPHER_KEY;
            if (((String) getConfigValue(config_key)) != null) {
                CONFIG_KEY config_key2 = CONFIG_KEY.PUBLIC_KEY;
                if (((String) getConfigValue(config_key2)) != null) {
                    return LTPassGuardEncrypt.getCipherText(LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key), (String) getConfigValue(config_key), (String) getConfigValue(config_key2));
                }
            }
        }
        return null;
    }

    public String getSM2SM4CipherText() {
        String str = this.SaveCiphertext;
        if (str != null && str.length() > 0) {
            CONFIG_KEY config_key = CONFIG_KEY.CIPHER_KEY;
            if (((String) getConfigValue(config_key)) != null) {
                CONFIG_KEY config_key2 = CONFIG_KEY.ECC_KEY;
                if (((String) getConfigValue(config_key2)) != null) {
                    String Decrypt = LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key);
                    return LTPassGuardEncrypt.getSM2SM4CipherText((String) getConfigValue(config_key2), (String) getConfigValue(config_key), Decrypt);
                }
            }
        }
        return null;
    }

    public String getSM2SM4CipherText(String str) {
        CONFIG_KEY config_key = CONFIG_KEY.CIPHER_KEY;
        if (((String) getConfigValue(config_key)) != null) {
            CONFIG_KEY config_key2 = CONFIG_KEY.ECC_KEY;
            if (((String) getConfigValue(config_key2)) != null) {
                return LTPassGuardEncrypt.getSM2SM4CipherText((String) getConfigValue(config_key2), (String) getConfigValue(config_key), str);
            }
        }
        return null;
    }

    public boolean isShowing() {
        if (this.PassGuardView != null) {
            return this.isShowing;
        }
        return false;
    }

    public int isWeakPassword(String str) {
        return LTPassGuardEncrypt.Decrypt(this.SaveCiphertext, this.key).contains(str) ? 1 : 0;
    }

    public void needTextView(boolean z) {
        LinearLayout linearLayout = this.PassGuardView;
        if (linearLayout != null) {
            if (z || this.needTextView != z) {
                if (z) {
                    if (this.insidePGCB == null) {
                        initPGCB();
                    }
                    this.passGuardCallBack = this.insidePGCB;
                    if ((this.PassGuardView.getChildAt(0) instanceof EditText) && !((Activity) this.m_context).isFinishing()) {
                        this.PassGuardView.removeViewAt(0);
                    }
                    this.editText.setInputType(0);
                    this.editText.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                    this.editText.setText(this.tempPGCB.getEditText());
                    this.editText.setSelection(this.tempPGCB.getStartSelection(), this.tempPGCB.getEndSelection());
                    this.PassGuardView.addView(this.editText, 0);
                } else {
                    if ((linearLayout.getChildAt(0) instanceof EditText) && !((Activity) this.m_context).isFinishing()) {
                        this.PassGuardView.removeViewAt(0);
                    }
                    this.passGuardCallBack = this.tempPGCB;
                }
                this.needTextView = z;
            }
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.needTextView = bundle.getBoolean("needTextView");
            String string = bundle.getString("SaveCiphertext");
            this.SaveCiphertext = (string == null || string.length() <= 0) ? null : LTPassGuardEncrypt.Encrypt(LTPassGuardEncrypt.Decrypt(string, bundle.getInt("Key")), this.key);
        }
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("needTextView", this.needTextView);
        bundle.putString("SaveCiphertext", this.SaveCiphertext);
        bundle.putInt("Key", this.key);
        return bundle;
    }

    public void removeTip(String str) {
        View view = this.tips.get(str);
        if (view == null || ((Activity) this.m_context).isFinishing()) {
            return;
        }
        ((WindowManager) this.m_context.getSystemService("window")).removeView(view);
        this.tips.remove(str);
        this.poupIsShow = !this.poupIsShow;
    }

    public void setConfigValue(CONFIG_KEY config_key, Object obj) {
        if (config_key == null || obj == null) {
            return;
        }
        switch (C17278.$SwitchMap$cn$ltzf$passguard$LTPassGuardKeyBoard$CONFIG_KEY[config_key.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                if (!(obj instanceof Boolean)) {
                    return;
                }
                break;
            case 10:
            case 11:
                if (!(obj instanceof Integer)) {
                    return;
                }
                break;
            case 12:
            case 13:
                if (!(obj instanceof LTdoAction)) {
                    return;
                }
                break;
            case 14:
                if (!(obj instanceof SynKeyboardInput)) {
                    return;
                }
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                if (!(obj instanceof String) && ((String) obj).length() > 0) {
                    return;
                }
                break;
        }
        this.PassGuardConfig.put(config_key, obj);
    }

    public void setConfigValue(HashMap<CONFIG_KEY, Object> hashMap) {
        for (Map.Entry<CONFIG_KEY, Object> entry : hashMap.entrySet()) {
            setConfigValue(entry.getKey(), entry.getValue());
        }
    }

    public void show() {
        this.passGuardCallBack.showAction();
        if (this.PassGuardView != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -2, 2, m_bscreenprotect ? 270368 : 262176, 1);
            layoutParams.windowAnimations = ((Integer) LTPassGuardAnimation.getAnimParam()).intValue();
            layoutParams.gravity = 80;
            ((WindowManager) this.m_context.getSystemService("window")).addView(this.PassGuardView, layoutParams);
            this.isShowing = true;
            if (this.Numpad != null && (((Integer) getConfigValue(CONFIG_KEY.IS_REORDER)).intValue() & KEY_CHAOS_SWITCH_VIEW) != 0 && this.Numpad.isShown()) {
                reOrder();
            }
            CONFIG_KEY config_key = CONFIG_KEY.KEYBOARD_SHOW_ACTION;
            if (getConfigValue(config_key) != null) {
                ((LTdoAction) getConfigValue(config_key)).doActionFunction();
            }
        }
    }

    public void stopShow() {
        Thread thread = this.PasswordShowThread;
        if (thread == null || !thread.isAlive()) {
            return;
        }
        this.PasswordShowThread.interrupt();
        this.passGuardCallBack.replaceText();
    }

    public void synckeyboardinput() {
        ((Activity) this.m_context).runOnUiThread(new Runnable() { // from class: cn.ltzf.passguard.LTPassGuardKeyBoard.7
            @Override // java.lang.Runnable
            public void run() {
                String charSequence = LTPassGuardKeyBoard.this.editText.getText().toString();
                if (LTPassGuardKeyBoard.this.SaveCiphertext != null && LTPassGuardKeyBoard.this.SaveCiphertext.length() > 0) {
                    LTPassGuardEncrypt.Decrypt(LTPassGuardKeyBoard.this.SaveCiphertext, LTPassGuardKeyBoard.this.key);
                }
                StringBuilder m22016a = C1730a.m22016a("javascript:document.getElementById('");
                m22016a.append(LTPassGuardKeyBoard.this.m_strid);
                m22016a.append("').value = '");
                m22016a.append(charSequence);
                m22016a.append("';");
                String sb = m22016a.toString();
                WebView webView = LTPassGuardKeyBoard.this.m_webview;
                if (webView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) webView, sb);
                } else {
                    webView.loadUrl(sb);
                }
            }
        });
    }

    public void uninitPassGuardKeyBoard() {
        this.uictrl.destory();
    }
}

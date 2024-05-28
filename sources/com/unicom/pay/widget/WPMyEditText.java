package com.unicom.pay.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.p086v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.bytedance.applog.tracker.Tracker;
import com.unicom.pay.widget.C10720a;
import p477s0.InterfaceC14117b;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPMyEditText extends AppCompatEditText {

    /* renamed from: a */
    public Drawable f20448a;

    /* renamed from: b */
    public boolean f20449b;

    /* renamed from: c */
    public Paint f20450c;

    /* renamed from: d */
    public C10720a f20451d;

    /* renamed from: e */
    public InterfaceC14117b f20452e;

    /* renamed from: f */
    public int f20453f;

    /* renamed from: g */
    public boolean f20454g;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.WPMyEditText$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10708a implements InputFilter {
        @Override // android.text.InputFilter
        public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            return null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.WPMyEditText$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10709b implements InputFilter {
        public C10709b() {
        }

        @Override // android.text.InputFilter
        public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            return TextUtils.isEmpty(WPMyEditText.this.getText().toString()) ? charSequence : WPMyEditText.this.getText().toString();
        }
    }

    /* renamed from: com.unicom.pay.widget.WPMyEditText$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class View$OnFocusChangeListenerC10710c implements View.OnFocusChangeListener {
        public View$OnFocusChangeListenerC10710c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z) {
            Tracker.onFocusChange(view, z);
            boolean z2 = false;
            if (z && WPMyEditText.this.getText().toString().length() >= 1) {
                z2 = true;
            }
            WPMyEditText.this.setClearDrawableVisible(z2);
            InterfaceC14117b interfaceC14117b = WPMyEditText.this.f20452e;
        }
    }

    /* renamed from: com.unicom.pay.widget.WPMyEditText$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10711d implements TextWatcher {

        /* renamed from: a */
        public int f20457a = 0;

        /* renamed from: b */
        public String f20458b = "";

        public C10711d() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0046, code lost:
            if (r2.length() > 24) goto L93;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
            if (r2.length() > 30) goto L93;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0059, code lost:
            r2 = r2.substring(0, r10);
         */
        /* JADX WARN: Removed duplicated region for block: B:48:0x00aa  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x00d5  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x0108  */
        @Override // android.text.TextWatcher
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void afterTextChanged(android.text.Editable r12) {
            /*
                Method dump skipped, instructions count: 439
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.widget.WPMyEditText.C10711d.afterTextChanged(android.text.Editable):void");
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f20458b = charSequence.toString();
            this.f20457a = WPMyEditText.this.getSelectionStart();
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public WPMyEditText(Context context) {
        super(context);
        this.f20454g = false;
        m6055a();
    }

    public WPMyEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f20454g = false;
        m6055a();
    }

    public WPMyEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f20454g = false;
        m6055a();
    }

    /* renamed from: a */
    public final void m6055a() {
        this.f20449b = false;
        this.f20450c = new Paint();
        setSingleLine(true);
        setImeOptions(6);
        this.f20448a = getCompoundDrawables()[2];
        setOnFocusChangeListener(new View$OnFocusChangeListenerC10710c());
        addTextChangedListener(new C10711d());
        setClearDrawableVisible(false);
        this.f20451d = new C10720a();
    }

    @Override // android.support.p086v7.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        this.f20451d.setTarget(super.onCreateInputConnection(editorInfo));
        return this.f20451d;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f20454g) {
            canvas.drawLine(0.0f, getHeight() - 2, getWidth() - 2, getHeight() - 2, this.f20450c);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        if (motionEvent.getAction() == 1) {
            if ((motionEvent.getX() <= ((float) (getWidth() - getTotalPaddingRight())) || motionEvent.getX() >= ((float) (getWidth() - getPaddingRight()))) ? false : false) {
                setText("");
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setBackgroudLine(boolean z) {
        this.f20454g = z;
        this.f20450c.setColor(Color.parseColor("#eeeeee"));
        this.f20450c.setStrokeWidth(2.0f);
    }

    public void setBackspaceListener(C10720a.InterfaceC10721a interfaceC10721a) {
        this.f20451d.f20511a = interfaceC10721a;
    }

    public void setClearDrawableVisible(boolean z) {
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[1], (!z || this.f20449b) ? null : this.f20448a, getCompoundDrawables()[3]);
    }

    public void setReadOnly(boolean z) {
        InputFilter[] inputFilterArr;
        this.f20449b = z;
        if (z) {
            setFocusable(false);
            setFocusableInTouchMode(false);
            inputFilterArr = new InputFilter[]{new C10709b()};
        } else {
            setFocusable(true);
            setFocusableInTouchMode(true);
            inputFilterArr = new InputFilter[]{new C10708a()};
        }
        setFilters(inputFilterArr);
    }

    public void setRule(int i) {
        this.f20453f = i;
        int i2 = 7;
        if (i == 1) {
            i2 = 13;
        } else if (i == 2) {
            i2 = 20;
        } else if (i == 3) {
            i2 = 23;
        } else {
            if (i != 4) {
                if (i != 5) {
                    if (i != 6) {
                        if (i != 7) {
                            if (i != 8) {
                                if (i != 9) {
                                    if (i != 10 && i != 11) {
                                        i2 = 0;
                                    }
                                }
                            }
                        }
                    }
                }
                i2 = 37;
            }
            i2 = 29;
        }
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
    }

    public void setmTextChangedListener(InterfaceC14117b interfaceC14117b) {
        this.f20452e = interfaceC14117b;
    }
}

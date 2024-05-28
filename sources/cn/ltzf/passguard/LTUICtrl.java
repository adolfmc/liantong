package cn.ltzf.passguard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class LTUICtrl {
    private static LTUICtrl INSTANCE;
    private static Object INSTANCE_LOCK = new Object();
    private DisplayMetrics outMetrics;
    private LTPassGuardKeyBoard pkb;
    private XmlPullParser xmlParser = null;
    private Context context = null;
    private String namespace = null;
    private String[] list = null;
    private boolean canButtonPress = true;
    private boolean is_MsNumPad = false;
    private float density = 1.0f;
    private float imagesize = 1.0f;
    private float fontsize = 1.0f;
    private int rate = 1;
    private HashMap<String, Bitmap> caches = new HashMap<>();

    private int dip2px(float f) {
        return (int) ((f * this.context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int getColor(String str) {
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, str);
        if (attributeValue != null) {
            return Color.parseColor(attributeValue);
        }
        return 0;
    }

    private float getFloat(String str) {
        if (str.contains("dp") || str.contains("dip")) {
            return TypedValue.applyDimension(1, Integer.parseInt(str.substring(0, str.contains("dip") ? str.length() - 3 : str.length() - 2)), this.context.getResources().getDisplayMetrics());
        }
        return str.contains("sp") ? TypedValue.applyDimension(2, Integer.parseInt(str.substring(0, str.length() - 2)), this.context.getResources().getDisplayMetrics()) : Float.parseFloat(str);
    }

    private int getGravity() {
        int i;
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, "layout_gravity");
        if (attributeValue == null) {
            attributeValue = this.xmlParser.getAttributeValue(this.namespace, "gravity");
        }
        int i2 = 0;
        if (attributeValue != null) {
            ArrayList arrayList = new ArrayList();
            while (attributeValue.indexOf("|") != -1) {
                arrayList.add(attributeValue.substring(0, attributeValue.indexOf("|")));
                attributeValue = attributeValue.substring(attributeValue.indexOf("|") + 1);
            }
            arrayList.add(attributeValue);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str.equalsIgnoreCase("center")) {
                    i = 17;
                } else if (str.equalsIgnoreCase("CENTER_HORIZONTAL")) {
                    i = 1;
                } else if (str.equalsIgnoreCase("CENTER_VERTICAL")) {
                    i = 16;
                } else if (str.equalsIgnoreCase("LEFT")) {
                    i = 3;
                } else if (str.equalsIgnoreCase("RIGHT")) {
                    i = 5;
                } else if (str.equalsIgnoreCase("BOTTOM")) {
                    i = 80;
                } else if (str.equalsIgnoreCase("TOP")) {
                    i = 48;
                } else if (str.equalsIgnoreCase("CLIP_HORIZONTAL")) {
                    i = 8;
                } else if (str.equalsIgnoreCase("CLIP_VERTICAL")) {
                    i = 128;
                } else if (str.equalsIgnoreCase("FILL")) {
                    i = 119;
                } else if (str.equalsIgnoreCase("FILL_HORIZONTAL")) {
                    i = 7;
                } else if (str.equalsIgnoreCase("FILL_VERTICAL")) {
                    i = 112;
                }
                i2 |= i;
            }
        }
        return i2;
    }

    private void getImage(View view, String str) {
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, str);
        if (attributeValue != null) {
            if (attributeValue.charAt(0) == '#' && str.equalsIgnoreCase("background")) {
                view.setBackgroundColor(Color.parseColor(attributeValue));
            }
            if (attributeValue.charAt(0) == '@' && attributeValue.contains("/")) {
                String substring = attributeValue.substring(attributeValue.indexOf("/") + 1, attributeValue.length());
                String str2 = null;
                if (this.list == null) {
                    this.list = this.context.getResources().getAssets().list("");
                }
                int i = 0;
                while (true) {
                    String[] strArr = this.list;
                    if (i > strArr.length - 1) {
                        break;
                    }
                    if (strArr[i].contains(".")) {
                        String[] strArr2 = this.list;
                        if (strArr2[i].substring(0, strArr2[i].indexOf(".")).equalsIgnoreCase(substring)) {
                            str2 = this.list[i];
                            break;
                        }
                    }
                    i++;
                }
                if (str2 != null) {
                    if (this.canButtonPress && (((view instanceof Button) || (view instanceof ImageButton)) && str.equalsIgnoreCase("background"))) {
                        StateListDrawable stateListDrawable = new StateListDrawable();
                        StringBuilder m22016a = C1730a.m22016a(substring);
                        m22016a.append(str2.contains(".9") ? "_press.9.png" : "_press.png");
                        stateListDrawable.addState(new int[]{16842919}, LoadImage(m22016a.toString()));
                        stateListDrawable.addState(new int[0], LoadImage(str2));
                        view.setBackgroundDrawable(stateListDrawable);
                    } else if (!(view instanceof ImageButton) || !str.equalsIgnoreCase("src")) {
                        view.setBackgroundDrawable(LoadImage(str2));
                    } else {
                        StateListDrawable stateListDrawable2 = new StateListDrawable();
                        StringBuilder m22016a2 = C1730a.m22016a(substring);
                        m22016a2.append(str2.contains(".9") ? "_press.9.png" : "_press.png");
                        String sb = m22016a2.toString();
                        stateListDrawable2.addState(new int[]{16842919}, sb.contains(".9") ? LoadImage(sb) : LoadScaledImage(sb));
                        stateListDrawable2.addState(new int[0], str2.contains(".9") ? LoadImage(str2) : LoadScaledImage(str2));
                        ((ImageButton) view).setImageDrawable(stateListDrawable2);
                    }
                }
            }
        }
    }

    private int getInt(double d) {
        BigDecimal scale = new BigDecimal(d).setScale(4, 4);
        if (scale.intValue() != 0) {
            return scale.intValue();
        }
        return 1;
    }

    private int getInteger(String str) {
        float applyDimension;
        if (str.contains("dp") || str.contains("dip")) {
            applyDimension = TypedValue.applyDimension(1, Integer.parseInt(str.substring(0, str.contains("dip") ? str.length() - 3 : str.length() - 2)), this.context.getResources().getDisplayMetrics());
        } else if (!str.contains("sp")) {
            return Integer.parseInt(str);
        } else {
            applyDimension = TypedValue.applyDimension(2, Integer.parseInt(str.substring(0, str.length() - 2)), this.context.getResources().getDisplayMetrics());
        }
        return (int) applyDimension;
    }

    private int getLayout(String str) {
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, str);
        if (attributeValue != null) {
            if (attributeValue.equalsIgnoreCase("fill_parent")) {
                return -1;
            }
            if (attributeValue.equalsIgnoreCase("wrap_content")) {
                return -2;
            }
            return getInteger(attributeValue);
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.widget.LinearLayout.LayoutParams getLayoutParams() {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.ltzf.passguard.LTUICtrl.getLayoutParams():android.widget.LinearLayout$LayoutParams");
    }

    private float getLayout_Float(String str) {
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, str);
        if (attributeValue != null) {
            if (attributeValue.equalsIgnoreCase("fill_parent")) {
                return -1.0f;
            }
            if (attributeValue.equalsIgnoreCase("wrap_content")) {
                return -2.0f;
            }
            return getFloat(attributeValue);
        }
        return 0.0f;
    }

    private int getOrientation() {
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, "orientation");
        return (attributeValue == null || !attributeValue.equalsIgnoreCase("vertical")) ? 0 : 1;
    }

    private String getText(String str) {
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, str);
        return attributeValue != null ? (attributeValue.charAt(0) != '\\' || attributeValue.length() <= 1) ? attributeValue : attributeValue.substring(1) : "";
    }

    private float getTextSize() {
        float f;
        float f2;
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, "textSize");
        if (attributeValue != null) {
            if (attributeValue.contains("sp") || attributeValue.contains("dp") || attributeValue.contains("dip")) {
                float f3 = getFloat(attributeValue);
                f = this.density;
                f2 = (f3 / f) * this.rate;
            } else {
                f2 = getFloat(attributeValue) * this.rate;
                f = this.density;
            }
            return (f2 / f) + this.fontsize;
        }
        return 0.0f;
    }

    private Typeface getTypeface() {
        int i;
        Typeface typeface = Typeface.DEFAULT;
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, "textStyle");
        if (attributeValue != null) {
            ArrayList arrayList = new ArrayList();
            while (true) {
                i = 0;
                if (attributeValue.indexOf("|") == -1) {
                    break;
                }
                arrayList.add(attributeValue.substring(0, attributeValue.indexOf("|")));
                attributeValue = attributeValue.substring(attributeValue.indexOf("|") + 1);
            }
            arrayList.add(attributeValue);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str.equalsIgnoreCase("BOLD")) {
                    i |= 1;
                } else if (str.equalsIgnoreCase("ITALIC")) {
                    i |= 2;
                } else if (str.equalsIgnoreCase("NORMAL")) {
                    i |= 0;
                }
            }
            return Typeface.create(Typeface.DEFAULT, i);
        }
        return typeface;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v20, types: [cn.ltzf.passguard.LTPGImageButton, android.widget.ImageView, android.view.View] */
    /* JADX WARN: Type inference failed for: r3v21, types: [cn.ltzf.passguard.LTPGButton, android.widget.TextView, android.widget.Button, android.view.View] */
    /* JADX WARN: Type inference failed for: r3v23, types: [android.widget.TextView, android.view.View] */
    /* JADX WARN: Type inference failed for: r6v0, types: [cn.ltzf.passguard.LTUICtrl] */
    @SuppressLint({"NewApi"})
    private View getView() {
        LTPassGuardLL lTPassGuardLL;
        LTPassGuardLL lTPassGuardLL2;
        LTPassGuardLL lTPassGuardLL3 = null;
        boolean z = true;
        while (this.xmlParser.getEventType() != 3) {
            try {
                if (this.xmlParser.getEventType() == 2) {
                    if (z) {
                        this.namespace = "http://schemas.android.com/apk/res/android";
                        if (this.xmlParser.getName().equalsIgnoreCase("LinearLayout")) {
                            LTPassGuardLL lTPassGuardLL4 = new LTPassGuardLL(this.context, this.pkb);
                            try {
                                lTPassGuardLL4.setOrientation(getOrientation());
                                if (getText("tag").contains("NumberGroup_Ms")) {
                                    this.is_MsNumPad = true;
                                }
                                lTPassGuardLL4.setLayoutParams(getLayoutParams());
                                lTPassGuardLL4.setGravity(getGravity());
                                setPadding(lTPassGuardLL4);
                                lTPassGuardLL4.setTag(getText("tag"));
                                setImage(lTPassGuardLL4);
                                lTPassGuardLL4.setVisibility(getVisibility());
                                lTPassGuardLL2 = lTPassGuardLL4;
                            } catch (IOException e) {
                                e = e;
                                lTPassGuardLL3 = lTPassGuardLL4;
                                e.printStackTrace();
                                return lTPassGuardLL3;
                            } catch (XmlPullParserException e2) {
                                e = e2;
                                lTPassGuardLL3 = lTPassGuardLL4;
                                e.printStackTrace();
                                return lTPassGuardLL3;
                            }
                        } else {
                            int i = 17;
                            if (this.xmlParser.getName().equalsIgnoreCase("TextView")) {
                                ?? textView = new TextView(this.context);
                                textView.setLayoutParams(getLayoutParams());
                                if (getGravity() != 0) {
                                    i = getGravity();
                                }
                                textView.setGravity(i);
                                textView.setTextColor(getColor("textColor"));
                                textView.setTypeface(getTypeface());
                                textView.setTextSize(1, getTextSize());
                                textView.setText(getText("text"));
                                lTPassGuardLL = textView;
                            } else if (this.xmlParser.getName().equalsIgnoreCase("Button")) {
                                ?? lTPGButton = new LTPGButton(this.context);
                                lTPGButton.setText(getText("text"));
                                lTPGButton.setAllCaps(false);
                                lTPGButton.setTextColor(getColor("textColor"));
                                if (getGravity() != 0) {
                                    i = getGravity();
                                }
                                lTPGButton.setGravity(i);
                                lTPGButton.setTag(getText("tag"));
                                lTPGButton.setTextSize(1, getTextSize());
                                lTPGButton.setTypeface(getTypeface());
                                lTPGButton.setLayoutParams(getLayoutParams());
                                lTPGButton.setVisibility(getVisibility());
                                setPadding(lTPGButton);
                                lTPassGuardLL = lTPGButton;
                            } else {
                                if (this.xmlParser.getName().equalsIgnoreCase("ImageButton")) {
                                    ?? lTPGImageButton = new LTPGImageButton(this.context);
                                    lTPGImageButton.setTag(getText("tag"));
                                    lTPGImageButton.setLayoutParams(getLayoutParams());
                                    setImage(lTPGImageButton);
                                    lTPGImageButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                    lTPassGuardLL2 = lTPGImageButton;
                                }
                                z = !z;
                            }
                            setImage(lTPassGuardLL);
                            lTPassGuardLL2 = lTPassGuardLL;
                        }
                        lTPassGuardLL3 = lTPassGuardLL2;
                        z = !z;
                    } else if (lTPassGuardLL3 != null) {
                        lTPassGuardLL3.addView(getView());
                    }
                }
                this.xmlParser.next();
            } catch (IOException e3) {
                e = e3;
            } catch (XmlPullParserException e4) {
                e = e4;
            }
        }
        return lTPassGuardLL3;
    }

    private int getVisibility() {
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, "visibility");
        if (attributeValue == null || attributeValue.equalsIgnoreCase("visible")) {
            return 0;
        }
        if (attributeValue.equalsIgnoreCase("invisible")) {
            return 4;
        }
        return attributeValue.equalsIgnoreCase("gone") ? 8 : 0;
    }

    private int px2dip(float f) {
        return (int) ((f / this.context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void setImage(View view) {
        getImage(view, "background");
        getImage(view, "src");
    }

    private void setPadding(View view) {
        String attributeValue = this.xmlParser.getAttributeValue(this.namespace, "padding");
        if (attributeValue != null) {
            view.setPadding(getInteger(attributeValue), getInteger(attributeValue), getInteger(attributeValue), getInteger(attributeValue));
            return;
        }
        String attributeValue2 = this.xmlParser.getAttributeValue(this.namespace, "paddingTop");
        String attributeValue3 = this.xmlParser.getAttributeValue(this.namespace, "paddingLeft");
        String attributeValue4 = this.xmlParser.getAttributeValue(this.namespace, "paddingBottom");
        String attributeValue5 = this.xmlParser.getAttributeValue(this.namespace, "paddingRight");
        int integer = attributeValue2 != null ? getInteger(attributeValue2) : 0;
        view.setPadding(attributeValue3 != null ? getInteger(attributeValue3) : 0, integer, attributeValue5 != null ? getInteger(attributeValue5) : 0, attributeValue4 != null ? getInteger(attributeValue4) : 0);
    }

    public Bitmap GetImage(String str) {
        Bitmap bitmap;
        if (this.caches.containsKey(str)) {
            this.caches.get(str);
        }
        InputStream inputStream = null;
        Bitmap bitmap2 = null;
        inputStream = null;
        try {
            try {
                InputStream open = this.context.getResources().getAssets().open(str);
                try {
                    bitmap2 = getBitmap(open);
                    this.caches.put(str, bitmap2);
                    if (open != null) {
                        try {
                            open.close();
                            return bitmap2;
                        } catch (IOException e) {
                            e = e;
                            e.printStackTrace();
                            return bitmap2;
                        }
                    }
                    return bitmap2;
                } catch (IOException e2) {
                    e = e2;
                    bitmap = bitmap2;
                    inputStream = open;
                    e.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e = e3;
                            bitmap2 = bitmap;
                            e.printStackTrace();
                            return bitmap2;
                        }
                    }
                    return bitmap;
                } catch (Throwable th) {
                    th = th;
                    inputStream = open;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            e = e5;
            bitmap = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [android.graphics.drawable.Drawable] */
    public Drawable LoadImage(String str) {
        Object obj;
        InputStream inputStream;
        if (this.caches.containsKey(str)) {
            return str.contains(".9") ? new NinePatchDrawable(this.context.getResources(), this.caches.get(str), this.caches.get(str).getNinePatchChunk(), new Rect(), null) : new BitmapDrawable(this.context.getResources(), this.caches.get(str));
        }
        Object obj2 = 0;
        try {
            try {
                inputStream = this.context.getResources().getAssets().open(str);
                try {
                    Bitmap bitmap = getBitmap(inputStream);
                    obj2 = str.contains(".9") ? new NinePatchDrawable(this.context.getResources(), bitmap, bitmap.getNinePatchChunk(), new Rect(), null) : new BitmapDrawable(this.context.getResources(), bitmap);
                    this.caches.put(str, bitmap);
                    obj2 = obj2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                            obj2 = obj2;
                        } catch (IOException e) {
                            e = e;
                            e.printStackTrace();
                            return obj2;
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    obj = obj2;
                    obj2 = inputStream;
                    e.printStackTrace();
                    if (obj2 != null) {
                        try {
                            obj2.close();
                        } catch (IOException e3) {
                            e = e3;
                            obj2 = obj;
                            e.printStackTrace();
                            return obj2;
                        }
                    }
                    obj2 = obj;
                    return obj2;
                } catch (Throwable th) {
                    th = th;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                obj = null;
            }
            return obj2;
        } catch (Throwable th2) {
            th = th2;
            inputStream = obj2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0076 A[Catch: IOException -> 0x007a, TRY_ENTER, TRY_LEAVE, TryCatch #0 {IOException -> 0x007a, blocks: (B:11:0x0060, B:27:0x0076), top: B:39:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.drawable.Drawable LoadScaledImage(java.lang.String r7) {
        /*
            r6 = this;
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r0 = r6.caches
            boolean r0 = r0.containsKey(r7)
            if (r0 == 0) goto L1c
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.Context r1 = r6.context
            android.content.res.Resources r1 = r1.getResources()
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r2 = r6.caches
            java.lang.Object r7 = r2.get(r7)
            android.graphics.Bitmap r7 = (android.graphics.Bitmap) r7
            r0.<init>(r1, r7)
            return r0
        L1c:
            r0 = 0
            android.content.Context r1 = r6.context     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            android.content.res.Resources r1 = r1.getResources()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.io.InputStream r1 = r1.open(r7)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            android.graphics.Bitmap r2 = r6.getBitmap(r1)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            int r3 = r2.getWidth()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            float r3 = (float) r3     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            float r4 = r6.imagesize     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            float r3 = r3 * r4
            double r3 = (double) r3     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            int r3 = r6.getInt(r3)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            int r4 = r2.getHeight()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            float r4 = (float) r4     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            float r5 = r6.imagesize     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            float r4 = r4 * r5
            double r4 = (double) r4     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            int r4 = r6.getInt(r4)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r5 = 1
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createScaledBitmap(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            android.graphics.drawable.BitmapDrawable r3 = new android.graphics.drawable.BitmapDrawable     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            android.content.Context r4 = r6.context     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            android.content.res.Resources r4 = r4.getResources()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r3.<init>(r4, r2)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r0 = r6.caches     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L66
            r0.put(r7, r2)     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L66
            if (r1 == 0) goto L7e
            r1.close()     // Catch: java.io.IOException -> L7a
            goto L7e
        L64:
            r7 = move-exception
            goto L70
        L66:
            r7 = move-exception
            goto L7f
        L68:
            r7 = move-exception
            goto L6f
        L6a:
            r7 = move-exception
            r1 = r0
            goto L7f
        L6d:
            r7 = move-exception
            r1 = r0
        L6f:
            r3 = r0
        L70:
            r0 = r1
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L6a
            if (r0 == 0) goto L7e
            r0.close()     // Catch: java.io.IOException -> L7a
            goto L7e
        L7a:
            r7 = move-exception
            r7.printStackTrace()
        L7e:
            return r3
        L7f:
            if (r1 == 0) goto L89
            r1.close()     // Catch: java.io.IOException -> L85
            goto L89
        L85:
            r0 = move-exception
            r0.printStackTrace()
        L89:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.ltzf.passguard.LTUICtrl.LoadScaledImage(java.lang.String):android.graphics.drawable.Drawable");
    }

    public void destory() {
        for (Map.Entry<String, Bitmap> entry : this.caches.entrySet()) {
            entry.getValue().recycle();
        }
        this.caches.clear();
        System.gc();
    }

    public Bitmap getBitmap(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    public int getPerHeight() {
        return this.outMetrics.widthPixels / 9;
    }

    public LTPassGuardKeyBoard getPkb() {
        return this.pkb;
    }

    public float getTextSize(String str) {
        float f;
        float f2;
        if (str != null) {
            if (str.contains("sp") || str.contains("dp") || str.contains("dip")) {
                float f3 = getFloat(str);
                f = this.density;
                f2 = (f3 / f) * this.rate;
            } else {
                f2 = getFloat(str) * this.rate;
                f = this.density;
            }
            return (f2 / f) + this.fontsize;
        }
        return 0.0f;
    }

    public void init() {
        Context context = this.context;
        this.context = context;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.outMetrics = displayMetrics;
        defaultDisplay.getMetrics(displayMetrics);
        this.rate = getInt((this.context.getResources().getConfiguration().orientation == 2 ? this.outMetrics.widthPixels : this.outMetrics.heightPixels) / 480);
        this.density = this.outMetrics.density;
        this.fontsize = 13.0f;
        this.imagesize = this.rate * 0.9f;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

    public void setPkb(LTPassGuardKeyBoard lTPassGuardKeyBoard) {
        this.pkb = lTPassGuardKeyBoard;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0093, code lost:
        if (r6 == null) goto L24;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View toViewInAssets(java.lang.String r5, boolean r6) {
        /*
            r4 = this;
            r4.canButtonPress = r6
            org.xmlpull.v1.XmlPullParser r6 = android.util.Xml.newPullParser()
            r4.xmlParser = r6
            r6 = 0
            android.content.Context r0 = r4.context     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> L8e
            android.content.res.Resources r0 = r0.getResources()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> L8e
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> L8e
            java.io.InputStream r5 = r0.open(r5)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> L8e
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            java.lang.String r2 = "UTF-8"
            r1.<init>(r5, r2)     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            r1.<init>()     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
        L28:
            java.lang.String r2 = r0.readLine()     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            if (r2 == 0) goto L32
            r1.append(r2)     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            goto L28
        L32:
            r0.close()     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            org.xmlpull.v1.XmlPullParser r1 = r4.xmlParser     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            java.io.StringReader r2 = new java.io.StringReader     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            java.lang.String r0 = cn.ltzf.passguard.LTPassGuardEncrypt.Decrypt2(r0)     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            r1.setInput(r2)     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
        L47:
            org.xmlpull.v1.XmlPullParser r0 = r4.xmlParser     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            int r0 = r0.getEventType()     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            r1 = 1
            if (r0 == r1) goto L68
            org.xmlpull.v1.XmlPullParser r0 = r4.xmlParser     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            int r0 = r0.getEventType()     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            if (r0 == 0) goto L59
            goto L62
        L59:
            org.xmlpull.v1.XmlPullParser r0 = r4.xmlParser     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            r0.next()     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            android.view.View r6 = r4.getView()     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
        L62:
            org.xmlpull.v1.XmlPullParser r0 = r4.xmlParser     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            r0.next()     // Catch: java.lang.Throwable -> L75 java.io.IOException -> L77 org.xmlpull.v1.XmlPullParserException -> L7c
            goto L47
        L68:
            if (r5 == 0) goto L73
            r5.close()     // Catch: java.io.IOException -> L6e
            goto L73
        L6e:
            r5 = move-exception
            r3 = r6
            r6 = r5
            r5 = r3
            goto L9a
        L73:
            r5 = r6
            goto L9d
        L75:
            r6 = move-exception
            goto L9e
        L77:
            r0 = move-exception
            r3 = r6
            r6 = r5
            r5 = r3
            goto L88
        L7c:
            r0 = move-exception
            r3 = r6
            r6 = r5
            r5 = r3
            goto L90
        L81:
            r5 = move-exception
            r3 = r6
            r6 = r5
            r5 = r3
            goto L9e
        L86:
            r0 = move-exception
            r5 = r6
        L88:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L81
            if (r6 == 0) goto L9d
            goto L95
        L8e:
            r0 = move-exception
            r5 = r6
        L90:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L81
            if (r6 == 0) goto L9d
        L95:
            r6.close()     // Catch: java.io.IOException -> L99
            goto L9d
        L99:
            r6 = move-exception
        L9a:
            r6.printStackTrace()
        L9d:
            return r5
        L9e:
            if (r5 == 0) goto La8
            r5.close()     // Catch: java.io.IOException -> La4
            goto La8
        La4:
            r5 = move-exception
            r5.printStackTrace()
        La8:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.ltzf.passguard.LTUICtrl.toViewInAssets(java.lang.String, boolean):android.view.View");
    }
}

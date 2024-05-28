package com.bytedance.applog.tracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.location.Location;
import android.preference.PreferenceFragment;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bytedance.applog.C3535a;
import com.bytedance.applog.C3542a2;
import com.bytedance.applog.C3557c;
import com.bytedance.applog.C3560c2;
import com.bytedance.applog.C3591h;
import com.bytedance.applog.C3654o1;
import com.bytedance.applog.C3704u2;
import com.bytedance.applog.C3737y1;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Tracker {

    /* renamed from: a */
    public static float f8821a;

    /* renamed from: b */
    public static float f8822b;

    /* renamed from: c */
    public static int[] f8823c = new int[2];
    public static C3654o1 sClick;

    public static void dismiss(Dialog dialog) {
    }

    public static void dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (C3704u2.f8845b) {
                C3704u2.m17108a("tracker:enter dispatchTouchEvent", (Throwable) null);
            }
            f8821a = motionEvent.getRawX();
            f8822b = motionEvent.getRawY();
        }
    }

    public static void hide(Dialog dialog) {
    }

    public static void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        onClick(compoundButton);
    }

    public static void onCheckedChanged(RadioGroup radioGroup, int i) {
        onClick(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
    }

    public static boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        onClick(view);
        return false;
    }

    public static void onClick(DialogInterface dialogInterface, int i) {
        if (dialogInterface instanceof AlertDialog) {
            onClick(((AlertDialog) dialogInterface).getButton(i));
            return;
        }
        boolean z = true;
        if (C3737y1.f8956h && (dialogInterface instanceof android.support.p086v7.app.AlertDialog)) {
            onClick(((android.support.p086v7.app.AlertDialog) dialogInterface).getButton(i));
            return;
        }
        if (!C3737y1.f8961m || !(dialogInterface instanceof androidx.appcompat.app.AlertDialog)) {
            z = false;
        }
        if (z) {
            onClick(((androidx.appcompat.app.AlertDialog) dialogInterface).getButton(i));
        }
    }

    public static void onFocusChange(View view, boolean z) {
        if (view instanceof TextView) {
            onClick(view);
        }
    }

    public static boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
        onClick(view);
        return true;
    }

    public static void onHiddenChanged(Fragment fragment, boolean z) {
        if (z) {
            C3557c.m17321b();
        } else {
            C3557c.m17324a(fragment);
        }
    }

    public static void onHiddenChanged(ListFragment listFragment, boolean z) {
        if (z) {
            C3557c.m17321b();
        } else {
            C3557c.m17324a(listFragment);
        }
    }

    public static void onHiddenChanged(PreferenceFragment preferenceFragment, boolean z) {
        if (z) {
            C3557c.m17321b();
        } else {
            C3557c.m17324a(preferenceFragment);
        }
    }

    public static void onHiddenChanged(android.support.p083v4.app.Fragment fragment, boolean z) {
        if (z) {
            C3557c.m17321b();
        } else {
            C3557c.m17324a(fragment);
        }
    }

    public static void onHiddenChanged(WebViewFragment webViewFragment, boolean z) {
        if (z) {
            C3557c.m17321b();
        } else {
            C3557c.m17324a(webViewFragment);
        }
    }

    public static void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        onClick(view);
    }

    public static boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return false;
    }

    public static void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        onItemClick(adapterView, view, i, j);
    }

    public static void onLocationChanged(Location location) {
    }

    public static void onLongClick(View view) {
    }

    public static boolean onMenuItemClick(MenuItem menuItem) {
        View m17336a;
        View view = null;
        if (menuItem != null) {
            C3560c2.m17317b();
            View[] m17319a = C3560c2.m17319a();
            try {
                int length = m17319a.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    View view2 = m17319a[i];
                    if (view2.getClass() == C3560c2.f8399d && (m17336a = C3542a2.m17336a(view2, menuItem)) != null) {
                        view = m17336a;
                        break;
                    }
                    i++;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        }
        onClick(view);
        return false;
    }

    public static void onOptionsItemSelected(MenuItem menuItem) {
        onMenuItemClick(menuItem);
    }

    public static void onPageFinished(WebViewClient webViewClient, WebView webView, String str) {
        WebViewJsUtil.injectJs(webView);
    }

    public static void onPageStarted(WebViewClient webViewClient, WebView webView, String str, Bitmap bitmap) {
        WebViewJsUtil.injectJsCallback(webView);
    }

    public static void onPause(Fragment fragment) {
        C3557c.m17321b();
    }

    public static void onPause(ListFragment listFragment) {
        C3557c.m17321b();
    }

    public static void onPause(PreferenceFragment preferenceFragment) {
        C3557c.m17321b();
    }

    public static void onPause(android.support.p083v4.app.Fragment fragment) {
        C3557c.m17321b();
    }

    public static void onPause(WebViewFragment webViewFragment) {
        C3557c.m17321b();
    }

    public static void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
        if (z) {
            onClick(ratingBar);
        }
    }

    public static void onResume(Fragment fragment) {
        C3557c.m17324a(fragment);
    }

    public static void onResume(ListFragment listFragment) {
        C3557c.m17324a(listFragment);
    }

    public static void onResume(PreferenceFragment preferenceFragment) {
        C3557c.m17324a(preferenceFragment);
    }

    public static void onResume(android.support.p083v4.app.Fragment fragment) {
        C3557c.m17324a(fragment);
    }

    public static void onResume(WebViewFragment webViewFragment) {
        C3557c.m17324a(webViewFragment);
    }

    public static void onStopTrackingTouch(SeekBar seekBar) {
        onClick(seekBar);
    }

    public static void setUserVisibleHint(Fragment fragment, boolean z) {
        if (z) {
            C3557c.m17324a(fragment);
        } else {
            C3557c.m17321b();
        }
    }

    public static void setUserVisibleHint(ListFragment listFragment, boolean z) {
        if (z) {
            C3557c.m17324a(listFragment);
        } else {
            C3557c.m17321b();
        }
    }

    public static void setUserVisibleHint(PreferenceFragment preferenceFragment, boolean z) {
        if (z) {
            C3557c.m17324a(preferenceFragment);
        } else {
            C3557c.m17321b();
        }
    }

    public static void setUserVisibleHint(android.support.p083v4.app.Fragment fragment, boolean z) {
        if (z) {
            C3557c.m17324a(fragment);
        } else {
            C3557c.m17321b();
        }
    }

    public static void setUserVisibleHint(WebViewFragment webViewFragment, boolean z) {
        if (z) {
            C3557c.m17324a(webViewFragment);
        } else {
            C3557c.m17321b();
        }
    }

    public static void show(Dialog dialog) {
    }

    public static void onClick(View view) {
        if (view == null || !C3591h.m17286d()) {
            return;
        }
        C3654o1 m17335a = C3542a2.m17335a(view, true);
        if (m17335a == null) {
            C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
            return;
        }
        if (C3704u2.f8845b) {
            sClick = m17335a;
        }
        view.getLocationOnScreen(f8823c);
        int[] iArr = f8823c;
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = (int) (f8821a - i);
        int i4 = (int) (f8822b - i2);
        if (i3 >= 0 && i3 <= view.getWidth() && i4 >= 0 && i4 <= view.getHeight()) {
            m17335a.f8673t = i3;
            m17335a.f8674u = i4;
        }
        f8821a = 0.0f;
        f8822b = 0.0f;
        if (C3704u2.f8845b) {
            StringBuilder m17349a = C3535a.m17349a("tracker:on click: width = ");
            m17349a.append(view.getWidth());
            m17349a.append(" height = ");
            m17349a.append(view.getHeight());
            m17349a.append(" touchX = ");
            m17349a.append(m17335a.f8673t);
            m17349a.append(" touchY = ");
            m17349a.append(m17335a.f8674u);
            C3704u2.m17108a(m17349a.toString(), (Throwable) null);
        }
        C3591h.m17294a(m17335a);
    }
}

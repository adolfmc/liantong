package com.yhao.floatwindow;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Util {
    private static Point sPoint;

    public static View inflate(Context context, int i) {
        return ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(i, (ViewGroup) null);
    }

    public static int getScreenWidth(Context context) {
        if (sPoint == null) {
            sPoint = new Point();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(sPoint);
        }
        return sPoint.x;
    }

    public static int getScreenHeight(Context context) {
        if (sPoint == null) {
            sPoint = new Point();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(sPoint);
        }
        return sPoint.y;
    }

    public static boolean isViewVisible(View view) {
        return view.getGlobalVisibleRect(new Rect());
    }
}

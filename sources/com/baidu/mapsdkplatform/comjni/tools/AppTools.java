package com.baidu.mapsdkplatform.comjni.tools;

import android.os.Bundle;
import com.baidu.platform.comapi.basestruct.ComplexPt;
import com.baidu.platform.comapi.basestruct.Point;
import com.baidu.platform.comjni.tools.ParcelItem;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comjni.tools.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AppTools {
    /* renamed from: a */
    public static ComplexPt m18115a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("strkey", str);
        JNITools.TransGeoStr2ComplexPt(bundle);
        ComplexPt complexPt = new ComplexPt();
        Bundle bundle2 = bundle.getBundle("map_bound");
        if (bundle2 != null) {
            Bundle bundle3 = bundle2.getBundle("ll");
            if (bundle3 != null) {
                complexPt.f7539b = new Point((int) bundle3.getDouble("ptx"), (int) bundle3.getDouble("pty"));
            }
            Bundle bundle4 = bundle2.getBundle("ru");
            if (bundle4 != null) {
                complexPt.f7540c = new Point((int) bundle4.getDouble("ptx"), (int) bundle4.getDouble("pty"));
            }
        }
        for (ParcelItem parcelItem : (ParcelItem[]) bundle.getParcelableArray("poly_line")) {
            if (complexPt.f7541d == null) {
                complexPt.f7541d = new ArrayList<>();
            }
            Bundle bundle5 = parcelItem.getBundle();
            if (bundle5 != null) {
                ParcelItem[] parcelItemArr = (ParcelItem[]) bundle5.getParcelableArray("point_array");
                ArrayList<Point> arrayList = new ArrayList<>();
                for (ParcelItem parcelItem2 : parcelItemArr) {
                    Bundle bundle6 = parcelItem2.getBundle();
                    if (bundle6 != null) {
                        arrayList.add(new Point((int) bundle6.getDouble("ptx"), (int) bundle6.getDouble("pty")));
                    }
                }
                arrayList.trimToSize();
                complexPt.f7541d.add(arrayList);
            }
        }
        complexPt.f7541d.trimToSize();
        complexPt.f7538a = (int) bundle.getDouble("type");
        return complexPt;
    }

    /* renamed from: a */
    public static double m18116a(Point point, Point point2) {
        Bundle bundle = new Bundle();
        bundle.putDouble("x1", point.f7536x);
        bundle.putDouble("y1", point.f7537y);
        bundle.putDouble("x2", point2.f7536x);
        bundle.putDouble("y2", point2.f7537y);
        JNITools.GetDistanceByMC(bundle);
        return bundle.getDouble("distance");
    }

    /* renamed from: a */
    public static String m18117a() {
        return JNITools.GetToken();
    }

    /* renamed from: a */
    public static void m18114a(boolean z, int i) {
        JNITools.openLogEnable(z, i);
    }

    /* renamed from: b */
    public static void m18113b() {
        JNITools.initClass(new Bundle(), 0);
    }
}

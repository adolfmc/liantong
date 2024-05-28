package com.sinovatech.unicom.separatemodule.dialog.action;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface BundleAction {
    boolean getBoolean(String str);

    boolean getBoolean(String str, boolean z);

    @Nullable
    Bundle getBundle();

    double getDouble(String str);

    double getDouble(String str, int i);

    float getFloat(String str);

    float getFloat(String str, int i);

    int getInt(String str);

    int getInt(String str, int i);

    ArrayList<Integer> getIntegerArrayList(String str);

    long getLong(String str);

    long getLong(String str, int i);

    <P extends Parcelable> P getParcelable(String str);

    <S extends Serializable> S getSerializable(String str);

    String getString(String str);

    ArrayList<String> getStringArrayList(String str);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.dialog.action.BundleAction$-CC  reason: invalid class name */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        public static int $default$getInt(BundleAction bundleAction, String str, int i) {
            Bundle bundle = bundleAction.getBundle();
            return bundle == null ? i : bundle.getInt(str, i);
        }

        public static long $default$getLong(BundleAction bundleAction, String str, int i) {
            Bundle bundle = bundleAction.getBundle();
            return bundle == null ? i : bundle.getLong(str, i);
        }

        public static float $default$getFloat(BundleAction bundleAction, String str, int i) {
            Bundle bundle = bundleAction.getBundle();
            return bundle == null ? i : bundle.getFloat(str, i);
        }

        public static double $default$getDouble(BundleAction bundleAction, String str, int i) {
            Bundle bundle = bundleAction.getBundle();
            return bundle == null ? i : bundle.getDouble(str, i);
        }

        public static boolean $default$getBoolean(BundleAction bundleAction, String str, boolean z) {
            Bundle bundle = bundleAction.getBundle();
            return bundle == null ? z : bundle.getBoolean(str, z);
        }

        public static String $default$getString(BundleAction bundleAction, String str) {
            Bundle bundle = bundleAction.getBundle();
            if (bundle == null) {
                return null;
            }
            return bundle.getString(str);
        }

        public static Parcelable $default$getParcelable(BundleAction bundleAction, String str) {
            Bundle bundle = bundleAction.getBundle();
            if (bundle == null) {
                return null;
            }
            return bundle.getParcelable(str);
        }

        public static Serializable $default$getSerializable(BundleAction bundleAction, String str) {
            Bundle bundle = bundleAction.getBundle();
            if (bundle == null) {
                return null;
            }
            return bundle.getSerializable(str);
        }

        public static ArrayList $default$getStringArrayList(BundleAction bundleAction, String str) {
            Bundle bundle = bundleAction.getBundle();
            if (bundle == null) {
                return null;
            }
            return bundle.getStringArrayList(str);
        }

        public static ArrayList $default$getIntegerArrayList(BundleAction bundleAction, String str) {
            Bundle bundle = bundleAction.getBundle();
            if (bundle == null) {
                return null;
            }
            return bundle.getIntegerArrayList(str);
        }
    }
}

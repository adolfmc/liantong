package com.bytedance.pangle.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p172b.p174b.C3779a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.j */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3951j {

    /* renamed from: a */
    static volatile ArrayList<String> f9376a;

    /* renamed from: b */
    private static String f9377b;

    /* renamed from: a */
    public static List<String> m16613a() {
        AssetManager assetManager;
        try {
            assetManager = (AssetManager) AssetManager.class.newInstance();
        } catch (Exception e) {
            ZeusLogger.errReport("Zeus/resources_pangle", "Execute 'AssetManager.class.newInstance()' failed. ", e);
            assetManager = null;
        }
        return m16611a(assetManager);
    }

    /* renamed from: a */
    public static List<String> m16611a(AssetManager assetManager) {
        ArrayList arrayList = new ArrayList();
        if (assetManager == null) {
            return arrayList;
        }
        try {
            if (C3950i.m16615i()) {
                Object[] objArr = (Object[]) MethodUtils.invokeMethod(assetManager, "getApkAssets", new Object[0]);
                if (objArr != null && objArr.length > 0) {
                    for (Object obj : objArr) {
                        arrayList.add((String) MethodUtils.invokeMethod(obj, "getAssetPath", new Object[0]));
                    }
                }
            } else {
                int intValue = ((Integer) MethodUtils.invokeMethod(assetManager, "getStringBlockCount", new Object[0])).intValue();
                for (int i = 0; i < intValue; i++) {
                    try {
                        String str = (String) MethodUtils.invokeMethod(assetManager, "getCookieName", Integer.valueOf(i + 1));
                        if (!TextUtils.isEmpty(str)) {
                            arrayList.add(str);
                        }
                    } catch (IndexOutOfBoundsException unused) {
                    }
                }
            }
        } catch (Throwable th) {
            ZeusLogger.errReport("Zeus/resources_pangle", "ResUtils GetAssetsPaths error. ", th);
        }
        return arrayList;
    }

    /* renamed from: a */
    public static boolean m16610a(AssetManager assetManager, String str) {
        try {
            if (C3950i.m16615i()) {
                Object[] objArr = (Object[]) MethodUtils.invokeMethod(assetManager, "getApkAssets", new Object[0]);
                if (objArr != null && objArr.length > 0) {
                    for (Object obj : objArr) {
                        if (TextUtils.equals((String) MethodUtils.invokeMethod(obj, "getAssetPath", new Object[0]), str)) {
                            return true;
                        }
                    }
                }
            } else {
                int intValue = ((Integer) MethodUtils.invokeMethod(assetManager, "getStringBlockCount", new Object[0])).intValue();
                int i = 0;
                while (i < intValue) {
                    i++;
                    if (TextUtils.equals((String) MethodUtils.invokeMethod(assetManager, "getCookieName", Integer.valueOf(i)), str)) {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
            ZeusLogger.errReport("Zeus/resources_pangle", "containsPath error. ", th);
        }
        return false;
    }

    /* renamed from: b */
    public static String m16608b(AssetManager assetManager) {
        List<String> m16611a = m16611a(assetManager);
        StringBuilder sb = new StringBuilder("[");
        if (m16611a.size() > 0) {
            for (String str : m16611a) {
                sb.append(str);
                sb.append(" , ");
            }
            sb.delete(sb.lastIndexOf(" , "), sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: b */
    public static synchronized List<String> m16609b() {
        ArrayList<String> arrayList;
        synchronized (C3951j.class) {
            if (f9376a == null) {
                synchronized (C3951j.class) {
                    if (f9376a == null) {
                        f9376a = new ArrayList<>();
                        boolean z = false;
                        if (C3950i.m16621c()) {
                            try {
                                Resources resources = Zeus.getAppApplication().getResources();
                                f9376a.add(Zeus.getAppApplication().createPackageContext(resources.getString(resources.getIdentifier("android:string/config_webViewPackageName", "string", "android")), 0).getApplicationInfo().sourceDir);
                            } catch (Exception e) {
                                ZeusLogger.m16787w("Zeus/load_pangle", "getWebViewPaths1 failed.", e);
                            }
                        } else if (C3950i.m16617g()) {
                            try {
                                Object invokeStaticMethod = MethodUtils.invokeStaticMethod(Class.forName("android.webkit.WebViewFactory"), "getWebViewContextAndSetProvider", new Object[0]);
                                if (Build.VERSION.SDK_INT >= 29 || (Build.VERSION.SDK_INT == 28 && Build.VERSION.PREVIEW_SDK_INT > 0)) {
                                    z = true;
                                }
                                if (z) {
                                    Collections.addAll(f9376a, m16612a(((Context) invokeStaticMethod).getApplicationInfo()));
                                } else {
                                    f9376a.add(((Context) invokeStaticMethod).getApplicationInfo().sourceDir);
                                }
                            } catch (Exception e2) {
                                ZeusLogger.m16787w("Zeus/load_pangle", "getWebViewPaths2 failed.", e2);
                            }
                        }
                    }
                }
            }
            arrayList = f9376a;
        }
        return arrayList;
    }

    @RequiresApi(api = 21)
    /* renamed from: a */
    private static String[] m16612a(ApplicationInfo applicationInfo) {
        String[] strArr;
        try {
            strArr = (String[]) C3779a.m16966a(ApplicationInfo.class, "resourceDirs").get(applicationInfo);
        } catch (Throwable th) {
            ZeusLogger.errReport("Zeus/load_pangle", "get resourceDirs failed.", th);
            strArr = new String[0];
        }
        String[][] strArr2 = {applicationInfo.splitSourceDirs, applicationInfo.sharedLibraryFiles, strArr};
        ArrayList arrayList = new ArrayList(10);
        if (applicationInfo.sourceDir != null) {
            arrayList.add(applicationInfo.sourceDir);
        }
        for (int i = 0; i < 3; i++) {
            String[] strArr3 = strArr2[i];
            if (strArr3 != null) {
                arrayList.addAll(Arrays.asList(strArr3));
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }
}

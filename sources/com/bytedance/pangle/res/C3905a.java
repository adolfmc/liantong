package com.bytedance.pangle.res;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.C3950i;
import com.bytedance.pangle.util.C3951j;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.bytedance.pangle.res.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3905a {

    /* renamed from: a */
    private static Map<String, Integer> f9290a = new HashMap();

    /* renamed from: b */
    private LinkedHashMap<String, Integer> f9291b = new LinkedHashMap<>();

    static {
        List<String> m16613a = C3951j.m16613a();
        if (m16613a == null || m16613a.size() <= 0) {
            return;
        }
        for (String str : m16613a) {
            f9290a.put(str, 0);
        }
    }

    public C3905a() {
        this.f9291b.put(Zeus.getAppApplication().getApplicationInfo().sourceDir, 0);
    }

    /* renamed from: a */
    public final AssetManager m16729a(AssetManager assetManager, String str, boolean z) {
        AssetManager m16730a;
        if (str.endsWith(".frro")) {
            ZeusLogger.m16788w("Zeus/load_pangle", "AssetManagerProcessor updateAssetManager skip frro. ".concat(String.valueOf(str)));
            return assetManager;
        }
        if (C3950i.m16623a()) {
            if (C3950i.m16620d()) {
                m16730a = m16727c(assetManager, str, z);
                if (!C3951j.m16610a(m16730a, str)) {
                    m16730a = m16728b(assetManager, str, z);
                }
            } else {
                m16730a = m16728b(assetManager, str, z);
            }
        } else {
            m16730a = m16730a(assetManager, str);
        }
        synchronized (this.f9291b) {
            this.f9291b.put(str, 0);
        }
        return m16730a;
    }

    /* renamed from: c */
    private static AssetManager m16727c(AssetManager assetManager, String str, boolean z) {
        int i = 3;
        Throwable th = null;
        int i2 = 3;
        while (true) {
            int i3 = i2 - 1;
            if (i2 < 0) {
                break;
            }
            try {
                synchronized (assetManager) {
                    int i4 = 0;
                    for (int i5 = 0; i5 < i; i5++) {
                        if (C3950i.m16621c()) {
                            i4 = ((Integer) MethodUtils.invokeMethod(assetManager, "addAssetPathNative", new Object[]{str}, new Class[]{String.class})).intValue();
                        } else if (Build.VERSION.SDK_INT >= 24 && Build.VERSION.SDK_INT <= 25) {
                            i4 = ((Integer) MethodUtils.invokeMethod(assetManager, "addAssetPathNative", new Object[]{str, Boolean.valueOf(z)}, new Class[]{String.class, Boolean.TYPE})).intValue();
                        }
                        if (i4 != 0) {
                            break;
                        }
                    }
                    if (i4 == 0) {
                        ZeusLogger.m16788w("Zeus/load_pangle", "AssetManagerProcessor invoke AssetManager.appendAssetPathSafely() failed, cookie = " + i4 + " " + str);
                    } else {
                        Object readField = FieldUtils.readField(assetManager, "mStringBlocks");
                        int length = readField != null ? Array.getLength(readField) : 0;
                        int intValue = ((Integer) MethodUtils.invokeMethod(assetManager, "getStringBlockCount", new Object[0])).intValue();
                        Object newInstance = Array.newInstance(readField.getClass().getComponentType(), intValue);
                        for (int i6 = 0; i6 < intValue; i6++) {
                            if (i6 < length) {
                                Array.set(newInstance, i6, Array.get(readField, i6));
                            } else {
                                Array.set(newInstance, i6, MethodUtils.invokeConstructor(readField.getClass().getComponentType(), new Object[]{Long.valueOf(((Long) MethodUtils.invokeMethod(assetManager, "getNativeStringBlock", new Object[]{Integer.valueOf(i6)}, new Class[]{Integer.TYPE})).longValue()), Boolean.TRUE}, new Class[]{Long.TYPE, Boolean.TYPE}));
                            }
                        }
                        FieldUtils.writeField(assetManager, "mStringBlocks", newInstance);
                        ZeusLogger.m16794d("Zeus/load_pangle", "AssetManagerProcessor appendAssetPathSafely success, sourceDir = ".concat(String.valueOf(str)));
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                i2 = i3;
                i = 3;
            }
        }
        if (th != null) {
            if (!TextUtils.equals(Build.BRAND.toLowerCase(), "samsung")) {
                ZeusLogger.errReport("Zeus/load_pangle", "AssetManagerProcessor appendAssetPathSafely failed, sourceDir = ".concat(String.valueOf(str)), th);
            }
            ZeusLogger.m16787w("Zeus/load_pangle", "AssetManagerProcessor appendAssetPathSafely failed, sourceDir = ".concat(String.valueOf(str)), th);
        }
        return assetManager;
    }

    /* renamed from: a */
    private AssetManager m16730a(AssetManager assetManager, String str) {
        AssetManager assetManager2;
        List<String> m16611a = C3951j.m16611a(assetManager);
        ArrayList<String> arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        for (String str2 : m16611a) {
            if (!f9290a.containsKey(str2) && !this.f9291b.containsKey(str2) && !str2.equals(str)) {
                arrayList.add(str2);
            }
        }
        ZeusLogger.m16792i("Zeus/load_pangle", "AssetManagerProcessor newAssetManager, runtimeAdditionalAssets path = ".concat(String.valueOf(str)));
        try {
            if (assetManager.getClass().getName().equals("android.content.res.BaiduAssetManager")) {
                assetManager2 = (AssetManager) Class.forName("android.content.res.BaiduAssetManager").getConstructor(new Class[0]).newInstance(new Object[0]);
            } else {
                assetManager2 = (AssetManager) AssetManager.class.newInstance();
            }
            ZeusLogger.m16792i("Zeus/load_pangle", "AssetManagerProcessor newAssetManager = ".concat(String.valueOf(assetManager2)));
            synchronized (this.f9291b) {
                for (Map.Entry<String, Integer> entry : this.f9291b.entrySet()) {
                    if (!f9290a.containsKey(entry.getKey())) {
                        sb.append(entry.getKey());
                        m16728b(assetManager2, entry.getKey(), false);
                    }
                }
            }
            if (!sb.toString().contains(Zeus.getAppApplication().getApplicationInfo().sourceDir)) {
                m16728b(assetManager2, Zeus.getAppApplication().getApplicationInfo().sourceDir, false);
                ZeusLogger.m16788w("Zeus/load_pangle", "AssetManagerProcessor newAssetManager lost host path : " + f9290a.containsKey(Zeus.getAppApplication().getApplicationInfo().sourceDir));
            }
            sb.append(str);
            m16728b(assetManager2, str, false);
            if (!arrayList.isEmpty()) {
                for (String str3 : arrayList) {
                    sb.append(str3);
                    m16728b(assetManager2, str3, false);
                }
            }
            if ((Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23) && !sb.toString().toLowerCase().contains("webview")) {
                try {
                    Resources resources = Zeus.getAppApplication().getResources();
                    String str4 = Zeus.getAppApplication().createPackageContext(resources.getString(resources.getIdentifier("android:string/config_webViewPackageName", "string", "android")), 0).getApplicationInfo().sourceDir;
                    if (!TextUtils.isEmpty(str4)) {
                        m16728b(assetManager2, str4, false);
                    }
                } catch (Exception e) {
                    ZeusLogger.errReport("Zeus/load_pangle", "AssetManagerProcessor newAssetManager appendAsset webview failed.", e);
                }
            }
            assetManager = assetManager2;
        } catch (Exception e2) {
            ZeusLogger.errReport("Zeus/load_pangle", "AssetManagerProcessor newAssetManager failed.", e2);
            m16728b(assetManager, str, false);
        }
        try {
            MethodUtils.invokeMethod(assetManager, "ensureStringBlocks", new Object[0]);
            ZeusLogger.m16792i("Zeus/load_pangle", "AssetManagerProcessor ensureStringBlocks");
        } catch (Exception e3) {
            ZeusLogger.errReport("Zeus/load_pangle", "AssetManagerProcessor ensureStringBlocks failed.", e3);
        }
        return assetManager;
    }

    /* renamed from: b */
    private static AssetManager m16728b(AssetManager assetManager, String str, boolean z) {
        int intValue;
        String str2 = z ? "addAssetPathAsSharedLibrary" : "addAssetPath";
        if ((Build.VERSION.SDK_INT >= 30 || (Build.VERSION.SDK_INT == 29 && Build.VERSION.PREVIEW_SDK_INT > 0)) && !z && str.startsWith("/product/overlay/")) {
            str2 = "addOverlayPath";
        }
        Method accessibleMethod = MethodUtils.getAccessibleMethod(AssetManager.class, str2, String.class);
        if (accessibleMethod == null && z) {
            str2 = "addAssetPath";
            accessibleMethod = MethodUtils.getAccessibleMethod(AssetManager.class, "addAssetPath", String.class);
            ZeusLogger.m16788w("Zeus/load_pangle", "AssetManagerProcessor AssetManager.addAssetPath() invoke addAssetPathAsSharedLibrary failed. use addAssetPath.");
        }
        if (accessibleMethod != null) {
            int i = 3;
            while (true) {
                int i2 = i - 1;
                if (i < 0) {
                    break;
                }
                try {
                    intValue = ((Integer) accessibleMethod.invoke(assetManager, str)).intValue();
                } catch (Exception e) {
                    ZeusLogger.m16787w("Zeus/load_pangle", "AssetManagerProcessor invoke AssetManager.addAssetPath() failed. asSharedLibrary = " + z + ", methodName = " + str2, e);
                }
                if (intValue != 0) {
                    ZeusLogger.m16792i("Zeus/load_pangle", "AssetManagerProcessor invoke AssetManager.addAssetPath() success, cookie = " + intValue + ", path = " + str);
                    break;
                }
                ZeusLogger.m16788w("Zeus/load_pangle", "AssetManagerProcessor invoke AssetManager.addAssetPath() failed, cookie = " + intValue + " " + str);
                i = i2;
            }
        } else {
            ZeusLogger.m16788w("Zeus/load_pangle", "AssetManagerProcessor reflect AssetManager.addAssetPath() failed. addAssetPathMethod == null. asSharedLibrary = " + z + " methodName:" + str2);
        }
        return assetManager;
    }
}

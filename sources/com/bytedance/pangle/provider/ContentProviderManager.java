package com.bytedance.pangle.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.Keep;
import android.support.annotation.RequiresApi;
import android.support.p083v4.content.FileProvider;
import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p176d.C3793d;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ContentProviderManager {
    public static final String PLUGIN_PKG_NAME = "plugin_pkg_name";
    public static final String PLUGIN_PROCESS_NAME = "process_name";
    public static final String PROVIDER_PARAM_FEILD = "provider_params";
    public static final String PROVIDER_PLUGIN_AUTHORITY = "provider_params";
    public static final String PROVIDER_PROXY_URI = "provider_proxy_uri";
    public static final String PROVIDER_URI = "uri";
    private static ContentProviderManager sInstance;
    private final Map<C3891b, C3890a> mContentProviderMap = new HashMap();
    private final Map<String, String> mAuthorityProcessNameMap = new HashMap();
    private final Map<String, C3892c> mSystemProviderInfoMap = new HashMap();

    /* renamed from: com.bytedance.pangle.provider.ContentProviderManager$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3891b {

        /* renamed from: a */
        public final String f9276a;

        /* renamed from: b */
        public final String f9277b;

        /* renamed from: c */
        public final String f9278c;

        public C3891b(String str, String str2, String str3) {
            this.f9276a = str2;
            this.f9277b = str3;
            this.f9278c = str;
        }

        public boolean equals(Object obj) {
            if (obj instanceof C3891b) {
                C3891b c3891b = (C3891b) obj;
                return TextUtils.equals(this.f9278c, c3891b.f9278c) && TextUtils.equals(this.f9277b, c3891b.f9277b) && TextUtils.equals(this.f9276a, c3891b.f9276a);
            }
            return false;
        }

        public int hashCode() {
            if (Build.VERSION.SDK_INT >= 19) {
                return Objects.hash(this.f9276a, this.f9277b, this.f9278c);
            }
            return (this.f9276a + this.f9277b + this.f9276a).hashCode();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.provider.ContentProviderManager$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class C3892c extends C3891b {

        /* renamed from: d */
        public final ProviderInfo f9279d;

        public C3892c(String str, String str2, ProviderInfo providerInfo) {
            super(str, str2, providerInfo.authority);
            this.f9279d = providerInfo;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.provider.ContentProviderManager$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class C3890a {

        /* renamed from: a */
        public final C3891b f9273a;

        /* renamed from: b */
        public final ProviderInfo f9274b;

        /* renamed from: c */
        public final PluginContentProvider f9275c;

        public C3890a(C3891b c3891b, ProviderInfo providerInfo, PluginContentProvider pluginContentProvider) {
            this.f9274b = providerInfo;
            this.f9273a = c3891b;
            this.f9275c = pluginContentProvider;
        }
    }

    private ContentProviderManager() {
    }

    public static ContentProviderManager getInstance() {
        if (sInstance == null) {
            synchronized (ContentProviderManager.class) {
                if (sInstance == null) {
                    sInstance = new ContentProviderManager();
                }
            }
        }
        return sInstance;
    }

    public Map<String, C3892c> getSystemProviderInfoMap() {
        return this.mSystemProviderInfoMap;
    }

    public String getPluginProcessNameByAuthority(String str) {
        return this.mAuthorityProcessNameMap.get(str);
    }

    @RequiresApi(api = 16)
    public Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal, String str3) {
        return C3893a.m16748a(contentResolver, uri, strArr, str, strArr2, str2, cancellationSignal, str3);
    }

    public Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, String str3) {
        return C3893a.m16747a(contentResolver, uri, strArr, str, strArr2, str2, str3);
    }

    @RequiresApi(api = 26)
    public Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal, String str) {
        return C3893a.m16749a(contentResolver, uri, strArr, bundle, cancellationSignal, str);
    }

    public String getType(ContentResolver contentResolver, Uri uri, String str) {
        return C3893a.m16752a(contentResolver, uri, str);
    }

    public Uri insert(ContentResolver contentResolver, Uri uri, ContentValues contentValues, String str) {
        return C3893a.m16755a(contentResolver, uri, contentValues, str);
    }

    public Uri insert(ContentResolver contentResolver, Uri uri, ContentValues contentValues, Bundle bundle, String str) {
        return C3893a.m16756a(contentResolver, uri, contentValues, bundle, str);
    }

    public int delete(ContentResolver contentResolver, Uri uri, String str, String[] strArr, String str2) {
        return C3893a.m16750a(contentResolver, uri, str, strArr, str2);
    }

    public int delete(ContentResolver contentResolver, Uri uri, Bundle bundle, String str) {
        return C3893a.m16753a(contentResolver, uri, bundle, str);
    }

    public int update(ContentResolver contentResolver, Uri uri, ContentValues contentValues, String str, String[] strArr, String str2) {
        return C3893a.m16754a(contentResolver, uri, contentValues, str, strArr, str2);
    }

    public int update(ContentResolver contentResolver, Uri uri, ContentValues contentValues, Bundle bundle, String str) {
        return C3893a.m16742b(contentResolver, uri, contentValues, bundle, str);
    }

    public Bundle call(ContentResolver contentResolver, Uri uri, String str, String str2, Bundle bundle, String str3) {
        return C3893a.m16751a(contentResolver, uri, str, str2, bundle, str3);
    }

    public final Bundle call(ContentResolver contentResolver, String str, String str2, String str3, Bundle bundle, String str4) {
        return C3893a.m16746a(contentResolver, str, str2, str3, bundle, str4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isPluginProvider(Uri uri) {
        if (uri == null) {
            return true;
        }
        String authority = uri.getAuthority();
        Set<String> keySet = this.mAuthorityProcessNameMap.keySet();
        return keySet != null && keySet.contains(authority);
    }

    public PluginContentProvider getPluginProvider(C3891b c3891b) {
        C3890a c3890a = this.mContentProviderMap.get(c3891b);
        if (c3890a == null) {
            return null;
        }
        return c3890a.f9275c;
    }

    public void installContentProviders(Collection<ProviderInfo> collection, Plugin plugin) {
        if (collection == null || collection.size() == 0 || plugin == null) {
            return;
        }
        Zeus.getAppApplication();
        String m16922a = C3793d.m16922a(C3793d.m16924a());
        for (ProviderInfo providerInfo : collection) {
            if (ZeusLogger.isDebug()) {
                StringBuilder sb = new StringBuilder(128);
                sb.append("Install plugin provider [authority:");
                sb.append(providerInfo.authority);
                sb.append("] - [className:");
                sb.append(providerInfo.name);
                sb.append("]");
                ZeusLogger.m16790v("Zeus/provider_pangle", sb.toString());
            }
            installProvider(m16922a, providerInfo, plugin);
        }
    }

    private void installProvider(String str, ProviderInfo providerInfo, Plugin plugin) {
        if (providerInfo == null) {
            ZeusLogger.m16788w("Zeus/provider_pangle", "ProviderInfo is null !! can not install plugin provider ， plugin-mPkgName：【" + plugin.mPkgName + "】");
            return;
        }
        if (TextUtils.equals(str, providerInfo.processName)) {
            ZeusLogger.m16790v("Zeus/provider_pangle", "Start install plugin provider [authority:" + providerInfo.authority + "] [className:" + providerInfo.name + "]");
            try {
                PluginContentProvider instantiateProvider = instantiateProvider(plugin, providerInfo);
                if (instantiateProvider == null) {
                    return;
                }
                instantiateProvider.attachInfo(ZeusTransformUtils.wrapperContext(plugin.mHostApplication, plugin.mPkgName), providerInfo);
                ZeusLogger.m16790v("Zeus/provider_pangle", "Install plugin provider finish and invoke plugin provider attachInfo(onCreate) method finish [className:" + providerInfo.name + "]");
                C3891b c3891b = new C3891b(providerInfo.packageName, providerInfo.processName, providerInfo.authority);
                this.mContentProviderMap.put(c3891b, new C3890a(c3891b, providerInfo, instantiateProvider));
            } catch (Exception e) {
                ZeusLogger.m16787w("Zeus/provider_pangle", "Instantiating Exception : ", e);
                return;
            }
        }
        this.mAuthorityProcessNameMap.put(providerInfo.authority, providerInfo.processName);
    }

    private PluginContentProvider instantiateProvider(Plugin plugin, ProviderInfo providerInfo) {
        Object newInstance = plugin.mClassLoader.loadClass(providerInfo.name).newInstance();
        if (newInstance instanceof FileProvider) {
            if (providerInfo.metaData == null) {
                throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data. provider:".concat(String.valueOf(newInstance)));
            }
            com.bytedance.pangle.FileProvider.m16986a(plugin, plugin.mResources.getXml(providerInfo.metaData.getInt("android.support.FILE_PROVIDER_PATHS")));
            return null;
        }
        return (PluginContentProvider) newInstance;
    }

    public void initSystemContentProviderInfo() {
        try {
            ProviderInfo[] providerInfoArr = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 8).providers;
            if (providerInfoArr == null || providerInfoArr.length == 0) {
                return;
            }
            for (ProviderInfo providerInfo : providerInfoArr) {
                if (providerInfo != null && providerInfo.authority != null && providerInfo.authority.contains(".pangle.provider.proxy.")) {
                    try {
                        ZeusLogger.m16794d("Zeus/provider_pangle", "Need to init system provider info start [packageNam:=" + providerInfo.packageName + "],[processName=" + providerInfo.processName + "],[authority:" + providerInfo.authority + "]");
                        if (providerInfo.authority.contains(Zeus.getAppApplication().getPackageName() + ".pangle.provider.proxy.")) {
                            String m16922a = C3793d.m16922a(providerInfo.processName);
                            this.mSystemProviderInfoMap.put(m16922a, new C3892c(Zeus.getAppApplication().getPackageName(), m16922a, providerInfo));
                            ZeusLogger.m16794d("Zeus/provider_pangle", "Init system provider info finish [packageNam:=" + providerInfo.packageName + "],[processName=" + providerInfo.processName + "],[authority:" + providerInfo.authority + "]");
                        }
                    } catch (Exception e) {
                        ZeusLogger.errReport("Zeus/provider_pangle", "Init system contentProviderInfo [authority:" + providerInfo.authority + "],exception：", e);
                    }
                }
            }
        } catch (Throwable th) {
            ZeusLogger.errReport("Zeus/provider_pangle", "init System ContentProviderInfo exception：", th);
        }
    }
}

package com.mob.tools.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.view.View;
import com.mob.commons.C5873u;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DeviceHelper implements PublicMemberKeeper {

    /* renamed from: a */
    private static DeviceHelper f15175a = new DeviceHelper();

    /* renamed from: b */
    private Context f15176b;

    public String getDefaultIMPkg() {
        return null;
    }

    public String getDeviceId() {
        return null;
    }

    public String getIMEI() {
        return null;
    }

    public String getIMSI() {
        return null;
    }

    public int getPlatformCode() {
        return 1;
    }

    public boolean getSdcardState() {
        return false;
    }

    public String getSerialno() {
        return null;
    }

    public String getSimSerialNumber() {
        return null;
    }

    public Activity getTopActivity() {
        return null;
    }

    public String[] queryIMEI() {
        return null;
    }

    public String[] queryIMSI() {
        return null;
    }

    public static synchronized DeviceHelper getInstance(Context context) {
        DeviceHelper deviceHelper;
        synchronized (DeviceHelper.class) {
            if (f15175a.f15176b == null && context != null) {
                f15175a.f15176b = context.getApplicationContext();
            }
            deviceHelper = f15175a;
        }
        return deviceHelper;
    }

    public boolean isRooted() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11590a();
    }

    public String getSSID() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11575a(false);
    }

    public String getBssid() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11540b(false);
    }

    public String getModel() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11517l();
    }

    public String getManufacturer() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11516m();
    }

    public String getSystemProperties(String str) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11582a(str);
    }

    public int getOSVersionInt() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11510s();
    }

    public String getOSVersionName() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11509t();
    }

    public String getOSLanguage() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11508u();
    }

    public String getAppLanguage() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11615B();
    }

    public String getOSCountry() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11507v();
    }

    public String getScreenSize() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11614C();
    }

    public String getCarrier() {
        return getCarrier(false);
    }

    public String getCarrier(boolean z) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11535c(z);
    }

    public String getCarrierName() {
        return getCarrierName(false);
    }

    public String getCarrierName(boolean z) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11532d(z);
    }

    public String getSignMD5() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11598S();
    }

    public String getSignMD5(String str) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11537c(str);
    }

    public String getNetworkType() {
        return getNetworkType(false);
    }

    public String getNetworkType(boolean z) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11526f(z);
    }

    public boolean checkNetworkAvailable() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11610G();
    }

    public String getNetworkTypeForStatic() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11612E();
    }

    public String getDetailNetworkTypeForStatic() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11611F();
    }

    public String getDeviceKey() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11600Q();
    }

    public String getDeviceKey(boolean z) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11523g(z);
    }

    public String getPackageName() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11597T();
    }

    public String getAppName() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11596U();
    }

    public String getAppName(String str) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11533d(str);
    }

    public int getAppVersion() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11595V();
    }

    public String getAppVersionName() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11594W();
    }

    public ArrayList<HashMap<String, String>> getIA(boolean z) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11571a(z, false);
    }

    public ArrayList<HashMap<String, String>> getSA() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11601P();
    }

    public boolean checkPermission(String str) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11530e(str);
    }

    public String getSdcardPath() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11599R();
    }

    public String getAdvertisingID() throws Throwable {
        return C6031c.m11708a(this.f15176b).m11704d().mo11519j();
    }

    public Location getLocation(int i, int i2, boolean z) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11589a(i, i2, z);
    }

    public ArrayList<HashMap<String, Object>> getNeighboringCellInfo() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11512q();
    }

    public String getDeviceType() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11514o();
    }

    public HashMap<String, Object> getCurrentWifiInfo() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11511r();
    }

    public boolean isPackageInstalled(String str) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11543b(str);
    }

    public HashMap<String, Object> getCPUInfo() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11506w();
    }

    public ArrayList<ArrayList<String>> getTTYDriversInfo() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11505x();
    }

    public String getQemuKernel() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11504y();
    }

    public HashMap<String, HashMap<String, Long>> getSizeInfo() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11503z();
    }

    public HashMap<String, Long> getMemoryInfo() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11616A();
    }

    public String getMIUIVersion() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11518k();
    }

    /* renamed from: cx */
    public boolean m11277cx() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11547b();
    }

    public boolean checkPad() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11538c();
    }

    public boolean usbEnable() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11522h();
    }

    public boolean devEnable() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11525g();
    }

    public boolean checkUA() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11528f();
    }

    public boolean vpn() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11531e();
    }

    public boolean debugable() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11534d();
    }

    public boolean isWifiProxy() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11520i();
    }

    public String getTimezone() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11608I();
    }

    public String getFlavor() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11607J();
    }

    public String getBaseband() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11606K();
    }

    public String getBoardFromSysProperty() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11605L();
    }

    public String getBoardPlatform() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11604M();
    }

    public int getDataNtType() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11609H();
    }

    public String getBrand() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11515n();
    }

    public boolean isInMainProcess() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11593X();
    }

    public String getCurrentProcessName() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11592Y();
    }

    public Object getSystemServiceSafe(String str) {
        return C5873u.m12171d(str);
    }

    public <T> T invokeInstanceMethod(Object obj, String str, Object... objArr) {
        return (T) ReflectHelper.invokeInstanceMethodNoThrow(obj, str, null, objArr);
    }

    public <T> T invokeInstanceMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) {
        return (T) ReflectHelper.invokeInstanceMethod(obj, str, objArr, clsArr, null);
    }

    public Context getApplication() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11570aa();
    }

    public List<ResolveInfo> queryIntentServices(Intent intent, int i) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11588a(intent, i);
    }

    public ResolveInfo resolveActivity(Intent intent, int i) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11545b(intent, i);
    }

    public PackageInfo getPInfo(String str, int i) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11574a(false, 0, str, i);
    }

    public PackageInfo getPInfo(boolean z, String str, int i) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11574a(z, 0, str, i);
    }

    public PackageInfo getPInfo(int i, String str, int i2) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11574a(false, i, str, i2);
    }

    public String getIPAddress() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11603N();
    }

    public void hideSoftInput(View view) {
        C5873u.m12184a(view);
    }

    public void showSoftInput(View view) {
        C5873u.m12177b(view);
    }

    public String getDeviceData() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11569ab();
    }

    public String getDeviceDataNotAES() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11568ac();
    }

    public String Base64AES(String str, String str2) {
        return Data.Base64AES(str, str2);
    }

    public static Object currentActivityThread() {
        return C5873u.m12178b();
    }

    public long getAppLastUpdateTime() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11567ad();
    }

    public String getDeviceName() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11566ae();
    }

    public String getCgroup() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11565af();
    }

    public String getCInfo() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11564ag();
    }

    public String getOD() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11563ah();
    }

    public String getODH() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11562ai();
    }

    public HashMap<String, Object> getALLD() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11561aj();
    }

    public ApplicationInfo getAInfo() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11560ak();
    }

    public ArrayList<HashMap<String, Object>> getAvailableWifiListOneKey() {
        return C6031c.m11708a(this.f15176b).m11704d().mo11559al();
    }

    public ApplicationInfo getAInfo(String str, int i) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11581a(str, i);
    }

    public ApplicationInfo getAInfo(boolean z, String str, int i) {
        return C6031c.m11708a(this.f15176b).m11704d().mo11573a(z, str, i);
    }
}

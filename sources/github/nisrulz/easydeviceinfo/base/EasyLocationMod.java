package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.RequiresPermission;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyLocationMod {
    private final boolean hasCoarseLocationPermission;
    private final boolean hasFineLocationPermission;

    /* renamed from: lm */
    private LocationManager f24307lm;

    public EasyLocationMod(Context context) {
        this.hasFineLocationPermission = PermissionUtil.hasPermission(context, "android.permission.ACCESS_FINE_LOCATION");
        this.hasCoarseLocationPermission = PermissionUtil.hasPermission(context, "android.permission.ACCESS_COARSE_LOCATION");
        if (this.hasCoarseLocationPermission || this.hasFineLocationPermission) {
            this.f24307lm = (LocationManager) context.getSystemService("location");
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public final double[] getLatLong() {
        double[] dArr = {0.0d, 0.0d};
        if (this.hasCoarseLocationPermission && this.f24307lm.isProviderEnabled("network")) {
            Location lastKnownLocation = this.f24307lm.getLastKnownLocation("network");
            if (lastKnownLocation != null) {
                dArr[0] = lastKnownLocation.getLatitude();
                dArr[1] = lastKnownLocation.getLongitude();
            }
        } else if (this.hasFineLocationPermission) {
            boolean isProviderEnabled = this.f24307lm.isProviderEnabled("gps");
            Location location = null;
            Location lastKnownLocation2 = this.f24307lm.isProviderEnabled("network") ? this.f24307lm.getLastKnownLocation("network") : null;
            Location lastKnownLocation3 = isProviderEnabled ? this.f24307lm.getLastKnownLocation("gps") : null;
            if (lastKnownLocation3 != null && lastKnownLocation2 != null) {
                location = getBetterLocation(lastKnownLocation3, lastKnownLocation2);
            }
            if (location == null) {
                location = this.f24307lm.getLastKnownLocation("passive");
            }
            if (location != null) {
                dArr[0] = location.getLatitude();
                dArr[1] = location.getLongitude();
            }
        }
        return dArr;
    }

    private Location getBetterLocation(Location location, Location location2) {
        return location.getAccuracy() >= location2.getAccuracy() ? location : location2;
    }
}

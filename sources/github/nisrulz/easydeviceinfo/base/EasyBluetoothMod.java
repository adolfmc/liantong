package github.nisrulz.easydeviceinfo.base;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyBluetoothMod {
    private final Context context;

    public EasyBluetoothMod(Context context) {
        this.context = context;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.BLUETOOTH")
    @Deprecated
    public final String getBluetoothMAC() {
        String str = "00:00:00:00:00:00";
        if (PermissionUtil.hasPermission(this.context, "android.permission.BLUETOOTH")) {
            if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 26) {
                str = Settings.Secure.getString(this.context.getContentResolver(), "bluetooth_address");
            } else {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    str = defaultAdapter.getAddress();
                }
            }
        }
        return CheckValidityUtil.checkValidData(str);
    }

    @RequiresPermission("android.permission.BLUETOOTH")
    public final boolean hasBluetoothLeAdvertising() {
        return Build.VERSION.SDK_INT >= 21 && hasBluetoothLe() && BluetoothAdapter.getDefaultAdapter().isMultipleAdvertisementSupported();
    }

    public final boolean hasBluetoothLe() {
        return Build.VERSION.SDK_INT >= 18 && this.context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }
}

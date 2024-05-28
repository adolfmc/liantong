package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.ParcelUuid;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BlueToothUtils {
    public static BlueToothUtils blueToothUtils;
    private BluetoothAdapter bluetoothAdapter;
    private Disposable disposable;
    public boolean isAdapterStage;
    public boolean isFoundDevice;
    private boolean isMatching;
    public setOnBluetoothDeviceFoundListener setOnBluetoothDeviceFoundListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface setOnBluetoothDeviceFoundListener {
        void onDeviceFound(ScanResult scanResult, boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface setonBluetoothAdapterStateChange {
        void onAdapterStateChange(boolean z, boolean z2);
    }

    public static BlueToothUtils getInstance() {
        if (blueToothUtils == null) {
            blueToothUtils = new BlueToothUtils();
        }
        return blueToothUtils;
    }

    public BluetoothAdapter openBluetoothAdapter() {
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return this.bluetoothAdapter;
    }

    public void startDiscovery(ArrayList<String> arrayList, boolean z, int i, String str) {
        if (this.bluetoothAdapter.isDiscovering()) {
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        if (arrayList != null && arrayList.size() > 0) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                arrayList2.add(new ScanFilter.Builder().setServiceUuid(new ParcelUuid(UUID.fromString(arrayList.get(i3)))).build());
            }
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1078030475) {
            if (hashCode != 0) {
                if (hashCode != 107348) {
                    if (hashCode == 3202466 && str.equals("high")) {
                        c = 3;
                    }
                } else if (str.equals("low")) {
                    c = 0;
                }
            } else if (str.equals("")) {
                c = 2;
            }
        } else if (str.equals("medium")) {
            c = 1;
        }
        switch (c) {
            case 0:
                i2 = 2;
                break;
            case 1:
            case 2:
                i2 = 1;
                break;
        }
        this.bluetoothAdapter.getBluetoothLeScanner().startScan(arrayList2, new ScanSettings.Builder().setScanMode(i2).build(), new C90721(i, z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BlueToothUtils$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C90721 extends ScanCallback {
        final /* synthetic */ boolean val$allowDuplicatesKey;
        final /* synthetic */ int val$interval;

        C90721(int i, boolean z) {
            this.val$interval = i;
            this.val$allowDuplicatesKey = z;
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, final ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            if (!BlueToothUtils.this.isFoundDevice || BlueToothUtils.this.setOnBluetoothDeviceFoundListener == null) {
                return;
            }
            BlueToothUtils blueToothUtils = BlueToothUtils.this;
            Observable<Long> observeOn = Observable.interval(this.val$interval, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread());
            final boolean z = this.val$allowDuplicatesKey;
            blueToothUtils.disposable = observeOn.subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.-$$Lambda$BlueToothUtils$1$VvipJIX66aSWHEg4VYvovxbw918
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Long l = (Long) obj;
                    BlueToothUtils.this.setOnBluetoothDeviceFoundListener.onDeviceFound(scanResult, z);
                }
            });
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
        }
    }

    public boolean stopBluetoothDevicesDiscovery() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
            Disposable disposable = this.disposable;
            if (disposable != null) {
                disposable.dispose();
                return true;
            }
            return true;
        }
        return false;
    }

    public BluetoothDevice isBtConDeviceByMac() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.bluetoothAdapter == null) {
            return null;
        }
        Method declaredMethod = BluetoothAdapter.class.getDeclaredMethod("getConnectionState", null);
        declaredMethod.setAccessible(true);
        if (((Integer) declaredMethod.invoke(this.bluetoothAdapter, null)).intValue() == 2) {
            for (BluetoothDevice bluetoothDevice : this.bluetoothAdapter.getBondedDevices()) {
                Method declaredMethod2 = BluetoothDevice.class.getDeclaredMethod("isConnected", null);
                declaredMethod.setAccessible(true);
                if (((Boolean) declaredMethod2.invoke(bluetoothDevice, null)).booleanValue()) {
                    return bluetoothDevice;
                }
            }
        }
        return null;
    }

    public boolean makeBluetoothPair(final String str, final String str2, int i) {
        Observable.create(new ObservableOnSubscribe<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BlueToothUtils.3
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(@NotNull ObservableEmitter<Boolean> observableEmitter) throws Exception {
                BluetoothDevice remoteDevice = BlueToothUtils.this.bluetoothAdapter.getRemoteDevice(str);
                ClsUtils.createBond(BlueToothUtils.this.bluetoothAdapter.getRemoteDevice(str).getClass(), BlueToothUtils.this.bluetoothAdapter.getRemoteDevice(str));
                observableEmitter.onNext(Boolean.valueOf(ClsUtils.setPin(remoteDevice.getClass(), remoteDevice, str2)));
                observableEmitter.onComplete();
            }
        }).timeout(i, TimeUnit.MILLISECONDS).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BlueToothUtils.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    BlueToothUtils.this.isMatching = true;
                }
            }
        });
        return this.isMatching;
    }

    public void setSetOnBluetoothDeviceFoundListener(setOnBluetoothDeviceFoundListener setonbluetoothdevicefoundlistener) {
        this.setOnBluetoothDeviceFoundListener = setonbluetoothdevicefoundlistener;
    }

    public void closeBluetoothAdapter() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.disable();
        }
    }

    public void onBluetoothAdapterStateChange(setonBluetoothAdapterStateChange setonbluetoothadapterstatechange) {
        BluetoothAdapter bluetoothAdapter;
        if (!this.isAdapterStage || (bluetoothAdapter = this.bluetoothAdapter) == null) {
            return;
        }
        setonbluetoothadapterstatechange.onAdapterStateChange(bluetoothAdapter.isEnabled(), this.bluetoothAdapter.isDiscovering());
    }
}

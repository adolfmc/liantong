package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BlueToothUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/CUBluetoothManager")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CUBluetoothManagerJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            new RxPermissions(this.activityContext).request("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION").subscribe(new C90821(this.parameterJO.optString("type")));
        } catch (Exception e) {
            callbackFail("10", "程序异常: " + e.getMessage());
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C90821 implements Consumer<Boolean> {
        final /* synthetic */ String val$type;

        /*  JADX ERROR: Failed to decode insn: 0x0472: FILLED_NEW_ARRAY_RANGE , method: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.1.accept(java.lang.Boolean):void
            java.lang.NullPointerException
            	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:479)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x051A: CONST_STRING r0, method: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.1.accept(java.lang.Boolean):void
            java.lang.IllegalArgumentException: newPosition > limit: (201588852 > 14732120)
            	at java.nio.Buffer.createPositionException(Buffer.java:269)
            	at java.nio.Buffer.position(Buffer.java:244)
            	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
            	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:84)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0556: UNKNOWN(0xCBE3), method: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.1.accept(java.lang.Boolean):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0556: UNKNOWN(0xCBE3)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0562: UNKNOWN(0xCBE7), method: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.1.accept(java.lang.Boolean):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0562: UNKNOWN(0xCBE7)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x056E: UNKNOWN(0xCBEB), method: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.1.accept(java.lang.Boolean):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x056E: UNKNOWN(0xCBEB)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x057A: UNKNOWN(0xCBF0), method: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.1.accept(java.lang.Boolean):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x057A: UNKNOWN(0xCBF0)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0586: UNKNOWN(0xCBF5), method: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.1.accept(java.lang.Boolean):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0586: UNKNOWN(0xCBF5)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x059E: CONST_METHOD_TYPE r203, method: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.1.accept(java.lang.Boolean):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x059E: CONST_METHOD_TYPE r203'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        @Override // io.reactivex.functions.Consumer
        @android.annotation.SuppressLint({"CheckResult"})
        public void accept(java.lang.Boolean r13) throws java.lang.Exception {
            /*
                Method dump skipped, instructions count: 1568
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.C90821.accept(java.lang.Boolean):void");
        }

        C90821(String str) {
            this.val$type = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin$1$1 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class C90831 implements BlueToothUtils.setOnBluetoothDeviceFoundListener {
            final /* synthetic */ JSONArray val$array;
            final /* synthetic */ JSONObject val$object;

            C90831(JSONArray jSONArray, JSONObject jSONObject) {
                this.val$array = jSONArray;
                this.val$object = jSONObject;
            }

            @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BlueToothUtils.setOnBluetoothDeviceFoundListener
            public void onDeviceFound(final ScanResult scanResult, final boolean z) {
                Observable.create(new ObservableOnSubscribe<JSONObject>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.1.1.2
                    @Override // io.reactivex.ObservableOnSubscribe
                    public void subscribe(@NotNull ObservableEmitter<JSONObject> observableEmitter) throws Exception {
                        if (!z && C90831.this.val$array.length() > 0) {
                            if (scanResult.getDevice().getBondState() == 12) {
                                return;
                            }
                            for (int i = 0; i < C90831.this.val$array.length(); i++) {
                                if (C90831.this.val$array.getJSONObject(i).get("deviceId").equals(scanResult.getDevice().getAddress())) {
                                    return;
                                }
                            }
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("name", scanResult.getDevice().getName());
                        jSONObject.put("deviceId", scanResult.getDevice().getAddress());
                        jSONObject.put("RSSI", scanResult.getRssi());
                        C90831.this.val$array.put(jSONObject);
                        C90831.this.val$object.put("devices", C90831.this.val$array);
                        MsLogUtil.m7979d("onBluetoothDeviceFound", C90831.this.val$array.length() + "");
                        observableEmitter.onNext(C90831.this.val$object);
                        observableEmitter.onComplete();
                    }
                }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JSONObject>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin.1.1.1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(JSONObject jSONObject) throws Exception {
                        CUBluetoothManagerJSPlugin.this.callbackSuccess(jSONObject);
                    }
                });
            }
        }

        /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin$1$2 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C90862 implements BlueToothUtils.setonBluetoothAdapterStateChange {
            final /* synthetic */ JSONObject val$object;

            C90862(JSONObject jSONObject) {
                this.val$object = jSONObject;
            }

            @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BlueToothUtils.setonBluetoothAdapterStateChange
            public void onAdapterStateChange(boolean z, boolean z2) {
                try {
                    this.val$object.put("available", z);
                    this.val$object.put("discovering", z2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBluetoothManagerJSPlugin$1$3 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        class C90873 extends BluetoothGattCallback {
            C90873() {
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
                super.onConnectionStateChange(bluetoothGatt, i, i2);
                if (i2 != 0) {
                    if (i2 != 2) {
                        return;
                    }
                    CUBluetoothManagerJSPlugin.this.callbackSuccess(true);
                }
                CUBluetoothManagerJSPlugin.this.callbackSuccess(false);
            }
        }
    }
}

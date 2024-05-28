package com.gmrz.appsdk.commlib;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.bytedance.applog.tracker.Tracker;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.util.Logger;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* renamed from: com.gmrz.appsdk.commlib.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafAppSdkIntent {

    /* renamed from: d */
    protected static final String f10305d = UafAppSdkIntent.class.getSimpleName() + "_fido";

    /* renamed from: e */
    private static UafAppSdkIntent f10306e = null;

    /* renamed from: a */
    private final Map<String, ComponentName> f10307a = new HashMap();

    /* renamed from: b */
    private ComponentName f10308b = null;

    /* renamed from: c */
    private final WeakReference<Context> f10309c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UafAppSdkIntent.java */
    /* renamed from: com.gmrz.appsdk.commlib.d$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class DialogInterface$OnClickListenerC4417a implements DialogInterface.OnClickListener {

        /* renamed from: a */
        final /* synthetic */ CharSequence[] f10310a;

        DialogInterface$OnClickListenerC4417a(CharSequence[] charSequenceArr) {
            this.f10310a = charSequenceArr;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Tracker.onClick(dialogInterface, i);
            UafAppSdkIntent uafAppSdkIntent = UafAppSdkIntent.this;
            uafAppSdkIntent.f10308b = (ComponentName) uafAppSdkIntent.f10307a.get(this.f10310a[i]);
        }
    }

    private UafAppSdkIntent(Context context) {
        this.f10309c = new WeakReference<>(context);
    }

    /* renamed from: b */
    private void m15817b() {
        if (this.f10307a.size() == 0) {
            return;
        }
        int size = this.f10307a.size();
        CharSequence[] charSequenceArr = new CharSequence[size];
        int i = 0;
        for (String str : this.f10307a.keySet()) {
            charSequenceArr[i] = str;
            i++;
        }
        if (size == 1) {
            this.f10308b = this.f10307a.get(charSequenceArr[0]);
            return;
        }
        Context context = this.f10309c.get();
        if (context == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setInverseBackgroundForced(true);
        builder.setTitle("Please choose FIDO client").setItems(charSequenceArr, new DialogInterface$OnClickListenerC4417a(charSequenceArr));
        builder.create().show();
    }

    /* renamed from: a */
    public static UafAppSdkIntent m15820a(Context context) {
        if (f10306e == null) {
            f10306e = new UafAppSdkIntent(context);
        }
        return f10306e;
    }

    /* renamed from: a */
    public void m15822a() {
        if (this.f10307a == null) {
            m15817b();
        }
    }

    /* renamed from: a */
    public SdkIntentResult m15821a(Fragment fragment, Intent intent) {
        PackageManager packageManager = fragment.getActivity().getPackageManager();
        Intent intent2 = new Intent();
        intent2.setAction("org.fidoalliance.intent.FIDO_OPERATION");
        intent2.setType("application/fido.uaf_client+json");
        if (packageManager.queryIntentActivities(intent2, 64).isEmpty()) {
            SdkIntentResult sdkIntentResult = new SdkIntentResult();
            sdkIntentResult.f10297a = FidoStatus.NOT_INSTALLED;
            return sdkIntentResult;
        }
        return m15816b(fragment, intent);
    }

    /* renamed from: b */
    private SdkIntentResult m15816b(Fragment fragment, Intent intent) {
        String str = f10305d;
        Logger.m15757e(str, "SDK caller activity AconCreate");
        int nextInt = new Random().nextInt(32767) + 1;
        Logger.m15757e(str, "requestCode: " + nextInt);
        SdkIntentResult sdkIntentResult = new SdkIntentResult();
        sdkIntentResult.f10297a = FidoStatus.SUCCESS;
        intent.setAction("org.fidoalliance.intent.FIDO_OPERATION");
        intent.setComponent(this.f10308b);
        intent.setType("application/fido.uaf_client+json");
        try {
            fragment.startActivityForResult(intent, nextInt);
            return sdkIntentResult;
        } catch (Exception e) {
            e.printStackTrace();
            this.f10307a.remove(this.f10308b);
            this.f10308b = null;
            sdkIntentResult.f10297a = FidoStatus.NOT_INSTALLED;
            return sdkIntentResult;
        }
    }
}

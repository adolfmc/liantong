package com.fido.android.framework.service;

import android.content.Context;
import android.content.res.Resources;
import com.gmrz.android.client.utils.Charsets;
import com.gmrz.android.client.utils.Logger;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Mfac implements IMfac {
    private static final String TAG = "Mfac";
    protected static IMfac mFido;
    private final Context mAppContext;
    private JSONObject mMfacConfig = null;

    @Override // com.fido.android.framework.service.IMfac
    public boolean isEmbedded() {
        return true;
    }

    @Override // com.fido.android.framework.service.IMfac
    public boolean isEnabled() {
        return true;
    }

    @Override // com.fido.android.framework.service.IMfac
    public void showEulaDialog() {
    }

    protected Mfac(Context context) {
        this.mAppContext = context;
        readMfacConfig();
    }

    public static IMfac Instance() {
        return mFido;
    }

    public static void init(Context context) {
        if (mFido == null) {
            mFido = new Mfac(context);
        }
    }

    @Override // com.fido.android.framework.service.IMfac
    public Context getContext() {
        return this.mAppContext;
    }

    @Override // com.fido.android.framework.service.IMfac
    public String[] getAllowedSSLProtocols() {
        if (this.mMfacConfig.length() > 0 && this.mMfacConfig.has("allowedSSLProtocols")) {
            try {
                JSONArray jSONArray = this.mMfacConfig.getJSONArray("allowedSSLProtocols");
                String[] strArr = new String[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    strArr[i] = jSONArray.getString(i);
                }
                return strArr;
            } catch (JSONException unused) {
                Logger.m15883w(TAG, "Could not read allowedSSLProtocols");
            }
        }
        Logger.m15883w(TAG, "Using default value (null)");
        return null;
    }

    @Override // com.fido.android.framework.service.IMfac
    public boolean usePreloadedOnly() {
        try {
            return this.mMfacConfig.getBoolean("usePreloadedOnly");
        } catch (JSONException unused) {
            Logger.m15883w(TAG, "Could not read usePreloadedOnly");
            Logger.m15883w(TAG, "Using default value (false)");
            return false;
        }
    }

    @Override // com.fido.android.framework.service.IMfac
    public boolean useAIDLService() {
        try {
            return this.mMfacConfig.getBoolean("useAIDLService");
        } catch (JSONException unused) {
            Logger.m15883w(TAG, "Could not read the value of useAIDLService field from mfac_config.json");
            Logger.m15883w(TAG, "Using default value (true)");
            return true;
        }
    }

    @Override // com.fido.android.framework.service.IMfac
    public boolean useSafetyNet() {
        try {
            return this.mMfacConfig.getBoolean("useSafetyNet");
        } catch (JSONException unused) {
            Logger.m15883w(TAG, "Could not read the value of useSafetyNet field from mfac_config.json");
            Logger.m15883w(TAG, "Using default value (false)");
            return false;
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [android.content.res.Resources, java.io.InputStream] */
    private void readMfacConfig() {
        this.mMfacConfig = new JSONObject();
        ?? resources = this.mAppContext.getResources();
        try {
            try {
                InputStream openRawResource = resources.openRawResource(resources.getIdentifier("mfac_config", "raw", this.mAppContext.getPackageName()));
                try {
                    byte[] bArr = new byte[openRawResource.available()];
                    openRawResource.read(bArr);
                    this.mMfacConfig = new JSONObject(new String(bArr, Charsets.utf8Charset));
                    if (openRawResource == null) {
                        return;
                    }
                } catch (IOException unused) {
                    Logger.m15883w(TAG, "Could not read MFAC configuration file");
                    if (openRawResource == null) {
                        return;
                    }
                } catch (JSONException unused2) {
                    Logger.m15883w(TAG, "Could not parse MFAC configuration file");
                    if (openRawResource == null) {
                        return;
                    }
                }
                try {
                    openRawResource.close();
                } catch (IOException unused3) {
                    Logger.m15883w(TAG, "Could not close MFAC configuration file");
                }
            } catch (Throwable th) {
                if (resources != 0) {
                    try {
                        resources.close();
                    } catch (IOException unused4) {
                        Logger.m15883w(TAG, "Could not close MFAC configuration file");
                    }
                }
                throw th;
            }
        } catch (Resources.NotFoundException unused5) {
            Logger.m15883w(TAG, "MFAC configuration file not found");
        }
    }
}

package com.gmrz.appsdk.commlib;

import android.os.Parcel;
import android.os.Parcelable;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UAFMessage implements Parcelable {
    public static final Parcelable.Creator<UAFMessage> CREATOR = new C4410a();

    /* renamed from: a */
    public String f10272a;

    /* renamed from: b */
    public String f10273b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.gmrz.appsdk.commlib.UAFMessage$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C4410a implements Parcelable.Creator<UAFMessage> {
        C4410a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public UAFMessage createFromParcel(Parcel parcel) {
            return new UAFMessage(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public UAFMessage[] newArray(int i) {
            return new UAFMessage[i];
        }
    }

    /* synthetic */ UAFMessage(Parcel parcel, C4410a c4410a) {
        this(parcel);
    }

    /* renamed from: a */
    public String m15847a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uafProtocolMessage", this.f10272a);
            jSONObject.put("additionalData", this.f10273b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10272a);
        parcel.writeString(this.f10273b);
    }

    public UAFMessage() {
        this.f10272a = null;
        this.f10273b = null;
    }

    public UAFMessage(String str) {
        this.f10272a = null;
        this.f10273b = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("uafProtocolMessage")) {
                this.f10272a = jSONObject.getString("uafProtocolMessage");
            }
            if (jSONObject.has("additionalData")) {
                this.f10273b = jSONObject.getString("additionalData");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private UAFMessage(Parcel parcel) {
        this.f10272a = null;
        this.f10273b = null;
        this.f10272a = parcel.readString();
        this.f10273b = parcel.readString();
    }
}

package com.fido.uaf.ver0100.types;

import android.util.Base64;
import com.fido.uaf.ver0100.message.UafJson;
import com.gmrz.android.client.utils.Charsets;
import com.google.gson.annotations.Expose;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FinalChallengeParams extends UafJson {
    @Expose
    public String appID;
    @Expose
    public String challenge;
    @Expose
    public ChannelBinding channelBinding;
    @Expose
    public String facetID;

    public FinalChallengeParams() {
    }

    public FinalChallengeParams(String str, String str2, String str3, ChannelBinding channelBinding) {
        this.appID = str;
        this.challenge = str2;
        this.facetID = str3;
        this.channelBinding = channelBinding;
    }

    public String getFinalChallenge() {
        return Base64.encodeToString(stringify().getBytes(Charsets.utf8Charset), 11);
    }

    public String getAppID() {
        return this.appID;
    }

    public void setAppID(String str) {
        this.appID = str;
    }

    public String getChallenge() {
        return this.challenge;
    }

    public void setChallenge(String str) {
        this.challenge = str;
    }

    public String getFacetID() {
        return this.facetID;
    }

    public void setFacetID(String str) {
        this.facetID = str;
    }

    public ChannelBinding getTlsData() {
        return this.channelBinding;
    }

    public void setTlsData(ChannelBinding channelBinding) {
        this.channelBinding = channelBinding;
    }
}
